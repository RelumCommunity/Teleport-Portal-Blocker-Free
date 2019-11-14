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
public class NPEvent implements Listener {
	@EventHandler
	public void PortalEnter (PlayerPortalEvent i) {
		if (i.getCause().equals(TeleportCause.NETHER_PORTAL)) {
			Player p = i.getPlayer();
			String ver = Bukkit.getVersion();
			FileConfiguration main = Main.getInstance().getConfig();
			String prefixs = main.getString("Prefix");
			String prefix = prefixs.replaceAll("&", "§");
			String netherportale = main.getString("NetherPortal.QuestName");
			String netherpe = netherportale.replaceAll("&", "§");
			String netherportalerror = Main.getLangFile().getString("error.netherportal");
			String npe = netherportalerror.replaceAll("&", "§");
			String colormsg1 = npe.replaceAll("%netherportalquest%", netherpe);
			String netherportalpermblockmessage = Main.getLangFile().getString("permblockteleport.netherportal");
			String colormsg2 = netherportalpermblockmessage.replaceAll("&", "§");
			if (!main.getBoolean("PermBlockTeleports.NPBlock")) {
				if (main.getBoolean("BlockThroughAchievement.NPBlock")) {
					if (ver.contains("1.12") || ver.contains("1.13") || ver.contains("1.14")) {
						if (hasAdvancement(p)) {
							return;
						}
						else {
							i.setCancelled(true);
							if (main.getBoolean("NetherPortal.ErrorMessage")) {
								p.sendMessage(prefix + colormsg1);
							}
						}
					}
					else {
						String ach = main.getString("NetherPortal.Achievement");
						if (p.hasAchievement(Achievement.valueOf(ach))) {
							return;
						}
						else {
							i.setCancelled(true);
							if (main.getBoolean("NetherPortal.ErrorMessage")) {
								p.sendMessage(prefix + colormsg1);
							}
						}
					}
				}
			}
			else {
				i.setCancelled(true);
				if (main.getBoolean("NetherPortal.PermBlockErrorMessage")) {
					p.sendMessage(prefix + colormsg2);
				}
			}
		}
	}
	public static boolean hasAdvancement(Player p) {
		FileConfiguration main = Main.getInstance().getConfig();
		String adv = main.getString("NetherPortal.Advancement");
		Advancement advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(adv));
		if (p.getAdvancementProgress(advancement).isDone()) {
			return true;
		}
		else {
			return false;
		}
	}
}
