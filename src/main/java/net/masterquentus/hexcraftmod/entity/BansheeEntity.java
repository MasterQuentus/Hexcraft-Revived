package net.masterquentus.hexcraftmod.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
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

import java.util.List;

public class BansheeEntity extends Monster implements GeoEntity, RangedAttackMob {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation SCREAM_ANIMATION = RawAnimation.begin().then("animation.banshee.scream", Animation.LoopType.PLAY_ONCE);

    private boolean isScreaming = false;

    public BansheeEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.xpReward = 50;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.0D, 20, 8.0F));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this) {
            @Override
            public boolean canUse() {
                Entity attacker = BansheeEntity.this.getLastHurtByMob();
                // Don't retaliate against other monsters (hostile mobs)
                if (attacker instanceof Monster) {
                    return false;
                }
                return super.canUse();
            }
        });

        // Attack players that aren't creative/spectator
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false,
                player -> player instanceof Player p && !p.isCreative() && !p.isSpectator()));

        // Attack villagers only if they're NOT monsters
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Villager.class, 10, true, false,
                villager -> !(villager instanceof Monster)));
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 24.0D)
                .build();
    }

    private int screamCooldown = 0;

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide && screamCooldown <= 0) {
            // Only scream if there are valid targets nearby
            boolean hasTargetNearby = level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(10)).stream()
                    .anyMatch(entity ->
                            entity != this &&
                                    !(entity instanceof BansheeEntity) &&
                                    !(entity instanceof Monster) && // Optional: don't scream if it's a hostile mob
                                    !(entity instanceof Player player && (player.isCreative() || player.isSpectator()))
                    );

            if (hasTargetNearby) {
                scream();
                screamCooldown = 200; // e.g. 10 seconds
            }
        } else {
            screamCooldown--;
        }
    }

    @Override
    public boolean isAlliedTo(Entity entity) {
        if (entity instanceof Monster) {
            return true;
        }
        return super.isAlliedTo(entity);
    }
    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        scream();
    }

    private void scream() {
        isScreaming = true;
        triggerAnim("scream_controller", "scream");
        level().playSound(null, blockPosition(), SoundEvents.GHAST_SCREAM, getSoundSource(), 1.5F, 0.9F);

        List<LivingEntity> entities = level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(10));
        for (LivingEntity entity : entities) {
            if (
                    entity != this &&
                            !(entity instanceof BansheeEntity) &&
                            !(entity instanceof Player player && (player.isCreative() || player.isSpectator()))
            ) {
                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 1));
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60));
                entity.hurt(level().damageSources().magic(), 6.0F); // Increase damage

                // Optional: Knockback
                Vec3 direction = entity.position().subtract(this.position()).normalize().scale(0.5);
                entity.push(direction.x, 0.25, direction.z);

                // Optional: Visual feedback
                ((ServerLevel) level()).sendParticles(ParticleTypes.SONIC_BOOM, entity.getX(), entity.getY() + 1, entity.getZ(), 1, 0, 0, 0, 0);
            }
        }
    }



    @Override
    public boolean doHurtTarget(Entity target) {
        boolean success = super.doHurtTarget(target);
        if (success && target instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100));
        }
        return success;
    }

    // Animation controllers
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "scream_controller", 0, state -> {
            if (isScreaming) {
                if (state.getController().hasAnimationFinished()) {
                    isScreaming = false;
                }
                return state.setAndContinue(SCREAM_ANIMATION);
            }
            return PlayState.STOP;
        }));

        controllers.add(new AnimationController<>(this, "movement_controller", 5, state -> {
            if (state.isMoving()) {
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.banshee.float"));
            } else {
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.banshee.idle"));
            }
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    // Sounds
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.GHAST_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.GHAST_SCREAM;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GHAST_DEATH;
    }
}