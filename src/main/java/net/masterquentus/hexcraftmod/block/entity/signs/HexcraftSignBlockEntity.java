package net.masterquentus.hexcraftmod.block.entity.signs;

import net.masterquentus.hexcraftmod.block.entity.HexcraftBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HexcraftSignBlockEntity extends SignBlockEntity {
    public HexcraftSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(HexcraftBlockEntities.HEXCRAFT_SIGN.get(),pPos, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return HexcraftBlockEntities.HEXCRAFT_SIGN.get();
    }
}
