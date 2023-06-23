/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.Listeners;

import me.hephaestus2023.test.EGCustom;
import me.hephaestus2023.test.Utils.InventUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Furnace;
import org.bukkit.block.data.type.TNT;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.UUID;

import static me.hephaestus2023.test.Utils.ItemStacks.Trident;

public class BomberTnt implements Listener {
    Plugin plugin = EGCustom.getPlugin(EGCustom.class);

    private YamlConfiguration loadPlayerConfiguration(UUID playerUUID) {
        File dataFolder = new File(plugin.getDataFolder(), "playerdata");
        File playerFile = new File(dataFolder, playerUUID + ".yml");

        if (playerFile.exists()) {
            return YamlConfiguration.loadConfiguration(playerFile);
        }

        return null;
    }

    @EventHandler
    public void Tntinteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        Block clickedBlock = event.getClickedBlock();

        UUID playerUUID = player.getUniqueId();
        YamlConfiguration playerConfig = loadPlayerConfiguration(playerUUID);

        if (playerConfig == null) {
            return;
        }

        String playerClass = playerConfig.getString("Class");

        if (!"Bomber".equalsIgnoreCase(playerClass)) {
            return;
        }
        if (clickedBlock != null && clickedBlock.getType() == Material.TNT) {
            if (playerConfig == null) {
                return;
            }

            if (!"Bomber".equalsIgnoreCase(playerClass)) {
                return;
            }

            if (action == Action.RIGHT_CLICK_BLOCK) {
                TNTPrimed tntPrimed = clickedBlock.getWorld().spawn(clickedBlock.getLocation(), TNTPrimed.class);
                tntPrimed.setYield(5.0F);
                tntPrimed.setFuseTicks(0);
                clickedBlock.setType(Material.AIR);
                event.setCancelled(true); // Cancel the event to prevent other interactions
            }
        }

    }
}
