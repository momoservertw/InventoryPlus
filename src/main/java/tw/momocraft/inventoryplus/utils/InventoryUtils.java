package tw.momocraft.inventoryplus.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import tw.momocraft.inventoryplus.handlers.ConfigHandler;
import tw.momocraft.inventoryplus.handlers.ServerHandler;

import java.util.List;

public class InventoryUtils {

    public static boolean start(CommandSender sender, Player target, String slot, String type) {
        slot = slot.toLowerCase();
        if (target == null) {
            target = (Player) sender;
        }

    }
    public static boolean startCleanSlot(CommandSender sender, Player target, String slot) {
        slot = slot.toLowerCase();
        if (target == null) {
            target = (Player) sender;
        }
        if (ConfigHandler.getConfigPath().getInvCleanProp().containsKey(slot)) {
            if (ConfigHandler.getConfigPath().isInventoryLogClean()) {
                ConfigHandler.getLogger().addCleanLog(target.getName() + " clean - " + slot, true);
            }
            cleanslots(target, ConfigHandler.getConfigPath().getInvCleanProp().get(slot));
            ServerHandler.sendFeatureMessage("Clean", target.getName(), "execute", "return", slot,
                    new Throwable().getStackTrace()[0]);
            return true;
        }
        if (slot.matches("^[0-9]*$")) {
            if (Integer.parseInt(slot) < 35) {
                if (ConfigHandler.getConfigPath().isInventoryLogClean()) {
                    ConfigHandler.getLogger().addCleanLog(target.getName() + " clean - " + slot, true);
                }
                cleanslot(target, slot);
                ServerHandler.sendFeatureMessage("Clean", target.getName(), "execute", "return", slot,
                        new Throwable().getStackTrace()[0]);
                return true;
            }
        }
        switch (slot) {
            case "all":
            case "head":
            case "chest":
            case "legs":
            case "feet":
            case "hand":
            case "off_hand":
            case "crafting[1]":
            case "crafting[2]":
            case "crafting[3]":
            case "crafting[4]":
                if (ConfigHandler.getConfigPath().isInventoryLogClean()) {
                    ConfigHandler.getLogger().addCleanLog(target.getName() + " clean - " + slot, true);
                }
                cleanslot(target, slot);
                ServerHandler.sendFeatureMessage("Clean", target.getName(), "execute", "return", slot,
                        new Throwable().getStackTrace()[0]);
                return true;
        }
        return false;
    }

    private static void cleanslots(Player player, List<String> slots) {
        for (String slot : slots) {
            cleanslot(player, slot);
        }
    }

    private static void cleanslot(Player player, String slot) {
        // Inventory
        if (slot.matches("^[0-9]*$")) {
            player.getInventory().setItem(Integer.parseInt(slot), null);
        }
        switch (slot) {
            // ALL
            case "all":
                player.getInventory().clear();
                break;
            // Equipment
            case "head":
                player.getInventory().setHelmet(null);
                break;
            case "chest":
                player.getInventory().setChestplate(null);
                break;
            case "legs":
                player.getInventory().setLeggings(null);
                break;
            case "feet":
                player.getInventory().setBoots(null);
                break;
            case "hand":
                player.getInventory().setItemInMainHand(null);
                break;
            case "off_hand":
                player.getInventory().setItemInOffHand(null);
                break;
            // Crafting
            case "crafting[1]":
            case "crafting[2]":
            case "crafting[3]":
            case "crafting[4]":
                player.getOpenInventory().getTopInventory().setItem(Integer.parseInt(slot.replace("CRAFTING[", "").replace("]", "")), null);
        }
    }

    public static boolean startTakeOffSlot(CommandSender sender, Player target, String slot) {
        slot = slot.toLowerCase();
        if (target == null) {
            target = (Player) sender;
        }
        if (ConfigHandler.getConfigPath().getInvTakeOffProp().containsKey(slot)) {
            if (ConfigHandler.getConfigPath().isInventoryLogTakeOff()) {
                ConfigHandler.getLogger().addCleanLog(target.getName() + " takeoff - " + slot, true);
            }
            takOffSlots(target, ConfigHandler.getConfigPath().getInvCleanProp().get(slot));
            ServerHandler.sendFeatureMessage("TakeOff", target.getName(), "execute", "return", slot,
                    new Throwable().getStackTrace()[0]);
            return true;
        }
        switch (slot) {
            case "all":
            case "head":
            case "chest":
            case "legs":
            case "feet":
            case "off_hand":
                if (ConfigHandler.getConfigPath().isInventoryLogTakeOff()) {
                    ConfigHandler.getLogger().addCleanLog(target.getName() + " takeoff - " + slot, true);
                }
                takeOffSlot(target, slot);
                ServerHandler.sendFeatureMessage("TakeOff", target.getName(), "execute", "return", slot,
                        new Throwable().getStackTrace()[0]);
                return true;
        }
        return false;
    }

    private static void takOffSlots(Player player, List<String> slots) {
        for (String slot : slots) {
            takeOffSlot(player, slot);
        }
    }

    private static void takeOffSlot(Player player, String slot) {
        PlayerInventory inventory = player.getInventory();
        ItemStack itemStack;
        int firstEmpty = inventory.firstEmpty();
        switch (slot) {
            // ALL
            case "all":
                if (firstEmpty != -1) {
                    itemStack = inventory.getHelmet();
                    inventory.setItem(firstEmpty, itemStack);
                    inventory.setHelmet(null);
                    firstEmpty = inventory.firstEmpty();
                }
                if (firstEmpty != -1) {
                    itemStack = inventory.getChestplate();
                    inventory.setItem(firstEmpty, itemStack);
                    inventory.setChestplate(null);
                    firstEmpty = inventory.firstEmpty();
                }
                if (firstEmpty != -1) {
                    itemStack = inventory.getLeggings();
                    inventory.setItem(firstEmpty, itemStack);
                    inventory.setLeggings(null);
                    firstEmpty = inventory.firstEmpty();
                }
                if (firstEmpty != -1) {
                    itemStack = inventory.getBoots();
                    inventory.setItem(firstEmpty, itemStack);
                    inventory.setBoots(null);
                    firstEmpty = inventory.firstEmpty();
                }
                if (firstEmpty != -1) {
                    itemStack = inventory.getItemInOffHand();
                    inventory.setItem(firstEmpty, itemStack);
                    inventory.setItemInOffHand(null);
                }
                break;
            // Equipment
            case "head":
                if (firstEmpty != -1) {
                    itemStack = inventory.getHelmet();
                    inventory.setItem(firstEmpty, itemStack);
                    inventory.setHelmet(null);
                }
                break;
            case "chest":
                if (firstEmpty != -1) {
                    itemStack = inventory.getChestplate();
                    inventory.setItem(firstEmpty, itemStack);
                    inventory.setChestplate(null);
                }
                break;
            case "legs":
                if (firstEmpty != -1) {
                    itemStack = inventory.getLeggings();
                    inventory.setItem(firstEmpty, itemStack);
                    inventory.setLeggings(null);
                }
                break;
            case "feet":
                if (firstEmpty != -1) {
                    itemStack = inventory.getBoots();
                    inventory.setItem(firstEmpty, itemStack);
                    inventory.setBoots(null);
                }
                break;
            case "off_hand":
                if (firstEmpty != -1) {
                    itemStack = inventory.getItemInOffHand();
                    inventory.setItem(firstEmpty, itemStack);
                    inventory.setItemInOffHand(null);
                }
                break;
        }
    }

    public static boolean startBinding(CommandSender sender, Player target, String slot) {
        slot = slot.toLowerCase();
        if (target == null) {
            target = (Player) sender;
        }
        if (ConfigHandler.getConfigPath().getInvTakeOffProp().containsKey(slot)) {
            if (ConfigHandler.getConfigPath().isInventoryLogTakeOff()) {
                ConfigHandler.getLogger().addCleanLog(target.getName() + " binding - " + slot, true);
            }
            takOffSlots(target, ConfigHandler.getConfigPath().getInvCleanProp().get(slot));
            ServerHandler.sendFeatureMessage("Binding-Curse", target.getName(), "execute", "return", slot,
                    new Throwable().getStackTrace()[0]);
            return true;
        }
        switch (slot) {
            case "all":
            case "head":
            case "chest":
            case "legs":
            case "feet":
            case "off_hand":
                if (ConfigHandler.getConfigPath().isInventoryLogBinding()) {
                    ConfigHandler.getLogger().addCleanLog(target.getName() + " binding - " + slot, true);
                }
                bindingSlot(target, slot);
                ServerHandler.sendFeatureMessage("Binding-Curse", target.getName(), "execute", "return", slot,
                        new Throwable().getStackTrace()[0]);
                return true;
        }
        return false;
    }

    private static void bindingSlot(Player player, String slot) {
        PlayerInventory inventory = player.getInventory();
        ItemStack itemStack;
        ItemMeta itemMeta;
        // Inventory
        if (slot.matches("^[0-9]*$")) {
            itemStack = inventory.getItem(Integer.parseInt(slot));
            try {
                itemMeta = itemStack.getItemMeta();
                if (itemMeta.getEnchants().containsKey(Enchantment.BINDING_CURSE)) {
                    itemMeta.getEnchants().remove(Enchantment.BINDING_CURSE);
                }
            } catch (Exception ignored) {
            }
        }
        switch (slot) {
            // ALL
            case "all":
                for (itemStack = inventory.get)
                itemStack = player.getOpenInventory().getTopInventory().getItem(Integer.parseInt(slot.replace("CRAFTING[", "").replace("]", "")));
                try {
                    itemMeta = itemStack.getItemMeta();
                    if (itemMeta.getEnchants().containsKey(Enchantment.BINDING_CURSE)) {
                        itemMeta.getEnchants().remove(Enchantment.BINDING_CURSE);
                    }
                } catch (Exception ignored) {
                }
                break;
            // Equipment
            case "head":
                itemStack = inventory.getHelmet();
                try {
                    itemMeta = itemStack.getItemMeta();
                    if (itemMeta.getEnchants().containsKey(Enchantment.BINDING_CURSE)) {
                        itemMeta.getEnchants().remove(Enchantment.BINDING_CURSE);
                    }
                } catch (Exception ignored) {
                }
                break;
            case "chest":
                itemStack = inventory.getChestplate();
                try {
                    itemMeta = itemStack.getItemMeta();
                    if (itemMeta.getEnchants().containsKey(Enchantment.BINDING_CURSE)) {
                        itemMeta.getEnchants().remove(Enchantment.BINDING_CURSE);
                    }
                } catch (Exception ignored) {
                }
                break;
            case "legs":
                itemStack = inventory.getLeggings();
                try {
                    itemMeta = itemStack.getItemMeta();
                    if (itemMeta.getEnchants().containsKey(Enchantment.BINDING_CURSE)) {
                        itemMeta.getEnchants().remove(Enchantment.BINDING_CURSE);
                    }
                } catch (Exception ignored) {
                }
                break;
            case "feet":
                itemStack = inventory.getBoots();
                try {
                    itemMeta = itemStack.getItemMeta();
                    if (itemMeta.getEnchants().containsKey(Enchantment.BINDING_CURSE)) {
                        itemMeta.getEnchants().remove(Enchantment.BINDING_CURSE);
                    }
                } catch (Exception ignored) {
                }
                break;
            case "hand":
                itemStack = inventory.getItemInMainHand();
                try {
                    itemMeta = itemStack.getItemMeta();
                    if (itemMeta.getEnchants().containsKey(Enchantment.BINDING_CURSE)) {
                        itemMeta.getEnchants().remove(Enchantment.BINDING_CURSE);
                    }
                } catch (Exception ignored) {
                }
                break;
            case "off_hand":
                itemStack = inventory.getItemInOffHand();
                try {
                    itemMeta = itemStack.getItemMeta();
                    if (itemMeta.getEnchants().containsKey(Enchantment.BINDING_CURSE)) {
                        itemMeta.getEnchants().remove(Enchantment.BINDING_CURSE);
                    }
                } catch (Exception ignored) {
                }
                break;
            // Crafting
            case "crafting[1]":
            case "crafting[2]":
            case "crafting[3]":
            case "crafting[4]":
                itemStack = player.getOpenInventory().getTopInventory().getItem(Integer.parseInt(slot.replace("CRAFTING[", "").replace("]", "")));
                try {
                    itemMeta = itemStack.getItemMeta();
                    if (itemMeta.getEnchants().containsKey(Enchantment.BINDING_CURSE)) {
                        itemMeta.getEnchants().remove(Enchantment.BINDING_CURSE);
                    }
                } catch (Exception ignored) {
                }
        }
    }
}
