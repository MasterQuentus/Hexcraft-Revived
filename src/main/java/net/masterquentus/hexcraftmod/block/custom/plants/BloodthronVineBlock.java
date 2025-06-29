package net.masterquentus.hexcraftmod.block.custom.plants;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.item.HexcraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BloodthronVineBlock extends GrowingPlantHeadBlock {
    public static final VoxelShape SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 15.0, 12.0);

    public BloodthronVineBlock(BlockBehaviour.Properties p_154864_) {
        super(p_154864_, Direction.UP, SHAPE, false, 0.1);
    }

    public ItemStack getCloneItemStack(BlockGetter getter, BlockPos pos, BlockState state) {
        return new ItemStack(HexcraftItems.BLOODTHORN_VINES_ITEM.get());
    }

    protected int getBlocksToGrowWhenBonemealed(RandomSource p_222649_) {
        return NetherVines.getBlocksToGrowWhenBonemealed(p_222649_);
    }

    protected Block getBodyBlock() {
        return HexcraftBlocks.BLOODTHORN_VINES_PLANT.get();
    }

    protected boolean canGrowInto(BlockState p_154869_) {
        return NetherVines.isValidGrowthState(p_154869_);
    }
}
