package net.niek.tutorialmod.curse;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class CurseManager {
    private static int curseTickCounter = 0;
    private static final int curseCheckTick = 20;  // Threshold is 6 blocks
    private static final int curseThreshold = 6;  // Threshold is 6 blocks

    public static void initialize() {
        ServerTickEvents.START_SERVER_TICK.register(CurseManager::onServerTick);
    }

    private static void onServerTick(MinecraftServer server) {
        curseTickCounter++;
        if (curseTickCounter > curseCheckTick) {
            curseTickCounter = 0;
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                double curseHeight = PlayerPositionTracker.getExcessHeight(player, curseThreshold);

                if (curseHeight >= curseThreshold) {
                    CurseApplier.applyCurse(player, curseHeight);
                }
            }
        }
    }
}
