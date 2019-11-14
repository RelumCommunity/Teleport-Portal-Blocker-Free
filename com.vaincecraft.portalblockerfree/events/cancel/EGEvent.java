package com.vaincecraft.portalblockerfree.events;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.configuration.file.FileConfiguration;
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
		if (i.getCause().equals(TeleportCause.END_GATEWAY)) {
			Player p = i.getPlayer();
			String ver = Bukkit.getVersion();
			FileConfiguration main = Main.getInstance().getConfig();
			String prefixs = main.getString("Prefix");
			String prefix = prefixs.replaceAll("&", "§");
			String endgatewaye = main.getString("EndGateway.QuestName");
			String endge = endgatewaye.replaceAll("&", "§");
			String endgatewayerror = Main.getLangFile().getString("error.endgateway");
			String ege = endgatewayerror.replaceAll("&", "§");
			String colormsg1 = ege.replaceAll("%endgatewayquest%", endge);
			String endgatewaypermblockmessage = Main.getLangFile().getString("permblockteleport.endgateway");
			String colormsg2 = endgatewaypermblockmessage.replaceAll("&", "§");
			if (!main.getBoolean("PermBlockTeleports.EGBlock")) {
				if (main.getBoolean("Teleports.EGBlock")) {
					if (ver.contains("1.12") || ver.contains("1.13") || ver.contains("1.14")){
						if (hasAdvancement(p)) {
							return;
						}
						else {
							i.setCancelled(true);
							if (main.getBoolean("EndGateway.ErrorMessage")) {
								p.sendMessage(prefix + colormsg1);
							}
						}
					}
					else {
						String ach = main.getString("EndGateway.Achievement");
						if (p.hasAchievement(Achievement.valueOf(ach))) {
							return;
						}
						else {
							i.setCancelled(true);
							if (main.getBoolean("EndGateway.ErrorMessage")) {
								p.sendMessage(prefix + colormsg1);
							}
						}
					}
				}
			}
			else {
				i.setCancelled(true);
				if (main.getBoolean("EndGateway.PermBlockErrorMessage")) {
					p.sendMessage(prefix + colormsg2);
				}
			}
		}
	}
	public static boolean hasAdvancement(Player p) {
		FileConfiguration main = Main.getInstance().getConfig();
		String adv = main.getString("EndGateway.Advancement");
		Advancement advancement = Bukkit.getAdvancement(NamespacedKey.minecraft(adv));
		if (p.getAdvancementProgress(advancement).isDone()) {
			return true;
		}
		else {
			return false;
		}
	}
}
