package me.rumdum.core.commands;

import me.rumdum.core.handler.Settings;
import me.rumdum.core.locale.Locale;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Alerts extends Command {

    public Alerts() {
        super("alerts");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        if (!player.hasPermission("core.alerts")) {
            player.sendMessage(new TextComponent(Locale.NO_PERMISSION.getMessage()));
            return;
        }

        if (Settings.getAlertsState().contains(player)) {
            Settings.getAlertsState().remove(player);
            player.sendMessage(new TextComponent(Locale.STAFF_ALERTS_DISABLED.getMessage()));
        } else {
            Settings.getAlertsState().add(player);
            player.sendMessage(new TextComponent(Locale.STAFF_ALERTS_ENABLED.getMessage()));
        }
    }
}
