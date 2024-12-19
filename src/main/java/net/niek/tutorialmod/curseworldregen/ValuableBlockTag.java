package net.niek.tutorialmod.curseworldregen;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ValuableBlockTag {
    private static final Map<BlockPos, Block> valuableBlocks = new ConcurrentHashMap<>();

    public static void trackValuableBlock(BlockPos pos, Block block) {
        valuableBlocks.put(pos, block);
    }

    public static Block getValuableBlock(BlockPos pos) {
        return valuableBlocks.get(pos);
    }

    public static boolean isValuableBlock(BlockPos pos) {
        return valuableBlocks.containsKey(pos);
    }
}
