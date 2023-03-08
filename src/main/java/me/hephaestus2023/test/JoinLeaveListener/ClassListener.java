/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.JoinLeaveListener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class ClassListener implements Listener {

    @EventHandler
    public void ClassClick (InventoryClickEvent e){

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Classes")){

            if (e.getCurrentItem() == null){
                return;
            }


            if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD){



            }




            e.setCancelled(true);
        }

    }
}
