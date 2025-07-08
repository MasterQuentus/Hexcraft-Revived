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
    public static final ResourceKey<Biome> BLOODLEAF_VAMPIRE_FOREST = register("bloodleaf_vampire_forest");
    public static final ResourceKey<Biome> FERAL_THICKET_VAMPIRE_FOREST = register("feral_thicketvampire_forest");
    public static final ResourceKey<Biome> CRIMSON_DESERT = register("crimson_desert");
    public static final ResourceKey<Biome> SHADOWLANDS = register("shadowlands");
    public static final ResourceKey<Biome> UNDERGARDEN = register("undergarden");
    public static final ResourceKey<Biome> CRYSTAL_PEAKS = register("crystal_peaks");
    public static final ResourceKey<Biome> ABYSSAL_SEAS = register("abyssal_seas");
    public static final ResourceKey<Biome> DEEP_ABYSSAL_SEAS = register("deep_abyssal_seas");
    public static final ResourceKey<Biome> FROSTFANG_OCEAN = register("frostfang_ocean");
    public static final ResourceKey<Biome> DEEP_FROSTFANG_OCEAN = register("deep_frostfang_ocean");
    public static final ResourceKey<Biome> STORMREACH_OCEAN = register("stormreach_ocean");
    public static final ResourceKey<Biome> DEEP_STORMREACH_OCEAN = register("deep_stormreach_ocean");
    public static final ResourceKey<Biome> VEIL_WATER_SHOALS = register("veil_water_shoals");
    public static final ResourceKey<Biome> VEIL_WATER_ABYSS = register("veil_water_abyss");
    public static final ResourceKey<Biome> SCHORCHING_REEF = register("schorching_reef");
    public static final ResourceKey<Biome> SCHORCHING_SHOALS = register("schorching_shoals");
    public static final ResourceKey<Biome> SCHORCHING_ABYSS = register("schorching_abyss");
    public static final ResourceKey<Biome> VEIL_SHORE_BANKS = register("veil_shore_banks");
    public static final ResourceKey<Biome> VEILWATER_BASIN = register("veil_water_basin");
    public static final ResourceKey<Biome> ECHO_FUNGLE_FOREST = register("echo_fungle_forest");
    public static final ResourceKey<Biome> HELL_FUNGLE_FOREST = register("hell_fungle_forest");
    public static final ResourceKey<Biome> TWILIGHT_SHOALS = register("twilight_shoals");
    public static final ResourceKey<Biome> FOGGY_HAUNTED_WOODS = register("foggy_haunted_woods");
    public static final ResourceKey<Biome> DENSE_HAUNTED_WOODS = register("dense_haunted_woods");
    public static final ResourceKey<Biome> CRACKED_BLIGHTED_WASTES = register("cracked_blighted_wastes");
    public static final ResourceKey<Biome> RED_BLIGHTED_WASTES = register("red_blighted_wastes");
    public static final ResourceKey<Biome> EMBER_FLARELANDS = register("ember_flarelands");
    public static final ResourceKey<Biome> DRIED_FLARELANDS = register("dried_flarelands");
    public static final ResourceKey<Biome> OVERGROWN_VILE_FOREST = register("overgrown_vile_forest");
    public static final ResourceKey<Biome> WITHERED_VILE_FOREST = register("withered_vile_forest");
    public static final ResourceKey<Biome> LUSH_WHISPERING_FOREST = register("lush_whispering_forest");
    public static final ResourceKey<Biome> TWILIGHT_WHISPERING_FOREST = register("twilight_whispering_forest");
    public static final ResourceKey<Biome> SCORCHED_BARRENS = register("scorched_barrens");
    public static final ResourceKey<Biome> OBSIDIAN_FLATS = register("obsidian_flats");
    public static final ResourceKey<Biome> DECAY_HIGHLANDS = register("decay_highlands");
    public static final ResourceKey<Biome> HOLLOWED_PEAKS = register("hollowed_peaks");
    public static final ResourceKey<Biome> MIRKMIRE_SWAMP = register("mirkmire_swamp");
    public static final ResourceKey<Biome> VOLCANIC_FOOTHILLS = register("volcanic_foothills");
    public static final ResourceKey<Biome> SOUL_GROVE = register("soul_grove");
    public static final ResourceKey<Biome> SHADOWED_HILLS = register("shadowed_hills");
    public static final ResourceKey<Biome> BLIGHTED_SLOPES = register("blighted_slopes");
    public static final ResourceKey<Biome> TWILIGHT_PLATEAU = register("twilight_plateau");


    public static void bootstrap(BootstapContext<Biome> context) {  // <-- fixed typo
        context.register(VAMPIRE_FOREST, vampireBiome(context));
        context.register(CRIMSON_DESERT, crimsondesertBiome(context));
        context.register(SHADOWLANDS, shadowlandsBiome(context));
        context.register(UNDERGARDEN, undergardenBiome(context));
        context.register(CRYSTAL_PEAKS, crystalPeaksBiome(context));
        context.register(ABYSSAL_SEAS, abyssalSeasBiome(context));
        context.register(DEEP_ABYSSAL_SEAS, deepAbyssalSeasBiome(context));
        context.register(FROSTFANG_OCEAN, frostfangOceanBiome(context));
        context.register(DEEP_FROSTFANG_OCEAN, deepfrostfangOceanBiome(context));
        context.register(STORMREACH_OCEAN, frostfangOceanBiome(context));
        context.register(DEEP_STORMREACH_OCEAN, stormreachOceanBiome(context));
        context.register(VEIL_WATER_SHOALS, veilwaterShoalsBiome(context));
        context.register(VEIL_WATER_ABYSS, veilwaterAbyssBiome(context));
        context.register(VEIL_SHORE_BANKS, veilshoreBanksBiome(context));
        context.register(SCHORCHING_REEF, scorchingReefBiome(context));
        context.register(SCHORCHING_SHOALS, scorchingShoalsBiome(context));
        context.register(SCHORCHING_ABYSS, scorchingAbyssBiome(context));
        context.register(VEILWATER_BASIN, veilwaterBasinBiome(context));
        context.register(TWILIGHT_SHOALS,twilightShoalsBiome(context));
        context.register(ECHO_FUNGLE_FOREST, echoFungleForestBiome(context));
        context.register(HELL_FUNGLE_FOREST, hellFungleForestBiome(context));
        context.register(BLOODLEAF_VAMPIRE_FOREST, bloodleafVampireForestBiome(context));
        context.register(FERAL_THICKET_VAMPIRE_FOREST, feralThicketVampireForestBiome(context));
        context.register(FOGGY_HAUNTED_WOODS, foggyHauntedWoodsBiome(context));
        context.register(DENSE_HAUNTED_WOODS, denseHauntedWoodsBiome(context));
        context.register(CRACKED_BLIGHTED_WASTES, crackedBlightedWastesBiome(context));
        context.register(RED_BLIGHTED_WASTES, redBlightedWastesBiome(context));
        context.register(EMBER_FLARELANDS, emberFlarelandsBiome(context));
        context.register(DRIED_FLARELANDS, driedFlarelandsBiome(context));
        context.register(OVERGROWN_VILE_FOREST, overgrownVileForestBiome(context));
        context.register(WITHERED_VILE_FOREST, witheredVileForestBiome(context));
        context.register(LUSH_WHISPERING_FOREST, lushWhisperingForestBiome(context));
        context.register(TWILIGHT_WHISPERING_FOREST, twilightWhisperingForestBiome(context));
        context.register(SCORCHED_BARRENS, scorchedBarrensBiome(context));
        context.register(OBSIDIAN_FLATS, obsidianFlatsBiome(context));
        context.register(DECAY_HIGHLANDS, decayHighlandsBiome(context));
        context.register(HOLLOWED_PEAKS, hollowedPeaksBiome(context));
        context.register(MIRKMIRE_SWAMP, mirkmireSwampBiome(context));
        context.register(VOLCANIC_FOOTHILLS, volcanicFoothillsBiome(context));
        context.register(SOUL_GROVE, soulGroveBiome(context));
        context.register(SHADOWED_HILLS, shadowedHillsBiome(context));
        context.register(BLIGHTED_SLOPES, blightedSlopesBiome(context));
        context.register(TWILIGHT_PLATEAU, twilightPlateauBiome(context));
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

    public static Biome veilshoreBanksBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.6f)
                .downfall(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3D4250)
                        .waterFogColor(0x232C3A)
                        .fogColor(0x4A505B)
                        .skyColor(0x6B6E77)
                        .grassColorOverride(0x4A5250)
                        .foliageColorOverride(0x586760)
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

    public static Biome deepfrostfangOceanBiome(BootstapContext<Biome> context) {
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

    public static Biome deepstormreachOceanBiome(BootstapContext<Biome> context) {
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

    public static Biome veilwaterShoalsBiome(BootstapContext<Biome> context) {
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
                .temperature(0.75f)
                .downfall(0.9f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x4CB8C4)          // bright cyan/blue
                        .waterFogColor(0x3A88A2)       // clear tropical fog
                        .fogColor(0x86BBD8)
                        .skyColor(0x95CBE4)
                        .grassColorOverride(0x4DB5AA)
                        .foliageColorOverride(0x56C3B6)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome veilwaterAbyssBiome(BootstapContext<Biome> context) {
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
                .temperature(0.5f)
                .downfall(1.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x24486C)          // deep ocean teal
                        .waterFogColor(0x132C44)       // dark fog
                        .fogColor(0x1A2F45)
                        .skyColor(0x234C67)
                        .grassColorOverride(0x2D5C6B)
                        .foliageColorOverride(0x2F6B73)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome scorchingShoalsBiome(BootstapContext<Biome> context) {
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
                .hasPrecipitation(false)
                .temperature(1.2f)
                .downfall(0.1f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xFF7F50)          // Coral orange
                        .waterFogColor(0xBF4020)       // Murky warm fog
                        .fogColor(0xE06236)            // Bright hot atmosphere
                        .skyColor(0xF59E71)
                        .grassColorOverride(0xE0964B)
                        .foliageColorOverride(0xFFBC7A)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome scorchingAbyssBiome(BootstapContext<Biome> context) {
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
                .hasPrecipitation(false)
                .temperature(1.4f)
                .downfall(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xD14F2D)          // Lava-tinted water
                        .waterFogColor(0x802B17)       // Dark molten fog
                        .fogColor(0xA1371C)            // Deep fiery tones
                        .skyColor(0xFF7A50)
                        .grassColorOverride(0xD65B3E)
                        .foliageColorOverride(0xF98A60)
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

    public static Biome twilightShoalsBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);

        // Optional: Add glowing plants or mystical coral-style features if you have custom ones
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.6f)  // Cool and damp
                .downfall(0.8f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3A4C8F)             // twilight-blue water
                        .waterFogColor(0x28375E)          // darker underwater
                        .skyColor(0x7B8ED3)               // soft indigo skies
                        .fogColor(0x4E5A87)               // muted ocean mist
                        .grassColorOverride(0x4D7B8C)     // slightly teal-hued
                        .foliageColorOverride(0x5F8B9E)   // cool-toned leaves
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome bloodleafVampireForestBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        // TODO: add vampire mob spawns here

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        // TODO: add bloodleaf vampire forest-specific features

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.7f)
                .downfall(0.8f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x225522)
                        .waterFogColor(0x114411)
                        .skyColor(0x557755)
                        .grassColorOverride(0x33aa33)
                        .foliageColorOverride(0x228822)
                        .fogColor(0x112211)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome feralThicketVampireForestBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        // TODO: add feral thicket-specific spawns

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        // TODO: add feral thicket vegetation, etc.

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.65f)
                .downfall(0.75f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x334422)
                        .waterFogColor(0x223311)
                        .skyColor(0x667744)
                        .grassColorOverride(0x44bb44)
                        .foliageColorOverride(0x339933)
                        .fogColor(0x223311)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome foggyHauntedWoodsBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        // TODO: add spooky mob spawns here

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        // TODO: add foggy haunted woods features

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.6f)
                .downfall(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x555566)
                        .waterFogColor(0x444455)
                        .skyColor(0x666677)
                        .grassColorOverride(0x556655)
                        .foliageColorOverride(0x445544)
                        .fogColor(0x222244)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome denseHauntedWoodsBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        // TODO: add dense haunted woods mobs

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.55f)
                .downfall(0.75f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x554455)
                        .waterFogColor(0x443344)
                        .skyColor(0x665566)
                        .grassColorOverride(0x664466)
                        .foliageColorOverride(0x553355)
                        .fogColor(0x332233)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome crackedBlightedWastesBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        // No farm animals here — harsh wastes

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        // TODO: add blighted terrain features

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(1.1f)
                .downfall(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x332200)
                        .waterFogColor(0x221100)
                        .skyColor(0x553300)
                        .grassColorOverride(0x664400)
                        .foliageColorOverride(0x552200)
                        .fogColor(0x331100)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome redBlightedWastesBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        // TODO: add red blighted features

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(1.15f)
                .downfall(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x660000)
                        .waterFogColor(0x440000)
                        .skyColor(0x990000)
                        .grassColorOverride(0xAA2222)
                        .foliageColorOverride(0x990000)
                        .fogColor(0x550000)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome emberFlarelandsBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        // TODO: add fire/ash mobs

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        // TODO: add ash and flare features

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(1.3f)
                .downfall(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xAA5500)
                        .waterFogColor(0x994400)
                        .skyColor(0xFF6600)
                        .grassColorOverride(0xCC3300)
                        .foliageColorOverride(0xBB2200)
                        .fogColor(0x771100)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome driedFlarelandsBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(1.2f)
                .downfall(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xAA5522)
                        .waterFogColor(0x996633)
                        .skyColor(0xFF7733)
                        .grassColorOverride(0xCC6622)
                        .foliageColorOverride(0xBB5522)
                        .fogColor(0x774411)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome overgrownVileForestBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        // TODO: add vile forest mobs

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.6f)
                .downfall(0.75f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x335533)
                        .waterFogColor(0x224422)
                        .skyColor(0x557755)
                        .grassColorOverride(0x44aa44)
                        .foliageColorOverride(0x338833)
                        .fogColor(0x223322)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome witheredVileForestBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.5f)
                .downfall(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x554433)
                        .waterFogColor(0x443322)
                        .skyColor(0x776655)
                        .grassColorOverride(0x664422)
                        .foliageColorOverride(0x553311)
                        .fogColor(0x332211)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome lushWhisperingForestBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.75f)
                .downfall(0.85f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x446622)
                        .waterFogColor(0x335511)
                        .skyColor(0x668844)
                        .grassColorOverride(0x55aa33)
                        .foliageColorOverride(0x449933)
                        .fogColor(0x223311)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome twilightWhisperingForestBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.65f)
                .downfall(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x334466)
                        .waterFogColor(0x223355)
                        .skyColor(0x556677)
                        .grassColorOverride(0x445577)
                        .foliageColorOverride(0x334466)
                        .fogColor(0x112233)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome scorchedBarrensBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(1.4f)
                .downfall(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xAA6600)
                        .waterFogColor(0x885500)
                        .skyColor(0xFF7733)
                        .grassColorOverride(0xCC7722)
                        .foliageColorOverride(0xBB6622)
                        .fogColor(0x774411)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome obsidianFlatsBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.7f)
                .downfall(0.1f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x333333)
                        .waterFogColor(0x222222)
                        .skyColor(0x444444)
                        .grassColorOverride(0x555555)
                        .foliageColorOverride(0x444444)
                        .fogColor(0x111111)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome decayHighlandsBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.4f)
                .downfall(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x666644)
                        .waterFogColor(0x555533)
                        .skyColor(0x777766)
                        .grassColorOverride(0x778844)
                        .foliageColorOverride(0x667755)
                        .fogColor(0x333322)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome hollowedPeaksBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        // TODO: add mountain specific mobs

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.2f)
                .downfall(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x888888)
                        .waterFogColor(0x666666)
                        .skyColor(0x999999)
                        .grassColorOverride(0x777777)
                        .foliageColorOverride(0x666666)
                        .fogColor(0x555555)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome mirkmireSwampBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addSwampVegetation(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.8f)
                .downfall(0.9f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3f76e4)
                        .waterFogColor(0x50533)
                        .skyColor(0x557755)
                        .grassColorOverride(0x556644)
                        .foliageColorOverride(0x447733)
                        .fogColor(0x223322)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome volcanicFoothillsBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        // TODO: volcanic mobs

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(1.2f)
                .downfall(0.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xaa3322)
                        .waterFogColor(0x992211)
                        .skyColor(0xff5522)
                        .grassColorOverride(0xbb4422)
                        .foliageColorOverride(0xaa3311)
                        .fogColor(0x662211)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome soulGroveBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        // TODO: soul grove mobs

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.65f)
                .downfall(0.75f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x556644)
                        .waterFogColor(0x445533)
                        .skyColor(0x667755)
                        .grassColorOverride(0x44aa44)
                        .foliageColorOverride(0x338833)
                        .fogColor(0x223322)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome shadowedHillsBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.4f)
                .downfall(0.6f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x556633)
                        .waterFogColor(0x445522)
                        .skyColor(0x667744)
                        .grassColorOverride(0x66aa55)
                        .foliageColorOverride(0x559944)
                        .fogColor(0x223311)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome blightedSlopesBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        // TODO: add blighted mobs

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.3f)
                .downfall(0.4f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x443322)
                        .waterFogColor(0x332211)
                        .skyColor(0x554433)
                        .grassColorOverride(0x665544)
                        .foliageColorOverride(0x553322)
                        .fogColor(0x332211)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static Biome twilightPlateauBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.6f)
                .downfall(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x556677)
                        .waterFogColor(0x445566)
                        .skyColor(0x667788)
                        .grassColorOverride(0x669955)
                        .foliageColorOverride(0x557744)
                        .fogColor(0x334455)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }


    public static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(HexcraftMod.MOD_ID, name));
    }
}