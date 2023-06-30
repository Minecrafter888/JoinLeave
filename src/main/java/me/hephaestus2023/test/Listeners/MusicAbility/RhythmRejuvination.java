/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.Listeners.MusicAbility;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RhythmRejuvination implements Listener {
    private static final int REQUIRED_CLICKS = 2;
    private static final int REJUVINATION_AMOUNT = 4;
    private final Map<UUID, Integer> clickCountMap = new HashMap<>();

    @EventHandler
    public void rhythmrejuvi(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemStack handItem = player.getInventory().getItemInMainHand();

            // Check if the player is holding the Rhythm Rod
            if (handItem != null && handItem.getType() == Material.STICK && handItem.getItemMeta() != null) {
                ItemMeta itemMeta = handItem.getItemMeta();

                // Check if the Rhythm Rod has the correct name (e.g., "Rhythm Rod")
                if (itemMeta.getDisplayName().equals("Rhythm Rod")) {
                    int clickCount = clickCountMap.getOrDefault(player.getUniqueId(), 0);

                    // Increment the click count
                    clickCount++;
                    clickCountMap.put(player.getUniqueId(), clickCount);

                    if (clickCount >= REQUIRED_CLICKS) {
                        // Apply the Rhythm Rejuvenation effect
                        applyRhythmRejuvenation(player);

                        // Reset the click count
                        clickCountMap.put(player.getUniqueId(), 0);

                        // Consume durability or perform any other necessary actions on the item

                        event.setCancelled(true); // Prevent default behavior of the right-click event
                    }
                }
            }
        }

    }
    private void applyRhythmRejuvenation (Player player){
        // Restore hunger and saturation levels for the player
        player.setFoodLevel(Math.min(20, player.getFoodLevel() + REJUVINATION_AMOUNT));
        player.setSaturation(Math.min(20f, player.getSaturation() + REJUVINATION_AMOUNT));
    }
}
