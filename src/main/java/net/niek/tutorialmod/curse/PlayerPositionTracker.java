package net.niek.tutorialmod.curse;

import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.UUID;

public class PlayerPositionTracker {
    private static final HashMap<UUID, Double> playerYPositions = new HashMap<>();
    private static final HashMap<UUID, Integer> simulatedYPositions = new HashMap<>();

    public static double getExcessHeight(ServerPlayerEntity player, double threshold) {
        UUID playerId = player.getUuid();
        double currentY = player.getY();

        playerYPositions.putIfAbsent(playerId, currentY);
        simulatedYPositions.putIfAbsent(playerId, (int) currentY);

        double previousY = playerYPositions.get(playerId);
        int simulatedY = simulatedYPositions.get(playerId);
        double deltaY = currentY - previousY;


        if (simulatedY < currentY) {        //slowly ascend
            ++simulatedY;
        } else if (simulatedY > currentY) { //snap to pos, we only care about the lowest point
            simulatedY = (int) currentY;
        }
        simulatedYPositions.put(playerId, simulatedY);


        if (currentY < previousY) {
            playerYPositions.put(playerId, currentY);
        }

        if (deltaY > threshold) {
            playerYPositions.put(playerId, currentY);
            return deltaY;
        } else if ((currentY - simulatedY) > threshold) {
            playerYPositions.put(playerId, currentY);
            return currentY - simulatedY;
        }
        return 0;
    }
}
