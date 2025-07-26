package net.masterquentus.hexcraftmod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;



public class HexcraftToolTiers {
    public static final ForgeTier ARTHANA = new ForgeTier(
            0, 15 * 5, 3.0F, 0.0F, 30,
            BlockTags.NEEDS_STONE_TOOL,
            () -> Ingredient.of(Items.GOLD_INGOT)
    ); // LOW, ritualistic, low durability & damage

    public static final ForgeTier STEEL = new ForgeTier(
            2, 28 * 59, 7.0F, 2.5F, 12,
            BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(HexcraftItems.STEEL_INGOT.get())
    ); // MID tier

    public static final ForgeTier DEEPSEER = new ForgeTier(
            2, 30 * 59, 8.0F, 2.5F, 16,
            BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(HexcraftItems.DEEPSEER_INGOT.get())
    ); // MID tier, slightly faster

    public static final ForgeTier JORMIUM = new ForgeTier(
            3, 33 * 59, 9.0F, 2.7F, 16,
            BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(HexcraftItems.JORMIUM_INGOT.get())
    ); // HIGH tier, fast mining

    public static final ForgeTier CUROGEN = new ForgeTier(
            3, 32 * 59, 10.0F, 2.7F, 18,
            BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(HexcraftItems.CUROGEN.get())
    ); // HIGH tier, strong and fast

    public static final ForgeTier SILVER = new ForgeTier(
            2, 30 * 59, 7.5F, 2.5F, 22,
            BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(HexcraftItems.SILVER_INGOT.get())
    ); // HIGH tier, fragile but enchantable

    public static final ForgeTier VAMPIRIC = new ForgeTier(
            3, 34 * 59, 8.0F, 3.0F, 24,
            BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(HexcraftItems.VAMPIRIC_GEM.get())
    ); // HIGH tier, magic-focused

    public static final ForgeTier DARKSTEEL = new ForgeTier(
            4, 40 * 59, 10.0F, 3.5F, 18,
            BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(HexcraftItems.DARK_STEEL.get())
    ); // RARE tier

    public static final ForgeTier BLOODYNYKIUM = new ForgeTier(
            4, 37 * 59, 11.0F, 3.5F, 18,
            BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(HexcraftItems.BLOODY_NYKIUM.get()));


}