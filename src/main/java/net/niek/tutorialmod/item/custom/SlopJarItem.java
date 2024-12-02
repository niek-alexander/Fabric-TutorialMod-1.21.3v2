package net.niek.tutorialmod.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.niek.tutorialmod.item.ModItems;

import java.util.List;

public class SlopJarItem extends Item {
    public SlopJarItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.isClient) {
            if (user.getHungerManager().isNotFull() || user.isCreative()) {
                user.getItemCooldownManager().set(itemStack, 20);
                user.getHungerManager().add(3, 2.0f); // 3 for 2, technically a full food bar per jar.
                user.incrementStat(Stats.USED.getOrCreateStat(this));

                float pitch = 0.6F + world.getRandom().nextFloat() * 0.2F;
                world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_HONEY_BOTTLE_DRINK.value(),
                        SoundCategory.PLAYERS, 1.0F, pitch);

                int ConsumeDamage = 50;
                if (itemStack.getDamage() + ConsumeDamage + 18 >= itemStack.getMaxDamage()) { //18 + 18 + 14 = 50(craft)
                    ItemStack emptyJarStack = new ItemStack(ModItems.EMPTYJAR);

                    if (!tryMergeEmptyJar(user))
                    {
                        user.setStackInHand(hand, emptyJarStack);
                    }
                    else
                    {
                        user.setStackInHand(hand, ItemStack.EMPTY);
                    }
                    return ActionResult.SUCCESS;
                }

                itemStack.damage(ConsumeDamage, ((ServerWorld) world), ((ServerPlayerEntity) user),
                        item -> user.sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                if (world.random.nextFloat() < 0.3F) { // 30% chance
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 140, 0));
                }
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 160, 0));

                return ActionResult.SUCCESS;
            } else {
                return ActionResult.FAIL;
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public boolean onStackClicked(ItemStack jarStack, Slot slot, ClickType clickType, PlayerEntity player) {
        ItemStack clickedStack = slot.getStack();

        if (clickType == ClickType.LEFT && clickedStack.getItem() instanceof SlopFruitItem) {
            int damage = jarStack.getDamage();
            if (damage > 0) {
                clickedStack.decrement(1);
                jarStack.setDamage(damage - 50);
                player.getWorld().playSound(null, player.getBlockPos(),
                        SoundEvents.BLOCK_SLIME_BLOCK_FALL, SoundCategory.PLAYERS, 0.8F, 1.0F);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onClicked(ItemStack jarStack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.LEFT && otherStack.getItem() instanceof SlopFruitItem) {
            int damage = jarStack.getDamage();
            if (damage > 0) {
                otherStack.decrement(1);
                jarStack.setDamage(damage - 50);
                player.getWorld().playSound(null, player.getBlockPos(),
                        SoundEvents.BLOCK_SLIME_BLOCK_FALL, SoundCategory.PLAYERS, 0.8F, 1.0F);
                return true;
            }
        }
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (Screen.hasShiftDown())
        {
            tooltip.add(Text.translatable("tooltip.tutorialmod.slopjar.shift_down"));
        }
        else
        {
            tooltip.add(Text.translatable("tooltip.tutorialmod.slopjar"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }

    /**
     * Tries to merge the empty jar into the player's inventory.
     *
     * @param user The player entity.
     * @return True if successfully merged, otherwise false.
     */
    private boolean tryMergeEmptyJar(PlayerEntity user) {
        for (int slotIndex = 0; slotIndex < user.getInventory().size(); ++slotIndex) {
            ItemStack stack = user.getInventory().getStack(slotIndex);

            if (stack.getItem() == ModItems.EMPTYJAR && stack.getCount() < 8) {
                stack.increment(1); // Add one empty jar to the stack
                return true;
            }
        }
        return false;
    }

}