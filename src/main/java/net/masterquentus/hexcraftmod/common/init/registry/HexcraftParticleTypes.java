package net.masterquentus.hexcraftmod.common.init.registry;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HexcraftParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, HexcraftMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> ASH_PARTICLE = PARTICLE_TYPES.register("ash_particle",
            () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
