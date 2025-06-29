package net.masterquentus.hexcraftmod.events;

import net.masterquentus.hexcraftmod.capabilities.VampireBloodProvider;
import net.masterquentus.hexcraftmod.compat.VampireBlood;
import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class VampireFeedingHandler {

    // **Method 1: Right-Click to Feed on Villagers & Players**
    @SubscribeEvent
    public static void onVampireFeed(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        if (player.hasEffect(HexcraftEffects.VAMPIRE_TRANSFORMATION.get()) && event.getTarget() instanceof LivingEntity target) {

            // **Ensure target is a valid food source (Villager or Player)**
            if (target instanceof Villager || target instanceof Player) {
                player.getCapability(VampireBloodProvider.VAMPIRE_BLOOD_CAPABILITY).ifPresent(blood -> {
                    blood.addBlood(4); // **Gain blood from feeding**
                    player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.REGENERATION, 200, 2));
                    player.getFoodData().eat(4, 1.0F); // **Simulate blood feeding**

                    // **Weaken the target (as if blood was drained)**
                    target.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.WEAKNESS, 400, 1));

                    // **Send feedback to the player**
                    player.sendSystemMessage(Component.literal("You drink the blood of your target..."));
                });
            }
        }
    }

    // **Method 2: Attack to Drain Blood from Any Living Entity**
    @SubscribeEvent
    public static void onVampireBite(AttackEntityEvent event) {
        if (!(event.getTarget() instanceof LivingEntity victim)) return;
        if (!(event.getEntity() instanceof Player)) return; // Fix: No need to define 'player' here

        Player player = (Player) event.getEntity(); // Fix: Explicitly cast instead of using 'instanceof'

        // Check if the player is a vampire
        if (player.hasEffect(HexcraftEffects.VAMPIRE_TRANSFORMATION.get())) {
            player.getCapability(VampireBloodProvider.VAMPIRE_BLOOD_CAPABILITY).ifPresent(blood -> {
                // Drain blood from the victim
                blood.addBlood(3);
                victim.hurt(player.damageSources().magic(), 6.0F);

                player.sendSystemMessage(Component.literal("You drain blood from your target..."));

                if (blood.getBloodLevel() >= 10) {
                    player.sendSystemMessage(Component.literal("You are fully fed."));
                }
            });
        }
    }
}