package org.sct.invitecode.commands;

import org.bukkit.plugin.java.JavaPlugin;
import org.sct.easylib.util.function.command.CommandHandler;
import org.sct.invitecode.commands.sub.*;

/**
 * @author LovesAsuna
 * @date 2020/3/24 13:16
 */

public class SubCommandHandler extends CommandHandler {
    public SubCommandHandler(JavaPlugin instance, String cmd) {
        super(instance, cmd);
        registerSubCommand("check", new Check());
        registerSubCommand("create", new Create());
        registerSubCommand("help", new Help());
        registerSubCommand("info", new Info());
        registerSubCommand("reload", new Reload());
        registerSubCommand("reset", new Reset());
        registerSubCommand("reward", new Reward());
        registerSubCommand("saveitem", new SaveItem());
        registerSubCommand("show", new Show());
        registerSubCommand("storge", new Storge());
        registerSubCommand("update", new Update());
    }

}
