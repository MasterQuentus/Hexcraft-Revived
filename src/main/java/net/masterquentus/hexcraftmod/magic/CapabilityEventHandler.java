package net.masterquentus.hexcraftmod.magic;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityEventHandler {

    public static final ResourceLocation MAGIC_STAMINA_CAP = new ResourceLocation("hexcraftmod", "magic_stamina");

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Player> event) {
        System.out.println("[DEBUG] Attaching MagicStamina capability to player: " + event.getObject().getScoreboardName());

        if (!event.getObject().getCapability(MagicStaminaProvider.MAGIC_STAMINA_CAPABILITY).isPresent()) {
            event.addCapability(MAGIC_STAMINA_CAP, new MagicStaminaProvider());
        }
    }

    @SubscribeEvent
    public static void onClonePlayer(PlayerEvent.Clone event) {
        System.out.println("[DEBUG] Cloning MagicStamina capability during respawn/death for: " + event.getOriginal().getScoreboardName());

        event.getOriginal().getCapability(MagicStaminaProvider.MAGIC_STAMINA_CAPABILITY).ifPresent(oldCap -> {
            event.getEntity().getCapability(MagicStaminaProvider.MAGIC_STAMINA_CAPABILITY).ifPresent(newCap -> {
                ((MagicStamina) newCap).setStamina(((MagicStamina) oldCap).getStamina());
            });
        });
    }
}