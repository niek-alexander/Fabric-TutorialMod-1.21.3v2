package net.niek.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.niek.tutorialmod.block.ModBlocks;
import net.niek.tutorialmod.util.ModTags;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.COMPRESSED_OBSIDIAN_BLOCK)
                .add(ModBlocks.COMPRESSED_OBSIDIAN_SHARD_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.COMPRESSED_OBSIDIAN_BLOCK)
                .add(ModBlocks.COMPRESSED_OBSIDIAN_SHARD_BLOCK);

        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_COMPRESSED_OBSIDIAN_TOOL)
                .add(Blocks.OBSIDIAN)
                .add(Blocks.CRYING_OBSIDIAN)
                .add(ModBlocks.COMPRESSED_OBSIDIAN_BLOCK)
                .add(ModBlocks.COMPRESSED_OBSIDIAN_SHARD_BLOCK)
                .forceAddTag(BlockTags.NEEDS_IRON_TOOL);


//        getOrCreateTagBuilder(ModTags.Blocks.INCORRECT_FOR_COMPRESSED_OBSIDIAN_TOOL);

    }
}
