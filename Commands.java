package gleb4uk.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    public Commands() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("AntiSpam") && sender instanceof Player) {
            Player player = (Player)sender;
            if (!player.hasPermission("AntiSpam.reload")) {
                player.sendMessage("[" + ChatColor.YELLOW + "AntiSpam" + ChatColor.WHITE + "] " + ChatColor.RED + "You don't have permissions to use this plugin! ");
                return false;
            }

            if (args.length == 0) {
                player.sendMessage("[" + ChatColor.YELLOW + "AntiSpam" + ChatColor.WHITE + "] " + ChatColor.AQUA + "Developed by Gleb4uk");
                player.sendMessage(ChatColor.GREEN + "Use " + ChatColor.WHITE + "/AntiSpam help " + ChatColor.GREEN + "for a list of commands");
                return true;
            }

            if (args[0].equalsIgnoreCase("reload") && player.hasPermission("AntiSpam.Admin")) {
                Helper.enableAntiSpam = Bukkit.getPluginManager().getPlugin("AntiSpam").getConfig().getBoolean("EnableAntiSpam");
                Helper.spamSeconds = Bukkit.getPluginManager().getPlugin("AntiSpam").getConfig().getInt("SpamSeconds");
                Bukkit.getPluginManager().getPlugin("AntiSpam").reloadConfig();
                player.sendMessage("[" + ChatColor.YELLOW + "AntiSpam" + ChatColor.WHITE + "] " + ChatColor.GREEN + "Config was reloaded!");
                return true;
            }

            if (args[0].equalsIgnoreCase("settime") && player.hasPermission("AntiSpam.Admin")) {
                if (args.length == 2 && isIntParsable(args[1])) {
                    Bukkit.getPluginManager().getPlugin("AntiSpam").getConfig().set("SpamSeconds", Integer.parseInt(args[1]));
                    Bukkit.getPluginManager().getPlugin("AntiSpam").saveConfig();
                    player.sendMessage("[" + ChatColor.YELLOW + "AntiSpam" + ChatColor.WHITE + "] " + ChatColor.GREEN + "Time set to " + args[1]);
                    return true;
                }

                player.sendMessage("[" + ChatColor.YELLOW + "AntiSpam" + ChatColor.WHITE + "] " + ChatColor.RED + "/AntiSpam settime [time] ");
                return false;
            }

            if (args[0].equalsIgnoreCase("setEnable") && player.hasPermission("AntiSpam.Admin")) {
                boolean isEnabled = Bukkit.getPluginManager().getPlugin("AntiSpam").getConfig().getBoolean("EnableAntiSpam");
                if (args.length != 2) {
                    player.sendMessage("[" + ChatColor.YELLOW + "AntiSpam" + ChatColor.WHITE + "] " + ChatColor.RED + "/AntiSpam setenable <on/off> ");
                    return false;
                }

                if (isBoolParsable(args[1])) {
                    if (Boolean.parseBoolean(args[1]) == isEnabled) {
                        player.sendMessage("[" + ChatColor.YELLOW + "AntiSpam" + ChatColor.WHITE + "] " + ChatColor.AQUA + "Plugin already set enabled" + args[1]);
                        return false;
                    }

                    Bukkit.getPluginManager().getPlugin("AntiSpam").getConfig().set("EnableAntiSpam", Boolean.parseBoolean(args[1]));
                    Bukkit.getPluginManager().getPlugin("AntiSpam").saveConfig();
                    player.sendMessage("[" + ChatColor.YELLOW + "AntiSpam" + ChatColor.WHITE + "] " + ChatColor.AQUA + "You have set enable to " + args[1]);
                    return true;
                }
            }

            if (args[0].equalsIgnoreCase("help") && player.hasPermission("AntiSpam.Admin")) {
                player.sendMessage(ChatColor.LIGHT_PURPLE + "/AntiSpam " + ChatColor.GREEN + "List plugin info");
                player.sendMessage(ChatColor.LIGHT_PURPLE + "/AntiSpam reload " + ChatColor.GREEN + "Reload config.yml safely");
                player.sendMessage(ChatColor.LIGHT_PURPLE + "/AntiSpam settime " + ChatColor.GREEN + "Change spam time");
                player.sendMessage(ChatColor.LIGHT_PURPLE + "/AntiSpam setenable " + ChatColor.GREEN + "Enable or disable plugin");
                return true;
            }
        }

        return false;
    }

    public static boolean isIntParsable(String input) {
        boolean parsable = true;

        try {
            Integer.parseInt(input);
        } catch (NumberFormatException var3) {
            parsable = false;
        }

        return parsable;
    }

    public static boolean isBoolParsable(String input) {
        boolean parsable = true;

        try {
            Boolean.parseBoolean(input);
        } catch (Exception var3) {
            parsable = false;
        }

        return parsable;
    }
}
