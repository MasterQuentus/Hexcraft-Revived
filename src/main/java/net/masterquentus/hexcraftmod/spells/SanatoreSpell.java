package net.masterquentus.hexcraftmod.spells;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public class SanatoreSpell extends HexSpell {

    private static final float HEAL_AMOUNT = 6.0F; // 3 hearts

    @Override
    public String getId() {
        return "sanatore";
    }

    @Override
    public void cast(ServerLevel level, Player caster) {
        // Heal the caster by HEAL_AMOUNT, but don't exceed max health
        float newHealth = Math.min(caster.getHealth() + HEAL_AMOUNT, caster.getMaxHealth());
        caster.setHealth(newHealth);

        // Play a healing sound effect (you can choose a different sound)
        level.playSound(null, caster.blockPosition(), SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 1.0F, 1.0F);

        // Optionally, spawn some particle effects around the caster
        if (!level.isClientSide) {
            ((ServerLevel) level).sendParticles(
                    ParticleTypes.HEART,
                    caster.getX(), caster.getY() + 1, caster.getZ(),
                    10, 0.5, 0.5, 0.5, 0.1
            );
        }
    }
}