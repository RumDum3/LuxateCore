package me.rumdum.core.commands;

import me.rumdum.core.handler.Settings;
import me.rumdum.core.locale.Locale;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Request extends Command {

    public Request() {
        super("request");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        if (strings.length == 0) return;

        StringBuilder message = new StringBuilder();
        for (int i = 1; i < strings.length; i++) {
            message.append(strings[i]).append(" ");
        }

        Settings.sendMessageFromRequest(player, message.toString());
        player.sendMessage(new TextComponent(Locale.REQUEST_CMD.getMessage()));
    }
}
