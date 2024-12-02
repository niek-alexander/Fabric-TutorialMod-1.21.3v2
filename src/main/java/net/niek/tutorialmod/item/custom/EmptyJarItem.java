package net.niek.tutorialmod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ClickType;
import net.niek.tutorialmod.item.ModItems;

public class EmptyJarItem extends Item {
    public EmptyJarItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean onStackClicked(ItemStack emptyJarStack, Slot slot, ClickType clickType, PlayerEntity player) {
        ItemStack clickedStack = slot.getStack();
        int magicValueDamage = 200;

        if (clickType == ClickType.LEFT && clickedStack.getItem() instanceof SlopFruitItem) {
            player.getWorld().playSound(
                    null,
                    player.getBlockPos(),
                    SoundEvents.BLOCK_SLIME_BLOCK_PLACE,
                    SoundCategory.PLAYERS,
                    0.8F,
                    1.0F);

            ItemStack slopJarStack = new ItemStack(ModItems.SLOPJAR);
            slopJarStack.setDamage(magicValueDamage);
            slot.setStack(slopJarStack); //replace fruit with slopjarItem

            ItemStack cursorStack = player.currentScreenHandler.getCursorStack();
            if (!cursorStack.isEmpty()) {
                cursorStack.decrement(1);
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean onClicked(ItemStack emptyJarStack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        int magicValueDamage = 200;

        if (clickType == ClickType.LEFT && otherStack.getItem() instanceof SlopFruitItem) {
            otherStack.decrement(1); // Consume one SlopFruit, max one in hand
            player.getWorld().playSound(null, player.getBlockPos(),
                    SoundEvents.BLOCK_SLIME_BLOCK_PLACE, SoundCategory.PLAYERS, 0.8F, 1.0F);

            ItemStack slopJarStack = new ItemStack(ModItems.SLOPJAR);
            slopJarStack.setDamage(magicValueDamage);

            emptyJarStack.decrement(1);
            cursorStackReference.set(slopJarStack);

            return true;
        }
        return false;
    }

}
