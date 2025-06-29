package net.masterquentus.hexcraftmod.entity;

import net.masterquentus.hexcraftmod.entity.ai.goal.MoveToFairyLanternGoal;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nullable;
import java.util.UUID;

public class FairyEntity extends TamableAnimal implements GeoEntity {
    private static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.defineId(FairyEntity.class, EntityDataSerializers.BOOLEAN);
    private final AnimatableInstanceCache animationCache = new SingletonAnimatableInstanceCache(this);
    private UUID ownerUUID;

    public FairyEntity(EntityType<? extends FairyEntity> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 10, true);
        this.navigation = new FlyingPathNavigation(this, level);
    }

    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(FairyEntity.class, EntityDataSerializers.INT);


    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.FLYING_SPEED, 0.6D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D).build();
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation navigation = new FlyingPathNavigation(this, level);
        navigation.setCanOpenDoors(false);
        navigation.setCanFloat(true);
        return navigation;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new MoveToFairyLanternGoal(this, 1.0D)); // ✅ Fairy will move toward Fairy Lanterns
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null; // Fairies cannot breed
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FLYING, true);
        this.entityData.define(VARIANT, 0);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData data, @Nullable CompoundTag nbt) {
        if (data == null) {
            this.setVariant(random.nextInt(3)); // Picks 0, 1, or 2
        }
        return super.finalizeSpawn(world, difficulty, reason, data, nbt);
    }

    public boolean isFlying() {
        return this.entityData.get(FLYING);
    }

    public void setFlying(boolean flying) {
        this.entityData.set(FLYING, flying);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.verticalCollision && this.isFlying()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
        }
    }

    public void setOwner(Player player) {
        this.ownerUUID = player.getUUID();
    }

    @Nullable
    public Player getOwner() {
        if (this.ownerUUID == null) {
            return null;
        }
        return this.level().getPlayerByUUID(this.ownerUUID);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "controller", 0, this::animationPredicate));
    }

    private <E extends GeoEntity> PlayState animationPredicate(AnimationState<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.fairy.fly"));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.fairy.idle"));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationCache;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ALLAY_AMBIENT_WITHOUT_ITEM;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ALLAY_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ALLAY_DEATH;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean wasRecentlyHit) {
        this.spawnAtLocation(HexcraftItems.FAIRY_DUST.get());
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false; // Fairies cannot breed
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setFlying(compound.getBoolean("Flying"));
        if (compound.hasUUID("OwnerUUID")) {
            this.ownerUUID = compound.getUUID("OwnerUUID");
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Flying", this.isFlying());
        if (this.ownerUUID != null) {
            compound.putUUID("OwnerUUID", this.ownerUUID);
        }
    }
}