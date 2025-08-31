package net.masterquentus.hexcraftmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class UnderworldTeleporter implements ITeleporter {
    private final ServerLevel level;
    private final BlockPos targetPos;

    public UnderworldTeleporter(ServerLevel level, BlockPos targetPos) {
        this.level = level;
        this.targetPos = targetPos;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentLevel, ServerLevel destinationLevel, float yaw, Function<Boolean, Entity> repositionEntity) {
        Entity newEntity = repositionEntity.apply(false);
        BlockPos pos = findSafeTeleportPos(destinationLevel, targetPos);
        newEntity.moveTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, entity.getYRot(), entity.getXRot());
        return newEntity;
    }

    private BlockPos findSafeTeleportPos(ServerLevel world, BlockPos target) {
        for (int y = world.getMinBuildHeight(); y < world.getMaxBuildHeight(); y++) {
            BlockPos pos = new BlockPos(target.getX(), y, target.getZ());
            if (world.getBlockState(pos).isAir() && world.getBlockState(pos.above()).isAir()) {
                return pos;
            }
        }
        return world.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, target);
    }
}