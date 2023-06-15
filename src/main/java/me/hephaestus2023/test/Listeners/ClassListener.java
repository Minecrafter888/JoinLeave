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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


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
        ItemStack Diver = Trident();
        ItemStack bomberhelm = Bomberhelm();
        ItemStack bombershirt = Bombershirt();
        ItemStack bomberpants = Bomberpant();
        ItemStack bomberboots = Bomberboots();
    }

    public void DeleteItems(Player player){
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        player.updateInventory();
    }
    public void AddItems(Player players){
        players.getInventory().setHelmet(Bomberhelm());
        players.getInventory().setChestplate(Bombershirt());
        players.getInventory().setLeggings(Bomberpant());
        players.getInventory().setBoots(Bomberboots());
        players.updateInventory();
    }
    public void EmptyClass(Player player){
        player.getInventory().removeItem(Sword());
        player.getInventory().removeItem(Shield());
        player.getInventory().removeItem(Bow());
        player.getInventory().removeItem(Arrow());
        player.getInventory().removeItem(Pickaxe());
        player.getInventory().removeItem(Trident());
        DeleteItems(player);
        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
        player.removePotionEffect(PotionEffectType.REGENERATION);
        player.removePotionEffect(PotionEffectType.FAST_DIGGING);
        player.removePotionEffect(PotionEffectType.WATER_BREATHING);
    }
    public void bomberclass(Player player){
        player.getInventory().removeItem(Sword());
        player.getInventory().removeItem(Shield());
        player.getInventory().removeItem(Bow());
        player.getInventory().removeItem(Arrow());
        player.getInventory().removeItem(Pickaxe());
        player.getInventory().removeItem(Trident());
        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
        player.removePotionEffect(PotionEffectType.REGENERATION);
        player.removePotionEffect(PotionEffectType.FAST_DIGGING);
        player.removePotionEffect(PotionEffectType.WATER_BREATHING);
    }



    @EventHandler
    public void ClassClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        UUID playerUUID = p.getUniqueId();
        int Dps = plugin.getConfig().getInt("Classes.DpsBuffMultiplier");
        int Healer = plugin.getConfig().getInt("Classes.HealerBuffMultiplier");
        int Tank = plugin.getConfig().getInt("Classes.TankBuffMultiplier");
        int Miner = plugin.getConfig().getInt("Classes.Minerbuffmultiplier");
        int Diver = plugin.getConfig().getInt("Classes.Diverbuffmultiplier");

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
                    p.getInventory().addItem(Sword());
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, Dps));
                    p.getInventory().removeItem(Shield());
                    p.getInventory().removeItem(Bow());
                    p.getInventory().removeItem(Arrow());
                    p.getInventory().removeItem(Pickaxe());
                    p.getInventory().removeItem(Trident());
                    DeleteItems(p);
                    p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
                    p.removePotionEffect(PotionEffectType.REGENERATION);
                    p.removePotionEffect(PotionEffectType.FAST_DIGGING);
                    p.removePotionEffect(PotionEffectType.WATER_BREATHING);
                    p.closeInventory();
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
                    p.getInventory().addItem(Bow());
                    p.getInventory().addItem(Arrow());
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, Healer));
                    p.getInventory().removeItem(Shield());
                    p.getInventory().removeItem(Sword());
                    p.getInventory().removeItem(Pickaxe());
                    p.getInventory().removeItem(Trident());
                    DeleteItems(p);
                    p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                    p.removePotionEffect(PotionEffectType.FAST_DIGGING);
                    p.removePotionEffect(PotionEffectType.WATER_BREATHING);
                    p.closeInventory();
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
                    p.getInventory().addItem(Pickaxe());
                    p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, Miner));
                    p.getInventory().removeItem(Shield());
                    p.getInventory().removeItem(Bow());
                    p.getInventory().removeItem(Arrow());
                    p.getInventory().removeItem(Sword());
                    p.getInventory().removeItem(Trident());
                    DeleteItems(p);
                    p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
                    p.removePotionEffect(PotionEffectType.REGENERATION);
                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                    p.removePotionEffect(PotionEffectType.WATER_BREATHING);
                    p.closeInventory();
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
                    p.getInventory().addItem(Shield());
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, Tank));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 100));
                    p.getInventory().removeItem(Sword());
                    p.getInventory().removeItem(Bow());
                    p.getInventory().removeItem(Arrow());
                    p.getInventory().removeItem(Pickaxe());
                    p.getInventory().removeItem(Trident());
                    DeleteItems(p);
                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                    p.removePotionEffect(PotionEffectType.REGENERATION);
                    p.removePotionEffect(PotionEffectType.FAST_DIGGING);
                    p.removePotionEffect(PotionEffectType.WATER_BREATHING);
                    p.closeInventory();
                    playerConfig.set("Class", "Tank");
                    savePlayerConfiguration(playerUUID, playerConfig);

                } else if (e.getCurrentItem().getType() == Material.TRIDENT) {
                    if (playerClass != null && playerClass.equals("Diver")){
                        p.closeInventory();
                        p.sendMessage("You already are within this class. Please pick another");
                        return;
                    }
                    p.getInventory().addItem(Trident());
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, Diver));
                    p.getInventory().removeItem(Shield());
                    p.getInventory().removeItem(Bow());
                    p.getInventory().removeItem(Arrow());
                    p.getInventory().removeItem(Pickaxe());
                    p.getInventory().removeItem(Sword());
                    DeleteItems(p);
                    p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
                    p.removePotionEffect(PotionEffectType.REGENERATION);
                    p.removePotionEffect(PotionEffectType.FAST_DIGGING);
                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                    p.closeInventory();
                    playerConfig.set("Class", "Diver");
                    savePlayerConfiguration(playerUUID, playerConfig);
                }else if(e.getCurrentItem().getType() == Material.TNT){
                    if(playerClass != null && playerClass.equals("Bomber")){
                        p.closeInventory();
                        p.sendMessage("You already are within this class. Please pick another");
                    }

                    AddItems(p);
                    bomberclass(p);
                    playerConfig.set("Class", "Bomber");
                    savePlayerConfiguration(playerUUID, playerConfig);
                    p.closeInventory();
                }
            }
            e.setCancelled(true);
        }



    }

}
