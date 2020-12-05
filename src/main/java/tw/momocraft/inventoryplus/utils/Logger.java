package tw.momocraft.inventoryplus.utils;

import tw.momocraft.inventoryplus.handlers.ConfigHandler;
import tw.momocraft.inventoryplus.handlers.ServerHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static final File inventoryFile = new File(tw.momocraft.inventoryplus.InventoryPlus.getInstance().getDataFolder().getPath() + "\\Logs\\latest.log");

    /**
     * To create lottery log file.
     */
    public void createCleanLog() {
        File folder = new File(tw.momocraft.inventoryplus.InventoryPlus.getInstance().getDataFolder().getPath() + "\\Logs");
        // Check log folder.
        if (!folder.exists()) {
            try {
                if (!folder.mkdir()) {
                    ServerHandler.sendConsoleMessage("&6Log: &fcreate folder &8\"&e" + folder.getName() + "&8\"  &c✘");
                }
            } catch (Exception e) {
                ServerHandler.sendDebugTrace(e);
            }
        }
        // Check log file.
        if (!inventoryFile.exists()) {
            try {
                if (!inventoryFile.createNewFile()) {
                    ServerHandler.sendConsoleMessage("&6Log: &fcreate log &8\"&e" + inventoryFile.getName() + ".log&8\"  &c✘");
                }
            } catch (Exception e) {
                ServerHandler.sendDebugTrace(e);
            }
        } else {
            if (ConfigHandler.getConfigPath().isInventoryLogNew()) {
                Date modifiedDate = new Date(inventoryFile.lastModified());
                Date currentDate = new Date();
                // Exist old log.
                if (modifiedDate.equals(currentDate)) {
                    // Rename old log file to "2020-11-10".
                    String logPath = inventoryFile.getParentFile().getPath() + "\\" + new SimpleDateFormat("yyyy-MM-dd").format(modifiedDate);
                    File renameFile = new File(logPath + ".log");
                    String logName;
                    int number = 1;
                    while (renameFile.exists()) {
                        logName = logPath + "-" + number;
                        renameFile = new File(logName + ".log");
                        number++;
                    }
                    try {
                        if (!inventoryFile.renameTo(renameFile)) {
                            ServerHandler.sendConsoleMessage("&6Log: &frename old log &8\"&e" + renameFile.getName() + "&8\"  &c✘");
                        }
                    } catch (Exception e) {
                        ServerHandler.sendDebugTrace(e);
                    }
                    if (ConfigHandler.getConfigPath().isInventoryLogZip()) {
                        try {
                            if (ConfigHandler.getZip().zipFiles(inventoryFile, null, null)) {
                                ServerHandler.sendConsoleMessage("&6Log: &fcompress log &8\"&e" + renameFile.getName() + ".zip &8\"  &c✘");
                            }
                        } catch (Exception e) {
                            ServerHandler.sendDebugTrace(e);
                        }
                    }
                    try {
                        if (!inventoryFile.createNewFile()) {
                            ServerHandler.sendConsoleMessage("&6Log: &fcreate new latest log &8\"&e" + inventoryFile.getName() + ".log&8\"  &c✘");
                        }
                    } catch (Exception e) {
                        ServerHandler.sendDebugTrace(e);
                    }
                }
            }
        }
    }
    /**
     * To add log to "LotteryLogs/latest.log".
     */
    public void addCleanLog(String message, boolean time) {
        message = message + "\n";
        if (time) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String date = dateFormat.format(new Date());
            message = "[" + date + "]: " + message;
        }
        if (!inventoryFile.exists()) {
            createCleanLog();
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(inventoryFile, true));
            bw.append(message);
            bw.close();
        } catch (IOException e) {
            ServerHandler.sendDebugTrace(e);
        }
    }
}
