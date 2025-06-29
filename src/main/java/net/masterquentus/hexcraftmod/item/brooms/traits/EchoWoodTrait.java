package net.masterquentus.hexcraftmod.item.brooms.traits;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class EchoWoodTrait extends BroomTrait {
    @Override
    public void applyTrait(Player player) {
        player.getCommandSenderWorld().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(3.0D))
                .forEach(entity -> {
                    if (entity != player) {
                        entity.knockback(1.5F, -player.getLookAngle().x, -player.getLookAngle().z); // Sonic Boom effect
                    }
                });
    }
}