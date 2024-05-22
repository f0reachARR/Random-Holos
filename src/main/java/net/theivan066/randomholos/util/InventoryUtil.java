package net.theivan066.randomholos.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class InventoryUtil {
    public static void removeItem(Player player, Item item, int count) {
        int itemsToRemove = count;

        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack currentStack = player.getInventory().getItem(i);
            if (!currentStack.isEmpty() && ItemStack.isSameItem(currentStack, new ItemStack(item))) {
                if (currentStack.getCount() > itemsToRemove) {
                    currentStack.shrink(itemsToRemove);
                    itemsToRemove = 0;
                    break;
                } else {
                    itemsToRemove -= currentStack.getCount();
                    currentStack.setCount(0);
                    if (itemsToRemove == 0) {
                        break;
                    }
                }
            }
        }
    }
}
