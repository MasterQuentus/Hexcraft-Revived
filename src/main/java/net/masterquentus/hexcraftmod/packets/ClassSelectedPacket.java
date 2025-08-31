package net.masterquentus.hexcraftmod.packets;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ClassSelectedPacket {

    private final String selectedClass;

    public ClassSelectedPacket(String selectedClass) {
        this.selectedClass = selectedClass;
    }

    public static void encode(ClassSelectedPacket pkt, FriendlyByteBuf buf) {
        buf.writeUtf(pkt.selectedClass);
    }

    public static ClassSelectedPacket decode(FriendlyByteBuf buf) {
        return new ClassSelectedPacket(buf.readUtf(32767));
    }

    public static void handle(ClassSelectedPacket pkt, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                CompoundTag data = player.getPersistentData();
                CompoundTag persistedTag = data.getCompound(Player.PERSISTED_NBT_TAG);

                persistedTag.putString("hexcraft_player_class", pkt.selectedClass);
                persistedTag.putBoolean("hexcraft_class_selected", true);

                data.put(Player.PERSISTED_NBT_TAG, persistedTag);

                player.sendSystemMessage(Component.literal("You selected class: " + pkt.selectedClass));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}