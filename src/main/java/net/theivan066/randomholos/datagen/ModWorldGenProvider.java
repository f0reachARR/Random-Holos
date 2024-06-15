package net.theivan066.randomholos.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.worldgen.ModBiomeModifiers;
import net.theivan066.randomholos.worldgen.ModConfiguredFeatures;
import net.theivan066.randomholos.worldgen.ModPlacedFeatures;
import net.theivan066.randomholos.worldgen.biome.ModBiomes;
import net.theivan066.randomholos.worldgen.dimension.ModDimensions;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            .add(Registries.BIOME, ModBiomes::boostrap)
            .add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem)
            .add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType)
            .add(Registries.NOISE_SETTINGS, ModDimensions::bootstrapNoise)
            .add(Registries.DENSITY_FUNCTION, ModDimensions::bootstrapDensity);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(RandomHolos.MOD_ID));
    }
}

