package net.masterquentus.hexcraftmod.spells;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ExpellereSpell extends HexSpell {
    private static final double RADIUS = 6.0;
    private static final double KNOCKBACK_STRENGTH = 1.5;

    @Override
    public String getId() {
        return "expellere";
    }

    @Override
    public void cast(ServerLevel level, Player caster) {
        Vec3 pos = caster.position();

        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class,
                new AABB(pos.x - RADIUS, pos.y - RADIUS, pos.z - RADIUS,
                        pos.x + RADIUS, pos.y + RADIUS, pos.z + RADIUS));

        for (LivingEntity entity : entities) {
            if (entity != caster && entity.isAlive()) {
                Vec3 dir = entity.position().subtract(pos).normalize().scale(KNOCKBACK_STRENGTH);
                entity.push(dir.x, 0.5, dir.z);
                entity.removeAllEffects();  // Dispels potion effects
            }
        }

        level.playSound(null, caster.blockPosition(), SoundEvents.EVOKER_CAST_SPELL, SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}