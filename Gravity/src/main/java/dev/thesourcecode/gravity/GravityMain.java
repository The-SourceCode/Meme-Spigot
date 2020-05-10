package dev.thesourcecode.gravity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GravityMain extends JavaPlugin implements Listener {

    private Map<UUID, Location> lastLocation = new HashMap<UUID, Location>();

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        locationChecker();
    }

    @Override
    public void onDisable() {
        lastLocation.clear();
    }

    private void locationChecker() {


        new BukkitRunnable() {
            public void run() {
                if (Bukkit.getOnlinePlayers().isEmpty()) return;
                for (Player player : Bukkit.getOnlinePlayers()) {
                    locationMapSetter(player);
                }
            }
        }.runTaskTimer(this, 0, 100);

    }


    private void locationMapSetter(Player player) {
        UUID uuid = player.getUniqueId();
        Location location = player.getLocation();

        if (!lastLocation.containsKey(uuid)) {
            lastLocation.put(uuid, location);
            System.out.println("Cords: " + location);
            return;
        }


        double x = lastLocation.get(uuid).getBlockX();
        double y = lastLocation.get(uuid).getBlockY();
        double z = lastLocation.get(uuid).getBlockZ();

        if (location.getBlockX() != x || location.getBlockY() != y || location.getBlockZ() != z) {
            lastLocation.put(uuid, location);
        } else {
            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + player.getName() + " has been launched into the air :)");
            player.playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
            player.setVelocity(player.getVelocity().setY(200));
            lastLocation.put(uuid, location);
        }
    }

}
