package org.gomadango0113.player_manager.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.OfflinePlayer;
import org.gomadango0113.player_manager.Main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

public class ReportManager {

    private static final Gson gson;

    static  {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public static void addReport(OfflinePlayer player, String reason) throws IOException {
        JsonObject json = getRawJson();

        JsonObject report_obj = new JsonObject();
        report_obj.addProperty("name", player.getName());
        report_obj.addProperty("UUID", player.getUniqueId().toString());
        report_obj.addProperty("reason", reason);
        report_obj.addProperty("reported", false);
        json.add(player.getName(), report_obj);

        writeFile(gson.toJson(json));
    }

    public static ReportPlayer getReportPlayer(OfflinePlayer player) throws IOException {
        JsonObject json = getRawJson();

        if (json.has(player.getName())) {
            JsonObject player_obj = json.getAsJsonObject(player.getName());
            String reason = player_obj.get("reason").getAsString();
            boolean reported = player_obj.get("reported").getAsBoolean();

            return new ReportPlayer(player.getName(), player.getUniqueId(), reason, reported);
        }

        return null;
    }

    public static void setReported(OfflinePlayer player, boolean flag) throws IOException {
        JsonObject json = getRawJson();

        if (json.has(player.getName())) {
            JsonObject player_obj = json.getAsJsonObject(player.getName());
            player_obj.addProperty("reported", flag);

            writeFile(gson.toJson(json));
        }
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
        return new File(Main.getInstance().getDataFolder(), "report_data.json");
    }

    public static class ReportPlayer {
        private final String name;
        private final UUID uuid;
        private final String reason;
        private final boolean reported;

        public ReportPlayer(String name, UUID uuid, String reason, boolean reported) {
            this.name = name;
            this.uuid = uuid;
            this.reason = reason;
            this.reported = reported;
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

        public boolean isReported() {
            return reported;
        }
    }

}
