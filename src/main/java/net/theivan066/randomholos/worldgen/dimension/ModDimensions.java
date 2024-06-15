package net.theivan066.randomholos.worldgen.dimension;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.doubles.Double2DoubleFunction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.worldgen.biome.ModBiomes;

import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Stream;

public class ModDimensions {
    public static final ResourceKey<LevelStem> KAKURIYO_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo"));
    public static final ResourceKey<Level> KAKURIYO_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo"));
    public static final ResourceKey<DimensionType> KAKURIYO_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo_type"));
    public static final ResourceKey<NoiseGeneratorSettings> KAKURIYO_NOISE_GEN = ResourceKey.create(Registries.NOISE_SETTINGS,
            new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo_noise_gen"));


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

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        ModBiomeClimateParameters.buildParameterList(biomeRegistry)
                ),
                noiseGenSettings.getOrThrow(KAKURIYO_NOISE_GEN));

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.KAKURIYO_TYPE), noiseBasedChunkGenerator);

        context.register(KAKURIYO_KEY, stem);
    }

    public static final ResourceKey<DensityFunction> SHIFT_X = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "shift_x"));
    public static final ResourceKey<DensityFunction> SHIFT_Z = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "shift_z"));
    public static final ResourceKey<DensityFunction> FACTOR = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo/factor"));
    public static final ResourceKey<DensityFunction> DEPTH = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo/depth"));
    public static final ResourceKey<DensityFunction> SLOPED_CHEESE = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo/sloped_cheese"));
    public static final ResourceKey<DensityFunction> Y = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "y"));
    public static final ResourceKey<DensityFunction> ENTRANCES = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo/caves/entrances"));
    public static final ResourceKey<DensityFunction> NOODLE = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo/caves/noodle"));
    public static final ResourceKey<DensityFunction> CONTINENTS = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo/continents"));
    public static final ResourceKey<DensityFunction> EROSION = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo/erosion"));
    public static final ResourceKey<DensityFunction> RIDGES = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo/ridges"));
    private static final ResourceKey<DensityFunction> PILLARS = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo/caves/pillars"));
    private static final ResourceKey<DensityFunction> SPAGHETTI_ROUGHNESS_FUNCTION = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo/caves/spaghetti_roughness_function"));
    private static final ResourceKey<DensityFunction> SPAGHETTI_2D = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo/caves/spaghetti_2d"));
    private static final ResourceKey<DensityFunction> BASE_3D_NOISE_OVERWORLD = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo/base_3d_noise"));
    private static final DensityFunction BLENDING_FACTOR = DensityFunctions.constant(10.0D);
    private static final DensityFunction BLENDING_JAGGEDNESS = DensityFunctions.zero();
    public static final ResourceKey<DensityFunction> RIDGES_FOLDED =  ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo/ridges_folded"));
    private static final ResourceKey<DensityFunction> SPAGHETTI_2D_THICKNESS_MODULATOR = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo/caves/spaghetti_2d_thickness_modulator"));
    public static final ResourceKey<DensityFunction> OFFSET = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo/offset"));
    public static final ResourceKey<DensityFunction> JAGGEDNESS = ResourceKey.create(Registries.DENSITY_FUNCTION, new ResourceLocation(RandomHolos.MOD_ID, "kakuriyo/jaggedness"));

    public static Holder<? extends DensityFunction> bootstrapDensity(BootstapContext<DensityFunction> pContext) {

        HolderGetter<NormalNoise.NoiseParameters> holdergetter = pContext.lookup(Registries.NOISE);
        HolderGetter<DensityFunction> holdergetter1 = pContext.lookup(Registries.DENSITY_FUNCTION);
        int i = DimensionType.MIN_Y * 2;
        int j = DimensionType.MAX_Y * 2;
        pContext.register(Y, DensityFunctions.yClampedGradient(i, j, (double)i, (double)j));
        DensityFunction densityfunction = registerAndWrap(pContext, SHIFT_X, DensityFunctions.flatCache(DensityFunctions.cache2d(DensityFunctions.shiftA(holdergetter.getOrThrow(Noises.SHIFT)))));
        DensityFunction densityfunction1 = registerAndWrap(pContext, SHIFT_Z, DensityFunctions.flatCache(DensityFunctions.cache2d(DensityFunctions.shiftB(holdergetter.getOrThrow(Noises.SHIFT)))));
        pContext.register(BASE_3D_NOISE_OVERWORLD, BlendedNoise.createUnseeded(0.25D, 0.125D, 80.0D, 160.0D, 8.0D));
        Holder<DensityFunction> holder = pContext.register(CONTINENTS, DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25D, holdergetter.getOrThrow(Noises.CONTINENTALNESS))));
        Holder<DensityFunction> holder1 = pContext.register(EROSION, DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25D, holdergetter.getOrThrow(Noises.EROSION))));
        DensityFunction densityfunction2 = registerAndWrap(pContext, RIDGES, DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25D, holdergetter.getOrThrow(Noises.RIDGE))));
        pContext.register(RIDGES_FOLDED, peaksAndValleys(densityfunction2));
        DensityFunction densityfunction3 = DensityFunctions.noise(holdergetter.getOrThrow(Noises.JAGGED), 1500.0D, 0.0D);
        registerTerrainNoises(pContext, holdergetter1, densityfunction3, holder, holder1, OFFSET, FACTOR, JAGGEDNESS, DEPTH, SLOPED_CHEESE);
        pContext.register(SPAGHETTI_ROUGHNESS_FUNCTION, spaghettiRoughnessFunction(holdergetter));
        pContext.register(SPAGHETTI_2D_THICKNESS_MODULATOR, DensityFunctions.cacheOnce(DensityFunctions.mappedNoise(holdergetter.getOrThrow(Noises.SPAGHETTI_2D_THICKNESS), 2.0D, 1.0D, -0.6D, -1.3D)));
        pContext.register(SPAGHETTI_2D, spaghetti2D(holdergetter1, holdergetter));
        pContext.register(ENTRANCES, entrances(holdergetter1, holdergetter));
        pContext.register(NOODLE, noodle(holdergetter1, holdergetter));

        return pContext.register(PILLARS, pillars(holdergetter));
    }

    private static DensityFunction spaghettiRoughnessFunction(HolderGetter<NormalNoise.NoiseParameters> pNoiseParameters) {
        DensityFunction densityfunction = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.SPAGHETTI_ROUGHNESS));
        DensityFunction densityfunction1 = DensityFunctions.mappedNoise(pNoiseParameters.getOrThrow(Noises.SPAGHETTI_ROUGHNESS_MODULATOR), 0.0D, -0.1D);
        return DensityFunctions.cacheOnce(DensityFunctions.mul(densityfunction1, DensityFunctions.add(densityfunction.abs(), DensityFunctions.constant(-0.4D))));
    }

    private static DensityFunction entrances(HolderGetter<DensityFunction> pDensityFunction, HolderGetter<NormalNoise.NoiseParameters> pNoiseParameters) {
        DensityFunction densityfunction = DensityFunctions.cacheOnce(DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.SPAGHETTI_3D_RARITY), 2.0D, 1.0D));
        DensityFunction densityfunction1 = DensityFunctions.mappedNoise(pNoiseParameters.getOrThrow(Noises.SPAGHETTI_3D_THICKNESS), -0.065D, -0.088D);
        DensityFunction densityfunction2 = weirdScaledSampler(densityfunction, pNoiseParameters.getOrThrow(Noises.SPAGHETTI_3D_1), WeirdScaledSampler.RarityValueMapper.TYPE1);
        DensityFunction densityfunction3 = weirdScaledSampler(densityfunction, pNoiseParameters.getOrThrow(Noises.SPAGHETTI_3D_2), WeirdScaledSampler.RarityValueMapper.TYPE1);
        DensityFunction densityfunction4 = DensityFunctions.add(DensityFunctions.max(densityfunction2, densityfunction3), densityfunction1).clamp(-1.0D, 1.0D);
        DensityFunction densityfunction5 = getFunction(pDensityFunction, SPAGHETTI_ROUGHNESS_FUNCTION);
        DensityFunction densityfunction6 = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.CAVE_ENTRANCE), 0.75D, 0.5D);
        DensityFunction densityfunction7 = DensityFunctions.add(DensityFunctions.add(densityfunction6, DensityFunctions.constant(0.37D)), DensityFunctions.yClampedGradient(-10, 30, 0.3D, 0.0D));
        return DensityFunctions.cacheOnce(DensityFunctions.min(densityfunction7, DensityFunctions.add(densityfunction5, densityfunction4)));
    }

    private static DensityFunction spaghetti2D(HolderGetter<DensityFunction> pDensityFunctions, HolderGetter<NormalNoise.NoiseParameters> pNoiseParameters) {
        DensityFunction densityfunction = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.SPAGHETTI_2D_MODULATOR), 2.0D, 1.0D);
        DensityFunction densityfunction1 = weirdScaledSampler(densityfunction, pNoiseParameters.getOrThrow(Noises.SPAGHETTI_2D), WeirdScaledSampler.RarityValueMapper.TYPE2);
        DensityFunction densityfunction2 = DensityFunctions.mappedNoise(pNoiseParameters.getOrThrow(Noises.SPAGHETTI_2D_ELEVATION), 0.0D, (double)Math.floorDiv(-64, 8), 8.0D);
        DensityFunction densityfunction3 = getFunction(pDensityFunctions, SPAGHETTI_2D_THICKNESS_MODULATOR);
        DensityFunction densityfunction4 = DensityFunctions.add(densityfunction2, DensityFunctions.yClampedGradient(-64, 320, 8.0D, -40.0D)).abs();
        DensityFunction densityfunction5 = DensityFunctions.add(densityfunction4, densityfunction3).cube();
        double d0 = 0.083D;
        DensityFunction densityfunction6 = DensityFunctions.add(densityfunction1, DensityFunctions.mul(DensityFunctions.constant(0.083D), densityfunction3));
        return DensityFunctions.max(densityfunction6, densityfunction5).clamp(-1.0D, 1.0D);
    }

    private static DensityFunction noodle(HolderGetter<DensityFunction> pDensityFunctions, HolderGetter<NormalNoise.NoiseParameters> pNoiseParameters) {
        DensityFunction densityfunction = getFunction(pDensityFunctions, Y);
        int i = -64;
        int j = -60;
        int k = 320;
        DensityFunction densityfunction1 = yLimitedInterpolatable(densityfunction, DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.NOODLE), 1.0D, 1.0D), -60, 320, -1);
        DensityFunction densityfunction2 = yLimitedInterpolatable(densityfunction, DensityFunctions.mappedNoise(pNoiseParameters.getOrThrow(Noises.NOODLE_THICKNESS), 1.0D, 1.0D, -0.05D, -0.1D), -60, 320, 0);
        double d0 = 2.6666666666666665D;
        DensityFunction densityfunction3 = yLimitedInterpolatable(densityfunction, DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.NOODLE_RIDGE_A), 2.6666666666666665D, 2.6666666666666665D), -60, 320, 0);
        DensityFunction densityfunction4 = yLimitedInterpolatable(densityfunction, DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.NOODLE_RIDGE_B), 2.6666666666666665D, 2.6666666666666665D), -60, 320, 0);
        DensityFunction densityfunction5 = DensityFunctions.mul(DensityFunctions.constant(1.5D), DensityFunctions.max(densityfunction3.abs(), densityfunction4.abs()));
        return DensityFunctions.rangeChoice(densityfunction1, -1000000.0D, 0.0D, DensityFunctions.constant(64.0D), DensityFunctions.add(densityfunction2, densityfunction5));
    }

    private static DensityFunction pillars(HolderGetter<NormalNoise.NoiseParameters> pNoiseParameters) {
        double d0 = 25.0D;
        double d1 = 0.3D;
        DensityFunction densityfunction = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.PILLAR), 25.0D, 0.3D);
        DensityFunction densityfunction1 = DensityFunctions.mappedNoise(pNoiseParameters.getOrThrow(Noises.PILLAR_RARENESS), 0.0D, -2.0D);
        DensityFunction densityfunction2 = DensityFunctions.mappedNoise(pNoiseParameters.getOrThrow(Noises.PILLAR_THICKNESS), 0.0D, 1.1D);
        DensityFunction densityfunction3 = DensityFunctions.add(DensityFunctions.mul(densityfunction, DensityFunctions.constant(2.0D)), densityfunction1);
        return DensityFunctions.cacheOnce(DensityFunctions.mul(densityfunction3, densityfunction2.cube()));
    }


    private static DensityFunction peaksAndValleys(DensityFunction pDensityFunction) {
        return DensityFunctions.mul(DensityFunctions.add(DensityFunctions.add(pDensityFunction.abs(), DensityFunctions.constant(-0.6666666666666666D)).abs(), DensityFunctions.constant(-0.3333333333333333D)), DensityFunctions.constant(-3.0D));
    }

    private static DensityFunction registerAndWrap(BootstapContext<DensityFunction> pContext, ResourceKey<DensityFunction> pKey, DensityFunction pDensityFunction) {
        return new DensityFunctions.HolderHolder(pContext.register(pKey, pDensityFunction));
    }

    private static void registerTerrainNoises(BootstapContext<DensityFunction> pContext, HolderGetter<DensityFunction> pDensityFunctionGetter, DensityFunction pJaggedNoise, Holder<DensityFunction> pContinents, Holder<DensityFunction> pErosion, ResourceKey<DensityFunction> pOffsetKey, ResourceKey<DensityFunction> pFactorKey, ResourceKey<DensityFunction> pJaggednessKey, ResourceKey<DensityFunction> pDepthKey, ResourceKey<DensityFunction> pSlopedCheeseKey) {
        DensityFunctions.Spline.Coordinate densityfunctions$spline$coordinate = new DensityFunctions.Spline.Coordinate(pContinents);
        DensityFunctions.Spline.Coordinate densityfunctions$spline$coordinate1 = new DensityFunctions.Spline.Coordinate(pErosion);
        DensityFunctions.Spline.Coordinate densityfunctions$spline$coordinate2 = new DensityFunctions.Spline.Coordinate(pDensityFunctionGetter.getOrThrow(RIDGES));
        DensityFunctions.Spline.Coordinate densityfunctions$spline$coordinate3 = new DensityFunctions.Spline.Coordinate(pDensityFunctionGetter.getOrThrow(RIDGES_FOLDED));
        DensityFunction densityfunction = registerAndWrap(pContext, pOffsetKey, splineWithBlending(DensityFunctions.add(DensityFunctions.constant((double)-0.50375F), DensityFunctions.spline(TerrainProvider.overworldOffset(densityfunctions$spline$coordinate, densityfunctions$spline$coordinate1, densityfunctions$spline$coordinate3, false))), DensityFunctions.blendOffset()));
        DensityFunction densityfunction1 = registerAndWrap(pContext, pFactorKey, splineWithBlending(DensityFunctions.spline(TerrainProvider.overworldFactor(densityfunctions$spline$coordinate, densityfunctions$spline$coordinate1, densityfunctions$spline$coordinate2, densityfunctions$spline$coordinate3, false)), BLENDING_FACTOR));
        DensityFunction densityfunction2 = registerAndWrap(pContext, pDepthKey, DensityFunctions.add(DensityFunctions.yClampedGradient(-64, 320, 1.5D, -1.5D), densityfunction));
        DensityFunction densityfunction3 = registerAndWrap(pContext, pJaggednessKey, splineWithBlending(DensityFunctions.spline(TerrainProvider.overworldJaggedness(densityfunctions$spline$coordinate, densityfunctions$spline$coordinate1, densityfunctions$spline$coordinate2, densityfunctions$spline$coordinate3, false)), BLENDING_JAGGEDNESS));
        DensityFunction densityfunction4 = DensityFunctions.mul(densityfunction3, pJaggedNoise.halfNegative());
        DensityFunction densityfunction5 = noiseGradientDensity(densityfunction1, DensityFunctions.add(densityfunction2, densityfunction4));
        pContext.register(pSlopedCheeseKey, DensityFunctions.add(densityfunction5, getFunction(pDensityFunctionGetter, BASE_3D_NOISE_OVERWORLD)));
    }

    private static DensityFunction splineWithBlending(DensityFunction pMinFunction, DensityFunction pMaxFunction) {
        DensityFunction densityfunction = DensityFunctions.lerp(DensityFunctions.blendAlpha(), pMaxFunction, pMinFunction);
        return DensityFunctions.flatCache(DensityFunctions.cache2d(densityfunction));
    }
    public static DensityFunction weirdScaledSampler(DensityFunction pInput, Holder<NormalNoise.NoiseParameters> pNoiseData, WeirdScaledSampler.RarityValueMapper pRarityValueMapper) {
        return new WeirdScaledSampler(pInput, new DensityFunction.NoiseHolder(pNoiseData), pRarityValueMapper);
    }

    interface TransformerWithContext extends DensityFunction {
        DensityFunction input();

        default double compute(DensityFunction.FunctionContext pContext) {
            return this.transform(pContext, this.input().compute(pContext));
        }

        default void fillArray(double[] pArray, DensityFunction.ContextProvider pContextProvider) {
            this.input().fillArray(pArray, pContextProvider);

            for(int i = 0; i < pArray.length; ++i) {
                pArray[i] = this.transform(pContextProvider.forIndex(i), pArray[i]);
            }

        }
        double transform(DensityFunction.FunctionContext pContext, double pValue);
    }

    protected static record WeirdScaledSampler(DensityFunction input, DensityFunction.NoiseHolder noise, WeirdScaledSampler.RarityValueMapper rarityValueMapper) implements TransformerWithContext {
        private static final MapCodec<WeirdScaledSampler> DATA_CODEC = RecordCodecBuilder.mapCodec((p_208438_) -> {
            return p_208438_.group(DensityFunction.HOLDER_HELPER_CODEC.fieldOf("input").forGetter(WeirdScaledSampler::input), DensityFunction.NoiseHolder.CODEC.fieldOf("noise").forGetter(WeirdScaledSampler::noise), WeirdScaledSampler.RarityValueMapper.CODEC.fieldOf("rarity_value_mapper").forGetter(WeirdScaledSampler::rarityValueMapper)).apply(p_208438_, WeirdScaledSampler::new);
        });
        public static final KeyDispatchDataCodec<WeirdScaledSampler> CODEC = makeCodec(DATA_CODEC);

        public double transform(DensityFunction.FunctionContext pContext, double pValue) {
            double d0 = this.rarityValueMapper.mapper.get(pValue);
            return d0 * Math.abs(this.noise.getValue((double)pContext.blockX() / d0, (double)pContext.blockY() / d0, (double)pContext.blockZ() / d0));
        }

        public DensityFunction mapAll(DensityFunction.Visitor pVisitor) {
            return pVisitor.apply(new WeirdScaledSampler(this.input.mapAll(pVisitor), pVisitor.visitNoise(this.noise), this.rarityValueMapper));
        }

        public double minValue() {
            return 0.0D;
        }

        public double maxValue() {
            return this.rarityValueMapper.maxRarity * this.noise.maxValue();
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            return CODEC;
        }

        public DensityFunction input() {
            return this.input;
        }

        public static enum RarityValueMapper implements StringRepresentable {
            TYPE1("type_1", QuantizedSpaghettiRarity::getSpaghettiRarity3D, 2.0D),
            TYPE2("type_2", QuantizedSpaghettiRarity::getSphaghettiRarity2D, 3.0D);

            public static final Codec<WeirdScaledSampler.RarityValueMapper> CODEC = StringRepresentable.fromEnum(WeirdScaledSampler.RarityValueMapper::values);
            private final String name;
            final Double2DoubleFunction mapper;
            final double maxRarity;

            private RarityValueMapper(String pName, Double2DoubleFunction pMapper, double pMaxRarity) {
                this.name = pName;
                this.mapper = pMapper;
                this.maxRarity = pMaxRarity;
            }

            public String getSerializedName() {
                return this.name;
            }
        }
    }
    static <O> KeyDispatchDataCodec<O> makeCodec(MapCodec<O> pMapCodec) {
        return KeyDispatchDataCodec.of(pMapCodec);
    }
    protected static final class QuantizedSpaghettiRarity {
        public static double getSphaghettiRarity2D(double pValue) {
            if (pValue < -0.75D) {
                return 0.5D;
            } else if (pValue < -0.5D) {
                return 0.75D;
            } else if (pValue < 0.5D) {
                return 1.0D;
            } else {
                return pValue < 0.75D ? 2.0D : 3.0D;
            }
        }

        public static double getSpaghettiRarity3D(double pValue) {
            if (pValue < -0.5D) {
                return 0.75D;
            } else if (pValue < 0.0D) {
                return 1.0D;
            } else {
                return pValue < 0.5D ? 1.5D : 2.0D;
            }
        }
    }

    private static Codec<? extends DensityFunction> register(Registry<Codec<? extends DensityFunction>> pRegistry, String pName, KeyDispatchDataCodec<? extends DensityFunction> pCodec) {
        return Registry.register(pRegistry, pName, pCodec.codec());
    }

    public static void bootstrapNoise(BootstapContext<NoiseGeneratorSettings> context) {
        HolderGetter<DensityFunction> functions = context.lookup(Registries.DENSITY_FUNCTION);
        HolderGetter<NormalNoise.NoiseParameters> noises = context.lookup(Registries.NOISE);

       context.register(KAKURIYO_NOISE_GEN, new NoiseGeneratorSettings(
               NoiseSettings.create(0, 512, 1, 2),
               Blocks.STONE.defaultBlockState(),
               Blocks.WATER.defaultBlockState(),
               kakuriyoNoise(context.lookup(Registries.DENSITY_FUNCTION), context.lookup(Registries.NOISE)),
                       kakuriyoSurfaceRules(true, false, true),
                       List.of(),
                       63,
                       false,
                       true,
                       true,
                       false

       ));
    }

    private static NoiseRouter kakuriyoNoise(HolderGetter<DensityFunction> pDensityFunctions, HolderGetter<NormalNoise.NoiseParameters> pNoiseParameters) {
        DensityFunction densityfunction = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.AQUIFER_BARRIER), 0.5D);
        DensityFunction densityfunction1 = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), 0.67D);
        DensityFunction densityfunction2 = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_SPREAD), 0.7142857142857143D);
        DensityFunction densityfunction3 = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.AQUIFER_LAVA));
        DensityFunction densityfunction4 = getFunction(pDensityFunctions, SHIFT_X);
        DensityFunction densityfunction5 = getFunction(pDensityFunctions, SHIFT_Z);
        DensityFunction densityfunction6 = DensityFunctions.shiftedNoise2d(densityfunction4, densityfunction5, 0.25D, pNoiseParameters.getOrThrow(Noises.TEMPERATURE));
        DensityFunction densityfunction7 = DensityFunctions.shiftedNoise2d(densityfunction4, densityfunction5, 0.25D, pNoiseParameters.getOrThrow(Noises.VEGETATION));
        DensityFunction densityfunction8 = getFunction(pDensityFunctions, FACTOR);
        DensityFunction densityfunction9 = getFunction(pDensityFunctions, DEPTH);
        DensityFunction densityfunction10 = noiseGradientDensity(DensityFunctions.cache2d(densityfunction8), densityfunction9);
        DensityFunction densityfunction11 = getFunction(pDensityFunctions, SLOPED_CHEESE);
        DensityFunction densityfunction12 = DensityFunctions.min(densityfunction11, DensityFunctions.mul(DensityFunctions.constant(5.0D), getFunction(pDensityFunctions, ENTRANCES)));
        DensityFunction densityfunction13 = DensityFunctions.rangeChoice(densityfunction11, -1000000.0D, 1.5625D, densityfunction12, underground(pDensityFunctions, pNoiseParameters, densityfunction11));
        DensityFunction densityfunction14 = DensityFunctions.min(postProcess(slideKakuriyo(densityfunction13)), getFunction(pDensityFunctions, NOODLE));
        DensityFunction densityfunction15 = getFunction(pDensityFunctions, Y);
        int i = Stream.of(ModOreVeinifier.VeinType.values()).mapToInt((p_224495_) -> {
            return p_224495_.minY;
        }).min().orElse(-DimensionType.MIN_Y * 2);
        int j = Stream.of(ModOreVeinifier.VeinType.values()).mapToInt((p_224457_) -> {
            return p_224457_.maxY;
        }).max().orElse(-DimensionType.MIN_Y * 2);
        DensityFunction densityfunction16 = yLimitedInterpolatable(densityfunction15, DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.ORE_VEININESS), 1.5D, 1.5D), i, j, 0);
        float f = 4.0F;
        DensityFunction densityfunction17 = yLimitedInterpolatable(densityfunction15, DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.ORE_VEIN_A), 4.0D, 4.0D), i, j, 0).abs();
        DensityFunction densityfunction18 = yLimitedInterpolatable(densityfunction15, DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.ORE_VEIN_B), 4.0D, 4.0D), i, j, 0).abs();
        DensityFunction densityfunction19 = DensityFunctions.add(DensityFunctions.constant((double)-0.08F), DensityFunctions.max(densityfunction17, densityfunction18));
        DensityFunction densityfunction20 = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.ORE_GAP));

        return new NoiseRouter(densityfunction, densityfunction1, densityfunction2, densityfunction3, densityfunction6, densityfunction7, getFunction(pDensityFunctions, CONTINENTS), getFunction(pDensityFunctions, EROSION), densityfunction9, getFunction(pDensityFunctions, RIDGES), slideKakuriyo(DensityFunctions.add(densityfunction10, DensityFunctions.constant(-0.703125D)).clamp(-64.0D, 64.0D)), densityfunction14, densityfunction16, densityfunction19, densityfunction20);
    }

    private static DensityFunction getFunction(HolderGetter<DensityFunction> pDensityFunctions, ResourceKey<DensityFunction> pKey) {
        return new DensityFunctions.HolderHolder(pDensityFunctions.getOrThrow(pKey));
    }

    private static DensityFunction noiseGradientDensity(DensityFunction pMinFunction, DensityFunction pMaxFunction) {
        DensityFunction densityfunction = DensityFunctions.mul(pMaxFunction, pMinFunction);
        return DensityFunctions.mul(DensityFunctions.constant(4.0D), densityfunction.quarterNegative());
    }

    private static DensityFunction postProcess(DensityFunction pDensityFunction) {
        DensityFunction densityfunction = DensityFunctions.blendDensity(pDensityFunction);
        return DensityFunctions.mul(DensityFunctions.interpolated(densityfunction), DensityFunctions.constant(0.64D)).squeeze();
    }

    private static DensityFunction slideKakuriyo(DensityFunction pDensityFunction) {
        return slide(pDensityFunction, 0, 512,  80,  64, -0.078125D, 0, 24,  0.1171875D);
    }
    private static DensityFunction slide(DensityFunction pDensityFunction, int pMinY, int pMaxY, int p_224447_, int p_224448_, double p_224449_, int p_224450_, int p_224451_, double p_224452_) {
        DensityFunction densityfunction1 = DensityFunctions.yClampedGradient(pMinY + pMaxY - p_224447_, pMinY + pMaxY - p_224448_, 1.0D, 0.0D);
        DensityFunction $$9 = DensityFunctions.lerp(densityfunction1, p_224449_, pDensityFunction);
        DensityFunction densityfunction2 = DensityFunctions.yClampedGradient(pMinY + p_224450_, pMinY + p_224451_, 0.0D, 1.0D);
        return DensityFunctions.lerp(densityfunction2, p_224452_, $$9);
    }

    private static DensityFunction yLimitedInterpolatable(DensityFunction p_209472_, DensityFunction p_209473_, int p_209474_, int p_209475_, int p_209476_) {
        return DensityFunctions.interpolated(DensityFunctions.rangeChoice(p_209472_, (double)p_209474_, (double)(p_209475_ + 1), p_209473_, DensityFunctions.constant((double)p_209476_)));
    }

    private static DensityFunction underground(HolderGetter<DensityFunction> pDensityFunctions, HolderGetter<NormalNoise.NoiseParameters> pNoiseParameters, DensityFunction p_256658_) {
        DensityFunction densityfunction = getFunction(pDensityFunctions, SPAGHETTI_2D);
        DensityFunction densityfunction1 = getFunction(pDensityFunctions, SPAGHETTI_ROUGHNESS_FUNCTION);
        DensityFunction densityfunction2 = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.CAVE_LAYER), 8.0D);
        DensityFunction densityfunction3 = DensityFunctions.mul(DensityFunctions.constant(4.0D), densityfunction2.square());
        DensityFunction densityfunction4 = DensityFunctions.noise(pNoiseParameters.getOrThrow(Noises.CAVE_CHEESE), 0.6666666666666666D);
        DensityFunction densityfunction5 = DensityFunctions.add(DensityFunctions.add(DensityFunctions.constant(0.27D), densityfunction4).clamp(-1.0D, 1.0D), DensityFunctions.add(DensityFunctions.constant(1.5D), DensityFunctions.mul(DensityFunctions.constant(-0.64D), p_256658_)).clamp(0.0D, 0.5D));
        DensityFunction densityfunction6 = DensityFunctions.add(densityfunction3, densityfunction5);
        DensityFunction densityfunction7 = getFunction(pDensityFunctions, ENTRANCES);
        DensityFunction densityfunction8 = DensityFunctions.min(DensityFunctions.min(densityfunction6, densityfunction7), DensityFunctions.add(densityfunction, densityfunction1));
        DensityFunction densityfunction9 = getFunction(pDensityFunctions, PILLARS);
        DensityFunction densityfunction10 = DensityFunctions.rangeChoice(densityfunction9, -1000000.0D, 0.03D, DensityFunctions.constant(-1000000.0D), densityfunction9);
        return DensityFunctions.max(densityfunction8, densityfunction10);
    }

    public static SurfaceRules.RuleSource kakuriyoSurfaceRules(boolean p_198381_, boolean pBedrockRoof, boolean pBedrockFloor) {
            return ModSurfaceRules.generateRules(p_198381_, pBedrockRoof, pBedrockFloor);
    }
}







