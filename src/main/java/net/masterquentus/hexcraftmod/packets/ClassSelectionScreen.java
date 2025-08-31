package net.masterquentus.hexcraftmod.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;


public class ClassSelectionScreen extends Screen {

    public ClassSelectionScreen() {
        super(Component.literal("Select Your Class"));
    }

    @Override
    protected void init() {
        int spacing = 25;
        int buttonWidth = 150;
        int buttonHeight = 20;
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.addRenderableWidget(Button.builder(Component.translatable("class.human"), btn -> selectClass("human"))
                .pos(centerX - buttonWidth / 2, centerY - spacing * 3 / 2)
                .size(buttonWidth, buttonHeight)
                .build());

        this.addRenderableWidget(Button.builder(Component.translatable("class.witch"), btn -> selectClass("witch"))
                .pos(centerX - buttonWidth / 2, centerY - spacing / 2)
                .size(buttonWidth, buttonHeight)
                .build());

        this.addRenderableWidget(Button.builder(Component.translatable("class.vampire"), btn -> selectClass("vampire"))
                .pos(centerX - buttonWidth / 2, centerY + spacing / 2)
                .size(buttonWidth, buttonHeight)
                .build());

        this.addRenderableWidget(Button.builder(Component.translatable("class.werewolf"), btn -> selectClass("werewolf"))
                .pos(centerX - buttonWidth / 2, centerY + spacing * 3 / 2)
                .size(buttonWidth, buttonHeight)
                .build());
    }

    private void selectClass(String className) {
        HexcraftModNetworking.CHANNEL.sendToServer(new ClassSelectedPacket(className));
        Minecraft.getInstance().setScreen(null);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}