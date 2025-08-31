package net.masterquentus.hexcraftmod.item.custom;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.block.custom.UnderworldPortalBlock;
import net.masterquentus.hexcraftmod.util.UnderworldPortalShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class FlintAndHellfireItem extends Item {

    public FlintAndHellfireItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide) return InteractionResult.SUCCESS;

        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        // If clicking on the side of a block, shift position to air space
        BlockPos targetPos = pos.relative(context.getClickedFace());

        // Try to light a portal (X-axis)
        UnderworldPortalBlock.Size sizeX = new UnderworldPortalBlock.Size(level, targetPos, Direction.Axis.X);
        if (sizeX.isValid()) {
            sizeX.placePortalBlocks();
            playPortalSound(level, targetPos);
            damageItem(stack, player, context);
            return InteractionResult.SUCCESS;
        }

        // Try to light a portal (Z-axis)
        UnderworldPortalBlock.Size sizeZ = new UnderworldPortalBlock.Size(level, targetPos, Direction.Axis.Z);
        if (sizeZ.isValid()) {
            sizeZ.placePortalBlocks();
            playPortalSound(level, targetPos);
            damageItem(stack, player, context);
            return InteractionResult.SUCCESS;
        }

        // If not a portal, place Hellfire
        if (level.getBlockState(targetPos).isAir() && HexcraftBlocks.HELLFIRE.get().defaultBlockState().canSurvive(level, targetPos)) {
            level.setBlock(targetPos, HexcraftBlocks.HELLFIRE.get().defaultBlockState(), 11);
            level.playSound(null, targetPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            damageItem(stack, player, context);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    private void playPortalSound(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.PORTAL_TRIGGER, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    /**
     * Damages the item and handles break animation.
     */
    private void damageItem(ItemStack stack, Player player, UseOnContext context) {
        if (player != null) {
            stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(context.getHand()));
        }
    }

    /**
     * Spawns a simple rectangular portal frame made of Black Obsidian centered at pos.
     * @return true if frame was placed, false if space was obstructed
     */
    private boolean spawnPortalFrame(Level level, BlockPos center) {
        int width = 4;
        int height = 5;
        BlockPos startPos = center.offset(-(width / 2), 0, 0);

        // Make sure the inside is clear
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                BlockPos checkPos = startPos.offset(x, y, 0);
                if (!level.isLoaded(checkPos) || !level.getBlockState(checkPos).isAir()) {
                    return false;
                }
            }
        }

        // Place the obsidian frame
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boolean isEdgeX = (x == 0) || (x == width - 1);
                boolean isEdgeY = (y == 0) || (y == height - 1);

                if (isEdgeX || isEdgeY) {
                    BlockPos placePos = startPos.offset(x, y, 0);
                    if (level.isLoaded(placePos)) {
                        level.setBlock(placePos, HexcraftBlocks.BLACK_OBSIDIAN.get().defaultBlockState(), 3);
                    }
                }
            }
        }
        return true;
    }
}