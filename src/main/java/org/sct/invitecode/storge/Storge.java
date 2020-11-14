package org.sct.invitecode.storge;

public abstract class Storge {

    public abstract void storge(String player, String ic);

    public abstract String read(String player);

    /**
     * @param ic 邀请码
     * @return 该邀请码的拥有者
     **/
    public abstract String readPlayer(String ic);

}
