/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.Listeners.MusicAbility;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class HarmoniousHealingAbility implements Listener {
    private final JavaPlugin plugin;
    private final Map<Player, Integer> shiftClickCounter;

    public HarmoniousHealingAbility(JavaPlugin plugin) {
        this.plugin = plugin;
        this.shiftClickCounter = new HashMap<>();
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (event.getClickedBlock() == null && event.getItem() != null && event.getItem().getType() == Material.STICK
                    && event.getItem().getItemMeta().hasDisplayName()
                    && event.getItem().getItemMeta().getDisplayName().equals("Rhythm Rod")) {

                if (event.getPlayer().isSneaking()) {
                    incrementShiftClickCounter(player);

                    if (getShiftClickCounter(player) >= 2) {
                        resetShiftClickCounter(player);
                        activateHarmoniousHealing(player);
                    }
                } else {
                    resetShiftClickCounter(player);
                }
            } else {
                resetShiftClickCounter(player);
            }
        } else {
            resetShiftClickCounter(player);
        }
    }

    private int getShiftClickCounter(Player player) {
        return shiftClickCounter.getOrDefault(player, 0);
    }

    private void incrementShiftClickCounter(Player player) {
        shiftClickCounter.put(player, getShiftClickCounter(player) + 1);
    }

    private void resetShiftClickCounter(Player player) {
        shiftClickCounter.remove(player);
    }

    private void activateHarmoniousHealing(Player player) {
        new BukkitRunnable() {
            private int duration = 10; // Duration in seconds
            private final double radius = 5.0; // Radius of healing aura

            @Override
            public void run() {
                if (duration > 0 && player.isValid()) {
                    Location location = player.getLocation();

                    // Apply healing effect to the player
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 1));

                    // Apply healing effect to nearby players within the radius
                    for (Player nearbyPlayer : player.getWorld().getPlayers()) {
                        if (!nearbyPlayer.equals(player) && nearbyPlayer.getLocation().distance(location) <= radius) {
                            nearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 1));
                        }
                    }

                    duration--;
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L); // Run the healing aura task every second
    }
}
