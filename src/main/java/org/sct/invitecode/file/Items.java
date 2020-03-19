package org.sct.invitecode.file;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.sct.invitecode.InviteCode;

import java.io.File;
import java.io.IOException;

public class Items {
    private static File file;
    private static YamlConfiguration items;

    public static void loadItems() {
        file = new File(InviteCode.getInstance().getDataFolder() + "\\items.yml");
        if (!file.exists()) {
            InviteCode.getInstance().saveResource("items.yml", false);
        }
        items = YamlConfiguration.loadConfiguration(file);
    }

    public static void SaveItem(String count, ItemStack itemStack) {
        loadItems();
        items.set(count, itemStack);
        try {
            items.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ItemStack getItemStack(int num) {
        loadItems();
        return items.getItemStack("items." + num);
    }
}
