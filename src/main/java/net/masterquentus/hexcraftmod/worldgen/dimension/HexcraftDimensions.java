package net.masterquentus.hexcraftmod.worldgen.dimension;

import com.mojang.datafixers.util.Pair;
import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.worldgen.biome.HexcraftBiomes;
import net.masterquentus.hexcraftmod.worldgen.biome.suface.HexcraftSurfaceRules;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;


public class HexcraftDimensions {
    // Existing Underworld dimension keys
    public static final ResourceKey<LevelStem> UNDERWORLDDIM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(HexcraftMod.MOD_ID, "underworlddim"));
    public static final ResourceKey<Level> UNDERWORLDDIM_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(HexcraftMod.MOD_ID, "underworlddim"));
    public static final ResourceKey<DimensionType> UNDERWORLD_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(HexcraftMod.MOD_ID, "underworlddim_type"));

    // New Prison World dimension keys
    public static final ResourceKey<LevelStem> PRISONWORLD_DIM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(HexcraftMod.MOD_ID, "prison_world"));
    public static final ResourceKey<Level> PRISONWORLD_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(HexcraftMod.MOD_ID, "prison_world"));
    public static final ResourceKey<DimensionType> PRISONWORLD_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(HexcraftMod.MOD_ID, "prison_world_type"));



    public static void bootstrapType(BootstapContext<DimensionType> context) {
        // Underworld Dimension Type
        context.register(UNDERWORLD_DIM_TYPE, new DimensionType(
                OptionalLong.of(12000), // fixedTime
                false, // hasSkylight
                true,  // hasCeiling
                false, // ultraWarm
                false, // natural
                1.0,   // coordinateScale
                false, // bedWorks
                true,  // respawnAnchorWorks
                -64,   // minY: lowered to underground
                320,   // height: enough vertical space underground (256 + 64)
                320,   // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                1.0f,  // ambientLight
                new DimensionType.MonsterSettings(true, false, ConstantInt.of(0), 0)));

        // Prison World Dimension Type - like vanilla overworld
        context.register(PRISONWORLD_DIM_TYPE, new DimensionType(
                OptionalLong.empty(), // no fixed time, dynamic day-night cycle
                true,                 // hasSkylight - true for natural daylight
                false,                // hasCeiling - false (no roof)
                false,                // ultraWarm - false (not like Nether)
                true,                 // natural - true, natural dimension
                1.0,                  // coordinateScale - standard scale
                true,                 // bedWorks - true, beds can be used
                true,                 // respawnAnchorWorks - true, respawn anchors work
                0,                    // minY
                256,                  // height
                256,                  // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // blocks that burn infinitely
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // overworld visual effects
                0.0f,                 // ambientLight - 0 means default daylight brightness
                new DimensionType.MonsterSettings(true, false, ConstantInt.of(0), 0)
        ));
    }

    public static MultiNoiseBiomeSource createVanillaOverworldBiomeSource(HolderGetter<Biome> biomeRegistry) {
        List<Pair<Climate.ParameterPoint, Holder<Biome>>> parameters = new ArrayList<>();

        // This list is based on vanilla's climate parameter points and biomes for the overworld
        // You can add all or just key biomes. Here’s a minimal example:

        parameters.add(Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F),
                biomeRegistry.getOrThrow(Biomes.PLAINS)));

        parameters.add(Pair.of(Climate.parameters(0.9F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1F, 0.9F),
                biomeRegistry.getOrThrow(Biomes.DESERT)));

        parameters.add(Pair.of(Climate.parameters(0.1F, 0.1F, 0.0F, 0.0F, 0.0F, 0.9F, 0.1F),
                biomeRegistry.getOrThrow(Biomes.FOREST)));

        parameters.add(Pair.of(Climate.parameters(0.2F, 0.3F, 0.0F, 0.0F, 0.0F, 0.7F, 0.5F),
                biomeRegistry.getOrThrow(Biomes.BIRCH_FOREST)));

        parameters.add(Pair.of(Climate.parameters(-0.2F, 0.1F, 0.0F, 0.0F, 0.0F, 0.3F, 0.3F),
                biomeRegistry.getOrThrow(Biomes.DARK_FOREST)));

        parameters.add(Pair.of(Climate.parameters(0.0F, -0.2F, 0.0F, 0.0F, 0.0F, 0.15F, 0.6F),
                biomeRegistry.getOrThrow(Biomes.SWAMP)));

        parameters.add(Pair.of(Climate.parameters(0.4F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8F, 0.2F),
                biomeRegistry.getOrThrow(Biomes.JUNGLE)));

        parameters.add(Pair.of(Climate.parameters(-0.5F, 0.2F, 0.0F, 0.0F, 0.0F, 0.2F, 0.4F),
                biomeRegistry.getOrThrow(Biomes.TAIGA)));

        parameters.add(Pair.of(Climate.parameters(-0.6F, -0.3F, 0.0F, 0.0F, 0.0F, 0.1F, 0.3F),
                biomeRegistry.getOrThrow(Biomes.SNOWY_TAIGA)));

        parameters.add(Pair.of(Climate.parameters(-0.85F, -0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2F),
                biomeRegistry.getOrThrow(Biomes.ICE_SPIKES)));

        parameters.add(Pair.of(Climate.parameters(0.5F, 0.4F, 0.0F, 0.0F, 0.0F, 0.9F, 0.6F),
                biomeRegistry.getOrThrow(Biomes.SAVANNA)));

        parameters.add(Pair.of(Climate.parameters(0.55F, 0.6F, 0.0F, 0.0F, 0.0F, 0.85F, 0.75F),
                biomeRegistry.getOrThrow(Biomes.SAVANNA_PLATEAU)));

        parameters.add(Pair.of(Climate.parameters(0.35F, 0.35F, 0.0F, 0.0F, 0.0F, 0.75F, 0.55F),
                biomeRegistry.getOrThrow(Biomes.FLOWER_FOREST)));

        parameters.add(Pair.of(Climate.parameters(0.15F, -0.15F, 0.0F, 0.0F, 0.0F, 0.2F, 0.2F),
                biomeRegistry.getOrThrow(Biomes.BADLANDS)));

        parameters.add(Pair.of(Climate.parameters(-0.1F, 0.0F, 0.0F, 0.0F, 0.0F, 0.25F, 0.4F),
                biomeRegistry.getOrThrow(Biomes.OCEAN)));

        parameters.add(Pair.of(Climate.parameters(-0.55F, -0.35F, 0.0F, 0.0F, 0.0F, 0.05F, 0.1F),
                biomeRegistry.getOrThrow(Biomes.DEEP_OCEAN)));

        parameters.add(Pair.of(Climate.parameters(-0.95F, -0.55F, 0.0F, 0.0F, 0.0F, 0.0F, 0.05F),
                biomeRegistry.getOrThrow(Biomes.FROZEN_OCEAN)));

        parameters.add(Pair.of(Climate.parameters(0.85F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.55F),
                biomeRegistry.getOrThrow(Biomes.MUSHROOM_FIELDS)));

        parameters.add(Pair.of(Climate.parameters(0.05F, 0.05F, 0.0F, 0.0F, 0.0F, 0.05F, 0.05F),
                biomeRegistry.getOrThrow(Biomes.SUNFLOWER_PLAINS)));

        parameters.add(Pair.of(Climate.parameters(-0.45F, 0.35F, 0.0F, 0.0F, 0.0F, 0.1F, 0.1F),
                biomeRegistry.getOrThrow(Biomes.SNOWY_PLAINS)));

        parameters.add(Pair.of(Climate.parameters(0.0F, -0.25F, 0.0F, 0.0F, 0.0F, 0.1F, 0.65F),
                biomeRegistry.getOrThrow(Biomes.MANGROVE_SWAMP)));

        parameters.add(Pair.of(Climate.parameters(0.25F, 0.35F, 0.0F, 0.0F, 0.0F, 0.7F, 0.55F),
                biomeRegistry.getOrThrow(Biomes.OLD_GROWTH_BIRCH_FOREST)));

        parameters.add(Pair.of(Climate.parameters(-0.5F, 0.25F, 0.0F, 0.0F, 0.0F, 0.35F, 0.4F),
                biomeRegistry.getOrThrow(Biomes.OLD_GROWTH_PINE_TAIGA)));

        parameters.add(Pair.of(Climate.parameters(-0.6F, 0.2F, 0.0F, 0.0F, 0.0F, 0.15F, 0.45F),
                biomeRegistry.getOrThrow(Biomes.OLD_GROWTH_SPRUCE_TAIGA)));

        parameters.add(Pair.of(Climate.parameters(0.4F, 0.45F, 0.0F, 0.0F, 0.0F, 0.75F, 0.6F),
                biomeRegistry.getOrThrow(Biomes.WINDSWEPT_GRAVELLY_HILLS)));

        parameters.add(Pair.of(Climate.parameters(0.3F, 0.35F, 0.0F, 0.0F, 0.0F, 0.7F, 0.65F),
                biomeRegistry.getOrThrow(Biomes.WINDSWEPT_FOREST)));

        parameters.add(Pair.of(Climate.parameters(0.55F, 0.5F, 0.0F, 0.0F, 0.0F, 0.85F, 0.75F),
                biomeRegistry.getOrThrow(Biomes.WINDSWEPT_SAVANNA)));

        parameters.add(Pair.of(Climate.parameters(0.25F, 0.05F, 0.0F, 0.0F, 0.0F, 0.9F, 0.35F),
                biomeRegistry.getOrThrow(Biomes.SPARSE_JUNGLE)));

        parameters.add(Pair.of(Climate.parameters(0.36F, 0.12F, 0.0F, 0.0F, 0.0F, 0.92F, 0.42F),
                biomeRegistry.getOrThrow(Biomes.BAMBOO_JUNGLE)));

        parameters.add(Pair.of(Climate.parameters(0.12F, 0.05F, 0.0F, 0.0F, 0.0F, 0.32F, 0.52F),
                biomeRegistry.getOrThrow(Biomes.ERODED_BADLANDS)));

        parameters.add(Pair.of(Climate.parameters(0.02F, 0.12F, 0.0F, 0.0F, 0.0F, 0.42F, 0.48F),
                biomeRegistry.getOrThrow(Biomes.WOODED_BADLANDS)));

        parameters.add(Pair.of(Climate.parameters(0.11F, 0.32F, 0.0F, 0.0F, 0.0F, 0.62F, 0.42F),
                biomeRegistry.getOrThrow(Biomes.MEADOW)));

        parameters.add(Pair.of(Climate.parameters(0.21F, 0.42F, 0.0F, 0.0F, 0.0F, 0.72F, 0.46F),
                biomeRegistry.getOrThrow(Biomes.CHERRY_GROVE)));

        parameters.add(Pair.of(Climate.parameters(0.32F, 0.33F, 0.0F, 0.0F, 0.0F, 0.67F, 0.52F),
                biomeRegistry.getOrThrow(Biomes.GROVE)));

        parameters.add(Pair.of(Climate.parameters(-0.52F, -0.62F, 0.0F, 0.0F, 0.0F, 0.12F, 0.38F),
                biomeRegistry.getOrThrow(Biomes.SNOWY_SLOPES)));

        parameters.add(Pair.of(Climate.parameters(-0.72F, -0.72F, 0.0F, 0.0F, 0.0F, 0.02F, 0.42F),
                biomeRegistry.getOrThrow(Biomes.FROZEN_PEAKS)));

        parameters.add(Pair.of(Climate.parameters(-0.62F, -0.82F, 0.0F, 0.0F, 0.0F, 0.02F, 0.32F),
                biomeRegistry.getOrThrow(Biomes.JAGGED_PEAKS)));

        parameters.add(Pair.of(Climate.parameters(-0.52F, -0.72F, 0.0F, 0.0F, 0.0F, 0.02F, 0.36F),
                biomeRegistry.getOrThrow(Biomes.STONY_PEAKS)));

        parameters.add(Pair.of(Climate.parameters(0.02F, 0.02F, 0.0F, 0.0F, 0.0F, 0.02F, 0.32F),
                biomeRegistry.getOrThrow(Biomes.RIVER)));

        parameters.add(Pair.of(Climate.parameters(-0.62F, -0.32F, 0.0F, 0.0F, 0.0F, 0.02F, 0.58F),
                biomeRegistry.getOrThrow(Biomes.FROZEN_RIVER)));

        parameters.add(Pair.of(Climate.parameters(0.02F, 0.22F, 0.0F, 0.0F, 0.0F, 0.32F, 0.28F),
                biomeRegistry.getOrThrow(Biomes.BEACH)));

        parameters.add(Pair.of(Climate.parameters(-0.52F, 0.12F, 0.0F, 0.0F, 0.0F, 0.02F, 0.28F),
                biomeRegistry.getOrThrow(Biomes.SNOWY_BEACH)));

        parameters.add(Pair.of(Climate.parameters(-0.32F, 0.22F, 0.0F, 0.0F, 0.0F, 0.12F, 0.38F),
                biomeRegistry.getOrThrow(Biomes.STONY_SHORE)));

        parameters.add(Pair.of(Climate.parameters(-0.22F, -0.62F, 0.0F, 0.0F, 0.0F, 0.02F, 0.82F),
                biomeRegistry.getOrThrow(Biomes.WARM_OCEAN)));

        parameters.add(Pair.of(Climate.parameters(-0.34F, -0.82F, 0.0F, 0.0F, 0.0F, 0.02F, 0.88F),
                biomeRegistry.getOrThrow(Biomes.LUKEWARM_OCEAN)));

        parameters.add(Pair.of(Climate.parameters(-0.45F, -0.91F, 0.0F, 0.0F, 0.0F, 0.02F, 0.94F),
                biomeRegistry.getOrThrow(Biomes.DEEP_LUKEWARM_OCEAN)));

        parameters.add(Pair.of(Climate.parameters(-0.63F, -1.0F, 0.0F, 0.0F, 0.0F, 0.02F, 0.975F),
                biomeRegistry.getOrThrow(Biomes.COLD_OCEAN)));

        parameters.add(Pair.of(Climate.parameters(-0.72F, -1.0F, 0.0F, 0.0F, 0.0F, 0.02F, 0.985F),
                biomeRegistry.getOrThrow(Biomes.DEEP_COLD_OCEAN)));

        parameters.add(Pair.of(Climate.parameters(-0.82F, -1.0F, 0.0F, 0.0F, 0.0F, 0.02F, 0.995F),
                biomeRegistry.getOrThrow(Biomes.DEEP_FROZEN_OCEAN)));

        return MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(parameters));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.CAVES);

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.SHADOWLANDS)),

                                Pair.of(Climate.parameters(-0.8F, 0.7F, 0.5F, 0.2F, 0.3F, 0.6F, 0.4F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.VAMPIRE_FOREST)),

                                Pair.of(Climate.parameters(0.7F, -0.7F, -0.3F, -0.5F, 0.8F, 0.2F, 0.7F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.CRIMSON_DESERT)),

                                Pair.of(Climate.parameters(0.3F, 0.8F, 0.6F, 0.4F, 0.1F, 0.7F, 0.3F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.UNDERGARDEN)),

                                Pair.of(Climate.parameters(0.32F, 0.82F, 0.62F, 0.42F, 0.12F, 0.72F, 0.32F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.CRYSTAL_PEAKS)),

                                Pair.of(Climate.parameters(-0.9F, -0.8F, -0.9F, -0.7F, -0.4F, 0.5F, 0.6F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.ABYSSAL_SEAS)),

                                Pair.of(Climate.parameters(-0.7F, -1.0F, -0.8F, -0.9F, -0.8F, 0.7F, 0.5F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.DEEP_ABYSSAL_SEAS)),

                                Pair.of(Climate.parameters(-0.5F, -0.3F, -0.2F, -0.1F, -0.3F, 0.6F, 0.4F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.FROSTFANG_OCEAN)),

                                Pair.of(Climate.parameters(0.31F, 0.81F, 0.61F, 0.41F, 0.11F, 0.71F, 0.31F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.DEEP_FROSTFANG_OCEAN)),

                                Pair.of(Climate.parameters(-0.6F, -0.4F, -0.1F, -0.3F, -0.2F, 0.5F, 0.3F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.STORMREACH_OCEAN)),

                                Pair.of(Climate.parameters(0.29F, 0.79F, 0.59F, 0.39F, 0.09F, 0.69F, 0.29F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.DEEP_STORMREACH_OCEAN)),

                                Pair.of(Climate.parameters(0.8F, 0.3F, 0.4F, 0.5F, 0.7F, 0.7F, 0.5F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.VEIL_WATER_SHOALS)),

                                Pair.of(Climate.parameters(0.28F, 0.78F, 0.58F, 0.38F, 0.08F, 0.68F, 0.28F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.VEIL_WATER_ABYSS)),

                                Pair.of(Climate.parameters(0.81F, 0.31F, 0.41F, 0.51F, 0.71F, 0.71F, 0.51F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.SCHORCHING_SHOALS)),

                                Pair.of(Climate.parameters(0.27F, 0.77F, 0.57F, 0.37F, 0.07F, 0.67F, 0.27F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.SCHORCHING_ABYSS)),

                                Pair.of(Climate.parameters(0.2F, 0.3F, 0.1F, 0.0F, 0.2F, 0.5F, 0.4F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.VEILWATER_BASIN)),

                                Pair.of(Climate.parameters(0.21F, 0.31F, 0.11F, 0.01F, 0.21F, 0.51F, 0.41F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.TWILIGHT_SHOALS)),

                                Pair.of(Climate.parameters(0.22F, 0.32F, 0.12F, 0.02F, 0.22F, 0.52F, 0.42F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.VEIL_SHORE_BANKS)),

                                Pair.of(Climate.parameters(0.5F, 0.4F, 0.2F, 0.2F, 0.3F, 0.4F, 0.3F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.ECHO_FUNGLE_FOREST)),

                                Pair.of(Climate.parameters(0.4F, 0.3F, 0.15F, 0.3F, 0.2F, 0.3F, 0.2F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.BLOODLEAF_VAMPIRE_FOREST)),

                                Pair.of(Climate.parameters(0.35F, 0.25F, 0.25F, 0.25F, 0.2F, 0.3F, 0.25F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.FERAL_THICKET_VAMPIRE_FOREST)),

                                Pair.of(Climate.parameters(0.1F, 0.5F, 0.2F, 0.25F, 0.15F, 0.35F, 0.3F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.FOGGY_HAUNTED_WOODS)),

                                Pair.of(Climate.parameters(0.15F, 0.45F, 0.25F, 0.3F, 0.2F, 0.3F, 0.3F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.DENSE_HAUNTED_WOODS)),

                                Pair.of(Climate.parameters(0.8F, 0.1F, 0.1F, 0.2F, 0.0F, 0.2F, 0.2F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.CRACKED_BLIGHTED_WASTES)),

                                Pair.of(Climate.parameters(0.85F, 0.15F, 0.1F, 0.2F, 0.05F, 0.15F, 0.2F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.RED_BLIGHTED_WASTES)),

                                Pair.of(Climate.parameters(1.0F, 0.0F, 0.3F, 0.1F, 0.2F, 0.1F, 0.1F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.EMBER_FLARELANDS)),

                                Pair.of(Climate.parameters(0.95F, 0.05F, 0.25F, 0.1F, 0.15F, 0.1F, 0.1F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.DRIED_FLARELANDS)),

                                Pair.of(Climate.parameters(0.3F, 0.3F, 0.4F, 0.2F, 0.35F, 0.3F, 0.3F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.OVERGROWN_VILE_FOREST)),

                                Pair.of(Climate.parameters(0.25F, 0.25F, 0.3F, 0.2F, 0.3F, 0.25F, 0.25F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.WITHERED_VILE_FOREST)),

                                Pair.of(Climate.parameters(0.2F, 0.4F, 0.5F, 0.3F, 0.4F, 0.3F, 0.35F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.LUSH_WHISPERING_FOREST)),

                                Pair.of(Climate.parameters(0.25F, 0.35F, 0.45F, 0.35F, 0.35F, 0.3F, 0.3F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.TWILIGHT_WHISPERING_FOREST)),

                                Pair.of(Climate.parameters(1.2F, 0.05F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.SCORCHED_BARRENS)),

                                Pair.of(Climate.parameters(1.1F, 0.1F, 0.05F, 0.1F, 0.1F, 0.1F, 0.1F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.OBSIDIAN_FLATS)),

                                Pair.of(Climate.parameters(0.5F, 0.2F, 0.6F, 0.2F, 0.3F, 0.4F, 0.4F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.DECAY_HIGHLANDS)),

                                Pair.of(Climate.parameters(0.45F, 0.3F, 0.65F, 0.2F, 0.3F, 0.4F, 0.45F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.HOLLOWED_PEAKS)),

                                Pair.of(Climate.parameters(0.4F, 0.2F, 0.6F, 0.3F, 0.35F, 0.45F, 0.35F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.TWILIGHT_PLATEAU)),

                                Pair.of(Climate.parameters(0.55F, 0.4F, 0.2F, 0.3F, 0.6F, 0.4F, 0.4F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.MIRKMIRE_SWAMP)),

                                Pair.of(Climate.parameters(0.9F, 0.2F, 0.35F, 0.15F, 0.15F, 0.2F, 0.15F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.VOLCANIC_FOOTHILLS)),

                                Pair.of(Climate.parameters(0.4F, 0.5F, 0.35F, 0.25F, 0.5F, 0.45F, 0.3F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.SOUL_GROVE)),

                                Pair.of(Climate.parameters(0.35F, 0.3F, 0.25F, 0.2F, 0.3F, 0.25F, 0.3F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.SHADOWED_HILLS)),

                                Pair.of(Climate.parameters(0.6F, 0.15F, 0.3F, 0.15F, 0.2F, 0.2F, 0.25F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.BLIGHTED_SLOPES)),

                                Pair.of(Climate.parameters(0.9F, 0.7F, 0.3F, 0.6F, 0.5F, 0.6F, 0.5F),
                                        biomeRegistry.getOrThrow(HexcraftBiomes.HELL_FUNGLE_FOREST))
                        ))),

                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.CAVES));

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(HexcraftDimensions.UNDERWORLD_DIM_TYPE), noiseBasedChunkGenerator);

        context.register(UNDERWORLDDIM_KEY, stem);

        // Prison World chunk generator with vanilla overworld biomes
        MultiNoiseBiomeSource prisonBiomeSource = createVanillaOverworldBiomeSource(biomeRegistry);
        Holder<NoiseGeneratorSettings> overworldNoiseSettings = noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD);

        NoiseBasedChunkGenerator prisonChunkGenerator = new NoiseBasedChunkGenerator(prisonBiomeSource, overworldNoiseSettings);

        context.register(PRISONWORLD_DIM_KEY, new LevelStem(dimTypes.getOrThrow(PRISONWORLD_DIM_TYPE), prisonChunkGenerator));
    }
}