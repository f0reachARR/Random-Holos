package net.theivan066.randomholos.worldgen.tree.trunk_placer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class BushTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<BushTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(mapleTrunkPlacerInstance ->
            trunkPlacerParts(mapleTrunkPlacerInstance).apply(mapleTrunkPlacerInstance, BushTrunkPlacer::new));

    public BushTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.BUSH_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
        placeLog(pLevel, pBlockSetter, pRandom, pPos.above(1), pConfig);
        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pPos.above(1), 0, false));
    }
}

