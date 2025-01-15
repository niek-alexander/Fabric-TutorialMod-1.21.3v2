package net.niek.tutorialmod.curseworldregen;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger("tutorialmod");

    public static void registerEvents() {
        LOGGER.info("Registering block event handlers...");

        // Handle block breaking
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if (!world.isClient() && world instanceof ServerWorld serverWorld) {
                Block block = state.getBlock();
                boolean isValuable = ValuableBlockTag.isValuableBlock(block);

                BlockRegenerationManager.scheduleRegeneration(serverWorld, pos, block, isValuable);
            }
        });

        // Handle block placement
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (!world.isClient() && world instanceof ServerWorld serverWorld) {
                BlockPos pos = hitResult.getBlockPos();
                Direction direction = hitResult.getSide();
                BlockPos placedBlockPos = pos.offset(direction);
                Block placedBlock = world.getBlockState(placedBlockPos).getBlock();

                boolean isValuable = ValuableBlockTag.isValuableBlock(placedBlock);
                BlockRegenerationManager.scheduleRegeneration(serverWorld, placedBlockPos, placedBlock, isValuable);

                LOGGER.info("Tracked block placed by player at {}", pos);
            }
            return ActionResult.PASS;
        });
    }
}