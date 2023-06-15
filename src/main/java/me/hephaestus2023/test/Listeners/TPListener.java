/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.Listeners;

import me.hephaestus2023.test.EGCustom;
import me.hephaestus2023.test.Utils.InventUtil;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static me.hephaestus2023.test.Utils.ItemStacks.*;

public class TPListener implements Listener {
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
        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
        player.removePotionEffect(PotionEffectType.REGENERATION);
        player.removePotionEffect(PotionEffectType.FAST_DIGGING);
        player.removePotionEffect(PotionEffectType.WATER_BREATHING);
    }
    Plugin plugin = EGCustom.getPlugin(EGCustom.class);
    @EventHandler
    public void playerteleport(PlayerTeleportEvent e){
        Player p = e.getPlayer();
        UUID playerUUID = p.getUniqueId();
        boolean hasSword = InventUtil.hasItem(p, Sword());
        boolean hasShield = InventUtil.hasItem(p, Shield());
        boolean hasPickaxe = InventUtil.hasItem(p, Pickaxe());
        boolean hasBow = InventUtil.hasItem(p, Bow());
        boolean hasTrident = InventUtil.hasItem(p, Trident());
        boolean hasarmor = InventUtil.hasItem(p, Bomberhelm());
        int Dps = plugin.getConfig().getInt("Classes.DpsBuffMultiplier");
        int Healer = plugin.getConfig().getInt("Classes.HealerBuffMultiplier");
        int Tank = plugin.getConfig().getInt("Classes.TankBuffMultiplier");
        int Miner = plugin.getConfig().getInt("Classes.Minerbuffmultiplier");
        int Diver = plugin.getConfig().getInt("Classes.Diverbuffmultiplier");
        YamlConfiguration playerConfig = loadPlayerConfiguration(playerUUID);
        String worlds = plugin.getConfig().getString("Classes.allowedwords");
        Location Destination = e.getTo();
        World Destination_world = Destination.getWorld();
        if(Destination_world.getName().equals(worlds)){
            String playerClass = playerConfig.getString("Class");
            if(playerClass.equals("Dps")){
                if(hasSword){
                    return;
                }else{
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, Dps));
                    p.getInventory().addItem(Sword());
                }
            }
            if(playerClass.equals("Healer")){
                if(hasBow){
                    return;
                }else{
                    p.getInventory().addItem(Bow());
                    p.getInventory().addItem(Arrow());
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, Healer));
                }
            }
            if(playerClass.equals("Tank")){
                if(hasShield){
                    return;
                }else{
                    p.getInventory().addItem(Shield());
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, Tank));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 100));
                }
            }
            if(playerClass.equals("Miner")){
                if(hasPickaxe){
                    return;
                }else{
                    p.getInventory().addItem(Pickaxe());
                    p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, Miner));
                }
            }
            if(playerClass.equals("Diver")){
                if(hasTrident){
                    return;
                }else{
                    p.getInventory().addItem(Trident());
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, Diver));
                }
            }
            if(playerClass.equals("Bomber")){
                if(hasarmor){
                    return;
                }else{
                    AddItems(p);
                }
            }
        }else{
            p.getInventory().removeItem(Shield());
            p.getInventory().removeItem(Sword());
            p.getInventory().removeItem(Bow());
            p.getInventory().removeItem(Arrow());
            p.getInventory().removeItem(Pickaxe());
            p.getInventory().removeItem(Trident());
            DeleteItems(p);
            p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
            p.removePotionEffect(PotionEffectType.REGENERATION);
            p.removePotionEffect(PotionEffectType.FAST_DIGGING);
            p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            p.removePotionEffect(PotionEffectType.WATER_BREATHING);
        }
    }
}
