package org.sct.invitecode.commands.sub

import org.bukkit.command.CommandSender
import org.sct.invitecode.data.InviteCodeData
import org.sct.plugincore.util.function.command.SubCommand
import java.util.HashMap

class Reset : SubCommand {
    override fun execute(sender: CommandSender, args: Array<String>): Boolean {
        if (args.size == 1) {
            return false
        }
        if (sender.isOp || sender.hasPermission("ic.reset")) {
            val playername = InviteCodeData.getplayername(args[1])
            InviteCodeData.Timer?.toMutableMap()?.set(playername + "start", 0L)
            sender.sendMessage("§7[§eInviteCode§7]§b玩家" + playername + "的时间已重置!")
        } else {
            sender.sendMessage("§7[§eInviteCode§7]§c你没有此命令的权限!")
        }
        return true
    }

    override fun getParams(): Map<Int, Array<String>>? {
        return null
    }
}