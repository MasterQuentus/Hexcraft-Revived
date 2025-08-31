package net.masterquentus.hexcraftmod.spells;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ClavemSpell extends HexSpell {

    private static final double RANGE = 8.0;
    private static final int SLOW_DURATION = 100; // ticks (5 seconds)
    private static final int SLOW_AMPLIFIER = 4; // Level 4 Slowness

    @Override
    public String getId() {
        return "clavem";
    }

    @Override
    public void cast(ServerLevel level, Player caster) {
        Vec3 look = caster.getLookAngle();
        Vec3 start = caster.position().add(0, caster.getEyeHeight(), 0);
        Vec3 end = start.add(look.scale(RANGE));

        EntityHitResult entityResult = ProjectileUtil.getEntityHitResult(level, caster, start, end, caster.getBoundingBox().expandTowards(look.scale(RANGE)), e -> e instanceof LivingEntity && e != caster);
        if (entityResult != null) {
            LivingEntity target = (LivingEntity) entityResult.getEntity();
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, SLOW_DURATION, SLOW_AMPLIFIER));
            level.playSound(null, target.blockPosition(), SoundEvents.TRIPWIRE_CLICK_OFF, SoundSource.PLAYERS, 1f, 1f);
        }
    }
}