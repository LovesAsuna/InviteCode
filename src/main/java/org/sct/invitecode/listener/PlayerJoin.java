package org.sct.invitecode.listener;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.sct.invitecode.InviteCode;
import org.sct.invitecode.data.InviteCodeData;
import org.sct.invitecode.file.Items;
import org.sct.invitecode.file.Offline;


public class PlayerJoin implements Listener {
    String playername;

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onJoin(PlayerJoinEvent e) {
        playername = e.getPlayer().getName();
        for (String pn : InviteCodeData.INSTANCE.getOfflinelist()) {
            if (playername.equalsIgnoreCase(pn)) {
                e.getPlayer().sendMessage("§7[§eInviteCode§7]§b你不在的时候有新玩家进服");
                e.getPlayer().sendMessage("§7[§eInviteCode§7]§b后使用了你的邀请码!");
                InviteCodeData.INSTANCE.getOfflinelist().remove(playername);
                Offline.saveOfflinePlayer();
            } else {
                continue;
            }

            Player rplayer = Bukkit.getPlayerExact(pn);
            for (int reward : InviteCode.getInstance().getConfig().getIntegerList("InviteCode.Reward")) {
                ItemStack item = Items.getItemStack(reward);
                try {
                    rplayer.getInventory().addItem(item);
                } catch (Exception ex) {
                    e.getPlayer().sendMessage("§7[§eInviteCode§7]§4§l配置文件内设置了不存在的物品,你未能正常获得奖励,请联系管理员!");
                    InviteCode.getInstance().getServer().getConsoleSender().sendMessage("§7§l[§eInviteCode§7]§4§l配置文件内设置了不存在的物品!");
                    ex.printStackTrace();
                }
            }
            //给钱
            if (InviteCodeData.INSTANCE.getEcon() != null && InviteCode.getInstance().getConfig().getInt("InviteCode.Money") != 0) {
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(e.getPlayer().getName());
                double money = InviteCode.getInstance().getConfig().getInt("InviteCode.Money");
                e.getPlayer().sendMessage("§7[§eInviteCode§7]§b获得货币" + money);
                InviteCodeData.INSTANCE.getEcon().depositPlayer(offlinePlayer, money);
                e.getPlayer().sendMessage("§7[§eInviteCode§7]§b当前余额为" + InviteCodeData.INSTANCE.getEcon().getBalance(e.getPlayer()));
            }
            //给点券
            if (Bukkit.getPluginManager().isPluginEnabled("PlayerPoints") && InviteCode.getInstance().getConfig().getInt("InviteCode.Points") != 0) {
                Bukkit.dispatchCommand(InviteCode.getInstance().getServer().getConsoleSender(), "points give " + e.getPlayer().getName() + " " + InviteCode.getInstance().getConfig().getInt("InviteCode.Points"));
            }
            rplayer.sendMessage("§7[§eInviteCode§7]§b已向你的背包发放奖励!");
        }
    }
}