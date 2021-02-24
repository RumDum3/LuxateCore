package me.rumdum.core.listeners;

import me.rumdum.core.handler.Settings;
import me.rumdum.core.locale.Locale;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Chat implements Listener {

    @EventHandler
    public void onToggleChat(net.md_5.bungee.api.event.ChatEvent event) {
        if (event.isCommand()) return;

        if (!(event.getSender() instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) event.getSender();

        if (player.hasPermission("core.bypass")) return;

        if (Settings.isGlobalChatMuted() || Settings.isServerChatMuted(player.getServer().getInfo().getName())) {
            event.setCancelled(true);
            player.sendMessage(new TextComponent(Locale.CHAT_MUTED.getMessage()));
        }
    }

    @EventHandler
    public void onSlowChat(net.md_5.bungee.api.event.ChatEvent event) {
        if (event.isCommand()) return;

        if (!(event.getSender() instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) event.getSender();

        if (player.hasPermission("core.bypass")) return;

        Server server = player.getServer();

        if (Settings.getServerSlowState(server.getInfo().getName()) == 0) return;

        if (Settings.getPlayerSlowState().contains(player)) {
            event.setCancelled(true);
            player.sendMessage(new TextComponent(Locale.SLOWED_CHAT.getMessage()));
        } else {
            Settings.getPlayerSlowState().add(player);
            Runnable task = () -> Settings.getPlayerSlowState().remove(player);
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            executor.schedule(task, Settings.getServerSlowState(server.getInfo().getName()), TimeUnit.MILLISECONDS);
        }
    }

    @EventHandler
    public void onStaffChat(net.md_5.bungee.api.event.ChatEvent event) {
        if (event.isCommand()) return;

        if (!(event.getSender() instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) event.getSender();

        if (Settings.getStaffChat().contains(player)) {
            event.setCancelled(true);
            Settings.sendMessageToStaffChat(player, event.getMessage());
        }
    }

}
