package com.relumcommunity.portalblockerfree.events;

import com.relumcommunity.portalblockerfree.main.Main;
import java.sql.SQLException;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BreakPlace implements Listener {
	@EventHandler
	public void BlockBreak(BlockBreakEvent i) throws SQLException {
		Player p = i.getPlayer();
		FileConfiguration main = Main.getInstance().getConfig();
		String npblock = main.getString("NetherPortal.BlockBreakType");
		Material npBlock = Material.valueOf(npblock);
		String epblock = main.getString("EndPortal.BlockBreakType");
		Material epBlock = Material.valueOf(epblock);
		String egblock = main.getString("EndGateway.BlockBreakType");
		Material egBlock = Material.valueOf(egblock);
		if (p instanceof Player) {
			if (main.getBoolean("NetherPortal.BlockBreakOption") && i.getBlock().getType().equals(npBlock)) {
				int numberlimit = main.getInt("NetherPortal.BlockBreakNumberLimit");
				int actualnumber = Main.npblocksbreak.ControlNumber(p.getName());
				if (actualnumber < numberlimit) {
					Main.npblocksbreak.ModifyNumber(p.getName());
				}
				else if (actualnumber == numberlimit) {
					return;
				}
			}
			if (main.getBoolean("EndPortal.BlockBreakOption") && i.getBlock().getType().equals(epBlock)) {
				int numberlimit = main.getInt("EndPortal.BlockBreakNumberLimit");
				int actualnumber = Main.epblocksbreak.ControlNumber(p.getName());
				if (actualnumber < numberlimit) {
					Main.epblocksbreak.ModifyNumber(p.getName());
				}
				else if (actualnumber == numberlimit) {
					return;
				}
			}
			if (main.getBoolean("EndGateway.BlockBreakOption") && i.getBlock().getType().equals(egBlock)) {
				int numberlimit = main.getInt("EndGateway.BlockBreakNumberLimit");
				int actualnumber = Main.egblocksbreak.ControlNumber(p.getName());
				if (actualnumber < numberlimit) {
					Main.egblocksbreak.ModifyNumber(p.getName());
				}
				else if (actualnumber == numberlimit) {
					return;
				}
			}
		}
	}
	@EventHandler
	public void BlockPlace(BlockPlaceEvent i) throws SQLException {
		Player p = i.getPlayer();
		FileConfiguration main = Main.getInstance().getConfig();
		String npblock = main.getString("NetherPortal.BlockPlaceType");
		Material npBlock = Material.valueOf(npblock);
		String epblock = main.getString("EndPortal.BlockPlaceType");
		Material epBlock = Material.valueOf(epblock);
		String egblock = main.getString("EndGateway.BlockPlaceType");
		Material egBlock = Material.valueOf(egblock);
		if (p instanceof Player) {
			if (main.getBoolean("NetherPortal.BlockPlaceOption") && i.getBlock().getType().equals(npBlock)) {
				int numberlimit = main.getInt("NetherPortal.BlockPlaceNumberLimit");
				int actualnumber = Main.npblocksplace.ControlNumber(p.getName());
				if (actualnumber < numberlimit) {
					Main.npblocksplace.ModifyNumber(p.getName());
				}
				else if (actualnumber == numberlimit) {
					return;
				}
			}
			else if (main.getBoolean("EndPortal.BlockPlaceOption") && i.getBlock().getType().equals(epBlock)) {
				int numberlimit = main.getInt("EndPortal.BlockPlaceNumberLimit");
				int actualnumber = Main.epblocksplace.ControlNumber(p.getName());
				if (actualnumber < numberlimit) {
					Main.epblocksplace.ModifyNumber(p.getName());
				}
				else if (actualnumber == numberlimit) {
					return;
				}
			}
			if (main.getBoolean("EndGateway.BlockPlaceOption") && i.getBlock().getType().equals(egBlock)) {
				int numberlimit = main.getInt("EndGateway.BlockPlaceNumberLimit");
				int actualnumber = Main.egblocksplace.ControlNumber(p.getName());
				if (actualnumber < numberlimit) {
					Main.egblocksplace.ModifyNumber(p.getName());
				}
				else if (actualnumber == numberlimit) {
					return;
				}
			}
		}
	}
}
