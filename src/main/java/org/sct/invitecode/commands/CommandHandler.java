package org.sct.invitecode.commands;

import com.google.common.collect.Maps;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.sct.invitecode.InviteCode;
import org.sct.invitecode.commands.sub.*;
import org.sct.plugincore.util.function.SubCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandHandler implements CommandExecutor, TabCompleter {
    protected static final String cmds = "InviteCode";
    private Map<String, SubCommand> subCommandMap = Maps.newHashMap();

    public CommandHandler() {
        subCommandMap.put("reload", new Reload());
        subCommandMap.put("help", new Help());
        subCommandMap.put("check", new Check());
        subCommandMap.put("create", new Create());
        subCommandMap.put("reset", new Reset());
        subCommandMap.put("reward", new Reward());
        subCommandMap.put("saveitem", new SaveItem());
        subCommandMap.put("show", new Show());
        subCommandMap.put("storge", new Storge());
        subCommandMap.put("update", new Update());
    }

    public void registerSubCommand(String commandName, SubCommand command) {
        if (subCommandMap.containsKey(commandName)) {
            InviteCode.getInstance().getLogger().warning("发现重复子命令注册!");
        }
        subCommandMap.put(commandName, command);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmds.equalsIgnoreCase(cmd.getName())) {
            if (args.length == 0) {
                //如果命令没有参数
                subCommandMap.get("help").execute(sender, args);
                return true;
            }

            SubCommand subCommand = subCommandMap.get(args[0]);
            if (subCommand == null) {
                //如果参数不正确
                sender.sendMessage("§7[§eInviteCode§7]§c你输入的命令不正确!");
                return true;
            }
            //执行命令
            subCommand.execute(sender, args);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (cmd.getName().equalsIgnoreCase("InviteCode")) {
            if (args.length == 1) {
                completions.add("reload");
                completions.add("help");
                completions.add("check");
                completions.add("create");
                completions.add("reward");
                completions.add("saveitem");
                completions.add("show");
                completions.add("storge");
                completions.add("reset");
                completions.add("update");
                return StringUtil.copyPartialMatches(args[0], completions, new ArrayList<>());
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("update")) {
                    completions.add("download");
                    completions.add("version");
                    return StringUtil.copyPartialMatches(args[1], completions, new ArrayList<>());
                }
            }
        }
        return completions;
    }
}
