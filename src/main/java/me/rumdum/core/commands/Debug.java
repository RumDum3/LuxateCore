package me.rumdum.core.commands;

import me.rumdum.core.handler.Settings;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Debug extends Command {

    public Debug() {
        super("debug");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        if (!player.hasPermission("core.debug")) return;

        Settings.debug(player);
    }
}
