package org.gomadango0113.player_manager;

import org.bukkit.plugin.java.JavaPlugin;

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

    }

    public static Main getInstance() {
        return getPlugin(Main.class);
    }

}
