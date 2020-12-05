package tw.momocraft.inventoryplus.handlers;

import tw.momocraft.inventoryplus.Commands;
import tw.momocraft.inventoryplus.utils.TabComplete;


public class RegisterHandler {

    public static void registerEvents() {
        tw.momocraft.inventoryplus.InventoryPlus.getInstance().getCommand("InventoryPlus").setExecutor(new Commands());
        tw.momocraft.inventoryplus.InventoryPlus.getInstance().getCommand("InventoryPlus").setTabCompleter(new TabComplete());
    }
}
