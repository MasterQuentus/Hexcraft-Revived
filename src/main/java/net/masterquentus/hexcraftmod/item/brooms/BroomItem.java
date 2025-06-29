package net.masterquentus.hexcraftmod.item.brooms;

import net.masterquentus.hexcraftmod.item.brooms.traits.BroomTrait;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public class BroomItem extends Item {
    private final BroomTrait trait;

    public BroomItem(Properties properties, BroomTrait trait) {
        super(properties);
        this.trait = trait;
    }

    public void applyTrait(Player player) {
        trait.applyTrait(player);
    }
}