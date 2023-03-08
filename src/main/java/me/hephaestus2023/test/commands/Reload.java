/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.commands;


import me.hephaestus2023.test.EGCustom;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Reload implements CommandExecutor {
    Plugin plugin = EGCustom.getPlugin(EGCustom.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("Test.Reload")){

                plugin.reloadConfig();
                Bukkit.getLogger().info("[Test] has been reloaded");
                p.sendMessage("Plugin is being reset");

            }else{

                p.sendMessage("You do not have the required permission");
                System.out.println(p.getDisplayName() + " did not have that correct permission");

            }


        } else if (sender instanceof ConsoleCommandSender){

            plugin.reloadConfig();
            Bukkit.getLogger().info("[Test] has been reloaded");

        }else{

            Bukkit.getLogger().info("You haven't given command input in the proper place. Try in game or console");

        }


        return true;
    }
}
