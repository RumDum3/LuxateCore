package me.rumdum.core.commands;

import me.rumdum.core.handler.Settings;
import me.rumdum.core.locale.Locale;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Report extends Command {

    public Report() {
        super("report");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        if (strings.length == 1) return;

        ProxiedPlayer target = null;

        try {
            target = ProxyServer.getInstance().getPlayer(strings[0]);
        } catch (Exception ignored) {}

        if (target == null) {
            player.sendMessage(new TextComponent(Locale.PLAYER_NOT_FOUND.getMessage()));
            return;
        }

        if (!target.isConnected()) return;

        StringBuilder message = new StringBuilder();
        for (int i = 1; i < strings.length; i++) {
            message.append(strings[i]).append(" ");
        }

        Settings.sendMessageFromReport(player, target, message.toString());
        player.sendMessage(new TextComponent(Locale.REPORT_CMD.getMessage().replace("%target%", target.getName())));
    }
}
