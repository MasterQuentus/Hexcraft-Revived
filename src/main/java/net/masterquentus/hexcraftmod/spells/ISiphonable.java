package net.masterquentus.hexcraftmod.spells;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


/**
 * Represents an object (usually an item) that can be siphoned for magic charge.
 */
public interface ISiphonable {

    /**
     * Returns the amount of magic charge this object provides when siphoned.
     *
     * @return the siphon power value
     */
    int getSiphonPower();

    /**
     * Called when this object is siphoned by a player.
     *
     * @param stack  the ItemStack being siphoned
     * @param player the player siphoning the item
     */
    void onSiphoned(ItemStack stack, Player player);
}