package tw.momocraft.inventoryplus;

import org.bukkit.plugin.java.JavaPlugin;
import tw.momocraft.inventoryplus.handlers.ConfigHandler;
import tw.momocraft.inventoryplus.handlers.RegisterHandler;
import tw.momocraft.inventoryplus.handlers.ServerHandler;

public class InventoryPlus extends JavaPlugin {
    private static tw.momocraft.inventoryplus.InventoryPlus instance;

    @Override
    public void onEnable() {
        instance = this;
        ConfigHandler.generateData(false);
        RegisterHandler.registerEvents();
        ServerHandler.sendConsoleMessage("&fhas been Enabled.");
    }

    @Override
    public void onDisable() {
        ServerHandler.sendConsoleMessage("&fhas been Disabled.");
    }

    public static tw.momocraft.inventoryplus.InventoryPlus getInstance() {
        return instance;
    }
}