package com.relumcommunity.portalblockerfree.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class VersionChecker implements Listener {
	public String pluginVersion = Main.getInstance().getDescription().getVersion();
	public VersionChecker() {
		FileConfiguration main = Main.getInstance().getConfig();
		Logger mlog = Main.getInstance().getLogger();
		if (main.getBoolean("ConsoleCheckUpdate")) {
			try {
				HttpsURLConnection connection = (HttpsURLConnection)(new URL("https://relumcommunity.com/progetti/plugins/tpblockerfdree/version.json")).openConnection();
				String version = (new BufferedReader(new InputStreamReader(connection.getInputStream()))).readLine();
				if (!pluginVersion.equals(version)) {
					mlog.log(Level.WARNING, " ");
					mlog.log(Level.WARNING, "         -= PortalBlockerFree =-");
					mlog.log(Level.WARNING, "    You do not have the latest version!");
					mlog.log(Level.WARNING, " ");
					mlog.log(Level.WARNING, "Current: " + pluginVersion);
					mlog.log(Level.WARNING, "Latest: " + version);
					mlog.log(Level.WARNING, "Download: https://relumcommunity.com/progetti/plugins/tpblockerfree/redirect.html");
					mlog.log(Level.WARNING, " ");
					mlog.log(Level.WARNING, "Portal Blocker Premium is OUT!");
					mlog.log(Level.WARNING, "Download: https://relumcommunity.com/progetti/plugins/tpblockerpremium/redirect.html");
					mlog.log(Level.WARNING, " ");
				}
				else {
					mlog.log(Level.INFO, " ");
					mlog.log(Level.INFO, "         -= PortalBlockerFree =-");
					mlog.log(Level.INFO, "    You are running the latest version!");
					mlog.log(Level.INFO, " ");
					mlog.log(Level.INFO, "Portal Blocker Premium is OUT!");
					mlog.log(Level.INFO, "Download: https://relumcommunity.com/progetti/plugins/tpblockerpremium/redirect.html");
					mlog.log(Level.INFO, " ");
				}
			} catch (IOException e) {
				mlog.log(Level.SEVERE, " ");
				mlog.log(Level.SEVERE, "         -= PortalBlockerFree =-");
				mlog.log(Level.SEVERE, "Could not make connection to RelumCommunity.com!");
				mlog.log(Level.SEVERE, " ");
				mlog.log(Level.SEVERE, "Portal Blocker Premium is OUT!");
				mlog.log(Level.SEVERE, "Download: https://relumcommunity.com/progetti/plugins/tpblockerpremium/redirect.html");
				mlog.log(Level.SEVERE, " ");
			}
		}
	}
	@EventHandler
  	public void onJoin(PlayerJoinEvent j) {
		String permupdate = "portalblocker.update";
		FileConfiguration main = Main.getInstance().getConfig();
		String prefixs = main.getString("Prefix");
		String prefix = prefixs.replaceAll("&", "§");
		Player p = j.getPlayer();
		if (p.hasPermission(permupdate)) {
			String Msg = (prefix + ChatColor.YELLOW + " Portal Blocker Premium is OUT! Go check it out!");
			TextComponent MSg = new TextComponent(Msg);
			MSg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.AQUA + "Click to open the plugin page.")));
			MSg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL , "https://relumcommunity.com/progetti/plugins/tpblockerpremium/redirect.html"));
			((Player) p).spigot().sendMessage(MSg);
			if (main.getBoolean("CheckUpdate")) {
				try {
					HttpsURLConnection connection = (HttpsURLConnection)(new URL("https://relumcommunity.com/progetti/plugins/tpblockerfree/version.json")).openConnection();
					String version = (new BufferedReader(new InputStreamReader(connection.getInputStream()))).readLine();
					if (!pluginVersion.equals(version)) {
						String Message = (prefix + ChatColor.RED + " You are not in the latest version, please update the plugin from our plugin page");
						TextComponent MSG = new TextComponent(Message);
						MSG.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.AQUA + "Click to open the plugin page.")));
						MSG.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL , "https://relumcommunity.com/progetti/plugins/tpblockerfree/redirect.html"));
						((Player) p).spigot().sendMessage(MSG);
					}
				} catch (IOException e) {
					Logger mlog = Main.getInstance().getLogger();
					mlog.log(Level.SEVERE, " ");
					mlog.log(Level.SEVERE, "         -= PortalBlockerFree =-");
					mlog.log(Level.SEVERE, "Could not make connection to RelumCommunity.com!");
					mlog.log(Level.SEVERE, " ");
					mlog.log(Level.SEVERE, "Portal Blocker Premium is OUT!");
					mlog.log(Level.SEVERE, "Download: https://relumcommunity.com/progetti/plugins/tpblockerpremium/redirect.html");
					mlog.log(Level.SEVERE, " ");
				}
			}
		}
	}
}
