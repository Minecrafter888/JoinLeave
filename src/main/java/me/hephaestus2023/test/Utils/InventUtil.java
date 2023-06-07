/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.Utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventUtil {

    public static boolean hasItem(Player player, ItemStack item) {
        for (ItemStack stack : player.getInventory().getContents()) {
            if (stack != null && stack.isSimilar(item)) {
                // Item found in the player's inventory
                return true;
            }
        }
        // Item not found in the player's inventory
        return false;
    }
}
