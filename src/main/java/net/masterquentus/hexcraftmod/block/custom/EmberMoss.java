package net.masterquentus.hexcraftmod.block.custom;

import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

public class EmberMoss extends Block implements BonemealableBlock {

    public EmberMoss(Properties properties) {
        super(properties);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        // When an entity touches Ember Moss, set it on fire for 3 seconds.
        entity.setSecondsOnFire(3);
        super.entityInside(state, level, pos, entity);
    }

    /////////////////
    // BONEMEAL CODE
    /////////////////

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        // For simplicity, allow bonemeal on Ember Moss.
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        // Here, bonemeal always succeeds; you can adjust the chance if desired.
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        // This bonemeal burst will try many times to spread the moss around.
        for (int i = 0; i < 128; i++) {
            // Choose a nearby position (in a 5x3x5 area)
            BlockPos targetPos = pos.offset(
                    random.nextInt(5) - 2,
                    random.nextInt(3) - 1,
                    random.nextInt(5) - 2
            );
            BlockState targetState = level.getBlockState(targetPos);

            // If the target block is stone, convert it to Ember Moss.
            if (targetState.is(Blocks.STONE)) {
                level.setBlock(targetPos, this.defaultBlockState(), 3);
            }
            // If the target is already Ember Moss, try to add carpet or (rarely) a phoenix sapling above it.
            else if (targetState.is(this)) {
                BlockPos abovePos = targetPos.above();
                if (level.getBlockState(abovePos).isAir()) {
                    if (random.nextFloat() < 0.05F) {  // 5% chance to place a Phoenix Sapling.
                        level.setBlock(abovePos, HexcraftBlocks.PHOENIX_SAPLING.get().defaultBlockState(), 3);
                    } else {
                        level.setBlock(abovePos, HexcraftBlocks.EMBER_MOSS_CARPET.get().defaultBlockState(), 3);
                    }
                }
            }
        }
    }

    ////////////////////
    // RANDOM TICK CODE
    ////////////////////

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isClientSide) {
            // Try to spread up to 4 times per tick.
            for (int i = 0; i < 4; i++) {
                // Choose a nearby position (offset by -1 to +1 in each axis)
                BlockPos targetPos = pos.offset(
                        random.nextInt(3) - 1,
                        random.nextInt(3) - 1,
                        random.nextInt(3) - 1
                );
                BlockState targetState = level.getBlockState(targetPos);

                // Spread to blocks tagged as MOSS_REPLACEABLE: convert them into Ember Moss.
                if (targetState.is(BlockTags.MOSS_REPLACEABLE)) {
                    level.setBlock(targetPos, this.defaultBlockState(), 3);
                }
                // If the target is already Ember Moss, check above it to possibly add carpet or a phoenix sapling.
                else if (targetState.is(this)) {
                    BlockPos abovePos = targetPos.above();
                    if (level.getBlockState(abovePos).isAir()) {
                        if (random.nextFloat() < 0.05F) {  // 5% chance to place a Phoenix Sapling.
                            level.setBlock(abovePos, HexcraftBlocks.PHOENIX_SAPLING.get().defaultBlockState(), 3);
                        } else {
                            level.setBlock(abovePos, HexcraftBlocks.EMBER_MOSS_CARPET.get().defaultBlockState(), 3);
                        }
                    }
                }
            }
        }
    }
}