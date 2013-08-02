package me.pusty.slime;
 
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;



import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
 
public class PustyObjectBlock extends BlockContainer
{
        private Class TestEntityClass;
        int id;
        protected PustyObjectBlock(int i,Class tClass)
                {
                  super(i,Material.cake);
                  TestEntityClass = tClass;  
          		setCreativeTab(PustySlime.pustyTab);
          	    this.setUnlocalizedName("slimeobject");
    
                }
   
    	
    public TileEntity getBlockEntity()
    {
            try{
                    return (TileEntity)TestEntityClass.newInstance();
            }
            catch(Exception exception){
                    throw new RuntimeException(exception);
            }
    }
    
    



   
       @Override
       public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer p, int par6, float par7, float par8, float par9)
       {
       	   if(p == null)
           	return false;
    	   int blockmeta = world.getBlockMetadata(par2, par3, par4);
    	   PustyObjectTileEntity b =	(PustyObjectTileEntity)world.getBlockTileEntity(par2, par3, par4);

    	   if(p.isSneaking()){
    			
    			if(b.isOpen)
    			{
    				b.isOpen = false;
    				 ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
	                 DataOutputStream outputStream = new DataOutputStream(bos);
	                 try {
                	     outputStream.writeInt(3);  
                         outputStream.writeInt(par2);  
                         outputStream.writeInt(par3);  
                         outputStream.writeInt(par4);  
	                       outputStream.writeInt(0);  
	                 } catch (Exception ex) {
	                         ex.printStackTrace();
	                 }
	                
	                 Packet250CustomPayload packet = new Packet250CustomPayload();
	                 packet.channel = "PustySlime";
	                 packet.data = bos.toByteArray();
	                 packet.length = bos.size();
	                
	                 Side side = FMLCommonHandler.instance().getEffectiveSide();
	                 
	                 if (side == Side.SERVER) {
	                         // We are on the server side.
	                         EntityPlayerMP player = (EntityPlayerMP) p;
	                 } else if (side == Side.CLIENT) {
	                         // We are on the client side.
	                         EntityClientPlayerMP player = (EntityClientPlayerMP) p;
	                         player.sendQueue.addToSendQueue(packet);
	                 } else {
	                         // We are on the Bukkit server.
	                 }
    			}else{
    				b.isOpen = true;
    				 ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
	                 DataOutputStream outputStream = new DataOutputStream(bos);
	                 try {
                	     outputStream.writeInt(3);  
                         outputStream.writeInt(par2);  
                         outputStream.writeInt(par3);  
                         outputStream.writeInt(par4);  
	                         outputStream.writeInt(1);  
	                 } catch (Exception ex) {
	                         ex.printStackTrace();
	                 }
	                
	                 Packet250CustomPayload packet = new Packet250CustomPayload();
	                 packet.channel = "PustySlime";
	                 packet.data = bos.toByteArray();
	                 packet.length = bos.size();
	                
	                 Side side = FMLCommonHandler.instance().getEffectiveSide();
	                 
	                 if (side == Side.SERVER) {
	                         // We are on the server side.
	                         EntityPlayerMP player = (EntityPlayerMP) p;
	                 } else if (side == Side.CLIENT) {
	                         // We are on the client side.
	                         EntityClientPlayerMP player = (EntityClientPlayerMP) p;
	                         player.sendQueue.addToSendQueue(packet);
	                 } else {
	                         // We are on the Bukkit server.
	                 }
    			}
    			 world.updateTileEntityChunkAndDoNothing(par2, par3, par4, b);
    			return true;
    	   }
  
    	   
    	   
    	                 if(p!=null&&p.getHeldItem()!=null&&p.getHeldItem().itemID==PustySlime.slimeItem.itemID){
    	
    	                	 int dmg =p.getHeldItem().getItemDamage();
                      world.setBlockMetadataWithNotify(par2, par3, par4, dmg, 0);
                      
                      ItemStack to = null;
                      if(b.getItem() != null)
                    	to = b.getItem().copy();
                      
                      b.setItem(p.getHeldItem());
                      p.setCurrentItemOrArmor(0, null);
              		
                 	 if(blockmeta != 0)
                	 {	
                		 p.setCurrentItemOrArmor(0, to);
                	 }
                       blockmeta = world.getBlockMetadata(par2, par3, par4);
                       ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
  	                 DataOutputStream outputStream = new DataOutputStream(bos);
  	                 try {
  	                	     outputStream.writeInt(2);  
	                         outputStream.writeInt(par2);  
	                         outputStream.writeInt(par3);  
	                         outputStream.writeInt(par4);  
  	                         outputStream.writeInt(dmg);  
  	                 } catch (Exception ex) {
  	                         ex.printStackTrace();
  	                 }
  	                
  	                 Packet250CustomPayload packet = new Packet250CustomPayload();
  	                 packet.channel = "PustySlime";
  	                 packet.data = bos.toByteArray();
  	                 packet.length = bos.size();
  	                
  	                 Side side = FMLCommonHandler.instance().getEffectiveSide();
  	                 
  	                 if (side == Side.SERVER) {
  	                         // We are on the server side.
  	                         EntityPlayerMP player = (EntityPlayerMP) p;
  	                 } else if (side == Side.CLIENT) {
  	                         // We are on the client side.
  	                         EntityClientPlayerMP player = (EntityClientPlayerMP) p;
  	                         player.sendQueue.addToSendQueue(packet);
  	                 } else {
  	                         // We are on the Bukkit server.
  	                 }
                   
                      return true;
    	                 }else if(p!=null&&p.getHeldItem()==null){
     
    	                      ItemStack to = null;
    	                      if(b.getItem() != null)
    	                    	to = b.getItem().copy();
    	                      
    	                      
    	                 	 world.setBlockMetadataWithNotify(par2, par3, par4, 0, 0);
    	                 	 b.setItem(null);

    	              		
    	                 	 if(to != null)
    	                	 {	
    	                		 p.setCurrentItemOrArmor(0, to);
    	                	 }
    	 
    	                 	 
    	                	 blockmeta = world.getBlockMetadata(par2, par3, par4);

    	           			 world.updateTileEntityChunkAndDoNothing(par2, par3, par4, b);
    	                     
    	           			 
        	                 
        	                 ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        	                 DataOutputStream outputStream = new DataOutputStream(bos);
        	                 try {
                        	     outputStream.writeInt(2);  
    	                         outputStream.writeInt(par2);  
    	                         outputStream.writeInt(par3);  
    	                         outputStream.writeInt(par4);  
        	                         outputStream.writeInt(blockmeta);  
        	                 } catch (Exception ex) {
        	                         ex.printStackTrace();
        	                 }
        	                
        	                 Packet250CustomPayload packet = new Packet250CustomPayload();
        	                 packet.channel = "PustySlime";
        	                 packet.data = bos.toByteArray();
        	                 packet.length = bos.size();
        	                
        	                 Side side = FMLCommonHandler.instance().getEffectiveSide();
        	                 
        	                 if (side == Side.SERVER) {
        	                         // We are on the server side.
        	                         EntityPlayerMP player = (EntityPlayerMP) p;
        	                 } else if (side == Side.CLIENT) {
        	                         // We are on the client side.
        	                         EntityClientPlayerMP player = (EntityClientPlayerMP) p;
        	                         player.sendQueue.addToSendQueue(packet);
        	                 } else {
        	                         // We are on the Bukkit server.
        	                 }
                         
            
    	                       return true;
    	    	           }

return false;
           	   
       
       }
       
       @Override
       @SideOnly(Side.CLIENT)
       public void registerIcons(IconRegister iconRegister) {
           this.blockIcon = iconRegister.registerIcon("pustyslime:glassroom");
       }
       

       


       /**
        * ejects contained items into the world, and notifies neighbours of an update, as appropriate
        */
       @Override
       public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
       {

               PustyObjectTileEntity tile = (PustyObjectTileEntity)par1World.getBlockTileEntity(par2, par3, par4);

               if (tile != null)
               {
                   for (int j1 = 0; j1 < tile.in.length; ++j1)
                   {
                       ItemStack itemstack = tile.in[j1];

                       if (itemstack != null)
                       {
                       	   Random r = new Random();
                           float f = r.nextFloat() * 0.8F + 0.1F;
                           float f1 = r.nextFloat() * 0.8F + 0.1F;
                           float f2 = r.nextFloat() * 0.8F + 0.1F;

                           while (itemstack.stackSize > 0)
                           {
                               int k1 = r.nextInt(21) + 10;

                               if (k1 > itemstack.stackSize)
                               {
                                   k1 = itemstack.stackSize;
                               }

                               itemstack.stackSize -= k1;
                               EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));

                               if (itemstack.hasTagCompound())
                               {
                                   entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                               }

                               float f3 = 0.05F;
                               entityitem.motionX = (double)(f3);
                               entityitem.motionY = (double)(f3 + 0.2F);
                               entityitem.motionZ = (double)(f3);
                               par1World.spawnEntityInWorld(entityitem);
                           }
                       }
                   }

                   par1World.func_96440_m(par2, par3, par4, par5);
               }
           

           super.breakBlock(par1World, par2, par3, par4, par5, par6);
       }


       @Override
    public int getRenderType(){
                return -1;
               
    }
   
    public boolean isOpaqueCube()
    {
        return false;
    }
   
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    
    
    


	@Override
	public TileEntity createNewTileEntity(World var1) {
		PustyObjectTileEntity p =	new PustyObjectTileEntity();

    return (TileEntity)p;
	}
 
}