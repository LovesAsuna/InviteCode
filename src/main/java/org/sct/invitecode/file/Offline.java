package org.sct.invitecode.file;

import org.bukkit.configuration.file.YamlConfiguration;
import org.sct.invitecode.InviteCode;
import org.sct.invitecode.data.InviteCodeData;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Offline {
    private static File file;
    private static YamlConfiguration offline;

    public static void loadOffline() {
        file = new File(InviteCode.getInstance().getDataFolder() + "\\offline.yml");
        if (!file.exists()) {
            InviteCode.getInstance().saveResource("offline.yml", false);
        }
        offline = YamlConfiguration.loadConfiguration(file);
    }

    public static void saveOfflinePlayer() {
        loadOffline();
        offline.set("offline", InviteCodeData.INSTANCE.getOfflinelist());
        try {
            offline.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getOfflinePlayer() {
        loadOffline();
        return offline.getStringList("offline");
    }
}
