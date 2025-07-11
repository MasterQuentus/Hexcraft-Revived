package net.masterquentus.hexcraftmod.datagen;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.masterquentus.hexcraftmod.recipe.WitchesCauldronRecipeBuilder;
import net.masterquentus.hexcraftmod.recipe.WitchesOvenRecipeBuilder;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class HexcraftRecipeProvider extends RecipeProvider implements IConditionBuilder {

	// Metal & Ore Smeltables (Higher Priority)
	private static final List<ItemLike> SILVER_SMELTABLES = List.of(
			HexcraftItems.RAW_SILVER.get(),
			HexcraftBlocks.SILVER_ORE.get(),
			HexcraftBlocks.DEEPSLATE_SILVER_ORE.get(),
			HexcraftBlocks.NETHER_SILVER_ORE.get(),
			HexcraftBlocks.END_SILVER_ORE.get()
	);
	private static final List<ItemLike> MOONSTONE_SMELTABLES = List.of(
			HexcraftBlocks.MOONSTONE_ORE.get(),
			HexcraftBlocks.DEEPSLATE_MOONSTONE_ORE.get(),
			HexcraftBlocks.NETHER_MOONSTONE_ORE.get(),
			HexcraftBlocks.END_MOONSTONE_ORE.get()
	);
	private static final List<ItemLike> VAMPIRIC_SMELTABLES = List.of(
			HexcraftBlocks.VAMPIRIC_ORE.get(),
			HexcraftBlocks.DEEPSLATE_VAMPIRIC_ORE.get(),
			HexcraftBlocks.NETHER_VAMPIRIC_ORE.get(),
			HexcraftBlocks.END_VAMPIRIC_ORE.get()
	);
	private static final List<ItemLike> NYKIUM_SMELTABLES = List.of(
			HexcraftItems.RAW_BLOODY_NYKIUM.get(),
			HexcraftBlocks.NYKIUM_ORE.get()
	);
	private static final List<ItemLike> TRENOGEN_SMELTABLES = List.of(
			HexcraftItems.RAW_CUROGEN.get(),
			HexcraftBlocks.TRENOGEN_ORE.get(),
			HexcraftBlocks.DEEPSLATE_TRENOGEN_ORE.get()
	);
	private static final List<ItemLike> JORMUIM_SMELTABLES = List.of(
			HexcraftItems.RAW_JORMIUM.get(),
			HexcraftBlocks.JORMUIM_ORE.get()
	);
	private static final List<ItemLike> SOULSTONE_SMELTABLES = List.of(
			HexcraftItems.RAW_SOULSTONE.get(),
			HexcraftBlocks.SOULSTONE_ORE.get()
	);
	private static final List<ItemLike> ABYSSIUM_SMELTABLES = List.of(
			HexcraftItems.RAW_ABYSSIUM.get(),
			HexcraftBlocks.ABYSSIUM_ORE.get()
	);
	private static final List<ItemLike> ECLIPSIUM_SMELTABLES = List.of(
			HexcraftItems.RAW_ECLIPSIUM.get(),
			HexcraftBlocks.ECLIPSIUM_ORE.get()
	);

	private static final List<ItemLike> NYKIUM_INGOT_SMELTABLES = List.of(
			HexcraftItems.BLOODY_NYKIUM.get()
	);

	// Leather & Miscellaneous Smeltables
	private static final List<ItemLike> LEATHER_SMELTABLES = List.of(Items.ROTTEN_FLESH);
	private static final List<ItemLike> TANNED_LEATHER_SMELTABLES = List.of(HexcraftItems.BOUND_LEATHER.get());
	private static final List<ItemLike> STEEL_SMELTABLES = List.of(HexcraftItems.STEEL_POWDER.get());

	// Clay Smeltables
	private static final List<ItemLike> CLAY_SMELTABLES = List.of(HexcraftItems.UNFIRED_CLAY_POT.get());

	// Pearl & Stone Smeltables
	private static final List<ItemLike> PEARL_STONE_SMELTABLES = List.of(HexcraftBlocks.PEARL_COBBLESTONE.get());
	private static final List<ItemLike> PEARL_STONE_BRICKS_SMELTABLES = List.of(HexcraftBlocks.PEARL_STONE_BRICKS.get());
	private static final List<ItemLike> CRIMSON_STONE_SMELTABLES = List.of(HexcraftBlocks.CRIMSON_COBBLESTONE.get());
	private static final List<ItemLike> CRIMSON_STONE_BRICKS_SMELTABLES = List.of(HexcraftBlocks.CRIMSON_STONE_BRICKS.get());
	private static final List<ItemLike> UNDER_WORLD_STONE_SMELTABLES = List.of(HexcraftBlocks.UNDER_WORLD_COBBLESTONE.get());
	private static final List<ItemLike> UNDER_WORLD_STONE_BRICKS_SMELTABLES = List.of(HexcraftBlocks.UNDER_WORLD_STONE_BRICKS.get());
	private static final List<ItemLike> CHARD_STONE_SMELTABLES = List.of(HexcraftBlocks.CHARSTONE_COBBLESTONE.get());
	private static final List<ItemLike> CHARD_STONE_BRICKS_SMELTABLES = List.of(HexcraftBlocks.CHARSTONE_BRICKS.get());

	// Sandstone & Glass Smeltables
	private static final List<ItemLike> SMOOTH_CRIMSON_SAND_STONE_SMELTABLES = List.of(HexcraftBlocks.CRIMSON_SAND_STONE.get());
	private static final List<ItemLike> CRIMSON_SAND_SMELTABLES = List.of(HexcraftBlocks.CRIMSON_SAND.get());
	private static final List<ItemLike> SMOOTH_FAIRY_SAND_STONE_SMELTABLES = List.of(HexcraftBlocks.FAIRY_SAND_STONE.get());
	private static final List<ItemLike> FAIRY_SAND_SMELTABLES = List.of(HexcraftBlocks.FAIRY_SAND.get());
	private static final List<ItemLike> SMOOTH_PIXIE_SAND_STONE_SMELTABLES = List.of(HexcraftBlocks.PIXIE_SAND_STONE.get());
	private static final List<ItemLike> PIXIE_SAND_SMELTABLES = List.of(HexcraftBlocks.PIXIE_SAND.get());

	// Blister Cactus Smeltables
	private static final List<ItemLike> BLISTER_CACTUS_FRUIT_SMELTABLES = List.of(HexcraftItems.BLISTER_CACTUS_FRUIT.get());


	public HexcraftRecipeProvider(PackOutput pOutput) {
		super(pOutput);

	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
		// Metals & Gems (Higher Priority)
		oreSmelting(pWriter, SILVER_SMELTABLES, RecipeCategory.MISC, HexcraftItems.SILVER_INGOT.get(), 0.7f, 200, "silver_ingot");
		oreBlasting(pWriter, SILVER_SMELTABLES, RecipeCategory.MISC, HexcraftItems.SILVER_INGOT.get(), 0.7f, 100, "silver_ingot");

		oreSmelting(pWriter, MOONSTONE_SMELTABLES, RecipeCategory.MISC, HexcraftItems.MOONSTONE.get(), 0.7f, 200, "moonstone");
		oreBlasting(pWriter, MOONSTONE_SMELTABLES, RecipeCategory.MISC, HexcraftItems.MOONSTONE.get(), 0.7f, 100, "moonstone");

		oreSmelting(pWriter, VAMPIRIC_SMELTABLES, RecipeCategory.MISC, HexcraftItems.VAMPIRIC_GEM.get(), 0.7f, 200, "vampiric_gem");
		oreBlasting(pWriter, VAMPIRIC_SMELTABLES, RecipeCategory.MISC, HexcraftItems.VAMPIRIC_GEM.get(), 0.7f, 100, "vampiric_gem");

		oreSmelting(pWriter, NYKIUM_SMELTABLES, RecipeCategory.MISC, HexcraftItems.BLOODY_NYKIUM.get(), 0.7f, 200, "bloody_nykium");
		oreBlasting(pWriter, NYKIUM_SMELTABLES, RecipeCategory.MISC, HexcraftItems.BLOODY_NYKIUM.get(), 0.7f, 100, "bloody_nykium");

		oreSmelting(pWriter, TRENOGEN_SMELTABLES, RecipeCategory.MISC, HexcraftItems.CUROGEN.get(), 0.7f, 200, "curogen");
		oreBlasting(pWriter, TRENOGEN_SMELTABLES, RecipeCategory.MISC, HexcraftItems.CUROGEN.get(), 0.7f, 100, "curogen");

		oreSmelting(pWriter, JORMUIM_SMELTABLES, RecipeCategory.MISC, HexcraftItems.JORMIUM_INGOT.get(), 0.7f, 200, "jormium_ingot");
		oreBlasting(pWriter, JORMUIM_SMELTABLES, RecipeCategory.MISC, HexcraftItems.JORMIUM_INGOT.get(), 0.7f, 100, "jormium_ingot");

		oreSmelting(pWriter, STEEL_SMELTABLES, RecipeCategory.MISC, HexcraftItems.STEEL_INGOT.get(), 0.7f, 200, "steel_ingot");
		oreBlasting(pWriter, STEEL_SMELTABLES, RecipeCategory.MISC, HexcraftItems.STEEL_INGOT.get(), 0.7f, 100, "steel_ingot");

		oreSmelting(pWriter, SOULSTONE_SMELTABLES, RecipeCategory.MISC, HexcraftItems.SOULSTONE_INGOT.get(), 0.7f, 200, "soulstone_ingot");
		oreSmelting(pWriter, ABYSSIUM_SMELTABLES, RecipeCategory.MISC, HexcraftItems.ABYSSIUM_INGOT.get(), 0.7f, 200, "abyssium_ingot");
		oreSmelting(pWriter, ECLIPSIUM_SMELTABLES, RecipeCategory.MISC, HexcraftItems.ECLIPSIUM_INGOT.get(), 0.7f, 200, "eclipsium_ingot");
		oreBlasting(pWriter, SOULSTONE_SMELTABLES, RecipeCategory.MISC, HexcraftItems.SOULSTONE_INGOT.get(), 0.7f, 100, "soulstone_ingot");
		oreBlasting(pWriter, ABYSSIUM_SMELTABLES, RecipeCategory.MISC, HexcraftItems.ABYSSIUM_INGOT.get(), 0.7f, 100, "abyssium_ingot");
		oreBlasting(pWriter, ECLIPSIUM_SMELTABLES, RecipeCategory.MISC, HexcraftItems.ECLIPSIUM_INGOT.get(), 0.7f, 100, "eclipsium_ingot");

		oreSmelting(pWriter, NYKIUM_INGOT_SMELTABLES, RecipeCategory.FOOD, HexcraftItems.NYKIUM_INGOT.get(), 0.3f, 200, "nykium_ingot");

		// Leather & Miscellaneous
		oreSmelting(pWriter, LEATHER_SMELTABLES, RecipeCategory.MISC, Items.LEATHER, 0.3f, 150, "leather");
		oreBlasting(pWriter, LEATHER_SMELTABLES, RecipeCategory.MISC, Items.LEATHER, 0.3f, 80, "leather");

		oreSmelting(pWriter, TANNED_LEATHER_SMELTABLES, RecipeCategory.MISC, HexcraftItems.TANNED_LEATHER.get(), 0.2f, 150, "tanned_leather");
		oreBlasting(pWriter, TANNED_LEATHER_SMELTABLES, RecipeCategory.MISC, HexcraftItems.TANNED_LEATHER.get(), 0.2f, 80, "tanned_leather");

		oreSmelting(pWriter, BLISTER_CACTUS_FRUIT_SMELTABLES, RecipeCategory.FOOD, HexcraftItems.BLISTER_FRUIT.get(), 0.3f, 200, "cooked_blister_fruit");

		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(HexcraftItems.BLISTER_CACTUS_FRUIT.get()), RecipeCategory.FOOD, HexcraftItems.BLISTER_FRUIT.get(), 0.3f, 600)
				.unlockedBy(getHasName(HexcraftItems.BLISTER_CACTUS_FRUIT.get()), has(HexcraftItems.BLISTER_CACTUS_FRUIT.get())).save(pWriter, HexcraftMod.MOD_ID + ":cooked_blister_fruit_campfire");

		SimpleCookingRecipeBuilder.smoking(Ingredient.of(HexcraftItems.BLISTER_CACTUS_FRUIT.get()), RecipeCategory.FOOD, HexcraftItems.BLISTER_FRUIT.get(), 0.3f, 100)
				.unlockedBy(getHasName(HexcraftItems.BLISTER_CACTUS_FRUIT.get()), has(HexcraftItems.BLISTER_CACTUS_FRUIT.get())).save(pWriter, HexcraftMod.MOD_ID + ":cooked_blister_fruit_smoker");

		// Stone & Bricks (No Blasting)
		oreSmelting(pWriter, PEARL_STONE_SMELTABLES, RecipeCategory.MISC, HexcraftBlocks.PEARL_STONE.get(), 0.2f, 200, "pearl_stone");
		oreSmelting(pWriter, PEARL_STONE_BRICKS_SMELTABLES, RecipeCategory.MISC, HexcraftBlocks.CRACKED_PEARL_STONE.get(), 0.2f, 200, "cracked_pearl_stone");
		oreSmelting(pWriter, CRIMSON_STONE_SMELTABLES, RecipeCategory.MISC, HexcraftBlocks.CRIMSON_STONE.get(), 0.2f, 200, "crimson_stone");
		oreSmelting(pWriter, CRIMSON_STONE_BRICKS_SMELTABLES, RecipeCategory.MISC, HexcraftBlocks.CRACKED_CRIMSON_STONE.get(), 0.2f, 200, "cracked_crimson_stone");
		oreSmelting(pWriter, UNDER_WORLD_STONE_SMELTABLES, RecipeCategory.MISC, HexcraftBlocks.UNDER_WORLD_STONE.get(), 0.2f, 200, "under_world_stone");
		oreSmelting(pWriter, UNDER_WORLD_STONE_BRICKS_SMELTABLES, RecipeCategory.MISC, HexcraftBlocks.CRACKED_UNDER_WORLD_STONE.get(), 0.2f, 200, "cracked_under_world_stone");
		oreSmelting(pWriter, CHARD_STONE_SMELTABLES, RecipeCategory.MISC, HexcraftBlocks.CHARSTONE.get(), 0.2f, 200, "charstone");
		oreSmelting(pWriter, CHARD_STONE_BRICKS_SMELTABLES, RecipeCategory.MISC, HexcraftBlocks.CRACKED_CHARSTONE.get(), 0.2f, 200, "cracked_charstone");

		// Sandstone & Glass (Shorter Smelting Time)
		oreSmelting(pWriter, SMOOTH_CRIMSON_SAND_STONE_SMELTABLES, RecipeCategory.MISC, HexcraftBlocks.SMOOTH_CRIMSON_SAND_STONE.get(), 0.1f, 150, "smooth_crimson_sand_stone");
		oreSmelting(pWriter, CRIMSON_SAND_SMELTABLES, RecipeCategory.MISC, HexcraftBlocks.CRIMSON_GLASS.get(), 0.1f, 150, "crimson_glass");
		oreSmelting(pWriter, SMOOTH_FAIRY_SAND_STONE_SMELTABLES, RecipeCategory.MISC, HexcraftBlocks.SMOOTH_FAIRY_SAND_STONE.get(), 0.1f, 150, "smooth_fairy_sand_stone");
		oreSmelting(pWriter, FAIRY_SAND_SMELTABLES, RecipeCategory.MISC, HexcraftBlocks.FAIRY_GLASS.get(), 0.1f, 150, "fairy_glass");
		oreSmelting(pWriter, SMOOTH_PIXIE_SAND_STONE_SMELTABLES, RecipeCategory.MISC, HexcraftBlocks.SMOOTH_PIXIE_SAND_STONE.get(), 0.1f, 150, "smooth_pixie_sand_stone");
		oreSmelting(pWriter, PIXIE_SAND_SMELTABLES, RecipeCategory.MISC, HexcraftBlocks.PIXIE_GLASS.get(), 0.1f, 150, "pixie_glass");




		//Shaped
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.AIR_RUNE.get())
				.pattern("BBB")
				.pattern("BFB")
				.pattern("BBB")
				.define('B', HexcraftItems.BLANK_RUNE.get())
				.define('F', Items.FEATHER)
				.unlockedBy(getHasName(HexcraftItems.BLANK_RUNE.get()), has(HexcraftItems.BLANK_RUNE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.BLACK_RUNE.get())
				.pattern("BBB")
				.pattern("BDB")
				.pattern("BBB")
				.define('B', HexcraftItems.BLANK_RUNE.get())
				.define('D', Items.BLACK_DYE)
				.unlockedBy(getHasName(HexcraftItems.BLANK_RUNE.get()), has(HexcraftItems.BLANK_RUNE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.BLANK_RUNE.get())
				.pattern(" C ")
				.pattern("CCC")
				.pattern("CC ")
				.define('C', Items.COBBLESTONE)
				.unlockedBy(getHasName(HexcraftItems.BLANK_RUNE.get()), has(HexcraftItems.BLANK_RUNE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.BLUE_RUNE.get())
				.pattern("BBB")
				.pattern("BDB")
				.pattern("BBB")
				.define('B', HexcraftItems.BLANK_RUNE.get())
				.define('D', Items.BLUE_DYE)
				.unlockedBy(getHasName(HexcraftItems.BLANK_RUNE.get()), has(HexcraftItems.BLANK_RUNE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.GREEN_RUNE.get())
				.pattern("BBB")
				.pattern("BGB")
				.pattern("BBB")
				.define('B', HexcraftItems.BLANK_RUNE.get())
				.define('G', Items.GREEN_DYE)
				.unlockedBy(getHasName(HexcraftItems.BLANK_RUNE.get()), has(HexcraftItems.BLANK_RUNE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.EARTH_RUNE.get())
				.pattern("BBB")
				.pattern("BVB")
				.pattern("BBB")
				.define('B', HexcraftItems.BLANK_RUNE.get())
				.define('V', Items.VINE)
				.unlockedBy(getHasName(HexcraftItems.BLANK_RUNE.get()), has(HexcraftItems.BLANK_RUNE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.FIRE_RUNE.get())
				.pattern("BBB")
				.pattern("BLB")
				.pattern("BBB")
				.define('B', HexcraftItems.BLANK_RUNE.get())
				.define('L', Items.LAVA_BUCKET)
				.unlockedBy(getHasName(HexcraftItems.BLANK_RUNE.get()), has(HexcraftItems.BLANK_RUNE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.ORANGE_RUNE.get())
				.pattern("BBB")
				.pattern("BOB")
				.pattern("BBB")
				.define('B', HexcraftItems.BLANK_RUNE.get())
				.define('O', Items.ORANGE_DYE)
				.unlockedBy(getHasName(HexcraftItems.BLANK_RUNE.get()), has(HexcraftItems.BLANK_RUNE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.PURPLE_RUNE.get())
				.pattern("BBB")
				.pattern("BPB")
				.pattern("BBB")
				.define('B', HexcraftItems.BLANK_RUNE.get())
				.define('P', Items.PURPLE_DYE)
				.unlockedBy(getHasName(HexcraftItems.BLANK_RUNE.get()), has(HexcraftItems.BLANK_RUNE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.RED_RUNE.get())
				.pattern("BBB")
				.pattern("BRB")
				.pattern("BBB")
				.define('B', HexcraftItems.BLANK_RUNE.get())
				.define('R', Items.RED_DYE)
				.unlockedBy(getHasName(HexcraftItems.BLANK_RUNE.get()), has(HexcraftItems.BLANK_RUNE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.WATER_RUNE.get())
				.pattern("BBB")
				.pattern("BWB")
				.pattern("BBB")
				.define('B', HexcraftItems.BLANK_RUNE.get())
				.define('W', Items.WATER_BUCKET)
				.unlockedBy(getHasName(HexcraftItems.BLANK_RUNE.get()), has(HexcraftItems.BLANK_RUNE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.WHITE_RUNE.get())
				.pattern("BBB")
				.pattern("BWB")
				.pattern("BBB")
				.define('B', HexcraftItems.BLANK_RUNE.get())
				.define('W', Items.WHITE_DYE)
				.unlockedBy(getHasName(HexcraftItems.BLANK_RUNE.get()), has(HexcraftItems.BLANK_RUNE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.YELLOW_RUNE.get())
				.pattern("BBB")
				.pattern("BYB")
				.pattern("BBB")
				.define('B', HexcraftItems.BLANK_RUNE.get())
				.define('Y', Items.YELLOW_DYE)
				.unlockedBy(getHasName(HexcraftItems.BLANK_RUNE.get()), has(HexcraftItems.BLANK_RUNE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.LIVING_KELP_BLOCK.get())
				.pattern("LLL")
				.pattern("LLL")
				.pattern("LLL")
				.define('L', HexcraftItems.LIVING_KELP_ITEM.get())
				.unlockedBy(getHasName(HexcraftItems.LIVING_KELP_ITEM.get()), has(HexcraftItems.LIVING_KELP_ITEM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.BLOODY_NYKIUM.get())
				.pattern("BBB")
				.pattern("BBB")
				.pattern("BBB")
				.define('B', HexcraftItems.BLOODY_NYKIUM_NUGGET.get())
				.unlockedBy(getHasName(HexcraftItems.BLOODY_NYKIUM.get()), has(HexcraftItems.BLOODY_NYKIUM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.CORRUPTED_STEEL.get())
				.pattern("CCC")
				.pattern("CCC")
				.pattern("CCC")
				.define('C', HexcraftItems.CORRUPTED_STEEL_NUGGET.get())
				.unlockedBy(getHasName(HexcraftItems.CORRUPTED_STEEL.get()), has(HexcraftItems.CORRUPTED_STEEL.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.CUROGEN.get())
				.pattern("CCC")
				.pattern("CCC")
				.pattern("CCC")
				.define('C', HexcraftItems.CUROGEN_NUGGET.get())
				.unlockedBy(getHasName(HexcraftItems.CUROGEN.get()), has(HexcraftItems.CUROGEN.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.DARK_STEEL.get())
				.pattern("DDD")
				.pattern("DDD")
				.pattern("DDD")
				.define('D', HexcraftItems.DARK_STEEL_NUGGET.get())
				.unlockedBy(getHasName(HexcraftItems.DARK_STEEL.get()), has(HexcraftItems.DARK_STEEL.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.JORMIUM_INGOT.get())
				.pattern("JJJ")
				.pattern("JJJ")
				.pattern("JJJ")
				.define('J', HexcraftItems.JORMIUM_NUGGET.get())
				.unlockedBy(getHasName(HexcraftItems.JORMIUM_INGOT.get()), has(HexcraftItems.JORMIUM_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.BOUND_LEATHER.get())
				.pattern("SSS")
				.pattern("LSL")
				.pattern("SSS")
				.define('S', Items.STRING)
				.define('L', Items.LEATHER)
				.unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.ROPE.get())
				.pattern("SV ")
				.pattern("VS ")
				.pattern("SV ")
				.define('S', Items.STRING)
				.define('V', Items.VINE)
				.unlockedBy(getHasName(Items.VINE), has(Items.VINE))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.UNFIRED_CLAY_POT.get())
				.pattern(" C ")
				.pattern("CCC")
				.pattern("   ")
				.define('C', Items.CLAY_BALL)
				.unlockedBy(getHasName(Items.CLAY_BALL), has(Items.CLAY_BALL))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCHES_OVEN.get())
				.pattern(" B ")
				.pattern("III")
				.pattern("IBI")
				.define('B', Items.IRON_BARS)
				.define('I', Items.IRON_INGOT)
				.unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.MOONSTONE_BLOCK.get())
				.pattern("MMM")
				.pattern("MMM")
				.pattern("MMM")
				.define('M', HexcraftItems.MOONSTONE.get())
				.unlockedBy(getHasName(HexcraftItems.MOONSTONE.get()), has(HexcraftItems.MOONSTONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.SILVER_BLOCK.get())
				.pattern("SSS")
				.pattern("SSS")
				.pattern("SSS")
				.define('S', HexcraftItems.SILVER_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.SILVER_INGOT.get()), has(HexcraftItems.SILVER_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.VAMPIRIC_BLOCK.get())
				.pattern("VVV")
				.pattern("VVV")
				.pattern("VVV")
				.define('V', HexcraftItems.VAMPIRIC_GEM.get())
				.unlockedBy(getHasName(HexcraftItems.VAMPIRIC_GEM.get()), has(HexcraftItems.VAMPIRIC_GEM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.SOULSTONE_BLOCK.get())
				.pattern("SSS")
				.pattern("SSS")
				.pattern("SSS")
				.define('S', HexcraftItems.SOULSTONE_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.SOULSTONE_INGOT.get()), has(HexcraftItems.SOULSTONE_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ABYSSIUM_BLOCK.get())
				.pattern("AAA")
				.pattern("AAA")
				.pattern("AAA")
				.define('A', HexcraftItems.ABYSSIUM_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.ABYSSIUM_INGOT.get()), has(HexcraftItems.ABYSSIUM_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ECLIPSIUM_BLOCK.get())
				.pattern("EEE")
				.pattern("EEE")
				.pattern("EEE")
				.define('E', HexcraftItems.ECLIPSIUM_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.ECLIPSIUM_INGOT.get()), has(HexcraftItems.ECLIPSIUM_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ABYSSAL_COAL_BLOCK.get())
				.pattern("AAA")
				.pattern("AAA")
				.pattern("AAA")
				.define('A', HexcraftItems.ABYSSAL_COAL.get())
				.unlockedBy(getHasName(HexcraftItems.ABYSSAL_COAL.get()), has(HexcraftItems.ABYSSAL_COAL.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.PEARL_STONE_WALL.get(), 6)
				.pattern("PPP")
				.pattern("PPP")
				.define('P', HexcraftBlocks.PEARL_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.PEARL_STONE.get()), has(HexcraftBlocks.PEARL_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.CRIMSON_STONE_WALL.get(), 6)
				.pattern("CCC")
				.pattern("CCC")
				.define('C', HexcraftBlocks.CRIMSON_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_STONE.get()), has(HexcraftBlocks.CRIMSON_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.UNDER_WORLD_STONE_WALL.get(), 6)
				.pattern("UUU")
				.pattern("UUU")
				.define('U', HexcraftBlocks.UNDER_WORLD_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.UNDER_WORLD_STONE.get()), has(HexcraftBlocks.UNDER_WORLD_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CHARSTONE_WALL.get(), 6)
				.pattern("CCC")
				.pattern("CCC")
				.define('C', HexcraftBlocks.CHARSTONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CHARSTONE.get()), has(HexcraftBlocks.CHARSTONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CRIMSON_SAND_STONE_WALL.get(), 6)
				.pattern("CCC")
				.pattern("CCC")
				.define('C', HexcraftBlocks.CRIMSON_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_SAND_STONE.get()), has(HexcraftBlocks.CRIMSON_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.FAIRY_SAND_STONE_WALL.get(), 6)
				.pattern("FFF")
				.pattern("FFF")
				.define('F', HexcraftBlocks.FAIRY_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.FAIRY_SAND_STONE.get()), has(HexcraftBlocks.FAIRY_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.PIXIE_SAND_STONE_WALL.get(), 6)
				.pattern("PPP")
				.pattern("PPP")
				.define('P', HexcraftBlocks.PIXIE_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.PIXIE_SAND_STONE.get()), has(HexcraftBlocks.PIXIE_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CRIMSON_GLASS_PANE.get(), 16)
				.pattern("CCC")
				.pattern("CCC")
				.define('C', HexcraftBlocks.CRIMSON_GLASS.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_GLASS.get()), has(HexcraftBlocks.CRIMSON_GLASS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.FAIRY_GLASS_PANE.get(), 16)
				.pattern("FFF")
				.pattern("FFF")
				.define('F', HexcraftBlocks.FAIRY_GLASS.get())
				.unlockedBy(getHasName(HexcraftBlocks.FAIRY_GLASS.get()), has(HexcraftBlocks.FAIRY_GLASS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.PIXIE_GLASS_PANE.get(), 16)
				.pattern("PPP")
				.pattern("PPP")
				.define('P', HexcraftBlocks.PIXIE_GLASS.get())
				.unlockedBy(getHasName(HexcraftBlocks.PIXIE_GLASS.get()), has(HexcraftBlocks.PIXIE_GLASS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.CRIMSON_PACKED_ICE.get())
				.pattern("CC ")
				.pattern("CC ")
				.define('C', HexcraftBlocks.CRIMSON_ICE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_ICE.get()), has(HexcraftBlocks.CRIMSON_ICE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.PEARL_STONE_BRICKS.get(),4)
				.pattern("PP ")
				.pattern("PP ")
				.define('P', HexcraftBlocks.PEARL_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.PEARL_STONE.get()), has(HexcraftBlocks.PEARL_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.POLISHED_PEARL_STONE.get(),4)
				.pattern("PP ")
				.pattern("PP ")
				.define('P', HexcraftBlocks.PEARL_STONE_BRICKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.PEARL_STONE_BRICKS.get()), has(HexcraftBlocks.PEARL_STONE_BRICKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.CHISELED_PEARL_STONE.get())
				.pattern(" P ")
				.pattern(" P ")
				.define('P', HexcraftBlocks.PEARL_STONE_SLAB.get())
				.unlockedBy(getHasName(HexcraftBlocks.PEARL_STONE_SLAB.get()), has(HexcraftBlocks.PEARL_STONE_SLAB.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.CRIMSON_STONE_BRICKS.get(),4)
				.pattern("CC ")
				.pattern("CC ")
				.define('C', HexcraftBlocks.CRIMSON_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_STONE.get()), has(HexcraftBlocks.CRIMSON_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.POLISHED_CRIMSON_STONE.get(),4)
				.pattern("CC ")
				.pattern("CC ")
				.define('C', HexcraftBlocks.CRIMSON_STONE_BRICKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_STONE_BRICKS.get()), has(HexcraftBlocks.CRIMSON_STONE_BRICKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.CRIMSON_SAND_STONE.get())
				.pattern("CC ")
				.pattern("CC ")
				.define('C', HexcraftBlocks.CRIMSON_SAND.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_SAND.get()), has(HexcraftBlocks.CRIMSON_SAND.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.CUT_CRIMSON_SAND_STONE.get(), 4)
				.pattern("CC ")
				.pattern("CC ")
				.define('C', HexcraftBlocks.CRIMSON_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_SAND_STONE.get()), has(HexcraftBlocks.CRIMSON_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.CHISELED_CRIMSON_SAND_STONE.get())
				.pattern(" C ")
				.pattern(" C ")
				.define('C', HexcraftBlocks.CRIMSON_SAND_STONE_SLAB.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_SAND_STONE_SLAB.get()), has(HexcraftBlocks.CRIMSON_SAND_STONE_SLAB.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.FAIRY_SAND_STONE.get())
				.pattern("FF ")
				.pattern("FF ")
				.define('F', HexcraftBlocks.FAIRY_SAND.get())
				.unlockedBy(getHasName(HexcraftBlocks.FAIRY_SAND.get()), has(HexcraftBlocks.FAIRY_SAND.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.CUT_FAIRY_SAND_STONE.get(), 4)
				.pattern("FF ")
				.pattern("FF ")
				.define('F', HexcraftBlocks.FAIRY_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.FAIRY_SAND_STONE.get()), has(HexcraftBlocks.FAIRY_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.CHISELED_FAIRY_SAND_STONE.get())
				.pattern(" F ")
				.pattern(" F ")
				.define('F', HexcraftBlocks.FAIRY_SAND_STONE_SLAB.get())
				.unlockedBy(getHasName(HexcraftBlocks.FAIRY_SAND_STONE_SLAB.get()), has(HexcraftBlocks.FAIRY_SAND_STONE_SLAB.get()))
				.save(pWriter);


		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.PIXIE_SAND_STONE.get())
				.pattern("PP ")
				.pattern("PP ")
				.define('P', HexcraftBlocks.PIXIE_SAND.get())
				.unlockedBy(getHasName(HexcraftBlocks.PIXIE_SAND.get()), has(HexcraftBlocks.PIXIE_SAND.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.CUT_PIXIE_SAND_STONE.get(), 4)
				.pattern("PP ")
				.pattern("PP ")
				.define('P', HexcraftBlocks.PIXIE_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.PIXIE_SAND_STONE.get()), has(HexcraftBlocks.PIXIE_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.CHISELED_PIXIE_SAND_STONE.get())
				.pattern(" P ")
				.pattern(" P ")
				.define('P', HexcraftBlocks.PIXIE_SAND_STONE_SLAB.get())
				.unlockedBy(getHasName(HexcraftBlocks.PIXIE_SAND_STONE_SLAB.get()), has(HexcraftBlocks.PIXIE_SAND_STONE_SLAB.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.CHISELED_CRIMSON_STONE.get())
				.pattern(" C ")
				.pattern(" C ")
				.define('C', HexcraftBlocks.CRIMSON_STONE_SLAB.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_STONE_SLAB.get()), has(HexcraftBlocks.CRIMSON_STONE_SLAB.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.UNDER_WORLD_STONE_BRICKS.get(),4)
				.pattern("UU ")
				.pattern("UU ")
				.define('U', HexcraftBlocks.UNDER_WORLD_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.UNDER_WORLD_STONE.get()), has(HexcraftBlocks.UNDER_WORLD_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.POLISHED_UNDER_WORLD_STONE.get(),4)
				.pattern("UU ")
				.pattern("UU ")
				.define('U', HexcraftBlocks.UNDER_WORLD_STONE_BRICKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.UNDER_WORLD_STONE_BRICKS.get()), has(HexcraftBlocks.UNDER_WORLD_STONE_BRICKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.CHISELED_UNDER_WORLD_STONE.get())
				.pattern(" U ")
				.pattern(" U ")
				.define('U', HexcraftBlocks.UNDER_WORLD_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.UNDER_WORLD_STONE_SLAB.get()), has(HexcraftBlocks.UNDER_WORLD_STONE_SLAB.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.CHARSTONE_BRICKS.get(),4)
				.pattern("CC ")
				.pattern("CC ")
				.define('C', HexcraftBlocks.CHARSTONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CHARSTONE.get()), has(HexcraftBlocks.CHARSTONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.POLISHED_CHARSTONE.get(),4)
				.pattern("CC ")
				.pattern("CC ")
				.define('C', HexcraftBlocks.CHARSTONE_BRICKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.CHARSTONE_BRICKS.get()), has(HexcraftBlocks.CHARSTONE_BRICKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.CHISELED_CHARSTONE.get())
				.pattern(" C ")
				.pattern(" C ")
				.define('C', HexcraftBlocks.CHARSTONE_SLAB.get())
				.unlockedBy(getHasName(HexcraftBlocks.CHARSTONE_SLAB.get()), has(HexcraftBlocks.CHARSTONE_SLAB.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.PEARL_STONE_STAIRS.get(),4)
				.pattern("P  ")
				.pattern("PP ")
				.pattern("PPP")
				.define('P', HexcraftBlocks.PEARL_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.PEARL_STONE.get()), has(HexcraftBlocks.PEARL_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.PEARL_COBBLESTONE_STAIRS.get(),4)
				.pattern("P  ")
				.pattern("PP ")
				.pattern("PPP")
				.define('P', HexcraftBlocks.PEARL_COBBLESTONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.PEARL_COBBLESTONE.get()), has(HexcraftBlocks.PEARL_COBBLESTONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.PEARL_STONE_BRICKS_STAIRS.get(),4)
				.pattern("P  ")
				.pattern("PP ")
				.pattern("PPP")
				.define('P', HexcraftBlocks.PEARL_STONE_BRICKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.PEARL_STONE_BRICKS.get()), has(HexcraftBlocks.PEARL_STONE_BRICKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.POLISHED_PEARL_STONE_STAIRS.get(),4)
				.pattern("P  ")
				.pattern("PP ")
				.pattern("PPP")
				.define('P', HexcraftBlocks.POLISHED_PEARL_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.POLISHED_PEARL_STONE.get()), has(HexcraftBlocks.POLISHED_PEARL_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CRIMSON_STONE_STAIRS.get(),4)
				.pattern("C  ")
				.pattern("CC ")
				.pattern("CCC")
				.define('C', HexcraftBlocks.CRIMSON_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_STONE.get()), has(HexcraftBlocks.CRIMSON_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CRIMSON_COBBLESTONE_STAIRS.get(),4)
				.pattern("C  ")
				.pattern("CC ")
				.pattern("CCC")
				.define('C', HexcraftBlocks.CRIMSON_COBBLESTONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_COBBLESTONE.get()), has(HexcraftBlocks.CRIMSON_COBBLESTONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CRIMSON_STONE_BRICKS_STAIRS.get(),4)
				.pattern("C  ")
				.pattern("CC ")
				.pattern("CCC")
				.define('C', HexcraftBlocks.CRIMSON_STONE_BRICKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_STONE_BRICKS.get()), has(HexcraftBlocks.CRIMSON_STONE_BRICKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.POLISHED_CRIMSON_STONE_STAIRS.get(),4)
				.pattern("C  ")
				.pattern("CC ")
				.pattern("CCC")
				.define('C', HexcraftBlocks.POLISHED_CRIMSON_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.POLISHED_CRIMSON_STONE.get()), has(HexcraftBlocks.POLISHED_CRIMSON_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.UNDER_WORLD_STONE_STAIRS.get(),4)
				.pattern("U  ")
				.pattern("UU ")
				.pattern("UUU")
				.define('U', HexcraftBlocks.UNDER_WORLD_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.UNDER_WORLD_STONE.get()), has(HexcraftBlocks.UNDER_WORLD_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.UNDER_WORLD_COBBLESTONE_STAIRS.get(),4)
				.pattern("U  ")
				.pattern("UU ")
				.pattern("UUU")
				.define('U', HexcraftBlocks.UNDER_WORLD_COBBLESTONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.UNDER_WORLD_COBBLESTONE.get()), has(HexcraftBlocks.UNDER_WORLD_COBBLESTONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.UNDER_WORLD_STONE_BRICKS_STAIRS.get(),4)
				.pattern("U  ")
				.pattern("UU ")
				.pattern("UUU")
				.define('U', HexcraftBlocks.UNDER_WORLD_STONE_BRICKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.UNDER_WORLD_STONE_BRICKS.get()), has(HexcraftBlocks.UNDER_WORLD_STONE_BRICKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.POLISHED_UNDER_WORLD_STONE_STAIRS.get(),4)
				.pattern("U  ")
				.pattern("UU ")
				.pattern("UUU")
				.define('U', HexcraftBlocks.POLISHED_UNDER_WORLD_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.POLISHED_UNDER_WORLD_STONE.get()), has(HexcraftBlocks.POLISHED_UNDER_WORLD_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CHARSTONE_STONE_STAIRS.get(),4)
				.pattern("C  ")
				.pattern("CC ")
				.pattern("CCC")
				.define('C', HexcraftBlocks.CHARSTONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CHARSTONE.get()), has(HexcraftBlocks.CHARSTONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CHARSTONE_COBBLESTONE_STAIRS.get(),4)
				.pattern("C  ")
				.pattern("CC ")
				.pattern("CCC")
				.define('C', HexcraftBlocks.CHARSTONE_COBBLESTONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CHARSTONE_COBBLESTONE.get()), has(HexcraftBlocks.CHARSTONE_COBBLESTONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CHARSTONE_STONE_BRICKS_STAIRS.get(),4)
				.pattern("C  ")
				.pattern("CC ")
				.pattern("CCC")
				.define('C', HexcraftBlocks.CHARSTONE_BRICKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.CHARSTONE_BRICKS.get()), has(HexcraftBlocks.CHARSTONE_BRICKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.POLISHED_CHARSTONE_STONE_STAIRS.get(),4)
				.pattern("C  ")
				.pattern("CC ")
				.pattern("CCC")
				.define('C', HexcraftBlocks.POLISHED_CHARSTONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.POLISHED_CHARSTONE.get()), has(HexcraftBlocks.POLISHED_CHARSTONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CRIMSON_SAND_STONE_STAIRS.get(),4)
				.pattern("C  ")
				.pattern("CC ")
				.pattern("CCC")
				.define('C', HexcraftBlocks.CRIMSON_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_SAND_STONE.get()), has(HexcraftBlocks.CRIMSON_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.SMOOTH_CRIMSON_SAND_STONE_STAIRS.get(),4)
				.pattern("C  ")
				.pattern("CC ")
				.pattern("CCC")
				.define('C', HexcraftBlocks.SMOOTH_CRIMSON_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.SMOOTH_CRIMSON_SAND_STONE.get()), has(HexcraftBlocks.SMOOTH_CRIMSON_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.FAIRY_SAND_STONE_STAIRS.get(),4)
				.pattern("F  ")
				.pattern("FF ")
				.pattern("FFF")
				.define('F', HexcraftBlocks.FAIRY_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.FAIRY_SAND_STONE.get()), has(HexcraftBlocks.FAIRY_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.SMOOTH_FAIRY_SAND_STONE_STAIRS.get(),4)
				.pattern("F  ")
				.pattern("FF ")
				.pattern("FFF")
				.define('F', HexcraftBlocks.SMOOTH_FAIRY_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.SMOOTH_FAIRY_SAND_STONE.get()), has(HexcraftBlocks.SMOOTH_FAIRY_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.PIXIE_SAND_STONE_STAIRS.get(),4)
				.pattern("P  ")
				.pattern("PP ")
				.pattern("PPP")
				.define('P', HexcraftBlocks.PIXIE_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.PIXIE_SAND_STONE.get()), has(HexcraftBlocks.PIXIE_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.SMOOTH_PIXIE_SAND_STONE_STAIRS.get(),4)
				.pattern("P  ")
				.pattern("PP ")
				.pattern("PPP")
				.define('P', HexcraftBlocks.SMOOTH_PIXIE_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.SMOOTH_PIXIE_SAND_STONE.get()), has(HexcraftBlocks.SMOOTH_PIXIE_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.EBONY_STAIRS.get(),4)
				.pattern("E  ")
				.pattern("EE ")
				.pattern("EEE")
				.define('E', HexcraftBlocks.EBONY_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.EBONY_PLANKS.get()), has(HexcraftBlocks.EBONY_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.BLOOD_OAK_STAIRS.get(),4)
				.pattern("B  ")
				.pattern("BB ")
				.pattern("BBB")
				.define('B', HexcraftBlocks.BLOOD_OAK_STAIRS.get())
				.unlockedBy(getHasName(HexcraftBlocks.BLOOD_OAK_STAIRS.get()), has(HexcraftBlocks.BLOOD_OAK_STAIRS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.HELL_BARK_STAIRS.get(),4)
				.pattern("H  ")
				.pattern("HH ")
				.pattern("HHH")
				.define('H', HexcraftBlocks.HELL_BARK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.HELL_BARK_PLANKS.get()), has(HexcraftBlocks.HELL_BARK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WHITE_OAK_STAIRS.get(),4)
				.pattern("W  ")
				.pattern("WW ")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WHITE_OAK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WHITE_OAK_PLANKS.get()), has(HexcraftBlocks.WHITE_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ALDER_STAIRS.get(),4)
				.pattern("A  ")
				.pattern("AA ")
				.pattern("AAA")
				.define('A', HexcraftBlocks.ALDER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ALDER_PLANKS.get()), has(HexcraftBlocks.ALDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_HAZEL_STAIRS.get(),4)
				.pattern("W  ")
				.pattern("WW ")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WITCH_HAZEL_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()), has(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WILLOW_STAIRS.get(),4)
				.pattern("W  ")
				.pattern("WW ")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WILLOW_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WILLOW_PLANKS.get()), has(HexcraftBlocks.WILLOW_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.HAWTHORN_STAIRS.get(),4)
				.pattern("H  ")
				.pattern("HH ")
				.pattern("HHH")
				.define('H', HexcraftBlocks.HAWTHORN_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.HAWTHORN_PLANKS.get()), has(HexcraftBlocks.HAWTHORN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CEDAR_STAIRS.get(),4)
				.pattern("C  ")
				.pattern("CC ")
				.pattern("CCC")
				.define('C', HexcraftBlocks.CEDAR_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.CEDAR_PLANKS.get()), has(HexcraftBlocks.CEDAR_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.DISTORTED_STAIRS.get(),4)
				.pattern("D  ")
				.pattern("DD ")
				.pattern("DDD")
				.define('D', HexcraftBlocks.DISTORTED_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.DISTORTED_PLANKS.get()), has(HexcraftBlocks.DISTORTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ELDER_STAIRS.get(),4)
				.pattern("E  ")
				.pattern("EE ")
				.pattern("EEE")
				.define('E', HexcraftBlocks.ELDER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ELDER_PLANKS.get()), has(HexcraftBlocks.ELDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.JUNIPER_STAIRS.get(),4)
				.pattern("J  ")
				.pattern("JJ ")
				.pattern("JJJ")
				.define('J', HexcraftBlocks.JUNIPER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.JUNIPER_PLANKS.get()), has(HexcraftBlocks.JUNIPER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ROWAN_STAIRS.get(),4)
				.pattern("R  ")
				.pattern("RR ")
				.pattern("RRR")
				.define('R', HexcraftBlocks.ROWAN_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ROWAN_PLANKS.get()), has(HexcraftBlocks.ROWAN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.TWISTED_STAIRS.get(),4)
				.pattern("T  ")
				.pattern("TT ")
				.pattern("TTT")
				.define('T', HexcraftBlocks.TWISTED_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.TWISTED_PLANKS.get()), has(HexcraftBlocks.TWISTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_WOOD_STAIRS.get(),4)
				.pattern("W  ")
				.pattern("WW ")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WITCH_WOOD_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_WOOD_PLANKS.get()), has(HexcraftBlocks.WITCH_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ECHO_WOOD_STAIRS.get(),4)
				.pattern("E  ")
				.pattern("EE ")
				.pattern("EEE")
				.define('E', HexcraftBlocks.ECHO_WOOD_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ECHO_WOOD_PLANKS.get()), has(HexcraftBlocks.ECHO_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.PHOENIX_STAIRS.get(),4)
				.pattern("P  ")
				.pattern("PP ")
				.pattern("PPP")
				.define('P', HexcraftBlocks.PHOENIX_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.PHOENIX_PLANKS.get()), has(HexcraftBlocks.PHOENIX_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.PEARL_STONE_SLAB.get(),6)
				.pattern("PPP")
				.define('P', HexcraftBlocks.PEARL_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.PEARL_STONE.get()), has(HexcraftBlocks.PEARL_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.PEARL_COBBLESTONE_SLAB.get(),6)
				.pattern("PPP")
				.define('P', HexcraftBlocks.PEARL_COBBLESTONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.PEARL_COBBLESTONE.get()), has(HexcraftBlocks.PEARL_COBBLESTONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.PEARL_STONE_BRICKS_SLAB.get(),6)
				.pattern("PPP")
				.define('P', HexcraftBlocks.PEARL_STONE_BRICKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.PEARL_STONE_BRICKS.get()), has(HexcraftBlocks.PEARL_STONE_BRICKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.POLISHED_PEARL_STONE_SLAB.get(),6)
				.pattern("PPP")
				.define('P', HexcraftBlocks.POLISHED_PEARL_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.POLISHED_PEARL_STONE.get()), has(HexcraftBlocks.POLISHED_PEARL_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CRIMSON_STONE_SLAB.get(),6)
				.pattern("CCC")
				.define('C', HexcraftBlocks.CRIMSON_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_STONE.get()), has(HexcraftBlocks.CRIMSON_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CRIMSON_COBBLESTONE_SLAB.get(),6)
				.pattern("CCC")
				.define('C', HexcraftBlocks.CRIMSON_COBBLESTONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_COBBLESTONE.get()), has(HexcraftBlocks.CRIMSON_COBBLESTONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CRIMSON_STONE_BRICKS_SLAB.get(),6)
				.pattern("CCC")
				.define('C', HexcraftBlocks.CRIMSON_STONE_BRICKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_STONE_BRICKS.get()), has(HexcraftBlocks.CRIMSON_STONE_BRICKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.POLISHED_CRIMSON_STONE_SLAB.get(),6)
				.pattern("CCC")
				.define('C', HexcraftBlocks.POLISHED_CRIMSON_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.POLISHED_CRIMSON_STONE.get()), has(HexcraftBlocks.POLISHED_CRIMSON_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.UNDER_WORLD_STONE_SLAB.get(),6)
				.pattern("UUU")
				.define('U', HexcraftBlocks.UNDER_WORLD_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.UNDER_WORLD_STONE.get()), has(HexcraftBlocks.UNDER_WORLD_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.UNDER_WORLD_COBBLESTONE_SLAB.get(),6)
				.pattern("UUU")
				.define('U', HexcraftBlocks.UNDER_WORLD_COBBLESTONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.UNDER_WORLD_COBBLESTONE.get()), has(HexcraftBlocks.UNDER_WORLD_COBBLESTONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.UNDER_WORLD_STONE_BRICKS_SLAB.get(),6)
				.pattern("UUU")
				.define('U', HexcraftBlocks.UNDER_WORLD_STONE_BRICKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.UNDER_WORLD_STONE_BRICKS.get()), has(HexcraftBlocks.UNDER_WORLD_STONE_BRICKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.POLISHED_UNDER_WORLD_STONE_SLAB.get(),6)
				.pattern("UUU")
				.define('U', HexcraftBlocks.POLISHED_UNDER_WORLD_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.POLISHED_UNDER_WORLD_STONE.get()), has(HexcraftBlocks.POLISHED_UNDER_WORLD_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CHARSTONE_SLAB.get(), 6)
				.pattern("CCC")
				.define('C', HexcraftBlocks.CHARSTONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CHARSTONE.get()), has(HexcraftBlocks.CHARSTONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CHARSTONE_COBBLESTONE_SLAB.get(),6)
				.pattern("CCC")
				.define('C', HexcraftBlocks.CHARSTONE_COBBLESTONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CHARSTONE_COBBLESTONE.get()), has(HexcraftBlocks.CHARSTONE_COBBLESTONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CHARSTONE_BRICKS_SLAB.get(),6)
				.pattern("CCC")
				.define('C', HexcraftBlocks.CHARSTONE_BRICKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.CHARSTONE_BRICKS.get()), has(HexcraftBlocks.CHARSTONE_BRICKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.POLISHED_CHARSTONE_SLAB.get(), 6)
				.pattern("CCC")
				.define('C', HexcraftBlocks.POLISHED_CHARSTONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.POLISHED_CHARSTONE.get()), has(HexcraftBlocks.POLISHED_CHARSTONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CRIMSON_SAND_STONE_SLAB.get(), 6)
				.pattern("CCC")
				.define('C', HexcraftBlocks.CRIMSON_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_SAND_STONE.get()), has(HexcraftBlocks.CRIMSON_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.SMOOTH_CRIMSON_SAND_STONE_SLAB.get(), 6)
				.pattern("CCC")
				.define('C', HexcraftBlocks.SMOOTH_CRIMSON_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.SMOOTH_CRIMSON_SAND_STONE.get()), has(HexcraftBlocks.SMOOTH_CRIMSON_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CUT_CRIMSON_SAND_STONE_SLAB.get(), 6)
				.pattern("CCC")
				.define('C', HexcraftBlocks.CUT_CRIMSON_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CUT_CRIMSON_SAND_STONE.get()), has(HexcraftBlocks.CUT_CRIMSON_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.FAIRY_SAND_STONE_SLAB.get(), 6)
				.pattern("FFF")
				.define('F', HexcraftBlocks.FAIRY_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.FAIRY_SAND_STONE.get()), has(HexcraftBlocks.FAIRY_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.SMOOTH_FAIRY_SAND_STONE_SLAB.get(), 6)
				.pattern("FFF")
				.define('F', HexcraftBlocks.SMOOTH_FAIRY_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.SMOOTH_FAIRY_SAND_STONE.get()), has(HexcraftBlocks.SMOOTH_FAIRY_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CUT_FAIRY_SAND_STONE_SLAB.get(), 6)
				.pattern("FFF")
				.define('F', HexcraftBlocks.CUT_FAIRY_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CUT_FAIRY_SAND_STONE.get()), has(HexcraftBlocks.CUT_FAIRY_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.PIXIE_SAND_STONE_SLAB.get(), 6)
				.pattern("PPP")
				.define('P', HexcraftBlocks.PIXIE_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.PIXIE_SAND_STONE.get()), has(HexcraftBlocks.PIXIE_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.SMOOTH_PIXIE_SAND_STONE_SLAB.get(), 6)
				.pattern("PPP")
				.define('P', HexcraftBlocks.SMOOTH_PIXIE_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.SMOOTH_PIXIE_SAND_STONE.get()), has(HexcraftBlocks.SMOOTH_PIXIE_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CUT_PIXIE_SAND_STONE_SLAB.get(), 6)
				.pattern("PPP")
				.define('P', HexcraftBlocks.CUT_PIXIE_SAND_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CUT_PIXIE_SAND_STONE.get()), has(HexcraftBlocks.CUT_PIXIE_SAND_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.EBONY_SLAB.get(),6)
				.pattern("EEE")
				.define('E', HexcraftBlocks.EBONY_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.EBONY_PLANKS.get()), has(HexcraftBlocks.EBONY_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.BLOOD_OAK_SLAB.get(),6)
				.pattern("BBB")
				.define('B', HexcraftBlocks.BLOOD_OAK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.BLOOD_OAK_PLANKS.get()), has(HexcraftBlocks.BLOOD_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.HELL_BARK_SLAB.get(),6)
				.pattern("HHH")
				.define('H', HexcraftBlocks.HELL_BARK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.HELL_BARK_PLANKS.get()), has(HexcraftBlocks.HELL_BARK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WHITE_OAK_SLAB.get(),6)
				.pattern("WWW")
				.define('W', HexcraftBlocks.WHITE_OAK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WHITE_OAK_PLANKS.get()), has(HexcraftBlocks.WHITE_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ALDER_SLAB.get(),6)
				.pattern("AAA")
				.define('A', HexcraftBlocks.ALDER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ALDER_PLANKS.get()), has(HexcraftBlocks.ALDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_HAZEL_SLAB.get(),6)
				.pattern("WWW")
				.define('W', HexcraftBlocks.WITCH_HAZEL_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()), has(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WILLOW_SLAB.get(),6)
				.pattern("WWW")
				.define('W', HexcraftBlocks.WILLOW_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WILLOW_PLANKS.get()), has(HexcraftBlocks.WILLOW_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.HAWTHORN_SLAB.get(),6)
				.pattern("HHH")
				.define('H', HexcraftBlocks.HAWTHORN_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.HAWTHORN_PLANKS.get()), has(HexcraftBlocks.HAWTHORN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CEDAR_SLAB.get(),6)
				.pattern("CCC")
				.define('C', HexcraftBlocks.CEDAR_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.CEDAR_PLANKS.get()), has(HexcraftBlocks.CEDAR_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.DISTORTED_SLAB.get(),6)
				.pattern("DDD")
				.define('D', HexcraftBlocks.DISTORTED_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.DISTORTED_PLANKS.get()), has(HexcraftBlocks.DISTORTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ELDER_SLAB.get(),6)
				.pattern("EEE")
				.define('E', HexcraftBlocks.ELDER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ELDER_PLANKS.get()), has(HexcraftBlocks.ELDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.JUNIPER_SLAB.get(),6)
				.pattern("JJJ")
				.define('J', HexcraftBlocks.JUNIPER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.JUNIPER_PLANKS.get()), has(HexcraftBlocks.JUNIPER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ROWAN_SLAB.get(), 6)
				.pattern("RRR")
				.define('R', HexcraftBlocks.ROWAN_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ROWAN_PLANKS.get()), has(HexcraftBlocks.ROWAN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.TWISTED_SLAB.get(),6)
				.pattern("TTT")
				.define('T', HexcraftBlocks.TWISTED_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.TWISTED_PLANKS.get()), has(HexcraftBlocks.TWISTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_WOOD_SLAB.get(),6)
				.pattern("WWW")
				.define('W', HexcraftBlocks.WITCH_WOOD_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_WOOD_PLANKS.get()), has(HexcraftBlocks.WITCH_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ECHO_WOOD_SLAB.get(), 6)
				.pattern("EEE")
				.define('E', HexcraftBlocks.ECHO_WOOD_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ECHO_WOOD_PLANKS.get()), has(HexcraftBlocks.ECHO_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.PHOENIX_SLAB.get(), 6)
				.pattern("PPP")
				.define('P', HexcraftBlocks.PHOENIX_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.PHOENIX_PLANKS.get()), has(HexcraftBlocks.PHOENIX_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.EMBER_MOSS_CARPET.get(),3)
				.pattern("EEE")
				.define('E', HexcraftBlocks.EMBER_MOSS_BLOCK.get())
				.unlockedBy(getHasName(HexcraftBlocks.EMBER_MOSS_BLOCK.get()), has(HexcraftBlocks.EMBER_MOSS_BLOCK.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.EBONY_FENCE.get(),3)
				.pattern("ESE")
				.pattern("ESE")
				.define('E', HexcraftBlocks.EBONY_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.EBONY_PLANKS.get()), has(HexcraftBlocks.EBONY_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.BLOOD_OAK_FENCE.get(),3)
				.pattern("BSB")
				.pattern("BSB")
				.define('B', HexcraftBlocks.BLOOD_OAK_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.BLOOD_OAK_PLANKS.get()), has(HexcraftBlocks.BLOOD_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.HELL_BARK_FENCE.get(),3)
				.pattern("HSH")
				.pattern("HSH")
				.define('H', HexcraftBlocks.HELL_BARK_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.HELL_BARK_PLANKS.get()), has(HexcraftBlocks.HELL_BARK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WHITE_OAK_FENCE.get(),3)
				.pattern("WSW")
				.pattern("WSW")
				.define('W', HexcraftBlocks.WHITE_OAK_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.WHITE_OAK_PLANKS.get()), has(HexcraftBlocks.WHITE_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ALDER_FENCE.get(),3)
				.pattern("ASA")
				.pattern("ASA")
				.define('A', HexcraftBlocks.ALDER_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.ALDER_PLANKS.get()), has(HexcraftBlocks.ALDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_HAZEL_FENCE.get(),3)
				.pattern("WSW")
				.pattern("WSW")
				.define('W', HexcraftBlocks.WITCH_HAZEL_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()), has(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WILLOW_FENCE.get(),3)
				.pattern("WSW")
				.pattern("WSW")
				.define('W', HexcraftBlocks.WILLOW_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.WILLOW_PLANKS.get()), has(HexcraftBlocks.WILLOW_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.HAWTHORN_FENCE.get(),3)
				.pattern("HSH")
				.pattern("HSH")
				.define('H', HexcraftBlocks.HAWTHORN_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.HAWTHORN_PLANKS.get()), has(HexcraftBlocks.HAWTHORN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CEDAR_FENCE.get(),3)
				.pattern("CSC")
				.pattern("CSC")
				.define('C', HexcraftBlocks.CEDAR_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.CEDAR_PLANKS.get()), has(HexcraftBlocks.CEDAR_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.DISTORTED_FENCE.get(),3)
				.pattern("DSD")
				.pattern("DSD")
				.define('D', HexcraftBlocks.DISTORTED_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.DISTORTED_PLANKS.get()), has(HexcraftBlocks.DISTORTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ELDER_FENCE.get(),3)
				.pattern("ESE")
				.pattern("ESE")
				.define('E', HexcraftBlocks.ELDER_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.ELDER_PLANKS.get()), has(HexcraftBlocks.ELDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.JUNIPER_FENCE.get(),3)
				.pattern("JSJ")
				.pattern("JSJ")
				.define('J', HexcraftBlocks.JUNIPER_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.JUNIPER_PLANKS.get()), has(HexcraftBlocks.JUNIPER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ROWAN_FENCE.get(), 3)
				.pattern("RSR")
				.pattern("RSR")
				.define('R', HexcraftBlocks.ROWAN_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.ROWAN_PLANKS.get()), has(HexcraftBlocks.ROWAN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.TWISTED_FENCE.get(),3)
				.pattern("TST")
				.pattern("TST")
				.define('T', HexcraftBlocks.TWISTED_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.TWISTED_PLANKS.get()), has(HexcraftBlocks.TWISTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_WOOD_FENCE.get(),3)
				.pattern("WSW")
				.pattern("WSW")
				.define('W', HexcraftBlocks.WITCH_WOOD_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_WOOD_PLANKS.get()), has(HexcraftBlocks.WITCH_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ECHO_WOOD_FENCE.get(), 3)
				.pattern("ESE")
				.pattern("ESE")
				.define('E', HexcraftBlocks.ECHO_WOOD_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.ECHO_WOOD_PLANKS.get()), has(HexcraftBlocks.ECHO_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.PHOENIX_PLANKS.get(), 3)
				.pattern("PSP")
				.pattern("PSP")
				.define('P', HexcraftBlocks.PHOENIX_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.PHOENIX_PLANKS.get()), has(HexcraftBlocks.PHOENIX_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.EBONY_FENCE_GATE.get())
				.pattern("SES")
				.pattern("SES")
				.define('E', HexcraftBlocks.EBONY_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.EBONY_PLANKS.get()), has(HexcraftBlocks.EBONY_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.BLOOD_OAK_FENCE_GATE.get())
				.pattern("SBS")
				.pattern("SBS")
				.define('B', HexcraftBlocks.BLOOD_OAK_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.BLOOD_OAK_PLANKS.get()), has(HexcraftBlocks.BLOOD_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.HELL_BARK_FENCE_GATE.get())
				.pattern("SHS")
				.pattern("SHS")
				.define('H', HexcraftBlocks.HELL_BARK_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.HELL_BARK_PLANKS.get()), has(HexcraftBlocks.HELL_BARK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WHITE_OAK_FENCE_GATE.get())
				.pattern("SWS")
				.pattern("SWS")
				.define('W', HexcraftBlocks.WHITE_OAK_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.WHITE_OAK_PLANKS.get()), has(HexcraftBlocks.WHITE_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ALDER_FENCE_GATE.get())
				.pattern("SAS")
				.pattern("SAS")
				.define('A', HexcraftBlocks.ALDER_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.ALDER_PLANKS.get()), has(HexcraftBlocks.ALDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_HAZEL_FENCE_GATE.get())
				.pattern("SWS")
				.pattern("SWS")
				.define('W', HexcraftBlocks.WITCH_HAZEL_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()), has(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WILLOW_FENCE_GATE.get())
				.pattern("SWS")
				.pattern("SWS")
				.define('W', HexcraftBlocks.WILLOW_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.WILLOW_PLANKS.get()), has(HexcraftBlocks.WILLOW_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.HAWTHORN_FENCE_GATE.get())
				.pattern("SHS")
				.pattern("SHS")
				.define('H', HexcraftBlocks.HAWTHORN_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.HAWTHORN_PLANKS.get()), has(HexcraftBlocks.HAWTHORN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CEDAR_FENCE_GATE.get())
				.pattern("SCS")
				.pattern("SCS")
				.define('C', HexcraftBlocks.CEDAR_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.CEDAR_PLANKS.get()), has(HexcraftBlocks.CEDAR_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.DISTORTED_FENCE_GATE.get())
				.pattern("SDS")
				.pattern("SDS")
				.define('D', HexcraftBlocks.DISTORTED_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.DISTORTED_PLANKS.get()), has(HexcraftBlocks.DISTORTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ELDER_FENCE_GATE.get())
				.pattern("SES")
				.pattern("SES")
				.define('E', HexcraftBlocks.ELDER_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.ELDER_PLANKS.get()), has(HexcraftBlocks.ELDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.JUNIPER_FENCE_GATE.get())
				.pattern("SJS")
				.pattern("SJS")
				.define('J', HexcraftBlocks.JUNIPER_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.JUNIPER_PLANKS.get()), has(HexcraftBlocks.JUNIPER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ROWAN_FENCE_GATE.get())
				.pattern("SRS")
				.pattern("SRS")
				.define('R', HexcraftBlocks.ROWAN_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.ROWAN_PLANKS.get()), has(HexcraftBlocks.ROWAN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.TWISTED_FENCE_GATE.get())
				.pattern("STS")
				.pattern("STS")
				.define('T', HexcraftBlocks.TWISTED_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.TWISTED_PLANKS.get()), has(HexcraftBlocks.TWISTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_WOOD_FENCE_GATE.get())
				.pattern("SWS")
				.pattern("SWS")
				.define('W', HexcraftBlocks.WITCH_WOOD_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_WOOD_PLANKS.get()), has(HexcraftBlocks.WITCH_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ECHO_WOOD_FENCE_GATE.get())
				.pattern("SES")
				.pattern("SES")
				.define('E', HexcraftBlocks.ECHO_WOOD_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.ECHO_WOOD_PLANKS.get()), has(HexcraftBlocks.ECHO_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.PHOENIX_FENCE_GATE.get())
				.pattern("SPS")
				.pattern("SPS")
				.define('P', HexcraftBlocks.PHOENIX_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.PHOENIX_PLANKS.get()), has(HexcraftBlocks.PHOENIX_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.EBONY_DOOR.get(), 3)
				.pattern("EE ")
				.pattern("EE ")
				.pattern("EE ")
				.define('E', HexcraftBlocks.EBONY_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.EBONY_PLANKS.get()), has(HexcraftBlocks.EBONY_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.BLOOD_OAK_DOOR.get(), 3)
				.pattern("BB ")
				.pattern("BB ")
				.pattern("BB ")
				.define('B', HexcraftBlocks.BLOOD_OAK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.BLOOD_OAK_PLANKS.get()), has(HexcraftBlocks.BLOOD_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.HELL_BARK_DOOR.get(), 3)
				.pattern("HH ")
				.pattern("HH ")
				.pattern("HH ")
				.define('H', HexcraftBlocks.HELL_BARK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.HELL_BARK_PLANKS.get()), has(HexcraftBlocks.HELL_BARK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WHITE_OAK_DOOR.get(), 3)
				.pattern("WW ")
				.pattern("WW ")
				.pattern("WW ")
				.define('W', HexcraftBlocks.WHITE_OAK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WHITE_OAK_PLANKS.get()), has(HexcraftBlocks.WHITE_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ALDER_DOOR.get(), 3)
				.pattern("AA ")
				.pattern("AA ")
				.pattern("AA ")
				.define('A', HexcraftBlocks.ALDER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ALDER_PLANKS.get()), has(HexcraftBlocks.ALDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_HAZEL_DOOR.get(), 3)
				.pattern("WW ")
				.pattern("WW ")
				.pattern("WW ")
				.define('W', HexcraftBlocks.WITCH_HAZEL_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()), has(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WILLOW_DOOR.get(), 3)
				.pattern("WW ")
				.pattern("WW ")
				.pattern("WW ")
				.define('W', HexcraftBlocks.WILLOW_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WILLOW_PLANKS.get()), has(HexcraftBlocks.WILLOW_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.HAWTHORN_DOOR.get(), 3)
				.pattern("HH ")
				.pattern("HH ")
				.pattern("HH ")
				.define('H', HexcraftBlocks.HAWTHORN_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.HAWTHORN_PLANKS.get()), has(HexcraftBlocks.HAWTHORN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CEDAR_DOOR.get(), 3)
				.pattern("CC ")
				.pattern("CC ")
				.pattern("CC ")
				.define('C', HexcraftBlocks.CEDAR_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.CEDAR_PLANKS.get()), has(HexcraftBlocks.CEDAR_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.DISTORTED_DOOR.get(), 3)
				.pattern("DD ")
				.pattern("DD ")
				.pattern("DD ")
				.define('D', HexcraftBlocks.DISTORTED_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.DISTORTED_PLANKS.get()), has(HexcraftBlocks.DISTORTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ELDER_DOOR.get(), 3)
				.pattern("EE ")
				.pattern("EE ")
				.pattern("EE ")
				.define('E', HexcraftBlocks.ELDER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ELDER_PLANKS.get()), has(HexcraftBlocks.ELDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.JUNIPER_DOOR.get(), 3)
				.pattern("JJ ")
				.pattern("JJ ")
				.pattern("JJ ")
				.define('J', HexcraftBlocks.JUNIPER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.JUNIPER_PLANKS.get()), has(HexcraftBlocks.JUNIPER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ROWAN_DOOR.get(), 3)
				.pattern("RR ")
				.pattern("RR ")
				.pattern("RR ")
				.define('R', HexcraftBlocks.ROWAN_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ROWAN_PLANKS.get()), has(HexcraftBlocks.ROWAN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.TWISTED_DOOR.get(), 3)
				.pattern("TT ")
				.pattern("TT ")
				.pattern("TT ")
				.define('T', HexcraftBlocks.TWISTED_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.TWISTED_PLANKS.get()), has(HexcraftBlocks.TWISTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_WOOD_DOOR.get(), 3)
				.pattern("WW ")
				.pattern("WW ")
				.pattern("WW ")
				.define('W', HexcraftBlocks.WITCH_WOOD_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_WOOD_PLANKS.get()), has(HexcraftBlocks.WITCH_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ECHO_WOOD_DOOR.get(), 3)
				.pattern("EE ")
				.pattern("EE ")
				.pattern("EE ")
				.define('E', HexcraftBlocks.ECHO_WOOD_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ECHO_WOOD_PLANKS.get()), has(HexcraftBlocks.ECHO_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.PHOENIX_DOOR.get(), 3)
				.pattern("PP ")
				.pattern("PP ")
				.pattern("PP ")
				.define('P', HexcraftBlocks.PHOENIX_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.PHOENIX_PLANKS.get()), has(HexcraftBlocks.PHOENIX_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.EBONY_TRAPDOOR.get(), 2)
				.pattern("EEE")
				.pattern("EEE")
				.define('E', HexcraftBlocks.EBONY_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.EBONY_PLANKS.get()), has(HexcraftBlocks.EBONY_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.BLOOD_OAK_TRAPDOOR.get(), 2)
				.pattern("BBB")
				.pattern("BBB")
				.define('B', HexcraftBlocks.BLOOD_OAK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.BLOOD_OAK_PLANKS.get()), has(HexcraftBlocks.BLOOD_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.HELL_BARK_TRAPDOOR.get(), 2)
				.pattern("HHH")
				.pattern("HHH")
				.define('H', HexcraftBlocks.HELL_BARK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.HELL_BARK_PLANKS.get()), has(HexcraftBlocks.HELL_BARK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WHITE_OAK_TRAPDOOR.get(), 2)
				.pattern("WWW")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WHITE_OAK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WHITE_OAK_PLANKS.get()), has(HexcraftBlocks.WHITE_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ALDER_TRAPDOOR.get(), 2)
				.pattern("AAA")
				.pattern("AAA")
				.define('A', HexcraftBlocks.ALDER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ALDER_PLANKS.get()), has(HexcraftBlocks.ALDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_HAZEL_TRAPDOOR.get(), 2)
				.pattern("WWW")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WITCH_HAZEL_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()), has(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WILLOW_TRAPDOOR.get(), 2)
				.pattern("WWW")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WILLOW_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WILLOW_PLANKS.get()), has(HexcraftBlocks.WILLOW_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.HAWTHORN_TRAPDOOR.get(), 2)
				.pattern("HHH")
				.pattern("HHH")
				.define('H', HexcraftBlocks.HAWTHORN_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.HAWTHORN_PLANKS.get()), has(HexcraftBlocks.HAWTHORN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CEDAR_TRAPDOOR.get(), 2)
				.pattern("CCC")
				.pattern("CCC")
				.define('C', HexcraftBlocks.CEDAR_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.CEDAR_PLANKS.get()), has(HexcraftBlocks.CEDAR_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.DISTORTED_TRAPDOOR.get(), 2)
				.pattern("DDD")
				.pattern("DDD")
				.define('D', HexcraftBlocks.DISTORTED_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.DISTORTED_PLANKS.get()), has(HexcraftBlocks.DISTORTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ELDER_TRAPDOOR.get(), 2)
				.pattern("EEE")
				.pattern("EEE")
				.define('E', HexcraftBlocks.ELDER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ELDER_PLANKS.get()), has(HexcraftBlocks.ELDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.JUNIPER_TRAPDOOR.get(), 2)
				.pattern("JJJ")
				.pattern("JJJ")
				.define('J', HexcraftBlocks.JUNIPER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.JUNIPER_PLANKS.get()), has(HexcraftBlocks.JUNIPER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ROWAN_TRAPDOOR.get(), 2)
				.pattern("RRR")
				.pattern("RRR")
				.define('R', HexcraftBlocks.ROWAN_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ROWAN_PLANKS.get()), has(HexcraftBlocks.ROWAN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.TWISTED_TRAPDOOR.get(), 2)
				.pattern("TTT")
				.pattern("TTT")
				.define('T', HexcraftBlocks.TWISTED_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.TWISTED_PLANKS.get()), has(HexcraftBlocks.TWISTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_WOOD_TRAPDOOR.get(), 2)
				.pattern("WWW")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WITCH_WOOD_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_WOOD_PLANKS.get()), has(HexcraftBlocks.WITCH_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ECHO_WOOD_TRAPDOOR.get(), 2)
				.pattern("EEE")
				.pattern("EEE")
				.define('E', HexcraftBlocks.ECHO_WOOD_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ECHO_WOOD_PLANKS.get()), has(HexcraftBlocks.ECHO_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.PHOENIX_TRAPDOOR.get(), 2)
				.pattern("PPP")
				.pattern("PPP")
				.define('P', HexcraftBlocks.PHOENIX_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.PHOENIX_PLANKS.get()), has(HexcraftBlocks.PHOENIX_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.EBONY_SIGN.get(), 3)
				.pattern("EEE")
				.pattern("EEE")
				.pattern(" S ")
				.define('E', HexcraftBlocks.EBONY_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.EBONY_PLANKS.get()), has(HexcraftBlocks.EBONY_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.BLOOD_OAK_SIGN.get(), 3)
				.pattern("BBB")
				.pattern("BBB")
				.pattern(" S ")
				.define('B', HexcraftBlocks.BLOOD_OAK_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.BLOOD_OAK_PLANKS.get()), has(HexcraftBlocks.BLOOD_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.HELL_BARK_SIGN.get(), 3)
				.pattern("HHH")
				.pattern("HHH")
				.pattern(" S ")
				.define('H', HexcraftBlocks.HELL_BARK_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.HELL_BARK_PLANKS.get()), has(HexcraftBlocks.HELL_BARK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.WHITE_OAK_SIGN.get(), 3)
				.pattern("WWW")
				.pattern("WWW")
				.pattern(" S ")
				.define('W', HexcraftBlocks.WHITE_OAK_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.WHITE_OAK_PLANKS.get()), has(HexcraftBlocks.WHITE_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.ALDER_SIGN.get(), 3)
				.pattern("AAA")
				.pattern("AAA")
				.pattern(" S ")
				.define('A', HexcraftBlocks.ALDER_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.ALDER_PLANKS.get()), has(HexcraftBlocks.ALDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.WITCH_HAZEL_SIGN.get(), 3)
				.pattern("WWW")
				.pattern("WWW")
				.pattern(" S ")
				.define('W', HexcraftBlocks.WITCH_HAZEL_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()), has(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.WILLOW_SIGN.get(), 3)
				.pattern("WWW")
				.pattern("WWW")
				.pattern(" S ")
				.define('W', HexcraftBlocks.WILLOW_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.WILLOW_PLANKS.get()), has(HexcraftBlocks.WILLOW_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.HAWTHORN_SIGN.get(), 3)
				.pattern("HHH")
				.pattern("HHH")
				.pattern(" S ")
				.define('H', HexcraftBlocks.HAWTHORN_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.HAWTHORN_PLANKS.get()), has(HexcraftBlocks.HAWTHORN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.CEDAR_SIGN.get(), 3)
				.pattern("CCC")
				.pattern("CCC")
				.pattern(" S ")
				.define('C', HexcraftBlocks.CEDAR_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.CEDAR_PLANKS.get()), has(HexcraftBlocks.CEDAR_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.DISTORTED_SIGN.get(), 3)
				.pattern("DDD")
				.pattern("DDD")
				.pattern(" S ")
				.define('D', HexcraftBlocks.DISTORTED_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.DISTORTED_PLANKS.get()), has(HexcraftBlocks.DISTORTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.ELDER_SIGN.get(), 3)
				.pattern("EEE")
				.pattern("EEE")
				.pattern(" S ")
				.define('E', HexcraftBlocks.ELDER_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.ELDER_PLANKS.get()), has(HexcraftBlocks.ELDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.JUNIPER_SIGN.get(), 3)
				.pattern("JJJ")
				.pattern("JJJ")
				.pattern(" S ")
				.define('J', HexcraftBlocks.JUNIPER_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.JUNIPER_PLANKS.get()), has(HexcraftBlocks.JUNIPER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.ROWAN_SIGN.get(), 3)
				.pattern("RRR")
				.pattern("RRR")
				.pattern(" S ")
				.define('R', HexcraftBlocks.ROWAN_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.ROWAN_PLANKS.get()), has(HexcraftBlocks.ROWAN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.TWISTED_SIGN.get(), 3)
				.pattern("TTT")
				.pattern("TTT")
				.pattern(" S ")
				.define('T', HexcraftBlocks.TWISTED_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.TWISTED_PLANKS.get()), has(HexcraftBlocks.TWISTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.WITCH_WOOD_SIGN.get(), 3)
				.pattern("WWW")
				.pattern("WWW")
				.pattern(" S ")
				.define('W', HexcraftBlocks.WITCH_WOOD_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_WOOD_PLANKS.get()), has(HexcraftBlocks.WITCH_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.ECHO_WOOD_SIGN.get(), 3)
				.pattern("EEE")
				.pattern("EEE")
				.pattern(" S ")
				.define('E', HexcraftBlocks.ECHO_WOOD_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.ECHO_WOOD_PLANKS.get()), has(HexcraftBlocks.ECHO_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.PHOENIX_SIGN.get(), 3)
				.pattern("PPP")
				.pattern("PPP")
				.pattern(" S ")
				.define('P', HexcraftBlocks.PHOENIX_PLANKS.get())
				.define('S', Items.STICK)
				.unlockedBy(getHasName(HexcraftBlocks.PHOENIX_PLANKS.get()), has(HexcraftBlocks.PHOENIX_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.EBONY_HANGING_SIGN.get(), 6)
				.pattern("C C")
				.pattern("EEE")
				.pattern("EEE")
				.define('E', HexcraftBlocks.EBONY_PLANKS.get())
				.define('C', Items.CHAIN)
				.unlockedBy(getHasName(HexcraftBlocks.EBONY_PLANKS.get()), has(HexcraftBlocks.EBONY_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.BLOOD_OAK_HANGING_SIGN.get(), 6)
				.pattern("C C")
				.pattern("BBB")
				.pattern("BBB")
				.define('B', HexcraftBlocks.BLOOD_OAK_PLANKS.get())
				.define('C', Items.CHAIN)
				.unlockedBy(getHasName(HexcraftBlocks.BLOOD_OAK_PLANKS.get()), has(HexcraftBlocks.BLOOD_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.HELL_BARK_HANGING_SIGN.get(), 6)
				.pattern("C C")
				.pattern("HHH")
				.pattern("HHH")
				.define('H', HexcraftBlocks.HELL_BARK_PLANKS.get())
				.define('C', Items.CHAIN)
				.unlockedBy(getHasName(HexcraftBlocks.HELL_BARK_PLANKS.get()), has(HexcraftBlocks.HELL_BARK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.WHITE_OAK_HANGING_SIGN.get(), 6)
				.pattern("C C")
				.pattern("WWW")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WHITE_OAK_PLANKS.get())
				.define('C', Items.CHAIN)
				.unlockedBy(getHasName(HexcraftBlocks.WHITE_OAK_PLANKS.get()), has(HexcraftBlocks.WHITE_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.ALDER_HANGING_SIGN.get(), 6)
				.pattern("C C")
				.pattern("AAA")
				.pattern("AAA")
				.define('A', HexcraftBlocks.ALDER_PLANKS.get())
				.define('C', Items.CHAIN)
				.unlockedBy(getHasName(HexcraftBlocks.ALDER_PLANKS.get()), has(HexcraftBlocks.ALDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.WITCH_HAZEL_HANGING_SIGN.get(), 6)
				.pattern("C C")
				.pattern("WWW")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WITCH_HAZEL_PLANKS.get())
				.define('C', Items.CHAIN)
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()), has(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.WILLOW_HANGING_SIGN.get(), 6)
				.pattern("C C")
				.pattern("WWW")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WILLOW_PLANKS.get())
				.define('C', Items.CHAIN)
				.unlockedBy(getHasName(HexcraftBlocks.WILLOW_PLANKS.get()), has(HexcraftBlocks.WILLOW_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.HAWTHORN_HANGING_SIGN.get(), 6)
				.pattern("C C")
				.pattern("HHH")
				.pattern("HHH")
				.define('H', HexcraftBlocks.HAWTHORN_PLANKS.get())
				.define('C', Items.CHAIN)
				.unlockedBy(getHasName(HexcraftBlocks.HAWTHORN_PLANKS.get()), has(HexcraftBlocks.HAWTHORN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.CEDAR_HANGING_SIGN.get(), 6)
				.pattern("I I")
				.pattern("CCC")
				.pattern("CCC")
				.define('C', HexcraftBlocks.CEDAR_PLANKS.get())
				.define('I', Items.CHAIN)
				.unlockedBy(getHasName(HexcraftBlocks.CEDAR_PLANKS.get()), has(HexcraftBlocks.CEDAR_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.DISTORTED_HANGING_SIGN.get(), 6)
				.pattern("C C")
				.pattern("DDD")
				.pattern("DDD")
				.define('D', HexcraftBlocks.DISTORTED_PLANKS.get())
				.define('C', Items.CHAIN)
				.unlockedBy(getHasName(HexcraftBlocks.DISTORTED_PLANKS.get()), has(HexcraftBlocks.DISTORTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.ELDER_HANGING_SIGN.get(), 6)
				.pattern("C C")
				.pattern("EEE")
				.pattern("EEE")
				.define('E', HexcraftBlocks.ELDER_PLANKS.get())
				.define('C', Items.CHAIN)
				.unlockedBy(getHasName(HexcraftBlocks.ELDER_PLANKS.get()), has(HexcraftBlocks.ELDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.JUNIPER_HANGING_SIGN.get(), 6)
				.pattern("C C")
				.pattern("JJJ")
				.pattern("JJJ")
				.define('J', HexcraftBlocks.JUNIPER_PLANKS.get())
				.define('C', Items.CHAIN)
				.unlockedBy(getHasName(HexcraftBlocks.JUNIPER_PLANKS.get()), has(HexcraftBlocks.JUNIPER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.ROWAN_HANGING_SIGN.get(), 6)
				.pattern("C C")
				.pattern("RRR")
				.pattern("RRR")
				.define('R', HexcraftBlocks.ROWAN_PLANKS.get())
				.define('C', Items.CHAIN)
				.unlockedBy(getHasName(HexcraftBlocks.ROWAN_PLANKS.get()), has(HexcraftBlocks.ROWAN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.TWISTED_HANGING_SIGN.get(), 6)
				.pattern("C C")
				.pattern("TTT")
				.pattern("TTT")
				.define('T', HexcraftBlocks.TWISTED_PLANKS.get())
				.define('C', Items.CHAIN)
				.unlockedBy(getHasName(HexcraftBlocks.TWISTED_PLANKS.get()), has(HexcraftBlocks.TWISTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.WITCH_WOOD_HANGING_SIGN.get(), 6)
				.pattern("C C")
				.pattern("WWW")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WITCH_WOOD_PLANKS.get())
				.define('C', Items.CHAIN)
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_WOOD_PLANKS.get()), has(HexcraftBlocks.WITCH_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.ECHO_WOOD_HANGING_SIGN.get(), 6)
				.pattern("C C")
				.pattern("EEE")
				.pattern("EEE")
				.define('E', HexcraftBlocks.ECHO_WOOD_PLANKS.get())
				.define('C', Items.CHAIN)
				.unlockedBy(getHasName(HexcraftBlocks.ECHO_WOOD_PLANKS.get()), has(HexcraftBlocks.ECHO_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.PHOENIX_HANGING_SIGN.get(), 6)
				.pattern("C C")
				.pattern("PPP")
				.pattern("PPP")
				.define('P', HexcraftBlocks.PHOENIX_PLANKS.get())
				.define('C', Items.CHAIN)
				.unlockedBy(getHasName(HexcraftBlocks.PHOENIX_PLANKS.get()), has(HexcraftBlocks.PHOENIX_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.EBONY_BOAT.get())
				.pattern("E E")
				.pattern("EEE")
				.define('E', HexcraftBlocks.EBONY_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.EBONY_PLANKS.get()), has(HexcraftBlocks.EBONY_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.BLOOD_OAK_BOAT.get())
				.pattern("B B")
				.pattern("BBB")
				.define('B', HexcraftBlocks.BLOOD_OAK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.BLOOD_OAK_PLANKS.get()), has(HexcraftBlocks.BLOOD_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.HELL_BARK_BOAT.get())
				.pattern("H H")
				.pattern("HHH")
				.define('H', HexcraftBlocks.HELL_BARK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.HELL_BARK_PLANKS.get()), has(HexcraftBlocks.HELL_BARK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.WHITE_OAK_BOAT.get())
				.pattern("W W")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WHITE_OAK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WHITE_OAK_PLANKS.get()), has(HexcraftBlocks.WHITE_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.ALDER_BOAT.get())
				.pattern("A A")
				.pattern("AAA")
				.define('A', HexcraftBlocks.ALDER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ALDER_PLANKS.get()), has(HexcraftBlocks.ALDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.WITCH_HAZEL_BOAT.get())
				.pattern("W W")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WITCH_HAZEL_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()), has(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.WILLOW_BOAT.get())
				.pattern("W W")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WILLOW_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WILLOW_PLANKS.get()), has(HexcraftBlocks.WILLOW_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.HAWTHORN_BOAT.get())
				.pattern("H H")
				.pattern("HHH")
				.define('H', HexcraftBlocks.HAWTHORN_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.HAWTHORN_PLANKS.get()), has(HexcraftBlocks.HAWTHORN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.CEDAR_BOAT.get())
				.pattern("C C")
				.pattern("CCC")
				.define('C', HexcraftBlocks.CEDAR_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.CEDAR_PLANKS.get()), has(HexcraftBlocks.CEDAR_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.DISTORTED_BOAT.get())
				.pattern("D D")
				.pattern("DDD")
				.define('D', HexcraftBlocks.DISTORTED_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.DISTORTED_PLANKS.get()), has(HexcraftBlocks.DISTORTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.ELDER_BOAT.get())
				.pattern("E E")
				.pattern("EEE")
				.define('E', HexcraftBlocks.ELDER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ELDER_PLANKS.get()), has(HexcraftBlocks.ELDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.JUNIPER_BOAT.get())
				.pattern("J J")
				.pattern("JJJ")
				.define('J', HexcraftBlocks.JUNIPER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.JUNIPER_PLANKS.get()), has(HexcraftBlocks.JUNIPER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.ROWAN_BOAT.get())
				.pattern("R R")
				.pattern("RRR")
				.define('R', HexcraftBlocks.ROWAN_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ROWAN_PLANKS.get()), has(HexcraftBlocks.ROWAN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.TWISTED_BOAT.get())
				.pattern("T T")
				.pattern("TTT")
				.define('T', HexcraftBlocks.TWISTED_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.TWISTED_PLANKS.get()), has(HexcraftBlocks.TWISTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.WITCH_WOOD_BOAT.get())
				.pattern("W W")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WITCH_WOOD_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_WOOD_PLANKS.get()), has(HexcraftBlocks.WITCH_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.ECHO_WOOD_BOAT.get())
				.pattern("E E")
				.pattern("EEE")
				.define('E', HexcraftBlocks.ECHO_WOOD_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ECHO_WOOD_PLANKS.get()), has(HexcraftBlocks.ECHO_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.PEARL_STONE_PRESSURE_PLATE.get())
				.pattern("PP ")
				.define('P', HexcraftBlocks.PEARL_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.PEARL_STONE.get()), has(HexcraftBlocks.PEARL_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.CRIMSON_STONE_PRESSURE_PLATE.get())
				.pattern("CC ")
				.define('C', HexcraftBlocks.CRIMSON_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_STONE.get()), has(HexcraftBlocks.CRIMSON_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.UNDER_WORLD_STONE_PRESSURE_PLATE.get())
				.pattern("UU ")
				.define('U', HexcraftBlocks.UNDER_WORLD_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.UNDER_WORLD_STONE.get()), has(HexcraftBlocks.UNDER_WORLD_STONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CHARSTONE_PRESSURE_PLATE.get())
				.pattern("CC ")
				.define('C', HexcraftBlocks.CHARSTONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CHARSTONE.get()), has(HexcraftBlocks.CHARSTONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.EBONY_PRESSURE_PLATE.get())
				.pattern("EE ")
				.define('E', HexcraftBlocks.EBONY_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.EBONY_PLANKS.get()), has(HexcraftBlocks.EBONY_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.BLOOD_OAK_PRESSURE_PLATE.get())
				.pattern("BB ")
				.define('B', HexcraftBlocks.BLOOD_OAK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.BLOOD_OAK_PLANKS.get()), has(HexcraftBlocks.BLOOD_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.HELL_BARK_PRESSURE_PLATE.get())
				.pattern("HH ")
				.define('H', HexcraftBlocks.HELL_BARK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.HELL_BARK_PLANKS.get()), has(HexcraftBlocks.HELL_BARK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WHITE_OAK_PRESSURE_PLATE.get())
				.pattern("WW ")
				.define('W', HexcraftBlocks.WHITE_OAK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WHITE_OAK_PLANKS.get()), has(HexcraftBlocks.WHITE_OAK_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ALDER_PRESSURE_PLATE.get())
				.pattern("AA ")
				.define('A', HexcraftBlocks.ALDER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ALDER_PLANKS.get()), has(HexcraftBlocks.ALDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_HAZEL_PRESSURE_PLATE.get())
				.pattern("WW ")
				.define('W', HexcraftBlocks.WITCH_HAZEL_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()), has(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WILLOW_PRESSURE_PLATE.get())
				.pattern("WW ")
				.define('W', HexcraftBlocks.WILLOW_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WILLOW_PLANKS.get()), has(HexcraftBlocks.WILLOW_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.HAWTHORN_PRESSURE_PLATE.get())
				.pattern("HH ")
				.define('H', HexcraftBlocks.HAWTHORN_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.HAWTHORN_PLANKS.get()), has(HexcraftBlocks.HAWTHORN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CEDAR_PRESSURE_PLATE.get())
				.pattern("CC ")
				.define('C', HexcraftBlocks.CEDAR_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.CEDAR_PLANKS.get()), has(HexcraftBlocks.CEDAR_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.DISTORTED_PRESSURE_PLATE.get())
				.pattern("DD ")
				.define('D', HexcraftBlocks.DISTORTED_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.DISTORTED_PLANKS.get()), has(HexcraftBlocks.DISTORTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ELDER_PRESSURE_PLATE.get())
				.pattern("EE ")
				.define('E', HexcraftBlocks.ELDER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ELDER_PLANKS.get()), has(HexcraftBlocks.ELDER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.JUNIPER_PRESSURE_PLATE.get())
				.pattern("JJ ")
				.define('J', HexcraftBlocks.JUNIPER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.JUNIPER_PLANKS.get()), has(HexcraftBlocks.JUNIPER_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ROWAN_PRESSURE_PLATE.get())
				.pattern("RR ")
				.define('R', HexcraftBlocks.ROWAN_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ROWAN_PLANKS.get()), has(HexcraftBlocks.ROWAN_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.TWISTED_PRESSURE_PLATE.get())
				.pattern("TT ")
				.define('T', HexcraftBlocks.TWISTED_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.TWISTED_PLANKS.get()), has(HexcraftBlocks.TWISTED_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_WOOD_PRESSURE_PLATE.get())
				.pattern("WW ")
				.define('W', HexcraftBlocks.WITCH_WOOD_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_WOOD_PLANKS.get()), has(HexcraftBlocks.WITCH_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ECHO_WOOD_PRESSURE_PLATE.get())
				.pattern("EE ")
				.define('E', HexcraftBlocks.ECHO_WOOD_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ECHO_WOOD_PLANKS.get()), has(HexcraftBlocks.ECHO_WOOD_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.PHOENIX_PRESSURE_PLATE.get())
				.pattern("PP ")
				.define('P', HexcraftBlocks.PHOENIX_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.PHOENIX_PLANKS.get()), has(HexcraftBlocks.PHOENIX_PLANKS.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.MAGIC_CRYSTAL_BLOCK.get(),4)
				.pattern("MM ")
				.pattern("MM ")
				.define('M', HexcraftItems.MAGIC_CRYSTAL.get())
				.unlockedBy(getHasName(HexcraftItems.MAGIC_CRYSTAL.get()), has(HexcraftItems.MAGIC_CRYSTAL.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.WITCHES_LADDER_ITEM.get(),4)
				.pattern("FRF")
				.pattern("FRF")
				.pattern("FRF")
				.define('R', HexcraftItems.ROPE.get())
				.define('F', Items.FEATHER)
				.unlockedBy(getHasName(HexcraftItems.ROPE.get()), has(HexcraftItems.ROPE.get()))
				.save(pWriter);


		//ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.ATTUNED_STONE.get())
		//.pattern("w")
		//.pattern("d")
		//.pattern("l")
		//.define('w', HexcraftItems.WHIFF_OF_MAGIC.get()).define('d', Items.DIAMOND)
		//.define('l', Items.LAVA_BUCKET)
		//.unlockedBy(getHasName(HexcraftItems.WHIFF_OF_MAGIC.get()), has(HexcraftItems.WHIFF_OF_MAGIC.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, HexcraftItems.ARTHANA.get())
				.pattern(" i ")
				.pattern("nen")
				.pattern(" s ")
				.define('i', Items.GOLD_INGOT).define('n', Items.GOLD_NUGGET)
				.define('e', Items.EMERALD).define('s', Items.STICK)
				.unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT)).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.EBONY_WOOD.get(), 4)
				.pattern("EE ")
				.pattern("EE ")
				.pattern("   ")
				.define('E', HexcraftBlocks.EBONY_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.EBONY_LOG.get()), has(HexcraftBlocks.EBONY_LOG.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.BLOOD_OAK_WOOD.get(), 4)
				.pattern("BB ")
				.pattern("BB ")
				.pattern("   ")
				.define('B', HexcraftBlocks.BLOOD_OAK_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.BLOOD_OAK_LOG.get()), has(HexcraftBlocks.BLOOD_OAK_LOG.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.HELL_BARK_WOOD.get(), 4)
				.pattern("HH ")
				.pattern("HH ")
				.pattern("   ")
				.define('H', HexcraftBlocks.HELL_BARK_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.HELL_BARK_LOG.get()), has(HexcraftBlocks.HELL_BARK_LOG.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WHITE_OAK_WOOD.get(), 4)
				.pattern("WW ")
				.pattern("WW ")
				.pattern("   ")
				.define('W', HexcraftBlocks.WHITE_OAK_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.WHITE_OAK_LOG.get()), has(HexcraftBlocks.WHITE_OAK_LOG.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ALDER_WOOD.get(), 4)
				.pattern("AA ")
				.pattern("AA ")
				.pattern("   ")
				.define('A', HexcraftBlocks.ALDER_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.ALDER_LOG.get()), has(HexcraftBlocks.ALDER_LOG.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_HAZEL_WOOD.get(), 4)
				.pattern("WW ")
				.pattern("WW ")
				.pattern("   ")
				.define('W', HexcraftBlocks.WITCH_HAZEL_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_HAZEL_LOG.get()), has(HexcraftBlocks.WITCH_HAZEL_LOG.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WILLOW_WOOD.get(), 4)
				.pattern("WW ")
				.pattern("WW ")
				.pattern("   ")
				.define('W', HexcraftBlocks.WILLOW_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.WILLOW_LOG.get()), has(HexcraftBlocks.WILLOW_LOG.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.HAWTHORN_WOOD.get(), 4)
				.pattern("HH ")
				.pattern("HH ")
				.pattern("   ")
				.define('H', HexcraftBlocks.HAWTHORN_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.HAWTHORN_LOG.get()), has(HexcraftBlocks.HAWTHORN_LOG.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CEDAR_WOOD.get(), 4)
				.pattern("CC ")
				.pattern("CC ")
				.pattern("   ")
				.define('C', HexcraftBlocks.CEDAR_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.CEDAR_LOG.get()), has(HexcraftBlocks.CEDAR_LOG.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.DISTORTED_WOOD.get(), 4)
				.pattern("DD ")
				.pattern("DD ")
				.pattern("   ")
				.define('D', HexcraftBlocks.DISTORTED_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.DISTORTED_LOG.get()), has(HexcraftBlocks.DISTORTED_LOG.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ELDER_WOOD.get(), 4)
				.pattern("EE ")
				.pattern("EE ")
				.pattern("   ")
				.define('E', HexcraftBlocks.ELDER_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.ELDER_LOG.get()), has(HexcraftBlocks.ELDER_LOG.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.JUNIPER_WOOD.get(), 4)
				.pattern("JJ ")
				.pattern("JJ ")
				.pattern("   ")
				.define('J', HexcraftBlocks.JUNIPER_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.JUNIPER_LOG.get()), has(HexcraftBlocks.JUNIPER_LOG.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ROWAN_WOOD.get(), 4)
				.pattern("RR ")
				.pattern("RR ")
				.pattern("   ")
				.define('R', HexcraftBlocks.ROWAN_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.ROWAN_LOG.get()), has(HexcraftBlocks.ROWAN_LOG.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.TWISTED_WOOD.get(), 4)
				.pattern("TT ")
				.pattern("TT ")
				.pattern("   ")
				.define('T', HexcraftBlocks.TWISTED_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.TWISTED_LOG.get()), has(HexcraftBlocks.TWISTED_LOG.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_WOOD_WOOD.get(), 4)
				.pattern("WW ")
				.pattern("WW ")
				.pattern("   ")
				.define('W', HexcraftBlocks.WITCH_WOOD_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_WOOD_LOG.get()), has(HexcraftBlocks.WITCH_WOOD_LOG.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ECHO_WOOD_WOOD.get(), 4)
				.pattern("EE ")
				.pattern("EE ")
				.pattern("   ")
				.define('E', HexcraftBlocks.ECHO_WOOD_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.ECHO_WOOD_LOG.get()), has(HexcraftBlocks.ECHO_WOOD_LOG.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.PHOENIX_WOOD.get(), 4)
				.pattern("PP ")
				.pattern("PP ")
				.pattern("   ")
				.define('P', HexcraftBlocks.PHOENIX_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.PHOENIX_LOG.get()), has(HexcraftBlocks.PHOENIX_LOG.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.EBONY_BOOKSHELF.get())
				.pattern("EEE")
				.pattern("BBB")
				.pattern("EEE")
				.define('E', HexcraftBlocks.EBONY_PLANKS.get())
				.define('B', Items.BOOK)
				.unlockedBy(getHasName(HexcraftBlocks.EBONY_PLANKS.get()), has(HexcraftBlocks.EBONY_PLANKS.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.BLOOD_OAK_BOOKSHELF.get())
				.pattern("EEE")
				.pattern("BBB")
				.pattern("EEE")
				.define('E', HexcraftBlocks.BLOOD_OAK_PLANKS.get())
				.define('B', Items.BOOK)
				.unlockedBy(getHasName(HexcraftBlocks.BLOOD_OAK_PLANKS.get()), has(HexcraftBlocks.BLOOD_OAK_PLANKS.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.HELL_BARK_BOOKSHELF.get())
				.pattern("HHH")
				.pattern("BBB")
				.pattern("HHH")
				.define('H', HexcraftBlocks.HELL_BARK_PLANKS.get())
				.define('B', Items.BOOK)
				.unlockedBy(getHasName(HexcraftBlocks.HELL_BARK_PLANKS.get()), has(HexcraftBlocks.HELL_BARK_PLANKS.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WHITE_OAK_BOOKSHELF.get())
				.pattern("WWW")
				.pattern("BBB")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WHITE_OAK_PLANKS.get())
				.define('B', Items.BOOK)
				.unlockedBy(getHasName(HexcraftBlocks.WHITE_OAK_PLANKS.get()), has(HexcraftBlocks.WHITE_OAK_PLANKS.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ALDER_BOOKSHELF.get())
				.pattern("AAA")
				.pattern("BBB")
				.pattern("AAA")
				.define('A', HexcraftBlocks.ALDER_PLANKS.get())
				.define('B', Items.BOOK)
				.unlockedBy(getHasName(HexcraftBlocks.ALDER_PLANKS.get()), has(HexcraftBlocks.ALDER_PLANKS.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_HAZEL_BOOKSHELF.get())
				.pattern("WWW")
				.pattern("BBB")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WITCH_HAZEL_PLANKS.get())
				.define('B', Items.BOOK)
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()), has(HexcraftBlocks.WITCH_HAZEL_PLANKS.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WILLOW_BOOKSHELF.get())
				.pattern("WWW")
				.pattern("BBB")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WILLOW_PLANKS.get())
				.define('B', Items.BOOK)
				.unlockedBy(getHasName(HexcraftBlocks.WILLOW_PLANKS.get()), has(HexcraftBlocks.WILLOW_PLANKS.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.HAWTHORN_BOOKSHELF.get())
				.pattern("HHH")
				.pattern("BBB")
				.pattern("HHH")
				.define('H', HexcraftBlocks.HAWTHORN_PLANKS.get())
				.define('B', Items.BOOK)
				.unlockedBy(getHasName(HexcraftBlocks.HAWTHORN_PLANKS.get()), has(HexcraftBlocks.HAWTHORN_PLANKS.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.CEDAR_BOOKSHELF.get())
				.pattern("CCC")
				.pattern("BBB")
				.pattern("CCC")
				.define('C', HexcraftBlocks.CEDAR_PLANKS.get())
				.define('B', Items.BOOK)
				.unlockedBy(getHasName(HexcraftBlocks.CEDAR_PLANKS.get()), has(HexcraftBlocks.CEDAR_PLANKS.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.DISTORTED_BOOKSHELF.get())
				.pattern("DDD")
				.pattern("BBB")
				.pattern("DDD")
				.define('D', HexcraftBlocks.DISTORTED_PLANKS.get())
				.define('B', Items.BOOK)
				.unlockedBy(getHasName(HexcraftBlocks.DISTORTED_PLANKS.get()), has(HexcraftBlocks.DISTORTED_PLANKS.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ELDER_BOOKSHELF.get())
				.pattern("EEE")
				.pattern("BBB")
				.pattern("EEE")
				.define('E', HexcraftBlocks.ELDER_PLANKS.get())
				.define('B', Items.BOOK)
				.unlockedBy(getHasName(HexcraftBlocks.ELDER_PLANKS.get()), has(HexcraftBlocks.ELDER_PLANKS.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.JUNIPER_BOOKSHELF.get())
				.pattern("JJJ")
				.pattern("BBB")
				.pattern("JJJ")
				.define('J', HexcraftBlocks.JUNIPER_PLANKS.get())
				.define('B', Items.BOOK)
				.unlockedBy(getHasName(HexcraftBlocks.JUNIPER_PLANKS.get()), has(HexcraftBlocks.JUNIPER_PLANKS.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ROWAN_BOOKSHELF.get())
				.pattern("RRR")
				.pattern("BBB")
				.pattern("RRR")
				.define('R', HexcraftBlocks.ROWAN_PLANKS.get())
				.define('B', Items.BOOK)
				.unlockedBy(getHasName(HexcraftBlocks.ROWAN_PLANKS.get()), has(HexcraftBlocks.ROWAN_PLANKS.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.TWISTED_BOOKSHELF.get())
				.pattern("TTT")
				.pattern("BBB")
				.pattern("TTT")
				.define('T', HexcraftBlocks.TWISTED_PLANKS.get())
				.define('B', Items.BOOK)
				.unlockedBy(getHasName(HexcraftBlocks.TWISTED_PLANKS.get()), has(HexcraftBlocks.TWISTED_PLANKS.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.WITCH_WOOD_BOOKSHELF.get())
				.pattern("WWW")
				.pattern("BBB")
				.pattern("WWW")
				.define('W', HexcraftBlocks.WITCH_WOOD_PLANKS.get())
				.define('B', Items.BOOK)
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_WOOD_PLANKS.get()), has(HexcraftBlocks.WITCH_WOOD_PLANKS.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.ECHO_WOOD_BOOKSHELF.get())
				.pattern("EEE")
				.pattern("BBB")
				.pattern("EEE")
				.define('E', HexcraftBlocks.ECHO_WOOD_PLANKS.get())
				.define('B', Items.BOOK)
				.unlockedBy(getHasName(HexcraftBlocks.ECHO_WOOD_PLANKS.get()), has(HexcraftBlocks.ECHO_WOOD_PLANKS.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.PHOENIX_BOOKSHELF.get())
				.pattern("PPP")
				.pattern("BBB")
				.pattern("PPP")
				.define('P', HexcraftBlocks.PHOENIX_PLANKS.get())
				.define('B', Items.BOOK)
				.unlockedBy(getHasName(HexcraftBlocks.PHOENIX_PLANKS.get()), has(HexcraftBlocks.PHOENIX_PLANKS.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.WITCHES_SATCHEL.get())
				.pattern("SSS")
				.pattern("I I")
				.pattern("III")
				.define('S', Items.STRING)
				.define('I', HexcraftItems.INFUSED_FABRIC.get())
				.unlockedBy(getHasName(HexcraftItems.INFUSED_FABRIC.get()), has(HexcraftItems.INFUSED_FABRIC.get())).save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.POPPET.get())
				.pattern("ISI")
				.pattern("BSL")
				.pattern("I I")
				.define('L', Items.STRING)
				.define('B', HexcraftItems.BONE_NEEDLE.get())
				.define('S', HexcraftBlocks.SPANISH_MOSS.get())
				.define('I', HexcraftItems.INFUSED_FABRIC.get())
				.unlockedBy(getHasName(HexcraftItems.INFUSED_FABRIC.get()), has(HexcraftItems.INFUSED_FABRIC.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.WATER_PROTECTION_POPPET.get())
				.pattern(" W ")
				.pattern("IPI")
				.pattern(" W ")
				.define('W', HexcraftItems.WATER_ARTICHOKE.get())
				.define('P', HexcraftItems.POPPET.get())
				.define('I', Items.INK_SAC)
				.unlockedBy(getHasName(HexcraftItems.POPPET.get()), has(HexcraftItems.POPPET.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.VOODOO_PROTECTION_POPPET.get())
				.pattern("DMR")
				.pattern("BPT")
				.pattern("RJD")
				.define('D', Items.DANDELION)
				.define('M', Items.RED_MUSHROOM)
				.define('R', HexcraftBlocks.BLOODY_ROSE.get())
				.define('B', HexcraftItems.BELLADONNA.get())
				.define('P', HexcraftItems.POPPET.get())
				.define('T', HexcraftItems.MANDRAKE_ROOT.get())
				.define('J', Items.BROWN_MUSHROOM)
				.unlockedBy(getHasName(HexcraftItems.POPPET.get()), has(HexcraftItems.POPPET.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.VOODOO_POPPET.get())
				.pattern(" S ")
				.pattern("BPM")
				.pattern(" E ")
				.define('S', Items.FERMENTED_SPIDER_EYE)
				.define('B', HexcraftItems.BELLADONNA.get())
				.define('P', HexcraftItems.POPPET.get())
				.define('M', HexcraftItems.MANDRAKE_ROOT.get())
				.define('E', HexcraftItems.EXHALE_OF_THE_HORNED_ONE.get())
				.unlockedBy(getHasName(HexcraftItems.POPPET.get()), has(HexcraftItems.POPPET.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.FALL_PROTECTION_POPPET.get())
				.pattern(" C ")
				.pattern("FPF")
				.pattern(" D ")
				.define('C', Items.CLAY_BALL)
				.define('F', Items.FEATHER)
				.define('P', HexcraftItems.POPPET.get())
				.define('D', Items.DIRT)
				.unlockedBy(getHasName(HexcraftItems.POPPET.get()), has(HexcraftItems.POPPET.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.EXPLOSION_PROTECTION_POPPET.get())
				.pattern("OTO")
				.pattern("WPW")
				.pattern("I I")
				.define('O', Items.OBSIDIAN)
				.define('W', Items.WATER_BUCKET)
				.define('T', Items.TNT)
				.define('P', HexcraftItems.POPPET.get())
				.define('I', HexcraftItems.INFUSED_FABRIC.get())
				.unlockedBy(getHasName(HexcraftItems.POPPET.get()), has(HexcraftItems.POPPET.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.PROJECTILE_PROTECTION_POPPET.get())
				.pattern(" A ")
				.pattern("FPF")
				.pattern("IDI")
				.define('A', Items.ARROW)
				.define('F', Items.FEATHER)
				.define('P', HexcraftItems.POPPET.get())
				.define('D', Items.DANDELION)
				.define('I', HexcraftItems.INFUSED_FABRIC.get())
				.unlockedBy(getHasName(HexcraftItems.POPPET.get()), has(HexcraftItems.POPPET.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.HUNGER_PROTECTION_POPPET.get())
				.pattern(" R ")
				.pattern("GPG")
				.pattern(" R ")
				.define('R', Items.ROTTEN_FLESH)
				.define('G', Items.GLISTERING_MELON_SLICE)
				.define('P', HexcraftItems.POPPET.get())
				.unlockedBy(getHasName(HexcraftItems.POPPET.get()), has(HexcraftItems.POPPET.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.POTION_PROTECTION_POPPET.get())
				.pattern(" S ")
				.pattern("FPG")
				.pattern(" M ")
				.define('S', Items.SPIDER_EYE)
				.define('F', Items.PUFFERFISH)
				.define('G', Items.GOLDEN_CARROT)
				.define('M', Items.PHANTOM_MEMBRANE)
				.define('P', HexcraftItems.POPPET.get())
				.unlockedBy(getHasName(HexcraftItems.POPPET.get()), has(HexcraftItems.POPPET.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.VOID_PROTECTION_POPPET.get())
				.pattern(" E ")
				.pattern("IPI")
				.pattern(" B ")
				.define('E', Items.ENDER_PEARL)
				.define('I', HexcraftItems.INFUSED_FABRIC.get())
				.define('P', HexcraftItems.POPPET.get())
				.define('B', HexcraftItems.BREATH_OF_THE_GODDESS.get())
				.unlockedBy(getHasName(HexcraftItems.POPPET.get()), has(HexcraftItems.POPPET.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.CURSE_PROTECTION_POPPET.get())
				.pattern(" T ")
				.pattern("IPI")
				.pattern(" B ")
				.define('T', Items.POTION)
				.define('I', HexcraftItems.INFUSED_FABRIC.get())
				.define('P', HexcraftItems.POPPET.get())
				.define('B', Items.BLAZE_POWDER)
				.unlockedBy(getHasName(HexcraftItems.POPPET.get()), has(HexcraftItems.POPPET.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.VAMPIRIC_POPPET.get())
				.pattern(" S ")
				.pattern("GPB")
				.pattern(" V ")
				.define('S', HexcraftBlocks.SPANISH_MOSS.get())
				.define('G', Items.GHAST_TEAR)
				.define('P', HexcraftItems.POPPET.get())
				.define('B', HexcraftItems.BLOOD_BOTTLE.get())
				.define('V', HexcraftItems.VAMPIRIC_GEM.get())
				.unlockedBy(getHasName(HexcraftItems.POPPET.get()), has(HexcraftItems.POPPET.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.DEATH_PROTECTION_POPPET.get())
				.pattern("LGD")
				.pattern("GPG")
				.pattern(" G ")
				.define('L', HexcraftItems.DROP_OF_LUCK.get())
				.define('G', Items.GOLD_NUGGET)
				.define('P', HexcraftItems.POPPET.get())
				.define('D', HexcraftItems.DIAMOND_VAPOUR.get())
				.unlockedBy(getHasName(HexcraftItems.POPPET.get()), has(HexcraftItems.POPPET.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.FIRE_PROTECTION_POPPET.get())
				.pattern(" E ")
				.pattern("WPW")
				.pattern(" E ")
				.define('E', HexcraftBlocks.EMBER_MOSS.get())
				.define('W', HexcraftItems.WOOL_OF_BAT.get())
				.define('P', HexcraftItems.POPPET.get())
				.unlockedBy(getHasName(HexcraftItems.POPPET.get()), has(HexcraftItems.POPPET.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.WITHER_PROTECTION_POPPET.get())
				.pattern(" W ")
				.pattern("IPI")
				.pattern(" B ")
				.define('W', Items.WITHER_ROSE)
				.define('I', HexcraftItems.INFUSED_FABRIC.get())
				.define('P', HexcraftItems.POPPET.get())
				.define('B', HexcraftItems.WOOL_OF_BAT.get())
				.unlockedBy(getHasName(HexcraftItems.POPPET.get()), has(HexcraftItems.POPPET.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.ARMOR_PROTECTION_POPPET.get())
				.pattern(" R ")
				.pattern("LPL")
				.pattern(" D ")
				.define('R', HexcraftItems.REEK_OF_MISFORTUNE.get())
				.define('L', HexcraftItems.DROP_OF_LUCK.get())
				.define('P', HexcraftItems.POPPET.get())
				.define('D', HexcraftItems.DIAMOND_VAPOUR.get())
				.unlockedBy(getHasName(HexcraftItems.POPPET.get()), has(HexcraftItems.POPPET.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.TOOL_PROTECTION_POPPET.get())
				.pattern(" R ")
				.pattern("LPL")
				.pattern(" R ")
				.define('R', HexcraftItems.REEK_OF_MISFORTUNE.get())
				.define('L', HexcraftItems.DROP_OF_LUCK.get())
				.define('P', HexcraftItems.POPPET.get())
				.unlockedBy(getHasName(HexcraftItems.POPPET.get()), has(HexcraftItems.POPPET.get()))
				.save(pWriter);

		//Armor
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.STEEL_HELMET.get())
				.pattern("SSS")
				.pattern("S S")
				.define('S', HexcraftItems.STEEL_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.STEEL_INGOT.get()), has(HexcraftItems.STEEL_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.STEEL_CHESTPLATE.get())
				.pattern("S S")
				.pattern("SSS")
				.pattern("SSS")
				.define('S', HexcraftItems.STEEL_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.STEEL_INGOT.get()), has(HexcraftItems.STEEL_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.STEEL_LEGGING.get())
				.pattern("SSS")
				.pattern("S S")
				.pattern("S S")
				.define('S', HexcraftItems.STEEL_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.STEEL_INGOT.get()), has(HexcraftItems.STEEL_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.STEEL_BOOTS.get())
				.pattern("S S")
				.pattern("S S")
				.define('S', HexcraftItems.STEEL_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.STEEL_INGOT.get()), has(HexcraftItems.STEEL_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.DARK_STEEL_HELMET.get())
				.pattern("SSS")
				.pattern("S S")
				.define('S', HexcraftItems.DARK_STEEL.get())
				.unlockedBy(getHasName(HexcraftItems.DARK_STEEL.get()), has(HexcraftItems.DARK_STEEL.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.DARK_STEEL_CHESTPLATE.get())
				.pattern("S S")
				.pattern("SSS")
				.pattern("SSS")
				.define('S', HexcraftItems.DARK_STEEL.get())
				.unlockedBy(getHasName(HexcraftItems.DARK_STEEL.get()), has(HexcraftItems.DARK_STEEL.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.DARK_STEEL_LEGGING.get())
				.pattern("SSS")
				.pattern("S S")
				.pattern("S S")
				.define('S', HexcraftItems.DARK_STEEL.get())
				.unlockedBy(getHasName(HexcraftItems.DARK_STEEL.get()), has(HexcraftItems.DARK_STEEL.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.DARK_STEEL_BOOTS.get())
				.pattern("S S")
				.pattern("S S")
				.define('S', HexcraftItems.DARK_STEEL.get())
				.unlockedBy(getHasName(HexcraftItems.DARK_STEEL.get()), has(HexcraftItems.DARK_STEEL.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.BLOODY_NYKIUM_HELMET.get())
				.pattern("BBB")
				.pattern("B B")
				.define('B', HexcraftItems.DARK_STEEL.get())
				.unlockedBy(getHasName(HexcraftItems.DARK_STEEL.get()), has(HexcraftItems.DARK_STEEL.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.BLOODY_NYKIUM_CHESTPLATE.get())
				.pattern("B B")
				.pattern("BBB")
				.pattern("BBB")
				.define('B', HexcraftItems.BLOODY_NYKIUM.get())
				.unlockedBy(getHasName(HexcraftItems.BLOODY_NYKIUM.get()), has(HexcraftItems.BLOODY_NYKIUM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.BLOODY_NYKIUM_LEGGING.get())
				.pattern("BBB")
				.pattern("B B")
				.pattern("B B")
				.define('B', HexcraftItems.BLOODY_NYKIUM.get())
				.unlockedBy(getHasName(HexcraftItems.BLOODY_NYKIUM.get()), has(HexcraftItems.BLOODY_NYKIUM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.BLOODY_NYKIUM_BOOTS.get())
				.pattern("B B")
				.pattern("B B")
				.define('B', HexcraftItems.BLOODY_NYKIUM.get())
				.unlockedBy(getHasName(HexcraftItems.BLOODY_NYKIUM.get()), has(HexcraftItems.BLOODY_NYKIUM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.JORMIUM_HELMET.get())
				.pattern("JJJ")
				.pattern("J J")
				.define('J', HexcraftItems.JORMIUM_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.JORMIUM_INGOT.get()), has(HexcraftItems.JORMIUM_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.JORMIUM_CHESTPLATE.get())
				.pattern("J J")
				.pattern("JJJ")
				.pattern("JJJ")
				.define('J', HexcraftItems.JORMIUM_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.JORMIUM_INGOT.get()), has(HexcraftItems.JORMIUM_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.JORMIUM_LEGGING.get())
				.pattern("JJJ")
				.pattern("J J")
				.pattern("J J")
				.define('J', HexcraftItems.JORMIUM_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.JORMIUM_INGOT.get()), has(HexcraftItems.JORMIUM_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.JORMIUM_BOOTS.get())
				.pattern("J J")
				.pattern("J J")
				.define('J', HexcraftItems.JORMIUM_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.JORMIUM_INGOT.get()), has(HexcraftItems.JORMIUM_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.CUROGEN_HELMET.get())
				.pattern("CCC")
				.pattern("C C")
				.define('C', HexcraftItems.CUROGEN.get())
				.unlockedBy(getHasName(HexcraftItems.CUROGEN.get()), has(HexcraftItems.CUROGEN.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.CUROGEN_CHESTPLATE.get())
				.pattern("C C")
				.pattern("CCC")
				.pattern("CCC")
				.define('C', HexcraftItems.CUROGEN.get())
				.unlockedBy(getHasName(HexcraftItems.CUROGEN.get()), has(HexcraftItems.CUROGEN.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.CUROGEN_LEGGING.get())
				.pattern("CCC")
				.pattern("C C")
				.pattern("C C")
				.define('C', HexcraftItems.CUROGEN.get())
				.unlockedBy(getHasName(HexcraftItems.CUROGEN.get()), has(HexcraftItems.CUROGEN.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.CUROGEN_BOOTS.get())
				.pattern("C C")
				.pattern("C C")
				.define('C', HexcraftItems.CUROGEN.get())
				.unlockedBy(getHasName(HexcraftItems.CUROGEN.get()), has(HexcraftItems.CUROGEN.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.SILVER_HELMET.get())
				.pattern("SSS")
				.pattern("S S")
				.define('S', HexcraftItems.SILVER_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.SILVER_INGOT.get()), has(HexcraftItems.SILVER_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.SILVER_CHESTPLATE.get())
				.pattern("S S")
				.pattern("SSS")
				.pattern("SSS")
				.define('S', HexcraftItems.SILVER_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.SILVER_INGOT.get()), has(HexcraftItems.SILVER_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.SILVER_LEGGING.get())
				.pattern("SSS")
				.pattern("S S")
				.pattern("S S")
				.define('S', HexcraftItems.SILVER_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.SILVER_INGOT.get()), has(HexcraftItems.SILVER_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.SILVER_BOOTS.get())
				.pattern("S S")
				.pattern("S S")
				.define('S', HexcraftItems.SILVER_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.SILVER_INGOT.get()), has(HexcraftItems.SILVER_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.VAMPIRIC_HELMET.get())
				.pattern("VVV")
				.pattern("V V")
				.define('V', HexcraftItems.VAMPIRIC_GEM.get())
				.unlockedBy(getHasName(HexcraftItems.VAMPIRIC_GEM.get()), has(HexcraftItems.VAMPIRIC_GEM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.VAMPIRIC_CHESTPLATE.get())
				.pattern("V V")
				.pattern("VVV")
				.pattern("VVV")
				.define('V', HexcraftItems.VAMPIRIC_GEM.get())
				.unlockedBy(getHasName(HexcraftItems.VAMPIRIC_GEM.get()), has(HexcraftItems.VAMPIRIC_GEM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.VAMPIRIC_LEGGING.get())
				.pattern("VVV")
				.pattern("V V")
				.pattern("V V")
				.define('V', HexcraftItems.VAMPIRIC_GEM.get())
				.unlockedBy(getHasName(HexcraftItems.VAMPIRIC_GEM.get()), has(HexcraftItems.VAMPIRIC_GEM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.VAMPIRIC_BOOTS.get())
				.pattern("V V")
				.pattern("V V")
				.define('V', HexcraftItems.VAMPIRIC_GEM.get())
				.unlockedBy(getHasName(HexcraftItems.VAMPIRIC_GEM.get()), has(HexcraftItems.VAMPIRIC_GEM.get()))
				.save(pWriter);

		//Bows
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.STEEL_BOW.get())
				.pattern(" SL")
				.pattern("S L")
				.pattern(" SL")
				.define('S', HexcraftItems.STEEL_INGOT.get())
				.define('L', Items.STRING)
				.unlockedBy(getHasName(HexcraftItems.STEEL_INGOT.get()), has(HexcraftItems.STEEL_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.DARK_STEEL_BOW.get())
				.pattern(" SL")
				.pattern("S L")
				.pattern(" SL")
				.define('S', HexcraftItems.DARK_STEEL.get())
				.define('L', Items.STRING)
				.unlockedBy(getHasName(HexcraftItems.DARK_STEEL.get()), has(HexcraftItems.DARK_STEEL.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.BLOODY_NYKIUM_BOW.get())
				.pattern(" BL")
				.pattern("B L")
				.pattern(" BL")
				.define('B', HexcraftItems.BLOODY_NYKIUM.get())
				.define('L', Items.STRING)
				.unlockedBy(getHasName(HexcraftItems.BLOODY_NYKIUM.get()), has(HexcraftItems.BLOODY_NYKIUM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.JORMIUM_BOW.get())
				.pattern(" JL")
				.pattern("J L")
				.pattern(" JL")
				.define('J', HexcraftItems.JORMIUM_INGOT.get())
				.define('L', Items.STRING)
				.unlockedBy(getHasName(HexcraftItems.JORMIUM_INGOT.get()), has(HexcraftItems.JORMIUM_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.CUROGEN_BOW.get())
				.pattern(" CL")
				.pattern("C L")
				.pattern(" CL")
				.define('C', HexcraftItems.CUROGEN.get())
				.define('L', Items.STRING)
				.unlockedBy(getHasName(HexcraftItems.CUROGEN.get()), has(HexcraftItems.CUROGEN.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.FAIRY_SAND.get(), 8)
				.pattern("SSS")
				.pattern("SFS")
				.pattern("SSS")
				.define('F', HexcraftItems.FAIRY_DUST.get())
				.define('S', Blocks.SAND)
				.unlockedBy(getHasName(HexcraftItems.FAIRY_DUST.get()), has(HexcraftItems.FAIRY_DUST.get()))
				.unlockedBy(getHasName(Blocks.SAND), has(Blocks.SAND))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftBlocks.PIXIE_SAND.get(), 8)
				.pattern("SSS")
				.pattern("SPS")
				.pattern("SSS")
				.define('P', HexcraftItems.PIXIE_DUST.get())
				.define('S', Blocks.SAND)
				.unlockedBy(getHasName(HexcraftItems.PIXIE_DUST.get()), has(HexcraftItems.PIXIE_DUST.get()))
				.unlockedBy(getHasName(Blocks.SAND), has(Blocks.SAND))
				.save(pWriter);

		// Fairy Ward Recipe
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.FAIRY_WARD.get())
				.pattern(" D ")
				.pattern("GCG")
				.pattern(" D ")
				.define('D', HexcraftItems.FAIRY_DUST.get())
				.define('G', Items.GLOWSTONE_DUST)
				.define('C', HexcraftBlocks.FAIRY_LANTERN.get())
				.unlockedBy(getHasName(HexcraftItems.FAIRY_DUST.get()), has(HexcraftItems.FAIRY_DUST.get()))
				.save(pWriter);

// Pixie Ward Recipe
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftBlocks.PIXIE_WARD.get())
				.pattern(" P ")
				.pattern("FLF")
				.pattern(" P ")
				.define('P', HexcraftItems.PIXIE_DUST.get())
				.define('F', Items.GLOWSTONE_DUST)
				.define('L', HexcraftBlocks.FAIRY_LANTERN.get())
				.unlockedBy(getHasName(HexcraftItems.PIXIE_DUST.get()), has(HexcraftItems.PIXIE_DUST.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.DREAMWEAVER_CHARM.get())
				.pattern(" S ")
				.pattern("FMP")
				.pattern(" G ")
				.define('S', Items.SPIDER_EYE)
				.define('P', HexcraftItems.PIXIE_DUST.get())
				.define('F', HexcraftItems.FAIRY_DUST.get())
				.define('M', HexcraftItems.MOONSTONE.get())
				.define('G', Items.GLOWSTONE_DUST)
				.unlockedBy(getHasName(HexcraftItems.FAIRY_DUST.get()), has(HexcraftItems.FAIRY_DUST.get()))
				.unlockedBy(getHasName(HexcraftItems.PIXIE_DUST.get()), has(HexcraftItems.PIXIE_DUST.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.FAIRY_LANTERN.get())
				.pattern("NNN")
				.pattern("NFN")
				.pattern("NNN")
				.define('N', Items.IRON_NUGGET)
				.define('F', HexcraftItems.FAIRY_DUST.get())
				.unlockedBy(getHasName(HexcraftItems.FAIRY_DUST.get()), has(HexcraftItems.FAIRY_DUST.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.PIXIE_LANTERN.get())
				.pattern("NNN")
				.pattern("NPN")
				.pattern("NNN")
				.define('N', Items.IRON_NUGGET)
				.define('P', HexcraftItems.PIXIE_DUST.get())
				.unlockedBy(getHasName(HexcraftItems.PIXIE_DUST.get()), has(HexcraftItems.PIXIE_DUST.get()))
				.save(pWriter);

		//Tools
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.STEEL_SWORD.get())
				.pattern(" S ")
				.pattern(" S ")
				.pattern(" W ")
				.define('S', HexcraftItems.STEEL_INGOT.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.STEEL_INGOT.get()), has(HexcraftItems.STEEL_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.STEEL_PICKAXE.get())
				.pattern("SSS")
				.pattern(" W ")
				.pattern(" W ")
				.define('S', HexcraftItems.STEEL_INGOT.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.STEEL_INGOT.get()), has(HexcraftItems.STEEL_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.STEEL_SHOVEL.get())
				.pattern(" S ")
				.pattern(" W ")
				.pattern(" W ")
				.define('S', HexcraftItems.STEEL_INGOT.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.STEEL_INGOT.get()), has(HexcraftItems.STEEL_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.STEEL_AXE.get())
				.pattern("SS ")
				.pattern("SW ")
				.pattern(" W ")
				.define('S', HexcraftItems.STEEL_INGOT.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.STEEL_INGOT.get()), has(HexcraftItems.STEEL_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.STEEL_HOE.get())
				.pattern("SS ")
				.pattern(" W ")
				.pattern(" W ")
				.define('S', HexcraftItems.STEEL_INGOT.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.STEEL_INGOT.get()), has(HexcraftItems.STEEL_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.DARK_STEEL_SWORD.get())
				.pattern(" S ")
				.pattern(" S ")
				.pattern(" W ")
				.define('S', HexcraftItems.DARK_STEEL.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.DARK_STEEL.get()), has(HexcraftItems.DARK_STEEL.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.DARK_STEEL_PICKAXE.get())
				.pattern("SSS")
				.pattern(" W ")
				.pattern(" W ")
				.define('S', HexcraftItems.DARK_STEEL.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.DARK_STEEL.get()), has(HexcraftItems.DARK_STEEL.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.DARK_STEEL_SHOVEL.get())
				.pattern(" S ")
				.pattern(" W ")
				.pattern(" W ")
				.define('S', HexcraftItems.DARK_STEEL.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.DARK_STEEL.get()), has(HexcraftItems.DARK_STEEL.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.DARK_STEEL_AXE.get())
				.pattern("SS ")
				.pattern("SW ")
				.pattern(" W ")
				.define('S', HexcraftItems.DARK_STEEL.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.DARK_STEEL.get()), has(HexcraftItems.DARK_STEEL.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.DARK_STEEL_HOE.get())
				.pattern("SS ")
				.pattern(" W ")
				.pattern(" W ")
				.define('S', HexcraftItems.DARK_STEEL.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.DARK_STEEL.get()), has(HexcraftItems.DARK_STEEL.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.BLOODY_NYKIUM_SWORD.get())
				.pattern(" B ")
				.pattern(" B ")
				.pattern(" W ")
				.define('B', HexcraftItems.BLOODY_NYKIUM.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.BLOODY_NYKIUM.get()), has(HexcraftItems.BLOODY_NYKIUM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.BLOODY_NYKIUM_PICKAXE.get())
				.pattern("BBB")
				.pattern(" W ")
				.pattern(" W ")
				.define('B', HexcraftItems.BLOODY_NYKIUM.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.BLOODY_NYKIUM.get()), has(HexcraftItems.BLOODY_NYKIUM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.BLOODY_NYKIUM_SHOVEL.get())
				.pattern(" B ")
				.pattern(" W ")
				.pattern(" W ")
				.define('B', HexcraftItems.BLOODY_NYKIUM.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.BLOODY_NYKIUM.get()), has(HexcraftItems.BLOODY_NYKIUM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.BLOODY_NYKIUM_AXE.get())
				.pattern("BB ")
				.pattern("BW ")
				.pattern(" W ")
				.define('B', HexcraftItems.BLOODY_NYKIUM.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.BLOODY_NYKIUM.get()), has(HexcraftItems.BLOODY_NYKIUM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.BLOODY_NYKIUM_HOE.get())
				.pattern("BB ")
				.pattern(" W ")
				.pattern(" W ")
				.define('B', HexcraftItems.BLOODY_NYKIUM.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.BLOODY_NYKIUM.get()), has(HexcraftItems.BLOODY_NYKIUM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.JORMIUM_SWORD.get())
				.pattern(" J ")
				.pattern(" J ")
				.pattern(" W ")
				.define('J', HexcraftItems.JORMIUM_INGOT.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.JORMIUM_INGOT.get()), has(HexcraftItems.JORMIUM_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.JORMIUM_PICKAXE.get())
				.pattern("JJJ")
				.pattern(" W ")
				.pattern(" W ")
				.define('J', HexcraftItems.JORMIUM_INGOT.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.JORMIUM_INGOT.get()), has(HexcraftItems.JORMIUM_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.JORMIUM_SHOVEL.get())
				.pattern(" J ")
				.pattern(" W ")
				.pattern(" W ")
				.define('J', HexcraftItems.JORMIUM_INGOT.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.JORMIUM_INGOT.get()), has(HexcraftItems.JORMIUM_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.JORMIUM_AXE.get())
				.pattern("JJ ")
				.pattern("JW ")
				.pattern(" W ")
				.define('J', HexcraftItems.JORMIUM_INGOT.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.JORMIUM_INGOT.get()), has(HexcraftItems.JORMIUM_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.JORMIUM_HOE.get())
				.pattern("JJ ")
				.pattern(" W ")
				.pattern(" W ")
				.define('J', HexcraftItems.JORMIUM_INGOT.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.JORMIUM_INGOT.get()), has(HexcraftItems.JORMIUM_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.CUROGEN_SWORD.get())
				.pattern(" C ")
				.pattern(" C ")
				.pattern(" W ")
				.define('C', HexcraftItems.CUROGEN.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.CUROGEN.get()), has(HexcraftItems.CUROGEN.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.CUROGEN_PICKAXE.get())
				.pattern("CCC")
				.pattern(" W ")
				.pattern(" W ")
				.define('C', HexcraftItems.CUROGEN.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.CUROGEN.get()), has(HexcraftItems.CUROGEN.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.CUROGEN_SHOVEL.get())
				.pattern(" C ")
				.pattern(" W ")
				.pattern(" W ")
				.define('C', HexcraftItems.CUROGEN.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.CUROGEN.get()), has(HexcraftItems.CUROGEN.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.CUROGEN_AXE.get())
				.pattern("CC ")
				.pattern("CW ")
				.pattern(" W ")
				.define('C', HexcraftItems.CUROGEN.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.CUROGEN.get()), has(HexcraftItems.CUROGEN.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.CUROGEN_HOE.get())
				.pattern("CC ")
				.pattern(" W ")
				.pattern(" W ")
				.define('C', HexcraftItems.CUROGEN.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.CUROGEN.get()), has(HexcraftItems.CUROGEN.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.SILVER_SWORD.get())
				.pattern(" S ")
				.pattern(" S ")
				.pattern(" W ")
				.define('S', HexcraftItems.SILVER_INGOT.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.SILVER_INGOT.get()), has(HexcraftItems.SILVER_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.SILVER_PICKAXE.get())
				.pattern("SSS")
				.pattern(" W ")
				.pattern(" W ")
				.define('S', HexcraftItems.SILVER_INGOT.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.SILVER_INGOT.get()), has(HexcraftItems.SILVER_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.SILVER_SHOVEL.get())
				.pattern(" S ")
				.pattern(" W ")
				.pattern(" W ")
				.define('S', HexcraftItems.SILVER_INGOT.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.SILVER_INGOT.get()), has(HexcraftItems.SILVER_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.SILVER_AXE.get())
				.pattern("SS ")
				.pattern("SW ")
				.pattern(" W ")
				.define('S', HexcraftItems.SILVER_INGOT.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.SILVER_INGOT.get()), has(HexcraftItems.SILVER_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.SILVER_HOE.get())
				.pattern("SS ")
				.pattern(" W ")
				.pattern(" W ")
				.define('S', HexcraftItems.SILVER_INGOT.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.SILVER_INGOT.get()), has(HexcraftItems.SILVER_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.VAMPIRIC_SWORD.get())
				.pattern(" V ")
				.pattern(" V ")
				.pattern(" W ")
				.define('V', HexcraftItems.VAMPIRIC_GEM.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.VAMPIRIC_GEM.get()), has(HexcraftItems.VAMPIRIC_GEM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.VAMPIRIC_PICKAXE.get())
				.pattern("VVV")
				.pattern(" W ")
				.pattern(" W ")
				.define('V', HexcraftItems.VAMPIRIC_GEM.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.VAMPIRIC_GEM.get()), has(HexcraftItems.VAMPIRIC_GEM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.VAMPIRIC_SHOVEL.get())
				.pattern(" V ")
				.pattern(" W ")
				.pattern(" W ")
				.define('V', HexcraftItems.VAMPIRIC_GEM.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.VAMPIRIC_GEM.get()), has(HexcraftItems.VAMPIRIC_GEM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.VAMPIRIC_AXE.get())
				.pattern("VV ")
				.pattern("VW ")
				.pattern(" W ")
				.define('V', HexcraftItems.VAMPIRIC_GEM.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.VAMPIRIC_GEM.get()), has(HexcraftItems.VAMPIRIC_GEM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.VAMPIRIC_HOE.get())
				.pattern("VV ")
				.pattern(" W ")
				.pattern(" W ")
				.define('V', HexcraftItems.VAMPIRIC_GEM.get())
				.define('W', Items.STICK)
				.unlockedBy(getHasName(HexcraftItems.VAMPIRIC_GEM.get()), has(HexcraftItems.VAMPIRIC_GEM.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.GARLIC_STRAND_ITEM.get())
				.pattern("SGS")
				.pattern("GSG")
				.define('G', HexcraftItems.GARLIC.get())
				.define('S', Items.STRING)
				.unlockedBy(getHasName(HexcraftItems.GARLIC.get()), has(HexcraftItems.GARLIC.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.VERVAIN_STRAND_ITEM.get())
				.pattern("SVS")
				.pattern("VSV")
				.define('V', HexcraftItems.VERVAIN.get())
				.define('S', Items.STRING)
				.unlockedBy(getHasName(HexcraftItems.VERVAIN.get()), has(HexcraftItems.VERVAIN.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.SACRIFICIAL_PILLAR.get())
				.pattern("D D")
				.pattern(" V ")
				.pattern(" E ")
				.define('D', HexcraftItems.DARK_CRYSTAL_SHARDS.get())
				.define('V', HexcraftItems.VAMPIRIC_GEM.get())
				.define('E', HexcraftBlocks.ECLIPSIUM_BLOCK.get())
				.unlockedBy(getHasName(HexcraftItems.DARK_CRYSTAL_SHARDS.get()), has(HexcraftItems.DARK_CRYSTAL_SHARDS.get()))
				.unlockedBy(getHasName(HexcraftItems.VAMPIRIC_GEM.get()), has(HexcraftItems.VAMPIRIC_GEM.get()))
				.unlockedBy(getHasName(HexcraftBlocks.ECLIPSIUM_BLOCK.get()), has(HexcraftBlocks.ECLIPSIUM_BLOCK.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, HexcraftItems.DAYLIGHT_RING.get())
				.pattern("GBG")
				.pattern("MWM")
				.pattern("GGG")
				.define('G', Items.GOLD_INGOT)
				.define('B', HexcraftItems.BLOOD_DROP.get())
				.define('M', HexcraftItems.MOONSTONE.get())
				.define('W', HexcraftItems.WITHER_BONE.get())
				.unlockedBy(getHasName(HexcraftItems.MOONSTONE.get()), has(HexcraftItems.MOONSTONE.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.INFERNAL_EMBER.get())
				.pattern("FBF")
				.pattern("LNL")
				.pattern("FBF")
				.define('F', Items.FLINT)
				.define('B', Items.BLAZE_POWDER)
				.define('L', Items.LAVA_BUCKET)
				.define('N', HexcraftItems.NYKIUM_INGOT.get()) // or a mod item available pre-Underworld
				.unlockedBy(getHasName(HexcraftItems.NYKIUM_INGOT.get()), has(HexcraftItems.NYKIUM_INGOT.get()))
				.save(pWriter);

		SmithingTransformRecipeBuilder.smithing(
						Ingredient.EMPTY, // No template
						Ingredient.of(Items.FLINT_AND_STEEL),
						Ingredient.of(HexcraftItems.INFERNAL_EMBER.get()),
						RecipeCategory.TOOLS,
						HexcraftItems.FLINT_AND_HELLFIRE.get()
				).unlocks("has_infernal_ember", has(HexcraftItems.INFERNAL_EMBER.get()))
				.save(pWriter, HexcraftMod.MOD_ID + ":flint_and_hellfire");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.ENHANCED_JORMIUM_INGOT.get())
				.define('J', HexcraftItems.JORMIUM_INGOT.get())
				.define('C', HexcraftItems.MAGIC_CRYSTAL.get())  // or a special catalyst item
				.pattern(" C ")
				.pattern("J J")
				.pattern(" J ")
				.unlockedBy("has_jormium_ingot", has(HexcraftItems.JORMIUM_INGOT.get()))
				.save(pWriter);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HexcraftItems.NECROTIC_STONE.get())
				.define('B', Items.BONE_BLOCK)
				.define('A', HexcraftItems.ASH.get())
				.define('D', HexcraftItems.DARK_CRYSTAL_SHARDS.get())
				.pattern(" A ")
				.pattern("DBD")
				.pattern(" A ")
				.unlockedBy("has_dark_crystal_shard", has(HexcraftItems.DARK_CRYSTAL_SHARDS.get()))
				.save(pWriter);

		// WHITE WITCH CANDLE
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.WHITE_WITCH_CANDLE.get())
				.define('C', Items.CANDLE)
				.define('S', Items.STRING)
				.define('H', Items.HONEYCOMB)
				.define('D', Items.WHITE_DYE)
				.pattern(" S ")
				.pattern("CHC")
				.pattern(" D ")
				.unlockedBy("has_candle", has(Items.CANDLE))
				.save(pWriter);

// ORANGE WITCH CANDLE
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.ORANGE_WITCH_CANDLE.get())
				.define('C', Items.CANDLE)
				.define('S', Items.STRING)
				.define('H', Items.HONEYCOMB)
				.define('D', Items.ORANGE_DYE)
				.define('N', Items.NETHER_WART)  // natural pigment
				.pattern(" S ")
				.pattern("CHN")
				.pattern(" D ")
				.unlockedBy("has_nether_wart", has(Items.NETHER_WART))
				.save(pWriter);

// MAGENTA WITCH CANDLE
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.MAGENTA_WITCH_CANDLE.get())
				.define('C', Items.CANDLE)
				.define('S', Items.STRING)
				.define('H', Items.HONEYCOMB)
				.define('D', Items.MAGENTA_DYE)
				.define('B', Items.BEETROOT)  // natural red pigment for magenta tint
				.pattern(" S ")
				.pattern("CHB")
				.pattern(" D ")
				.unlockedBy("has_beetroot", has(Items.BEETROOT))
				.save(pWriter);

// LIGHT BLUE WITCH CANDLE
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.LIGHT_BLUE_WITCH_CANDLE.get())
				.define('C', Items.CANDLE)
				.define('S', Items.STRING)
				.define('H', Items.HONEYCOMB)
				.define('D', Items.LIGHT_BLUE_DYE)
				.define('L', Items.LAPIS_LAZULI)  // blue pigment
				.pattern(" S ")
				.pattern("CHL")
				.pattern(" D ")
				.unlockedBy("has_lapis_lazuli", has(Items.LAPIS_LAZULI))
				.save(pWriter);

// YELLOW WITCH CANDLE
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.YELLOW_WITCH_CANDLE.get())
				.define('C', Items.CANDLE)
				.define('S', Items.STRING)
				.define('H', Items.HONEYCOMB)
				.define('D', Items.YELLOW_DYE)
				.define('A', Items.HAY_BLOCK)  // straw for a natural touch
				.pattern(" S ")
				.pattern("CHA")
				.pattern(" D ")
				.unlockedBy("has_hay_block", has(Items.HAY_BLOCK))
				.save(pWriter);

// LIME WITCH CANDLE
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.LIME_WITCH_CANDLE.get())
				.define('C', Items.CANDLE)
				.define('S', Items.STRING)
				.define('H', Items.HONEYCOMB)
				.define('D', Items.LIME_DYE)
				.define('L', Items.OAK_LEAVES)  // natural leaves for herbal vibe
				.pattern(" S ")
				.pattern("CHL")
				.pattern(" D ")
				.unlockedBy("has_oak_leaves", has(Items.OAK_LEAVES))
				.save(pWriter);

// PINK WITCH CANDLE
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.PINK_WITCH_CANDLE.get())
				.define('C', Items.CANDLE)
				.define('S', Items.STRING)
				.define('H', Items.HONEYCOMB)
				.define('D', Items.PINK_DYE)
				.define('R', Items.ROSE_BUSH)  // floral natural ingredient
				.pattern(" S ")
				.pattern("CHR")
				.pattern(" D ")
				.unlockedBy("has_rose_bush", has(Items.ROSE_BUSH))
				.save(pWriter);

// GRAY WITCH CANDLE
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.GRAY_WITCH_CANDLE.get())
				.define('C', Items.CANDLE)
				.define('S', Items.STRING)
				.define('H', Items.HONEYCOMB)
				.define('D', Items.GRAY_DYE)
				.define('O', Items.COAL)  // charcoal for grayish pigment
				.pattern(" S ")
				.pattern("CHO")
				.pattern(" D ")
				.unlockedBy("has_coal", has(Items.COAL))
				.save(pWriter);

// LIGHT GRAY WITCH CANDLE
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.LIGHT_GRAY_WITCH_CANDLE.get())
				.define('C', Items.CANDLE)
				.define('S', Items.STRING)
				.define('H', Items.HONEYCOMB)
				.define('D', Items.LIGHT_GRAY_DYE)
				.define('B', Items.BONE_MEAL)  // natural whitening agent
				.pattern(" S ")
				.pattern("CHB")
				.pattern(" D ")
				.unlockedBy("has_bone_meal", has(Items.BONE_MEAL))
				.save(pWriter);

// CYAN WITCH CANDLE
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.CYAN_WITCH_CANDLE.get())
				.define('C', Items.CANDLE)
				.define('S', Items.STRING)
				.define('H', Items.HONEYCOMB)
				.define('D', Items.CYAN_DYE)
				.define('L', Items.LAPIS_LAZULI)
				.pattern(" S ")
				.pattern("CHL")
				.pattern(" D ")
				.unlockedBy("has_lapis_lazuli", has(Items.LAPIS_LAZULI))
				.save(pWriter);

// PURPLE WITCH CANDLE
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.PURPLE_WITCH_CANDLE.get())
				.define('C', Items.CANDLE)
				.define('S', Items.STRING)
				.define('H', Items.HONEYCOMB)
				.define('D', Items.PURPLE_DYE)
				.define('B', Items.BEETROOT)
				.pattern(" S ")
				.pattern("CHB")
				.pattern(" D ")
				.unlockedBy("has_beetroot", has(Items.BEETROOT))
				.save(pWriter);

// BLUE WITCH CANDLE
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.BLUE_WITCH_CANDLE.get())
				.define('C', Items.CANDLE)
				.define('S', Items.STRING)
				.define('H', Items.HONEYCOMB)
				.define('D', Items.BLUE_DYE)
				.define('L', Items.LAPIS_LAZULI)
				.pattern(" S ")
				.pattern("CHL")
				.pattern(" D ")
				.unlockedBy("has_lapis_lazuli", has(Items.LAPIS_LAZULI))
				.save(pWriter);

// BROWN WITCH CANDLE
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.BROWN_WITCH_CANDLE.get())
				.define('C', Items.CANDLE)
				.define('S', Items.STRING)
				.define('H', Items.HONEYCOMB)
				.define('D', Items.BROWN_DYE)
				.define('O', Items.COCOA_BEANS)  // natural brown pigment
				.pattern(" S ")
				.pattern("CHO")  // changed from CHC to CHO to include cocoa beans
				.pattern(" D ")
				.unlockedBy("has_cocoa_beans", has(Items.COCOA_BEANS))
				.save(pWriter);

// GREEN WITCH CANDLE
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.GREEN_WITCH_CANDLE.get())
				.define('C', Items.CANDLE)
				.define('S', Items.STRING)
				.define('H', Items.HONEYCOMB)
				.define('D', Items.GREEN_DYE)
				.define('L', Items.OAK_LEAVES)
				.pattern(" S ")
				.pattern("CHL")
				.pattern(" D ")
				.unlockedBy("has_oak_leaves", has(Items.OAK_LEAVES))
				.save(pWriter);

// RED WITCH CANDLE
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.RED_WITCH_CANDLE.get())
				.define('C', Items.CANDLE)
				.define('S', Items.STRING)
				.define('H', Items.HONEYCOMB)
				.define('D', Items.RED_DYE)
				.define('N', Items.NETHER_WART)
				.pattern(" S ")
				.pattern("CHN")
				.pattern(" D ")
				.unlockedBy("has_nether_wart", has(Items.NETHER_WART))
				.save(pWriter);

// BLACK WITCH CANDLE
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, HexcraftBlocks.BLACK_WITCH_CANDLE.get())
				.define('C', Items.CANDLE)
				.define('S', Items.STRING)
				.define('H', Items.HONEYCOMB)
				.define('D', Items.BLACK_DYE)
				.define('O', Items.COAL)
				.pattern(" S ")
				.pattern("CHO")
				.pattern(" D ")
				.unlockedBy("has_coal", has(Items.COAL))
				.save(pWriter);

		//Shapeless
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.BLOODY_NYKIUM_NUGGET.get(), 9)
				.requires(HexcraftItems.BLOODY_NYKIUM.get())
				.unlockedBy(getHasName(HexcraftItems.BLOODY_NYKIUM.get()), has(HexcraftItems.BLOODY_NYKIUM.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.CORRUPTED_STEEL_NUGGET.get(), 9)
				.requires(HexcraftItems.CORRUPTED_STEEL.get())
				.unlockedBy(getHasName(HexcraftItems.CORRUPTED_STEEL.get()), has(HexcraftItems.BLOODY_NYKIUM.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.CUROGEN_NUGGET.get(), 9)
				.requires(HexcraftItems.CUROGEN.get())
				.unlockedBy(getHasName(HexcraftItems.CUROGEN.get()), has(HexcraftItems.CUROGEN.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.DARK_STEEL_NUGGET.get(), 9)
				.requires(HexcraftItems.DARK_STEEL.get())
				.unlockedBy(getHasName(HexcraftItems.DARK_STEEL.get()), has(HexcraftItems.DARK_STEEL.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.JORMIUM_NUGGET.get(), 9)
				.requires(HexcraftItems.JORMIUM_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.JORMIUM_INGOT.get()), has(HexcraftItems.JORMIUM_INGOT.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.SILVER_NUGGET.get(), 9)
				.requires(HexcraftItems.SILVER_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.STEEL_INGOT.get()), has(HexcraftItems.STEEL_INGOT.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.SOULSTONE_NUGGET.get(), 9)
				.requires(HexcraftItems.SOULSTONE_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.SOULSTONE_INGOT.get()), has(HexcraftItems.SOULSTONE_INGOT.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.ABYSSIUM_NUGGET.get(), 9)
				.requires(HexcraftItems.ABYSSIUM_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.ABYSSIUM_INGOT.get()), has(HexcraftItems.ABYSSIUM_INGOT.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.ECLIPSIUM_NUGGET.get(), 9)
				.requires(HexcraftItems.ECLIPSIUM_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.ECLIPSIUM_INGOT.get()), has(HexcraftItems.ECLIPSIUM_INGOT.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.SILVER_INGOT.get(), 9)
				.requires(HexcraftBlocks.SILVER_BLOCK.get())
				.unlockedBy(getHasName(HexcraftItems.SILVER_INGOT.get()), has(HexcraftItems.SILVER_INGOT.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.VAMPIRIC_GEM.get(), 9)
				.requires(HexcraftBlocks.VAMPIRIC_BLOCK.get())
				.unlockedBy(getHasName(HexcraftItems.VAMPIRIC_GEM.get()), has(HexcraftItems.VAMPIRIC_GEM.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.MOONSTONE.get(), 9)
				.requires(HexcraftBlocks.MOONSTONE_BLOCK.get())
				.unlockedBy(getHasName(HexcraftItems.MOONSTONE.get()), has(HexcraftItems.MOONSTONE.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.SOULSTONE_INGOT.get(), 9)
				.requires(HexcraftBlocks.SOULSTONE_BLOCK.get())
				.unlockedBy(getHasName(HexcraftItems.SOULSTONE_INGOT.get()), has(HexcraftItems.SOULSTONE_INGOT.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.ABYSSIUM_INGOT.get(), 9)
				.requires(HexcraftBlocks.ABYSSIUM_BLOCK.get())
				.unlockedBy(getHasName(HexcraftItems.ABYSSIUM_INGOT.get()), has(HexcraftItems.ABYSSIUM_INGOT.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.ECLIPSIUM_INGOT.get(), 9)
				.requires(HexcraftBlocks.ECLIPSIUM_BLOCK.get())
				.unlockedBy(getHasName(HexcraftItems.ECLIPSIUM_INGOT.get()), has(HexcraftItems.ECLIPSIUM_INGOT.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.ABYSSAL_COAL.get(), 9)
				.requires(HexcraftBlocks.ABYSSAL_COAL_BLOCK.get())
				.unlockedBy(getHasName(HexcraftItems.ABYSSAL_COAL.get()), has(HexcraftItems.ABYSSAL_COAL.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.BONE_NEEDLE.get(), 1)
				.requires(Items.BONE)
				.requires(Items.FLINT)
				.unlockedBy(getHasName(Items.BONE), has(Items.BONE))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.TAGLOCK_KIT.get(), 1)
				.requires(HexcraftItems.BONE_NEEDLE.get())
				.requires(Items.GLASS_BOTTLE)
				.unlockedBy(getHasName(Items.GLASS_BOTTLE), has(Items.GLASS_BOTTLE))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.STEEL_POWDER.get(), 1)
				.requires(Items.IRON_NUGGET)
				.requires(Items.COAL)
				.requires(Items.IRON_NUGGET)
				.unlockedBy(getHasName(Items.COAL), has(Items.COAL))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.PINK_DYE)
				.requires(HexcraftBlocks.VAMPIRE_ORCHID.get())
				.unlockedBy(getHasName(HexcraftBlocks.VAMPIRE_ORCHID.get()), has(HexcraftBlocks.VAMPIRE_ORCHID.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.RED_DYE)
				.requires(HexcraftBlocks.BLOODY_ROSE.get())
				.unlockedBy(getHasName(HexcraftBlocks.BLOODY_ROSE.get()), has(HexcraftBlocks.BLOODY_ROSE.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.ANOINTING_PASTE.get())
				.requires(HexcraftItems.WATER_ARTICHOKE_SEEDS.get()).requires(HexcraftItems.MANDRAKE_SEEDS.get())
				.requires(HexcraftItems.BELLADONNA_SEEDS.get()).requires(HexcraftItems.AERPINE_SEEDS.get())
				.unlockedBy(getHasName(HexcraftItems.BELLADONNA_SEEDS.get()), has(HexcraftItems.BELLADONNA_SEEDS.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.EBONY_PLANKS.get(), 4)
				.requires(HexcraftBlocks.EBONY_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.EBONY_LOG.get()), has(HexcraftBlocks.EBONY_LOG.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.BLOOD_OAK_PLANKS.get(), 4)
				.requires(HexcraftBlocks.BLOOD_OAK_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.BLOOD_OAK_LOG.get()), has(HexcraftBlocks.BLOOD_OAK_LOG.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.HELL_BARK_PLANKS.get(), 4)
				.requires(HexcraftBlocks.HELL_BARK_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.HELL_BARK_LOG.get()), has(HexcraftBlocks.HELL_BARK_LOG.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.WHITE_OAK_PLANKS.get(), 4)
				.requires(HexcraftBlocks.WHITE_OAK_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.WHITE_OAK_LOG.get()), has(HexcraftBlocks.WHITE_OAK_LOG.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.ALDER_PLANKS.get(), 4)
				.requires(HexcraftBlocks.ALDER_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.ALDER_LOG.get()), has(HexcraftBlocks.ALDER_LOG.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.WITCH_HAZEL_PLANKS.get(), 4)
				.requires(HexcraftBlocks.WITCH_HAZEL_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_HAZEL_LOG.get()), has(HexcraftBlocks.WITCH_HAZEL_LOG.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.WILLOW_PLANKS.get(), 4)
				.requires(HexcraftBlocks.WILLOW_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.WILLOW_LOG.get()), has(HexcraftBlocks.WILLOW_LOG.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.HAWTHORN_PLANKS.get(), 4)
				.requires(HexcraftBlocks.HAWTHORN_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.HAWTHORN_LOG.get()), has(HexcraftBlocks.HAWTHORN_LOG.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.CEDAR_PLANKS.get(), 4)
				.requires(HexcraftBlocks.CEDAR_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.CEDAR_LOG.get()), has(HexcraftBlocks.CEDAR_LOG.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.DISTORTED_PLANKS.get(), 4)
				.requires(HexcraftBlocks.DISTORTED_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.DISTORTED_LOG.get()), has(HexcraftBlocks.DISTORTED_LOG.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.ELDER_PLANKS.get(), 4)
				.requires(HexcraftBlocks.ELDER_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.ELDER_LOG.get()), has(HexcraftBlocks.ELDER_LOG.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.JUNIPER_PLANKS.get(), 4)
				.requires(HexcraftBlocks.JUNIPER_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.JUNIPER_LOG.get()), has(HexcraftBlocks.JUNIPER_LOG.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.ROWAN_PLANKS.get(), 4)
				.requires(HexcraftBlocks.ROWAN_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.ROWAN_LOG.get()), has(HexcraftBlocks.ROWAN_LOG.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.TWISTED_PLANKS.get(), 4)
				.requires(HexcraftBlocks.TWISTED_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.TWISTED_LOG.get()), has(HexcraftBlocks.TWISTED_LOG.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.WITCH_WOOD_PLANKS.get(), 4)
				.requires(HexcraftBlocks.WITCH_WOOD_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_WOOD_LOG.get()), has(HexcraftBlocks.WITCH_WOOD_LOG.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.ECHO_WOOD_PLANKS.get(), 4)
				.requires(HexcraftBlocks.ECHO_WOOD_LOG.get())
				.unlockedBy(getHasName(HexcraftBlocks.ECHO_WOOD_LOG.get()), has(HexcraftBlocks.ECHO_WOOD_LOG.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.EBONY_CHEST_BOAT.get())
				.requires(HexcraftItems.EBONY_BOAT.get())
				.requires(Items.CHEST)
				.unlockedBy(getHasName(HexcraftItems.EBONY_BOAT.get()), has(HexcraftItems.EBONY_BOAT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.BLOOD_OAK_CHEST_BOAT.get())
				.requires(HexcraftItems.BLOOD_OAK_BOAT.get())
				.requires(Items.CHEST)
				.unlockedBy(getHasName(HexcraftItems.BLOOD_OAK_BOAT.get()), has(HexcraftItems.BLOOD_OAK_BOAT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.HELL_BARK_CHEST_BOAT.get())
				.requires(HexcraftItems.HELL_BARK_BOAT.get())
				.requires(Items.CHEST)
				.unlockedBy(getHasName(HexcraftItems.HELL_BARK_BOAT.get()), has(HexcraftItems.HELL_BARK_BOAT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.WHITE_OAK_CHEST_BOAT.get())
				.requires(HexcraftItems.WHITE_OAK_BOAT.get())
				.requires(Items.CHEST)
				.unlockedBy(getHasName(HexcraftItems.WHITE_OAK_BOAT.get()), has(HexcraftItems.WHITE_OAK_BOAT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.ALDER_CHEST_BOAT.get())
				.requires(HexcraftItems.ALDER_BOAT.get())
				.requires(Items.CHEST)
				.unlockedBy(getHasName(HexcraftItems.ALDER_BOAT.get()), has(HexcraftItems.ALDER_BOAT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.WITCH_HAZEL_CHEST_BOAT.get())
				.requires(HexcraftItems.WITCH_HAZEL_BOAT.get())
				.requires(Items.CHEST)
				.unlockedBy(getHasName(HexcraftItems.WITCH_HAZEL_BOAT.get()), has(HexcraftItems.WITCH_HAZEL_BOAT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.WILLOW_CHEST_BOAT.get())
				.requires(HexcraftItems.WILLOW_BOAT.get())
				.requires(Items.CHEST)
				.unlockedBy(getHasName(HexcraftItems.WILLOW_BOAT.get()), has(HexcraftItems.WILLOW_BOAT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.HAWTHORN_CHEST_BOAT.get())
				.requires(HexcraftItems.HAWTHORN_BOAT.get())
				.requires(Items.CHEST)
				.unlockedBy(getHasName(HexcraftItems.HAWTHORN_BOAT.get()), has(HexcraftItems.HAWTHORN_BOAT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.CEDAR_CHEST_BOAT.get())
				.requires(HexcraftItems.CEDAR_BOAT.get())
				.requires(Items.CHEST)
				.unlockedBy(getHasName(HexcraftItems.CEDAR_BOAT.get()), has(HexcraftItems.CEDAR_BOAT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.DISTORTED_CHEST_BOAT.get())
				.requires(HexcraftItems.DISTORTED_BOAT.get())
				.requires(Items.CHEST)
				.unlockedBy(getHasName(HexcraftItems.DISTORTED_BOAT.get()), has(HexcraftItems.DISTORTED_BOAT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.ELDER_CHEST_BOAT.get())
				.requires(HexcraftItems.ELDER_BOAT.get())
				.requires(Items.CHEST)
				.unlockedBy(getHasName(HexcraftItems.ELDER_BOAT.get()), has(HexcraftItems.ELDER_BOAT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.JUNIPER_CHEST_BOAT.get())
				.requires(HexcraftItems.JUNIPER_BOAT.get())
				.requires(Items.CHEST)
				.unlockedBy(getHasName(HexcraftItems.JUNIPER_BOAT.get()), has(HexcraftItems.JUNIPER_BOAT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.ROWAN_CHEST_BOAT.get())
				.requires(HexcraftItems.ROWAN_BOAT.get())
				.requires(Items.CHEST)
				.unlockedBy(getHasName(HexcraftItems.ROWAN_BOAT.get()), has(HexcraftItems.ROWAN_BOAT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.TWISTED_CHEST_BOAT.get())
				.requires(HexcraftItems.TWISTED_BOAT.get())
				.requires(Items.CHEST)
				.unlockedBy(getHasName(HexcraftItems.TWISTED_BOAT.get()), has(HexcraftItems.TWISTED_BOAT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.WITCH_WOOD_CHEST_BOAT.get())
				.requires(HexcraftItems.WITCH_WOOD_BOAT.get())
				.requires(Items.CHEST)
				.unlockedBy(getHasName(HexcraftItems.WITCH_WOOD_BOAT.get()), has(HexcraftItems.WITCH_WOOD_BOAT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.ECHO_WOOD_CHEST_BOAT.get())
				.requires(HexcraftItems.ECHO_WOOD_BOAT.get())
				.requires(Items.CHEST)
				.unlockedBy(getHasName(HexcraftItems.ECHO_WOOD_BOAT.get()), has(HexcraftItems.ECHO_WOOD_BOAT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.PEARL_STONE_BUTTON.get())
				.requires(HexcraftBlocks.PEARL_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.PEARL_STONE.get()), has(HexcraftBlocks.PEARL_STONE.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.CRIMSON_STONE_BUTTON.get())
				.requires(HexcraftBlocks.CRIMSON_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CRIMSON_STONE.get()), has(HexcraftBlocks.CRIMSON_STONE.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.UNDER_WORLD_STONE_BUTTON.get())
				.requires(HexcraftBlocks.UNDER_WORLD_STONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.UNDER_WORLD_STONE.get()), has(HexcraftBlocks.UNDER_WORLD_STONE.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.CHARSTONE_BUTTON.get())
				.requires(HexcraftBlocks.CHARSTONE.get())
				.unlockedBy(getHasName(HexcraftBlocks.CHARSTONE.get()), has(HexcraftBlocks.CHARSTONE.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.EBONY_BUTTON.get())
				.requires(HexcraftBlocks.EBONY_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.EBONY_PLANKS.get()), has(HexcraftBlocks.EBONY_PLANKS.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.BLOOD_OAK_BUTTON.get())
				.requires(HexcraftBlocks.BLOOD_OAK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.BLOOD_OAK_PLANKS.get()), has(HexcraftBlocks.BLOOD_OAK_PLANKS.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.HELL_BARK_BUTTON.get())
				.requires(HexcraftBlocks.HELL_BARK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.HELL_BARK_PLANKS.get()), has(HexcraftBlocks.HELL_BARK_PLANKS.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.WHITE_OAK_BUTTON.get())
				.requires(HexcraftBlocks.WHITE_OAK_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WHITE_OAK_PLANKS.get()), has(HexcraftBlocks.WHITE_OAK_PLANKS.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.ALDER_BUTTON.get())
				.requires(HexcraftBlocks.ALDER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ALDER_PLANKS.get()), has(HexcraftBlocks.ALDER_PLANKS.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.WITCH_HAZEL_BUTTON.get())
				.requires(HexcraftBlocks.WITCH_HAZEL_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_HAZEL_PLANKS.get()), has(HexcraftBlocks.WITCH_HAZEL_PLANKS.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.WILLOW_BUTTON.get())
				.requires(HexcraftBlocks.WILLOW_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WILLOW_PLANKS.get()), has(HexcraftBlocks.WILLOW_PLANKS.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.HAWTHORN_BUTTON.get())
				.requires(HexcraftBlocks.HAWTHORN_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.HAWTHORN_PLANKS.get()), has(HexcraftBlocks.HAWTHORN_PLANKS.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.CEDAR_BUTTON.get())
				.requires(HexcraftBlocks.CEDAR_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.CEDAR_PLANKS.get()), has(HexcraftBlocks.CEDAR_PLANKS.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.DISTORTED_BUTTON.get())
				.requires(HexcraftBlocks.DISTORTED_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.DISTORTED_PLANKS.get()), has(HexcraftBlocks.DISTORTED_PLANKS.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.ELDER_BUTTON.get())
				.requires(HexcraftBlocks.ELDER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ELDER_PLANKS.get()), has(HexcraftBlocks.ELDER_PLANKS.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.JUNIPER_BUTTON.get())
				.requires(HexcraftBlocks.JUNIPER_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.JUNIPER_PLANKS.get()), has(HexcraftBlocks.JUNIPER_PLANKS.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.ROWAN_BUTTON.get())
				.requires(HexcraftBlocks.ROWAN_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ROWAN_PLANKS.get()), has(HexcraftBlocks.ROWAN_PLANKS.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.TWISTED_BUTTON.get())
				.requires(HexcraftBlocks.TWISTED_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.TWISTED_PLANKS.get()), has(HexcraftBlocks.TWISTED_PLANKS.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.WITCH_WOOD_BUTTON.get())
				.requires(HexcraftBlocks.WITCH_WOOD_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.WITCH_WOOD_PLANKS.get()), has(HexcraftBlocks.WITCH_WOOD_PLANKS.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftBlocks.ECHO_WOOD_BUTTON.get())
				.requires(HexcraftBlocks.ECHO_WOOD_PLANKS.get())
				.unlockedBy(getHasName(HexcraftBlocks.ECHO_WOOD_PLANKS.get()), has(HexcraftBlocks.ECHO_WOOD_PLANKS.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.LIVING_KELP_ITEM.get(), 9)
				.requires(HexcraftBlocks.LIVING_KELP_BLOCK.get())
				.unlockedBy(getHasName(HexcraftBlocks.LIVING_KELP_BLOCK.get()), has(HexcraftBlocks.LIVING_KELP_BLOCK.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.STEEL_PAXEL.get())
				.requires(HexcraftItems.STEEL_AXE.get())
				.requires(HexcraftItems.STEEL_PICKAXE.get())
				.requires(HexcraftItems.STEEL_SHOVEL.get())
				.unlockedBy(getHasName(HexcraftItems.STEEL_INGOT.get()), has(HexcraftItems.STEEL_INGOT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.DARK_STEEL_PAXEL.get())
				.requires(HexcraftItems.DARK_STEEL_AXE.get())
				.requires(HexcraftItems.DARK_STEEL_PICKAXE.get())
				.requires(HexcraftItems.DARK_STEEL_SHOVEL.get())
				.unlockedBy(getHasName(HexcraftItems.DARK_STEEL.get()), has(HexcraftItems.DARK_STEEL.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.BLOODY_NYKIUM_PAXEL.get())
				.requires(HexcraftItems.BLOODY_NYKIUM_AXE.get())
				.requires(HexcraftItems.BLOODY_NYKIUM_PICKAXE.get())
				.requires(HexcraftItems.BLOODY_NYKIUM_SHOVEL.get())
				.unlockedBy(getHasName(HexcraftItems.BLOODY_NYKIUM.get()), has(HexcraftItems.BLOODY_NYKIUM.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.JORMIUM_PAXEL.get())
				.requires(HexcraftItems.JORMIUM_AXE.get())
				.requires(HexcraftItems.JORMIUM_PICKAXE.get())
				.requires(HexcraftItems.JORMIUM_SHOVEL.get())
				.unlockedBy(getHasName(HexcraftItems.JORMIUM_INGOT.get()), has(HexcraftItems.JORMIUM_INGOT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.CUROGEN_PAXEL.get())
				.requires(HexcraftItems.CUROGEN_AXE.get())
				.requires(HexcraftItems.CUROGEN_PICKAXE.get())
				.requires(HexcraftItems.CUROGEN_SHOVEL.get())
				.unlockedBy(getHasName(HexcraftItems.CUROGEN.get()), has(HexcraftItems.CUROGEN.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.SILVER_PAXEL.get())
				.requires(HexcraftItems.SILVER_AXE.get())
				.requires(HexcraftItems.SILVER_PICKAXE.get())
				.requires(HexcraftItems.SILVER_SHOVEL.get())
				.unlockedBy(getHasName(HexcraftItems.SILVER_INGOT.get()), has(HexcraftItems.SILVER_INGOT.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.VAMPIRIC_PAXEL.get())
				.requires(HexcraftItems.VAMPIRIC_AXE.get())
				.requires(HexcraftItems.VAMPIRIC_PICKAXE.get())
				.requires(HexcraftItems.VAMPIRIC_SHOVEL.get())
				.unlockedBy(getHasName(HexcraftItems.VAMPIRIC_GEM.get()), has(HexcraftItems.VAMPIRIC_GEM.get())).save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.LIVING_KELP_SALAD.get(), 1)
				.requires(HexcraftItems.LIVING_KELP_ITEM.get())
				.requires(Items.COD)
				.requires(Items.SEAGRASS)
				.unlockedBy(getHasName(Items.COAL), has(Items.COAL))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.CORRUPTED_NETHER_STAR.get())
				.requires(Items.NETHER_STAR)
				.requires(HexcraftItems.CORRUPTED_STEEL.get())
				.unlockedBy(getHasName(HexcraftItems.CORRUPTED_STEEL.get()), has(HexcraftItems.CORRUPTED_STEEL.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.DARK_CRYSTAL_SHARDS.get(), 3)
				.requires(HexcraftItems.ECLIPSIUM_INGOT.get())
				.requires(HexcraftItems.SOULSTONE_INGOT.get())
				.unlockedBy(getHasName(HexcraftItems.ECLIPSIUM_INGOT.get()), has(HexcraftItems.ECLIPSIUM_INGOT.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.CORRUPTED_RUNE.get())
				.requires(HexcraftItems.TOXIC_FUMES.get())
				.requires(HexcraftItems.ENCHANTED_RUNE.get())
				.unlockedBy(getHasName(HexcraftItems.ENCHANTED_RUNE.get()), has(HexcraftItems.ENCHANTED_RUNE.get()))
				.save(pWriter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, HexcraftItems.RITUAL_CHALK.get())
				.requires(HexcraftItems.ASH.get())
				.requires(HexcraftItems.SALT.get())
				.requires(Items.BONE_MEAL)
				.unlockedBy(getHasName(HexcraftItems.SALT.get()), has(HexcraftItems.SALT.get()))
				.save(pWriter);

		WitchesOvenRecipeBuilder
				.buildOvenRecipe(HexcraftItems.WHITE_OAK_ASH.get(), HexcraftItems.WOOD_ASH.get(), 0.35f, 200)
				.requires(HexcraftBlocks.WHITE_OAK_SAPLING.get()).requires(HexcraftItems.CLAY_POT.get())
				.unlockedBy("has_clay_pot", inventoryTrigger(ItemPredicate.Builder.item().of(HexcraftItems.CLAY_POT.get()).build()))
				.save(pWriter);


		WitchesOvenRecipeBuilder.buildOvenRecipe(HexcraftItems.BREATH_OF_THE_GODDESS.get(), HexcraftItems.WOOD_ASH.get(), 0.35f, 200)
				.requires(Items.OAK_SAPLING).requires(HexcraftItems.CLAY_POT.get())
				.unlockedBy("has_clay_pot", inventoryTrigger(ItemPredicate.Builder.item().of(HexcraftItems.CLAY_POT.get()).build()))
				.save(pWriter);

		WitchesOvenRecipeBuilder.buildOvenRecipe(HexcraftItems.SALT.get(), HexcraftItems.TOXIC_FUMES.get(), 0.65f, 200)
				.requires(Items.ROTTEN_FLESH)
				.requires(HexcraftItems.CLAY_POT.get())
				.unlockedBy("has_rotten_flesh", inventoryTrigger(ItemPredicate.Builder.item().of(Items.ROTTEN_FLESH).build()))
				.save(pWriter);

		WitchesOvenRecipeBuilder.buildOvenRecipe(HexcraftItems.ENCHANTED_RUNE.get(), HexcraftItems.ASH.get(), 0.4f, 300)
				.requires(HexcraftItems.BLANK_RUNE.get()) // Main item input
				.requires(HexcraftItems.CLAY_POT.get())   // Required clay pot
				.unlockedBy("has_blank_rune", inventoryTrigger(ItemPredicate.Builder.item().of(HexcraftItems.BLANK_RUNE.get()).build()))
				.save(pWriter);

		WitchesCauldronRecipeBuilder.buildCauldronRecipe(HexcraftItems.MUTANDIS.get(), 6).requires(HexcraftItems.EXHALE_OF_THE_HORNED_ONE.get())
				.requires(Items.EGG).requires(HexcraftItems.MANDRAKE_ROOT.get()).unlockedBy("has_cauldron",
						inventoryTrigger(ItemPredicate.Builder.item().of(Items.CAULDRON).build()))
				.save(pWriter);

		WitchesCauldronRecipeBuilder.buildCauldronRecipe(HexcraftBlocks.PURE_MAGIC_CRYSTAL.get()).requires(HexcraftItems.SALT.get())
				.requires(HexcraftItems.MAGIC_CRYSTAL.get()).requires(HexcraftItems.EARTH_RUNE.get()).unlockedBy("has_cauldron",
						inventoryTrigger(ItemPredicate.Builder.item().of(Items.CAULDRON).build()))
				.save(pWriter);

		WitchesCauldronRecipeBuilder.buildCauldronRecipe(HexcraftItems.ATTUNED_STONE_CHARGED.get()).requires(HexcraftItems.ATTUNED_STONE.get())
				.requires(HexcraftItems.BLOODY_NYKIUM.get()).requires(HexcraftItems.SALT.get()).unlockedBy("has_cauldron",
						inventoryTrigger(ItemPredicate.Builder.item().of(Items.CAULDRON).build()))
				.save(pWriter);

		WitchesCauldronRecipeBuilder.buildCauldronRecipe(HexcraftItems.ATTUNED_STONE.get()).requires(HexcraftItems.NECROTIC_STONE.get())
				.requires(HexcraftItems.ENCHANTED_RUNE.get()).requires(HexcraftItems.SALT.get()).unlockedBy("has_cauldron",
						inventoryTrigger(ItemPredicate.Builder.item().of(Items.CAULDRON).build()))
				.save(pWriter);

		WitchesCauldronRecipeBuilder.buildCauldronRecipe(HexcraftItems.WITCH_SIGIL.get())
				.requires(HexcraftItems.SALT.get())
				.requires(HexcraftItems.ENCHANTED_RUNE.get())
				.requires(HexcraftItems.NECROMANTIC_STONE.get())
				.unlockedBy("has_cauldron",
						inventoryTrigger(ItemPredicate.Builder.item().of(Items.CAULDRON).build()))
				.save(pWriter);

		WitchesCauldronRecipeBuilder.buildCauldronRecipe(HexcraftItems.DEEPSEER_INGOT.get())
				.requires(HexcraftItems.ABYSSIUM_INGOT.get())    // base ingredient
				.requires(HexcraftItems.SALT.get())               // example additive
				.requires(Items.GLOWSTONE_DUST)                    // example additive
				.unlockedBy("has_abyssium_ingot",
						inventoryTrigger(ItemPredicate.Builder.item().of(HexcraftItems.ABYSSIUM_INGOT.get()).build()))
				.save(pWriter);

		WitchesCauldronRecipeBuilder.buildCauldronRecipe(HexcraftItems.NECROMANTIC_STONE.get())
				.requires(HexcraftItems.ASH.get())
				.requires(Items.BONE)
				.requires(HexcraftItems.SALT.get())
				.unlockedBy("has_cauldron",
						inventoryTrigger(ItemPredicate.Builder.item().of(Items.CAULDRON).build()))
				.save(pWriter);


	}

	protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients,
			RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
		oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult,
				pExperience, pCookingTIme, pGroup, "_from_smelting");
	}


	protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients,
			RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
		oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult,
				pExperience, pCookingTime, pGroup, "_from_blasting");
	}

	protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer,
			RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients,
			RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup,
			String pRecipeName) {
		Iterator<?> var9 = pIngredients.iterator();

		while (var9.hasNext()) {
			ItemLike itemlike = (ItemLike) var9.next();
			SimpleCookingRecipeBuilder
					.generic(Ingredient.of(new ItemLike[] { itemlike }), pCategory, pResult, pExperience, pCookingTime,
							pCookingSerializer)
					.group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
					.save(pFinishedRecipeConsumer, HexcraftMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_"
							+ getItemName(itemlike));
		}

	}

}
