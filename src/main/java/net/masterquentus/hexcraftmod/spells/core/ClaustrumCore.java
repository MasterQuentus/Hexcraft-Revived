package net.masterquentus.hexcraftmod.spells.core;

import net.masterquentus.hexcraftmod.spells.CoreComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class ClaustrumCore implements CoreComponent {
    private static final double RANGE = 10.0;
    private static final int DURATION_TICKS = 20 * 30; // 30 seconds

    @Override
    public String getId() {
        return "claustrum";
    }

    @Override
    public void applyEffect(ServerLevel level, Player caster) {
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

            List<BlockPos> cageBlocks = new ArrayList<>();

            for (int y = 0; y < 3; y++) {
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        if (Math.abs(x) == 1 || Math.abs(z) == 1) {
                            BlockPos cagePos = pos.offset(x, y, z);
                            if (level.isEmptyBlock(cagePos)) {
                                level.setBlockAndUpdate(cagePos, Blocks.IRON_BARS.defaultBlockState());
                                cageBlocks.add(cagePos.immutable());
                            }
                        }
                    }
                }
            }

            level.playSound(null, pos, SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 1.0F, 0.8F);

            MinecraftServer server = level.getServer();

            // Define the removal task as a Runnable with state:
            Runnable removalTask = new Runnable() {
                private int ticksPassed = 0;

                @Override
                public void run() {
                    if (ticksPassed >= DURATION_TICKS) {
                        for (BlockPos cagePos : cageBlocks) {
                            if (level.getBlockState(cagePos).is(Blocks.IRON_BARS)) {
                                level.removeBlock(cagePos, false);
                            }
                        }
                        level.playSound(null, pos, SoundEvents.ANVIL_BREAK, SoundSource.BLOCKS, 1.0F, 0.8F);
                    } else {
                        ticksPassed++;
                        // Re-schedule itself after 1 tick delay
                        server.tell(new TickTask(1, this));
                    }
                }
            };

            // Schedule the first execution after 1 tick
            server.tell(new TickTask(1, removalTask));
        }
    }
}