package org.sct.invitecode.commands.sub

import com.google.common.collect.Maps
import org.bukkit.command.CommandSender
import org.sct.invitecode.InviteCode
import org.sct.invitecode.data.InviteCodeData
import org.sct.plugincore.util.function.command.SubCommand
import java.io.IOException

class Update : SubCommand {
    override fun execute(sender: CommandSender, args: Array<String>): Boolean {
        if (!sender.isOp) {
            sender.sendMessage("§7[§eInviteCode§7]§c你没有其命令的权限")
        }
        if (args.size == 2) {
            if (args.size == 2 && args[1].equals("download", ignoreCase = true)) {
                InviteCodeData.pool?.submit {
                    InviteCode.getPluginCoreAPI().gitHubAPI.download(sender, InviteCode.getInstance(), "LovesAsuna")
                    sender.sendMessage("§7[§eInviteCode§7]§2下载成功")
                }
            } else if (args.size == 2 && args[1].equals("version", ignoreCase = true)) {
                InviteCodeData.pool?.submit {
                    try {
                        InviteCode.getPluginCoreAPI().gitHubAPI.getUpdateDetail(sender, InviteCode.getInstance(), "LovesAsuna", "ZDRlZWY4ZDZlMzIyNDExYjk3NThlMGNiN2ZmYzg3NTRiOGIwZDUzZA==")
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
        return true
    }

    override fun getParams(): Map<Int, Array<String>> {
        val params: MutableMap<Int, Array<String>> = Maps.newHashMap()
        params[1] = arrayOf("download", "version")
        return params
    }
}