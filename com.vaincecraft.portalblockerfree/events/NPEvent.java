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
public class NPEvent implements Listener {
	@EventHandler
	public void PortalEnter (PlayerPortalEvent i) {
		String prefixs = Main.getInstance().getConfig().getString("Prefix");
		String prefix = prefixs.replaceAll("&", "§");
		String netherportale = Main.getInstance().getConfig().getString("NetherPortal.QuestName");
		String netherpe = netherportale.replaceAll("&", "§");
		String netherportalerror = Main.getLangFile().getString("error.netherportal");
		String npe = netherportalerror.replaceAll("&", "§");
		String colormsg1 = npe.replaceAll("%netherportalquest%", netherpe);
		if (Main.getInstance().getConfig().getBoolean("Teleports.NPBlock")) {
			if (i.getCause().equals(TeleportCause.NETHER_PORTAL)) {
				if (Bukkit.getVersion().contains("1.12") || Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14")){
					if (hasAdvancement(i.getPlayer())) {
						return;
					}
					else {
						i.setCancelled(true);
						if (Main.getInstance().getConfig().getBoolean("NetherPortal.ErrorMessage")) {
							i.getPlayer().sendMessage(prefix + colormsg1);
						}
					}
				}
				else {
					String ach = Main.getInstance().getConfig().getString("NetherPortal.Achievement");
					if (i.getPlayer().hasAchievement(Achievement.valueOf(ach))) {
						return;
					}
					else {
						i.setCancelled(true);
						if (Main.getInstance().getConfig().getBoolean("NetherPortal.ErrorMessage")) {
							i.getPlayer().sendMessage(prefix + colormsg1);
						}
					}
				}
			}
		}
	}
	public static boolean hasAdvancement(Player p) {
		String adv = Main.getInstance().getConfig().getString("NetherPortal.Advancement");
		Advancement advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(adv));
		if (p.getAdvancementProgress(advancement).isDone()) {
			return true;
		}
		else {
			return false;
		}
	}
}
