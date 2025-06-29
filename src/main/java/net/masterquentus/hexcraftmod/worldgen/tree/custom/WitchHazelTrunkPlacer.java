package net.masterquentus.hexcraftmod.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.masterquentus.hexcraftmod.block.HexcraftBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class WitchHazelTrunkPlacer extends TrunkPlacer {
    public static final Codec<WitchHazelTrunkPlacer> CODEC = RecordCodecBuilder.create(instance ->
            trunkPlacerParts(instance).apply(instance, WitchHazelTrunkPlacer::new));

    public WitchHazelTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return HexcraftTrunkPlacers.WITCH_HAZEL_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level,
                                                            BiConsumer<BlockPos, BlockState> blockSetter,
                                                            RandomSource random,
                                                            int trunkHeight,
                                                            BlockPos startPos,
                                                            TreeConfiguration config) {
        List<FoliagePlacer.FoliageAttachment> foliagePositions = new ArrayList<>();

        // Generate 3x3 thick trunk
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                for (int y = 0; y < trunkHeight; y++) {
                    BlockPos pos = startPos.offset(x, y, z);
                    placeLog(level, blockSetter, random, pos, config);
                }
            }
        }

        // Generate twisted branches extending outward at varying heights
        int branchStart = trunkHeight / 2;
        int branchEnd = trunkHeight - 3;

        for (int i = 0; i < 4; i++) { // 4 branches
            int branchY = branchStart + random.nextInt(branchEnd - branchStart);
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            BlockPos branchBase = startPos.offset(direction.getStepX(), branchY, direction.getStepZ());

            for (int j = 0; j < 3 + random.nextInt(3); j++) { // 3-5 block-long branches
                BlockPos branchPos = branchBase.offset(direction.getStepX() * j, j % 2 == 0 ? 1 : 0, direction.getStepZ() * j);
                placeLog(level, blockSetter, random, branchPos, config);
            }

            foliagePositions.add(new FoliagePlacer.FoliageAttachment(branchBase.offset(direction.getStepX() * 4, 1, direction.getStepZ() * 4), 2, false));
        }

        // Add foliage on the top of the trunk
        foliagePositions.add(new FoliagePlacer.FoliageAttachment(startPos.above(trunkHeight), 2, false));

        return foliagePositions;
    }
}

