package net.masterquentus.hexcraftmod.magic;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = "hexcraftmod", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MagicStaminaTickHandler {

    private static final int REGEN_RATE = 1;
    private static final int REGEN_TICK_INTERVAL = 20;
    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !event.player.level().isClientSide) {
            tickCounter++;
            if (tickCounter >= REGEN_TICK_INTERVAL) {
                tickCounter = 0;
                event.player.getCapability(MagicStaminaProvider.MAGIC_STAMINA_CAPABILITY).ifPresent(stamina -> {
                    stamina.regenerate(REGEN_RATE);
                    System.out.println("[DEBUG] Regenerated stamina for: " + event.player.getName().getString());
                });
            }
        }
    }
}