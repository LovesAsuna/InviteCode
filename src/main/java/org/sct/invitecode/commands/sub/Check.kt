package org.sct.invitecode.commands.sub

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.sct.easylib.util.function.command.SubCommand
import org.sct.invitecode.data.InviteCodeData

/**
 * @author icestar
 */
class Check : SubCommand {
    override fun execute(sender: CommandSender, args: Array<String>): Boolean {
        if (args.size == 1 && args[0].equals("check", ignoreCase = true)) {
            //为自己查询
            val player = sender as Player
            sender.sendMessage("§7[§eInviteCode§7]§b你的邀请码为: " + InviteCodeData.storge?.read(player.name))
            return true
        } else if (args.size == 2 && args[0].equals("check", ignoreCase = true)) {
            //为别人查询
            val playername = args[1]
            if (sender.hasPermission("ic.checkothers") || sender.isOp) {
                sender.sendMessage("§7[§eInviteCode§7]§b你输入的玩家ID为$playername")
                sender.sendMessage("§7[§eInviteCode§7]§b" + "玩家" + InviteCodeData.getPlayerName(playername) + "的邀请码为: " + InviteCodeData.storge?.read(InviteCodeData.getPlayerName(playername)))
            } else {
                sender.sendMessage("§7[§eInviteCode§7]§c你没有此命令的权限!")
            }
            return true
        }
        return true
    }

    override fun getParams(): Map<Int, Array<String>>? {
        return null
    }
}