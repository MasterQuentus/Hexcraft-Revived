package net.masterquentus.hexcraftmod.spells.core;

import net.masterquentus.hexcraftmod.spells.CoreComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ExpellereCore implements CoreComponent {
    private static final double RANGE = 8.0;
    private static final double KNOCKBACK_STRENGTH = 2.0;

    @Override
    public String getId() {
        return "expellere";
    }

    @Override
    public void applyEffect(ServerLevel level, Player caster) {
        List<Entity> nearby = level.getEntities(caster, caster.getBoundingBox().inflate(RANGE),
                e -> e instanceof LivingEntity && e != caster);

        for (Entity entity : nearby) {
            Vec3 direction = entity.position().subtract(caster.position()).normalize().scale(KNOCKBACK_STRENGTH);
            entity.push(direction.x, 0.5, direction.z);
        }

        level.playSound(null, caster.blockPosition(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.2F);
    }
}