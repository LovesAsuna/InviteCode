package org.sct.invitecode.commands.sub;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.sct.invitecode.file.Items;
import org.sct.plugincore.util.function.command.SubCommand;

import java.util.Map;


public class SaveItem implements SubCommand {
    private int itemcount;

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("ic.saveitem") || player.isOp()) {
                @SuppressWarnings("deprecation")
                ItemStack item = player.getItemInHand();
                Items.SaveItem("items." + ++itemcount, item);
                Items.loadItems();
                sender.sendMessage("§7[§eInviteCode§7]§a物品保存成功");
            } else {
                sender.sendMessage("§7[§eInviteCode§7]§c你没有此命令的权限!");
            }
        } else {
            sender.sendMessage("§7[§eInviteCode§7]§c你必须是一名玩家");
        }
        return true;
    }

    @Override
    public Map<Integer, String[]> getParams() {
        return null;
    }
}
