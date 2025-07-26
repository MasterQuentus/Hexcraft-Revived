package net.masterquentus.hexcraftmod.entity;

import net.masterquentus.hexcraftmod.entity.ai.goal.SirenRandomSwimGoal;
import net.masterquentus.hexcraftmod.entity.ai.goal.SirenSwimAttackGoal;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.monster.Monster;
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

import java.util.EnumSet;
import java.util.List;

public class SirenEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation SING_ANIMATION = RawAnimation.begin().then("animation.siren.sing", Animation.LoopType.LOOP);
    private boolean isSinging = false;
    private int lureCooldown = 0;
    private int singDuration = 0;

    public SirenEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.xpReward = 50;
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.1F, 0.5F, true);
        this.navigation = new WaterBoundPathNavigation(this, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(1, new SirenSwimAttackGoal(this, 0.8D));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new SirenRandomSwimGoal(this, 0.6D));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false,
                player -> player instanceof Player p && !p.isCreative() && !p.isSpectator()));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Villager.class, true));
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 20.0D)
                .build();
    }

    @Override
    public boolean isNoGravity() {
        return this.isInWater();
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WaterBoundPathNavigation(this, level);
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean isInWater() {
        return super.isInWater(); // You can customize this if you want to include bubble columns, etc.
    }

    @Override
    public void tick() {
        super.tick();

        // Water healing
        if (this.isInWater() && this.tickCount % 10 == 0) {
            this.heal(1.0F);
        }

        // Luring song every 200 ticks
        if (--lureCooldown <= 0) {
            isSinging = true;
            singDuration = 60; // 3 seconds
            lureCooldown = 200;
        }

        if (isSinging) {
            applySirenLure();
            singDuration--;
            if (singDuration <= 0) {
                isSinging = false;
            }

            if (this.isInWater()) {
                this.setSpeed(1.5F); // Higher than normal
            } else {
                this.setSpeed(1.0F);
            }

            if (this.isInWater() && this.tickCount % 40 == 0) {
                this.heal(1.0F);
            }
        }
    }

    @Override
    public int getMaxAirSupply() {
        return 300;
    }

    @Override
    public void setAirSupply(int air) {
        // Do nothing to prevent drowning logic
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.setAirSupply(this.getMaxAirSupply());
    }

    private void applySirenLure() {
        List<Player> players = level().getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(12));
        for (Player player : players) {
            // ⛔ Skip creative/spectator players
            if (player.isCreative() || player.isSpectator()) continue;

            Vec3 pull = this.position().subtract(player.position()).normalize().scale(0.4);
            player.setDeltaMovement(player.getDeltaMovement().add(pull));
            player.hurtMarked = true;

            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
        }

        level().playSound(null, blockPosition(), SoundEvents.ALLAY_AMBIENT_WITHOUT_ITEM, getSoundSource(), 1.5F, 0.8F);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean success = super.doHurtTarget(target);
        if (success && target instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
        }
        return success;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "sing_controller", 0, state -> {
            if (isSinging) {
                return state.setAndContinue(SING_ANIMATION);
            }
            return PlayState.STOP;
        }));

        controllers.add(new AnimationController<>(this, "movement_controller", 5, state -> {
            SirenEntity siren = state.getAnimatable();
            if (siren.isInWater() && !siren.getDeltaMovement().equals(Vec3.ZERO)) {
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.siren.swim"));
            }
            return state.setAndContinue(RawAnimation.begin().thenLoop("animation.siren.idle"));
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isInWater()) {
            this.moveRelative(0.05F, travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());

            // Damp X/Z motion, and stop floating up
            Vec3 motion = this.getDeltaMovement();
            this.setDeltaMovement(motion.x * 0.9, motion.y * 0.5, motion.z * 0.9);

            // Optional: Force slight sinking if no target
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, 0.01, 0));
            }
        } else {
            super.travel(travelVector);
        }
    }

    public EnumSet<Goal.Flag> getFlags() {
        return EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ALLAY_AMBIENT_WITH_ITEM;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.WANDERING_TRADER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WANDERING_TRADER_DEATH;
    }
}