package net.theivan066.randomholos.worldgen.dimension;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.theivan066.randomholos.worldgen.biome.ModBiomes;

import java.util.List;

public class ModBiomeClimateParameters {
    public static Climate.ParameterList<Holder<Biome>> buildParameterList(HolderGetter<Biome> biomeRegistry) {
        return new Climate.ParameterList<>(List.of(
                Pair.of(Climate.parameters(Climate.Parameter.span(-0.45F,0.2F)/*Temp*/, Climate.Parameter.span(-0.35F,-0.1F),/*Humid*/ Climate.Parameter.span(0.3F,1F)/*Continent*/, Climate.Parameter.span(-0.7799F,-0.375F)/*Erosion*/, Climate.Parameter.span(0F,1F)/*Depth*/, Climate.Parameter.span(0.2666F,0.4F)/*PV*/, 0.0F/*Offset*/), biomeRegistry.getOrThrow(ModBiomes.SAKURA_FOREST)),
                Pair.of(Climate.parameters(Climate.Parameter.span(-0.45F,0.2F)/*Temp*/, Climate.Parameter.span(-0.35F,-0.1F),/*Humid*/ Climate.Parameter.span(0.3F,1F)/*Continent*/, Climate.Parameter.span(-0.375F,-0.2225F)/*Erosion*/, Climate.Parameter.span(0F,1F)/*Depth*/, Climate.Parameter.span(0.4F,1F)/*PV*/, 0.01F/*Offset*/), biomeRegistry.getOrThrow(Biomes.CHERRY_GROVE)),

                Pair.of(Climate.parameters(Climate.Parameter.span(0F,0.2F)/*Temp*/, Climate.Parameter.span(-0.35F,-0.1F),/*Humid*/ Climate.Parameter.span(0.03F,0.3F)/*Continent*/, Climate.Parameter.span(-0.2225F,0.55F)/*Erosion*/, Climate.Parameter.span(0F,1F)/*Depth*/, Climate.Parameter.span(0.4F,1F)/*PV*/, 0.0F/*Offset*/), biomeRegistry.getOrThrow(ModBiomes.MAPLE_FOREST)),
                Pair.of(Climate.parameters(Climate.Parameter.span(-0.45F,0.2F)/*Temp*/, Climate.Parameter.span(-0.35F,-0.1F),/*Humid*/ Climate.Parameter.span(0.03F,0.3F)/*Continent*/, Climate.Parameter.span(0.05F,0.55F)/*Erosion*/, Climate.Parameter.span(0F,1F)/*Depth*/, Climate.Parameter.span(0.4F,1F)/*PV*/, 0.0F/*Offset*/), biomeRegistry.getOrThrow(ModBiomes.MORIRIN)),

                Pair.of(Climate.parameters(Climate.Parameter.span(0.55F,1F)/*Temp*/, Climate.Parameter.span(-1F,-0.35F),/*Humid*/ Climate.Parameter.span(0.3F,1F)/*Continent*/, Climate.Parameter.span(0.05F,1F)/*Erosion*/, Climate.Parameter.span(0F,1F)/*Depth*/, Climate.Parameter.span(-0.6F,0.2F)/*PV*/, 0.0F/*Offset*/), biomeRegistry.getOrThrow(ModBiomes.SHATTERED_GROUNDS)),
                Pair.of(Climate.parameters(Climate.Parameter.span(-0.15F,0.2F)/*Temp*/, Climate.Parameter.span(-1F,-0.35F),/*Humid*/ Climate.Parameter.span(-0.19F,0.3F)/*Continent*/, Climate.Parameter.span(0.55F,1F)/*Erosion*/, Climate.Parameter.span(0F,1F)/*Depth*/, Climate.Parameter.span(-0.85F,-0.6F)/*PV*/, 0.0F/*Offset*/), biomeRegistry.getOrThrow(ModBiomes.LAND_OF_SOULS)),
                Pair.of(Climate.parameters(Climate.Parameter.span(-1F,-0.45F)/*Temp*/, Climate.Parameter.span(-1F,0.35F),/*Humid*/ Climate.Parameter.span(0.03F,0.3F)/*Continent*/, Climate.Parameter.span(-1F,-0.375F)/*Erosion*/, Climate.Parameter.span(0F,1F)/*Depth*/, Climate.Parameter.span(0.2F,1F)/*PV*/, 0.0F/*Offset*/), biomeRegistry.getOrThrow(ModBiomes.SNOWY_LANDS)),

                Pair.of(Climate.parameters(Climate.Parameter.span(-1F, 1F)/*Temp*/, Climate.Parameter.span(-1F, 1F)/*Humid*/, Climate.Parameter.span(-1.2F, -1.05F)/*Continent*/, Climate.Parameter.span(-1F, 1F)/*Erosion*/, Climate.Parameter.span(-1F, 1F)/*Depth*/, Climate.Parameter.span(-1F, 1F)/*PV*/, 0.0F/*Offset*/), biomeRegistry.getOrThrow(ModBiomes.SKYLAND)),
                Pair.of(Climate.parameters(Climate.Parameter.span(-1F, 1F)/*Temp*/, Climate.Parameter.span(0.3F, 1F)/*Humid*/, Climate.Parameter.span(-1.05F, -0.19F)/*Continent*/, Climate.Parameter.span(-1F, 1F)/*Erosion*/, Climate.Parameter.span(-1F, 1F)/*Depth*/, Climate.Parameter.span(-1F, 1F)/*PV*/, 0.0F/*Offset*/), biomeRegistry.getOrThrow(ModBiomes.THE_MARINE))
        ));
    }
}