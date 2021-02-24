package me.rumdum.core.commands;

import me.rumdum.core.handler.Settings;
import me.rumdum.core.locale.Locale;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Command;

public class ToggleChat extends Command {

    public ToggleChat() {
        super("togglechat");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        if (!player.hasPermission("core.togglechat")) {
            player.sendMessage(new TextComponent(Locale.NO_PERMISSION.getMessage()));
            return;
        }

        switch (strings.length) {
            case 0:
                Server server = player.getServer();
                if (Settings.isServerChatMuted(server.getInfo().getName())) {
                    player.sendMessage(new TextComponent(Locale.TOGGLECHAT_SERVER_ENABLE_CMD.getMessage().replace("%server%", server.getInfo().getName())));
                } else {
                    player.sendMessage(new TextComponent(Locale.TOGGLECHAT_SERVER_DISABLE_CMD.getMessage().replace("%server%", server.getInfo().getName())));
                }
                Settings.toggleChatState(server.getInfo().getName());
                break;
            case 1:
                String name = strings[0];
                if (name.equalsIgnoreCase("global")) {
                    if (Settings.isGlobalChatMuted()) {
                        player.sendMessage(new TextComponent(Locale.TOGGLECHAT_GLOBAL_ENABLE_CMD.getMessage()));
                    } else {
                        player.sendMessage(new TextComponent(Locale.TOGGLECHAT_GLOBAL_DISABLE_CMD.getMessage()));
                    }
                    Settings.toggleGlobalChatState();
                } else {
                    if (Settings.isServerChatMuted(name)) {
                        player.sendMessage(new TextComponent(Locale.TOGGLECHAT_SERVER_ENABLE_CMD.getMessage().replace("%server%", name)));
                    } else {
                        player.sendMessage(new TextComponent(Locale.TOGGLECHAT_SERVER_DISABLE_CMD.getMessage().replace("%server%", name)));
                    }
                    Settings.toggleChatState(name);
                }
                break;
        }
    }
}
