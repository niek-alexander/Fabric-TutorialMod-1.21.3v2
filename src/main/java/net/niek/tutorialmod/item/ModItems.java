package net.niek.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SwordItem;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.niek.tutorialmod.TutorialMod;
import net.niek.tutorialmod.item.custom.EmptyJarItem;
import net.niek.tutorialmod.item.custom.SlopFruitItem;
import net.niek.tutorialmod.item.custom.SlopJarItem;


public class ModItems {
    public static final Item MOSAIC_SHARD = registeritem("mosaic_shard", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID,"mosaic_shard")))));

    public static final Item COMPRESSED_OBSIDIAN_SHARD = registeritem("compressed_obsidian_shard", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID,"compressed_obsidian_shard")))));

    public static final Item TOUGH_HIDE = registeritem("tough_hide", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID,"tough_hide")))));

    public static final Item SLOPJAR = registeritem("slopjar", new SlopJarItem(new Item.Settings()
            .maxCount(1)
            .maxDamage(251)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID,"slopjar")))));

    public static final Item EMPTYJAR = registeritem("empty_jar", new EmptyJarItem(new Item.Settings()
            .maxCount(8)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID,"empty_jar")))));

    public static final Item SLOPFRUIT = registeritem("slop_fruit", new SlopFruitItem(new Item.Settings()
            .maxCount(3)
            .maxDamage(100)
            .food(new FoodComponent.Builder()
                    .nutrition(3)
                    .saturationModifier(3)
                    .build())
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID,"slop_fruit")))));


    public static final Item COMPRESSED_OBSIDIAN_SWORD = registeritem("compressed_obsidian_sword",
            new SwordItem(ModToolMaterials.COMPRESSED_OBSIDIAN_SHARD, 3, -2.4f, new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID,"compressed_obsidian_sword")))));




    public static final Item TOUGH_HIDE_HELMET = registeritem("tough_hide_helmet",
            new ArmorItem(ModArmorMaterials.TOUGH_HIDE_ARMOR_MATERIAL, EquipmentType.HELMET, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID,"tough_hide_helmet")))));
    public static final Item TOUGH_HIDE_CHESTPLATE = registeritem("tough_hide_chestplate",
            new ArmorItem(ModArmorMaterials.TOUGH_HIDE_ARMOR_MATERIAL, EquipmentType.CHESTPLATE, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID,"tough_hide_chestplate")))));
    public static final Item TOUGH_HIDE_LEGGINGS = registeritem("tough_hide_leggings",
            new ArmorItem(ModArmorMaterials.TOUGH_HIDE_ARMOR_MATERIAL, EquipmentType.LEGGINGS, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID,"tough_hide_leggings")))));
    public static final Item TOUGH_HIDE_BOOTS = registeritem("tough_hide_boots",
                new ArmorItem(ModArmorMaterials.TOUGH_HIDE_ARMOR_MATERIAL, EquipmentType.BOOTS, new Item.Settings()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID,"tough_hide_boots")))));









    public static void registerFuels () {
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            // You can add multiple items at once in this lambda.
            builder.add(SLOPFRUIT, 100);
        });
    }

    private static Item registeritem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for: " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries ->  {
            entries.add(MOSAIC_SHARD);
            entries.add(TOUGH_HIDE);
            entries.add(MOSAIC_SHARD);

//            entries.add(COMPRESSED_OBSIDIAN_SWORD);
            entries.add(COMPRESSED_OBSIDIAN_SHARD);
        });
    }
}
