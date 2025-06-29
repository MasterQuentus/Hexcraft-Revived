package net.masterquentus.hexcraftmod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (VampireKeybinds.BLOODLUST_MODE_KEY.consumeClick()) { // ✅ Fix: Use BLOODLUST_MODE_KEY
            Player player = Minecraft.getInstance().player;
            if (player != null && player.getPersistentData().getBoolean("BloodlustMode")) {
                player.sendSystemMessage(Component.literal("You activated Bloodlust Mode!"));
                player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.DAMAGE_BOOST, 400, 2));
                player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED, 400, 2));
                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - 4); // Drain hunger
            }
        }
    }
}