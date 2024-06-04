package net.theivan066.randomholos.entity.client.projectile;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.theivan066.randomholos.entity.custom.projectile.GlassHeelsProjectileEntity;

public class GlassHeelProjectileRenderer extends ThrownItemRenderer<GlassHeelsProjectileEntity> {
    public GlassHeelProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, 1.5f, true);
    }
}
