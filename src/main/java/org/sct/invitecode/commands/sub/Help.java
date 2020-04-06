package org.sct.invitecode.commands.sub;

import org.bukkit.command.CommandSender;
import org.sct.invitecode.enumeration.LangType;
import org.sct.invitecode.file.Lang;
import org.sct.plugincore.util.function.command.SubCommand;

import java.util.Map;

public class Help implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        int length = Lang.getStringList(LangType.COMMANG_HELP).size();

        if (!sender.isOp()) {
            for (String help : Lang.getStringList(LangType.COMMANG_HELP)) {
                sender.sendMessage(help);
            }
        } else {
            int count = 0;
            for (String help : Lang.getStringList(LangType.COMMANG_HELP)) {
                if (count == length - 1) {
                    break;
                }
                sender.sendMessage(help);
                count++;
            }
            for (String help : Lang.getStringList(LangType.OP_COMMANG_HELP)) {
                sender.sendMessage(help);
            }
            sender.sendMessage(Lang.getStringList(LangType.COMMANG_HELP).get(length - 1));
        }

        return true;
    }

    @Override
    public Map<Integer, String[]> getParams() {
        return null;
    }
}
