package net.masterquentus.hexcraftmod.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.gui.components.Button;
import net.minecraft.world.effect.MobEffectInstance;

public class VampireAbilitiesScreen extends Screen {
    private static final ResourceLocation TEXTURE = new ResourceLocation(HexcraftMod.MOD_ID, "textures/gui/vampire_abilities_screen.png");

    public VampireAbilitiesScreen() {
        super(Component.translatable("screen.hexcraft.vampire_abilities"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // ✅ Updated: Use Button.Builder for compatibility with modern versions
        this.addRenderableWidget(Button.builder(Component.literal("Activate Blood Vision"), button -> {
            if (this.minecraft != null && this.minecraft.player != null) {
                this.minecraft.player.addEffect(new MobEffectInstance(HexcraftEffects.BLOOD_VISION.get(), 1200, 0));
            }
        }).bounds(centerX - 50, centerY - 40, 100, 20).build());

        this.addRenderableWidget(Button.builder(Component.literal("Activate Bloodlust"), button -> {
            if (this.minecraft != null && this.minecraft.player != null) {
                this.minecraft.player.addEffect(new MobEffectInstance(HexcraftEffects.BLOODLUST.get(), 1200, 0));
            }
        }).bounds(centerX - 50, centerY, 100, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}