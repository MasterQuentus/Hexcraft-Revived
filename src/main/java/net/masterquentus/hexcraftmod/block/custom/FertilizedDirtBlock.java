package net.masterquentus.hexcraftmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FertilizedDirtBlock extends FarmBlock {
    public FertilizedDirtBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Boosts crop growth
        for (BlockPos offset : BlockPos.betweenClosed(pos.offset(-1, 0, -1), pos.offset(1, 0, 1))) {
            BlockState cropState = level.getBlockState(offset);
            Block cropBlock = cropState.getBlock();

            if (cropBlock instanceof CropBlock crop) {
                // Simulate random tick to force faster growth
                if (crop.isValidBonemealTarget(level, offset, cropState, false)) {
                    crop.performBonemeal(level, random, offset, cropState);
                }
            }
        }

        super.randomTick(state, level, pos, random);
    }
}