package me.rumdum.core.locale;

import net.md_5.bungee.api.ChatColor;

public enum Locale {

    PREFIX("&b[CORE]"),

    TOGGLECHAT_GLOBAL_ENABLE_CMD("&aYou have enabled global chat"),
    TOGGLECHAT_GLOBAL_DISABLE_CMD("&cYou have disabled global chat"),
    TOGGLECHAT_SERVER_ENABLE_CMD("&fYou have &aenabled &fchat in server &e%server%"),
    TOGGLECHAT_SERVER_DISABLE_CMD("&fYou have &cdisabled &fchat in server &e%server%"),

    CHAT_MUTED("The chat is currently muted"),

    SLOWCHAT_SERVER_ENABLE_CMD("&aChat has been delayed by %time% seconds in &e%server%"),
    SLOWCHAT_SERVER_DISABLE_CMD("&cAll chat restrictions have been lifted in &e%server%"),

    SLOWED_CHAT("&cThe chat is currently in slow mode, please wait before writing anything else"),

    STAFFCHAT_ENABLE_CMD("&aYou have enabled staff chat"),
    STAFFCHAT_DISABLE_CMD("&cYou have disabled staff chat"),

    REPORT_CMD("Successfully filled %target%'s report. All online staff have been " +
            "notified and will respond accordingly"),
    REPORT_STAFF_OUTPUT("&e(!) &c%player% &fhas reported &c%target% &ffor &e%reason%"),

    REQUEST_CMD("Thank you, we have received your request and have notified all available staff"),
    REQUEST_STAFF_OUTPUT("&e(!) &c%player% &fhas requested help with &e%reason%"),

    STAFF_ALERTS_ENABLED("&aYou have enabled staff alerts"),
    STAFF_ALERTS_DISABLED("&cYou have disabled staff alerts"),

    LIST("&4&lSTAFF: &7%staff%" + "\n" +
            "&7&oThere are currently &e%online%/400 &7&oplayers connected"),

    BLACKLIST_PLAYER("&e%player% &chas been blacklisted from the network"),
    BLACKLIST_SCREEN("You have been blacklisted from the network" + "\n" + "\n" +
            "Reason: %reason%" + "\n" +
            "If you feel like the ban was not fair, appeal on our forums: www.luxate.com"),
    BLACKLIST_STAFF_OUTPUT("You have successfully blacklisted %player% for %reason%"),
    BAN_PLAYER("&e%player% &chas been banned for %reason%" + "\n" +
            "Reason: %reason%" + "\n" +
            "&7&oExpires in: &6%time%" + "\n" +
            "If you feel like the ban was not fair, appeal on our forums: www.luxate.com"),
    BAN_STAFF_OUTPUT("You have successfully banned %player% for %reason%" + "\n" +
            "Expires in: %time%"),

    PLAYER_NOT_FOUND("&cThe player you specified is not found"),
    INVALID_SERVER_SPECIFIED("&cThe server you specified does not exist"),
    NO_PERMISSION("&cYou do not have permission to execute that command");

    private final String message;

    Locale(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ChatColor.translateAlternateColorCodes('&', PREFIX.message + " " + this.message);
    }

}
