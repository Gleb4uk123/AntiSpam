package gleb4uk.main;

import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AntiSpamListener implements Listener {

	public AntiSpamListener() {
    }

    @EventHandler
    public void playerChat(AsyncPlayerChatEvent event) {
        if (Helper.enableAntiSpam) {
            Player player = event.getPlayer();
            if (!player.hasPermission("AntiSpam.Exempt")) {
                int spamSeconds = Bukkit.getPluginManager().getPlugin("AntiSpam").getConfig().getInt("spamSeconds");
                if (!Helper.delay.containsKey(player)) {
                    Helper.delay.put(player, System.currentTimeMillis());
                } else if (System.currentTimeMillis() - (Long)Helper.delay.get(player) >= (long)(spamSeconds * 1000)) {
                    Helper.delay.remove(player);
                } else {
                    player.sendMessage("[" + ChatColor.YELLOW + "AntiSpam" + ChatColor.WHITE + "] " + ChatColor.AQUA + "Please wait " + ChatColor.YELLOW + spamSeconds + ChatColor.AQUA + " second before sending a new message.");
                   Iterator var5 = Bukkit.getServer().getOnlinePlayers().iterator();

                    while(true) {
                        Player p;
                        do {
                            if (!var5.hasNext()) {
                                event.setCancelled(true);
                                return;
                            }

                            p = (Player)var5.next();
                        } while(!p.isOp() && !p.hasPermission("AntiSpam.Staff"));

                        p.sendMessage(ChatColor.RED + "ATTENTION STAFF: " + ChatColor.AQUA + "Player " + ChatColor.YELLOW + player.getName() + ChatColor.AQUA + " is spamming");
                    }
                }
            }
        }

    }
}
