package net.masterquentus.hexcraftmod.entity;

import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;

public abstract class VampireEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this); // ✅ Fix: Declare cache
    private boolean isDayWalker;

    protected VampireEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.isDayWalker = RandomSource.create().nextFloat() < 0.15F; // 15% chance to be a Day Walker
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D) // Health
                .add(Attributes.ATTACK_DAMAGE, 6.0D) // Attack Damage
                .add(Attributes.ATTACK_SPEED, 1.0f) // Movement Speed
                .add(Attributes.FOLLOW_RANGE, 32.0D) // Follow range
                .add(Attributes.MOVEMENT_SPEED, 0.3D).build(); // Armor
    }


    public abstract void performRangedAttack(LivingEntity target, float distanceFactor);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide && this.level().isDay() && !this.isDayWalker) {
            if (this.level().canSeeSky(this.blockPosition())) {
                this.setSecondsOnFire(8);
            }
        }
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        this.isDayWalker = this.random.nextFloat() < 0.2F; // 20% chance to be a day walker
        return super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        if (this.random.nextFloat() < 0.5F) { // 50% chance to drop a Blood Drop
            this.spawnAtLocation(new ItemStack(HexcraftItems.BLOOD_DROP.get()));
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, state -> {
            if (state.isMoving()) {
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.vampire.walk"));
            } else {
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.vampire.idle"));
            }
        }));
    }


    @Override
    public void die(DamageSource cause) {
        super.die(cause);
        if (this.level() instanceof ServerLevel serverLevel) {
            for (int i = 0; i < 10; i++) {
                serverLevel.sendParticles(ParticleTypes.LARGE_SMOKE, this.getX(), this.getY() + 1, this.getZ(), 1, 0.2, 0.2, 0.2, 0.1);
            }
        }
    }
}

class VampireEvokerEntity extends VampireEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public VampireEvokerEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}

class VampireVindicatorEntity extends VampireEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public VampireVindicatorEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}

class VampirePiglinEntity extends VampireEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public VampirePiglinEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.addGoal(2, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Villager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Animal.class, true)); // Attacks all animals
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, WanderingTrader.class, true));
        this.targetSelector.addGoal(6, new HurtByTargetGoal(this));
    }

}
