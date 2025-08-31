package net.masterquentus.hexcraftmod.spells;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SiccitasSpell extends HexSpell {
    private static final double RADIUS = 5.0;

    @Override
    public String getId() {
        return "siccitas";
    }

    @Override
    public void cast(ServerLevel level, Player caster) {
        Vec3 pos = caster.position();

        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class,
                new AABB(pos.x - RADIUS, pos.y - RADIUS, pos.z - RADIUS,
                        pos.x + RADIUS, pos.y + RADIUS, pos.z + RADIUS));

        // Get Holder<DamageType> for MAGIC damage
        Holder<DamageType> magicDamageType = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(DamageTypes.MAGIC);

        DamageSource magicDamage = new DamageSource(magicDamageType);

        for (LivingEntity entity : entities) {
            if (entity.isAlive() && entity != caster) {
                entity.hurt(magicDamage, 4.0F);
                entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1)); // 5 seconds of Wither
            }
        }

        level.playSound(null, caster.blockPosition(), SoundEvents.WITHER_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}