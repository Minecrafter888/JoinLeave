/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test;

import me.hephaestus2023.test.Listeners.*;
import me.hephaestus2023.test.commands.Class;
import me.hephaestus2023.test.commands.ClassChecker;
import me.hephaestus2023.test.commands.Reload;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class EGCustom extends JavaPlugin {
    File database;
    FileConfiguration data;


    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        saveConfig();
        getServer().getPluginManager().registerEvents(new onDrop(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new ClassListener(this), this);
        getServer().getPluginManager().registerEvents(new BomberTnt(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new RespawnListener(), this);
        getCommand("reload-Test").setExecutor(new Reload());
        getCommand("Class").setExecutor(new Class());
        getCommand("Checker").setExecutor(new ClassChecker());
        PluginManager pluginManager = getServer().getPluginManager();
        Permission yourPermission = new Permission("EGCustom.ClassChecker");
        pluginManager.addPermission(yourPermission);
    }
}
