package me.mical.worldalias;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class WorldAlias extends JavaPlugin {

    private static WorldAlias instance;

    public static WorldAlias getInstance() {
        return instance;
    }

    private WorldAliasExpansion expansion;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        expansion = new WorldAliasExpansion();
        expansion.register();
        getLogger().info("插件已加载，应用于 StarrySky 服务器。");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        expansion.unregister();
        getLogger().info("插件已卸载。");
    }

    public static List<String> getAliases() {
        return getInstance().getConfig().getConfigurationSection("alias").getKeys(false).stream().toList();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("worldalias")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    reloadConfig();
                    sender.sendMessage("OK");
                }
            }
        }
        return true;
    }
}
