package me.pusty.slime;


import java.util.Random;

import cpw.mods.fml.client.registry.ClientRegistry;

import me.pusty.slime.generator.PustyGeneratorTileEntity;
import me.pusty.slime.generator.SlimeGeneratorRender;
import me.pusty.slime.generator.TileEntityGenerator;
import me.pusty.slime.mutater.TileEntityMutator;
import me.pusty.slime.object.TileEntityObject;
import me.pusty.slime.render.Slime3DRender;
import me.pusty.slime.render.SlimeMutatorRender;
import me.pusty.slime.render.SlimeObjectRender;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers(){
		//MinecraftForgeClient.preloadTexture(ITEM_PNG);
		//MinecraftForge.preloadTexture(BLOCK_PNG);
		MinecraftForgeClient.registerItemRenderer(PustySlime.slimeItem.itemID, new Slime3DRender());

	}
	


	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
     return null; 
	}
	

		
	
    @Override
	public void registerRenderInformation() {
    	SlimeObjectRender tileent1 = new SlimeObjectRender();
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityObject.class, tileent1);
    	SlimeMutatorRender tileent2 = new SlimeMutatorRender();
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMutator.class, tileent2);
    	SlimeGeneratorRender tileent3 = new SlimeGeneratorRender();
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGenerator.class, tileent3);
		
	}
	
		
}
