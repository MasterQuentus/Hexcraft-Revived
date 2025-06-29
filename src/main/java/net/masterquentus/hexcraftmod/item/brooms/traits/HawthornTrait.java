package net.masterquentus.hexcraftmod.item.brooms.traits;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class HawthornTrait extends BroomTrait {
    @Override
    public void applyTrait(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 3, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0, false, false));
    }
}