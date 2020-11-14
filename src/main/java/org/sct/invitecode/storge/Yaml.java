package org.sct.invitecode.storge;

import org.sct.invitecode.file.Code;


public class Yaml extends Storge {
    private final String playerPath = "InviteCode.Player.";

    @Override
    public void storge(String player, String ic) {
        Code.saveCode(playerPath + player, ic);
    }

    @Override
    public String readPlayer(String ic) {
        String invitecode;
        String playername = null;
        for (String p : Code.getCode().getConfigurationSection("InviteCode.Player").getKeys(false)) {
            invitecode = Code.getCode().getString(playerPath + p);
            if (ic.equals(invitecode)) {
                playername = p;
                break;
            }
        }
        return playername;
    }

    @Override
    public String read(String player) {
        String ic;
        ic = Code.getCode().getString(playerPath + player);
        return ic;
    }
}
