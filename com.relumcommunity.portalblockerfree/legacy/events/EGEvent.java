package com.relumcommunity.portalblockerfree.events;

import com.relumcommunity.portalblockerfree.main.Main;
import java.sql.SQLException;
import org.bukkit.Achievement;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

@SuppressWarnings("deprecation")
public class EGEvent implements Listener {
	@EventHandler
	public void PortalEnter(PlayerTeleportEvent i) throws SQLException {
		if (i.getCause().equals(TeleportCause.END_GATEWAY)) {
			Player p = i.getPlayer();
			FileConfiguration main = Main.getInstance().getConfig();
			FileConfiguration lang = Main.getLangFile();
			int breaknumberlimit = main.getInt("EndGateway.BlockBreakNumberLimit");
			String breakblocktype = lang.getString("breakblockerror.endportalblockname");
			int actualbreaknumber = Main.egblocksbreak.ControlNumber(p.getName());
			int placenumberlimit = main.getInt("EndGateway.BlockPlaceNumberLimit");
			String placeblocktype = lang.getString("placeblockerror.endportalblockname");
			int actualplacenumber = Main.egblocksplace.ControlNumber(p.getName());
			String prefixs = main.getString("Prefix");
			String prefix = prefixs.replaceAll("&", "§");
			String endgatewaye = main.getString("EndGateway.QuestName");
			String endge = endgatewaye.replaceAll("&", "§");
			String endgatewayerror = lang.getString("error.endgateway");
			String endgatewayerrorp = endgatewayerror.replaceAll("%prefix%", prefix);
			String ege = endgatewayerrorp.replaceAll("&", "§");
			String colormsg1 = ege.replaceAll("%endgatewayquest%", endge);
			String endgatewaypermblockmessage = lang.getString("permblockteleport.endgateway");
			String endgatewaypermblockmessagep = endgatewaypermblockmessage.replaceAll("%prefix%", prefix);
			String colormsg2 = endgatewaypermblockmessagep.replaceAll("&", "§");
			String breakblocks = lang.getString("breakblockerror.endgateway");
			String breakblock = breakblocks.replaceAll("%prefix%", prefix);
			String breakblock1 = breakblock.replaceAll("%numberbreak%", Integer.toString(breaknumberlimit));
			String breakblock2 = breakblock1.replaceAll("%blockbreak%", breakblocktype);
			String breakblock3 = breakblock2.replaceAll("%blockbroken%", Integer.toString(actualbreaknumber));
			String colormsg3 = breakblock3.replaceAll("&", "§");
			String placeblocks = lang.getString("placeblockerror.endgateway");
			String placeblock = placeblocks.replaceAll("%prefix%", prefix);
			String placeblock1 = placeblock.replaceAll("%numberplace%", Integer.toString(placenumberlimit));
			String placeblock2 = placeblock1.replaceAll("%blockplace%", placeblocktype);
			String placeblock3 = placeblock2.replaceAll("%blockplaced%", Integer.toString(actualplacenumber));
			String colormsg4 = placeblock3.replaceAll("&", "§");
			if (!main.getBoolean("PermBlockTeleports.EGBlock")) {
				if (main.getBoolean("BlockThroughAchievement.EGBlock")) {
					String ach = main.getString("EndGateway.Achievement");
					if (!p.hasAchievement(Achievement.valueOf(ach))) {
						i.setCancelled(true);
						if (main.getBoolean("EndGateway.ErrorMessage")) {
							p.sendMessage(colormsg1);
						}
					}
				}
				if (main.getBoolean("EndGateway.BlockBreakOption") && actualbreaknumber != breaknumberlimit) {
					i.setCancelled(true);
					p.sendMessage(colormsg3);
				}
				if (main.getBoolean("EndGateway.BlockPlaceOption") && actualplacenumber != placenumberlimit) {
					i.setCancelled(true);
					p.sendMessage(colormsg4);
				}
			}
			else {
				i.setCancelled(true);
				if (main.getBoolean("EndGateway.PermBlockErrorMessage")) {
					p.sendMessage(colormsg2);
				}
			}
		}
	}
}
