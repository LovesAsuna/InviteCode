package org.sct.invitecode;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.sct.invitecode.commands.SubCommandHandler;
import org.sct.invitecode.data.InviteCodeData;
import org.sct.invitecode.file.*;
import org.sct.invitecode.listener.Register;
import org.sct.invitecode.util.JudgeDependencies;
import org.sct.invitecode.util.JudgeStorge;
import org.sct.invitecode.util.ListenerManager;
import org.sct.plugincore.PluginCore;
import org.sct.plugincore.PluginCoreAPI;
import org.sct.plugincore.util.function.econoomy.EcoUtil;
import org.sct.plugincore.util.plugin.CheckUpdate;
import org.sct.plugincore.util.plugin.FileUpdate;

public class InviteCode extends JavaPlugin {
    @Getter
    public static InviteCode instance;
    @Getter
    private static PluginCoreAPI pluginCoreAPI;

    @Override
    public void onEnable() {
        instance = this;
        pluginCoreAPI = PluginCore.getPluginCoreAPI();
        InviteCodeData.getPool().submit(() -> {
            FileUpdate.update(instance, "config.yml", getDataFolder().getPath());
            FileUpdate.update(instance, "lang.yml", getDataFolder().getPath());
            CheckUpdate.check(Bukkit.getConsoleSender(), instance, "LovesAsuna", "ZDRlZWY4ZDZlMzIyNDExYjk3NThlMGNiN2ZmYzg3NTRiOGIwZDUzZA==");
        });
        Bukkit.getPluginCommand("InviteCode").setExecutor(new SubCommandHandler(instance, "InviteCode"));

        ListenerManager.register();
        if (Bukkit.getPluginManager().isPluginEnabled("Authme")) {
            Bukkit.getPluginManager().registerEvents(new Register(), this);
        }

        if (!EcoUtil.loadVault()) {
            getLogger().severe("Vault初始化失败,可能未安装Vault");
            getLogger().severe("或未使用相应的经济插件!");
            getLogger().severe("只能使用普通的物品奖励功能!");
        } else {
            getServer().getConsoleSender().sendMessage("§7[§eInviteCode§7]§2插件已挂钩Vault");
        }
        initialize();
        getServer().getConsoleSender().sendMessage("§7[§eInviteCode§7]§2插件已加载");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§7[§eInviteCode§7]§c插件已卸载");
    }

    public void initialize() {
        /*初始化文件*/
        Items.loadItems();
        Code.loadCode();
        Lang.loadLang();
        Offline.loadOffline();
        Times.loadTimes();

        /*初始化config*/
        saveDefaultConfig();
        reloadConfig();

        /*判断数据储存方式*/
        JudgeStorge.whichStorge();

        /*判断依赖是否启用*/
        JudgeDependencies.useDenpendencies();

        /*存入离线玩家*/
        for (String list : Offline.getOfflinePlayer()) {
            InviteCodeData.getOfflinelist().add(list);
        }
    }

}
