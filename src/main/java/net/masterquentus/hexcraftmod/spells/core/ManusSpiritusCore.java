package net.masterquentus.hexcraftmod.spells.core;

import net.masterquentus.hexcraftmod.spells.CoreComponent;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class ManusSpiritusCore implements CoreComponent {
    @Override
    public String getId() {
        return "manus_spiritus";
    }

    @Override
    public void applyEffect(ServerLevel level, Player caster) {
        // Communicate with nearby spirits (just spawn particles + sound as placeholder)
        Vec3 pos = caster.position();
        ((ServerLevel) level).sendParticles(ParticleTypes.SOUL, pos.x, pos.y + 1, pos.z, 20, 0.5, 1, 0.5, 0.02);
        level.playSound(null, caster.blockPosition(), SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}