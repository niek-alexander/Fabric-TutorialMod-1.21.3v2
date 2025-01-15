package net.niek.tutorialmod.curseworldregen;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ValuableBlockTag {
    private static final Map<BlockPos, Block> valuableBlocks = new ConcurrentHashMap<>();

    public static boolean isValuableBlock(Block block) {
        return (block == Blocks.DIAMOND_BLOCK);
    }
}
