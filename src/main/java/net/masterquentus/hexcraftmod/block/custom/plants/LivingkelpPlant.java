package net.masterquentus.hexcraftmod.block.custom.plants;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;

import java.util.List;

import static net.masterquentus.hexcraftmod.util.HexcraftTags.AQUATIC_MOBS_TAG;

public class LivingkelpPlant extends GrowingPlantBodyBlock implements LiquidBlockContainer {
    public LivingkelpPlant(BlockBehaviour.Properties properties) {
        super(properties, Direction.UP, Shapes.block(), true);
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) HexcraftBlocks.LIVING_KELP.get();
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        boolean optimalConditions = checkEnvironmentalConditions(level, pos);
        float growthChance = optimalConditions ? 0.75f : 0.25f;

        if (random.nextFloat() < growthChance) {
            performBonemeal(level, random, pos, state);
        }
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);

        if (aboveState.isAir()) {
            BlockState newState = this.getStateForPlacement(level);
            if (newState != null) {
                level.setBlock(abovePos, newState, 2);
            }
        }
    }

    private boolean checkEnvironmentalConditions(Level level, BlockPos pos) {
        int lightLevel = level.getMaxLocalRawBrightness(pos);
        int waterDepth = getWaterDepth(level, pos);
        return lightLevel >= 8 && waterDepth >= 5;
    }

    private int getWaterDepth(Level level, BlockPos pos) {
        int depth = 0;
        while (level.getBlockState(pos.below(depth + 1)).getFluidState().is(FluidTags.WATER)) {
            depth++;
        }
        return depth;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return Fluids.WATER.getSource(false);
    }

    @Override
    protected boolean canAttachTo(BlockState state) {
        return super.canAttachTo(state);
    }

    @Override
    public boolean canPlaceLiquid(BlockGetter level, BlockPos pos, BlockState state, Fluid fluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
        return false;
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter getter, BlockPos pos, BlockState state) {
        return new ItemStack(HexcraftItems.LIVING_KELP_ITEM.get());
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        FluidState fluidState = level.getFluidState(pos);
        return fluidState.is(FluidTags.WATER) && fluidState.getAmount() == 8;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        if (!level.isClientSide) {
            attractAquaticMobs((ServerLevel) level, pos);
        }
    }

    private void attractAquaticMobs(ServerLevel level, BlockPos pos) {
        int radius = 10;
        AABB area = new AABB(pos).inflate(radius);
        List<PathfinderMob> mobs = level.getEntitiesOfClass(PathfinderMob.class, area, mob -> mob.getType().is(AQUATIC_MOBS_TAG));

        for (PathfinderMob mob : mobs) {
            mob.getNavigation().moveTo(pos.getX(), pos.getY(), pos.getZ(), 1.0);
        }
    }
}