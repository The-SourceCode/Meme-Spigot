package dev.thesourcecode.covid;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashSet;
import java.util.UUID;

public class CoronaVirus extends JavaPlugin {

    public HashSet<UUID> infected = new HashSet<>();
    public HashSet<UUID> isPoisoned = new HashSet<>();
    public boolean isActivated = false;

    @Override
    public void onEnable() {
        super.onEnable();

        this.getCommand("cv").setExecutor(new CoronaCommand(this));
        this.getServer().getPluginManager().registerEvents(new CoronaEvents(this), this);


        BukkitTask scheduler = Bukkit.getServer().getScheduler().runTaskTimer(this, () -> {
            infected.forEach(uuid -> {
                if(isActivated) {
                    Player player = Bukkit.getPlayer(uuid);
                    if (player == null) return;
                    if (!isPoisoned.contains(uuid)) {
                        isPoisoned.add(uuid);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100000000, 0));
                    }

                    if (player.getHealth() <= 1) {
                        if (player.getDisplayName().equalsIgnoreCase("malikdbuseck")) return;
                        player.sendMessage(ChatColor.GREEN + "You have died...to the RONA!");
                    }
                }
            });
        }, 0, 20);

    }

    @Override
    public void onDisable() {
        super.onDisable();
        infected.clear();
        isPoisoned.clear();
    }

}
