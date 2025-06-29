package net.masterquentus.hexcraftmod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.compat.VampireBlood;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class BloodHungerHUD {
    private static final ResourceLocation BLOOD_HUD = new ResourceLocation(HexcraftMod.MOD_ID, "textures/gui/blood_hunger.png");

    public static void renderBloodHUD(GuiGraphics guiGraphics, Minecraft minecraft, VampireBlood blood) {
        if (minecraft.player == null) return;

        int screenWidth = minecraft.getWindow().getGuiScaledWidth();
        int screenHeight = minecraft.getWindow().getGuiScaledHeight();
        int bloodLevel = blood.getBloodLevel();

        // Render blood hunger bar at the bottom
        RenderSystem.setShaderTexture(0, BLOOD_HUD);
        guiGraphics.blit(BLOOD_HUD, screenWidth / 2 - 91, screenHeight - 40, 0, (10 - bloodLevel) * 9, 182, 9);
    }
}