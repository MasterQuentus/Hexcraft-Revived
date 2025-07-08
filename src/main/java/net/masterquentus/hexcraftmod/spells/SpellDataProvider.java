package net.masterquentus.hexcraftmod.spells;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;

public class SpellDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final Capability<ISpellData> SPELL_DATA =
            CapabilityManager.get(new CapabilityToken<>(){});

    private final SpellData backend = new SpellData();
    private final LazyOptional<ISpellData> optional = LazyOptional.of(() -> backend);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        return cap == SPELL_DATA ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return backend.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        backend.deserializeNBT(tag);
    }
}