package me.hephaestus2023.test;

import me.hephaestus2023.test.JoinLeaveListener.joinleaveListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Test extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new joinleaveListener(), this);

    }

}
