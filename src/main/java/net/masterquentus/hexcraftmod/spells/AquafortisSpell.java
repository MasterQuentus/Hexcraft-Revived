package net.masterquentus.hexcraftmod.spells;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class AquafortisSpell extends HexSpell {
    private static final double RANGE = 8.0;

    @Override
    public String getId() {
        return "aquafortis";
    }

    @Override
    public void cast(ServerLevel level, Player caster) {
        Vec3 look = caster.getLookAngle();
        Vec3 start = caster.position().add(0, caster.getEyeHeight(), 0);
        Vec3 end = start.add(look.scale(RANGE));

        BlockHitResult result = level.clip(new ClipContext(
                start, end,
                ClipContext.Block.OUTLINE,
                ClipContext.Fluid.NONE,
                caster
        ));

        if (result.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = result.getBlockPos().relative(result.getDirection());
            // extinguish fire if present
            if (level.getBlockState(pos).getBlock() == Blocks.FIRE) {
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                level.playSound(null, pos, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
            // spawn water splash particles anyway for effect
            ((ServerLevel) level).sendParticles(ParticleTypes.SPLASH, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
                    10, 0.3, 0.3, 0.3, 0.1);
        }
    }
}