package me.rumdum.core.listeners;

import me.rumdum.core.handler.Settings;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent event) {
        if (event.getPlayer().hasPermission("core.alerts")) {
            Settings.getAlertsState().add(event.getPlayer());
        }
    }

}
