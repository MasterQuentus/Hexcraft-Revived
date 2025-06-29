package net.masterquentus.hexcraftmod.entity.vampires.goals;

import net.masterquentus.hexcraftmod.entity.HexcraftEntities;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class DarkMagicProjectileEntity extends AbstractHurtingProjectile {
    public DarkMagicProjectileEntity(EntityType<? extends DarkMagicProjectileEntity> type, Level level) {
        super(type, level);
    }

    public DarkMagicProjectileEntity(Level level, LivingEntity shooter, double xPower, double yPower, double zPower) {
        super(HexcraftEntities.DARK_MAGIC_PROJECTILE.get(), shooter, xPower, yPower, zPower, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity target = result.getEntity();
        if (target instanceof LivingEntity livingTarget) {
            livingTarget.hurt(this.damageSources().magic(), 6.0F); // Use magic damage
            livingTarget.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 1)); // 3 sec Wither
        }
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        this.discard();
    }
}