package net.masterquentus.hexcraftmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class HellfireCampfireBlock extends CampfireBlock {
    public HellfireCampfireBlock(Properties properties, boolean spawnParticles) {
        super(spawnParticles, 2, properties);
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        super.animateTick(state, world, pos, random);

        // Custom particle effect (Example: Soul Fire particles)
        if (random.nextFloat() < 0.5F) {
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 0.7;
            double z = pos.getZ() + 0.5;
            world.addParticle(ParticleTypes.SMOKE, x, y, z, 0, 0.05, 0);
        }
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, world, pos, oldState, isMoving);
        world.playSound(null, pos, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 0.8F);
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (!entity.fireImmune() && state.getValue(LIT)) {
            entity.hurt(world.damageSources().inFire(), 3.0F); // Deals 3 damage per tick
        }
        super.entityInside(state, world, pos, entity);
    }
}