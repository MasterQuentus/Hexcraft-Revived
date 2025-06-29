package net.masterquentus.hexcraftmod.client.render;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VampireHUDRenderer {

    @SubscribeEvent
    public static void onRenderHUD(RenderGuiOverlayEvent event) {
        com.yourmod.hexcraftmod.client.gui.VampireHUD.renderHUD(event.getGuiGraphics(), Minecraft.getInstance());
    }
}