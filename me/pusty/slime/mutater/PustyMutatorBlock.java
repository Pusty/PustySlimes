package me.pusty.slime.mutater;
 
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;



import me.pusty.slime.PustyMutatorTileEntity;
import me.pusty.slime.PustySlime;
import me.pusty.slime.slimeball.PustySlimeBlockTileEntity;
import me.pusty.slime.slimeball.PustySlimeBlockTileEntityCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
 
public class PustyMutatorBlock extends BlockContainer
{
        private Class TestEntityClass;
        int id;
        public PustyMutatorBlock(int i,Class tClass)
                {
                  super(i,Material.cake);
                  TestEntityClass = tClass;  
          		setCreativeTab(PustySlime.pustyTab);
              this.setUnlocalizedName("slimemutator");
              this.setHardness(0.2F);
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
    
    

    private void dropItems(World world, int x, int y, int z){
        Random rand = new Random();

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
                return;
        }
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
                ItemStack item = inventory.getStackInSlot(i);

                if (item != null && item.stackSize > 0) {
                        float rx = rand.nextFloat() * 0.8F + 0.1F;
                        float ry = rand.nextFloat() * 0.8F + 0.1F;
                        float rz = rand.nextFloat() * 0.8F + 0.1F;

                        EntityItem entityItem = new EntityItem(world,
                                        x + rx, y + ry, z + rz,
                                        item.copy());

                        if (item.hasTagCompound()) {
                                entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                        }

                        float factor = 0.05F;
                        entityItem.motionX = rand.nextGaussian() * factor;
                        entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                        entityItem.motionZ = rand.nextGaussian() * factor;
                        world.spawnEntityInWorld(entityItem);
                        item.stackSize = 0;
                }
        }
}
    


  /* 
       @Override
       public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer p, int par6, float par7, float par8, float par9)
       {
       	   if(p == null)
           	return false;
    	   int blockmeta = world.getBlockMetadata(par2, par3, par4);
    	   PustyMutatorTileEntity b =	(PustyMutatorTileEntity)world.getBlockTileEntity(par2, par3, par4);
    	   //if(te.getItem()==null&&p.getHeldItem()==null){
    		//   return true;
    	  // }
             //      System.out.println(te.getItem());
          

                  //    EntityItem entityitem = new EntityItem(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord, te.getItem());
                  //    entityitem.delayBeforeCanPickup = 10;
                 //     te.getWorldObj().spawnEntityInWorld(entityitem);


                  //    te.setItem(p.getHeldItem());


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
  	                	     outputStream.writeInt(4);  
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
                        	     outputStream.writeInt(4);  
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
    	    	           }   	      else if(p!=null&&p.getHeldItem()!=null&&b.getItem()!=null && p.getHeldItem().itemID==PustySlime.slimeMutationItem.itemID && b.getMItem()==null){

 
                        b.setMItem(p.getHeldItem());
                        p.setCurrentItemOrArmor(0, null);

                       b.setItem(b.mutate());
                       b.setMItem(null);
                       
                       int meta = b.getItem()!=null?b.getItem().getItemDamage():0;

                       world.setBlockMetadataWithNotify(par2, par3, par4,meta,0);
                       
                         blockmeta = world.getBlockMetadata(par2, par3, par4);
                         ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
    	                 DataOutputStream outputStream = new DataOutputStream(bos);
    	                 try {
    	                	     outputStream.writeInt(4);  
  	                         outputStream.writeInt(par2);  
  	                         outputStream.writeInt(par3);  
  	                         outputStream.writeInt(par4);  
    	                         outputStream.writeInt(meta);  
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
           	   
       
       }*/
       
       @Override
       @SideOnly(Side.CLIENT)
       public void registerIcons(IconRegister iconRegister) {
           this.blockIcon = iconRegister.registerIcon("pustyslime:mutator");
       }
       

       
       
   	@Override
   	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
           if (world.isRemote)
           {
               return true;
           }
           else
           {
        	   TileEntityMutator te = ((TileEntityMutator)world.getBlockTileEntity(x, y, z));
           	

               if (te != null)
               {

               	par5EntityPlayer.openGui(PustySlime.instance, 1, world, te.xCoord, te.yCoord, te.zCoord);
               }

               return true;
           }
   	}
   	

       @Override
       public void breakBlock(World world, int x, int y, int z, int par5, int par6)
       {
       	TileEntityMutator dummy = (TileEntityMutator)world.getBlockTileEntity(x, y, z);
              dropItems(world, x, y, z);
           	super.breakBlock(world, x,y,z, par5, par6);
       	
       }
       

       /**
        * ejects contained items into the world, and notifies neighbours of an update, as appropriate
        */
     /*  @Override
       public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
       {

               PustyMutatorTileEntity tile = (PustyMutatorTileEntity)par1World.getBlockTileEntity(par2, par3, par4);

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
       }*/


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
		TileEntityMutator p =	new TileEntityMutator();

    return (TileEntity)p;
	}
 
}