package net.masterquentus.hexcraftmod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;



public class HexcraftToolTiers {
    public static final ForgeTier ARTHANA = new ForgeTier(0, 32, 12.0F,
            0.0F, 22, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(HexcraftItems.SILVER_INGOT.get()));

    public static final ForgeTier STEEL = new ForgeTier(3, 1900, 7.0F,
            2.5F, 12, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(HexcraftItems.STEEL_INGOT.get()));

    public static final ForgeTier DARKSTEEL = new ForgeTier(3, 1561, 10.0F,
            3.0F, 18, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(HexcraftItems.DARK_STEEL.get()));

    public static final ForgeTier BLOODYNYKIUM = new ForgeTier(4, 2031, 11.0F,
            4.0F, 18, BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(HexcraftItems.BLOODY_NYKIUM.get()));

    public static final ForgeTier JORMIUM = new ForgeTier(1, 250, 12.0F,
            0.0F, 15, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(HexcraftItems.JORMIUM_INGOT.get()));

    public static final ForgeTier CUROGEN = new ForgeTier(4, 2031, 11.0F,
            3.0F, 18, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(HexcraftItems.CUROGEN.get()));

    public static final ForgeTier SILVER = new ForgeTier(2, 200, 7.5F,
            2.0F, 20, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(HexcraftItems.SILVER_INGOT.get()));

    public static final ForgeTier VAMPIRIC = new ForgeTier(2, 225, 7.0F,
            2.5F, 22, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(HexcraftItems.VAMPIRIC_GEM.get()));


}