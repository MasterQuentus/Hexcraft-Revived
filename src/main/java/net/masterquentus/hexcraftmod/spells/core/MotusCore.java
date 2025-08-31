package net.masterquentus.hexcraftmod.spells.core;

import net.masterquentus.hexcraftmod.spells.CoreComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class MotusCore implements CoreComponent {
    private static final double RANGE = 6.0;

    @Override
    public String getId() {
        return "motus";
    }

    @Override
    public void applyEffect(ServerLevel level, Player caster) {
        Vec3 look = caster.getLookAngle();
        Vec3 start = caster.position().add(0, caster.getEyeHeight(), 0);
        Vec3 end = start.add(look.scale(RANGE));

        EntityHitResult result = ProjectileUtil.getEntityHitResult(level, caster, start, end, caster.getBoundingBox().expandTowards(look.scale(RANGE)), (e) -> e instanceof LivingEntity && e != caster);
        if (result != null) {
            LivingEntity target = (LivingEntity) result.getEntity();
            // Push the target away with knockback
            target.push(look.x * 2.0, 0.5, look.z * 2.0);
            level.playSound(null, target.blockPosition(), SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }
}