package org.gomadango0113.player_manager.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.gomadango0113.player_manager.manager.ReportManager;

import java.io.IOException;

public class ReportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("warn_player")) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
            try {
                ReportManager.addReport(player, args[1]);
                send.sendMessage(player.getName() + "を警告しました。");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

}
