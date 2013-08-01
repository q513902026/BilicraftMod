package bilicraft.mods.core.block;

import java.util.Random;

import bilicraft.mods.core.Bilicraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * 
 * @author HopeAsd,WeAthFolD
 **/
public abstract class BCBlockContainer extends BlockContainer {
	private String iconName;

	/**
	 * 这是用来表示GUIID的int值 初始值为-1
	 * 
	 * @author WeAthFolD
	 **/
	protected int guiId = -1;

	public BCBlockContainer(int id, Material material) {
		super(id, material);
	}

	public BCBlockContainer setIconName(String iconName) {
		this.iconName = iconName;
		return this;
	}

	public BCBlockContainer setGuiId(int guiId) {
		this.guiId = guiId;
		return this;
	}

	public int getGuiID() {
		return this.guiId;
	}

	public String getIconName() {
		return this.iconName;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int idk, float what, float these, float are) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if (guiId == -1 || te == null || player.isSneaking()) {
			return false;
		}
		player.openGui(Bilicraft.instance, guiId, world, x, y, z);
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("bilicraft:" + iconName);
	}

	/**
	 * 判断是否有掉落的物品是否存在NBT 如果是则往掉落的物品添加NBT
	 * 
	 * @author WeAthFolD
	 **/
	protected void dropItems(World world, int x, int y, int z,
			ItemStack[] inventory) {
		Random rand = new Random();

		for (ItemStack item : inventory) {

			if (item != null && item.stackSize > 0) {
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z
						+ rz, new ItemStack(item.itemID, item.stackSize,
						item.getItemDamage()));

				if (item.hasTagCompound()) {
					entityItem.getEntityItem().setTagCompound(
							(NBTTagCompound) item.getTagCompound().copy());
				}

				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				item.stackSize = 0;
			}
		}
	}
}