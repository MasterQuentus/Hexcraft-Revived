package net.masterquentus.hexcraftmod.events;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HexcraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEventHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return; // Prevents duplicate execution

        Player player = event.player;
        if (player == null) return; // Failsafe

        // **Check if the player is a vampire**
        if (player.hasEffect(HexcraftEffects.VAMPIRE_TRANSFORMATION.get())) {

            // **Sunlight Weakness - Vampires burn unless they have the Blessed Daylight Ring**
            if (!player.level().isClientSide && player.level().isDay()) {
                boolean hasDaylightRing = player.getInventory().contains(new ItemStack(HexcraftItems.BLESSED_DAYLIGHT_RING.get()));
                if (!hasDaylightRing) {
                    player.setSecondsOnFire(4); // Burns for 4 seconds
                }
            }
        }
    }

    // Ensure this class is registered in HexcraftMod.java
    public static void register() {
        MinecraftForge.EVENT_BUS.register(PlayerEventHandler.class);
    }
}