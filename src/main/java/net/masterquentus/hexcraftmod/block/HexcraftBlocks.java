package net.masterquentus.hexcraftmod.block;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.block.custom.*;
import net.masterquentus.hexcraftmod.block.custom.plants.*;
import net.masterquentus.hexcraftmod.block.custom.signs.HexcraftStadingSignBlock;
import net.masterquentus.hexcraftmod.block.custom.signs.HexcraftWallHangingSignBlock;
import net.masterquentus.hexcraftmod.block.custom.signs.HexcraftWallSignBlock;
import net.masterquentus.hexcraftmod.block.custom.signs.HexcraftlHangingSignBlock;
import net.masterquentus.hexcraftmod.block.entity.custom.UnderworldPortalBlock;
import net.masterquentus.hexcraftmod.block.entity.custom.liquid.DeepWaterBlock;
import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.masterquentus.hexcraftmod.fluid.HexcraftFluids;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.masterquentus.hexcraftmod.util.HexcraftWoodTypes;
import net.masterquentus.hexcraftmod.worldgen.mushroom.BloodMushroomGrower;
import net.masterquentus.hexcraftmod.worldgen.mushroom.GhostshroomGrower;
import net.masterquentus.hexcraftmod.worldgen.mushroom.VileshroomGrower;
import net.masterquentus.hexcraftmod.worldgen.tree.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HexcraftBlocks {

	private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
		return (p_50763_) -> {
			return (Boolean) p_50763_.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
		};
	}

	public static final DeferredRegister<Block> BLOCKS =
			DeferredRegister.create(ForgeRegistries.BLOCKS, HexcraftMod.MOD_ID);

	public static final RegistryObject<Block> BRAMBLE_DIRT = BLOCKS.register("bramble_dirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)
					.strength(0.8F)
					.sound(SoundType.ROOTED_DIRT)));

	public static final RegistryObject<Block> BRAMBLE_GRASS_BLOCK = BLOCKS.register("bramble_grass_block",
			() -> new BrambleGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
					.strength(1.0F)
					.sound(SoundType.SWEET_BERRY_BUSH)
					.lightLevel(state -> 1)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> VEILSHADE_DIRT = BLOCKS.register("vileshade_dirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)
					.strength(0.6F)
					.friction(0.85F)
					.sound(SoundType.GRASS)));

	public static final RegistryObject<Block> VEILSHADE_SOIL = BLOCKS.register("veilshade_soil",
			() -> new VeilshadeGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
					.strength(0.6F)
					.sound(SoundType.SHROOMLIGHT)
					.lightLevel(state -> 2)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> TAINTED_DIRT = BLOCKS.register("tainted_dirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)
					.strength(0.6F)               // Slightly easier to break than grass
					.jumpFactor(0.95F)
					.sound(SoundType.SLIME_BLOCK)));

	public static final RegistryObject<Block> TAINTED_SOIL = BLOCKS.register("tainted_soil",
			() -> new TaintedSoilBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
					.strength(0.9F)               // Stronger than dirt for durability
					.jumpFactor(0.9F)             // Slightly less slippery on grass
					.sound(SoundType.SLIME_BLOCK)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> STONELOAM_DIRT = BLOCKS.register("stoneloam_dirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)
					.strength(1.5F)
					.sound(SoundType.STONE)));

	public static final RegistryObject<Block> STONELOAM_SOIL = BLOCKS.register("stoneloam_soil",
			() -> new StoneloamSoilBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
					.strength(1.8F)                   // Slightly tougher than the dirt
					.sound(SoundType.TUFF)            // Dense but not harsh like full stone
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> CINDERWEED_DIRT = BLOCKS.register("cindeweed_dirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)
					.strength(1.0F)
					.lightLevel(state -> 1)
					.sound(SoundType.GRAVEL)));

	public static final RegistryObject<Block> CINDERWEED_SOIL = BLOCKS.register("cinderweed_soil",
			() -> new CinderweedSoilBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
					.strength(1.3F)                        // Slightly tougher than the dirt
					.lightLevel(state -> 2)                // Emits a stronger ember-like glow
					.sound(SoundType.BASALT)               // Charred, brittle crunch
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> SEETHING_RED_DIRT = BLOCKS.register("seething_red_dirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)
					.strength(1.2F)                         // Tougher, dense soil
					.lightLevel(state -> 2)                // Faint glow from seething heat
					.sound(SoundType.BASALT)));

	public static final RegistryObject<Block> FESTERING_GRASS_BLOCK = BLOCKS.register("festering_grass_block",
			() -> new FesteringGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
					.strength(1.5F)                         // Firmer and hostile
					.lightLevel(state -> 3)                // Slightly more glow, corrupted aura
					.sound(SoundType.NETHERRACK)           // Fleshy, rotten crunch
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> FLAREWORN_DIRT = BLOCKS.register("flareworn_dirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)
					.strength(0.6F)
					.sound(SoundType.SAND)));

	public static final RegistryObject<Block> FLAREWORN_GRASS_BLOCK = BLOCKS.register("flareworn_grass_block",
			() -> new FlarewornGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
					.strength(0.9F)                         // Slightly tougher than the dirt
					.sound(SoundType.SAND)                  // Dry, dusty grass
					.lightLevel(state -> 1)                 // Faint shimmer of heat or embers
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> BILEWEED_DIRT = BLOCKS.register("bileweed_dirt_dirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)
					.strength(0.5F)
					.sound(SoundType.SLIME_BLOCK)
					.friction(0.9F)));

	public static final RegistryObject<Block> BILEWEED_GRASS_BLOCK = BLOCKS.register("bileweed_grass_block",
			() -> new BileweedGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
					.strength(0.8F)                          // Tougher than the soft dirt
					.friction(0.85F)                         // Slightly slick, like decay
					.sound(SoundType.SLIME_BLOCK)            // Wet, squelchy growth
					.lightLevel(state -> 1)                  // Faint glow, like bile luminescence
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> WHISPERBLOOM_DIRT = BLOCKS.register("whisperbloom_dirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)
					.strength(0.7F)
					.sound(SoundType.GRASS)));

	public static final RegistryObject<Block> WHISPERBLOOM_SOIL = BLOCKS.register("whisperbloom_soil",
			() -> new WhisperbloomSoilBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
					.strength(1.0F)                         // Slightly tougher than the dirt
					.sound(SoundType.GRASS)                 // Gentle grassy step
					.lightLevel(state -> 2)                 // Soft magical glow
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> TWILIGHTLOAM_DIRT = BLOCKS.register("twilightloam_dirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)
					.strength(0.8F)
					.sound(SoundType.NETHER_WART)));

	public static final RegistryObject<Block> TWILIGHTLOAM_GRASS_BLOCK = BLOCKS.register("twilightloam_grass_block",
			() -> new TwilightloamGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
					.strength(1.1F)                            // Firmer than the loamy base
					.sound(SoundType.NETHER_WART)             // Damp, fungal texture
					.lightLevel(state -> 2)                   // Subtle ambient glow, like dusklight
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> ROTROOT_DIRT = BLOCKS.register("rodroot_dirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)
					.strength(0.5F)
					.sound(SoundType.SLIME_BLOCK)
					.jumpFactor(0.85F)));

	public static final RegistryObject<Block> ROTROOT_GRASS_BLOCK = BLOCKS.register("rotroot_grass_block",
			() -> new RotrootGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
					.strength(0.9F)                          // Tougher than the base rotroot dirt
					.sound(SoundType.SLIME_BLOCK)            // Wet, decaying ground
					.jumpFactor(0.85F)                       // Matches the bouncy feel of decay
					.lightLevel(state -> 1)                  // Faint glow, like bio-luminescent rot
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> HUSKWEED_DIRT = BLOCKS.register("huskweed_dirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)
					.strength(0.6F)
					.sound(SoundType.SOUL_SAND)
					.friction(0.85F)));

	public static final RegistryObject<Block> HUSKWEED_GRASS_BLOCK = BLOCKS.register("huskweed_grass_block",
			() -> new HuskweedGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
					.strength(0.9F)                          // Slightly firmer than the base dirt
					.friction(0.85F)                         // Matches dirt’s sluggish drag
					.sound(SoundType.SOUL_SAND)              // Hollow, dry, cursed sound
					.lightLevel(state -> 1)                  // Dim ambient glow like haunted roots
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> SPIRITWEED_DIRT = BLOCKS.register("soulloam_dirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)
					.strength(0.7F)
					.sound(SoundType.SOUL_SOIL)));

	public static final RegistryObject<Block> SPIRITWEED_GRASS_BLOCK = BLOCKS.register("spiritweed_grass_block",
			() -> new SpiritweedGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
					.strength(1.0F)                          // More resilient than the base dirt
					.sound(SoundType.SOUL_SOIL)              // Ethereal, dry echo
					.lightLevel(state -> 3)                  // Gentle spirit-glow
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> DUSKDIRT = BLOCKS.register("duskdirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)
					.strength(0.6F)
					.sound(SoundType.DEEPSLATE)
					.friction(0.9F)));

	public static final RegistryObject<Block> DUSK_GRASS_BLOCK = BLOCKS.register("dusk_grass_block",
			() -> new DuskGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
					.strength(0.9F)                          // Firmer than DUSKDIRT
					.sound(SoundType.DEEPSLATE)              // Deep, muted crunch
					.friction(0.9F)                          // Matches dirt's slightly sticky feel
					.lightLevel(state -> 1)                  // Low dusk-like glow
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> NIGHTVEIN_DIRT = BLOCKS.register("nightvein_dirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)
					.strength(0.8F)
					.lightLevel(state -> 1)
					.sound(SoundType.AMETHYST)));

	public static final RegistryObject<Block> NIGHTVEIN_GRASS_BLOCK = BLOCKS.register("nightvein_grass_block",
			() -> new NightveinGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
					.strength(1.2F)                         // Firmer than the base dirt
					.sound(SoundType.AMETHYST)              // Crystal-like chiming underfoot
					.lightLevel(state -> 2)                 // Brighter glow than the dirt
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> SCORCHFIRE_GRASS_BLOCK = BLOCKS.register("scorchfire_grass_block",
			() -> new ScorchfireGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
					.lightLevel(state -> 3)));

	public static final RegistryObject<Block> SCORCHFIRE_DIRT = BLOCKS.register("scorchfire_dirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)
					.lightLevel(state -> 1)));

	public static final RegistryObject<Block> SCORCHFIRE_GRASS = registerBlock("scorchfire_grass",
			() -> new TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
					.mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS)
					.offsetType(BlockBehaviour.OffsetType.XYZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));

	public static final RegistryObject<Block> UMBRAL_BLOOM_GRASS_BLOCK = BLOCKS.register("umbral_bloom_grass_block",
			() -> new UmbralBloomGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)
					.strength(0.7F)
					.sound(SoundType.NYLIUM)
					.lightLevel(state -> 3)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> UMBRAL_BLOOM_DIRT = BLOCKS.register("umbral_bloom_dirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)
					.strength(0.9F)
					.sound(SoundType.NYLIUM)
					.lightLevel(state -> 1)));

	public static final RegistryObject<Block> UMBRAL_BLOOM_GRASS = registerBlock("umbral_bloom_grass",
			() -> new TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
					.mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS)
					.offsetType(BlockBehaviour.OffsetType.XYZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));

	public static final RegistryObject<Block> VILE_GRASS_BLOCK = BLOCKS.register("vile_grass_block",
			() -> new VileGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));

	public static final RegistryObject<Block> VILE_DIRT = BLOCKS.register("vile_dirt",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));

	public static final RegistryObject<Block> VILE_GRASS = registerBlock("vile_grass",
			() -> new TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
					.mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS)
					.offsetType(BlockBehaviour.OffsetType.XYZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));

	public static final RegistryObject<Block> TWILIGHT_MOSSGRASS = registerBlock("twilight_mossgrass",
			() -> new TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
					.mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS)
					.offsetType(BlockBehaviour.OffsetType.XYZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));

	public static final RegistryObject<Block> GLOOMROOT_SOIL = BLOCKS.register("gloomroot_soil",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));

	public static final RegistryObject<Block> GLIMMER_CAP = BLOCKS.register("glimmer_cap",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f).lightLevel(state -> 7)));

	public static final RegistryObject<Block> SHARDSTONE = BLOCKS.register("shardstone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.8f)));

	public static final RegistryObject<Block> LUMICLAST = BLOCKS.register("lumiclast",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(2.0f).lightLevel(state -> 4)));

	public static final RegistryObject <Block> CRIMSON_MAGMA = registerBlock("crimson_magma",
			() -> new MagmaBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
					.sound(SoundType.NETHER_BRICKS)));

	public static final RegistryObject<Block> CURSED_SOIL = BLOCKS.register("cursed_soil",
			() -> new CursedSoilBlock(BlockBehaviour.Properties.of().strength(1.5f).sound(SoundType.SOUL_SAND)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> CURED_SOIL = BLOCKS.register("cured_soil",
			() -> new CuredSoilBlock(BlockBehaviour.Properties.of().strength(1.5f).sound(SoundType.GRASS)
					.requiresCorrectToolForDrops().lightLevel((state) -> 4))); // Emits a slight glow

	public static final RegistryObject<Block> FERTILIZED_DIRT = BLOCKS.register("fertilized_dirt",
			() -> new FertilizedDirtBlock(BlockBehaviour.Properties.of().strength(1.5f).sound(SoundType.GRASS)
					.requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> LUMENBLOOM_CLUSTER = BLOCKS.register("lumenbloom_cluster",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE)
					.strength(0.3F)
					.sound(SoundType.AMETHYST_CLUSTER) // Glassy + magical
					.lightLevel(state -> 15)
					.requiresCorrectToolForDrops()
					.noOcclusion()));

	public static final RegistryObject<Block> SEETHING_COALBED = BLOCKS.register("seething_coalbed",
			() -> new SeethingCoalbedBlock(BlockBehaviour.Properties.copy(Blocks.MAGMA_BLOCK)
					.strength(1.2F)
					.lightLevel(state -> 10)
					.requiresCorrectToolForDrops()
					.sound(SoundType.BASALT)));

	public static final RegistryObject <Block> VAMPIRE_ORCHID = registerBlock("vampire_orchid",
			() -> new FlowerBlock(() -> MobEffects.HEALTH_BOOST, 4, BlockBehaviour.Properties.copy(Blocks.ALLIUM)));

	public static final RegistryObject <Block> POTTED_VAMPIRE_ORCHID = BLOCKS.register("potted_vampire_orchid",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), VAMPIRE_ORCHID, BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM)));

	public static final RegistryObject <Block> BLOODY_ROSE = registerBlock("bloody_rose",
			BloodyRoseBlock::new);

	public static final RegistryObject <Block> POTTED_BLOODY_ROSE = BLOCKS.register("potted_bloody_rose",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), BLOODY_ROSE, BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM)));

	public static final RegistryObject<Block> DUSKROOT_LANTERN = registerBlock("duskroot_lantern",
			() -> new DuskrootLanternBlock(
					() -> HexcraftEffects.BLOODLUST.get(), // This is a Supplier<MobEffect>
					200,
					BlockBehaviour.Properties.copy(Blocks.POPPY)
							.lightLevel(state -> 12)
							.sound(SoundType.GRASS)));

	public static final RegistryObject <Block> POTTED_DUSKROOT_LANTERN = BLOCKS.register("potted_duskroot_lantern",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), DUSKROOT_LANTERN, BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM)));

	public static final RegistryObject <Block> SCORCHSTALKS = registerBlock("scorchstalks",
			() -> new FlowerBlock(() -> MobEffects.FIRE_RESISTANCE, 2, BlockBehaviour.Properties.copy(Blocks.POPPY)));

	public static final RegistryObject <Block> POTTED_SCORCHASTALKS = BLOCKS.register("potted_scorchstalks",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), SCORCHSTALKS, BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM)));

	public static final Supplier<Block> GLINT_WEED = registerBlock("glint_weed",
			GlintWeedBlock::new);

	public static final Supplier<Block> EMBER_MOSS = registerBlock("ember_moss",
			EmberMossBlock::new);

	public static final Supplier<Block> SPANISH_MOSS = registerBlock("spanish_moss",
			SpanishMossBlock::new);

	public static final RegistryObject<Block> WISPY_COTTON = registerBlock("wispy_cotton",
			WispyCottenBlock::new);

	public static final Supplier<Block> BLISTER_CACTUS_FLOWER = registerBlock("blister_cactus_flower",
			() -> new BlisterCactusFlowerBlock(BlockBehaviour.Properties.copy(Blocks.SPORE_BLOSSOM)
					.instabreak().noCollission().sound(SoundType.SPORE_BLOSSOM).pushReaction(PushReaction.DESTROY)));

	public static final Supplier<Block> BLISTER_CACTUS = registerBlock("blister_cactus",
			() -> new BlisterCactusBlock(BlockBehaviour.Properties.copy(Blocks.CACTUS)
					.sound(SoundType.MOSS).strength(0.1f)));

	public static final RegistryObject <Block> POTTED_WISPY_COTTON = BLOCKS.register("potted_wispy_cotton",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), WISPY_COTTON, BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM)));

	public static final RegistryObject<Block> SOUL_FLOWER = registerBlock("soul_flower",
			() -> new FlowerBlock(MobEffects.HEAL, 2, BlockBehaviour.Properties.copy(Blocks.POPPY)
					.instabreak().noOcclusion()));

	public static final RegistryObject <Block> POTTED_SOUL_FLOWER = BLOCKS.register("potted_soul_flower",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), SOUL_FLOWER, BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM)));

	public static final Supplier<Block> EMBER_MOSS_BLOCK = registerBlock("ember_moss_block",
			() -> new EmberMoss(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK)
					.instabreak().sound(SoundType.MOSS).strength(0.1f)));

	public static final Supplier<Block> DROWNED_MOSS = registerBlock("drowned_moss",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK)
					.strength(0.4F) // More saturated and dense than dry moss
					.sound(SoundType.WET_GRASS) // Damp, soft sound
					.friction(0.8F) // Slippery like waterlogged terrain
			));

	public static final RegistryObject<Block> EMBER_MOSS_CARPET = registerBlock("ember_moss_carpet",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK)
					.instabreak().sound(SoundType.MOSS_CARPET).strength(0.1f)));

	public static final RegistryObject <Block> DEMON_HEART = registerBlock("demon_heart",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.SCULK)
					.strength(0.5F).sound(SoundType.WET_GRASS).noOcclusion()));

	public static final RegistryObject<Block> WITCHES_LADDER = BLOCKS.register("witches_ladder",
			() -> new WitchesLadderBlock(BlockBehaviour.Properties.copy(Blocks.WEEPING_VINES)
					.instabreak().noOcclusion().randomTicks()));

	public static final RegistryObject<Block> WITCHES_LADDER_PLANT = BLOCKS.register("witches_ladder_plant",
			() -> new WitchesLadderPlant(BlockBehaviour.Properties.copy(Blocks.WEEPING_VINES)
					.instabreak().noOcclusion().noLootTable()));

	public static final RegistryObject<Block> VILEVINE = BLOCKS.register("vilevine",
			() -> new VileVineBlock(BlockBehaviour.Properties.copy(Blocks.WEEPING_VINES)
					.instabreak().noOcclusion().randomTicks()));

	public static final RegistryObject<Block> VILEVINE_PLANT = BLOCKS.register("vilevine_plant",
			() -> new VleVinePlant(BlockBehaviour.Properties.copy(Blocks.WEEPING_VINES)
					.instabreak().noOcclusion().noLootTable()));

	public static final RegistryObject<Block> BLOODTHORN_VINES = BLOCKS.register("bloodthorn_vines",
			() -> new BloodthronVineBlock(BlockBehaviour.Properties.copy(Blocks.TWISTING_VINES)
					.instabreak().noOcclusion().randomTicks()));

	public static final RegistryObject<Block> BLOODTHORN_VINES_PLANT = BLOCKS.register("bloodthorn_vines_plant",
			() -> new BloodthronVinePlantBlock(BlockBehaviour.Properties.copy(Blocks.TWISTING_VINES_PLANT)
					.instabreak().noOcclusion().noLootTable()));

	public static final RegistryObject<Block> SLINKROOT = BLOCKS.register("slinkroot",
			() -> new SlinkrootBlock(BlockBehaviour.Properties.copy(Blocks.TWISTING_VINES)
					.mapColor(MapColor.COLOR_CYAN).randomTicks().noCollission().instabreak().sound(SoundType.WEEPING_VINES)
					.offsetType(BlockBehaviour.OffsetType.XZ).hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).lightLevel(s -> 14)));

	public static final RegistryObject<Block> SLINKROOT_PLANT = BLOCKS.register("slinkroot_plant",
			() -> new SlinkrootPlantBlock(BlockBehaviour.Properties.copy(Blocks.TWISTING_VINES_PLANT)
					.mapColor(MapColor.COLOR_CYAN).noCollission().noLootTable().instabreak().sound(SoundType.WEEPING_VINES).offsetType(BlockBehaviour.OffsetType.XZ)));

	public static final RegistryObject<Block> WILD_BRAMBLE = registerBlock("wild_bramble",
			() -> new WildBrambleBlock(BlockBehaviour.Properties.copy(Blocks.SUGAR_CANE)
					.instabreak()));

	public static final RegistryObject<Block> ENDER_BRAMBLE = registerBlock("ender_bramble",
			() -> new EnderBrambleBlock(BlockBehaviour.Properties.copy(Blocks.SUGAR_CANE)
					.instabreak()));

	public static final RegistryObject<Block> LIVING_KELP = BLOCKS.register("living_kelp",
			() -> new LivingKelpBlock(BlockBehaviour.Properties.copy(Blocks.KELP)
					.noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS)
					.lightLevel((state) -> 15)));

	public static final RegistryObject<Block> LIVING_KELP_PLANT = BLOCKS.register("living_kelp_plant",
			() -> new LivingkelpPlant(BlockBehaviour.Properties.copy(Blocks.KELP_PLANT)
					.instabreak().lightLevel((state) -> 15).noOcclusion().noLootTable()));

	public static final RegistryObject<Block> LIVING_KELP_BLOCK = registerBlock("living_kelp_block",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)
					.strength(0.5F, 2.5F)));

	public static final RegistryObject<Block> BLOOD_BERRIES_PLANT = registerBlockWithoutBlockItem("blood_berries_plant",
			() -> new BloodBerryBush(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH).noOcclusion().randomTicks().noLootTable()));

	public static final RegistryObject<Block> GARLIC_STRAND = registerBlock("garlic_strand",
			() -> new GarlicStrandBlock(BlockBehaviour.Properties.copy(Blocks.VINE).noCollission()));

	public static final RegistryObject<Block> VERVAIN_STRAND = registerBlock("vervain_strand",
			() -> new VervainStrandBlock(BlockBehaviour.Properties.copy(Blocks.VINE).noCollission()));

	//public static final RegistryObject<Block> PHOENIX_EMBER = registerBlock("phoenix_ember",
			//() -> new Block(BlockBehaviour.Properties.copy(Blocks.MAGMA_BLOCK)
					//.strength(0.4F)
					//.sound(SoundType.BASALT)
					//.lightLevel(state -> 12))); // Slightly dimmer glow

	//public static final RegistryObject<Block> ASH_BLOCK = registerBlock("ash_block",
			//() -> new Block(BlockBehaviour.Properties.copy(Blocks.SNOW)
					//.strength(0.1F)
					//.sound(SoundType.SAND)));

	public static final RegistryObject<Block> OBSIDROCK = registerBlock("obsidrock",
			() -> new Block(
					BlockBehaviour.Properties.copy(Blocks.OBSIDIAN)
							.strength(4.5F, 1200.0F) // Durable and blast-resistant, but not unbreakable
							.sound(SoundType.STONE) // Sharp stone impact
							.requiresCorrectToolForDrops()
			));

	public static final RegistryObject <Block> BLACK_OBSIDIAN = registerBlock("black_obsidian",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).requiresCorrectToolForDrops()
					.strength(50.0F, 1200.0F).sound(SoundType.STONE)));


	public static final RegistryObject<Block> HELLFIRE_CAMPFIRE = BLOCKS.register("hellfire_campfire",
			() -> new CampfireBlock(true, 2, BlockBehaviour.Properties.copy(Blocks.CAMPFIRE)
					.strength(2.0F)
					.lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 15 : 0)
					.sound(SoundType.WOOD)));


	//Light Blocks
	public static final RegistryObject<Block> ECHO_FUNGAL_LAMP = registerBlock("echo_fungal_lamp",
			() -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.VERDANT_FROGLIGHT)
					.strength(0.3F).sound(SoundType.FROGLIGHT)
					.lightLevel((state) -> 15)));

	public static final RegistryObject<Block> HELL_FUNGAL_LAMP = registerBlock("hell_fungal_lamp",
			() -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.VERDANT_FROGLIGHT)
					.strength(0.3F).sound(SoundType.FROGLIGHT)
					.lightLevel((state) -> 15)));

	public static final RegistryObject<Block> VILESHROOM_LAMP = registerBlock("vileshroom_lamp",
			() -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.VERDANT_FROGLIGHT)
					.strength(0.3F).sound(SoundType.FROGLIGHT)
					.lightLevel((state) -> 15)));

	public static final RegistryObject<Block> GHOSTSHROOM_LAMP = registerBlock("ghostshroom_lamp",
			() -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.VERDANT_FROGLIGHT)
					.strength(0.3F).sound(SoundType.FROGLIGHT)
					.lightLevel((state) -> 15)));

	public static final RegistryObject<Block> FAIRY_LANTERN = BLOCKS.register("fairy_lantern",
			() -> new FairyLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN)
					.strength(1.5F).requiresCorrectToolForDrops()
					.lightLevel(state -> 10)
					.sound(SoundType.LANTERN)));

	public static final RegistryObject<Block> PIXIE_LANTERN = BLOCKS.register("pixie_lantern",
			() -> new PixieLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN)
					.strength(1.5F)
					.lightLevel(state -> 12)
					.sound(SoundType.LANTERN)));


	//Mushrooms
	public static final RegistryObject<Block> BLOOD_MUSHROOM = registerBlock("blood_mushroom",
			() -> new SaplingBlock (new BloodMushroomGrower(),BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM)
					.lightLevel((state) -> 1)));

	public static final RegistryObject <Block> POTTED_BLOOD_MUSHROOM = BLOCKS.register("potted_blood_mushroom",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), BLOOD_MUSHROOM, BlockBehaviour.Properties.copy(Blocks.POTTED_BROWN_MUSHROOM)));

	public static final RegistryObject<Block> BLOOD_MUSHROOM_BLOCK = registerBlock("blood_mushroom_block",
			() -> new HugeMushroomBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM_BLOCK)
					.strength(0.2F, 0F)));

	public static final RegistryObject<Block> BLOOD_MUSHROOM_STEM = registerBlock("blood_mushroom_stem",
			() -> new HugeMushroomBlock(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM)
					.strength(0.2F, 0F)));

	public static final RegistryObject<Block> VILESHROOM = registerBlock("vileshroom",
			() -> new SaplingBlock (new VileshroomGrower(),BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM)
					.lightLevel((state) -> 1)));

	public static final RegistryObject <Block> POTTED_VILESHROOM = BLOCKS.register("potted_vileshroom",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), VILESHROOM, BlockBehaviour.Properties.copy(Blocks.POTTED_BROWN_MUSHROOM)));

	public static final RegistryObject<Block> VILESHROOM_BLOCK = registerBlock("vileshroom_block",
			() -> new HugeMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM_BLOCK)
					.strength(0.2F, 0F)));

	public static final RegistryObject<Block> VILESHROOM_STEM = registerBlock("vileshroom_stem",
			() -> new HugeMushroomBlock(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM)
					.strength(0.2F, 0F)));

	public static final RegistryObject<Block> GHOSTSHROOM = registerBlock("ghostshroom",
			() -> new SaplingBlock (new GhostshroomGrower(),BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM)
					.lightLevel((state) -> 1)));

	public static final RegistryObject <Block> POTTED_GHOSTSHROOM = BLOCKS.register("potted_ghostshroom",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), GHOSTSHROOM, BlockBehaviour.Properties.copy(Blocks.POTTED_BROWN_MUSHROOM)));

	public static final RegistryObject<Block> GHOSTSHROOM_BLOCK = registerBlock("ghostshroom_block",
			() -> new HugeMushroomBlock(BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM_BLOCK)
					.strength(0.2F, 0F)));

	public static final RegistryObject<Block> GHOSTSHROOM_STEM = registerBlock("ghostshroom_stem",
			() -> new HugeMushroomBlock(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM)
					.strength(0.2F, 0F)));

	public static final RegistryObject<Block> PEARL_STONE = registerBlock("pearl_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> PEARL_COBBLESTONE = registerBlock("pearl_cobblestone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F, 6.0F)));

	public static final RegistryObject<Block> PEARL_STONE_BRICKS = registerBlock("pearl_stone_bricks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> POLISHED_PEARL_STONE = registerBlock("polished_pearl_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CHISELED_PEARL_STONE = registerBlock("chiseled_pearl_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CRACKED_PEARL_STONE = registerBlock("cracked_pearl_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CRIMSON_STONE = registerBlock("crimson_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CRIMSON_COBBLESTONE = registerBlock("crimson_cobblestone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F, 6.0F)));

	public static final RegistryObject<Block> CRIMSON_STONE_BRICKS = registerBlock("crimson_stone_bricks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> POLISHED_CRIMSON_STONE = registerBlock("polished_crimson_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CHISELED_CRIMSON_STONE = registerBlock("chiseled_crimson_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CRACKED_CRIMSON_STONE = registerBlock("cracked_crimson_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> UNDER_WORLD_STONE = registerBlock("under_world_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> UNDER_WORLD_COBBLESTONE = registerBlock("under_world_cobblestone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F, 6.0F)));

	public static final RegistryObject<Block> UNDER_WORLD_STONE_BRICKS = registerBlock("under_world_stone_bricks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> POLISHED_UNDER_WORLD_STONE = registerBlock("polished_under_world_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CHISELED_UNDER_WORLD_STONE = registerBlock("chiseled_under_world_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CRACKED_UNDER_WORLD_STONE = registerBlock("cracked_under_world_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
					.requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CHARSTONE = registerBlock("charstone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CHARSTONE_COBBLESTONE = registerBlock("charstone_cobblestone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F, 6.0F)));

	public static final RegistryObject<Block> CHARSTONE_BRICKS = registerBlock("charstone_bricks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> POLISHED_CHARSTONE = registerBlock("polished_charstone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CHISELED_CHARSTONE = registerBlock("chiseled_charstone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CRACKED_CHARSTONE = registerBlock("cracked_charstone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
					.strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> IGNEOROCK = registerBlock("igneorock",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.BASALT)
					.strength(3.0F, 9.0F) // Tougher than normal stone
					.lightLevel(state -> 2) // Slight ambient glow
					.requiresCorrectToolForDrops()));

	public static final RegistryObject <Block> CRIMSON_SAND = registerBlock("crimson_sand",
			() -> new SandBlock(14406560,BlockBehaviour.Properties.copy(Blocks.SAND)
					.strength(0.5F).sound(SoundType.SAND)));

	public static final RegistryObject <Block> CRIMSON_SAND_STONE = registerBlock("crimson_sand_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE).strength(0.8F).sound(SoundType.STONE)));

	public static final RegistryObject <Block> SMOOTH_CRIMSON_SAND_STONE = registerBlock("smooth_crimson_sand_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_SANDSTONE).strength(2.0F, 6.0F).sound(SoundType.STONE)));

	public static final RegistryObject <Block> CHISELED_CRIMSON_SAND_STONE = registerBlock("chiseled_crimson_sand_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_SANDSTONE).strength(0.8F).sound(SoundType.STONE)));

	public static final RegistryObject <Block> CUT_CRIMSON_SAND_STONE = registerBlock("cut_crimson_sand_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE).strength(0.8F).sound(SoundType.STONE)));

	public static final RegistryObject<Block> ABYSSAL_SAND = registerBlock("abyssal_sand",
			() -> new SandBlock(0x0F1E2D,  // Example color (dark blue-ish)
					BlockBehaviour.Properties.copy(Blocks.SAND)
							.strength(0.5F)
							.sound(SoundType.SAND)
			));

	public static final RegistryObject<Block> ABYSSAL_GRAVEL = registerBlock("abyssal_gravel",
			() -> new GravelBlock(
					BlockBehaviour.Properties.copy(Blocks.GRAVEL)
							.strength(0.6F)
							.sound(SoundType.GRAVEL)
			));

	public static final RegistryObject<Block> VOIDGRIT = registerBlock("voidgrit",
			() -> new GravelBlock(
					BlockBehaviour.Properties.copy(Blocks.GRAVEL)
							.strength(0.5F) // Slightly weaker and looser than regular gravel
							.sound(SoundType.SOUL_SAND) // Echoey, strange crunch
							.friction(0.85F) // Slippery and unsettling
							.jumpFactor(0.95F) // Feels off when walking
			));

	public static final RegistryObject<Block> MAGMAGRIT = registerBlock("magmagrit",
			() -> new GravelBlock(
					BlockBehaviour.Properties.copy(Blocks.GRAVEL)
							.strength(0.8F) // Slightly tougher than normal gravel
							.sound(SoundType.BASALT) // Heavier, stone-like crunch
							.jumpFactor(0.9F) // Slightly slows jumping, like sticky ash
			));

	public static final RegistryObject <Block> FAIRY_SAND = registerBlock("fairy_sand",
			() -> new SandBlock(14406560,BlockBehaviour.Properties.copy(Blocks.SAND)
					.strength(0.5F).sound(SoundType.SAND)));

	public static final RegistryObject <Block> FAIRY_SAND_STONE = registerBlock("fairy_sand_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE).strength(0.8F).sound(SoundType.STONE)));

	public static final RegistryObject <Block> SMOOTH_FAIRY_SAND_STONE = registerBlock("smooth_fairy_sand_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_SANDSTONE)
					.strength(2.0F, 6.0F).sound(SoundType.STONE)));

	public static final RegistryObject <Block> CHISELED_FAIRY_SAND_STONE = registerBlock("chiseled_fairy_sand_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_SANDSTONE).strength(0.8F).sound(SoundType.STONE)));

	public static final RegistryObject <Block> CUT_FAIRY_SAND_STONE = registerBlock("cut_fairy_sand_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE).strength(0.8F).sound(SoundType.STONE)));

	public static final RegistryObject <Block> PIXIE_SAND = registerBlock("pixie_sand",
			() -> new SandBlock(14406560,BlockBehaviour.Properties.copy(Blocks.SAND)
					.strength(0.5F).sound(SoundType.SAND)));

	public static final RegistryObject <Block> PIXIE_SAND_STONE = registerBlock("pixie_sand_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE).strength(0.8F).sound(SoundType.STONE)));

	public static final RegistryObject <Block> SMOOTH_PIXIE_SAND_STONE = registerBlock("smooth_pixie_sand_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_SANDSTONE).strength(2.0F, 6.0F).sound(SoundType.STONE)));

	public static final RegistryObject <Block> CHISELED_PIXIE_SAND_STONE = registerBlock("chiseled_pixie_sand_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_SANDSTONE).strength(0.8F).sound(SoundType.STONE)));

	public static final RegistryObject <Block> CUT_PIXIE_SAND_STONE = registerBlock("cut_pixie_sand_stone",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE).strength(0.8F).sound(SoundType.STONE)));

	//Glass Blocks
	public static final RegistryObject <Block> CRIMSON_GLASS = registerBlock("crimson_glass",
			() -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).instrument(NoteBlockInstrument.HAT)
					.strength(0.3F).sound(SoundType.GLASS).noOcclusion()));

	public static final RegistryObject <Block> CRIMSON_GLASS_PANE = registerBlock("crimson_glass_pane",
			() -> new StainedGlassPaneBlock(DyeColor.RED, BlockBehaviour.Properties.copy(Blocks.GLASS)
					.strength(0.3F).sound(SoundType.GLASS).noOcclusion()));

	public static final RegistryObject <Block> FAIRY_GLASS = registerBlock("fairy_glass",
			() -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).instrument(NoteBlockInstrument.HAT)
					.strength(0.3F).sound(SoundType.GLASS).noOcclusion()));

	public static final RegistryObject <Block> FAIRY_GLASS_PANE = registerBlock("fairy_glass_pane",
			() -> new StainedGlassPaneBlock(DyeColor.RED, BlockBehaviour.Properties.copy(Blocks.GLASS).instrument(NoteBlockInstrument.HAT)
					.strength(0.3F).sound(SoundType.GLASS).noOcclusion()));

	public static final RegistryObject <Block> PIXIE_GLASS = registerBlock("pixie_glass",
			() -> new GlassBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)
					.strength(0.3F).sound(SoundType.GLASS).noOcclusion()));

	public static final RegistryObject <Block> PIXIE_GLASS_PANE = registerBlock("pixie_glass_pane",
			() -> new StainedGlassPaneBlock(DyeColor.RED, BlockBehaviour.Properties.copy(Blocks.GLASS)
					.strength(0.3F).sound(SoundType.GLASS).noOcclusion()));

	//Ice Blocks
	public static final RegistryObject <Block> CRIMSON_ICE = registerBlock("crimson_ice",
			() -> new IceBlock(BlockBehaviour.Properties.copy(Blocks.ICE).friction(0.98F).randomTicks().strength(0.5F)
					.sound(SoundType.GLASS).noOcclusion()));

	public static final RegistryObject <Block> CRIMSON_PACKED_ICE = registerBlock("crimson_packed_ice",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE).friction(0.98F)
					.strength(0.5F).sound(SoundType.GLASS)));

	//Saplings
	public static final RegistryObject<Block> EBONY_SAPLING = registerBlock("ebony_sapling",
			() -> new SaplingBlock(new EbonyTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject <Block> POTTED_EBONY_SAPLING = BLOCKS.register("potted_ebony_sapling",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), EBONY_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

	public static final RegistryObject<Block> BLOOD_OAK_SAPLING = registerBlock("blood_oak_sapling",
			() -> new SaplingBlock(new BloodOakTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject <Block> POTTED_BLOOD_OAK_SAPLING = BLOCKS.register("potted_blood_oak_sapling",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), BLOOD_OAK_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

	public static final RegistryObject<Block> HELL_BARK_SAPLING = registerBlock("hell_bark_sapling",
			() -> new SaplingBlock(new HellBarkTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject <Block> POTTED_HELL_BARK_SAPLING = BLOCKS.register("potted_hell_bark_sapling",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), HELL_BARK_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

	public static final RegistryObject<Block> WHITE_OAK_SAPLING = registerBlock("white_oak_sapling",
			() -> new SaplingBlock(new WhiteOakTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject <Block> POTTED_WHITE_OAK_SAPLING = BLOCKS.register("potted_white_oak_sapling",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), WHITE_OAK_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

	public static final RegistryObject<Block> ALDER_SAPLING = registerBlock("alder_sapling",
			() -> new SaplingBlock(new AlderTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject <Block> POTTED_ALDER_SAPLING = BLOCKS.register("potted_alder_sapling",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), ALDER_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

	public static final RegistryObject<Block> WITCH_HAZEL_SAPLING = registerBlock("witch_hazel_sapling",
			() -> new SaplingBlock(new WitchHazelTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject <Block> POTTED_WITCH_HAZEL_SAPLING = BLOCKS.register("potted_witch_hazel_sapling",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), WITCH_HAZEL_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

	public static final RegistryObject<Block> WILLOW_SAPLING = registerBlock("willow_sapling",
			() -> new SaplingBlock(new WillowTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject <Block> POTTED_WILLOW_SAPLING = BLOCKS.register("potted_willow_sapling",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), WILLOW_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

	public static final RegistryObject<Block> HAWTHORN_SAPLING = registerBlock("hawthorn_sapling",
			() -> new SaplingBlock(new HawthornTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject <Block> POTTED_HAWTHORN_SAPLING = BLOCKS.register("potted_hawthorn_sapling",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), HAWTHORN_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

	public static final RegistryObject<Block> CEDAR_SAPLING = registerBlock("cedar_sapling",
			() -> new SaplingBlock(new CedarTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject <Block> POTTED_CEDAR_SAPLING = BLOCKS.register("potted_cedar_sapling",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), CEDAR_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

	public static final RegistryObject<Block> DISTORTED_SAPLING = registerBlock("distorted_sapling",
			() -> new SaplingBlock(new DistortedTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject <Block> POTTED_DISTORTED_SAPLING = BLOCKS.register("potted_distorted_sapling",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), DISTORTED_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

	public static final RegistryObject<Block> ELDER_SAPLING = registerBlock("elder_sapling",
			() -> new SaplingBlock(new ElderTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject <Block> POTTED_ELDER_SAPLING = BLOCKS.register("potted_elder_sapling",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), ELDER_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

	public static final RegistryObject<Block> JUNIPER_SAPLING = registerBlock("juniper_sapling",
			() -> new SaplingBlock(new JuniperTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject <Block> POTTED_JUNIPER_SAPLING = BLOCKS.register("potted_juniper_sapling",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), JUNIPER_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

	public static final RegistryObject<Block> ROWAN_SAPLING = registerBlock("rowan_sapling",
			() -> new SaplingBlock(new RowanTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject <Block> POTTED_ROWAN_SAPLING = BLOCKS.register("potted_rowan_sapling",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), ROWAN_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

	public static final RegistryObject<Block> TWISTED_SAPLING = registerBlock("twisted_sapling",
			() -> new SaplingBlock(new TwistedTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject <Block> POTTED_TWISTED_SAPLING = BLOCKS.register("potted_twisted_sapling",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), TWISTED_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

	public static final RegistryObject<Block> WITCH_WOOD_SAPLING = registerBlock("witch_wood_sapling",
			() -> new SaplingBlock(new WitchWoodTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject <Block> POTTED_WITCH_WOOD_SAPLING = BLOCKS.register("potted_witch_wood_sapling",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), WITCH_WOOD_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

	public static final RegistryObject<Block> ECHO_WOOD_SAPLING = registerBlock("echo_sapling",
			() -> new SaplingBlock(new EchoTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject <Block> POTTED_ECHO_WOOD_SAPLING = BLOCKS.register("potted_echo_sapling",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), ECHO_WOOD_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

	public static final RegistryObject<Block> PHOENIX_SAPLING = registerBlock("phoenix_sapling",
			() -> new SaplingBlock(new PhoenixTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

	public static final RegistryObject <Block> POTTED_PHOENIX_SAPLING = BLOCKS.register("potted_phoenix_sapling",
			() -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), PHOENIX_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));

	//Leaves
	public static final RegistryObject<Block> EBONY_LEAVES = registerBlock("ebony_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
					return 30;
				}
			});

	public static final RegistryObject<Block> BLOOD_OAK_LEAVES = registerBlock("blood_oak_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
			});

	public static final RegistryObject<Block> HELL_BARK_LEAVES = registerBlock("hell_bark_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return false;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}

				@Override
				public boolean isRandomlyTicking(BlockState state) {
					return false;  // Disable random ticking (decay checks)
				}
			});

	public static final RegistryObject<Block> WHITE_OAK_LEAVES = registerBlock("white_oak_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return false;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
			});

	public static final RegistryObject<Block> ALDER_LEAVES = registerBlock("alder_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return false;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
			});

	public static final RegistryObject<Block> WITCH_HAZEL_LEAVES = registerBlock("witch_hazel_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return false;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
			});

	public static final RegistryObject<Block> WILLOW_LEAVES = registerBlock("willow_leaves",
			() -> new WillowLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));


	public static final RegistryObject<Block> HAWTHORN_LEAVES = registerBlock("hawthorn_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return false;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
			});

	public static final RegistryObject<Block> CEDAR_LEAVES = registerBlock("cedar_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return false;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
			});

	public static final RegistryObject<Block> DISTORTED_LEAVES = registerBlock("distorted_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return false;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
			});

	public static final RegistryObject<Block> ELDER_LEAVES = registerBlock("elder_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return false;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
			});

	public static final RegistryObject<Block> JUNIPER_LEAVES = registerBlock("juniper_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return false;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
			});

	public static final RegistryObject<Block> ROWAN_LEAVES = registerBlock("rowan_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return false;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
			});

	public static final RegistryObject<Block> TWISTED_LEAVES = registerBlock("twisted_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return false;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
			});

	public static final RegistryObject<Block> WITCH_WOOD_LEAVES = registerBlock("witch_wood_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return false;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}
			});

	public static final RegistryObject<Block> ECHO_WOOD_LEAVES = registerBlock("echo_wood_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return false;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 30;
				}

				@Override
				public boolean isRandomlyTicking(BlockState state) {
					return false;  // Disable random ticking (decay checks)
				}
			});

	//Leaves
	public static final RegistryObject<Block> PHOENIX_LEAVES = registerBlock("phoenix_leaves",
			() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
					return false;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
					return 60;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
					return 30;
				}
			});


	//Leaves Pile
	public static final RegistryObject <Block> EBONY_LEAVES_PILE = registerBlock("ebony_leaves_pile",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.1F)
					.sound(SoundType.GRASS).noLootTable().noOcclusion().noCollission()));

	public static final RegistryObject <Block> BLOOD_OAK_LEAVES_PILE = registerBlock("blood_oak_leaves_pile",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.1F)
					.sound(SoundType.GRASS).noLootTable().noOcclusion().noCollission()));

	public static final RegistryObject <Block> HELL_BARK_LEAVES_PILE = registerBlock("hell_bark_leaves_pile",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.1F)
					.sound(SoundType.GRASS).noLootTable().noOcclusion().noCollission()));

	public static final RegistryObject <Block> WHITE_OAK_LEAVES_PILE = registerBlock("white_oak_leaves_pile",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.1F)
					.sound(SoundType.GRASS).noLootTable().noOcclusion().noCollission()));

	public static final RegistryObject <Block> ALDER_LEAVES_PILE = registerBlock("alder_leaves_pile",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.1F)
					.sound(SoundType.GRASS).noLootTable().noOcclusion().noCollission()));

	public static final RegistryObject <Block> WITCH_HAZEL_LEAVES_PILE = registerBlock("witch_hazel_leaves_pile",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.1F)
					.sound(SoundType.GRASS).noLootTable().noOcclusion().noCollission()));

	public static final RegistryObject <Block> WILLOW_LEAVES_PILE = registerBlock("willow_leaves_pile",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.1F)
					.sound(SoundType.GRASS).noLootTable().noOcclusion().noCollission()));

	public static final RegistryObject <Block> HAWTHORN_LEAVES_PILE = registerBlock("hawthorn_leaves_pile",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.1F)
					.sound(SoundType.GRASS).noLootTable().noOcclusion().noCollission()));

	public static final RegistryObject <Block> CEDAR_LEAVES_PILE = registerBlock("cedar_leaves_pile",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.1F)
					.sound(SoundType.GRASS).noLootTable().noOcclusion().noCollission()));

	public static final RegistryObject <Block> DISTORTED_LEAVES_PILE = registerBlock("distorted_leaves_pile",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.1F)
					.sound(SoundType.GRASS).noLootTable().noOcclusion().noCollission()));

	public static final RegistryObject <Block> ELDER_LEAVES_PILE = registerBlock("elder_leaves_pile",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.1F)
					.sound(SoundType.GRASS).noLootTable().noOcclusion().noCollission()));

	public static final RegistryObject <Block> JUNIPER_LEAVES_PILE = registerBlock("juniper_leaves_pile",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.1F)
					.sound(SoundType.GRASS).noLootTable().noOcclusion().noCollission()));

	public static final RegistryObject <Block> ROWAN_LEAVES_PILE = registerBlock("rowan_leaves_pile",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.1F)
					.sound(SoundType.GRASS).noLootTable().noOcclusion().noCollission()));

	public static final RegistryObject <Block> TWISTED_LEAVES_PILE = registerBlock("twisted_leaves_pile",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.1F)
					.sound(SoundType.GRASS).noLootTable().noOcclusion().noCollission()));

	public static final RegistryObject <Block> WITCH_WOOD_LEAVES_PILE = registerBlock("witch_wood_leaves_pile",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.1F)
					.sound(SoundType.GRASS).noLootTable().noOcclusion().noCollission()));

	public static final RegistryObject <Block> ECHO_WOOD_LEAVES_PILE = registerBlock("echo_wood_leaves_pile",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.1F)
					.sound(SoundType.GRASS).noLootTable().noOcclusion().noCollission()));

	public static final RegistryObject <Block> PHOENIX_LEAVES_PILE = registerBlock("phoenix_leaves_pile",
			() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.1F)
					.sound(SoundType.GRASS).noLootTable().noOcclusion().noCollission()));


	//Logs
	public static final RegistryObject<Block> EBONY_LOG = registerBlock("ebony_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> BLOOD_OAK_LOG = registerBlock("blood_oak_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> HELL_BARK_LOG = registerBlock("hell_bark_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));

	public static final RegistryObject<Block> WHITE_OAK_LOG = registerBlock("white_oak_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> ALDER_LOG = registerBlock("alder_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> WITCH_HAZEL_LOG = registerBlock("witch_hazel_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> WILLOW_LOG = registerBlock("willow_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> HAWTHORN_LOG = registerBlock("hawthorn_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> CEDAR_LOG = registerBlock("cedar_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> DISTORTED_LOG = registerBlock("distorted_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> ELDER_LOG = registerBlock("elder_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> JUNIPER_LOG = registerBlock("juniper_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> ROWAN_LOG = registerBlock("rowan_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> TWISTED_LOG = registerBlock("twisted_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> WITCH_WOOD_LOG = registerBlock("witch_wood_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> ECHO_WOOD_LOG = registerBlock("echo_wood_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> PHOENIX_LOG = registerBlock("phoenix_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2.0F)
			));

	//Striped Logs
	public static final RegistryObject<Block> STRIPPED_EBONY_LOG = registerBlock("stripped_ebony_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> STRIPPED_BLOOD_OAK_LOG = registerBlock("stripped_blood_oak_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> STRIPPED_HELL_BARK_LOG = registerBlock("stripped_hell_bark_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> STRIPPED_WHITE_OAK_LOG = registerBlock("stripped_white_oak_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> STRIPPED_ALDER_LOG = registerBlock("stripped_alder_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> STRIPPED_WITCH_HAZEL_LOG = registerBlock("stripped_witch_hazel_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> STRIPPED_WILLOW_LOG = registerBlock("stripped_willow_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> STRIPPED_HAWTHORN_LOG = registerBlock("stripped_hawthorn_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> STRIPPED_CEDAR_LOG = registerBlock("stripped_cedar_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> STRIPPED_DISTORTED_LOG = registerBlock("stripped_distorted_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> STRIPPED_ELDER_LOG = registerBlock("stripped_elder_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> STRIPPED_JUNIPER_LOG = registerBlock("stripped_juniper_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> STRIPPED_ROWAN_LOG = registerBlock("stripped_rowan_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> STRIPPED_TWISTED_LOG = registerBlock("stripped_twisted_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> STRIPPED_WITCH_WOOD_LOG = registerBlock("stripped_witch_wood_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> STRIPPED_ECHO_WOOD_LOG = registerBlock("stripped_echo_wood_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0F)
			));

	public static final RegistryObject<Block> STRIPPED_PHOENIX_LOG = registerBlock("stripped_phoenix_log",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2.0F)
			));

	//Wood
	public static final RegistryObject<Block> EBONY_WOOD = registerBlock("ebony_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> BLOOD_OAK_WOOD = registerBlock("blood_oak_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> HELL_BARK_WOOD = registerBlock("hell_bark_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> WHITE_OAK_WOOD = registerBlock("white_oak_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> ALDER_WOOD = registerBlock("alder_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> WITCH_HAZEL_WOOD = registerBlock("witch_hazel_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> WILLOW_WOOD = registerBlock("willow_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> HAWTHORN_WOOD = registerBlock("hawthorn_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> CEDAR_WOOD = registerBlock("cedar_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> DISTORTED_WOOD = registerBlock("distorted_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> ELDER_WOOD = registerBlock("elder_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> JUNIPER_WOOD = registerBlock("juniper_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> ROWAN_WOOD = registerBlock("rowan_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> TWISTED_WOOD = registerBlock("twisted_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> WITCH_WOOD_WOOD = registerBlock("witch_wood_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> ECHO_WOOD_WOOD = registerBlock("echo_wood_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> PHOENIX_WOOD = registerBlock("phoenix_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F)
			));

	//Stripped Wood
	public static final RegistryObject<Block> STRIPPED_EBONY_WOOD = registerBlock("stripped_ebony_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> STRIPPED_BLOOD_OAK_WOOD = registerBlock("stripped_blood_oak_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> STRIPPED_HELL_BARK_WOOD = registerBlock("stripped_hell_bark_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> STRIPPED_WHITE_OAK_WOOD = registerBlock("stripped_white_oak_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> STRIPPED_ALDER_WOOD = registerBlock("stripped_alder_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> STRIPPED_WITCH_HAZEL_WOOD = registerBlock("stripped_witch_hazel_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> STRIPPED_WILLOW_WOOD = registerBlock("stripped_willow_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> STRIPPED_HAWTHORN_WOOD = registerBlock("stripped_hawthorn_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> STRIPPED_CEDAR_WOOD = registerBlock("stripped_cedar_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> STRIPPED_DISTORTED_WOOD = registerBlock("stripped_distorted_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> STRIPPED_ELDER_WOOD = registerBlock("stripped_elder_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> STRIPPED_JUNIPER_WOOD = registerBlock("stripped_juniper_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> STRIPPED_ROWAN_WOOD = registerBlock("stripped_rowan_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> STRIPPED_TWISTED_WOOD = registerBlock("stripped_twisted_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> STRIPPED_WITCH_WOOD_WOOD = registerBlock("stripped_witch_wood_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> STRIPPED_ECHO_WOOD_WOOD = registerBlock("stripped_echo_wood_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0F)
			));
	public static final RegistryObject<Block> STRIPPED_PHOENIX_WOOD = registerBlock("stripped_phoenix_wood",
			() -> new HexcraftFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2.0F)
			));

	//Planks
	public static final RegistryObject<Block> EBONY_PLANKS = registerBlock("ebony_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});

	public static final RegistryObject<Block> BLOOD_OAK_PLANKS = registerBlock("blood_oak_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});

	public static final RegistryObject<Block> HELL_BARK_PLANKS = registerBlock("hell_bark_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return false;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});

	public static final RegistryObject<Block> WHITE_OAK_PLANKS = registerBlock("white_oak_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});

	public static final RegistryObject<Block> ALDER_PLANKS = registerBlock("alder_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});

	public static final RegistryObject<Block> WITCH_HAZEL_PLANKS = registerBlock("witch_hazel_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});

	public static final RegistryObject<Block> WILLOW_PLANKS = registerBlock("willow_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});

	public static final RegistryObject<Block> HAWTHORN_PLANKS = registerBlock("hawthorn_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});

	public static final RegistryObject<Block> CEDAR_PLANKS = registerBlock("cedar_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});

	public static final RegistryObject<Block> DISTORTED_PLANKS = registerBlock("distorted_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});

	public static final RegistryObject<Block> ELDER_PLANKS = registerBlock("elder_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});

	public static final RegistryObject<Block> JUNIPER_PLANKS = registerBlock("juniper_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});

	public static final RegistryObject<Block> ROWAN_PLANKS = registerBlock("rowan_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});

	public static final RegistryObject<Block> TWISTED_PLANKS = registerBlock("twisted_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});

	public static final RegistryObject<Block> WITCH_WOOD_PLANKS = registerBlock("witch_wood_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});
	public static final RegistryObject<Block> ECHO_WOOD_PLANKS = registerBlock("echo_wood_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return true;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});

	public static final RegistryObject<Block> PHOENIX_PLANKS = registerBlock("phoenix_planks",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
				@Override
				public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return false;
				}

				@Override
				public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 20;
				}

				@Override
				public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
					return 5;
				}
			});


	//Signs
	public static final RegistryObject<Block> EBONY_SIGN = BLOCKS.register("ebony_sign",
			() -> new HexcraftStadingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), HexcraftWoodTypes.EBONY));

	public static final RegistryObject<Block> BLOOD_OAK_SIGN = BLOCKS.register("blood_oak_sign",
			() -> new HexcraftStadingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), HexcraftWoodTypes.BLOOD_OAK));

	public static final RegistryObject<Block> HELL_BARK_SIGN = BLOCKS.register("hell_bark_sign",
			() -> new HexcraftStadingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), HexcraftWoodTypes.HELL_BARK));

	public static final RegistryObject<Block> WHITE_OAK_SIGN = BLOCKS.register("white_oak_sign",
			() -> new HexcraftStadingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), HexcraftWoodTypes.WHITE_OAK));

	public static final RegistryObject<Block> ALDER_SIGN = BLOCKS.register("alder_sign",
			() -> new HexcraftStadingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), HexcraftWoodTypes.ALDER));

	public static final RegistryObject<Block> WITCH_HAZEL_SIGN = BLOCKS.register("witch_hazel_sign",
			() -> new HexcraftStadingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), HexcraftWoodTypes.WITCH_HAZEL));

	public static final RegistryObject<Block> WILLOW_SIGN = BLOCKS.register("willow_sign",
			() -> new HexcraftStadingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), HexcraftWoodTypes.WILLOW));

	public static final RegistryObject<Block> HAWTHORN_SIGN = BLOCKS.register("hawthorn_sign",
			() -> new HexcraftStadingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), HexcraftWoodTypes.HAWTHORN));

	public static final RegistryObject<Block> CEDAR_SIGN = BLOCKS.register("cedar_sign",
			() -> new HexcraftStadingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), HexcraftWoodTypes.CEDAR));

	public static final RegistryObject<Block> DISTORTED_SIGN = BLOCKS.register("distorted_sign",
			() -> new HexcraftStadingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), HexcraftWoodTypes.DISTORTED));

	public static final RegistryObject<Block> ELDER_SIGN = BLOCKS.register("elder_sign",
			() -> new HexcraftStadingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), HexcraftWoodTypes.ELDER));

	public static final RegistryObject<Block> JUNIPER_SIGN = BLOCKS.register("juniper_sign",
			() -> new HexcraftStadingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), HexcraftWoodTypes.JUNIPER));

	public static final RegistryObject<Block> ROWAN_SIGN = BLOCKS.register("rowan_sign",
			() -> new HexcraftStadingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), HexcraftWoodTypes.ROWAN));

	public static final RegistryObject<Block> TWISTED_SIGN = BLOCKS.register("twisted_sign",
			() -> new HexcraftStadingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), HexcraftWoodTypes.TWISTED));

	public static final RegistryObject<Block> WITCH_WOOD_SIGN = BLOCKS.register("witch_wood_sign",
			() -> new HexcraftStadingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), HexcraftWoodTypes.WITCH_WOOD));

	public static final RegistryObject<Block> ECHO_WOOD_SIGN = BLOCKS.register("echo_sign",
			() -> new HexcraftStadingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), HexcraftWoodTypes.ECHO_WOOD));

	public static final RegistryObject<Block> PHOENIX_SIGN = BLOCKS.register("phoenix_sign",
			() -> new HexcraftStadingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), HexcraftWoodTypes.PHOENIX));

	public static final RegistryObject<Block> EBONY_WALL_SIGN = BLOCKS.register("ebony_wall_sign",
			() -> new HexcraftWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), HexcraftWoodTypes.EBONY));

	public static final RegistryObject<Block> BLOOD_OAK_WALL_SIGN = BLOCKS.register("blood_oak_wall_sign",
			() -> new HexcraftWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), HexcraftWoodTypes.BLOOD_OAK));

	public static final RegistryObject<Block> HELL_BARK_WALL_SIGN = BLOCKS.register("hell_bark_wall_sign",
			() -> new HexcraftWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), HexcraftWoodTypes.HELL_BARK));

	public static final RegistryObject<Block> WHITE_OAK_WALL_SIGN = BLOCKS.register("white_oak_wall_sign",
			() -> new HexcraftWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), HexcraftWoodTypes.WHITE_OAK));

	public static final RegistryObject<Block> ALDER_WALL_SIGN = BLOCKS.register("alder_wall_sign",
			() -> new HexcraftWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), HexcraftWoodTypes.ALDER));

	public static final RegistryObject<Block> WITCH_HAZEL_WALL_SIGN = BLOCKS.register("witch_hazel_wall_sign",
			() -> new HexcraftWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), HexcraftWoodTypes.WITCH_HAZEL));

	public static final RegistryObject<Block> WILLOW_WALL_SIGN = BLOCKS.register("willow_wall_sign",
			() -> new HexcraftWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), HexcraftWoodTypes.WILLOW));

	public static final RegistryObject<Block> HAWTHORN_WALL_SIGN = BLOCKS.register("hawthorn_wall_sign",
			() -> new HexcraftWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), HexcraftWoodTypes.HAWTHORN));

	public static final RegistryObject<Block> CEDAR_WALL_SIGN = BLOCKS.register("cedar_wall_sign",
			() -> new HexcraftWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), HexcraftWoodTypes.CEDAR));

	public static final RegistryObject<Block> DISTORTED_WALL_SIGN = BLOCKS.register("distorted_wall_sign",
			() -> new HexcraftWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), HexcraftWoodTypes.DISTORTED));

	public static final RegistryObject<Block> ELDER_WALL_SIGN = BLOCKS.register("elder_wall_sign",
			() -> new HexcraftWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), HexcraftWoodTypes.ELDER));

	public static final RegistryObject<Block> JUNIPER_WALL_SIGN = BLOCKS.register("juniper_wall_sign",
			() -> new HexcraftWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), HexcraftWoodTypes.JUNIPER));

	public static final RegistryObject<Block> ROWAN_WALL_SIGN = BLOCKS.register("rowan_wall_sign",
			() -> new HexcraftWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), HexcraftWoodTypes.ROWAN));

	public static final RegistryObject<Block> TWISTED_WALL_SIGN = BLOCKS.register("twisted_wall_sign",
			() -> new HexcraftWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), HexcraftWoodTypes.TWISTED));

	public static final RegistryObject<Block> WITCH_WOOD_WALL_SIGN = BLOCKS.register("witch_wood_wall_sign",
			() -> new HexcraftWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), HexcraftWoodTypes.WITCH_WOOD));

	public static final RegistryObject<Block> ECHO_WOOD_WALL_SIGN = BLOCKS.register("echo_wall_sign",
			() -> new HexcraftWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), HexcraftWoodTypes.ECHO_WOOD));

	public static final RegistryObject<Block> PHOENIX_WALL_SIGN = BLOCKS.register("phoenix_wall_sign",
			() -> new HexcraftWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), HexcraftWoodTypes.PHOENIX));

	public static final RegistryObject<Block> EBONY_HANGING_SIGN = BLOCKS.register("ebony_hanging_sign",
			() -> new HexcraftlHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), HexcraftWoodTypes.EBONY));

	public static final RegistryObject<Block> BLOOD_OAK_HANGING_SIGN = BLOCKS.register("blood_oak_hanging_sign",
			() -> new HexcraftlHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), HexcraftWoodTypes.BLOOD_OAK));

	public static final RegistryObject<Block> HELL_BARK_HANGING_SIGN = BLOCKS.register("hell_bark_hanging_sign",
			() -> new HexcraftlHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), HexcraftWoodTypes.HELL_BARK));

	public static final RegistryObject<Block> WHITE_OAK_HANGING_SIGN = BLOCKS.register("white_oak_hanging_sign",
			() -> new HexcraftlHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), HexcraftWoodTypes.WHITE_OAK));

	public static final RegistryObject<Block> ALDER_HANGING_SIGN = BLOCKS.register("alder_hanging_sign",
			() -> new HexcraftlHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), HexcraftWoodTypes.ALDER));

	public static final RegistryObject<Block> WITCH_HAZEL_HANGING_SIGN = BLOCKS.register("witch_hazel_hanging_sign",
			() -> new HexcraftlHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), HexcraftWoodTypes.WITCH_HAZEL));

	public static final RegistryObject<Block> WILLOW_HANGING_SIGN = BLOCKS.register("willow_hanging_sign",
			() -> new HexcraftlHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), HexcraftWoodTypes.WILLOW));

	public static final RegistryObject<Block> HAWTHORN_HANGING_SIGN = BLOCKS.register("hawthorn_hanging_sign",
			() -> new HexcraftlHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), HexcraftWoodTypes.HAWTHORN));

	public static final RegistryObject<Block> CEDAR_HANGING_SIGN = BLOCKS.register("cedar_hanging_sign",
			() -> new HexcraftlHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), HexcraftWoodTypes.CEDAR));

	public static final RegistryObject<Block> DISTORTED_HANGING_SIGN = BLOCKS.register("distorted_hanging_sign",
			() -> new HexcraftlHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), HexcraftWoodTypes.DISTORTED));

	public static final RegistryObject<Block> ELDER_HANGING_SIGN = BLOCKS.register("elder_hanging_sign",
			() -> new HexcraftlHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), HexcraftWoodTypes.ELDER));

	public static final RegistryObject<Block> JUNIPER_HANGING_SIGN = BLOCKS.register("juniper_hanging_sign",
			() -> new HexcraftlHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), HexcraftWoodTypes.JUNIPER));

	public static final RegistryObject<Block> ROWAN_HANGING_SIGN = BLOCKS.register("rowan_hanging_sign",
			() -> new HexcraftlHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), HexcraftWoodTypes.ROWAN));

	public static final RegistryObject<Block> TWISTED_HANGING_SIGN = BLOCKS.register("twisted_hanging_sign",
			() -> new HexcraftlHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), HexcraftWoodTypes.TWISTED));

	public static final RegistryObject<Block> WITCH_WOOD_HANGING_SIGN = BLOCKS.register("witch_wood_hanging_sign",
			() -> new HexcraftlHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), HexcraftWoodTypes.WITCH_WOOD));

	public static final RegistryObject<Block> ECHO_WOOD_HANGING_SIGN = BLOCKS.register("echo_hanging_sign",
			() -> new HexcraftlHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), HexcraftWoodTypes.ECHO_WOOD));

	public static final RegistryObject<Block> PHOENIX_HANGING_SIGN = BLOCKS.register("phoenix_hanging_sign",
			() -> new HexcraftlHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), HexcraftWoodTypes.PHOENIX));

	public static final RegistryObject<Block> EBONY_WALL_HANGING_SIGN = BLOCKS.register("ebony_wall_hanging_sign",
			() -> new HexcraftWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), HexcraftWoodTypes.EBONY));

	public static final RegistryObject<Block> BLOOD_OAK_WALL_HANGING_SIGN = BLOCKS.register("blood_oak_wall_hanging_sign",
			() -> new HexcraftWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), HexcraftWoodTypes.BLOOD_OAK));

	public static final RegistryObject<Block> HELL_BARK_WALL_HANGING_SIGN = BLOCKS.register("hell_bark_wall_hanging_sign",
			() -> new HexcraftWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), HexcraftWoodTypes.HELL_BARK));

	public static final RegistryObject<Block> WHITE_OAK_WALL_HANGING_SIGN = BLOCKS.register("white_oak_wall_hanging_sign",
			() -> new HexcraftWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), HexcraftWoodTypes.WHITE_OAK));

	public static final RegistryObject<Block> ALDER_WALL_HANGING_SIGN = BLOCKS.register("alder_wall_hanging_sign",
			() -> new HexcraftWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), HexcraftWoodTypes.ALDER));

	public static final RegistryObject<Block> WITCH_HAZEL_WALL_HANGING_SIGN = BLOCKS.register("witch_hazel_wall_hanging_sign",
			() -> new HexcraftWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), HexcraftWoodTypes.WITCH_HAZEL));

	public static final RegistryObject<Block> WILLOW_WALL_HANGING_SIGN = BLOCKS.register("willow_wall_hanging_sign",
			() -> new HexcraftWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), HexcraftWoodTypes.WILLOW));

	public static final RegistryObject<Block> HAWTHORN_WALL_HANGING_SIGN = BLOCKS.register("hawthorn_wall_hanging_sign",
			() -> new HexcraftWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), HexcraftWoodTypes.HAWTHORN));

	public static final RegistryObject<Block> CEDAR_WALL_HANGING_SIGN = BLOCKS.register("cedar_wall_hanging_sign",
			() -> new HexcraftWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), HexcraftWoodTypes.CEDAR));

	public static final RegistryObject<Block> DISTORTED_WALL_HANGING_SIGN = BLOCKS.register("distorted_wall_hanging_sign",
			() -> new HexcraftWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), HexcraftWoodTypes.DISTORTED));

	public static final RegistryObject<Block> ELDER_WALL_HANGING_SIGN = BLOCKS.register("elder_wall_hanging_sign",
			() -> new HexcraftWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), HexcraftWoodTypes.ELDER));

	public static final RegistryObject<Block> JUNIPER_WALL_HANGING_SIGN = BLOCKS.register("juniper_wall_hanging_sign",
			() -> new HexcraftWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), HexcraftWoodTypes.JUNIPER));

	public static final RegistryObject<Block> ROWAN_WALL_HANGING_SIGN = BLOCKS.register("rowan_wall_hanging_sign",
			() -> new HexcraftWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), HexcraftWoodTypes.ROWAN));

	public static final RegistryObject<Block> TWISTED_WALL_HANGING_SIGN = BLOCKS.register("twisted_wall_hanging_sign",
			() -> new HexcraftWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), HexcraftWoodTypes.TWISTED));

	public static final RegistryObject<Block> WITCH_WOOD_WALL_HANGING_SIGN = BLOCKS.register("witch_wood_wall_hanging_sign",
			() -> new HexcraftWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), HexcraftWoodTypes.WITCH_WOOD));

	public static final RegistryObject<Block> ECHO_WOOD_WALL_HANGING_SIGN = BLOCKS.register("echo_wall_hanging_sign",
			() -> new HexcraftWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), HexcraftWoodTypes.ECHO_WOOD));

	public static final RegistryObject<Block> PHOENIX_WALL_HANGING_SIGN = BLOCKS.register("phoenix_wall_hanging_sign",
			() -> new HexcraftWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), HexcraftWoodTypes.PHOENIX));

	//Bookshelves
	public static final RegistryObject<Block> EBONY_BOOKSHELF = registerBlock("ebony_bookshelf",
			() -> new HexcraftBookshelf(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
					.strength(1.5F)));

	public static final RegistryObject<Block> BLOOD_OAK_BOOKSHELF = registerBlock("blood_oak_bookshelf",
			() -> new HexcraftBookshelf(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
					.strength(1.5F)));

	public static final RegistryObject<Block> HELL_BARK_BOOKSHELF = registerBlock("hell_bark_bookshelf",
			() -> new HexcraftBookshelf(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
					.strength(1.5F)));

	public static final RegistryObject<Block> WHITE_OAK_BOOKSHELF = registerBlock("white_oak_bookshelf",
			() -> new HexcraftBookshelf(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
					.strength(1.5F)));

	public static final RegistryObject<Block> ALDER_BOOKSHELF = registerBlock("alder_bookshelf",
			() -> new HexcraftBookshelf(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
					.strength(1.5F)));

	public static final RegistryObject<Block> WITCH_HAZEL_BOOKSHELF = registerBlock("witch_hazel_bookshelf",
			() -> new HexcraftBookshelf(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
					.strength(1.5F)));

	public static final RegistryObject<Block> WILLOW_BOOKSHELF = registerBlock("willow_bookshelf",
			() -> new HexcraftBookshelf(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
					.strength(1.5F)));

	public static final RegistryObject<Block> HAWTHORN_BOOKSHELF = registerBlock("hawthorn_bookshelf",
			() -> new HexcraftBookshelf(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
					.strength(1.5F)));

	public static final RegistryObject<Block> CEDAR_BOOKSHELF = registerBlock("cedar_bookshelf",
			() -> new HexcraftBookshelf(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
					.strength(1.5F)));

	public static final RegistryObject<Block> DISTORTED_BOOKSHELF = registerBlock("distorted_bookshelf",
			() -> new HexcraftBookshelf(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
					.strength(1.5F)));

	public static final RegistryObject<Block> ELDER_BOOKSHELF = registerBlock("elder_bookshelf",
			() -> new HexcraftBookshelf(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
					.strength(1.5F)));

	public static final RegistryObject<Block> JUNIPER_BOOKSHELF = registerBlock("juniper_bookshelf",
			() -> new HexcraftBookshelf(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
					.strength(1.5F)));

	public static final RegistryObject<Block> ROWAN_BOOKSHELF = registerBlock("rowan_bookshelf",
			() -> new HexcraftBookshelf(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
					.strength(1.5F)));

	public static final RegistryObject<Block> TWISTED_BOOKSHELF = registerBlock("twisted_bookshelf",
			() -> new HexcraftBookshelf(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
					.strength(1.5F)));

	public static final RegistryObject<Block> WITCH_WOOD_BOOKSHELF = registerBlock("witch_wood_bookshelf",
			() -> new HexcraftBookshelf(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
					.strength(1.5F)));

	public static final RegistryObject<Block> ECHO_WOOD_BOOKSHELF = registerBlock("echo_wood_bookshelf",
			() -> new HexcraftBookshelf(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
					.strength(1.5F)));

	public static final RegistryObject<Block> PHOENIX_BOOKSHELF = registerBlock("phoenix_bookshelf",
			() -> new HexcraftBookshelf(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
					.strength(1.5F)));

	//Crates
	//public static final RegistryObject<Block> CRATE_EBONY = BLOCKS.register("crate_ebony", () -> new CrateEbonyBlock());
	//public static final RegistryObject<Block> CRATE_BLOOD_OAK = BLOCKS.register("crate_blood_oak", () -> new CrateBloodOakBlock());
	//public static final RegistryObject<Block> CRATE_HELLBARK = BLOCKS.register("crate_hellbark", () -> new CrateHellbarkBlock());
	//public static final RegistryObject<Block> CRATE_WHITE_OAK = BLOCKS.register("crate_white_oak", () -> new CrateWhiteOakBlock());
	//public static final RegistryObject<Block> CRATE_ALDER = BLOCKS.register("crate_alder", () -> new CrateAlderBlock());
	//public static final RegistryObject<Block> CRATE_WITCH_HAZEL = BLOCKS.register("crate_witch_hazel", () -> new CrateWitchHazelBlock());
	//public static final RegistryObject<Block> CRATE_WILLOW = BLOCKS.register("crate_willow", () -> new CrateWillowBlock());
	//public static final RegistryObject<Block> CRATE_HAWTHORN = BLOCKS.register("crate_hawthorn", () -> new CrateHawthornBlock());
	//public static final RegistryObject<Block> CRATE_CEDAR = BLOCKS.register("crate_cedar", () -> new CrateCedarBlock());
	//public static final RegistryObject<Block> CRATE_DISTORTED = BLOCKS.register("crate_distorted", () -> new CrateDistortedBlock());
	//public static final RegistryObject<Block> CRATE_ELDER = BLOCKS.register("crate_elder", () -> new CrateElderBlock());
	//public static final RegistryObject<Block> CRATE_JUNIPER = BLOCKS.register("crate_juniper", () -> new CrateJuniperBlock());
	//public static final RegistryObject<Block> CRATE_ROWAN = BLOCKS.register("crate_rowan", () -> new CrateRowanBlock());
	//public static final RegistryObject<Block> CRATE_TWISTED = BLOCKS.register("crate_twisted", () -> new CrateTwistedBlock());
	//public static final RegistryObject<Block> CRATE_WITCH_WOOD = BLOCKS.register("crate_witch_wood", () -> new CrateWitchWoodBlock());
	//public static final RegistryObject<Block> CRATE_ECHO_WOOD = BLOCKS.register("crate_echo_wood", () -> new CrateEchoBlock());
	//public static final RegistryObject<Block> CRATE_OAK = BLOCKS.register("crate_oak", () -> new CrateOakBlock());
	//public static final RegistryObject<Block> CRATE_SPRUCE = BLOCKS.register("crate_spruce", () -> new CrateSpruceBlock());
	//public static final RegistryObject<Block> CRATE_BIRCH = BLOCKS.register("crate_birch", () -> new CrateBirchBlock());
	//public static final RegistryObject<Block> CRATE_JUNGLE = BLOCKS.register("crate_jungle", () -> new CrateJungleBlock());
	//public static final RegistryObject<Block> CRATE_ACACIA = BLOCKS.register("crate_acacia", () -> new CrateAcaciaBlock());
	//public static final RegistryObject<Block> CRATE_DARK_OAK = BLOCKS.register("crate_dark_oak", () -> new CrateDarkOakBlock());
	//public static final RegistryObject<Block> CRATE_CRIMSON = BLOCKS.register("crate_crimson", () -> new CrateCrimsonBlock());
	//public static final RegistryObject<Block> CRATE_WARPED = BLOCKS.register("crate_warped", () -> new CrateWarpedBlock());
	//public static final RegistryObject<Block> CRATE_MANGROVE = BLOCKS.register("crate_mangrove", () -> new CrateMangroveBlock());


	//Walls
	public static final RegistryObject<Block> PEARL_STONE_WALL = registerBlock("pearl_stone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_WALL).forceSolidOn().requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> CRIMSON_STONE_WALL = registerBlock("crimson_stone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_WALL).forceSolidOn().requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> UNDER_WORLD_STONE_WALL = registerBlock("under_world_stone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_WALL).forceSolidOn().requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> CHARSTONE_WALL = registerBlock("charstone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE_WALL).forceSolidOn().requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> CRIMSON_SAND_STONE_WALL = registerBlock("crimson_sand_stone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE_WALL).forceSolidOn().requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> FAIRY_SAND_STONE_WALL = registerBlock("fairy_sand_stone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE_WALL).forceSolidOn().requiresCorrectToolForDrops()));

	public static final RegistryObject<Block> PIXIE_SAND_STONE_WALL = registerBlock("pixie_sand_stone_wall",
			() -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE_WALL).forceSolidOn().requiresCorrectToolForDrops()));


	//Stairs
	public static final RegistryObject<Block> PEARL_STONE_STAIRS = registerBlock("pearl_stone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.PEARL_STONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> PEARL_COBBLESTONE_STAIRS = registerBlock("pearl_cobblestone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.PEARL_STONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> PEARL_STONE_BRICKS_STAIRS = registerBlock("pearl_stone_bricks_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.PEARL_STONE_BRICKS.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> POLISHED_PEARL_STONE_STAIRS = registerBlock("polished_pearl_stone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.POLISHED_PEARL_STONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CRIMSON_STONE_STAIRS = registerBlock("crimson_stone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.CRIMSON_STONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CRIMSON_COBBLESTONE_STAIRS = registerBlock("crimson_cobblestone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.CRIMSON_COBBLESTONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CRIMSON_STONE_BRICKS_STAIRS = registerBlock("crimson_stone_bricks_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.CRIMSON_STONE_BRICKS.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> POLISHED_CRIMSON_STONE_STAIRS = registerBlock("polished_crimson_stone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.POLISHED_CRIMSON_STONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> UNDER_WORLD_STONE_STAIRS = registerBlock("under_world_stone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.UNDER_WORLD_STONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> UNDER_WORLD_COBBLESTONE_STAIRS = registerBlock("under_world_cobblestone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.UNDER_WORLD_COBBLESTONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> UNDER_WORLD_STONE_BRICKS_STAIRS = registerBlock("under_world_stone_bricks_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.UNDER_WORLD_STONE_BRICKS.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> POLISHED_UNDER_WORLD_STONE_STAIRS = registerBlock("polished_under_world_stone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.POLISHED_UNDER_WORLD_STONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CHARSTONE_STONE_STAIRS = registerBlock("charstone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.CHARSTONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CHARSTONE_COBBLESTONE_STAIRS = registerBlock("charstone_cobblestone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.CHARSTONE_COBBLESTONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CHARSTONE_STONE_BRICKS_STAIRS = registerBlock("charstone_bricks_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.CHARSTONE_BRICKS.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> POLISHED_CHARSTONE_STONE_STAIRS = registerBlock("polished_charstone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.POLISHED_CHARSTONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CRIMSON_SAND_STONE_STAIRS = registerBlock("crimson_sand_stone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.CRIMSON_SAND_STONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.SANDSTONE_STAIRS).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> SMOOTH_CRIMSON_SAND_STONE_STAIRS = registerBlock("smooth_crimson_sand_stone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.SMOOTH_CRIMSON_SAND_STONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.SMOOTH_SANDSTONE_STAIRS).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> FAIRY_SAND_STONE_STAIRS = registerBlock("fairy_sand_stone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.FAIRY_SAND_STONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.SANDSTONE_STAIRS).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> SMOOTH_FAIRY_SAND_STONE_STAIRS = registerBlock("smooth_fairy_sand_stone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.SMOOTH_FAIRY_SAND_STONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.SMOOTH_SANDSTONE_STAIRS).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> PIXIE_SAND_STONE_STAIRS = registerBlock("pixie_sand_stone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.PIXIE_SAND_STONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.SANDSTONE_STAIRS).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> SMOOTH_PIXIE_SAND_STONE_STAIRS = registerBlock("smooth_pixie_sand_stone_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.SMOOTH_PIXIE_SAND_STONE.get().defaultBlockState(),
					BlockBehaviour.Properties.copy(Blocks.SMOOTH_SANDSTONE_STAIRS).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> EBONY_STAIRS = registerBlock("ebony_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.EBONY_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
			));

	public static final RegistryObject<Block> BLOOD_OAK_STAIRS = registerBlock("blood_oak_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.BLOOD_OAK_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
			));

	public static final RegistryObject<Block> HELL_BARK_STAIRS = registerBlock("hell_bark_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.HELL_BARK_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
			));

	public static final RegistryObject<Block> WHITE_OAK_STAIRS = registerBlock("white_oak_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.WHITE_OAK_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
			));

	public static final RegistryObject<Block> ALDER_STAIRS = registerBlock("alder_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.ALDER_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
			));

	public static final RegistryObject<Block> WITCH_HAZEL_STAIRS = registerBlock("witch_hazel_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.WITCH_HAZEL_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
			));

	public static final RegistryObject<Block> WILLOW_STAIRS = registerBlock("willow_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.WILLOW_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
			));

	public static final RegistryObject<Block> HAWTHORN_STAIRS = registerBlock("hawthorn_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.HAWTHORN_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
			));

	public static final RegistryObject<Block> CEDAR_STAIRS = registerBlock("cedar_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.CEDAR_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
			));

	public static final RegistryObject<Block> DISTORTED_STAIRS = registerBlock("distorted_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.DISTORTED_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
			));

	public static final RegistryObject<Block> ELDER_STAIRS = registerBlock("elder_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.ELDER_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
			));

	public static final RegistryObject<Block> JUNIPER_STAIRS = registerBlock("juniper_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.DISTORTED_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
			));

	public static final RegistryObject<Block> ROWAN_STAIRS = registerBlock("rowan_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.ROWAN_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
			));

	public static final RegistryObject<Block> TWISTED_STAIRS = registerBlock("twisted_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.TWISTED_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
			));

	public static final RegistryObject<Block> WITCH_WOOD_STAIRS = registerBlock("witch_wood_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.WITCH_WOOD_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
			));

	public static final RegistryObject<Block> ECHO_WOOD_STAIRS = registerBlock("echo_wood_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.ECHO_WOOD_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
			));

	public static final RegistryObject<Block> PHOENIX_STAIRS = registerBlock("phoenix_stairs",
			() -> new StairBlock(() -> HexcraftBlocks.PHOENIX_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
			));

	//Pressure Plates
	public static final RegistryObject<Block> PEARL_STONE_PRESSURE_PLATE = registerBlock("pearl_stone_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS,BlockBehaviour.Properties.copy(Blocks.STONE_PRESSURE_PLATE).mapColor(MapColor.STONE).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM)
					.requiresCorrectToolForDrops().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY), BlockSetType.STONE));

	public static final RegistryObject<Block> CRIMSON_STONE_PRESSURE_PLATE = registerBlock("crimson_stone_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS,BlockBehaviour.Properties.copy(Blocks.STONE_PRESSURE_PLATE).mapColor(MapColor.STONE).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM)
					.requiresCorrectToolForDrops().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY), BlockSetType.STONE));

	public static final RegistryObject<Block> UNDER_WORLD_STONE_PRESSURE_PLATE = registerBlock("under_world_stone_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS,BlockBehaviour.Properties.copy(Blocks.STONE_PRESSURE_PLATE).mapColor(MapColor.STONE).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM)
					.requiresCorrectToolForDrops().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY), BlockSetType.STONE));

	public static final RegistryObject<Block> CHARSTONE_PRESSURE_PLATE = registerBlock("charstone_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS,BlockBehaviour.Properties.copy(Blocks.STONE_PRESSURE_PLATE).mapColor(MapColor.STONE).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM)
					.requiresCorrectToolForDrops().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY), BlockSetType.STONE));

	public static final RegistryObject<Block> EBONY_PRESSURE_PLATE = registerBlock("ebony_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
					.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK
			));

	public static final RegistryObject<Block> BLOOD_OAK_PRESSURE_PLATE = registerBlock("blood_oak_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
					.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK
			));

	public static final RegistryObject<Block> HELL_BARK_PRESSURE_PLATE = registerBlock("hell_bark_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
					.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK
			));

	public static final RegistryObject<Block> WHITE_OAK_PRESSURE_PLATE = registerBlock("white_oak_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
					.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK
			));

	public static final RegistryObject<Block> ALDER_PRESSURE_PLATE = registerBlock("alder_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
					.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK
			));

	public static final RegistryObject<Block> WITCH_HAZEL_PRESSURE_PLATE = registerBlock("witch_hazel_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
					.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK
			));

	public static final RegistryObject<Block> WILLOW_PRESSURE_PLATE = registerBlock("willow_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
					.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK
			));

	public static final RegistryObject<Block> HAWTHORN_PRESSURE_PLATE = registerBlock("hawthorn_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
					.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK
			));

	public static final RegistryObject<Block> CEDAR_PRESSURE_PLATE = registerBlock("cedar_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
					.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK
			));

	public static final RegistryObject<Block> DISTORTED_PRESSURE_PLATE = registerBlock("distorted_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
					.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK
			));

	public static final RegistryObject<Block> ELDER_PRESSURE_PLATE = registerBlock("elder_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
					.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK
			));

	public static final RegistryObject<Block> JUNIPER_PRESSURE_PLATE = registerBlock("juniper_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
					.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK
			));

	public static final RegistryObject<Block> ROWAN_PRESSURE_PLATE = registerBlock("rowan_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
					.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK
			));

	public static final RegistryObject<Block> TWISTED_PRESSURE_PLATE = registerBlock("twisted_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
					.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK
			));

	public static final RegistryObject<Block> WITCH_WOOD_PRESSURE_PLATE = registerBlock("witch_wood_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
					.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK
			));

	public static final RegistryObject<Block> ECHO_WOOD_PRESSURE_PLATE = registerBlock("echo_wood_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
					.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK
			));

	public static final RegistryObject<Block> PHOENIX_PRESSURE_PLATE = registerBlock("phoenix_pressure_plate",
			() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
					.mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.OAK
			));

	//Buttons
	public static final RegistryObject<Block> PEARL_STONE_BUTTON = registerBlock("pearl_stone_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.STONE, 20, false
			));

	public static final RegistryObject<Block> CRIMSON_STONE_BUTTON = registerBlock("crimson_stone_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.STONE, 20, false
			));

	public static final RegistryObject<Block> UNDER_WORLD_STONE_BUTTON = registerBlock("under_world_stone_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.STONE, 20, false
			));

	public static final RegistryObject<Block> CHARSTONE_BUTTON = registerBlock("charstone_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.STONE, 20, false
			));

	public static final RegistryObject<Block> EBONY_BUTTON = registerBlock("ebony_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 10, true
			));

	public static final RegistryObject<Block> BLOOD_OAK_BUTTON = registerBlock("blood_oak_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 10, true
			));

	public static final RegistryObject<Block> HELL_BARK_BUTTON = registerBlock("hell_bark_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 10, true
			));

	public static final RegistryObject<Block> WHITE_OAK_BUTTON = registerBlock("white_oak_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 10, true
			));

	public static final RegistryObject<Block> ALDER_BUTTON = registerBlock("alder_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 10, true
			));

	public static final RegistryObject<Block> WITCH_HAZEL_BUTTON = registerBlock("witch_hazel_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 10, true
			));

	public static final RegistryObject<Block> WILLOW_BUTTON = registerBlock("willow_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 10, true
			));

	public static final RegistryObject<Block> HAWTHORN_BUTTON = registerBlock("hawthorn_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 10, true
			));

	public static final RegistryObject<Block> CEDAR_BUTTON = registerBlock("cedar_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 10, true
			));

	public static final RegistryObject<Block> DISTORTED_BUTTON = registerBlock("distorted_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 10, true
			));

	public static final RegistryObject<Block> ELDER_BUTTON = registerBlock("elder_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 10, true
			));

	public static final RegistryObject<Block> JUNIPER_BUTTON = registerBlock("juniper_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 10, true
			));

	public static final RegistryObject<Block> ROWAN_BUTTON = registerBlock("rowan_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 10, true
			));

	public static final RegistryObject<Block> TWISTED_BUTTON = registerBlock("twisted_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 10, true
			));

	public static final RegistryObject<Block> WITCH_WOOD_BUTTON = registerBlock("witch_wood_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 10, true
			));

	public static final RegistryObject<Block> ECHO_WOOD_BUTTON = registerBlock("echo_wood_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 10, true
			));

	public static final RegistryObject<Block> PHOENIX_BUTTON = registerBlock("phoenix_button",
			() -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).noCollission().strength(0.5F)
					.pushReaction(PushReaction.DESTROY), BlockSetType.OAK, 10, true
			));

	//Fence
	public static final RegistryObject<Block> EBONY_FENCE = registerBlock("ebony_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
			));

	public static final RegistryObject<Block> BLOOD_OAK_FENCE = registerBlock("blood_oak_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
			));

	public static final RegistryObject<Block> HELL_BARK_FENCE = registerBlock("hell_bark_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
			));

	public static final RegistryObject<Block> WHITE_OAK_FENCE = registerBlock("white_oak_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
			));

	public static final RegistryObject<Block> ALDER_FENCE = registerBlock("alder_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
			));

	public static final RegistryObject<Block> WITCH_HAZEL_FENCE = registerBlock("witch_hazel_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
			));

	public static final RegistryObject<Block> WILLOW_FENCE = registerBlock("willow_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
			));

	public static final RegistryObject<Block> HAWTHORN_FENCE = registerBlock("hawthorn_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
			));

	public static final RegistryObject<Block> CEDAR_FENCE = registerBlock("cedar_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
			));

	public static final RegistryObject<Block> DISTORTED_FENCE = registerBlock("distorted_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
			));

	public static final RegistryObject<Block> ELDER_FENCE = registerBlock("elder_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
			));

	public static final RegistryObject<Block> JUNIPER_FENCE = registerBlock("juniper_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
			));

	public static final RegistryObject<Block> ROWAN_FENCE = registerBlock("rowan_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
			));

	public static final RegistryObject<Block> TWISTED_FENCE = registerBlock("twisted_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
			));

	public static final RegistryObject<Block> WITCH_WOOD_FENCE = registerBlock("witch_wood_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
			));

	public static final RegistryObject<Block> ECHO_WOOD_FENCE = registerBlock("echo_wood_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
			));

	public static final RegistryObject<Block> PHOENIX_FENCE = registerBlock("phoenix_fence",
			() -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
			));


	//Fence Gates
	public static final RegistryObject<Block> EBONY_FENCE_GATE = registerBlock("ebony_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
			));

	public static final RegistryObject<Block> BLOOD_OAK_FENCE_GATE = registerBlock("blood_oak_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
			));

	public static final RegistryObject<Block> HELL_BARK_FENCE_GATE = registerBlock("hell_bark_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
			));

	public static final RegistryObject<Block> WHITE_OAK_FENCE_GATE = registerBlock("white_oak_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
			));

	public static final RegistryObject<Block> ALDER_FENCE_GATE = registerBlock("alder_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
			));

	public static final RegistryObject<Block> WITCH_HAZEL_FENCE_GATE = registerBlock("witch_hazel_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
			));

	public static final RegistryObject<Block> WILLOW_FENCE_GATE = registerBlock("willow_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
			));

	public static final RegistryObject<Block> HAWTHORN_FENCE_GATE = registerBlock("hawthorn_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
			));

	public static final RegistryObject<Block> CEDAR_FENCE_GATE = registerBlock("cedar_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
			));

	public static final RegistryObject<Block> DISTORTED_FENCE_GATE = registerBlock("distorted_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
			));

	public static final RegistryObject<Block> ELDER_FENCE_GATE = registerBlock("elder_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
			));

	public static final RegistryObject<Block> JUNIPER_FENCE_GATE = registerBlock("juniper_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
			));

	public static final RegistryObject<Block> ROWAN_FENCE_GATE = registerBlock("rowan_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
			));

	public static final RegistryObject<Block> TWISTED_FENCE_GATE = registerBlock("twisted_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
			));

	public static final RegistryObject<Block> WITCH_WOOD_FENCE_GATE = registerBlock("witch_wood_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
			));

	public static final RegistryObject<Block> ECHO_WOOD_FENCE_GATE = registerBlock("echo_wood_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
			));

	public static final RegistryObject<Block> PHOENIX_FENCE_GATE = registerBlock("phoenix_fence_gate",
			() -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE
			));


	//Doors
	public static final RegistryObject<Block> EBONY_DOOR = registerBlock("ebony_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> BLOOD_OAK_DOOR = registerBlock("blood_oak_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> HELL_BARK_DOOR = registerBlock("hell_bark_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> WHITE_OAK_DOOR = registerBlock("white_oak_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> ALDER_DOOR = registerBlock("alder_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> WITCH_HAZEL_DOOR = registerBlock("witch_hazel_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> WILLOW_DOOR = registerBlock("willow_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> HAWTHORN_DOOR = registerBlock("hawthorn_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> CEDAR_DOOR = registerBlock("cedar_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> DISTORTED_DOOR = registerBlock("distorted_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> ELDER_DOOR = registerBlock("elder_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> JUNIPER_DOOR = registerBlock("juniper_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> ROWAN_DOOR = registerBlock("rowan_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> TWISTED_DOOR = registerBlock("twisted_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> WITCH_WOOD_DOOR = registerBlock("witch_wood_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> ECHO_WOOD_DOOR = registerBlock("echo_wood_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> PHOENIX_DOOR = registerBlock("phoenix_door",
			() -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK
			));

	//Trapdoor
	public static final RegistryObject<Block> EBONY_TRAPDOOR = registerBlock("ebony_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> BLOOD_OAK_TRAPDOOR = registerBlock("blood_oak_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> HELL_BARK_TRAPDOOR = registerBlock("hell_bark_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> WHITE_OAK_TRAPDOOR = registerBlock("white_oak_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> ALDER_TRAPDOOR = registerBlock("alder_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> WITCH_HAZEL_TRAPDOOR = registerBlock("witch_hazel_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> WILLOW_TRAPDOOR = registerBlock("willow_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> HAWTHORN_TRAPDOOR = registerBlock("hawthorn_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> CEDAR_TRAPDOOR = registerBlock("cedar_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> DISTORTED_TRAPDOOR = registerBlock("distorted_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> ELDER_TRAPDOOR = registerBlock("elder_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> JUNIPER_TRAPDOOR = registerBlock("juniper_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> ROWAN_TRAPDOOR = registerBlock("rowan_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> TWISTED_TRAPDOOR = registerBlock("twisted_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> WITCH_WOOD_TRAPDOOR = registerBlock("witch_wood_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> ECHO_WOOD_TRAPDOOR = registerBlock("echo_wood_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK
			));

	public static final RegistryObject<Block> PHOENIX_TRAPDOOR = registerBlock("phoenix_trapdoor",
			() -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK
			));

	public static final RegistryObject <Block> AMETHYST_CHIMES = registerBlock("amethyst_chimes",
			() -> new AmethysChimesBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)
					.strength(1.7F).requiresCorrectToolForDrops()
					.sound(SoundType.AMETHYST).noOcclusion()));

	public static final RegistryObject<Block> PANDORAS_BOX = BLOCKS.register("pandoras_box",
			() -> {
				System.out.println("Registering PANDORAS_BOX");
				return new PandorasBox(Block.Properties.copy(Blocks.CRIMSON_NYLIUM).strength(3.0F).sound(SoundType.WOOD));
			});

	public static final RegistryObject<Block> SACRIFICIAL_PILLAR = BLOCKS.register("sacrificial_pillar",
			() -> new SacrificialPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
					.strength(3.5F)
					.requiresCorrectToolForDrops()
					.noOcclusion()));

	public static final RegistryObject<Block> WHITE_WITCH_CANDLE = BLOCKS.register("white_witch_candle",
			() -> new WitchesCandleBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel((state) -> 7)));

	public static final RegistryObject<Block> ORANGE_WITCH_CANDLE = BLOCKS.register("orange_witch_candle",
			() -> new WitchesCandleBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel((state) -> 7)));

	public static final RegistryObject<Block> MAGENTA_WITCH_CANDLE = BLOCKS.register("magenta_witch_candle",
			() -> new WitchesCandleBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel((state) -> 7)));

	public static final RegistryObject<Block> LIGHT_BLUE_WITCH_CANDLE = BLOCKS.register("light_blue_witch_candle",
			() -> new WitchesCandleBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel((state) -> 7)));

	public static final RegistryObject<Block> YELLOW_WITCH_CANDLE = BLOCKS.register("yellow_witch_candle",
			() -> new WitchesCandleBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel((state) -> 7)));

	public static final RegistryObject<Block> LIME_WITCH_CANDLE = BLOCKS.register("lime_witch_candle",
			() -> new WitchesCandleBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel((state) -> 7)));

	public static final RegistryObject<Block> PINK_WITCH_CANDLE = BLOCKS.register("pink_witch_candle",
			() -> new WitchesCandleBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel((state) -> 7)));

	public static final RegistryObject<Block> GRAY_WITCH_CANDLE = BLOCKS.register("gray_witch_candle",
			() -> new WitchesCandleBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel((state) -> 7)));

	public static final RegistryObject<Block> LIGHT_GRAY_WITCH_CANDLE = BLOCKS.register("light_gray_witch_candle",
			() -> new WitchesCandleBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel((state) -> 7)));

	public static final RegistryObject<Block> CYAN_WITCH_CANDLE = BLOCKS.register("cyan_witch_candle",
			() -> new WitchesCandleBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel((state) -> 7)));

	public static final RegistryObject<Block> PURPLE_WITCH_CANDLE = BLOCKS.register("purple_witch_candle",
			() -> new WitchesCandleBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel((state) -> 7)));

	public static final RegistryObject<Block> BLUE_WITCH_CANDLE = BLOCKS.register("blue_witch_candle",
			() -> new WitchesCandleBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel((state) -> 7)));

	public static final RegistryObject<Block> BROWN_WITCH_CANDLE = BLOCKS.register("brown_witch_candle",
			() -> new WitchesCandleBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel((state) -> 7)));

	public static final RegistryObject<Block> GREEN_WITCH_CANDLE = BLOCKS.register("green_witch_candle",
			() -> new WitchesCandleBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel((state) -> 7)));

	public static final RegistryObject<Block> RED_WITCH_CANDLE = BLOCKS.register("red_witch_candle",
			() -> new WitchesCandleBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel((state) -> 7)));

	public static final RegistryObject<Block> BLACK_WITCH_CANDLE = BLOCKS.register("black_witch_candle",
			() -> new WitchesCandleBlock(BlockBehaviour.Properties.copy(Blocks.CANDLE).lightLevel((state) -> 7)));

	public static final RegistryObject <Block> PURE_MAGIC_CRYSTAL = registerBlock("pure_magic_crystal",
			() -> new PureMagicCrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)
					.strength(1.7F).requiresCorrectToolForDrops()
					.sound(SoundType.AMETHYST).noOcclusion()));

	public static final RegistryObject<Block> FAIRY_WARD = BLOCKS.register("fairy_ward",
			() -> new FairyWardBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
					.strength(3.0F).requiresCorrectToolForDrops()
					.sound(SoundType.AMETHYST)
					.lightLevel(state -> 7)));

	public static final RegistryObject<Block> PIXIE_WARD = BLOCKS.register("pixie_ward",
			() -> new PixieWardBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
					.strength(4.0F)
					.sound(SoundType.AMETHYST)
					.lightLevel(state -> 7)));

	public static final RegistryObject <Block> WITCHES_OVEN = registerBlock("witches_oven",
			() -> new WitchesOven(BlockBehaviour.Properties.copy(Blocks.FURNACE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()
					.strength(3.5F).lightLevel(litBlockEmission(13)).noOcclusion().sound(SoundType.ANVIL)));

	public static final RegistryObject<Block> FUME_FUNNEL = BLOCKS.register("fume_funnel",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.ANVIL)
					.strength(3.0F, 6.0F)
					.sound(SoundType.METAL)));

	public static final RegistryObject <Block> WITCHES_CAULDRON = registerBlock("witches_cauldron",
			() -> new WitchesCauldron(BlockBehaviour.Properties.copy(Blocks.CAULDRON).requiresCorrectToolForDrops()
					.strength(3.5F).noOcclusion()));

	public static final RegistryObject<Block> CHALICE = BLOCKS.register("chalice",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.ANVIL).strength(2.0F)));

	public static final RegistryObject<Block> CANDELABRA = BLOCKS.register("candelabra",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.ANVIL).strength(2.5F)));

	public static final RegistryObject<Block> SKULL = BLOCKS.register("skull",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3.0F)));

	public static final RegistryObject<Block> ALTAR_BASE = BLOCKS.register("altar_base", () -> new AltarBaseBlock());

	public static final RegistryObject<Block> ALTAR_TOP = BLOCKS.register("altar_top", () -> new AltarTopBlock());

	//public static final RegistryObject <Block> FAIRY_HOUSE = BLOCKS.register("fairy_house",
			//() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion().dynamicShape()
					//.strength(2.0F).sound(SoundType.WOOD)));

	//public static final RegistryObject <Block> PIXIE_HOUSE = BLOCKS.register("pixie_house",
			//() -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion().dynamicShape()
					//.strength(2.0F).sound(SoundType.WOOD)));

	//public static final RegistryObject<Block> POPPET_TABLE = BLOCKS.register("poppet_table",
			//() -> new PoppetTableBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));

	//Slabs
	public static final RegistryObject<Block> PEARL_STONE_SLAB = registerBlock("pearl_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> PEARL_COBBLESTONE_SLAB = registerBlock("pearl_cobblestone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F)));

	public static final RegistryObject<Block> PEARL_STONE_BRICKS_SLAB = registerBlock("pearl_stone_bricks_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> POLISHED_PEARL_STONE_SLAB = registerBlock("polished_pearl_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CRIMSON_STONE_SLAB = registerBlock("crimson_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CRIMSON_COBBLESTONE_SLAB = registerBlock("crimson_cobblestone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F)));

	public static final RegistryObject<Block> CRIMSON_STONE_BRICKS_SLAB = registerBlock("crimson_stone_bricks_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> POLISHED_CRIMSON_STONE_SLAB = registerBlock("polished_crimson_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> UNDER_WORLD_STONE_SLAB = registerBlock("under_world_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> UNDER_WORLD_COBBLESTONE_SLAB = registerBlock("under_world_cobblestone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F)));

	public static final RegistryObject<Block> UNDER_WORLD_STONE_BRICKS_SLAB = registerBlock("under_world_stone_bricks_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> POLISHED_UNDER_WORLD_STONE_SLAB = registerBlock("polished_under_world_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CHARSTONE_SLAB = registerBlock("charstone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CHARSTONE_COBBLESTONE_SLAB = registerBlock("charstone_cobblestone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F)));

	public static final RegistryObject<Block> CHARSTONE_BRICKS_SLAB = registerBlock("charstone_bricks_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> POLISHED_CHARSTONE_SLAB = registerBlock("polished_charstone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CRIMSON_SAND_STONE_SLAB = registerBlock("crimson_sand_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE_SLAB).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CUT_CRIMSON_SAND_STONE_SLAB = registerBlock("cut_crimson_sand_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE_SLAB).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> SMOOTH_CRIMSON_SAND_STONE_SLAB = registerBlock("smooth_crimson_sand_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_SANDSTONE_SLAB).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> FAIRY_SAND_STONE_SLAB = registerBlock("fairy_sand_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE_SLAB).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CUT_FAIRY_SAND_STONE_SLAB = registerBlock("cut_fairy_sand_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE_SLAB).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> SMOOTH_FAIRY_SAND_STONE_SLAB = registerBlock("smooth_fairy_sand_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_SANDSTONE_SLAB).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> PIXIE_SAND_STONE_SLAB = registerBlock("pixie_sand_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SANDSTONE_SLAB).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> CUT_PIXIE_SAND_STONE_SLAB = registerBlock("cut_pixie_sand_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CUT_SANDSTONE_SLAB).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> SMOOTH_PIXIE_SAND_STONE_SLAB = registerBlock("smooth_pixie_sand_stone_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SMOOTH_SANDSTONE_SLAB).sound(SoundType.STONE).strength(1.5F, 6.0F)));

	public static final RegistryObject<Block> EBONY_SLAB = registerBlock("ebony_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).strength(2.0F, 3.0F)
			));

	public static final RegistryObject<Block> BLOOD_OAK_SLAB = registerBlock("blood_oak_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).strength(2.0F, 3.0F)
			));

	public static final RegistryObject<Block> HELL_BARK_SLAB = registerBlock("hell_bark_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2.0F, 3.0F)
			));

	public static final RegistryObject<Block> WHITE_OAK_SLAB = registerBlock("white_oak_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).strength(2.0F, 3.0F)
			));

	public static final RegistryObject<Block> ALDER_SLAB = registerBlock("alder_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).strength(2.0F, 3.0F)
			));

	public static final RegistryObject<Block> WITCH_HAZEL_SLAB = registerBlock("witch_hazel_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).strength(2.0F, 3.0F)
			));

	public static final RegistryObject<Block> WILLOW_SLAB = registerBlock("willow_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).strength(2.0F, 3.0F)
			));

	public static final RegistryObject<Block> HAWTHORN_SLAB = registerBlock("hawthorn_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).strength(2.0F, 3.0F)
			));

	public static final RegistryObject<Block> CEDAR_SLAB = registerBlock("cedar_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).strength(2.0F, 3.0F)
			));

	public static final RegistryObject<Block> DISTORTED_SLAB = registerBlock("distorted_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).strength(2.0F, 3.0F)
			));

	public static final RegistryObject<Block> ELDER_SLAB = registerBlock("elder_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).strength(2.0F, 3.0F)
			));

	public static final RegistryObject<Block> JUNIPER_SLAB = registerBlock("juniper_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).strength(2.0F, 3.0F)
			));

	public static final RegistryObject<Block> ROWAN_SLAB = registerBlock("rowan_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).strength(2.0F, 3.0F)
			));

	public static final RegistryObject<Block> TWISTED_SLAB = registerBlock("twisted_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).strength(2.0F, 3.0F)
			));

	public static final RegistryObject<Block> WITCH_WOOD_SLAB = registerBlock("witch_wood_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).strength(2.0F, 3.0F)
			));

	public static final RegistryObject<Block> ECHO_WOOD_SLAB = registerBlock("echo_wood_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).strength(2.0F, 3.0F)
			));

	public static final RegistryObject<Block> PHOENIX_SLAB = registerBlock("phoenix_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).strength(2.0F, 3.0F)
			));


	//Ores
	public static final RegistryObject <Block> MOONSTONE_ORE = registerBlock("moonstone_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F).sound(SoundType.STONE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> END_MOONSTONE_ORE = registerBlock("end_moonstone_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F).sound(SoundType.STONE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> NETHER_MOONSTONE_ORE = registerBlock("nether_moonstone_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F).sound(SoundType.NETHER_ORE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> DEEPSLATE_MOONSTONE_ORE = registerBlock("deepslate_moonstone_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> SILVER_ORE = registerBlock("silver_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F).sound(SoundType.STONE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> END_SILVER_ORE = registerBlock("end_silver_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F).sound(SoundType.STONE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> NETHER_SILVER_ORE = registerBlock("nether_silver_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F).sound(SoundType.NETHER_ORE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> VAMPIRIC_ORE = registerBlock("vampiric_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F).sound(SoundType.STONE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> END_VAMPIRIC_ORE = registerBlock("end_vampiric_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F).sound(SoundType.STONE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> NETHER_VAMPIRIC_ORE = registerBlock("nether_vampiric_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F).sound(SoundType.NETHER_ORE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> DEEPSLATE_VAMPIRIC_ORE = registerBlock("deepslate_vampiric_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> NYKIUM_ORE = registerBlock("nykium_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F).sound(SoundType.NETHER_ORE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> TRENOGEN_ORE = registerBlock("trenogen_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F).sound(SoundType.STONE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> DEEPSLATE_TRENOGEN_ORE = registerBlock("deepslate_trenogen_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> JORMUIM_ORE = registerBlock("jormuim_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F).sound(SoundType.STONE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> SOULSTONE_ORE = registerBlock("soulstone_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F).sound(SoundType.STONE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> ABYSSIUM_ORE = registerBlock("abyssium_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F).sound(SoundType.STONE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> ECLIPSIUM_ORE = registerBlock("eclipsium_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F).sound(SoundType.STONE), UniformInt.of(3, 7)));

	public static final RegistryObject <Block> ABYSSAL_COAL_ORE = registerBlock("abyssal_coal_ore",
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F).sound(SoundType.STONE), UniformInt.of(3, 7)));

	//Geodes
	public static final RegistryObject <Block> MAGIC_CRYSTAL_BLOCK = registerBlock("magic_crystal_block",
			() -> new AmethystBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).strength(1.5F).requiresCorrectToolForDrops()
					.sound(SoundType.AMETHYST)));

	public static final RegistryObject <Block> BUDDING_MAGIC_CRYSTAL = registerBlock("budding_magic_crystal",
			() -> new BuddingMagicalCrystalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).randomTicks().strength(1.5F).requiresCorrectToolForDrops().noLootTable()
					.sound(SoundType.AMETHYST)));

	public static final RegistryObject <Block> MAGIC_CRYSTAL_CLUSTER = registerBlock("magic_crystal_cluster",
			() -> new MagicCrystalClusterBlock(7, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).noOcclusion()
					.randomTicks().strength(1.5F).requiresCorrectToolForDrops().randomTicks()
					.sound(SoundType.AMETHYST).lightLevel((state) -> 5)));

	public static final RegistryObject <Block> LARGE_MAGIC_CRYSTAL_BUD = registerBlock("large_magic_crystal_bud",
			() -> new MagicCrystalClusterBlock(7, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).noOcclusion()
					.randomTicks().strength(1.5F).requiresCorrectToolForDrops()
					.sound(SoundType.AMETHYST).lightLevel((state) -> 4)));

	public static final RegistryObject <Block> MEDIUM_MAGIC_CRYSTAL_BUD = registerBlock("medium_magic_crystal_bud",
			() -> new MagicCrystalClusterBlock(7, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).noOcclusion()
					.randomTicks().strength(1.5F).requiresCorrectToolForDrops()
					.sound(SoundType.AMETHYST).lightLevel((state) -> 2).noLootTable()));

	public static final RegistryObject <Block> SMALL_MAGIC_CRYSTAL_BUD = registerBlock("small_magic_crystal_bud",
			() -> new MagicCrystalClusterBlock(7, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).noOcclusion()
					.randomTicks().strength(1.5F).requiresCorrectToolForDrops()
					.sound(SoundType.AMETHYST).lightLevel((state) -> 1).noLootTable()));

	public static final RegistryObject<Block> HELLFIRE = BLOCKS.register("hell_fire",
			() -> new HellfireBlock(BlockBehaviour.Properties.copy(Blocks.FIRE).noLootTable()));

	public static final RegistryObject<Block> UNDERWORLD_PORTAL = BLOCKS.register("underworld_portal",
			() -> new UnderworldPortalBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_PORTAL)
					.noCollission()
					.strength(-1.0F)
					.lightLevel(state -> 11).noLootTable()));


	//Ore Blocks
	public static final RegistryObject <Block> MOONSTONE_BLOCK = registerBlock("moonstone_block",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.LAPIS_BLOCK).requiresCorrectToolForDrops()
					.strength(5.0F, 6.0F).sound(SoundType.METAL)));

	public static final RegistryObject <Block> SILVER_BLOCK = registerBlock("silver_block",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()
					.strength(5.0F, 6.0F).sound(SoundType.METAL)));

	public static final RegistryObject <Block> VAMPIRIC_BLOCK = registerBlock("vampiric_block",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.LAPIS_BLOCK).requiresCorrectToolForDrops()
					.strength(5.0F, 6.0F).sound(SoundType.METAL)));

	public static final RegistryObject <Block> SOULSTONE_BLOCK = registerBlock("soulstone_block",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()
					.strength(5.0F, 6.0F).sound(SoundType.METAL)));

	public static final RegistryObject <Block> ABYSSIUM_BLOCK = registerBlock("abyssium_block",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()
					.strength(5.0F, 6.0F).sound(SoundType.METAL)));

	public static final RegistryObject <Block> ECLIPSIUM_BLOCK = registerBlock("eclipsium_block",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()
					.strength(3.0F, 3.0F).sound(SoundType.METAL)));

	public static final RegistryObject <Block> ABYSSAL_COAL_BLOCK = registerBlock("abyssal_coal_block",
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));

	//Crops
	public static final RegistryObject<Block> MANDRAKE_FLOWER = registerBlockWithoutBlockItem("mandrake_flower",
			() -> new MandrakePlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().randomTicks()));

	public static final RegistryObject<Block> GARLIC_PLANT = registerBlockWithoutBlockItem("garlic_plant",
			() -> new GarlicPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().randomTicks()));

	public static final RegistryObject<Block> VERVAIN_FLOWER = registerBlockWithoutBlockItem("vervain_flower",
			() -> new VervainPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().randomTicks()));

	public static final RegistryObject<Block> WOLFSBANE_FLOWER = registerBlockWithoutBlockItem("wolfsbane_flower",
			() -> new WolfsBanePlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().randomTicks()));

	public static final RegistryObject<Block> IRENIAL_FLOWER = registerBlockWithoutBlockItem("irenial_flower",
			() -> new IrenialPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().randomTicks()));

	public static final RegistryObject<Block> MIRA_FLOWER = registerBlockWithoutBlockItem("mira_flower",
			() -> new MiraPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().randomTicks()));

	public static final RegistryObject<Block> XERIFAE_FLOWER = registerBlockWithoutBlockItem("xerifae_flower",
			() -> new XerifaePlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().randomTicks()));

	public static final RegistryObject<Block> SENIA_FLOWER = registerBlockWithoutBlockItem("senia_flower",
			() -> new SeniaPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().randomTicks()));

	public static final RegistryObject<Block> AERPINE_FLOWER = registerBlockWithoutBlockItem("aerpine_flower",
			() -> new AerpinePlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().randomTicks()));

	public static final RegistryObject<Block> PERENNIA_FLOWER = registerBlockWithoutBlockItem("perennia_flower",
			() -> new PerenniaPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().randomTicks()));

	public static final RegistryObject<Block> BELLADONNA_PLANT = registerBlockWithoutBlockItem("belladonna_plant",
			() -> new BelladonnaPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().randomTicks()));

	public static final RegistryObject<Block> HELLEBORE_PLANT = registerBlockWithoutBlockItem("hellebore_plant",
			() -> new HelleborePlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().randomTicks()));

	public static final RegistryObject<Block> SAGE_PLANT = registerBlockWithoutBlockItem("sage_plant",
			() -> new SagePlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().randomTicks()));

	public static final RegistryObject<Block> WORMWOOD_PLANT = registerBlockWithoutBlockItem("wormwood_plant",
			() -> new WormwoodPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).sound(SoundType.NETHER_WART).noOcclusion().randomTicks()));

	public static final RegistryObject<Block> WATER_ARTICHOKE_PLANT = registerBlockWithoutBlockItem("water_artichoke_plant",
			() -> new WaterArtichokePlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).sound(SoundType.WET_GRASS).noOcclusion().randomTicks()));

	public static final RegistryObject<LiquidBlock> BLOOD_BLOCK = BLOCKS.register("blood_block",
			() -> new LiquidBlock(HexcraftFluids.SOURCE_BLOOD, BlockBehaviour.Properties.copy(Blocks.WATER).noLootTable().noCollission().noLootTable()));

	public static final RegistryObject<LiquidBlock> DEEP_WATER_BLOCK = BLOCKS.register("deep_water_block",
			() -> new DeepWaterBlock(
					(FlowingFluid) HexcraftFluids.SOURCE_DEEP_WATER.get(),
					BlockBehaviour.Properties.copy(Blocks.WATER)
							.noLootTable()
							.noCollission()
			));

	//Coral
	public static final RegistryObject<Block> DEAD_TWILIGHT_CORAL = registerBlock("dead_twilightcoral",
			() -> new BaseCoralPlantBlock(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL).noCollission().instabreak()));

	public static final RegistryObject<Block> TWILIGHT_CORAL = registerBlock("twilightcoral",
			() -> new CoralPlantBlock(HexcraftBlocks.DEAD_TWILIGHT_CORAL.get(),
					(Block.Properties.copy(Blocks.BRAIN_CORAL).noCollission().instabreak())));

	public static final RegistryObject<Block> DEAD_TWILIGHT_CORAL_BLOCK = registerBlock("dead_twilightcoral_block",
			() -> new Block(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_BLOCK)));

	public static final RegistryObject<Block> TWILIGHT_CORAL_BLOCK = registerBlock("twilightcoral_block",
			() -> new CoralBlock(HexcraftBlocks.DEAD_TWILIGHT_CORAL_BLOCK.get(),
					(Block.Properties.copy(Blocks.BRAIN_CORAL_BLOCK))));

	public static final RegistryObject<Block> DEAD_TWILIGHT_CORAL_FAN = registerBlock("dead_twilightcoral_fan",
			() -> new BaseCoralPlantBlock(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_BLOCK).noCollission().instabreak()));

	public static final RegistryObject<Block> TWILIGHT_CORAL_FAN = registerBlock("twilightcoral_fan",
			() -> new CoralFanBlock(HexcraftBlocks.DEAD_TWILIGHT_CORAL_FAN.get(),
					Block.Properties.copy(Blocks.BRAIN_CORAL_FAN).noCollission().instabreak()));

	public static final RegistryObject<Block> DEAD_TWILIGHT_CORAL_WALL_FAN = registerBlock("dead_twilightcoral_wall_fan",
			() -> new BaseCoralWallFanBlock(
					Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_WALL_FAN).noCollission().instabreak()));

	public static final RegistryObject<Block> TWILIGHT_CORAL_WALL_FAN = registerBlock("twilightcoral_wall_fan",
			() -> new CoralWallFanBlock(HexcraftBlocks.DEAD_TWILIGHT_CORAL_WALL_FAN.get(),
					Block.Properties.copy(Blocks.BRAIN_CORAL_WALL_FAN).noCollission().instabreak()));

	public static final RegistryObject<Block> DEAD_SANGUINE_CORAL = registerBlock("dead_sanguine_coral",
			() -> new BaseCoralPlantBlock(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL).noCollission().instabreak()));

	public static final RegistryObject<Block> SANGUINE_CORAL = registerBlock("sanguine_coral",
			() -> new CoralPlantBlock(HexcraftBlocks.DEAD_SANGUINE_CORAL.get(),
					(Block.Properties.copy(Blocks.BRAIN_CORAL).noCollission().instabreak())));

	public static final RegistryObject<Block> DEAD_SANGUINE_CORAL_BLOCK = registerBlock("dead_sanguine_coral_block",
			() -> new Block(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_BLOCK)));

	public static final RegistryObject<Block> SANGUINE_CORAL_BLOCK = registerBlock("sanguine_coral_block",
			() -> new CoralBlock(HexcraftBlocks.DEAD_SANGUINE_CORAL_BLOCK.get(),
					(Block.Properties.copy(Blocks.BRAIN_CORAL_BLOCK))));

	public static final RegistryObject<Block> DEAD_SANGUINE_CORAL_FAN = registerBlock("dead_sanguine_coral_fan",
			() -> new BaseCoralPlantBlock(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_BLOCK).noCollission().instabreak()));

	public static final RegistryObject<Block> SANGUINE_CORAL_FAN = registerBlock("sanguine_coral_fan",
			() -> new CoralFanBlock(HexcraftBlocks.DEAD_SANGUINE_CORAL_FAN.get(),
					Block.Properties.copy(Blocks.BRAIN_CORAL_FAN).noCollission().instabreak()));

	public static final RegistryObject<Block> DEAD_SANGUINE_CORAL_WALL_FAN = registerBlock("dead_sanguine_coral_wall_fan",
			() -> new BaseCoralWallFanBlock(
					Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_WALL_FAN).noCollission().instabreak()));

	public static final RegistryObject<Block> SANGUINE_CORAL_WALL_FAN = registerBlock("sanguine_coral_wall_fan",
			() -> new CoralWallFanBlock(HexcraftBlocks.DEAD_SANGUINE_CORAL_WALL_FAN.get(),
					Block.Properties.copy(Blocks.BRAIN_CORAL_WALL_FAN).noCollission().instabreak()));

	public static final RegistryObject<Block> DEAD_WHISPER_CORAL = registerBlock("dead_whisper_coral",
			() -> new BaseCoralPlantBlock(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL).noCollission().instabreak()));

	public static final RegistryObject<Block> WHISPER_CORAL = registerBlock("whisper_coral",
			() -> new CoralPlantBlock(HexcraftBlocks.DEAD_WHISPER_CORAL.get(),
					(Block.Properties.copy(Blocks.BRAIN_CORAL).noCollission().instabreak())));

	public static final RegistryObject<Block> DEAD_WHISPER_CORAL_BLOCK = registerBlock("dead_whisper_coral_block",
			() -> new Block(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_BLOCK)));

	public static final RegistryObject<Block> WHISPER_CORAL_BLOCK = registerBlock("whisper_coral_block",
			() -> new CoralBlock(HexcraftBlocks.DEAD_WHISPER_CORAL_BLOCK.get(),
					(Block.Properties.copy(Blocks.BRAIN_CORAL_BLOCK))));

	public static final RegistryObject<Block> DEAD_WHISPER_CORAL_FAN = registerBlock("dead_whisper_coral_fan",
			() -> new BaseCoralPlantBlock(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_BLOCK).noCollission().instabreak()));

	public static final RegistryObject<Block> WHISPER_CORAL_FAN = registerBlock("whisper_coral_fan",
			() -> new CoralFanBlock(HexcraftBlocks.DEAD_WHISPER_CORAL_FAN.get(),
					Block.Properties.copy(Blocks.BRAIN_CORAL_FAN).noCollission().instabreak()));

	public static final RegistryObject<Block> DEAD_WHISPER_CORAL_WALL_FAN = registerBlock("dead_whisper_coral_wall_fan",
			() -> new BaseCoralWallFanBlock(
					Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_WALL_FAN).noCollission().instabreak()));

	public static final RegistryObject<Block> WHISPER_CORAL_WALL_FAN = registerBlock("whisper_coral_wall_fan",
			() -> new CoralWallFanBlock(HexcraftBlocks.DEAD_WHISPER_CORAL_WALL_FAN.get(),
					Block.Properties.copy(Blocks.BRAIN_CORAL_WALL_FAN).noCollission().instabreak()));

	public static final RegistryObject<Block> DEAD_EBONFANG_CORAL = registerBlock("dead_ebonfang_coral",
			() -> new BaseCoralPlantBlock(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL).noCollission().instabreak()));

	public static final RegistryObject<Block> EBONFANG_CORAL = registerBlock("ebonfang_coral",
			() -> new CoralPlantBlock(HexcraftBlocks.DEAD_EBONFANG_CORAL.get(),
					(Block.Properties.copy(Blocks.BRAIN_CORAL).noCollission().instabreak())));

	public static final RegistryObject<Block> DEAD_EBONFANG_CORAL_BLOCK = registerBlock("dead_ebonfang_coral_block",
			() -> new Block(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_BLOCK)));

	public static final RegistryObject<Block> EBONFANG_CORAL_BLOCK = registerBlock("ebonfang_coral_block",
			() -> new CoralBlock(HexcraftBlocks.DEAD_EBONFANG_CORAL_BLOCK.get(),
					(Block.Properties.copy(Blocks.BRAIN_CORAL_BLOCK))));

	public static final RegistryObject<Block> DEAD_EBONFANG_CORAL_FAN = registerBlock("dead_ebonfang_coral_fan",
			() -> new BaseCoralPlantBlock(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_BLOCK).noCollission().instabreak()));

	public static final RegistryObject<Block> EBONFANG_CORAL_FAN = registerBlock("ebonfang_coral_fan",
			() -> new CoralFanBlock(HexcraftBlocks.DEAD_EBONFANG_CORAL_FAN.get(),
					Block.Properties.copy(Blocks.BRAIN_CORAL_FAN).noCollission().instabreak()));

	public static final RegistryObject<Block> DEAD_EBONFANG_CORAL_WALL_FAN = registerBlock("dead_ebonfang_coral_wall_fan",
			() -> new BaseCoralWallFanBlock(
					Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_WALL_FAN).noCollission().instabreak()));

	public static final RegistryObject<Block> EBONFANG_CORAL_WALL_FAN = registerBlock("ebonfang_coral_wall_fan",
			() -> new CoralWallFanBlock(HexcraftBlocks.DEAD_EBONFANG_CORAL_WALL_FAN.get(),
					Block.Properties.copy(Blocks.BRAIN_CORAL_WALL_FAN).noCollission().instabreak()));

	public static final RegistryObject<Block> DEAD_SPECTRAL_BLOOM_CORAL = registerBlock("dead_spectral_bloom_coral",
			() -> new BaseCoralPlantBlock(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL).noCollission().instabreak()));

	public static final RegistryObject<Block> SPECTRAL_BLOOM_CORAL = registerBlock("spectral_bloom_coral",
			() -> new CoralPlantBlock(HexcraftBlocks.DEAD_SPECTRAL_BLOOM_CORAL.get(),
					(Block.Properties.copy(Blocks.BRAIN_CORAL).noCollission().instabreak())));

	public static final RegistryObject<Block> DEAD_SPECTRAL_BLOOM_CORAL_BLOCK = registerBlock("dead_spectral_bloom_coral_block",
			() -> new Block(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_BLOCK)));

	public static final RegistryObject<Block> SPECTRAL_BLOOM_CORAL_BLOCK = registerBlock("spectral_bloom_coral_block",
			() -> new CoralBlock(HexcraftBlocks.DEAD_SPECTRAL_BLOOM_CORAL_BLOCK.get(),
					(Block.Properties.copy(Blocks.BRAIN_CORAL_BLOCK))));

	public static final RegistryObject<Block> DEAD_SPECTRAL_BLOOM_CORAL_FAN = registerBlock("dead_spectral_bloom_coral_fan",
			() -> new BaseCoralPlantBlock(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_BLOCK).noCollission().instabreak()));

	public static final RegistryObject<Block> SPECTRAL_BLOOM_CORAL_FAN = registerBlock("spectral_bloom_coral_fan",
			() -> new CoralFanBlock(HexcraftBlocks.DEAD_SPECTRAL_BLOOM_CORAL_FAN.get(),
					Block.Properties.copy(Blocks.BRAIN_CORAL_FAN).noCollission().instabreak()));

	public static final RegistryObject<Block> DEAD_SPECTRAL_BLOOM_CORAL_WALL_FAN = registerBlock("dead_spectral_bloom_coral_wall_fan",
			() -> new BaseCoralWallFanBlock(
					Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_WALL_FAN).noCollission().instabreak()));

	public static final RegistryObject<Block> SPECTRAL_BLOOM_CORAL_WALL_FAN = registerBlock("spectral_bloom_coral_wall_fan",
			() -> new CoralWallFanBlock(HexcraftBlocks.DEAD_SPECTRAL_BLOOM_CORAL_WALL_FAN.get(),
					Block.Properties.copy(Blocks.BRAIN_CORAL_WALL_FAN).noCollission().instabreak()));

	public static final RegistryObject<Block> DEAD_HELLVINE_CORAL = registerBlock("dead_hellvine_coral",
			() -> new BaseCoralPlantBlock(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL).noCollission().instabreak()));

	public static final RegistryObject<Block> HELLVINE_CORAL = registerBlock("hellvine_coral",
			() -> new CoralPlantBlock(HexcraftBlocks.DEAD_HELLVINE_CORAL.get(),
					(Block.Properties.copy(Blocks.BRAIN_CORAL).noCollission().instabreak())));

	public static final RegistryObject<Block> DEAD_HELLVINE_CORAL_BLOCK = registerBlock("dead_hellvine_coral_block",
			() -> new Block(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_BLOCK)));
	public static final RegistryObject<Block> HELLVINE_CORAL_BLOCK = registerBlock("hellvine_coral_block",
			() -> new CoralBlock(HexcraftBlocks.DEAD_HELLVINE_CORAL_BLOCK.get(),
					(Block.Properties.copy(Blocks.BRAIN_CORAL_BLOCK))));

	public static final RegistryObject<Block> DEAD_HELLVINE_CORAL_FAN = registerBlock("dead_hellvine_coral_fan",
			() -> new BaseCoralPlantBlock(Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_BLOCK).noCollission().instabreak()));

	public static final RegistryObject<Block> HELLVINE_CORAL_FAN = registerBlock("hellvine_coral_fan",
			() -> new CoralFanBlock(HexcraftBlocks.DEAD_HELLVINE_CORAL_FAN.get(),
					Block.Properties.copy(Blocks.BRAIN_CORAL_FAN).noCollission().instabreak()));

	public static final RegistryObject<Block> DEAD_HELLVINE_CORAL_WALL_FAN = registerBlock("dead_hellvine_coral_wall_fan",
			() -> new BaseCoralWallFanBlock(
					Block.Properties.copy(Blocks.DEAD_BRAIN_CORAL_WALL_FAN).noCollission().instabreak()));
	public static final RegistryObject<Block> HELLVINE_CORAL_WALL_FAN = registerBlock("hellvine_coral_wall_fan",
			() -> new CoralWallFanBlock(HexcraftBlocks.DEAD_HELLVINE_CORAL_WALL_FAN.get(),
					Block.Properties.copy(Blocks.BRAIN_CORAL_WALL_FAN).noCollission().instabreak()));

	public static final RegistryObject<Block> SOULSTEM_CANDLE = registerBlock("soulstem_candle", () -> new SoulstemCandlesBlock(
			Block.Properties.copy(Blocks.CANDLE).strength(0.3f, 0.2f).noOcclusion().strength(0.1F).sound(SoundType.CANDLE)
					.lightLevel(CandleBlock.LIGHT_EMISSION)));

	//Chests

	public static final RegistryObject<HexcraftChestBlock> ALDER_CHEST = BLOCKS.register("alder_chest",
			() -> new HexcraftChestBlock(BlockBehaviour.Properties.copy(ALDER_PLANKS.get()).strength(2.5f)));

	public static final RegistryObject<HexcraftChestBlock> BLOOD_OAK_CHEST = BLOCKS.register("blood_oak_chest",
			() -> new HexcraftChestBlock(BlockBehaviour.Properties.copy(BLOOD_OAK_PLANKS.get()).strength(2.5f)));

	public static final RegistryObject<HexcraftChestBlock> CEDAR_CHEST = BLOCKS.register("cedar_chest",
			() -> new HexcraftChestBlock(BlockBehaviour.Properties.copy(CEDAR_PLANKS.get()).strength(2.5f)));

	public static final RegistryObject<HexcraftChestBlock> DISTORTED_CHEST = BLOCKS.register("distorted_chest",
			() -> new HexcraftChestBlock(BlockBehaviour.Properties.copy(DISTORTED_PLANKS.get()).strength(2.5f)));

	public static final RegistryObject<HexcraftChestBlock> EBONY_CHEST = BLOCKS.register("ebony_chest",
			() -> new HexcraftChestBlock(BlockBehaviour.Properties.copy(EBONY_PLANKS.get()).strength(2.5f)));

	public static final RegistryObject<HexcraftChestBlock> ECHO_WOOD_CHEST = BLOCKS.register("echo_wood_chest",
			() -> new HexcraftChestBlock(BlockBehaviour.Properties.copy(ECHO_WOOD_PLANKS.get()).strength(2.5f)));

	public static final RegistryObject<HexcraftChestBlock> ELDER_CHEST = BLOCKS.register("elder_chest",
			() -> new HexcraftChestBlock(BlockBehaviour.Properties.copy(ELDER_PLANKS.get()).strength(2.5f)));

	public static final RegistryObject<HexcraftChestBlock> HAWTHORN_CHEST = BLOCKS.register("hawthorn_chest",
			() -> new HexcraftChestBlock(BlockBehaviour.Properties.copy(HAWTHORN_PLANKS.get()).strength(2.5f)));

	public static final RegistryObject<HexcraftChestBlock> HELL_BARK_CHEST = BLOCKS.register("hell_bark_chest",
			() -> new HexcraftChestBlock(BlockBehaviour.Properties.copy(HELL_BARK_PLANKS.get()).strength(2.5f)));

	public static final RegistryObject<HexcraftChestBlock> JUNIPER_CHEST = BLOCKS.register("juniper_chest",
			() -> new HexcraftChestBlock(BlockBehaviour.Properties.copy(JUNIPER_PLANKS.get()).strength(2.5f)));

	public static final RegistryObject<HexcraftChestBlock> ROWAN_CHEST = BLOCKS.register("rowan_chest",
			() -> new HexcraftChestBlock(BlockBehaviour.Properties.copy(ROWAN_PLANKS.get()).strength(2.5f)));

	public static final RegistryObject<HexcraftChestBlock> TWISTED_CHEST = BLOCKS.register("twisted_chest",
			() -> new HexcraftChestBlock(BlockBehaviour.Properties.copy(TWISTED_PLANKS.get()).strength(2.5f)));

	public static final RegistryObject<HexcraftChestBlock> WHITE_OAK_CHEST = BLOCKS.register("white_oak_chest",
			() -> new HexcraftChestBlock(BlockBehaviour.Properties.copy(WHITE_OAK_PLANKS.get()).strength(2.5f)));

	public static final RegistryObject<HexcraftChestBlock> WILLOW_CHEST = BLOCKS.register("willow_chest",
			() -> new HexcraftChestBlock(BlockBehaviour.Properties.copy(WILLOW_PLANKS.get()).strength(2.5f)));

	public static final RegistryObject<HexcraftChestBlock> WITCH_HAZEL_CHEST = BLOCKS.register("witch_hazel_chest",
			() -> new HexcraftChestBlock(BlockBehaviour.Properties.copy(WITCH_HAZEL_PLANKS.get()).strength(2.5f)));

	public static final RegistryObject<HexcraftChestBlock> WITCH_WOOD_CHEST = BLOCKS.register("witch_wood_chest",
			() -> new HexcraftChestBlock(BlockBehaviour.Properties.copy(WITCH_WOOD_PLANKS.get()).strength(2.5f)));

	public static final RegistryObject<HexcraftChestBlock> PHOENIX_CHEST = BLOCKS.register("phoenix_chest",
			() -> new HexcraftChestBlock(BlockBehaviour.Properties.copy(WITCH_WOOD_PLANKS.get()).strength(2.5f)));

	private static RegistryObject<Block> registerChest(String name) {
		return BLOCKS.register(name, () -> new HexcraftChestBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.5f)));

	}

	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}

	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
		return HexcraftItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	}

	private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
		return BLOCKS.register(name, block);
	}

	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}