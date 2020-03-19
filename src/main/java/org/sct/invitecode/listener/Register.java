package org.sct.invitecode.listener;

import fr.xephi.authme.events.RegisterEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.sct.invitecode.InviteCode;
import org.sct.invitecode.data.InviteCodeData;


public class Register implements Listener {


    @EventHandler
    public void onRegister(RegisterEvent e) {
        InviteCodeData.getRegister().put(e.getPlayer().getName(), true);
        Bukkit.getScheduler().runTaskLater(InviteCode.getInstance(), () -> {
            e.getPlayer().sendMessage("§7[§eInviteCode§7]§b在此次登陆期间,你可以输入邀请码!");
            e.getPlayer().sendMessage("§7[§eInviteCode§7]§c退出后将无法再次输入!");
        }, 20L);
    }
}

