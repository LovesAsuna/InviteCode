package org.sct.invitecode.commands.sub;

import org.bukkit.command.CommandSender;
import org.sct.invitecode.data.InviteCodeData;
import org.sct.plugincore.util.function.command.SubCommand;

import java.util.Map;

public class Reset implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {

        if (args.length == 1) {
            return false;
        }

        if (sender.isOp() || sender.hasPermission("ic.reset")) {
            String playername = InviteCodeData.getplayername(args[1]);
            InviteCodeData.getTimer().put(playername + "start", 0L);
            sender.sendMessage("§7[§eInviteCode§7]§b玩家" + playername + "的时间已重置!");
        } else {
            sender.sendMessage("§7[§eInviteCode§7]§c你没有此命令的权限!");
        }
        return true;
    }

    @Override
    public Map<Integer, String[]> getParams() {
        return null;
    }
}
