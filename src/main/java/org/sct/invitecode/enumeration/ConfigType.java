package org.sct.invitecode.enumeration;

import lombok.Getter;

public enum ConfigType {
    REWARD("InviteCode.Reward"),
    DELAY("InviteCode.Delay"),
    MONEY("InviteCode.Money"),
    POINTS("InviteCode.Points"),
    Authme("InviteCode.Authme"),
    STORGE("InviteCode.storge"),
    Host("InviteCode.Host"),
    PORT("InviteCode.Port"),
    USERNAME("InviteCode.Username"),
    PASSWORD("InviteCode.Password"),
    DATABASE("InviteCode.DataBase"),
    TABLENAME("InviteCode.Tablename"),
    TIMES("InviteCode.Times");


    @Getter
    String path;

    ConfigType(String path) {
        this.path = path;
    }

    public String TimesPath(int times) {
        return path + "." + times;
    }

}
