package net.masterquentus.hexcraftmod.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.masterquentus.hexcraftmod.client.gui.VampireAbilitiesScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VampireKeybinds {

    // **Register Keybinding for Bloodlust Mode & GUI**
    public static final KeyMapping BLOODLUST_MODE_KEY = new KeyMapping(
            "key.hexcraftmod.bloodlust", // Bloodlust Mode Activation
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V, // Default key: V
            "key.categories.hexcraft"
    );

    public static final KeyMapping VAMPIRE_GUI_KEY = new KeyMapping(
            "key.hexcraftmod.vampire_gui", // Open Vampire Abilities GUI
            GLFW.GLFW_KEY_V, // Same key: V
            "key.categories.hexcraft"
    );

    // **Register Keybindings**
    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(BLOODLUST_MODE_KEY);
        event.register(VAMPIRE_GUI_KEY);
    }

    // **Handle Key Presses**
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class KeyInputHandler {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            Player player = Minecraft.getInstance().player;
            if (player == null) return;

            // **Open Vampire GUI**
            if (VampireKeybinds.VAMPIRE_GUI_KEY.consumeClick()) {
                Minecraft.getInstance().setScreen(new VampireAbilitiesScreen());
            }

            // **Activate Bloodlust Mode**
            if (VampireKeybinds.BLOODLUST_MODE_KEY.consumeClick() && player.getPersistentData().getBoolean("BloodlustMode")) {
                player.sendSystemMessage(Component.literal("You activated Bloodlust Mode!"));
                player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.DAMAGE_BOOST, 400, 2));
                player.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED, 400, 2));
                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - 4); // Hunger drains quickly
            }
        }
    }
}