package net.masterquentus.hexcraftmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;


public class HellfireBlock extends FireBlock {

    public HellfireBlock(Properties properties) {
        super(properties
                .noCollission()
                .lightLevel(state -> 10) // soft glow
                .replaceable()
                .instabreak()
                .noLootTable());
    }

    // Survives on any block
    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    // Never extinguish
    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Prevent natural extinguishing
    }

    // Prevent fire logic from reacting to block updates
    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {}

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {}

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return false;
    }

    // Not flammable
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return false;
    }

    // Unique particles and sound
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextInt(4) == 0) {
            level.playLocalSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS,
                    1.0F, random.nextFloat() * 0.4F + 0.8F, false);
        }

        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.7;
        double z = pos.getZ() + 0.5;

        level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x, y, z,
                (random.nextDouble() - 0.5) * 0.1,
                (random.nextDouble() - 0.5) * 0.1,
                (random.nextDouble() - 0.5) * 0.1);
    }

    // Optional: Damage entities (e.g., players not fire-resistant)
    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        super.entityInside(state, level, pos, entity);

        // Deal damage to entities touching the cactus
        entity.hurt(level.damageSources().cactus(), 1.0F);
        entity.setSecondsOnFire(1);
    }

    // Optional: Only allow it to exist in the Underworld dimension
    @Override
    public void onNeighborChange(BlockState state, LevelReader reader, BlockPos pos, BlockPos neighbor) {
        if (reader instanceof Level level) {
            ResourceKey<Level> currentDim = level.dimension();
            if (!currentDim.location().getPath().equals("underworlddim")) {
                // Replace with air if not in underworld
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            }
        }
    }

    // Optional: Visual only (optional drop)
    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
        super.playerWillDestroy(level, pos, state, player);
    }
}