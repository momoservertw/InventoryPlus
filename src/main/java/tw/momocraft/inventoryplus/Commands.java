package tw.momocraft.inventoryplus;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import tw.momocraft.inventoryplus.handlers.ConfigHandler;
import tw.momocraft.inventoryplus.handlers.PermissionsHandler;
import tw.momocraft.inventoryplus.handlers.PlayerHandler;
import tw.momocraft.inventoryplus.handlers.ServerHandler;
import tw.momocraft.inventoryplus.utils.InventoryUtils;
import tw.momocraft.inventoryplus.utils.Language;


public class Commands implements CommandExecutor {

    public boolean onCommand(final CommandSender sender, Command c, String l, String[] args) {
        switch (args.length) {
            case 0:
                if (PermissionsHandler.hasPermission(sender, "inventoryplus.use")) {
                    Language.dispatchMessage(sender, "");
                    Language.sendLangMessage("Message.InventoryPlus.Commands.title", sender, false);
                    if (PermissionsHandler.hasPermission(sender, "InventoryPlus.command.version")) {
                        Language.dispatchMessage(sender, "&d&lInventoryPlus &e&lv" + InventoryPlus.getInstance().getDescription().getVersion() + "&8 - &fby Momocraft");
                    }
                    Language.sendLangMessage("Message.InventoryPlus.Commands.help", sender, false);
                    Language.dispatchMessage(sender, "");
                } else {
                    Language.sendLangMessage("Message.noPermission", sender);
                }
                return true;
            case 1:
                if (args[0].equalsIgnoreCase("help")) {
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.use")) {
                        Language.dispatchMessage(sender, "");
                        Language.sendLangMessage("Message.InventoryPlus.Commands.title", sender, false);
                        if (PermissionsHandler.hasPermission(sender, "InventoryPlus.command.version")) {
                            Language.dispatchMessage(sender, "&d&lInventoryPlus &e&lv" + InventoryPlus.getInstance().getDescription().getVersion() + "&8 - &fby Momocraft");
                        }
                        Language.sendLangMessage("Message.InventoryPlus.Commands.help", sender, false);
                        if (PermissionsHandler.hasPermission(sender, "InventoryPlus.command.reload")) {
                            Language.sendLangMessage("Message.InventoryPlus.Commands.reload", sender, false);
                        }
                        if (PermissionsHandler.hasPermission(sender, "InventoryPlus.command.clear.other")) {
                            Language.sendLangMessage("Message.InventoryPlus.Commands.clean", sender, false);
                            Language.sendLangMessage("Message.InventoryPlus.Commands.cleanOther", sender, false);
                        } else if (PermissionsHandler.hasPermission(sender, "InventoryPlus.command.clear")) {
                            Language.sendLangMessage("Message.InventoryPlus.Commands.clean", sender, false);
                        }
                        if (PermissionsHandler.hasPermission(sender, "InventoryPlus.command.cleanslot.other")) {
                            Language.sendLangMessage("Message.InventoryPlus.Commands.cleanSlot", sender, false);
                            Language.sendLangMessage("Message.InventoryPlus.Commands.cleanSlotOther", sender, false);
                        } else if (PermissionsHandler.hasPermission(sender, "InventoryPlus.command.cleanslot")) {
                            Language.sendLangMessage("Message.InventoryPlus.Commands.cleanSlot", sender, false);
                        }
                        if (PermissionsHandler.hasPermission(sender, "InventoryPlus.command.takeoff.other")) {
                            Language.sendLangMessage("Message.InventoryPlus.Commands.takeOff", sender, false);
                            Language.sendLangMessage("Message.InventoryPlus.Commands.takeOffOther", sender, false);
                        } else if (PermissionsHandler.hasPermission(sender, "InventoryPlus.command.clear")) {
                            Language.sendLangMessage("Message.InventoryPlus.Commands.clean", sender, false);
                        }
                        if (PermissionsHandler.hasPermission(sender, "InventoryPlus.command.takeoffslot.other")) {
                            Language.sendLangMessage("Message.InventoryPlus.Commands.takeOffSlot", sender, false);
                            Language.sendLangMessage("Message.InventoryPlus.Commands.takeOffSlotOther", sender, false);
                        } else if (PermissionsHandler.hasPermission(sender, "InventoryPlus.command.takeoffslot")) {
                            Language.sendLangMessage("Message.InventoryPlus.Commands.takeOffSlot", sender, false);
                        }
                        Language.dispatchMessage(sender, "");
                    } else {
                        Language.sendLangMessage("Message.noPermission", sender);
                    }
                    return true;
                } else if (args[0].equalsIgnoreCase("reload")) {
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.reload")) {
                        ConfigHandler.generateData(true);
                        Language.sendLangMessage("Message.configReload", sender);
                    } else {
                        Language.sendLangMessage("Message.noPermission", sender);
                    }
                    return true;
                } else if (args[0].equalsIgnoreCase("version")) {
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.version")) {
                        Language.dispatchMessage(sender, "&d&lInventoryPlus &e&lv" + InventoryPlus.getInstance().getDescription().getVersion() + "&8 - &fby Momocraft");
                        ConfigHandler.getUpdater().checkUpdates(sender);
                    } else {
                        Language.sendLangMessage("Message.noPermission", sender);
                    }
                    return true;
                    // /itp clear
                } else if (args[0].equalsIgnoreCase("clear")) {
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.clear")) {
                        if (ConfigHandler.getConfigPath().isInventoryClean()) {
                            if (sender instanceof ConsoleCommandSender) {
                                Language.sendLangMessage("Message.onlyPlayer", sender);
                            } else {
                                if (tw.momocraft.inventoryplus.utils.InventoryUtils.startCleanSlot(sender, null, "ALL")) {
                                    Language.sendLangMessage("Message.InventoryPlus.clearSucceed", sender);
                                }
                            }
                        } else {
                            Language.sendLangMessage("Message.featureDisabled", sender);
                        }
                    } else {
                        Language.sendLangMessage("Message.noPermission", sender);
                    }
                    return true;
                    // /itp takeoff
                } else if (args[0].equalsIgnoreCase("takeoff")) {
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.takeoff")) {
                        if (ConfigHandler.getConfigPath().isInventoryTakeOff()) {
                            if (sender instanceof ConsoleCommandSender) {
                                Language.sendLangMessage("Message.onlyPlayer", sender);
                            } else {
                                if (InventoryUtils.startTakeOffSlot(sender, null, "ALL")) {
                                    Language.sendLangMessage("Message.InventoryPlus.takeOffSucceed", sender);
                                }
                            }
                        } else {
                            Language.sendLangMessage("Message.featureDisabled", sender);
                        }
                    } else {
                        Language.sendLangMessage("Message.noPermission", sender);
                    }
                    return true;
                }
                break;
            case 2:
                // /itp clear PLAYER
                if (args[0].equalsIgnoreCase("clear")) {
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.clear.other")) {
                        if (ConfigHandler.getConfigPath().isInventoryClean()) {
                            Player player = PlayerHandler.getPlayerString(args[1]);
                            String[] placeHolders = Language.newString();
                            placeHolders[2] = args[1];
                            if (player == null) {
                                Language.sendLangMessage("Message.targetNotFound", sender, placeHolders);
                                return true;
                            }
                            if (InventoryUtils.startCleanSlot(sender, player, "ALL")) {
                                Language.sendLangMessage("Message.InventoryPlus.clearSucceed", player);
                                Language.sendLangMessage("Message.InventoryPlus.clearTargetSucceed", sender, placeHolders);
                            }
                        } else {
                            Language.sendLangMessage("Message.featureDisabled", sender);
                        }
                    } else {
                        Language.sendLangMessage("Message.noPermission", sender);
                    }
                    return true;
                    // /itp takeoff PLAYER
                } else if (args[0].equalsIgnoreCase("takeoff")) {
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.takeoff.other")) {
                        if (ConfigHandler.getConfigPath().isInventoryTakeOff()) {
                            Player player = PlayerHandler.getPlayerString(args[1]);
                            String[] placeHolders = Language.newString();
                            placeHolders[2] = args[1];
                            if (player == null) {
                                Language.sendLangMessage("Message.targetNotFound", sender, placeHolders);
                                return true;
                            }
                            if (InventoryUtils.startTakeOffSlot(sender, player, "ALL")) {
                                Language.sendLangMessage("Message.InventoryPlus.takeOffSucceed", player);
                                Language.sendLangMessage("Message.InventoryPlus.takeOffTargetSucceed", sender, placeHolders);
                            }
                        } else {
                            Language.sendLangMessage("Message.featureDisabled", sender);
                        }
                    } else {
                        Language.sendLangMessage("Message.noPermission", sender);
                    }
                    return true;
                    // /itp cleanslot SLOT
                } else if (args[0].equalsIgnoreCase("cleanslot")) {
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.cleanslot")) {
                        if (sender instanceof ConsoleCommandSender) {
                            Language.sendLangMessage("Message.onlyPlayer", sender);
                        } else {
                            if (ConfigHandler.getConfigPath().isInventoryClean()) {
                                String[] placeHolders = Language.newString();
                                placeHolders[11] = args[1];
                                if (tw.momocraft.inventoryplus.utils.InventoryUtils.startCleanSlot(sender, null, args[1])) {
                                    Language.sendLangMessage("Message.InventoryPlus.cleanSlotSucceed", sender);
                                } else {
                                    Language.sendLangMessage("Message.InventoryPlus.slotNotFound", sender, placeHolders);
                                }
                            } else {
                                Language.sendLangMessage("Message.featureDisabled", sender);
                            }
                        }
                    } else {
                        Language.sendLangMessage("Message.noPermission", sender);
                    }
                    return true;
                    // /itp takeoff SLOT
                } else if (args[0].equalsIgnoreCase("takeoffslot")) {
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.takeoffslot")) {
                        if (sender instanceof ConsoleCommandSender) {
                            Language.sendLangMessage("Message.onlyPlayer", sender);
                        } else {
                            if (ConfigHandler.getConfigPath().isInventoryTakeOff()) {
                                String[] placeHolders = Language.newString();
                                placeHolders[11] = args[1];
                                if (InventoryUtils.startTakeOffSlot(sender, null, args[1])) {
                                    Language.sendLangMessage("Message.InventoryPlus.takeOffSlotSucceed", sender);
                                } else {
                                    Language.sendLangMessage("Message.InventoryPlus.slotNotFound", sender, placeHolders);
                                }
                            } else {
                                Language.sendLangMessage("Message.featureDisabled", sender);
                            }
                        }
                    } else {
                        Language.sendLangMessage("Message.noPermission", sender);
                    }
                    return true;
                }
                break;
            case 3:
                // /itp clear PLAYER false
                if (args[0].equalsIgnoreCase("clear") && args[2].equals("false")) {
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.clear.other")) {
                        if (ConfigHandler.getConfigPath().isInventoryClean()) {
                            Player player = PlayerHandler.getPlayerString(args[1]);
                            String[] placeHolders = Language.newString();
                            placeHolders[2] = args[1];
                            if (player == null) {
                                Language.sendLangMessage("Message.targetNotFound", sender, placeHolders);
                                return true;
                            }
                            if (InventoryUtils.startCleanSlot(sender, player, "ALL")) {
                                Language.sendLangMessage("Message.InventoryPlus.clearTargetSucceed", sender, placeHolders);
                            }
                        } else {
                            Language.sendLangMessage("Message.featureDisabled", sender);
                        }
                    } else {
                        Language.sendLangMessage("Message.noPermission", sender);
                    }
                    return true;
                    // /itp takeoff PLAYER false
                } else if (args[0].equalsIgnoreCase("takeoff") && args[2].equals("false")) {
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.takeoff.other")) {
                        if (ConfigHandler.getConfigPath().isInventoryTakeOff()) {
                            Player player = PlayerHandler.getPlayerString(args[1]);
                            String[] placeHolders = Language.newString();
                            placeHolders[2] = args[1];
                            if (player == null) {
                                Language.sendLangMessage("Message.targetNotFound", sender, placeHolders);
                                return true;
                            }
                            if (InventoryUtils.startTakeOffSlot(sender, player, "ALL")) {
                                Language.sendLangMessage("Message.InventoryPlus.takeOffTargetSucceed", sender, placeHolders);
                            }
                        } else {
                            Language.sendLangMessage("Message.featureDisabled", sender);
                        }
                    } else {
                        Language.sendLangMessage("Message.noPermission", sender);
                    }
                    return true;
                    // /itp cleanslot SLOT PLAYER
                } else if (args[0].equalsIgnoreCase("cleanslot")) {
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.cleanslot.other")) {
                        if (ConfigHandler.getConfigPath().isInventoryClean()) {
                            Player player = PlayerHandler.getPlayerString(args[2]);
                            String[] placeHolders = Language.newString();
                            placeHolders[2] = args[2];
                            placeHolders[11] = args[1];
                            if (player == null) {
                                Language.sendLangMessage("Message.targetNotFound", sender, placeHolders);
                                return true;
                            }
                            if (InventoryUtils.startCleanSlot(sender, player, args[1])) {
                                Language.sendLangMessage("Message.InventoryPlus.cleanSlotSucceed", player, placeHolders);
                                Language.sendLangMessage("Message.InventoryPlus.cleanSlotTargetSucceed", sender, placeHolders);
                            } else {
                                Language.sendLangMessage("Message.InventoryPlus.slotNotFound", sender, placeHolders);
                            }
                        } else {
                            Language.sendLangMessage("Message.featureDisabled", sender);
                        }
                    } else {
                        Language.sendLangMessage("Message.noPermission", sender);
                    }
                    return true;
                    // /itp takeoffslot SLOT PLAYER
                } else if (args[0].equalsIgnoreCase("takeoffslot")) {
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.takeoffslot.other")) {
                        if (ConfigHandler.getConfigPath().isInventoryTakeOff()) {
                            Player player = PlayerHandler.getPlayerString(args[2]);
                            String[] placeHolders = Language.newString();
                            placeHolders[2] = args[2];
                            placeHolders[11] = args[1];
                            if (player == null) {
                                Language.sendLangMessage("Message.targetNotFound", sender, placeHolders);
                                return true;
                            }
                            if (InventoryUtils.startTakeOffSlot(sender, player, args[1])) {
                                Language.sendLangMessage("Message.InventoryPlus.takeOffSlotSucceed", player, placeHolders);
                                Language.sendLangMessage("Message.InventoryPlus.takeOffSlotTargetSucceed", sender, placeHolders);
                            } else {
                                Language.sendLangMessage("Message.InventoryPlus.slotNotFound", sender, placeHolders);
                            }
                        } else {
                            Language.sendLangMessage("Message.featureDisabled", sender);
                        }
                    } else {
                        Language.sendLangMessage("Message.noPermission", sender);
                    }
                    return true;
                }
                break;
            case 4:
                // /itp cleanslot SLOT PLAYER false
                if (args[0].equalsIgnoreCase("cleanslot") && args[3].equals("false")) {
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.cleanslot.other")) {
                        if (ConfigHandler.getConfigPath().isInventoryClean()) {
                            Player player = PlayerHandler.getPlayerString(args[2]);
                            String[] placeHolders = Language.newString();
                            placeHolders[2] = args[2];
                            placeHolders[11] = args[1];
                            if (player == null) {
                                Language.sendLangMessage("Message.targetNotFound", sender, placeHolders);
                                return true;
                            }
                            if (tw.momocraft.inventoryplus.utils.InventoryUtils.startCleanSlot(sender, player, args[1])) {
                                Language.sendLangMessage("Message.InventoryPlus.cleanSlotTargetSucceed", sender, placeHolders);
                            } else {
                                Language.sendLangMessage("Message.InventoryPlus.slotNotFound", sender, placeHolders);
                            }
                        } else {
                            Language.sendLangMessage("Message.featureDisabled", sender);
                        }
                    } else {
                        Language.sendLangMessage("Message.noPermission", sender);
                    }
                    return true;
                    // /itp takeoffslot SLOT PLAYER false
                } else if (args[0].equalsIgnoreCase("takeoffslot") && args[3].equals("false")) {
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.takeoffslot.other")) {
                        if (ConfigHandler.getConfigPath().isInventoryTakeOff()) {
                            Player player = PlayerHandler.getPlayerString(args[2]);
                            String[] placeHolders = Language.newString();
                            placeHolders[2] = args[2];
                            placeHolders[11] = args[1];
                            if (player == null) {
                                Language.sendLangMessage("Message.targetNotFound", sender, placeHolders);
                                return true;
                            }
                            if (tw.momocraft.inventoryplus.utils.InventoryUtils.startTakeOffSlot(sender, player, args[1])) {
                                Language.sendLangMessage("Message.InventoryPlus.takeOffSlotTargetSucceed", sender, placeHolders);
                            } else {
                                Language.sendLangMessage("Message.InventoryPlus.slotNotFound", sender, placeHolders);
                            }
                        } else {
                            Language.sendLangMessage("Message.featureDisabled", sender);
                        }
                    } else {
                        Language.sendLangMessage("Message.noPermission", sender);
                    }
                    return true;
                }
                break;
        }
        Language.sendLangMessage("Message.unknownCommand", sender);
        return true;
    }
}