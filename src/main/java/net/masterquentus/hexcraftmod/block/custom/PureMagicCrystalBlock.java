package net.masterquentus.hexcraftmod.block.custom;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class PureMagicCrystalBlock extends Block {
    private static final int SCAN_RADIUS = 100;
    private static final int MIN_HEIGHT = 50;    // Minimum cage height
    private static final int MAX_HEIGHT = 300;   // Max cage height cap (adjust as needed)

    public PureMagicCrystalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
            updateCage(level, pos);
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!level.isClientSide && !state.is(newState.getBlock())) {
            updateCage(level, pos);
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    /**
     * Scans for crystals, dynamically calculates cage bounds and height,
     * then places or removes cage blocks accordingly.
     */
    private void updateCage(Level level, BlockPos center) {
        List<BlockPos> crystals = new ArrayList<>();

        for (int dx = -SCAN_RADIUS; dx <= SCAN_RADIUS; dx++) {
            for (int dz = -SCAN_RADIUS; dz <= SCAN_RADIUS; dz++) {
                BlockPos check = center.offset(dx, 0, dz);
                if (level.getBlockState(check).getBlock() instanceof PureMagicCrystalBlock) {
                    crystals.add(check);
                }
            }
        }

        // Not enough crystals? Remove cages and exit
        if (crystals.size() < 8) {
            removeCageBlocks(level, center);
            return;
        }

        // Find bounds of cluster
        int minX = crystals.stream().mapToInt(BlockPos::getX).min().orElse(center.getX());
        int maxX = crystals.stream().mapToInt(BlockPos::getX).max().orElse(center.getX());
        int minZ = crystals.stream().mapToInt(BlockPos::getZ).min().orElse(center.getZ());
        int maxZ = crystals.stream().mapToInt(BlockPos::getZ).max().orElse(center.getZ());
        int baseY = center.getY();

        // Calculate cage height dynamically based on cluster size
        int clusterWidthX = maxX - minX + 1;
        int clusterWidthZ = maxZ - minZ + 1;
        int maxWidth = Math.max(clusterWidthX, clusterWidthZ);

        // Calculate height, with min and max caps
        int height = Math.min(MAX_HEIGHT, Math.max(MIN_HEIGHT, maxWidth / 2));

        BlockState cage = HexcraftBlocks.MAGIC_CAGE_BLOCK.get().defaultBlockState();

        // Build walls and roof
        for (int x = minX - 1; x <= maxX + 1; x++) {
            for (int z = minZ - 1; z <= maxZ + 1; z++) {
                boolean isEdgeX = (x == minX - 1) || (x == maxX + 1);
                boolean isEdgeZ = (z == minZ - 1) || (z == maxZ + 1);

                for (int y = baseY; y <= baseY + height; y++) {
                    BlockPos pos = new BlockPos(x, y, z);

                    // Place walls on edges only, up to roof level - 1
                    if (y < baseY + height) {
                        if ((isEdgeX || isEdgeZ) && level.getBlockState(pos).isAir()) {
                            level.setBlock(pos, cage, 3);
                        }
                    } else {
                        // Roof layer - fill all blocks within bounds
                        // So roof is solid across entire cage area
                        if (x >= minX - 1 && x <= maxX + 1 && z >= minZ - 1 && z <= maxZ + 1) {
                            if (level.getBlockState(pos).isAir()) {
                                level.setBlock(pos, cage, 3);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Removes all cage blocks in a big area around center.
     */
    private void removeCageBlocks(Level level, BlockPos center) {
        int range = SCAN_RADIUS + 2;
        for (int dx = -range; dx <= range; dx++) {
            for (int dz = -range; dz <= range; dz++) {
                for (int dy = 0; dy <= MAX_HEIGHT + 2; dy++) {  // Clear vertically as well
                    BlockPos pos = center.offset(dx, dy, dz);
                    if (level.getBlockState(pos).getBlock() == HexcraftBlocks.MAGIC_CAGE_BLOCK.get()) {
                        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                    }
                }
            }
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextFloat() < 0.15F) {
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 1.05;
            double z = pos.getZ() + 0.5;

            level.addParticle(ParticleTypes.ENCHANT, x, y, z,
                    (random.nextDouble() - 0.5) * 0.03,
                    0.01,
                    (random.nextDouble() - 0.5) * 0.03);
        }
    }

    public void overload(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        level.addParticle(ParticleTypes.EXPLOSION,
                pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                0, 0, 0);
    }
}