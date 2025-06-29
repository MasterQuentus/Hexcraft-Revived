package net.masterquentus.hexcraftmod.worldgen.tree;

import com.mojang.serialization.Codec;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class HangingLeavesFeature extends Feature<TreeConfiguration> {
    public HangingLeavesFeature(Codec<TreeConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
        LevelAccessor level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();
        TreeConfiguration config = context.config();

        // Loop through the tree's existing leaves
        for (BlockPos leafPos : BlockPos.betweenClosed(pos.offset(-3, 0, -3), pos.offset(3, 5, 3))) {
            if (level.getBlockState(leafPos).is(config.foliageProvider.getState(random, leafPos).getBlock())) {
                // Chance to place hanging leaves under each leaf block
                BlockPos hangingPos = leafPos.below();
                if (level.isEmptyBlock(hangingPos) && random.nextFloat() < 0.6F) { // 60% chance for hanging leaves
                    level.setBlock(hangingPos, HexcraftBlocks.WILLOW_LEAVES.get().defaultBlockState(), 2);
                }
            }
        }
        return true;
    }
}