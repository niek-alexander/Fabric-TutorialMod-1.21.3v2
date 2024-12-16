package net.niek.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.equipment.EquipmentModel;
import net.minecraft.util.Identifier;
import net.niek.tutorialmod.TutorialMod;
import net.niek.tutorialmod.block.ModBlocks;
import net.niek.tutorialmod.item.ModItems;
import org.apache.commons.logging.Log;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator bmg) { //bmg = blockStateModelGenerator
        bmg.registerCubeAllModelTexturePool(ModBlocks.COMPRESSED_OBSIDIAN_BLOCK);
        bmg.registerCubeAllModelTexturePool(ModBlocks.COMPRESSED_OBSIDIAN_SHARD_BLOCK);
        bmg.registerCubeAllModelTexturePool(ModBlocks.SLOP_LEAF_BLOCK);
        bmg.registerCubeAllModelTexturePool(ModBlocks.SPIDER_EGG_BLOCK);
        
        bmg.registerParentedItemModel(ModBlocks.COMPRESSED_OBSIDIAN_BLOCK, ModelIds.getBlockModelId(ModBlocks.COMPRESSED_OBSIDIAN_BLOCK));
        bmg.registerParentedItemModel(ModBlocks.COMPRESSED_OBSIDIAN_SHARD_BLOCK, ModelIds.getBlockModelId(ModBlocks.COMPRESSED_OBSIDIAN_SHARD_BLOCK));
        bmg.registerParentedItemModel(ModBlocks.SLOP_LEAF_BLOCK, ModelIds.getBlockModelId(ModBlocks.SLOP_LEAF_BLOCK));
        bmg.registerParentedItemModel(ModBlocks.SPIDER_EGG_BLOCK, ModelIds.getBlockModelId(ModBlocks.SPIDER_EGG_BLOCK));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        TutorialMod.LOGGER.info("CREATING MODELS");

        itemModelGenerator.register(ModItems.COMPRESSED_OBSIDIAN_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOUGH_HIDE, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMPTYJAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.MOSAIC_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.SLOPFRUIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SLOPJAR, Models.GENERATED);


        itemModelGenerator.register(ModItems.COMPRESSED_OBSIDIAN_SWORD, Models.HANDHELD);


        //----------------[Armor]-----------------------
        itemModelGenerator.registerArmor(ModItems.TOUGH_HIDE_HELMET, Identifier.of(TutorialMod.MOD_ID, "tough_hide"),
                EquipmentModel.builder().addHumanoidLayers(Identifier.of(TutorialMod.MOD_ID, "tough_hide")).build(), EquipmentSlot.HEAD);
        itemModelGenerator.registerArmor(ModItems.TOUGH_HIDE_CHESTPLATE, Identifier.of(TutorialMod.MOD_ID, "tough_hide"),
                EquipmentModel.builder().addHumanoidLayers(Identifier.of(TutorialMod.MOD_ID, "tough_hide")).build(), EquipmentSlot.CHEST);
        itemModelGenerator.registerArmor(ModItems.TOUGH_HIDE_LEGGINGS, Identifier.of(TutorialMod.MOD_ID, "tough_hide"),
                EquipmentModel.builder().addHumanoidLayers(Identifier.of(TutorialMod.MOD_ID, "tough_hide")).build(), EquipmentSlot.LEGS);
        itemModelGenerator.registerArmor(ModItems.TOUGH_HIDE_BOOTS, Identifier.of(TutorialMod.MOD_ID, "tough_hide"),
                EquipmentModel.builder().addHumanoidLayers(Identifier.of(TutorialMod.MOD_ID, "tough_hide")).build(), EquipmentSlot.FEET);
        //----------------[Armor]-----------------------

    }
}