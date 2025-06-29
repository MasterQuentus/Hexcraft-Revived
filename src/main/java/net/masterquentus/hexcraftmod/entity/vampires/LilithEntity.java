package net.masterquentus.hexcraftmod.entity.vampires;

import net.masterquentus.hexcraftmod.entity.HexcraftEntities;
import net.masterquentus.hexcraftmod.entity.ai.goal.UseAbilityGoal;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;

import java.util.List;
import java.util.Random;

public class LilithEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private int bloodMoonCooldown = 1200; // 60 seconds
    private int darkVeilCooldown = 1800; // 90 seconds
    private int shadowDashCooldown = 600; // 30 seconds cooldown


    // Fixed: Correct animation play state
    private static final RawAnimation ATTACK_ANIM = RawAnimation.begin().then("animation.lilith.attack", Animation.LoopType.DEFAULT);
    private static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.lilith.idle");

    public LilithEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 500; // High XP reward
    }

    @Override
    protected void registerGoals() {
        // Combat & Movement
        // Combat & Movement
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.2, true));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.2D));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.5F));

        // **NEW: Apply Blood Moon Curse on Spawn**
        this.goalSelector.addGoal(5, new UseAbilityGoal(this, this::applyBloodMoonCurse, 1)); // Activates on spawn

        // Custom Abilities (Fixed & Fully Working)
        this.goalSelector.addGoal(5, new UseAbilityGoal(this, this::applyDarkDominion, 200)); // Life Drain (70% HP)
        this.goalSelector.addGoal(6, new UseAbilityGoal(this, this::shadowDash, 300)); // Dash Attack (40% HP)
        this.goalSelector.addGoal(7, new UseAbilityGoal(this, this::summonVampireMinions, 250)); // Random minion summon (50% HP)
        this.goalSelector.addGoal(8, new UseAbilityGoal(this, this::triggerBatSwarm, 250)); // Turns into Bats (60% HP)
        this.goalSelector.addGoal(9, new UseAbilityGoal(this, this::triggerDarkVeil, 1)); // Darkness & Weakness (30% HP)

        // Targeting Goals (Attacks Everything Except Undead)
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Villager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Animal.class, true));
    }

    private final ServerBossEvent bossEvent = new ServerBossEvent(Component.translatable("boss.hexcraft.lilith"),
            BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS);

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 300.0D) // High HP (Lilith is a boss)
                .add(Attributes.MOVEMENT_SPEED, 0.35D) // Slightly faster than a normal mob
                .add(Attributes.ATTACK_DAMAGE, 15.0D) // Strong melee damage
                .add(Attributes.ATTACK_KNOCKBACK, 1.5D) // Slight knockback on hit
                .add(Attributes.ARMOR, 10.0D) // High armor value (resistant to damage)
                .add(Attributes.ARMOR_TOUGHNESS, 8.0D) // Reduces armor penetration
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8D) // Hard to knock back
                .add(Attributes.FOLLOW_RANGE, 40.0D).build(); // Detects players from far away
    }


    @Override
    public void tick() {
        super.tick();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());

        // Reduce cooldowns (but prevent negative values)

        if (bloodMoonCooldown > 0) bloodMoonCooldown--;
        if (darkVeilCooldown > 0) darkVeilCooldown--;
        if (shadowDashCooldown > 0) shadowDashCooldown--;

        // Apply abilities with proper cooldowns
        if (this.getHealth() < this.getMaxHealth() * 0.7 && this.random.nextInt(250) == 0) {
            applyDarkDominion(this);
        }

        if (this.getHealth() < this.getMaxHealth() * 0.5 && this.random.nextInt(200) == 0) {
            summonVampireMinions(this);
        }

        if (this.getHealth() < this.getMaxHealth() * 0.4) {
            shadowDash(this);
        }

        if (this.getHealth() < this.getMaxHealth() * 0.6 && this.random.nextInt(250) == 0) {
            triggerBatSwarm(this);
        }

        if (this.getHealth() < this.getMaxHealth() * 0.3) {
            triggerDarkVeil(this);
        }

        if (this.getHealth() < this.getMaxHealth() * 0.9) {
            applyBloodMoonCurse(this);
        }
    }

    private Player getNearestPlayer() {
        return this.level().getNearestPlayer(this, 10.0D);
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossEvent.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossEvent.removePlayer(player);
    }

    private void applyBloodMoonCurse(LivingEntity entity) {
        if (!level().isClientSide && bloodMoonCooldown == 0) { // Ensures cooldown is expired
            for (Player player : level().players()) {
                player.addEffect(new MobEffectInstance(MobEffects.WITHER, 400, 1)); // Wither II for 20s
                player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 300, 1)); // Darkness for 15s
            }
            bloodMoonCooldown = 1200; // Reset cooldown to 60 seconds
        }
    }

    private void triggerBatSwarm(LivingEntity entity) {
        this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 200, 0));

        for (int i = 0; i < 5; i++) {
            Bat bat = EntityType.BAT.create(level());
            if (bat != null) {
                bat.moveTo(this.getX() + random.nextDouble(), this.getY() + random.nextDouble(), this.getZ() + random.nextDouble());
                level().addFreshEntity(bat);
            }
        }
    }

    // Fixed: Use DamageSources.GENERIC (or create a custom damage source)
    private void applyDarkDominion(LivingEntity entity) {
        AABB area = new AABB(this.blockPosition()).inflate(8);
        List<LivingEntity> targets = level().getEntitiesOfClass(LivingEntity.class, area, e -> e instanceof Player);

        for (LivingEntity target : targets) {
            target.hurt(this.damageSources().magic(), 4.0F); // Deals magic damage
            this.heal(4.0F); // Heals Lilith
        }
    }


    private void shadowDash(LivingEntity entity) {
        if (!level().isClientSide && shadowDashCooldown == 0) { // Ensures cooldown is expired
            Player target = getNearestPlayer();
            if (target != null) {
                this.setDeltaMovement(target.getX() - this.getX(), 0.2, target.getZ() - this.getZ());
                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0)); // 3 seconds of Blindness
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1)); // 5 seconds of Slowness
            }
            shadowDashCooldown = 600; // 30-second cooldown
        }
    }

    // Fixed: Corrected Vampire Summoning
    private void summonVampireMinions(LivingEntity entity) {
        Random random = new Random();
        int choice = random.nextInt(3); // Picks a number 0, 1, or 2

        EntityType<? extends Monster> minionType;

        if (choice == 0) {
            minionType = HexcraftEntities.VAMPIRE_PIGLIN.get(); // 33% chance to spawn Vampire Piglin
        } else if (choice == 1) {
            minionType = HexcraftEntities.VAMPIRE_EVOKER.get(); // 33% chance to spawn Vampire Evoker
        } else {
            minionType = HexcraftEntities.VAMPIRE_VINDICATOR.get(); // 33% chance to spawn Vampire Vindicator
        }

        Monster minion = minionType.create(level());
        if (minion != null) {
            minion.moveTo(this.getX() + random.nextDouble() * 3, this.getY(), this.getZ() + random.nextDouble() * 3);
            level().addFreshEntity(minion);
        }
    }


    private void triggerDarkVeil(LivingEntity entity) {
        if (!level().isClientSide && darkVeilCooldown == 0) { // Ensures cooldown is expired
            ((ServerLevel) this.level()).setDayTime(18000); // Turns night instantly
            for (Player player : level().players()) {
                player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 400, 1)); // 20 seconds of Blindness
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 400, 1)); // 20 seconds of Weakness
            }
            darkVeilCooldown = 1800; // 90-second cooldown
        }
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        if (!level().isClientSide) {
            Random random = new Random();

            // 50% chance to drop Lilith’s Soul
            if (random.nextFloat() < 0.50) {
                this.spawnAtLocation(HexcraftItems.LILITH_SOUL.get());
            }

            // 40% chance to drop Lilith’s Contract
            if (random.nextFloat() < 0.40) {
                this.spawnAtLocation(HexcraftItems.LILITH_CONTRACT.get());
            }
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> {
            if (state.isMoving()) {
                return state.setAndContinue(DefaultAnimations.WALK);
            }
            return state.setAndContinue(DefaultAnimations.IDLE);
        }));

        controllers.add(new AnimationController<>(this, "attack_controller", 5, state -> {
            return state.setAndContinue(ATTACK_ANIM);
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}