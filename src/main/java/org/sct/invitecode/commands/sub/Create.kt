package org.sct.invitecode.commands.sub

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.sct.invitecode.data.InviteCodeData
import org.sct.invitecode.util.CreateInviteCode
import org.sct.plugincore.util.function.command.SubCommand

class Create : SubCommand {
    override fun execute(sender: CommandSender, args: Array<String>): Boolean {
        if (args.size == 1 && args[0].equals("create", ignoreCase = true)) {
            //为自己创建
            if (sender is Player) {
                val player = sender
                val playername = player.name
                val ic = CreateInviteCode.createInviteCode()
                if (InviteCodeData.storge?.read(playername) == null) {
                    player.sendMessage("§7[§eInviteCode§7]§b你的id为$playername")
                    player.sendMessage("§7[§eInviteCode§7]§b你的邀请码为: $ic")
                    InviteCodeData.storge?.storge(playername, ic)
                } else {
                    player.sendMessage("§7[§eInviteCode§7]§c你已生成过邀请码")
                    player.sendMessage("§7[§eInviteCode§7]§b你的邀请码为: " + InviteCodeData.storge?.read(playername))
                }
            } else {
                sender.sendMessage("§7[§eInviteCode§7]§c你必须是一名玩家")
            }
            return true
        } else if (args.size == 2 && args[0].equals("create", ignoreCase = true)) {
            //为别人创建
            val playername = args[1]
            val ic = CreateInviteCode.createInviteCode()
            if (sender.hasPermission("ic.createothers") || sender.isOp) {
                if (InviteCodeData.storge?.read(playername) == null) {
                    sender.sendMessage("§7[§eInviteCode§7]§b你输入的玩家ID为$playername")
                    sender.sendMessage("§7[§eInviteCode§7]§b为玩家" + playername + "生成的邀请码为: " + ic)
                    InviteCodeData.storge?.storge(playername, ic)
                } else {
                    sender.sendMessage("§7[§eInviteCode§7]§c该玩家已有邀请码")
                    sender.sendMessage("§7[§eInviteCode§7]§b其邀请码为: " + InviteCodeData.storge?.read(playername))
                }
            } else {
                sender.sendMessage("§7[§eInviteCode§7]§c你没有此命令的权限!")
            }
        }
        return true
    }

    override fun getParams(): Map<Int, Array<String>>? {
        return null
    }
}