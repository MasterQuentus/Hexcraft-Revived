package net.masterquentus.hexcraftmod.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class UnderworldPortalBlock extends NetherPortalBlock {

    public UnderworldPortalBlock(Properties properties) {
        super(properties);
    }

    // Optionally override the shape to be similar to Nether Portal or custom
    private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 3.0D, 16.0D, 16.0D, 13.0D);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextInt(100) == 0) {
            level.playLocalSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
                    SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int i = 0; i < 4; i++) {
            double px = pos.getX() + random.nextDouble();
            double py = pos.getY() + random.nextDouble();
            double pz = pos.getZ() + random.nextDouble();

            double vx = (random.nextDouble() - 0.5D) * 0.5D;
            double vy = (random.nextDouble() - 0.5D) * 0.5D;
            double vz = (random.nextDouble() - 0.5D) * 0.5D;

            level.addParticle(ParticleTypes.PORTAL, px, py, pz, vx, vy, vz);
        }
    }

    // You can override onEntityCollision to teleport entities between dimensions
    // but that’s more complex and requires dimension registration.
}