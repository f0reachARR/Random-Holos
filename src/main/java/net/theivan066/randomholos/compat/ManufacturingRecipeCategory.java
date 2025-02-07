package net.theivan066.randomholos.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.block.ModBlocks;
import net.theivan066.randomholos.recipe.ManufacturingRecipe;


public class ManufacturingRecipeCategory implements IRecipeCategory<ManufacturingRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "manufacturing");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, "textures/gui/manufacturing_table_gui_jei.png");

    public static final RecipeType<ManufacturingRecipe> MANUFACTURING_TYPE =
            new RecipeType<>(UID, ManufacturingRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;

    public ManufacturingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.MANUFACTURING_TABLE.get()));
    }

    @Override
    public RecipeType<ManufacturingRecipe> getRecipeType() {
        return MANUFACTURING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("title.randomholos.manufacturing");
    }

//    @Override
//    public IDrawable getBackground() {
//        return this.background;
//    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ManufacturingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 14, 17).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 32, 17).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 50, 17).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 14, 35).addIngredients(recipe.getIngredients().get(3));
        builder.addSlot(RecipeIngredientRole.INPUT, 32, 35).addIngredients(recipe.getIngredients().get(4));
        builder.addSlot(RecipeIngredientRole.INPUT, 50, 35).addIngredients(recipe.getIngredients().get(5));
        builder.addSlot(RecipeIngredientRole.INPUT, 14, 53).addIngredients(recipe.getIngredients().get(6));
        builder.addSlot(RecipeIngredientRole.INPUT, 32, 53).addIngredients(recipe.getIngredients().get(7));
        builder.addSlot(RecipeIngredientRole.INPUT, 50, 53).addIngredients(recipe.getIngredients().get(8));

        builder.addSlot(RecipeIngredientRole.INPUT, 91, 35).addItemStack(recipe.getAdditiveInputItem(null));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 142, 35).addItemStack(recipe.getResultItem(null));
    }
}
