package tw.momocraft.inventoryplus.handlers;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class PermissionsHandler {

    public static boolean hasPermission(CommandSender sender, String permission) {
        return sender.hasPermission(permission) || sender.hasPermission("InventoryPlus.*") || sender.isOp() || (sender instanceof ConsoleCommandSender);
    }
}