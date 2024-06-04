package net.theivan066.randomholos.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({"deprecation", "dep-ann"})
public class KimchiHotpotBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final int MAX_BITES = 3;
    public static final IntegerProperty TIMES_EATEN = IntegerProperty.create("times_eaten", 0, 3);;
    public KimchiHotpotBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(TIMES_EATEN, Integer.valueOf(0)));
    }

    public static final VoxelShape SHAPE = Block.box(3, 3, 3, 13, 5, 13);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     * @deprecated call via {@link BlockStateBase#mirror} whenever
     * possible. Implementing/overriding is fine.
     */
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
        pBuilder.add(TIMES_EATEN);
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    //eat
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (pLevel.isClientSide) {
            if (eat(pLevel, pPos, pState, pPlayer).consumesAction()) {
                return InteractionResult.SUCCESS;
            }
            if (itemstack.isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }
        return eat(pLevel, pPos, pState, pPlayer);
    }

    protected static InteractionResult eat(LevelAccessor pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (!pPlayer.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            int i = pState.getValue(TIMES_EATEN);
            if (i < 3) {
                pPlayer.getFoodData().eat(6, 3F);
                pPlayer.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0));
                pLevel.gameEvent(pPlayer, GameEvent.EAT, pPos);
                pLevel.setBlock(pPos, pState.setValue(TIMES_EATEN, Integer.valueOf(i + 1)), 3);
            } else {
                return InteractionResult.FAIL;
            }
            return InteractionResult.SUCCESS;
        }
    }

    public int getAnalogOutputSignal(BlockState pBlockState, Level pLevel, BlockPos pPos) {
        return getOutputSignal(pBlockState.getValue(TIMES_EATEN));
    }

    public static int getOutputSignal(int pEaten) {
        return (3 - pEaten) * 5;
    }
}
