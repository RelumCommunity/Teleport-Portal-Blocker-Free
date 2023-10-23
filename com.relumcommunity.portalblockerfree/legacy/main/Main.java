package com.relumcommunity.portalblockerfree.main;

import com.relumcommunity.portalblockerfree.commands.Commands;
import com.relumcommunity.portalblockerfree.data.EGBlocksBreak;
import com.relumcommunity.portalblockerfree.data.EGBlocksPlace;
import com.relumcommunity.portalblockerfree.data.EPBlocksBreak;
import com.relumcommunity.portalblockerfree.data.EPBlocksPlace;
import com.relumcommunity.portalblockerfree.data.NPBlocksBreak;
import com.relumcommunity.portalblockerfree.data.NPBlocksPlace;
import com.relumcommunity.portalblockerfree.events.BreakPlace;
import com.relumcommunity.portalblockerfree.events.EGEvent;
import com.relumcommunity.portalblockerfree.events.EPEvent;
import com.relumcommunity.portalblockerfree.events.NPEvent;
import com.relumcommunity.portalblockerfree.messages.LanguageFile;
import com.relumcommunity.portalblockerfree.messages.messages;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static Main plugin;
	public String pluginVersion = "V.2.3";
	public static EGBlocksBreak egblocksbreak;
	public static EGBlocksPlace egblocksplace;
	public static EPBlocksBreak epblocksbreak;
	public static EPBlocksPlace epblocksplace;
	public static NPBlocksBreak npblocksbreak;
	public static NPBlocksPlace npblocksplace;
	public void onEnable() {
		plugin = this;
		String ver = Bukkit.getVersion();
		ConsoleCommandSender cmsg = plugin.getServer().getConsoleSender();
		PluginManager bpm = Bukkit.getPluginManager();
		File egblockbreak = new File(getDataFolder() + "/egblocksbreak.sqlite");
		File egblockplace = new File(getDataFolder() + "/egblocksplace.sqlite");
		File epblockbreak = new File(getDataFolder() + "/epblocksbreak.sqlite");
		File epblockplace = new File(getDataFolder() + "/epblocksplace.sqlite");
		File npblockbreak = new File(getDataFolder() + "/npblocksbreak.sqlite");
		File npblockplace = new File(getDataFolder() + "/npblocksplace.sqlite");
		String versioncheck = ver;
		String[] version = versioncheck.split(" ");
		String servercheck = ver;
		String[] server = servercheck.split("-");
		if (ver.contains("Spigot")) {
			cmsg.sendMessage("[PortalBlockerFree INFO] " + ChatColor.YELLOW + "PortalBlockerFree using: " + server[1] + " version " + version[1] + version[2]);
		} else {
			cmsg.sendMessage("[PortalBlockerFree INFO] " + ChatColor.YELLOW + "PortalBlockerFree using: " + ver + ChatColor.RED + " UNTESTED SERVER VERSION");
		}
		bpm.registerEvents(new VersionChecker(), this);
		bpm.registerEvents(new BreakPlace(), this);
		bpm.registerEvents(new NPEvent(), this);
		bpm.registerEvents(new EPEvent(), this);
		if (ver.contains("1.9") || ver.contains("1.10") || ver.contains("1.11")) {
			bpm.registerEvents(new EGEvent(), this);
		}
		getCommand("portalblocker").setExecutor(new Commands());
		if (ver.contains("1.9")) {
			cmsg.sendMessage("[PortalBlockerFree ERROR] " + ChatColor.RED + "PortalBlockerFree using: " + server[1] + " version " + version[1] + version[2] + ChatColor.RED + "(NetherPortal Option Should Not Work Due To Spigot Issue)");
		}
		cmsg.sendMessage("[PortalBlockerFree] " + ChatColor.GREEN + "PortalBlockerFree has been enabled (" + pluginVersion + ")");
		saveDefaultConfig();
		if (!egblockbreak.exists()) {
			try {
				egblockbreak.createNewFile();
			} catch (IOException l) {
				l.printStackTrace();
			}
		}
		try {
			egblocksbreak = new EGBlocksBreak(egblockbreak.getAbsolutePath());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (!egblockplace.exists()) {
			try {
				egblockplace.createNewFile();
			} catch (IOException l) {
				l.printStackTrace();
			}
		}
		try {
			egblocksplace = new EGBlocksPlace(egblockplace.getAbsolutePath());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (!epblockbreak.exists()) {
			try {
				epblockbreak.createNewFile();
			} catch (IOException l) {
				l.printStackTrace();
			}
		}
		try {
			epblocksbreak = new EPBlocksBreak(epblockbreak.getAbsolutePath());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (!epblockplace.exists()) {
			try {
				epblockplace.createNewFile();
			} catch (IOException l) {
				l.printStackTrace();
			}
		}
		try {
			epblocksplace = new EPBlocksPlace(epblockplace.getAbsolutePath());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (!npblockbreak.exists()) {
			try {
				npblockbreak.createNewFile();
			} catch (IOException l) {
				l.printStackTrace();
			}
		}
		try {
			npblocksbreak = new NPBlocksBreak(npblockbreak.getAbsolutePath());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (!npblockplace.exists()) {
			try {
				npblockplace.createNewFile();
			} catch (IOException l) {
				l.printStackTrace();
			}
		}
		try {
			npblocksplace = new NPBlocksPlace(npblockplace.getAbsolutePath());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
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
		String Lang = getInstance().getConfig().getString("Language");
		return Lang;
	}
}
