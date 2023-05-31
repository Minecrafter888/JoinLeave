/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static me.hephaestus2023.test.Utils.ItemStacks.*;


public class ClassListener implements Listener {
    private final Plugin plugin;

    public ClassListener(Plugin plugin) {
        this.plugin = plugin;
    }
    private YamlConfiguration loadPlayerConfiguration(UUID playerUUID) {
        File dataFolder = new File(plugin.getDataFolder(), "playerdata");
        File playerFile = new File(dataFolder, playerUUID + ".yml");

        if (playerFile.exists()) {
            return YamlConfiguration.loadConfiguration(playerFile);
        }

        return null;
    }
    private void savePlayerConfiguration(UUID playerUUID, YamlConfiguration config) {
        File dataFolder = new File(plugin.getDataFolder(), "playerdata");
        File playerFile = new File(dataFolder, playerUUID + ".yml");

        try {
            config.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void items(){
        ItemStack pick = Pickaxe();
        ItemStack DPS = Sword();
        ItemStack TANK = Shield();
        ItemStack HEALERBOW = Bow();
        ItemStack HEALERARROW = Arrow();
    }



    @EventHandler
    public void ClassClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        UUID playerUUID = p.getUniqueId();

        YamlConfiguration playerConfig = loadPlayerConfiguration(playerUUID);


        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Classes")) {
            if (e.getCurrentItem() == null) {
                return;
            }

            if (playerConfig != null) {
                String playerClass = playerConfig.getString("Class");

                // Handle class selection based on the retrieved value
                if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
                    if (playerClass != null && playerClass.equals("Dps")) {
                        // Player has already selected the Dps class, handle accordingly
                        p.closeInventory();
                        p.sendMessage("You already are within this class. Please pick another");
                        return;
                    }

                    // Set the player's class in the configuration and save it
                    playerConfig.set("Class", "Dps");
                    savePlayerConfiguration(playerUUID, playerConfig);

                    // Adjust the class and inventory based on the Dps class selection
                    // Rest of the code...
                } else if (e.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
                    if (playerClass != null && playerClass.equals("Healer")) {
                        // Player has already selected the Healer class, handle accordingly
                        p.closeInventory();
                        p.sendMessage("You already are within this class. Please pick another");
                        return;
                    }

                    // Set the player's class in the configuration and save it
                    playerConfig.set("Class", "Healer");
                    savePlayerConfiguration(playerUUID, playerConfig);

                    // Adjust the class and inventory based on the Healer class selection
                    // Rest of the code...
                } else if (e.getCurrentItem().getType() == Material.DIAMOND_PICKAXE) {
                    if (playerClass != null && playerClass.equals("Miner")) {
                        // Player has already selected the Miner class, handle accordingly
                        p.closeInventory();
                        p.sendMessage("You already are within this class. Please pick another");
                        return;
                    }

                    // Set the player's class in the configuration and save it
                    playerConfig.set("Class", "Miner");
                    savePlayerConfiguration(playerUUID, playerConfig);

                    // Adjust the class and inventory based on the Miner class selection
                    // Rest of the code...
                } else if (e.getCurrentItem().getType() == Material.SHIELD) {
                    if (playerClass != null && playerClass.equals("Tank")) {
                        // Player has already selected the Tank class, handle accordingly
                        p.closeInventory();
                        p.sendMessage("You already are within this class. Please pick another");
                        return;
                    }

                    // Set the player's class in the configuration and save it
                    playerConfig.set("Class", "Tank");
                    savePlayerConfiguration(playerUUID, playerConfig);

                } else if (e.getCurrentItem().getType() == Material.TRIDENT) {
                    if (playerClass != null && playerClass.equals("Diver")){
                        p.closeInventory();
                        p.sendMessage("You already are within this class. Please pick another");
                        return;
                    }

                    playerConfig.set("Class", "Diver");
                    savePlayerConfiguration(playerUUID, playerConfig);
                }
            }
            e.setCancelled(true);
        }



    }

}
