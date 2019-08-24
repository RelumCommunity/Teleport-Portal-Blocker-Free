package com.vaincecraft.portalblockerfree.events;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.vaincecraft.portalblockerfree.main.Main;

@SuppressWarnings("deprecation")
public class EGEvent implements Listener {
	@EventHandler
	public void PortalEnter (PlayerTeleportEvent i) {
		String prefixs = Main.getInstance().getConfig().getString("Prefix");
		String prefix = prefixs.replaceAll("&", "§");
		String endgatewaye = Main.getInstance().getConfig().getString("EndGateway.QuestName");
		String endge = endgatewaye.replaceAll("&", "§");
		String endgatewayerror = Main.getLangFile().getString("error.endgateway");
		String ege = endgatewayerror.replaceAll("&", "§");
		String colormsg1 = ege.replaceAll("%endgatewayquest%", endge);
		if (Main.getInstance().getConfig().getBoolean("Teleports.EGBlock")) {
			if (i.getCause().equals(TeleportCause.END_GATEWAY)) {
				if (Bukkit.getVersion().contains("1.12") || Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14")){
					if (hasAdvancement(i.getPlayer())) {
						return;
					}
					else {
						i.setCancelled(true);
						if (Main.getInstance().getConfig().getBoolean("EndGateway.ErrorMessage")) {
							i.getPlayer().sendMessage(prefix + colormsg1);
						}
					}
				}
				else {
					String ach = Main.getInstance().getConfig().getString("EndGateway.Achievement");
					if (i.getPlayer().hasAchievement(Achievement.valueOf(ach))) {
						return;
					}
					else {
						i.setCancelled(true);
						if (Main.getInstance().getConfig().getBoolean("EndGateway.ErrorMessage")) {
							i.getPlayer().sendMessage(prefix + colormsg1);
						}
					}
				}
			}
		}
	}
	public static boolean hasAdvancement(Player p) {
		String adv = Main.getInstance().getConfig().getString("EndGateway.Advancement");
		Advancement advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(adv));
		if (p.getAdvancementProgress(advancement).isDone()) {
			return true;
		}
		else {
			return false;
		}
	}
}
