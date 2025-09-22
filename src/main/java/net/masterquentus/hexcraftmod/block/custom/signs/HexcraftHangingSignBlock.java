package net.masterquentus.hexcraftmod.block.custom.signs;

import net.masterquentus.hexcraftmod.block.entity.signs.HexcraftHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class HexcraftHangingSignBlock extends CeilingHangingSignBlock {
    public HexcraftHangingSignBlock(Properties pProperties, WoodType pType) {
        super(pProperties, pType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new HexcraftHangingSignBlockEntity(pPos, pState);
    }
}