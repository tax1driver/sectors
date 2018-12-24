package com.github.tax1driver.sectors;

import com.github.tax1driver.sectors.helpers.InventorySerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {
    private static PluginMain instance;

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getLogger().info("Enabled MCSectors plugin..");
        Bukkit.getLogger().info("Checking the config for sector redivision needs..");



    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("hujtesthuj")) {
            Player p = (Player)sender;
            System.out.println("Serialized inventory of " + p.getName() + ":"+ InventorySerializer.serializeInventory(p.getInventory()));
            System.out.println("Serialized armor contents of " + p.getName() + ":"+ InventorySerializer.serializeItemArray(p.getInventory().getArmorContents()));

            p.getInventory().setArmorContents(InventorySerializer.deserializeItemArray("180000040000020009km800odlkmspb3e9gmct1qchkm2rbfdpi5uq35dhmmat01002k6rrldpq020g00p262rb1ctig0000"));

        }

        return false;
    }

    public static PluginMain getInstance() {
        return instance;
    }
}
