package net.masterquentus.hexcraftmod.effects;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class VampireInfectionEffect extends MobEffect {
    public VampireInfectionEffect() {
        super(MobEffectCategory.HARMFUL, 0x550000); // Dark Red Effect
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            if (player.getHealth() <= 0.5F) { // Near death
                turnPlayerIntoVampire(player);
            }
        }
    }

    private void turnPlayerIntoVampire(Player player) {
        player.sendSystemMessage(Component.literal("You feel the darkness consuming you..."));
        player.addEffect(new MobEffectInstance(HexcraftEffects.VAMPIRE_TRANSFORMATION.get(), 200, 0));
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}