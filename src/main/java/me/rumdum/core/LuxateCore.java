package me.rumdum.core;

import me.rumdum.core.commands.*;
import me.rumdum.core.listeners.Chat;
import me.rumdum.core.handler.Settings;
import me.rumdum.core.listeners.JoinEvent;
import net.md_5.bungee.api.plugin.Plugin;

public final class LuxateCore extends Plugin {

    @Override
    public void onEnable() {
        Settings.init();
        this.getProxy().getPluginManager().registerCommand(this, new ToggleChat());
        this.getProxy().getPluginManager().registerCommand(this, new SlowChat());
        this.getProxy().getPluginManager().registerCommand(this, new Debug());
        this.getProxy().getPluginManager().registerCommand(this, new StaffChat());
        this.getProxy().getPluginManager().registerCommand(this, new Report());
        this.getProxy().getPluginManager().registerCommand(this, new Request());
        this.getProxy().getPluginManager().registerCommand(this, new Alerts());
        this.getProxy().getPluginManager().registerCommand(this, new List());
        this.getProxy().getPluginManager().registerListener(this, new Chat());
        this.getProxy().getPluginManager().registerListener(this, new JoinEvent());
    }

}
