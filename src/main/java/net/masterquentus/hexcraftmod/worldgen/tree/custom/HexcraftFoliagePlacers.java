package net.masterquentus.hexcraftmod.worldgen.tree.custom;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class HexcraftFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, HexcraftMod.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<WitchHazelFoliagePlacer>> WITCH_HAZEL_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("witch_hazel_foliage_placer",
                    () -> new FoliagePlacerType<>(WitchHazelFoliagePlacer.CODEC));



    public static void register(IEventBus eventBus) {
        FOLIAGE_PLACERS.register(eventBus);
    }
}