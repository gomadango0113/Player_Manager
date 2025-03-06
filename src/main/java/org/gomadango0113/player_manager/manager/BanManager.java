package org.gomadango0113.player_manager.manager;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class BanManager {

    private static final BanList name_list;

    static {
        Server server = Bukkit.getServer();

        name_list = server.getBanList(BanList.Type.NAME);
    }

    public static void normalBan(OfflinePlayer player, String reason) {
        name_list.addBan(player.getName(), reason, null, null);
    }

    public static void tempBan(OfflinePlayer player, String reason, Date date) {
        name_list.addBan(player.getName(), reason, date, null);
    }

    public static void tempBan(OfflinePlayer player, String reason, LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        name_list.addBan(player.getName(), reason, date, null);
    }
}
