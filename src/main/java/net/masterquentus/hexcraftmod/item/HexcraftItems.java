package net.masterquentus.hexcraftmod.item;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.block.custom.HexcraftChestBlock;
import net.masterquentus.hexcraftmod.block.entity.boats.HexcraftBoatEntity;
import net.masterquentus.hexcraftmod.effects.HexcraftEffects;
import net.masterquentus.hexcraftmod.entity.HexcraftEntities;
import net.masterquentus.hexcraftmod.fluid.HexcraftFluids;
import net.masterquentus.hexcraftmod.item.brews.SimpleEffectBrewItem;
import net.masterquentus.hexcraftmod.item.brews.SimpleEffectSplashBrewItem;
import net.masterquentus.hexcraftmod.item.brooms.BroomItem;
import net.masterquentus.hexcraftmod.item.brooms.traits.*;
import net.masterquentus.hexcraftmod.item.custom.*;
import net.masterquentus.hexcraftmod.util.HexcraftTags;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.masterquentus.hexcraftmod.potions.HexcraftPotions.POTIONS;

public class HexcraftItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HexcraftMod.MOD_ID);

    public static final RegistryObject<Item> HEXCRAFT_GRIMOIRE = ITEMS.register("hexcraft_grimoire",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> LUMENBLOOM_CLUSTER_ITEM = ITEMS.register("lumenbloom_cluster",
            () -> new BlockItem(HexcraftBlocks.LUMENBLOOM_CLUSTER.get(), new Item.Properties()));

    public static final RegistryObject<Item> SEETHING_COALBED_ITEM = ITEMS.register("seething_coalbed",
            () -> new BlockItem(HexcraftBlocks.SEETHING_COALBED.get(), new Item.Properties()));

    // Bramble
    public static final RegistryObject<Item> BRAMBLE_DIRT_ITEM = ITEMS.register("bramble_dirt",
            () -> new BlockItem(HexcraftBlocks.BRAMBLE_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> BRAMBLE_GRASS_BLOCK_ITEM = ITEMS.register("bramble_grass_block",
            () -> new BlockItem(HexcraftBlocks.BRAMBLE_GRASS_BLOCK.get(), new Item.Properties()));

    // Veilshade
    public static final RegistryObject<Item> VEILSHADE_DIRT_ITEM = ITEMS.register("vileshade_dirt",
            () -> new BlockItem(HexcraftBlocks.VEILSHADE_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> VEILSHADE_SOIL_ITEM = ITEMS.register("veilshade_soil",
            () -> new BlockItem(HexcraftBlocks.VEILSHADE_SOIL.get(), new Item.Properties()));

    // Tainted
    public static final RegistryObject<Item> TAINTED_DIRT_ITEM = ITEMS.register("tainted_dirt",
            () -> new BlockItem(HexcraftBlocks.TAINTED_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> TAINTED_SOIL_ITEM = ITEMS.register("tainted_soil",
            () -> new BlockItem(HexcraftBlocks.TAINTED_SOIL.get(), new Item.Properties()));

    // Stoneloam
    public static final RegistryObject<Item> STONELOAM_DIRT_ITEM = ITEMS.register("stoneloam_dirt",
            () -> new BlockItem(HexcraftBlocks.STONELOAM_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> STONELOAM_SOIL_ITEM = ITEMS.register("stoneloam_soil",
            () -> new BlockItem(HexcraftBlocks.STONELOAM_SOIL.get(), new Item.Properties()));

    // Cinderweed
    public static final RegistryObject<Item> CINDERWEED_DIRT_ITEM = ITEMS.register("cindeweed_dirt",
            () -> new BlockItem(HexcraftBlocks.CINDERWEED_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> CINDERWEED_SOIL_ITEM = ITEMS.register("cinderweed_soil",
            () -> new BlockItem(HexcraftBlocks.CINDERWEED_SOIL.get(), new Item.Properties()));

    // Seething + Festering
    public static final RegistryObject<Item> SEETHING_RED_DIRT_ITEM = ITEMS.register("seething_red_dirt",
            () -> new BlockItem(HexcraftBlocks.SEETHING_RED_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> FESTERING_GRASS_BLOCK_ITEM = ITEMS.register("festering_grass_block",
            () -> new BlockItem(HexcraftBlocks.FESTERING_GRASS_BLOCK.get(), new Item.Properties()));

    // Flareworn
    public static final RegistryObject<Item> FLAREWORN_DIRT_ITEM = ITEMS.register("flareworn_dirt",
            () -> new BlockItem(HexcraftBlocks.FLAREWORN_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> FLAREWORN_GRASS_BLOCK_ITEM = ITEMS.register("flareworn_grass_block",
            () -> new BlockItem(HexcraftBlocks.FLAREWORN_GRASS_BLOCK.get(), new Item.Properties()));

    // Bileweed
    public static final RegistryObject<Item> BILEWEED_DIRT_ITEM = ITEMS.register("bileweed_dirt",
            () -> new BlockItem(HexcraftBlocks.BILEWEED_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> BILEWEED_GRASS_BLOCK_ITEM = ITEMS.register("bileweed_grass_block",
            () -> new BlockItem(HexcraftBlocks.BILEWEED_GRASS_BLOCK.get(), new Item.Properties()));

    // Whisperbloom
    public static final RegistryObject<Item> WHISPERBLOOM_DIRT_ITEM = ITEMS.register("whisperbloom_dirt",
            () -> new BlockItem(HexcraftBlocks.WHISPERBLOOM_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> WHISPERBLOOM_SOIL_ITEM = ITEMS.register("whisperbloom_soil",
            () -> new BlockItem(HexcraftBlocks.WHISPERBLOOM_SOIL.get(), new Item.Properties()));

    // Twilightloam
    public static final RegistryObject<Item> TWILIGHTLOAM_DIRT_ITEM = ITEMS.register("twilightloam_dirt",
            () -> new BlockItem(HexcraftBlocks.TWILIGHTLOAM_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> TWILIGHTLOAM_GRASS_BLOCK_ITEM = ITEMS.register("twilightloam_grass_block",
            () -> new BlockItem(HexcraftBlocks.TWILIGHTLOAM_GRASS_BLOCK.get(), new Item.Properties()));

    // Rotroot
    public static final RegistryObject<Item> ROTROOT_DIRT_ITEM = ITEMS.register("rodroot_dirt",
            () -> new BlockItem(HexcraftBlocks.ROTROOT_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> ROTROOT_GRASS_BLOCK_ITEM = ITEMS.register("rotroot_grass_block",
            () -> new BlockItem(HexcraftBlocks.ROTROOT_GRASS_BLOCK.get(), new Item.Properties()));

    // Huskweed
    public static final RegistryObject<Item> HUSKWEED_DIRT_ITEM = ITEMS.register("huskweed_dirt",
            () -> new BlockItem(HexcraftBlocks.HUSKWEED_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> HUSKWEED_GRASS_BLOCK_ITEM = ITEMS.register("huskweed_grass_block",
            () -> new BlockItem(HexcraftBlocks.HUSKWEED_GRASS_BLOCK.get(), new Item.Properties()));

    // Spiritweed
    public static final RegistryObject<Item> SPIRITWEED_DIRT_ITEM = ITEMS.register("soulloam_dirt",
            () -> new BlockItem(HexcraftBlocks.SPIRITWEED_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> SPIRITWEED_GRASS_BLOCK_ITEM = ITEMS.register("spiritweed_grass_block",
            () -> new BlockItem(HexcraftBlocks.SPIRITWEED_GRASS_BLOCK.get(), new Item.Properties()));

    // Dusk
    public static final RegistryObject<Item> DUSKDIRT_ITEM = ITEMS.register("duskdirt",
            () -> new BlockItem(HexcraftBlocks.DUSKDIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> DUSK_GRASS_BLOCK_ITEM = ITEMS.register("dusk_grass_block",
            () -> new BlockItem(HexcraftBlocks.DUSK_GRASS_BLOCK.get(), new Item.Properties()));

    // Nightvein
    public static final RegistryObject<Item> NIGHTVEIN_DIRT_ITEM = ITEMS.register("nightvein_dirt",
            () -> new BlockItem(HexcraftBlocks.NIGHTVEIN_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> NIGHTVEIN_GRASS_BLOCK_ITEM = ITEMS.register("nightvein_grass_block",
            () -> new BlockItem(HexcraftBlocks.NIGHTVEIN_GRASS_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> SCORCHFIRE_GRASS_BLOCK_ITEM  = ITEMS.register("scorchfire_grass_block",
            () -> new BlockItem(HexcraftBlocks. SCORCHFIRE_GRASS_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> SCORCHFIRE_DIRT_ITEM = ITEMS.register("scorchfire_dirt",
            () -> new BlockItem(HexcraftBlocks.SCORCHFIRE_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> UMBRAL_BLOOM_GRASS_BLOCK_ITEM  = ITEMS.register("umbral_bloom_grass_block",
            () -> new BlockItem(HexcraftBlocks. UMBRAL_BLOOM_GRASS_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> UMBRAL_BLOOM_DIRT_ITEM = ITEMS.register("umbral_bloom_dirt",
            () -> new BlockItem(HexcraftBlocks. UMBRAL_BLOOM_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> VILE_GRASS_BLOCK_ITEM = ITEMS.register("vile_grass_block",
            () -> new BlockItem(HexcraftBlocks.VILE_GRASS_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> VILE_DIRT_ITEM = ITEMS.register("vile_dirt",
            () -> new BlockItem(HexcraftBlocks.VILE_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> GLOOMROOT_SOIL_ITEM = ITEMS.register("gloomroot_soil",
            () -> new BlockItem(HexcraftBlocks.GLOOMROOT_SOIL.get(), new Item.Properties()));

    public static final RegistryObject<Item> GLIMMER_CAP_ITEM = ITEMS.register("glimmer_cap",
            () -> new BlockItem(HexcraftBlocks.GLIMMER_CAP.get(), new Item.Properties()));

    public static final RegistryObject<Item> SHARDSTONE_ITEM = ITEMS.register("shardstone",
            () -> new BlockItem(HexcraftBlocks.SHARDSTONE.get(), new Item.Properties()));

    public static final RegistryObject<Item> LUMICLAST_ITEM = ITEMS.register("lumiclast",
            () -> new BlockItem(HexcraftBlocks.LUMICLAST.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> CURSED_SOIL = ITEMS.register("cursed_soil",
            () -> new BlockItem(HexcraftBlocks.CURSED_SOIL.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> CURED_SOIL = ITEMS.register("cured_soil",
            () -> new BlockItem(HexcraftBlocks.CURED_SOIL.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> FERTILIZED_DIRT = ITEMS.register("fertilized_dirt",
            () -> new BlockItem(HexcraftBlocks.FERTILIZED_DIRT.get(), new Item.Properties()));

    public static final RegistryObject<Item> FAIRY_LANTERN_ITEM = ITEMS.register("fairy_lantern",
            () -> new BlockItem(HexcraftBlocks.FAIRY_LANTERN.get(), new Item.Properties()));

    public static final RegistryObject<Item> PIXIE_LANTERN_ITEM = ITEMS.register("pixie_lantern",
            () -> new BlockItem(HexcraftBlocks.PIXIE_LANTERN.get(), new Item.Properties()));

    public static final RegistryObject<Item> FAIRY_WARD_ITEM = ITEMS.register("fairy_ward",
            () -> new BlockItem(HexcraftBlocks.FAIRY_WARD.get(), new Item.Properties()));

    public static final RegistryObject<Item> PIXIE_WARD_ITEM = ITEMS.register("pixie_ward",
            () -> new BlockItem(HexcraftBlocks.PIXIE_WARD.get(), new Item.Properties()));

    public static final RegistryObject<Item> HELLFIRE_CAMP_ITEM = ITEMS.register("hellfire_camp",
            () -> new BlockItem(HexcraftBlocks.HELLFIRE_CAMPFIRE.get(), new Item.Properties()));

    public static final RegistryObject<Item> FUME_FUNNEL = ITEMS.register("fume_funnel",
            () -> new BlockItem(HexcraftBlocks.FUME_FUNNEL.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> ALTAR_BASE_ITEM = ITEMS.register("altar_base", () -> new BlockItem(HexcraftBlocks.ALTAR_BASE.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> ALTAR_TOP_ITEM = ITEMS.register("altar_top", () -> new BlockItem(HexcraftBlocks.ALTAR_TOP.get(), new Item.Properties()));

    public static final RegistryObject<Item> INFUSED_FABRIC = ITEMS.register("infused_fabric",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BOUND_LEATHER = ITEMS.register("bound_leather",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TANNED_LEATHER = ITEMS.register("tanned_leather",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DAYLIGHT_RING = ITEMS.register("daylight_ring",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLESSED_DAYLIGHT_RING = ITEMS.register("blessed_daylight_ring",
            () -> new BlessedDaylightRing(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> WITHER_BONE = ITEMS.register("wither_bone",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DREAMWEAVER_CHARM = ITEMS.register("dreamweaver_charm",
            () -> new DreamweaverCharmItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> BONE_NEEDLE = ITEMS.register("bone_needle",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TAGLOCK_KIT = ITEMS.register("taglock_kit",
            () -> new TagLockKitItem(new Item.Properties()));

    public static final RegistryObject<Item> TAGLOCK_KIT_FULL = ITEMS.register("taglock_kit_full",
            () -> new TagLockKitFilled(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> WITCHES_SATCHEL = ITEMS.register("witches_satchel",
            () -> new WitchesSatchelItem(new Item.Properties()));

    public static final RegistryObject<Item> FLINT_AND_HELLFIRE = ITEMS.register("flint_and_hellfire",
            () -> new FlintAndHellfireItem(new Item.Properties().durability(64)));

    public static final RegistryObject<Item> UNFIRED_CLAY_POT = ITEMS.register("unfired_clay_pot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INFERNAL_EMBER = ITEMS.register("infernal_ember",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ASH = ITEMS.register("ash",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TOXIC_FUMES = ITEMS.register("toxic_fumes",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RITUAL_CHALK = ITEMS.register("ritual_chalk",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLAY_POT = ITEMS.register("clay_pot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLOOD_BOTTLE = ITEMS.register("blood_bottle",
            () -> new BloodBottleItem(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> VENOM_BOTTLE = ITEMS.register("venom_bottle",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LILITH_CONTRACT = ITEMS.register("lilith_contract",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LILITH_SOUL = ITEMS.register("lilith_soul",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEATH_CONTRACT = ITEMS.register("death_contract",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEATH_SOUL = ITEMS.register("death_soul",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENVY_SOUL = ITEMS.register("envy_soul",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GLUTTONY_SOUL = ITEMS.register("gluttony_soul",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GREED_SOUL = ITEMS.register("greed_soul",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LUST_SOUL = ITEMS.register("lust_soul",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MEDUSA_SOUL = ITEMS.register("medusa_soul",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MIRROR_DEMON_SOUL = ITEMS.register("mirror_demon_soul",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PRIDE_SOUL = ITEMS.register("pride_soul",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SLOTH_SOUL = ITEMS.register("sloth_soul",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WRATH_SOUL = ITEMS.register("wrath_soul",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MAGIC_CRYSTAL = ITEMS.register("magic_crystal",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WITCH_SIGIL = ITEMS.register("witch_sigil",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NECROTIC_STONE = ITEMS.register("necrotic_stone",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENCHANTED_RUNE = ITEMS.register("enchanted_rune",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CORRUPTED_RUNE = ITEMS.register("corrupted_rune",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLANK_RUNE = ITEMS.register("blank_rune",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AIR_RUNE = ITEMS.register("air_rune",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EARTH_RUNE = ITEMS.register("earth_rune",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FIRE_RUNE = ITEMS.register("fire_rune",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WATER_RUNE = ITEMS.register("water_rune",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RED_RUNE = ITEMS.register("red_rune",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLACK_RUNE = ITEMS.register("black_rune",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLUE_RUNE = ITEMS.register("blue_rune",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GREEN_RUNE = ITEMS.register("green_rune",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ORANGE_RUNE = ITEMS.register("orange_rune",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PURPLE_RUNE = ITEMS.register("purple_rune",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WHITE_RUNE = ITEMS.register("white_rune",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> YELLOW_RUNE = ITEMS.register("yellow_rune",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROPE = ITEMS.register("rope",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLOOD_DROP = ITEMS.register("blood_drop",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MOONSTONE = ITEMS.register("moonstone",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SILVER_NUGGET = ITEMS.register("silver_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> VAMPIRIC_GEM = ITEMS.register("vampiric_gem",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CORRUPTED_NETHER_STAR = ITEMS.register("corrupted_nether_star",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CORRUPTED_STEEL = ITEMS.register("corrupted_steel",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CORRUPTED_STEEL_NUGGET = ITEMS.register("corrupted_steel_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DARK_CRYSTAL_SHARDS = ITEMS.register("dark_crystal_shards",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DARK_STEEL = ITEMS.register("dark_steel",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DARK_STEEL_NUGGET = ITEMS.register("dark_steel_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_BLOODY_NYKIUM = ITEMS.register("raw_bloody_nykium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLOODY_NYKIUM = ITEMS.register("bloody_nykium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLOODY_NYKIUM_NUGGET = ITEMS.register("bloody_nykium_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NYKIUM_INGOT = ITEMS.register("nykium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NYKIUM_NUGGET = ITEMS.register("nykium_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_CUROGEN = ITEMS.register("raw_curogen",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUROGEN = ITEMS.register("curogen",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUROGEN_NUGGET = ITEMS.register("curogen_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_JORMIUM = ITEMS.register("raw_jormium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENHANCED_JORMIUM_INGOT = ITEMS.register("enhanced_jormium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JORMIUM_INGOT = ITEMS.register("jormium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JORMIUM_NUGGET = ITEMS.register("jormium_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_POWDER = ITEMS.register("steel_powder",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_SOULSTONE = ITEMS.register("raw_soulstone",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOULSTONE_INGOT = ITEMS.register("soulstone_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOULSTONE_NUGGET = ITEMS.register("soulstone_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_ABYSSIUM = ITEMS.register("raw_abyssium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ABYSSIUM_INGOT = ITEMS.register("abyssium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ABYSSIUM_NUGGET = ITEMS.register("abyssium_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_ECLIPSIUM = ITEMS.register("raw_eclipsium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ECLIPSIUM_INGOT = ITEMS.register("eclipsium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ECLIPSIUM_NUGGET = ITEMS.register("eclipsium_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEEPSEER_INGOT = ITEMS.register("deepseer_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WHITE_OAK_ASH = ITEMS.register("white_oak_ash",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SALT = ITEMS.register("salt",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> NECROMANTIC_STONE = ITEMS.register("necromantic_stone",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ATTUNED_STONE = ITEMS.register("attuned_stone",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ATTUNED_STONE_CHARGED = ITEMS.register("attuned_stone_charged",
            () -> new SimpleFoiledItem(new Item.Properties()));

    public static final RegistryObject<Item> BLOODED_WAYSTONE = ITEMS.register("blooded_waystone",
            () -> new BloodedWaystoneItem(new Item.Properties()));

    public static final RegistryObject<Item> BOUND_WAYSTONE = ITEMS.register("bound_waystone",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WOOD_ASH = ITEMS.register("wood_ash",
            () -> new HexcraftFuelItem(new Item.Properties(), 200));

    public static final RegistryObject<Item> ABYSSAL_COAL = ITEMS.register("abyssal_coal",
            () -> new HexcraftFuelItem(new Item.Properties(), 800));

    public static final RegistryObject<Item> BREATH_OF_THE_GODDESS = ITEMS.register("breath_of_the_goddess",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EXHALE_OF_THE_HORNED_ONE = ITEMS.register("exhale_of_the_horned_one",
            () -> new Item(new Item.Properties()));

    //public static final RegistryObject<Item> BREW_OF_LOVE = ITEMS.register("brew_of_love",
    //() -> new BrewOfLoveItem(new Item.Properties()));

    public static final RegistryObject<Item> BREW_OF_SPROUTING = ITEMS.register("brew_of_sprouting",
            () -> new BrewOfSproutingItem(new Item.Properties()));

    //public static final Supplier<SimpleEffectBrewItem> BREW_OF_THE_DEPTHS = registerBrew("brew_of_the_depths",
    //MobEffects.WATER_BREATHING, 6000, 0);

    public static final RegistryObject<Item> BREW_OF_THE_GROTESQUE = ITEMS.register("brew_of_the_grotesque",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLISTER_CACTUS_FLOWER = ITEMS.register("blister_cactus_flower_item",
            () -> new BlockItem(HexcraftBlocks.BLISTER_CACTUS_FLOWER.get(), new Item.Properties()));

    public static final RegistryObject<Item> NUMBING_AGENT = ITEMS.register("numbing_agent",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLISTERING_VITALITY_POTION = ITEMS.register("blistering_vitality_potion",
            () -> new PotionItem(new Item.Properties()));

    public static final RegistryObject<Item> IRENIAL_BREW = ITEMS.register("irenial_brew",
            () -> new SimpleEffectBrewItem(
                    HexcraftEffects.BLOODHORN.get(),
                    20 * 15, // 15 seconds
                    0,
                    new Item.Properties().stacksTo(1)
            ));

    public static final RegistryObject<Item> SENIA_BREW = ITEMS.register("senia_brew",
            () -> new SimpleEffectBrewItem(
                    HexcraftEffects.PARALYZED.get(),
                    20 * 10, // 10 seconds
                    0,
                    new Item.Properties().stacksTo(1)
            ));

    public static final RegistryObject<Item> MIRA_BREW = ITEMS.register("mira_brew",
            () -> new SimpleEffectBrewItem(
                    HexcraftEffects.MAGICAL_BOOST.get(),
                    20 * 30, // 30 seconds
                    0,
                    new Item.Properties().stacksTo(1)
            ));

    public static final RegistryObject<Item> CONDENSED_FEAR = ITEMS.register("condensed_fear",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_VAPOUR = ITEMS.register("diamond_vapour",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DROP_OF_LUCK = ITEMS.register("drop_of_luck",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> REEK_OF_MISFORTUNE = ITEMS.register("reek_of_misfortune",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WOOL_OF_BAT = ITEMS.register("wool_of_bat",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ARTHANA = ITEMS.register("arthana",
            () -> new SwordItem(HexcraftToolTiers.ARTHANA , 3, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> VEIL_ASCENDANT_KEY = ITEMS.register("veil_ascendant_key",
            () -> new VeilAscendantKeyItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> ASCENDANT_DIAL = ITEMS.register("ascendant_dial",
            () -> new AscendantDialItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> ANOINTING_PASTE = ITEMS.register("anointing_paste",
            () -> new AnointingPasteItem(new Item.Properties()));

    public static final RegistryObject<Item> MUTANDIS = ITEMS.register("mutandis",
            () -> new MutandisItem(HexcraftTags.Blocks.MUTANDIS_PLANTS));

    public static final RegistryObject<Item> MUTANDIS_EXTREMIS = ITEMS.register("mutandis_extremis",
            () -> new MutandisItem(HexcraftTags.Blocks.MUTANDIS_EXTREMIS_PLANTS));

    public static final RegistryObject<Item> POPPET = ITEMS.register("poppet",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> VOODOO_POPPET = ITEMS.register("voodoo_poppet",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> VOODOO_PROTECTION_POPPET = ITEMS.register("voodoo_protection_poppet",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> FALL_PROTECTION_POPPET = ITEMS.register("fall_protection_poppet",
            () -> new Item(new Item.Properties().stacksTo(1).durability(256)));

    public static final RegistryObject<Item> EXPLOSION_PROTECTION_POPPET = ITEMS.register("explosion_protection_poppet",
            () -> new Item(new Item.Properties().stacksTo(1).durability(256)));

    public static final RegistryObject<Item> PROJECTILE_PROTECTION_POPPET = ITEMS.register("projectile_protection_poppet",
            () -> new Item(new Item.Properties().stacksTo(1).durability(256)));

    public static final RegistryObject<Item> HUNGER_PROTECTION_POPPET = ITEMS.register("hunger_protection_poppet",
            () -> new Item(new Item.Properties().stacksTo(1).durability(256)));

    public static final RegistryObject<Item> POTION_PROTECTION_POPPET = ITEMS.register("potion_protection_poppet",
            () -> new Item(new Item.Properties().stacksTo(1).durability(256)));

    public static final RegistryObject<Item> VOID_PROTECTION_POPPET = ITEMS.register("void_protection_poppet",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> CURSE_PROTECTION_POPPET = ITEMS.register("curse_protection_poppet",
            () -> new Item(new Item.Properties().stacksTo(1).durability(256)));

    public static final RegistryObject<Item> VAMPIRIC_POPPET = ITEMS.register("vampiric_poppet",
            () -> new Item(new Item.Properties().stacksTo(1).durability(10)));

    public static final RegistryObject<Item> DEATH_PROTECTION_POPPET = ITEMS.register("death_protection_poppet",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> FIRE_PROTECTION_POPPET = ITEMS.register("fire_protection_poppet",
            () -> new Item(new Item.Properties().stacksTo(1).durability(256)));

    public static final RegistryObject<Item> WATER_PROTECTION_POPPET = ITEMS.register("water_protection_poppet",
            () -> new Item(new Item.Properties().stacksTo(1).durability(256)));

    public static final RegistryObject<Item> WITHER_PROTECTION_POPPET = ITEMS.register("wither_protection_poppet",
            () -> new Item(new Item.Properties().stacksTo(1).durability(256)));

    public static final RegistryObject<Item> ARMOR_PROTECTION_POPPET = ITEMS.register("armor_protection_poppet",
            () -> new Item(new Item.Properties().stacksTo(1).durability(256)));

    public static final RegistryObject<Item> TOOL_PROTECTION_POPPET = ITEMS.register("tool_protection_poppet",
            () -> new Item(new Item.Properties().stacksTo(1).durability(256)));


    //Tools/Weapons
    public static final RegistryObject<Item> VAMPIRIC_STAFF = ITEMS.register("vampiric_staff",
            () -> new VampiricStaffItem(new Item.Properties().durability(324)));

    public static final RegistryObject<Item> VAMPIRIC_STAFF_PROJECTILE = ITEMS.register("vampiric_staff_projectile",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(HexcraftToolTiers.STEEL, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(HexcraftToolTiers.STEEL, 1, 2, new Item.Properties()));

    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(HexcraftToolTiers.STEEL, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(HexcraftToolTiers.STEEL, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(HexcraftToolTiers.STEEL, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> STEEL_PAXEL = ITEMS.register("steel_paxel",
            () -> new PaxelItem(HexcraftToolTiers.STEEL, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> DARK_STEEL_SWORD = ITEMS.register("dark_steel_sword",
            () -> new SwordItem(HexcraftToolTiers.DARKSTEEL, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> DARK_STEEL_PICKAXE = ITEMS.register("dark_steel_pickaxe",
            () -> new PickaxeItem(HexcraftToolTiers.DARKSTEEL, 1, 2, new Item.Properties()));

    public static final RegistryObject<Item> DARK_STEEL_SHOVEL = ITEMS.register("dark_steel_shovel",
            () -> new ShovelItem(HexcraftToolTiers.DARKSTEEL, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> DARK_STEEL_AXE = ITEMS.register("dark_steel_axe",
            () -> new AxeItem(HexcraftToolTiers.DARKSTEEL, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> DARK_STEEL_HOE = ITEMS.register("dark_steel_hoe",
            () -> new HoeItem(HexcraftToolTiers.DARKSTEEL, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> DARK_STEEL_PAXEL = ITEMS.register("dark_steel_paxel",
            () -> new PaxelItem(HexcraftToolTiers.DARKSTEEL, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> BLOODY_NYKIUM_SWORD = ITEMS.register("bloody_nykium_sword",
            () -> new SwordItem(HexcraftToolTiers.BLOODYNYKIUM, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> BLOODY_NYKIUM_PICKAXE = ITEMS.register("bloody_nykium_pickaxe",
            () -> new PickaxeItem(HexcraftToolTiers.BLOODYNYKIUM, 1, 2, new Item.Properties()));

    public static final RegistryObject<Item> BLOODY_NYKIUM_SHOVEL = ITEMS.register("bloody_nykium_shovel",
            () -> new ShovelItem(HexcraftToolTiers.BLOODYNYKIUM, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> BLOODY_NYKIUM_AXE = ITEMS.register("bloody_nykium_axe",
            () -> new AxeItem(HexcraftToolTiers.BLOODYNYKIUM, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> BLOODY_NYKIUM_HOE = ITEMS.register("bloody_nykium_hoe",
            () -> new HoeItem(HexcraftToolTiers.BLOODYNYKIUM, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> BLOODY_NYKIUM_PAXEL = ITEMS.register("bloody_nykium_paxel",
            () -> new PaxelItem(HexcraftToolTiers.BLOODYNYKIUM, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> JORMIUM_SWORD = ITEMS.register("jormuim_sword",
            () -> new SwordItem(HexcraftToolTiers.JORMIUM, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> JORMIUM_PICKAXE = ITEMS.register("jormuim_pickaxe",
            () -> new PickaxeItem(HexcraftToolTiers.JORMIUM, 1, 2, new Item.Properties()));

    public static final RegistryObject<Item> JORMIUM_SHOVEL = ITEMS.register("jormuim_shovel",
            () -> new ShovelItem(HexcraftToolTiers.JORMIUM, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> JORMIUM_AXE = ITEMS.register("jormuim_axe",
            () -> new AxeItem(HexcraftToolTiers.JORMIUM, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> JORMIUM_HOE = ITEMS.register("jormuim_hoe",
            () -> new HoeItem(HexcraftToolTiers.JORMIUM, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> JORMIUM_PAXEL = ITEMS.register("jormium_paxel",
            () -> new PaxelItem(HexcraftToolTiers.JORMIUM, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> CUROGEN_SWORD = ITEMS.register("curogen_sword",
            () -> new SwordItem(HexcraftToolTiers.CUROGEN, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> CUROGEN_PICKAXE = ITEMS.register("curogen_pickaxe",
            () -> new PickaxeItem(HexcraftToolTiers.CUROGEN, 1, 2, new Item.Properties()));

    public static final RegistryObject<Item> CUROGEN_SHOVEL = ITEMS.register("curogen_shovel",
            () -> new ShovelItem(HexcraftToolTiers.CUROGEN, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> CUROGEN_AXE = ITEMS.register("curogen_axe",
            () -> new AxeItem(HexcraftToolTiers.CUROGEN, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> CUROGEN_HOE = ITEMS.register("curogen_hoe",
            () -> new HoeItem(HexcraftToolTiers.CUROGEN, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> CUROGEN_PAXEL = ITEMS.register("curogen_paxel",
            () -> new PaxelItem(HexcraftToolTiers.CUROGEN, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> SILVER_SWORD = ITEMS.register("silver_sword",
            () -> new SwordItem(HexcraftToolTiers.SILVER, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> SILVER_PICKAXE = ITEMS.register("silver_pickaxe",
            () -> new PickaxeItem(HexcraftToolTiers.SILVER, 1, 2, new Item.Properties()));

    public static final RegistryObject<Item> SILVER_SHOVEL = ITEMS.register("silver_shovel",
            () -> new ShovelItem(HexcraftToolTiers.SILVER, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> SILVER_AXE = ITEMS.register("silver_axe",
            () -> new AxeItem(HexcraftToolTiers.SILVER, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> SILVER_HOE = ITEMS.register("silver_hoe",
            () -> new HoeItem(HexcraftToolTiers.SILVER, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> SILVER_PAXEL = ITEMS.register("silver_paxel",
            () -> new PaxelItem(HexcraftToolTiers.STEEL, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> VAMPIRIC_SWORD = ITEMS.register("vampiric_sword",
            () -> new SwordItem(HexcraftToolTiers.VAMPIRIC, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> VAMPIRIC_PICKAXE = ITEMS.register("vampiric_pickaxe",
            () -> new PickaxeItem(HexcraftToolTiers.VAMPIRIC, 1, 2, new Item.Properties()));

    public static final RegistryObject<Item> VAMPIRIC_SHOVEL = ITEMS.register("vampiric_shovel",
            () -> new ShovelItem(HexcraftToolTiers.VAMPIRIC, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> VAMPIRIC_AXE = ITEMS.register("vampiric_axe",
            () -> new AxeItem(HexcraftToolTiers.VAMPIRIC, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> VAMPIRIC_HOE = ITEMS.register("vampiric_hoe",
            () -> new HoeItem(HexcraftToolTiers.VAMPIRIC, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> VAMPIRIC_PAXEL = ITEMS.register("vampiric_paxel",
            () -> new PaxelItem(HexcraftToolTiers.VAMPIRIC, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> DEEPSEER_SWORD = ITEMS.register("deepseer_sword",
            () -> new SwordItem(HexcraftToolTiers.DEEPSEER, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> DEEPSEER_PICKAXE = ITEMS.register("deepseer_pickaxe",
            () -> new PickaxeItem(HexcraftToolTiers.DEEPSEER, 1, 2, new Item.Properties()));

    public static final RegistryObject<Item> DEEPSEER_SHOVEL = ITEMS.register("deepseer_shovel",
            () -> new ShovelItem(HexcraftToolTiers.DEEPSEER, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> DEEPSEER_AXE = ITEMS.register("deepseer_axe",
            () -> new AxeItem(HexcraftToolTiers.DEEPSEER, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> DEEPSEER_HOE = ITEMS.register("deepseer_hoe",
            () -> new HoeItem(HexcraftToolTiers.DEEPSEER, 2, 3, new Item.Properties()));

    public static final RegistryObject<Item> DEEPSEER_PAXEL = ITEMS.register("deepseer_paxel",
            () -> new PaxelItem(HexcraftToolTiers.DEEPSEER, 2, 3, new Item.Properties()));


    //Special Weapons
    public static final RegistryObject<Item> CRIMSON_FANG = ITEMS.register("crimson_fang",
            CrimsonFang::new);

    //Bows
    public static final RegistryObject<Item> STEEL_BOW = ITEMS.register("steel_bow",
            () -> new BowItem(new Item.Properties().durability(384)));

    public static final RegistryObject<Item> DARK_STEEL_BOW = ITEMS.register("dark_steel_bow",
            () -> new BowItem(new Item.Properties().durability(384)));

    public static final RegistryObject<Item> BLOODY_NYKIUM_BOW = ITEMS.register("bloody_nykium_bow",
            () -> new BowItem(new Item.Properties().durability(384)));

    public static final RegistryObject<Item> JORMIUM_BOW = ITEMS.register("jormium_bow",
            () -> new BowItem(new Item.Properties().durability(384)));

    public static final RegistryObject<Item> CUROGEN_BOW = ITEMS.register("curogen_bow",
            () -> new BowItem(new Item.Properties().durability(384)));

    //Armor
    public static final RegistryObject<Item> BLINDFOLD = ITEMS.register("blindfold",
            () -> new BlindfoldItem());

    public static final RegistryObject<Item> STEEL_HELMET = ITEMS.register("steel_helmet",
            () -> new ArmorItem(HexcraftArmorMaterials.STEEL, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> STEEL_CHESTPLATE = ITEMS.register("steel_chestplate",
            () -> new ArmorItem(HexcraftArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> STEEL_LEGGING = ITEMS.register("steel_leggings",
            () -> new ArmorItem(HexcraftArmorMaterials.STEEL, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> STEEL_BOOTS = ITEMS.register("steel_boots",
            () -> new ArmorItem(HexcraftArmorMaterials.STEEL, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> DARK_STEEL_HELMET = ITEMS.register("dark_steel_helmet",
            () -> new ArmorItem(HexcraftArmorMaterials.DARKSTEEL, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> DARK_STEEL_CHESTPLATE = ITEMS.register("dark_steel_chestplate",
            () -> new ArmorItem(HexcraftArmorMaterials.DARKSTEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> DARK_STEEL_LEGGING = ITEMS.register("dark_steel_leggings",
            () -> new ArmorItem(HexcraftArmorMaterials.DARKSTEEL, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> DARK_STEEL_BOOTS = ITEMS.register("dark_steel_boots",
            () -> new ArmorItem(HexcraftArmorMaterials.DARKSTEEL, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> BLOODY_NYKIUM_HELMET = ITEMS.register("bloody_nykium_helmet",
            () -> new HexcraftArmorItem(HexcraftArmorMaterials.BLOODYNYKIUM, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> BLOODY_NYKIUM_CHESTPLATE = ITEMS.register("bloody_nykium_chestplate",
            () -> new HexcraftArmorItem(HexcraftArmorMaterials.BLOODYNYKIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> BLOODY_NYKIUM_LEGGING = ITEMS.register("bloody_nykium_leggings",
            () -> new HexcraftArmorItem(HexcraftArmorMaterials.BLOODYNYKIUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> BLOODY_NYKIUM_BOOTS = ITEMS.register("bloody_nykium_boots",
            () -> new HexcraftArmorItem(HexcraftArmorMaterials.BLOODYNYKIUM, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> JORMIUM_HELMET = ITEMS.register("jormium_helmet",
            () -> new ArmorItem(HexcraftArmorMaterials.JORMIUM, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> JORMIUM_CHESTPLATE = ITEMS.register("jormium_chestplate",
            () -> new ArmorItem(HexcraftArmorMaterials.JORMIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> JORMIUM_LEGGING = ITEMS.register("jormium_leggings",
            () -> new ArmorItem(HexcraftArmorMaterials.JORMIUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> JORMIUM_BOOTS = ITEMS.register("jormium_boots",
            () -> new ArmorItem(HexcraftArmorMaterials.JORMIUM, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> CUROGEN_HELMET = ITEMS.register("curogen_helmet",
            () -> new HexcraftArmorItem(HexcraftArmorMaterials.CUROGEN, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> CUROGEN_CHESTPLATE = ITEMS.register("curogen_chestplate",
            () -> new HexcraftArmorItem(HexcraftArmorMaterials.CUROGEN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> CUROGEN_LEGGING = ITEMS.register("curogen_leggings",
            () -> new HexcraftArmorItem(HexcraftArmorMaterials.CUROGEN, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> CUROGEN_BOOTS = ITEMS.register("curogen_boots",
            () -> new HexcraftArmorItem(HexcraftArmorMaterials.CUROGEN, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> SILVER_HELMET = ITEMS.register("silver_helmet",
            () -> new ArmorItem(HexcraftArmorMaterials.SILVER, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> SILVER_CHESTPLATE = ITEMS.register("silver_chestplate",
            () -> new ArmorItem(HexcraftArmorMaterials.SILVER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> SILVER_LEGGING = ITEMS.register("silver_leggings",
            () -> new ArmorItem(HexcraftArmorMaterials.SILVER, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> SILVER_BOOTS = ITEMS.register("silver_boots",
            () -> new ArmorItem(HexcraftArmorMaterials.SILVER, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> VAMPIRIC_HELMET = ITEMS.register("vampiric_helmet",
            () -> new ArmorItem(HexcraftArmorMaterials.VAMPIRIC, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> VAMPIRIC_CHESTPLATE = ITEMS.register("vampiric_chestplate",
            () -> new ArmorItem(HexcraftArmorMaterials.VAMPIRIC, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> VAMPIRIC_LEGGING = ITEMS.register("vampiric_leggings",
            () -> new ArmorItem(HexcraftArmorMaterials.VAMPIRIC, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> VAMPIRIC_BOOTS = ITEMS.register("vampiric_boots",
            () -> new ArmorItem(HexcraftArmorMaterials.VAMPIRIC, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> DEEPSEER_HELM = ITEMS.register("deepseer_helm",
            () -> new ArmorItem(HexcraftArmorMaterials.DEEPSEER, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> DEEPSEER_CHESTPLATE = ITEMS.register("deepseer_chestplate",
            () -> new ArmorItem(HexcraftArmorMaterials.DEEPSEER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> DEEPSEER_LEGGING = ITEMS.register("deepseer_leggings",
            () -> new ArmorItem(HexcraftArmorMaterials.DEEPSEER, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> DEEPSEER_BOOTS = ITEMS.register("deepseer_boots",
            () -> new ArmorItem(HexcraftArmorMaterials.DEEPSEER, ArmorItem.Type.BOOTS, new Item.Properties()));


    //Plants
    public static final RegistryObject<Item> WITCHES_LADDER_ITEM = ITEMS.register("witches_ladder_item",
            () -> new BlockItem(HexcraftBlocks.WITCHES_LADDER.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> VILEVINE_ITEM = ITEMS.register("vilevine_item",
            () -> new BlockItem(HexcraftBlocks.VILEVINE.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> BLOODTHORN_VINES_ITEM = ITEMS.register("bloodthorn_vines_item",
            () -> new BlockItem(HexcraftBlocks.BLOODTHORN_VINES.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> SLINKROOT_ITEM = ITEMS.register("slinkroot_item",
            () -> new BlockItem(HexcraftBlocks.SLINKROOT.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> LIVING_KELP_ITEM = ITEMS.register("living_kelp_item",
            () -> new BlockItem(HexcraftBlocks.LIVING_KELP_PLANT.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> GARLIC_STRAND_ITEM = ITEMS.register("garlic_strand_item",
            () -> new BlockItem(HexcraftBlocks.GARLIC_STRAND.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> VERVAIN_STRAND_ITEM = ITEMS.register("vervain_strand_item",
            () -> new BlockItem(HexcraftBlocks.VERVAIN_STRAND.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> MANDRAKE_SEEDS = ITEMS.register("mandrake_seeds",
            () -> new ItemNameBlockItem(HexcraftBlocks.MANDRAKE_FLOWER.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> MANDRAKE_ROOT = ITEMS.register("mandrake_root",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GARLIC = ITEMS.register("garlic",
            () -> new ItemNameBlockItem(HexcraftBlocks.GARLIC_PLANT.get(),
                    new Item.Properties().food(Foods.CARROT).food(Foods.CARROT)));

    public static final RegistryObject<Item> VERVAIN_SEEDS = ITEMS.register("vervain_seeds",
            () -> new ItemNameBlockItem(HexcraftBlocks.VERVAIN_FLOWER.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> VERVAIN = ITEMS.register("vervain",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WOLFSBANE_SEEDS = ITEMS.register("wolfsbane_seeds",
            () -> new ItemNameBlockItem(HexcraftBlocks.WOLFSBANE_FLOWER.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> WOLFSBANE = ITEMS.register("wolfsbane",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> IRENIAL_SEEDS = ITEMS.register("irenial_seeds",
            () -> new ItemNameBlockItem(HexcraftBlocks.IRENIAL_FLOWER.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> IRENIAL = ITEMS.register("irenial",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MIRA_SEEDS = ITEMS.register("mira_seeds",
            () -> new ItemNameBlockItem(HexcraftBlocks.MIRA_FLOWER.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> MIRA = ITEMS.register("mira",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> XERIFAE_SEEDS = ITEMS.register("xerifae_seeds",
            () -> new ItemNameBlockItem(HexcraftBlocks.XERIFAE_FLOWER.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> XERIFAE = ITEMS.register("xerifae",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SENIA_SEEDS = ITEMS.register("senia_seeds",
            () -> new ItemNameBlockItem(HexcraftBlocks.SENIA_FLOWER.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> SENIA = ITEMS.register("senia",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AERPINE_SEEDS = ITEMS.register("aerpine_seeds",
            () -> new ItemNameBlockItem(HexcraftBlocks.AERPINE_FLOWER.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> AERPINE = ITEMS.register("aerpine",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PERENNIA_SEEDS = ITEMS.register("perennia_seeds",
            () -> new ItemNameBlockItem(HexcraftBlocks.PERENNIA_FLOWER.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> PERENNIA = ITEMS.register("perennia",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BELLADONNA_SEEDS = ITEMS.register("belladonna_seeds",
            () -> new ItemNameBlockItem(HexcraftBlocks.BELLADONNA_PLANT.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> BELLADONNA = ITEMS.register("belladonna",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HELLEBORE_SEEDS = ITEMS.register("hellebore_seeds",
            () -> new ItemNameBlockItem(HexcraftBlocks.HELLEBORE_PLANT.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> HELLEBORE = ITEMS.register("hellebore",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAGE_SEEDS = ITEMS.register("sage_seeds",
            () -> new ItemNameBlockItem(HexcraftBlocks.SAGE_PLANT.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> SAGE = ITEMS.register("sage",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WORMWOOD_SEEDS = ITEMS.register("wormwood_seeds",
            () -> new ItemNameBlockItem(HexcraftBlocks.WORMWOOD_PLANT.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> WORMWOOD = ITEMS.register("wormwood",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WATER_ARTICHOKE_SEEDS = ITEMS.register("water_artichoke_seeds",
            () -> new ItemNameBlockItem(HexcraftBlocks.WATER_ARTICHOKE_PLANT.get(),
                    new Item.Properties()));

    public static final RegistryObject<Item> WATER_ARTICHOKE = ITEMS.register("water_artichoke",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DUSKROOT = ITEMS.register("duskroot",
            () -> new Item(new Item.Properties()));

    //Foods
    public static final RegistryObject<Item> JUNIPER_BERRIES = ITEMS.register("juniper_berries",
            () -> new Item(new Item.Properties().food(HexcraftFoods.JUNIPER_BERRIES)));

    public static final RegistryObject<Item> BLOOD_BERRIES = ITEMS.register("blood_berries",
            () -> new ItemNameBlockItem(HexcraftBlocks.BLOOD_BERRIES_PLANT.get(),
                    new Item.Properties().food(HexcraftFoods.BLOOD_BERRIES)));

    public static final RegistryObject<Item> BLOOD_APPLE = ITEMS.register("blood_apple",
            () -> new Item(new Item.Properties().food(HexcraftFoods.BLOOD_APPLE)));

    public static final RegistryObject<Item> FAIRY_DUST = ITEMS.register("fairy_dust",
            () -> new Item(new Item.Properties().food(Foods.SUSPICIOUS_STEW)));

    public static final RegistryObject<Item> PIXIE_DUST = ITEMS.register("pixie_dust",
            () -> new Item(new Item.Properties().food(Foods.SUSPICIOUS_STEW)));

    public static final RegistryObject<Item> BLISTER_CACTUS_FRUIT = ITEMS.register("blister_cactus_fruit",
            () -> new Item(new Item.Properties().food(HexcraftFoods.BLISTER_CACTUS_FRUIT)));

    public static final RegistryObject<Item> BLISTER_FRUIT = ITEMS.register("blister_fruit",
            () -> new Item(new Item.Properties().food(HexcraftFoods.COOKED_BLISTER_FRUIT)));

    public static final RegistryObject<Item> LIVING_KELP_SALAD = ITEMS.register("living_kelp_salad",
            () -> new Item(new Item.Properties().food(HexcraftFoods.LIVING_KELP_SALAD_FOOD)));

    public static final RegistryObject<Item> INCENDIA_SPELL_PAGE = ITEMS.register("incendia_spell_page",
            () -> new SpellPageItem("incendia", new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));



    //Liquids
    public static final RegistryObject<Item> BLOOD_BUCKET = ITEMS.register("blood_bucket",
            () -> new BucketItem(HexcraftFluids.SOURCE_BLOOD, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> DEEP_WATER_BUCKET = ITEMS.register("deep_water_bucket",
            () -> new BucketItem(HexcraftFluids.SOURCE_DEEP_WATER, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));


    //Signs
    public static final RegistryObject<Item> EBONY_SIGN = ITEMS.register("ebony_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), HexcraftBlocks.EBONY_SIGN.get(), HexcraftBlocks.EBONY_WALL_SIGN.get()));

    public static final RegistryObject<Item> BLOOD_OAK_SIGN = ITEMS.register("blood_oak_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), HexcraftBlocks.BLOOD_OAK_SIGN.get(), HexcraftBlocks.BLOOD_OAK_WALL_SIGN.get()));

    public static final RegistryObject<Item> HELL_BARK_SIGN = ITEMS.register("hell_bark_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), HexcraftBlocks.HELL_BARK_SIGN.get(), HexcraftBlocks.HELL_BARK_WALL_SIGN.get()));

    public static final RegistryObject<Item> WHITE_OAK_SIGN = ITEMS.register("white_oak_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), HexcraftBlocks.WHITE_OAK_SIGN.get(), HexcraftBlocks.WHITE_OAK_WALL_SIGN.get()));

    public static final RegistryObject<Item> ALDER_SIGN = ITEMS.register("alder_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), HexcraftBlocks.ALDER_SIGN.get(), HexcraftBlocks.ALDER_WALL_SIGN.get()));

    public static final RegistryObject<Item> WITCH_HAZEL_SIGN = ITEMS.register("witch_hazel_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), HexcraftBlocks.WITCH_HAZEL_SIGN.get(), HexcraftBlocks.WITCH_WOOD_WALL_SIGN.get()));

    public static final RegistryObject<Item> WILLOW_SIGN = ITEMS.register("willow_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), HexcraftBlocks.WILLOW_SIGN.get(), HexcraftBlocks.WILLOW_WALL_SIGN.get()));

    public static final RegistryObject<Item> HAWTHORN_SIGN = ITEMS.register("hawthorn_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), HexcraftBlocks.HAWTHORN_SIGN.get(), HexcraftBlocks.HAWTHORN_WALL_SIGN.get()));

    public static final RegistryObject<Item> CEDAR_SIGN = ITEMS.register("cedar_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), HexcraftBlocks.CEDAR_SIGN.get(), HexcraftBlocks.CEDAR_WALL_SIGN.get()));

    public static final RegistryObject<Item> DISTORTED_SIGN = ITEMS.register("distorted_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), HexcraftBlocks.DISTORTED_SIGN.get(), HexcraftBlocks.DISTORTED_WALL_SIGN.get()));

    public static final RegistryObject<Item> ELDER_SIGN = ITEMS.register("elder_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), HexcraftBlocks.ELDER_SIGN.get(), HexcraftBlocks.ELDER_WALL_SIGN.get()));

    public static final RegistryObject<Item> JUNIPER_SIGN = ITEMS.register("juniper_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), HexcraftBlocks.JUNIPER_SIGN.get(), HexcraftBlocks.JUNIPER_WALL_SIGN.get()));

    public static final RegistryObject<Item> ROWAN_SIGN = ITEMS.register("rowan_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), HexcraftBlocks.ROWAN_SIGN.get(), HexcraftBlocks.ROWAN_WALL_SIGN.get()));

    public static final RegistryObject<Item> TWISTED_SIGN = ITEMS.register("twisted_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), HexcraftBlocks.TWISTED_SIGN.get(), HexcraftBlocks.TWISTED_WALL_SIGN.get()));

    public static final RegistryObject<Item> WITCH_WOOD_SIGN = ITEMS.register("witch_wood_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), HexcraftBlocks.WITCH_WOOD_SIGN.get(), HexcraftBlocks.WITCH_WOOD_WALL_SIGN.get()));

    public static final RegistryObject<Item> ECHO_WOOD_SIGN = ITEMS.register("echo_wood_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), HexcraftBlocks.ECHO_WOOD_SIGN.get(), HexcraftBlocks.ECHO_WOOD_WALL_SIGN.get()));

    public static final RegistryObject<Item> PHOENIX_SIGN = ITEMS.register("phoenix_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), HexcraftBlocks.PHOENIX_SIGN.get(), HexcraftBlocks.PHOENIX_WALL_SIGN.get()));

    public static final RegistryObject<Item> EBONY_HANGING_SIGN = ITEMS.register("ebony_hanging_sign",
            () ->  new HangingSignItem(HexcraftBlocks.EBONY_HANGING_SIGN.get(), HexcraftBlocks.EBONY_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> BLOOD_OAK_HANGING_SIGN = ITEMS.register("blood_oak_hanging_sign",
            () ->  new HangingSignItem(HexcraftBlocks.BLOOD_OAK_HANGING_SIGN.get(), HexcraftBlocks.BLOOD_OAK_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> HELL_BARK_HANGING_SIGN = ITEMS.register("hell_bark_hanging_sign",
            () ->  new HangingSignItem(HexcraftBlocks.HELL_BARK_HANGING_SIGN.get(), HexcraftBlocks.HELL_BARK_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> WHITE_OAK_HANGING_SIGN = ITEMS.register("white_oak_hanging_sign",
            () ->  new HangingSignItem(HexcraftBlocks.WHITE_OAK_HANGING_SIGN.get(), HexcraftBlocks.WHITE_OAK_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> ALDER_HANGING_SIGN = ITEMS.register("alder_hanging_ign",
            () ->  new HangingSignItem(HexcraftBlocks.ALDER_HANGING_SIGN.get(), HexcraftBlocks.ALDER_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> WITCH_HAZEL_HANGING_SIGN = ITEMS.register("witch_hazel_hanging_sign",
            () ->  new HangingSignItem(HexcraftBlocks.WITCH_HAZEL_HANGING_SIGN.get(), HexcraftBlocks.WITCH_HAZEL_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> WILLOW_HANGING_SIGN = ITEMS.register("willow_hanging_sign",
            () ->  new HangingSignItem(HexcraftBlocks.WILLOW_HANGING_SIGN.get(), HexcraftBlocks.WILLOW_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> HAWTHORN_HANGING_SIGN = ITEMS.register("hawthorn_hanging_sign",
            () ->  new HangingSignItem(HexcraftBlocks.HAWTHORN_HANGING_SIGN.get(), HexcraftBlocks.HAWTHORN_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> CEDAR_HANGING_SIGN = ITEMS.register("cedar_hanging_sign",
            () ->  new HangingSignItem(HexcraftBlocks.CEDAR_HANGING_SIGN.get(), HexcraftBlocks.CEDAR_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> DISTORTED_HANGING_SIGN = ITEMS.register("distorted_hanging_sign",
            () ->  new HangingSignItem(HexcraftBlocks.DISTORTED_HANGING_SIGN.get(), HexcraftBlocks.DISTORTED_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> ELDER_HANGING_SIGN = ITEMS.register("elder_hanging_sign",
            () ->  new HangingSignItem(HexcraftBlocks.ELDER_HANGING_SIGN.get(), HexcraftBlocks.ELDER_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> JUNIPER_HANGING_SIGN = ITEMS.register("juniper_hanging_sign",
            () ->  new HangingSignItem(HexcraftBlocks.JUNIPER_HANGING_SIGN.get(), HexcraftBlocks.JUNIPER_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> ROWAN_HANGING_SIGN = ITEMS.register("rowan_hanging_sign",
            () ->  new HangingSignItem(HexcraftBlocks.ROWAN_HANGING_SIGN.get(), HexcraftBlocks.ROWAN_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> TWISTED_HANGING_SIGN = ITEMS.register("twisted_hanging_sign",
            () ->  new HangingSignItem(HexcraftBlocks.TWISTED_HANGING_SIGN.get(), HexcraftBlocks.TWISTED_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> WITCH_WOOD_HANGING_SIGN = ITEMS.register("witch_wood_hanging_sign",
            () ->  new HangingSignItem(HexcraftBlocks.WITCH_WOOD_HANGING_SIGN.get(), HexcraftBlocks.WITCH_WOOD_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> ECHO_WOOD_HANGING_SIGN = ITEMS.register("echo_wood_hanging_sign",
            () ->  new HangingSignItem(HexcraftBlocks.ECHO_WOOD_HANGING_SIGN.get(), HexcraftBlocks.ECHO_WOOD_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> PHOENIX_HANGING_SIGN = ITEMS.register("phoenix_hanging_sign",
            () ->  new HangingSignItem(HexcraftBlocks.PHOENIX_HANGING_SIGN.get(), HexcraftBlocks.PHOENIX_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    //Boats
    public static final RegistryObject<Item> EBONY_BOAT = ITEMS.register("ebony_boat",
            () -> new HexcraftBoatItem(false, HexcraftBoatEntity.Type.EBONY, new Item.Properties()));
    
    public static final RegistryObject<Item> EBONY_CHEST_BOAT = ITEMS.register("ebony_chest_boat",
            () -> new HexcraftBoatItem(true, HexcraftBoatEntity.Type.EBONY, new Item.Properties()));

    public static final RegistryObject<Item> BLOOD_OAK_BOAT = ITEMS.register("blood_oak_boat",
            () -> new HexcraftBoatItem(false, HexcraftBoatEntity.Type.BLOOD_OAK, new Item.Properties()));

    public static final RegistryObject<Item> BLOOD_OAK_CHEST_BOAT = ITEMS.register("blood_oak_chest_boat",
            () -> new HexcraftBoatItem(true, HexcraftBoatEntity.Type.BLOOD_OAK, new Item.Properties()));

    public static final RegistryObject<Item> HELL_BARK_BOAT = ITEMS.register("hell_bark_boat",
            () -> new HexcraftBoatItem(false, HexcraftBoatEntity.Type.HELL_BARK, new Item.Properties()));

    public static final RegistryObject<Item> HELL_BARK_CHEST_BOAT = ITEMS.register("hell_bark_chest_boat",
            () -> new HexcraftBoatItem(true, HexcraftBoatEntity.Type.HELL_BARK, new Item.Properties()));

    public static final RegistryObject<Item> WHITE_OAK_BOAT = ITEMS.register("white_oak_boat",
            () -> new HexcraftBoatItem(false, HexcraftBoatEntity.Type.WHITE_OAK, new Item.Properties()));

    public static final RegistryObject<Item> WHITE_OAK_CHEST_BOAT = ITEMS.register("white_oak_chest_boat",
            () -> new HexcraftBoatItem(true, HexcraftBoatEntity.Type.WHITE_OAK, new Item.Properties()));

    public static final RegistryObject<Item> ALDER_BOAT = ITEMS.register("alder_boat",
            () -> new HexcraftBoatItem(false, HexcraftBoatEntity.Type.ALDER, new Item.Properties()));

    public static final RegistryObject<Item> ALDER_CHEST_BOAT = ITEMS.register("alder_chest_boat",
            () -> new HexcraftBoatItem(true, HexcraftBoatEntity.Type.ALDER, new Item.Properties()));

    public static final RegistryObject<Item> WITCH_HAZEL_BOAT = ITEMS.register("witch_hazel_boat",
            () -> new HexcraftBoatItem(false, HexcraftBoatEntity.Type.WITCH_HAZEL, new Item.Properties()));

    public static final RegistryObject<Item> WITCH_HAZEL_CHEST_BOAT = ITEMS.register("witch_hazel_chest_boat",
            () -> new HexcraftBoatItem(true, HexcraftBoatEntity.Type.WITCH_HAZEL, new Item.Properties()));

    public static final RegistryObject<Item> WILLOW_BOAT = ITEMS.register("willow_boat",
            () -> new HexcraftBoatItem(false, HexcraftBoatEntity.Type.WILLOW, new Item.Properties()));

    public static final RegistryObject<Item>WILLOW_CHEST_BOAT = ITEMS.register("willow_chest_boat",
            () -> new HexcraftBoatItem(true, HexcraftBoatEntity.Type.WILLOW, new Item.Properties()));

    public static final RegistryObject<Item> HAWTHORN_BOAT = ITEMS.register("hawthorn_boat",
            () -> new HexcraftBoatItem(false, HexcraftBoatEntity.Type.HAWTHORN, new Item.Properties()));

    public static final RegistryObject<Item> HAWTHORN_CHEST_BOAT = ITEMS.register("hawthorn_chest_boat",
            () -> new HexcraftBoatItem(true, HexcraftBoatEntity.Type.HAWTHORN, new Item.Properties()));

    public static final RegistryObject<Item> CEDAR_BOAT = ITEMS.register("cedar_boat",
            () -> new HexcraftBoatItem(false, HexcraftBoatEntity.Type.CEDAR, new Item.Properties()));

    public static final RegistryObject<Item> CEDAR_CHEST_BOAT = ITEMS.register("cedar_chest_boat",
            () -> new HexcraftBoatItem(true, HexcraftBoatEntity.Type.CEDAR, new Item.Properties()));

    public static final RegistryObject<Item> DISTORTED_BOAT = ITEMS.register("distorted_boat",
            () -> new HexcraftBoatItem(false, HexcraftBoatEntity.Type.DISTORTED, new Item.Properties()));

    public static final RegistryObject<Item> DISTORTED_CHEST_BOAT = ITEMS.register("distorted_chest_boat",
            () -> new HexcraftBoatItem(true, HexcraftBoatEntity.Type.DISTORTED, new Item.Properties()));

    public static final RegistryObject<Item> ELDER_BOAT = ITEMS.register("elder_boat",
            () -> new HexcraftBoatItem(false, HexcraftBoatEntity.Type.ELDER, new Item.Properties()));

    public static final RegistryObject<Item> ELDER_CHEST_BOAT = ITEMS.register("elder_chest_boat",
            () -> new HexcraftBoatItem(true, HexcraftBoatEntity.Type.ELDER, new Item.Properties()));

    public static final RegistryObject<Item> JUNIPER_BOAT = ITEMS.register("juniper_boat",
            () -> new HexcraftBoatItem(false, HexcraftBoatEntity.Type.JUNIPER, new Item.Properties()));

    public static final RegistryObject<Item> JUNIPER_CHEST_BOAT = ITEMS.register("juniper_chest_boat",
            () -> new HexcraftBoatItem(true, HexcraftBoatEntity.Type.JUNIPER, new Item.Properties()));

    public static final RegistryObject<Item> ROWAN_BOAT = ITEMS.register("rowan_boat",
            () -> new HexcraftBoatItem(false, HexcraftBoatEntity.Type.ROWAN, new Item.Properties()));

    public static final RegistryObject<Item> ROWAN_CHEST_BOAT = ITEMS.register("rowan_chest_boat",
            () -> new HexcraftBoatItem(true, HexcraftBoatEntity.Type.ROWAN, new Item.Properties()));

    public static final RegistryObject<Item> TWISTED_BOAT = ITEMS.register("twisted_boat",
            () -> new HexcraftBoatItem(false, HexcraftBoatEntity.Type.TWISTED, new Item.Properties()));

    public static final RegistryObject<Item> TWISTED_CHEST_BOAT = ITEMS.register("twisted_chest_boat",
            () -> new HexcraftBoatItem(true, HexcraftBoatEntity.Type.TWISTED, new Item.Properties()));

    public static final RegistryObject<Item> WITCH_WOOD_BOAT = ITEMS.register("witch_wood_boat",
            () -> new HexcraftBoatItem(false, HexcraftBoatEntity.Type.WITCH_WOOD, new Item.Properties()));

    public static final RegistryObject<Item> WITCH_WOOD_CHEST_BOAT = ITEMS.register("witch_wood_chest_boat",
            () -> new HexcraftBoatItem(true, HexcraftBoatEntity.Type.WITCH_WOOD, new Item.Properties()));

    public static final RegistryObject<Item> ECHO_WOOD_BOAT = ITEMS.register("echo_wood_boat",
            () -> new HexcraftBoatItem(false, HexcraftBoatEntity.Type.ECHO_WOOD, new Item.Properties()));

    public static final RegistryObject<Item> ECHO_WOOD_CHEST_BOAT = ITEMS.register("echo_wood_chest_boat",
            () -> new HexcraftBoatItem(true, HexcraftBoatEntity.Type.ECHO_WOOD, new Item.Properties()));

    //public static final RegistryObject<Item> PHOENIX_BOAT = ITEMS.register("phoenix_boat",
            //() -> new HexcraftBoatItem(false, HexcraftBoatEntity.Type.PHOENIX, new Item.Properties()));

    //public static final RegistryObject<Item> PHOENIX_CHEST_BOAT = ITEMS.register("phoenix_chest_boat",
            //() -> new HexcraftBoatItem(true, HexcraftBoatEntity.Type.PHOENIX, new Item.Properties()));


    //Chest

    public static final RegistryObject<Item> EBONY_CHEST = registerChestItem("ebony_chest", HexcraftBlocks.EBONY_CHEST);

    public static final RegistryObject<Item> BLOOD_OAK_CHEST = registerChestItem("blood_oak_chest", HexcraftBlocks.BLOOD_OAK_CHEST);

    public static final RegistryObject<Item> HELL_BARK_CHEST = registerChestItem("hell_bark_chest", HexcraftBlocks.HELL_BARK_CHEST);

    public static final RegistryObject<Item> WHITE_OAK_CHEST = registerChestItem("white_oak_chest", HexcraftBlocks.WHITE_OAK_CHEST);

    public static final RegistryObject<Item> ALDER_CHEST = registerChestItem("alder_chest", HexcraftBlocks.ALDER_CHEST);

    public static final RegistryObject<Item> WITCH_HAZEL_CHEST = registerChestItem("witch_hazel_chest", HexcraftBlocks.WITCH_HAZEL_CHEST);

    public static final RegistryObject<Item> WILLOW_CHEST = registerChestItem("willow_chest", HexcraftBlocks.WILLOW_CHEST);

    public static final RegistryObject<Item> HAWTHORN_CHEST = registerChestItem("hawthorn_chest", HexcraftBlocks.HAWTHORN_CHEST);

    public static final RegistryObject<Item> CEDAR_CHEST = registerChestItem("cedar_chest", HexcraftBlocks.CEDAR_CHEST);

    public static final RegistryObject<Item> DISTORTED_CHEST = registerChestItem("distorted_chest", HexcraftBlocks.DISTORTED_CHEST);

    public static final RegistryObject<Item> ELDER_CHEST = registerChestItem("elder_chest", HexcraftBlocks.ELDER_CHEST);

    public static final RegistryObject<Item> JUNIPER_CHEST = registerChestItem("juniper_chest", HexcraftBlocks.JUNIPER_CHEST);

    public static final RegistryObject<Item> ROWAN_CHEST = registerChestItem("rowan_chest", HexcraftBlocks.ROWAN_CHEST);

    public static final RegistryObject<Item> TWISTED_CHEST = registerChestItem("twisted_chest", HexcraftBlocks.TWISTED_CHEST);

    public static final RegistryObject<Item> WITCH_WOOD_CHEST = registerChestItem("witch_wood_chest", HexcraftBlocks.WITCH_WOOD_CHEST);

    public static final RegistryObject<Item> ECHO_WOOD_CHEST = registerChestItem("echo_wood_chest", HexcraftBlocks.ECHO_WOOD_CHEST);

    public static final RegistryObject<Item> PHOENIX_CHEST = registerChestItem("phoenix_chest", HexcraftBlocks.PHOENIX_CHEST);

    public static final RegistryObject<Item> PANDORAS_BOX = ITEMS.register("pandoras_box",
            () -> new BlockItem(HexcraftBlocks.PANDORAS_BOX.get(), new Item.Properties()));

    public static final RegistryObject<Item> SACRIFICIAL_PILLAR = ITEMS.register("sacrificial_pillar",
            () -> new BlockItem(HexcraftBlocks.SACRIFICIAL_PILLAR.get(), new Item.Properties()));

    public static final RegistryObject<Item> DEAD_TWILIGHT_CORAL_WALL_FAN_ITEM = ITEMS.register("dead_twilightcoral_wall_fan_item",
            () -> new StandingAndWallBlockItem(HexcraftBlocks.DEAD_TWILIGHT_CORAL_FAN.get(), HexcraftBlocks.DEAD_TWILIGHT_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> TWILIGHT_CORAL_WALL_FAN_ITEM = ITEMS.register("twilightcoral_wall_fan_item",
            () -> new StandingAndWallBlockItem(HexcraftBlocks.TWILIGHT_CORAL_FAN.get(), HexcraftBlocks.TWILIGHT_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> DEAD_SANGUINE_CORAL_WALL_FAN_ITEM = ITEMS.register("dead_sanguine_coral_wall_fan_item",
            () -> new StandingAndWallBlockItem(HexcraftBlocks.DEAD_SANGUINE_CORAL_FAN.get(), HexcraftBlocks.DEAD_SANGUINE_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> SANGUINE_CORAL_WALL_FAN_ITEM = ITEMS.register("sanguine_coral_wall_fan_item",
            () -> new StandingAndWallBlockItem(HexcraftBlocks.SANGUINE_CORAL_FAN.get(), HexcraftBlocks.SANGUINE_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> DEAD_WHISPER_CORAL_WALL_FAN_ITEM = ITEMS.register("dead_whisper_coral_wall_fan_item",
            () -> new StandingAndWallBlockItem(HexcraftBlocks.DEAD_WHISPER_CORAL_FAN.get(), HexcraftBlocks.DEAD_WHISPER_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> WHISPER_CORAL_WALL_FAN_ITEM = ITEMS.register("whisper_coral_wall_fan_item",
            () -> new StandingAndWallBlockItem(HexcraftBlocks.WHISPER_CORAL_FAN.get(), HexcraftBlocks.WHISPER_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> DEAD_EBONFANG_CORAL_WALL_FAN_ITEM = ITEMS.register("dead_ebonfang_coral_wall_fan_item",
            () -> new StandingAndWallBlockItem(HexcraftBlocks.DEAD_EBONFANG_CORAL_FAN.get(), HexcraftBlocks.DEAD_EBONFANG_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> EBONFANG_CORAL_WALL_FAN_ITEM = ITEMS.register("ebonfang_coral_wall_fan_item",
            () -> new StandingAndWallBlockItem(HexcraftBlocks.EBONFANG_CORAL_FAN.get(), HexcraftBlocks.EBONFANG_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> DEAD_SPECTRAL_BLOOM_CORAL_WALL_FAN_ITEM = ITEMS.register("dead_spectral_bloom_coral_wall_fan_item",
            () -> new StandingAndWallBlockItem(HexcraftBlocks.DEAD_SPECTRAL_BLOOM_CORAL_FAN.get(), HexcraftBlocks.DEAD_SPECTRAL_BLOOM_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> SPECTRAL_BLOOM_CORAL_WALL_FAN_ITEM = ITEMS.register("spectral_bloom_coral_wall_fan_item",
            () -> new StandingAndWallBlockItem(HexcraftBlocks.SPECTRAL_BLOOM_CORAL_FAN.get(), HexcraftBlocks.SPECTRAL_BLOOM_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> DEAD_HELLVINE_CORAL_WALL_FAN_ITEM = ITEMS.register("dead_hellvine_coral_wall_fan_item",
            () -> new StandingAndWallBlockItem(HexcraftBlocks.DEAD_HELLVINE_CORAL_FAN.get(), HexcraftBlocks.DEAD_HELLVINE_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> HELLVINE_CORAL_WALL_FAN_ITEM = ITEMS.register("hellvine_coral_wall_fan_item",
            () -> new StandingAndWallBlockItem(HexcraftBlocks.HELLVINE_CORAL_FAN.get(), HexcraftBlocks.HELLVINE_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    //Brooms
    public static final RegistryObject<Item> EBONY_BROOM = ITEMS.register("ebony_broom",
            () -> new BroomItem(new Item.Properties(), new EbonyTrait()));

    public static final RegistryObject<Item> HAWTHORN_BROOM = ITEMS.register("hawthorn_broom",
            () -> new BroomItem(new Item.Properties(), new HawthornTrait()));

    public static final RegistryObject<Item> WHITE_OAK_BROOM = ITEMS.register("white_oak_broom",
            () -> new BroomItem(new Item.Properties(), new WhiteOakTrait()));

    public static final RegistryObject<Item> WILLOW_BROOM = ITEMS.register("willow_broom",
            () -> new BroomItem(new Item.Properties(), new WillowTrait()));

    public static final RegistryObject<Item> ALDER_BROOM = ITEMS.register("alder_broom",
            () -> new BroomItem(new Item.Properties(), new AlderTrait()));

    public static final RegistryObject<Item> CEDAR_BROOM = ITEMS.register("cedar_broom",
            () -> new BroomItem(new Item.Properties(), new CedarTrait()));

    public static final RegistryObject<Item> DISTORTED_BROOM = ITEMS.register("distorted_broom",
            () -> new BroomItem(new Item.Properties(), new DistortedTrait()));

    public static final RegistryObject<Item> ELDER_BROOM = ITEMS.register("elder_broom",
            () -> new BroomItem(new Item.Properties(), new ElderTrait()));

    public static final RegistryObject<Item> JUNIPER_BROOM = ITEMS.register("juniper_broom",
            () -> new BroomItem(new Item.Properties(), new JuniperTrait()));

    public static final RegistryObject<Item> TWISTED_BROOM = ITEMS.register("twisted_broom",
            () -> new BroomItem(new Item.Properties(), new TwistedTrait()));

    public static final RegistryObject<Item> WITCH_WOOD_BROOM = ITEMS.register("witch_wood_broom",
            () -> new BroomItem(new Item.Properties(), new WitchWoodTrait()));

    public static final RegistryObject<Item> ECHO_WOOD_BROOM = ITEMS.register("echo_wood_broom",
            () -> new BroomItem(new Item.Properties(), new EchoWoodTrait()));

    public static final RegistryObject<Item> PHOENIX_BROOM = ITEMS.register("phoenix_broom",
            () -> new BroomItem(new Item.Properties(), new PhoenixTrait()));


    // 🕯 Witch Candles - Item Registration
    public static final RegistryObject<Item> WHITE_WITCH_CANDLE = registerCandleItem("white_witch_candle", HexcraftBlocks.WHITE_WITCH_CANDLE);
    public static final RegistryObject<Item> ORANGE_WITCH_CANDLE = registerCandleItem("orange_witch_candle", HexcraftBlocks.ORANGE_WITCH_CANDLE);
    public static final RegistryObject<Item> MAGENTA_WITCH_CANDLE = registerCandleItem("magenta_witch_candle", HexcraftBlocks.MAGENTA_WITCH_CANDLE);
    public static final RegistryObject<Item> LIGHT_BLUE_WITCH_CANDLE = registerCandleItem("light_blue_witch_candle", HexcraftBlocks.LIGHT_BLUE_WITCH_CANDLE);
    public static final RegistryObject<Item> YELLOW_WITCH_CANDLE = registerCandleItem("yellow_witch_candle", HexcraftBlocks.YELLOW_WITCH_CANDLE);
    public static final RegistryObject<Item> LIME_WITCH_CANDLE = registerCandleItem("lime_witch_candle", HexcraftBlocks.LIME_WITCH_CANDLE);
    public static final RegistryObject<Item> PINK_WITCH_CANDLE = registerCandleItem("pink_witch_candle", HexcraftBlocks.PINK_WITCH_CANDLE);
    public static final RegistryObject<Item> GRAY_WITCH_CANDLE = registerCandleItem("gray_witch_candle", HexcraftBlocks.GRAY_WITCH_CANDLE);
    public static final RegistryObject<Item> LIGHT_GRAY_WITCH_CANDLE = registerCandleItem("light_gray_witch_candle", HexcraftBlocks.LIGHT_GRAY_WITCH_CANDLE);
    public static final RegistryObject<Item> CYAN_WITCH_CANDLE = registerCandleItem("cyan_witch_candle", HexcraftBlocks.CYAN_WITCH_CANDLE);
    public static final RegistryObject<Item> PURPLE_WITCH_CANDLE = registerCandleItem("purple_witch_candle", HexcraftBlocks.PURPLE_WITCH_CANDLE);
    public static final RegistryObject<Item> BLUE_WITCH_CANDLE = registerCandleItem("blue_witch_candle", HexcraftBlocks.BLUE_WITCH_CANDLE);
    public static final RegistryObject<Item> BROWN_WITCH_CANDLE = registerCandleItem("brown_witch_candle", HexcraftBlocks.BROWN_WITCH_CANDLE);
    public static final RegistryObject<Item> GREEN_WITCH_CANDLE = registerCandleItem("green_witch_candle", HexcraftBlocks.GREEN_WITCH_CANDLE);
    public static final RegistryObject<Item> RED_WITCH_CANDLE = registerCandleItem("red_witch_candle", HexcraftBlocks.RED_WITCH_CANDLE);
    public static final RegistryObject<Item> BLACK_WITCH_CANDLE = registerCandleItem("black_witch_candle", HexcraftBlocks.BLACK_WITCH_CANDLE);


    //Spawn Eggs
    public static final RegistryObject<Item> VAMPIRE_EVOKER_SPAWN_EGG = ITEMS.register("vampire_evoker_spawn_egg",
            () -> new ForgeSpawnEggItem(HexcraftEntities.VAMPIRE_EVOKER, 0x4B4B4B, 0x1D4023, new Item.Properties()));

    public static final RegistryObject<Item> VAMPIRE_VINDICATOR_SPAWN_EGG = ITEMS.register("vampire_vindicator_spawn_egg",
            () -> new ForgeSpawnEggItem(HexcraftEntities.VAMPIRE_VINDICATOR, 0x8B1E1E, 0x7C6A63, new Item.Properties()));

    public static final RegistryObject<Item> VAMPIRE_PILLAGER_SPAWN_EGG = ITEMS.register("vampire_pillager_spawn_egg",
            () -> new ForgeSpawnEggItem(HexcraftEntities.VAMPIRE_PILLAGER, 0x5A5A5A, 0x3A1F0F, new Item.Properties()));

    public static final RegistryObject<Item> VAMPIRE_PIGLIN_SPAWN_EGG = ITEMS.register("vampire_piglin_spawn_egg",
            () -> new ForgeSpawnEggItem(HexcraftEntities.VAMPIRE_PIGLIN, 0x3b1e08, 0x9b2424, new Item.Properties()));

    public static final RegistryObject<Item> LILITH_SPAWN_EGG = ITEMS.register("lilith_spawn_egg",
            () -> new ForgeSpawnEggItem(HexcraftEntities.LILITH, 0x590000, 0x2D0B0B, new Item.Properties()));

    public static final RegistryObject<Item> WENDIGO_SPAWN_EGG = ITEMS.register("wendigo_spawn_egg",
            () -> new ForgeSpawnEggItem(HexcraftEntities.WENDIGO, 0x8B5A2B, 0xFFFFFF, new Item.Properties()));

    public static final RegistryObject<Item> FAIRY_SPAWN_EGG = ITEMS.register("fairy_spawn_egg",
            () -> new ForgeSpawnEggItem(HexcraftEntities.FAIRY, 0xFFC0CB, 0xFF69B4, new Item.Properties()));

    public static final RegistryObject<Item> DROWNED_SLIME_SPAWN_EGG = ITEMS.register("drowned_slime_spawn_egg",
            () -> new ForgeSpawnEggItem(HexcraftEntities.DROWNED_SLIME, 0xADD8E6, 0x5DADE2, new Item.Properties()));

    public static final RegistryObject<Item> BASILISK_SPAWN_EGG = ITEMS.register("basilisk_spawn_egg",
            () -> new ForgeSpawnEggItem(HexcraftEntities.BASILISK, 0x3A9D23, 0x1B4F1B, new Item.Properties()));

    public static final RegistryObject<Item> SIREN_SPAWN_EGG = ITEMS.register("siren_spawn_egg",
            () -> new ForgeSpawnEggItem(HexcraftEntities.SIREN, 0x7D7D7D, 0x4E342E, new Item.Properties()));

    public static final RegistryObject<Item> BANSHEE_SPAWN_EGG = ITEMS.register("banshee_spawn_egg",
            () -> new ForgeSpawnEggItem(HexcraftEntities.BANSHEE, 0xA8A8FF, 0x3A3A6E, new Item.Properties()));

    private static RegistryObject<Item> registerChestItem(String name, RegistryObject<HexcraftChestBlock> block) {
        return ITEMS.register(name, () -> new HexcraftChestBlockItem(block.get(), new Item.Properties()));

    }

    private static RegistryObject<Item> registerBlockItem(RegistryObject<Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));

    }

    private static RegistryObject<Item> registerCandleItem(String name, RegistryObject<Block> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static void registerCompostables() {
        ComposterBlock.COMPOSTABLES.put(BLOOD_APPLE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(JUNIPER_BERRIES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(BLOOD_BERRIES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(WATER_ARTICHOKE_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(WATER_ARTICHOKE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(JUNIPER_BERRIES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(WORMWOOD_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(WORMWOOD.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(SAGE_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(SAGE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(HELLEBORE_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(HELLEBORE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(PERENNIA_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(PERENNIA.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(AERPINE_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(AERPINE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(SENIA_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(SENIA.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(XERIFAE_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(XERIFAE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(MIRA_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(MIRA.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(IRENIAL_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(IRENIAL.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(VERVAIN_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(VERVAIN.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(BELLADONNA_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(BELLADONNA.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(MANDRAKE_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(MANDRAKE_ROOT.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(WOLFSBANE_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(WOLFSBANE.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(GARLIC.get(), 0.45F);
        ComposterBlock.COMPOSTABLES.put(WITCHES_LADDER_ITEM.get(), 0.5F);
        ComposterBlock.COMPOSTABLES.put(LIVING_KELP_ITEM.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(DUSKROOT.get(), 0.65F);

    }

}
