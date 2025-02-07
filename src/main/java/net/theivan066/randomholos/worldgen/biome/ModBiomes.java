package net.theivan066.randomholos.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.worldgen.biome.custom.*;

public class ModBiomes {
    public static final ResourceKey<Biome> SAKURA_FOREST = register("sakura_forest");
    public static final ResourceKey<Biome> MAPLE_FOREST = register("maple_forest");
    public static final ResourceKey<Biome> SHATTERED_GROUNDS = register("shattered_grounds");
    public static final ResourceKey<Biome> LAND_OF_SOULS = register("land_of_souls");
    public static final ResourceKey<Biome> SNOWY_LANDS = register("snowy_lands");
    public static final ResourceKey<Biome> MORIRIN = register("moririn");
    public static final ResourceKey<Biome> SKYLAND = register("skyland");
    public static final ResourceKey<Biome> THE_MARINE = register("the_marine");

    public static void boostrap(BootstrapContext<Biome> context) {
        context.register(SAKURA_FOREST, SakuraForestBiome.sakuraForest(context));
        context.register(MAPLE_FOREST, MapleForestBiome.mapleForest(context));
        context.register(SHATTERED_GROUNDS, ShatteredGroundsBiome.shatteredGrounds(context));
        context.register(LAND_OF_SOULS, LandOfSoulsBiome.landOfSouls(context));
        context.register(SNOWY_LANDS, SnowyLandsBiome.snowyLands(context));
        context.register(MORIRIN, MoririnBiome.moririn(context));
        context.register(SKYLAND, SkylandBiome.skyland(context));
        context.register(THE_MARINE, TheMarineBiome.theMarine(context));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(RandomHolos.MOD_ID, name));
    }
}
