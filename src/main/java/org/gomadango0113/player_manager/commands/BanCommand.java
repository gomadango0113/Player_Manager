package org.gomadango0113.player_manager.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.gomadango0113.player_manager.manager.BanManager;
import org.gomadango0113.player_manager.util.TimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("normal_ban")) {
            BanManager.normalBan(Bukkit.getPlayer(args[0]), args[1]);
            send.sendMessage("Banをすることができました。");
        }
        else if (cmd.getName().equalsIgnoreCase("temp_ban")) {
            List<String> format_list = Arrays.asList("yyyy-MM-dd hh:mm", "hh:mm");

            try {
                if (TimeUtil.isFormat(args[2], "yyyy-MM-dd hh:mm")) {
                    Date parse = TimeUtil.toParse(args[2], "yyyy-MM-dd hh:mm");
                    BanManager.tempBan(Bukkit.getPlayer(args[0]), args[1], parse);
                }
                else if (TimeUtil.isFormat(args[2], "hh:mm")) {
                    Date parse = TimeUtil.toParse(args[2], "hh:mm");
                    LocalDateTime local = TimeUtil.dateToLocal(parse);
                    LocalDateTime set_local = LocalDateTime.of(
                            LocalDateTime.now().getYear(),
                            LocalDateTime.now().getMonth(),
                            LocalDateTime.now().getDayOfMonth(),
                            local.getHour(),
                            local.getMinute());
                    BanManager.tempBan(Bukkit.getPlayer(args[0]), args[1], set_local);
                }


                send.sendMessage("Banをすることができました。");
            }
            catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

}
