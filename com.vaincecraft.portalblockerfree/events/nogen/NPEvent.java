package com.vaincecraft.portalblockerfree.events.nogen;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.event.world.PortalCreateEvent.CreateReason;

import com.vaincecraft.portalblockerfree.main.Main;

public class NPEvent implements Listener {
	@EventHandler
	public void PortalDenyCreation (PortalCreateEvent e) {
		if (e.getReason().equals(CreateReason.FIRE)) {
			if (Main.getInstance().getConfig().getBoolean("BlockPortalCreation.NPCreate")) {
				e.setCancelled(true);
			}
		}
	}
}
