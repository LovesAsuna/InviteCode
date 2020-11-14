package org.sct.invitecode.commands.sub

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.sct.easylib.util.function.command.SubCommand
import org.sct.invitecode.data.InviteCodeData

class Reset : SubCommand {
    override fun execute(sender: CommandSender, args: Array<String>): Boolean {
        if (args.size == 1) {
            return false
        }
        if (sender.isOp || sender.hasPermission("ic.reset")) {
            val playerName = InviteCodeData.getPlayerName(args[1])
            InviteCodeData.Timer[Bukkit.getPlayer(playerName)?.name + "@Start"] = 0L
            sender.sendMessage("§7[§eInviteCode§7]§b玩家" + playerName + "的时间已重置!")
        } else {
            sender.sendMessage("§7[§eInviteCode§7]§c你没有此命令的权限!")
        }
        return true
    }

    override fun getParams(): Map<Int, Array<String>>? {
        return null
    }
}