package dev.thesourcecode.covid;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class CoronaEvents implements Listener {

    private CoronaVirus plugin;

    public CoronaEvents(CoronaVirus plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void moveEvent(PlayerMoveEvent event){
        if(plugin.isActivated){
        Player player = event.getPlayer();

        player.getNearbyEntities(6,6,6).forEach(entity -> {
            if(entity instanceof Player){
                Player nearby = (Player) entity;
                if(plugin.infected.contains(nearby.getUniqueId())) return;

                plugin.infected.add(nearby.getUniqueId());
                nearby.sendMessage(ChatColor.GREEN + "You have been infected...INFECT EVERYONE BEFORE YOU DIE!");
            }
        });
}   }


    @EventHandler
    public void playerDamage(EntityDamageByEntityEvent event){
        if(plugin.isActivated){
       if(event.getDamager() instanceof Player){
            if(event.getEntity() instanceof Player){
                Player damager = (Player) event.getDamager();
                Player damaged = (Player) event.getEntity();

                if(plugin.infected.contains(damager.getUniqueId())){
                    if(plugin.infected.contains(damaged.getUniqueId())) return;
                    damaged.sendMessage(ChatColor.GREEN + "You have been infected...INFECT EVERYONE BEFORE YOU DIE!");
                    plugin.infected.add(damaged.getUniqueId());
                }
            }}
        }
    }
}
