package net.masterquentus.hexcraftmod.packets;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class HexcraftModNetworking {
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(HexcraftMod.MOD_ID, "network"),
            () -> "1.0",
            s -> true,
            s -> true
    );

    public static void register() {
        int id = 0;
        CHANNEL.registerMessage(id++, OpenClassSelectionGuiPacket.class, OpenClassSelectionGuiPacket::encode,
                OpenClassSelectionGuiPacket::decode, OpenClassSelectionGuiPacket::handle);

        CHANNEL.registerMessage(id++, ClassSelectedPacket.class, ClassSelectedPacket::encode,
                ClassSelectedPacket::decode, ClassSelectedPacket::handle);
    }
}