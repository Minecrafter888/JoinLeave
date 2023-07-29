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
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.util.UUID;

public class HarmonicDefense implements Listener {
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
    public void playerleftclick (PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        UUID playerUUID = player.getUniqueId();
        YamlConfiguration playerConfig = loadPlayerConfiguration(playerUUID);
        int duration = plugin.getConfig().getInt("Classes.melodist.harmonicdefense.duration");
        int amp = plugin.getConfig().getInt("Classes.melodist.harmonicdefense.amp");

        if(playerConfig == null){
            return;
        }
        String playerClass = playerConfig.getString("Class");
        if(playerClass.equals("Melodist")){
            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta itemMeta = item.getItemMeta();
            if(item.getType().toString().equals("STICK") && itemMeta.hasDisplayName() && itemMeta.getDisplayName().equals("Rhythm Rod")){
                if(action == Action.LEFT_CLICK_AIR){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, duration, amp));
                }else{
                    return;
                }
            }else{
                return;
            }
        }
    }
}
