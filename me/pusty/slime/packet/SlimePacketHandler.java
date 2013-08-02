package me.pusty.slime.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

import me.pusty.slime.KnowledgeSet;
import me.pusty.slime.PustyObjectTileEntity;
import me.pusty.slime.PustySlime;
import me.pusty.slime.SlimeBookItem;
import me.pusty.slime.SlimeItem;
import me.pusty.slime.SlimeKnowledgeItem;
import me.pusty.slime.mutater.TileEntityMutator;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class SlimePacketHandler implements IPacketHandler {
	
    @Override
    public void onPacketData(INetworkManager manager,
                    Packet250CustomPayload packet, Player player) {
           
            if (packet.channel.equals("PustySlime")) {
                    handleRandom(packet,player);
                    
            }
            
    }
   
    private void handleRandom(Packet250CustomPayload packet,Player pl) {
            DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
            EntityPlayer p = (EntityPlayer)pl;
            int id;
           int x;
           int y;
           int z;
           int meta;

           
            try {
            	    id = inputStream.readInt();
                    x = inputStream.readInt();
                    y = inputStream.readInt();
                    z = inputStream.readInt();
                    meta = inputStream.readInt();
                  
            } catch (IOException e) {
                    e.printStackTrace();
                    return;
            }
     
            if(id==2){
           p.worldObj.setBlockMetadataWithNotify(x, y, z, meta, 0);
           p.worldObj.updateTileEntityChunkAndDoNothing(x, y, z, p.worldObj.getBlockTileEntity(x, y, z));
           
           if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.SERVER)){
           ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
             DataOutputStream outputStream = new DataOutputStream(bos);
             try {
                 outputStream.writeInt(id);  
                   outputStream.writeInt(x);  
                   outputStream.writeInt(y);  
                   outputStream.writeInt(z);  
                     outputStream.writeInt(meta);  
             } catch (Exception ex) {
                     ex.printStackTrace();
             }
            
             Packet250CustomPayload pt = new Packet250CustomPayload();
             pt.channel = "PustySlime";
             pt.data = bos.toByteArray();
             pt.length = bos.size();
           PacketDispatcher.sendPacketToAllPlayers(pt);
           }
             
            }else if(id==3){
            	PustyObjectTileEntity tile =	((PustyObjectTileEntity)p.worldObj.getBlockTileEntity(x, y, z));
            	tile.isOpen=meta==0?false:true;
                  p.worldObj.updateTileEntityChunkAndDoNothing(x, y, z, p.worldObj.getBlockTileEntity(x, y, z));
                  
                  if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.SERVER)){
                  ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                    DataOutputStream outputStream = new DataOutputStream(bos);
                    try {
                    	outputStream.writeInt(id);  
                          outputStream.writeInt(x);  
                          outputStream.writeInt(y);  
                          outputStream.writeInt(z);  
                            outputStream.writeInt(meta);  
                    } catch (Exception ex) {
                            ex.printStackTrace();
                    }
                   
                    Packet250CustomPayload pt = new Packet250CustomPayload();
                    pt.channel = "PustySlime";
                    pt.data = bos.toByteArray();
                    pt.length = bos.size();
                  PacketDispatcher.sendPacketToAllPlayers(pt);
                  }
            }else if(id==4){
                p.worldObj.setBlockMetadataWithNotify(x, y, z, meta, 0);
                p.worldObj.updateTileEntityChunkAndDoNothing(x, y, z, p.worldObj.getBlockTileEntity(x, y, z));
                
                if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.SERVER)){
                ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                  DataOutputStream outputStream = new DataOutputStream(bos);
                  try {
                      outputStream.writeInt(id);  
                        outputStream.writeInt(x);  
                        outputStream.writeInt(y);  
                        outputStream.writeInt(z);  
                          outputStream.writeInt(meta);  
                  } catch (Exception ex) {
                          ex.printStackTrace();
                  }
                 
                  Packet250CustomPayload pt = new Packet250CustomPayload();
                  pt.channel = "PustySlime";
                  pt.data = bos.toByteArray();
                  pt.length = bos.size();
                PacketDispatcher.sendPacketToAllPlayers(pt);
                }
            }else if(id==5){
                p.worldObj.setBlockMetadataWithNotify(x, y, z, meta, 0);
                p.worldObj.updateTileEntityChunkAndDoNothing(x, y, z, p.worldObj.getBlockTileEntity(x, y, z));
                
                if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.SERVER)){
                ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                  DataOutputStream outputStream = new DataOutputStream(bos);
                  try {
                      outputStream.writeInt(id);  
                        outputStream.writeInt(x);  
                        outputStream.writeInt(y);  
                        outputStream.writeInt(z);  
                          outputStream.writeInt(meta);  
                  } catch (Exception ex) {
                          ex.printStackTrace();
                  }
                 
                  Packet250CustomPayload pt = new Packet250CustomPayload();
                  pt.channel = "PustySlime";
                  pt.data = bos.toByteArray();
                  pt.length = bos.size();
                PacketDispatcher.sendPacketToAllPlayers(pt);
                }
            }else if(id==6){


            	EntityPlayerMP playerMP = (EntityPlayerMP)pl;

                
            	TileEntityMutator mutator = (TileEntityMutator) playerMP.worldObj.getBlockTileEntity(x, y, z);
                 if(mutator == null){
                	 System.out.println("Server does not found it");
                	 return;
                 }
                 
                 mutator.shownItem = meta==0?null:new ItemStack(PustySlime.slimeItem,1,meta);
                 playerMP.worldObj.markBlockForUpdate(x, y, z);
                if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.SERVER)){
                ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                  DataOutputStream outputStream = new DataOutputStream(bos);
                  try {
                      outputStream.writeInt(7);  
                        outputStream.writeInt(x);  
                        outputStream.writeInt(y);  
                        outputStream.writeInt(z);  
                          outputStream.writeInt(mutator.shownItem!=null?(mutator.shownItem.getItem() instanceof SlimeItem?mutator.shownItem.getItemDamage():0):0);  
                  } catch (Exception ex) {
                          ex.printStackTrace();
                  }
                 
                  Packet250CustomPayload pt = new Packet250CustomPayload();
                  pt.channel = "PustySlime";
                  pt.data = bos.toByteArray();
                  pt.length = bos.size();
                  
                PacketDispatcher.sendPacketToAllInDimension(pt, playerMP.worldObj.provider.dimensionId);
                }
                
            }else if(id==7){

            	EntityPlayerMP playerMP = (EntityPlayerMP)pl;
            	TileEntityMutator mutator = (TileEntityMutator)playerMP.worldObj.getBlockTileEntity(x, y, z);
            	
            	
            	
                 if(mutator == null){
                	 return;
                 }
                 
                 mutator.shownItem = meta==0?null:new ItemStack(PustySlime.slimeItem,1,meta);
            	
                playerMP.worldObj.markBlockForUpdate(x, y, z);
                
            }else if(id==8){

            	EntityPlayerMP playerMP = (EntityPlayerMP)pl;
            	SlimeBookItem.setPage(playerMP.getCurrentItemOrArmor(0),meta);

            	
                
            }else if(id==9){



            	boolean side[] = new boolean[6];
            	String sideTag[] = new String[6];
        
            	 try {
            		 for(int i=0;i<side.length;i++){
     					side[i] = inputStream.readBoolean();
    					sideTag[i] = inputStream.readUTF();     			 
            		 }
				} catch (IOException e) {
					// TODO Automatisch generierter Erfassungsblock
					e.printStackTrace();
				}
            	 
             	EntityPlayerMP playerMP = (EntityPlayerMP)pl;
             	for(int i=0;i<side.length;i++)
             	SlimeKnowledgeItem.setPage(playerMP.getCurrentItemOrArmor(0),i,side[i]);
             	
             	for(int i=0;i<sideTag.length;i++)
             	SlimeKnowledgeItem.setPageString(playerMP.getCurrentItemOrArmor(0),i,sideTag[i]);
                
            }else if(id==10){

            	 
             	EntityPlayerMP playerMP = (EntityPlayerMP)pl;
             	playerMP.setCurrentItemOrArmor(0, KnowledgeSet.list.get(meta).item);
             	
             	
             	
             	if(KnowledgeSet.list.get(meta).item.getItem() instanceof SlimeItem && KnowledgeSet.list.get(meta).item.getItemDamage() == 18){
             		
             		

             		NBTTagCompound tag = p.getEntityData();
             		NBTBase modeTag = tag.getTag("slimefirstmade");
             		
             		
             		if (modeTag == null || !tag.getBoolean("slimefirstmade")) {

                        World world = playerMP.worldObj;
                        ItemStack item = new ItemStack(PustySlime.slimebook2,1);

                                EntityItem entityItem = new EntityItem(world,
                                                playerMP.lastTickPosX, playerMP.lastTickPosY, playerMP.lastTickPosZ,
                                                item.copy());

                                if (item.hasTagCompound()) {
                                        entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                                }


                                world.spawnEntityInWorld(entityItem);
                                item.stackSize = 0;
             		}

             		tag.setBoolean("slimefirstmade", true);

                          }
                  
             	}
                
            }
    }

