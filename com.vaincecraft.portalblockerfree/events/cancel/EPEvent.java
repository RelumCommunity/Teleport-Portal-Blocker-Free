package com.vaincecraft.portalblockerfree.events;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.vaincecraft.portalblockerfree.main.Main;

@SuppressWarnings("deprecation")
public class EPEvent implements Listener {
	@EventHandler
	public void PortalEnter (PlayerPortalEvent i) {
		if (i.getCause().equals(TeleportCause.END_PORTAL)) {
			Player p = i.getPlayer();
			String ver = Bukkit.getVersion();
			FileConfiguration main = Main.getInstance().getConfig();
			String prefixs = main.getString("Prefix");
			String prefix = prefixs.replaceAll("&", "§");
			String endportale = main.getString("EndPortal.QuestName");
			String endpe = endportale.replaceAll("&", "§");
			String endportalerror = Main.getLangFile().getString("error.endportal");
			String epe = endportalerror.replaceAll("&", "§");
			String colormsg1 = epe.replaceAll("%endportalquest%", endpe);
			String endportalpermblockmessage = Main.getLangFile().getString("permblockteleport.endportal");
			String colormsg2 = endportalpermblockmessage.replaceAll("&", "§");
			if (!main.getBoolean("PermBlockTeleports.EPBlock")) {
				if (main.getBoolean("Teleports.EPBlock")) {
					if (ver.contains("1.12") || ver.contains("1.13") || ver.contains("1.14")){
						if (hasAdvancement(p)) {
							return;
						}
						else {
							i.setCancelled(true);
							if (main.getBoolean("EndPortal.ErrorMessage")) {
								p.sendMessage(prefix + colormsg1);
							}
						}
					}
					else {
						String ach = main.getString("EndPortal.Achievement");
						if (p.hasAchievement(Achievement.valueOf(ach))) {
							return;
						}
						else {
							i.setCancelled(true);
							if (main.getBoolean("EndPortal.ErrorMessage")) {
								p.sendMessage(prefix + colormsg1);
							}
						}
					}
				}
			}
			else {
				i.setCancelled(true);
				if (main.getBoolean("EndPortal.PermBlockErrorMessage")) {
					p.sendMessage(prefix + colormsg2);
				}
			}
		}
	}
	public static boolean hasAdvancement(Player p) {
		FileConfiguration main = Main.getInstance().getConfig();
		String adv = main.getString("EndPortal.Advancement");
		Advancement advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(adv));
		if (p.getAdvancementProgress(advancement).isDone()) {
			return true;
		}
		else {
			return false;
		}
	}
}
