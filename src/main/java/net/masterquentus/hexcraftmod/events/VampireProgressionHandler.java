package net.masterquentus.hexcraftmod.events;

import net.masterquentus.hexcraftmod.capabilities.VampireBloodProvider;
import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.masterquentus.hexcraftmod.item.custom.BlessedDaylightRing;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class VampireProgressionHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player == null || !player.hasEffect(HexcraftEffects.VAMPIRE_TRANSFORMATION.get())) return;

        // ☀️ Sunlight Weakness - Vampires Burn Unless Wearing the Blessed Daylight Ring
        if (player.level().isDay()) {
            boolean hasDaylightRing = false;
            for (ItemStack stack : player.getInventory().items) {
                if (stack.getItem() instanceof BlessedDaylightRing) {
                    hasDaylightRing = true;
                    break;
                }
            }
            if (!hasDaylightRing) {
                player.setSecondsOnFire(4); // Burn for 4 seconds
            }
        }

        // **🩸 Handle Vampire Starvation**
        player.getCapability(VampireBloodProvider.VAMPIRE_BLOOD_CAPABILITY).ifPresent(blood -> {
            if (blood.getBloodLevel() < 3) {
                player.sendSystemMessage(Component.literal("You are starving and feel weak..."));
                player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.WEAKNESS, 100, 1));
            }
        });

        // **⏳ Track vampire progression**
        int vampireTime = player.getPersistentData().getInt("vampireTime");
        vampireTime++;
        player.getPersistentData().putInt("vampireTime", vampireTime);

        // **🧛 Unlock Vampire Abilities Over Time**
        if (vampireTime >= 6000 && !player.getPersistentData().getBoolean("EnhancedStrength")) {
            player.getPersistentData().putBoolean("EnhancedStrength", true);
            player.sendSystemMessage(Component.literal("You unlocked Enhanced Strength!"));
        }
        if (vampireTime >= 12000 && !player.getPersistentData().getBoolean("EnhancedSpeed")) {
            player.getPersistentData().putBoolean("EnhancedSpeed", true);
            player.sendSystemMessage(Component.literal("You unlocked Enhanced Speed!"));
        }
        if (vampireTime >= 18000 && !player.getPersistentData().getBoolean("BloodVision")) {
            player.getPersistentData().putBoolean("BloodVision", true);
            player.sendSystemMessage(Component.literal("You unlocked Blood Vision!"));
        }
        if (vampireTime >= 24000 && !player.getPersistentData().getBoolean("BloodlustMode")) {
            player.getPersistentData().putBoolean("BloodlustMode", true);
            player.sendSystemMessage(Component.literal("You unlocked Bloodlust Mode!"));
        }

        // **⚡ Apply Passive Vampire Abilities**
        if (player.getPersistentData().getBoolean("EnhancedStrength")) {
            player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.DAMAGE_BOOST, 40, 1, false, false));
        }
        if (player.getPersistentData().getBoolean("EnhancedSpeed")) {
            player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED, 40, 1, false, false));
        }
        if (player.getPersistentData().getBoolean("BloodVision") && player.isShiftKeyDown()) { // ✅ Fix: Use `isShiftKeyDown()`
            player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.NIGHT_VISION, 200, 0, false, false));
            player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.GLOWING, 200, 0, false, false));
        }

        // **🔥 Apply Hunger Drain for Bloodlust Mode**
        if (player.getPersistentData().getBoolean("BloodlustMode") && player.getFoodData().getFoodLevel() > 4) {
            player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.DAMAGE_BOOST, 200, 2, false, false));
            player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED, 200, 2, false, false));
            player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - 1); // **Drains hunger quickly**
        }

        // **🩸 Transformation Effects**
        if (player.hasEffect(HexcraftEffects.VAMPIRE_TRANSFORMATION.get())) {
            player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.DAMAGE_BOOST, 40, 1, false, false));
            player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED, 40, 1, false, false));
            player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.NIGHT_VISION, 200, 0, false, false));

            // **Apply hunger reduction effect**
            if (player.getFoodData().getFoodLevel() < 6) {
                player.sendSystemMessage(Component.literal("You feel your hunger growing..."));
            }
        }
    }
}