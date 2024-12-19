package net.niek.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.niek.tutorialmod.block.ModBlocks;
import net.niek.tutorialmod.curseworldregen.BlockEventHandler;
import net.niek.tutorialmod.item.ModItemGroups;
import net.niek.tutorialmod.item.ModItems;
import net.niek.tutorialmod.curse.CurseManager;
import net.niek.tutorialmod.world.gen.ModWorldGeneration;
import net.niek.tutorialmod.curseworldregen.BlockRegenerationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModWorldGeneration.generateModWorldGen();
		CurseManager.initialize();
		BlockEventHandler.registerEvents();
		BlockRegenerationManager.initialize();
	}
}