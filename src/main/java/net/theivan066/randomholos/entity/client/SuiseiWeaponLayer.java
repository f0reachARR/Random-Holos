package net.theivan066.randomholos.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.theivan066.randomholos.entity.custom.SuiseiEntity;
import net.theivan066.randomholos.item.ModItems;
import org.joml.Quaternionf;

public class SuiseiWeaponLayer extends RenderLayer<SuiseiEntity, SuiseiModel<SuiseiEntity>> {
    private final ItemRenderer itemRenderer;

    public SuiseiWeaponLayer(RenderLayerParent<SuiseiEntity, SuiseiModel<SuiseiEntity>> renderer,
                             ItemRenderer itemRenderer) {
        super(renderer);
        this.itemRenderer = itemRenderer;
    }


    @Override
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int packedLight,
                       SuiseiEntity entity, float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.isAttacking()) {
            ModelPart attackArm = this.getParentModel().getAttackArm();
            matrixStack.mulPose(Axis.YP.rotationDegrees(-90F));
            matrixStack.mulPose(Axis.ZP.rotationDegrees(-130F));
            matrixStack.translate(0D, -1D, 0.65D); // Move axe to the right

            matrixStack.translate(0.0D, -0.5D, 0.0D); // Rotate forward (axe offset)
            Quaternionf fix = new Quaternionf().rotateAxis(3.14f, -1, 0, 1);
            matrixStack.mulPose(fix);

            attackArm.translateAndRotate(matrixStack);

            matrixStack.translate(-0.0D, 0.5D, 0.0D); // Rotate back (axe offset)
            matrixStack.mulPose(fix.invert()); // Fix for ItemDisplayContext
            matrixStack.mulPose(Axis.ZP.rotationDegrees(-80F));
        } else {
            matrixStack.mulPose(Axis.XP.rotationDegrees(-10.0F));
            matrixStack.mulPose(Axis.ZP.rotationDegrees(-100F));
            matrixStack.translate(-0.4D, -0.1D, 0.4D);
        }

        matrixStack.scale(1.2F, 1.2F, 1.2F);
        // Render the weapon here
        this.itemRenderer.renderStatic(
                new ItemStack(ModItems.PSYCHOPATH_AXE.get()),
                ItemDisplayContext.NONE,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                matrixStack,
                buffer,
                entity.level(),
                0
        );
    }
}
