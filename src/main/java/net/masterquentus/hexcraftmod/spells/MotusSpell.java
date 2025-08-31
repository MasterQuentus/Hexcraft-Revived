package net.masterquentus.hexcraftmod.spells;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class MotusSpell extends HexSpell {

    private static final double RANGE = 8.0;
    private static final double FORCE = 1.0;

    @Override
    public String getId() {
        return "motus";
    }

    @Override
    public void cast(ServerLevel level, Player caster) {
        Vec3 look = caster.getLookAngle();
        Vec3 start = caster.position().add(0, caster.getEyeHeight(), 0);
        Vec3 end = start.add(look.scale(RANGE));

        // Find first entity in sight within range
        EntityHitResult entityResult = ProjectileUtil.getEntityHitResult(level, caster, start, end, caster.getBoundingBox().expandTowards(look.scale(RANGE)), e -> e.isAlive() && e != caster);
        if (entityResult != null) {
            Entity entity = entityResult.getEntity();
            // Push entity away from caster
            Vec3 pushVec = entity.position().subtract(caster.position()).normalize().scale(FORCE);
            entity.setDeltaMovement(pushVec);
            entity.hurtMarked = true;
            level.playSound(null, caster.blockPosition(), SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 1f, 1f);
            return;
        }

        // Else try to push block if air in front
        BlockHitResult blockResult = level.clip(new ClipContext(
                start, end,
                ClipContext.Block.OUTLINE,
                ClipContext.Fluid.NONE,
                caster
        ));
        if (blockResult.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = blockResult.getBlockPos();
            BlockPos posInFront = pos.relative(blockResult.getDirection());
            if (level.isEmptyBlock(posInFront)) {
                BlockState state = level.getBlockState(pos);
                if (!state.isAir()) {
                    level.setBlockAndUpdate(posInFront, state);
                    level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                    level.playSound(null, posInFront, SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 1f, 1f);
                }
            }
        }
    }
}