/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.commands;

import me.hephaestus2023.test.EGCustom;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.UUID;

public class ClassChecker implements CommandExecutor {
    private final String permission = "EGCustom.ClassChecker";
    Plugin plugin = EGCustom.getPlugin(EGCustom.class);
    private YamlConfiguration loadPlayerConfiguration(UUID playerUUID) {
        File dataFolder = new File(plugin.getDataFolder(), "playerdata");
        File playerFile = new File(dataFolder, playerUUID + ".yml");

        if (playerFile.exists()) {
            return YamlConfiguration.loadConfiguration(playerFile);
        }

        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission(permission)) {
            sender.sendMessage("You do not have permission to use this command.");
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("Usage: /playerinfo <player>");
            return true;
        }

        Player target = plugin.getServer().getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage("Player not found.");
            return true;
        }

        UUID playerUUID = target.getUniqueId();
        YamlConfiguration playerConfig = loadPlayerConfiguration(playerUUID);

        if (playerConfig != null && playerConfig.contains("Class")) {
            String playerClass = playerConfig.getString("Class");
            sender.sendMessage("Player: " + target.getName());
            sender.sendMessage("Class: " + playerClass);
        } else {
            sender.sendMessage("Player does not have a class assigned.");
        }

        return true;
    }
}
