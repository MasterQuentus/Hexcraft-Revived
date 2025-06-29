package net.masterquentus.hexcraftmod.item.brooms.traits;

import net.masterquentus.hexcraftmod.item.brooms.BroomEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;

public abstract class BroomTrait {

    public void onBroomUse(Player player) {
        if (player.getVehicle() == null) {
            // Start flying by mounting the broom entity
            player.startRiding(new BroomEntity(EntityType.ITEM, player.getCommandSenderWorld()), true);
        } else {
            // Stop flying by dismounting the broom entity
            player.stopRiding();
        }
    }

    public abstract void applyTrait(Player player);
}