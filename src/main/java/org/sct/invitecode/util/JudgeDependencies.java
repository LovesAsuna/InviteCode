package org.sct.invitecode.util;

import org.sct.invitecode.data.InviteCodeData;
import org.sct.invitecode.enumeration.ConfigType;
import org.sct.invitecode.file.Config;

public class JudgeDependencies {
    public static void useDenpendencies() {
        if (Config.getInt(ConfigType.MONEY) != 0) {
            InviteCodeData.INSTANCE.setUseVault(true);
        }
        if (Config.getBoolean(ConfigType.Authme)) {
            InviteCodeData.INSTANCE.setUseAuthme(true);
        }
    }

}
