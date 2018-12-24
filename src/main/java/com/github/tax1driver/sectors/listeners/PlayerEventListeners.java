package com.github.tax1driver.sectors.listeners;

import com.github.tax1driver.sectors.objects.LocalSector;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEventListeners implements Listener {


    @EventHandler(priority=EventPriority.LOW)
    public void onJoin(PlayerJoinEvent e) {
        LocalSector localSector = LocalSector.getLocalSector();
    }
}
