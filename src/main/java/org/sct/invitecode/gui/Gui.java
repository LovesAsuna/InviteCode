package org.sct.invitecode.gui;

import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.sct.invitecode.file.Config;
import org.sct.invitecode.file.Items;
import org.sct.invitecode.file.Times;

import java.util.HashMap;
import java.util.Map;

public class Gui {
    private static int slot;
    private Map<Integer, Integer> slot_item = new HashMap<>();

    public Inventory setGUI(@NonNull Player player) {
        slot = 0;
        Inventory inv = Bukkit.createInventory(null, 1 * 9, "§bRewardList");
        int time = Times.getTimes().getInt(player.getName());
        for (int reward : Config.getRewardList(time + 1)) {
            if (slot <= 8) {
                /*获取config内设置的奖励物品*/
                ItemStack item = Items.getItemStack(reward);

                /*存入栏位-物品*/
                slot_item.put(slot, reward);
                inv.setItem(slot, item);
            }
            slot++;
        }
        return inv;
    }

    public Map<Integer, Integer> gethashmap() {
        return slot_item;
    }


}
