package net.masterquentus.hexcraftmod.effects;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class VampireTransformationEffect extends MobEffect {
    public VampireTransformationEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x550000); // Dark Red Effect
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            player.sendSystemMessage(Component.literal("You have fully transformed into a Vampire!"));
            player.addEffect(new MobEffectInstance(HexcraftEffects.VAMPIRE_TRANSFORMATION.get(), Integer.MAX_VALUE, 0));
            player.removeEffect(this);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 40 == 0;
    }
}