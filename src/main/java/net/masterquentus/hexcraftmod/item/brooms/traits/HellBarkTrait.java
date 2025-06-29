package net.masterquentus.hexcraftmod.item.brooms.traits;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class HellBarkTrait extends BroomTrait {
    @Override
    public void applyTrait(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0, false, false));
    }
}