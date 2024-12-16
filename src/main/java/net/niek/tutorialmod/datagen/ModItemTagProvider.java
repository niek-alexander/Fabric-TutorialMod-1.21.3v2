package net.niek.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.niek.tutorialmod.util.ModTags;
import net.niek.tutorialmod.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.UNSET_TAG_ITEMS)
                .add(ModItems.SLOPFRUIT);

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.COMPRESSED_OBSIDIAN_SWORD);

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.TOUGH_HIDE_HELMET)
                .add(ModItems.TOUGH_HIDE_CHESTPLATE)
                .add(ModItems.TOUGH_HIDE_LEGGINGS)
                .add(ModItems.TOUGH_HIDE_BOOTS);
    }
}
