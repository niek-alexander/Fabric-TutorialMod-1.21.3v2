package net.niek.tutorialmod.item;

import net.minecraft.item.ToolMaterial;
import net.niek.tutorialmod.util.ModTags;

public class ModToolMaterials {
    public static final ToolMaterial COMPRESSED_OBSIDIAN_SHARD =
            new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_COMPRESSED_OBSIDIAN_TOOL,
                    2000,
                    10f,
                    0.1f,
                    1,
                    ModTags.Items.COMPRESSED_OBSIDIAN_SHARD_REPAIR);
}
