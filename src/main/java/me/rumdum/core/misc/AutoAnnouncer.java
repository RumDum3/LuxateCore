package me.rumdum.core.misc;

import net.md_5.bungee.api.ProxyServer;

public class AutoAnnouncer extends Thread {

    public void run() {
        ProxyServer.getInstance().broadcast();
    }

}
