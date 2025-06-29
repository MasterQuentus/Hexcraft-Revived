package net.masterquentus.hexcraftmod.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DreamweaverCharmItem extends Item {
    public DreamweaverCharmItem(Properties properties) {
        super(properties);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (!player.level().isClientSide) {
            if (hasCharmInInventory(player)) {
                applyEffects(player);
            }
        }
    }

    private static boolean hasCharmInInventory(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof DreamweaverCharmItem) {
                return true;
            }
        }
        return false;
    }

    private static void applyEffects(Player player) {
        if (!player.hasEffect(MobEffects.NIGHT_VISION)) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, true, false));
        }
        if (!player.hasEffect(MobEffects.DAMAGE_RESISTANCE)) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 220, 0, true, false));
        }
    }
}