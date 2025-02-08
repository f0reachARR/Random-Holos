package net.theivan066.randomholos.block.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.theivan066.randomholos.block.ModBlocks;
import net.theivan066.randomholos.worldgen.dimension.ModDimensions;

import java.util.List;
import java.util.Optional;

public class KakuriyoPortalBlock extends Block {
    public KakuriyoPortalBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            boolean canTeleport = player.canUsePortal(false);
            if (!canTeleport) {
                player.displayClientMessage(Component.literal("You can't teleport from this dimension"), true);
            } else {
                ServerLevel destinationWorld = level.dimension() == ModDimensions.KAKURIYO_LEVEL_KEY
                        ? level.getServer().getLevel(Level.OVERWORLD)
                        : level.getServer().getLevel(ModDimensions.KAKURIYO_LEVEL_KEY);

                BlockPos destinationPos = setupPortal(pos, destinationWorld);
                DimensionTransition transition = new DimensionTransition(destinationWorld, destinationPos.getCenter(),
                        Vec3.ZERO, 0.0F, 0.0F, false, DimensionTransition.PLAY_PORTAL_SOUND);

                player.changeDimension(transition);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }


    private Optional<BlockPos> findDestination(BlockPos thisPos, Level destinationWorld) {
        BlockPos destinationPos = thisPos;

        int tries = 0;
        while ((destinationWorld.getBlockState(destinationPos).getBlock() != Blocks.AIR) &&
                !destinationWorld.getBlockState(destinationPos).canBeReplaced(Fluids.WATER) &&
                (destinationWorld.getBlockState(destinationPos.above()).getBlock() != Blocks.AIR) &&
                !destinationWorld.getBlockState(destinationPos.above()).canBeReplaced(Fluids.WATER) && (tries < 25)) {
            destinationPos = destinationPos.above(2);
            tries++;
        }

        return Optional.of(destinationPos);
    }

    private BlockPos setupPortal(BlockPos thisPos, Level destinationWorld) {
        // Find a destination above
        Optional<BlockPos> destinationPos = findDestination(thisPos, destinationWorld);

        if (destinationPos.isEmpty()) {
            // Try to find a destination below
            destinationPos = findDestination(thisPos.atY(-64), destinationWorld);
        }

        if (destinationPos.isEmpty()) {
            return thisPos;
        }

        // Find a portal block in the area
        for (BlockPos checkPos : BlockPos.betweenClosed(destinationPos.get().below(10).west(10),
                destinationPos.get().above(10).east(10))) {
            if (destinationWorld.getBlockState(checkPos).getBlock() instanceof KakuriyoPortalBlock) {
                return destinationPos.get().above();
            }
        }

        destinationWorld.setBlock(destinationPos.get(), ModBlocks.KAKURIYO_PORTAL.get().defaultBlockState(), 3);

        return destinationPos.get().above();
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        if (Screen.hasShiftDown() && Screen.hasAltDown()) {
            pTooltip.add(Component.translatable("tooltip.randomholos.kakuriyo.shift"));
            pTooltip.add(Component.translatable("tooltip.randomholos.kakuriyo.alt"));
        } else if (Screen.hasAltDown()) {
            pTooltip.add(Component.translatable("tooltip.randomholos.tooltip"));
            pTooltip.add(Component.translatable("tooltip.randomholos.kakuriyo.alt"));
        } else if (Screen.hasShiftDown()) {
            pTooltip.add(Component.translatable("tooltip.randomholos.kakuriyo.shift"));
            pTooltip.add(Component.translatable("tooltip.randomholos.tooltip.caution"));
        } else {
            pTooltip.add(Component.translatable("tooltip.randomholos.tooltip"));
            pTooltip.add(Component.translatable("tooltip.randomholos.tooltip.caution"));
        }
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
