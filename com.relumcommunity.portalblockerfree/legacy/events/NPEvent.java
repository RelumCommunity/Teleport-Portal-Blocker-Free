package com.relumcommunity.portalblockerfree.events;

import com.relumcommunity.portalblockerfree.main.Main;
import java.sql.SQLException;
import java.util.List;

import org.bukkit.Achievement;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.world.PortalCreateEvent;

@SuppressWarnings("deprecation")
public class NPEvent implements Listener {
	@EventHandler
	public void PortalEnter(PlayerTeleportEvent i) throws SQLException {
		if (i.getCause().equals(TeleportCause.NETHER_PORTAL)) {
			Player p = i.getPlayer();
			FileConfiguration main = Main.getInstance().getConfig();
			FileConfiguration lang = Main.getLangFile();
			List<String> nodeactivate = main.getStringList("NetherPortal.DoNotIn");
			Location loc = p.getLocation();
			String locW = loc.getWorld().getName();
			Material portalFrame = loc.getWorld().getBlockAt(loc).getType();
			int breaknumberlimit = main.getInt("NetherPortal.BlockBreakNumberLimit");
			String breakblocktype = lang.getString("breakblockerror.netherportalblockname");
			int actualbreaknumber = Main.npblocksbreak.ControlNumber(p.getName());
			int placenumberlimit = main.getInt("NetherPortal.BlockPlaceNumberLimit");
			String placeblocktype = lang.getString("placeblockerror.netherportalblockname");
			int actualplacenumber = Main.npblocksplace.ControlNumber(p.getName());
			String prefixs = main.getString("Prefix");
			String prefix = prefixs.replaceAll("&", "§");
			String netherportale = main.getString("NetherPortal.QuestName");
			String netherpe = netherportale.replaceAll("&", "§");
			String netherportalerror = lang.getString("error.netherportal");
			String netherportalerrorp = netherportalerror.replaceAll("%prefix%", prefix);
			String npe = netherportalerrorp.replaceAll("&", "§");
			String colormsg1 = npe.replaceAll("%netherportalquest%", netherpe);
			String netherportalpermblockmessage = lang.getString("permblockteleport.netherportal");
			String netherportalpermblockmessagep = netherportalpermblockmessage.replaceAll("%prefix%", prefix);
			String colormsg2 = netherportalpermblockmessagep.replaceAll("&", "§");
			String breakblocks = lang.getString("breakblockerror.netherportal");
			String breakblock = breakblocks.replaceAll("%prefix%", prefix);
			String breakblock1 = breakblock.replaceAll("%numberbreak%", Integer.toString(breaknumberlimit));
			String breakblock2 = breakblock1.replaceAll("%blockbreak%", breakblocktype);
			String breakblock3 = breakblock2.replaceAll("%blockbroken%", Integer.toString(actualbreaknumber));
			String colormsg3 = breakblock3.replaceAll("&", "§");
			String placeblocks = lang.getString("placeblockerror.netherportal");
			String placeblock = placeblocks.replaceAll("%prefix%", prefix);
			String placeblock1 = placeblock.replaceAll("%numberplace%", Integer.toString(placenumberlimit));
			String placeblock2 = placeblock1.replaceAll("%blockplace%", placeblocktype);
			String placeblock3 = placeblock2.replaceAll("%blockplaced%", Integer.toString(actualplacenumber));
			String colormsg4 = placeblock3.replaceAll("&", "§");
			if (!main.getBoolean("PermBlockTeleports.NPBlock")) {
				if (main.getBoolean("BlockThroughAchievement.NPBlock")) {
					String ach = main.getString("NetherPortal.Achievement");
					if (!p.hasAchievement(Achievement.valueOf(ach))) {
						i.setCancelled(true);
						if (main.getBoolean("NetherPortal.ErrorMessage")) {
							p.sendMessage(colormsg1);
						}
					}
				}
				if (main.getBoolean("NetherPortal.BlockBreakOption") && actualbreaknumber != breaknumberlimit) {
					i.setCancelled(true);
					p.sendMessage(colormsg3);
				}
				if (main.getBoolean("NetherPortal.BlockPlaceOption") && actualplacenumber != placenumberlimit) {
					i.setCancelled(true);
					p.sendMessage(colormsg4);
				}
				if (i.isCancelled() && main.getBoolean("NetherPortal.Deactivate")) {
					if (!nodeactivate.contains(locW)) {
						Material Portal = Material.PORTAL;
						if (portalFrame.equals(Portal)) {
							loc.getWorld().getBlockAt(loc).setType(Material.AIR);
						}
					}
				}
			}
			else {
				i.setCancelled(true);
				if (main.getBoolean("NetherPortal.PermBlockErrorMessage")) {
					p.sendMessage(colormsg2);
				}
			}
		}
	}
	@EventHandler
	public void PortalDenyCreation(PortalCreateEvent e) {
		if (e.getReason().equals(PortalCreateEvent.CreateReason.FIRE) && Main.getInstance().getConfig().getBoolean("BlockPortalCreation.NPCreate")) {
			e.setCancelled(true);
		}
	}
}
