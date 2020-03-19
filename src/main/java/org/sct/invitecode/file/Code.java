package org.sct.invitecode.file;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.sct.invitecode.InviteCode;

import java.io.File;
import java.io.IOException;

public class Code {

    private static File file;
    @Getter
    private static YamlConfiguration code;

    public static void loadCode() {
        file = new File(InviteCode.getInstance().getDataFolder() + "\\code.yml");
        if (!file.exists()) {
            InviteCode.getInstance().saveResource("code.yml", false);
        }
        code = YamlConfiguration.loadConfiguration(file);
    }

    public static void saveCode(String player, String ic) {
        loadCode();
        code.set(player, ic);
        try {
            code.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
