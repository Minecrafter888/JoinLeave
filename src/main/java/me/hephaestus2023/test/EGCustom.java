package me.hephaestus2023.test;

import me.hephaestus2023.test.JoinLeaveListener.joinleaveListener;
import me.hephaestus2023.test.commands.Reload;
import org.bukkit.plugin.java.JavaPlugin;

public final class EGCustom extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new joinleaveListener(), this);
        getCommand("reload-Test").setExecutor(new Reload());

    }

}
