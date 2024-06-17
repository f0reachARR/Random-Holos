package net.theivan066.randomholos.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.theivan066.randomholos.item.custom.base_items.GunItem;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        makeBow(ModItems.MIKO_BOW.get());
        makeBow(ModItems.MIKOMET_BOW.get());
    }

    private static void makeBow(Item item) {
        ItemProperties.register(item, new ResourceLocation("pull"), (p_174635_, p_174636_, p_174637_, p_174638_) -> {
            if (p_174637_ == null) {
                return 0.0F;
            } else {
                return p_174637_.getUseItem() != p_174635_ ? 0.0F : (float)(p_174635_.getUseDuration() - p_174637_.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(item, new ResourceLocation("pulling"), (p_174630_, p_174631_, p_174632_, p_174633_) -> {
            return p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F;
        });
    }

    private static void makeGun(GunItem item) {
        ItemProperties.register(item, new ResourceLocation("reloading"), (itemstack, level, entity, id) -> {
            return entity != null && !item.isReloading() && entity.getUseItem() == itemstack ? 1F : 0F;
        });

        ItemProperties.register(item, new ResourceLocation("reload"), (itemStack, level, entity, id) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getUseItem() != itemStack ? 0.0F : (float)(item.getReloadCooldown() - item.getReloadTicks()) / item.getReloadCooldown();
            }
        });
    }
}
