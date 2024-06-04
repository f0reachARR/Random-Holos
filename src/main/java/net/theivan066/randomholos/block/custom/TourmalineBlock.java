package net.theivan066.randomholos.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("deprecation")
public class TourmalineBlock extends Block {
    public TourmalineBlock(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        super.tick(pState, pLevel, pPos, pRandom);
        if (pRandom.nextInt(5) == 0) {
            pLevel.sendParticles(ParticleTypes.WAX_OFF, pPos.getX() + pRandom.nextDouble(), pPos.getY()+ pRandom.nextDouble(), pPos.getZ()+ pRandom.nextDouble(), 12, 0, 0, 0, 1);
        }
    }
}
