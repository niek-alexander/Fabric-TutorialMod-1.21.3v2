package net.niek.tutorialmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.niek.tutorialmod.TutorialMod;
import net.niek.tutorialmod.block.custom.SpiderEggBlock;

public class ModBlocks {
    public static final Block SLOP_LEAF_BLOCK = registerBlock("slop_leaf_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(0.2F)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AZALEA_LEAVES)
                    .nonOpaque()
                    .blockVision(Blocks::never)
                    .solidBlock(Blocks::never)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, "slop_leaf_block")))
            )
    );

    public static final Block COMPRESSED_OBSIDIAN_BLOCK = registerBlock("compressed_obsidian_block",
            new ExperienceDroppingBlock(UniformIntProvider.create(3,7),
                    AbstractBlock.Settings.create()
                    .strength(25f,3000f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DRIPSTONE_BLOCK)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, "compressed_obsidian_block")))
            )
    );

    public static final Block COMPRESSED_OBSIDIAN_SHARD_BLOCK = registerBlock("compressed_obsidian_shard_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(155F,12000f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.POLISHED_DEEPSLATE)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, "compressed_obsidian_shard_block")))
            )
    );

    public static final Block SPIDER_EGG_BLOCK = registerBlock("spider_egg_block",
            new SpiderEggBlock(AbstractBlock.Settings.create()
                    .strength(.8F)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.COBWEB)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, "spider_egg_block")))
            )
    );


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name));
        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, name))
        )));
    }

    public static void registerModBlocks() {
        TutorialMod.LOGGER.info("Registering Mod Blocks for: " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.SLOP_LEAF_BLOCK);
            entries.add(ModBlocks.COMPRESSED_OBSIDIAN_BLOCK);
            entries.add(ModBlocks.COMPRESSED_OBSIDIAN_SHARD_BLOCK);
            entries.add(ModBlocks.SPIDER_EGG_BLOCK);

        });
    }
}