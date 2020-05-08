package org.sct.invitecode.commands.sub

import org.bukkit.command.CommandSender
import org.sct.easylib.util.function.command.SubCommand
import org.sct.invitecode.InviteCode

class Info : SubCommand {
    override fun execute(sender: CommandSender, args: Array<String>): Boolean {
        sender.sendMessage("§7┌ §ePlugin§7:§b InviteCode")
        sender.sendMessage("§7├ §eAuthor§7:§b 冰星")
        sender.sendMessage("§7├ §eVersion§7:§b " + InviteCode.getInstance().description.version)
        sender.sendMessage("§7└ §eLink§7:§b https://www.mcbbs.net/thread-916058-1-1.html")
        return true
    }

    override fun getParams(): Map<Int, Array<String>>? {
        return null
    }
}