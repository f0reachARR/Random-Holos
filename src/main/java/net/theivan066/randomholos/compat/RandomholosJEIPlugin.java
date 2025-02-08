package net.theivan066.randomholos.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.recipe.ManufacturingRecipe;
import net.theivan066.randomholos.recipe.ModRecipes;
import net.theivan066.randomholos.screen.ManufacturingTableScreen;

import java.util.List;

@JeiPlugin
public class RandomholosJEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new ManufacturingRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<ManufacturingRecipe> empoweringRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.MANUFACTURING_TYPE.get())
                .stream().map(RecipeHolder::value).toList();
        registration.addRecipes(ManufacturingRecipeCategory.MANUFACTURING_TYPE, empoweringRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(ManufacturingTableScreen.class, 72, 36, 13, 13,
                ManufacturingRecipeCategory.MANUFACTURING_TYPE);
        registration.addRecipeClickArea(ManufacturingTableScreen.class, 111, 35, 22, 15,
                ManufacturingRecipeCategory.MANUFACTURING_TYPE);
    }
}