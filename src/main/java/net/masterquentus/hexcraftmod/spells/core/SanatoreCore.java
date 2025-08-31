package net.masterquentus.hexcraftmod.spells.core;

import net.masterquentus.hexcraftmod.spells.CoreComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SanatoreCore implements CoreComponent {
    @Override
    public String getId() {
        return "sanatore";
    }

    @Override
    public void applyEffect(ServerLevel level, Player caster) {
        // Heal caster and nearby allies
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, caster.getBoundingBox().inflate(5));
        for (LivingEntity entity : entities) {
            if (entity.isAlive()) {
                entity.heal(6.0F); // Heal 3 hearts
                level.playSound(null, entity.blockPosition(), SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        }
    }
}