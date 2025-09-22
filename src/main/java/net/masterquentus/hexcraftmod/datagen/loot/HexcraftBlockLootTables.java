package net.masterquentus.hexcraftmod.datagen.loot;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.block.custom.plants.AerpinePlantBlock;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.function.Supplier;


public class HexcraftBlockLootTables extends BlockLootSubProvider {
	protected static final LootItemCondition.Builder HAS_SHEARS;
	public HexcraftBlockLootTables() {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags());
	}

	public static final float[] NORMAL_LEAVES_SAPLING_CHANCES = {0.05F};  // 5% chance

	@Override
	protected void generate() {
		this.dropSelf(HexcraftBlocks.MOONSTONE_BLOCK.get());

		this.dropSelf(HexcraftBlocks.ALTAR_BASE.get());

		this.dropSelf(HexcraftBlocks.CHALICE.get());

		this.dropSelf(HexcraftBlocks.CANDELABRA.get());

		this.dropSelf(HexcraftBlocks.SKULL.get());

		this.dropSelf(HexcraftBlocks.SILVER_BLOCK.get());

		this.dropSelf(HexcraftBlocks.VAMPIRIC_BLOCK.get());

		this.dropSelf(HexcraftBlocks.UMBRACITE_BLOCK.get());

		this.dropSelf(HexcraftBlocks.UMBRACITE_CHAIN.get());

		this.dropSelf(HexcraftBlocks.REINFORCED_UMBRACITE_CHAIN_BLOCK.get());

		this.dropSelf(HexcraftBlocks.UMBRACITE_BARS.get());

		this.dropSelf(HexcraftBlocks.SOULSTONE_BLOCK.get());

		this.dropSelf(HexcraftBlocks.ABYSSIUM_BLOCK.get());

		this.dropSelf(HexcraftBlocks.ECLIPSIUM_BLOCK.get());

		this.dropSelf(HexcraftBlocks.OBSIDROCK.get());

		this.dropSelf(HexcraftBlocks.BLACK_OBSIDIAN.get());

		this.dropSelf(HexcraftBlocks.AMETHYST_CHIMES.get());

		this.dropSelf(HexcraftBlocks.PURE_MAGIC_CRYSTAL.get());

		this.dropSelf(HexcraftBlocks.FAIRY_WARD.get());

		this.dropSelf(HexcraftBlocks.PIXIE_WARD.get());

		this.dropSelf(HexcraftBlocks.WITCHES_OVEN.get());

		this.dropSelf(HexcraftBlocks.FUME_FUNNEL.get());

		this.dropSelf(HexcraftBlocks.WITCHES_CAULDRON.get());

		//this.dropSelf(HexcraftBlocks.FAIRY_HOUSE.get());

		//this.dropSelf(HexcraftBlocks.PIXIE_HOUSE.get());

		//this.dropSelf(HexcraftBlocks.POPPET_TABLE.get());

		this.dropSelf(HexcraftBlocks.CURSED_SOIL.get());

		this.dropSelf(HexcraftBlocks.CURED_SOIL.get());

		this.dropSelf(HexcraftBlocks.FERTILIZED_DIRT.get());

		this.add(HexcraftBlocks.SCORCHFIRE_GRASS_BLOCK.get(), block -> createSilkTouchDispatchTable(
				block,
				LootItem.lootTableItem(HexcraftBlocks.SCORCHFIRE_DIRT.get())
		));

		this.add(HexcraftBlocks.SANGUINE_LANTERN.get(), block -> createSilkTouchDispatchTable(
				block,
				LootItem.lootTableItem(HexcraftItems.BLOODSHALE_SHARDS.get())
		));

		this.dropSelf(HexcraftBlocks.SCORCHFIRE_DIRT.get());


		add(HexcraftBlocks.SCORCHFIRE_GRASS.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(HexcraftBlocks.SCORCHFIRE_GRASS.get()))));


		this.add(HexcraftBlocks.UMBRAL_BLOOM_GRASS_BLOCK.get(), block -> createSilkTouchDispatchTable(
				block,
				LootItem.lootTableItem(HexcraftBlocks.UMBRAL_BLOOM_DIRT.get())
		));

// Dirt blocks (drop self)
		this.dropSelf(HexcraftBlocks.BRAMBLE_DIRT.get());
		this.dropSelf(HexcraftBlocks.VEILSHADE_DIRT.get());
		this.dropSelf(HexcraftBlocks.TAINTED_DIRT.get());
		this.dropSelf(HexcraftBlocks.STONELOAM_DIRT.get());
		this.dropSelf(HexcraftBlocks.CINDERWEED_DIRT.get());
		this.dropSelf(HexcraftBlocks.SEETHING_RED_DIRT.get());
		this.dropSelf(HexcraftBlocks.FLAREWORN_DIRT.get());
		this.dropSelf(HexcraftBlocks.BILEWEED_DIRT.get());
		this.dropSelf(HexcraftBlocks.WHISPERBLOOM_DIRT.get());
		this.dropSelf(HexcraftBlocks.TWILIGHTLOAM_DIRT.get());
		this.dropSelf(HexcraftBlocks.ROTROOT_DIRT.get());
		this.dropSelf(HexcraftBlocks.HUSKWEED_DIRT.get());
		this.dropSelf(HexcraftBlocks.SPIRITWEED_DIRT.get());
		this.dropSelf(HexcraftBlocks.DUSKDIRT.get());
		this.dropSelf(HexcraftBlocks.NIGHTVEIN_DIRT.get());

// Grass/Soil blocks (silk touch or shears)
		add(HexcraftBlocks.BRAMBLE_GRASS_BLOCK.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(block))));
		add(HexcraftBlocks.VEILSHADE_SOIL.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(block))));
		add(HexcraftBlocks.TAINTED_SOIL.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(block))));
		add(HexcraftBlocks.STONELOAM_SOIL.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(block))));
		add(HexcraftBlocks.CINDERWEED_SOIL.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(block))));
		add(HexcraftBlocks.FESTERING_GRASS_BLOCK.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(block))));
		add(HexcraftBlocks.FLAREWORN_GRASS_BLOCK.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(block))));
		add(HexcraftBlocks.BILEWEED_GRASS_BLOCK.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(block))));
		add(HexcraftBlocks.WHISPERBLOOM_SOIL.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(block))));
		add(HexcraftBlocks.TWILIGHTLOAM_GRASS_BLOCK.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(block))));
		add(HexcraftBlocks.ROTROOT_GRASS_BLOCK.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(block))));
		add(HexcraftBlocks.HUSKWEED_GRASS_BLOCK.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(block))));
		add(HexcraftBlocks.SPIRITWEED_GRASS_BLOCK.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(block))));
		add(HexcraftBlocks.DUSK_GRASS_BLOCK.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(block))));
		add(HexcraftBlocks.NIGHTVEIN_GRASS_BLOCK.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(block))));

		this.dropSelf(HexcraftBlocks.LUMENBLOOM_CLUSTER.get());

		this.dropSelf(HexcraftBlocks.UMBRAL_BLOOM_DIRT.get());

		this.dropSelf(HexcraftBlocks.SEETHING_COALBED.get());


		add(HexcraftBlocks.UMBRAL_BLOOM_GRASS.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(HexcraftBlocks.UMBRAL_BLOOM_GRASS.get()))));


		this.add(HexcraftBlocks.VILE_GRASS_BLOCK.get(), block -> createSilkTouchDispatchTable(
				block,
				LootItem.lootTableItem(HexcraftBlocks.VILE_DIRT.get())
		));

		this.dropSelf(HexcraftBlocks.VILE_DIRT.get());

		add(HexcraftBlocks.VILE_GRASS.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(HexcraftBlocks.VILE_GRASS.get()))));

		add(HexcraftBlocks.TWILIGHT_MOSSGRASS.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(HexcraftBlocks.TWILIGHT_MOSSGRASS.get()))));

		this.dropSelf(HexcraftBlocks.GLOOMROOT_SOIL.get());

		this.dropSelf(HexcraftBlocks.GLIMMER_CAP.get());

		this.dropSelf(HexcraftBlocks.SHARDSTONE.get());

		this.dropSelf(HexcraftBlocks.LUMICLAST.get());

		this.dropSelf(HexcraftBlocks.VAMPIRE_ORCHID.get());

		this.add(HexcraftBlocks.POTTED_VAMPIRE_ORCHID.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_VAMPIRE_ORCHID.get()));

		this.dropSelf(HexcraftBlocks.WISPY_COTTON.get());

		this.add(HexcraftBlocks.POTTED_WISPY_COTTON.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_WISPY_COTTON.get()));

		this.dropSelf(HexcraftBlocks.SOUL_FLOWER.get());

		this.add(HexcraftBlocks.POTTED_SOUL_FLOWER.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_SOUL_FLOWER.get()));

		this.dropSelf(HexcraftBlocks.BLOODY_ROSE.get());

		this.add(HexcraftBlocks.POTTED_BLOODY_ROSE.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_BLOODY_ROSE.get()));

		this.dropSelf(HexcraftBlocks.BLOOD_MUSHROOM.get());

		this.add(HexcraftBlocks.POTTED_BLOOD_MUSHROOM.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_BLOOD_MUSHROOM.get()));

		this.dropSelf(HexcraftBlocks.DUSKROOT_LANTERN.get());

		this.add(HexcraftBlocks.POTTED_DUSKROOT_LANTERN.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_DUSKROOT_LANTERN.get()));

		this.add(HexcraftBlocks.BLISTER_CACTUS_FLOWER.get(),
				block -> createSilkTouchDispatchTable(
						HexcraftBlocks.BLISTER_CACTUS_FLOWER.get(),
						applyExplosionDecay(block, LootItem.lootTableItem(HexcraftItems.BLISTER_CACTUS_FRUIT.get()))));

		this.dropSelf(HexcraftBlocks.VILESHROOM.get());

		this.add(HexcraftBlocks.POTTED_VILESHROOM.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_VILESHROOM.get()));

		this.dropSelf(HexcraftBlocks.GHOSTSHROOM.get());

		this.add(HexcraftBlocks.POTTED_GHOSTSHROOM.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_GHOSTSHROOM.get()));

		this.dropSelf(HexcraftBlocks.WILD_BRAMBLE.get());

		this.dropSelf(HexcraftBlocks.ENDER_BRAMBLE.get());

		this.dropSelf(HexcraftBlocks.CRIMSON_MAGMA.get());

		this.dropSelf(HexcraftBlocks.GLINT_WEED.get());

		this.dropSelf(HexcraftBlocks.SCORCHSTALKS.get());

		this.add(HexcraftBlocks.POTTED_SCORCHASTALKS.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_VAMPIRE_ORCHID.get()));

		add(HexcraftBlocks.SPANISH_MOSS.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(HexcraftBlocks.SPANISH_MOSS.get()))));

		this.dropSelf(HexcraftBlocks.DROWNED_MOSS.get());

		this.dropSelf(HexcraftBlocks.EMBER_MOSS_BLOCK.get());

		this.dropSelf(HexcraftBlocks.EMBER_MOSS_CARPET.get());

		this.dropSelf(HexcraftBlocks.LIVING_KELP_BLOCK.get());

		add(HexcraftBlocks.EMBER_MOSS.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(HexcraftBlocks.EMBER_MOSS.get()))));

		this.dropSelf(HexcraftBlocks.VILEVINE.get());

		this.dropSelf(HexcraftBlocks.BLOODTHORN_VINES.get());

		this.add(HexcraftBlocks.BLISTER_CACTUS.get(),
				block -> LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								.add(LootItem.lootTableItem(HexcraftBlocks.BLISTER_CACTUS.get()))));

		this.add(HexcraftBlocks.GARLIC_STRAND.get(), block ->
				createSilkTouchOrShearsDispatchTable(block,
						applyExplosionCondition(block, LootItem.lootTableItem(HexcraftItems.GARLIC_STRAND_ITEM.get()))));

		this.add(HexcraftBlocks.VERVAIN_STRAND.get(), block ->
				createSilkTouchOrShearsDispatchTable(block,
						applyExplosionCondition(block, LootItem.lootTableItem(HexcraftItems.VERVAIN_STRAND_ITEM.get()))));

		this.dropSelf(HexcraftBlocks.DEMON_HEART.get());

		this.dropSelf(HexcraftBlocks.PANDORAS_BOX.get());

		this.dropSelf(HexcraftBlocks.SACRIFICIAL_PILLAR.get());

		this.dropSelf(HexcraftBlocks.EBONY_LOG.get());

		this.dropSelf(HexcraftBlocks.BLOOD_OAK_LOG.get());

		this.dropSelf(HexcraftBlocks.HELL_BARK_LOG.get());

		this.dropSelf(HexcraftBlocks.WHITE_OAK_LOG.get());

		this.dropSelf(HexcraftBlocks.ALDER_LOG.get());

		this.dropSelf(HexcraftBlocks.WITCH_HAZEL_LOG.get());

		this.dropSelf(HexcraftBlocks.WILLOW_LOG.get());

		this.dropSelf(HexcraftBlocks.HAWTHORN_LOG.get());

		this.dropSelf(HexcraftBlocks.CEDAR_LOG.get());

		this.dropSelf(HexcraftBlocks.DISTORTED_LOG.get());

		this.dropSelf(HexcraftBlocks.ELDER_LOG.get());

		this.dropSelf(HexcraftBlocks.JUNIPER_LOG.get());

		this.dropSelf(HexcraftBlocks.ROWAN_LOG.get());

		this.dropSelf(HexcraftBlocks.TWISTED_LOG.get());

		this.dropSelf(HexcraftBlocks.WITCH_WOOD_LOG.get());

		this.dropSelf(HexcraftBlocks.ECHO_WOOD_LOG.get());

		this.dropSelf(HexcraftBlocks.PHOENIX_LOG.get());

		this.dropSelf(HexcraftBlocks.EBONY_WOOD.get());

		this.dropSelf(HexcraftBlocks.BLOOD_OAK_WOOD.get());

		this.dropSelf(HexcraftBlocks.HELL_BARK_WOOD.get());

		this.dropSelf(HexcraftBlocks.WHITE_OAK_WOOD.get());

		this.dropSelf(HexcraftBlocks.ALDER_WOOD.get());

		this.dropSelf(HexcraftBlocks.WITCH_HAZEL_WOOD.get());

		this.dropSelf(HexcraftBlocks.WILLOW_WOOD.get());

		this.dropSelf(HexcraftBlocks.HAWTHORN_WOOD.get());

		this.dropSelf(HexcraftBlocks.CEDAR_WOOD.get());

		this.dropSelf(HexcraftBlocks.DISTORTED_WOOD.get());

		this.dropSelf(HexcraftBlocks.ELDER_WOOD.get());

		this.dropSelf(HexcraftBlocks.JUNIPER_WOOD.get());

		this.dropSelf(HexcraftBlocks.ROWAN_WOOD.get());

		this.dropSelf(HexcraftBlocks.TWISTED_WOOD.get());

		this.dropSelf(HexcraftBlocks.WITCH_WOOD_WOOD.get());

		this.dropSelf(HexcraftBlocks.ECHO_WOOD_WOOD.get());

		this.dropSelf(HexcraftBlocks.PHOENIX_WOOD.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_EBONY_LOG.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_BLOOD_OAK_LOG.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_HELL_BARK_LOG.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_WHITE_OAK_LOG.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_ALDER_LOG.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_WITCH_HAZEL_LOG.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_WILLOW_LOG.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_HAWTHORN_LOG.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_CEDAR_LOG.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_DISTORTED_LOG.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_ELDER_LOG.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_JUNIPER_LOG.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_ROWAN_LOG.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_TWISTED_LOG.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_WITCH_WOOD_LOG.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_ECHO_WOOD_LOG.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_PHOENIX_LOG.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_EBONY_WOOD.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_BLOOD_OAK_WOOD.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_HELL_BARK_WOOD.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_WHITE_OAK_WOOD.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_ALDER_WOOD.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_WITCH_HAZEL_WOOD.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_WILLOW_WOOD.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_HAWTHORN_WOOD.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_CEDAR_WOOD.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_DISTORTED_WOOD.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_ELDER_WOOD.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_JUNIPER_WOOD.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_ROWAN_WOOD.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_TWISTED_WOOD.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_WITCH_WOOD_WOOD.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_ECHO_WOOD_WOOD.get());

		this.dropSelf(HexcraftBlocks.STRIPPED_PHOENIX_WOOD.get());

		this.dropSelf(HexcraftBlocks.EBONY_PLANKS.get());

		this.dropSelf(HexcraftBlocks.BLOOD_OAK_PLANKS.get());

		this.dropSelf(HexcraftBlocks.HELL_BARK_PLANKS.get());

		this.dropSelf(HexcraftBlocks.WHITE_OAK_PLANKS.get());

		this.dropSelf(HexcraftBlocks.ALDER_PLANKS.get());

		this.dropSelf(HexcraftBlocks.WITCH_HAZEL_PLANKS.get());

		this.dropSelf(HexcraftBlocks.WILLOW_PLANKS.get());

		this.dropSelf(HexcraftBlocks.HAWTHORN_PLANKS.get());

		this.dropSelf(HexcraftBlocks.CEDAR_PLANKS.get());

		this.dropSelf(HexcraftBlocks.DISTORTED_PLANKS.get());

		this.dropSelf(HexcraftBlocks.ELDER_PLANKS.get());

		this.dropSelf(HexcraftBlocks.JUNIPER_PLANKS.get());

		this.dropSelf(HexcraftBlocks.ROWAN_PLANKS.get());

		this.dropSelf(HexcraftBlocks.TWISTED_PLANKS.get());

		this.dropSelf(HexcraftBlocks.WITCH_WOOD_PLANKS.get());

		this.dropSelf(HexcraftBlocks.ECHO_WOOD_PLANKS.get());

		this.dropSelf(HexcraftBlocks.PHOENIX_PLANKS.get());

		this.dropSelf(HexcraftBlocks.EBONY_SAPLING.get());

		this.add(HexcraftBlocks.POTTED_EBONY_SAPLING.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_EBONY_SAPLING.get()));

		this.dropSelf(HexcraftBlocks.BLOOD_OAK_SAPLING.get());

		this.add(HexcraftBlocks.POTTED_BLOOD_OAK_SAPLING.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_BLOOD_OAK_SAPLING.get()));

		this.dropSelf(HexcraftBlocks.HELL_BARK_SAPLING.get());

		this.add(HexcraftBlocks.POTTED_HELL_BARK_SAPLING.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_HELL_BARK_SAPLING.get()));

		this.dropSelf(HexcraftBlocks.WHITE_OAK_SAPLING.get());

		this.add(HexcraftBlocks.POTTED_WHITE_OAK_SAPLING.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_WHITE_OAK_SAPLING.get()));

		this.dropSelf(HexcraftBlocks.ALDER_SAPLING.get());

		this.add(HexcraftBlocks.POTTED_ALDER_SAPLING.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_ALDER_SAPLING.get()));

		this.dropSelf(HexcraftBlocks.WITCH_HAZEL_SAPLING.get());

		this.add(HexcraftBlocks.POTTED_WITCH_HAZEL_SAPLING.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_WITCH_HAZEL_SAPLING.get()));

		this.dropSelf(HexcraftBlocks.WILLOW_SAPLING.get());

		this.add(HexcraftBlocks.POTTED_WILLOW_SAPLING.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_WILLOW_SAPLING.get()));

		this.dropSelf(HexcraftBlocks.HAWTHORN_SAPLING.get());

		this.add(HexcraftBlocks.POTTED_HAWTHORN_SAPLING.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_HAWTHORN_SAPLING.get()));

		this.dropSelf(HexcraftBlocks.CEDAR_SAPLING.get());

		this.add(HexcraftBlocks.POTTED_CEDAR_SAPLING.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_CEDAR_SAPLING.get()));

		this.dropSelf(HexcraftBlocks.DISTORTED_SAPLING.get());

		this.add(HexcraftBlocks.POTTED_DISTORTED_SAPLING.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_DISTORTED_SAPLING.get()));

		this.dropSelf(HexcraftBlocks.ELDER_SAPLING.get());

		this.add(HexcraftBlocks.POTTED_ELDER_SAPLING.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_ELDER_SAPLING.get()));

		this.dropSelf(HexcraftBlocks.JUNIPER_SAPLING.get());

		this.add(HexcraftBlocks.POTTED_JUNIPER_SAPLING.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_JUNIPER_SAPLING.get()));

		this.dropSelf(HexcraftBlocks.ROWAN_SAPLING.get());

		this.add(HexcraftBlocks.POTTED_ROWAN_SAPLING.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_ROWAN_SAPLING.get()));

		this.dropSelf(HexcraftBlocks.TWISTED_SAPLING.get());

		this.add(HexcraftBlocks.POTTED_TWISTED_SAPLING.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_TWISTED_SAPLING.get()));

		this.dropSelf(HexcraftBlocks.WITCH_WOOD_SAPLING.get());

		this.add(HexcraftBlocks.POTTED_WITCH_WOOD_SAPLING.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_WITCH_WOOD_SAPLING.get()));

		this.dropSelf(HexcraftBlocks.ECHO_WOOD_SAPLING.get());

		this.add(HexcraftBlocks.POTTED_ECHO_WOOD_SAPLING.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_ECHO_WOOD_SAPLING.get()));

		this.dropSelf(HexcraftBlocks.PHOENIX_SAPLING.get());

		this.add(HexcraftBlocks.POTTED_PHOENIX_SAPLING.get(),
				createPotFlowerItemTable(HexcraftBlocks.POTTED_PHOENIX_SAPLING.get()));

		this.add(HexcraftBlocks.EBONY_LEAVES.get(), block ->
				createLeavesDrops(block, HexcraftBlocks.EBONY_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

		this.add(HexcraftBlocks.BLOOD_OAK_LEAVES.get(), block ->
				createBloodOakLeavesDrops(block, () -> HexcraftBlocks.BLOOD_OAK_SAPLING.get().asItem(), HexcraftItems.BLOOD_APPLE::get));

		this.add(HexcraftBlocks.HELL_BARK_LEAVES.get(), block ->
				createLeavesDrops(block, HexcraftBlocks.HELL_BARK_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

		this.add(HexcraftBlocks.WHITE_OAK_LEAVES.get(), block ->
				createLeavesDrops(block, HexcraftBlocks.WHITE_OAK_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

		this.add(HexcraftBlocks.ALDER_LEAVES.get(), block ->
				createLeavesDrops(block, HexcraftBlocks.ALDER_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

		this.add(HexcraftBlocks.WITCH_HAZEL_LEAVES.get(), block ->
				createLeavesDrops(block, HexcraftBlocks.WITCH_HAZEL_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

		this.add(HexcraftBlocks.WILLOW_LEAVES.get(), block ->
				createLeavesDrops(block, HexcraftBlocks.WILLOW_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

		this.add(HexcraftBlocks.HAWTHORN_LEAVES.get(), block ->
				createLeavesDrops(block, HexcraftBlocks.HAWTHORN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

		this.add(HexcraftBlocks.CEDAR_LEAVES.get(), block ->
				createLeavesDrops(block, HexcraftBlocks.CEDAR_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

		this.add(HexcraftBlocks.DISTORTED_LEAVES.get(), block ->
				createLeavesDrops(block, HexcraftBlocks.DISTORTED_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

		this.add(HexcraftBlocks.ELDER_LEAVES.get(), block ->
				createLeavesDrops(block, HexcraftBlocks.ELDER_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

		this.add(HexcraftBlocks.JUNIPER_LEAVES.get(), block ->
				createJuniperLeavesDrops(block, () -> HexcraftBlocks.JUNIPER_SAPLING.get().asItem(), HexcraftItems.JUNIPER_BERRIES::get));

		this.add(HexcraftBlocks.ROWAN_LEAVES.get(), block ->
				createLeavesDrops(block, HexcraftBlocks.ROWAN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

		this.add(HexcraftBlocks.TWISTED_LEAVES.get(), block ->
				createLeavesDrops(block, HexcraftBlocks.TWISTED_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

		this.add(HexcraftBlocks.WITCH_WOOD_LEAVES.get(), block ->
				createLeavesDrops(block, HexcraftBlocks.WITCH_WOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

		this.add(HexcraftBlocks.ECHO_WOOD_LEAVES.get(), block ->
				createLeavesDrops(block, HexcraftBlocks.ECHO_WOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

		this.add(HexcraftBlocks.PHOENIX_LEAVES.get(), block ->
				createLeavesDrops(block, HexcraftBlocks.PHOENIX_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

		this.dropSelf(HexcraftBlocks.FAIRY_LANTERN.get());

		this.dropSelf(HexcraftBlocks.PIXIE_LANTERN.get());

		this.dropSelf(HexcraftBlocks.HELL_FUNGAL_LAMP.get());

		this.dropSelf(HexcraftBlocks.ECHO_FUNGAL_LAMP.get());

		this.dropSelf(HexcraftBlocks.VILESHROOM_LAMP.get());

		this.dropSelf(HexcraftBlocks.GHOSTSHROOM_LAMP.get());

		this.dropSelf(HexcraftBlocks.SOULSTEM_CANDLE.get());

		this.dropSelf(HexcraftBlocks.WHITE_WITCH_CANDLE.get());

		this.dropSelf(HexcraftBlocks.ORANGE_WITCH_CANDLE.get());

		this.dropSelf(HexcraftBlocks.MAGENTA_WITCH_CANDLE.get());

		this.dropSelf(HexcraftBlocks.LIGHT_BLUE_WITCH_CANDLE.get());

		this.dropSelf(HexcraftBlocks.YELLOW_WITCH_CANDLE.get());

		this.dropSelf(HexcraftBlocks.LIME_WITCH_CANDLE.get());

		this.dropSelf(HexcraftBlocks.PINK_WITCH_CANDLE.get());

		this.dropSelf(HexcraftBlocks.GRAY_WITCH_CANDLE.get());

		this.dropSelf(HexcraftBlocks.LIGHT_GRAY_WITCH_CANDLE.get());

		this.dropSelf(HexcraftBlocks.CYAN_WITCH_CANDLE.get());

		this.dropSelf(HexcraftBlocks.PURPLE_WITCH_CANDLE.get());

		this.dropSelf(HexcraftBlocks.BLUE_WITCH_CANDLE.get());

		this.dropSelf(HexcraftBlocks.BROWN_WITCH_CANDLE.get());

		this.dropSelf(HexcraftBlocks.GREEN_WITCH_CANDLE.get());

		this.dropSelf(HexcraftBlocks.RED_WITCH_CANDLE.get());

		this.dropSelf(HexcraftBlocks.BLACK_WITCH_CANDLE.get());

		this.dropSelf(HexcraftBlocks.PEARL_COBBLESTONE.get());

		this.dropSelf(HexcraftBlocks.PEARL_STONE_BRICKS.get());

		this.dropSelf(HexcraftBlocks.POLISHED_PEARL_STONE.get());

		this.dropSelf(HexcraftBlocks.CHISELED_PEARL_STONE.get());

		this.dropSelf(HexcraftBlocks.CRACKED_PEARL_STONE.get());

		this.dropSelf(HexcraftBlocks.CRIMSON_COBBLESTONE.get());

		this.dropSelf(HexcraftBlocks.CRIMSON_STONE_BRICKS.get());

		this.dropSelf(HexcraftBlocks.POLISHED_CRIMSON_STONE.get());

		this.dropSelf(HexcraftBlocks.CHISELED_CRIMSON_STONE.get());

		this.dropSelf(HexcraftBlocks.CRACKED_CRIMSON_STONE.get());

		this.dropSelf(HexcraftBlocks.UNDER_WORLD_COBBLESTONE.get());

		this.dropSelf(HexcraftBlocks.UNDER_WORLD_STONE_BRICKS.get());

		this.dropSelf(HexcraftBlocks.POLISHED_UNDER_WORLD_STONE.get());

		this.dropSelf(HexcraftBlocks.CHISELED_UNDER_WORLD_STONE.get());

		this.dropSelf(HexcraftBlocks.CRACKED_UNDER_WORLD_STONE.get());

		this.dropSelf(HexcraftBlocks.IGNEOROCK_COBBLESTONE.get());

		this.dropSelf(HexcraftBlocks.IGNEOROCK_STONE_BRICKS.get());

		this.dropSelf(HexcraftBlocks.POLISHED_IGNEOROCK.get());

		this.dropSelf(HexcraftBlocks.CHISELED_IGNEOROCK.get());

		this.dropSelf(HexcraftBlocks.CRACKED_IGNEOROCK.get());

		this.dropSelf(HexcraftBlocks.BLOODSHALE_COBBLESTONE.get());

		this.dropSelf(HexcraftBlocks.BLOODSHALE_STONE_BRICKS.get());

		this.dropSelf(HexcraftBlocks.POLISHED_BLOODSHALE.get());

		this.dropSelf(HexcraftBlocks.CHISELED_BLOODSHALE.get());

		this.dropSelf(HexcraftBlocks.CRACKED_BLOODSHALE.get());

		this.dropSelf(HexcraftBlocks.CHARSTONE_COBBLESTONE.get());

		this.dropSelf(HexcraftBlocks.CHARSTONE_BRICKS.get());

		this.dropSelf(HexcraftBlocks.POLISHED_CHARSTONE.get());

		this.dropSelf(HexcraftBlocks.CHISELED_CHARSTONE.get());

		this.dropSelf(HexcraftBlocks.CRACKED_CHARSTONE.get());

		this.dropSelf(HexcraftBlocks.ABYSSAL_SAND.get());

		this.dropSelf(HexcraftBlocks.ABYSSAL_GRAVEL.get());

		this.dropSelf(HexcraftBlocks.VOIDGRIT.get());

		this.dropSelf(HexcraftBlocks.MAGMAGRIT.get());

		this.dropSelf(HexcraftBlocks.CRIMSON_SAND.get());

		this.dropSelf(HexcraftBlocks.CRIMSON_SAND_STONE.get());

		this.dropSelf(HexcraftBlocks.SMOOTH_CRIMSON_SAND_STONE.get());

		this.dropSelf(HexcraftBlocks.CHISELED_CRIMSON_SAND_STONE.get());

		this.dropSelf(HexcraftBlocks.CUT_CRIMSON_SAND_STONE.get());

		this.dropSelf(HexcraftBlocks.FAIRY_SAND.get());

		this.dropSelf(HexcraftBlocks.FAIRY_SAND_STONE.get());

		this.dropSelf(HexcraftBlocks.SMOOTH_FAIRY_SAND_STONE.get());

		this.dropSelf(HexcraftBlocks.CHISELED_FAIRY_SAND_STONE.get());

		this.dropSelf(HexcraftBlocks.CUT_FAIRY_SAND_STONE.get());

		this.dropSelf(HexcraftBlocks.PIXIE_SAND.get());

		this.dropSelf(HexcraftBlocks.PIXIE_SAND_STONE.get());

		this.dropSelf(HexcraftBlocks.SMOOTH_PIXIE_SAND_STONE.get());

		this.dropSelf(HexcraftBlocks.CHISELED_PIXIE_SAND_STONE.get());

		this.dropSelf(HexcraftBlocks.CUT_PIXIE_SAND_STONE.get());

		this.dropWhenSilkTouch(HexcraftBlocks.CRIMSON_GLASS.get());

		this.dropWhenSilkTouch(HexcraftBlocks.CRIMSON_GLASS_PANE.get());

		this.dropWhenSilkTouch(HexcraftBlocks.FAIRY_GLASS.get());

		this.dropWhenSilkTouch(HexcraftBlocks.FAIRY_GLASS_PANE.get());

		this.dropWhenSilkTouch(HexcraftBlocks.PIXIE_GLASS.get());

		this.dropWhenSilkTouch(HexcraftBlocks.PIXIE_GLASS_PANE.get());

		this.dropWhenSilkTouch(HexcraftBlocks.CRIMSON_ICE.get());

		this.dropWhenSilkTouch(HexcraftBlocks.CRIMSON_PACKED_ICE.get());

		this.dropSelf(HexcraftBlocks.PEARL_STONE_WALL.get());

		this.dropSelf(HexcraftBlocks.CRIMSON_STONE_WALL.get());

		this.dropSelf(HexcraftBlocks.UNDER_WORLD_STONE_WALL.get());

		this.dropSelf(HexcraftBlocks.CHARSTONE_WALL.get());

		this.dropSelf(HexcraftBlocks.CRIMSON_SAND_STONE_WALL.get());

		this.dropSelf(HexcraftBlocks.FAIRY_SAND_STONE_WALL.get());

		this.dropSelf(HexcraftBlocks.PIXIE_SAND_STONE_WALL.get());

		this.dropSelf(HexcraftBlocks.PEARL_STONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.PEARL_COBBLESTONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.PEARL_STONE_BRICKS_STAIRS.get());

		this.dropSelf(HexcraftBlocks.POLISHED_PEARL_STONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.CRIMSON_STONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.CRIMSON_COBBLESTONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.CRIMSON_STONE_BRICKS_STAIRS.get());

		this.dropSelf(HexcraftBlocks.POLISHED_CRIMSON_STONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.UNDER_WORLD_STONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.UNDER_WORLD_COBBLESTONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.UNDER_WORLD_STONE_BRICKS_STAIRS.get());

		this.dropSelf(HexcraftBlocks.POLISHED_UNDER_WORLD_STONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.CHARSTONE_STONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.CHARSTONE_COBBLESTONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.CHARSTONE_STONE_BRICKS_STAIRS.get());

		this.dropSelf(HexcraftBlocks.POLISHED_CHARSTONE_STONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.CRIMSON_SAND_STONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.IGNEOROCK_STAIRS.get());

		this.dropSelf(HexcraftBlocks.IGNEOROCK_COBBLESTONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.IGNEOROCK_STONE_BRICKS_STAIRS.get());

		this.dropSelf(HexcraftBlocks.POLISHED_IGNEOROCK_STAIRS.get());

		this.dropSelf(HexcraftBlocks.BLOODSHALE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.BLOODSHALE_COBBLESTONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.BLOODSHALE_STONE_BRICKS_STAIRS.get());

		this.dropSelf(HexcraftBlocks.POLISHED_BLOODSHALE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.SMOOTH_CRIMSON_SAND_STONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.FAIRY_SAND_STONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.SMOOTH_FAIRY_SAND_STONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.PIXIE_SAND_STONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.SMOOTH_PIXIE_SAND_STONE_STAIRS.get());

		this.dropSelf(HexcraftBlocks.EBONY_STAIRS.get());

		this.dropSelf(HexcraftBlocks.BLOOD_OAK_STAIRS.get());

		this.dropSelf(HexcraftBlocks.HELL_BARK_STAIRS.get());

		this.dropSelf(HexcraftBlocks.WHITE_OAK_STAIRS.get());

		this.dropSelf(HexcraftBlocks.ALDER_STAIRS.get());

		this.dropSelf(HexcraftBlocks.WITCH_HAZEL_STAIRS.get());

		this.dropSelf(HexcraftBlocks.WILLOW_STAIRS.get());

		this.dropSelf(HexcraftBlocks.HAWTHORN_STAIRS.get());

		this.dropSelf(HexcraftBlocks.CEDAR_STAIRS.get());

		this.dropSelf(HexcraftBlocks.DISTORTED_STAIRS.get());

		this.dropSelf(HexcraftBlocks.ELDER_STAIRS.get());

		this.dropSelf(HexcraftBlocks.JUNIPER_STAIRS.get());

		this.dropSelf(HexcraftBlocks.ROWAN_STAIRS.get());

		this.dropSelf(HexcraftBlocks.TWISTED_STAIRS.get());

		this.dropSelf(HexcraftBlocks.WITCH_WOOD_STAIRS.get());

		this.dropSelf(HexcraftBlocks.ECHO_WOOD_STAIRS.get());

		this.dropSelf(HexcraftBlocks.PHOENIX_STAIRS.get());

		this.dropSelf(HexcraftBlocks.PEARL_STONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.PEARL_COBBLESTONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.PEARL_STONE_BRICKS_SLAB.get());

		this.dropSelf(HexcraftBlocks.POLISHED_PEARL_STONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.CRIMSON_STONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.CRIMSON_COBBLESTONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.CRIMSON_STONE_BRICKS_SLAB.get());

		this.dropSelf(HexcraftBlocks.POLISHED_CRIMSON_STONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.UNDER_WORLD_STONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.UNDER_WORLD_COBBLESTONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.UNDER_WORLD_STONE_BRICKS_SLAB.get());

		this.dropSelf(HexcraftBlocks.POLISHED_UNDER_WORLD_STONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.CHARSTONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.CHARSTONE_COBBLESTONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.CHARSTONE_BRICKS_SLAB.get());

		this.dropSelf(HexcraftBlocks.POLISHED_CHARSTONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.CRIMSON_SAND_STONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.SMOOTH_CRIMSON_SAND_STONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.CUT_CRIMSON_SAND_STONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.FAIRY_SAND_STONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.SMOOTH_FAIRY_SAND_STONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.CUT_FAIRY_SAND_STONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.PIXIE_SAND_STONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.SMOOTH_PIXIE_SAND_STONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.CUT_PIXIE_SAND_STONE_SLAB.get());

		this.dropSelf(HexcraftBlocks.EBONY_SLAB.get());

		this.dropSelf(HexcraftBlocks.BLOOD_OAK_SLAB.get());

		this.dropSelf(HexcraftBlocks.HELL_BARK_SLAB.get());

		this.dropSelf(HexcraftBlocks.WHITE_OAK_SLAB.get());

		this.dropSelf(HexcraftBlocks.ALDER_SLAB.get());

		this.dropSelf(HexcraftBlocks.WITCH_HAZEL_SLAB.get());

		this.dropSelf(HexcraftBlocks.WILLOW_SLAB.get());

		this.dropSelf(HexcraftBlocks.HAWTHORN_SLAB.get());

		this.dropSelf(HexcraftBlocks.CEDAR_SLAB.get());

		this.dropSelf(HexcraftBlocks.DISTORTED_SLAB.get());

		this.dropSelf(HexcraftBlocks.ELDER_SLAB.get());

		this.dropSelf(HexcraftBlocks.JUNIPER_SLAB.get());

		this.dropSelf(HexcraftBlocks.ROWAN_SLAB.get());

		this.dropSelf(HexcraftBlocks.TWISTED_SLAB.get());

		this.dropSelf(HexcraftBlocks.WITCH_WOOD_SLAB.get());

		this.dropSelf(HexcraftBlocks.ECHO_WOOD_SLAB.get());

		this.dropSelf(HexcraftBlocks.PHOENIX_SLAB.get());

		this.dropSelf(HexcraftBlocks.PEARL_STONE_BUTTON.get());

		this.dropSelf(HexcraftBlocks.CRIMSON_STONE_BUTTON.get());

		this.dropSelf(HexcraftBlocks.UNDER_WORLD_STONE_BUTTON.get());

		this.dropSelf(HexcraftBlocks.CHARSTONE_BUTTON.get());

		this.dropSelf(HexcraftBlocks.IGNEOROCK_BUTTON.get());

		this.dropSelf(HexcraftBlocks.BLOODSHALE_BUTTON.get());

		this.dropSelf(HexcraftBlocks.EBONY_BUTTON.get());

		this.dropSelf(HexcraftBlocks.BLOOD_OAK_BUTTON.get());

		this.dropSelf(HexcraftBlocks.HELL_BARK_BUTTON.get());

		this.dropSelf(HexcraftBlocks.WHITE_OAK_BUTTON.get());

		this.dropSelf(HexcraftBlocks.ALDER_BUTTON.get());

		this.dropSelf(HexcraftBlocks.WITCH_HAZEL_BUTTON.get());

		this.dropSelf(HexcraftBlocks.WILLOW_BUTTON.get());

		this.dropSelf(HexcraftBlocks.HAWTHORN_BUTTON.get());

		this.dropSelf(HexcraftBlocks.CEDAR_BUTTON.get());

		this.dropSelf(HexcraftBlocks.DISTORTED_BUTTON.get());

		this.dropSelf(HexcraftBlocks.ELDER_BUTTON.get());

		this.dropSelf(HexcraftBlocks.JUNIPER_BUTTON.get());

		this.dropSelf(HexcraftBlocks.ROWAN_BUTTON.get());

		this.dropSelf(HexcraftBlocks.TWISTED_BUTTON.get());

		this.dropSelf(HexcraftBlocks.WITCH_WOOD_BUTTON.get());

		this.dropSelf(HexcraftBlocks.ECHO_WOOD_BUTTON.get());

		this.dropSelf(HexcraftBlocks.PHOENIX_BUTTON.get());

		this.dropSelf(HexcraftBlocks.EBONY_FENCE.get());

		this.dropSelf(HexcraftBlocks.BLOOD_OAK_FENCE.get());

		this.dropSelf(HexcraftBlocks.HELL_BARK_FENCE.get());

		this.dropSelf(HexcraftBlocks.WHITE_OAK_FENCE.get());

		this.dropSelf(HexcraftBlocks.ALDER_FENCE.get());

		this.dropSelf(HexcraftBlocks.WITCH_HAZEL_FENCE.get());

		this.dropSelf(HexcraftBlocks.WILLOW_FENCE.get());

		this.dropSelf(HexcraftBlocks.HAWTHORN_FENCE.get());

		this.dropSelf(HexcraftBlocks.CEDAR_FENCE.get());

		this.dropSelf(HexcraftBlocks.DISTORTED_FENCE.get());

		this.dropSelf(HexcraftBlocks.ELDER_FENCE.get());

		this.dropSelf(HexcraftBlocks.JUNIPER_FENCE.get());

		this.dropSelf(HexcraftBlocks.ROWAN_FENCE.get());

		this.dropSelf(HexcraftBlocks.TWISTED_FENCE.get());

		this.dropSelf(HexcraftBlocks.WITCH_WOOD_FENCE.get());

		this.dropSelf(HexcraftBlocks.ECHO_WOOD_FENCE.get());

		this.dropSelf(HexcraftBlocks.EBONY_FENCE_GATE.get());

		this.dropSelf(HexcraftBlocks.PHOENIX_FENCE.get());

		this.dropSelf(HexcraftBlocks.BLOOD_OAK_FENCE_GATE.get());

		this.dropSelf(HexcraftBlocks.HELL_BARK_FENCE_GATE.get());

		this.dropSelf(HexcraftBlocks.WHITE_OAK_FENCE_GATE.get());

		this.dropSelf(HexcraftBlocks.ALDER_FENCE_GATE.get());

		this.dropSelf(HexcraftBlocks.WITCH_HAZEL_FENCE_GATE.get());

		this.dropSelf(HexcraftBlocks.WILLOW_FENCE_GATE.get());

		this.dropSelf(HexcraftBlocks.HAWTHORN_FENCE_GATE.get());

		this.dropSelf(HexcraftBlocks.CEDAR_FENCE_GATE.get());

		this.dropSelf(HexcraftBlocks.DISTORTED_FENCE_GATE.get());

		this.dropSelf(HexcraftBlocks.ELDER_FENCE_GATE.get());

		this.dropSelf(HexcraftBlocks.JUNIPER_FENCE_GATE.get());

		this.dropSelf(HexcraftBlocks.ROWAN_FENCE_GATE.get());

		this.dropSelf(HexcraftBlocks.TWISTED_FENCE_GATE.get());

		this.dropSelf(HexcraftBlocks.WITCH_WOOD_FENCE_GATE.get());

		this.dropSelf(HexcraftBlocks.ECHO_WOOD_FENCE_GATE.get());

		this.dropSelf(HexcraftBlocks.PHOENIX_FENCE_GATE.get());

		this.add(HexcraftBlocks.EBONY_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.EBONY_DOOR.get()));

		this.add(HexcraftBlocks.BLOOD_OAK_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.BLOOD_OAK_DOOR.get()));

		this.add(HexcraftBlocks.HELL_BARK_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.HELL_BARK_DOOR.get()));

		this.add(HexcraftBlocks.WHITE_OAK_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.WHITE_OAK_DOOR.get()));

		this.add(HexcraftBlocks.ALDER_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.ALDER_DOOR.get()));

		this.add(HexcraftBlocks.WITCH_HAZEL_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.WITCH_HAZEL_DOOR.get()));

		this.add(HexcraftBlocks.WILLOW_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.WILLOW_DOOR.get()));

		this.add(HexcraftBlocks.HAWTHORN_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.HAWTHORN_DOOR.get()));

		this.add(HexcraftBlocks.CEDAR_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.CEDAR_DOOR.get()));

		this.add(HexcraftBlocks.DISTORTED_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.DISTORTED_DOOR.get()));

		this.add(HexcraftBlocks.ELDER_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.ELDER_DOOR.get()));

		this.add(HexcraftBlocks.JUNIPER_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.JUNIPER_DOOR.get()));

		this.add(HexcraftBlocks.ROWAN_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.ROWAN_DOOR.get()));

		this.add(HexcraftBlocks.TWISTED_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.TWISTED_DOOR.get()));

		this.add(HexcraftBlocks.WITCH_WOOD_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.WITCH_WOOD_DOOR.get()));

		this.add(HexcraftBlocks.ECHO_WOOD_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.ECHO_WOOD_DOOR.get()));

		this.add(HexcraftBlocks.PHOENIX_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.PHOENIX_DOOR.get()));

		this.add(HexcraftBlocks.UMBRACITE_DOOR.get(),
				block -> createDoorTable(HexcraftBlocks.UMBRACITE_DOOR.get()));

		this.dropSelf(HexcraftBlocks.EBONY_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.BLOOD_OAK_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.HELL_BARK_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.WHITE_OAK_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.ALDER_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.WITCH_HAZEL_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.WILLOW_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.HAWTHORN_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.CEDAR_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.DISTORTED_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.ELDER_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.JUNIPER_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.ROWAN_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.TWISTED_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.WITCH_WOOD_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.ECHO_WOOD_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.PHOENIX_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.UMBRACITE_TRAPDOOR.get());

		this.dropSelf(HexcraftBlocks.PEARL_STONE_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.CRIMSON_STONE_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.UNDER_WORLD_STONE_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.CHARSTONE_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.EBONY_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.BLOOD_OAK_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.HELL_BARK_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.WHITE_OAK_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.ALDER_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.WITCH_HAZEL_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.WILLOW_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.HAWTHORN_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.CEDAR_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.DISTORTED_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.ELDER_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.JUNIPER_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.ROWAN_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.TWISTED_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.WITCH_WOOD_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.ECHO_WOOD_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.PHOENIX_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.UMBRACITE_PRESSURE_PLATE.get());

		this.dropSelf(HexcraftBlocks.MAGIC_CRYSTAL_BLOCK.get());

		this.dropSelf(HexcraftBlocks.TWILIGHT_CORAL_BLOCK.get());

		this.dropSelf(HexcraftBlocks.DEAD_TWILIGHT_CORAL_BLOCK.get());

		this.dropSelf(HexcraftBlocks.SANGUINE_CORAL_BLOCK.get());

		this.dropSelf(HexcraftBlocks.DEAD_SANGUINE_CORAL_BLOCK.get());

		this.dropSelf(HexcraftBlocks.WHISPER_CORAL_BLOCK.get());

		this.dropSelf(HexcraftBlocks.DEAD_WHISPER_CORAL_BLOCK.get());

		this.dropSelf(HexcraftBlocks.WHISPER_CORAL_BLOCK.get());

		this.dropSelf(HexcraftBlocks.EBONFANG_CORAL_BLOCK.get());

		this.dropSelf(HexcraftBlocks.DEAD_EBONFANG_CORAL_BLOCK.get());

		this.dropSelf(HexcraftBlocks.SPECTRAL_BLOOM_CORAL_BLOCK.get());

		this.dropSelf(HexcraftBlocks.DEAD_SPECTRAL_BLOOM_CORAL_BLOCK.get());

		this.dropSelf(HexcraftBlocks.WHISPER_CORAL_BLOCK.get());

		this.dropSelf(HexcraftBlocks.DEAD_HELLVINE_CORAL_BLOCK.get());

		this.dropSelf(HexcraftBlocks.HELLVINE_CORAL_BLOCK.get());


		this.add(HexcraftBlocks.TWILIGHT_CORAL.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.TWILIGHT_CORAL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.TWILIGHT_CORAL_WALL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_TWILIGHT_CORAL.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_TWILIGHT_CORAL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_TWILIGHT_CORAL_WALL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.SANGUINE_CORAL.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.SANGUINE_CORAL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.SANGUINE_CORAL_WALL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_SANGUINE_CORAL.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_SANGUINE_CORAL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_SANGUINE_CORAL_WALL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.WHISPER_CORAL.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.WHISPER_CORAL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.WHISPER_CORAL_WALL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_WHISPER_CORAL.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_WHISPER_CORAL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_WHISPER_CORAL_WALL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.EBONFANG_CORAL.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.EBONFANG_CORAL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.EBONFANG_CORAL_WALL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_EBONFANG_CORAL.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_EBONFANG_CORAL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_EBONFANG_CORAL_WALL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.SPECTRAL_BLOOM_CORAL.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.SPECTRAL_BLOOM_CORAL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.SPECTRAL_BLOOM_CORAL_WALL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_SPECTRAL_BLOOM_CORAL.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_SPECTRAL_BLOOM_CORAL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_SPECTRAL_BLOOM_CORAL_WALL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.HELLVINE_CORAL.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.HELLVINE_CORAL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.HELLVINE_CORAL_WALL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_HELLVINE_CORAL.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_HELLVINE_CORAL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.add(HexcraftBlocks.DEAD_HELLVINE_CORAL_WALL_FAN.get(),
				block -> createSilkTouchOnlyTable(block));

		this.dropOther(HexcraftBlocks.MAGIC_CRYSTAL_CLUSTER.get(), HexcraftItems.MAGIC_CRYSTAL.get());

		this.dropOther(HexcraftBlocks.LARGE_MAGIC_CRYSTAL_BUD.get(), HexcraftItems.MAGIC_CRYSTAL.get());

		add(HexcraftBlocks.WITCHES_LADDER.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(HexcraftItems.WITCHES_LADDER_ITEM.get()))));

		this.dropOther(HexcraftBlocks.LIVING_KELP.get(), HexcraftItems.LIVING_KELP_ITEM.get());

		add(HexcraftBlocks.DEEP_SEA_GRASS.get(),
				block -> createSilkTouchOrShearsDispatchTable(
						block,
						applyExplosionCondition(block, LootItem.lootTableItem(HexcraftBlocks.DEEP_SEA_GRASS.get()))
				)
		);

		add(HexcraftBlocks.TAll_DEEP_SEA_GRASS.get(),
				block -> createSilkTouchOrShearsDispatchTable(
						block,
						applyExplosionCondition(block, LootItem.lootTableItem(HexcraftBlocks.TAll_DEEP_SEA_GRASS.get()))
				)
		);

		add(HexcraftBlocks.VILEVINE.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(HexcraftItems.VILEVINE_ITEM.get()))));

		add(HexcraftBlocks.SLINKROOT.get(), block -> createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(HexcraftItems.SLINKROOT_ITEM.get()))));
		;
		this.add(HexcraftBlocks.BLOOD_MUSHROOM_BLOCK.get(),
				createSilkTouchDispatchTable(
						HexcraftBlocks.BLOOD_MUSHROOM_BLOCK.get(),
						applyExplosionDecay(HexcraftBlocks.BLOOD_MUSHROOM_BLOCK.get(),
								LootItem.lootTableItem(HexcraftBlocks.BLOOD_MUSHROOM.get())
										.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
						)
				)
		);

		this.add(HexcraftBlocks.BLOOD_MUSHROOM_STEM.get(),
				createSilkTouchDispatchTable(
						HexcraftBlocks.BLOOD_MUSHROOM_STEM.get(),
						applyExplosionDecay(HexcraftBlocks.BLOOD_MUSHROOM_STEM.get(),
								LootItem.lootTableItem(HexcraftBlocks.BLOOD_MUSHROOM.get())
										.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
						)
				)
		);

		this.add(HexcraftBlocks.VILESHROOM_BLOCK.get(),
				createSilkTouchDispatchTable(
						HexcraftBlocks.VILESHROOM_BLOCK.get(),
						applyExplosionDecay(HexcraftBlocks.VILESHROOM_BLOCK.get(),
								LootItem.lootTableItem(HexcraftBlocks.VILESHROOM.get())
										.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
						)
				)
		);

		this.add(HexcraftBlocks.VILESHROOM_STEM.get(),
				createSilkTouchDispatchTable(
						HexcraftBlocks.VILESHROOM_STEM.get(),
						applyExplosionDecay(HexcraftBlocks.VILESHROOM_STEM.get(),
								LootItem.lootTableItem(HexcraftBlocks.VILESHROOM.get())
										.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
						)
				)
		);

		this.add(HexcraftBlocks.GHOSTSHROOM_BLOCK.get(),
				createSilkTouchDispatchTable(
						HexcraftBlocks.GHOSTSHROOM_BLOCK.get(),
						applyExplosionDecay(HexcraftBlocks.GHOSTSHROOM_BLOCK.get(),
								LootItem.lootTableItem(HexcraftBlocks.GHOSTSHROOM.get())
										.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
						)
				)
		);

		this.add(HexcraftBlocks.GHOSTSHROOM_STEM.get(),
				createSilkTouchDispatchTable(
						HexcraftBlocks.GHOSTSHROOM_STEM.get(),
						applyExplosionDecay(HexcraftBlocks.GHOSTSHROOM_STEM.get(),
								LootItem.lootTableItem(HexcraftBlocks.GHOSTSHROOM.get())
										.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
						)
				)
		);

		this.dropOther(HexcraftBlocks.HELLFIRE_CAMPFIRE.get(), HexcraftItems.ABYSSAL_COAL.get());

		this.dropOther(HexcraftBlocks.ALTAR_TOP.get(), HexcraftItems.ALTAR_BASE_ITEM.get());


		this.add(HexcraftBlocks.EBONY_BOOKSHELF.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem.lootTableItem(Items.BOOK)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
								.apply(ApplyExplosionDecay.explosionDecay())));

		this.add(HexcraftBlocks.BLOOD_OAK_BOOKSHELF.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem.lootTableItem(Items.BOOK)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
								.apply(ApplyExplosionDecay.explosionDecay())));

		this.add(HexcraftBlocks.HELL_BARK_BOOKSHELF.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem.lootTableItem(Items.BOOK)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
								.apply(ApplyExplosionDecay.explosionDecay())));

		this.add(HexcraftBlocks.WHITE_OAK_BOOKSHELF.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem.lootTableItem(Items.BOOK)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
								.apply(ApplyExplosionDecay.explosionDecay())));

		this.add(HexcraftBlocks.ALDER_BOOKSHELF.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem.lootTableItem(Items.BOOK)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
								.apply(ApplyExplosionDecay.explosionDecay())));

		this.add(HexcraftBlocks.WITCH_HAZEL_BOOKSHELF.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem.lootTableItem(Items.BOOK)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
								.apply(ApplyExplosionDecay.explosionDecay())));

		this.add(HexcraftBlocks.WILLOW_BOOKSHELF.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem.lootTableItem(Items.BOOK)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
								.apply(ApplyExplosionDecay.explosionDecay())));

		this.add(HexcraftBlocks.HAWTHORN_BOOKSHELF.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem.lootTableItem(Items.BOOK)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
								.apply(ApplyExplosionDecay.explosionDecay())));

		this.add(HexcraftBlocks.CEDAR_BOOKSHELF.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem.lootTableItem(Items.BOOK)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
								.apply(ApplyExplosionDecay.explosionDecay())));

		this.add(HexcraftBlocks.DISTORTED_BOOKSHELF.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem.lootTableItem(Items.BOOK)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
								.apply(ApplyExplosionDecay.explosionDecay())));

		this.add(HexcraftBlocks.ELDER_BOOKSHELF.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem.lootTableItem(Items.BOOK)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
								.apply(ApplyExplosionDecay.explosionDecay())));

		this.add(HexcraftBlocks.JUNIPER_BOOKSHELF.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem.lootTableItem(Items.BOOK)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
								.apply(ApplyExplosionDecay.explosionDecay())));

		this.add(HexcraftBlocks.ROWAN_BOOKSHELF.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem.lootTableItem(Items.BOOK)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
								.apply(ApplyExplosionDecay.explosionDecay())));

		this.add(HexcraftBlocks.TWISTED_BOOKSHELF.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem.lootTableItem(Items.BOOK)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
								.apply(ApplyExplosionDecay.explosionDecay())));

		this.add(HexcraftBlocks.WITCH_WOOD_BOOKSHELF.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem.lootTableItem(Items.BOOK)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
								.apply(ApplyExplosionDecay.explosionDecay())));

		this.add(HexcraftBlocks.ECHO_WOOD_BOOKSHELF.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem.lootTableItem(Items.BOOK)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
								.apply(ApplyExplosionDecay.explosionDecay())));

		this.add(HexcraftBlocks.PHOENIX_BOOKSHELF.get(),
				block -> createSilkTouchDispatchTable(block,
						LootItem.lootTableItem(Items.BOOK)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
								.apply(ApplyExplosionDecay.explosionDecay())));

		this.add(HexcraftBlocks.PEARL_STONE.get(),
				block -> createSingleItemTableWithSilkTouch(HexcraftBlocks.PEARL_STONE.get(), HexcraftBlocks.PEARL_COBBLESTONE.get()));

		this.add(HexcraftBlocks.CRIMSON_STONE.get(),
				block -> createSingleItemTableWithSilkTouch(HexcraftBlocks.CRIMSON_STONE.get(), HexcraftBlocks.CRIMSON_COBBLESTONE.get()));

		this.add(HexcraftBlocks.UNDER_WORLD_STONE.get(),
				block -> createSingleItemTableWithSilkTouch(HexcraftBlocks.UNDER_WORLD_STONE.get(), HexcraftBlocks.UNDER_WORLD_COBBLESTONE.get()));

		this.add(HexcraftBlocks.CHARSTONE.get(),
				block -> createSingleItemTableWithSilkTouch(HexcraftBlocks.CHARSTONE.get(), HexcraftBlocks.CHARSTONE_COBBLESTONE.get()));

		this.add(HexcraftBlocks.BLOODSHALE.get(),
				block -> createSingleItemTableWithSilkTouch(HexcraftBlocks.BLOODSHALE.get(), HexcraftBlocks.BLOODSHALE_COBBLESTONE.get()));

		this.add(HexcraftBlocks.IGNEOROCK.get(),
				block -> createSingleItemTableWithSilkTouch(HexcraftBlocks.IGNEOROCK.get(), HexcraftBlocks.IGNEOROCK_COBBLESTONE.get()));

		this.add(HexcraftBlocks.MOONSTONE_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.MOONSTONE_ORE.get(), HexcraftItems.MOONSTONE.get()));

		this.add(HexcraftBlocks.DEEPSLATE_MOONSTONE_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.DEEPSLATE_MOONSTONE_ORE.get(), HexcraftItems.MOONSTONE.get()));

		this.add(HexcraftBlocks.NETHER_MOONSTONE_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.NETHER_MOONSTONE_ORE.get(), HexcraftItems.MOONSTONE.get()));

		this.add(HexcraftBlocks.END_MOONSTONE_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.END_MOONSTONE_ORE.get(), HexcraftItems.MOONSTONE.get()));

		this.add(HexcraftBlocks.SILVER_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.SILVER_ORE.get(), HexcraftItems.RAW_SILVER.get()));

		this.add(HexcraftBlocks.DEEPSLATE_SILVER_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.DEEPSLATE_SILVER_ORE.get(), HexcraftItems.RAW_SILVER.get()));

		this.add(HexcraftBlocks.NETHER_SILVER_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.NETHER_SILVER_ORE.get(), HexcraftItems.RAW_SILVER.get()));

		this.add(HexcraftBlocks.END_SILVER_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.END_SILVER_ORE.get(), HexcraftItems.RAW_SILVER.get()));

		this.add(HexcraftBlocks.VAMPIRIC_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.VAMPIRIC_ORE.get(), HexcraftItems.VAMPIRIC_GEM.get()));

		this.add(HexcraftBlocks.DEEPSLATE_VAMPIRIC_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.DEEPSLATE_VAMPIRIC_ORE.get(), HexcraftItems.VAMPIRIC_GEM.get()));

		this.add(HexcraftBlocks.NETHER_VAMPIRIC_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.NETHER_VAMPIRIC_ORE.get(), HexcraftItems.VAMPIRIC_GEM.get()));

		this.add(HexcraftBlocks.END_VAMPIRIC_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.END_VAMPIRIC_ORE.get(), HexcraftItems.VAMPIRIC_GEM.get()));

		this.add(HexcraftBlocks.NYKIUM_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.NYKIUM_ORE.get(), HexcraftItems.RAW_BLOODY_NYKIUM.get()));

		this.add(HexcraftBlocks.TRENOGEN_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.TRENOGEN_ORE.get(), HexcraftItems.RAW_CUROGEN.get()));

		this.add(HexcraftBlocks.DEEPSLATE_TRENOGEN_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.DEEPSLATE_TRENOGEN_ORE.get(), HexcraftItems.RAW_CUROGEN.get()));

		this.add(HexcraftBlocks.JORMUIM_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.JORMUIM_ORE.get(), HexcraftItems.RAW_JORMIUM.get()));

		this.add(HexcraftBlocks.SOULSTONE_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.SOULSTONE_ORE.get(), HexcraftItems.RAW_SOULSTONE.get()));

		this.add(HexcraftBlocks.ABYSSIUM_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.ABYSSIUM_ORE.get(), HexcraftItems.RAW_ABYSSIUM.get()));

		this.add(HexcraftBlocks.ECLIPSIUM_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.ECLIPSIUM_ORE.get(), HexcraftItems.RAW_ECLIPSIUM.get()));

		this.add(HexcraftBlocks.UMBRACITE_ORE.get(),
				block -> createOreDrop(HexcraftBlocks.UMBRACITE_ORE.get(), HexcraftItems.RAW_UMBRACITE.get()));

		this.add(HexcraftBlocks.ABYSSAL_COAL_ORE.get(),
				block -> createOreDropsWithCount(HexcraftBlocks.ABYSSAL_COAL_ORE.get(), HexcraftItems.ABYSSAL_COAL.get(), 1, 2));

		this.dropSelf(HexcraftBlocks.ABYSSAL_COAL_BLOCK.get());


		LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
				.hasBlockStateProperties(HexcraftBlocks.AERPINE_FLOWER.get())
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3))
				.or(LootItemBlockStatePropertyCondition
						.hasBlockStateProperties(HexcraftBlocks.AERPINE_FLOWER.get())
						.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3)));

		LootItemCondition.Builder lootitemcondition$builder2 = LootItemBlockStatePropertyCondition
				.hasBlockStateProperties(HexcraftBlocks.BELLADONNA_PLANT.get())
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3))
				.or(LootItemBlockStatePropertyCondition
						.hasBlockStateProperties(HexcraftBlocks.BELLADONNA_PLANT.get())
						.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3)));

		LootItemCondition.Builder lootitemcondition$builder3 = LootItemBlockStatePropertyCondition
				.hasBlockStateProperties(HexcraftBlocks.GARLIC_PLANT.get())
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 2))
				.or(LootItemBlockStatePropertyCondition
						.hasBlockStateProperties(HexcraftBlocks.GARLIC_PLANT.get())
						.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 2)));

		LootItemCondition.Builder lootitemcondition$builder4 = LootItemBlockStatePropertyCondition
				.hasBlockStateProperties(HexcraftBlocks.HELLEBORE_PLANT.get())
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3))
				.or(LootItemBlockStatePropertyCondition
						.hasBlockStateProperties(HexcraftBlocks.HELLEBORE_PLANT.get())
						.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3)));

		LootItemCondition.Builder lootitemcondition$builder5 = LootItemBlockStatePropertyCondition
				.hasBlockStateProperties(HexcraftBlocks.IRENIAL_FLOWER.get())
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3))
				.or(LootItemBlockStatePropertyCondition
						.hasBlockStateProperties(HexcraftBlocks.IRENIAL_FLOWER.get())
						.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3)));

		LootItemCondition.Builder lootitemcondition$builder6 = LootItemBlockStatePropertyCondition
				.hasBlockStateProperties(HexcraftBlocks.MANDRAKE_FLOWER.get())
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3))
				.or(LootItemBlockStatePropertyCondition
						.hasBlockStateProperties(HexcraftBlocks.MANDRAKE_FLOWER.get())
						.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3)));

		LootItemCondition.Builder lootitemcondition$builder7 = LootItemBlockStatePropertyCondition
				.hasBlockStateProperties(HexcraftBlocks.MIRA_FLOWER.get())
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3))
				.or(LootItemBlockStatePropertyCondition
						.hasBlockStateProperties(HexcraftBlocks.MIRA_FLOWER.get())
						.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3)));

		LootItemCondition.Builder lootitemcondition$builder8 = LootItemBlockStatePropertyCondition
				.hasBlockStateProperties(HexcraftBlocks.PERENNIA_FLOWER.get())
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3))
				.or(LootItemBlockStatePropertyCondition
						.hasBlockStateProperties(HexcraftBlocks.PERENNIA_FLOWER.get())
						.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3)));

		LootItemCondition.Builder lootitemcondition$builder9 = LootItemBlockStatePropertyCondition
				.hasBlockStateProperties(HexcraftBlocks.SAGE_PLANT.get())
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3))
				.or(LootItemBlockStatePropertyCondition
						.hasBlockStateProperties(HexcraftBlocks.SAGE_PLANT.get())
						.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3)));

		LootItemCondition.Builder lootitemcondition$builder10 = LootItemBlockStatePropertyCondition
				.hasBlockStateProperties(HexcraftBlocks.SENIA_FLOWER.get())
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3))
				.or(LootItemBlockStatePropertyCondition
						.hasBlockStateProperties(HexcraftBlocks.SENIA_FLOWER.get())
						.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3)));

		LootItemCondition.Builder lootitemcondition$builder11 = LootItemBlockStatePropertyCondition
				.hasBlockStateProperties(HexcraftBlocks.VERVAIN_FLOWER.get())
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3))
				.or(LootItemBlockStatePropertyCondition
						.hasBlockStateProperties(HexcraftBlocks.VERVAIN_FLOWER.get())
						.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3)));

		LootItemCondition.Builder lootitemcondition$builder12 = LootItemBlockStatePropertyCondition
				.hasBlockStateProperties(HexcraftBlocks.WATER_ARTICHOKE_PLANT.get())
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3))
				.or(LootItemBlockStatePropertyCondition
						.hasBlockStateProperties(HexcraftBlocks.WATER_ARTICHOKE_PLANT.get())
						.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3)));

		LootItemCondition.Builder lootitemcondition$builder13 = LootItemBlockStatePropertyCondition
				.hasBlockStateProperties(HexcraftBlocks.WOLFSBANE_FLOWER.get())
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3))
				.or(LootItemBlockStatePropertyCondition
						.hasBlockStateProperties(HexcraftBlocks.WOLFSBANE_FLOWER.get())
						.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3)));

		LootItemCondition.Builder lootitemcondition$builder14 = LootItemBlockStatePropertyCondition
				.hasBlockStateProperties(HexcraftBlocks.WORMWOOD_PLANT.get())
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3))
				.or(LootItemBlockStatePropertyCondition
						.hasBlockStateProperties(HexcraftBlocks.WORMWOOD_PLANT.get())
						.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3)));

		LootItemCondition.Builder lootitemcondition$builder15 = LootItemBlockStatePropertyCondition
				.hasBlockStateProperties(HexcraftBlocks.XERIFAE_FLOWER.get())
				.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3))
				.or(LootItemBlockStatePropertyCondition
						.hasBlockStateProperties(HexcraftBlocks.XERIFAE_FLOWER.get())
						.setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AerpinePlantBlock.AGE, 3)));


		this.add(HexcraftBlocks.AERPINE_FLOWER.get(), createCropDrops(HexcraftBlocks.AERPINE_FLOWER.get(), HexcraftItems.AERPINE.get(),
				HexcraftItems.AERPINE_SEEDS.get(), lootitemcondition$builder));

		this.add(HexcraftBlocks.BELLADONNA_PLANT.get(), createCropDrops(HexcraftBlocks.BELLADONNA_PLANT.get(), HexcraftItems.BELLADONNA.get(),
				HexcraftItems.BELLADONNA_SEEDS.get(), lootitemcondition$builder2));

		this.add(HexcraftBlocks.GARLIC_PLANT.get(), createCropDrops(HexcraftBlocks.GARLIC_PLANT.get(), HexcraftItems.GARLIC.get(),
				HexcraftItems.GARLIC.get(), lootitemcondition$builder3));

		this.add(HexcraftBlocks.HELLEBORE_PLANT.get(), createCropDrops(HexcraftBlocks.HELLEBORE_PLANT.get(), HexcraftItems.HELLEBORE.get(),
				HexcraftItems.HELLEBORE_SEEDS.get(), lootitemcondition$builder4));

		this.add(HexcraftBlocks.IRENIAL_FLOWER.get(), createCropDrops(HexcraftBlocks.IRENIAL_FLOWER.get(), HexcraftItems.IRENIAL.get(),
				HexcraftItems.IRENIAL_SEEDS.get(), lootitemcondition$builder5));

		this.add(HexcraftBlocks.MANDRAKE_FLOWER.get(), createCropDrops(HexcraftBlocks.MANDRAKE_FLOWER.get(), HexcraftItems.MANDRAKE_ROOT.get(),
				HexcraftItems.MANDRAKE_SEEDS.get(), lootitemcondition$builder6));

		this.add(HexcraftBlocks.MIRA_FLOWER.get(), createCropDrops(HexcraftBlocks.MIRA_FLOWER.get(), HexcraftItems.MIRA.get(),
				HexcraftItems.MIRA_SEEDS.get(), lootitemcondition$builder7));

		this.add(HexcraftBlocks.PERENNIA_FLOWER.get(), createCropDrops(HexcraftBlocks.PERENNIA_FLOWER.get(), HexcraftItems.PERENNIA.get(),
				HexcraftItems.PERENNIA_SEEDS.get(), lootitemcondition$builder8));

		this.add(HexcraftBlocks.SAGE_PLANT.get(), createCropDrops(HexcraftBlocks.SAGE_PLANT.get(), HexcraftItems.SAGE.get(),
				HexcraftItems.SAGE_SEEDS.get(), lootitemcondition$builder9));

		this.add(HexcraftBlocks.SENIA_FLOWER.get(), createCropDrops(HexcraftBlocks.SENIA_FLOWER.get(), HexcraftItems.SENIA.get(),
				HexcraftItems.SENIA_SEEDS.get(), lootitemcondition$builder10));

		this.add(HexcraftBlocks.VERVAIN_FLOWER.get(), createCropDrops(HexcraftBlocks.VERVAIN_FLOWER.get(), HexcraftItems.VERVAIN.get(),
				HexcraftItems.VERVAIN_SEEDS.get(), lootitemcondition$builder11));

		this.add(HexcraftBlocks.WATER_ARTICHOKE_PLANT.get(), createCropDrops(HexcraftBlocks.WATER_ARTICHOKE_PLANT.get(), HexcraftItems.WATER_ARTICHOKE.get(),
				HexcraftItems.WATER_ARTICHOKE_SEEDS.get(), lootitemcondition$builder12));

		this.add(HexcraftBlocks.WOLFSBANE_FLOWER.get(), createCropDrops(HexcraftBlocks.WOLFSBANE_FLOWER.get(), HexcraftItems.WOLFSBANE.get(),
				HexcraftItems.WOLFSBANE_SEEDS.get(), lootitemcondition$builder13));

		this.add(HexcraftBlocks.WORMWOOD_PLANT.get(), createCropDrops(HexcraftBlocks.WORMWOOD_PLANT.get(), HexcraftItems.WORMWOOD.get(),
				HexcraftItems.WORMWOOD_SEEDS.get(), lootitemcondition$builder14));

		this.add(HexcraftBlocks.XERIFAE_FLOWER.get(), createCropDrops(HexcraftBlocks.XERIFAE_FLOWER.get(), HexcraftItems.XERIFAE.get(),
				HexcraftItems.XERIFAE_SEEDS.get(), lootitemcondition$builder15));


		this.add(HexcraftBlocks.EBONY_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.EBONY_SIGN.get()));
		this.add(HexcraftBlocks.EBONY_WALL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.EBONY_SIGN.get()));
		this.add(HexcraftBlocks.EBONY_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.EBONY_HANGING_SIGN.get()));
		this.add(HexcraftBlocks.EBONY_WALL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.EBONY_HANGING_SIGN.get()));

		this.add(HexcraftBlocks.BLOOD_OAK_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.BLOOD_OAK_SIGN.get()));
		this.add(HexcraftBlocks.BLOOD_OAK_WALL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.BLOOD_OAK_SIGN.get()));
		this.add(HexcraftBlocks.BLOOD_OAK_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.BLOOD_OAK_HANGING_SIGN.get()));
		this.add(HexcraftBlocks.BLOOD_OAK_WALL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.BLOOD_OAK_HANGING_SIGN.get()));

		this.add(HexcraftBlocks.HELL_BARK_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.HELL_BARK_SIGN.get()));
		this.add(HexcraftBlocks.HELL_BARK_WALL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.HELL_BARK_SIGN.get()));
		this.add(HexcraftBlocks.HELL_BARK_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.HELL_BARK_HANGING_SIGN.get()));
		this.add(HexcraftBlocks.HELL_BARK_WALL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.HELL_BARK_HANGING_SIGN.get()));

		this.add(HexcraftBlocks.WHITE_OAK_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.WHITE_OAK_SIGN.get()));
		this.add(HexcraftBlocks.WHITE_OAK_WALL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.WHITE_OAK_SIGN.get()));
		this.add(HexcraftBlocks.WHITE_OAK_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.WHITE_OAK_HANGING_SIGN.get()));
		this.add(HexcraftBlocks.WHITE_OAK_WALL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.WHITE_OAK_HANGING_SIGN.get()));

		this.add(HexcraftBlocks.ALDER_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.ALDER_SIGN.get()));
		this.add(HexcraftBlocks.ALDER_WALL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.ALDER_SIGN.get()));
		this.add(HexcraftBlocks.ALDER_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.ALDER_HANGING_SIGN.get()));
		this.add(HexcraftBlocks.ALDER_WALL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.ALDER_HANGING_SIGN.get()));

		this.add(HexcraftBlocks.WITCH_HAZEL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.WITCH_HAZEL_SIGN.get()));
		this.add(HexcraftBlocks.WITCH_HAZEL_WALL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.WITCH_HAZEL_SIGN.get()));
		this.add(HexcraftBlocks.WITCH_HAZEL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.WITCH_HAZEL_HANGING_SIGN.get()));
		this.add(HexcraftBlocks.WITCH_HAZEL_WALL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.WITCH_HAZEL_HANGING_SIGN.get()));

		this.add(HexcraftBlocks.WILLOW_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.WILLOW_SIGN.get()));
		this.add(HexcraftBlocks.WILLOW_WALL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.WILLOW_SIGN.get()));
		this.add(HexcraftBlocks.WILLOW_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.WILLOW_HANGING_SIGN.get()));
		this.add(HexcraftBlocks.WILLOW_WALL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.WILLOW_HANGING_SIGN.get()));

		this.add(HexcraftBlocks.HAWTHORN_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.HAWTHORN_SIGN.get()));
		this.add(HexcraftBlocks.HAWTHORN_WALL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.HAWTHORN_SIGN.get()));
		this.add(HexcraftBlocks.HAWTHORN_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.HAWTHORN_HANGING_SIGN.get()));
		this.add(HexcraftBlocks.HAWTHORN_WALL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.HAWTHORN_HANGING_SIGN.get()));

		this.add(HexcraftBlocks.CEDAR_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.CEDAR_SIGN.get()));
		this.add(HexcraftBlocks.CEDAR_WALL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.CEDAR_SIGN.get()));
		this.add(HexcraftBlocks.CEDAR_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.CEDAR_HANGING_SIGN.get()));
		this.add(HexcraftBlocks.CEDAR_WALL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.CEDAR_HANGING_SIGN.get()));

		this.add(HexcraftBlocks.DISTORTED_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.DISTORTED_SIGN.get()));
		this.add(HexcraftBlocks.DISTORTED_WALL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.DISTORTED_SIGN.get()));
		this.add(HexcraftBlocks.DISTORTED_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.DISTORTED_HANGING_SIGN.get()));
		this.add(HexcraftBlocks.DISTORTED_WALL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.DISTORTED_HANGING_SIGN.get()));

		this.add(HexcraftBlocks.ELDER_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.ELDER_SIGN.get()));
		this.add(HexcraftBlocks.ELDER_WALL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.ELDER_SIGN.get()));
		this.add(HexcraftBlocks.ELDER_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.ELDER_HANGING_SIGN.get()));
		this.add(HexcraftBlocks.ELDER_WALL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.ELDER_HANGING_SIGN.get()));

		this.add(HexcraftBlocks.JUNIPER_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.JUNIPER_SIGN.get()));
		this.add(HexcraftBlocks.JUNIPER_WALL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.JUNIPER_SIGN.get()));
		this.add(HexcraftBlocks.JUNIPER_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.JUNIPER_HANGING_SIGN.get()));
		this.add(HexcraftBlocks.JUNIPER_WALL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.JUNIPER_HANGING_SIGN.get()));

		this.add(HexcraftBlocks.ROWAN_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.ROWAN_SIGN.get()));
		this.add(HexcraftBlocks.ROWAN_WALL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.ROWAN_SIGN.get()));
		this.add(HexcraftBlocks.ROWAN_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.ROWAN_HANGING_SIGN.get()));
		this.add(HexcraftBlocks.ROWAN_WALL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.ROWAN_HANGING_SIGN.get()));

		this.add(HexcraftBlocks.TWISTED_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.TWISTED_SIGN.get()));
		this.add(HexcraftBlocks.TWISTED_WALL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.TWISTED_SIGN.get()));
		this.add(HexcraftBlocks.TWISTED_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.TWISTED_HANGING_SIGN.get()));
		this.add(HexcraftBlocks.TWISTED_WALL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.TWISTED_HANGING_SIGN.get()));

		this.add(HexcraftBlocks.WITCH_WOOD_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.WITCH_WOOD_SIGN.get()));
		this.add(HexcraftBlocks.WITCH_WOOD_WALL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.WITCH_WOOD_SIGN.get()));
		this.add(HexcraftBlocks.WITCH_WOOD_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.WITCH_WOOD_HANGING_SIGN.get()));
		this.add(HexcraftBlocks.WITCH_WOOD_WALL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.WITCH_WOOD_HANGING_SIGN.get()));

		this.add(HexcraftBlocks.ECHO_WOOD_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.ECHO_WOOD_SIGN.get()));
		this.add(HexcraftBlocks.ECHO_WOOD_WALL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.ECHO_WOOD_SIGN.get()));
		this.add(HexcraftBlocks.ECHO_WOOD_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.ECHO_WOOD_HANGING_SIGN.get()));
		this.add(HexcraftBlocks.ECHO_WOOD_WALL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.ECHO_WOOD_HANGING_SIGN.get()));

		this.add(HexcraftBlocks.PHOENIX_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.PHOENIX_SIGN.get()));
		this.add(HexcraftBlocks.PHOENIX_WALL_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.PHOENIX_SIGN.get()));
		this.add(HexcraftBlocks.PHOENIX_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.PHOENIX_HANGING_SIGN.get()));
		this.add(HexcraftBlocks.PHOENIX_WALL_HANGING_SIGN.get(), block ->
				createSingleItemTable(HexcraftItems.PHOENIX_HANGING_SIGN.get()));

		addChestLootTable(HexcraftBlocks.EBONY_CHEST.get());
		addChestLootTable(HexcraftBlocks.BLOOD_OAK_CHEST.get());
		addChestLootTable(HexcraftBlocks.HELL_BARK_CHEST.get());
		addChestLootTable(HexcraftBlocks.WHITE_OAK_CHEST.get());
		addChestLootTable(HexcraftBlocks.ALDER_CHEST.get());
		addChestLootTable(HexcraftBlocks.WITCH_HAZEL_CHEST.get());
		addChestLootTable(HexcraftBlocks.WILLOW_CHEST.get());
		addChestLootTable(HexcraftBlocks.HAWTHORN_CHEST.get());
		addChestLootTable(HexcraftBlocks.CEDAR_CHEST.get());
		addChestLootTable(HexcraftBlocks.DISTORTED_CHEST.get());
		addChestLootTable(HexcraftBlocks.ELDER_CHEST.get());
		addChestLootTable(HexcraftBlocks.JUNIPER_CHEST.get());
		addChestLootTable(HexcraftBlocks.ROWAN_CHEST.get());
		addChestLootTable(HexcraftBlocks.TWISTED_CHEST.get());
		addChestLootTable(HexcraftBlocks.WITCH_WOOD_CHEST.get());
		addChestLootTable(HexcraftBlocks.ECHO_WOOD_CHEST.get());
		addChestLootTable(HexcraftBlocks.PHOENIX_CHEST.get());

	}

	private void dropOther(Item item) {
	}

	protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
		return createSilkTouchDispatchTable(pBlock,
				this.applyExplosionDecay(pBlock,
						LootItem.lootTableItem(item)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
								.apply(ApplyBonusCount.addOreBonusCount(Enchantments.SILK_TOUCH))));

	}

	private static LootTable.Builder createBloodOakLeavesDrops(Block block, Supplier<Item> saplingSupplier, Supplier<Item> bloodAppleSupplier) {
		return LootTable.lootTable()
				.withPool(
						LootPool.lootPool()
								.setRolls(UniformGenerator.between(1, 2)) // Mimics vanilla leaf drop behavior
								.add(LootItem.lootTableItem(saplingSupplier.get())
										.when(LootItemRandomChanceCondition.randomChance(0.05f))) // 5% sapling drop chance
								.add(LootItem.lootTableItem(Items.STICK)
										.when(LootItemRandomChanceCondition.randomChance(0.02f))) // 2% stick drop chance
								.add(LootItem.lootTableItem(bloodAppleSupplier.get())
										.when(LootItemRandomChanceCondition.randomChance(0.005f))) // 0.5% Blood Apple drop chance
				);
	}

	private static LootTable.Builder createJuniperLeavesDrops(Block block, Supplier<Item> saplingSupplier, Supplier<Item> juniperBerriesSupplier) {
		return LootTable.lootTable()
				.withPool(
						LootPool.lootPool()
								.setRolls(UniformGenerator.between(1, 2)) // Mimics vanilla leaf drop behavior
								.add(LootItem.lootTableItem(saplingSupplier.get())
										.when(LootItemRandomChanceCondition.randomChance(0.05f))) // 5% sapling drop chance
								.add(LootItem.lootTableItem(Items.STICK)
										.when(LootItemRandomChanceCondition.randomChance(0.02f))) // 2% stick drop chance
								.add(LootItem.lootTableItem(juniperBerriesSupplier.get())
										.when(LootItemRandomChanceCondition.randomChance(0.05f))) // 5% Juniper Berries drop chance
				);
	}

	private void addChestLootTable(Block chestBlock) {
		add(chestBlock, LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(chestBlock)))
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(Items.CHEST))
						.when(ExplosionCondition.survivesExplosion()))
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(Items.CHEST)
								.apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY)))
						.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(chestBlock)))
		);

	}

	public void dropDoubleWithSilk(Block block, ItemLike drop) {
		this.add(block, (result) -> this.droppingDoubleWithSilkTouch(result, drop));
	}

	public LootTable.Builder droppingDoubleWithSilkTouch(Block block, ItemLike noSilkTouch) {
		return this.droppingDoubleWithSilkTouch(block, (ItemLike) this.applyExplosionCondition(block, LootItem.lootTableItem(noSilkTouch)));
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return HexcraftBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
	}

	public LootTable.Builder createOreDropsWithCount(Block block, ItemLike drop, int min, int max) {
		return LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						// If mined with Silk Touch, drop the ore block instead
						.when(MatchTool.toolMatches(ItemPredicate.Builder.item()
								.hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1)))))
						.add(LootItem.lootTableItem(block)) // Drops the ore block when Silk Touch is applied
				)
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.when(InvertedLootItemCondition.invert(
								MatchTool.toolMatches(ItemPredicate.Builder.item()
										.hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))))
						.add(LootItem.lootTableItem(drop) // Drops raw item when not using Silk Touch
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))) // Drops between min-max
								.apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)) // Fortune applies to drops
								.apply(ApplyExplosionDecay.explosionDecay()) // Handles explosion logic
						)
				);
	}

	private void dropMushroomLike(RegistryObject<Block> block, RegistryObject<Block> mushroom) {
		this.add(block.get(),
				createSilkTouchDispatchTable(
						block.get(),
						applyExplosionDecay(block.get(),
								LootItem.lootTableItem(mushroom.get())
										.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
						)
				)
		);

	}


	protected void add(Block pBlock, LootTable.Builder pBuilder) {
		this.map.put(pBlock.getLootTable(), pBuilder);
	}

	static {
		HAS_SHEARS = MatchTool.toolMatches(net.minecraft.advancements.critereon.ItemPredicate.Builder.item().of(new ItemLike[]{Items.SHEARS}));
	}
}