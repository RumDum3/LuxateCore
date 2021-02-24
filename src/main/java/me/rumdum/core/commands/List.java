package me.rumdum.core.commands;

import me.rumdum.core.handler.Settings;
import me.rumdum.core.locale.Locale;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class List extends Command {

    public List() {
        super("list");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        player.sendMessage(new TextComponent(Locale.LIST.getMessage().replace("%staff%",
                Settings.convertStaffToString()).replace("%online%",
                String.valueOf(ProxyServer.getInstance().getPlayers().size()))));
    }
}
