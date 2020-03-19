package org.sct.invitecode.file;

import org.bukkit.configuration.file.YamlConfiguration;
import org.sct.invitecode.InviteCode;
import org.sct.invitecode.enumeration.LangType;
import org.sct.plugincore.util.BasicUtil;

import java.io.File;
import java.util.List;

public class Lang {
    private static File file;
    private static YamlConfiguration lang;

    public static void loadLang() {
        file = new File(InviteCode.getInstance().getDataFolder() + "\\lang.yml");
        if (!file.exists()) {
            InviteCode.getInstance().saveResource("lang.yml", false);
        }
        lang = YamlConfiguration.loadConfiguration(file);
    }

    public static String getString(LangType langType) {
        loadLang();
        return BasicUtil.convert(lang.getString(langType.getPath()));
    }

    public static List<String> getStringList(LangType langType) {
        loadLang();
        return BasicUtil.convert(lang.getStringList(langType.getPath()));
    }
}
