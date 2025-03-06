package org.gomadango0113.player_manager.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("normal_ban")) {

        }
        else if (cmd.getName().equalsIgnoreCase("temp_ban")) {

        }
        return false;
    }

}
