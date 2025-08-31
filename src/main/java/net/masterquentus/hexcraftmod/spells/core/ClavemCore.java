package net.masterquentus.hexcraftmod.spells.core;

import net.masterquentus.hexcraftmod.spells.CoreComponent;
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

public class ClavemCore implements CoreComponent {
    @Override
    public String getId() {
        return "clavem";
    }

    @Override
    public void applyEffect(ServerLevel level, Player caster) {
        // Bind a target temporarily (could be replaced with a real bind mechanic)
        Vec3 look = caster.getLookAngle();
        Vec3 start = caster.position().add(0, caster.getEyeHeight(), 0);
        Vec3 end = start.add(look.scale(6));

        EntityHitResult result = ProjectileUtil.getEntityHitResult(level, caster, start, end, caster.getBoundingBox().expandTowards(look.scale(6)), (e) -> e instanceof LivingEntity && e != caster);
        if (result != null) {
            LivingEntity target = (LivingEntity) result.getEntity();
            // Apply Slowness effect as binding
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
            level.playSound(null, target.blockPosition(), SoundEvents.LEASH_KNOT_PLACE, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }
}