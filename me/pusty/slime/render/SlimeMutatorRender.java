package me.pusty.slime.render;
 
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

import me.pusty.slime.PustyMutatorTileEntity;
import me.pusty.slime.PustyObjectTileEntity;
import me.pusty.slime.SlimeItem;
import me.pusty.slime.SlimeTypes;
import me.pusty.slime.model.PustyObjectModel;
import me.pusty.slime.model.PustySlimeModel;
import me.pusty.slime.mutater.TileEntityMutator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
 
public class SlimeMutatorRender extends TileEntitySpecialRenderer
{ 
    private PustyObjectModel aModel;
    private PustySlimeModel bModel;

    public SlimeMutatorRender()
    {
        aModel = new PustyObjectModel();
        bModel  = new PustySlimeModel();
    }
       
    public void renderAModelAt(TileEntityMutator tileentity1, double d, double d1, double d2, float f)
    {  
    	
         int meta = tileentity1.getWorldObj().getBlockMetadata(tileentity1.xCoord, tileentity1.yCoord,  tileentity1.zCoord);
         

         
      //  meta = tileentity1.shownItem!=null?(tileentity1.shownItem.getItem() instanceof SlimeItem?tileentity1.shownItem.getItemDamage():0):0;

        GL11.glPushMatrix();
        GL11.glTranslatef((float)d + 0.5F, (float)d1-0.5F, (float)d2 + 0.5F);

		  Minecraft.getMinecraft().renderEngine.func_110577_a(new ResourceLocation("PustySlime:textures/slime/pustymutator.png"));
        	
        GL11.glPushMatrix();
        aModel.renderModel(0.0625F,true);
   //     GL11.glPopMatrix();    
       // System.out.println(meta);
        if(meta!=0){
		  Minecraft.getMinecraft().renderEngine.func_110577_a(new ResourceLocation("PustySlime:textures/slime/pustyslime.png"));
		  GL11.glScaled(0.5, 0.5, 0.5);
		  GL11.glTranslated(0, 1, 0);
		  GL11.glRotated(90, 0, 180, 0);
		  if(SlimeTypes.list.get(meta).effect==1){
		         /*GL11.glDepthMask(false);
		            GL11.glEnable(GL11.GL_BLEND);
		            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);*/
		            GL11.glColor4f(SlimeTypes.list.get(meta).bcolor.getRed(),SlimeTypes.list.get(meta).bcolor.getGreen(),SlimeTypes.list.get(meta).bcolor.getBlue(), 0.25F);
		            bModel.renderModel( 0.0625F,0);  
		            GL11.glColor4f(SlimeTypes.list.get(meta).rcolor.getRed(),SlimeTypes.list.get(meta).rcolor.getGreen(),SlimeTypes.list.get(meta).rcolor.getBlue(), 0.25F);
		            bModel.renderModel(0.0625F,1);     
			 	       GL11.glColor4d(0,0,0,0.25F);
			 	      bModel.renderModel(0.0625F,2);
				   
			  }else if(SlimeTypes.list.get(meta).effect==0){
				  GL11.glColor4d(SlimeTypes.list.get(meta).bcolor.getRed(),SlimeTypes.list.get(meta).bcolor.getGreen(),SlimeTypes.list.get(meta).bcolor.getBlue(),SlimeTypes.list.get(meta).bcolor.getAlpha());
				  bModel.renderModel( 0.0625F,0);
		          GL11.glColor4d(SlimeTypes.list.get(meta).rcolor.getRed(),SlimeTypes.list.get(meta).rcolor.getGreen(),SlimeTypes.list.get(meta).rcolor.getBlue(),SlimeTypes.list.get(meta).rcolor.getAlpha());
		       bModel.renderModel(0.0625F,1);
			       GL11.glColor3d(0, 0, 0);
			       bModel.renderModel(0.0625F,2);
			  }
        }
	       GL11.glPopMatrix();    
        
        GL11.glPopMatrix();     
        
        


        
        
    }
 


	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4,
			double var6, float var8) {
		
        renderAModelAt((TileEntityMutator)var1, var2, var4, var6, var8);
		
	}

}