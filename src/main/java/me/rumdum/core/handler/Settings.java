package me.rumdum.core.handler;

import me.rumdum.core.locale.Locale;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Settings {

    private static final HashMap<String, Boolean> serverChatState = new HashMap<>();
    private static boolean globalChatState;

    private static final HashMap<String, Long> serverSlowState = new HashMap<>();
    private static final List<ProxiedPlayer> playerSlowState = new ArrayList<>();

    private static final List<ProxiedPlayer> staffChat = new ArrayList<>();

    private static final List<ProxiedPlayer> alertsState = new ArrayList<>();

    public static boolean isValidServer(String name) {
        for (String server : ProxyServer.getInstance().getServersCopy().keySet()) {
            if (name.equalsIgnoreCase(server)) {
                return true;
            }
        }
        return false;
    }

    public static void init() {
        for (String server : ProxyServer.getInstance().getServersCopy().keySet()) {
            serverChatState.put(server, false);
            serverSlowState.put(server, 0L);
        }
    }

    public static Boolean isGlobalChatMuted() {
        return globalChatState;
    }

    public static List<ProxiedPlayer> getAlertsState() { return alertsState; }

    public static Boolean isServerChatMuted(String server) {
        return serverChatState.getOrDefault(server, false);
    }

    public static void toggleGlobalChatState() {
        globalChatState = !globalChatState;
    }

    public static void toggleChatState(String server) {
        if (!serverChatState.containsKey(server)) return;
        if (serverChatState.get(server)) {
            serverChatState.remove(server);
            serverChatState.put(server, false);
        } else {
            serverChatState.remove(server);
            serverChatState.put(server, true);
        }
    }

    public static long getServerSlowState(String server) {
        if (!serverSlowState.containsKey(server)) return 0;
        return serverSlowState.get(server);
    }

    public static void setServerSlowState(String server, long time) {
        if (!serverSlowState.containsKey(server)) return;
        serverSlowState.remove(server);
        serverSlowState.put(server, time);
    }

    public static List<ProxiedPlayer> getPlayerSlowState() { return playerSlowState; }

    public static List<ProxiedPlayer> getStaffChat() { return staffChat; }

    public static void sendMessageToStaffChat(ProxiedPlayer player, String message) {
        for (ProxiedPlayer target : alertsState) {
            if (target.hasPermission("core.staffchat")) {
                target.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
                        "&e[" + player.getServer().getInfo().getName() + "]" +
                                " " + "&c" + player.getName() + ":" + " " + message)));
            }
        }
    }

    public static void sendMessageFromReport(ProxiedPlayer player, ProxiedPlayer target, String message) {
        for (ProxiedPlayer staff : alertsState) {
            if (staff.hasPermission("core.alerts")) {
                staff.sendMessage(new TextComponent(Locale.REPORT_STAFF_OUTPUT.getMessage().replace("%player%",
                        player.getName()).replace("%target%",
                        target.getName()).replace("%reason%", message)));
            }
        }
    }

    public static void sendMessageFromRequest(ProxiedPlayer player, String message) {
        for (ProxiedPlayer staff : alertsState) {
            if (staff.hasPermission("core.alerts")) {
                staff.sendMessage(new TextComponent(Locale.REQUEST_STAFF_OUTPUT.getMessage().
                        replace("%player%", player.getName()).replace("%reason%", message)));
            }
        }
    }

    public static String convertStaffToString() {
        StringBuilder serializer = new StringBuilder();
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            if (alertsState.contains(player)) {
                serializer.append(player.getName()).append(" ");
            }
        }
        return serializer.toString();
    }

    public static void debug(ProxiedPlayer player) {
        for (String srv : serverChatState.keySet()) {
            player.sendMessage(new TextComponent(srv + " -> " + serverChatState.get(srv)));
        }
        player.sendMessage(new TextComponent("global -> " + isGlobalChatMuted()));
    }
}
