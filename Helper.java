package gleb4uk.main;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Helper {
    static HashMap<Player, Long> delay = new HashMap();
    static boolean enableAntiSpam = Bukkit.getPluginManager().getPlugin("AntiSpam").getConfig().getBoolean("EnableAntiSpam");
    static int spamSeconds = Bukkit.getPluginManager().getPlugin("AntiSpam").getConfig().getInt("SpamSeconds");

    public Helper() {
    }
}
