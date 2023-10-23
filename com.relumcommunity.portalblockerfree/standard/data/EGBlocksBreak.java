package com.relumcommunity.portalblockerfree.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import com.relumcommunity.portalblockerfree.main.Main;

public class EGBlocksBreak {
	public Connection con;
	public Statement stat;
	public EGBlocksBreak(String string) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:" + string);
		CreateTabBreak();
	}
	public void CloseConnection() throws SQLException {
		if (con == null) {
			return;
		}
		con.close();
	}
	public void CreateTabBreak() throws SQLException {
		String querysqlite = "CREATE TABLE IF NOT EXISTS playernames1(id INTEGER PRIMARY KEY AUTOINCREMENT, names VARCHAR(255), number INTEGER)";
		stat = con.createStatement();
		stat.execute(querysqlite);
	}
	public boolean ControlName1(String playername) throws SQLException {
    	String querysqlite = "SELECT * FROM playernames1 WHERE names = '" + playername + "'";
    	stat = con.createStatement();
    	ResultSet result = stat.executeQuery(querysqlite);
    	if (result.next()) {
    		return true;
    	}
    	return false;
	}
	public int ControlNumber(String playername) throws SQLException {
		if (ControlName1(playername)) {
			String querysqlite = "SELECT * FROM playernames1 WHERE names = '" + playername + "'";
			stat = con.createStatement();
			ResultSet result = stat.executeQuery(querysqlite);
			if (result.next()) {
				int number = result.getInt("number");
				return number;
			}
		}
		return 0;
	}
	public void ModifyNumber(String playername) throws SQLException {
		if (ControlName1(playername)) {
			int updatenumber = ControlNumber(playername) + 1;
			String querysqlite = "UPDATE playernames1 SET number=" + updatenumber + " WHERE names='" + playername + "'";
			stat = con.createStatement();
			stat.execute(querysqlite);
		}
		else {
			AddName1(playername);
		}
	}
	public void AddName1(String playername) throws SQLException {
		if (ControlName1(playername)) {
			return;
		}
		String querysqlite = "INSERT INTO playernames1(names, number) VALUES('" + playername + "', 1)";
		stat = con.createStatement();
		stat.execute(querysqlite);
	}
	public void RemoveName1(String playername, CommandSender sender) throws SQLException {
		FileConfiguration main = Main.getInstance().getConfig();
		FileConfiguration lang = Main.getLangFile();
		String prefixs = main.getString("Prefix");
		String prefix = prefixs.replaceAll("&", "§");
		String removename = lang.getString("database.endgateway.blockbreak.removename");
		String removename1 = removename.replaceAll("%prefix%", prefix);
		String removename2 = removename1.replaceAll("%playername%", playername);
		String msg1 = removename2.replaceAll("&", "§");
		String notname = lang.getString("database.endgateway.blockbreak.namenotpresent");
		String notname1 = notname.replaceAll("%prefix%", prefix);
		String notname2 = notname1.replaceAll("%playername%", playername);
		String msg2 = notname2.replaceAll("&", "§");
		if (ControlName1(playername)) {
			String querysqlite = "DELETE FROM playernames1 WHERE names = '" + playername + "'";
			stat = con.createStatement();
			stat.execute(querysqlite);
			sender.sendMessage(msg1);
		}
		else {
			sender.sendMessage(msg2);
		}
	}
	public void Reset(CommandSender sender) throws SQLException {
		String conto = "SELECT COUNT(id) FROM playernames1";
		stat = con.createStatement();
		ResultSet result = stat.executeQuery(conto);
		int playerseliminated = result.getInt(1);
		String querysqlite = "DELETE FROM playernames1";
		stat.execute(querysqlite);
		FileConfiguration main = Main.getInstance().getConfig();
		FileConfiguration lang = Main.getLangFile();
		String prefixs = main.getString("Prefix");
		String prefix = prefixs.replaceAll("&", "§");
		String resetdata = lang.getString("database.endgateway.blockbreak.resetdata");
		String resetdata1 = resetdata.replaceAll("%prefix%", prefix);
		String resetdata2 = resetdata1.replaceAll("%number%", Integer.toString(playerseliminated));
		String msg1 = resetdata2.replaceAll("&", "§");
		sender.sendMessage(msg1);
	}
}
