package net.masterquentus.hexcraftmod.worldgen.biome.suface;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.worldgen.biome.HexcraftBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class HexcraftSurfaceRules {
    // Block state rules for your custom blocks and vanilla blocks
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource VILE_GRASS_BLOCK = makeStateRule(HexcraftBlocks.VILE_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource VILE_DIRT = makeStateRule(HexcraftBlocks.VILE_DIRT.get());
    private static final SurfaceRules.RuleSource CRIMSON_SAND_BLOCK = makeStateRule(HexcraftBlocks.CRIMSON_SAND.get());
    private static final SurfaceRules.RuleSource CRIMSON_SAND_STONE_BLOCK = makeStateRule(HexcraftBlocks.CRIMSON_SAND_STONE.get());
    private static final SurfaceRules.RuleSource UNDER_WORLD_STONE_BLOCK = makeStateRule(HexcraftBlocks.UNDER_WORLD_STONE.get());
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final SurfaceRules.RuleSource DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);
    private static final SurfaceRules.RuleSource TWILIGHT_MOSSGRASS_BLOCK = makeStateRule(HexcraftBlocks.TWILIGHT_MOSSGRASS.get());
    private static final SurfaceRules.RuleSource GLOOMROOT_SOIL = makeStateRule(HexcraftBlocks.GLOOMROOT_SOIL.get());
    private static final SurfaceRules.RuleSource GLIMMER_CAP_BLOCK = makeStateRule(HexcraftBlocks.GLIMMER_CAP.get());
    private static final SurfaceRules.RuleSource SHARDSTONE_BLOCK = makeStateRule(HexcraftBlocks.SHARDSTONE.get());
    private static final SurfaceRules.RuleSource LUMICLAST_BLOCK = makeStateRule(HexcraftBlocks.LUMICLAST.get());
    private static final SurfaceRules.RuleSource ABYSSAL_SAND_BLOCK = makeStateRule(HexcraftBlocks.ABYSSAL_SAND.get());
    private static final SurfaceRules.RuleSource ABYSSAL_GRAVEL_BLOCK = makeStateRule(HexcraftBlocks.ABYSSAL_GRAVEL.get());
    private static final SurfaceRules.RuleSource DEEP_WATER_BLOCK = makeStateRule(HexcraftBlocks.DEEP_WATER_BLOCK.get());
    private static final SurfaceRules.RuleSource TWILIGHT_CORAL = makeStateRule(HexcraftBlocks.TWILIGHT_CORAL.get());
    private static final SurfaceRules.RuleSource TWILIGHT_CORAL_BLOCK = makeStateRule(HexcraftBlocks.TWILIGHT_CORAL_BLOCK.get());
    private static final SurfaceRules.RuleSource TWILIGHT_CORAL_FAN = makeStateRule(HexcraftBlocks.TWILIGHT_CORAL_FAN.get());
    private static final SurfaceRules.RuleSource TWILIGHT_CORAL_WALL_FAN = makeStateRule(HexcraftBlocks.TWILIGHT_CORAL_WALL_FAN.get());
    private static final SurfaceRules.RuleSource SANGUINE_CORAL = makeStateRule(HexcraftBlocks.SANGUINE_CORAL.get());
    private static final SurfaceRules.RuleSource SANGUINE_CORAL_BLOCK = makeStateRule(HexcraftBlocks.SANGUINE_CORAL_BLOCK.get());
    private static final SurfaceRules.RuleSource SANGUINE_CORAL_FAN = makeStateRule(HexcraftBlocks.SANGUINE_CORAL_FAN.get());
    private static final SurfaceRules.RuleSource SANGUINE_CORAL_WALL_FAN = makeStateRule(HexcraftBlocks.SANGUINE_CORAL_WALL_FAN.get());
    private static final SurfaceRules.RuleSource WHISPER_CORAL = makeStateRule(HexcraftBlocks.WHISPER_CORAL.get());
    private static final SurfaceRules.RuleSource WHISPER_CORAL_BLOCK = makeStateRule(HexcraftBlocks.WHISPER_CORAL_BLOCK.get());
    private static final SurfaceRules.RuleSource WHISPER_CORAL_FAN = makeStateRule(HexcraftBlocks.WHISPER_CORAL_FAN.get());
    private static final SurfaceRules.RuleSource WHISPER_CORAL_WALL_FAN = makeStateRule(HexcraftBlocks.WHISPER_CORAL_WALL_FAN.get());
    private static final SurfaceRules.RuleSource EBONFANG_CORAL = makeStateRule(HexcraftBlocks.EBONFANG_CORAL.get());
    private static final SurfaceRules.RuleSource EBONFANG_CORAL_BLOCK = makeStateRule(HexcraftBlocks.EBONFANG_CORAL_BLOCK.get());
    private static final SurfaceRules.RuleSource EBONFANG_CORAL_FAN = makeStateRule(HexcraftBlocks.EBONFANG_CORAL_FAN.get());
    private static final SurfaceRules.RuleSource EBONFANG_CORAL_WALL_FAN = makeStateRule(HexcraftBlocks.EBONFANG_CORAL_WALL_FAN.get());
    private static final SurfaceRules.RuleSource SPECTRAL_BLOOM_CORAL = makeStateRule(HexcraftBlocks.SPECTRAL_BLOOM_CORAL.get());
    private static final SurfaceRules.RuleSource SPECTRAL_BLOOM_CORAL_BLOCK = makeStateRule(HexcraftBlocks.SPECTRAL_BLOOM_CORAL_BLOCK.get());
    private static final SurfaceRules.RuleSource SPECTRAL_BLOOM_CORAL_FAN = makeStateRule(HexcraftBlocks.SPECTRAL_BLOOM_CORAL_FAN.get());
    private static final SurfaceRules.RuleSource SPECTRAL_BLOOM_CORAL_WALL_FAN = makeStateRule(HexcraftBlocks.SPECTRAL_BLOOM_CORAL_WALL_FAN.get());
    private static final SurfaceRules.RuleSource HELLVINE_CORAL = makeStateRule(HexcraftBlocks.HELLVINE_CORAL.get());
    private static final SurfaceRules.RuleSource HELLVINE_CORAL_BLOCK = makeStateRule(HexcraftBlocks.HELLVINE_CORAL_BLOCK.get());
    private static final SurfaceRules.RuleSource HELLVINE_CORAL_FAN = makeStateRule(HexcraftBlocks.HELLVINE_CORAL_FAN.get());
    private static final SurfaceRules.RuleSource HELLVINE_CORAL_WALL_FAN = makeStateRule(HexcraftBlocks.HELLVINE_CORAL_WALL_FAN.get());
    private static final SurfaceRules.RuleSource SOULSTEM_CANDLE = makeStateRule(HexcraftBlocks.SOULSTEM_CANDLE.get());
    private static final SurfaceRules.RuleSource LIVING_KELP_PLANT = makeStateRule(HexcraftBlocks.LIVING_KELP_PLANT.get());

    public static SurfaceRules.RuleSource makeRules() {
        // Condition: is block at or above water level
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        // Default Overworld surface: Grass on top with dirt below
        SurfaceRules.RuleSource defaultOverworld = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK)),
                DIRT
        );

        // Bedrock layer at the bottom 5 blocks
        SurfaceRules.RuleSource bedrockFloor = SurfaceRules.ifTrue(
                SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)),
                BEDROCK
        );

        // Underworld stone base (used underground as filler)
        SurfaceRules.RuleSource underworldStoneLayer = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, UNDER_WORLD_STONE_BLOCK)
        );

        // Vampire Forest surface: vile grass on top, vile dirt below
        SurfaceRules.RuleSource vampireSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, VILE_GRASS_BLOCK)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, VILE_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, VILE_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        // Crimson Desert surface: crimson sand on top, crimson sandstone below
        SurfaceRules.RuleSource crimsonDesertSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, CRIMSON_SAND_BLOCK)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, CRIMSON_SAND_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, CRIMSON_SAND_STONE_BLOCK)
                        )
                ),
                underworldStoneLayer
        );

        // NEW: Shadowlands (Underworld main biome) surface: vile grass on top, underworld stone below
        SurfaceRules.RuleSource shadowlandsSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, VILE_GRASS_BLOCK)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, UNDER_WORLD_STONE_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, UNDER_WORLD_STONE_BLOCK)
                        )
                ),
                underworldStoneLayer
        );

        // Undergarden surface: Twilight Mossgrass on top, Gloomroot Soil below
        SurfaceRules.RuleSource undergardenSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, TWILIGHT_MOSSGRASS_BLOCK)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GLOOMROOT_SOIL),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, GLOOMROOT_SOIL)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource crystalPeaksSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GLIMMER_CAP_BLOCK)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SHARDSTONE_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, LUMICLAST_BLOCK)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource stormreachOceanSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, ABYSSAL_SAND_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, ABYSSAL_GRAVEL_BLOCK)
                        )
                ),
                underworldStoneLayer
        );

// Glimmering Shoals surface
        SurfaceRules.RuleSource glimmeringShoalsSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, TWILIGHT_CORAL_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, GLOOMROOT_SOIL)
                        )
                ),
                underworldStoneLayer
        );

// Twilight Shoals surface
        SurfaceRules.RuleSource twilightShoalsSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, TWILIGHT_CORAL_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, TWILIGHT_MOSSGRASS_BLOCK)
                        )
                ),
                underworldStoneLayer
        );

// Veilwater Basin surface
        SurfaceRules.RuleSource veilwaterBasinSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, WHISPER_CORAL_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, GLOOMROOT_SOIL)
                        )
                ),
                underworldStoneLayer
        );

// Scorching Reef surface
        SurfaceRules.RuleSource scorchingReefSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SANGUINE_CORAL_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, CRIMSON_SAND_BLOCK)
                        )
                ),
                underworldStoneLayer
        );

// Frostfang Ocean surface
        SurfaceRules.RuleSource frostfangOceanSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, EBONFANG_CORAL_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DEEPSLATE)
                        )
                ),
                underworldStoneLayer
        );

// Deep Abyssal Seas surface
        SurfaceRules.RuleSource deepAbyssalSeasSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, ABYSSAL_GRAVEL_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, ABYSSAL_SAND_BLOCK)
                        )
                ),
                underworldStoneLayer
        );

// Abyssal Seas surface
        SurfaceRules.RuleSource abyssalSeasSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, ABYSSAL_SAND_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, ABYSSAL_GRAVEL_BLOCK)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource echoFungleSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, TWILIGHT_MOSSGRASS_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, GLOOMROOT_SOIL)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource hellFungleSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, CRIMSON_SAND_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, CRIMSON_SAND_STONE_BLOCK)
                        )
                ),
                underworldStoneLayer
        );



        // Combine biome-specific rules by biome
        SurfaceRules.RuleSource underworldBiomes = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.VAMPIRE_FOREST), vampireSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.CRIMSON_DESERT), crimsonDesertSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.SHADOWLANDS), shadowlandsSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.UNDERGARDEN), undergardenSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.CRYSTAL_PEAKS), crystalPeaksSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.STORMREACH_OCEAN), stormreachOceanSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.GLIMMERING_SHOALS), glimmeringShoalsSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.TWILIGHT_SHOALS), twilightShoalsSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.VEILWATER_BASIN), veilwaterBasinSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.SCORCHING_REEF), scorchingReefSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.FROSTFANG_OCEAN), frostfangOceanSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.DEEP_ABYSSAL_SEAS), deepAbyssalSeasSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.ABYSSAL_SEAS), abyssalSeasSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.ECHO_FUNGLE_FOREST), echoFungleSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.HELL_FUNGLE_FOREST), hellFungleSurface)
        );

        // Final combined rule set: underworld biomes, then default overworld rules as fallback
        return SurfaceRules.sequence(
                underworldBiomes,
                defaultOverworld
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}