package org.sct.invitecode.commands.sub

import fr.xephi.authme.api.v3.AuthMeApi
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.sct.easylib.util.function.command.SubCommand
import org.sct.invitecode.InviteCode
import org.sct.invitecode.data.InviteCodeData
import org.sct.invitecode.enumeration.ConfigType
import org.sct.invitecode.file.Config
import org.sct.invitecode.file.Items
import org.sct.invitecode.file.Offline
import org.sct.invitecode.file.Times

class Reward : SubCommand {
    private var startTime: Long = 0
    private var endTime: Long = 0
    private val delay = Config.getInt(ConfigType.DELAY)

    @Suppress("deprecation")
    override fun execute(sender: CommandSender, args: Array<String>): Boolean {
        if (args.size == 1) {
            return false
        }
        val ic = args[1]
        val player = sender as Player
        var noTimer = false
        if (InviteCodeData.useAuthme && AuthMeApi.getInstance().isRegistered(player.name) && InviteCodeData.register.contains(player.uniqueId)) {
            sender.sendMessage("§7[§eInviteCode§7]§c此账号已经注册,禁止再次使用邀请码")
            return true
        } else if (InviteCodeData.storge?.readPlayer(ic) == null) {
            sender.sendMessage("§7[§eInviteCode§7]§c你输入的邀请码不存在")
            return true
        } else if (!player.isOp && player.name.equals(InviteCodeData.storge?.readPlayer(ic), ignoreCase = true)) {
            sender.sendMessage("§7[§eInviteCode§7]不能使用自己的邀请码")
            return true
        }
        // 邀请码拥有者
        val rewardPlayer = Bukkit.getPlayerExact(InviteCodeData.storge?.readPlayer(ic)!!)

        // 不存在时间戳就创建
        if (!InviteCodeData.Timer.containsKey(player.name + "@Start")) {
            // 定义没有计时器的第一次使用状态
            noTimer = true
            InviteCodeData.Timer.putIfAbsent(player.name + "@Start", System.currentTimeMillis() / 1000)
        }
        startTime = InviteCodeData.Timer[player.name + "@Start"]!!
        endTime = System.currentTimeMillis() / 1000

        // 有计时器并且时间未到
        if (!noTimer && endTime - startTime < delay * 60) {
            sender.sendMessage("§7[§eInviteCode§7]§c" + InviteCode.getInstance().config.getInt("InviteCode.Delay") + "分钟内只能邀请一次")
            sender.sendMessage("§7[§eInviteCode§7]§c你还剩下" + "§a" + (delay * 60 - endTime + startTime) + "§c秒!")
            return true
        }
        if (!checkOnline(ic)) {
            sender.sendMessage("§7[§eInviteCode§7]§c邀请玩家未在线,将在其在线后发放奖励!")
            return true
        }

        // 获取次数
        var times = Times.getTimes().getInt(rewardPlayer!!.name)
        if (times == 0) {
            Times.saveTimes(rewardPlayer.name, 1)
        } else {
            Times.saveTimes(rewardPlayer.name, times + 1)
        }
        times += 1

        // 给物品
        for (items in Config.getRewardList(times)) {
            val itemStack = Items.getItemStack(items)
            try {
                rewardPlayer.inventory.addItem(itemStack)
            } catch (e: NullPointerException) {
                sender.sendMessage("§7[§eInviteCode§7]§4§l配置文件内设置了不存在的物品,请联系管理员!")
                InviteCode.getInstance().server.consoleSender.sendMessage("§7§l[§eInviteCode§7]§4§l配置文件内设置了不存在的物品!")
                e.printStackTrace()
            }
        }

        // 给钱
        if (InviteCodeData.econ != null && Config.getRewardMoney(times) != 0) {
            val offlinePlayer = Bukkit.getOfflinePlayer(player.name)
            val money = Config.getRewardMoney(times).toDouble()
            sender.sendMessage("§7[§eInviteCode§7]§b获得货币$money")
            InviteCodeData.econ!!.depositPlayer(offlinePlayer, money)
            sender.sendMessage("§7[§eInviteCode§7]§b当前余额为" + InviteCodeData.econ!!.getBalance(player))
        }

        // 给点券
        if (Bukkit.getPluginManager().isPluginEnabled("PlayerPoints") && Config.getRewardPoints(times) != 0) {
            Bukkit.dispatchCommand(InviteCode.getInstance().server.consoleSender, "points give " + player.name + " " + Config.getRewardPoints(times))
        }
        InviteCodeData.register.remove(player.uniqueId)
        rewardPlayer.sendMessage("§7[§eInviteCode§7]§b你已成功邀请一位玩家")
        rewardPlayer.sendMessage("§7[§eInviteCode§7]§b已向你的背包发放奖励!")
        // 刷新初始时间
        InviteCodeData.Timer[player.name + "start"] = System.currentTimeMillis() / 1000
        return true
    }

    override fun getParams(): Map<Int, Array<String>>? {
        return null
    }

    private fun checkOnline(ic: String): Boolean {
        var online = true
        val playerName = InviteCodeData.storge?.readPlayer(ic)
        val player = Bukkit.getPlayerExact(playerName!!)
        // 不在线状况
        if (player == null) {
            InviteCodeData.offlinelist.add(playerName)
            Offline.saveOfflinePlayer()
            online = false
        }
        return online
    }
}