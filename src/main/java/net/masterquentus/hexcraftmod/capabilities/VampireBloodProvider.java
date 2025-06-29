package net.masterquentus.hexcraftmod.capabilities;

import net.masterquentus.hexcraftmod.compat.VampireBlood;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class VampireBloodProvider implements ICapabilitySerializable<CompoundTag> {
    public static final Capability<VampireBlood> VAMPIRE_BLOOD_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    private final VampireBlood instance = new VampireBlood();
    private final LazyOptional<VampireBlood> optional = LazyOptional.of(() -> instance);

    @Override
    public CompoundTag serializeNBT() {
        return instance.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        instance.deserializeNBT(nbt);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == VAMPIRE_BLOOD_CAPABILITY ? optional.cast() : LazyOptional.empty();
    }
}