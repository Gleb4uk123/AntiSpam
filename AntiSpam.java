package gleb4uk.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiSpam extends JavaPlugin implements Listener {
    public FileConfiguration globalConfig = this.getConfig();

    public AntiSpam() {
    }

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getLogger().info(ChatColor.YELLOW + "AntiSpam is" + ChatColor.AQUA + "Enabled!");
        this.getLogger().info(ChatColor.DARK_GREEN + "This plugin was made by" + ChatColor.GREEN + "Gleb4uk");
        if (this.getConfig().getName() != "config.yml") {
            this.getConfig().options().copyDefaults(true);
            this.saveConfig();
        }

        this.getCommand("AntiSpam").setExecutor(new Commands());
        this.getServer().getPluginManager().registerEvents(new AntiSpamListener(), this);
    }

    public void onDisable() {
        this.getLogger().info(ChatColor.YELLOW + "AntiSpam is" + ChatColor.RED + "Disabled!");
    }

    public static void registerEvents(Plugin plugin, AntiSpamListener... listeners) {
        Listener[] var5 = listeners;
        int var4 = listeners.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            Listener listener = var5[var3];
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }

    }
}
