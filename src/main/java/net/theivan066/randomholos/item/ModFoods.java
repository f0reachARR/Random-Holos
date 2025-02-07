package net.theivan066.randomholos.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties BAGUETTE = new FoodProperties.Builder().nutrition(9).saturationModifier(11f).build();
    public static final FoodProperties HUMONGOUS_BAGUETTE = new FoodProperties.Builder().nutrition(18).saturationModifier(17f).build();
    public static final FoodProperties GARGANTUAN_BAGUETTE = new FoodProperties.Builder().nutrition(36).saturationModifier(34f).build();
    public static final FoodProperties CHIVES = new FoodProperties.Builder().nutrition(1).saturationModifier(0.75f).build();
    public static final FoodProperties SCALLION = new FoodProperties.Builder().nutrition(1).saturationModifier(0.75f).build();
    public static final FoodProperties CHINESE_CABBAGE = new FoodProperties.Builder().nutrition(3).saturationModifier(1.25f).build();
    public static final FoodProperties RED_CHILI_PEPPER = new FoodProperties.Builder().nutrition(1).saturationModifier(0.25f).build();
    public static final FoodProperties GREEN_CHILI_PEPPER = new FoodProperties.Builder().nutrition(1).saturationModifier(0.25f).build();
    public static final FoodProperties GHOST_PEPPER = new FoodProperties.Builder().nutrition(2).saturationModifier(0.25f).build();
    public static final FoodProperties KIMCHI = new FoodProperties.Builder().nutrition(5).saturationModifier(2f).build();
    public static final FoodProperties SODA_WATER = new FoodProperties.Builder().nutrition(1).saturationModifier(0.5f).build();
    public static final FoodProperties TOKINO_SODA = new FoodProperties.Builder().nutrition(2).saturationModifier(1f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0), 1f).build();
    public static final FoodProperties COOKED_RICE = new FoodProperties.Builder().nutrition(6).saturationModifier(6.75f).build();
    public static final FoodProperties SHRIMP = new FoodProperties.Builder().nutrition(1).saturationModifier(1f).fast().build();
    public static final FoodProperties COOKED_SHRIMP = new FoodProperties.Builder().nutrition(2).saturationModifier(1.5f).fast().build();
    public static final FoodProperties EBI_KATSU = new FoodProperties.Builder().nutrition(8).saturationModifier(6.5f).build();
    public static final FoodProperties CHOCOLATE = new FoodProperties.Builder().nutrition(1).saturationModifier(0f).build();
    public static final FoodProperties CHOCOLATE_CAKE = new FoodProperties.Builder().nutrition(10).saturationModifier(8f).build();
    public static final FoodProperties CURRY = new FoodProperties.Builder().nutrition(5).saturationModifier(3f).build();
    public static final FoodProperties CURRY_BREAD = new FoodProperties.Builder().nutrition(8).saturationModifier(7.5f).build();
    public static final FoodProperties APPLE_JUICE = new FoodProperties.Builder().nutrition(4).saturationModifier(2f).build();
    public static final FoodProperties TOKINOSORA_SET_MEAL_A = new FoodProperties.Builder().nutrition(18).saturationModifier(10f)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 12000, 2), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 12000, 0), 1f).build();
    public static final FoodProperties TOKINOSORA_SET_MEAL_B = new FoodProperties.Builder().nutrition(12).saturationModifier(8.5f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000, 0), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.LUCK, 6000, 1), 1f).build();


}
