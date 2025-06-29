package net.masterquentus.hexcraftmod.entity.vampires;

import net.masterquentus.hexcraftmod.entity.VampireEntity;
import net.masterquentus.hexcraftmod.entity.vampires.goals.SummonMinionsGoal;
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

    public VampireEvokerEntity(EntityType<? extends VampireEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SummonMinionsGoal(this)); // Summon undead minions
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

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> {
            if (state.isMoving()) {
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.vampire_evoker.walk"));
            } else {
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.vampire_evoker.idle"));
            }
        }));
    }

    @Override
    public void aiStep() {
        super.aiStep();
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
