package net.masterquentus.hexcraftmod.magic;

import net.masterquentus.hexcraftmod.spells.SiphonerData;
import net.masterquentus.hexcraftmod.spells.SiphonerDataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityEventHandler {

    public static final ResourceLocation MAGIC_STAMINA_CAP = new ResourceLocation("hexcraftmod", "magic_stamina");

    @SubscribeEvent
    public static void registerCaps(RegisterCapabilitiesEvent event) {
        event.register(MagicStamina.class);
        event.register(SiphonerData.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Player> event) {
        Player player = event.getObject();

        System.out.println("[DEBUG] Attaching capabilities to player: " + player.getScoreboardName());

        // Magic Stamina
        if (!player.getCapability(MagicStaminaProvider.MAGIC_STAMINA_CAPABILITY).isPresent()) {
            event.addCapability(new ResourceLocation("hexcraftmod", "magic_stamina"), new MagicStaminaProvider());
        }

        // Siphoner Data
        if (!player.getCapability(SiphonerDataProvider.SIPHONER_DATA_CAPABILITY).isPresent()) {
            event.addCapability(new ResourceLocation("hexcraftmod", "siphoner_data"), new SiphonerDataProvider());
        }
    }

    @SubscribeEvent
    public static void onClonePlayer(PlayerEvent.Clone event) {
        Player original = event.getOriginal();
        Player clone = event.getEntity();

        System.out.println("[DEBUG] Cloning capabilities during respawn for: " + original.getScoreboardName());

        // Magic Stamina
        original.getCapability(MagicStaminaProvider.MAGIC_STAMINA_CAPABILITY).ifPresent(oldCap -> {
            clone.getCapability(MagicStaminaProvider.MAGIC_STAMINA_CAPABILITY).ifPresent(newCap -> {
                if (oldCap instanceof MagicStamina && newCap instanceof MagicStamina) {
                    ((MagicStamina) newCap).setStamina(((MagicStamina) oldCap).getStamina());
                }
            });
        });

        // Siphoner Data
        original.getCapability(SiphonerDataProvider.SIPHONER_DATA_CAPABILITY).ifPresent(oldCap -> {
            clone.getCapability(SiphonerDataProvider.SIPHONER_DATA_CAPABILITY).ifPresent(newCap -> {
                if (oldCap instanceof SiphonerData && newCap instanceof SiphonerData) {
                    ((SiphonerData) newCap).setSiphoner(((SiphonerData) oldCap).isSiphoner());
                }
            });
        });
    }
}