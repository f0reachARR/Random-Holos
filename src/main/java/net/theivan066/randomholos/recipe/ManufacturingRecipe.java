package net.theivan066.randomholos.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

import java.util.stream.IntStream;

public class ManufacturingRecipe implements Recipe<RecipeWrapper> {

    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ItemStack additives;

    public ManufacturingRecipe(NonNullList<Ingredient> inputItems, ItemStack additives, ItemStack output) {
        this.inputItems = inputItems;
        this.additives = additives;
        this.output = output;
    }

    @Override
    public boolean matches(RecipeWrapper pContainer, Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }
        if (pContainer.size() < inputItems.size()) {
            return false;
        }
        return IntStream.range(0, inputItems.size())
                .allMatch(i -> (inputItems.get(i).isEmpty() && pContainer.getItem(i).isEmpty()) || inputItems.get(i).test(pContainer.getItem(i)))
                && pContainer.getItem(9).is(additives.getItem());
    }

    @Override
    public ItemStack assemble(RecipeWrapper simpleContainer, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.inputItems;
    }

    public ItemStack getAdditiveInputItem(RegistryAccess pRegistryAccess) {
        return additives.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.MANUFACTURING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.MANUFACTURING_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<ManufacturingRecipe> {
        private static final MapCodec<ManufacturingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst ->
                inst.group(
                        Ingredient.LIST_CODEC_NONEMPTY.fieldOf("ingredients").xmap(ingredients -> {
                            NonNullList<Ingredient> nonNullList = NonNullList.create();
                            nonNullList.addAll(ingredients);
                            if (nonNullList.size() != 9) {
                                // Fill the rest of the list with empty ingredients
                                for (int i = nonNullList.size(); i < 9; i++) {
                                    nonNullList.add(Ingredient.EMPTY);
                                }
                            }
                            return nonNullList;
                        }, ingredients -> ingredients).forGetter(ManufacturingRecipe::getIngredients),
                        ItemStack.STRICT_CODEC.fieldOf("additional_input").forGetter(r -> r.additives),
                        ItemStack.STRICT_CODEC.fieldOf("output").forGetter(r -> r.output)
                ).apply(inst, ManufacturingRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, ManufacturingRecipe> STREAM_CODEC =
                StreamCodec.of(ManufacturingRecipe.Serializer::toNetwork, ManufacturingRecipe.Serializer::fromNetwork);

        @Override
        public MapCodec<ManufacturingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ManufacturingRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        public static ManufacturingRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
            }
            ItemStack additives = ItemStack.STREAM_CODEC.decode(buf);

            ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
            return new ManufacturingRecipe(inputs, additives, output);
        }

        public static void toNetwork(RegistryFriendlyByteBuf buf, ManufacturingRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ing);
            }

            ItemStack.STREAM_CODEC.encode(buf, recipe.additives);
            ItemStack.STREAM_CODEC.encode(buf, recipe.output);
        }
    }
}
