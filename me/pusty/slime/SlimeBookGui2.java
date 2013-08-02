package me.pusty.slime;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class SlimeBookGui2 extends GuiScreen

{
    private static final ResourceLocation texture = new ResourceLocation("PustySlime:textures/gui/slimebook.png");
    /** Stacks renderer. Icons, stack size, health, etc... */
    protected static RenderItem itemRenderer = new RenderItem();
    public final int xSizeOfTexture = 256;
    
    public final int ySizeOfTexture = 188;
    public int side = 0;
    public int mside = 0;
    boolean useable = false;

    EntityPlayer p;

    public SlimeBookGui2(EntityPlayer p)
    {
        super();

       this.p = p;
       side=  SlimeBookItem.getPage(p.getCurrentItemOrArmor(0));
    }

    public void initGui()
    {

    this.buttonList.clear();

   
	side =  SlimeBookItem.getPage(p.getCurrentItemOrArmor(0));

	SlimeBookItem.setPage(p.getCurrentItemOrArmor(0), side);
	
    if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT)){
    ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
      DataOutputStream outputStream = new DataOutputStream(bos);
      try {
          outputStream.writeInt(8);  
            outputStream.writeInt(0);  
            outputStream.writeInt(0);  
            outputStream.writeInt(0);  
              outputStream.writeInt(side);  
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

    this.buttonList.add(new GuiButton(0, posX+3, posY +3, 45, 20, "Page 1"));
    this.buttonList.add(new GuiButton(1, posX+ 3, posY + 20+3 , 45, 20, "Page 2"));	
    this.buttonList.add(new GuiButton(2, posX+ 3, posY + (20*2)+3 , 45, 20, "Page 3"));	
    this.buttonList.add(new GuiButton(3, posX+ 3, posY + (20*3)+3 , 45, 20, "Page 4"));	
    this.buttonList.add(new GuiButton(4, posX+ 3, posY + (20*4)+3 , 45, 20, "Page 5"));	
    this.buttonList.add(new GuiButton(5, posX+ 3, posY + (20*5)+3 , 45, 20, "Page 6"));	
    this.buttonList.add(new GuiButton(7, posX+ 3, posY + (20*7)+3 , 45, 20, "Close"));	
    
    ((GuiButton)this.buttonList.get(side)).enabled = false;
    
    }
    
    
    public void actionPerformed(GuiButton button)
    {
    switch(button.id)
    {
    case 0:
    {
    	
    	side = 0;
    	SlimeBookItem.setPage(p.getCurrentItemOrArmor(0),side);
        if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT)){
            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
              DataOutputStream outputStream = new DataOutputStream(bos);
              try {
                  outputStream.writeInt(8);  
                    outputStream.writeInt(0);  
                    outputStream.writeInt(0);  
                    outputStream.writeInt(0);  
                      outputStream.writeInt(side);  
              } catch (Exception ex) {
                      ex.printStackTrace();
              }
             
              Packet250CustomPayload pt = new Packet250CustomPayload();
              pt.channel = "PustySlime";
              pt.data = bos.toByteArray();
              pt.length = bos.size();
            PacketDispatcher.sendPacketToServer(pt);
            }
        this.initGui();

    }
    break;
    case 1:
    {
    	side = 1;
    	SlimeBookItem.setPage(p.getCurrentItemOrArmor(0),side);
        if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT)){
            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
              DataOutputStream outputStream = new DataOutputStream(bos);
              try {
                  outputStream.writeInt(8);  
                    outputStream.writeInt(0);  
                    outputStream.writeInt(0);  
                    outputStream.writeInt(0);  
                      outputStream.writeInt(side);  
              } catch (Exception ex) {
                      ex.printStackTrace();
              }
             
              Packet250CustomPayload pt = new Packet250CustomPayload();
              pt.channel = "PustySlime";
              pt.data = bos.toByteArray();
              pt.length = bos.size();
            PacketDispatcher.sendPacketToServer(pt);
            }
        this.initGui();
    }
    break;
    case 2:
    {
    	side = 2;
    	SlimeBookItem.setPage(p.getCurrentItemOrArmor(0),side);
        if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT)){
            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
              DataOutputStream outputStream = new DataOutputStream(bos);
              try {
                  outputStream.writeInt(8);  
                    outputStream.writeInt(0);  
                    outputStream.writeInt(0);  
                    outputStream.writeInt(0);  
                      outputStream.writeInt(side);  
              } catch (Exception ex) {
                      ex.printStackTrace();
              }
             
              Packet250CustomPayload pt = new Packet250CustomPayload();
              pt.channel = "PustySlime";
              pt.data = bos.toByteArray();
              pt.length = bos.size();
            PacketDispatcher.sendPacketToServer(pt);
            }
        this.initGui();
    }
    break;
    case 3:
    {
    	side = 3;
    	SlimeBookItem.setPage(p.getCurrentItemOrArmor(0),side);
        if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT)){
            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
              DataOutputStream outputStream = new DataOutputStream(bos);
              try {
                  outputStream.writeInt(8);  
                    outputStream.writeInt(0);  
                    outputStream.writeInt(0);  
                    outputStream.writeInt(0);  
                      outputStream.writeInt(side);  
              } catch (Exception ex) {
                      ex.printStackTrace();
              }
             
              Packet250CustomPayload pt = new Packet250CustomPayload();
              pt.channel = "PustySlime";
              pt.data = bos.toByteArray();
              pt.length = bos.size();
            PacketDispatcher.sendPacketToServer(pt);
            }
        this.initGui();
    }
    break;
    case 4:
    {
    	side = 4;
    	SlimeBookItem.setPage(p.getCurrentItemOrArmor(0),side);
        if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT)){
            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
              DataOutputStream outputStream = new DataOutputStream(bos);
              try {
                  outputStream.writeInt(8);  
                    outputStream.writeInt(0);  
                    outputStream.writeInt(0);  
                    outputStream.writeInt(0);  
                      outputStream.writeInt(side);  
              } catch (Exception ex) {
                      ex.printStackTrace();
              }
             
              Packet250CustomPayload pt = new Packet250CustomPayload();
              pt.channel = "PustySlime";
              pt.data = bos.toByteArray();
              pt.length = bos.size();
            PacketDispatcher.sendPacketToServer(pt);
            }
        this.initGui();
    }
    break;
    case 5:
    {
    	side = 5;
    	SlimeBookItem.setPage(p.getCurrentItemOrArmor(0),side);
        if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT)){
            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
              DataOutputStream outputStream = new DataOutputStream(bos);
              try {
                  outputStream.writeInt(8);  
                    outputStream.writeInt(0);  
                    outputStream.writeInt(0);  
                    outputStream.writeInt(0);  
                      outputStream.writeInt(side);  
              } catch (Exception ex) {
                      ex.printStackTrace();
              }
             
              Packet250CustomPayload pt = new Packet250CustomPayload();
              pt.channel = "PustySlime";
              pt.data = bos.toByteArray();
              pt.length = bos.size();
            PacketDispatcher.sendPacketToServer(pt);
            }
        this.initGui();
    }
    break;
    case 6:
    {
    	side = 6;
    	SlimeBookItem.setPage(p.getCurrentItemOrArmor(0),side);
        if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT)){
            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
              DataOutputStream outputStream = new DataOutputStream(bos);
              try {
                  outputStream.writeInt(8);  
                    outputStream.writeInt(0);  
                    outputStream.writeInt(0);  
                    outputStream.writeInt(0);  
                      outputStream.writeInt(side);  
              } catch (Exception ex) {
                      ex.printStackTrace();
              }
             
              Packet250CustomPayload pt = new Packet250CustomPayload();
              pt.channel = "PustySlime";
              pt.data = bos.toByteArray();
              pt.length = bos.size();
            PacketDispatcher.sendPacketToServer(pt);
            }
        this.initGui();
    }
    break;
    case 7:
    {
    	this.mc.thePlayer.closeScreen(); 
    }
    break;
    default:
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
    this.drawString(fontRenderer, "Page: "+(side+1), posX+xSizeOfTexture-50, posY+10, 0xFFFFFF);
    
    if(side==0)
    	drawPage1(x,y,f);
    else if(side==1)
    	drawPage2(x,y,f);
    else if(side==2)
    	drawPage3(x,y,f);
    else if(side==3)
    	drawPage4(x,y,f);
    else if(side==4)
    	drawPage5(x,y,f);
    else if(side==5)
    	drawPage6(x,y,f);



    super.drawScreen(x, y, f);
    }
    
    
    public void drawPage1(int x,int y, float f){
    	  
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;
        
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        this.drawString(fontRenderer, "Basic Slime", posX+xSizeOfTexture/2-20, posY+10, 0xFFFFFF);
        
        this.drawString(fontRenderer, "This book is about the Basic Slime", posX+45+15, posY+25, 0xFFFFFF);
        this.drawString(fontRenderer, "and the Main Slime.", posX+45+15, posY+35, 0xFFFFFF);
        this.drawString(fontRenderer, "It will tell you everything you need", posX+45+15, posY+45, 0xFFFFFF);
        this.drawString(fontRenderer, "to start with Slimes", posX+45+15, posY+55, 0xFFFFFF);
        this.drawString(fontRenderer, "      -Pusty", posX+45+15, posY+65, 0xFFFFFF);
        
    }
    
    public void drawPage2(int x,int y, float f){
    	  
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;
        
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        this.drawString(fontRenderer, "Infomations", posX+xSizeOfTexture/2-20, posY+10, 0xFFFFFF);
        
        this.drawString(fontRenderer, "The Basic Slime will produce", posX+45+15, posY+25, 0xFFFFFF);
        this.drawString(fontRenderer, "normal Slimeballs when you", posX+45+15, posY+35, 0xFFFFFF);
        this.drawString(fontRenderer, "put it in a Slime Generator", posX+45+15, posY+45, 0xFFFFFF);
        this.drawString(fontRenderer, "so its a good source of", posX+45+15, posY+55, 0xFFFFFF);
        this.drawString(fontRenderer, "SlimeBalls.", posX+45+15, posY+65, 0xFFFFFF);
        this.drawString(fontRenderer, "You need to mutate the slime", posX+45+15, posY+75, 0xFFFFFF);
        this.drawString(fontRenderer, "a Main-Mutator,in the first book", posX+45+15, posY+85, 0xFFFFFF);
        this.drawString(fontRenderer, "is the Knowledge Book recipe", posX+45+15, posY+95, 0xFFFFFF);
        this.drawString(fontRenderer, "for it.", posX+45+15, posY+105, 0xFFFFFF);
        this.drawString(fontRenderer, "Then you put it in a", posX+45+15, posY+115, 0xFFFFFF);
        this.drawString(fontRenderer, "SlimeMutator to mutate it", posX+45+15, posY+125, 0xFFFFFF);
        this.drawString(fontRenderer, "to a Main-Slime", posX+45+15, posY+135, 0xFFFFFF);
        
    }
    
    public void drawPage3(int x,int y, float f){
  	  
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;
        
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        this.drawString(fontRenderer, "Main Slime", posX+xSizeOfTexture/2-20, posY+10, 0xFFFFFF);
        
        this.drawString(fontRenderer, "The Main Slime will produce", posX+45+15, posY+25, 0xFFFFFF);
        this.drawString(fontRenderer, "'MainSlimeball's when you", posX+45+15, posY+35, 0xFFFFFF);
        this.drawString(fontRenderer, "put it in a Slime Generator.", posX+45+15, posY+45, 0xFFFFFF);
        this.drawString(fontRenderer, "This Slimeballs are used", posX+45+15, posY+55, 0xFFFFFF);
        this.drawString(fontRenderer, "to craft somestuff and", posX+45+15, posY+65, 0xFFFFFF);
        this.drawString(fontRenderer, "the block made of it", posX+45+15, posY+75, 0xFFFFFF);
        this.drawString(fontRenderer, "can be used for high jumping.", posX+45+15, posY+85, 0xFFFFFF);
        this.drawString(fontRenderer, "The Main-Slime can be mutated", posX+45+15, posY+95, 0xFFFFFF);
        this.drawString(fontRenderer, "with Blue,Green and Red", posX+45+15, posY+105, 0xFFFFFF);
        this.drawString(fontRenderer, "Mutators.", posX+45+15, posY+115, 0xFFFFFF);
  
        
    }
    
    
    public void drawPage4(int x,int y, float f){
    	  
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;
        
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        this.drawString(fontRenderer, "Mutations", posX+xSizeOfTexture/2-20, posY+10, 0xFFFFFF);
        
        
        int yxc = 0;
        renderItem(new ItemStack(PustySlime.slimeItem,1,18),posX+45+15,posY+35+yxc);
        this.drawString(fontRenderer, "+", posX+45+15+16,posY+35+4+yxc, 0xFFFFFF);
        renderItem(new ItemStack(PustySlime.slimeMutationItem,1,6),posX+45+15+16+5,posY+35+yxc);
        this.drawString(fontRenderer, "->", posX+45+15+16+5+16,posY+35+4+yxc, 0xFFFFFF);       
        renderItem(new ItemStack(PustySlime.slimeItem,1,4),posX+45+5+16+15+16+15,posY+35+yxc);
        
        yxc = 20;
        renderItem(new ItemStack(PustySlime.slimeItem,1,4),posX+45+15,posY+35+yxc);
        this.drawString(fontRenderer, "+", posX+45+15+16,posY+35+4+yxc, 0xFFFFFF);
        renderItem(new ItemStack(PustySlime.slimeMutationItem,1,1),posX+45+15+16+5,posY+35+yxc);
        this.drawString(fontRenderer, "->", posX+45+15+16+5+16,posY+35+4+yxc, 0xFFFFFF);       
        renderItem(new ItemStack(PustySlime.slimeItem,1,1),posX+45+5+16+15+16+15,posY+35+yxc);
        
        yxc = 40;
        renderItem(new ItemStack(PustySlime.slimeItem,1,4),posX+45+15,posY+35+yxc);
        this.drawString(fontRenderer, "+", posX+45+15+16,posY+35+4+yxc, 0xFFFFFF);
        renderItem(new ItemStack(PustySlime.slimeMutationItem,1,3),posX+45+15+16+5,posY+35+yxc);
        this.drawString(fontRenderer, "->", posX+45+15+16+5+16,posY+35+4+yxc, 0xFFFFFF);       
        renderItem(new ItemStack(PustySlime.slimeItem,1,2),posX+45+5+16+15+16+15,posY+35+yxc);
        
        yxc = 60;
        renderItem(new ItemStack(PustySlime.slimeItem,1,4),posX+45+15,posY+35+yxc);
        this.drawString(fontRenderer, "+", posX+45+15+16,posY+35+4+yxc, 0xFFFFFF);
        renderItem(new ItemStack(PustySlime.slimeMutationItem,1,2),posX+45+15+16+5,posY+35+yxc);
        this.drawString(fontRenderer, "->", posX+45+15+16+5+16,posY+35+4+yxc, 0xFFFFFF);       
        renderItem(new ItemStack(PustySlime.slimeItem,1,3),posX+45+5+16+15+16+15,posY+35+yxc);
        
        yxc = 80;
        renderItem(new ItemStack(PustySlime.slimeItem,1,4),posX+45+15,posY+35+yxc);
        this.drawString(fontRenderer, "+", posX+45+15+16,posY+35+4+yxc, 0xFFFFFF);
        renderItem(new ItemStack(PustySlime.slimeMutationItem,1,1),posX+45+15+16+5,posY+35+yxc);
        this.drawString(fontRenderer, "->", posX+45+15+16+5+16,posY+35+4+yxc, 0xFFFFFF);       
        renderItem(new ItemStack(PustySlime.slimeItem,1,5),posX+45+5+16+15+16+15,posY+35+yxc);
        
        
    

  
        
    }
    
    public void drawPage5(int x,int y, float f){
  	  
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;
        
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        this.drawString(fontRenderer, "Knowledge Recipes", posX+xSizeOfTexture/2-40, posY+10, 0xFFFFFF);
        
        
        renderItem(new ItemStack(PustySlime.slimeMutationItem, 1, 1),posX+45+15, posY+30);
        
       
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Bedrock"), posX+45+15+20, posY+25, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Rare"), posX+45+15+70+20, posY+25, 0xFFFFFF);
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Air"), posX+45+15+20, posY+35, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Air"), posX+45+15+70+20, posY+35, 0xFFFFFF);
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Rare"), posX+45+15+20, posY+45, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Bedrock"), posX+45+15+70+20, posY+45, 0xFFFFFF);
        
        
        //
        
        renderItem(new ItemStack(PustySlime.slimeMutationItem, 1, 2),posX+45+15, posY+30+45);
        
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Bedrock"), posX+45+15+20, posY+25+45, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Air"), posX+45+15+70+20, posY+25+45, 0xFFFFFF);
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Fire"), posX+45+15+20, posY+35+45, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Fire"), posX+45+15+70+20, posY+35+45, 0xFFFFFF);
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Air"), posX+45+15+20, posY+45+45, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Bedrock"), posX+45+15+70+20, posY+45+45, 0xFFFFFF);
        
        //
        
        //
        
        renderItem(new ItemStack(PustySlime.slimeMutationItem, 1, 3),posX+45+15, posY+30+45+45);
        
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Bedrock"), posX+45+15+20, posY+25+45+45, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Rare"), posX+45+15+70+20, posY+25+45+45, 0xFFFFFF);
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Water"), posX+45+15+20, posY+35+45+45, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Water"), posX+45+15+70+20, posY+35+45+45, 0xFFFFFF);
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Rare"), posX+45+15+20, posY+45+45+45, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Bedrock"), posX+45+15+70+20, posY+45+45+45, 0xFFFFFF);
        
        //
    }
    
    
    public void drawPage6(int x,int y, float f){
    	  
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;
        
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        this.drawString(fontRenderer, "Knowledge Recipes", posX+xSizeOfTexture/2-40, posY+10, 0xFFFFFF);
        
        
        renderItem(new ItemStack(PustySlime.slimeMutationItem, 1, 4),posX+45+15, posY+30);
        
       
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Bedrock"), posX+45+15+20, posY+25, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Rare"), posX+45+15+70+20, posY+25, 0xFFFFFF);
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Plant"), posX+45+15+20, posY+35, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Plant"), posX+45+15+70+20, posY+35, 0xFFFFFF);
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Rare"), posX+45+15+20, posY+45, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Bedrock"), posX+45+15+70+20, posY+45, 0xFFFFFF);
        
      
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





