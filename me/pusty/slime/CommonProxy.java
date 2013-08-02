package me.pusty.slime;


import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy  implements IGuiHandler{

	
	public void registerRenderers(){}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
       return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	public void registerRenderInformation() {
		// TODO Auto-generated method stub
		
	}

	public void registerTileEntitySpecialRenderer(Class tileEntityClass,Class d) {
		// TODO Auto-generated method stub
		
	}



}
