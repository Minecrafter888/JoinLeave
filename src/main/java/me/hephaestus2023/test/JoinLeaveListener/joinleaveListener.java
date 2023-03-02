package me.hephaestus2023.test.JoinLeaveListener;

import me.hephaestus2023.test.Test;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class joinleaveListener implements Listener {

    Plugin plugin = Test.getPlugin(Test.class);

    @EventHandler
    public void join(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (player.hasPlayedBefore()){
            String ja = plugin.getConfig().getString("wa");
            event.setJoinMessage(ja);

        }
        else{
            String j = plugin.getConfig().getString("w");
            event.setJoinMessage(j);

        }



    }



    @EventHandler
    public void Leave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        String l = plugin.getConfig().getString("l");

        event.setQuitMessage(l);

    }
}
