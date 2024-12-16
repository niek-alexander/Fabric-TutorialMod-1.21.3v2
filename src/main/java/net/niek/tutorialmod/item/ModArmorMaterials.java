package net.niek.tutorialmod.item;

import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.niek.tutorialmod.TutorialMod;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.niek.tutorialmod.util.ModTags;
//ModTags.Items.COMPRESSED_OBSIDIAN_SHARD_REPAIR,

import java.util.EnumMap;


public class ModArmorMaterials {
    public static final ArmorMaterial TOUGH_HIDE_ARMOR_MATERIAL = new ArmorMaterial(6,
            Util.make(new EnumMap<>(EquipmentType.class), map -> {
                map.put(EquipmentType.BOOTS, 1);
                map.put(EquipmentType.LEGGINGS, 2);
                map.put(EquipmentType.CHESTPLATE, 3);
                map.put(EquipmentType.HELMET, 2);
                map.put(EquipmentType.BODY, 4);
            }), 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ModTags.Items.COMPRESSED_OBSIDIAN_SHARD_REPAIR,
                    Identifier.of(TutorialMod.MOD_ID, "tough_hide"));
}

//wow!