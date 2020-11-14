package org.sct.invitecode.util;

import org.sct.invitecode.data.InviteCodeData;
import org.sct.invitecode.enumeration.ConfigType;
import org.sct.invitecode.file.Config;
import org.sct.invitecode.storge.Mysql;
import org.sct.invitecode.storge.Yaml;

/**
 * @author LovesAsuna
 */
public class DBUtil {
    public static void whichStorge() {
        String storgetype = null;
        if (Config.getString(ConfigType.STORGE).equalsIgnoreCase("yaml")) {
            storgetype = "yaml";
        } else if (Config.getString(ConfigType.STORGE).equalsIgnoreCase("mysql")) {
            storgetype = "mysql";
        }
        InviteCodeData.setStorge("yaml".equalsIgnoreCase(storgetype) ? new Yaml() : new Mysql());
    }
}
