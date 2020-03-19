package org.sct.invitecode.file;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.sct.invitecode.InviteCode;

import java.io.File;
import java.io.IOException;

public class Times {

    private static File file;
    @Getter
    private static YamlConfiguration times;

    public static void loadTimes() {
        file = new File(InviteCode.getInstance().getDataFolder() + "\\times.yml");
        if (!file.exists()) {
            InviteCode.getInstance().saveResource("times.yml", false);
        }
        times = YamlConfiguration.loadConfiguration(file);
    }

    public static void saveTimes(String player, int ic) {
        times.set(player, ic);
        try {
            times.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
