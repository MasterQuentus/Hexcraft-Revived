package net.masterquentus.hexcraftmod.worldgen.tree.custom;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class HexcraftTrunkPlacers {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, HexcraftMod.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<WitchHazelTrunkPlacer>> WITCH_HAZEL_TRUNK_PLACER =
            TRUNK_PLACERS.register("witch_hazel_trunk_placer",
                    () -> new TrunkPlacerType<>(WitchHazelTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACERS.register(eventBus);
    }
}