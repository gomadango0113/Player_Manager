package org.gomadango0113.player_manager.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Collection;

public class PlayerCommandListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        for (Player online_player : Bukkit.getOnlinePlayers()) {
            if (online_player.isOp()) { //OPの人に
                if (!player.equals(online_player)) { //自分自身以外に
                    online_player.sendMessage(ChatColor.GRAY + "[CMD]" + player.getName() + ":" + message);
                }
            }
        }
    }

}
