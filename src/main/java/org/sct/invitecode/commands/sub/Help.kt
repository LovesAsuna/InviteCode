package org.sct.invitecode.commands.sub

import org.bukkit.command.CommandSender
import org.sct.invitecode.enumeration.LangType
import org.sct.invitecode.file.Lang
import org.sct.plugincore.util.function.command.SubCommand

class Help : SubCommand {
    override fun execute(sender: CommandSender, args: Array<String>): Boolean {
        val length = Lang.getStringList(LangType.COMMANG_HELP).size
        if (!sender.isOp) {
            for (help in Lang.getStringList(LangType.COMMANG_HELP)) {
                sender.sendMessage(help!!)
            }
        } else {
            var count = 0
            for (help in Lang.getStringList(LangType.COMMANG_HELP)) {
                if (count == length - 1) {
                    break
                }
                sender.sendMessage(help!!)
                count++
            }
            for (help in Lang.getStringList(LangType.OP_COMMANG_HELP)) {
                sender.sendMessage(help!!)
            }
            sender.sendMessage(Lang.getStringList(LangType.COMMANG_HELP)[length - 1])
        }
        return true
    }

    override fun getParams(): Map<Int, Array<String>>? {
        return null
    }
}