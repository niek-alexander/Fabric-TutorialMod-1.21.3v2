package net.niek.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.niek.tutorialmod.block.ModBlocks;
import net.niek.tutorialmod.item.ModItems;


import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COMPRESSED_OBSIDIAN_SHARD_BLOCK)
                        .pattern("SSS")
                        .pattern("SSS")
                        .pattern("SSS")
                        .input('S', ModItems.COMPRESSED_OBSIDIAN_SHARD)
                        .criterion(hasItem(ModItems.COMPRESSED_OBSIDIAN_SHARD), conditionsFromItem(ModItems.COMPRESSED_OBSIDIAN_SHARD))
                        .offerTo(exporter);
                createShaped(RecipeCategory.MISC, ModItems.EMPTYJAR, 2)
                        .pattern("G G")
                        .pattern("GGG")
                        .input('G', Blocks.GLASS)
                        .criterion(hasItem(Blocks.GLASS), conditionsFromItem(Blocks.GLASS))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "Tutorial Mod Recipes";
    }
}


