package net.niek.tutorialmod;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.RenderLayer;
import net.niek.tutorialmod.block.ModBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.niek.tutorialmod.item.ModItems;


public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SLOP_LEAF_BLOCK, RenderLayer.getCutout());

        ModItems.registerFuels();
    }
}
