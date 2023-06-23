/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.Listeners;

import jdk.tools.jlink.internal.PluginRepository;
import me.hephaestus2023.test.EGCustom;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.UUID;

import static me.hephaestus2023.test.Utils.ItemStacks.*;
import static me.hephaestus2023.test.Utils.ItemStacks.Bomberboots;

public class RespawnListener implements Listener {

    Plugin plugin = EGCustom.getPlugin(EGCustom.class);
    private YamlConfiguration loadPlayerConfiguration(UUID playerUUID) {
        File dataFolder = new File(plugin.getDataFolder(), "playerdata");
        File playerFile = new File(dataFolder, playerUUID + ".yml");

        if (playerFile.exists()) {
            return YamlConfiguration.loadConfiguration(playerFile);
        }

        return null;
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
    public void Bomberarmor(Player p){
        p.getInventory().setHelmet(Bomberhelm());
        p.getInventory().setChestplate(Bombershirt());
        p.getInventory().setLeggings(Bomberpant());
        p.getInventory().setBoots(Bomberboots());
    }

    @EventHandler
    public void Respawnevent (PlayerRespawnEvent event){
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        YamlConfiguration playerConfig = loadPlayerConfiguration(playerUUID);
        String playerClass = playerConfig.getString("Class");
        if(playerClass.equals("Dps")){
            player.getInventory().addItem(Sword());
        }
        if(playerClass.equals("Healer")){
            player.getInventory().addItem(Bow());
            player.getInventory().addItem(Arrow());
        }
        if(playerClass.equals("Tank")){
            player.getInventory().addItem(Shield());
        }
        if(playerClass.equals("Miner")){
            player.getInventory().addItem(Pickaxe());
        }
        if(playerClass.equals("Diver")){
            player.getInventory().addItem(Trident());
        }
        if(playerClass.equals("Bomber")){
            Bomberarmor(player);
        }
    }
}
