package org.sct.invitecode.data;

import lombok.Getter;
import lombok.Setter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.sct.invitecode.gui.gui;
import org.sct.invitecode.storge.Storge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InviteCodeData {

    /*计时器*/
    @Getter
    private static Map<String, Long> Timer;
    /*注册*/
    @Getter
    private static Map<String, Boolean> register;
    /*储存离线玩家列表*/
    @Getter
    private static List<String> offlinelist;
    /*依赖启动状态*/
    @Setter
    @Getter
    private static boolean useVault, useAuthme;
    /*经济依赖*/
    @Setter
    @Getter
    private static Economy econ;
    /*数据库*/
    @Setter
    @Getter
    private static Storge storge;
    /*gui*/
    @Getter
    private static org.sct.invitecode.gui.gui gui;
    /*插件专用线程池*/
    @Getter
    private static ExecutorService pool;

    static {
        Timer = new HashMap<>();
        register = new HashMap<>();
        offlinelist = new ArrayList<>();
        gui = new gui();
        pool = Executors.newFixedThreadPool(5);
    }

    @SuppressWarnings("deprecation")
    public static String getplayername(String playername) {
        Player player = Bukkit.getPlayer(playername);
        return player.getName();
    }
}
