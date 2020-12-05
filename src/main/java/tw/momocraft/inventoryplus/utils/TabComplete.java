package tw.momocraft.inventoryplus.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import tw.momocraft.inventoryplus.handlers.ConfigHandler;
import tw.momocraft.inventoryplus.handlers.PermissionsHandler;
import tw.momocraft.inventoryplus.handlers.ServerHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        final List<String> completions = new ArrayList<>();
        final List<String> commands = new ArrayList<>();
        Collection<?> playersOnlineNew;
        Player[] playersOnlineOld;
        switch (args.length) {
            case 1:
                if (PermissionsHandler.hasPermission(sender, "inventoryplus.use")) {
                    commands.add("help");
                }
                if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.reload")) {
                    commands.add("reload");
                }
                if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.version")) {
                    commands.add("version");
                }
                if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.clear")) {
                    commands.add("clear");
                }
                if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.cleanslot")) {
                    commands.add("cleanslot");
                }
                if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.takeoff")) {
                    commands.add("takeoff");
                }
                if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.takeoffslot")) {
                    commands.add("takeoffslot");
                }
                break;
            case 2:
                // /itp clear PLAYER
                if (args[0].equalsIgnoreCase("clear") && PermissionsHandler.hasPermission(sender, "inventoryplus.command.clear.other")) {
                    try {
                        if (Bukkit.class.getMethod("getOnlinePlayers").getReturnType() == Collection.class) {
                            if (Bukkit.class.getMethod("getOnlinePlayers").getReturnType() == Collection.class) {
                                playersOnlineNew = ((Collection<?>) Bukkit.class.getMethod("getOnlinePlayers", new Class<?>[0]).invoke(null, new Object[0]));
                                for (Object objPlayer : playersOnlineNew) {
                                    commands.add(((Player) objPlayer).getName());
                                }
                            }
                        } else {
                            playersOnlineOld = ((Player[]) Bukkit.class.getMethod("getOnlinePlayers", new Class<?>[0]).invoke(null, new Object[0]));
                            for (Player player : playersOnlineOld) {
                                commands.add(player.getName());
                            }
                        }
                    } catch (Exception e) {
                        ServerHandler.sendDebugTrace(e);
                    }
                    // /itp takeoff PLAYER
                } else if (args[0].equalsIgnoreCase("takeoff") && PermissionsHandler.hasPermission(sender, "inventoryplus.command.takeoff.other")) {
                    try {
                        if (Bukkit.class.getMethod("getOnlinePlayers").getReturnType() == Collection.class) {
                            if (Bukkit.class.getMethod("getOnlinePlayers").getReturnType() == Collection.class) {
                                playersOnlineNew = ((Collection<?>) Bukkit.class.getMethod("getOnlinePlayers", new Class<?>[0]).invoke(null, new Object[0]));
                                for (Object objPlayer : playersOnlineNew) {
                                    commands.add(((Player) objPlayer).getName());
                                }
                            }
                        } else {
                            playersOnlineOld = ((Player[]) Bukkit.class.getMethod("getOnlinePlayers", new Class<?>[0]).invoke(null, new Object[0]));
                            for (Player player : playersOnlineOld) {
                                commands.add(player.getName());
                            }
                        }
                    } catch (Exception e) {
                        ServerHandler.sendDebugTrace(e);
                    }
                    // /itp cleanslot SLOT
                } else if (args[0].equalsIgnoreCase("cleanslot") && PermissionsHandler.hasPermission(sender, "inventoryplus.command.cleanslot")) {
                    commands.add("HAND");
                    commands.add("0");
                    commands.add("1");
                    commands.add("2");
                    commands.add("3");
                    commands.add("4");
                    commands.add("5");
                    commands.add("6");
                    commands.add("7");
                    commands.add("8");
                    commands.add("head");
                    commands.add("chest");
                    commands.add("legs");
                    commands.add("feet");
                    commands.add("off_hand");
                    commands.add("crafting[1]");
                    commands.add("crafting[2]");
                    commands.add("crafting[3]");
                    commands.add("crafting[4]");
                    commands.addAll(ConfigHandler.getConfigPath().getInvCleanProp().keySet());
                    // /itp takeoff PLAYER
                } else if (args[0].equalsIgnoreCase("takeoff") && PermissionsHandler.hasPermission(sender, "inventoryplus.command.takeoff.other")) {
                    try {
                        if (Bukkit.class.getMethod("getOnlinePlayers").getReturnType() == Collection.class) {
                            if (Bukkit.class.getMethod("getOnlinePlayers").getReturnType() == Collection.class) {
                                playersOnlineNew = ((Collection<?>) Bukkit.class.getMethod("getOnlinePlayers", new Class<?>[0]).invoke(null, new Object[0]));
                                for (Object objPlayer : playersOnlineNew) {
                                    commands.add(((Player) objPlayer).getName());
                                }
                            }
                        } else {
                            playersOnlineOld = ((Player[]) Bukkit.class.getMethod("getOnlinePlayers", new Class<?>[0]).invoke(null, new Object[0]));
                            for (Player player : playersOnlineOld) {
                                commands.add(player.getName());
                            }
                        }
                    } catch (Exception e) {
                        ServerHandler.sendDebugTrace(e);
                    }
                    // /itp takeoffslot SLOT
                } else if (args[0].equalsIgnoreCase("takeoffslot") && PermissionsHandler.hasPermission(sender, "inventoryplus.command.takeoffslot")) {
                    commands.addAll(ConfigHandler.getConfigPath().getInvTakeOffProp().keySet());
                    commands.add("head");
                    commands.add("chest");
                    commands.add("legs");
                    commands.add("feet");
                    commands.add("off_hand");
                }
                break;
            case 3:
                // /itp cleanslot SLOT false
                if (args[0].equalsIgnoreCase("cleanslot")) {
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.cleanslot")) {
                        commands.add("false");
                    }
                    // /itp cleanslot SLOT PLAYER
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.cleanslot.other")) {
                        try {
                            if (Bukkit.class.getMethod("getOnlinePlayers").getReturnType() == Collection.class) {
                                if (Bukkit.class.getMethod("getOnlinePlayers").getReturnType() == Collection.class) {
                                    playersOnlineNew = ((Collection<?>) Bukkit.class.getMethod("getOnlinePlayers", new Class<?>[0]).invoke(null, new Object[0]));
                                    for (Object objPlayer : playersOnlineNew) {
                                        commands.add(((Player) objPlayer).getName());
                                    }
                                }
                            } else {
                                playersOnlineOld = ((Player[]) Bukkit.class.getMethod("getOnlinePlayers", new Class<?>[0]).invoke(null, new Object[0]));
                                for (Player player : playersOnlineOld) {
                                    commands.add(player.getName());
                                }
                            }
                        } catch (Exception e) {
                            ServerHandler.sendDebugTrace(e);
                        }
                    }
                    // /itp takeoffslot SLOT false
                } else if (args[0].equalsIgnoreCase("takeoffslot")) {
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.takeoffslot")) {
                        commands.add("false");
                    }
                    // /itp takeoffslot SLOT PLAYER
                    if (PermissionsHandler.hasPermission(sender, "inventoryplus.command.takeoffslot.other")) {
                        try {
                            if (Bukkit.class.getMethod("getOnlinePlayers").getReturnType() == Collection.class) {
                                if (Bukkit.class.getMethod("getOnlinePlayers").getReturnType() == Collection.class) {
                                    playersOnlineNew = ((Collection<?>) Bukkit.class.getMethod("getOnlinePlayers", new Class<?>[0]).invoke(null, new Object[0]));
                                    for (Object objPlayer : playersOnlineNew) {
                                        commands.add(((Player) objPlayer).getName());
                                    }
                                }
                            } else {
                                playersOnlineOld = ((Player[]) Bukkit.class.getMethod("getOnlinePlayers", new Class<?>[0]).invoke(null, new Object[0]));
                                for (Player player : playersOnlineOld) {
                                    commands.add(player.getName());
                                }
                            }
                        } catch (Exception e) {
                            ServerHandler.sendDebugTrace(e);
                        }
                    }
                }
                break;
            case 4:
                // /itp cleanslot SLOT PLAYER false
                if (args[0].equalsIgnoreCase("cleanslot") && PermissionsHandler.hasPermission(sender, "inventoryplus.command.cleanslot.other")) {
                    commands.add("false");
                    // /itp takeoffslot SLOT PLAYER false
                } else if (args[0].equalsIgnoreCase("takeoffslot") && PermissionsHandler.hasPermission(sender, "inventoryplus.command.takeoffslot.other")) {
                    commands.add("false");
                }
                break;
        }
        StringUtil.copyPartialMatches(args[(args.length - 1)], commands, completions);
        Collections.sort(completions);
        return completions;
    }
}