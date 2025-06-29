package net.masterquentus.hexcraftmod.block.entity.custom;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.block.entity.HexcraftBlockEntities;
import net.masterquentus.hexcraftmod.config.HexcraftConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class PandorasBoxBlockEntity extends BlockEntity {


    // ✅ Basic Loot (Common Chest)
    private static final List<String> COMMON_CHEST_LOOT = List.of(
            "minecraft:iron_ingot", "minecraft:gold_ingot", "minecraft:coal",
            "minecraft:redstone", "minecraft:lapis_lazuli", "minecraft:experience_bottle",
            "minecraft:enchanted_book", "minecraft:bread", "minecraft:wheat", "minecraft:apple",
            "minecraft:carrot", "minecraft:potato", "minecraft:string", "minecraft:bone",
            "minecraft:gunpowder", "minecraft:leather", "minecraft:feather", "minecraft:ink_sac",
            "minecraft:paper", "minecraft:flint", "minecraft:compass", "minecraft:bucket",
            "minecraft:glass_bottle", "minecraft:clay_ball", "minecraft:honeycomb",
            "minecraft:sugar", "minecraft:melon_slice", "minecraft:pumpkin_seeds",
            "minecraft:beetroot_seeds", "minecraft:copper_ingot", "minecraft:amethyst_shard",
            "minecraft:dandelion", "minecraft:fern", "minecraft:oak_sapling", "minecraft:spruce_sapling",
            "minecraft:dark_oak_sapling", "minecraft:birch_sapling", "minecraft:jungle_sapling",
            "minecraft:acacia_sapling", "minecraft:mangrove_propagule", "minecraft:bamboo",
            "hexcraftmod:silver_ingot", "hexcraftmod:vampiric_gem", "hexcraftmod:steel_ingot",
            "hexcraftmod:witch_sigil", "hexcraftmod:rope", "hexcraftmod:bone_needle",
            "hexcraftmod:wood_ash", "hexcraftmod:infused_fabric", "hexcraftmod:mutandis",
            "hexcraftmod:attuned_stone", "hexcraftmod:drop_of_luck"
    );

    // ✅ Mid-Tier Loot (Uncommon Chest)
    private static final List<String> UNCOMMON_CHEST_LOOT = List.of(
            "minecraft:emerald", "minecraft:diamond", "minecraft:golden_apple",
            "minecraft:ender_pearl", "minecraft:blaze_rod", "minecraft:phantom_membrane",
            "minecraft:enchanted_book", "minecraft:glowstone_dust", "minecraft:ghast_tear",
            "minecraft:chorus_fruit", "minecraft:firework_rocket", "minecraft:honey_bottle",
            "minecraft:slime_ball", "minecraft:prismarine_crystals", "minecraft:prismarine_shard",
            "minecraft:shulker_shell", "minecraft:sculk_sensor", "minecraft:echo_shard",
            "minecraft:turtle_egg", "minecraft:spider_eye", "minecraft:fermented_spider_eye",
            "minecraft:magma_cream", "minecraft:copper_block", "minecraft:lodestone",
            "hexcraftmod:moonstone", "hexcraftmod:bloody_nykium", "hexcraftmod:wither_bone",
            "hexcraftmod:necrotic_stone", "hexcraftmod:enchanted_rune", "hexcraftmod:breath_of_the_goddess",
            "hexcraftmod:reek_of_misfortune", "hexcraftmod:exhale_of_the_horned_one",
            "hexcraftmod:diamond_vapour", "hexcraftmod:crimson_fang", "hexcraftmod:wool_of_bat",
            "hexcraftmod:anointing_paste"
    );

    // ✅ High-Tier Loot (Rare Chest)
    private static final List<String> RARE_CHEST_LOOT = List.of(
            "minecraft:netherite_scrap", "minecraft:golden_carrot", "minecraft:potion",
            "minecraft:totem_of_undying", "minecraft:dragon_breath", "minecraft:nether_wart",
            "minecraft:elytra", "minecraft:trident", "minecraft:heart_of_the_sea",
            "minecraft:echo_shard", "minecraft:sculk_catalyst", "minecraft:shulker_shell",
            "minecraft:beacon", "minecraft:end_crystal", "minecraft:netherite_upgrade_smithing_template",
            "hexcraftmod:corrupted_steel", "hexcraftmod:dark_crystal_shards", "hexcraftmod:dark_steel",
            "hexcraftmod:ancient_vampiric_relic", "hexcraftmod:eldritch_shard",
            "hexcraftmod:witch_sigil", "hexcraftmod:necrotic_stone", "hexcraftmod:enchanted_rune",
            "hexcraftmod:condensed_fear", "hexcraftmod:blistering_vitality_potion",
            "hexcraftmod:blood_bottle", "hexcraftmod:venom_bottle", "hexcraftmod:arthana"
    );

    // ✅ Best Loot (Legendary Chest)
    private static final List<String> LEGENDARY_CHEST_LOOT = List.of(
            "minecraft:netherite_ingot", "minecraft:beacon", "minecraft:totem_of_undying",
            "minecraft:nether_star", "minecraft:enchanted_golden_apple",
            "minecraft:dragon_head", "minecraft:netherite_upgrade_smithing_template", "minecraft:music_disc_otherside",
            "minecraft:music_disc_pigstep", "minecraft:elytra",
            "hexcraftmod:abyssium_ingot", "hexcraftmod:eclipsium_ingot",
            "hexcraftmod:corrupted_nether_star", "hexcraftmod:soulstone_ingot",
            "hexcraftmod:diamond_vapour", "hexcraftmod:lilith_contract", "hexcraftmod:lilith_soul",
            "hexcraftmod:attuned_stone_charged", "hexcraftmod:blooded_waystone",
            "hexcraftmod:breath_of_the_goddess", "hexcraftmod:drop_of_luck"
    );



    private int animationTicks = 0;
    private boolean isOpening = false;

    private int currentRound = 0;
    private int totalRounds = 5 + new Random().nextInt(6); // ✅ Random rounds between 5 and 10

    private int roundCooldown = 100;

    private long cooldownEndTime = 0;
    private UUID activePlayerUUID = null; // Track player for failure detection

    private boolean warnedAboutMobs = false; // ✅ Prevents repeated warnings

    private final ServerBossEvent bossBar = new ServerBossEvent(
            Component.literal("Pandora’s Box Event"),
            BossEvent.BossBarColor.PURPLE,
            BossEvent.BossBarOverlay.PROGRESS
    );

    public PandorasBoxBlockEntity(BlockPos pos, BlockState state) {
        super(HexcraftBlockEntities.PANDORAS_BOX_ENTITY.get(), pos, state);

    }

    public void activatePandorasBox(Level level, BlockPos pos) {
        if (!level.isClientSide) {
            int radius = 3; // Spread radius
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos targetPos = pos.offset(x, 0, z);
                    BlockState targetState = level.getBlockState(targetPos);

                    // Replace only dirt or grass-like blocks
                    if (targetState.is(Blocks.GRASS_BLOCK) || targetState.is(Blocks.DIRT) || targetState.is(HexcraftBlocks.VILE_GRASS_BLOCK.get())||
                            targetState.is(HexcraftBlocks.VILE_DIRT.get()) || targetState.is(Blocks.FARMLAND)) {
                        level.setBlock(targetPos, HexcraftBlocks.CURSED_SOIL.get().defaultBlockState(), 3);
                    }
                }
            }
        }
    }

    public void startOpeningAnimation(Player player) {
        if (isOpening) return; // ✅ Prevents multiple triggers

        // ✅ Prevent using Pandora’s Box if the world is set to Peaceful difficulty (unless in Creative)
        if (!player.isCreative() && level.getDifficulty() == Difficulty.PEACEFUL) {
            player.sendSystemMessage(Component.literal("❌ Pandora’s Box cannot be used in Peaceful mode!")
                    .withStyle(ChatFormatting.RED, ChatFormatting.BOLD));
            return; // ❌ Stops execution
        }

// ✅ Prevent usage if still on cooldown (Creative mode bypasses this)
        if (!player.isCreative() && System.currentTimeMillis() < cooldownEndTime) {
            long remainingTime = (cooldownEndTime - System.currentTimeMillis()) / 1000;
            player.sendSystemMessage(Component.literal("⏳ Pandora’s Box is still recharging! Try again in " + remainingTime + " seconds.")
                    .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
            return;
        }

        // ✅ Set Opening State to TRUE to properly start the event
        isOpening = true;
        animationTicks = 0;
        setCooldown();
        activePlayerUUID = player.getUUID();
        setChanged();

        spawnOpeningParticles();
        broadcastMessage("⚡ Pandora's Box has been opened! The darkness spreads...", ChatFormatting.DARK_PURPLE);

        activatePandorasBox(level, worldPosition); // 💀 This ensures the land turns into Cursed Soil!

        // ✅ Make it night when Pandora's Box opens
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.setDayTime(13000); // ✅ Set time to NIGHT
            broadcastMessage("🌙 The sky darkens as Pandora’s Box is unleashed!", ChatFormatting.DARK_BLUE);
        }

        // ✅ Check if a Good Event Should Happen Instead of a Battle
        if (new Random().nextDouble() < HexcraftConfig.PANDORA_GOOD_EVENT_CHANCE.get()) {
            triggerGoodEvent();
            return;
        }

        // ✅ Otherwise, Start the Normal Battle Rounds
        currentRound = 0;
        totalRounds = 3 + new Random().nextInt(5);
        roundCooldown = 100;
    }


    private void triggerGoodEvent() {
        if (level == null || level.isClientSide) return;

        Random random = new Random();
        int eventRoll = random.nextInt(4); // Pick a random good event

        switch (eventRoll) {
            case 0 -> {
                spawnTieredRewardChest();
                broadcastMessage("🎁 A treasure chest appears!", ChatFormatting.GOLD);
            }
            case 1 -> {
                applyPositiveEffects();
                broadcastMessage("✨ You feel a surge of power!", ChatFormatting.AQUA);
            }
            case 2 -> {
                grantXPReward();
                broadcastMessage("🟢 You gain mystical knowledge!", ChatFormatting.GREEN);
            }
            case 3 -> {
                spawnLuckyDrop();
                broadcastMessage("💎 Pandora’s Box grants you riches!", ChatFormatting.BLUE);
            }
        }

        // ✅ Ensure player still gets rewards when a good event happens
        givePlayerReward();

        setCooldown();
    }

    private void applyPositiveEffects() {
        Player player = level.getNearestPlayer(worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), 10, false);
        if (player == null) return;

        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1)); // 30 sec Regen
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0)); // 30 sec Strength
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 1)); // 30 sec Speed
    }

    private void grantXPReward() {
        Player player = level.getNearestPlayer(worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), 10, false);
        if (player == null) return;

        player.giveExperiencePoints(500 + new Random().nextInt(500)); // 500-1000 XP
    }

    private void spawnLuckyDrop() {
        Player player = level.getNearestPlayer(worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), 10, false);
        if (player == null) return;

        Random random = new Random();
        ItemStack drop;

        if (random.nextBoolean()) {
            drop = new ItemStack(Items.DIAMOND, 2 + random.nextInt(3)); // 2-4 Diamonds
        } else {
            drop = new ItemStack(Items.EMERALD, 3 + random.nextInt(4)); // 3-6 Emeralds
        }

        ItemEntity entity = new ItemEntity(level, player.getX(), player.getY() + 1, player.getZ(), drop);
        level.addFreshEntity(entity);
    }

    private void setCooldown() {
        int cooldownSeconds = HexcraftConfig.PANDORA_COOLDOWN.get();
        cooldownEndTime = System.currentTimeMillis() + (cooldownSeconds * 1000L);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, PandorasBoxBlockEntity entity) {
        if (!entity.isOpening) return;

        entity.checkForFailure();

        if (!entity.isOpening || entity.roundCooldown == -1) return;

        if (entity.arePandoraMobsAlive()) {
            return;
        }

        entity.animationTicks++;

        if (entity.animationTicks >= 40) {
            entity.animationTicks = 0;
            entity.startNextRound();
            entity.setChanged();
        } else if (entity.currentRound < entity.totalRounds) {
            entity.roundCooldown--;

            if (entity.roundCooldown <= 0) {
                entity.startNextRound();
                entity.roundCooldown = 100;
                entity.setChanged();
            }
        }

        if (entity.currentRound > entity.totalRounds) {
            entity.isOpening = false;
        }

        // ✅ Show progress in boss bar
        if (entity.totalRounds > 0) {
            float progress = Math.min(1.0f, (float) entity.currentRound / (float) entity.totalRounds);
            entity.bossBar.setProgress(progress);
        }

        entity.checkForFailure();
    }

    public int getAnimationTicks() {
        return animationTicks;
    }

    private void startNextRound() {
        if (level == null || level.isClientSide) return;

        // ✅ Ensure all previous mobs are dead before continuing
        if (arePandoraMobsAlive()) {
            if (!warnedAboutMobs) {
                broadcastMessage("⚠️ You must defeat all spawned mobs before advancing!", ChatFormatting.RED);
                warnedAboutMobs = true;
            }
            return;
        }
        warnedAboutMobs = false;

        currentRound++;

        // ✅ Final Round Handling
        boolean isFinalRound = (currentRound == totalRounds);

        if (isFinalRound) {
            broadcastMessage("🔥 **Final Round!** Survive this last wave!", ChatFormatting.DARK_RED);

            if (new Random().nextDouble() < 0.10) { // 10% chance to spawn a Mini-Boss
                spawnMiniBoss();
            }
        }

        // ✅ If the event has ended, check for victory
        if (currentRound > totalRounds) {
            if (arePandoraMobsAlive()) {
                if (!warnedAboutMobs) {
                    broadcastMessage("⚠️ You must defeat all remaining mobs before receiving rewards!", ChatFormatting.RED);
                    warnedAboutMobs = true;
                }
                return; // ❌ Do NOT spawn the chest yet!
            }

            warnedAboutMobs = false; // ✅ Reset warning flag when mobs are gone

            broadcastMessage("🎉 You have survived Pandora’s Box! Your rewards await!", ChatFormatting.GOLD);
            spawnTieredRewardChest();

            // ✅ Return to daytime after event
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.setDayTime(0);
                broadcastMessage("☀️ The sun rises once more!", ChatFormatting.YELLOW);
            }

            isOpening = false; // ✅ Stops the event properly
            return;
        }

        boolean isEliteRound = (currentRound % 3 == 0);
        if (isEliteRound) {
            broadcastMessage("⚠️ **Elite Round!** Enemies are stronger!", ChatFormatting.GOLD);
        }

        // ✅ Notify player of current round
        broadcastMessage("⚔ Round " + currentRound + " of " + totalRounds + " begins!", ChatFormatting.DARK_PURPLE);

        // ✅ Trigger a random arena effect every 2 rounds
        if (currentRound % 2 == 0) {
            triggerRandomEffect();
        }


        Random random = new Random();
        int baseSpawnCount = 6;
        int extraMobs = (int) Math.floor(currentRound * 1.5);
        int spawnCount = Math.min(baseSpawnCount + extraMobs, 20);

        Player nearestPlayer = level.getNearestPlayer(worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), 10, false);
        if (nearestPlayer == null) return;

        for (int i = 0; i < spawnCount; i++) {
            EntityType<?> entityType = selectRandomMob();
            Entity entity = entityType.create(level);

            if (entity instanceof Mob mobEntity) {
                // ✅ Spawn mobs 3-6 blocks away from the player
                double angle = random.nextDouble() * Math.PI * 2;
                double distance = 3 + random.nextDouble() * 3;
                int spawnX = Mth.floor(nearestPlayer.getX() + Math.cos(angle) * distance);
                int spawnZ = Mth.floor(nearestPlayer.getZ() + Math.sin(angle) * distance);
                int spawnY = level.getHeight(Heightmap.Types.WORLD_SURFACE, spawnX, spawnZ);

                mobEntity.setPos(spawnX + 0.5, spawnY, spawnZ + 0.5);
                mobEntity.setCustomName(Component.literal(getRandomPandoraMobName()).withStyle(ChatFormatting.DARK_RED, ChatFormatting.BOLD));
                mobEntity.setCustomNameVisible(true);
                mobEntity.getPersistentData().putBoolean("Hexcraft_PandorasMob", true);

                if (isEliteRound) {
                    mobEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 1));
                    mobEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 1));

                    if (mobEntity instanceof Warden warden) {
                        warden.setPersistenceRequired(); // ✅ Prevents despawning
                        warden.setCustomName(Component.literal("The Abyssal Horror").withStyle(ChatFormatting.DARK_RED, ChatFormatting.BOLD));
                        warden.setCustomNameVisible(true);

                        // ✅ Force the Warden to be permanently aggressive
                        warden.getBrain().setMemory(MemoryModuleType.ANGRY_AT, nearestPlayer.getUUID());
                        warden.getBrain().setMemory(MemoryModuleType.ATTACK_TARGET, nearestPlayer); // ✅ Forces attack behavior
                        warden.setAggressive(true);
                        warden.setTarget(nearestPlayer);

                        // ✅ Remove Burrowing-related Memories
                        warden.getBrain().eraseMemory(MemoryModuleType.DIG_COOLDOWN);  // Prevents Digging cooldown
                        warden.getBrain().eraseMemory(MemoryModuleType.ROAR_TARGET);   // Stops from roaring instead of fighting
                        warden.getBrain().eraseMemory(MemoryModuleType.DISTURBANCE_LOCATION); // Keeps it aggressive
                        warden.getBrain().eraseMemory(MemoryModuleType.SONIC_BOOM_COOLDOWN); // Reset Sonic Boom cooldown

                        // ✅ "Trick" the Warden into **never calming down** by keeping its anger high
                        warden.increaseAngerAt(nearestPlayer, 200, true); // 🔥 Makes the Warden **rage instantly**
                        warden.getPersistentData().putInt("AngerLevel", 200); // ✅ Keeps it in attack mode

                        // ✅ Optional: Reset behavior every tick (Ensures it doesn't get a chance to dig)
                        warden.setDeltaMovement(0, 0, 0); // Stops movement resets
                    }


                    // ✅ Ensure mobs spawn with correct weapons
                    if (mobEntity instanceof Skeleton skeleton) {
                        skeleton.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.BOW));
                        skeleton.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
                    }
                    if (mobEntity instanceof WitherSkeleton witherSkeleton) {
                        witherSkeleton.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.STONE_SWORD));
                        witherSkeleton.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
                    }
                    if (mobEntity instanceof Stray stray) {
                        stray.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.BOW));
                        stray.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
                    }
                    if (mobEntity instanceof Piglin piglin) {
                        if (random.nextBoolean()) {
                            piglin.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.GOLDEN_SWORD));
                        } else {
                            piglin.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.CROSSBOW));
                        }
                        piglin.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
                    }
                    if (mobEntity instanceof PiglinBrute brute) {
                        brute.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.GOLDEN_AXE));
                        brute.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
                    }
                    if (mobEntity instanceof ZombifiedPiglin zombifiedPiglin) {
                        zombifiedPiglin.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.GOLDEN_SWORD));
                        zombifiedPiglin.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
                    }
                    if (mobEntity instanceof Vindicator vindicator) {
                        vindicator.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.IRON_AXE));
                        vindicator.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
                    }
                    if (mobEntity instanceof Pillager pillager) {
                        pillager.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.CROSSBOW));
                        pillager.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
                    }
                    if (mobEntity instanceof Illusioner illusioner) {
                        illusioner.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.BOW));
                        illusioner.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
                    }

                    level.addFreshEntity(mobEntity);
                }

                level.addFreshEntity(mobEntity);
            }
        }

        if (HexcraftConfig.ENABLE_CHAOS_MODE.get()) {
            triggerChaosMode();
        }

        if (currentRound < totalRounds) {
            roundCooldown = 100;
        }

        // ✅ 30% chance of applying a battle effect
        applyRandomBattleEffects();
    }

    private static final List<String> MINI_BOSSES = List.of(
            "The Bone Tyrant",   // Buffed skeleton with minions
            "The Cursed Warlock", // Spellcaster, summons Vex
            "The Void Revenant"   // Teleporting Enderman-type boss
    );

    private void triggerRandomEffect() {
        if (level == null || level.isClientSide) return;

        Random random = new Random();
        int effect = random.nextInt(5);

        switch (effect) {
            case 0 -> {
                broadcastMessage("⚡ A lightning storm begins!", ChatFormatting.AQUA);
                for (int i = 0; i < 5; i++) {
                    BlockPos strikePos = worldPosition.offset(random.nextInt(10) - 5, 0, random.nextInt(10) - 5);
                    if (level instanceof ServerLevel serverLevel) {
                        LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(serverLevel);
                        if (lightningBolt != null) {
                            lightningBolt.moveTo(Vec3.atCenterOf(strikePos));
                            serverLevel.addFreshEntity(lightningBolt);
                        }
                    }
                }
            }
            case 1 -> broadcastMessage("👁️ A dark fog surrounds you!", ChatFormatting.DARK_GRAY);
            case 2 -> broadcastMessage("💀 Creatures become hostile!", ChatFormatting.DARK_RED);
            case 3 -> broadcastMessage("🌪️ Gravity shifts unexpectedly!", ChatFormatting.LIGHT_PURPLE);
            case 4 -> broadcastMessage("🔥 Fire rains from the heavens!", ChatFormatting.GOLD);
        }
    }


    private void applyEffectToPlayers() {
        if (level == null || level.isClientSide) return;

        List<MobEffect> effects = List.of(
                MobEffects.BLINDNESS,  // 👁️ Dark fog
                MobEffects.WEAKNESS,   // 💀 Player feels weak
                MobEffects.LEVITATION, // 🌪️ Gravity shift
                MobEffects.SLOW_FALLING, // 🌬️ Slow motion effect
                MobEffects.MOVEMENT_SPEED       // ⚡ Sudden speed burst
        );

        Random random = new Random();
        MobEffect selectedEffect = effects.get(random.nextInt(effects.size()));
        int duration = 200 + random.nextInt(400); // ⏳ Duration between 10-30 seconds

        // Apply effect to all players nearby
        for (Player player : level.players()) {
            if (player.distanceToSqr(Vec3.atCenterOf(worldPosition)) <= 100) { // ✅ Only affect nearby players
                player.addEffect(new MobEffectInstance(selectedEffect, duration, 0));
            }
        }

        broadcastMessage("🌀 A strange force affects you...", ChatFormatting.DARK_PURPLE);

    }

    private boolean arePandoraMobsAlive() {
        for (Entity entity : level.getEntities(null, new AABB(worldPosition).inflate(15))) {
            if (entity instanceof Mob mobEntity && mobEntity.getPersistentData().getBoolean("Hexcraft_PandorasMob")) {
                return true; // ✅ Mobs are still alive
            }
        }
        return false; // ✅ All mobs are dead
    }


    private void spawnMiniBoss() {
        if (level == null || level.isClientSide) return;

        Random random = new Random();
        String bossName = MINI_BOSSES.get(random.nextInt(MINI_BOSSES.size()));

        EntityType<?> bossType = EntityType.WARDEN; // ✅ Change this to another boss if needed
        Entity boss = bossType.create(level);

        if (boss instanceof Mob mobEntity) {
            mobEntity.setPos(worldPosition.getX(), worldPosition.getY() + 1, worldPosition.getZ());
            mobEntity.setCustomName(Component.literal(bossName).withStyle(ChatFormatting.DARK_RED, ChatFormatting.BOLD));
            mobEntity.setCustomNameVisible(true);
            mobEntity.getPersistentData().putBoolean("Hexcraft_PandorasMob", true);

            // ✅ Apply Mini-Boss Buffs
            mobEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 2)); // Strength III for 1 minute
            mobEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 1200, 1)); // Regeneration II for 1 minute
            mobEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 1)); // Speed II for 1 minute

            level.addFreshEntity(mobEntity);
        }

        broadcastMessage("💀 " + bossName + " has risen from the darkness!", ChatFormatting.RED);
    }



    private void triggerChaosMode() {
        if (level == null || level.isClientSide) return;

        Random random = new Random();

        // ✅ Check if Chaos Mode should activate (based on config chance)
        if (!HexcraftConfig.ENABLE_CHAOS_MODE.get() || random.nextDouble() >= HexcraftConfig.PANDORA_CHAOS_MODE_CHANCE.get()) {
            return; // ❌ Chaos Mode does NOT activate
        }

        // ✅ Select a random effect (only one happens)
        int effect = random.nextInt(4);
        switch (effect) {
            case 0 -> triggerRandomEffect(); // ⚡ Special world effect
            case 1 -> spawnMiniBoss(); // 💀 Spawn a mini-boss
            case 2 -> spawnTieredRewardChest(); // 🎁 Spawn an extra reward chest
            case 3 -> applyEffectToPlayers(); // 🌀 Apply a status effect
        }

        broadcastMessage("⚡ Chaos Mode has been activated! Expect the unexpected!", ChatFormatting.LIGHT_PURPLE);
    }

    private void spawnTieredRewardChest() {
        if (level == null || level.isClientSide) return;

        Random random = new Random();
        double roll = random.nextDouble();

        List<String> selectedLoot;
        String chestTier;

        if (roll < HexcraftConfig.LEGENDARY_CHEST_CHANCE.get()) {
            selectedLoot = LEGENDARY_CHEST_LOOT;
            chestTier = "LEGENDARY";
        } else if (roll < HexcraftConfig.RARE_CHEST_CHANCE.get()) {
            selectedLoot = RARE_CHEST_LOOT;
            chestTier = "RARE";
        } else if (roll < HexcraftConfig.UNCOMMON_CHEST_CHANCE.get()) {
            selectedLoot = UNCOMMON_CHEST_LOOT;
            chestTier = "UNCOMMON";
        } else {
            selectedLoot = COMMON_CHEST_LOOT;
            chestTier = "COMMON";
        }

        BlockPos chestPos = worldPosition.offset(1, 0, 1);
        level.setBlockAndUpdate(chestPos, Blocks.CHEST.defaultBlockState());

        BlockEntity blockEntity = level.getBlockEntity(chestPos);
        if (blockEntity instanceof ChestBlockEntity chest) {
            for (int i = 0; i < 3 + random.nextInt(4); i++) {
                String rewardId = selectedLoot.get(random.nextInt(selectedLoot.size()));
                Item rewardItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(rewardId));

                if (rewardItem != null && rewardItem != Items.AIR) {
                    ItemStack stack = new ItemStack(rewardItem, 1); // ✅ Default initialization

                    if (rewardItem == Items.ENCHANTED_BOOK) {
                        stack = getRandomEnchantedBook(chestTier); // ✅ Ensure enchanted book gets an enchantment
                    } else {
                        stack.setCount(1 + random.nextInt(2)); // ✅ Assign correct stack count
                    }

                    chest.setItem(random.nextInt(chest.getContainerSize()), stack);
                }
            }
        }

        broadcastMessage("📦 A " + chestTier + " Reward Chest has spawned! Open it for rewards!", ChatFormatting.GOLD);
    }


    private void givePlayerReward() {
        if (level == null || level.isClientSide) return;

        Player nearestPlayer = level.getNearestPlayer(worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), 10, false);
        if (nearestPlayer == null) return;

        broadcastMessage("🎉 You have survived Pandora’s Box! Rewards await!", ChatFormatting.GOLD);
    }


    private ItemStack getRandomEnchantedBook(String chestTier) {
        Random random = new Random();
        ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);

        List<Enchantment> possibleEnchantments;

        switch (chestTier) {
            case "COMMON":
                possibleEnchantments = List.of(
                        Enchantments.BLOCK_EFFICIENCY, Enchantments.SHARPNESS, Enchantments.ALL_DAMAGE_PROTECTION,
                        Enchantments.UNBREAKING, Enchantments.POWER_ARROWS, Enchantments.PUNCH_ARROWS
                );
                break;
            case "UNCOMMON":
                possibleEnchantments = List.of(
                        Enchantments.BLOCK_FORTUNE, Enchantments.MOB_LOOTING, Enchantments.FISHING_LUCK,
                        Enchantments.FALL_PROTECTION, Enchantments.FIRE_ASPECT, Enchantments.RESPIRATION
                );
                break;
            case "RARE":
                possibleEnchantments = List.of(
                        Enchantments.SHARPNESS, Enchantments.ALL_DAMAGE_PROTECTION, Enchantments.BLOCK_EFFICIENCY,
                        Enchantments.SOUL_SPEED, Enchantments.MULTISHOT, Enchantments.DEPTH_STRIDER
                );
                break;
            case "LEGENDARY":
                possibleEnchantments = List.of(
                        Enchantments.MENDING, Enchantments.SHARPNESS, Enchantments.INFINITY_ARROWS,
                        Enchantments.MOB_LOOTING, Enchantments.SILK_TOUCH, Enchantments.FROST_WALKER
                );
                break;
            default:
                possibleEnchantments = List.of(Enchantments.UNBREAKING); // Fallback
        }

        // ✅ Select a random enchantment from the list
        Enchantment selectedEnchantment = possibleEnchantments.get(random.nextInt(possibleEnchantments.size()));

        // ✅ Assign a random level (1 to max level)
        int level = 1 + random.nextInt(selectedEnchantment.getMaxLevel());

        // ✅ Apply enchantment to the book
        enchantedBook.enchant(selectedEnchantment, level);

        return enchantedBook;
    }

    private EntityType<?> selectRandomMob() {
        Random random = new Random();

        // ✅ Check if boss mobs are enabled and roll for Warden or Wither
        if (HexcraftConfig.ENABLE_BOSS_MOBS.get()) {
            if (random.nextDouble() < HexcraftConfig.PANDORA_WARDEN_SPAWN_CHANCE.get()) {
                return EntityType.WARDEN;
            }
            if (random.nextDouble() < HexcraftConfig.PANDORA_WITHER_SPAWN_CHANCE.get()) {
                return EntityType.WITHER;
            }
        }

        // ✅ Get list of mobs from config
        List<? extends String> mobList = HexcraftConfig.PANDORA_SPAWNABLE_MOBS.get();
        if (mobList.isEmpty()) {
            return EntityType.ZOMBIE; // Default fallback
        }

        // ✅ Randomly pick a mob from the config
        String randomMobId = mobList.get(random.nextInt(mobList.size()));
        EntityType<?> selectedMob = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(randomMobId));

        return selectedMob != null ? selectedMob : EntityType.ZOMBIE;
    }

    private void checkForFailure() {
        if (level == null || level.isClientSide || activePlayerUUID == null) return;

        Player player = level.getPlayerByUUID(activePlayerUUID);
        if (player == null || player.isDeadOrDying()) {
            broadcastMessage("☠ You have perished! Pandora’s Box remains undefeated...", ChatFormatting.RED);
            applyFailureEffects(player); // ✅ Apply negative effects on death
            despawnPandoraMobs(); // ✅ Force remove all spawned mobs
            isOpening = false;
            activePlayerUUID = null;
            currentRound = totalRounds;
            setCooldown();
            setChanged();
        } else {
            int maxRadius = HexcraftConfig.PANDORA_LEAVE_RADIUS.get();
            int maxDistanceSquared = maxRadius * maxRadius;

            if (player.distanceToSqr(Vec3.atCenterOf(worldPosition)) > maxDistanceSquared) {
                broadcastMessage("⚠️ The event was abandoned! Pandora’s Box remains restless...", ChatFormatting.RED);
                applyFailureEffects(player); // ✅ Apply punishment for leaving
                despawnPandoraMobs(); // ✅ Now actually removes all mobs
                grantConsolationPrize();
                isOpening = false;
                activePlayerUUID = null;
                currentRound = -1; // ✅ Prevents further rounds (makes event fail instead of skipping to "victory")
                setCooldown();
                setChanged();
            }
        }
    }

    private void failEvent(Player player) {
        if (player != null) {
            applyFailureEffects(player); // ✅ Apply punishment for leaving
        }

        despawnPandoraMobs(); // ✅ Remove all spawned mobs
        grantConsolationPrize();

        // ✅ Fully **stop** the event and prevent further rounds
        isOpening = false;
        activePlayerUUID = null;
        currentRound = totalRounds + 1; // ✅ Forces the event to register as **over**
        roundCooldown = -1; // ✅ Prevents the tick() method from advancing rounds
        setCooldown(); // ✅ Applies cooldown before the box can be used again
        setChanged();
    }

    private void despawnPandoraMobs() {
        if (level == null) return;

        for (Entity entity : level.getEntities(null, new AABB(worldPosition).inflate(30))) { // ✅ 30-block radius
            if (entity instanceof Mob mobEntity && mobEntity.getPersistentData().getBoolean("Hexcraft_PandorasMob")) {
                mobEntity.discard(); // ✅ Despawn mob
            }
        }
    }

    private void applyFailureEffects(Player player) {
        if (player == null) return;

        Random random = new Random();
        int effectRoll = random.nextInt(3);

        switch (effectRoll) {
            case 0 -> {
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 600, 1)); // 30 sec Weakness
                broadcastMessage("💀 You feel drained of your strength...", ChatFormatting.DARK_RED);
            }
            case 1 -> {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 600, 1)); // 30 sec Mining Fatigue
                broadcastMessage("⛏️ Your hands feel heavy...", ChatFormatting.GRAY);
            }
            case 2 -> {
                player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 400, 0)); // 20 sec Blindness
                broadcastMessage("👁️ Darkness engulfs you...", ChatFormatting.BLACK);
            }
        }
    }

    private void applyRandomBattleEffects() {
        if (level == null || level.isClientSide) return;

        Random random = new Random();
        if (random.nextDouble() > 0.30) return; // ✅ 30% chance to trigger effect

        Player player = level.getNearestPlayer(worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), 10, false);
        if (player == null) return;

        int effectRoll = random.nextInt(6);

        switch (effectRoll) {
            case 0 -> {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1)); // 20 sec Regen
                broadcastMessage("✨ A warm energy surrounds you!", ChatFormatting.AQUA);
            }
            case 1 -> {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 400, 0)); // 20 sec Strength
                broadcastMessage("⚔️ You feel a surge of power!", ChatFormatting.RED);
            }
            case 2 -> {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 1)); // 20 sec Speed
                broadcastMessage("💨 Your movements become swift!", ChatFormatting.GREEN);
            }
            case 3 -> {
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 400, 0)); // 20 sec Night Vision
                broadcastMessage("🌙 Your vision sharpens in the dark!", ChatFormatting.LIGHT_PURPLE);
            }
            case 4 -> {
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 400, 1)); // 20 sec Weakness
                broadcastMessage("💀 A dark force weakens you!", ChatFormatting.DARK_RED);
            }
            case 5 -> {
                player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 200, 0)); // 10 sec Levitation
                broadcastMessage("🌪️ The air lifts you up!", ChatFormatting.YELLOW);
            }
        }
    }

    private void grantConsolationPrize() {
        if (level == null || level.isClientSide) return;

        Player nearestPlayer = level.getNearestPlayer(worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), 10, false);
        if (nearestPlayer == null) return;

        broadcastMessage("😢 You abandoned Pandora’s Box, but you still receive a small gift.", ChatFormatting.GRAY);

        ItemStack drop = new ItemStack(Items.COAL, 1); // ✅ Small reward for trying
        ItemEntity entity = new ItemEntity(level, nearestPlayer.getX(), nearestPlayer.getY() + 1, nearestPlayer.getZ(), drop);
        level.addFreshEntity(entity);
    }

    private void spawnOpeningParticles() {
        if (level == null || level.isClientSide) return;

        ((ServerLevel) level).sendParticles(
                ParticleTypes.PORTAL,
                worldPosition.getX() + 0.5, worldPosition.getY() + 0.5, worldPosition.getZ() + 0.5,
                20,
                0.5, 0.5, 0.5,
                0.1
        );
    }

    @Mod.EventBusSubscriber(modid = "hexcraftmod", bus = Mod.EventBusSubscriber.Bus.FORGE)
    public class PandoraMobLootHandler {
        @SubscribeEvent
        public static void onMobDrop(LivingDropsEvent event) {
            Entity entity = event.getEntity();

            if (entity instanceof Mob mobEntity && mobEntity.getPersistentData().getBoolean("Hexcraft_PandorasMob")) {
                event.setCanceled(true); // ✅ Prevents drops
            }
        }
    }


    private String getRandomPandoraMobName() {
        List<? extends String> names = HexcraftConfig.PANDORA_MOB_NAMES.get();
        return names.get(new Random().nextInt(names.size()));
    }

    private void broadcastMessage(String message, ChatFormatting color) {
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.players().forEach(player ->
                    player.sendSystemMessage(Component.literal(message).withStyle(color, ChatFormatting.BOLD))
            );
        }
    }
}