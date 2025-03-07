package org.gomadango0113.player_manager;

import org.bukkit.plugin.java.JavaPlugin;
import org.gomadango0113.player_manager.commands.BanCommand;
import org.gomadango0113.player_manager.commands.PlayerInfoCommand;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommand();

        getLogger().info("[PlayerManager] プラグインの準備ができました。");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerCommand() {
        getCommand("normal_ban").setExecutor(new BanCommand());
        getCommand("temp_ban").setExecutor(new BanCommand());
        getCommand("player_info").setExecutor(new PlayerInfoCommand());
    }

    public static Main getInstance() {
        return getPlugin(Main.class);
    }

}
