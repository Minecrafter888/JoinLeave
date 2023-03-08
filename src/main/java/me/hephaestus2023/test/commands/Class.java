/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Class implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            Inventory invent = Bukkit.createInventory(p, 9, ChatColor.GOLD + "Classes");

            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
            ItemMeta itemmeta = sword.getItemMeta();
            itemmeta.setDisplayName(ChatColor.RED + "Attack class");
            sword.setItemMeta(itemmeta);


            invent.setItem(0, sword);


            ItemStack healer = new ItemStack(Material.GOLDEN_APPLE, 1);
            ItemMeta item = healer.getItemMeta();
            item.setDisplayName(ChatColor.GOLD + "Healer Class");
            healer.setItemMeta(item);

            invent.setItem(1, healer);


            ItemStack tank = new ItemStack(Material.SHIELD, 1);
            ItemMeta Tankmeta = tank.getItemMeta();
            Tankmeta.setDisplayName(ChatColor.WHITE + "Tank Class");
            tank.setItemMeta(Tankmeta);

            invent.setItem(2, tank);

            p.openInventory(invent);

            }


        return true;
    }
}
