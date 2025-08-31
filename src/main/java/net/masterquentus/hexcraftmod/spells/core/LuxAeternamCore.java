package net.masterquentus.hexcraftmod.spells.core;

import net.masterquentus.hexcraftmod.spells.CoreComponent;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class LuxAeternamCore implements CoreComponent {
    @Override
    public String getId() {
        return "lux_aeternam";
    }

    @Override
    public void applyEffect(ServerLevel level, Player caster) {
        // Area sunlight burst that blinds mobs temporarily
        Vec3 pos = caster.position();
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, new AABB(
                pos.x - 5, pos.y - 5, pos.z - 5,
                pos.x + 5, pos.y + 5, pos.z + 5));

        for (LivingEntity entity : entities) {
            if (entity.isAlive() && entity != caster && entity instanceof Monster) {
                entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 1)); // 5 seconds blind
            }
        }
        level.playSound(null, caster.blockPosition(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.0F);
        ((ServerLevel) level).sendParticles(ParticleTypes.END_ROD, pos.x, pos.y + 1, pos.z, 50, 2, 2, 2, 0.1);
    }
}