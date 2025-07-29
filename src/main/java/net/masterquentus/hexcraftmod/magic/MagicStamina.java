package net.masterquentus.hexcraftmod.magic;

import net.minecraft.nbt.CompoundTag;

public class MagicStamina {
    private int maxStamina = 100;
    private int stamina = maxStamina;

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int value) {
        stamina = Math.max(0, Math.min(value, maxStamina));
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(int max) {
        maxStamina = max;
        if (stamina > maxStamina) {
            stamina = maxStamina;
        }
    }

    /**
     * Attempts to consume stamina. Returns true if successful.
     */
    public boolean consume(int amount) {
        if (stamina >= amount) {
            stamina -= amount;
            return true;
        }
        return false;
    }

    /**
     * Restores stamina up to the max.
     */
    public void regenerate(int amount) {
        setStamina(stamina + amount);
    }

    /**
     * Saves stamina values to NBT.
     */
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("Stamina", stamina);
        tag.putInt("MaxStamina", maxStamina);
        return tag;
    }

    /**
     * Loads stamina values from NBT.
     */
    public void deserializeNBT(CompoundTag tag) {
        stamina = tag.getInt("Stamina");
        maxStamina = tag.getInt("MaxStamina");
    }
}