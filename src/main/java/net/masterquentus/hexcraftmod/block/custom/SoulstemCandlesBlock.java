package net.masterquentus.hexcraftmod.block.custom;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.masterquentus.hexcraftmod.fluid.HexcraftFluids;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.function.ToIntFunction;

public class SoulstemCandlesBlock extends CandleBlock implements BonemealableBlock, SimpleWaterloggedBlock {
    public static final int MIN_CANDLES = 1;
    public static final int MAX_CANDLES = 4;
    public static final IntegerProperty CANDLES = BlockStateProperties.CANDLES;
    public static final BooleanProperty LIT = AbstractCandleBlock.LIT;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape ONE_AABB;
    protected static final VoxelShape TWO_AABB;
    protected static final VoxelShape THREE_AABB;
    protected static final VoxelShape FOUR_AABB;
    public static final ToIntFunction<BlockState> LIGHT_EMISSION = (p_152848_) -> {
        return p_152848_.getValue(LIT) ? 3 * p_152848_.getValue(CANDLES) : 0;
    };

    private static final Int2ObjectMap<List<Vec3>> PARTICLE_OFFSETS = Util.make(() -> {
        Int2ObjectMap<List<Vec3>> map = new Int2ObjectOpenHashMap<>();
        map.defaultReturnValue(ImmutableList.of());

        map.put(1, ImmutableList.of(
                new Vec3(0.5D, 0.65D, 0.5D)
        ));

        map.put(2, ImmutableList.of(
                new Vec3(0.38D, 0.6D, 0.5D),
                new Vec3(0.62D, 0.6D, 0.5D)
        ));

        map.put(3, ImmutableList.of(
                new Vec3(0.38D, 0.6D, 0.38D),
                new Vec3(0.5D, 0.7D, 0.5D),
                new Vec3(0.62D, 0.6D, 0.62D)
        ));

        map.put(4, ImmutableList.of(
                new Vec3(0.375D, 0.6D, 0.375D),
                new Vec3(0.625D, 0.6D, 0.375D),
                new Vec3(0.375D, 0.6D, 0.625D),
                new Vec3(0.625D, 0.6D, 0.625D)
        ));

        return Int2ObjectMaps.unmodifiable(map);
    });


    public SoulstemCandlesBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(CANDLES, Integer.valueOf(1)).setValue(LIT, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.getAbilities().mayBuild && pPlayer.getItemInHand(pHand).isEmpty() && pState.getValue(LIT)) {
            extinguish(pPlayer, pState, pLevel, pPos);
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return !pUseContext.isSecondaryUseActive() && pUseContext.getItemInHand().getItem() == this.asItem() && pState.getValue(CANDLES) < 4 ? true : super.canBeReplaced(pState, pUseContext);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos());
        if (blockstate.is(this)) {
            return blockstate.cycle(CANDLES);
        } else {
            FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
            boolean flag = fluidstate.getType() == HexcraftFluids.SOURCE_DEEP_WATER.get();
            return super.getStateForPlacement(pContext).setValue(WATERLOGGED, Boolean.valueOf(flag));
        }
    }

    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if (pState.getValue(WATERLOGGED)) {
            FluidState fluidState = pLevel.getFluidState(pCurrentPos);
            pLevel.scheduleTick(pCurrentPos, fluidState.getType(), fluidState.getType().getTickDelay(pLevel));
        }

        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
    }

    @Override
    public FluidState getFluidState(BlockState pState) {
        if (pState.getValue(WATERLOGGED)) {
            return HexcraftFluids.SOURCE_DEEP_WATER.get().getSource(false);
        }
        return super.getFluidState(pState);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch ((Integer) pState.getValue(CANDLES)) {
            case 1:
            default:
                return ONE_AABB;
            case 2:
                return TWO_AABB;
            case 3:
                return THREE_AABB;
            case 4:
                return FOUR_AABB;
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(CANDLES, LIT, WATERLOGGED);
    }

    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        if (!pState.getValue(WATERLOGGED) &&
                (pFluidState.getType() == Fluids.WATER || pFluidState.getType() == HexcraftFluids.SOURCE_DEEP_WATER.get())) {

            BlockState newState = pState.setValue(WATERLOGGED, true);
            if (pState.getValue(LIT)) {
                extinguish(null, newState, pLevel, pPos);
            } else {
                pLevel.setBlock(pPos, newState, 3);
            }

            pLevel.scheduleTick(pPos, pFluidState.getType(), pFluidState.getType().getTickDelay(pLevel));
            return true;
        }
        return false;
    }

    public static boolean canLight(BlockState pState) {
        return pState.is(BlockTags.CANDLES, (p_152810_) -> {
            return p_152810_.hasProperty(LIT) && p_152810_.hasProperty(WATERLOGGED);
        }) && !pState.getValue(LIT) && !pState.getValue(WATERLOGGED);
    }

    protected Iterable<Vec3> getParticleOffsets(BlockState pState) {
        return PARTICLE_OFFSETS.get(pState.getValue(CANDLES).intValue());
    }

    protected boolean canBeLit(BlockState pState) {
        return !pState.getValue(WATERLOGGED) && super.canBeLit(pState);
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return Block.canSupportCenter(pLevel, pPos.below(), Direction.UP);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean b) {
        return true;
    }

    public static boolean isDead(BlockState pState) {
        return !(Boolean)pState.getValue(WATERLOGGED);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return !pState.getCollisionShape(pLevel, pPos).getFaceShape(Direction.UP).isEmpty() || pState.isFaceSturdy(pLevel, pPos, Direction.UP);
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {

    }
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    static {
        ONE_AABB = Block.box(6.0, 0.0, 6.0, 10.0, 6.0, 10.0);
        TWO_AABB = Block.box(3.0, 0.0, 3.0, 13.0, 6.0, 13.0);
        THREE_AABB = Block.box(2.0, 0.0, 2.0, 14.0, 6.0, 14.0);
        FOUR_AABB = Block.box(2.0, 0.0, 2.0, 14.0, 7.0, 14.0);
    }
}