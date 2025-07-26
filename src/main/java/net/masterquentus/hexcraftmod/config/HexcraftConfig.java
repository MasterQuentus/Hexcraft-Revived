package net.masterquentus.hexcraftmod.config;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = HexcraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HexcraftConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CONFIG;

    // ✅ Configurable Mob Names
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> PANDORA_MOB_NAMES;

    // ✅ Configurable Cooldown Duration (Seconds)
    public static final ForgeConfigSpec.IntValue PANDORA_COOLDOWN;

    // ✅ Configurable Loot Settings
    public static final ForgeConfigSpec.BooleanValue PANDORA_MOBS_DROP_ITEMS;
    public static final ForgeConfigSpec.BooleanValue PANDORA_MOBS_DROP_XP;

    // ✅ Configurable Player Leaving Radius
    public static final ForgeConfigSpec.IntValue PANDORA_LEAVE_RADIUS;

    // ✅ Configurable Mob Spawns
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> PANDORA_SPAWNABLE_MOBS;

    // ✅ Enable/Disable Boss Mobs (Warden & Wither)
    public static final ForgeConfigSpec.BooleanValue ENABLE_BOSS_MOBS;

    // ✅ Boss Mob Spawn Chances
    public static final ForgeConfigSpec.DoubleValue PANDORA_WARDEN_SPAWN_CHANCE;
    public static final ForgeConfigSpec.DoubleValue PANDORA_WITHER_SPAWN_CHANCE;

    // ✅ Enable/Disable Chaos Mode (Rare event)
    public static final ForgeConfigSpec.BooleanValue ENABLE_CHAOS_MODE;
    public static final ForgeConfigSpec.DoubleValue PANDORA_CHAOS_MODE_CHANCE;

    // ✅ Enable/Disable Mini-Boss Spawning
    public static final ForgeConfigSpec.BooleanValue ENABLE_MINI_BOSSES;

    // ✅ Configurable Chance for a Good Event Instead of Battle
    public static final ForgeConfigSpec.DoubleValue PANDORA_GOOD_EVENT_CHANCE;

    // ✅ Chest Tiers (Chance of Spawning)
    public static final ForgeConfigSpec.DoubleValue COMMON_CHEST_CHANCE;
    public static final ForgeConfigSpec.DoubleValue UNCOMMON_CHEST_CHANCE;
    public static final ForgeConfigSpec.DoubleValue RARE_CHEST_CHANCE;
    public static final ForgeConfigSpec.DoubleValue LEGENDARY_CHEST_CHANCE;

    // ✅ Whitelisted Items (Prevents Clearing of Rare Items)
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> WHITELISTED_ITEMS;
    public static Set<Item> whitelistedItems;

    // ✅ Drowned Slime Fishing Loot (Configurable)
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> DROWNED_SLIME_FISHING_LOOT;

    private static boolean validateFishingLootEntry(Object obj) {
        if (!(obj instanceof String str)) return false;
        String[] parts = str.split(":");
        if (parts.length != 3) return false;
        String category = parts[0];
        String itemName = parts[1] + ":" + parts[2].split(" ")[0]; // avoid trailing spaces
        try {
            Double.parseDouble(parts[2]);
        } catch (NumberFormatException e) {
            return false;
        }
        return Set.of("fish", "treasure", "junk").contains(category) &&
                ForgeRegistries.ITEMS.containsKey(new ResourceLocation(parts[1]));
    }

    static {
        BUILDER.push("PandorasBox");

        PANDORA_MOB_NAMES = BUILDER.comment("List of custom mob names for Pandora's Box mobs")
                .defineList("mobNames", List.of(
                        "Pandora's Warden", "Cursed Sentinel", "Harbinger of Doom", "Echo of the Abyss",
                        "Specter of Suffering", "Dreadbound Guardian", "Tormented Soul", "Eclipse Knight",
                        "Twilight Revenant", "Shadowborn Stalker", "Abyssal Servant", "Famine's Herald"
                ), obj -> obj instanceof String);

        PANDORA_COOLDOWN = BUILDER.comment("Cooldown time (in seconds) before Pandora's Box can be used again")
                .defineInRange("cooldown", 3600, 60, 86400);

        PANDORA_MOBS_DROP_ITEMS = BUILDER.comment("Should Pandora's mobs drop items?")
                .define("dropItems", false);
        PANDORA_MOBS_DROP_XP = BUILDER.comment("Should Pandora's mobs drop XP?")
                .define("dropXP", false);

        PANDORA_LEAVE_RADIUS = BUILDER.comment("Max distance a player can move away before the event fails")
                .defineInRange("leaveRadius", 30, 10, 100);

        BUILDER.pop();

        // ✅ Configurable List of Spawnable Mobs
        BUILDER.push("PandoraMobSpawns");

        PANDORA_SPAWNABLE_MOBS = BUILDER.comment("List of mobs Pandora's Box can spawn")
                .defineList("spawnableMobs", List.of(
                        "minecraft:zombie", "minecraft:skeleton", "minecraft:wither_skeleton",
                        "minecraft:vindicator", "minecraft:spider", "minecraft:cave_spider",
                        "minecraft:evoker","minecraft:witch","hexcraftmod:vampire_evoker",
                        "hexcraftmod:vampire_piglin","hexcraftmod:vampire_vindicator"
                ), obj -> obj instanceof String);

        ENABLE_BOSS_MOBS = BUILDER.comment("Enable or disable boss mobs (Warden & Wither) from spawning")
                .define("enableBossMobs", true);

        PANDORA_WARDEN_SPAWN_CHANCE = BUILDER.comment("Chance for the Warden to spawn")
                .defineInRange("wardenSpawnChance", 0.01, 0.0, 1.0);

        PANDORA_WITHER_SPAWN_CHANCE = BUILDER.comment("Chance for the Wither to spawn")
                .defineInRange("witherSpawnChance", 0.005, 0.0, 1.0);

        BUILDER.pop();

        // ✅ Chaos Mode Configuration
        BUILDER.push("ChaosMode");

        ENABLE_CHAOS_MODE = BUILDER.comment("Enable or disable Chaos Mode (Rare event)")
                .define("enableChaosMode", true);

        PANDORA_CHAOS_MODE_CHANCE = BUILDER.comment("Chance for Chaos Mode to activate (0.0 to 1.0)")
                .defineInRange("chaosModeChance", 0.02, 0.0, 1.0); // Default: 2% chance

        ENABLE_MINI_BOSSES = BUILDER.comment("Enable or disable mini-bosses from spawning")
                .define("enableMiniBosses", true);

        BUILDER.pop();

        // ✅ Chance for a Good Event Instead of Battle
        BUILDER.push("GoodEvents");

        PANDORA_GOOD_EVENT_CHANCE = BUILDER.comment("Chance for a good event instead of spawning mobs (0.0 to 1.0)")
                .defineInRange("goodEventChance", 0.15, 0.0, 1.0); // Default: 15% chance

        BUILDER.pop();

        // ✅ Add Whitelisted Items Configuration
        BUILDER.push("WhitelistedItems");

        WHITELISTED_ITEMS = BUILDER.comment("A list of whitelisted items to prevent from getting cleared when a wrong input is added. "
                        + "Preferably used for rare or one-time items. Example: Dragon Egg")
                .defineList("items", List.of("minecraft:dragon_egg"), HexcraftConfig::validateItemName);

        BUILDER.pop();

        // ✅ Drowned Slime Fishing Loot Configuration
        BUILDER.push("DrownedSlimeLoot");

        DROWNED_SLIME_FISHING_LOOT = BUILDER.comment(
                "List of drowned slime fishing loot entries, format 'category:itemName:weight'. " +
                        "Categories: fish, treasure, junk. Weights control drop chances within category."
        ).defineList("fishingLootWithWeights", List.of(
                "fish:minecraft:cod:60",
                "fish:minecraft:salmon:25",
                "fish:minecraft:pufferfish:13",
                "fish:minecraft:tropical_fish:2",
                "treasure:minecraft:bow:16.7",
                "treasure:minecraft:enchanted_book:16.7",
                "treasure:minecraft:fishing_rod:16.7",
                "treasure:minecraft:name_tag:16.7",
                "treasure:minecraft:nautilus_shell:16.7",
                "treasure:minecraft:saddle:16.7",
                "junk:minecraft:lily_pad:17",
                "junk:minecraft:bone:10",
                "junk:minecraft:bowl:10",
                "junk:minecraft:leather:10",
                "junk:minecraft:leather_boots:10",
                "junk:minecraft:rotten_flesh:10",
                "junk:minecraft:water_bottle:10",
                "junk:minecraft:tripwire_hook:10",
                "junk:minecraft:stick:5",
                "junk:minecraft:string:5",
                "junk:minecraft:fishing_rod:2",
                "junk:minecraft:ink_sac:1"
        ), HexcraftConfig::validateFishingLootEntry);

        BUILDER.pop();

        // ✅ Chest Tiers (Chance of Spawning)
        BUILDER.push("RewardChests");

        COMMON_CHEST_CHANCE = BUILDER.comment("Chance for Common Chest (50%)")
                .defineInRange("commonChestChance", 0.50, 0.0, 1.0);

        UNCOMMON_CHEST_CHANCE = BUILDER.comment("Chance for Uncommon Chest (30%)")
                .defineInRange("uncommonChestChance", 0.30, 0.0, 1.0);

        RARE_CHEST_CHANCE = BUILDER.comment("Chance for Rare Chest (15%)")
                .defineInRange("rareChestChance", 0.15, 0.0, 1.0);

        LEGENDARY_CHEST_CHANCE = BUILDER.comment("Chance for Legendary Chest (5%)")
                .defineInRange("legendaryChestChance", 0.05, 0.0, 1.0);

        BUILDER.pop();

        CONFIG = BUILDER.build();
    }

    public static List<FishingLootEntry> getFishingLootEntries() {
        return DROWNED_SLIME_FISHING_LOOT.get().stream()
                .map(str -> {
                    // Format: category:namespace:path:weight
                    String[] parts = str.split(":");
                    if (parts.length < 4) return null;

                    String category = parts[0];
                    String namespace = parts[1];
                    String path = parts[2];
                    String weightStr = parts[3].trim();

                    double weight;
                    try {
                        weight = Double.parseDouble(weightStr);
                    } catch (NumberFormatException e) {
                        weight = 0;
                    }

                    Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(namespace, path));
                    if (item == null) return null;

                    return new FishingLootEntry(category, item, weight);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static <T> T weightedRandom(Map<T, Double> weightedMap, RandomSource random) {
        double totalWeight = weightedMap.values().stream().mapToDouble(Double::doubleValue).sum();
        double r = random.nextDouble() * totalWeight;
        double cumulative = 0.0;
        for (Map.Entry<T, Double> entry : weightedMap.entrySet()) {
            cumulative += entry.getValue();
            if (r <= cumulative) {
                return entry.getKey();
            }
        }
        return weightedMap.keySet().iterator().next(); // fallback
    }

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName
                && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }

    public static Item getRandomFishingLoot(RandomSource random) {
        List<FishingLootEntry> lootEntries = getFishingLootEntries();

        // Group entries by category
        Map<String, List<FishingLootEntry>> grouped = lootEntries.stream()
                .collect(Collectors.groupingBy(e -> e.category()));

        // Category weights (from Minecraft: fish=85%, treasure=5%, junk=10%)
        Map<String, Double> categoryChances = Map.of(
                "fish", 85.0,
                "treasure", 5.0,
                "junk", 10.0
        );

        // Pick a category weighted by these chances
        String chosenCategory = weightedRandom(categoryChances, random);

        List<FishingLootEntry> chosenCategoryLoot = grouped.get(chosenCategory);
        if (chosenCategoryLoot == null || chosenCategoryLoot.isEmpty()) {
            // fallback to fish category
            chosenCategory = "fish";
            chosenCategoryLoot = grouped.get("fish");
        }

        // Build map of item to weight for chosen category
        Map<Item, Double> itemWeights = chosenCategoryLoot.stream()
                .collect(Collectors.toMap(FishingLootEntry::item, FishingLootEntry::weight));

        // Pick weighted random item
        return weightedRandom(itemWeights, random);
    }

    /**
     * Helper method to get Drowned Slime fishing loot as a list of Items
     */
    public static List<Item> getDrownedSlimeFishingLoot() {
        return DROWNED_SLIME_FISHING_LOOT.get().stream()
                .map(itemName -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName)))
                .filter(Objects::nonNull)
                .filter(item -> item != Items.AIR)
                .collect(Collectors.toList());
    }
    public static record FishingLootEntry(String category, Item item, double weight) {}

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent event) {
        whitelistedItems = WHITELISTED_ITEMS.get().stream()
                .map(itemName -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName)))
                .collect(Collectors.toSet());
    }
}