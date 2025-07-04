package net.masterquentus.hexcraftmod.entity.vampires;

import net.masterquentus.hexcraftmod.entity.VampireEntity;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;

public class VampirePiglinEntity extends VampireEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public VampirePiglinEntity(EntityType<? extends VampireEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.addGoal(3, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        // Targeting Goals - Attack multiple entities
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Villager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Animal.class, true)); // Attacks all animals
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, WanderingTrader.class, true));
        this.targetSelector.addGoal(6, new HurtByTargetGoal(this));
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ZOMBIFIED_PIGLIN_ANGRY, this.getSoundSource(), 1.0F, 0.8F);

    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {

    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean success = super.doHurtTarget(target);
        if (success && !this.level().isClientSide) {
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.PIGLIN_ANGRY, this.getSoundSource(), 1.0F, 1.0F);
        }
        return success;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide && this.getTarget() == null && this.random.nextInt(600) == 0) {
            this.playSound(SoundEvents.PIGLIN_ANGRY, 0.7F, 0.9F + this.random.nextFloat() * 0.2F);
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.PIGLIN_STEP, 0.15F, 1.0F);
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        super.setTarget(target);
        if (target != null && !this.level().isClientSide) {
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.PIGLIN_ANGRY, this.getSoundSource(), 1.0F, 1.0F);
        }
    }

    @Override
    public void awardKillScore(Entity killed, int score, DamageSource source) {
        super.awardKillScore(killed, score, source);
        if (!this.level().isClientSide && killed instanceof LivingEntity) {
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.PIGLIN_CELEBRATE, this.getSoundSource(), 1.0F, 1.0F);
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.level().getMaxLocalRawBrightness(this.blockPosition()) < 5) {
            return SoundEvents.ZOMBIFIED_PIGLIN_AMBIENT;
        }
        return SoundEvents.PIGLIN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return switch (this.random.nextInt(3)) {
            case 0 -> SoundEvents.PIGLIN_HURT;
            case 1 -> SoundEvents.ZOMBIFIED_PIGLIN_HURT;
            default -> SoundEvents.HOGLIN_HURT;
        };
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.PIGLIN_DEATH;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean wasRecentlyHit) {
        this.spawnAtLocation(HexcraftItems.BLOOD_DROP.get());
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> {
            if (state.isMoving()) {
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.vampire_piglin.walk"));
            } else {
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.vampire_piglin.idle"));
            }
        }));
    }
}