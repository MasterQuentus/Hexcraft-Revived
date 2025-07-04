package net.masterquentus.hexcraftmod.entity.vampires;

import net.masterquentus.hexcraftmod.entity.VampireEntity;
import net.masterquentus.hexcraftmod.entity.vampires.goals.DarkMagicProjectileEntity;
import net.masterquentus.hexcraftmod.entity.vampires.goals.SummonMinionsGoal;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class VampireEvokerEntity extends VampireEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private SummonMinionsGoal summonMinionsGoal;


    public VampireEvokerEntity(EntityType<? extends VampireEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.summonMinionsGoal = new SummonMinionsGoal(this);
        this.goalSelector.addGoal(1, summonMinionsGoal);
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
    }

    private boolean isCastingSpell;

    public void setCastingSpell(boolean casting) {
        this.isCastingSpell = casting;
    }

    public boolean isCastingSpell() {
        return this.isCastingSpell;
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        if (!this.level().isClientSide) {
            DarkMagicProjectileEntity projectile = new DarkMagicProjectileEntity(this.level(), this,
                    target.getX() - this.getX(),
                    target.getY() - this.getY(),
                    target.getZ() - this.getZ()
            );
            projectile.setPos(this.getX(), this.getY() + 1.5D, this.getZ());
            this.level().addFreshEntity(projectile);
        }
    }

    public class SummonMinionsGoal extends Goal {
        private final VampireEvokerEntity evoker;
        private int summonCooldown;
        private boolean hasSummoned;

        public SummonMinionsGoal(VampireEvokerEntity evoker) {
            this.evoker = evoker;
            this.summonCooldown = 0;
            this.hasSummoned = false;
        }

        @Override
        public boolean canUse() {
            return this.summonCooldown <= 0 && evoker.getTarget() != null;
        }

        @Override
        public void start() {
            evoker.setCastingSpell(true);
            evoker.playSound(SoundEvents.EVOKER_PREPARE_SUMMON, 1.0F, 1.0F);
            this.summonCooldown = 200;
            this.hasSummoned = false;
        }

        @Override
        public void tick() {
            if (evoker.level().isClientSide) return;
            if (evoker.getTarget() == null) {
                this.stop();
                return;
            }

            if (!hasSummoned) {
                for (int i = 0; i < 2; i++) {
                    Zombie zombie = new Zombie(EntityType.ZOMBIE, evoker.level());
                    zombie.setPos(evoker.getX() + (evoker.getRandom().nextDouble() - 0.5) * 4, evoker.getY(), evoker.getZ() + (evoker.getRandom().nextDouble() - 0.5) * 4);
                    evoker.level().addFreshEntity(zombie);

                    Skeleton skeleton = new Skeleton(EntityType.SKELETON, evoker.level());
                    skeleton.setPos(evoker.getX() + (evoker.getRandom().nextDouble() - 0.5) * 4, evoker.getY(), evoker.getZ() + (evoker.getRandom().nextDouble() - 0.5) * 4);
                    evoker.level().addFreshEntity(skeleton);
                }
                hasSummoned = true;
                this.stop();
            }
        }

        @Override
        public void stop() {
            evoker.setCastingSpell(false);
        }

        @Override
        public boolean canContinueToUse() {
            return this.evoker.isCastingSpell();
        }

        public void tickCooldown() {
            if (this.summonCooldown > 0) this.summonCooldown--;
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> {
            if (this.isCastingSpell()) {
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.vampire_evoker.cast"));
            } else if (state.isMoving()) {
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.vampire_evoker.walk"));
            } else {
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.vampire_evoker.idle"));
            }
        }));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.EVOKER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.EVOKER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.EVOKER_DEATH;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (summonMinionsGoal != null) {
            summonMinionsGoal.tickCooldown();
        }

        if (!this.level().isClientSide && this.tickCount % 100 == 0) { // Every 5 seconds
            for (LivingEntity entity : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(6.0D))) {
                if (entity instanceof Zombie || entity instanceof Skeleton || entity instanceof VampireEntity) {
                    entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1)); // Strength 2 for 5 sec
                    entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1)); // Regen for 5 sec
                }
            }
        }
    }
}
