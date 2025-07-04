package net.masterquentus.hexcraftmod.recipe;

import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;

public class WitchesOvenRecipe implements Recipe<SimpleContainer> {
	protected final RecipeType<?> type;
	private final ResourceLocation id;
	private final NonNullList<Ingredient> ingredients;
	private final ItemStack result;
	private final ItemStack excessResult;
	protected final float experience;
	protected final int bakeTime;

	public WitchesOvenRecipe(ResourceLocation id, NonNullList<Ingredient> ingredients, ItemStack result,
			ItemStack excessResult, float experience, int bakeTime) {
		this.type = Type.INSTANCE;
		this.id = id;
		this.ingredients = ingredients;
		this.result = result;
		this.excessResult = excessResult;
		this.experience = experience;
		this.bakeTime = bakeTime;
	}

	@Override
	public boolean matches(SimpleContainer pContainer, Level pLevel) {
		if (pLevel.isClientSide())
			return false;

		return this.ingredients.get(0).test(pContainer.getItem(0))
				&& this.ingredients.get(1).test(pContainer.getItem(2));
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return ingredients;
	}
	
	@Override
	public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
		return result.copy();
	}

	@Override
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		return true;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
		return result.copy();
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(HexcraftBlocks.WITCHES_OVEN.get());
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return Serializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}

	public ItemStack getExcessResultItem(RegistryAccess pRegistryAccess) {
		return this.excessResult.copy();
	}

	public float getExperience() {
		return this.experience;
	}

	public int getBakeTime() {
		return this.bakeTime;
	}

	public static class Type implements RecipeType<WitchesOvenRecipe> {
		public static final Type INSTANCE = new Type();
		public static final String ID = "witches_oven";
	}

	public static class Serializer implements RecipeSerializer<WitchesOvenRecipe> {
		public static final Serializer INSTANCE = new Serializer();
		public static final ResourceLocation ID = new ResourceLocation(HexcraftMod.MOD_ID, WitchesOvenRecipe.Type.ID);

		@Override
		public WitchesOvenRecipe fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
			int bakeTime = GsonHelper.getAsInt(pJson, "bakeTime");
			float experience = GsonHelper.getAsFloat(pJson, "experience");

			ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "result"));
			ItemStack excessResult = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "excessResult"));

			JsonArray ingredients = GsonHelper.getAsJsonArray(pJson, "ingredients");
			NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

			for (int i = 0; i < inputs.size(); i++)
				inputs.set(i, Ingredient.fromJson(ingredients.get(i)));

			return new WitchesOvenRecipe(pRecipeId, inputs, result, excessResult, experience, bakeTime);
		}

		@Override
		public @Nullable WitchesOvenRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
			int bakeTime = pBuffer.readInt();
			float experience = pBuffer.readFloat();

			NonNullList<Ingredient> ingredients = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);
			for (int i = 0; i < ingredients.size(); i++) {
				ingredients.set(i, Ingredient.fromNetwork(pBuffer));
			}

			ItemStack result = pBuffer.readItem();
			ItemStack excessResult = pBuffer.readItem();

			return new WitchesOvenRecipe(pRecipeId, ingredients, result, excessResult, experience, bakeTime);
		}

		@Override
		public void toNetwork(FriendlyByteBuf pBuffer, WitchesOvenRecipe pRecipe) {
			pBuffer.writeInt(pRecipe.getBakeTime());
			pBuffer.writeFloat(pRecipe.getExperience());

			pBuffer.writeInt(pRecipe.ingredients.size());
			for (Ingredient ingredient : pRecipe.getIngredients()) {
				ingredient.toNetwork(pBuffer);
			}

			pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
			pBuffer.writeItemStack(pRecipe.getExcessResultItem(null), false);
		}
	}
}