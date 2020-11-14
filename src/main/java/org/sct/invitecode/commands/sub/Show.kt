package org.sct.invitecode.commands.sub

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.sct.easylib.util.function.command.SubCommand
import org.sct.invitecode.data.InviteCodeData

class Show : SubCommand {
    override fun execute(sender: CommandSender, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("§7[§eInviteCode§7]§c你必须是一名玩家")
            return false
        }
        val player = sender
        player.closeInventory()
        val inv = InviteCodeData.gui.setGUI(player)
        player.openInventory(inv!!)
        return true
    }

    override fun getParams(): Map<Int, Array<String>>? {
        return null
    }
}