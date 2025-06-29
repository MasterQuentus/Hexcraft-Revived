package net.masterquentus.hexcraftmod.block.custom;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Properties;

public class HellfireBlock extends FireBlock {

    public HellfireBlock(Properties properties) {
        super(properties); // 1.0F means fire spread speed (like Netherrack)
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState below = level.getBlockState(pos.below());
        return below.is(HexcraftBlocks.UNDER_WORLD_STONE.get()) || below.is(HexcraftBlocks.BLACK_OBSIDIAN.get());
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return false; // Hellfire block itself is not flammable (won't catch fire)
    }
}