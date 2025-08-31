package net.masterquentus.hexcraftmod.events;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.packets.HexcraftModNetworking;
import net.masterquentus.hexcraftmod.packets.OpenClassSelectionGuiPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = HexcraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerJoinHandler {

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;

        CompoundTag persistentData = serverPlayer.getPersistentData();
        CompoundTag persistedTag = persistentData.getCompound(Player.PERSISTED_NBT_TAG);

        if (!persistedTag.getBoolean("hexcraft_class_selected")) {
            // Send packet to open GUI
            HexcraftModNetworking.CHANNEL.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new OpenClassSelectionGuiPacket());
        } else {
            // For debugging, print the stored class (if you stored it)
            String selectedClass = persistedTag.getString("hexcraft_selected_class");
            System.out.println("Player " + serverPlayer.getName().getString() + " has selected class: " + selectedClass);
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;

        CompoundTag persistentData = serverPlayer.getPersistentData();
        CompoundTag persistedTag = persistentData.getCompound(Player.PERSISTED_NBT_TAG);

        if (!persistedTag.getBoolean("hexcraft_class_selected")) {
            HexcraftModNetworking.CHANNEL.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new OpenClassSelectionGuiPacket());
        }
    }

    // **This is the crucial part: a method you call when the player picks their class**
    // This is just an example: you need to call this method yourself in your packet handler
    public static void saveClassSelection(ServerPlayer player, String selectedClass) {
        CompoundTag persistentData = player.getPersistentData();
        CompoundTag persistedTag = persistentData.getCompound(Player.PERSISTED_NBT_TAG);

        persistedTag.putBoolean("hexcraft_class_selected", true);
        persistedTag.putString("hexcraft_selected_class", selectedClass);
        persistentData.put(Player.PERSISTED_NBT_TAG, persistedTag);


        System.out.println("Saved class selection for player " + player.getName().getString() + ": " + selectedClass);
    }
}