package net.niek.tutorialmod.curse;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurseManager {
    private static int curseTickCounter = 0;
    private static final int curseCheckTick = 20;  // Threshold is 6 blocks
    private static final int curseThreshold = 6;  // Threshold is 6 blocks

    public static final String MOD_ID = "tutorialmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void initialize() {
        ServerTickEvents.START_SERVER_TICK.register(CurseManager::onServerTick);
    }

    private static void onServerTick(MinecraftServer server) {
        curseTickCounter++;
        if (curseTickCounter > curseCheckTick) {
            LOGGER.info("Curse Check!");
            curseTickCounter = 0;
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                double curseHeight = PlayerPositionTracker.getExcessHeight(player, curseThreshold);
                LOGGER.info(String.valueOf(curseHeight));

                if (curseHeight >= curseThreshold) {
                    LOGGER.info("CURSED!");
                    CurseApplier.applyCurse(player, curseHeight);
                }
            }
        }
    }
}
