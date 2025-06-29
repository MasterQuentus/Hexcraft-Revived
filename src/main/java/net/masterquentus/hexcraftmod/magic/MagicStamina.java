package net.masterquentus.hexcraftmod.magic;

import net.minecraft.nbt.CompoundTag;

public class MagicStamina {
    private int maxStamina = 100;
    private int stamina = maxStamina;

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int value) {
        stamina = Math.min(value, maxStamina);
        if (stamina < 0) stamina = 0;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(int max) {
        maxStamina = max;
        if (stamina > maxStamina) stamina = maxStamina;
    }

    public boolean consume(int amount) {
        if (stamina >= amount) {
            stamina -= amount;
            return true;
        }
        return false;
    }

    public void regenerate(int amount) {
        setStamina(stamina + amount);
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("Stamina", stamina);
        tag.putInt("MaxStamina", maxStamina);
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        stamina = tag.getInt("Stamina");
        maxStamina = tag.getInt("MaxStamina");
    }
}