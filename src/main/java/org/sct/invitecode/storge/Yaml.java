package org.sct.invitecode.storge;

import org.sct.invitecode.file.Code;


public class Yaml extends Storge {

    @Override
    public void storge(String player, String ic) {
        Code.saveCode("InviteCode.Player." + player, ic);
    }

    @Override
    public String readplayer(String ic) {
        String invitecode = null;
        String path = null;
        String playername = null;
        for (String p : Code.getCode().getKeys(true)) {
            invitecode = Code.getCode().getString(p);
            if (invitecode == null) {
                continue;
            } else if (invitecode.equals(ic)) {
                path = p;
                break;
            }
        }
        if (path == null) {
            playername = null;
        } else {
            playername = path.split("\\.")[2];
        }
        return playername;
    }

    @Override
    public String read(String player) {
        String ic = null;
        ic = Code.getCode().getString("InviteCode.Player." + player);
        return ic;
    }
}
