package net.masterquentus.hexcraftmod.block.entity.custom;


import net.masterquentus.hexcraftmod.block.custom.chalk.AbstractChalkBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class RedChalkMarkBlock extends AbstractChalkBlock {
    public static final EnumProperty<RedChalkSymbols> SHAPE = EnumProperty.create("shape", RedChalkSymbols.class);
    public static final IntegerProperty GLYPH = IntegerProperty.create("glyph", 1, 12);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public RedChalkMarkBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(GLYPH, 1)
                .setValue(SHAPE, RedChalkSymbols.BLOODLINE_TOTEM)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SHAPE, GLYPH, FACING);
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        // Don't allow red chalk blocks stacked on themselves
        return !(world.getBlockState(pos.below()).getBlock() instanceof RedChalkMarkBlock);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (neighborState.getBlock() instanceof RedChalkMarkBlock) {
            return state.setValue(SHAPE, getRandomShape(level));
        }
        return state;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        int glyphVariant = level.random.nextInt(12) + 1;
        Direction facing = context.getHorizontalDirection().getOpposite();
        RedChalkSymbols randomShape = getRandomShape(level);

        return this.defaultBlockState()
                .setValue(GLYPH, glyphVariant)
                .setValue(SHAPE, randomShape)
                .setValue(FACING, facing);
    }

    private RedChalkSymbols getRandomShape(LevelAccessor level) {
        RedChalkSymbols[] shapes = RedChalkSymbols.values();
        return shapes[level.getRandom().nextInt(shapes.length)];
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // Flat thin plate shape
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 0.01D, 16.0D);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // No collision
        return Shapes.empty();
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return context.getClickedFace() != Direction.UP;
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.BLOCK;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (level.getBlockState(pos.below()).getBlock() instanceof RedChalkMarkBlock) {
            level.destroyBlock(pos.below(), false);
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!player.isCreative()) {
            // Prevent breaking outside creative mode by restoring block
            level.setBlock(pos, state, 3);
        }
        super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter level, BlockPos pos, Player player) {
        return player.isCreative();
    }

    private boolean isChalkAt(LevelAccessor level, BlockPos pos) {
        return level.getBlockState(pos).getBlock() instanceof RedChalkMarkBlock;
    }
}