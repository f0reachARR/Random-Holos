package net.theivan066.randomholos.block.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.theivan066.randomholos.worldgen.dimension.ModDimensions;
import net.theivan066.randomholos.worldgen.portal.KakuriyoTeleporter;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("deprecation")
public class KakuriyoPortalBlock extends Block {
    public KakuriyoPortalBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.canChangeDimensions()) {
            activatePortal(pPlayer, pPos);
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.CONSUME;
        }
    }
    private void activatePortal(Entity player, BlockPos pPos) {
        if (player.level() instanceof ServerLevel serverlevel) {
            MinecraftServer minecraftserver = serverlevel.getServer();
            ResourceKey<Level> resourcekey = player.level().dimension() == ModDimensions.KAKURIYO_LEVEL_KEY ?
                    Level.OVERWORLD : ModDimensions.KAKURIYO_LEVEL_KEY;

            ServerLevel portalDimension = minecraftserver.getLevel(resourcekey);
            if (portalDimension != null && !player.isPassenger()) {
                if(resourcekey == ModDimensions.KAKURIYO_LEVEL_KEY) {
                    player.changeDimension(portalDimension, new KakuriyoTeleporter(pPos, true));
                } else {
                    player.changeDimension(portalDimension, new KakuriyoTeleporter(pPos, false));
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        if(Screen.hasShiftDown() && Screen.hasAltDown()) {
            pTooltip.add(Component.translatable("tooltip.randomholos.kakuriyo.shift"));
            pTooltip.add(Component.translatable("tooltip.randomholos.kakuriyo.alt"));
        } else if(Screen.hasAltDown()) {
            pTooltip.add(Component.translatable("tooltip.randomholos.tooltip"));
            pTooltip.add(Component.translatable("tooltip.randomholos.kakuriyo.alt"));
        } else if(Screen.hasShiftDown()) {
            pTooltip.add(Component.translatable("tooltip.randomholos.kakuriyo.shift"));
            pTooltip.add(Component.translatable("tooltip.randomholos.tooltip.caution"));
        } else {
            pTooltip.add(Component.translatable("tooltip.randomholos.tooltip"));
            pTooltip.add(Component.translatable("tooltip.randomholos.tooltip.caution"));
        }
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
