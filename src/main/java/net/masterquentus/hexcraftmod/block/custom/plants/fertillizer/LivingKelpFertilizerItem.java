package net.masterquentus.hexcraftmod.block.custom.plants.fertillizer;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

public class LivingKelpFertilizerItem extends Item {
    public LivingKelpFertilizerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());

        if (applyFertilizer(level, pos)) {
            context.getItemInHand().shrink(1);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    private boolean applyFertilizer(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof BonemealableBlock) {
            BonemealableBlock plant = (BonemealableBlock) state.getBlock();
            if (plant.isValidBonemealTarget(level, pos, state, level.isClientSide)) {
                if (level instanceof ServerLevel) {
                    if (plant.isBonemealSuccess(level, level.random, pos, state)) {
                        plant.performBonemeal((ServerLevel) level, level.random, pos, state);
                    }
                }
                return true;
            }
        }
        return false;
    }
}