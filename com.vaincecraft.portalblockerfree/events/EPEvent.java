package com.vaincecraft.portalblockerfree.events;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
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
		String prefixs = Main.getInstance().getConfig().getString("Prefix");
		String prefix = prefixs.replaceAll("&", "§");
		String endportale = Main.getInstance().getConfig().getString("EndPortal.QuestName");
		String endpe = endportale.replaceAll("&", "§");
		String endportalerror = Main.getLangFile().getString("error.endportal");
		String epe = endportalerror.replaceAll("&", "§");
		String colormsg1 = epe.replaceAll("%endportalquest%", endpe);
		if (Main.getInstance().getConfig().getBoolean("Teleports.EPBlock")) {
			if (i.getCause().equals(TeleportCause.END_PORTAL)) {
				if (Bukkit.getVersion().contains("1.12") || Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14")){
					if (hasAdvancement(i.getPlayer())) {
						return;
					}
					else {
						i.setCancelled(true);
						if (Main.getInstance().getConfig().getBoolean("EndPortal.ErrorMessage")) {
							i.getPlayer().sendMessage(prefix + colormsg1);
						}
					}
				}
				else {
					String ach = Main.getInstance().getConfig().getString("EndPortal.Achievement");
					if (i.getPlayer().hasAchievement(Achievement.valueOf(ach))) {
						return;
					}
					else {
						i.setCancelled(true);
						if (Main.getInstance().getConfig().getBoolean("EndPortal.ErrorMessage")) {
							i.getPlayer().sendMessage(prefix + colormsg1);
						}
					}
				}
			}
		}
	}
	public static boolean hasAdvancement(Player p) {
		String adv = Main.getInstance().getConfig().getString("EndPortal.Advancement");
		Advancement advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(adv));
		if (p.getAdvancementProgress(advancement).isDone()) {
			return true;
		}
		else {
			return false;
		}
	}
}
