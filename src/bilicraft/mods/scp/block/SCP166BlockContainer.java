package bilicraft.mods.scp.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import bilicraft.mods.core.block.BCBlockContainer;
import bilicraft.mods.core.proxy.Props;
import bilicraft.mods.scp.tileentity.SCP166TileEntity;

public class SCP166BlockContainer extends BCBlockContainer{

	public SCP166BlockContainer(int id, Material material) {
		super(id, material);
		this.setUnlocalizedName("scp166");
		this.setIconName("scp166");
		this.setGuiId(Props.GUI_ID_SCP);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return new SCP166TileEntity();
	}

}
