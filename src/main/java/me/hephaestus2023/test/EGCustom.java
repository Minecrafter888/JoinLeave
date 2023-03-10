/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test;

import me.hephaestus2023.test.JoinLeaveListener.ClassListener;
import me.hephaestus2023.test.JoinLeaveListener.joinleaveListener;
import me.hephaestus2023.test.JoinLeaveListener.onDrop;
import me.hephaestus2023.test.commands.Class;
import me.hephaestus2023.test.commands.Reload;
import org.bukkit.plugin.java.JavaPlugin;

public final class EGCustom extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new onDrop(), this);
        getServer().getPluginManager().registerEvents(new ClassListener(), this);
        getServer().getPluginManager().registerEvents(new joinleaveListener(), this);
        getCommand("reload-Test").setExecutor(new Reload());
        getCommand("Class").setExecutor(new Class());

    }

}
