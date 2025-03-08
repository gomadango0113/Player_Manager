package org.gomadango0113.player_manager.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.bukkit.OfflinePlayer;
import org.gomadango0113.player_manager.Main;
import org.gomadango0113.player_manager.util.TimeUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MuteManager {

    private static final Gson gson;

    static  {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    public static void addMute(OfflinePlayer player, String reason) throws IOException {
        JsonObject raw_json = getRawJson();
        if (!raw_json.has(player.getName())) {
            JsonObject player_obj = new JsonObject();
            player_obj.addProperty("UUID", player.getUniqueId().toString());
            player_obj.addProperty("reason", reason);
            player_obj.addProperty("TYPE", "NORMAL");
            player_obj.addProperty("unmute-time", Long.MAX_VALUE);
            
            raw_json.add(player.getName(), player_obj);
            
            writeFile(gson.toJson(raw_json));
        }
    }

    public static void addTempMute(OfflinePlayer player, String reason, LocalDateTime localDateTime) throws IOException {
        JsonObject raw_json = getRawJson();
        if (!raw_json.has(player.getName())) {
            JsonObject player_obj = new JsonObject();
            player_obj.addProperty("UUID", player.getUniqueId().toString());
            player_obj.addProperty("reason", reason);
            player_obj.addProperty("TYPE", "TEMP");
            player_obj.addProperty("unmute-time", TimeUtil.localToDate(localDateTime).getTime());

            raw_json.add(player.getName(), player_obj);

            writeFile(gson.toJson(raw_json));
        }
    }

    public static void removeMute(OfflinePlayer player) throws IOException {
        JsonObject raw_json = getRawJson();
        if (raw_json.has(player.getName())) {
            raw_json.remove(player.getName());

            writeFile(gson.toJson(raw_json));
        }
    }
    
    public static MutePlayer getMutePlayer(OfflinePlayer player) throws IOException {
        JsonObject raw_json = getRawJson();
        if (raw_json.has(player.getName())) {
            JsonObject player_obj = raw_json.getAsJsonObject(player.getName());

            UUID uuid = UUID.fromString(player_obj.get("UUID").getAsString());
            String reason = player_obj.get("reason").getAsString();
            LocalDateTime unmute_time = TimeUtil.dateToLocal(new Date(player_obj.get("unmute-time").getAsLong()));

            return new MutePlayer(player.getName(), uuid, reason, unmute_time);
        }
        return null;
    }

    public static JsonObject getRawJson() throws IOException {
        createJson();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(getFile().toPath()), StandardCharsets.UTF_8))) {
            return gson.fromJson(reader, JsonObject.class);
        }
    }

    public static void writeFile(String date) {
        try (BufferedWriter write = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(getFile().toPath()), StandardCharsets.UTF_8))) {
            write.write(date);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createJson() throws IOException {
        if (!getFile().exists()) {
            Main.getInstance().getDataFolder().mkdir();
            getFile().createNewFile();
            writeFile("{}");
        }
    }

    private static File getFile() {
        return new File(Main.getInstance().getDataFolder(), "mute_data.json");
    }
    
    public static class MutePlayer {
        private final String name;
        private final UUID uuid;
        private final String reason;
        private final MuteType type;
        private final LocalDateTime unban_time;
        
        public MutePlayer(String name, UUID uuid, String reason, LocalDateTime unban_time) {
            this.name = name;
            this.uuid = uuid;
            this.reason = reason;
            this.unban_time = unban_time;

            if (unban_time != null) {
                this.type = MuteType.TEMP;
            }
            else {
                this.type = MuteType.NORMAL;
            }
        }

        public String getName() {
            return name;
        }

        public UUID getUuid() {
            return uuid;
        }

        public String getReason() {
            return reason;
        }

        public MuteType getType() {
            return type;
        }

        public LocalDateTime getUnBanTime() {
            return unban_time;
        }

        public Date getUnBanDate() {
            return TimeUtil.localToDate(unban_time);
        }
    }
    
    public enum MuteType {
        NORMAL,
        TEMP
    }

}
