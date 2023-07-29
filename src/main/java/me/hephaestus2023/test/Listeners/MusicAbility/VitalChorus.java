/*
 * cmd= calls cmd
 * conc= calls forward config string
 * config= config to main class
 * econfig= config to non main
 * ilist= import listener to main
 * listener= implements listener
 * permission= adds perms to main
 * sender= calls player sender
 * tnull= target null
 * tplayer= target online
 */

package me.hephaestus2023.test.Listeners.MusicAbility;

import me.hephaestus2023.test.EGCustom;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.util.UUID;

public class VitalChorus implements Listener {
    Plugin plugin = EGCustom.getPlugin(EGCustom.class);

    private YamlConfiguration loadPlayerConfiguration(UUID playerUUID) {
        File dataFolder = new File(plugin.getDataFolder(), "playerdata");
        File playerFile = new File(dataFolder, playerUUID + ".yml");

        if (playerFile.exists()) {
            return YamlConfiguration.loadConfiguration(playerFile);
        }

        return null;
    }

    @EventHandler
    public void playerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        YamlConfiguration playerConfig = loadPlayerConfiguration(playerUUID);

        if (playerConfig == null) {
            return;
        }

        String playerClass = playerConfig.getString("Class");
        if (playerClass.equals("Melodist")) {
            if (player.isSneaking()) { // Check if the player is sneaking (Shift key is tapped)
                int duration = plugin.getConfig().getInt("Classes.melodist.vitalchorus.duration");
                int amp = plugin.getConfig().getInt("Classes.melodist.vitalchorus.amp");

                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, amp));
            }
        }
    }
}
