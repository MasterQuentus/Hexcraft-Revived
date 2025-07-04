package net.masterquentus.hexcraftmod.datagen;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;

public class HexcraftNoiseBuilders {
    private static final SurfaceRules.RuleSource GRASS_BLOCK = SurfaceRules.state(Blocks.GRASS_BLOCK.defaultBlockState());
    private static final SurfaceRules.RuleSource DIRT = SurfaceRules.state(Blocks.DIRT.defaultBlockState());
    private static final SurfaceRules.RuleSource VILE_GRASS_BLOCK = SurfaceRules.state(HexcraftBlocks.VILE_GRASS_BLOCK.get().defaultBlockState());
    private static final SurfaceRules.RuleSource VILE_DIRT = SurfaceRules.state(HexcraftBlocks.VILE_DIRT.get().defaultBlockState());
    private static final SurfaceRules.RuleSource CRIMSON_SAND_BLOCK = SurfaceRules.state(HexcraftBlocks.CRIMSON_SAND.get().defaultBlockState());
    private static final SurfaceRules.RuleSource CRIMSON_SAND_STONE_BLOCK = SurfaceRules.state(HexcraftBlocks.CRIMSON_SAND_STONE.get().defaultBlockState());
    private static final SurfaceRules.RuleSource UNDER_WORLD_STONE_BLOCK = SurfaceRules.state(HexcraftBlocks.UNDER_WORLD_STONE.get().defaultBlockState());
    private static final SurfaceRules.RuleSource BEDROCK = SurfaceRules.state(Blocks.BEDROCK.defaultBlockState());
    private static final SurfaceRules.RuleSource DEEPSLATE = SurfaceRules.state(Blocks.DEEPSLATE.defaultBlockState());
    private static final SurfaceRules.RuleSource TWILIGHT_MOSSGRASS_BLOCK = SurfaceRules.state(HexcraftBlocks.TWILIGHT_MOSSGRASS.get().defaultBlockState());
    private static final SurfaceRules.RuleSource GLOOMROOT_SOIL = SurfaceRules.state(HexcraftBlocks.GLOOMROOT_SOIL.get().defaultBlockState());
    private static final SurfaceRules.RuleSource GLIMMER_CAP_BLOCK = SurfaceRules.state(HexcraftBlocks.GLIMMER_CAP.get().defaultBlockState());
    private static final SurfaceRules.RuleSource SHARDSTONE_BLOCK = SurfaceRules.state(HexcraftBlocks.SHARDSTONE.get().defaultBlockState());
    private static final SurfaceRules.RuleSource LUMICLAST_BLOCK = SurfaceRules.state(HexcraftBlocks.LUMICLAST.get().defaultBlockState());
    private static final SurfaceRules.RuleSource ABYSSAL_SAND_BLOCK = SurfaceRules.state(HexcraftBlocks.ABYSSAL_SAND.get().defaultBlockState());
    private static final SurfaceRules.RuleSource ABYSSAL_GRAVEL_BLOCK = SurfaceRules.state(HexcraftBlocks.ABYSSAL_GRAVEL.get().defaultBlockState());
    private static final SurfaceRules.RuleSource DEEP_WATER_BLOCK = SurfaceRules.state(HexcraftBlocks.DEEP_WATER_BLOCK.get().defaultBlockState());
    private static final SurfaceRules.RuleSource TWILIGHT_CORAL = SurfaceRules.state(HexcraftBlocks.TWILIGHT_CORAL.get().defaultBlockState());
    private static final SurfaceRules.RuleSource TWILIGHT_CORAL_BLOCK = SurfaceRules.state(HexcraftBlocks.TWILIGHT_CORAL_BLOCK.get().defaultBlockState());
    private static final SurfaceRules.RuleSource TWILIGHT_CORAL_FAN = SurfaceRules.state(HexcraftBlocks.TWILIGHT_CORAL_FAN.get().defaultBlockState());
    private static final SurfaceRules.RuleSource TWILIGHT_CORAL_WALL_FAN = SurfaceRules.state(HexcraftBlocks.TWILIGHT_CORAL_WALL_FAN.get().defaultBlockState());
    private static final SurfaceRules.RuleSource SANGUINE_CORAL = SurfaceRules.state(HexcraftBlocks.SANGUINE_CORAL.get().defaultBlockState());
    private static final SurfaceRules.RuleSource SANGUINE_CORAL_BLOCK = SurfaceRules.state(HexcraftBlocks.SANGUINE_CORAL_BLOCK.get().defaultBlockState());
    private static final SurfaceRules.RuleSource SANGUINE_CORAL_FAN = SurfaceRules.state(HexcraftBlocks.SANGUINE_CORAL_FAN.get().defaultBlockState());
    private static final SurfaceRules.RuleSource SANGUINE_CORAL_WALL_FAN = SurfaceRules.state(HexcraftBlocks.SANGUINE_CORAL_WALL_FAN.get().defaultBlockState());
    private static final SurfaceRules.RuleSource WHISPER_CORAL = SurfaceRules.state(HexcraftBlocks.WHISPER_CORAL.get().defaultBlockState());
    private static final SurfaceRules.RuleSource WHISPER_CORAL_BLOCK = SurfaceRules.state(HexcraftBlocks.WHISPER_CORAL_BLOCK.get().defaultBlockState());
    private static final SurfaceRules.RuleSource WHISPER_CORAL_FAN = SurfaceRules.state(HexcraftBlocks.WHISPER_CORAL_FAN.get().defaultBlockState());
    private static final SurfaceRules.RuleSource WHISPER_CORAL_WALL_FAN = SurfaceRules.state(HexcraftBlocks.WHISPER_CORAL_WALL_FAN.get().defaultBlockState());
    private static final SurfaceRules.RuleSource EBONFANG_CORAL = SurfaceRules.state(HexcraftBlocks.EBONFANG_CORAL.get().defaultBlockState());
    private static final SurfaceRules.RuleSource EBONFANG_CORAL_BLOCK = SurfaceRules.state(HexcraftBlocks.EBONFANG_CORAL_BLOCK.get().defaultBlockState());
    private static final SurfaceRules.RuleSource EBONFANG_CORAL_FAN = SurfaceRules.state(HexcraftBlocks.EBONFANG_CORAL_FAN.get().defaultBlockState());
    private static final SurfaceRules.RuleSource EBONFANG_CORAL_WALL_FAN = SurfaceRules.state(HexcraftBlocks.EBONFANG_CORAL_WALL_FAN.get().defaultBlockState());
    private static final SurfaceRules.RuleSource SPECTRAL_BLOOM_CORAL = SurfaceRules.state(HexcraftBlocks.SPECTRAL_BLOOM_CORAL.get().defaultBlockState());
    private static final SurfaceRules.RuleSource SPECTRAL_BLOOM_CORAL_BLOCK = SurfaceRules.state(HexcraftBlocks.SPECTRAL_BLOOM_CORAL_BLOCK.get().defaultBlockState());
    private static final SurfaceRules.RuleSource SPECTRAL_BLOOM_CORAL_FAN = SurfaceRules.state(HexcraftBlocks.SPECTRAL_BLOOM_CORAL_FAN.get().defaultBlockState());
    private static final SurfaceRules.RuleSource SPECTRAL_BLOOM_CORAL_WALL_FAN = SurfaceRules.state(HexcraftBlocks.SPECTRAL_BLOOM_CORAL_WALL_FAN.get().defaultBlockState());
    private static final SurfaceRules.RuleSource HELLVINE_CORAL = SurfaceRules.state(HexcraftBlocks.HELLVINE_CORAL.get().defaultBlockState());
    private static final SurfaceRules.RuleSource HELLVINE_CORAL_BLOCK = SurfaceRules.state(HexcraftBlocks.HELLVINE_CORAL_BLOCK.get().defaultBlockState());
    private static final SurfaceRules.RuleSource HELLVINE_CORAL_FAN = SurfaceRules.state(HexcraftBlocks.HELLVINE_CORAL_FAN.get().defaultBlockState());
    private static final SurfaceRules.RuleSource HELLVINE_CORAL_WALL_FAN = SurfaceRules.state(HexcraftBlocks.HELLVINE_CORAL_WALL_FAN.get().defaultBlockState());
    private static final SurfaceRules.RuleSource SOULSTEM_CANDLE = SurfaceRules.state(HexcraftBlocks.SOULSTEM_CANDLE.get().defaultBlockState());
    private static final SurfaceRules.RuleSource LIVING_KELP_PLANT = SurfaceRules.state(HexcraftBlocks.LIVING_KELP_PLANT.get().defaultBlockState());
    private static final SurfaceRules.RuleSource UMBRAL_BLOOM_GRASS = SurfaceRules.state(HexcraftBlocks.UMBRAL_BLOOM_GRASS_BLOCK.get().defaultBlockState());
    private static final SurfaceRules.RuleSource UMBRAL_BLOOM_DIRT = SurfaceRules.state(HexcraftBlocks.UMBRAL_BLOOM_DIRT.get().defaultBlockState());
    private static final SurfaceRules.RuleSource SCORCHFIRE_GRASS = SurfaceRules.state(HexcraftBlocks.SCORCHFIRE_GRASS_BLOCK.get().defaultBlockState());
    private static final SurfaceRules.RuleSource SCORCHFIRE_DIRT = SurfaceRules.state(HexcraftBlocks.SCORCHFIRE_DIRT.get().defaultBlockState());

    public static NoiseGeneratorSettings underworldNoiseSettings(HolderGetter<DensityFunction> densityFunctions, HolderGetter<NormalNoise.NoiseParameters> noise) {
        BlockState holystone = HexcraftBlocks.UNDER_WORLD_STONE.get().defaultBlockState();
        return new NoiseGeneratorSettings(
                new NoiseSettings(0, 128, 2, 1), // noiseSettings
                holystone, // defaultBlock
                HexcraftBlocks.DEEP_WATER_BLOCK.get().defaultBlockState(), // defaultFluid
                makeNoiseRouter(densityFunctions, noise), // noiseRouter
                vampireforestSurfaceRules(), // surfaceRule
                List.of(), // spawnTarget
                -64, // seaLevel
                false, // disableMobGeneration
                false, // aquifersEnabled
                false, // oreVeinsEnabled
                false  // useLegacyRandomSource
        );
    }

    public static SurfaceRules.RuleSource vampireforestSurfaceRules() {
        SurfaceRules.RuleSource surface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0), VILE_GRASS_BLOCK),
                VILE_DIRT
        );

        // ON_FLOOR means surface layer (top block)
        // UNDER_FLOOR means blocks just under surface
        // Everything below that will fall back to default block in NoiseGeneratorSettings (UNDER_WORLD_STONE)

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, surface),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, UNDER_WORLD_STONE_BLOCK)
        );
    }

    private static NoiseRouter makeNoiseRouter(HolderGetter<DensityFunction> densityFunctions, HolderGetter<NormalNoise.NoiseParameters> noise) {
        return createNoiseRouter(densityFunctions, noise, buildFinalDensity(densityFunctions));
    }

    private static DensityFunction buildFinalDensity(HolderGetter<DensityFunction> densityFunctions) {
        DensityFunction density = getFunction(densityFunctions, ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(HexcraftMod.MOD_ID, "base_3d_noise_aether")));
        density = DensityFunctions.add(density, DensityFunctions.constant(-0.13));
        density = slide(density, 0, 128, 72, 0, -0.2, 8, 40, -0.1);
        density = DensityFunctions.add(density, DensityFunctions.constant(-0.05));
        density = DensityFunctions.blendDensity(density);
        density = DensityFunctions.interpolated(density);
        density = density.squeeze();
        return density;
    }

    private static DensityFunction slide(DensityFunction density, int minY, int maxY, int fromYTop, int toYTop, double offset1, int fromYBottom, int toYBottom, double offset2) {
        DensityFunction topSlide = DensityFunctions.yClampedGradient(minY + maxY - fromYTop, minY + maxY - toYTop, 1, 0);
        density = DensityFunctions.lerp(topSlide, offset1, density);
        DensityFunction bottomSlide = DensityFunctions.yClampedGradient(minY + fromYBottom, minY + toYBottom, 0, 1);
        return DensityFunctions.lerp(bottomSlide, offset2, density);
    }

    private static NoiseRouter createNoiseRouter(HolderGetter<DensityFunction> densityFunctions, HolderGetter<NormalNoise.NoiseParameters> noise, DensityFunction finalDensity) {
        DensityFunction shiftX = getFunction(densityFunctions, ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation("shift_x")));
        DensityFunction shiftZ = getFunction(densityFunctions, ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation("shift_z")));
        DensityFunction temperature = DensityFunctions.shiftedNoise2d(shiftX, shiftZ, 0.25, noise.getOrThrow(HexcraftNoises.TEMPERATURE));
        DensityFunction vegetation = DensityFunctions.shiftedNoise2d(shiftX, shiftZ, 0.25, noise.getOrThrow(HexcraftNoises.VEGETATION));
        return new NoiseRouter(
                DensityFunctions.zero(), // barrier noise
                DensityFunctions.zero(), // fluid level floodedness noise
                DensityFunctions.zero(), // fluid level spread noise
                DensityFunctions.zero(), // lava noise
                temperature, // temperature
                vegetation, // vegetation
                DensityFunctions.zero(), // continentalness noise
                DensityFunctions.zero(), // erosion noise
                DensityFunctions.zero(), // depth
                DensityFunctions.zero(), // ridges
                DensityFunctions.zero(), // initial density without jaggedness, not sure if this is needed. Some vanilla dimensions use this while others don't.
                finalDensity, // finaldensity
                DensityFunctions.zero(), // veinToggle
                DensityFunctions.zero(), // veinRidged
                DensityFunctions.zero()); // veinGap
    }

    private static DensityFunction getFunction(HolderGetter<DensityFunction> densityFunctions, ResourceKey<DensityFunction> key) {
        return new DensityFunctions.HolderHolder(densityFunctions.getOrThrow(key));
    }
}