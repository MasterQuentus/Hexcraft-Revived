package net.masterquentus.hexcraftmod.entity.ai.goal;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.entity.FairyEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.nio.file.Path;
import java.util.EnumSet;

public class MoveToFairyLanternGoal extends Goal {
    private final FairyEntity fairy;
    private BlockPos targetLantern;
    private final double speed;

    public MoveToFairyLanternGoal(FairyEntity fairy, double speed) {
        this.fairy = fairy;
        this.speed = speed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (fairy.getRandom().nextInt(20) != 0) { // Check every second
            return false;
        }

        Level level = fairy.level();
        BlockPos fairyPos = fairy.blockPosition();
        int searchRadius = 10; // Scan within 10 blocks

        for (BlockPos pos : BlockPos.betweenClosed(fairyPos.offset(-searchRadius, -searchRadius, -searchRadius),
                fairyPos.offset(searchRadius, searchRadius, searchRadius))) {
            Block block = level.getBlockState(pos).getBlock();
            if (block == HexcraftBlocks.FAIRY_LANTERN.get()) {
                targetLantern = pos;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return targetLantern != null && fairy.distanceToSqr(targetLantern.getX(), targetLantern.getY(), targetLantern.getZ()) > 2.0D;
    }

    @Override
    public void start() {
        if (targetLantern != null) {
            fairy.getNavigation().moveTo(targetLantern.getX() + 0.5, targetLantern.getY() + 0.5, targetLantern.getZ() + 0.5, speed);
        }
    }

    @Override
    public void stop() {
        targetLantern = null;
        fairy.getNavigation().stop();
    }
}