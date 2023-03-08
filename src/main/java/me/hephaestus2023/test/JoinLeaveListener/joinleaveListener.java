/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.JoinLeaveListener;

import me.hephaestus2023.test.EGCustom;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class joinleaveListener implements Listener {

    Plugin plugin = EGCustom.getPlugin(EGCustom.class);

    @EventHandler
    public void join(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (player.hasPlayedBefore()){
            String ja = plugin.getConfig().getString("JoinLeave.welcome-again").replace("$player", player.getName());
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', ja));

        }
        else{
            String j = plugin.getConfig().getString("JoinLeave.welcome").replace("$player", player.getName());
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', j));

        }



    }



    @EventHandler
    public void Leave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        String l = plugin.getConfig().getString("JoinLeave.Leave").replace("$player", player.getName());
        String p = player.getName();

        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', l));

    }
}