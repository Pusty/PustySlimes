package me.pusty.slime;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class SlimeKnowledgeBookGui extends GuiScreen

{
    private static final ResourceLocation texture = new ResourceLocation("PustySlime:textures/gui/knowledgebook.png");
    /** Stacks renderer. Icons, stack size, health, etc... */
    protected static RenderItem itemRenderer = new RenderItem();
    public final int xSizeOfTexture = 256;
    
    public final int ySizeOfTexture = 188;
    
    public boolean[] side = new boolean[6];
    public String[] sideText = new String[6];
    
    public int sides = 0;
    public int mside = 0;
    boolean useable = false;

    EntityPlayer p;

    public SlimeKnowledgeBookGui(EntityPlayer p)
    {
        super();

       this.p = p;
       for(int i=0;i<side.length;i++)
       side[i]=SlimeKnowledgeItem.getPage(p.getCurrentItemOrArmor(0),i);
       for(int i=0;i<sideText.length;i++)
    	   sideText[i]=SlimeKnowledgeItem.getPageString(p.getCurrentItemOrArmor(0),i);

    }

    public void initGui()
    {

    this.buttonList.clear();

   
    for(int i=0;i<side.length;i++)
    side[i]=SlimeKnowledgeItem.getPage(p.getCurrentItemOrArmor(0),i);
    
    for(int i=0;i<sideText.length;i++)
 	   sideText[i]=SlimeKnowledgeItem.getPageString(p.getCurrentItemOrArmor(0),i);
    
    for(int i=0;i<side.length;i++)
    SlimeKnowledgeItem.setPage(p.getCurrentItemOrArmor(0), i,side[i]);
    
    for(int i=0;i<sideText.length;i++)
    SlimeKnowledgeItem.setPageString(p.getCurrentItemOrArmor(0), i,sideText[i]);
    

	
    if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT)){
    ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
      DataOutputStream outputStream = new DataOutputStream(bos);
      try {
          outputStream.writeInt(9);  
          outputStream.writeInt(0);  
          outputStream.writeInt(0);  
          outputStream.writeInt(0);  
          outputStream.writeInt(0);  
          for(int i=0;i<side.length;i++){
          outputStream.writeBoolean(side[i]);
          outputStream.writeUTF(sideText[i]);
          }
            
      } catch (Exception ex) {
              ex.printStackTrace();
      }
     
      Packet250CustomPayload pt = new Packet250CustomPayload();
      pt.channel = "PustySlime";
      pt.data = bos.toByteArray();
      pt.length = bos.size();
    PacketDispatcher.sendPacketToServer(pt);
    }
    
    
	
	
    int posX = (this.width - xSizeOfTexture) / 2;
    int posY = (this.height - ySizeOfTexture) / 2;

    this.buttonList.add(new GuiButton(0, posX+ 3,  posY + (20*1)+3, 45, 20, "Use"));
    this.buttonList.add(new GuiButton(1, posX+ 3, posY + (20*2)+3 , 45, 20, "Use"));	
    this.buttonList.add(new GuiButton(2, posX+ 3, posY + (20*3)+3 , 45, 20, "Use"));	
    this.buttonList.add(new GuiButton(3, posX+ 3, posY + (20*4)+3 , 45, 20, "Use"));	
    this.buttonList.add(new GuiButton(4, posX+ 3, posY + (20*5)+3 , 45, 20, "Use"));	
    this.buttonList.add(new GuiButton(5, posX+ 3, posY + (20*6)+3 , 45, 20, "Use"));	
    this.buttonList.add(new GuiButton(7, posX+ 3, posY + (20*7)+3 , 45, 20, "Close"));	
    
    
    for(int i=0;i<side.length;i++){
    	if(side[i] == true)
    	    ((GuiButton)this.buttonList.get(i)).enabled = false;
    }
    

    }
    
    public String getCurrentPlace(){
    	
    	
    	if(p.lastTickPosY>128)
            return "Air";
    	else if(p.lastTickPosY<8)
            return "Bedrock";
    	else if(p.worldObj.getBiomeGenForCoords((int)p.lastTickPosX,(int) p.lastTickPosZ).equals(BiomeGenBase.plains) || p.worldObj.getBiomeGenForCoords((int)p.lastTickPosX,(int) p.lastTickPosZ).equals(BiomeGenBase.forest) || p.worldObj.getBiomeGenForCoords((int)p.lastTickPosX,(int) p.lastTickPosZ).equals(BiomeGenBase.forestHills))
            return "Plant";
    	else if(p.worldObj.getBiomeGenForCoords((int)p.lastTickPosX,(int) p.lastTickPosZ).equals(BiomeGenBase.river) || p.worldObj.getBiomeGenForCoords((int)p.lastTickPosX,(int) p.lastTickPosZ).equals(BiomeGenBase.ocean) || p.worldObj.getBiomeGenForCoords((int)p.lastTickPosX,(int) p.lastTickPosZ).equals(BiomeGenBase.frozenRiver) || p.worldObj.getBiomeGenForCoords((int)p.lastTickPosX,(int) p.lastTickPosZ).equals(BiomeGenBase.frozenOcean))
        return "Water";
    	else if(p.worldObj.getBiomeGenForCoords((int)p.lastTickPosX,(int) p.lastTickPosZ).equals(BiomeGenBase.mushroomIsland))
            return "Rare";
    	else if(p.worldObj.getBiomeGenForCoords((int)p.lastTickPosX,(int) p.lastTickPosZ).temperature > 1.1)
            return "Fire";
    	else if(p.worldObj.getBiomeGenForCoords((int)p.lastTickPosX,(int) p.lastTickPosZ).temperature < 0.1)
            return "Water";
    	else if(p.worldObj.getBiomeGenForCoords((int)p.lastTickPosX,(int) p.lastTickPosZ).isHighHumidity())
            return "Water";
    	else if(p.worldObj.getBiomeGenForCoords((int)p.lastTickPosX,(int) p.lastTickPosZ).getEnableSnow())
            return "Water";
    	else if(!p.worldObj.getBiomeGenForCoords((int)p.lastTickPosX,(int) p.lastTickPosZ).canSpawnLightningBolt())
            return "Air";
    	else if(p.isInsideOfMaterial(Material.sand))
            return "Rare"; 
    	else
    		return "Normal";

    }
    
    public void actionPerformed(GuiButton button)
    {
    switch(button.id)
    {
    case 7:
    {
    	this.mc.thePlayer.closeScreen(); 
    }
    break;
    default:
    	if(getCurrentPlace().equalsIgnoreCase("null"))
    		break;
    	side[button.id] = true;
    	sideText[button.id] = getCurrentPlace();
    	
    	

        for(int i=0;i<side.length;i++)
            SlimeKnowledgeItem.setPage(p.getCurrentItemOrArmor(0), i,side[i]);
            
        for(int i=0;i<sideText.length;i++)
            SlimeKnowledgeItem.setPageString(p.getCurrentItemOrArmor(0), i,sideText[i]);
        

    	
    	
        	
            if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT)){
            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
              DataOutputStream outputStream = new DataOutputStream(bos);
              try {
                  outputStream.writeInt(9);  
                  outputStream.writeInt(0);  
                  outputStream.writeInt(0);  
                  outputStream.writeInt(0);  
                  outputStream.writeInt(0);  
                  for(int i=0;i<side.length;i++){
                  outputStream.writeBoolean(side[i]);
                  outputStream.writeUTF(sideText[i]);
                  }
                    
              } catch (Exception ex) {
                      ex.printStackTrace();
              }
             
              Packet250CustomPayload pt = new Packet250CustomPayload();
              pt.channel = "PustySlime";
              pt.data = bos.toByteArray();
              pt.length = bos.size();
            PacketDispatcher.sendPacketToServer(pt);
            }
            
            
            
            
            
            int side2=0;
            for(int i=0;i<side.length;i++)
                if(side[i])
                	side2++;
          
        		if(side2 > 5 && SlimeKnowledgeItem.getSet(sideText) != null){
        			
        			p.setCurrentItemOrArmor(0, SlimeKnowledgeItem.getSet(sideText).item);
        		  	this.mc.thePlayer.closeScreen(); 
        			
                    if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT)){
                        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                          DataOutputStream outputStream = new DataOutputStream(bos);
                          try {
                              outputStream.writeInt(10);  
                              outputStream.writeInt(0);  
                              outputStream.writeInt(0);  
                              outputStream.writeInt(0);  
                              outputStream.writeInt(KnowledgeSet.list.indexOf(SlimeKnowledgeItem.getSet(sideText)));  

                              
                                
                          } catch (Exception ex) {
                                  ex.printStackTrace();
                          }
                         
                          Packet250CustomPayload pt = new Packet250CustomPayload();
                          pt.channel = "PustySlime";
                          pt.data = bos.toByteArray();
                          pt.length = bos.size();
                        PacketDispatcher.sendPacketToServer(pt);
                        }
        		}
        		
        		
        		
            initGui();
    	
    break;
    }
    }
    
    @Override
    protected void keyTyped(char par1, int par2)
    {
    if (par2 == 1 || par2 == this.mc.gameSettings.keyBindInventory.keyCode)
    {
    this.mc.thePlayer.closeScreen();
    }
    }
    
    public boolean doesGuiPauseGame(){
    	return false;
    }
    @Override
    public void drawScreen(int x, int y, float f)
    {
    drawDefaultBackground();
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    this.mc.func_110434_K().func_110577_a(texture);
    int posX = (this.width - xSizeOfTexture) / 2;
    int posY = (this.height - ySizeOfTexture) / 2;


    drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);
    
    FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
    
    	drawPage(x,y,f);



    super.drawScreen(x, y, f);
    }
    
    
    public void drawPage(int x,int y, float f){
    	  
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;
        
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        sides = 0;
        for(int i=0;i<side.length;i++)
        {
        	if(side[i] == true)
        	 sides++;
        }
        
        
        
        int px[] = {96,176,96,176,96,176};
        int py[] = {20,20,54,54,92,92};
        for(int i=0;i<side.length;i++)
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag(sideText[i]), posX+px[i], posY+py[i], 0xFFFFFF);
        
    }
    

    public void renderItem(ItemStack item,int x,int y){
        this.zLevel = 100.0F;
        itemRenderer.zLevel = 100.0F;
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        ItemStack itemstack = item;
        itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.func_110434_K(), itemstack, x, y);
        itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.func_110434_K(), itemstack, x, y);
        GL11.glDisable(GL11.GL_LIGHTING);
        itemRenderer.zLevel = 0.0F;
        this.zLevel = 0.0F;
    }
}





