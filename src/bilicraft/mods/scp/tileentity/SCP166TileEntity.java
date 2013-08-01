package bilicraft.mods.scp.tileentity;

import java.util.HashMap;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.ItemArmor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class SCP166TileEntity extends TileEntity implements IInventory {

	private ItemStack[] stack = new ItemStack[5];
	
	private HashMap<Integer, ItemStack> stackitem = new HashMap<Integer, ItemStack>();

	public int tableBurnTime = 0;
	public int maxBurnTime = 0;

	public void updateEntity() {
		if (tableBurnTime > 0) {
			ItemStack stackinput = getStackInSlot(0);
			ItemStack stackoutput = getStackInSlot(5);
			if (stackinput != null && stackoutput == null) {
				if (stackinput.getItem() instanceof ItemTool
						|| stackinput.getItem() instanceof ItemArmor) {
					if (stackinput.getItemDamage() > 0) {
						stackinput
								.setItemDamage(stackinput.getItemDamage() - 1);
					}
				}
			}
			tableBurnTime -= 1;
		} else {
			if (getStackInSlot(0) != null) {
				ItemStack stackburn = getStackInSlot(1);
				int getBurnTime = this.getItemBurnTime(stackburn);
				if (getBurnTime > 0) {
					maxBurnTime = getBurnTime;
					tableBurnTime = getBurnTime;
					if (stackburn.getItem().itemID == Item.bucketLava.itemID){
						setInventorySlotContents(5, new ItemStack(
								Item.bucketLava));
					}else{
						if(stackburn.stackSize - 1 > 0 ){
							stackburn.stackSize--;
							setInventorySlotContents(5,stackburn);
						}else{
							setInventorySlotContents(5,null);
						}
					}
				}
			}
		}
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
		this.stack = new ItemStack[this.getSizeInventory()];
		for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
			NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
			byte var5 = var4.getByte("Slot");
			if (var5 >= 0 && var5 < this.stack.length) {
				this.stack[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}
		this.tableBurnTime = par1NBTTagCompound.getShort("tableBurnTime");
		this.maxBurnTime = par1NBTTagCompound.getShort("maxBurnTime");
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound
				.setShort("tableBurnTime", (short) this.tableBurnTime);
		par1NBTTagCompound.setShort("maxBurnTime", (short) this.maxBurnTime);
		NBTTagList var2 = new NBTTagList();
		for (int var3 = 0; var3 < this.stack.length; ++var3) {
			if (this.stack[var3] != null) {
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				this.stack[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}
		par1NBTTagCompound.setTag("Items", var2);
	}

	@Override
	public int getSizeInventory() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		// TODO 自动生成的方法存根
		return stack[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		// TODO 自动生成的方法存根
		if (this.stack != null) {
			ItemStack par1;

			if (this.stack[i].stackSize <= j) {
				par1 = this.stack[i];
				this.stack[i] = null;
				return par1;
			} else {
				par1 = this.stack[i].splitStack(j);
				if (this.stack[i].stackSize == 0) {
					this.stack[i] = null;
				}
				return par1;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		// TODO 自动生成的方法存根
		this.stack[i] = itemstack;
		if (itemstack != null
				&& itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO 自动生成的方法存根
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public void openChest() {
		// TODO 自动生成的方法存根

	}

	@Override
	public void closeChest() {
		// TODO 自动生成的方法存根

	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		// TODO 自动生成的方法存根
		return false;
	}

	public static int getItemBurnTime(ItemStack par0ItemStack) {
		if (par0ItemStack == null) {
			return 0;
		} else {
			int var1 = par0ItemStack.getItem().itemID;
			Item var2 = par0ItemStack.getItem();
			if (var2 instanceof ItemBlock
					&& Block.blocksList[var1 + 256] != null) {
				Block var3 = Block.blocksList[var1 + 256];
				if (var3 == Block.woodSingleSlab) {
					return 150;
				}

				if (var3.blockMaterial == Material.wood) {
					return 300;
				}
			}
			if (var2 instanceof ItemTool
					&& ((ItemTool) var2).getToolMaterialName().equals("WOOD"))
				return 200;
			if (var2 instanceof ItemSword
					&& ((ItemSword) var2).getToolMaterialName().equals("WOOD"))
				return 200;
			if (var2 instanceof ItemHoe
					&& ((ItemHoe) var2).getMaterialName().equals("WOOD"))
				return 200;
			if (var1 == Item.stick.itemID)
				return 100;
			if (var1 == Item.coal.itemID)
				return 1600;
			if (var1 == Item.bucketLava.itemID)
				return 20000;
			if (var1 == Block.sapling.blockID)
				return 100;
			if (var1 == Item.blazeRod.itemID)
				return 2400;
			return GameRegistry.getFuelValue(par0ItemStack);
		}

	}
}
