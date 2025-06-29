package net.masterquentus.hexcraftmod.item.brooms.traits;

import net.minecraft.world.entity.player.Player;

public class TwistedTrait extends BroomTrait {
    @Override
    public void applyTrait(Player player) {
        player.setDeltaMovement(player.getDeltaMovement().multiply(2.0D, 1.0D, 2.0D));
    }
}