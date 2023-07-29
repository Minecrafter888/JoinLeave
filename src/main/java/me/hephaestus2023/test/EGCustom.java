/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test;

import me.hephaestus2023.test.Listeners.*;

import me.hephaestus2023.test.Listeners.MusicAbility.HarmonicDefense;
import me.hephaestus2023.test.Listeners.MusicAbility.HarmonyVeil;
import me.hephaestus2023.test.Listeners.MusicAbility.VitalChorus;
import me.hephaestus2023.test.commands.Class;
import me.hephaestus2023.test.commands.ClassChecker;
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
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new RespawnListener(), this);
        getServer().getPluginManager().registerEvents(new TPListener(), this);
        getServer().getPluginManager().registerEvents(new HarmonicDefense(), this);
        getServer().getPluginManager().registerEvents(new HarmonyVeil(), this);
        getServer().getPluginManager().registerEvents(new VitalChorus(), this);
        getCommand("Class").setExecutor(new Class());
        getCommand("Checker").setExecutor(new ClassChecker());
        PluginManager pluginManager = getServer().getPluginManager();
        Permission yourPermission = new Permission("EGCustom.ClassChecker");
        pluginManager.addPermission(yourPermission);
    }
}
