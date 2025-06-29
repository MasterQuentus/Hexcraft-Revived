package net.masterquentus.hexcraftmod.block.custom.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.PlantType;

public class EnderBrambleBlock extends Block implements net.minecraftforge.common.IPlantable {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_15;
    protected static final float AABB_OFFSET = 6.0F;
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public EnderBrambleBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pState.canSurvive(pLevel, pPos)) {
            pLevel.destroyBlock(pPos, true);
        }
        grow(pState, pLevel, pPos, pRandom);  // Add growth logic here
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.isEmptyBlock(pPos.above())) {
            int i;
            for (i = 1; pLevel.getBlockState(pPos.below(i)).is(this); ++i) {
            }

            if (i < 3) {
                int j = pState.getValue(AGE);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, true)) {
                    if (j == 15) {
                        pLevel.setBlockAndUpdate(pPos.above(), this.defaultBlockState());
                        net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pLevel, pPos.above(), this.defaultBlockState());
                        pLevel.setBlock(pPos, pState.setValue(AGE, Integer.valueOf(0)), 4);
                    } else {
                        pLevel.setBlock(pPos, pState.setValue(AGE, Integer.valueOf(j + 1)), 4);
                    }
                }
            }
        }
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (!pState.canSurvive(pLevel, pCurrentPos)) {
            pLevel.scheduleTick(pCurrentPos, this, 1);
        }
        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    @Override
    public net.minecraftforge.common.PlantType getPlantType(BlockGetter world, BlockPos pos) {
        return PlantType.PLAINS;
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockState soil = pLevel.getBlockState(pPos.below());
        if (soil.canSustainPlant(pLevel, pPos.below(), Direction.UP, this)) return true;
        BlockState blockstate = pLevel.getBlockState(pPos.below());
        if (blockstate.is(this)) {
            return true;
        } else {
            if (blockstate.is(BlockTags.DIRT) || blockstate.is(Blocks.SAND) || blockstate.is(Blocks.RED_SAND)) {
                BlockPos blockpos = pPos.below();

                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    BlockState blockstate1 = pLevel.getBlockState(blockpos.relative(direction));
                    FluidState fluidstate = pLevel.getFluidState(blockpos.relative(direction));
                    if (pState.canBeHydrated(pLevel, pPos, fluidstate, blockpos.relative(direction)) || blockstate1.is(Blocks.FROSTED_ICE)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Override
    public BlockState getPlant(BlockGetter world, BlockPos pos) {
        return defaultBlockState();
    }

    // Method to teleport the player standing on the block to a very far random location
    public void teleportPlayer(Entity entity, ServerLevel level, BlockPos pos) {
        if (entity instanceof Player) {
            Player player = (Player) entity;
            RandomSource random = level.getRandom();

            // Generate a much larger random offset for teleportation
            int offsetX = random.nextInt(10000) - 5000; // Range from -5000 to +5000
            int offsetY = random.nextInt(100) - 50;     // Range from -50 to +50 (height)
            int offsetZ = random.nextInt(10000) - 5000; // Range from -5000 to +5000

            // Calculate the new position
            BlockPos newPos = pos.offset(offsetX, offsetY, offsetZ);

            // Make sure the new position is valid (e.g., not inside blocks)
            if (level.getBlockState(newPos).isAir() && level.getBlockState(newPos.below()).isSolidRender(level, newPos)) {
                player.teleportTo(newPos.getX() + 0.5, newPos.getY(), newPos.getZ() + 0.5); // Teleport to the random position
            }
        }
    }

    // Detect when a player steps on the Ender Bramble
    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        super.entityInside(state, level, pos, entity);

        if (level instanceof ServerLevel && entity instanceof Player) {
            teleportPlayer(entity, (ServerLevel) level, pos);
        }
    }

    // Method to handle the growth behavior (like sugar cane)
    private void grow(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int age = state.getValue(AGE);

        // If it's fully grown, it tries to grow upwards
        if (age >= 15) {
            BlockPos upPos = pos.above();
            if (level.isEmptyBlock(upPos)) {
                level.setBlockAndUpdate(upPos, this.defaultBlockState());  // Grow upwards
            }
        }
    }
}