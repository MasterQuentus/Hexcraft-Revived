package net.masterquentus.hexcraftmod.block.custom;

import net.masterquentus.hexcraftmod.block.entity.custom.AltarTopBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class AltarTopBlock extends Block implements EntityBlock {

    public AltarTopBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3.0f, 3.0f));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AltarTopBlockEntity(pos, state);  // Create the block entity for the top
    }
}