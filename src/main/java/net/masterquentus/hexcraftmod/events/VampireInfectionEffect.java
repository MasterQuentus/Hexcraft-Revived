package net.masterquentus.hexcraftmod.events;

import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class VampireInfectionEffect extends MobEffect {

    public VampireInfectionEffect() {
        super(MobEffectCategory.HARMFUL, 0x550000); // Dark Red Color
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            player.hurt(player.damageSources().magic(), 0.5F * (amplifier + 1)); // Slowly damages player
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 100 == 0; // Applies every 5 seconds
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            if (player.hasEffect(HexcraftEffects.VAMPIRE_INFECTION.get())) {
                event.setCanceled(true); // Prevent normal death
                player.removeEffect(HexcraftEffects.VAMPIRE_INFECTION.get());
                player.addEffect(new MobEffectInstance(HexcraftEffects.VAMPIRE_TRANSFORMATION.get(), 200, 0));
                player.sendSystemMessage(Component.literal("The darkness consumes you..."));
            }
        }
    }
}