package net.masterquentus.hexcraftmod.worldgen;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.Collections;
import java.util.List;

public class HexcraftPlacedFeatures {

    public static final ResourceKey<PlacedFeature> VAMPIRIC_ORE_PLACED_KEY = registerKey("vampiric_ore_placed");

    public static final ResourceKey<PlacedFeature> NETHER_VAMPIRIC_ORE_PLACED_KEY = registerKey("nether_vampiric_ore_placed");

    public static final ResourceKey<PlacedFeature> END_VAMPIRIC_ORE_PLACED_KEY = registerKey("end_vampiric_ore_placed");

    public static final ResourceKey<PlacedFeature> SILVER_ORE_PLACED_KEY = registerKey("silver_ore_placed");

    public static final ResourceKey<PlacedFeature> NETHER_SILVER_ORE_PLACED_KEY = registerKey("nether_silver_ore_placed");

    public static final ResourceKey<PlacedFeature> END_SILVER_ORE_PLACED_KEY = registerKey("end_silver_ore_placed");

    public static final ResourceKey<PlacedFeature> MOON_STONE_ORE_PLACED_KEY = registerKey("moon_stone_ore_placed");

    public static final ResourceKey<PlacedFeature> NETHER_MOON_STONE_ORE_PLACED_KEY = registerKey("nether_moon_stone_ore_placed");

    public static final ResourceKey<PlacedFeature> END_MOON_STONE_ORE_PLACED_KEY = registerKey("end_moon_stone_ore_placed");

    public static final ResourceKey<PlacedFeature> TRENOGEN_ORE_PLACED_KEY = registerKey("trenogen_ore_placed");

    public static final ResourceKey<PlacedFeature> JORMUIM_ORE_PLACED_KEY = registerKey("jormuim_ore_placed");

    public static final ResourceKey<PlacedFeature> SOULSTONE_ORE_PLACED_KEY = registerKey("soulstone_ore_placed");

    public static final ResourceKey<PlacedFeature> ABYSSIUM_ORE_PLACED_KEY = registerKey("abyssium_ore_placed");

    public static final ResourceKey<PlacedFeature> ECLIPSIUM_ORE_PLACED_KEY = registerKey("eclipsium_ore_placed");

    public static final ResourceKey<PlacedFeature> ABYSSAL_COAL_ORE_PLACED_KEY = registerKey("abyssal_coal_ore_placed");

    public static final ResourceKey<PlacedFeature> PEARL_STONE_ORE_PLACED_KEY = registerKey("pearl_stone_ore_placed");

    public static final ResourceKey<PlacedFeature> CRIMSON_STONE_ORE_PLACED_KEY = registerKey("crimson_stone_ore_placed");

    public static final ResourceKey<PlacedFeature> CHARSTONE_ORE_PLACED_KEY = registerKey("charstone_ore_placed");

    public static final ResourceKey<PlacedFeature> MAGIC_CRYSTAL_GEODE_PLACED_KEY = registerKey("magic_crystal_geode_placed");

    public static final ResourceKey<PlacedFeature> VAMPIRE_ORCHID_PLACED_KEY = registerKey("vampire_orchid_placed");

    public static final ResourceKey<PlacedFeature> BLOOD_OAK_PLACED_KEY = registerKey("blood_oak_placed");

    public static final ResourceKey<PlacedFeature> VILE_GRASS_BONEMEAL = createKey("vile_grass_bonemeal");

    public static final ResourceKey<PlacedFeature> UMBRAL_BLOOM_GRASS_BONEMEAL = createKey("umbral_grass_bonemeal");

    public static final ResourceKey<PlacedFeature> SCORCHFIRE_GRASS_BONEMEAL = createKey("scorchfire_grass_bonemeal");

    public static final ResourceKey<PlacedFeature> BRAMBLE_GRASS_BONEMEAL = createKey("bramble_grass_bonemeal");

    public static final ResourceKey<PlacedFeature> VEILSHADE_GRASS_BONEMEAL = createKey("vilshade_soil_bonemeal");

    public static final ResourceKey<PlacedFeature> CRACKED_SOIL_BONEMEAL = createKey("cracked_soil_bonemeal");

    public static final ResourceKey<PlacedFeature> PACKED_HUMUS_BONEMEAL = createKey("packed_humus_bonemeal");

    public static final ResourceKey<PlacedFeature> FESTERING_GRASS_BONEMEAL = createKey("festering_grass_bonemeal");

    public static final ResourceKey<PlacedFeature> CINDERWEED_SOIL_BONEMEAL = createKey("cinderweed_soil_bonemeal");

    public static final ResourceKey<PlacedFeature> FLAREWORN_GRASS_BONEMEAL = createKey("flareworn_grass_bonemeal");

    public static final ResourceKey<PlacedFeature> BILEWEED_GRASS_BONEMEAL = createKey("bileweed_grass_bonemeal");

    public static final ResourceKey<PlacedFeature> WHISPERBLOOM_SOIL_BONEMEAL = createKey("whisperblom_soil_bonemeal");

    public static final ResourceKey<PlacedFeature> TWILIGHTLOAM_GRASS_BONEMEAL = createKey("twilightloam_grass_bonemeal");

    public static final ResourceKey<PlacedFeature> ROTROOT_GRASS_BONEMEAL = createKey("rotroot_grass_bonemeal");

    public static final ResourceKey<PlacedFeature> HUSKWEED_GRASS_BONEMEAL = createKey("huskweed_grass_bonemeal");

    public static final ResourceKey<PlacedFeature> SPIRITWEED_GRASS_BONEMEAL = createKey("spiritweed_grass_bonemeal");

    public static final ResourceKey<PlacedFeature> DUSK_GRASS_BONEMEAL = createKey("dusk_grass_bonemeal");

    public static final ResourceKey<PlacedFeature> NIGHTVEIN_GRASS_BONEMEAL = createKey("nightvein_grass_bonemeal");

    public static final ResourceKey<PlacedFeature> GRASS_PATCH_PLACEMENT = createKey("grass_patch");


    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(HexcraftMod.MOD_ID, name));
    }

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, VAMPIRIC_ORE_PLACED_KEY, configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.OVERWORLD_VAMPIRIC_ORE_KEY),
                HexcraftOrePlacement.commonOrePlacement(10, // Reduced slightly from 12
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(128))));

        register(context, NETHER_VAMPIRIC_ORE_PLACED_KEY, configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.NETHER_VAMPIRIC_ORE_KEY),
                HexcraftOrePlacement.commonOrePlacement(8,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(100))));

        register(context, END_VAMPIRIC_ORE_PLACED_KEY, configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.END_VAMPIRIC_ORE_KEY),
                HexcraftOrePlacement.commonOrePlacement(6,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(20), VerticalAnchor.absolute(80))));

        register(context, SILVER_ORE_PLACED_KEY, configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.OVERWORLD_SILVER_ORE_KEY),
                HexcraftOrePlacement.commonOrePlacement(9, // Slightly less than Vampiric Ore
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(96))));

        register(context, NETHER_SILVER_ORE_PLACED_KEY, configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.NETHER_SILVER_ORE_KEY),
                HexcraftOrePlacement.commonOrePlacement(7,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(80))));

        register(context, END_SILVER_ORE_PLACED_KEY, configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.END_SILVER_ORE_KEY),
                HexcraftOrePlacement.commonOrePlacement(5,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(20), VerticalAnchor.absolute(60))));

        register(context, MOON_STONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.OVERWORLD_MOON_STONE_ORE_KEY),
                HexcraftOrePlacement.commonOrePlacement(6, // Lower than Silver and Vampiric Ore
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(64))));

        register(context, NETHER_MOON_STONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.NETHER_MOON_STONE_ORE_KEY),
                HexcraftOrePlacement.commonOrePlacement(5,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(60))));

        register(context, END_MOON_STONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.END_MOON_STONE_ORE_KEY),
                HexcraftOrePlacement.commonOrePlacement(4,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(10), VerticalAnchor.absolute(50))));

        register(context, TRENOGEN_ORE_PLACED_KEY, configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.OVERWORLD_TRENOGEN_ORE_KEY),
                HexcraftOrePlacement.commonOrePlacement(5, // Rarer than Moonstone
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(48))));

        register(context, JORMUIM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.JORMUIM_ORE_KEY),
                HexcraftOrePlacement.commonOrePlacement(5, // Similar rarity to Trenogen, different placement
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(40))));

        // Register Soulstone Ore with appropriate frequency and height range (more common)
        register(context, SOULSTONE_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.UNDER_WORLD_SOUL_ORE_KEY),
                HexcraftOrePlacement.commonOrePlacement(10, // More common like Nether ores
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(5), VerticalAnchor.absolute(120)))); // Adjusted height range

// Register Abyssium Ore with a slightly rarer frequency and adjusted height range
        register(context, ABYSSIUM_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.UNDER_WORLD_ABYSS_ORE_KEY),
                HexcraftOrePlacement.commonOrePlacement(7, // Slightly rarer than Soulstone
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(10), VerticalAnchor.absolute(100)))); // Adjusted height range

// Register Eclipsium Ore with very low frequency (rare ore) and adjusted height range
        register(context, ECLIPSIUM_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.UNDER_WORLD_ECLIPSE_ORE_KEY),
                HexcraftOrePlacement.rareOrePlacement(4, // Very rare, for high-value ores
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-20), VerticalAnchor.absolute(80)))); // Adjusted height range

// Register Abyssal Coal Ore with a frequency closer to Nether Quartz and adjusted height range
        register(context, ABYSSAL_COAL_ORE_PLACED_KEY,
                configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.UNDER_WORLD_ABYSSAL_COAL_ORE_KEY),
                HexcraftOrePlacement.commonOrePlacement(12, // Similar to Nether Quartz, quite common
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(10), VerticalAnchor.absolute(110)))); // Adjusted height range

        register(context, PEARL_STONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.PEARL_STONE_ORE_KEY),
                HexcraftOrePlacement.commonOrePlacement(33, // Similar to Andesite spawn rate
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(256)))); // Full height range like Andesite

        register(context, CRIMSON_STONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.CRIMSON_STONE_ORE_KEY),
                HexcraftOrePlacement.commonOrePlacement(33,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(256))));

        register(context, CHARSTONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.CHARSTONE_ORE_KEY),
                HexcraftOrePlacement.commonOrePlacement(33,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(256))));

        register(context, MAGIC_CRYSTAL_GEODE_PLACED_KEY, configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.MAGIC_CRYSTAL_GEODE_KEY),
                List.of(RarityFilter.onAverageOnceEvery(24), InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(30)),
                        BiomeFilter.biome()));

        register(context, VAMPIRE_ORCHID_PLACED_KEY, configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.VAMPIRE_ORCHID_KEY),
                List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context, BLOOD_OAK_PLACED_KEY, configuredFeatures.getOrThrow(HexcraftConfiguredFeatures.BLOOD_OAK_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        HexcraftBlocks.BLOOD_OAK_SAPLING.get()));

        register(context, VILE_GRASS_BONEMEAL, configuredFeatures.getOrThrow(VegetationFeatures.SINGLE_PIECE_OF_GRASS),
                Collections.singletonList(PlacementUtils.isEmpty()));

        register(context, UMBRAL_BLOOM_GRASS_BONEMEAL, configuredFeatures.getOrThrow(VegetationFeatures.SINGLE_PIECE_OF_GRASS),
                Collections.singletonList(PlacementUtils.isEmpty()));

        register(context, SCORCHFIRE_GRASS_BONEMEAL, configuredFeatures.getOrThrow(VegetationFeatures.SINGLE_PIECE_OF_GRASS),
                Collections.singletonList(PlacementUtils.isEmpty()));


    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(HexcraftMod.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}