package net.masterquentus.hexcraftmod.block.custom;

import net.masterquentus.hexcraftmod.fluid.HexcraftFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class CrimsonMagmaBlock extends Block {
    public CrimsonMagmaBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity &&
                !EnchantmentHelper.hasFrostWalker((LivingEntity) entity)) {
            entity.hurt(level.damageSources().hotFloor(), 1.0F);
        }
        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        scheduleBubbleTick(level, pos);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState,
                                  LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (facing == Direction.UP) {
            scheduleBubbleTick((Level) level, currentPos);
        }
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        updateBubbleColumn(level, pos.above());
    }

    private void scheduleBubbleTick(Level level, BlockPos pos) {
        FluidState fluidAbove = level.getFluidState(pos.above());
        if (fluidAbove.is(FluidTags.WATER) ||
                fluidAbove.getType() == HexcraftFluids.SOURCE_DEEP_WATER.get() ||
                fluidAbove.getType() == HexcraftFluids.FLOWING_DEEP_WATER.get()) {
            level.scheduleTick(pos, this, 5);
        }
    }

    private void updateBubbleColumn(Level level, BlockPos startPos) {
        BlockPos.MutableBlockPos pos = startPos.mutable();
        while (true) {
            FluidState fluid = level.getFluidState(pos);
            if (!(fluid.is(FluidTags.WATER) ||
                    fluid.getType() == HexcraftFluids.SOURCE_DEEP_WATER.get() ||
                    fluid.getType() == HexcraftFluids.FLOWING_DEEP_WATER.get())) {
                break; // stop at non-water
            }

            // Set bubble column without replacing fluid
            BlockState bubble = Blocks.BUBBLE_COLUMN.defaultBlockState()
                    .setValue(BubbleColumnBlock.DRAG_DOWN, true);
            level.setBlock(pos, bubble, 3);

            pos.move(Direction.UP);
        }
    }
}