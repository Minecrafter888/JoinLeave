/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.Listeners;

import me.hephaestus2023.test.EGCustom;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class JoinEvent implements Listener {
    Plugin plugin = EGCustom.getPlugin(EGCustom.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        UUID playerUUID = e.getPlayer().getUniqueId();
        String playerName = e.getPlayer().getName();
        File dataFolder = new File(plugin.getDataFolder(), "playerdata");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        File PlayerFile = new File(dataFolder, playerUUID + ".yml");
        if (!PlayerFile.exists()){
            try {
                PlayerFile.createNewFile();
                YamlConfiguration playerconfig = YamlConfiguration.loadConfiguration(PlayerFile);

                playerconfig.set("Class", "human");
                playerconfig.set("Name", playerName);

                playerconfig.save(PlayerFile);

            }catch (IOException ev){
                ev.printStackTrace();
            }
        }
    }
}

