package org.sct.invitecode.commands.sub;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sct.invitecode.data.InviteCodeData;
import org.sct.plugincore.util.function.SubCommand;

/**
 * @author icestar
 */
public class Check implements SubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("check")) {
            //为自己查询
            Player player = (Player) sender;
            sender.sendMessage("§7[§eInviteCode§7]§b你的邀请码为: " + InviteCodeData.getStorge().read(player.getName()));
            return true;
        } else if (args.length == 2 && args[0].equalsIgnoreCase("check")) {
            //为别人查询
            String playername = args[1];
            if (sender.hasPermission("ic.checkothers") || sender.isOp()) {
                sender.sendMessage("§7[§eInviteCode§7]§b你输入的玩家ID为" + playername);
                sender.sendMessage("§7[§eInviteCode§7]§b" + "玩家" + InviteCodeData.getplayername(playername) + "的邀请码为: " + InviteCodeData.getStorge().read(InviteCodeData.getplayername(playername)));
            } else {
                sender.sendMessage("§7[§eInviteCode§7]§c你没有此命令的权限!");
            }
            return true;
        }
        return true;
    }

}
