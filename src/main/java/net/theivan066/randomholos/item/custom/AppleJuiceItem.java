package net.theivan066.randomholos.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

public class AppleJuiceItem extends ShiftTooltipItem{
    public AppleJuiceItem(Properties pProperties, Component discription) {
        super(pProperties, discription);
    }
    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }
}

