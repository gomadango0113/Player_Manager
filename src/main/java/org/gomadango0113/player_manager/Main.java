package org.gomadango0113.player_manager;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.gomadango0113.player_manager.commands.BanCommand;
import org.gomadango0113.player_manager.commands.MuteCommand;
import org.gomadango0113.player_manager.commands.PlayerInfoCommand;
import org.gomadango0113.player_manager.listener.PlayerChatListener;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommand();
        registerListener();

        getLogger().info("[PlayerManager] プラグインの準備ができました。");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerCommand() {
        getCommand("normal_ban").setExecutor(new BanCommand());
        getCommand("temp_ban").setExecutor(new BanCommand());
        getCommand("normal_mute").setExecutor(new MuteCommand());
        getCommand("temp_mute").setExecutor(new MuteCommand());
        getCommand("player_info").setExecutor(new PlayerInfoCommand());
    }

    public void registerListener() {
        PluginManager plm = getServer().getPluginManager();

        plm.registerEvents(new PlayerChatListener(), this);
    }

    public static Main getInstance() {
        return getPlugin(Main.class);
    }

}
