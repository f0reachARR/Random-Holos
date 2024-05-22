package net.theivan066.randomholos.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.ModEntities;
import net.theivan066.randomholos.worldgen.biome.ModBiomes;

import java.util.List;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_TREE_MAPLE = registerKey("add_tree_maple");

    public static final ResourceKey<BiomeModifier> ADD_FALLEN_LEAVES = registerKey("add_fallen_leaves");

    public static final ResourceKey<BiomeModifier> SPAWN_MIKO_MORIRIN = registerKey("spawn_miko_moririn");
    public static final ResourceKey<BiomeModifier> SPAWN_MIKO_SAKURA = registerKey("spawn_miko_sakura");
    public static final ResourceKey<BiomeModifier> SPAWN_MIKOP = registerKey("spawn_mikop");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_TREE_MAPLE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.MAPLE_FOREST)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.MAPLE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));


        context.register(ADD_FALLEN_LEAVES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.MAPLE_FOREST)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.FALLEN_LEAVES_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(SPAWN_MIKO_MORIRIN, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.MORIRIN)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.MIKO.get(), 15, 1, 1))));

        context.register(SPAWN_MIKO_SAKURA, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.SAKURA_FOREST)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.MIKO.get(), 2, 1, 1))));

        context.register(SPAWN_MIKOP, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.MORIRIN), biomes.getOrThrow(ModBiomes.SAKURA_FOREST)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.MIKOP.get(), 10, 1, 8))));

    }


    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(RandomHolos.MOD_ID, name));
    }
}
