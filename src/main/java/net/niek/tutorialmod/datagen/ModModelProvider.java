package net.niek.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.niek.tutorialmod.block.ModBlocks;
import net.niek.tutorialmod.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.COMPRESSED_OBSIDIAN_BLOCK);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.COMPRESSED_OBSIDIAN_SHARD_BLOCK);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SLOP_LEAF_BLOCK);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SPIDER_EGG_BLOCK);


        blockStateModelGenerator.registerParentedItemModel(ModBlocks.COMPRESSED_OBSIDIAN_BLOCK,
                ModelIds.getBlockModelId(ModBlocks.COMPRESSED_OBSIDIAN_BLOCK));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.COMPRESSED_OBSIDIAN_SHARD_BLOCK,
                ModelIds.getBlockModelId(ModBlocks.COMPRESSED_OBSIDIAN_SHARD_BLOCK));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.SLOP_LEAF_BLOCK,
                ModelIds.getBlockModelId(ModBlocks.SLOP_LEAF_BLOCK));
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.SPIDER_EGG_BLOCK,
                ModelIds.getBlockModelId(ModBlocks.SPIDER_EGG_BLOCK));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.COMPRESSED_OBSIDIAN_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMPTYJAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.MOSAIC_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.SLOPFRUIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SLOPJAR, Models.GENERATED);
    }
}