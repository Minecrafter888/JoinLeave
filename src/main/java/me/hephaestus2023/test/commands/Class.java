/*
 * Copyright (c) 2023. Hephaestus2023
 * All rights reserved to owner
 */

package me.hephaestus2023.test.commands;


        import me.hephaestus2023.test.EGCustom;
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
        import org.bukkit.plugin.Plugin;

        import java.util.HashMap;
        import java.util.Map;

public class Class implements CommandExecutor {
    Plugin plugin = EGCustom.getPlugin(EGCustom.class);
    private int cooldownSeconds;


    private final Map<Player, Long> cooldowns = new HashMap<>();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            int cld = plugin.getConfig().getInt("Classes.CommandCooldown");
            cooldownSeconds = cld;

            if (isOnCooldown(p)) {
                long secondsLeft = getCooldownSeconds(p);
                p.sendMessage(ChatColor.RED + "You must wait " + secondsLeft + " seconds before using this command again.");
                return true;
            }

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

            ItemStack miner = new ItemStack(Material.DIAMOND_PICKAXE, 1);
            ItemMeta Minermeta = miner.getItemMeta();
            Minermeta.setDisplayName(ChatColor.BLUE + "Miner Class");
            miner.setItemMeta(Minermeta);

            invent.setItem(3, miner);

            ItemStack diver = new ItemStack(Material.TRIDENT, 1);
            ItemMeta Divermeta = diver.getItemMeta();
            Divermeta.setDisplayName(ChatColor.BLUE + "Diver Class");
            diver.setItemMeta(Minermeta);

            invent.setItem(4, diver);

            p.openInventory(invent);
            setCooldown(p, cooldownSeconds);

        }


        return true;
    }
    private void setCooldown(Player player, int clds) {
        long cooldownTime = System.currentTimeMillis() + (clds + cooldownSeconds);
        cooldowns.put(player, cooldownTime);
    }

    private boolean isOnCooldown(Player player) {
        return cooldowns.containsKey(player) && cooldowns.get(player) > System.currentTimeMillis();
    }

    private long getCooldownSeconds(Player player) {
        if (cooldowns.containsKey(player)) {
            return (cooldowns.get(player) - System.currentTimeMillis()) / 1000L;
        }
        return 0;
    }
}
