package net.niek.tutorialmod.curseworldregen;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.niek.tutorialmod.world.dimension.ModDimensions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;

public class BlockRegenerationManager {
    private static final PriorityQueue<ScheduledBlock> blockQueue = new PriorityQueue<>();
    private static final Map<BlockPos, ScheduledBlock> activeTasks = new ConcurrentHashMap<>(); // Track active regeneration tasks
    private static final Set<RegistryKey<World>> importantDimensions = new HashSet<>();
    private static final Logger LOGGER = LoggerFactory.getLogger("tutorialmod");
    private static int tickCounter = 0;

    private static class ScheduledBlock implements Comparable<ScheduledBlock> {
        public final BlockPos pos;
        public final Block originalBlock;
        public long scheduledTime; // The scheduled time can be updated

        public ScheduledBlock(BlockPos pos, Block block, long scheduledTime) {
            this.pos = pos;
            this.originalBlock = block;
            this.scheduledTime = scheduledTime;
        }

        @Override
        public int compareTo(ScheduledBlock other) {
            return Long.compare(this.scheduledTime, other.scheduledTime);
        }
    }

    public static void initialize() {
        importantDimensions.add(ModDimensions.DARKDIM_LEVEL_KEY);
        ServerTickEvents.START_SERVER_TICK.register(BlockRegenerationManager::onServerTick);
        LOGGER.info("Block regeneration system initialized.");
    }

    public static void scheduleRegeneration(ServerWorld world, BlockPos pos, Block originalBlock, boolean isValuable) {
        if (!importantDimensions.contains(world.getRegistryKey())) return;
        if (activeTasks.containsKey(pos)) {
            LOGGER.info("Block at {} is already scheduled for regeneration.", pos);
            return; // Skip scheduling if the block is already in the queue
        }

        long currentTime = world.getTime();
        long delayTicks = isValuable ? 6 * 60 * 20 : 60 * 20;
        long scheduledTime = currentTime + delayTicks;

        ScheduledBlock scheduledBlock = new ScheduledBlock(pos, originalBlock, scheduledTime);
        blockQueue.add(scheduledBlock);
        activeTasks.put(pos, scheduledBlock); // Track active regeneration task

        LOGGER.info("Scheduled regeneration for block at {}.", pos);
    }

    private static void processQueue(ServerWorld world) {
        long currentTime = world.getTime();

        while (!blockQueue.isEmpty() && blockQueue.peek().scheduledTime <= currentTime) {
            ScheduledBlock block = blockQueue.poll();
            activeTasks.remove(block.pos); // Remove from active tasks
            world.setBlockState(block.pos, block.originalBlock.getDefaultState());
            LOGGER.info("Regenerated block at {}.", block.pos);
        }
    }

    private static void onServerTick(MinecraftServer server) {
        tickCounter++;
        if (tickCounter >= 200) { // Every 10 seconds
            tickCounter = 0;
            for (ServerWorld world : server.getWorlds()) {
                if (importantDimensions.contains(world.getRegistryKey())) {
                    processQueue(world);
                }
            }
        }
    }
}
