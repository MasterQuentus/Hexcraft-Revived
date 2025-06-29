package net.masterquentus.hexcraftmod.magic;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.*;

import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MagicStaminaProvider implements ICapabilitySerializable<CompoundTag> {
    public static Capability<MagicStamina> MAGIC_STAMINA_CAPABILITY = CapabilityManager.get(new CapabilityToken<MagicStamina>(){});

    private MagicStamina instance = new MagicStamina();

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable net.minecraft.core.Direction side) {
        return cap == MAGIC_STAMINA_CAPABILITY ? LazyOptional.of(() -> instance).cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return instance.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        instance.deserializeNBT(nbt);
    }
}
