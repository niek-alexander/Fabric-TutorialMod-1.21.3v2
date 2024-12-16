package net.niek.tutorialmod.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.niek.tutorialmod.TutorialMod;

public class ModTags {

    public static class Blocks{
        public static final TagKey<Block> NEEDS_COMPRESSED_OBSIDIAN_TOOL = createTag("needs_compressed_obsidian_tool");
        public static final TagKey<Block> INCORRECT_FOR_COMPRESSED_OBSIDIAN_TOOL = createTag("incorrect_for_compressed_obsidian_tool");


        private static TagKey<Block> createTag(String name){
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name));
        }
    }

    public static class Items{
        public static final TagKey<Item> UNSET_TAG_ITEMS = createTag("unset_tag_items");
        public static final TagKey<Item> COMPRESSED_OBSIDIAN_SHARD_REPAIR = createTag("compressed_obsidian_shard_repair");

        private static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, name));
        }
    }
}
