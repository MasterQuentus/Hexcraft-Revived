package net.masterquentus.hexcraftmod.spells;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class LuxAeternamSpell extends HexSpell {

    private static final double RADIUS = 5.0;

    @Override
    public String getId() {
        return "lux_aeternam";
    }

    @Override
    public void cast(ServerLevel level, Player caster) {
        Vec3 pos = caster.position().add(0, caster.getEyeHeight(), 0);

        level.playSound(null, caster.blockPosition(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1f, 1f);

        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, new AABB(
                pos.x - RADIUS, pos.y - RADIUS, pos.z - RADIUS,
                pos.x + RADIUS, pos.y + RADIUS, pos.z + RADIUS));

        // Get Holder<DamageType> from the registry
        Holder<DamageType> magicDamageType = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(DamageTypes.MAGIC);

        DamageSource magicDamage = new DamageSource(magicDamageType);

        for (LivingEntity entity : entities) {
            if (entity instanceof Monster && entity.isAlive() && entity.isAffectedByPotions()) {
                entity.hurt(magicDamage, 6.0F);
            }
        }
    }
}