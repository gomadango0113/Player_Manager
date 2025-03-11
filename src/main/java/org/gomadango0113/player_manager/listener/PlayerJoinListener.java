package org.gomadango0113.player_manager.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.gomadango0113.player_manager.manager.ReportManager;

import java.io.IOException;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();
        ReportManager.ReportPlayer report_player = ReportManager.getReportPlayer(player);

        if (report_player != null) {
            if (!report_player.isReported()) {
                ReportManager.setReported(player, true);
                player.sendMessage("==警告==" + "\n" +
                        "理由：" + report_player.getReason());
            }
        }
    }
}
