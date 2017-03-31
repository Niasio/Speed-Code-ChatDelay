package nl.ItsCodex.ChatDelay;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigYML{
	static ConfigYML instance = new ConfigYML();
	Plugin plugin;
	FileConfiguration dataconfig;
	File file;

	public void setup(Plugin p){
		this.file = new File(p.getDataFolder(), "Config.yml");
		if (!this.file.exists()){
			try{
				this.file.createNewFile();
			}
			catch (IOException e){
				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Kan config.yml niet aanmaken");
			}
		}
		this.dataconfig = YamlConfiguration.loadConfiguration(this.file);
	}

	public void saveConfig(){
		try{
			this.dataconfig.save(this.file);
		}
		catch (IOException e){
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Kan config.yml niet opslaan");
		}
	}

	public FileConfiguration getConfig(){
		return this.dataconfig;
	}

	public void reloadConfig(){
		this.dataconfig = YamlConfiguration.loadConfiguration(this.file);
	}
	public static ConfigYML getInstance(){
		return instance;
	}
}
