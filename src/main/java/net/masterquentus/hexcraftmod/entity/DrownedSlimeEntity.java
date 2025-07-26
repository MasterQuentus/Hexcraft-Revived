package net.masterquentus.hexcraftmod.entity;

import net.masterquentus.hexcraftmod.config.HexcraftConfig;
import net.masterquentus.hexcraftmod.entity.ai.goal.DrownedSlimeFloorBounceGoal;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nullable;

public class DrownedSlimeEntity extends Slime implements GeoEntity {
    private static final EntityDataAccessor<Integer> SIZE = SynchedEntityData.defineId(DrownedSlimeEntity.class, EntityDataSerializers.INT);
    private final AnimatableInstanceCache animationCache = new SingletonAnimatableInstanceCache(this);
    private float targetSquish, squish, oSquish;
    private boolean wasOnGround;
    private int jumpDelay;

    public DrownedSlimeEntity(EntityType<? extends DrownedSlimeEntity> type, Level level) {
        super(type, level);
        this.navigation = new GroundPathNavigation(this, level);
        this.navigation = new GroundPathNavigation(this, level);
        this.setSize(Mth.nextInt(this.random, 2, 4), true);
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0D) // Will be updated in setSize
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .build();
    }

    @Override
    protected void registerGoals() {
        // DO NOT add FloatGoal since this mob should not float
        this.goalSelector.addGoal(1, new DrownedSlimeFloorBounceGoal(this));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SIZE, 1);
    }
    @Override
    public void travel(Vec3 travelVector) {
        if (this.isInWater()) {
            this.setDeltaMovement(this.getDeltaMovement().x, -0.05D, this.getDeltaMovement().z); // slight pull down
            this.moveRelative(0.1F, travelVector);
            this.setNoGravity(this.isInWater());
            if (this.onGround()) {
                this.jumpFromGround(); // Force it to bounce
            }
        } else {
            super.travel(travelVector);
        }
    }
    public void setSize(int size, boolean resetHealth) {
        int s = Mth.clamp(size, 1, Slime.MAX_SIZE);
        this.entityData.set(SIZE, s);
        this.reapplyPosition();
        this.refreshDimensions();
        double hp = s * s;
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(hp);
        if (resetHealth) {
            this.setHealth((float) hp);
        }
        this.xpReward = s;

        // Scale movement speed based on size
        double speedMultiplier = (s > 1) ? 0.2D * s : 0.15D;  // Keep smaller slimes faster for better visibility
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(speedMultiplier);
    }

    public int getSize() {
        return this.entityData.get(SIZE);
    }

    public boolean isTiny() {
        return this.getSize() <= 1;
    }


    @Override
    public void tick() {
        this.oSquish = this.squish;
        this.squish += (this.targetSquish - this.squish) * 0.5F;
        super.tick();

        boolean onGroundOrWater = this.onGround() || this.isInWater();
        if (onGroundOrWater && !this.wasOnGround) {
            for (int i = 0; i < getSize() * 8; i++) {
                double angle = random.nextDouble() * Math.PI * 2;
                double mag = (random.nextDouble() * 0.5 + 0.5) * getSize() * 0.5;
                double dx = Math.sin(angle) * mag, dz = Math.cos(angle) * mag;
                this.level().addParticle(ParticleTypes.ITEM_SLIME,
                        this.getX() + dx, this.getY(), this.getZ() + dz, 0, 0, 0);
            }
            this.playSound(getSquishSound(), getSoundVolume(), (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            targetSquish = -0.5F;
        } else if (!onGroundOrWater && this.wasOnGround) {
            targetSquish = 1.0F;
        }
        this.wasOnGround = onGroundOrWater;
        this.targetSquish *= 0.6F;

        if (this.isInWater()) {
            setAirSupply(getMaxAirSupply());
            Vec3 mv = this.getDeltaMovement();
            if (mv.y < 0) this.setDeltaMovement(mv.x, 0, mv.z);
            this.setNoGravity(true);
        } else {
            this.setNoGravity(false);
        }
    }

    protected int getJumpDelay() {
        return this.random.nextInt(20) + 10;
    }

    @Override
    protected void jumpFromGround() {
        if (this.isInWater()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, 0.4D, 0));
        } else {
            super.jumpFromGround();
        }
    }
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        int randomSize = 1 + this.random.nextInt(3); // Sizes 1 to 3
        this.setSize(randomSize, true);
        return super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource ds) {
        return isTiny() ? SoundEvents.SLIME_HURT_SMALL : SoundEvents.SLIME_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return isTiny() ? SoundEvents.SLIME_DEATH_SMALL : SoundEvents.SLIME_DEATH;
    }

    protected SoundEvent getSquishSound() {
        return isTiny() ? SoundEvents.SLIME_SQUISH_SMALL : SoundEvents.SLIME_SQUISH;
    }

    protected float getSoundVolume() {
        return 0.4F * getSize();
    }

    public boolean isDealsDamage() {
        return !isTiny();
    }

    @Override
    public void die(DamageSource cause) {
        int size = getSize();
        if (!this.level().isClientSide && size > 1) {  // Only split if size > 1
            int newSize = size / 2;  // Half size for splits, or you can do size - 1 if you want
            for (int i = 0; i < 2 + random.nextInt(2); i++) {
                DrownedSlimeEntity smaller = HexcraftEntities.DROWNED_SLIME.get().create(this.level());
                if (smaller != null) {
                    smaller.setSize(newSize, true);
                    smaller.moveTo(getX() + (random.nextDouble() - 0.5),
                            getY(),
                            getZ() + (random.nextDouble() - 0.5),
                            random.nextFloat() * 360, 0);
                    this.level().addFreshEntity(smaller);
                }
            }
        }
        super.die(cause);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource src, int looting, boolean hit) {
        super.dropCustomDeathLoot(src, looting, hit);
        Item loot = HexcraftConfig.getRandomFishingLoot(this.random);
        if (loot != null && loot != Items.AIR) spawnAtLocation(loot);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    // GeckoLib controller & animation
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "controller", 0, this::animationPredicate));
    }

    private <E extends GeoEntity> PlayState animationPredicate(AnimationState<E> event) {
        event.getController().setAnimation(
                RawAnimation.begin()
                        .thenLoop(isDealsDamage() ? "animation.drownslime.swim" : "animation.drownslime.idle")
        );
        return PlayState.CONTINUE;
    }

    @Override public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animationCache;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Size", getSize());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setSize(tag.getInt("Size"), false);
    }

    // Custom Slime-style MoveControl adapted
    static class SlimeMoveControl extends MoveControl {
        private final DrownedSlimeEntity slime;
        private float yRot;
        private int jumpDelay;
        private boolean aggressive;



        SlimeMoveControl(DrownedSlimeEntity slime) {
            super(slime);
            this.slime = slime;
            this.yRot = (float)(slime.getYRot() * (180F / (float)Math.PI));
        }

        public void setDirection(float rot, boolean agg) {
            this.yRot = rot;
            this.aggressive = agg;
        }

        public void setWantedMovement(double speed) {
            this.speedModifier = speed;
            this.operation = Operation.MOVE_TO;
        }

        @Override
        public void tick() {
            this.mob.setYRot(this.rotlerp(this.mob.getYRot(), this.yRot, 90F));
            this.mob.yHeadRot = this.mob.getYRot();
            this.mob.yBodyRot = this.mob.getYRot();
            if (this.operation != Operation.MOVE_TO) {
                this.mob.setZza(0F);
            } else {
                this.operation = Operation.WAIT;
                if (this.mob.onGround() || this.slime.isInWater()) {
                    this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                    if (this.jumpDelay-- <= 0) {
                        this.jumpDelay = this.slime.getJumpDelay();
                        if (aggressive) this.jumpDelay /= 3;
                        this.slime.jumpFromGround();
                    } else {
                        this.slime.xxa = 0F;
                        this.slime.zza = 0F;
                        this.mob.setSpeed(0F);
                    }
                } else {
                    this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                }
            }
        }
    }
}