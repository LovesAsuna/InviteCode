package org.sct.invitecode.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.sct.invitecode.InviteCode;
import org.sct.invitecode.data.InviteCodeData;

import java.util.ArrayList;
import java.util.Map;

public class InventoryClick implements Listener {
    int count;
    ArrayList<Integer> rewardlist;
    private Map<Integer, Integer> slot_item;

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getPlayer().isOp()) {
            rewardlist = (ArrayList<Integer>) InviteCode.getInstance().getConfig().getIntegerList("InviteCode.Reward");
            slot_item = InviteCodeData.getGui().gethashmap();
            //System.out.println(rewardlist);
            //System.out.println(slot_item);
            if (e.getView().getTitle().equalsIgnoreCase("§bRewardList")) {
                count = 0;
                for (int none : InviteCode.getInstance().getConfig().getIntegerList("InviteCode.Reward")) {
                    count++;//计算gui中的物品数量
                }
                if (e.getRawSlot() >= 0 && e.getRawSlot() <= count) {
                    rewardlist.remove(slot_item.get(e.getRawSlot()));
                    InviteCode.getInstance().getConfig().set("InviteCode.Reward", rewardlist);
                    InviteCode.getInstance().saveConfig();
                    e.getView().getPlayer().closeInventory();
                    e.getView().getPlayer().openInventory(InviteCodeData.getGui().setGUI((Player) e.getView().getPlayer()));
                }
            }
        }
        e.setCancelled(true);
    }
}
