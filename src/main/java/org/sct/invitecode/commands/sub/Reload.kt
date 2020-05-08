package org.sct.invitecode.commands.sub

import org.bukkit.command.CommandSender
import org.sct.easylib.util.function.command.SubCommand
import org.sct.invitecode.InviteCode
import org.sct.invitecode.file.*

class Reload : SubCommand {
    override fun execute(sender: CommandSender, args: Array<String>): Boolean {
        if (sender.hasPermission("ic.reload") || sender.isOp) {
            InviteCode.getInstance().reloadConfig()
            Config.loadConfig()
            Items.loadItems()
            Code.loadCode()
            Lang.loadLang()
            Offline.loadOffline()
            Times.loadTimes()
            sender.sendMessage("§7[§eInviteCode§7]§2插件配置重载成功")
        } else {
            sender.sendMessage("§7[§eInviteCode§7]§c你没有此命令的权限!")
        }
        return true
    }

    override fun getParams(): Map<Int, Array<String>>? {
        return null
    }
}