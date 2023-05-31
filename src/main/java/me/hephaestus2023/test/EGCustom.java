/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test;

import me.hephaestus2023.test.JoinLeaveListener.ClassListener;
import me.hephaestus2023.test.JoinLeaveListener.JoinEvent;
import me.hephaestus2023.test.JoinLeaveListener.onDrop;
import me.hephaestus2023.test.commands.Class;
import me.hephaestus2023.test.commands.Reload;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
        getCommand("reload-Test").setExecutor(new Reload());
        getCommand("Class").setExecutor(new Class());

    }
}
