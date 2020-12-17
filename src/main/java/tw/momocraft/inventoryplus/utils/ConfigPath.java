package tw.momocraft.inventoryplus.utils;

import org.bukkit.configuration.ConfigurationSection;
import tw.momocraft.inventoryplus.handlers.ConfigHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConfigPath {
    public ConfigPath() {
        setUp();
    }

    //  ============================================== //
    //         CleanSlot Settings                      //
    //  ============================================== //
    private boolean inventory;
    private boolean inventoryLog;
    private boolean inventoryLogNew;
    private boolean inventoryLogZip;
    private boolean inventoryLogClean;
    private boolean inventoryLogTakeOff;
    private boolean inventoryLogBinding;
    private boolean inventoryClean;
    private boolean inventoryTakeOff;
    private boolean inventoryBinding;
    private final Map<String, List<String>> invCleanProp = new HashMap<>();
    private final Map<String, List<String>> invTakeOffProp = new HashMap<>();
    private final Map<String, List<String>> invBindingProp = new HashMap<>();

    //  ============================================== //
    //         Setup all configuration.                //
    //  ============================================== //
    private void setUp() {
        setupCleanSlot();
    }

    //  ============================================== //
    //         CleanSlot Settings                      //
    //  ============================================== //
    private void setupCleanSlot() {
        inventory = ConfigHandler.getConfig("config.yml").getBoolean("Player.Enable");
        inventoryLog = ConfigHandler.getConfig("config.yml").getBoolean("Player.Settings.Log.Enable");
        inventoryLogNew = ConfigHandler.getConfig("config.yml").getBoolean("Player.Settings.Log.New-File");
        inventoryLogZip = ConfigHandler.getConfig("config.yml").getBoolean("Player.Settings.Log.To-Zip");
        if (inventoryLog) {
            inventoryLogClean = ConfigHandler.getConfig("config.yml").getBoolean("Player.Clean.Settings.Log");
            inventoryLogTakeOff = ConfigHandler.getConfig("config.yml").getBoolean("Player.TakeOff.Settings.Log");
            inventoryLogBinding = ConfigHandler.getConfig("config.yml").getBoolean("Player.Binding-Curse.Settings.Log");
        }
        inventoryClean = ConfigHandler.getConfig("config.yml").getBoolean("Player.Clean.Enable");
        ConfigurationSection cleanConfig = ConfigHandler.getConfig("config.yml").getConfigurationSection("Player.Clean.Groups");
        if (cleanConfig != null) {
            for (String group : cleanConfig.getKeys(false)) {
                if (Utils.isEnable(ConfigHandler.getConfig("config.yml").getString("Player.Clean.Groups." + group + ".Enable"), true)) {
                    invCleanProp.put(group.toLowerCase(), ConfigHandler.getConfig("config.yml").getStringList("Player.Clean.Groups." + group + ".Slots"));
                }
            }
        }
        inventoryTakeOff = ConfigHandler.getConfig("config.yml").getBoolean("Player.TakeOff.Enable");
        ConfigurationSection takeOffConfig = ConfigHandler.getConfig("config.yml").getConfigurationSection("Player.TakeOff.Groups");
        if (takeOffConfig != null) {
            for (String group : takeOffConfig.getKeys(false)) {
                if (Utils.isEnable(ConfigHandler.getConfig("config.yml").getString("Player.TakeOff.Groups." + group + ".Enable"), true)) {
                    invTakeOffProp.put(group.toLowerCase(), ConfigHandler.getConfig("config.yml").getStringList("Player.TakeOff.Groups." + group + ".Slots"));
                }
            }
        }
        inventoryBinding = ConfigHandler.getConfig("config.yml").getBoolean("Player.Binding-Curse.Enable");
        ConfigurationSection bindingConfig = ConfigHandler.getConfig("config.yml").getConfigurationSection("Player.Binding-Curse.Groups");
        if (bindingConfig != null) {
            for (String group : bindingConfig.getKeys(false)) {
                if (Utils.isEnable(ConfigHandler.getConfig("config.yml").getString("Player.Binding-Curse.Groups." + group + ".Enable"), true)) {
                    invBindingProp.put(group.toLowerCase(), ConfigHandler.getConfig("config.yml").getStringList("Player.Binding-Curse.Groups." + group + ".Slots"));
                }
            }
        }
    }

    //  ============================================== //
    //         CleanSlot Settings                      //
    //  ============================================== //
    public boolean isInventory() {
        return inventory;
    }

    public boolean isInventoryLog() {
        return inventoryLog;
    }

    public boolean isInventoryLogNew() {
        return inventoryLogNew;
    }

    public boolean isInventoryLogZip() {
        return inventoryLogZip;
    }

    public boolean isInventoryLogClean() {
        return inventoryLogClean;
    }

    public boolean isInventoryLogTakeOff() {
        return inventoryLogTakeOff;
    }

    public boolean isInventoryLogBinding() {
        return inventoryLogBinding;
    }

    public boolean isInventoryClean() {
        return inventoryClean;
    }

    public boolean isInventoryTakeOff() {
        return inventoryTakeOff;
    }

    public boolean isInventoryBinding() {
        return inventoryBinding;
    }

    public Map<String, List<String>> getInvCleanProp() {
        return invCleanProp;
    }

    public Map<String, List<String>> getInvTakeOffProp() {
        return invTakeOffProp;
    }

    public Map<String, List<String>> getInvBindingProp() {
        return invBindingProp;
    }
}
