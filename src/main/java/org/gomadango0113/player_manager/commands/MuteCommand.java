package org.gomadango0113.player_manager.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.gomadango0113.player_manager.manager.BanManager;
import org.gomadango0113.player_manager.manager.MuteManager;
import org.gomadango0113.player_manager.util.TimeUtil;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;

public class MuteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender send, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("normal_mute")) {
            try {
                MuteManager.addMute(Bukkit.getOfflinePlayer(args[0]), args[1]);
                send.sendMessage("ミュートしました。");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if (cmd.getName().equalsIgnoreCase("temp_mute")) {
            try {
                if (TimeUtil.isFormat(args[2], "yyyy-MM-dd hh:mm")) {
                    Date parse = TimeUtil.toParse(args[2], "yyyy-MM-dd hh:mm");
                    MuteManager.addTempMute(Bukkit.getPlayer(args[0]), args[1], TimeUtil.dateToLocal(parse));
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
                    MuteManager.addTempMute(Bukkit.getPlayer(args[0]), args[1], set_local);
                }
            }
            catch (ParseException | IOException e) {
                throw new RuntimeException(e);
            }
            send.sendMessage("ミュートしました。");
        }
        return false;
    }

}
