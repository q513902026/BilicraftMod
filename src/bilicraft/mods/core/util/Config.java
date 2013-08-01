package bilicraft.mods.core.util;

import java.io.File;
import java.io.IOException;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;


public class Config{
	private static Configuration config;

	public Config(File configFile) {
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				System.out.println(e);
				return;
			}
		}
		config = new Configuration(configFile);
		config.load();
	}

	public void initliazeConfig(File ConfigFile) {
		if (this != null) {
			return;
		}
		config = new Configuration(ConfigFile);
	}

	public String getGeneralProperties(String PropertyName, String DefaultValue)
			throws Exception {
		if (this == null) {
			throw new NullPointerException();
		}
		return config.get("general", PropertyName, DefaultValue).getString();
	}

	public Property getProperty(String category, String propertyName,
			String defaultValue) throws Exception {
		if (this == null) {
			throw new NullPointerException();
		}
		return config.get(category, propertyName, defaultValue);
	}

	public Boolean getBoolean(String name, Boolean defaultValue)
			throws Exception {
		if (this == null) {
			throw new NullPointerException();
		}
		return config.get("general", name, defaultValue).getBoolean(
				defaultValue);
	}

	public int getInteger(String name, Integer defaultValue) throws Exception {
		if (this == null) {
			throw new NullPointerException();
		}
		return config.get("general", name, defaultValue).getInt();
	}

	public int getItemID(String itemName, int defaultValue) throws Exception {
		if (this == null) {
			throw new NullPointerException();
		}
		return config.getItem("item", "ID." + itemName, defaultValue).getInt();
	}

	public int getBlockID(String blockName, int defaultID) throws Exception {
		if (this == null) {
			throw new NullPointerException();
		}
		return config.getBlock("ID." + blockName, defaultID).getInt();
	}

	public void SaveConfig() {
		config.save();
	}

}