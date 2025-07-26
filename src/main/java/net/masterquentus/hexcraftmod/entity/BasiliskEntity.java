package net.masterquentus.hexcraftmod.entity;

import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

import java.util.List;

public class BasiliskEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation BASILISK_ATTACK_1 = RawAnimation.begin().then("animation.basilisk.attack", Animation.LoopType.PLAY_ONCE);
    private static final RawAnimation BASILISK_ATTACK_2 = RawAnimation.begin().then("animation.basilisk.attack2", Animation.LoopType.PLAY_ONCE);

    private boolean isUsingAttack1 = false;
    private boolean isUsingAttack2 = false;
    private long lastShadowStep = 0;

    public BasiliskEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 100;
    }

    @Override
    protected void registerGoals() {
        // Combat Goals
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false,
                player -> player instanceof Player p && !p.isCreative() && !p.isSpectator()));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Villager.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Animal.class, false)); // Attacks passive mobs

        // Attack behavior
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.2D, true));

        // Movement & Observation
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));

        // Optional: Makes it attack Tamed Wolves, Foxes, Cats (could simulate fear)
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Wolf.class, false));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Cat.class, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Fox.class, false));
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.FOLLOW_RANGE, 30.0D)
                .build();
    }

    @Override
    public void tick() {
        super.tick();

        applyToxicAura();
        applyPetrifyingGaze();

        if (this.getHealth() < this.getMaxHealth() / 2 && level().getGameTime() - lastShadowStep > 200) {
            shadowStep();
            lastShadowStep = level().getGameTime();
        }
    }

    // Petrifying Gaze
    private void applyPetrifyingGaze() {
        List<Player> players = level().getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(10));
        for (Player player : players) {
            // ❌ Skip creative and spectator players
            if (player.isCreative() || player.isSpectator()) continue;

            // Skip if player is wearing a blindfold
            ItemStack headItem = player.getItemBySlot(EquipmentSlot.HEAD);
            if (headItem != null && headItem.getItem() == HexcraftItems.BLINDFOLD.get()) {
                continue;
            }

            Vec3 viewVec = player.getViewVector(1.0F).normalize();
            Vec3 toEntity = this.position().subtract(player.position()).normalize();
            double dot = viewVec.dot(toEntity);

            if (dot > 0.85) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 2));
                player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60));
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 60));
            }
        }
    }

    // Toxic Aura
    private void applyToxicAura() {
        List<LivingEntity> nearby = level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4));
        for (LivingEntity entity : nearby) {
            // Skip self
            if (entity == this) continue;

            // Skip creative and spectator players
            if (entity instanceof Player player && (player.isCreative() || player.isSpectator())) continue;

            // Optionally skip undead (immune to poison)
            if (entity.getMobType() == MobType.UNDEAD) continue;

            entity.addEffect(new MobEffectInstance(MobEffects.POISON, 40, 0));
        }
    }

    // Shadow Step
    private void shadowStep() {
        for (int i = 0; i < 10; i++) {
            double dx = this.getX() + (random.nextDouble() - 0.5) * 10;
            double dz = this.getZ() + (random.nextDouble() - 0.5) * 10;
            BlockPos newPos = new BlockPos(Mth.floor(dx), Mth.floor(this.getY()), Mth.floor(dz));
            if (level().getBlockState(newPos).isAir()) {
                this.teleportTo(dx, this.getY(), dz);
                level().playSound(null, newPos, SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);
                break;
            }
        }
    }

    // Venomous Bite
    @Override
    public boolean doHurtTarget(Entity target) {
        boolean success = super.doHurtTarget(target);
        if (success && target instanceof LivingEntity living) {
            if (random.nextBoolean()) {
                triggerAttack1();
            } else {
                triggerAttack2();
            }

            living.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));
            living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));

            if (target.getType().getDescriptionId().contains("vampire")) {
                living.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 200, -1));
            }
        }
        return success;
    }

    private void triggerAttack1() {
        isUsingAttack1 = true;
        triggerAnim("attack1_controller", "attack1");
        level().playSound(null, blockPosition(), SoundEvents.SPIDER_AMBIENT, getSoundSource(), 1.0F, 1.0F);
    }

    private void triggerAttack2() {
        isUsingAttack2 = true;
        triggerAnim("attack2_controller", "attack2");
        level().playSound(null, blockPosition(), SoundEvents.SPIDER_AMBIENT, getSoundSource(), 1.0F, 0.8F);
    }

    // Animation Controllers
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "attack1_controller", 0, state -> {
            if (isUsingAttack1) {
                if (state.getController().hasAnimationFinished()) {
                    isUsingAttack1 = false;
                }
                return state.setAndContinue(BASILISK_ATTACK_1);
            }
            return PlayState.STOP;
        }));

        controllers.add(new AnimationController<>(this, "attack2_controller", 0, state -> {
            if (isUsingAttack2) {
                if (state.getController().hasAnimationFinished()) {
                    isUsingAttack2 = false;
                }
                return state.setAndContinue(BASILISK_ATTACK_2);
            }
            return PlayState.STOP;
        }));

        controllers.add(new AnimationController<>(this, "movement_controller", 5, state -> {
            if (state.isMoving()) {
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.basilisk.movement"));
            } else {
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.basilisk.idle"));
            }
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WARDEN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.WITHER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WITHER_DEATH;
    }
}