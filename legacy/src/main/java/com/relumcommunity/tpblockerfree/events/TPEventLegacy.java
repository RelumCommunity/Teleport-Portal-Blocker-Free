package com.relumcommunity.tpblockerfree.events;

import org.bukkit.entity.Player;

public class TPEventLegacy extends TPEvent {
    @Override
    protected boolean checkAchievement(Player p, String achievement) {
        return p.hasAchievement(org.bukkit.Achievement.valueOf(achievement.toUpperCase()));
    }
}