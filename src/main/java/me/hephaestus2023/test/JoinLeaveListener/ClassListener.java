/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.JoinLeaveListener;

import me.hephaestus2023.test.EGCustom;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;


public class ClassListener implements Listener {
    Plugin plugin = EGCustom.getPlugin(EGCustom.class);

    @EventHandler
    public void ClassClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();


        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Classes")) {


            ItemStack shieldd = new ItemStack(Material.SHIELD);
            ItemMeta sd = shieldd.getItemMeta();
            sd.setUnbreakable(true);
            sd.setDisplayName("Tank shield");
            shieldd.setItemMeta(sd);

            ItemStack swordd = new ItemStack(Material.IRON_SWORD);
            ItemMeta swd = swordd.getItemMeta();
            swd.setDisplayName("Dps sword");
            swordd.setItemMeta(swd);

            ItemStack bowd = new ItemStack(Material.BOW);
            bowd.addEnchantment(Enchantment.ARROW_INFINITE, 1);
            ItemStack arrowd = new ItemStack(Material.TIPPED_ARROW, 64);
            PotionMeta tad = (PotionMeta) arrowd.getItemMeta();
            tad.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
            arrowd.setItemMeta(tad);
            ItemMeta healerd = bowd.getItemMeta();
            healerd.setDisplayName("Healer bow");
            bowd.addEnchantment(Enchantment.ARROW_INFINITE, 1);
            bowd.setItemMeta(healerd);


            if (e.getCurrentItem() == null) {
                return;
            }


            if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
                    int am = plugin.getConfig().getInt("Classes.DpsBuffMultiplier");

                    if(p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)){
                        p.sendMessage("You already are within this class. Please pick another");
                        return;

                    }else {


                        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, am));
                        p.sendMessage(ChatColor.RED + "Attack class selected");
                        ItemStack sword = new ItemStack(Material.IRON_SWORD);
                        ItemMeta sm = sword.getItemMeta();
                        sm.setDisplayName("Dps sword");
                        sword.setItemMeta(sm);
                        p.getInventory().addItem(sword);
                        p.getInventory().removeItem(shieldd);
                        p.getInventory().removeItem(bowd);
                        p.getInventory().removeItem(arrowd);
                        p.removePotionEffect(PotionEffectType.REGENERATION);
                        p.removePotionEffect(PotionEffectType.HEALTH_BOOST);

                    }
            }

            if (e.getCurrentItem().getType() == Material.GOLDEN_APPLE) {

                int amp = plugin.getConfig().getInt("Classes.HealerBuffMultiplier");

                if(p.hasPotionEffect(PotionEffectType.HEALTH_BOOST)){


                    p.sendMessage("You already are within this class. Please pick another");
                    return;
                }else {

                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, amp));
                    p.sendMessage(ChatColor.GOLD + "Healer class selected");
                    ItemStack bow = new ItemStack(Material.BOW);
                    bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                    ItemStack arrow = new ItemStack(Material.TIPPED_ARROW, 64);
                    PotionMeta ta = (PotionMeta) arrow.getItemMeta();
                    ta.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
                    arrow.setItemMeta(ta);
                    ItemMeta healer = bow.getItemMeta();
                    healer.setDisplayName("Healer bow");
                    bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                    bow.setItemMeta(healer);
                    p.getInventory().addItem(bow);
                    p.getInventory().addItem(arrow);
                    p.getInventory().removeItem(shieldd);
                    p.getInventory().removeItem(swordd);
                    p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                }

            }
            if (e.getCurrentItem().getType() == Material.SHIELD) {

                int pli = plugin.getConfig().getInt("Classes.TankBuffMultiplier");
                if(p.hasPotionEffect(PotionEffectType.HEALTH_BOOST)){

                    p.sendMessage("You already are within this class. Please pick another");
                    return;
                }else {

                    p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, pli));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 12, 3));
                    p.sendMessage(ChatColor.WHITE + "Tank classes selected");
                    ItemStack shield = new ItemStack(Material.SHIELD);
                    ItemMeta sm = shield.getItemMeta();
                    sm.setUnbreakable(true);
                    sm.setDisplayName("Tank shield");
                    shield.setItemMeta(sm);
                    p.getInventory().addItem(shield);
                    p.getInventory().removeItem(swordd);
                    p.getInventory().removeItem(bowd);
                    p.getInventory().removeItem(arrowd);
                    p.removePotionEffect(PotionEffectType.REGENERATION);
                    p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                }
            }
            e.setCancelled(true);
        }



    }

}
