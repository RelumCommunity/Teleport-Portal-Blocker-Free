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
public class EPEvent implements Listener {
	@EventHandler
	public void PortalEnter(PlayerTeleportEvent i) throws SQLException {
		if (i.getCause().equals(TeleportCause.END_PORTAL)) {
			Player p = i.getPlayer();
		    FileConfiguration main = Main.getInstance().getConfig();
		    FileConfiguration lang = Main.getLangFile();
		    int breaknumberlimit = main.getInt("EndPortal.BlockBreakNumberLimit");
		    String breakblocktype = lang.getString("breakblockerror.endportalblockname");
		    int actualbreaknumber = Main.epblocksbreak.ControlNumber(p.getName());
		    int placenumberlimit = main.getInt("EndPortal.BlockPlaceNumberLimit");
		    String placeblocktype = lang.getString("placeblockerror.endportalblockname");
		    int actualplacenumber = Main.epblocksplace.ControlNumber(p.getName());
		    String prefixs = main.getString("Prefix");
		    String prefix = prefixs.replaceAll("&", "§");
		    String endportale = main.getString("EndPortal.QuestName");
		    String endpe = endportale.replaceAll("&", "§");
		    String endportalerror = lang.getString("error.endportal");
		    String endportalerrorp = endportalerror.replaceAll("%prefix%", prefix);
		    String epe = endportalerrorp.replaceAll("&", "§");
		    String colormsg1 = epe.replaceAll("%endportalquest%", endpe);
		    String endportalpermblockmessage = lang.getString("permblockteleport.endportal");
		    String endportalpermblockmessagep = endportalpermblockmessage.replaceAll("%prefix%", prefix);
		    String colormsg2 = endportalpermblockmessagep.replaceAll("&", "§");
		    String breakblocks = lang.getString("breakblockerror.endportal");
		    String breakblock = breakblocks.replaceAll("%prefix%", prefix);
		    String breakblock1 = breakblock.replaceAll("%numberbreak%", Integer.toString(breaknumberlimit));
		    String breakblock2 = breakblock1.replaceAll("%blockbreak%", breakblocktype);
		    String breakblock3 = breakblock2.replaceAll("%blockbroken%", Integer.toString(actualbreaknumber));
		    String colormsg3 = breakblock3.replaceAll("&", "§");
		    String placeblocks = lang.getString("placeblockerror.endportal");
		    String placeblock = placeblocks.replaceAll("%prefix%", prefix);
		    String placeblock1 = placeblock.replaceAll("%numberplace%", Integer.toString(placenumberlimit));
		    String placeblock2 = placeblock1.replaceAll("%blockplace%", placeblocktype);
		    String placeblock3 = placeblock2.replaceAll("%blockplaced%", Integer.toString(actualplacenumber));
		    String colormsg4 = placeblock3.replaceAll("&", "§");
		    if (!main.getBoolean("PermBlockTeleports.EPBlock")) {
		    	if (main.getBoolean("BlockThroughAchievement.EPBlock")) {
	    			String ach = main.getString("EndPortal.Achievement");
	    			if (!p.hasAchievement(Achievement.valueOf(ach))) {
	    				i.setCancelled(true);
	    				if (main.getBoolean("EndPortal.ErrorMessage")) {
	    					p.sendMessage(colormsg1);
	    				}
	    			}
		    	}
		    	if (main.getBoolean("EndPortal.BlockBreakOption") && actualbreaknumber != breaknumberlimit) {
		    		i.setCancelled(true);
		    		p.sendMessage(colormsg3);
		    	}
		    	if (main.getBoolean("EndPortal.BlockPlaceOption") && actualplacenumber != placenumberlimit) {
		    		i.setCancelled(true);
		    		p.sendMessage(colormsg4);
		    	}
		    }
		    else {
		    	i.setCancelled(true);
		    	if (main.getBoolean("EndPortal.PermBlockErrorMessage")) {
		    		p.sendMessage(colormsg2);
		    	}
		    }
		}
	}
}
