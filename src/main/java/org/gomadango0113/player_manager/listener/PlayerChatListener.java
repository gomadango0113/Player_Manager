package org.gomadango0113.player_manager.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.gomadango0113.player_manager.manager.MuteManager;

import java.io.IOException;
import java.time.temporal.TemporalField;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) throws IOException {
        Player player = event.getPlayer();
        MuteManager.MutePlayer mute_player = MuteManager.getMutePlayer(player);

        if (mute_player != null) {
            player.sendMessage("あなたはミュートされています。");
            event.setCancelled(true);
        }
    }

}
