package net.masterquentus.hexcraftmod.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;

public class BlisterEffect extends MobEffect {
    public BlisterEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // Example: Deal damage over time
        // Use the MAGIC damage source to apply damage
        entity.hurt(entity.damageSources().mobAttack(entity), 0.5F * (amplifier + 1)); // Deal increasing damage
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // Apply effect every 40 ticks (2 seconds)
        return duration % 40 == 0;
    }
}