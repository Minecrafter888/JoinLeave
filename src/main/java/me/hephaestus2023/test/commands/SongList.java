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

import java.util.ArrayList;
import java.util.List;

public class SongList implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            Inventory invent = Bukkit.createInventory(p, 9, ChatColor.RED + "Music Sheet");
            String LeftClick = "Left CLick";
            String RigtClick = "Right Click";
            String Shift = "Shift";

            ItemStack HHA = new ItemStack(Material.PAPER);
            ItemMeta HHAM = HHA.getItemMeta();
            HHAM.setDisplayName("Harmonious Healing");
            List<String> loreHHA = new ArrayList<>();
            loreHHA.add(Shift);
            loreHHA.add(LeftClick);
            loreHHA.add(LeftClick);
            HHAM.setLore(loreHHA);
            HHA.setItemMeta(HHAM);

            invent.setItem(0, HHA);

            ItemStack RRA = new ItemStack(Material.PAPER);
            ItemMeta RRAM = RRA.getItemMeta();
            RRAM.setDisplayName("Rhythm Rejuvination");
            List<String> loreRRA = new ArrayList<>();
            loreRRA.add(RigtClick);
            loreRRA.add(RigtClick);
            RRAM.setLore(loreRRA);
            RRA.setItemMeta(RRAM);

            invent.setItem(1, RRA);

            p.openInventory(invent);
        }
        return true;
    }
}
