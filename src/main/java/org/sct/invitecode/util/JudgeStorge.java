package org.sct.invitecode.util;

import org.sct.invitecode.data.InviteCodeData;
import org.sct.invitecode.enumeration.ConfigType;
import org.sct.invitecode.file.Config;
import org.sct.invitecode.storge.Mysql;
import org.sct.invitecode.storge.Yaml;

public class JudgeStorge {
    public static void whichStorge() {
        String storgetype = null;
        if (Config.getString(ConfigType.STORGE).equalsIgnoreCase("yaml")) {
            storgetype = "yaml";
        } else if (Config.getString(ConfigType.STORGE).equalsIgnoreCase("mysql")) {
            storgetype = "mysql";
        }
        InviteCodeData.INSTANCE.setStorge(storgetype.equalsIgnoreCase("yaml") ? new Yaml() : new Mysql());
    }
}
