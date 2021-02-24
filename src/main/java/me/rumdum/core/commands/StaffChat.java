package me.rumdum.core.commands;

import me.rumdum.core.handler.Settings;
import me.rumdum.core.locale.Locale;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class StaffChat extends Command {

    public StaffChat() {
        super("staffchat", "", "sc");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        if (!player.hasPermission("core.staffchat")) {
            player.sendMessage(new TextComponent(Locale.NO_PERMISSION.getMessage()));
            return;
        }

        if (strings.length == 0) {
            if (Settings.getStaffChat().contains(player)) {
                Settings.getStaffChat().remove(player);
                player.sendMessage(new TextComponent(Locale.STAFFCHAT_DISABLE_CMD.getMessage()));
            } else {
                Settings.getStaffChat().add(player);
                player.sendMessage(new TextComponent(Locale.STAFFCHAT_ENABLE_CMD.getMessage()));
            }
            return;
        }

        StringBuilder message = new StringBuilder();
        for (int i = 1; i <= strings.length; i++) {
            message.append(strings[i-1]).append(" ");
        }
        Settings.sendMessageToStaffChat(player, message.toString());
    }
}
