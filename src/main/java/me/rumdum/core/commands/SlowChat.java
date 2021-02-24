package me.rumdum.core.commands;

import me.rumdum.core.handler.Settings;
import me.rumdum.core.locale.Locale;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Command;

public class SlowChat extends Command {

    public SlowChat() {
        super("slowchat");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        if (!player.hasPermission("core.slowchat")) {
            player.sendMessage(new TextComponent(Locale.NO_PERMISSION.getMessage()));
            return;
        }

        long num = Long.parseLong(strings[0]) * 1000;

        switch (strings.length) {
            case 1:
                Server server = player.getServer();
                if (num == 0) {
                    Settings.setServerSlowState(server.getInfo().getName(), num);
                    player.sendMessage(new TextComponent(Locale.SLOWCHAT_SERVER_DISABLE_CMD.getMessage().replace("%server%", server.getInfo().getName())));
                } else {
                    Settings.setServerSlowState(server.getInfo().getName(), num);
                    player.sendMessage(new TextComponent(Locale.SLOWCHAT_SERVER_ENABLE_CMD.getMessage().replace("%time%", strings[0]).replace("%server%", server.getInfo().getName())));
                }
                break;
            case 2:
                String name = strings[1];
                if (!Settings.isValidServer(name)) {
                    player.sendMessage(new TextComponent(Locale.INVALID_SERVER_SPECIFIED.getMessage()));
                    return;
                }
                if (num == 0) {
                    Settings.setServerSlowState(name, num);
                    player.sendMessage(new TextComponent(Locale.SLOWCHAT_SERVER_DISABLE_CMD.getMessage().replace("%server%", name)));
                } else {
                    Settings.setServerSlowState(name, num);
                    player.sendMessage(new TextComponent(Locale.SLOWCHAT_SERVER_ENABLE_CMD.getMessage().replace("%time%", strings[0]).replace("%server%", name)));
                }
                break;
        }
    }
}
