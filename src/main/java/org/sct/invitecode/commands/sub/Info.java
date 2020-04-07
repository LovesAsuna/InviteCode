package org.sct.invitecode.commands.sub;

import org.bukkit.command.CommandSender;
import org.sct.invitecode.InviteCode;
import org.sct.plugincore.util.function.command.SubCommand;

import java.util.Map;

public class Info implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage("§7┌ §ePlugin§7:§b InviteCode");
        sender.sendMessage("§7├ §eAuthor§7:§b 冰星");
        sender.sendMessage("§7├ §eVersion§7:§b " + InviteCode.getInstance().getDescription().getVersion());
        sender.sendMessage("§7└ §eLink§7:§b https://www.mcbbs.net/thread-916058-1-1.html");

        return true;
    }

    @Override
    public Map<Integer, String[]> getParams() {
        return null;
    }
}
