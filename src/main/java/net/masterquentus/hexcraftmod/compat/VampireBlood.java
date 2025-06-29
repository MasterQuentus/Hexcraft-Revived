package net.masterquentus.hexcraftmod.compat;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class VampireBlood {
    private int bloodLevel = 10; // Default blood level (10 max)

    public int getBloodLevel() {
        return bloodLevel;
    }

    public void setBloodLevel(int amount) {
        this.bloodLevel = Math.max(0, Math.min(amount, 10)); // Limit between 0 and 10
    }

    public void addBlood(int amount) {
        this.bloodLevel = Math.min(this.bloodLevel + amount, 10);
    }

    public void drainBlood(int amount) {
        this.bloodLevel = Math.max(this.bloodLevel - amount, 0);
    }

    public boolean isHungry() {
        return bloodLevel <= 3; // If blood is too low, vampire is starving
    }

    // Save data
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("BloodLevel", bloodLevel);
        return tag;
    }

    // Load data
    public void deserializeNBT(CompoundTag tag) {
        this.bloodLevel = tag.getInt("BloodLevel");
    }
}