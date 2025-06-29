package com.yourmod.hexcraftmod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class VampireHUD {
    private static final ResourceLocation BLOOD_BAR = new ResourceLocation(HexcraftMod.MOD_ID, "textures/gui/blood_bar.png");
    private static final ResourceLocation ABILITY_ICONS = new ResourceLocation(HexcraftMod.MOD_ID, "textures/gui/vampire_abilities.png");

    public static void renderHUD(GuiGraphics guiGraphics, Minecraft minecraft) {
        if (minecraft.player == null) return;

        // Check if the player is a vampire
        if (minecraft.player.hasEffect(HexcraftEffects.VAMPIRE_TRANSFORMATION.get())) {
            int screenWidth = minecraft.getWindow().getGuiScaledWidth();
            int screenHeight = minecraft.getWindow().getGuiScaledHeight();

            // Render the blood bar at the bottom
            RenderSystem.setShaderTexture(0, BLOOD_BAR);
            guiGraphics.blit(BLOOD_BAR, screenWidth / 2 - 91, screenHeight - 40, 0, 0, 182, 9);

            // Render active vampire abilities
            int x = screenWidth / 2 + 100;
            int y = screenHeight - 50;

            if (minecraft.player.hasEffect(HexcraftEffects.BLOOD_VISION.get())) {
                RenderSystem.setShaderTexture(0, ABILITY_ICONS);
                guiGraphics.blit(ABILITY_ICONS, x, y, 0, 0, 16, 16);
            }
            if (minecraft.player.hasEffect(HexcraftEffects.BLOODLUST.get())) {
                RenderSystem.setShaderTexture(0, ABILITY_ICONS);
                guiGraphics.blit(ABILITY_ICONS, x + 20, y, 16, 0, 16, 16);
            }
        }
    }
}