package net.masterquentus.hexcraftmod.spells.core;

import net.masterquentus.hexcraftmod.spells.CoreComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;

public class SiccitasCore implements CoreComponent {
    private static final int RADIUS = 5; // int now

    @Override
    public String getId() {
        return "siccitas";
    }

    @Override
    public void applyEffect(ServerLevel level, Player caster) {
        // Evaporate water in an area
        BlockPos center = caster.blockPosition();
        for (BlockPos pos : BlockPos.betweenClosed(
                center.offset(-RADIUS, -1, -RADIUS),
                center.offset(RADIUS, 1, RADIUS))) {

            if (level.getBlockState(pos).getBlock() == Blocks.WATER) {
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            }
        }

        level.playSound(null, center, SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
        level.sendParticles(ParticleTypes.CLOUD,
                center.getX() + 0.5, center.getY() + 0.5, center.getZ() + 0.5,
                20, 1, 1, 1, 0.1);
    }
}