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

import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;

public class BlockRegenerationManager {
    private static final PriorityQueue<ScheduledBlock> blockQueue = new PriorityQueue<>();
    private static final Set<RegistryKey<World>> importantDimensions = new HashSet<>();
    private static final Logger LOGGER = LoggerFactory.getLogger("tutorialmod");
    private static int tickCounter = 0;

    // Inner class for scheduled blocks
    private static class ScheduledBlock implements Comparable<ScheduledBlock> {
        public final BlockPos pos;
        public final Block originalBlock;
        public final long scheduledTime;

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

        long currentTime = world.getTime();
        long delayTicks = isValuable ? 24 * 60 * 60 * 20 : 3 * 60 * 20;
        long scheduledTime = currentTime + delayTicks;

        blockQueue.add(new ScheduledBlock(pos, originalBlock, scheduledTime));
        LOGGER.info("Scheduled regeneration for block at {}.", pos);
    }

    private static void processQueue(ServerWorld world) {
        long currentTime = world.getTime();

        while (!blockQueue.isEmpty() && blockQueue.peek().scheduledTime <= currentTime) {
            ScheduledBlock block = blockQueue.poll();
            if (world.getBlockState(block.pos).isAir()) {
                world.setBlockState(block.pos, block.originalBlock.getDefaultState());
                LOGGER.info("Regenerated block at {}.", block.pos);
            }
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
