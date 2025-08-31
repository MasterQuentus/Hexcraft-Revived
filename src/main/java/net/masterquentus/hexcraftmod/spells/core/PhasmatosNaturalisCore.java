package net.masterquentus.hexcraftmod.spells.core;

import net.masterquentus.hexcraftmod.spells.CoreComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class PhasmatosNaturalisCore implements CoreComponent {
    private static final int RADIUS = 3; // int now

    @Override
    public String getId() {
        return "phasmatos_naturalis";
    }

    @Override
    public void applyEffect(ServerLevel level, Player caster) {
        BlockPos center = caster.blockPosition();

        // Loop over all blocks in a cubic area
        for (BlockPos blockPos : BlockPos.betweenClosed(
                center.offset(-RADIUS, -1, -RADIUS),
                center.offset(RADIUS, 1, RADIUS))) {

            if (level.getBlockState(blockPos).getBlock() instanceof BonemealableBlock growable) {
                if (growable.isValidBonemealTarget(level, blockPos, level.getBlockState(blockPos), false)) {
                    growable.performBonemeal(level, level.random, blockPos, level.getBlockState(blockPos));
                }
            }
        }

        // Particles + sound
        level.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                center.getX() + 0.5, center.getY() + 1, center.getZ() + 0.5,
                20, 1, 1, 1, 0.1);
        level.playSound(null, center, SoundEvents.GRASS_PLACE, SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}