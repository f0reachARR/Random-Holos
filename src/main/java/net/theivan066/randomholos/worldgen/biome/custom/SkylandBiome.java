package net.theivan066.randomholos.worldgen.biome.custom;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.biome.*;

public class SkylandBiome {
    public static Biome skyland(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addDefaultOres(biomeBuilder, true);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.3f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x5db7ef)
                        .waterFogColor(0x5db7ea)
                        .skyColor(0x245eff)
                        .grassColorOverride(0x266AFF)
                        .foliageColorOverride(0xfba4ba)
                        .fogColor(0xfe8a8d)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FLOWER_FOREST)).build())
                .build();
    }
}
