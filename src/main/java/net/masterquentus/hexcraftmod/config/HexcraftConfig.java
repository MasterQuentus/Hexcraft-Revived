package net.masterquentus.hexcraftmod.config;

import net.masterquentus.hexcraftmod.HexcraftMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
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

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName
                && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent event) {
        whitelistedItems = WHITELISTED_ITEMS.get().stream()
                .map(itemName -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName)))
                .collect(Collectors.toSet());
    }
}