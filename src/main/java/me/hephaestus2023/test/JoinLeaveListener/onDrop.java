/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.JoinLeaveListener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class onDrop implements Listener {

   @EventHandler
    public void onDrop(PlayerDropItemEvent e){

       Player p = e.getPlayer();

       if (e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("Dps sword")){

           e.setCancelled(true);
       }if (e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("Healer bow")){

           e.setCancelled(true);
       }if (e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("Tank shield")){

           e.setCancelled(true);
       }

   }
}
