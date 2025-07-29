package net.masterquentus.hexcraftmod.spells;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * Capability Provider for the ISiphonerData capability.
 */
public class SiphonerDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final Capability<ISiphonerData> SIPHONER_DATA_CAPABILITY =
            CapabilityManager.get(new CapabilityToken<>() {});

    private final SiphonerData backend = new SiphonerData();
    private final LazyOptional<ISiphonerData> optional = LazyOptional.of(() -> backend);

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == SIPHONER_DATA_CAPABILITY ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return backend.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        backend.deserializeNBT(nbt);
    }

    // Optional: Useful for copying data when player respawns or changes dimension
    public void copyFrom(SiphonerDataProvider other) {
        this.backend.deserializeNBT(other.backend.serializeNBT());
    }
}