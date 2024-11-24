package net.niek.tutorialmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.niek.tutorialmod.TutorialMod;

public class ModBlocks {
    public static final Block SLOP_LEAF_BLOCK = registerBlock("slop_leaf_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(0.2F)
                    .sounds(BlockSoundGroup.AZALEA_LEAVES)
                    .nonOpaque()
                    .blockVision(Blocks ::never)
                    .solidBlock(Blocks::never)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, "slop_leaf_block")))
            )
    );

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name));
        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static void registerBlockItem(String name, Block block) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, name));
        Registry.register(Registries.ITEM, itemKey, new BlockItem(block, new Item.Settings()
                .registryKey(itemKey)
        ));
    }

    public static void registerModBlocks() {
        TutorialMod.LOGGER.info("Registering Mod Blocks for: " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.SLOP_LEAF_BLOCK );
        });
    }
}