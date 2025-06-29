package net.masterquentus.hexcraftmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

public class WitchesCandleBlock extends CandleBlock implements SimpleWaterloggedBlock {
    public static final IntegerProperty CANDLES = CandleBlock.CANDLES;
    public static final BooleanProperty LIT = CandleBlock.LIT;
    public static final IntegerProperty CANDLES_LIT = IntegerProperty.create("candles_lit", 0, 4);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public WitchesCandleBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(CANDLES, 1)
                .setValue(LIT, false)
                .setValue(CANDLES_LIT, 0)
                .setValue(WATERLOGGED, false)); // default not waterlogged
    }

    private void spawnCustomSmokeParticles(BlockState state, Level level, BlockPos pos) {
        int candleCount = state.getValue(CANDLES_LIT);
        RandomSource random = level.getRandom();

        for (int i = 0; i < candleCount; ++i) {
            double x = pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.3;
            double y = pos.getY() + 0.7 + random.nextDouble() * 0.2;
            double z = pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.3;

            level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CANDLES, LIT, CANDLES_LIT, WATERLOGGED);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(LIT);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            spawnCustomSmokeParticles(state, level, pos);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {

        ItemStack heldItem = player.getItemInHand(hand);
        Block heldBlock = Block.byItem(heldItem.getItem());

        // Light candle(s)
        if (!state.getValue(LIT) && heldItem.is(Items.FLINT_AND_STEEL)) {
            int lit = state.getValue(CANDLES);
            level.setBlock(pos, state.setValue(LIT, true).setValue(CANDLES_LIT, lit), 3);
            level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 0.8F);
            heldItem.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
            return InteractionResult.sidedSuccess(level.isClientSide());
        }

        // Extinguish candle(s)
        if (state.getValue(LIT) && heldItem.isEmpty()) {
            level.setBlock(pos, state.setValue(LIT, false).setValue(CANDLES_LIT, 0), 3);
            level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.sidedSuccess(level.isClientSide());
        }

        // Add candle(s) to existing block
        if (heldBlock instanceof WitchesCandleBlock) {
            if (heldBlock == state.getBlock()) {
                int count = state.getValue(CANDLES);
                if (count < 4) {
                    int lit = state.getValue(LIT) ? count + 1 : 0;
                    level.setBlock(pos, state.setValue(CANDLES, count + 1).setValue(CANDLES_LIT, lit), 3);

                    level.playSound(null, pos, SoundEvents.CANDLE_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);

                    if (!player.isCreative()) {
                        heldItem.shrink(1);
                    }
                    return InteractionResult.sidedSuccess(level.isClientSide());
                } else {
                    // Max candles reached
                    return InteractionResult.PASS;
                }
            } else {
                // Replace candle color/type but keep count and lit state
                BlockState newState = heldBlock.defaultBlockState()
                        .setValue(CANDLES, state.getValue(CANDLES))
                        .setValue(LIT, state.getValue(LIT))
                        .setValue(CANDLES_LIT, state.getValue(CANDLES_LIT))
                        .setValue(WATERLOGGED, state.getValue(WATERLOGGED)); // Keep waterlogged state
                level.setBlock(pos, newState, 3);
                if (!player.isCreative()) {
                    heldItem.shrink(1);
                }
                return InteractionResult.sidedSuccess(level.isClientSide());
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getValue(LIT) ? 3 + state.getValue(CANDLES_LIT) * 3 : 0;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (state.getValue(LIT)) {
            level.scheduleTick(pos, this, 1);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            spawnCustomSmokeParticles(state, level, pos);
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidState fluidState = level.getFluidState(pos);

        return this.defaultBlockState()
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER)
                .setValue(CANDLES, 1)
                .setValue(LIT, false)
                .setValue(CANDLES_LIT, 0);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level,
                                  BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }
}