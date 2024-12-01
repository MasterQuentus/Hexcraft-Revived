package net.masterquentus.hexcraftmod.worldgen.biome.suface;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.worldgen.biome.HexcraftBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class HexcraftSurfaceRules {
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
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        // Default grass surface
        SurfaceRules.RuleSource defaultOverWorld = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK)), DIRT);

        //Default bedrock floor
        SurfaceRules.RuleSource bedrock_floor = SurfaceRules.ifTrue(SurfaceRules.verticalGradient(
                        "minecraft:bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)),
                BEDROCK);

        SurfaceRules.RuleSource vilegrassSurface = SurfaceRules.ifTrue(
                SurfaceRules.abovePreliminarySurface(),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, VILE_GRASS_BLOCK)),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, VILE_DIRT),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, VILE_DIRT)
                )

        );

        SurfaceRules.RuleSource desertSurface = SurfaceRules.ifTrue(
                SurfaceRules.abovePreliminarySurface(),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, CRIMSON_SAND_BLOCK)),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, CRIMSON_SAND_BLOCK),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, CRIMSON_SAND_STONE_BLOCK)
                )
        );

        SurfaceRules.RuleSource vampireSurface = SurfaceRules.sequence(
                bedrock_floor,
                vilegrassSurface,
                DEEPSLATE
        );

        SurfaceRules.RuleSource crimsonSurface = SurfaceRules.sequence(
                bedrock_floor,
                desertSurface,
                DEEPSLATE
        );

        SurfaceRules.RuleSource abyssGeneration = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.VAMPIRE_FOREST), vampireSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(HexcraftBiomes.CRIMSON_DESERT), crimsonSurface)
        );

        return SurfaceRules.sequence(
                abyssGeneration,
                defaultOverWorld
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}