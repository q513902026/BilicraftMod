package bilicraft.mods.core.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;

public class ModUtil {

	private static ModUtil INSTANCE = new ModUtil();

	private static Set<String> modsname = new HashSet<String>();
	private static Map<String, Boolean> mods = new HashMap<String, Boolean>();

	static {
		modsname.add("Forgotten Nature");
	}

	public ModUtil instance() {
		return INSTANCE;
	}

	public void init() {
		this.checkMod();
	}

	public void checkMod() {
		for (String modname : modsname) {
			if (ModLoader.isModLoaded(modname)) {
				mods.put(modname, true);
				System.out.println("[Bilicraft]检测到兼容列表中的" + modname);
			}
		}
	}

	public ItemStack getModItem(String itemname, String modname,
			String packagename) {
		if (mods.get(modname) != null & mods.get(modname)) {
			try {
				Object item = Class.forName(packagename).getField("itemname")
						.get((Object) null);
				return item instanceof ItemStack ? (ItemStack) item : null;
			} catch (Exception e) {
				System.out.println("[Bilicraft]获取需要的Item失败 原因为" + e);
				return null;
			}
		}
		return null;
	}

	public Block getModBlock(String blockname, String modname,
			String packagename) {
		if (mods.get(modname)) {
			try {
				Object block = Class.forName(packagename).getField(blockname)
						.get((Object) null);
				return block instanceof Block ? (Block) block : null;
			} catch (Exception e) {
				System.out.println("[Bilicraft]获取需要的Item失败 原因为" + e);
				return null;
			}
		}
		return null;
	}
}
