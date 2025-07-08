package net.masterquentus.hexcraftmod.events;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.masterquentus.hexcraftmod.spells.SpellDataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HexcraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEventHandler {

    // Attach spell capability to players
    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(HexcraftMod.MOD_ID, "spell_data"), new SpellDataProvider());
        }
    }

    // Copy spell data on player death and respawn
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return;

        event.getOriginal().getCapability(SpellDataProvider.SPELL_DATA).ifPresent(oldStore -> {
            event.getEntity().getCapability(SpellDataProvider.SPELL_DATA).ifPresent(newStore -> {
                for (String spell : oldStore.getKnownSpells()) {
                    newStore.learnSpell(spell);
                }
            });
        });
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        if (player == null) return;

        // Check if player is vampire
        if (player.hasEffect(HexcraftEffects.VAMPIRE_TRANSFORMATION.get())) {
            if (!player.level().isClientSide && player.level().isDay()) {
                boolean hasDaylightRing = player.getInventory().contains(new ItemStack(HexcraftItems.BLESSED_DAYLIGHT_RING.get()));
                if (!hasDaylightRing) {
                    player.setSecondsOnFire(4);
                }
            }
        }
    }

    // Register this class on Forge bus
    public static void register() {
        MinecraftForge.EVENT_BUS.register(PlayerEventHandler.class);
    }
}