package com.vaincecraft.portalblockerfree.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.vaincecraft.portalblockerfree.messages.LanguageFile;
import com.vaincecraft.portalblockerfree.messages.messages;
import com.vaincecraft.portalblockerfree.events.EGEvent;
import com.vaincecraft.portalblockerfree.events.EPEvent;
import com.vaincecraft.portalblockerfree.events.NPEvent;

public class Main extends JavaPlugin {
	public static Main plugin;
	public String pluginVersion = "V.1.3";
	public void onEnable() {
		plugin = this;
		
		String versioncheck = Bukkit.getVersion();
		plugin.getServer().getConsoleSender().sendMessage(versioncheck);
		String version[] = versioncheck.split(" ");
		if (Bukkit.getVersion().contains("Spigot")) {
			String servercheck = Bukkit.getVersion();
			String server[] = servercheck.split("-");
			plugin.getServer().getConsoleSender().sendMessage("[PortalBlockerFree INFO] " + ChatColor.YELLOW + "PortalBlockerFree using: " + server[1] + " version " + version[1] + version[2]);
		}
		else {
			plugin.getServer().getConsoleSender().sendMessage("[PortalBlockerFree INFO] " + ChatColor.YELLOW + "PortalBlockerFree using: " + Bukkit.getVersion() + ChatColor.RED + ("UNTESTED SERVER VERSION"));
		}
		
		Bukkit.getPluginManager().registerEvents(new NPEvent(), this);
		Bukkit.getPluginManager().registerEvents(new EPEvent(), this);
		if (Bukkit.getVersion().contains("1.9.2") || Bukkit.getVersion().contains("1.9.4") || Bukkit.getVersion().contains("1.10") || Bukkit.getVersion().contains("1.11") || Bukkit.getVersion().contains("1.12") || Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14")) {
			Bukkit.getPluginManager().registerEvents(new EGEvent(), this);
		}
		if (Bukkit.getVersion().contains("1.9")) {
			if (!Bukkit.getVersion().contains("1.9.2")) {
				if (!Bukkit.getVersion().contains("1.9.4")) {
					String servercheck = Bukkit.getVersion();
					String server[] = servercheck.split("-");
					plugin.getServer().getConsoleSender().sendMessage("[PortalBlockerFree ERROR] " + ChatColor.RED + "PortalBlockerFree using: " + server[1] + ChatColor.RED + ("EndGateway Option Disabled"));
				}
			}
		}
		plugin.getServer().getConsoleSender().sendMessage("[PortalBlockerFree] " + ChatColor.GREEN + "PortalBlockerFree has been enabled (" + pluginVersion + ")");
		
		saveDefaultConfig();
		new LanguageFile();
	 	new messages();
	}
	public void onDisable() {
		plugin.getServer().getConsoleSender().sendMessage("[PortalBlockerFree] " + ChatColor.RED + "PortalBlockerFree has been disabled (" + pluginVersion + ")");
	}
	public static Main getInstance() {
		return plugin;
	}
	public static FileConfiguration getLangFile() {
		messages lang = new messages();
		return lang.getFile();
	}
	public static String getLang() {
		String Lang = Main.getInstance().getConfig().getString("Language");
		return Lang;
	}
}
