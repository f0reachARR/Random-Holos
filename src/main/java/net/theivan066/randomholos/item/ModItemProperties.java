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
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pull"),
                (stack, level, entity, p_174638_) -> {
                    if (entity == null) {
                        return 0.0F;
                    } else {
                        return entity.getUseItem() != stack
                                ? 0.0F
                                : (float) (stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0F;
                    }
                });
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("pulling"),
                (stack, level, entity, p_174633_) -> {
                    return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
                });
    }

    private static void makeGun(GunItem item) {
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("reloading"),
                (itemstack, level, entity, id) -> {
                    return entity != null && !item.isReloading() && entity.getUseItem() == itemstack ? 1F : 0F;
                });

        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("reload"),
                (itemStack, level, entity, id) -> {
                    if (entity == null) {
                        return 0.0F;
                    } else {
                        return entity.getUseItem() != itemStack
                                ? 0.0F
                                : (float) (item.getReloadCooldown() - item.getReloadTicks()) / item.getReloadCooldown();
                    }
                });
    }
}
