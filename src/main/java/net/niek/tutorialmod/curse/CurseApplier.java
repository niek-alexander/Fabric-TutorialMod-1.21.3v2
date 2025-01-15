package net.niek.tutorialmod.curse;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;


public class CurseApplier {
    public static void applyCurse(ServerPlayerEntity player, double curseHeight) {
        int DEFAULT_THRESHOLD = 6; // Default threshold in blocks
        int DEFAULT_DURATION = 200; // 20 ticks in second

        RegistryKey<World> playerDimension = player.getWorld().getRegistryKey();

        double durationAmplifier = curseHeight / DEFAULT_THRESHOLD;
        int duration = Math.max((int) Math.floor(DEFAULT_DURATION * durationAmplifier), 20);

        switch (playerDimension.getValue().getPath()) {
            case "darkdim":
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, duration, 4));
                break;
            case "layer2":
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, duration, 2));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, duration, 1));
                break;
        }
    }
}
