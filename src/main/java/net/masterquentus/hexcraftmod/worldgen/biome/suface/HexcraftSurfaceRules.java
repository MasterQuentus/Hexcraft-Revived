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
                // DEEPSLATE removed
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
                // DEEPSLATE removed
                underworldStoneLayer
        );


        // Combine biome-specific rules by biome
        SurfaceRules.RuleSource underworldBiomes = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.VAMPIRE_FOREST), vampireSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.CRIMSON_DESERT), crimsonDesertSurface)
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