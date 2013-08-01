package bilicraft.mods.scp.client.gui;

import org.lwjgl.opengl.GL11;

import bilicraft.mods.core.proxy.Props;
import bilicraft.mods.scp.tileentity.SCP166TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class SCP166Gui extends GuiContainer {

	private SCP166TileEntity te;

	public SCP166Gui(Container par1Container, SCP166TileEntity te) {
		super(par1Container);
		// TODO 自动生成的构造函数存根
		this.te = te;
		this.doesGuiPauseGame();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		super.drawGuiContainerForegroundLayer(par1, par2);
		this.fontRenderer.drawString(StatCollector.translateToLocal("SCP166"),
				65, 6, 4210752);
		this.fontRenderer.drawString(
				StatCollector.translateToLocal("container.inventory.scp166"),
				8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		// TODO 自动生成的方法存根
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(Props.GUI_PATH + "SCP166.png");
		int var1 =  (this.width - this.xSize) / 2;
		int var2 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var1, var2, 0, 0, this.xSize, this.ySize);
		
		int b = te.tableBurnTime; // 
        float maxBurnTime = te.maxBurnTime*1.0F;// 
        if (b > 0 && maxBurnTime > 0) // 
        {
                // 描绘火焰图像
            this.drawTexturedModalRect(this.guiLeft + 81, this.guiTop + 37 + (int)(14 - 14 * ((float)b / maxBurnTime)), 176, (int)(14 - 14 * ((float)b / maxBurnTime)), 14, (int)(14 * ((float)b / maxBurnTime)));
	}
	}
}

