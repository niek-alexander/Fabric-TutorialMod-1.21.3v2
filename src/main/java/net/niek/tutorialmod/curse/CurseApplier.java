package net.niek.tutorialmod.curse;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CurseApplier {
    public static final String MOD_ID = "tutorialmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void applyCurse(ServerPlayerEntity player, double curseHeight) {
        int DEFAULT_THRESHOLD = 6; // Default threshold in blocks
        int DEFAULT_DURATION = 200; // 20 ticks in second

        RegistryKey<World> playerDimension = player.getWorld().getRegistryKey();

        double durationAmplifier = curseHeight / DEFAULT_THRESHOLD;
        int duration = Math.max((int) Math.floor(DEFAULT_DURATION * durationAmplifier), 20);

        LOGGER.info("Entering Switch Statement!");
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