package net.masterquentus.hexcraftmod.item.brooms.traits;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class CedarTrait extends BroomTrait {
    @Override
    public void applyTrait(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1, false, false));
    }
}