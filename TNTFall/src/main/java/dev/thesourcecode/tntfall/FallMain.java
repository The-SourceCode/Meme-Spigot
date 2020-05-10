package dev.thesourcecode.tntfall;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;


public class FallMain extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void blockBreakEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        int random = new Random().nextInt(11);

        if (random == 0) {
            Entity entity = player.getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.PRIMED_TNT);
            this.getServer().broadcastMessage(ChatColor.GOLD  + player.getName() + ChatColor.YELLOW + " has primed TNT :)");
        }
    }

}
