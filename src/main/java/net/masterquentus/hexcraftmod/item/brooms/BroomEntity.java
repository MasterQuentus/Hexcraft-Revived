package net.masterquentus.hexcraftmod.item.brooms;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class BroomEntity extends Entity {
    public BroomEntity(EntityType<? extends Entity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public void tick() {
        // Handle flight logic here, e.g., apply speed, maneuverability, etc.
        if (this.getVehicle() instanceof Player player) {
            // Apply traits while flying
            BroomItem broomItem = (BroomItem) player.getMainHandItem().getItem();
            broomItem.applyTrait(player);
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }

    public void onBroomUse(Player player) {
        if (player.getVehicle() == null) {
            // Start flying by mounting the broom entity
            player.startRiding(new BroomEntity(EntityType.ITEM, player.getCommandSenderWorld()), true);
        } else {
            // Stop flying by dismounting the broom entity
            player.stopRiding();
        }
    }
}