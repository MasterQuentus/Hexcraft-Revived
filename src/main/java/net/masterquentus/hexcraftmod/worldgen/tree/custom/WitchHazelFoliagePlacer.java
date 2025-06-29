package net.masterquentus.hexcraftmod.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;


public class WitchHazelFoliagePlacer extends FoliagePlacer {
    public static final Codec<WitchHazelFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            foliagePlacerParts(instance).apply(instance, WitchHazelFoliagePlacer::new));

    public WitchHazelFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return HexcraftFoliagePlacers.WITCH_HAZEL_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level,
                                 FoliageSetter foliageSetter,
                                 RandomSource random,
                                 TreeConfiguration config,
                                 int trunkHeight,
                                 FoliageAttachment attachment,
                                 int foliageHeight,
                                 int radius,
                                 int offset) {

        BlockPos center = attachment.pos();

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                for (int y = -foliageHeight; y <= foliageHeight; y++) {
                    BlockPos leafPos = center.offset(x, y, z);

                    // Ensuring leaves are connected to logs within 4 blocks to prevent decay
                    if (random.nextFloat() < 0.85 && isNearLog(level, leafPos)) {
                        foliageSetter.set(leafPos, config.foliageProvider.getState(random, leafPos));
                    }
                }
            }
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 4; // Adjust to make sure foliage stays close to branches
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int x, int y, int z, int radius, boolean large) {
        return false;
    }

    private boolean isNearLog(LevelSimulatedReader level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (level.isStateAtPosition(pos.relative(direction), state -> state.is(BlockTags.LOGS))) {
                return true; // Prevents leaves from decaying if near a log
            }
        }
        return false;
    }
}



