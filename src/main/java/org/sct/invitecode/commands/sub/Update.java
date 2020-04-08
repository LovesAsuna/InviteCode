package org.sct.invitecode.commands.sub;

import com.google.common.collect.Maps;
import org.bukkit.command.CommandSender;
import org.sct.invitecode.InviteCode;
import org.sct.invitecode.data.InviteCodeData;
import org.sct.plugincore.util.function.command.SubCommand;
import org.sct.plugincore.util.plugin.DownloadUtil;

import java.io.IOException;
import java.util.Map;

public class Update implements SubCommand {

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("§7[§eInviteCode§7]§c你没有其命令的权限");
        }

        if (args.length == 2) {
            if (args.length == 2 && args[1].equalsIgnoreCase("download")) {
                InviteCodeData.getPool().submit(() -> {
                    try {
                        DownloadUtil.download(sender, InviteCode.getInstance(), "LovesAsuna");
                        sender.sendMessage("§7[§eInviteCode§7]§2下载成功");
                    } catch (IOException e) {
                        sender.sendMessage("§7[§eInviteCode§7]§c下载更新时出错");
                    }
                });

            } else if (args.length == 2 && args[1].equalsIgnoreCase("version")) {
                InviteCodeData.getPool().submit(() -> {
                    try {
                        InviteCode.getPluginCoreAPI().getGitHubAPI().getUpdateDetail(sender, InviteCode.getInstance(), "LovesAsuna", "ZDRlZWY4ZDZlMzIyNDExYjk3NThlMGNiN2ZmYzg3NTRiOGIwZDUzZA==");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }

        }
        return true;
    }

    @Override
    public Map<Integer, String[]> getParams() {
        Map<Integer, String[]> params = Maps.newHashMap();
        params.put(1, new String[]{"download", "version"});
        return params;
    }
}
