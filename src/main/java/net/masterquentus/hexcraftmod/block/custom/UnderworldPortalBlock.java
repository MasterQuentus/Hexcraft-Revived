package net.masterquentus.hexcraftmod.block.custom;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.item.custom.UnderworldTeleporter;
import net.masterquentus.hexcraftmod.util.HexcraftTags;
import net.masterquentus.hexcraftmod.worldgen.dimension.HexcraftDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.BlockEvent;

import javax.annotation.Nullable;


public class UnderworldPortalBlock extends Block {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final VoxelShape X_AABB = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    public UnderworldPortalBlock() {
        super(Properties.copy(Blocks.NETHER_PORTAL)
                .strength(-1F)
                .noCollission()
                .lightLevel(state -> 10)
                .noLootTable()
        );
        registerDefaultState(stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return state.getValue(AXIS) == Direction.Axis.Z ? Z_AABB : X_AABB;
    }

    public boolean trySpawnPortal(LevelAccessor worldIn, BlockPos pos) {
        Size size = isPortal(worldIn, pos);
        if (size != null && !onTrySpawnPortal(worldIn, pos, size)) {
            size.placePortalBlocks();
            return true;
        }
        return false;
    }

    public static boolean onTrySpawnPortal(LevelAccessor world, BlockPos pos, Size size) {
        return MinecraftForge.EVENT_BUS.post(new PortalSpawnEvent(world, pos, world.getBlockState(pos), size));
    }

    @Nullable
    public Size isPortal(LevelAccessor worldIn, BlockPos pos) {
        Size sizeX = new Size(worldIn, pos, Direction.Axis.X);
        if (sizeX.isValid() && sizeX.portalBlockCount == 0) {
            return sizeX;
        }
        Size sizeZ = new Size(worldIn, pos, Direction.Axis.Z);
        return sizeZ.isValid() && sizeZ.portalBlockCount == 0 ? sizeZ : null;
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        Direction.Axis axis = facing.getAxis();
        Direction.Axis stateAxis = stateIn.getValue(AXIS);
        boolean differentAxis = stateAxis != axis && axis.isHorizontal();
        return !differentAxis && facingState.getBlock() != this && !(new Size(worldIn, currentPos, stateAxis)).validatePortal()
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && entity.canChangeDimensions()) {
            if (entity instanceof ServerPlayer player) {
                // Use the Level key for dimension comparisons
                ResourceKey<Level> target = player.level().dimension() == HexcraftDimensions.UNDERWORLDDIM_LEVEL_KEY
                        ? Level.OVERWORLD
                        : HexcraftDimensions.UNDERWORLDDIM_LEVEL_KEY;

                player.changeDimension(player.server.getLevel(target),
                        new UnderworldTeleporter(player.server.getLevel(target), pos));
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
        if (rand.nextInt(100) == 0) {
            worldIn.playLocalSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
                    SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS,
                    0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        Direction.Axis axis = stateIn.getValue(AXIS);
        for (int i = 0; i < 4; ++i) {
            double x = pos.getX() + rand.nextDouble();
            double y = pos.getY() + rand.nextDouble();
            double z = pos.getZ() + rand.nextDouble();

            // Default small random speed in all directions
            double xSpeed = (rand.nextFloat() - 0.5D) * 0.1D;
            double ySpeed = (rand.nextFloat() - 0.5D) * 0.1D;
            double zSpeed = (rand.nextFloat() - 0.5D) * 0.1D;

            // Emit particles mainly outward from the portal plane
            if (axis == Direction.Axis.X) {
                // Portal plane is in X axis, so particles move along Z
                z = pos.getZ() + 0.5D + 0.25D * (rand.nextBoolean() ? 1 : -1);
                zSpeed = 0.05D * (rand.nextBoolean() ? 1 : -1);
                x = pos.getX() + rand.nextDouble();
            } else {
                // Portal plane is in Z axis, so particles move along X
                x = pos.getX() + 0.5D + 0.25D * (rand.nextBoolean() ? 1 : -1);
                xSpeed = 0.05D * (rand.nextBoolean() ? 1 : -1);
                z = pos.getZ() + rand.nextDouble();
            }

            worldIn.addParticle(ParticleTypes.SCULK_SOUL, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        if (rot == Rotation.CLOCKWISE_90 || rot == Rotation.COUNTERCLOCKWISE_90) {
            return state.setValue(AXIS, state.getValue(AXIS) == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X);
        }
        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    public static class PortalSpawnEvent extends BlockEvent {
        private final Size size;

        public PortalSpawnEvent(LevelAccessor world, BlockPos pos, BlockState state, Size size) {
            super(world, pos, state);
            this.size = size;
        }

        public Size getPortalSize() {
            return size;
        }
    }

    public static class Size {
        private final LevelAccessor level;
        private final Direction.Axis axis;
        private final Direction rightDir;
        private final Direction leftDir;
        private int portalBlockCount;
        @Nullable
        private BlockPos bottomLeft;
        private int height;
        private int width;

        public Size(LevelAccessor level, BlockPos pos, Direction.Axis axis) {
            this.level = level;
            this.axis = axis;
            this.leftDir = axis == Direction.Axis.X ? Direction.EAST : Direction.NORTH;
            this.rightDir = axis == Direction.Axis.X ? Direction.WEST : Direction.SOUTH;

            while (pos.getY() > 0 && canConnect(level.getBlockState(pos.below()))) {
                pos = pos.below();
            }

            int i = getDistanceUntilEdge(pos, leftDir) - 1;
            if (i >= 0) {
                this.bottomLeft = pos.relative(leftDir, i);
                this.width = getDistanceUntilEdge(bottomLeft, rightDir);
                if (width < 2 || width > 21) {
                    this.bottomLeft = null;
                    this.width = 0;
                }
            }

            if (this.bottomLeft != null) {
                this.height = calculatePortalHeight();
            }
        }

        protected int getDistanceUntilEdge(BlockPos pos, Direction direction) {
            int distance;
            for (distance = 0; distance < 22; ++distance) {
                BlockPos checkPos = pos.relative(direction, distance);
                BlockState state = level.getBlockState(checkPos);
                BlockState belowState = level.getBlockState(checkPos.below());

                if (!canConnect(state) || !belowState.is(HexcraftTags.Blocks.PORTAL_FRAME_BLOCKS)) {
                    break;
                }
            }
            // After loop, distance is either the breaking point or 22
            // So check if block at distance position is a frame block (portal frame block)
            BlockPos framePos = pos.relative(direction, distance);
            return level.getBlockState(framePos).is(HexcraftTags.Blocks.PORTAL_FRAME_BLOCKS) ? distance : 0;
        }

        protected int calculatePortalHeight() {
            for (height = 0; height < 21; ++height) {
                for (int i = 0; i < width; ++i) {
                    BlockPos pos = bottomLeft.relative(rightDir, i).above(height);
                    BlockState state = level.getBlockState(pos);
                    if (!canConnect(state)) {
                        return height; // Return the height found so far
                    }
                    if (state.getBlock() == HexcraftBlocks.UNDERWORLD_PORTAL.get()) {
                        portalBlockCount++;
                    }

                    // Check left & right frame blocks
                    if ((i == 0 && !level.getBlockState(pos.relative(leftDir)).is(HexcraftTags.Blocks.PORTAL_FRAME_BLOCKS)) ||
                            (i == width - 1 && !level.getBlockState(pos.relative(rightDir)).is(HexcraftTags.Blocks.PORTAL_FRAME_BLOCKS))) {
                        return 0;
                    }
                }
            }

            // Check top frame line after max height reached
            for (int j = 0; j < width; ++j) {
                BlockPos topFrame = bottomLeft.relative(rightDir, j).above(height);
                if (!level.getBlockState(topFrame).is(HexcraftTags.Blocks.PORTAL_FRAME_BLOCKS)) {
                    return 0;
                }
            }

            return height >= 3 && height <= 21 ? height : 0;
        }

        protected boolean canConnect(BlockState state) {
            Block block = state.getBlock();
            return state.isAir() || block == HexcraftBlocks.UNDERWORLD_PORTAL.get();
        }

        public boolean isValid() {
            return bottomLeft != null && width >= 2 && width <= 21 && height >= 3 && height <= 21;
        }

        public boolean validatePortal() {
            return isValid() && portalBlockCount >= width * height;
        }

        public void placePortalBlocks() {
            for (int i = 0; i < width; ++i) {
                BlockPos column = bottomLeft.relative(rightDir, i);
                for (int j = 0; j < height; ++j) {
                    BlockPos blockpos = column.above(j);
                    level.setBlock(blockpos, HexcraftBlocks.UNDERWORLD_PORTAL.get().defaultBlockState().setValue(AXIS, axis), 18);
                }
            }
        }
    }
}