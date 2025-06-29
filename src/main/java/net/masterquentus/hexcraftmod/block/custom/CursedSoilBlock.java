package net.masterquentus.hexcraftmod.block.custom;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class CursedSoilBlock extends Block {
    public CursedSoilBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // 30% chance per tick to spread
        if (random.nextFloat() < 0.3F) {
            for (Direction direction : Direction.values()) {
                BlockPos spreadPos = pos.relative(direction);
                BlockState spreadState = level.getBlockState(spreadPos);

                if (spreadState.is(Blocks.DIRT) || spreadState.is(Blocks.GRASS_BLOCK)) {
                    level.setBlockAndUpdate(spreadPos, HexcraftBlocks.CURSED_SOIL.get().defaultBlockState());
                }
            }
        }

        super.randomTick(state, level, pos, random);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
        }
        super.stepOn(level, pos, state, entity);
    }
}