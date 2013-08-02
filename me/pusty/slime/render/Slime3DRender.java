package me.pusty.slime.render;


import me.pusty.slime.SlimeItem;
import me.pusty.slime.SlimeTypes;
import me.pusty.slime.model.PustySlimeModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class Slime3DRender implements IItemRenderer {

	protected PustySlimeModel slimeModel  = new PustySlimeModel();
	static RenderItem renderItem = new RenderItem();
	/*public Slime3DRender(){
		slimeModel = new SlimeModel();
	}*/
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type)
		{
		case EQUIPPED: return true;
		case EQUIPPED_FIRST_PERSON: return true;
		case INVENTORY: return true;
		case ENTITY: return true;
		default: return false;
		
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack itemStack, Object... data) {




		
		  if(type.equals(ItemRenderType.INVENTORY))
		  {
		         FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
	            // ====================== Render item texture ======================
		         if(itemStack.getItemDamage()==16)
		        	 return;

						  GL11.glColor4d(SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getRed(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getGreen(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getBlue(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getAlpha());			
						    renderItem.renderIcon(0, 0, SlimeItem.icon, 16, 16);
				            GL11.glColor4d(SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getRed(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getGreen(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getBlue(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getAlpha());
				            renderItem.renderIcon(0, 0, SlimeItem.icon2, 16, 16);
				 	       GL11.glColor3d(0, 0, 0);	   
			               renderItem.renderIcon(0, 0, SlimeItem.icon3, 16, 16);
					  
				  

                
	         
			  return;
		  }
		  
		  if(type.equals(ItemRenderType.ENTITY))
		  {
			  Minecraft.getMinecraft().renderEngine.func_110577_a(new ResourceLocation("PustySlime:textures/slime/pustyslime.png"));
			   GL11.glPushMatrix();  
		       GL11.glScaled(0.5F, 0.5F, 0.5F);
			  GL11.glTranslated(0, 0, 0);  
		
		         if(itemStack.getItemDamage()==16)
		         {
		  	       GL11.glPopMatrix();
		        	 return;
		         }
		         
			  if(SlimeTypes.list.get(itemStack.getItemDamage()).effect==1){
			         /*GL11.glDepthMask(false);
		            GL11.glEnable(GL11.GL_BLEND);
		            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);*/
			            GL11.glColor4f(SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getRed(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getGreen(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getBlue(), 0.25F);
			            	 slimeModel.renderModel( 0.0625F,0);  
			            GL11.glColor4f(SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getRed(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getGreen(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getBlue(), 0.25F);
					       slimeModel.renderModel(0.0625F,1);     
				 	       GL11.glColor4d(0,0,0,0.25F);
			 	      slimeModel.renderModel(0.0625F,2);
					   
				  }else if(SlimeTypes.list.get(itemStack.getItemDamage()).effect==0){
			            
				  
					  GL11.glColor4d(SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getRed(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getGreen(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getBlue(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getAlpha());
				       slimeModel.renderModel( 0.0625F,0);
				       GL11.glColor4d(SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getRed(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getGreen(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getBlue(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getAlpha());
				       slimeModel.renderModel(0.0625F,1);
				       GL11.glColor3d(0, 0, 0);
				       slimeModel.renderModel(0.0625F,2);
				  }

		       
		      
		       GL11.glPopMatrix();
			  return;
		  }
		if(data.length<2)
		{
		       return;
			   
		}
		
		
		  Minecraft.getMinecraft().renderEngine.func_110577_a(new ResourceLocation("PustySlime:textures/slime/pustyslime.png"));
	  if(data[1] != null && data[1] instanceof EntityPlayer){
		   GL11.glPushMatrix();

		  if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
		  {
		   //   GL11.glTranslated(0.7, 0.2, 0);  

			  
		       GL11.glScaled(0.5F, 0.5F, 0.5F);
			  GL11.glTranslated(2, 0, 0);  
		         if(itemStack.getItemDamage()==16)
		         {
		  	       GL11.glPopMatrix();
		        	 return;
		         }
		         
			  if(SlimeTypes.list.get(itemStack.getItemDamage()).effect==1){
			         /*GL11.glDepthMask(false);
		            GL11.glEnable(GL11.GL_BLEND);
		            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);*/
			            GL11.glColor4f(SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getRed(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getGreen(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getBlue(), 0.25F);
			            	 slimeModel.renderModel( 0.0625F,0);  
			            GL11.glColor4f(SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getRed(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getGreen(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getBlue(), 0.25F);
					       slimeModel.renderModel(0.0625F,1);     
				 	       GL11.glColor4d(0,0,0,0.25F);
			 	      slimeModel.renderModel(0.0625F,2);
					   
				   
			  }else if(SlimeTypes.list.get(itemStack.getItemDamage()).effect==0){
		            
		      GL11.glColor4d(SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getRed(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getGreen(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getBlue(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getAlpha());
		       slimeModel.render((Entity)data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625F,0);
		       GL11.glColor4d(SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getRed(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getGreen(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getBlue(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getAlpha());
			     slimeModel.render((Entity)data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625F,1);
		       GL11.glColor3d(0, 0, 0);
		       slimeModel.render((Entity)data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625F,2);
			  }
		       
			  
		//	  GL11.glColor3d(160, 0, 0);


	
		  }else{
		       GL11.glScaled(1, 1, 1);
			  GL11.glTranslated(2, 0, 0);  
		         if(itemStack.getItemDamage()==16)
		         {
		  	       GL11.glPopMatrix();
		        	 return;
		         }
			  if(SlimeTypes.list.get(itemStack.getItemDamage()).effect==1){
			         /*GL11.glDepthMask(false);
		            GL11.glEnable(GL11.GL_BLEND);
		            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);*/
			            GL11.glColor4f(SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getRed(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getGreen(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getBlue(), 0.15F);
			            slimeModel.render((Entity)data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625F,0);
			            GL11.glColor4f(SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getRed(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getGreen(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getBlue(), 0.15F);
			            slimeModel.render((Entity)data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625F,1);
			 	       GL11.glColor4d(0,0,0,0.15F);
				       slimeModel.render((Entity)data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625F,2);
					   
				  }else if(SlimeTypes.list.get(itemStack.getItemDamage()).effect==0){
			            
			      GL11.glColor4d(SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getRed(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getGreen(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getBlue(),SlimeTypes.list.get(itemStack.getItemDamage()).bcolor.getAlpha());
			       slimeModel.render((Entity)data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625F,0);
			       GL11.glColor4d(SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getRed(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getGreen(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getBlue(),SlimeTypes.list.get(itemStack.getItemDamage()).rcolor.getAlpha());
				     slimeModel.render((Entity)data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625F,1);
			       GL11.glColor3d(0, 0, 0);
			       slimeModel.render((Entity)data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625F,2);
				  }

      }

	       GL11.glPopMatrix();
			   
            
		  
	  }else
	  {

  
	  }
      
     //  GL11.glTranslated(0, 0, 0);
       // GL11.glRotatef(80F, 1,0,0);


	  
	


       //GL11.glTranslated(x, y, z)


   
	}
   
}