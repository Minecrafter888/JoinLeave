/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.Listeners;

import me.hephaestus2023.test.EGCustom;
import me.hephaestus2023.test.Utils.InventUtil;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import static me.hephaestus2023.test.Utils.ItemStacks.*;

public class TPListener implements Listener {
    public void items(){
        ItemStack pick = Pickaxe();
        ItemStack DPS = Sword();
        ItemStack TANK = Shield();
        ItemStack HEALERBOW = Bow();
        ItemStack HEALERARROW = Arrow();
    }
    Plugin plugin = EGCustom.getPlugin(EGCustom.class);
    @EventHandler
    public void playerteleport(PlayerTeleportEvent e){
        Player p = e.getPlayer();
        boolean hasSword = InventUtil.hasItem(p, Sword());
        boolean hasShield = InventUtil.hasItem(p, Shield());
        boolean hasPickaxe = InventUtil.hasItem(p, Pickaxe());
        boolean hasBow = InventUtil.hasItem(p, Bow());
        boolean hasArrow = InventUtil.hasItem(p, Arrow());
        String worlds = plugin.getConfig().getString("Classes.allowedwords");
        Location Destination = e.getTo();
        World Destination_world = Destination.getWorld();
        World OriginWorld = e.getFrom().getWorld();
        if(Destination_world.getName().equals(worlds)){
            if(hasSword){
                return;
            }else{
                p.getInventory().addItem(Sword());
            }
            if(hasShield){
                return;
            }else{
                p.getInventory().addItem(Shield());
            }
            if(hasPickaxe){
                return;
            }else{
                p.getInventory().addItem(Pickaxe());
            }
            if(hasBow){
                return;
            }else{
                p.getInventory().addItem(Bow());
                p.getInventory().addItem(Arrow());
            }
        }else{
            p.getInventory().removeItem(Shield());
            p.getInventory().removeItem(Sword());
            p.getInventory().removeItem(Bow());
            p.getInventory().removeItem(Arrow());
            p.getInventory().removeItem(Pickaxe());
        }
    }
}
