package net.masterquentus.hexcraftmod.util;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

public class UnderworldPortalShape {

    private final ServerLevel level;
    private final Direction.Axis axis;
    private final BlockPos bottomLeft;
    private final int height;
    private final int width;

    public UnderworldPortalShape(ServerLevel level, BlockPos pos, Direction.Axis axis) {
        this.level = level;
        this.axis = axis;
        this.bottomLeft = pos;
        this.width = calculateWidth();
        this.height = calculateHeight();
    }

    private int calculateWidth() {
        // check for frame width with your BLACK_OBSIDIAN blocks
        // implement logic to detect frame width here
        return 0;
    }

    private int calculateHeight() {
        // check for frame height with your BLACK_OBSIDIAN blocks
        // implement logic to detect frame height here
        return 0;
    }

    public boolean isComplete() {
        return width >= 2 && width <= 21 && height >= 3 && height <= 21;
    }

    public void createPortalBlocks() {
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                BlockPos pos = bottomLeft.offset(axis == Direction.Axis.X ? x : 0, y, axis == Direction.Axis.Z ? x : 0);
                level.setBlock(pos, HexcraftBlocks.UNDERWORLD_PORTAL.get().defaultBlockState(), 2);
            }
        }
    }

    public static UnderworldPortalShape findPortalShape(Level level, BlockPos pos) {
        for (Direction.Axis axis : Direction.Axis.values()) {
            UnderworldPortalShape shape = new UnderworldPortalShape((ServerLevel) level, pos, axis);
            if (shape.isComplete()) {
                return shape;
            }
        }
        return null;
    }
}