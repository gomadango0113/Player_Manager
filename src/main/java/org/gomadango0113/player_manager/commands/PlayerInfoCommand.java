package org.gomadango0113.player_manager.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;

public class PlayerInfoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("player_info")) {
            if (args.length == 0) {
                Player player = (Player) send;
                InetSocketAddress address = player.getAddress();

                send.sendMessage("==あなたの情報==" + "\n" +
                        "UUID:" + player.getUniqueId().toString() + "\n" +
                        "IP:" + address.getAddress().getHostAddress() + "\n");
            }
            else {
                Player player = Bukkit.getPlayer(args[0]);
                InetSocketAddress address = player.getAddress();

                send.sendMessage("==" + player.getName() + "の情報==" + "\n" +
                        "UUID:" + player.getUniqueId().toString() + "\n" +
                        "IP:" + address.getAddress().getHostAddress() + "\n");
            }
        }
        return false;
    }

}
