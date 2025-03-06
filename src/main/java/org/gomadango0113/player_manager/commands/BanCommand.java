package org.gomadango0113.player_manager.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.gomadango0113.player_manager.manager.BanManager;

import java.time.LocalDateTime;

public class BanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("normal_ban")) {
            BanManager.normalBan(Bukkit.getPlayer(args[0]), args[1]);
            send.sendMessage("Banをすることができました。");
        }
        else if (cmd.getName().equalsIgnoreCase("temp_ban")) {
            BanManager.tempBan(Bukkit.getPlayer(args[0]), args[1],
                    LocalDateTime.of(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6])));
            send.sendMessage("Banをすることができました。");
        }
        return false;
    }

}
