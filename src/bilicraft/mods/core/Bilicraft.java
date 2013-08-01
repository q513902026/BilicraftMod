package bilicraft.mods.core;

import bilicraft.mods.core.proxy.Proxy;
import bilicraft.mods.core.register.BCGuiHandler;
import bilicraft.mods.core.util.Config;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Bilicraft.MOD_MODID, name = Bilicraft.MOD_MODNAME, version = Bilicraft.MOD_MODVERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Bilicraft {
	public static final String MOD_MODID = "Bilicraft";
	public static final String MOD_MODNAME = "Bilicraft Core";
	public static final String MOD_MODVERSION = "version:0.0.1a";

	@Instance("Bilicraft")
	public static Bilicraft instance;

	public static Config conf;

	@SidedProxy(clientSide = "bilicraft.mods.core.proxy.ClientProxy", serverSide = "bilicraft.mods.core.proxy.Proxy")
	public static Proxy proxy;

	@PreInit
	public void preLoad(FMLPreInitializationEvent event) {
		conf = new Config(event.getSuggestedConfigurationFile());

		proxy.preLoad();

	}

	@Init
	public void load(FMLInitializationEvent event) {
		NetworkRegistry.instance().registerGuiHandler(this, new BCGuiHandler());
		proxy.load();
	}

	@PostInit
	public void postLoad(FMLPostInitializationEvent event) {
		conf.SaveConfig();
		proxy.postLoad();
	}
}