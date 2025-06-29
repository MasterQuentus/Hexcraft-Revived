package net.masterquentus.hexcraftmod.item.custom;

import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlessedDaylightRing extends Item {

    public BlessedDaylightRing(Properties properties) {
        super(properties);
    }

    // FIX: Remove @Override since inventoryTick is not in Item class
    public void tickWhileInInventory(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player) {
            if (player.hasEffect(HexcraftEffects.VAMPIRE_TRANSFORMATION.get()) && level.isDay()) {
                // Prevent burning
                player.clearFire();
            }
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true; // Gives the item an enchanted glow
    }
}