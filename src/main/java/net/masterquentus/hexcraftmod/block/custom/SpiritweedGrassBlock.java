package net.masterquentus.hexcraftmod.block.custom;

import net.masterquentus.hexcraftmod.block.HexcraftBlockStateProperties;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.masterquentus.hexcraftmod.mixin.mixins.common.accessor.SpreadingSnowyDirtBlockAccessor;
import net.masterquentus.hexcraftmod.worldgen.HexcraftPlacedFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class SpiritweedGrassBlock extends GrassBlock {
    public SpiritweedGrassBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(HexcraftBlockStateProperties.DOUBLE_DROPS, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(HexcraftBlockStateProperties.DOUBLE_DROPS);
    }

    @Override
    public boolean onTreeGrow(BlockState state, LevelReader level, BiConsumer<BlockPos, BlockState> placeFunction, RandomSource randomSource, BlockPos pos, TreeConfiguration config) {
        placeFunction.accept(pos, HexcraftBlocks.SPIRITWEED_GRASS_BLOCK.get().defaultBlockState().setValue(HexcraftBlockStateProperties.DOUBLE_DROPS, state.getValue(HexcraftBlockStateProperties.DOUBLE_DROPS)));
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getBlockState(pos.above()).is(HexcraftBlocks.SPIRITWEED_GRASS_BLOCK.get())) {
            return;
        }

        // Check if the position above is a solid block or flower pot
        BlockState blockAbove = level.getBlockState(pos.above());
        if (!blockAbove.isAir() && !(blockAbove.getBlock() instanceof SnowLayerBlock)) {
            return; // Stop spreading if the block above is occupied
        }

        if (level == null || pos == null || state == null) {
            return;
        }

        // Fix: Ensure `invokeCanBeGrass()` works correctly and handle null checks
        boolean canGrass = false;
        try {
            // Create an instance of SpreadingSnowyDirtBlockAccessor to call the method
            SpreadingSnowyDirtBlockAccessor accessor = new SpreadingSnowyDirtBlockAccessor();
            canGrass = accessor.invokeCanBeGrass(state, level, pos); // Use the instance method
        } catch (Exception e) {
            // Log or handle the exception if invokeCanBeGrass() fails
            System.err.println("Error in invokeCanBeGrass: " + e.getMessage());
        }

        if (!canGrass) {
            if (level != null && !level.isAreaLoaded(pos, 3)) return; // Prevent loading unloaded chunks
            level.setBlockAndUpdate(pos, HexcraftBlocks.SPIRITWEED_DIRT.get().defaultBlockState());
        } else {
            if (level != null && !level.isAreaLoaded(pos, 3)) return; // Prevent loading unloaded chunks
            if (level.getMaxLocalRawBrightness(pos.above()) >= 9) { // Check if there's enough light
                for (int i = 0; i < 4; ++i) {
                    BlockPos targetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    BlockState targetState = level.getBlockState(targetPos);


                    if (targetState.is(HexcraftBlocks.SPIRITWEED_DIRT.get())) {
                        level.setBlockAndUpdate(targetPos, this.defaultBlockState().setValue(SNOWY, level.getBlockState(targetPos.above()).is(Blocks.SNOW)));
                    }
                }
            }
        }
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockPos abovePos = pos.above();
        Block grass = HexcraftBlocks.SPIRITWEED_GRASS_BLOCK.get();
        Optional<Holder.Reference<PlacedFeature>> grassFeatureOptional = level.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(HexcraftPlacedFeatures.SPIRITWEED_GRASS_BONEMEAL);

        start:
        for (int i = 0; i < 128; ++i) {
            BlockPos blockPos = abovePos;

            for (int j = 0; j < i / 16; ++j) {
                blockPos = blockPos.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!level.getBlockState(blockPos.below()).is(this) || level.getBlockState(blockPos).isCollisionShapeFullBlock(level, blockPos)) {
                    continue start;
                }
            }

            BlockState blockState = level.getBlockState(blockPos);
            if (blockState.is(grass) && random.nextInt(10) == 0) {
                ((BonemealableBlock) grass).performBonemeal(level, random, blockPos, blockState);
            }

            if (blockState.isAir()) {
                Holder<PlacedFeature> featureHolder;
                if (random.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> list = level.getBiome(blockPos).value().getGenerationSettings().getFlowerFeatures();
                    if (list.isEmpty()) {
                        continue;
                    }
                    featureHolder = ((RandomPatchConfiguration) list.get(random.nextInt(list.size())).config()).feature();
                } else {
                    if (grassFeatureOptional.isEmpty()) {
                        continue;
                    }
                    featureHolder = grassFeatureOptional.get();
                }
                featureHolder.value().place(level, level.getChunkSource().getGenerator(), random, blockPos);
            }
        }
    }
}