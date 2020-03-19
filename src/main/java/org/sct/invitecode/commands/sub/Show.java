package org.sct.invitecode.commands.sub;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.sct.invitecode.data.InviteCodeData;
import org.sct.plugincore.util.function.SubCommand;

public class Show implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§7[§eInviteCode§7]§c你必须是一名玩家");
            return false;
        }

        Player player = (Player) sender;
        player.closeInventory();
        Inventory inv = InviteCodeData.getGui().setGUI(player);
        player.openInventory(inv);
        return true;
    }
}
