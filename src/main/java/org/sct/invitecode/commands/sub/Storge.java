package org.sct.invitecode.commands.sub;

import org.bukkit.command.CommandSender;
import org.sct.invitecode.data.InviteCodeData;
import org.sct.invitecode.storge.Yaml;
import org.sct.plugincore.util.function.SubCommand;


public class Storge implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("ic.storge") || sender.isOp()) {
            if (InviteCodeData.getStorge() instanceof Yaml) {
                sender.sendMessage("§7[§eInviteCode§7]§2数据储存方式为Yaml");
            } else {
                sender.sendMessage("§7[§eInviteCode§7]§2数据储存方式为Mysql");
            }
        } else {
            sender.sendMessage("§7[§eInviteCode§7]§c你没有此命令的权限!");
        }
        return true;
    }
}
