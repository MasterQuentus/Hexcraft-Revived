package net.masterquentus.hexcraftmod.item.brooms.traits;

import net.minecraft.world.entity.player.Player;

public class DistortedTrait extends BroomTrait {
    @Override
    public void applyTrait(Player player) {
        // Unpredictable speed changes (could be randomized boosts/slows)
        double speedFactor = (Math.random() > 0.5) ? 1.5 : 0.5;
        player.setDeltaMovement(player.getDeltaMovement().multiply(speedFactor, 1, speedFactor));
    }
}