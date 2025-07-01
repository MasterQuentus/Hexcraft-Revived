package net.masterquentus.hexcraftmod.init;

import net.masterquentus.hexcraftmod.worldgen.dimension.HexcraftDimensions;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class HexcraftRegistryBootstrap {


    public static void bootstrapDimensionTypes(BootstapContext<DimensionType> context) {
        HexcraftDimensions.bootstrapType(context);
    }

    public static void bootstrapLevelStems(BootstapContext<LevelStem> context) {
        HexcraftDimensions.bootstrapStem(context);
    }
}