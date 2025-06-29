package net.masterquentus.hexcraftmod.item.brooms.traits;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class PhoenixTrait extends BroomTrait {
    @Override
    public void applyTrait(Player player) {
        if (player.hurtTime > 0) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0, false, false));
        }
    }
}