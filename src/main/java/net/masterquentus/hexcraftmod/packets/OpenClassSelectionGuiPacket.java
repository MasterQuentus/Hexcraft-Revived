package net.masterquentus.hexcraftmod.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenClassSelectionGuiPacket {

    public OpenClassSelectionGuiPacket() {
    }

    public static void encode(OpenClassSelectionGuiPacket msg, FriendlyByteBuf buf) {
        // No data needed here
    }

    public static OpenClassSelectionGuiPacket decode(FriendlyByteBuf buf) {
        return new OpenClassSelectionGuiPacket();
    }

    public static void handle(OpenClassSelectionGuiPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // On client side, open the GUI
            Minecraft.getInstance().setScreen(new ClassSelectionScreen());
        });
        ctx.get().setPacketHandled(true);
    }
}