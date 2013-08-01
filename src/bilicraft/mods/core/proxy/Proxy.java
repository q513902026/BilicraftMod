package bilicraft.mods.core.proxy;

import cpw.mods.fml.common.FMLCommonHandler;

public class Proxy{
	public void preLoad(){}
	public void load(){}
	public void postLoad(){}
	
	public static boolean isRendering() {
		return !isSimulating();
	}

	private static boolean isSimulating() {
		return !FMLCommonHandler.instance().getEffectiveSide().isClient();
	}
}