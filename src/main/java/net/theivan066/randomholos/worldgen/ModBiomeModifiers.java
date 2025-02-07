package net.theivan066.randomholos.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.ModEntities;
import net.theivan066.randomholos.worldgen.biome.ModBiomes;

import java.util.List;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_TREE_MAPLE = registerKey("add_tree_maple");

    public static final ResourceKey<BiomeModifier> ADD_FALLEN_LEAVES = registerKey("add_fallen_leaves");

    public static final ResourceKey<BiomeModifier> ADD_TOURMALINE_ORE = registerKey("add_tourmaline_ore");

    public static final ResourceKey<BiomeModifier> SPAWN_SORA = registerKey("spawn_sora");
    public static final ResourceKey<BiomeModifier> SPAWN_NUNNUN = registerKey("spawn_nunnun");
    public static final ResourceKey<BiomeModifier> SPAWN_MIKO_MORIRIN = registerKey("spawn_miko_moririn");
    public static final ResourceKey<BiomeModifier> SPAWN_MIKO_SAKURA = registerKey("spawn_miko_sakura");
    public static final ResourceKey<BiomeModifier> SPAWN_MIKOP = registerKey("spawn_mikop");
    public static final ResourceKey<BiomeModifier> SPAWN_SUISEI = registerKey("spawn_suisei");
    public static final ResourceKey<BiomeModifier> SPAWN_AZKI = registerKey("spawn_azki");
    public static final ResourceKey<BiomeModifier> SPAWN_ROBOCO = registerKey("spawn_roboco");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_TREE_MAPLE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.MAPLE_FOREST)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.MAPLE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));


        context.register(ADD_FALLEN_LEAVES, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.MAPLE_FOREST)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.FALLEN_LEAVES_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));


        context.register(ADD_TOURMALINE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.TOURMALINE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));


        context.register(SPAWN_MIKO_MORIRIN, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.MORIRIN)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.MIKO.get(), 15, 1, 1))));

        context.register(SPAWN_MIKO_SAKURA, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.SAKURA_FOREST)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.MIKO.get(), 2, 1, 1))));

        context.register(SPAWN_MIKOP, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.MORIRIN), biomes.getOrThrow(ModBiomes.SAKURA_FOREST)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.MIKOP.get(), 10, 1, 8))));

        context.register(SPAWN_SUISEI, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.SHATTERED_GROUNDS)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.SUISEI.get(), 3, 1, 1))));

        context.register(SPAWN_AZKI, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.SHATTERED_GROUNDS)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.AZKI.get(), 3, 1, 1))));

        context.register(SPAWN_SORA, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.SKYLAND)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.SORA.get(), 1, 1, 1))));

        context.register(SPAWN_NUNNUN, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.SKYLAND)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.NUNNUN.get(), 3, 1, 8))));

        context.register(SPAWN_ROBOCO, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.SHATTERED_GROUNDS)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.ROBOCO.get(), 2, 1, 1))));
    }


    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(RandomHolos.MOD_ID, name));
    }
}
