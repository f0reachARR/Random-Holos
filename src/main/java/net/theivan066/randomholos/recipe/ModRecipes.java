package net.theivan066.randomholos.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.theivan066.randomholos.RandomHolos;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, RandomHolos.MOD_ID);

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, RandomHolos.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ManufacturingRecipe>> MANUFACTURING_SERIALIZER =
            SERIALIZERS.register("manufacturing", ManufacturingRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<ManufacturingRecipe>> MANUFACTURING_TYPE =
            TYPES.register("manufacturing", () -> registerRecipeType("manufacturing"));

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
        return new RecipeType<>() {
            public String toString() {
                return RandomHolos.MOD_ID + ":" + identifier;
            }
        };
    }
}
