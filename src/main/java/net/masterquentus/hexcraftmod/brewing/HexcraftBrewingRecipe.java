package net.masterquentus.hexcraftmod.brewing;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.masterquentus.hexcraftmod.potions.HexcraftPotions;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class HexcraftBrewingRecipe {

    // Method to register brewing recipes (no need for DeferredRegister for brewing recipes)
    public static void registerBrewingRecipes() {
        // Brewing recipe for the Potion of Blistering Vitality
        BrewingRecipeRegistry.addRecipe(
                new BrewingRecipe(
                        Ingredient.of(new ItemStack(Items.POTION)), // Input: Awkward Potion
                        Ingredient.of(HexcraftItems.BLISTER_FRUIT.get()), // Ingredient: Blistering Cactus Fruit
                        new ItemStack((ItemLike) HexcraftPotions.BLISTERING_VITALITY_EFFECT.get())
                )
        );
    }
}