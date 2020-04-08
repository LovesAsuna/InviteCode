package org.sct.invitecode.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Map;

public class InventoryClick implements Listener {
    int count;
    ArrayList<Integer> rewardlist;
    private Map<Integer, Integer> slot_item;

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if ("§bRewardList".equalsIgnoreCase(e.getView().getTitle())) {
            e.setCancelled(true);
        }
    }
}
