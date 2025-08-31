package net.masterquentus.hexcraftmod.spells.core;

import net.masterquentus.hexcraftmod.spells.CoreComponent;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class VitaReverteturCore implements CoreComponent {
    @Override
    public String getId() {
        return "vita_revertetur";
    }

    @Override
    public void applyEffect(ServerLevel level, Player caster) {
        // Revive a recently dead ally (placeholder: heal and particles)
        Vec3 look = caster.getLookAngle();
        Vec3 start = caster.position().add(0, caster.getEyeHeight(), 0);
        Vec3 end = start.add(look.scale(6));

        EntityHitResult result = ProjectileUtil.getEntityHitResult(level, caster, start, end, caster.getBoundingBox().expandTowards(look.scale(6)), (e) -> e instanceof LivingEntity && e != caster);
        if (result != null) {
            LivingEntity target = (LivingEntity) result.getEntity();
            if (!target.isAlive()) {
                // Simple revival: set health and particles (you will want to implement real revive logic)
                target.setHealth(target.getMaxHealth() / 2);
                level.playSound(null, target.blockPosition(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
                ((ServerLevel) level).sendParticles(ParticleTypes.HEART, target.getX(), target.getY() + 1, target.getZ(), 30, 0.5, 0.5, 0.5, 0.1);
            }
        }
    }
}