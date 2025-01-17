package net.niek.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.niek.tutorialmod.TutorialMod;
import net.niek.tutorialmod.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup CHALLENGE_PIT_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TutorialMod.MOD_ID, "challenge_pit_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.MOSAIC_SHARD))
                    .displayName(Text.translatable("itemgroup.tutorialmod.challenge_pit_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.MOSAIC_SHARD);
                        entries.add(ModItems.TOUGH_HIDE);
                        entries.add(ModItems.COMPRESSED_OBSIDIAN_SHARD);

                        entries.add(ModItems.SLOPJAR);
                        entries.add(ModItems.EMPTYJAR);
                        entries.add(ModItems.SLOPFRUIT);

                        entries.add(ModItems.COMPRESSED_OBSIDIAN_SWORD);

                        entries.add(ModItems.TOUGH_HIDE_HELMET);
                        entries.add(ModItems.TOUGH_HIDE_CHESTPLATE);
                        entries.add(ModItems.TOUGH_HIDE_LEGGINGS);
                        entries.add(ModItems.TOUGH_HIDE_BOOTS);
                    }).build());

    public static final ItemGroup CHALLENGE_PIT_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TutorialMod.MOD_ID, "challenge_pit_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.SLOP_LEAF_BLOCK))
                    .displayName(Text.translatable("itemgroup.tutorialmod.challenge_pit_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.SLOP_LEAF_BLOCK);
                        entries.add(ModBlocks.COMPRESSED_OBSIDIAN_BLOCK);
                        entries.add(ModBlocks.COMPRESSED_OBSIDIAN_SHARD_BLOCK);
                        entries.add(ModBlocks.SPIDER_EGG_BLOCK);

                    }).build());


    public static void registerItemGroups(){
        TutorialMod.LOGGER.info("Registering Item Groups for: " + TutorialMod.MOD_ID);
    }
}
