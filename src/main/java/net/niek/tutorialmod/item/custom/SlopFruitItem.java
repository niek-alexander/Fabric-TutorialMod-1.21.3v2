package net.niek.tutorialmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SlopFruitItem extends Item {
    public static final int MAX_USE_TIME = 32;

    public SlopFruitItem(Settings settings) {
        super(settings);
    }

    private void consumeSlopFruit(World world, PlayerEntity user, ItemStack stack) {
        world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 1.0F, 1.0F);
        stack.decrement(1);
        user.incrementStat(Stats.USED.getOrCreateStat(this));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return MAX_USE_TIME;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            if (user.getHungerManager().isNotFull() || user.isCreative()) {
                user.setCurrentHand(hand);
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.FAIL;
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity playerEntity) {
            if (!world.isClient) {
                consumeSlopFruit(world, playerEntity, stack);
                if (world.random.nextFloat() < 0.3F) { // 20% chance
                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 0));
                }
            }
        }
        return super.finishUsing(stack, world, user);
    }
}
