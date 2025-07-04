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
    private static final SurfaceRules.RuleSource UMBRAL_BLOOM_GRASS = makeStateRule(HexcraftBlocks.UMBRAL_BLOOM_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource UMBRAL_BLOOM_DIRT = makeStateRule(HexcraftBlocks.UMBRAL_BLOOM_DIRT.get());
    private static final SurfaceRules.RuleSource SCORCHFIRE_GRASS = makeStateRule(HexcraftBlocks.SCORCHFIRE_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource SCORCHFIRE_DIRT = makeStateRule(HexcraftBlocks.SCORCHFIRE_DIRT.get());
    private static final SurfaceRules.RuleSource THICKET_GRASS = makeStateRule(HexcraftBlocks.BRAMBLE_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource THICKET_DIRT = makeStateRule(HexcraftBlocks.BRAMBLE_DIRT.get());
    private static final SurfaceRules.RuleSource FOGGY_SOIL = makeStateRule(HexcraftBlocks.VEILSHADE_SOIL.get());
    private static final SurfaceRules.RuleSource FOGGY_DIRT = makeStateRule(HexcraftBlocks.VEILSHADE_DIRT.get());
    private static final SurfaceRules.RuleSource BLIGHTED_CRACKED_SOIL = makeStateRule(HexcraftBlocks.TAINTED_SOIL.get());
    private static final SurfaceRules.RuleSource BLIGHTED_DIRT = makeStateRule(HexcraftBlocks.TAINTED_DIRT.get());
    private static final SurfaceRules.RuleSource DENSE_SOIL = makeStateRule(HexcraftBlocks.STONELOAM_SOIL.get());
    private static final SurfaceRules.RuleSource DENSE_DIRT = makeStateRule(HexcraftBlocks.STONELOAM_DIRT.get());
    private static final SurfaceRules.RuleSource BLIGHTED_RED_SOIL = makeStateRule(HexcraftBlocks.SEETHING_RED_DIRT.get());
    private static final SurfaceRules.RuleSource BLIGHTED_GRASS = makeStateRule(HexcraftBlocks.FESTERING_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource EMBER_GRASS = makeStateRule(HexcraftBlocks.CINDERWEED_SOIL.get());
    private static final SurfaceRules.RuleSource EMBER_DIRT = makeStateRule(HexcraftBlocks.CINDERWEED_DIRT.get());
    private static final SurfaceRules.RuleSource DRIED_FLARE_GRASS = makeStateRule(HexcraftBlocks.FLAREWORN_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource DRIED_FLARE_DIRT = makeStateRule(HexcraftBlocks.FLAREWORN_DIRT.get());
    private static final SurfaceRules.RuleSource WITHERED_VILE_GRASS = makeStateRule(HexcraftBlocks.BILEWEED_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource WITHERED_VILE_DIRT = makeStateRule(HexcraftBlocks.BILEWEED_DIRT.get());
    private static final SurfaceRules.RuleSource LUSH_WHISPERING_GRASS = makeStateRule(HexcraftBlocks.WHISPERBLOOM_SOIL.get());
    private static final SurfaceRules.RuleSource LUSH_WHISPERING_DIRT = makeStateRule(HexcraftBlocks.WHISPERBLOOM_DIRT.get());
    private static final SurfaceRules.RuleSource TWILIGHT_WHISPERING_GRASS = makeStateRule(HexcraftBlocks.TWILIGHTLOAM_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource TWILIGHT_WHISPERING_DIRT = makeStateRule(HexcraftBlocks.TWILIGHTLOAM_DIRT.get());
    private static final SurfaceRules.RuleSource OBSIDIAN_GRAVEL = makeStateRule(HexcraftBlocks.VOIDGRIT.get());
    private static final SurfaceRules.RuleSource OBSIDIAN_STONE = makeStateRule(HexcraftBlocks.OBSIDROCK.get());
    private static final SurfaceRules.RuleSource DECAY_GRASS = makeStateRule(HexcraftBlocks.ROTROOT_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource DECAY_DIRT = makeStateRule(HexcraftBlocks.ROTROOT_DIRT.get());
    private static final SurfaceRules.RuleSource HOLLOWED_GRASS = makeStateRule(HexcraftBlocks.HUSKWEED_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource HOLLOWED_DIRT = makeStateRule(HexcraftBlocks.HUSKWEED_DIRT.get());
    private static final SurfaceRules.RuleSource MIRKMIRE_MOSS = makeStateRule(HexcraftBlocks.DROWNED_MOSS.get());
    private static final SurfaceRules.RuleSource VOLCANIC_GRAVEL = makeStateRule(HexcraftBlocks.MAGMAGRIT.get());
    private static final SurfaceRules.RuleSource VOLCANIC_ROCK = makeStateRule(HexcraftBlocks.IGNEOROCK.get());
    private static final SurfaceRules.RuleSource SOUL_GRASS = makeStateRule(HexcraftBlocks.SPIRITWEED_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource SOUL_DIRT = makeStateRule(HexcraftBlocks.SPIRITWEED_DIRT.get());
    private static final SurfaceRules.RuleSource SHADOWED_GRASS = makeStateRule(HexcraftBlocks.DUSK_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource SHADOWED_DIRT = makeStateRule(HexcraftBlocks.DUSKDIRT.get());
    private static final SurfaceRules.RuleSource TWILIGHT_PLATEAU_GRASS = makeStateRule(HexcraftBlocks.NIGHTVEIN_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource TWILIGHT_PLATEAU_DIRT = makeStateRule(HexcraftBlocks.NIGHTVEIN_DIRT.get());


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

        SurfaceRules.RuleSource veilwaterShoalsBiome = SurfaceRules.sequence(
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
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, ABYSSAL_SAND_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, ABYSSAL_GRAVEL_BLOCK)
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
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, ABYSSAL_SAND_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, ABYSSAL_GRAVEL_BLOCK)
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
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, ABYSSAL_SAND_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, ABYSSAL_GRAVEL_BLOCK)
                        )
                ),
                underworldStoneLayer
        );

        // Veilwater Basin surface
        SurfaceRules.RuleSource veilwaterAbyssBiome = SurfaceRules.sequence(
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

        SurfaceRules.RuleSource scorchingShoalsBiome = SurfaceRules.sequence(
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

        SurfaceRules.RuleSource scorchingAbyssBiome = SurfaceRules.sequence(
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

// Scorching Reef surface
        SurfaceRules.RuleSource scorchingReefSurface = SurfaceRules.sequence(
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

// Frostfang Ocean surface
        SurfaceRules.RuleSource frostfangOceanSurface = SurfaceRules.sequence(
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

        SurfaceRules.RuleSource veilShoreBanksSurface = SurfaceRules.sequence(
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
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, UMBRAL_BLOOM_GRASS),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, UMBRAL_BLOOM_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource hellFungleSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SCORCHFIRE_GRASS),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SCORCHFIRE_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource bloodleafVampireForestSurface = SurfaceRules.sequence(
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

        SurfaceRules.RuleSource feralThicketVampireForestSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, THICKET_GRASS)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, THICKET_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, THICKET_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource foggyHauntedWoodsSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, FOGGY_SOIL)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, FOGGY_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, FOGGY_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource denseHauntedWoodsSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, DENSE_SOIL)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DENSE_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DENSE_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource crackedBlightedWastesSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, BLIGHTED_CRACKED_SOIL)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, BLIGHTED_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, BLIGHTED_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource redBlightedWastesSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, BLIGHTED_RED_SOIL)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, BLIGHTED_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, BLIGHTED_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource emberFlarelandsSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, EMBER_GRASS)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, EMBER_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, EMBER_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource driedFlarelandsSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, DRIED_FLARE_GRASS)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DRIED_FLARE_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DRIED_FLARE_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource overgrownVileForestSurface = SurfaceRules.sequence(
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

        SurfaceRules.RuleSource witheredVileForestSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, WITHERED_VILE_GRASS)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, WITHERED_VILE_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, WITHERED_VILE_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource lushWhisperingForestSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, LUSH_WHISPERING_GRASS)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, LUSH_WHISPERING_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, LUSH_WHISPERING_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource twilightWhisperingForestSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, TWILIGHT_WHISPERING_GRASS)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, TWILIGHT_WHISPERING_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, TWILIGHT_WHISPERING_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource scorchedBarrensSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, SCORCHFIRE_DIRT)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SCORCHFIRE_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SCORCHFIRE_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource obsidianFlatsSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, OBSIDIAN_GRAVEL)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, OBSIDIAN_STONE),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, OBSIDIAN_STONE)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource decayHighlandsSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, DECAY_GRASS)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DECAY_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DECAY_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource hollowedPeaksSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, HOLLOWED_GRASS)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, HOLLOWED_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, HOLLOWED_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource mirkmireSwampSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, MIRKMIRE_MOSS)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, MIRKMIRE_MOSS),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, MIRKMIRE_MOSS)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource volcanicFoothillsSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, VOLCANIC_GRAVEL)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, VOLCANIC_ROCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, VOLCANIC_ROCK)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource soulGroveSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, SOUL_GRASS)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SOUL_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SOUL_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource shadowedHillsSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, SHADOWED_GRASS)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SHADOWED_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SHADOWED_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource blightedSlopesSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, BLIGHTED_GRASS)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, BLIGHTED_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, BLIGHTED_DIRT)
                        )
                ),
                underworldStoneLayer
        );

        SurfaceRules.RuleSource twilightPlateauSurface = SurfaceRules.sequence(
                bedrockFloor,
                SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, TWILIGHT_PLATEAU_GRASS)),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, TWILIGHT_PLATEAU_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, TWILIGHT_PLATEAU_DIRT)
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
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.ABYSSAL_SEAS), abyssalSeasSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.DEEP_ABYSSAL_SEAS), deepAbyssalSeasSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.STORMREACH_OCEAN), stormreachOceanSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.DEEP_STORMREACH_OCEAN), stormreachOceanSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.FROSTFANG_OCEAN), frostfangOceanSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.DEEP_FROSTFANG_OCEAN), deepAbyssalSeasSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.VEIL_WATER_SHOALS), veilwaterShoalsBiome),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.VEIL_WATER_ABYSS), veilwaterAbyssBiome),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.SCHORCHING_SHOALS), scorchingShoalsBiome),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.SCHORCHING_ABYSS), scorchingAbyssBiome),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.VEILWATER_BASIN), veilwaterBasinSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.FROSTFANG_OCEAN), frostfangOceanSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.DEEP_ABYSSAL_SEAS), deepAbyssalSeasSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.ABYSSAL_SEAS), abyssalSeasSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.VEIL_SHORE_BANKS), veilShoreBanksSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.ECHO_FUNGLE_FOREST), echoFungleSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.HELL_FUNGLE_FOREST), hellFungleSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.BLOODLEAF_VAMPIRE_FOREST), bloodleafVampireForestSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.FERAL_THICKET_VAMPIRE_FOREST), feralThicketVampireForestSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.FOGGY_HAUNTED_WOODS), foggyHauntedWoodsSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.DENSE_HAUNTED_WOODS), denseHauntedWoodsSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.CRACKED_BLIGHTED_WASTES), crackedBlightedWastesSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.RED_BLIGHTED_WASTES), redBlightedWastesSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.EMBER_FLARELANDS), emberFlarelandsSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.DRIED_FLARELANDS), driedFlarelandsSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.OVERGROWN_VILE_FOREST), overgrownVileForestSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.WITHERED_VILE_FOREST), witheredVileForestSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.LUSH_WHISPERING_FOREST), lushWhisperingForestSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.TWILIGHT_WHISPERING_FOREST), twilightWhisperingForestSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.SCORCHED_BARRENS), scorchedBarrensSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.OBSIDIAN_FLATS), obsidianFlatsSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.DECAY_HIGHLANDS), decayHighlandsSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.HOLLOWED_PEAKS), hollowedPeaksSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.MIRKMIRE_SWAMP), mirkmireSwampSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.VOLCANIC_FOOTHILLS), volcanicFoothillsSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.SOUL_GROVE), soulGroveSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.SHADOWED_HILLS), shadowedHillsSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.BLIGHTED_SLOPES), blightedSlopesSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.TWILIGHT_PLATEAU), twilightPlateauSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.TWILIGHT_SHOALS), twilightShoalsSurface)
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