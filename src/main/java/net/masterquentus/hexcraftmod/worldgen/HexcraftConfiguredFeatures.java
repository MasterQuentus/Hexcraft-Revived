package net.masterquentus.hexcraftmod.worldgen;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.worldgen.tree.custom.WitchHazelFoliagePlacer;
import net.masterquentus.hexcraftmod.worldgen.tree.custom.WitchHazelTrunkPlacer;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.HugeFungusConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class HexcraftConfiguredFeatures {

	public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_VAMPIRIC_ORE_KEY = registerKey("vampiric_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_VAMPIRIC_ORE_KEY = registerKey("nether_vampiric_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> END_VAMPIRIC_ORE_KEY = registerKey("end_vampiric_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SILVER_ORE_KEY = registerKey("silver_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_SILVER_ORE_KEY = registerKey("nether_silver_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> END_SILVER_ORE_KEY = registerKey("end_silver_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_MOON_STONE_ORE_KEY = registerKey("moon_stone_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_MOON_STONE_ORE_KEY = registerKey("nether_moon_stone_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> END_MOON_STONE_ORE_KEY = registerKey("end_moon_stone_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TRENOGEN_ORE_KEY = registerKey("trenogen_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> JORMUIM_ORE_KEY = registerKey("jormuim_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> NYKIUM_ORE_KEY = registerKey("nykium_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> UNDER_WORLD_SOUL_ORE_KEY = registerKey("soulstone_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> UNDER_WORLD_ABYSS_ORE_KEY = registerKey("abyss_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> UNDER_WORLD_ECLIPSE_ORE_KEY = registerKey("eclipse_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> UNDER_WORLD_ABYSSAL_COAL_ORE_KEY = registerKey("abyssal_coal_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> PEARL_STONE_ORE_KEY = registerKey("pearl_stone_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> CRIMSON_STONE_ORE_KEY = registerKey("crimson_stone_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> CHARSTONE_ORE_KEY = registerKey("charstone_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> MAGIC_CRYSTAL_GEODE_KEY = registerKey("magic_crystal_geode");

	public static final ResourceKey<ConfiguredFeature<?, ?>> EBONY_KEY = registerKey("ebony");

	public static final ResourceKey<ConfiguredFeature<?, ?>> BLOOD_OAK_KEY = registerKey("blood_oak");

	public static final ResourceKey<ConfiguredFeature<?, ?>> HELL_BARK_KEY = registerKey("hell_bark");

	public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_OAK_KEY = registerKey("white_oak");

	public static final ResourceKey<ConfiguredFeature<?, ?>> ALDER_KEY = registerKey("alder");

	public static final ResourceKey<ConfiguredFeature<?, ?>> WITCH_HAZEL_KEY = registerKey("witch_hazel");

	public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_KEY = registerKey("willow");

	public static final ResourceKey<ConfiguredFeature<?, ?>> HAWTHORN_KEY = registerKey("hawthorn");

	public static final ResourceKey<ConfiguredFeature<?, ?>> CEDAR_KEY = registerKey("cedar");

	public static final ResourceKey<ConfiguredFeature<?, ?>> DISTORTED_KEY = registerKey("distorted");

	public static final ResourceKey<ConfiguredFeature<?, ?>> ELDER_KEY = registerKey("elder");

	public static final ResourceKey<ConfiguredFeature<?, ?>> JUNIPER_KEY = registerKey("juniper");

	public static final ResourceKey<ConfiguredFeature<?, ?>> ROWAN_KEY = registerKey("rowan");

	public static final ResourceKey<ConfiguredFeature<?, ?>> TWISTED_KEY = registerKey("twisted");

	public static final ResourceKey<ConfiguredFeature<?, ?>> WITCH_WOOD_KEY = registerKey("witch_wood");

	public static final ResourceKey<ConfiguredFeature<?, ?>> ECHO_KEY = registerKey("echo");

	public static final ResourceKey<ConfiguredFeature<?, ?>> BLOOD_MUSHROOM_KEY = registerKey("blood_mushroom");

	public static final ResourceKey<ConfiguredFeature<?, ?>> VILESHROOM_KEY = registerKey("vileshroom");

	public static final ResourceKey<ConfiguredFeature<?, ?>> GHOSTSHROOM_KEY = registerKey("ghostshroom");

	public static final ResourceKey<ConfiguredFeature<?, ?>> VAMPIRE_ORCHID_KEY = registerKey("vampire_orchid");

	public static final ResourceKey<ConfiguredFeature<?, ?>> GRASS_PATCH_CONFIGURATION = createKey("grass_patch");

	public static final ResourceKey<ConfiguredFeature<?, ?>> HANGING_WILLOW_LEAVES_KEY = registerKey("hanging_willow_leaves");

	public static final ResourceKey<ConfiguredFeature<?, ?>> PHOENIX_KEY = registerKey("phoenix");

	private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(HexcraftMod.MOD_ID, name));
	}

	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

		HolderGetter<Block> $$1 = context.lookup(Registries.BLOCK);

		BlockPredicate validGround = BlockPredicate.matchesBlocks(new Block[] {
				Blocks.DIRT,
				Blocks.GRASS_BLOCK,
				Blocks.MYCELIUM,
				Blocks.CRIMSON_FUNGUS,
				Blocks.CRIMSON_NYLIUM,
				Blocks.WARPED_NYLIUM
				// add your modded ground blocks too, if any
		});


		RuleTest stoneReplaceabeles = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
		RuleTest deepslateReplaceabeles = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
		RuleTest netherrackReplaceabeles = new BlockMatchTest(Blocks.NETHERRACK);
		RuleTest endReplaceabeles = new BlockMatchTest(Blocks.END_STONE);
		RuleTest underworldReplaceabeles = new BlockMatchTest(HexcraftBlocks.UNDER_WORLD_STONE.get());

		List<OreConfiguration.TargetBlockState> overworldVampiricOres = List.of(OreConfiguration.target(stoneReplaceabeles,
						HexcraftBlocks.VAMPIRIC_ORE.get().defaultBlockState()),
				OreConfiguration.target(deepslateReplaceabeles, HexcraftBlocks.DEEPSLATE_VAMPIRIC_ORE.get().defaultBlockState()));

		List<OreConfiguration.TargetBlockState> overworldSilverOres = List.of(OreConfiguration.target(stoneReplaceabeles,
						HexcraftBlocks.SILVER_ORE.get().defaultBlockState()),
				OreConfiguration.target(deepslateReplaceabeles, HexcraftBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState()));

		List<OreConfiguration.TargetBlockState> overworldMoonStoneOres = List.of(OreConfiguration.target(stoneReplaceabeles,
						HexcraftBlocks.MOONSTONE_ORE.get().defaultBlockState()),
				OreConfiguration.target(deepslateReplaceabeles, HexcraftBlocks.DEEPSLATE_MOONSTONE_ORE.get().defaultBlockState()));

		List<OreConfiguration.TargetBlockState> overworldTrenogenOres = List.of(OreConfiguration.target(stoneReplaceabeles,
						HexcraftBlocks.TRENOGEN_ORE.get().defaultBlockState()),
				OreConfiguration.target(deepslateReplaceabeles, HexcraftBlocks.DEEPSLATE_TRENOGEN_ORE.get().defaultBlockState()));

		List<OreConfiguration.TargetBlockState> underworldSoulstoneOres = List.of(OreConfiguration.target(underworldReplaceabeles,
				HexcraftBlocks.SOULSTONE_ORE.get().defaultBlockState()));

		List<OreConfiguration.TargetBlockState> underworldAbyssOres = List.of(OreConfiguration.target(underworldReplaceabeles,
				HexcraftBlocks.ABYSSIUM_ORE.get().defaultBlockState()));

		List<OreConfiguration.TargetBlockState> underworldEclipseOres = List.of(OreConfiguration.target(underworldReplaceabeles,
				HexcraftBlocks.ECLIPSIUM_ORE.get().defaultBlockState()));

		List<OreConfiguration.TargetBlockState> underworldAbyssalCoalOres = List.of(OreConfiguration.target(underworldReplaceabeles,
				HexcraftBlocks.ABYSSAL_COAL_ORE.get().defaultBlockState()));

		List<OreConfiguration.TargetBlockState> overworldPearlStoneOres = List.of(OreConfiguration.target(stoneReplaceabeles,
				HexcraftBlocks.PEARL_STONE.get().defaultBlockState()));

		List<OreConfiguration.TargetBlockState> overworldCrimsonStoneOres = List.of(OreConfiguration.target(stoneReplaceabeles,
				HexcraftBlocks.CRIMSON_STONE.get().defaultBlockState()));

		List<OreConfiguration.TargetBlockState> overworldCharstoneOres = List.of(OreConfiguration.target(stoneReplaceabeles,
				HexcraftBlocks.CHARSTONE.get().defaultBlockState()));

		register(context, OVERWORLD_VAMPIRIC_ORE_KEY, Feature.ORE, new OreConfiguration(overworldVampiricOres, 9));
		register(context, NETHER_VAMPIRIC_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceabeles,
				HexcraftBlocks.NETHER_VAMPIRIC_ORE.get().defaultBlockState(), 9));

		register(context, END_VAMPIRIC_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceabeles,
				HexcraftBlocks.END_VAMPIRIC_ORE.get().defaultBlockState(), 9));

		register(context, OVERWORLD_SILVER_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSilverOres, 9));
		register(context, NETHER_SILVER_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceabeles,
				HexcraftBlocks.NETHER_SILVER_ORE.get().defaultBlockState(), 9));

		register(context, END_SILVER_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceabeles,
				HexcraftBlocks.END_SILVER_ORE.get().defaultBlockState(), 9));

		register(context, OVERWORLD_MOON_STONE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldMoonStoneOres, 9));
		register(context, NETHER_MOON_STONE_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceabeles,
				HexcraftBlocks.NETHER_MOONSTONE_ORE.get().defaultBlockState(), 9));

		register(context, END_MOON_STONE_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceabeles,
				HexcraftBlocks.END_MOONSTONE_ORE.get().defaultBlockState(), 9));
		register(context, OVERWORLD_TRENOGEN_ORE_KEY, Feature.ORE, new OreConfiguration(overworldTrenogenOres, 9));

		register(context, JORMUIM_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceabeles,
				HexcraftBlocks.JORMUIM_ORE.get().defaultBlockState(), 9));

		register(context, NYKIUM_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceabeles,
				HexcraftBlocks.JORMUIM_ORE.get().defaultBlockState(), 6));

		register(context, UNDER_WORLD_SOUL_ORE_KEY, Feature.ORE, new OreConfiguration(underworldSoulstoneOres, 6));

		register(context, UNDER_WORLD_ABYSS_ORE_KEY, Feature.ORE, new OreConfiguration(underworldAbyssOres, 6));

		register(context, UNDER_WORLD_ECLIPSE_ORE_KEY, Feature.ORE, new OreConfiguration(underworldEclipseOres, 6));

		register(context, UNDER_WORLD_ABYSSAL_COAL_ORE_KEY, Feature.ORE, new OreConfiguration(underworldAbyssalCoalOres, 6));

		register(context, CHARSTONE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldCharstoneOres, 64));

		register(context, PEARL_STONE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldPearlStoneOres, 64));

		register(context, CRIMSON_STONE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldCrimsonStoneOres, 64));

		register(context, VAMPIRE_ORCHID_KEY, Feature.FLOWER,
				new RandomPatchConfiguration(32, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(BlockStateProvider.simple(HexcraftBlocks.VAMPIRE_ORCHID.get())))));

		register(context, MAGIC_CRYSTAL_GEODE_KEY, Feature.GEODE,
				new GeodeConfiguration(
						new GeodeBlockSettings(
								BlockStateProvider.simple(Blocks.AIR),
								BlockStateProvider.simple(HexcraftBlocks.MAGIC_CRYSTAL_BLOCK.get()),
								BlockStateProvider.simple(HexcraftBlocks.BUDDING_MAGIC_CRYSTAL.get()),
								BlockStateProvider.simple(Blocks.CALCITE),
								BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
								List.of(
										HexcraftBlocks.SMALL_MAGIC_CRYSTAL_BUD.get().defaultBlockState(),
										HexcraftBlocks.MEDIUM_MAGIC_CRYSTAL_BUD.get().defaultBlockState(),
										HexcraftBlocks.LARGE_MAGIC_CRYSTAL_BUD.get().defaultBlockState(),
										HexcraftBlocks.MAGIC_CRYSTAL_CLUSTER.get().defaultBlockState()
								),
								BlockTags.FEATURES_CANNOT_REPLACE,
								BlockTags.GEODE_INVALID_BLOCKS
						),
						new GeodeLayerSettings(1.7, 2.2, 3.2, 4.2), // Thickness settings (same as Amethyst)
						new GeodeCrackSettings(0.95, 2.0, 2), // Crack chance and severity (matches Amethyst)
						0.95, // Use chance (higher means more common)
						0.1, // Placement chance per chunk (slightly rarer than Amethyst)
						true, // Can generate in any biome
						UniformInt.of(4, 6), // Outer shell thickness
						UniformInt.of(3, 4), // Middle layer thickness
						UniformInt.of(1, 2), // Inner core thickness
						-64, 30, // Spawn range, similar to Amethyst Geodes (Y -64 to Y 30)
						0.05, // Density factor
						1 // Rarity modifier (same as Amethyst)
				));

		register(context, EBONY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(HexcraftBlocks.EBONY_LOG.get()),
				new StraightTrunkPlacer(7, 3, 2),  // Increased trunk height

				BlockStateProvider.simple(HexcraftBlocks.EBONY_LEAVES.get()),
				new SpruceFoliagePlacer(UniformInt.of(3, 4), UniformInt.of(0, 1), UniformInt.of(3, 5)),  // More spread-out foliage

				new TwoLayersFeatureSize(2, 0, 2)).ignoreVines().build());

		// Blood Oak Tree Registration (Dark Oak Style)
		register(context, BLOOD_OAK_KEY, Feature.TREE,
				(new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(HexcraftBlocks.BLOOD_OAK_LOG.get()),
						new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.simple(HexcraftBlocks.BLOOD_OAK_LEAVES.get()),
						new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
						new ThreeLayersFeatureSize(1, 1, 0, 1, 2,
								OptionalInt.empty()))).ignoreVines().build());


		// Hell Bark Huge Fungus Registration
		register(context, HELL_BARK_KEY, Feature.HUGE_FUNGUS, new HugeFungusConfiguration(
				Blocks.GRASS_BLOCK.defaultBlockState(),
				HexcraftBlocks.HELL_BARK_LOG.get().defaultBlockState(),
				HexcraftBlocks.HELL_BARK_LEAVES.get().defaultBlockState(),
				HexcraftBlocks.HELL_FUNGAL_LAMP.get().defaultBlockState(),
				validGround,
				true
		));

		// White Oak Tree Registration (Dark Oak Style)
		register(context, WHITE_OAK_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(HexcraftBlocks.WHITE_OAK_LOG.get()),  // Log block from your mod
				new DarkOakTrunkPlacer(6, 2, 1),                                // Dark Oak-like trunk placer
				BlockStateProvider.simple(HexcraftBlocks.WHITE_OAK_LEAVES.get()), // Leaves block from your mod
				new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),   // Vanilla-style foliage placer
				new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())   // Size parameters matching vanilla Dark Oak
		).ignoreVines().build());

		register(context, ALDER_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(HexcraftBlocks.ALDER_LOG.get()),
				new StraightTrunkPlacer(2, 3, 2),

				BlockStateProvider.simple(HexcraftBlocks.ALDER_LEAVES.get()),
				new BlobFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), 5),  // Denser foliage

				new TwoLayersFeatureSize(2, 0, 2)).build());

		register(context, WITCH_HAZEL_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(HexcraftBlocks.WITCH_HAZEL_LOG.get()),

				// Custom trunk placer to ensure a solid 3x3 trunk with natural branching
				new WitchHazelTrunkPlacer(18, 5, 3), // Adjusted for height & variation

				BlockStateProvider.simple(HexcraftBlocks.WITCH_HAZEL_LEAVES.get()),

				// Custom foliage placer: Balanced leaf density to prevent excessive decay
				new WitchHazelFoliagePlacer(UniformInt.of(2, 5), UniformInt.of(1, 3)),

				// Ensures the tree grows with proper layers and height balance
				new ThreeLayersFeatureSize(4, 2, 2, 1, 2, OptionalInt.of(5)))
				.ignoreVines()
				.build());

		// Willow Tree
		register(context, WILLOW_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(HexcraftBlocks.WILLOW_LOG.get()),

				// Trunk with some forking to allow branch formation
				new ForkingTrunkPlacer(5, 3, 2),

				BlockStateProvider.simple(HexcraftBlocks.WILLOW_LEAVES.get()),

				// Custom foliage placer for weeping willow effect
				new BlobFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), 3),  // Thorny feature

				// Ensure proper tree growth shape
				new TwoLayersFeatureSize(2, 1, 1)).ignoreVines().build());

		register(context, HAWTHORN_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(HexcraftBlocks.HAWTHORN_LOG.get()),
				new ForkingTrunkPlacer(3, 3, 3),

				BlockStateProvider.simple(HexcraftBlocks.HAWTHORN_LEAVES.get()),
				new BlobFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), 3),  // Thorny feature

				new TwoLayersFeatureSize(3, 3, 3)).ignoreVines().build());

		register(context, CEDAR_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(HexcraftBlocks.CEDAR_LOG.get()),
				new StraightTrunkPlacer(5, 3, 2), // Slightly taller trunk for more grandeur

				BlockStateProvider.simple(HexcraftBlocks.CEDAR_LEAVES.get()),
				new SpruceFoliagePlacer(UniformInt.of(5, 6), UniformInt.of(3, 5), UniformInt.of(4, 6)), // Thicker foliage with more spread

				new TwoLayersFeatureSize(3, 2, 2)).ignoreVines().build());

		register(context, DISTORTED_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(HexcraftBlocks.DISTORTED_LOG.get()),
				new StraightTrunkPlacer(4, 5, 3), // Straight trunk with more height

				BlockStateProvider.simple(HexcraftBlocks.DISTORTED_LEAVES.get()),
				new SpruceFoliagePlacer(UniformInt.of(3, 4), UniformInt.of(2, 3), UniformInt.of(2, 4)), // More foliage spread

				new TwoLayersFeatureSize(3, 2, 2)).ignoreVines().build());

		register(context, ELDER_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(HexcraftBlocks.ELDER_LOG.get()),
				new StraightTrunkPlacer(2, 3, 3),

				BlockStateProvider.simple(HexcraftBlocks.ELDER_LEAVES.get()),
				new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 2),

				new TwoLayersFeatureSize(2, 1, 0)).ignoreVines().build());

		register(context, JUNIPER_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(HexcraftBlocks.JUNIPER_LOG.get()),
				new ForkingTrunkPlacer(2, 4, 4),

				BlockStateProvider.simple(HexcraftBlocks.JUNIPER_LEAVES.get()),
				new DarkOakFoliagePlacer(UniformInt.of(1, 1), UniformInt.of(0, 0)),

				new TwoLayersFeatureSize(1, 1, 0)).ignoreVines().build());

		register(context, ROWAN_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(HexcraftBlocks.ROWAN_LOG.get()),
				new StraightTrunkPlacer(3, 5, 4),

				BlockStateProvider.simple(HexcraftBlocks.ROWAN_LEAVES.get()),
				new BlobFoliagePlacer(ConstantInt.of(4), ConstantInt.of(1), 3),

				new TwoLayersFeatureSize(3, 2, 0)).ignoreVines().build());

		register(context, TWISTED_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(HexcraftBlocks.TWISTED_LOG.get()),
				new ForkingTrunkPlacer(5, 5, 5),  // Twisted branches

				BlockStateProvider.simple(HexcraftBlocks.TWISTED_LEAVES.get()),
				new DarkOakFoliagePlacer(UniformInt.of(0, 0), UniformInt.of(0, 0)),

				new TwoLayersFeatureSize(1, 1, 0)).ignoreVines().build());

		register(context, WITCH_WOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(HexcraftBlocks.WITCH_WOOD_LOG.get()),
				new ForkingTrunkPlacer(5, 5, 5),

				BlockStateProvider.simple(HexcraftBlocks.WITCH_WOOD_LEAVES.get()),
				new DarkOakFoliagePlacer(UniformInt.of(0, 0), UniformInt.of(0, 0)),

				new TwoLayersFeatureSize(1, 1, 0)).ignoreVines().build());

		register(context, PHOENIX_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(HexcraftBlocks.PHOENIX_LOG.get()),

				// Short, wide, and strong base trunk like dark oak
				new DarkOakTrunkPlacer(5, 2, 1),

				BlockStateProvider.simple(HexcraftBlocks.PHOENIX_LEAVES.get()),

				// Dense flame-shape foliage
				new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),

				// Thick, low-hanging leaf shape — wide canopy
				new ThreeLayersFeatureSize(1, 0, 0, 1, 2, OptionalInt.of(4))
		).ignoreVines().build());

		// Echo Wood Huge Fungus Registration
		register(context, ECHO_KEY, Feature.HUGE_FUNGUS, new HugeFungusConfiguration(
				Blocks.GRASS_BLOCK.defaultBlockState(),
				HexcraftBlocks.ECHO_WOOD_LOG.get().defaultBlockState(),
				HexcraftBlocks.ECHO_WOOD_LEAVES.get().defaultBlockState(),
				HexcraftBlocks.ECHO_FUNGAL_LAMP.get().defaultBlockState(),
				validGround,
				true
		));

		register(context, BLOOD_MUSHROOM_KEY, Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfiguration(
				BlockStateProvider.simple(HexcraftBlocks.BLOOD_MUSHROOM_BLOCK.get().defaultBlockState()
						.setValue(HugeMushroomBlock.UP, Boolean.valueOf(true))
						.setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))),
				BlockStateProvider.simple(HexcraftBlocks.BLOOD_MUSHROOM_STEM.get().defaultBlockState()
						.setValue(HugeMushroomBlock.UP, Boolean.valueOf(false))
						.setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))),
				3));

		register(context, VILESHROOM_KEY, Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfiguration(
				BlockStateProvider.simple(HexcraftBlocks.VILESHROOM_BLOCK.get().defaultBlockState()
						.setValue(HugeMushroomBlock.UP, Boolean.valueOf(true))
						.setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))),
				BlockStateProvider.simple(HexcraftBlocks.VILESHROOM_STEM.get().defaultBlockState()
						.setValue(HugeMushroomBlock.UP, Boolean.valueOf(false))
						.setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))),
				3));

		register(context, GHOSTSHROOM_KEY, Feature.HUGE_RED_MUSHROOM, new HugeMushroomFeatureConfiguration(
				BlockStateProvider.simple(HexcraftBlocks.GHOSTSHROOM_BLOCK.get().defaultBlockState()
						.setValue(HugeMushroomBlock.UP, Boolean.valueOf(true))
						.setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))),
				BlockStateProvider.simple(HexcraftBlocks.GHOSTSHROOM_STEM.get().defaultBlockState()
						.setValue(HugeMushroomBlock.UP, Boolean.valueOf(false))
						.setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))),
				3));
	}


	public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(HexcraftMod.MOD_ID, name));
	}

	private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
																						  ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
		context.register(key, new ConfiguredFeature<>(feature, configuration));
	}

	private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block p_195147_, Block p_195148_, int pBaseHeight, int pHeightRandA, int pHeightRandB, int p_195152_) {
		return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(p_195147_), new StraightTrunkPlacer(pBaseHeight, pHeightRandA, pHeightRandB), BlockStateProvider.simple(p_195148_), new BlobFoliagePlacer(ConstantInt.of(p_195152_), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1));
	}

}