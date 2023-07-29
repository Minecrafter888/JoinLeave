//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.hephaestus2023.test.Listeners;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.hephaestus2023.test.EGCustom;
import me.hephaestus2023.test.Utils.ItemStacks;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import static me.hephaestus2023.test.Utils.ItemStacks.*;

public class DeathListener implements Listener {
    Plugin plugin = EGCustom.getPlugin(EGCustom.class);
    private YamlConfiguration loadPlayerConfiguration(UUID playerUUID) {
        File dataFolder = new File(this.plugin.getDataFolder(), "playerdata");
        File playerFile = new File(dataFolder, playerUUID + ".yml");
        return playerFile.exists() ? YamlConfiguration.loadConfiguration(playerFile) : null;
    }

    public void items() {
        ItemStack pick = Pickaxe();
        ItemStack DPS = Sword();
        ItemStack TANK = Shield();
        ItemStack HEALERBOW = Bow();
        ItemStack HEALERARROW = Arrow();
        ItemStack Diver = Trident();
        ItemStack bomberhelm = Bomberhelm();
        ItemStack bombershirt = Bombershirt();
        ItemStack bomberpants = Bomberpant();
        ItemStack bomberboots = Bomberboots();
        ItemStack Melodist = Stick();
    }

    public void Bomberarmor(Player p) {
        p.getInventory().setHelmet(Bomberhelm());
        p.getInventory().setChestplate(Bombershirt());
        p.getInventory().setLeggings(Bomberpant());
        p.getInventory().setBoots(Bomberboots());
    }
    private boolean isPlayerDead = false;

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (isPlayerDead) {
            return; // Player has already died, skip further processing
        }

        Player player = event.getEntity();
        UUID playerUUID = player.getUniqueId();
        YamlConfiguration playerConfig = loadPlayerConfiguration(playerUUID);
        List<ItemStack> drops = event.getDrops();

        List<ItemStack> itemsToRemove = getItemsToRemove();
        drops.removeIf(drop -> itemsToRemove.stream().anyMatch(item -> item.isSimilar(drop)));

        // Remove the items from the player's inventory
        for (ItemStack item : itemsToRemove) {
            player.getInventory().remove(item);
        }
        isPlayerDead = true;
    }
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        isPlayerDead = false; // Reset the flag when the player respawns
    }
    private List<ItemStack> getItemsToRemove() {
        List<ItemStack> itemsToRemove = new ArrayList<>();
        itemsToRemove.add(Pickaxe());
        itemsToRemove.add(Sword());
        itemsToRemove.add(Shield());
        itemsToRemove.add(Bow());
        itemsToRemove.add(Arrow());
        itemsToRemove.add(Trident());
        itemsToRemove.add(Bomberhelm());
        itemsToRemove.add(Bombershirt());
        itemsToRemove.add(Bomberpant());
        itemsToRemove.add(Bomberboots());
        itemsToRemove.add(Stick());
        return itemsToRemove;
    }
}

