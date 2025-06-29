package net.masterquentus.hexcraftmod.block.events;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.config.HexcraftConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HexcraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PandoraMobLootHandler {

    @SubscribeEvent
    public static void onMobDrop(LivingDropsEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Mob mobEntity && mobEntity.getPersistentData().getBoolean("Hexcraft_PandorasMob")) {
            if (!HexcraftConfig.PANDORA_MOBS_DROP_ITEMS.get()) {
                event.setCanceled(true); // 🚫 Prevents loot drops if disabled in config
            }
        }
    }

    @SubscribeEvent
    public static void onMobXPDrop(LivingExperienceDropEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Mob mobEntity && mobEntity.getPersistentData().getBoolean("Hexcraft_PandorasMob")) {
            if (!HexcraftConfig.PANDORA_MOBS_DROP_XP.get()) {
                event.setCanceled(true); // 🚫 Prevents XP drops if disabled in config
            }
        }
    }
}