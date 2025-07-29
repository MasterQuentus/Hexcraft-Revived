package net.masterquentus.hexcraftmod.entity.vampires;

import net.masterquentus.hexcraftmod.entity.VampireEntity;
import net.masterquentus.hexcraftmod.entity.ai.goal.VampirePiglinPickupGoal;
import net.masterquentus.hexcraftmod.entity.vampires.goals.DarkMagicProjectileEntity;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;

public class VampirePiglinEntity extends VampireEntity implements CrossbowAttackMob {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING_CROSSBOW = SynchedEntityData.defineId(VampirePiglinEntity.class, EntityDataSerializers.BOOLEAN);
    private boolean adult = true;



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
        this.goalSelector.addGoal(7, new VampirePiglinPickupGoal(this, 1.0D));

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
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag tag) {
        SpawnGroupData data = super.finalizeSpawn(world, difficulty, reason, spawnData, tag);
        this.setCanPickUpLoot(true);
        return data;
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

    public boolean canFireProjectileWeapon(ProjectileWeaponItem pProjectileWeapon) {
        return pProjectileWeapon == Items.CROSSBOW;
    }

    @Override
    public void setChargingCrossbow(boolean charging) {
        this.entityData.set(DATA_IS_CHARGING_CROSSBOW, charging);
    }

    public boolean isChargingCrossbow() {
        return this.entityData.get(DATA_IS_CHARGING_CROSSBOW);
    }

    @Override
    public void onCrossbowAttackPerformed() {
        this.noActionTime = 0;
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.CROSSBOW_SHOOT, this.getSoundSource(), 1.0F, 1.0F);
    }

    protected void holdInMainHand(ItemStack pStack) {
        this.setItemSlotAndDropWhenKilled(EquipmentSlot.MAINHAND, pStack);
    }

    protected void holdInOffHand(ItemStack pStack) {
        if (pStack.isPiglinCurrency()) {
            this.setItemSlot(EquipmentSlot.OFFHAND, pStack);
            this.setGuaranteedDrop(EquipmentSlot.OFFHAND);
        } else {
            this.setItemSlotAndDropWhenKilled(EquipmentSlot.OFFHAND, pStack);
        }

    }

    @Override
    public boolean wantsToPickUp(ItemStack stack) {
        return stack.is(HexcraftItems.VAMPIRIC_GEM.get()) ||
                stack.is(HexcraftItems.VAMPIRIC_SWORD.get()) ||
                stack.is(Items.CROSSBOW);
    }

    public boolean isLovedItem(ItemStack stack) {
        return stack.is(HexcraftItems.VAMPIRIC_GEM.get())
                || stack.is(HexcraftItems.VAMPIRIC_SWORD.get())
                || stack.is(Items.CROSSBOW);
    }

    @Override
    protected void pickUpItem(ItemEntity itemEntity) {
        super.pickUpItem(itemEntity);
        // You can add extra logic here if needed (like notifying AI)
    }

    @Override
    public void shootCrossbowProjectile(LivingEntity target, ItemStack crossbow, Projectile projectile, float velocity) {
        double dx = target.getX() - this.getX();
        double dy = target.getY(0.333D) - projectile.getY();
        double dz = target.getZ() - this.getZ();
        double dist = Math.sqrt(dx * dx + dz * dz);

        projectile.shoot(dx, dy + dist * 0.2D, dz, velocity, 14 - this.level().getDifficulty().getId() * 4);
        this.level().addFreshEntity(projectile);
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    // Randomly give crossbow or sword on spawn
    @Override
    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        if (isAdult()) {
            if (random.nextFloat() < 0.5F) {
                this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
            } else {
                this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(HexcraftItems.VAMPIRIC_SWORD.get()));
            }

            // Armor: increase chance
            maybeWearArmor(EquipmentSlot.HEAD, new ItemStack(HexcraftItems.VAMPIRIC_HELMET.get()), random);
            maybeWearArmor(EquipmentSlot.CHEST, new ItemStack(HexcraftItems.VAMPIRIC_CHESTPLATE.get()), random);
            maybeWearArmor(EquipmentSlot.LEGS, new ItemStack(HexcraftItems.VAMPIRIC_LEGGING.get()), random);
            maybeWearArmor(EquipmentSlot.FEET, new ItemStack(HexcraftItems.VAMPIRIC_BOOTS.get()), random);
        }
    }

    private void maybeWearArmor(EquipmentSlot slot, ItemStack stack, RandomSource random) {
        if (random.nextFloat() < 1.0F) { // Always equips for testing
            this.setItemSlot(slot, stack);
        }
    }

    public void callPickUpItem(ItemEntity itemEntity) {
        super.pickUpItem(itemEntity);
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        if (!this.level().isClientSide) {
            DarkMagicProjectileEntity bolt = new DarkMagicProjectileEntity(this.level(), this,
                    target.getX() - this.getX(),
                    target.getY() - this.getY(),
                    target.getZ() - this.getZ());
            bolt.setPos(this.getX(), this.getY() + 1.5D, this.getZ());
            this.level().addFreshEntity(bolt);
        }
    }
}