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
public class SlimeBookGui extends GuiScreen

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

    public SlimeBookGui(EntityPlayer p)
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
    this.buttonList.add(new GuiButton(6, posX+ 3, posY + (20*6)+3 , 45, 20, "Page 7"));	
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
    else if(side==6)
    	drawPage7(x,y,f);


    super.drawScreen(x, y, f);
    }
    
    
    public void drawPage1(int x,int y, float f){
    	  
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;
        
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        this.drawString(fontRenderer, "Basics", posX+xSizeOfTexture/2-20, posY+10, 0xFFFFFF);
        
        this.drawString(fontRenderer, "This Book is the basic Slime book,", posX+45+15, posY+25, 0xFFFFFF);
        this.drawString(fontRenderer, "it will explane you all the basics", posX+45+15, posY+35, 0xFFFFFF);
        this.drawString(fontRenderer, "about PustySlimes, so if u are", posX+45+15, posY+45, 0xFFFFFF);
        this.drawString(fontRenderer, "interest at this mod better", posX+45+15, posY+55, 0xFFFFFF);
        this.drawString(fontRenderer, "read the other pages too.", posX+45+15, posY+65, 0xFFFFFF);
        this.drawString(fontRenderer, "      -Pusty", posX+45+15, posY+75, 0xFFFFFF);
        
    }
    
    public void drawPage2(int x,int y, float f){
    	  
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;
        
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        this.drawString(fontRenderer, "Slimes", posX+xSizeOfTexture/2-20, posY+10, 0xFFFFFF);
        
        this.drawString(fontRenderer, "The main object in this mod", posX+45+15, posY+25, 0xFFFFFF);
        this.drawString(fontRenderer, "are Slimes,not normal ones", posX+45+15, posY+35, 0xFFFFFF);
        this.drawString(fontRenderer, "this mod add it owns. You can", posX+45+15, posY+45, 0xFFFFFF);
        this.drawString(fontRenderer, "find Slimes in Dungeons. They", posX+45+15, posY+55, 0xFFFFFF);
        this.drawString(fontRenderer, "should be 'Main Slimes' and maybe", posX+45+15, posY+65, 0xFFFFFF);
        this.drawString(fontRenderer, "they got an 'Slimness' enchantment", posX+45+15, posY+75, 0xFFFFFF);
        this.drawString(fontRenderer, "it make the Slime produce more in", posX+45+15, posY+85, 0xFFFFFF);
        this.drawString(fontRenderer, "the 'SlimeGenerator'.", posX+45+15, posY+95, 0xFFFFFF);
        this.drawString(fontRenderer, "You may thing:why i should get slimes?", posX+45+15, posY+105, 0xFFFFFF);
        this.drawString(fontRenderer, "The anwser is that 'Slimes' can get ", posX+45+15, posY+115, 0xFFFFFF);
        this.drawString(fontRenderer, "mutatated at the 'SlimeMutator'", posX+45+15, posY+125, 0xFFFFFF);
        this.drawString(fontRenderer, "so you can get better slimes.", posX+45+15, posY+135, 0xFFFFFF);
        
    }
    
    public void drawPage3(int x,int y, float f){
  	  
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;
        
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        this.drawString(fontRenderer, "Craftings", posX+xSizeOfTexture/2-20, posY+10, 0xFFFFFF);
        
        this.drawString(fontRenderer, "SlimeBox Crafting:", posX+45+15, posY+20, 0xFFFFFF);
        
        
        
        renderItem(new ItemStack(PustySlime.objectBlock,1),posX+45+15,posY+45);
        this.drawString(fontRenderer, "<-", posX+45+15+15,posY+45+3, 0xFFFFFF);
        
        renderItem(new ItemStack(Block.cloth,1,9),posX+45+30+15,posY+30);
        renderItem(new ItemStack(Block.cloth,1,9),posX+45+45+15,posY+30);
        renderItem(new ItemStack(Block.cloth,1,9),posX+45+60+15,posY+30);
        renderItem(new ItemStack(Block.glass,1),posX+45+30+15,posY+45);
        renderItem(new ItemStack(Block.obsidian,1),posX+45+45+15,posY+45);
        renderItem(new ItemStack(Block.glass,1),posX+45+60+15,posY+45);
        renderItem(new ItemStack(Block.cloth,1,9),posX+45+30+15,posY+60);
        renderItem(new ItemStack(Block.cloth,1,9),posX+45+45+15,posY+60);
        renderItem(new ItemStack(Block.cloth,1,9),posX+45+60+15,posY+60);
        
        
        
        
        this.drawString(fontRenderer, "SlimeGenerator Crafting:", posX+45+15, posY+20+60, 0xFFFFFF);
        
        renderItem(new ItemStack(PustySlime.generatorBlock,1),posX+45+15,posY+45+60);
        this.drawString(fontRenderer, "<-", posX+45+15+15,posY+45+60+3, 0xFFFFFF);
        
        
        renderItem(new ItemStack(Block.cloth,1,5),posX+45+30+15,posY+30+60);
        renderItem(new ItemStack(Block.cloth,1,5),posX+45+45+15,posY+30+60);
        renderItem(new ItemStack(Block.cloth,1,5),posX+45+60+15,posY+30+60);
        renderItem(new ItemStack(Block.glass,1),posX+45+30+15,posY+45+60);
        renderItem(new ItemStack(Item.diamond,1),posX+45+45+15,posY+45+60);
        renderItem(new ItemStack(Block.glass,1),posX+45+60+15,posY+45+60);
        renderItem(new ItemStack(Block.cloth,1,5),posX+45+30+15,posY+60+60);
        renderItem(new ItemStack(Block.cloth,1,5),posX+45+45+15,posY+60+60);
        renderItem(new ItemStack(Block.cloth,1,5),posX+45+60+15,posY+60+60);
        

  
        
    }
    
    
    public void drawPage4(int x,int y, float f){
    	  
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;
        
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        this.drawString(fontRenderer, "Craftings", posX+xSizeOfTexture/2-20, posY+10, 0xFFFFFF);
        
        this.drawString(fontRenderer, "SlimeMutator Crafting:", posX+45+15, posY+20, 0xFFFFFF);
        
        
        
        renderItem(new ItemStack(PustySlime.mutatorBlock,1),posX+45+15,posY+45);
        this.drawString(fontRenderer, "<-", posX+45+15+15,posY+45+3, 0xFFFFFF);
        
        renderItem(new ItemStack(Item.slimeBall,1),posX+45+30+15,posY+30);
        renderItem(new ItemStack(Item.slimeBall,1),posX+45+45+15,posY+30);
        renderItem(new ItemStack(Item.slimeBall,1),posX+45+60+15,posY+30);
        renderItem(new ItemStack(Block.glass,1),posX+45+30+15,posY+45);
        renderItem(new ItemStack(Block.obsidian,1),posX+45+45+15,posY+45);
        renderItem(new ItemStack(Block.glass,1),posX+45+60+15,posY+45);
        renderItem(new ItemStack(Block.cloth,1,1),posX+45+30+15,posY+60);
        renderItem(new ItemStack(Block.cloth,1,1),posX+45+45+15,posY+60);
        renderItem(new ItemStack(Block.cloth,1,1),posX+45+60+15,posY+60);
        
        
        
        
        this.drawString(fontRenderer, "SlimeBlock Crafting:", posX+45+15, posY+20+60, 0xFFFFFF);
        
        renderItem(new ItemStack(PustySlime.slimeBlock,1,4),posX+45+15,posY+45+60);
        this.drawString(fontRenderer, "<-", posX+45+15+15,posY+45+60+3, 0xFFFFFF);
        
        
        renderItem(new ItemStack(PustySlime.slimeBallItem,1,4),posX+45+30+15,posY+30+60);
        renderItem(new ItemStack(PustySlime.slimeBallItem,1,4),posX+45+45+15,posY+30+60);
        renderItem(new ItemStack(PustySlime.slimeBallItem,1,4),posX+45+60+15,posY+30+60);
        renderItem(new ItemStack(PustySlime.slimeBallItem,1,4),posX+45+30+15,posY+45+60);
        renderItem(new ItemStack(PustySlime.slimeBallItem,1,4),posX+45+45+15,posY+45+60);
        renderItem(new ItemStack(PustySlime.slimeBallItem,1,4),posX+45+60+15,posY+45+60);
        renderItem(new ItemStack(PustySlime.slimeBallItem,1,4),posX+45+30+15,posY+60+60);
        renderItem(new ItemStack(PustySlime.slimeBallItem,1,4),posX+45+45+15,posY+60+60);
        renderItem(new ItemStack(PustySlime.slimeBallItem,1,4),posX+45+60+15,posY+60+60);
        
        this.drawString(fontRenderer, "This Crafting works with SOME", posX+45+15+15,posY+60+60+15, 0xFFFFFF);
        this.drawString(fontRenderer, "SlimeBalls NOT all.", posX+45+15+15,posY+60+60+25, 0xFFFFFF);
        

  
        
    }
    
    public void drawPage5(int x,int y, float f){
  	  
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;
        
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        this.drawString(fontRenderer, "Main Slime", posX+xSizeOfTexture/2-20, posY+10, 0xFFFFFF);
        
        this.drawString(fontRenderer, "When you got your first 'Main Slime'", posX+45+15, posY+25, 0xFFFFFF);
        this.drawString(fontRenderer, "you should put it in an", posX+45+15, posY+35, 0xFFFFFF);
        this.drawString(fontRenderer, "'SlimeGenerator' there it will produce", posX+45+15, posY+45, 0xFFFFFF);
        this.drawString(fontRenderer, "'Main SlimeBalls'. With them you", posX+45+15, posY+55, 0xFFFFFF);
        this.drawString(fontRenderer, "can craft SlimeBlocks.", posX+45+15, posY+65, 0xFFFFFF);
        this.drawString(fontRenderer, "'Main Slime Balls' havent got a big use", posX+45+15, posY+75, 0xFFFFFF);
        this.drawString(fontRenderer, "you should put them with a", posX+45+15, posY+85, 0xFFFFFF);
        this.drawString(fontRenderer, "MutaterItem (can be find in dungeons)", posX+45+15, posY+95, 0xFFFFFF);
        this.drawString(fontRenderer, "in an 'SlimeMutater' for getting better", posX+45+15, posY+105, 0xFFFFFF);
        this.drawString(fontRenderer, "Slimes. Watch out! When the Slime can't", posX+45+15, posY+115, 0xFFFFFF);
        this.drawString(fontRenderer, "mutate with that Item it will disappear.", posX+45+15, posY+125, 0xFFFFFF);
        this.drawString(fontRenderer, "So be carefull.", posX+45+15, posY+135, 0xFFFFFF);
        
    }
    
    public void drawPage6(int x,int y, float f){
    	  
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;
        
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        this.drawString(fontRenderer, "Knowledge Books", posX+xSizeOfTexture/2-20, posY+10, 0xFFFFFF);
        
        this.drawString(fontRenderer, "This books are used to get", posX+45+15, posY+25, 0xFFFFFF);
        this.drawString(fontRenderer, "Slimes and Mutations without", posX+45+15, posY+35, 0xFFFFFF);
        this.drawString(fontRenderer, "finding a Dungeon.", posX+45+15, posY+45, 0xFFFFFF);
        this.drawString(fontRenderer, "You must click the 'Use'", posX+45+15, posY+55, 0xFFFFFF);
        this.drawString(fontRenderer, "buttons at the right places", posX+45+15, posY+65, 0xFFFFFF);
        this.drawString(fontRenderer, "and Biomes to use it", posX+45+15, posY+75, 0xFFFFFF);
        this.drawString(fontRenderer, "correctly.", posX+45+15, posY+85, 0xFFFFFF);
        
        
        
        renderItem(new ItemStack(PustySlime.slimeknowledgebook,1),posX+45+15,posY+115);
        this.drawString(fontRenderer, "<-", posX+45+33,posY+120, 0xFFFFFF);
        
        
        renderItem(new ItemStack(Block.blockRedstone,1),posX+45+30+15,posY+100+1);
        renderItem(new ItemStack(Item.paper,1),posX+45+45+15,posY+100+1);
        renderItem(new ItemStack(Block.blockRedstone,1),posX+45+60+15,posY+100+1);
        renderItem(new ItemStack(Item.paper,1),posX+45+30+15,posY+115+1);
        renderItem(new ItemStack(Item.diamond,1),posX+45+45+15,posY+115+1);
        renderItem(new ItemStack(Item.paper,1),posX+45+60+15,posY+115+1);
        renderItem(new ItemStack(Block.blockRedstone,1),posX+45+30+15,posY+130+1);
        renderItem(new ItemStack(Item.paper,1),posX+45+45+15,posY+130+1);
        renderItem(new ItemStack(Block.blockRedstone,1),posX+45+60+15,posY+130+1);

        
    }
    
    public void drawPage7(int x,int y, float f){
  	  
        int posX = (this.width - xSizeOfTexture) / 2;
        int posY = (this.height - ySizeOfTexture) / 2;
        
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        this.drawString(fontRenderer, "Knowledge Recipes", posX+xSizeOfTexture/2-40, posY+10, 0xFFFFFF);
        
        
        renderItem(new ItemStack(PustySlime.slimebook,1),posX+45+15, posY+30);
        
       
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Bedrock"), posX+45+15+20, posY+25, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Rare"), posX+45+15+70+20, posY+25, 0xFFFFFF);
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Air"), posX+45+15+20, posY+35, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Air"), posX+45+15+70+20, posY+35, 0xFFFFFF);
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Rare"), posX+45+15+20, posY+45, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Bedrock"), posX+45+15+70+20, posY+45, 0xFFFFFF);
        
        
        //
        
        renderItem(new ItemStack(PustySlime.slimeItem,1,18),posX+45+15, posY+30+45);
        
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Bedrock"), posX+45+15+20, posY+25+45, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Air"), posX+45+15+70+20, posY+25+45, 0xFFFFFF);
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Normal"), posX+45+15+20, posY+35+45, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Normal"), posX+45+15+70+20, posY+35+45, 0xFFFFFF);
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Air"), posX+45+15+20, posY+45+45, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Bedrock"), posX+45+15+70+20, posY+45+45, 0xFFFFFF);
        
        //
        
        //
        
        renderItem(new ItemStack(PustySlime.slimeMutationItem, 1, 6),posX+45+15, posY+30+45+45);
        
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Bedrock"), posX+45+15+20, posY+25+45+45, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Normal"), posX+45+15+70+20, posY+25+45+45, 0xFFFFFF);
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Normal"), posX+45+15+20, posY+35+45+45, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Normal"), posX+45+15+70+20, posY+35+45+45, 0xFFFFFF);
        
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Normal"), posX+45+15+20, posY+45+45+45, 0xFFFFFF);
        this.drawString(fontRenderer, SlimeKnowledgeItem.getShownTag("Bedrock"), posX+45+15+70+20, posY+45+45+45, 0xFFFFFF);
        
        //
        


        
        


        
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





