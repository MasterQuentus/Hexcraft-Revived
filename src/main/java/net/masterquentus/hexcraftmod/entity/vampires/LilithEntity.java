package net.masterquentus.hexcraftmod.entity.vampires;

import net.masterquentus.hexcraftmod.entity.HexcraftEntities;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LilithEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private int bloodMoonCooldown = 0;
    private int darkVeilCooldown = 0;
    private int shadowDashCooldown = 0;
    private int darkDominionCooldown = 0; // Added cooldown
    private boolean isUsingDarkDominion = false;
    private boolean isSummoningMinions = false;
    private int abilityPhaseTimer = 0;
    private static final double MAX_FLOAT_HEIGHT = 12.0;
    private double startingY = -1; // to store the Y where boss started floating
    private int darkDominionRounds = 0; // count how many waves summoned
    private static final int MAX_DOMINION_ROUNDS = 5; // how many waves before phase ends

    private boolean isAttacking = false;
    private int attackTimer = 0;
    private int attackType = 1;

    // Transformation fields
    private boolean isTransformed = false;
    private int transformTimer = 0; // ticks remaining until Lilith returns
    private final List<LilithBat> activeBats = new ArrayList<>();
    private final List<Monster> darkDominionMinions = new ArrayList<>();

    // === Animations ===
    private static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("animation.lilith.walk");
    private static final RawAnimation ATTACK_ANIM = RawAnimation.begin().then("animation.lilith.attack", Animation.LoopType.DEFAULT);
    private static final RawAnimation ATTACK2_ANIM = RawAnimation.begin().then("animation.lilith.attack2", Animation.LoopType.DEFAULT);
    private static final RawAnimation ATTACK3_ANIM = RawAnimation.begin().then("animation.lilith.attack3", Animation.LoopType.DEFAULT);
    private static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.lilith.idle");

    public LilithEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 500;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.2, true));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.2D));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.5F));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Villager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Animal.class, true));
    }

    private final ServerBossEvent bossEvent = new ServerBossEvent(
            Component.translatable("boss.hexcraft.lilith"),
            BossEvent.BossBarColor.RED,
            BossEvent.BossBarOverlay.PROGRESS
    );

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 300.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.ATTACK_DAMAGE, 15.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 1.5D)
                .add(Attributes.ARMOR, 10.0D)
                .add(Attributes.ARMOR_TOUGHNESS, 8.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8D)
                .add(Attributes.FOLLOW_RANGE, 40.0D).build();
    }

    @Override
    public void tick() {
        super.tick();

        if (isUsingDarkDominion) {
            abilityPhaseTimer--;

            // Clean up dead or removed minions from the tracking list
            darkDominionMinions.removeIf(mob -> mob.isRemoved() || !mob.isAlive());

            // Smoothly float up to max height (startingY + MAX_FLOAT_HEIGHT)
            double targetY = startingY + MAX_FLOAT_HEIGHT;
            if (this.getY() < targetY) {
                // Gradually increase upward speed, capped at 0.2
                double currentYSpeed = getDeltaMovement().y();
                double newYSpeed = Math.min(currentYSpeed + 0.05, 0.2);
                setDeltaMovement(0, newYSpeed, 0);
            } else {
                // Reached max height: fix position and stop vertical movement
                setDeltaMovement(0, 0, 0);
                setPos(getX(), targetY, getZ());
            }

            // Prevent targeting and AI during phase
            this.setTarget(null);
            this.setNoAi(true);

            // Check if ready for next wave: timer expired and all minions dead
            if (abilityPhaseTimer <= 0 && darkDominionMinions.isEmpty()) {
                darkDominionRounds++;

                if (darkDominionRounds >= MAX_DOMINION_ROUNDS) {
                    // End Dark Dominion phase
                    isUsingDarkDominion = false;
                    this.setNoAi(false);
                    this.setInvulnerable(false);
                    darkDominionMinions.clear();
                } else {
                    // Spawn next wave and reset timer
                    spawnDarkDominionWave();
                    abilityPhaseTimer = 100 + random.nextInt(60);
                    startAttackAnimation(2);
                    playAttackSound(2);
                }
            }

            return; // skip other tick logic while in Dark Dominion phase
        }

        // === Transformed into bats ===
        if (isTransformed) {
            transformTimer--;
            if (transformTimer <= 0 || activeBats.isEmpty()) {
                revertFromBatForm();
            }
            return; // Skip other behavior while transformed
        }

        // === Normal combat logic ===
        bossEvent.setProgress(this.getHealth() / this.getMaxHealth());

        if (bloodMoonCooldown > 0) bloodMoonCooldown--;
        if (darkVeilCooldown > 0) darkVeilCooldown--;
        if (shadowDashCooldown > 0) shadowDashCooldown--;
        if (darkDominionCooldown > 0) darkDominionCooldown--;
        if (attackTimer > 0) attackTimer--;
        else isAttacking = false;

        // Only trigger abilities if NOT in Dark Dominion or Bat Swarm phase:
        if (!isUsingDarkDominion && !isTransformed) {
            if (bloodMoonCooldown <= 0 && getHealth() < getMaxHealth() * 0.9) {
                applyBloodMoonCurse(this);
                bloodMoonCooldown = 1200;
                broadcastAbility("Blood Moon Curse");
            }
            if (getHealth() < getMaxHealth() * 0.7 && darkDominionCooldown <= 0 && random.nextInt(10) == 0) {
                applyDarkDominion(this);
                darkDominionCooldown = 1200;
            }
            if (getHealth() < getMaxHealth() * 0.6 && random.nextInt(250) == 0) {
                triggerBatSwarm(this);
                broadcastAbility("Bat Swarm");
            }
            if (getHealth() < getMaxHealth() * 0.5 && random.nextInt(200) == 0) {
                summonVampireMinions(this);
                broadcastAbility("Summon Vampire Minions");
            }
            if (shadowDashCooldown <= 0 && getHealth() < getMaxHealth() * 0.4) {
                shadowDash(this);
                shadowDashCooldown = 600;
                broadcastAbility("Shadow Dash");
            }
            if (darkVeilCooldown <= 0 && getHealth() < getMaxHealth() * 0.3) {
                triggerDarkVeil(this);
                darkVeilCooldown = 1800;
                broadcastAbility("Dark Veil");
            }
        }
    }

    private void broadcastAbility(String abilityName) {
        if (!level().isClientSide) {
            // Fancy colored chat message
            Component msg = Component.empty()
                    .append(Component.literal("[Lilith] ").withStyle(ChatFormatting.DARK_RED, ChatFormatting.BOLD))
                    .append(Component.literal("uses ").withStyle(ChatFormatting.GRAY))
                    .append(Component.literal(abilityName).withStyle(ChatFormatting.RED, ChatFormatting.BOLD))
                    .append(Component.literal("!").withStyle(ChatFormatting.GRAY));
            for (Player player : level().players()) {
                player.sendSystemMessage(msg);
            }
        }
    }

    private Player getNearestPlayer() {
        return level().getNearestPlayer(this, 10.0D);
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        bossEvent.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        bossEvent.removePlayer(player);
    }

    private void applyBloodMoonCurse(LivingEntity entity) {
        if (!level().isClientSide && bloodMoonCooldown == 0) {
            for (Player player : level().players()) {
                player.addEffect(new MobEffectInstance(MobEffects.WITHER, 400, 1));
                player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 300, 1));
            }
            bloodMoonCooldown = 1200;
        }
    }

    private void triggerBatSwarm(LivingEntity entity) {
        if (!level().isClientSide && !isTransformed) {
            for (int i = 0; i < 5; i++) {
                LilithBat bat = new LilithBat(level(), this);
                bat.moveTo(getX() + random.nextDouble() - 0.5, getY() + random.nextDouble() * 0.5, getZ() + random.nextDouble() - 0.5);
                level().addFreshEntity(bat);
                activeBats.add(bat);
            }

            isTransformed = true;
            transformTimer = 200;
            this.setInvisible(true);
            this.setNoAi(true);
            this.setSilent(true);
            this.setInvulnerable(true);
            this.setDeltaMovement(0, 0, 0);
            this.setTarget(null);  // clear target to prevent attacks

            playSound(SoundEvents.BAT_TAKEOFF, 1.0F, 1.0F);
            startAttackAnimation(3);
            playAttackSound(3);
        }
    }

    private void revertFromBatForm() {
        for (LilithBat bat : activeBats) {
            if (bat != null && !bat.isRemoved()) {
                bat.discard();
            }
        }
        activeBats.clear();

        isTransformed = false;
        this.setInvisible(false);
        this.setNoAi(false);
        this.setSilent(false);
        this.setInvulnerable(false);

        playSound(SoundEvents.BAT_AMBIENT, 1.0F, 1.0F);
    }

    public void notifyBatDeath(LilithBat bat) {
        activeBats.remove(bat);
        if (isTransformed && activeBats.isEmpty()) {
            revertFromBatForm();
        }
    }

    public static class LilithBat extends Bat {
        private int life = 200;
        private final LilithEntity owner;

        public LilithBat(Level level, LilithEntity owner) {
            super(EntityType.BAT, level);
            this.owner = owner;
        }

        @Override
        public boolean requiresCustomPersistence() {
            return false;
        }

        @Override
        public void tick() {
            super.tick();
            if (!level().isClientSide) {
                life--;
                if (life <= 0) discard();
            }
        }

        @Override
        public void die(DamageSource cause) {
            super.die(cause);
            if (!level().isClientSide && owner != null) {
                owner.notifyBatDeath(this);
            }
        }
    }

    // Dark Dominion spawns hostile skeleton minions with bows & targets players
    private void applyDarkDominion(LivingEntity entity) {
        if (!level().isClientSide && !isUsingDarkDominion) {
            isUsingDarkDominion = true;
            startingY = this.getY();

            this.setNoAi(true);
            this.setInvulnerable(true);
            this.setTarget(null);
            setDeltaMovement(0, 0.2, 0); // float up slightly
            broadcastAbility("Dark Dominion");

            darkDominionMinions.clear();

            spawnDarkDominionWave();

            startAttackAnimation(2);
            playAttackSound(2);
        }
    }

    private void equipMobGear(Monster mob) {
        if (mob instanceof Witch) return; // Witches get no gear

        EquipmentSlot[] slots = {
                EquipmentSlot.HEAD, EquipmentSlot.CHEST,
                EquipmentSlot.LEGS, EquipmentSlot.FEET
        };

        Item[] helmets = {
                Items.LEATHER_HELMET, Items.CHAINMAIL_HELMET, Items.IRON_HELMET,
                Items.GOLDEN_HELMET, Items.DIAMOND_HELMET, Items.NETHERITE_HELMET,
                HexcraftItems.STEEL_HELMET.get(), HexcraftItems.DARK_STEEL_HELMET.get(),
                HexcraftItems.BLOODY_NYKIUM_HELMET.get(), HexcraftItems.JORMIUM_HELMET.get(),
                HexcraftItems.CUROGEN_HELMET.get(), HexcraftItems.SILVER_HELMET.get(),
                HexcraftItems.VAMPIRIC_HELMET.get(), HexcraftItems.DEEPSEER_HELM.get()
        };
        Item[] chestplates = {
                Items.LEATHER_CHESTPLATE, Items.CHAINMAIL_CHESTPLATE, Items.IRON_CHESTPLATE,
                Items.GOLDEN_CHESTPLATE, Items.DIAMOND_CHESTPLATE, Items.NETHERITE_CHESTPLATE,
                HexcraftItems.STEEL_CHESTPLATE.get(), HexcraftItems.DARK_STEEL_CHESTPLATE.get(),
                HexcraftItems.BLOODY_NYKIUM_CHESTPLATE.get(), HexcraftItems.JORMIUM_CHESTPLATE.get(),
                HexcraftItems.CUROGEN_CHESTPLATE.get(), HexcraftItems.SILVER_CHESTPLATE.get(),
                HexcraftItems.VAMPIRIC_CHESTPLATE.get(), HexcraftItems.DEEPSEER_CHESTPLATE.get()
        };
        Item[] leggings = {
                Items.LEATHER_LEGGINGS, Items.CHAINMAIL_LEGGINGS, Items.IRON_LEGGINGS,
                Items.GOLDEN_LEGGINGS, Items.DIAMOND_LEGGINGS, Items.NETHERITE_LEGGINGS,
                HexcraftItems.STEEL_LEGGING.get(), HexcraftItems.DARK_STEEL_LEGGING.get(),
                HexcraftItems.BLOODY_NYKIUM_LEGGING.get(), HexcraftItems.JORMIUM_LEGGING.get(),
                HexcraftItems.CUROGEN_LEGGING.get(), HexcraftItems.SILVER_LEGGING.get(),
                HexcraftItems.VAMPIRIC_LEGGING.get(), HexcraftItems.DEEPSEER_LEGGING.get()
        };
        Item[] boots = {
                Items.LEATHER_BOOTS, Items.CHAINMAIL_BOOTS, Items.IRON_BOOTS,
                Items.GOLDEN_BOOTS, Items.DIAMOND_BOOTS, Items.NETHERITE_BOOTS,
                HexcraftItems.STEEL_BOOTS.get(), HexcraftItems.DARK_STEEL_BOOTS.get(),
                HexcraftItems.BLOODY_NYKIUM_BOOTS.get(), HexcraftItems.JORMIUM_BOOTS.get(),
                HexcraftItems.CUROGEN_BOOTS.get(), HexcraftItems.SILVER_BOOTS.get(),
                HexcraftItems.VAMPIRIC_BOOTS.get(), HexcraftItems.DEEPSEER_BOOTS.get()
        };

        Item[][] gearSets = { helmets, chestplates, leggings, boots };

        for (int s = 0; s < slots.length; s++) {
            Item gear = gearSets[s][random.nextInt(gearSets[s].length)];
            mob.setItemSlot(slots[s], new ItemStack(gear));

            boolean isRare = gear == Items.DIAMOND_HELMET || gear == Items.NETHERITE_HELMET
                    || gear == Items.DIAMOND_CHESTPLATE || gear == Items.NETHERITE_CHESTPLATE
                    || gear == Items.DIAMOND_LEGGINGS || gear == Items.NETHERITE_LEGGINGS
                    || gear == Items.DIAMOND_BOOTS || gear == Items.NETHERITE_BOOTS
                    || gear == HexcraftItems.BLOODY_NYKIUM_HELMET.get()
                    || gear == HexcraftItems.BLOODY_NYKIUM_CHESTPLATE.get()
                    || gear == HexcraftItems.BLOODY_NYKIUM_LEGGING.get()
                    || gear == HexcraftItems.BLOODY_NYKIUM_BOOTS.get()
                    || gear == HexcraftItems.DARK_STEEL_HELMET.get()
                    || gear == HexcraftItems.DARK_STEEL_CHESTPLATE.get()
                    || gear == HexcraftItems.DARK_STEEL_LEGGING.get()
                    || gear == HexcraftItems.DARK_STEEL_BOOTS.get();

            mob.setDropChance(slots[s], isRare ? 0.1F : 0.5F);
        }

        Item[] weapons = {
                Items.WOODEN_SWORD,
                Items.STONE_SWORD,
                Items.IRON_SWORD,
                Items.GOLDEN_SWORD,
                Items.DIAMOND_SWORD,
                Items.NETHERITE_SWORD,
                HexcraftItems.DEEPSEER_SWORD.get(),
                HexcraftItems.STEEL_SWORD.get(),
                HexcraftItems.BLOODY_NYKIUM_SWORD.get(),
                HexcraftItems.JORMIUM_SWORD.get(),
                HexcraftItems.CUROGEN_SWORD.get(),
                HexcraftItems.SILVER_SWORD.get(),
                HexcraftItems.VAMPIRIC_SWORD.get(),

                Items.WOODEN_AXE,
                Items.STONE_AXE,
                Items.IRON_AXE,
                Items.GOLDEN_AXE,
                Items.DIAMOND_AXE,
                Items.NETHERITE_AXE,
                HexcraftItems.DEEPSEER_AXE.get(),
                HexcraftItems.STEEL_AXE.get(),
                HexcraftItems.BLOODY_NYKIUM_AXE.get(),
                HexcraftItems.JORMIUM_AXE.get(),
                HexcraftItems.CUROGEN_AXE.get(),
                HexcraftItems.SILVER_AXE.get(),
                HexcraftItems.VAMPIRIC_AXE.get(),

                Items.BOW,
                Items.CROSSBOW,
                Items.TRIDENT,
                HexcraftItems.VAMPIRIC_STAFF.get(),
                HexcraftItems.STEEL_BOW.get(),
                HexcraftItems.DARK_STEEL_BOW.get(),
                HexcraftItems.BLOODY_NYKIUM_BOW.get(),
                HexcraftItems.JORMIUM_BOW.get(),
                HexcraftItems.CUROGEN_BOW.get()
        };

        Item weapon = weapons[random.nextInt(weapons.length)];
        mob.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(weapon));
        mob.setDropChance(EquipmentSlot.MAINHAND, 0.5f);
    }


    private void spawnDarkDominionWave() {
        AABB area = new AABB(blockPosition()).inflate(12);
        List<Player> nearbyPlayers = level().getEntitiesOfClass(Player.class, area);

        for (int i = 0; i < 6; i++) {
            EntityType<? extends Monster> type = switch (random.nextInt(6)) {
                case 0 -> EntityType.SKELETON;
                case 1 -> EntityType.WITHER_SKELETON;
                case 2 -> EntityType.ZOMBIE;
                case 3 -> EntityType.HUSK;
                case 4 -> EntityType.DROWNED;
                default -> EntityType.WITCH;
            };

            Monster mob = type.create(level());
            if (mob != null) {
                double xOff = random.nextDouble() * 6 - 3;
                double zOff = random.nextDouble() * 6 - 3;
                mob.moveTo(getX() + xOff, getY(), getZ() + zOff);

                equipMobGear(mob);

                mob.setTarget(nearbyPlayers.isEmpty() ? null : nearbyPlayers.get(random.nextInt(nearbyPlayers.size())));
                level().addFreshEntity(mob);
                darkDominionMinions.add(mob);
            }
        }
    }

    private void shadowDash(LivingEntity entity) {
        if (!level().isClientSide && shadowDashCooldown == 0) {
            Player target = getNearestPlayer();
            if (target != null) {
                setDeltaMovement(target.getX() - getX(), 0.2, target.getZ() - getZ());
                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0));
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
                startAttackAnimation(1);
                playAttackSound(1);
            }
            shadowDashCooldown = 600;
        }
    }

    private void summonVampireMinions(LivingEntity entity) {
        if (!level().isClientSide && !isSummoningMinions) {
            isSummoningMinions = true;
            abilityPhaseTimer = 80 + random.nextInt(40); // 4–6 seconds
            this.setNoAi(true);
            this.setInvulnerable(true);
            this.setTarget(null);
            setDeltaMovement(0, 0.2, 0);
            broadcastAbility("Summon Vampire Minions");

            for (int i = 0; i < 3 + random.nextInt(3); i++) {
                EntityType<? extends Monster> type = switch (random.nextInt(3)) {
                    case 0 -> HexcraftEntities.VAMPIRE_PIGLIN.get();
                    case 1 -> HexcraftEntities.VAMPIRE_EVOKER.get();
                    default -> HexcraftEntities.VAMPIRE_VINDICATOR.get();
                };
                Monster minion = type.create(level());
                if (minion != null) {
                    minion.moveTo(getX() + random.nextDouble() * 3, getY(), getZ() + random.nextDouble() * 3);
                    level().addFreshEntity(minion);
                }
            }

            startAttackAnimation(1);
            playAttackSound(1);
        }
    }

    private void triggerDarkVeil(LivingEntity entity) {
        if (!level().isClientSide && darkVeilCooldown == 0) {
            ((ServerLevel) level()).setDayTime(18000);
            for (Player player : level().players()) {
                player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 400, 1));
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 400, 1));
            }
            darkVeilCooldown = 1800;
            startAttackAnimation(2);
            playAttackSound(2);
        }
    }

    private void startAttackAnimation(int type) {
        isAttacking = true;
        attackTimer = 20;
        attackType = type;
    }

    private void playAttackSound(int type) {
        SoundEvent sound = switch (type) {
            case 2 -> SoundEvents.WITCH_AMBIENT;
            case 3 -> SoundEvents.BAT_TAKEOFF;
            default -> SoundEvents.SPIDER_AMBIENT;
        };
        playSound(sound, 1.0F, 1.0F);
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        playSound(SoundEvents.ELDER_GUARDIAN_DEATH, 1.0F, 1.0F);
        if (!level().isClientSide) {
            if (random.nextFloat() < 0.50f) spawnAtLocation(HexcraftItems.LILITH_SOUL.get());
            if (random.nextFloat() < 0.40f) spawnAtLocation(HexcraftItems.LILITH_CONTRACT.get());
            if (random.nextFloat() < 0.10f) spawnAtLocation(HexcraftItems.CRIMSON_FANG.get());
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> {
            if (isAttacking) {
                return switch (attackType) {
                    case 2 -> state.setAndContinue(ATTACK2_ANIM);
                    case 3 -> state.setAndContinue(ATTACK3_ANIM);
                    default -> state.setAndContinue(ATTACK_ANIM);
                };
            } else if (state.isMoving()) {
                return state.setAndContinue(WALK_ANIM);
            } else {
                return state.setAndContinue(IDLE_ANIM);
            }
        }));
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ELDER_GUARDIAN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ELDER_GUARDIAN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ELDER_GUARDIAN_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ZOMBIE_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean isNoGravity() {
        if (isUsingDarkDominion) {
            return true; // disable gravity while using Dark Dominion
        }
        return super.isNoGravity();
    }

    // Override getTarget to prevent targeting while transformed (bat swarm)
    @Override
    public LivingEntity getTarget() {
        return isTransformed ? null : super.getTarget();
    }

    // Make Lilith invulnerable while transformed (bat swarm)
    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        if (isTransformed || isUsingDarkDominion) return true;
        return super.isInvulnerableTo(source);
    }
}