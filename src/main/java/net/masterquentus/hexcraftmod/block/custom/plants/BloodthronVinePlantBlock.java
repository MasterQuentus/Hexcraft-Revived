package net.masterquentus.hexcraftmod.block.custom.plants;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.TwistingVinesPlantBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BloodthronVinePlantBlock extends GrowingPlantBodyBlock {
    public static final VoxelShape SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);

    public BloodthronVinePlantBlock(BlockBehaviour.Properties p_154873_) {
        super(p_154873_, Direction.UP, SHAPE, false);
    }

    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) HexcraftBlocks.BLOODTHORN_VINES.get();
    }
}
