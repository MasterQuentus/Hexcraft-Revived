package net.masterquentus.hexcraftmod.block.custom.plants;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlisterCactusBlock extends Block {
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 15);
    private static final int MAX_AGE = 15;
    private static final int MAX_HEIGHT = 3; // Limits growth to 3 blocks high
    protected static final VoxelShape COLLISION_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 15.0, 15.0);
    protected static final VoxelShape OUTLINE_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
    public BlisterCactusBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int age = state.getValue(AGE);
        System.out.println("✅ Blister Cactus Tick at " + pos + " | Age: " + age);

        // Count cactus stack height
        int height = 1;
        BlockPos belowPos = pos.below();
        while (level.getBlockState(belowPos).getBlock() == this) {
            height++;
            belowPos = belowPos.below();
            if (height >= MAX_HEIGHT) {
                System.out.println("🚫 Cactus stopped growing at max height: " + height);
                return;
            }
        }

        // Grow if below max age and no block above
        if (age < MAX_AGE && level.isEmptyBlock(pos.above())) {
            level.setBlock(pos, state.setValue(AGE, age + 1), 2);
            System.out.println("🌱 Cactus grew! New age: " + (age + 1));

            // If cactus reaches max age, 25% chance to spawn a flower instead of another cactus
            if (age + 1 == MAX_AGE) {
                if (random.nextFloat() < 0.25f) { // 25% chance for a flower
                    level.setBlock(pos.above(), HexcraftBlocks.BLISTER_CACTUS_FLOWER.get().defaultBlockState(), 2);
                    System.out.println("🌸 Blister Cactus Flower placed at " + pos.above());
                } else {
                    level.setBlock(pos.above(), this.defaultBlockState(), 2);
                    System.out.println("🌵 New Cactus Block spawned at " + pos.above());
                }
            }
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState belowState = level.getBlockState(pos.below());

        // Cactus can only grow on another cactus or sand-like blocks
        boolean canSurvive = belowState.is(this) || belowState.is(BlockTags.SAND);

        System.out.println("🌵 Can Cactus Survive? " + canSurvive + " at " + pos);
        return canSurvive;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        super.entityInside(state, level, pos, entity);

        // Deal damage to entities touching the cactus
        entity.hurt(level.damageSources().cactus(), 1.0F);
        entity.setSecondsOnFire(1);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return COLLISION_SHAPE;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return OUTLINE_SHAPE;
    }


    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos neighborPos, boolean isMoving) {
        if (!level.isClientSide) {
            System.out.println("⚠️ Blister Cactus Neighbor Change at " + pos);
// If the block below is removed, break the cactus
            if (neighborPos.equals(pos.below()) && level.getBlockState(neighborPos).isAir()) {
                level.destroyBlock(pos, true);
                System.out.println("🚨 Cactus broke because block below was removed at " + pos);
                return;
            }

            // Prevent placement next to solid blocks
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                BlockState neighborState = level.getBlockState(pos.relative(direction));
                if (!neighborState.isAir() && neighborState.getBlock() != this) {
                    level.destroyBlock(pos, true);
                    System.out.println("🚨 Cactus broke due to a neighbor block at " + neighborPos);
                    return;
                }
            }
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock()) && !level.isClientSide) {
            Player player = level.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10, false);

            // Prevent drops in Creative Mode
            if (player == null || !player.isCreative()) {
                popResource(level, pos, new ItemStack(HexcraftItems.BLISTER_CACTUS_FRUIT.get(), 1));
                System.out.println("🍎 Dropped Blister Cactus Fruit at " + pos);
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }
}
