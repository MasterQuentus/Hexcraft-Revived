package net.masterquentus.hexcraftmod.worldgen.tree.custom;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class HexcraftTreePlacers {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, "hexcraftmod");

    public static final RegistryObject<TrunkPlacerType<WitchHazelTrunkPlacer>> WITCH_HAZEL_TRUNK_PLACER =
            TRUNK_PLACERS.register("witch_hazel_trunk_placer",
                    () -> new TrunkPlacerType<>(WitchHazelTrunkPlacer.CODEC));

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, "hexcraftmod");

    public static final RegistryObject<FoliagePlacerType<WitchHazelFoliagePlacer>> WITCH_HAZEL_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("witch_hazel_foliage_placer",
                    () -> new FoliagePlacerType<>(WitchHazelFoliagePlacer.CODEC));
}
