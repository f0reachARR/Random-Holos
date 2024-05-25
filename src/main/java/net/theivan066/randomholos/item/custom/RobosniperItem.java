package net.theivan066.randomholos.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RobosniperItem extends GunItem{
    public RobosniperItem(Properties pProperties, float gunDamage, float bulletSpeed, int rateOfFire, int magSize, Item ammoType, int reloadCooldown, float[] bulletSpread, float[] gunRecoil, int pelletCount, SoundEvent reloadSound, SoundEvent shootSound, int reloadCycles, boolean isScoped, boolean unscopeAfterShot, int reloadStage1, int reloadStage2, int reloadStage3, LoadingType loadingType, FiringType firingType) {
        super(pProperties, gunDamage, bulletSpeed, rateOfFire, magSize, ammoType, reloadCooldown, bulletSpread, gunRecoil, pelletCount, reloadSound, shootSound, reloadCycles, isScoped, unscopeAfterShot, reloadStage1, reloadStage2, reloadStage3, loadingType, firingType);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.randomholos.robosniper.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.randomholos.tooltip"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
