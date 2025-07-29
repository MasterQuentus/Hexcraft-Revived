package net.masterquentus.hexcraftmod.block.events;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.magic.MagicStaminaProvider;
import net.masterquentus.hexcraftmod.spells.SiphonerDataProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerEvents {
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            player.getCapability(MagicStaminaProvider.MAGIC_STAMINA_CAPABILITY).ifPresent(stamina -> {
                HexcraftMod.LOGGER.info(">>> " + player.getName().getString() + " stamina = " + stamina.getStamina());
            });

            player.getCapability(SiphonerDataProvider.SIPHONER_DATA_CAPABILITY).ifPresent(siphon -> {
                HexcraftMod.LOGGER.info(">>> " + player.getName().getString() + " siphoner status = " + siphon.isSiphoner());
            });
        }
    }
}