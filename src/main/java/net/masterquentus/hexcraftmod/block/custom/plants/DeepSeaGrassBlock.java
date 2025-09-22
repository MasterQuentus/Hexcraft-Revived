package net.masterquentus.hexcraftmod.block.custom.plants;

import net.masterquentus.hexcraftmod.fluid.HexcraftFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class DeepSeaGrassBlock extends BushBlock implements BonemealableBlock, LiquidBlockContainer {
    protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 12.0, 14.0);

    public DeepSeaGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos) {
        // Can place on sturdy blocks but not magma
        return state.isFaceSturdy(world, pos, Direction.UP) && !state.is(Blocks.MAGMA_BLOCK);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        // Only place if fully submerged in DEEP WATER
        if (fluid.is(HexcraftFluids.SOURCE_DEEP_WATER.get()) && fluid.getAmount() == 8) {
            return super.getStateForPlacement(context);
        }
        return null;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState,
                                  LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        BlockState blockstate = super.updateShape(state, facing, facingState, world, currentPos, facingPos);
        if (!blockstate.isAir()) {
            world.scheduleTick(currentPos, HexcraftFluids.SOURCE_DEEP_WATER.get(), HexcraftFluids.SOURCE_DEEP_WATER.get().getTickDelay(world));
        }
        return blockstate;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel p_222423_, RandomSource p_222424_, BlockPos p_222425_, BlockState p_222426_) {
        BlockState blockstate = Blocks.TALL_SEAGRASS.defaultBlockState();
        BlockState blockstate1 = (BlockState)blockstate.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER);
        BlockPos blockpos = p_222425_.above();
        if (p_222423_.getBlockState(blockpos).is(Blocks.WATER)) {
            p_222423_.setBlock(p_222425_, blockstate, 2);
            p_222423_.setBlock(blockpos, blockstate1, 2);
        }

    }

    @Override
    public boolean canPlaceLiquid(BlockGetter world, BlockPos pos, BlockState state, Fluid fluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(LevelAccessor world, BlockPos pos, BlockState state, FluidState fluid) {
        return false;
    }
}