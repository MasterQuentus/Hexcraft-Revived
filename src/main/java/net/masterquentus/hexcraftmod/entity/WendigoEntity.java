package net.masterquentus.hexcraftmod.entity;

import net.masterquentus.hexcraftmod.entity.ai.goal.UseAbilityGoal;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationController;

import java.util.Random;

public class WendigoEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private int hungerCurseCooldown = 1200; // Every 60s
    private int illusionCooldown = 1800; // Every 90s
    private int stalkerCooldown = 600; // Every 30s

    // Flag to mark illusions, default false
    private boolean isIllusion = false;

    private static final RawAnimation WENDIGO_ATTACK = RawAnimation.begin().then("animation.wendigo.attack", Animation.LoopType.PLAY_ONCE);

    public WendigoEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 150;
    }

    // Setter for illusions
    public void setIllusion(boolean illusion) {
        this.isIllusion = illusion;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.4, true));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));

        this.goalSelector.addGoal(4, new UseAbilityGoal(this, this::applyHungerCurse, 1200));
        this.goalSelector.addGoal(5, new UseAbilityGoal(this, this::summonIllusions, 1800));
        this.goalSelector.addGoal(6, new UseAbilityGoal(this, this::stalkerMode, 600));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Villager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Animal.class, true));
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 120.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.45D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 1.5D)
                .add(Attributes.ARMOR, 5.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.3D)
                .add(Attributes.FOLLOW_RANGE, 60.0D)
                .build();
    }

    @Override
    public void tick() {
        super.tick();

        // **Skip cooldown reduction & ability usage if this is an illusion**
        if (isIllusion) {
            // Optionally despawn illusions after some time, e.g. 20 seconds:
            if (this.tickCount > 400) {
                this.discard();
            }
            return;
        }

        if (hungerCurseCooldown > 0) hungerCurseCooldown--;
        if (illusionCooldown > 0) illusionCooldown--;
        if (stalkerCooldown > 0) stalkerCooldown--;

        if (hungerCurseCooldown == 0) {
            applyHungerCurse(this);
            hungerCurseCooldown = 1200;
        }
        if (illusionCooldown == 0) {
            summonIllusions(this);
            illusionCooldown = 1800;
        }
        if (stalkerCooldown == 0) {
            stalkerMode(this);
            stalkerCooldown = 600;
        }
    }

    private void applyHungerCurse(LivingEntity entity) {
        if (!level().isClientSide) {
            for (Player player : level().players()) {
                if (!player.isCreative() && !player.isSpectator()) {
                    player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 1)); // 30s
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1)); // 10s
                }
            }
            level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.WOLF_GROWL, SoundSource.HOSTILE, 1.0F, 0.8F);
        }
    }

    private void summonIllusions(LivingEntity entity) {
        if (!level().isClientSide) {
            Random random = new Random();
            for (int i = 0; i < 3; i++) {
                WendigoEntity illusion = HexcraftEntities.WENDIGO.get().create(level());
                if (illusion != null) {
                    illusion.setIllusion(true); // Mark as illusion so it doesn't spawn more illusions
                    illusion.moveTo(this.getX() + (random.nextDouble() - 0.5) * 6, this.getY(), this.getZ() + (random.nextDouble() - 0.5) * 6);
                    illusion.setInvisible(true);
                    level().addFreshEntity(illusion);
                }
            }
            level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.WARDEN_HEARTBEAT, SoundSource.HOSTILE, 1.0F, 1.2F);
        }
    }

    private void stalkerMode(LivingEntity entity) {
        if (!level().isClientSide) {
            this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 400, 0));
            level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.HOSTILE, 1.0F, 0.5F);
        }
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean success = super.doHurtTarget(target);
        if (success && target instanceof LivingEntity) {
            ((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 1));
            this.triggerAttackAnimation();
        }
        return success;
    }

    private void triggerAttackAnimation() {
        this.triggerAnim("attack_controller", "attack");
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        if (!level().isClientSide) {
            if (random.nextFloat() < 0.50) {
                this.spawnAtLocation(Items.ROTTEN_FLESH);
            }
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "movement_controller", 5, state -> {
            if (state.isMoving()) {
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.wendigo.walk"));
            } else {
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.wendigo.idle"));
            }
        }));

        controllers.add(new AnimationController<>(this, "attack_controller", 0, state -> {
            return state.setAndContinue(WENDIGO_ATTACK);
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}