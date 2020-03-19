package org.sct.invitecode.commands.sub;

import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.sct.invitecode.InviteCode;
import org.sct.invitecode.data.InviteCodeData;
import org.sct.invitecode.enumeration.ConfigType;
import org.sct.invitecode.file.Config;
import org.sct.invitecode.file.Items;
import org.sct.invitecode.file.Offline;
import org.sct.invitecode.file.Times;
import org.sct.plugincore.util.function.SubCommand;


public class Reward implements SubCommand {

    private long starttime, endtime;
    private int delay = Config.getInt(ConfigType.DELAY);

    @Override
    @SuppressWarnings("deprecation")
    public boolean execute(CommandSender sender, String[] args) {

        if (args.length == 1) {
            return false;
        }

        String ic = args[1];
        Player player = (Player) sender;
        boolean noTimer = false;
        if (InviteCodeData.isUseAuthme() && AuthMeApi.getInstance().isRegistered(player.getName()) && InviteCodeData.getRegister().containsKey(player.getName())) {
            sender.sendMessage("§7[§eInviteCode§7]§c此账号已经注册,禁止再次使用邀请码");
            return true;
        } else if (InviteCodeData.getStorge().readplayer(ic) == null) {
            sender.sendMessage("§7[§eInviteCode§7]§c你输入的邀请码不存在");
            return true;
        } else if (!player.isOp() && player.getName().equalsIgnoreCase(InviteCodeData.getStorge().readplayer(ic))) {
            sender.sendMessage("§7[§eInviteCode§7]不能使用自己的邀请码");
            return true;
        }
        Player rplayer = Bukkit.getPlayerExact(InviteCodeData.getStorge().readplayer(ic));

        /*不存在时间戳就创建*/
        if (!InviteCodeData.getTimer().containsKey(player.getName() + "start")) {
            /*定义没有计时器的第一次使用状态*/
            noTimer = true;
            InviteCodeData.getTimer().putIfAbsent(player.getName() + "start", System.currentTimeMillis() / 1000);
        }

        starttime = InviteCodeData.getTimer().get(player.getName() + "start");
        endtime = System.currentTimeMillis() / 1000;

        /*有计时器并且时间未到*/
        if (!noTimer && (endtime - starttime) < (delay * 60)) {
            sender.sendMessage("§7[§eInviteCode§7]§c" + InviteCode.getInstance().getConfig().getInt("InviteCode.Delay") + "分钟内只能邀请一次");
            sender.sendMessage("§7[§eInviteCode§7]§c你还剩下" + "§a" + (delay * 60 - endtime + starttime) + "§c秒!");
            return true;
        }

        if (!checkonline(ic)) {
            sender.sendMessage("§7[§eInviteCode§7]§c邀请玩家未在线,将在其在线后发放奖励!");
            return true;
        }

        /*获取次数*/
        int times = Times.getTimes().getInt(rplayer.getName());

        if (times == 0) {
            Times.saveTimes(rplayer.getName(), 1);
        } else {
            Times.saveTimes(rplayer.getName(), times + 1);
        }

        times += 1;

        /*给物品*/
        for (int items : Config.getRewardList(times)) {
            ItemStack itemStack = Items.getItemStack(items);
            try {
                rplayer.getInventory().addItem(itemStack);
            } catch (NullPointerException e) {
                sender.sendMessage("§7[§eInviteCode§7]§4§l配置文件内设置了不存在的物品,请联系管理员!");
                InviteCode.getInstance().getServer().getConsoleSender().sendMessage("§7§l[§eInviteCode§7]§4§l配置文件内设置了不存在的物品!");
                e.printStackTrace();
            }
        }

        /*给钱*/
        if (InviteCodeData.getEcon() != null && Config.getRewardMoney(times) != 0) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
            double money = Config.getRewardMoney(times);
            sender.sendMessage("§7[§eInviteCode§7]§b获得货币" + money);
            InviteCodeData.getEcon().depositPlayer(offlinePlayer, money);
            sender.sendMessage("§7[§eInviteCode§7]§b当前余额为" + InviteCodeData.getEcon().getBalance(player));
        }

        /*给点券*/
        if (Bukkit.getPluginManager().isPluginEnabled("PlayerPoints") && Config.getRewardPoints(times) != 0) {
            Bukkit.dispatchCommand(InviteCode.getInstance().getServer().getConsoleSender(), "points give " + player.getName() + " " + Config.getRewardPoints(times));
        }
        InviteCodeData.getRegister().remove(player.getName());
        player.sendMessage("§7[§eInviteCode§7]§b你已成功邀请一位玩家");
        player.sendMessage("§7[§eInviteCode§7]§b已向你的背包发放奖励!");
        /*刷新初始时间*/
        InviteCodeData.getTimer().put(player.getName() + "start", System.currentTimeMillis() / 1000);
        return true;
    }

    @SuppressWarnings("deprecation")
    private boolean checkonline(String ic) {
        boolean online = true;
        String playername = InviteCodeData.getStorge().readplayer(ic);
        Player player = Bukkit.getPlayerExact(playername);
        /*不在线状况*/
        if (player == null) {
            InviteCodeData.getOfflinelist().add(playername);
            Offline.SaveOfflinePlayer();
            online = false;
        }
        return online;
    }
}
