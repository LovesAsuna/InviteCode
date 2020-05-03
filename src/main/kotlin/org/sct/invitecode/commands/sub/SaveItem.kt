package org.sct.invitecode.commands.sub

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.sct.invitecode.file.Items
import org.sct.plugincore.util.function.command.SubCommand

class SaveItem : SubCommand {
    private var itemcount = 0
    override fun execute(sender: CommandSender, args: Array<String>): Boolean {
        if (sender is Player) {
            val player = sender
            if (player.hasPermission("ic.saveitem") || player.isOp) {
                val item = player.itemInHand
                Items.SaveItem("items." + ++itemcount, item)
                Items.loadItems()
                sender.sendMessage("§7[§eInviteCode§7]§a物品保存成功")
            } else {
                sender.sendMessage("§7[§eInviteCode§7]§c你没有此命令的权限!")
            }
        } else {
            sender.sendMessage("§7[§eInviteCode§7]§c你必须是一名玩家")
        }
        return true
    }

    override fun getParams(): Map<Int, Array<String>>? {
        return null
    }
}