package com.relumcommunity.portalblockerfree.commands;

import com.relumcommunity.portalblockerfree.main.Main;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player || sender instanceof ConsoleCommandSender) {
			String permhelp = "portalblocker.help";
			String permreload = "portalblocker.reload";
			String permaliases = "portalblocker.aliases";
			String permegrdbb = "portalblocker.resetdata.blockbreak.endgateway";
			String permegrdbp = "portalblocker.resetdata.blockplace.endgateway";
			String permeprdbb = "portalblocker.resetdata.blockbreak.endportal";
			String permeprdbp = "portalblocker.resetdata.blockplace.endportal";
			String permnprdbb = "portalblocker.resetdata.blockbreak.netherportal";
			String permnprdbp = "portalblocker.resetdata.blockplace.netherportal";
			String permegrnbb = "portalblocker.removename.blockbreak.endgateway";
			String permegrnbp = "portalblocker.removename.blockplace.endgateway";
			String permeprnbb = "portalblocker.removename.blockbreak.endportal";
			String permeprnbp = "portalblocker.removename.blockplace.endportal";
			String permnprnbb = "portalblocker.removename.blockbreak.netherportal";
			String permnprnbp = "portalblocker.removename.blockplace.netherportal";
			FileConfiguration main = Main.getInstance().getConfig();
			FileConfiguration lang = Main.getLangFile();
			String prefixs = main.getString("Prefix");
			String prefix = prefixs.replaceAll("&", "§");
			String nopermerror = lang.getString("error.nopermission");
			String nopermerror1 = nopermerror.replaceAll("%prefix%", prefix);
			String Error1 = nopermerror1.replaceAll("&", "§");
			String reload = lang.getString("config.reload");
			String reload1 = reload.replaceAll("%prefix%", prefix);
			String Reload1 = reload1.replaceAll("&", "§");
			String Base1 = String.valueOf(prefix) + " " + ChatColor.GREEN + "Try with /" + label + " help";
			String Aliases1 = ChatColor.GREEN + "Aliases list";
			String Aliases2 = ChatColor.GREEN + "PortalBlocker = pb - portalb - pblocker";
			String Aliases3 = ChatColor.GREEN + "NetherPortal = np";
			String Aliases4 = ChatColor.GREEN + "EndPortal = ep";
			String Aliases5 = ChatColor.GREEN + "EndGateway = eg";
			if (cmd.getName().equalsIgnoreCase("portalblocker")) {
				if (args.length == 0) {
					sender.sendMessage(Base1);
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("help")) {
						if (sender.hasPermission(permhelp)) {
							help(label, sender);
						}
						else {
							sender.sendMessage(Error1);
						}
					}
					if (args[0].equalsIgnoreCase("reload")) {
						if (sender.hasPermission(permreload)) {
							Main.getInstance().reloadConfig();
							sender.sendMessage(Reload1);
						}
						else {
							sender.sendMessage(Error1);
						}
					}
					if (args[0].equalsIgnoreCase("aliases")) {
						if (sender.hasPermission(permaliases)) {
							sender.sendMessage(Aliases1);
							sender.sendMessage(Aliases2);
							sender.sendMessage(Aliases3);
							sender.sendMessage(Aliases4);
							sender.sendMessage(Aliases5);
						}
						else {
							sender.sendMessage(Error1);
						}
					}
				}
				if (args.length == 3 && args[0].equalsIgnoreCase("resetdata")) {
					if (args[1].equalsIgnoreCase("blockbreak")) {
						if (args[2].equalsIgnoreCase("netherportal") || args[2].equalsIgnoreCase("np")) {
							if (sender.hasPermission(permnprdbb)) {
								try {
									Main.npblocksbreak.Reset(sender);
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							else {
								sender.sendMessage(Error1);
							}
						}
						if (args[2].equalsIgnoreCase("endportal") || args[2].equalsIgnoreCase("ep")) {
							if (sender.hasPermission(permeprdbb)) {
								try {
									Main.epblocksbreak.Reset(sender);
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							else {
								sender.sendMessage(Error1);
							}
						}
						if (args[2].equalsIgnoreCase("endgateway") || args[2].equalsIgnoreCase("eg")) {
							if (sender.hasPermission(permegrdbb)) {
								try {
									Main.egblocksbreak.Reset(sender);
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							else {
								sender.sendMessage(Error1);
							}
						}
					}
					if (args[1].equalsIgnoreCase("blockplace")) {
						if (args[2].equalsIgnoreCase("netherportal") || args[2].equalsIgnoreCase("np")) {
							if (sender.hasPermission(permnprdbp)) {
								try {
									Main.npblocksplace.Reset(sender);
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							else {
								sender.sendMessage(Error1);
							}
						}
						if (args[2].equalsIgnoreCase("endportal") || args[2].equalsIgnoreCase("ep")) {
							if (sender.hasPermission(permeprdbp)) {
								try {
									Main.epblocksplace.Reset(sender);
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							else {
								sender.sendMessage(Error1);
							}
						}
						if (args[2].equalsIgnoreCase("endgateway") || args[2].equalsIgnoreCase("eg")) {
							if (sender.hasPermission(permegrdbp)) {
								try {
									Main.egblocksplace.Reset(sender);
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							else {
								sender.sendMessage(Error1);
							}
						}
					} 
				} 
			} 
			if (args.length == 4 && args[0].equalsIgnoreCase("removename")) {
				if (args[1].equalsIgnoreCase("blockbreak")) {
					if (args[2].equalsIgnoreCase("netherportal") || args[2].equalsIgnoreCase("np")) {
						if (sender.hasPermission(permnprnbb)) {
							try {
								Main.npblocksbreak.RemoveName1(args[3], sender);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						else {
							sender.sendMessage(Error1);
						}
					}
					if (args[2].equalsIgnoreCase("endportal") || args[2].equalsIgnoreCase("ep")) {
						if (sender.hasPermission(permeprnbb)) {
							try {
								Main.epblocksbreak.RemoveName1(args[3], sender);
							} catch (SQLException e) {
								e.printStackTrace();
							} 
						} else {
							sender.sendMessage(Error1);
						}  
					if (args[2].equalsIgnoreCase("endgateway") || args[2].equalsIgnoreCase("eg"))
						if (sender.hasPermission(permegrnbb)) {
							try {
								Main.egblocksbreak.RemoveName1(args[3], sender);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						else {
							sender.sendMessage(Error1);
						}
					}
				} 
				if (args[1].equalsIgnoreCase("blockplace")) {
					if (args[2].equalsIgnoreCase("netherportal") || args[2].equalsIgnoreCase("np")) {
						if (sender.hasPermission(permnprnbp)) {
							try {
								Main.npblocksplace.RemoveName1(args[3], sender);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						else {
							sender.sendMessage(Error1);
						}
					}
					if (args[2].equalsIgnoreCase("endportal") || args[2].equalsIgnoreCase("ep")) {
						if (sender.hasPermission(permeprnbp)) {
							try {
								Main.epblocksplace.RemoveName1(args[3], sender);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						else {
							sender.sendMessage(Error1);
						}
					}
					if (args[2].equalsIgnoreCase("endgateway") || args[2].equalsIgnoreCase("eg")) {
						if (sender.hasPermission(permegrnbp)) {
							try {
								Main.egblocksplace.RemoveName1(args[3], sender);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						else {
							sender.sendMessage(Error1);
						}
					}
				}
			}
		}
		return false;
	}
	private void help(String label, CommandSender sender) {
		FileConfiguration lang = Main.getLangFile();
		String pluginVersion = Main.getInstance().getDescription().getVersion();
		FileConfiguration main = Main.getInstance().getConfig();
		String prefixs = main.getString("Prefix");
		String prefix = prefixs.replaceAll("&", "§");
		List<?> help = new ArrayList<Object>();
		help.clear();
		help = lang.getList("messages.Help");
		int size = help.size();
		int i = 0;
		while (i < size) {
			String helps = (String)help.get(i);
			String help1 = helps.replaceAll("&", "§");
			String help2 = help1.replaceAll("%prefix%", prefix);
			String pluginVersions = help2.replace("%label%", label);
			String colormsg3 = pluginVersions.replace("%ver%", pluginVersion);
			sender.sendMessage(colormsg3);
			i++;
		}
	}
}
