package net.masterquentus.hexcraftmod.events;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.capabilities.VampireBloodProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class VampireBloodEventHandler {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(HexcraftMod.MOD_ID, "vampire_blood"), new VampireBloodProvider());
        }
    }
}