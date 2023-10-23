package com.relumcommunity.portalblockerfree.messages;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.relumcommunity.portalblockerfree.main.Main;

public class messages {
	private String LangFileName = Main.getLang();
	private File file = new File(Main.getInstance().getDataFolder() + "/lang/" + LangFileName + ".yml");
	private FileConfiguration conf;
	public messages() {
		conf = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
	}
	public FileConfiguration getFile() {
		return conf;
	}
}
