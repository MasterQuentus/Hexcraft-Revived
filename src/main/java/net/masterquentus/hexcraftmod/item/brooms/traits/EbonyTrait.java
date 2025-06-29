package net.masterquentus.hexcraftmod.item.brooms.traits;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LightLayer;

public class EbonyTrait extends BroomTrait {
    @Override
    public void applyTrait(Player player) {
        // Use getCommandSenderWorld() instead of accessing level directly
        if (player.getCommandSenderWorld().isNight() || player.getCommandSenderWorld().getBrightness(LightLayer.BLOCK, player.blockPosition()) < 5) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 0, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1, false, false));
        }
    }
}