package dev.thesourcecode.covid;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoronaCommand implements CommandExecutor {

    private CoronaVirus plugin;


    public CoronaCommand(CoronaVirus plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender.isOp()) {
        if (command.getName().equalsIgnoreCase("cv")) {
                plugin.isActivated = true;
                int counter = 0;
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    if (counter <= 3) {
                        plugin.infected.add(player.getUniqueId());
                        player.sendMessage(ChatColor.GREEN + "You have been infected...INFECT EVERYONE BEFORE YOU DIE!");
                        counter++;
                    }else{
                        break;
                    }
                }

                Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + "**ALERT**" +
                ChatColor.RED + " 3 players have tested positive for the Corona Virus. Please stand clear of the players!");
            }
        }
        return true;
    }
}
