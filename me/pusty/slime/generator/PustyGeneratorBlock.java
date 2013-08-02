package me.pusty.slime.generator;
 
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;



import me.pusty.slime.PustySlime;
import me.pusty.slime.SlimeTypes;
import me.pusty.slime.mutater.TileEntityMutator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
 
public class PustyGeneratorBlock extends BlockContainer
{
        private Class TestEntityClass;
        int id;
        public PustyGeneratorBlock(int i,Class tClass)
                {
                  super(i,Material.cake);
                  TestEntityClass = tClass;  
                  this.setTickRandomly(true);
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
    



   
       @Override
       public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p, int par6, float par7, float par8, float par9)
       {
    	
          	
    	      if (world.isRemote)
              {
                  return true;
              }
              else
              {
            	   TileEntityGenerator te = ((TileEntityGenerator)world.getBlockTileEntity(x, y, z));
              	

                  if (te != null)
                  {

                  	p.openGui(PustySlime.instance, 3, world, te.xCoord, te.yCoord, te.zCoord);
                  }

                  return true;
              }
           	   
       
       }
       
       
       @Override
       public void updateTick(World world, int par2, int par3, int par4, Random par5Random)
       {
           super.updateTick(world, par2, par3, par4, par5Random);

  
    	   int blockmeta = world.getBlockMetadata(par2, par3, par4);
    	   TileEntityGenerator b =	(TileEntityGenerator)world.getBlockTileEntity(par2, par3, par4);
    	   if(b.inventory[0]!=null){
    	         if(par5Random.nextInt(6)>1+EnchantmentHelper.getEnchantmentLevel(52, b.inventory[0]))
    	        return;
    		   ItemStack itemstack = SlimeTypes.list.get(b.inventory[0].getItemDamage()).output.copy();
    		   if(itemstack == null)
    			   return;
    		   
    		   world.playAuxSFX(1001, par2, par3, par4, 0);

                   int i1 = 1;
                   IInventory iinventory = TileEntityHopper.getInventoryAtLocation(world, (double)(par2 + Facing.offsetsXForSide[i1]), (double)(par3 + Facing.offsetsYForSide[i1]), (double)(par4 + Facing.offsetsZForSide[i1]));
                   ItemStack itemstack1;

                   if (iinventory != null)
                   {
                       itemstack1 = TileEntityHopper.insertStack(iinventory, itemstack.copy().splitStack(1), Facing.oppositeSide[i1]);

                       if (itemstack1 == null)
                       {
                           itemstack1 = itemstack.copy();

                           if (--itemstack1.stackSize == 0)
                           {
                               itemstack1 = null;
                           }
                       }
                       else
                       {
                           itemstack1 = itemstack.copy();
                       }
                   }else{
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
                           EntityItem entityitem = new EntityItem(world, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));

                           if (itemstack.hasTagCompound())
                           {
                               entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                           }

                           float f3 = 0.05F;
                           entityitem.motionX = (double)(f3);
                           entityitem.motionY = (double)(f3 + 0.2F);
                           entityitem.motionZ = (double)(f3);
                           world.spawnEntityInWorld(entityitem);
                       }    
                   }
    		   
    	   }    	   
    	   
       }

       
       @Override
       @SideOnly(Side.CLIENT)
       public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
       {
           super.randomDisplayTick(par1World, par2, par3, par4, par5Random);

       	   int blockmeta = par1World.getBlockMetadata(par2, par3, par4);
    	   if(blockmeta!=0){
           for(int i=0;i<7;i++){
           float f = (float)par2 + 0.5F;
           float f1 = (float)par3 + 0.7F;
           float f2 = (float)par4 + 0.5F;
           float f3 = (par5Random.nextFloat()) * 0.6F - 0.3F;
           float f4 = (par5Random.nextFloat()) * -0.6F + 0.3F;
           par1World.spawnParticle(SlimeTypes.list.get(blockmeta).texturename.particelname, (double)(f+f3), (double)(f1), (double)(f2+f4), 0.0D, 0.0D, 0.0D);
           }
    	   }
       }


       
       
       
       @Override
       @SideOnly(Side.CLIENT)
       public void registerIcons(IconRegister iconRegister) {
           this.blockIcon = iconRegister.registerIcon("pustyslime:generator");
       }
       

       


       @Override
       public void breakBlock(World world, int x, int y, int z, int par5, int par6)
       {
    	   TileEntityGenerator dummy = (TileEntityGenerator)world.getBlockTileEntity(x, y, z);
              dropItems(world, x, y, z);
           	super.breakBlock(world, x,y,z, par5, par6);
       	
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
		TileEntityGenerator p =	new TileEntityGenerator();

    return (TileEntity)p;
	}
 
}