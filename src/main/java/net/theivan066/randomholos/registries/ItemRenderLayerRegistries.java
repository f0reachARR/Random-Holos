package net.theivan066.randomholos.registries;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.theivan066.randomholos.fluid.ModFluids;

public class ItemRenderLayerRegistries {
    public static void registerRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_ELITE_LAVA.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_ELITE_LAVA.get(), RenderType.translucent());
    }
}
