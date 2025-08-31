package net.masterquentus.hexcraftmod.spells;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class ManusSpiritusSpell extends HexSpell {
    private static final double RANGE = 6.0;

    @Override
    public String getId() {
        return "manus_spiritus";
    }

    @Override
    public void cast(ServerLevel level, Player caster) {
        Vec3 pos = caster.position().add(0, caster.getEyeHeight(), 0);
        // Spawn ghostly spirit particles around the caster
        ((ServerLevel) level).sendParticles(ParticleTypes.SOUL, pos.x, pos.y, pos.z, 30, 1.0, 1.0, 1.0, 0.1);
        // You can add sound or other effects here if you want
    }
}