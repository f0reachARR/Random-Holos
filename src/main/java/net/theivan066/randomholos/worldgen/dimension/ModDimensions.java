package net.theivan066.randomholos.worldgen.dimension;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.worldgen.biome.ModBiomes;

import java.util.List;
import java.util.OptionalLong;

public class ModDimensions {
    public static final ResourceKey<LevelStem> KAKURIYO_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo"));
    public static final ResourceKey<Level> KAKURIYO_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo"));
    public static final ResourceKey<DimensionType> KAKURIYO_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo_type"));

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(KAKURIYO_TYPE, new DimensionType(
                OptionalLong.of(12000), // fixedTime
                true, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                false, // natural
                1.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                0, // minY
                512, // height
                512, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
                1.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.SAKURA_FOREST)),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED));

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(Climate.parameters(Climate.Parameter.span(-0.45F,0.2F)/*Temp*/, Climate.Parameter.span(-0.35F,-0.1F),/*Humid*/ Climate.Parameter.span(0.3F,1F)/*Continent*/, Climate.Parameter.span(-0.7799F,-0.375F)/*Erosion*/, Climate.Parameter.span(0F,1F)/*Depth*/, Climate.Parameter.span(0.2666F,0.4F)/*PV*/, 0.0F/*Offset*/), biomeRegistry.getOrThrow(ModBiomes.SAKURA_FOREST)),
                                Pair.of(Climate.parameters(Climate.Parameter.span(-0.45F,0.2F)/*Temp*/, Climate.Parameter.span(-0.35F,-0.1F),/*Humid*/ Climate.Parameter.span(0.3F,1F)/*Continent*/, Climate.Parameter.span(-0.375F,-0.2225F)/*Erosion*/, Climate.Parameter.span(0F,1F)/*Depth*/, Climate.Parameter.span(0.4F,1F)/*PV*/, 0.01F/*Offset*/), biomeRegistry.getOrThrow(Biomes.CHERRY_GROVE)),

                                Pair.of(Climate.parameters(Climate.Parameter.span(0F,0.2F)/*Temp*/, Climate.Parameter.span(-0.35F,-0.1F),/*Humid*/ Climate.Parameter.span(0.03F,0.3F)/*Continent*/, Climate.Parameter.span(-0.2225F,0.55F)/*Erosion*/, Climate.Parameter.span(0F,1F)/*Depth*/, Climate.Parameter.span(0.4F,1F)/*PV*/, 0.0F/*Offset*/), biomeRegistry.getOrThrow(ModBiomes.MAPLE_FOREST)),
                                Pair.of(Climate.parameters(Climate.Parameter.span(-0.45F,0.2F)/*Temp*/, Climate.Parameter.span(-0.35F,-0.1F),/*Humid*/ Climate.Parameter.span(0.03F,0.3F)/*Continent*/, Climate.Parameter.span(0.05F,0.55F)/*Erosion*/, Climate.Parameter.span(0F,1F)/*Depth*/, Climate.Parameter.span(0.4F,1F)/*PV*/, 0.0F/*Offset*/), biomeRegistry.getOrThrow(ModBiomes.MORIRIN)),

                                Pair.of(Climate.parameters(Climate.Parameter.span(0.55F,1F)/*Temp*/, Climate.Parameter.span(-1F,-0.35F),/*Humid*/ Climate.Parameter.span(0.3F,1F)/*Continent*/, Climate.Parameter.span(0.05F,1F)/*Erosion*/, Climate.Parameter.span(0F,1F)/*Depth*/, Climate.Parameter.span(-0.6F,0.2F)/*PV*/, 0.0F/*Offset*/), biomeRegistry.getOrThrow(ModBiomes.SHATTERED_GROUNDS)),
                                Pair.of(Climate.parameters(Climate.Parameter.span(-0.15F,0.2F)/*Temp*/, Climate.Parameter.span(-1F,-0.35F),/*Humid*/ Climate.Parameter.span(-0.19F,-0.11F)/*Continent*/, Climate.Parameter.span(0.55F,1F)/*Erosion*/, Climate.Parameter.span(0F,1F)/*Depth*/, Climate.Parameter.span(-0.85F,-0.6F)/*PV*/, 0.0F/*Offset*/), biomeRegistry.getOrThrow(ModBiomes.LAND_OF_SOULS)),
                                Pair.of(Climate.parameters(Climate.Parameter.span(-1F,-0.45F)/*Temp*/, Climate.Parameter.span(-1F,0.35F),/*Humid*/ Climate.Parameter.span(0.03F,1F)/*Continent*/, Climate.Parameter.span(-1F,-0.375F)/*Erosion*/, Climate.Parameter.span(0F,1F)/*Depth*/, Climate.Parameter.span(0.2F,1F)/*PV*/, 0.0F/*Offset*/), biomeRegistry.getOrThrow(ModBiomes.SNOWY_LANDS))

                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD));

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.KAKURIYO_TYPE), noiseBasedChunkGenerator);

        context.register(KAKURIYO_KEY, stem);
    }
}







