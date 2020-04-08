package org.sct.invitecode.util;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.sct.invitecode.InviteCode;
import org.sct.invitecode.listener.InventoryClick;
import org.sct.invitecode.listener.PlayerJoin;

public class ListenerManager {

    private static void register(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, InviteCode.getInstance());
    }

    /**
     * 注册监听器
     */
    public static void register() {
        register(new InventoryClick());
        register(new PlayerJoin());
    }

}
