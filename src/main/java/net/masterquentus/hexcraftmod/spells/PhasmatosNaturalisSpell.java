package net.masterquentus.hexcraftmod.spells;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class PhasmatosNaturalisSpell extends HexSpell {

    private static final double RANGE = 6.0;

    @Override
    public String getId() {
        return "phasmatos_naturalis";
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
            BlockPos pos = result.getBlockPos();
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() instanceof BonemealableBlock growable) {
                if (growable.isValidBonemealTarget(level, pos, state, level.isClientSide()) &&
                        growable.isBonemealSuccess(level, level.random, pos, state)) {

                    if (!level.isClientSide) {
                        growable.performBonemeal(level, level.random, pos, state);
                        level.levelEvent(2005, pos, 0); // Bonemeal particles effect
                    }
                }
            }
        }
    }
}