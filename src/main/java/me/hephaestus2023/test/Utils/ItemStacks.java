/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.Utils;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class ItemStacks {


    public static ItemStack Pickaxe(){

        ItemStack pickaxe = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta px = pickaxe.getItemMeta();
        px.setUnbreakable(true);
        px.setDisplayName("Miner pickaxe");
        px.addEnchant(Enchantment.DIG_SPEED, 1, false);
        px.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, false);
        pickaxe.setItemMeta(px);

        return pickaxe;
    }
    public static ItemStack Sword(){
        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        ItemMeta sw = sword.getItemMeta();
        sw.setUnbreakable(true);
        sw.setDisplayName("Dps sword");
        sword.setItemMeta(sw);
        return sword;
    }
    public static ItemStack Shield(){
        ItemStack shield = new ItemStack(Material.SHIELD);
        ItemMeta sd = shield.getItemMeta();
        sd.setUnbreakable(true);
        sd.setDisplayName("Tank shield");
        shield.setItemMeta(sd);
        return shield;
    }
    public static ItemStack Arrow(){
        ItemStack arrow = new ItemStack(Material.TIPPED_ARROW, 64);
        PotionMeta ta = (PotionMeta) arrow.getItemMeta();
        ta.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
        arrow.setItemMeta(ta);
        return arrow;
    }
    public static ItemStack Bow(){
        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        ItemMeta healer = bow.getItemMeta();
        healer.setUnbreakable(true);
        healer.setDisplayName("Healer bow");
        bow.setItemMeta(healer);
        return bow;
    }
    public static ItemStack Trident(){
        ItemStack trident = new ItemStack(Material.TRIDENT);
        trident.addEnchantment(Enchantment.LOYALTY, 3);
        ItemMeta diver = trident.getItemMeta();
        diver.setUnbreakable(true);
        diver.setDisplayName("Diver trident");
        trident.setItemMeta(diver);
        return trident;
    }
}
