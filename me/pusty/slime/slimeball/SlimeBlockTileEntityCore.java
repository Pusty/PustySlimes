package me.pusty.slime.slimeball;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.pusty.slime.PustyMutatorTileEntity;
import me.pusty.slime.PustySlime;
import me.pusty.slime.SlimeBallTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class SlimeBlockTileEntityCore extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;


    
    private Class TestEntityClass;
    int id;
    public SlimeBlockTileEntityCore(int i,Class tClass)
            {
              super(i,Material.cake);
              TestEntityClass = tClass;  
              this.setHardness(0.5F);
              this.setResistance(0.5F);
            }

    
    /*
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("Generic:" + (this.getUnlocalizedName().substring(5)));
    }
    */
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray = new Icon[16];

        for (int i = 0; i < SlimeBallTypes.list.size(); ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon("pustyslime:mslime2");
        }
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
       // return this.iconArray[par2 % this.iconArray.length];
    	return this.iconArray[0];
    }
    
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {

    }

    public int idDropped(int par1, Random par2Random, int par3)
    {

        return PustySlime.slimeBlock.blockID;
    }
    
    @Override
    public int damageDropped(int par1){
		return 1;
    	
    }
    
   
    
    
    


    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
    	PustySlimeBlockTileEntityCore dummy = (PustySlimeBlockTileEntityCore)world.getBlockTileEntity(x, y, z);
           dummy.invalidateMultiblock();
           dropItems(world, x, y, z);
        	super.breakBlock(world, x,y,z, par5, par6);
    	
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


	
public TileEntity getBlockEntity()
{
        try{
                return (TileEntity)TestEntityClass.newInstance();
        }
        catch(Exception exception){
                throw new RuntimeException(exception);
        }
}



public boolean isOpaqueCube()
{
 return false;
}







@Override
public TileEntity createNewTileEntity(World var1) {
	PustySlimeBlockTileEntityCore p =	new PustySlimeBlockTileEntityCore();
return (TileEntity)p;
}
  
}