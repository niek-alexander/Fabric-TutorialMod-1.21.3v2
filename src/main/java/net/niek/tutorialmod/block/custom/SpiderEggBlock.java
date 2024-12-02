package net.niek.tutorialmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class SpiderEggBlock extends Block {
    public SpiderEggBlock(Settings settings) {
        super(settings);
    }

//    @Override
//    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
//        activate(world, pos, player);
//        return ActionResult.SUCCESS;
//    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        activate(world, pos, player);
        super.onBreak(world, pos, state, player);
        return state;
    }

    private void activate(World world, BlockPos pos, PlayerEntity player) {

        float pitch = 0.6F + world.getRandom().nextFloat() * 0.2F;
        world.playSound(player, pos, SoundEvents.BLOCK_SNIFFER_EGG_CRACK, SoundCategory.HOSTILE, 1f, pitch);
        world.setBlockState(pos, Blocks.AIR.getDefaultState());
        if (!world.isClient) {
            SpiderEntity spider = new SpiderEntity(EntityType.SPIDER, world);
            spider.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
            world.spawnEntity(spider);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("tooltip.tutorialmod.spider_egg_block"));
        super.appendTooltip(stack, context, tooltip, options);
    }
}
