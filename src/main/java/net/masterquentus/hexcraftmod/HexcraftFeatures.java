package net.masterquentus.hexcraftmod;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class HexcraftFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> VILLAGE_CROPS = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, new ResourceLocation(HexcraftMod.MOD_ID, "village_crops"));

    public static final ResourceKey<PlacedFeature> VILLAGE_CROPS_PLACEMENT = ResourceKey.create(
            Registries.PLACED_FEATURE, new ResourceLocation(HexcraftMod.MOD_ID, "village_crops_placement"));

    public static void bootstrapConfiguredFeatures(BootstapContext<ConfiguredFeature<?, ?>> context) {
        context.register(VILLAGE_CROPS, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(HexcraftBlocks.VERVAIN_FLOWER.get()))));
    }

    public static void bootstrapPlacedFeatures(BootstapContext<PlacedFeature> context) {
        context.register(VILLAGE_CROPS_PLACEMENT, new PlacedFeature(
                context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(VILLAGE_CROPS), // ✅ Corrected lookup
                List.of(PlacementUtils.HEIGHTMAP_WORLD_SURFACE))
        );
    }
}