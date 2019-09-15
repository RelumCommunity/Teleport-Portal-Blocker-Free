package com.vaincecraft.portalblockerfree.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.vaincecraft.portalblockerfree.messages.LanguageFile;
import com.vaincecraft.portalblockerfree.messages.messages;
import com.vaincecraft.portalblockerfree.events.EGEvent;
import com.vaincecraft.portalblockerfree.events.EPEvent;
import com.vaincecraft.portalblockerfree.events.NPEvent;

public class Main extends JavaPlugin {
	public static Main plugin;
	public String pluginVersion = "V.1.4";
	public void onEnable() {
		plugin = this;
		String ver = Bukkit.getVersion();
		ConsoleCommandSender cmsg = plugin.getServer().getConsoleSender();
		PluginManager bpm = Bukkit.getPluginManager();
		
		String versioncheck = ver;
		String version[] = versioncheck.split(" ");
		String servercheck = ver;
		String server[] = servercheck.split("-");
		if (ver.contains("Spigot")) {
			cmsg.sendMessage("[PortalBlockerFree INFO] " + ChatColor.YELLOW + "PortalBlockerFree using: " + server[1] + " version " + version[1] + version[2]);
		}
		else {
			cmsg.sendMessage("[PortalBlockerFree INFO] " + ChatColor.YELLOW + "PortalBlockerFree using: " + ver + ChatColor.RED + ("UNTESTED SERVER VERSION"));
		}
		bpm.registerEvents(new NPEvent(), this);
		bpm.registerEvents(new EPEvent(), this);
		if (ver.contains("1.9") || ver.contains("1.10") || ver.contains("1.11") || ver.contains("1.12") || ver.contains("1.13") || ver.contains("1.14")) {
			bpm.registerEvents(new EGEvent(), this);
		}
		if (ver.contains("1.9")) {
			cmsg.sendMessage("[PortalBlockerFree ERROR] " + ChatColor.RED + "PortalBlockerFree using: " + server[1] + " version " + version[1] + version[2] + ChatColor.RED + ("(NetherPortal Option Should Not Work Due To Spigot Issue)"));
		}
		cmsg.sendMessage("[PortalBlockerFree] " + ChatColor.GREEN + "PortalBlockerFree has been enabled (" + pluginVersion + ")");
		
		saveDefaultConfig();
		new LanguageFile();
	 	new messages();
	}
	public void onDisable() {
		ConsoleCommandSender cmsg = plugin.getServer().getConsoleSender();
		cmsg.sendMessage("[PortalBlockerFree] " + ChatColor.RED + "PortalBlockerFree has been disabled (" + pluginVersion + ")");
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
