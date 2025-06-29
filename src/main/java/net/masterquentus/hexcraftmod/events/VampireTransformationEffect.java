package net.masterquentus.hexcraftmod.events;

import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class VampireTransformationEffect extends MobEffect {

    public VampireTransformationEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x880000); // Dark Red Color
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            player.sendSystemMessage(Component.literal("You are now a vampire. Find blood or perish!"));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 200 == 0; // Every 10 seconds
    }

    @SubscribeEvent
    public static void onVampireFirstDays(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        if (player.hasEffect(HexcraftEffects.VAMPIRE_TRANSFORMATION.get())) {
            int transformationTime = player.getPersistentData().getInt("VampireTransformationTime");
            transformationTime++;
            player.getPersistentData().putInt("VampireTransformationTime", transformationTime);

            // **Must feed within 3 Minecraft days (6000 ticks per day)**
            if (transformationTime >= 18000) {
                player.sendSystemMessage(Component.literal("You failed to feed. You perish..."));
                player.hurt(player.damageSources().magic(), 9999F); // Instant death
                player.getPersistentData().remove("VampireTransformationTime");
            }
        }
    }
}