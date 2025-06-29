package net.masterquentus.hexcraftmod.block.custom.plants;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class GlintWeedBlock extends AbstractSpreadingBlock {

    public GlintWeedBlock() {
        super(Properties.copy(Blocks.POPPY).lightLevel((a) -> 14).randomTicks());
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState state1 = level.getBlockState(pos.below());
        BlockState state2 = level.getBlockState(pos.above());

        return (!level.isEmptyBlock(pos.below()) && state1.isFaceSturdy(level, pos.below(), Direction.UP) || state1.is(BlockTags.LEAVES) ) ||
                (!level.isEmptyBlock(pos.above()) && state2.isFaceSturdy(level, pos.above(), Direction.DOWN) || state2.is(BlockTags.LEAVES) );
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
        return new ItemStack(HexcraftBlocks.GLINT_WEED.get()); // Change dynamically per flower
    }

    @Override
    public boolean canSpreadOn(Block block) {
        return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.SAND;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos neighborPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, block, neighborPos, isMoving);

        // If the block below or above is removed and it's no longer a valid support, destroy the Glint Weed
        if (!canSurvive(state, level, pos)) {
            level.destroyBlock(pos, true); // Drops the item
            System.out.println("✨ Glint Weed broke due to missing support at " + pos);
        }
    }

}