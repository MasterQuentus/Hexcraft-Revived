package net.masterquentus.hexcraftmod.item.brooms.traits;

import net.minecraft.world.entity.player.Player;

public class WillowTrait extends BroomTrait {
    @Override
    public void applyTrait(Player player) {
        if (player.isFallFlying()) {
            // Make player glide longer distances with slow falling effect
            player.getAbilities().mayfly = true; // Enable glide
        }
    }
}