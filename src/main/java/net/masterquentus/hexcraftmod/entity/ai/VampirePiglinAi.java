package net.masterquentus.hexcraftmod.entity.ai;

import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class VampirePiglinAi {
    public static boolean wantsToPickup(Entity entity, ItemStack stack) {
        // replicate logic from PiglinAi.wantsToPickup here
        return isLovedItem(stack) || stack.is(Items.CROSSBOW);
    }

    public static boolean isLovedItem(ItemStack stack) {
        return stack.is(HexcraftItems.VAMPIRIC_GEM.get());
    }

    public static void pickUpItem(Entity entity, ItemEntity itemEntity) {

    }
}