package com.vaincecraft.portalblockerfree.messages;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.vaincecraft.portalblockerfree.main.Main;

public class messages {
	private String LangFileName = Main.getLang();
	private File file = new File(Main.getInstance().getDataFolder() + "/lang/" + LangFileName + ".yml");
	private FileConfiguration conf;
	public messages() {
		conf = YamlConfiguration.loadConfiguration(file);
	}
	public FileConfiguration getFile() {
		return conf;
	}
}
