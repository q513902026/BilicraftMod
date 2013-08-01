package bilicraft.mods.scp.block.container;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import bilicraft.mods.core.block.container.BCContainer;
import bilicraft.mods.scp.tileentity.SCP166TileEntity;

public class SCP166Container extends BCContainer {
	private SCP166TileEntity te;
	
	private int lastTableBurnTime = 0;
	private int lastMaxBurnTime = 0;
	
	public SCP166Container(InventoryPlayer par1InventoryPlayer,
			SCP166TileEntity par2te) {
		this.te = par2te;
		this.addSlotToContainer(new Slot(par2te, 0, 49, 19));
		this.addSlotToContainer(new Slot(par2te, 1, 112, 19));
		
		
		//添加玩家背包
		int var3;
		for (var3 = 0; var3 < 3; ++var3) {
			for (int var4 = 0; var4 < 9; ++var4) {
				this.addSlotToContainer(new Slot(par1InventoryPlayer, var4
						+ var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}

		for (var3 = 0; var3 < 9; ++var3) {
			this.addSlotToContainer(new Slot(par1InventoryPlayer, var3,
					8 + var3 * 18, 142));
		}
	}
	
	@Override
    public void addCraftingToCrafters(ICrafting par1iCrafting) {
            // TODO Auto-generated method stub
            super.addCraftingToCrafters(par1iCrafting);
            par1iCrafting.sendProgressBarUpdate(this, 0, this.te.tableBurnTime);
            par1iCrafting.sendProgressBarUpdate(this, 1, this.te.maxBurnTime);
    }
	public void updateProgressBar(int par1, int par2)
    {
                if (par1 == 0)
        {
            this.te.tableBurnTime = par2;
        }
        if (par1 == 1)
        {
            this.te.maxBurnTime = par2;
        }
    }
        @Override
    public void detectAndSendChanges()
    {
        // TODO Auto-generated method stub
        super.detectAndSendChanges();
        Iterator var1 = this.crafters.iterator();
        while (var1.hasNext())
        {
            ICrafting var2 = (ICrafting)var1.next();

            if (this.lastTableBurnTime != this.te.tableBurnTime)
            {
                var2.sendProgressBarUpdate(this, 0, this.te.tableBurnTime);
            }

            if (this.lastMaxBurnTime  != this.te.maxBurnTime)
            {
                var2.sendProgressBarUpdate(this, 1, this.te.maxBurnTime);
            }
        }
        this.lastTableBurnTime = this.te.tableBurnTime;
        this.lastMaxBurnTime = this.te.maxBurnTime;
    }
        @Override
        public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
        {
            ItemStack var3 = null;
            Slot var4 = (Slot)this.inventorySlots.get(par2);

            if (var4 != null && var4.getHasStack())
            {
                ItemStack var5 = var4.getStack();
                var3 = var5.copy();
                // 点击到Slot的ID为0-2之间的时候，将物品送回玩家的背包中
                if (par2 >= 0 && par2 <= 5)
                {
                    if (!this.mergeItemStack(var5, 3, 30, false))
                    {
                        return null;
                    }

                    var4.onSlotChange(var5, var3);
                }
                // 点击到玩家的背包的时候将物品送到玩家的快捷栏中
                else if (par2 > 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(var5, 30, 39, false))
                    {
                        return null;
                    }
                }
                // 点击到玩家的快捷栏的时候将物品送到背包中
                else if (par2 >= 30 && par2 < 39)
                {
                    if (!this.mergeItemStack(var5, 3, 30, false))
                    {
                        return null;
                    }
                }

                if (var5.stackSize == 0)
                {
                    var4.putStack((ItemStack)null);
                }
                else
                {
                    var4.onSlotChanged();
                }

                if (var5.stackSize == var3.stackSize)
                {
                    return null;
                }

                var4.onPickupFromSlot(par1EntityPlayer, var5);
            }

            return var3;
        }
}
