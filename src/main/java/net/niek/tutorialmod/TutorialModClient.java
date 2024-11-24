package net.niek.tutorialmod;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.RenderLayer;
import net.niek.tutorialmod.block.ModBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;


public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SLOP_LEAF_BLOCK, RenderLayer.getCutout());

    }
}
