package net.theivan066.randomholos.worldgen.biome.custom;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.theivan066.randomholos.worldgen.biome.ModBiomes;

public class TheMarineBiome {
    public static Biome theMarine(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeDefaultFeatures.oceanSpawns(spawnBuilder, 2, 3, 5);
        BiomeDefaultFeatures.warmOceanSpawns(spawnBuilder, 2, 3);
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);
        BiomeDefaultFeatures.monsters(spawnBuilder, 95, 5, 20, false);
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DOLPHIN, 4, 1, 3));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        ModBiomes.globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addLukeWarmKelp(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSeagrass(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.5f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3f76e4)
                        .waterFogColor(0x050533)
                        .skyColor(0x7ba4ff)
                        .fogColor(0xc0d8ff)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FLOWER_FOREST)).build())
                .build();
    }
}
