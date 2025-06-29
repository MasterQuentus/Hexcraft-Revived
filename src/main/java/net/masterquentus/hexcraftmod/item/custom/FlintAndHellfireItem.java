package net.masterquentus.hexcraftmod.item.custom;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.util.UnderworldPortalShape;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class FlintAndHellfireItem extends Item {

    public FlintAndHellfireItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        if (!level.isClientSide) {
            // 1. Try to light portal if frame is Black Obsidian
            if (block == HexcraftBlocks.BLACK_OBSIDIAN.get()) {
                UnderworldPortalShape portalShape = UnderworldPortalShape.findPortalShape(level, pos);
                if (portalShape != null) {
                    portalShape.createPortalBlocks();

                    level.playSound(null, pos, SoundEvents.PORTAL_TRIGGER, SoundSource.BLOCKS, 1.0F, 1.0F);
                    if (player != null) {
                        stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(context.getHand()));
                    }
                    return InteractionResult.SUCCESS;
                }
            }

            // 2. Else try to place Hellfire if the block above is air and can accept fire
            BlockPos abovePos = pos.above();
            BlockState aboveState = level.getBlockState(abovePos);

            if (aboveState.isAir() && HexcraftBlocks.HELLFIRE.get().defaultBlockState().canSurvive(level, abovePos)) {
                level.setBlock(abovePos, HexcraftBlocks.HELLFIRE.get().defaultBlockState(), 11);

                level.playSound(null, abovePos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (player != null) {
                    stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(context.getHand()));
                }
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }
}