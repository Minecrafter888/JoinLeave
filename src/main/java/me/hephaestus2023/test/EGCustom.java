/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test;

import me.hephaestus2023.test.Listeners.BomberTnt;
import me.hephaestus2023.test.Listeners.ClassListener;
import me.hephaestus2023.test.Listeners.JoinEvent;
import me.hephaestus2023.test.Listeners.onDrop;
import me.hephaestus2023.test.commands.Class;
import me.hephaestus2023.test.commands.ClassChecker;
import me.hephaestus2023.test.commands.Reload;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

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
        getCommand("reload-Test").setExecutor(new Reload());
        getCommand("Class").setExecutor(new Class());
        getCommand("Checker").setExecutor(new ClassChecker());
        PluginManager pluginManager = getServer().getPluginManager();
        Permission yourPermission = new Permission("EGCustom.ClassChecker");
        pluginManager.addPermission(yourPermission);

    }
}
