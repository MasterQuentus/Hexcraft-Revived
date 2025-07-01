package net.masterquentus.hexcraftmod.worldgen.biome;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.worldgen.HexcraftPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class HexcraftBiomes {
    public static final ResourceKey<Biome> VAMPIRE_FOREST = register("vampire_forest");
    public static final ResourceKey<Biome> CRIMSON_DESERT = register("crimson_desert");
    public static final ResourceKey<Biome> SHADOWLANDS = register("shadowlands");
    public static final ResourceKey<Biome> UNDERGARDEN = register("undergarden");
    public static final ResourceKey<Biome> CRYSTAL_PEAKS = register("crystal_peaks");

    public static final ResourceKey<Biome> ABYSSAL_SEAS = register("abyssal_seas");
    public static final ResourceKey<Biome> DEEP_ABYSSAL_SEAS = register("deep_abyssal_seas");
    public static final ResourceKey<Biome> FROSTFANG_OCEAN = register("frostfang_ocean");
    public static final ResourceKey<Biome> STORMREACH_OCEAN = register("deep_frostfang_ocean");
    public static final ResourceKey<Biome> SCORCHING_REEF = register("scorching_reef");
    public static final ResourceKey<Biome> GLIMMERING_SHOALS = register("lukewarm_tidelands");
    public static final ResourceKey<Biome> TWILIGHT_SHOALS = register("glacial_bay");
    public static final ResourceKey<Biome> VEILWATER_BASIN = register("deep_glacial_bay");
    public static final ResourceKey<Biome> ECHO_FUNGLE_FOREST = register("echo_fungle_forest");
    public static final ResourceKey<Biome> HELL_FUNGLE_FOREST = register("hell_fungle_forest");

    public static void bootstrap(BootstapContext<Biome> context) {  // <-- fixed typo
        context.register(VAMPIRE_FOREST, vampireBiome(context));
        context.register(CRIMSON_DESERT, crimsondesertBiome(context));
        context.register(SHADOWLANDS, shadowlandsBiome(context));
        context.register(UNDERGARDEN, undergardenBiome(context));
        context.register(CRYSTAL_PEAKS, crystalPeaksBiome(context));
        context.register(ABYSSAL_SEAS, abyssalSeasBiome(context));
        context.register(DEEP_ABYSSAL_SEAS, deepAbyssalSeasBiome(context));
        context.register(FROSTFANG_OCEAN, frostfangOceanBiome(context));
        context.register(STORMREACH_OCEAN, stormreachOceanBiome(context));
        context.register(SCORCHING_REEF, scorchingReefBiome(context));
        context.register(GLIMMERING_SHOALS, glimmeringShoalsBiome(context));
        context.register(TWILIGHT_SHOALS, twilightShoalsBiome(context));
        context.register(VEILWATER_BASIN, veilwaterBasinBiome(context));
        context.register(ECHO_FUNGLE_FOREST, echoFungleForestBiome(context));
        context.register(HELL_FUNGLE_FOREST, hellFungleForestBiome(context));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
    }

    public static Biome shadowlandsBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        // You can add custom mobs here later or add common spawns
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        // Add basic terrain generation features suitable for Underworld
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);

        // You can add custom features here like glowing fungi, ash fields, etc.
        // biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HexcraftPlacedFeatures.GLOWING_FUNGUS_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)    // no rain, like Nether
                .downfall(0.0f)
                .temperature(2.0f)          // hot and hostile feeling
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3B0000)           // dark blood red water
                        .waterFogColor(0x1A0000)
                        .fogColor(0x330000)             // smoky dark red fog
                        .skyColor(0x550000)             // crimson sky
                        .grassColorOverride(0x4A1F1F)   // dark red grass
                        .foliageColorOverride(0x6B0B0B) // dark red foliage
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome vampireBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_PLAINS);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HexcraftPlacedFeatures.VAMPIRE_ORCHID_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HexcraftPlacedFeatures.BLOOD_OAK_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xe0000FF)
                        .waterFogColor(0x0051FF)
                        .skyColor(0x2400A6)
                        .grassColorOverride(0x006100)
                        .foliageColorOverride(0xd009300)
                        .fogColor(0x22a1e6)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome crimsondesertBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        // You might want to remove forest flowers for a desert biome:
        // BiomeDefaultFeatures.addForestFlowers(biomeBuilder);

        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)  // changed to false for desert
                .downfall(0.0f)
                .temperature(1.5f)        // hotter for desert
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x8D8300)
                        .waterFogColor(0x8D7100)
                        .skyColor(0x8D71B3)
                        .grassColorOverride(0xC6A93E)
                        .foliageColorOverride(0xE5A93E)
                        .fogColor(0xAC8700)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome undergardenBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        // Add any custom mobs or override spawns here if you want

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        // Use default overworld features or customize for dense plants, fungi, etc.
        globalOverworldGeneration(biomeBuilder);

        // You might want to add extra plant features here:
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)          // plants like some moisture
                .downfall(0.85f)                 // fairly wet, lush feeling
                .temperature(0.7f)               // temperate like Vampire Forest
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x2A3B3B)           // deep, murky water color
                        .waterFogColor(0x162525)
                        .fogColor(0x1E2D2D)             // dense fog
                        .skyColor(0x364B4B)             // muted green-blue sky tone
                        .grassColorOverride(0x3A5C40)   // dark green mossy grass
                        .foliageColorOverride(0x4B6F50) // rich green foliage
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome crystalPeaksBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder); // Add default spawns or customize

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);

        // Add any special crystal features here if you have them:
        // biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, HexcraftPlacedFeatures.CRYSTAL_ORE_PLACED_KEY);
        // biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HexcraftPlacedFeatures.CRYSTAL_SPIKES_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.1f)             // low rainfall for a cold/crystal biome
                .temperature(0.3f)          // cold-ish climate
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x5599CC)           // icy blue water
                        .waterFogColor(0x2A4C6D)
                        .fogColor(0xAADDFF)             // light icy fog
                        .skyColor(0x77BBEE)             // clear bright sky
                        .grassColorOverride(0xA0D6E3)   // pale bluish grass (or leave default)
                        .foliageColorOverride(0x9BD4E5) // pale blue foliage (if any)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome abyssalSeasBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        // Add ocean mobs - you can customize or add your own later
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        // Add ocean-specific terrain features
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);

        // Add ocean vegetation & coral features
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)           // ocean biomes get rain
                .downfall(0.5f)
                .temperature(0.5f)                // cool ocean temp
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x0A2F4A)      // deep blue water color
                        .waterFogColor(0x04202C)   // darker underwater fog
                        .fogColor(0x1B263B)        // misty ocean fog
                        .skyColor(0x3C6997)        // overcast sky blue
                        .grassColorOverride(0x1C3B47) // dark seaweed green tint
                        .foliageColorOverride(0x2A4D62) // cool foliage color for underwater plants
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome deepAbyssalSeasBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.4f)
                .temperature(0.3f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x051E2A)        // darker, deep ocean water
                        .waterFogColor(0x031219)     // very dark water fog
                        .fogColor(0x0A1A26)          // heavy deep ocean fog
                        .skyColor(0x25435F)          // dimmer sky blue
                        .grassColorOverride(0x153944) // darker seaweed
                        .foliageColorOverride(0x1E4A5A)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome frostfangOceanBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.7f)
                .temperature(0.1f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x9AD9FF)        // icy blue water
                        .waterFogColor(0x6CBFFF)     // icy underwater fog
                        .fogColor(0xA3CCFF)          // cold fog
                        .skyColor(0xA0C4FF)          // cold, crisp sky
                        .grassColorOverride(0x6DB0C6)
                        .foliageColorOverride(0x8FC1D4)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome scorchingReefBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.5f)
                .temperature(1.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xFF9F42)        // warm, orange water
                        .waterFogColor(0xE67E22)     // warm underwater fog
                        .fogColor(0xFFB65E)          // warm, sunny fog
                        .skyColor(0xFFAA33)          // bright sky
                        .grassColorOverride(0xC27C3A)
                        .foliageColorOverride(0xD99E55)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome twilightShoalsBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.6f)
                .temperature(0.6f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x4B5E7A)        // cool dusk blue
                        .waterFogColor(0x364F6B)     // twilight underwater fog
                        .fogColor(0x435D7D)          // foggy dusk atmosphere
                        .skyColor(0x5A6C8A)          // twilight sky
                        .grassColorOverride(0x3F5A73)
                        .foliageColorOverride(0x4A657A)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome veilwaterBasinBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.3f)
                .temperature(0.4f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x0F1921)        // very dark water
                        .waterFogColor(0x0A0E12)     // near black water fog
                        .fogColor(0x141B22)          // shadowy fog
                        .skyColor(0x1B252D)          // dark sky
                        .grassColorOverride(0x1A2228)
                        .foliageColorOverride(0x1E262E)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome glimmeringShoalsBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.7f)
                .temperature(0.8f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x5ED4FF)        // bright shimmering water
                        .waterFogColor(0x39A0FF)     // bright water fog
                        .fogColor(0x70BFFF)          // light fog
                        .skyColor(0x8EDCFF)          // bright sky
                        .grassColorOverride(0x67B5E1)
                        .foliageColorOverride(0x8ABCE9)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome stormreachOceanBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.85f)
                .temperature(0.6f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x375E7A)        // dark turbulent water
                        .waterFogColor(0x1F3B50)     // murky fog
                        .fogColor(0x29455E)          // stormy sky fog
                        .skyColor(0x406B8E)          // stormy blue sky
                        .grassColorOverride(0x3A5768)
                        .foliageColorOverride(0x44697C)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome echoFungleForestBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addForestGrass(biomeBuilder); // or your custom feature
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3F6660)
                        .waterFogColor(0x243A38)
                        .fogColor(0x344E4C)
                        .skyColor(0x47645F)
                        .grassColorOverride(0x476F66)
                        .foliageColorOverride(0x3E6056)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome hellFungleForestBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder); // fiery/underground look
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(2.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x660000)
                        .waterFogColor(0x300000)
                        .fogColor(0x450000)
                        .skyColor(0x6F0000)
                        .grassColorOverride(0x5B1F1F)
                        .foliageColorOverride(0x6A2B2B)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }


    public static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(HexcraftMod.MOD_ID, name));
    }
}