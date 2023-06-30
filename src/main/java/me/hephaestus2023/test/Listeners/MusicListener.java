/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MusicListener implements Listener {
    @EventHandler
    public void musicclick (InventoryClickEvent event){
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Music Sheet")) {
            event.setCancelled(true);
        }
    }
}
