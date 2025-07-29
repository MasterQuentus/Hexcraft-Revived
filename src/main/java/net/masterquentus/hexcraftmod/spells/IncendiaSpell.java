package net.masterquentus.hexcraftmod.spells;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class IncendiaSpell extends HexSpell {

    private static final double RANGE = 8.0;

    @Override
    public String getId() {
        return "incendia";
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
            if (level.getBlockState(pos).isAir()) {
                level.setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
                level.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        }
    }
}