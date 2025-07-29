package net.masterquentus.hexcraftmod.sound;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.platform.services.CommonServices;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class HexcraftSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HexcraftMod.MOD_ID);

    public static final RegistryObject<SoundEvent> CHALK_WRITE = SOUND_EVENTS.register("chalk_write",
            () -> SoundEvent.createVariableRangeEvent(HexcraftMod.id("chalk_write")));

    public static final RegistryObject<SoundEvent> BROOM_SWEEP = SOUND_EVENTS.register("broom_sweep",
            () -> SoundEvent.createVariableRangeEvent(HexcraftMod.id("broom_sweep")));

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}