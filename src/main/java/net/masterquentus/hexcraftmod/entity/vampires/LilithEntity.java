package net.masterquentus.hexcraftmod.entity.vampires;

import net.masterquentus.hexcraftmod.entity.HexcraftEntities;
import net.masterquentus.hexcraftmod.entity.ai.goal.UseAbilityGoal;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
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

    private boolean isAttacking = false;
    private int attackTimer = 0;
    private int attackType = 1; // Track which attack animation to play
    private boolean isFlying = false;
    private int flyingTimer = 0;

    private static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("animation.lilith.walk");
    private static final RawAnimation ATTACK_ANIM = RawAnimation.begin().then("animation.lilith.attack", Animation.LoopType.DEFAULT);
    private static final RawAnimation ATTACK2_ANIM = RawAnimation.begin().then("animation.lilith.attack2", Animation.LoopType.DEFAULT);
    private static final RawAnimation ATTACK3_ANIM = RawAnimation.begin().then("animation.lilith.attack3", Animation.LoopType.DEFAULT);
    private static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.lilith.idle");
    private static final RawAnimation FLY_ANIM = RawAnimation.begin().thenLoop("animation.lilith.fly");

    public LilithEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 500; // High XP reward
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.2, true));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.2D));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.5F));
        this.goalSelector.addGoal(5, new UseAbilityGoal(this, this::applyBloodMoonCurse, 1));
        this.goalSelector.addGoal(6, new UseAbilityGoal(this, this::applyDarkDominion, 200));
        this.goalSelector.addGoal(7, new UseAbilityGoal(this, this::shadowDash, 300));
        this.goalSelector.addGoal(8, new UseAbilityGoal(this, this::summonVampireMinions, 250));
        this.goalSelector.addGoal(9, new UseAbilityGoal(this, this::triggerBatSwarm, 250));
        this.goalSelector.addGoal(10, new UseAbilityGoal(this, this::triggerDarkVeil, 1));

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

        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());

        if (bloodMoonCooldown > 0) bloodMoonCooldown--;
        if (darkVeilCooldown > 0) darkVeilCooldown--;
        if (shadowDashCooldown > 0) shadowDashCooldown--;

        if (attackTimer > 0) {
            attackTimer--;
        } else {
            isAttacking = false;
        }

        // Randomly start flying if not already flying and on ground
        if (!this.isFlying && this.onGround() && this.random.nextInt(600) == 0) { // roughly every 30 seconds chance per tick
            startFlying();
        }

        // Handle flying timer countdown
        if (isFlying) {
            flyingTimer--;
            if (flyingTimer <= 0) {
                stopFlying();
            } else {
                // Add gentle upward motion while flying
                this.setDeltaMovement(this.getDeltaMovement().x, 0.2, this.getDeltaMovement().z);
            }
        }
    }

    private void startFlying() {
        isFlying = true;
        flyingTimer = 100 + random.nextInt(100); // fly for 5-10 seconds
        this.playSound(SoundEvents.BAT_TAKEOFF, 1.0F, 1.0F); // Use bat takeoff sound as placeholder
    }

    private void stopFlying() {
        isFlying = false;
        this.playSound(SoundEvents.BAT_AMBIENT, 1.0F, 1.0F); // Use bat ambient sound as placeholder
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
        if (!level().isClientSide && bloodMoonCooldown == 0) {
            for (Player player : level().players()) {
                player.addEffect(new MobEffectInstance(MobEffects.WITHER, 400, 1));
                player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 300, 1));
            }
            bloodMoonCooldown = 1200;
        }
    }

    private void triggerBatSwarm(LivingEntity entity) {
        if (!level().isClientSide) {
            this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 200, 0));

            for (int i = 0; i < 5; i++) {
                Bat bat = EntityType.BAT.create(level());
                if (bat != null) {
                    bat.moveTo(this.getX() + random.nextDouble(), this.getY() + random.nextDouble(), this.getZ() + random.nextDouble());
                    level().addFreshEntity(bat);
                }
            }
            startAttackAnimation(3);
            playAttackSound(3);
        }
    }

    private void applyDarkDominion(LivingEntity entity) {
        if (!level().isClientSide) {
            AABB area = new AABB(this.blockPosition()).inflate(8);
            List<LivingEntity> targets = level().getEntitiesOfClass(LivingEntity.class, area, e -> e instanceof Player);

            for (LivingEntity target : targets) {
                target.hurt(this.damageSources().magic(), 4.0F);
                this.heal(4.0F);
            }
            startAttackAnimation(2);
            playAttackSound(2);
        }
    }

    private void shadowDash(LivingEntity entity) {
        if (!level().isClientSide && shadowDashCooldown == 0) {
            Player target = getNearestPlayer();
            if (target != null) {
                this.setDeltaMovement(target.getX() - this.getX(), 0.2, target.getZ() - this.getZ());
                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0));
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
                startAttackAnimation(1);
                playAttackSound(1);
            }
            shadowDashCooldown = 600;
        }
    }

    private void summonVampireMinions(LivingEntity entity) {
        if (!level().isClientSide) {
            Random random = new Random();
            int choice = random.nextInt(3);

            EntityType<? extends Monster> minionType;

            if (choice == 0) {
                minionType = HexcraftEntities.VAMPIRE_PIGLIN.get();
            } else if (choice == 1) {
                minionType = HexcraftEntities.VAMPIRE_EVOKER.get();
            } else {
                minionType = HexcraftEntities.VAMPIRE_VINDICATOR.get();
            }

            Monster minion = minionType.create(level());
            if (minion != null) {
                minion.moveTo(this.getX() + random.nextDouble() * 3, this.getY(), this.getZ() + random.nextDouble() * 3);
                level().addFreshEntity(minion);
                startAttackAnimation(1);
                playAttackSound(1);
            }
        }
    }

    private void triggerDarkVeil(LivingEntity entity) {
        if (!level().isClientSide && darkVeilCooldown == 0) {
            ((ServerLevel) this.level()).setDayTime(18000);
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
        attackTimer = 20; // 1 second attack animation duration
        attackType = type;
    }

    private void playAttackSound(int attackType) {
        SoundEvent sound;
        switch (attackType) {
            case 2:
                sound = SoundEvents.WITCH_AMBIENT; // Example: witch ambient for attack2
                break;
            case 3:
                sound = SoundEvents.BAT_TAKEOFF; // Example: bat takeoff for attack3
                break;
            case 1:
            default:
                sound = SoundEvents.SPIDER_AMBIENT; // Example: spider ambient for attack1
                break;
        }
        this.playSound(sound, 1.0F, 1.0F);
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        this.playSound(SoundEvents.ELDER_GUARDIAN_DEATH, 1.0F, 1.0F); // death sound example
        if (!level().isClientSide) {
            Random random = new Random();

            if (random.nextFloat() < 0.50) {
                this.spawnAtLocation(HexcraftItems.LILITH_SOUL.get());
            }

            if (random.nextFloat() < 0.40) {
                this.spawnAtLocation(HexcraftItems.LILITH_CONTRACT.get());
            }
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> {
            if (isAttacking) {
                switch (attackType) {
                    case 2: return state.setAndContinue(ATTACK2_ANIM);
                    case 3: return state.setAndContinue(ATTACK3_ANIM);
                    default: return state.setAndContinue(ATTACK_ANIM);
                }
            } else if (isFlying) {
                return state.setAndContinue(FLY_ANIM);
            } else if (state.isMoving()) {
                return state.setAndContinue(WALK_ANIM);
            } else {
                return state.setAndContinue(IDLE_ANIM);
            }
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ELDER_GUARDIAN_AMBIENT; // idle ambient sound example
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ELDER_GUARDIAN_HURT; // hurt sound example
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ELDER_GUARDIAN_DEATH; // death sound example (also played in die())
    }

    protected void playStepSound(net.minecraft.world.level.block.state.BlockState blockIn, net.minecraft.core.BlockPos pos, net.minecraft.world.level.LevelReader worldIn) {
        this.playSound(SoundEvents.ZOMBIE_STEP, 0.15F, 1.0F); // step sound example
    }
}