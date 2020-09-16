package tk.thesenate.bedwars;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Bedwars extends JavaPlugin {

    private FileConfiguration config;
    private FileConfiguration data;
    BwMgr bwMgr = new BwMgr(this);

    public void onEnable() {
        if (!getDataFolder().exists()) getDataFolder().mkdir();
        createYaml();
        this.getCommand("bw").setExecutor(new BwCmd(bwMgr, this));
        getServer().getPluginManager().registerEvents(new BwListener(bwMgr, this), this);
    }

    public void onDisable() {}

    public FileConfiguration getConfig() {return this.config;}
    public FileConfiguration getData() {return this.data;}

    private void createYaml() {
        File configFile = new File(getDataFolder(), "config.yml");
        File dataFile = new File(getDataFolder(), "data.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            saveResource("data.yml", false);
        }

        config = new YamlConfiguration();
        data = new YamlConfiguration();

        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        try {
            data.load(dataFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }
}
