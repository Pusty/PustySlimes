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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class SlimeBlockTileEntity extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;


    
    private Class TestEntityClass;
    int id;
    public SlimeBlockTileEntity(int i,Class tClass)
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
            this.iconArray[i] = par1IconRegister.registerIcon("pustyslime:mslime1");
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
    
    
    
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
        	PustySlimeBlockTileEntityCore te = ((PustySlimeBlockTileEntity)world.getBlockTileEntity(x, y, z)).getCore();

            if (te != null && te.isvalid)
            {

            	par5EntityPlayer.openGui(PustySlime.instance, 0, world, te.xCoord, te.yCoord, te.zCoord);
            }

            return true;
        }
	}

    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
    	PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x, y, z);
    	
    	if(dummy != null && dummy.getCore() != null)
    	{
            dummy.getCore().invalidateMultiblock();
    	}
    		super.breakBlock(world, x, y, z, par5, par6);
    }



    public int idDropped(int par1, Random par2Random, int par3)
    {
        return PustySlime.slimeBlock.blockID;
    }
    
    @Override
    public int damageDropped(int par1){
		return 10;
    	
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
	PustySlimeBlockTileEntity p =	new PustySlimeBlockTileEntity();
   
return (TileEntity)p;
}
  
}