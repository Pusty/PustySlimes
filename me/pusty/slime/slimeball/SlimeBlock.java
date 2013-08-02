package me.pusty.slime.slimeball;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.pusty.slime.PustySlime;
import me.pusty.slime.SlimeBallTypes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class SlimeBlock extends Block
{
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;
    public SlimeBlock(int par1)
    {
        super(par1, Material.cake);
        this.setHardness(0.5F);
        this.setResistance(0.5F);
        this.setCreativeTab(PustySlime.pustyTab);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
    	if(par1World.getBlockMetadata(par2, par3, par4)==10)
    		 return AxisAlignedBB.getAABBPool().getAABB((double)par2, (double)par3, (double)par4, (double)(par2 + 1), (double)((float)(par3 + 1)), (double)(par4 + 1));
        float f = 0.125F;
        return AxisAlignedBB.getAABBPool().getAABB((double)par2, (double)par3, (double)par4, (double)(par2 + 1), (double)((float)(par3 + 1) - f), (double)(par4 + 1));
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
            this.iconArray[i] = par1IconRegister.registerIcon("pustyslime:slimeblock" + "_" + SlimeBallTypes.list.get(i).texturename);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
        return this.iconArray[par2 % this.iconArray.length];
    }
    
    
    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 10));
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
      //  par5Entity.motionX *= 0.4D;
       // par5Entity.motionZ *= 0.4D;

    	if(world.getBlockMetadata(x, y, z)==0)
    	entity.motionY += 0.5;
    	if(world.getBlockMetadata(x, y, z)==4)
    	entity.motionY += 0.8;
    	if(world.getBlockMetadata(x, y, z)==1)
    	entity.setFire(15);
    	if(world.getBlockMetadata(x, y, z)==3)
    	entity.extinguish();
  
    	
    }
    
    
    @Override
    public int damageDropped(int i){
    	return i;
    	}

    
    @Override
    public void onFallenUpon(World world, int x, int y, int z, Entity par5Entity, float par6)
    {
    	if(world.getBlockMetadata(x, y, z)==0||world.getBlockMetadata(x, y, z)==4||world.getBlockMetadata(x, y, z)==2)
      par5Entity.fallDistance = 0.0F;
    }
    
    
    
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if (isValidCombination(par2, par3, par4, par1World)) {
				System.out.println("This is the master block");
				replaceValidBlocks(par2,par3,par4,par1World);
				return true;
			
		}
	
		return false;
	}
	
	
	protected boolean isValidCombination(int x,int y,int z,World world){
		int wantedid = this.blockID;
		int coreid = 1;
		int sides = 10;
		
		if(world.getBlockId(x, y, z) == wantedid && world.getBlockMetadata(x, y, z)==coreid){
			int x2 = x;
			int y2 = y;
			int z2 = z;

			
			if(world.getBlockId(x2+1, y2, z2) == wantedid&& world.getBlockMetadata(x2, y2, z2-1) == sides&& world.getBlockMetadata(x2, y2, z2+1) == sides&& world.getBlockMetadata(x2, y2+1, z2) == sides && world.getBlockMetadata(x2, y2-1, z2) == sides && world.getBlockMetadata(x2+1, y2, z2) == sides && world.getBlockMetadata(x2-1, y2, z2) == sides &&world.getBlockId(x2-1, y2, z2) == wantedid &&  world.getBlockId(x2, y2+1, z2) == wantedid  &&  world.getBlockId(x2, y2-1, z2) == wantedid &&  world.getBlockId(x2, y2, z2-1) == wantedid &&  world.getBlockId(x2, y2, z2+1) == wantedid )	
			return true;
		    
		}else if(world.getBlockId(x, y, z) == wantedid && world.getBlockMetadata(x, y, z)!=coreid){
			
			if(world.getBlockId(x-1, y, z) == wantedid && world.getBlockMetadata(x-1, y, z)==coreid)
			{
				int x2 = x-1;
				int y2 = y;
				int z2 = z;
				if(world.getBlockId(x2+1, y2, z2) == wantedid&& world.getBlockMetadata(x2, y2, z2-1) == sides&& world.getBlockMetadata(x2, y2, z2+1) == sides&& world.getBlockMetadata(x2, y2+1, z2) == sides && world.getBlockMetadata(x2, y2-1, z2) == sides && world.getBlockMetadata(x2+1, y2, z2) == sides && world.getBlockMetadata(x2-1, y2, z2) == sides &&world.getBlockId(x2-1, y2, z2) == wantedid &&  world.getBlockId(x2, y2+1, z2) == wantedid  &&  world.getBlockId(x2, y2-1, z2) == wantedid &&  world.getBlockId(x2, y2, z2-1) == wantedid &&  world.getBlockId(x2, y2, z2+1) == wantedid )	
					return true;
			}else if(world.getBlockId(x+1, y, z) == wantedid && world.getBlockMetadata(x+1, y, z)==coreid)
			{
				int x2 = x+1;
				int y2 = y;
				int z2 = z;
				
				if(world.getBlockId(x2+1, y2, z2) == wantedid&& world.getBlockMetadata(x2, y2, z2-1) == sides&& world.getBlockMetadata(x2, y2, z2+1) == sides&& world.getBlockMetadata(x2, y2+1, z2) == sides && world.getBlockMetadata(x2, y2-1, z2) == sides && world.getBlockMetadata(x2+1, y2, z2) == sides && world.getBlockMetadata(x2-1, y2, z2) == sides &&world.getBlockId(x2-1, y2, z2) == wantedid &&  world.getBlockId(x2, y2+1, z2) == wantedid  &&  world.getBlockId(x2, y2-1, z2) == wantedid &&  world.getBlockId(x2, y2, z2-1) == wantedid &&  world.getBlockId(x2, y2, z2+1) == wantedid )	
					return true;
			}else if(world.getBlockId(x, y-1, z) == wantedid && world.getBlockMetadata(x, y-1, z)==coreid)
			{
				int x2 = x;
				int y2 = y-1;
				int z2 = z;
				if(world.getBlockId(x2+1, y2, z2) == wantedid&& world.getBlockMetadata(x2, y2, z2-1) == sides&& world.getBlockMetadata(x2, y2, z2+1) == sides&& world.getBlockMetadata(x2, y2+1, z2) == sides && world.getBlockMetadata(x2, y2-1, z2) == sides && world.getBlockMetadata(x2+1, y2, z2) == sides && world.getBlockMetadata(x2-1, y2, z2) == sides &&world.getBlockId(x2-1, y2, z2) == wantedid &&  world.getBlockId(x2, y2+1, z2) == wantedid  &&  world.getBlockId(x2, y2-1, z2) == wantedid &&  world.getBlockId(x2, y2, z2-1) == wantedid &&  world.getBlockId(x2, y2, z2+1) == wantedid )	
					return true;
			}else if(world.getBlockId(x, y+1, z) == wantedid && world.getBlockMetadata(x, y+1, z)==coreid)
			{
				int x2 = x;
				int y2 = y+1;
				int z2 = z;
				if(world.getBlockId(x2+1, y2, z2) == wantedid&& world.getBlockMetadata(x2, y2, z2-1) == sides&& world.getBlockMetadata(x2, y2, z2+1) == sides&& world.getBlockMetadata(x2, y2+1, z2) == sides && world.getBlockMetadata(x2, y2-1, z2) == sides && world.getBlockMetadata(x2+1, y2, z2) == sides && world.getBlockMetadata(x2-1, y2, z2) == sides &&world.getBlockId(x2-1, y2, z2) == wantedid &&  world.getBlockId(x2, y2+1, z2) == wantedid  &&  world.getBlockId(x2, y2-1, z2) == wantedid &&  world.getBlockId(x2, y2, z2-1) == wantedid &&  world.getBlockId(x2, y2, z2+1) == wantedid )	
					return true;
			}else if(world.getBlockId(x, y, z-1) == wantedid && world.getBlockMetadata(x, y, z-1)==coreid)
			{
				int x2 = x;
				int y2 = y;
				int z2 = z-1;
				if(world.getBlockId(x2+1, y2, z2) == wantedid&& world.getBlockMetadata(x2, y2, z2-1) == sides&& world.getBlockMetadata(x2, y2, z2+1) == sides&& world.getBlockMetadata(x2, y2+1, z2) == sides && world.getBlockMetadata(x2, y2-1, z2) == sides && world.getBlockMetadata(x2+1, y2, z2) == sides && world.getBlockMetadata(x2-1, y2, z2) == sides &&world.getBlockId(x2-1, y2, z2) == wantedid &&  world.getBlockId(x2, y2+1, z2) == wantedid  &&  world.getBlockId(x2, y2-1, z2) == wantedid &&  world.getBlockId(x2, y2, z2-1) == wantedid &&  world.getBlockId(x2, y2, z2+1) == wantedid )	
					return true;
			}else if(world.getBlockId(x, y, z+1) == wantedid && world.getBlockMetadata(x, y, z+1)==coreid)
			{
				int x2 = x;
				int y2 = y;
				int z2 = z+1;
				if(world.getBlockId(x2+1, y2, z2) == wantedid&& world.getBlockMetadata(x2, y2, z2-1) == sides&& world.getBlockMetadata(x2, y2, z2+1) == sides&& world.getBlockMetadata(x2, y2+1, z2) == sides && world.getBlockMetadata(x2, y2-1, z2) == sides && world.getBlockMetadata(x2+1, y2, z2) == sides && world.getBlockMetadata(x2-1, y2, z2) == sides &&world.getBlockId(x2-1, y2, z2) == wantedid &&  world.getBlockId(x2, y2+1, z2) == wantedid  &&  world.getBlockId(x2, y2-1, z2) == wantedid &&  world.getBlockId(x2, y2, z2-1) == wantedid &&  world.getBlockId(x2, y2, z2+1) == wantedid )	
					return true;
			}
	
		    
		}
		return false;
		
	}
	
	protected void replaceValidBlocks(int x,int y,int z,World world){
		int wantedid = this.blockID;
		int coreid = 1;
		int sides = 10;
		int toid= PustySlime.tileentityBlock.blockID;
		int tometa = -1;
		int tocoreid = PustySlime.tileentityCoreBlock.blockID;
		
		if(world.getBlockId(x, y, z) == wantedid && world.getBlockMetadata(x, y, z)==coreid){
			int x2 = x;
			int y2 = y;
			int z2 = z;

			
			if(world.getBlockId(x2+1, y2, z2) == wantedid&& world.getBlockMetadata(x2, y2, z2-1) == sides&& world.getBlockMetadata(x2, y2, z2+1) == sides&& world.getBlockMetadata(x2, y2+1, z2) == sides && world.getBlockMetadata(x2, y2-1, z2) == sides && world.getBlockMetadata(x2+1, y2, z2) == sides && world.getBlockMetadata(x2-1, y2, z2) == sides &&world.getBlockId(x2-1, y2, z2) == wantedid &&  world.getBlockId(x2, y2+1, z2) == wantedid  &&  world.getBlockId(x2, y2-1, z2) == wantedid &&  world.getBlockId(x2, y2, z2-1) == wantedid &&  world.getBlockId(x2, y2, z2+1) == wantedid )	
			{			
				world.setBlock(x2, y2, z2, tocoreid);
				world.setBlock(x2+1, y2, z2, toid);
				world.setBlock(x2-1, y2, z2, toid);
				world.setBlock(x2, y2+1, z2, toid);
				world.setBlock(x2, y2-1, z2, toid);
				world.setBlock(x2, y2, z2+1, toid);
				world.setBlock(x2, y2, z2-1, toid);       		

	       		world.markBlockForUpdate(x2, y2, z2);
	       		world.markBlockForUpdate(x2+1, y2, z2);
	       		world.markBlockForUpdate(x2-1, y2, z2);
	       		world.markBlockForUpdate(x2, y2+1, z2);
	       		world.markBlockForUpdate(x2, y2-1, z2);
	       		world.markBlockForUpdate(x2, y2, z2+1);
	       		world.markBlockForUpdate(x2, y2, z2-1);
	       		
	       		if(tometa != -1){
					world.setBlockMetadataWithNotify(x2, y2, z2, tometa, 0);
					world.setBlockMetadataWithNotify(x2+1, y2, z2, tometa, 0);
					world.setBlockMetadataWithNotify(x2-1, y2, z2, tometa, 0);
					world.setBlockMetadataWithNotify(x2, y2+1, z2, tometa, 0);
					world.setBlockMetadataWithNotify(x2, y2, z2+1, tometa, 0);
					world.setBlockMetadataWithNotify(x2, y2-1, z2, tometa, 0);
		       		world.setBlockMetadataWithNotify(x2, y2, z2-1, tometa, 0);
	       		}else{
					world.setBlockMetadataWithNotify(x2, y2, z2, 0, 0);
					world.setBlockMetadataWithNotify(x2+1, y2, z2, 1, 0);
					world.setBlockMetadataWithNotify(x2-1, y2, z2, 1, 0);
					world.setBlockMetadataWithNotify(x2, y2+1, z2, 1, 0);
					world.setBlockMetadataWithNotify(x2, y2, z2+1, 1, 0);
					world.setBlockMetadataWithNotify(x2, y2-1, z2, 1, 0);
		       		world.setBlockMetadataWithNotify(x2, y2, z2-1, 1, 0);
	       		}
	       		{
	       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2-1, y2, z2);
	        	dummy.setCore(x2,y2,z2);
	       		}
	       		{
	       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2+1, y2, z2);
	        	dummy.setCore(x2,y2,z2);
	       		}
	       		{
	       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2-1, z2);
	        	dummy.setCore(x2,y2,z2);
	       		}
	       		{
	       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2+1, z2);
	        	dummy.setCore(x2,y2,z2);
	       		}
	       		{
	       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2, z2+1);
	        	dummy.setCore(x2,y2,z2);
	       		}
	       		{
	       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2, z2-1);
	        	dummy.setCore(x2,y2,z2);
	       		}
	       		{
	       			((PustySlimeBlockTileEntityCore)world.getBlockTileEntity(x2, y2, z2)).isvalid=true;
	       		}
			}
		    
		}else if(world.getBlockId(x, y, z) == wantedid && world.getBlockMetadata(x, y, z)!=coreid){
			
			if(world.getBlockId(x-1, y, z) == wantedid && world.getBlockMetadata(x-1, y, z)==coreid)
			{
				int x2 = x-1;
				int y2 = y;
				int z2 = z;
				if(world.getBlockId(x2+1, y2, z2) == wantedid&& world.getBlockMetadata(x2, y2, z2-1) == sides&& world.getBlockMetadata(x2, y2, z2+1) == sides&& world.getBlockMetadata(x2, y2+1, z2) == sides && world.getBlockMetadata(x2, y2-1, z2) == sides && world.getBlockMetadata(x2+1, y2, z2) == sides && world.getBlockMetadata(x2-1, y2, z2) == sides &&world.getBlockId(x2-1, y2, z2) == wantedid &&  world.getBlockId(x2, y2+1, z2) == wantedid  &&  world.getBlockId(x2, y2-1, z2) == wantedid &&  world.getBlockId(x2, y2, z2-1) == wantedid &&  world.getBlockId(x2, y2, z2+1) == wantedid )	
				{
					world.setBlock(x2, y2, z2, tocoreid);
					world.setBlock(x2+1, y2, z2, toid);
					world.setBlock(x2-1, y2, z2, toid);
					world.setBlock(x2, y2+1, z2, toid);
					world.setBlock(x2, y2-1, z2, toid);
					world.setBlock(x2, y2, z2+1, toid);
					world.setBlock(x2, y2, z2-1, toid);       		

		       		world.markBlockForUpdate(x2, y2, z2);
		       		world.markBlockForUpdate(x2+1, y2, z2);
		       		world.markBlockForUpdate(x2-1, y2, z2);
		       		world.markBlockForUpdate(x2, y2+1, z2);
		       		world.markBlockForUpdate(x2, y2-1, z2);
		       		world.markBlockForUpdate(x2, y2, z2+1);
		       		world.markBlockForUpdate(x2, y2, z2-1);
		       		
		       		if(tometa != -1){
						world.setBlockMetadataWithNotify(x2, y2, z2, tometa, 0);
						world.setBlockMetadataWithNotify(x2+1, y2, z2, tometa, 0);
						world.setBlockMetadataWithNotify(x2-1, y2, z2, tometa, 0);
						world.setBlockMetadataWithNotify(x2, y2+1, z2, tometa, 0);
						world.setBlockMetadataWithNotify(x2, y2, z2+1, tometa, 0);
						world.setBlockMetadataWithNotify(x2, y2-1, z2, tometa, 0);
			       		world.setBlockMetadataWithNotify(x2, y2, z2-1, tometa, 0);
		       		}else{
						world.setBlockMetadataWithNotify(x2, y2, z2, 0, 0);
						world.setBlockMetadataWithNotify(x2+1, y2, z2, 1, 0);
						world.setBlockMetadataWithNotify(x2-1, y2, z2, 1, 0);
						world.setBlockMetadataWithNotify(x2, y2+1, z2, 1, 0);
						world.setBlockMetadataWithNotify(x2, y2, z2+1, 1, 0);
						world.setBlockMetadataWithNotify(x2, y2-1, z2, 1, 0);
			       		world.setBlockMetadataWithNotify(x2, y2, z2-1, 1, 0);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2-1, y2, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2+1, y2, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2-1, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2+1, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2, z2+1);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2, z2-1);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       			((PustySlimeBlockTileEntityCore)world.getBlockTileEntity(x2, y2, z2)).isvalid=true;
		       		}
				}
			}else if(world.getBlockId(x+1, y, z) == wantedid && world.getBlockMetadata(x+1, y, z)==coreid)
			{
				int x2 = x+1;
				int y2 = y;
				int z2 = z;
				
				if(world.getBlockId(x2+1, y2, z2) == wantedid&& world.getBlockMetadata(x2, y2, z2-1) == sides&& world.getBlockMetadata(x2, y2, z2+1) == sides&& world.getBlockMetadata(x2, y2+1, z2) == sides && world.getBlockMetadata(x2, y2-1, z2) == sides && world.getBlockMetadata(x2+1, y2, z2) == sides && world.getBlockMetadata(x2-1, y2, z2) == sides &&world.getBlockId(x2-1, y2, z2) == wantedid &&  world.getBlockId(x2, y2+1, z2) == wantedid  &&  world.getBlockId(x2, y2-1, z2) == wantedid &&  world.getBlockId(x2, y2, z2-1) == wantedid &&  world.getBlockId(x2, y2, z2+1) == wantedid )	
				{
					world.setBlock(x2, y2, z2, tocoreid);
					world.setBlock(x2+1, y2, z2, toid);
					world.setBlock(x2-1, y2, z2, toid);
					world.setBlock(x2, y2+1, z2, toid);
					world.setBlock(x2, y2-1, z2, toid);
					world.setBlock(x2, y2, z2+1, toid);
					world.setBlock(x2, y2, z2-1, toid);       		

		       		world.markBlockForUpdate(x2, y2, z2);
		       		world.markBlockForUpdate(x2+1, y2, z2);
		       		world.markBlockForUpdate(x2-1, y2, z2);
		       		world.markBlockForUpdate(x2, y2+1, z2);
		       		world.markBlockForUpdate(x2, y2-1, z2);
		       		world.markBlockForUpdate(x2, y2, z2+1);
		       		world.markBlockForUpdate(x2, y2, z2-1);
		       		
		       		if(tometa != -1){
						world.setBlockMetadataWithNotify(x2, y2, z2, tometa, 0);
						world.setBlockMetadataWithNotify(x2+1, y2, z2, tometa, 0);
						world.setBlockMetadataWithNotify(x2-1, y2, z2, tometa, 0);
						world.setBlockMetadataWithNotify(x2, y2+1, z2, tometa, 0);
						world.setBlockMetadataWithNotify(x2, y2, z2+1, tometa, 0);
						world.setBlockMetadataWithNotify(x2, y2-1, z2, tometa, 0);
			       		world.setBlockMetadataWithNotify(x2, y2, z2-1, tometa, 0);
		       		}else{
						world.setBlockMetadataWithNotify(x2, y2, z2, 0, 0);
						world.setBlockMetadataWithNotify(x2+1, y2, z2, 1, 0);
						world.setBlockMetadataWithNotify(x2-1, y2, z2, 1, 0);
						world.setBlockMetadataWithNotify(x2, y2+1, z2, 1, 0);
						world.setBlockMetadataWithNotify(x2, y2, z2+1, 1, 0);
						world.setBlockMetadataWithNotify(x2, y2-1, z2, 1, 0);
			       		world.setBlockMetadataWithNotify(x2, y2, z2-1, 1, 0);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2-1, y2, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2+1, y2, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2-1, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2+1, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2, z2+1);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2, z2-1);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       			((PustySlimeBlockTileEntityCore)world.getBlockTileEntity(x2, y2, z2)).isvalid=true;
		       		}
		       		
				}
			}else if(world.getBlockId(x, y-1, z) == wantedid && world.getBlockMetadata(x, y-1, z)==coreid)
			{
				int x2 = x;
				int y2 = y-1;
				int z2 = z;
				if(world.getBlockId(x2+1, y2, z2) == wantedid&& world.getBlockMetadata(x2, y2, z2-1) == sides&& world.getBlockMetadata(x2, y2, z2+1) == sides&& world.getBlockMetadata(x2, y2+1, z2) == sides && world.getBlockMetadata(x2, y2-1, z2) == sides && world.getBlockMetadata(x2+1, y2, z2) == sides && world.getBlockMetadata(x2-1, y2, z2) == sides &&world.getBlockId(x2-1, y2, z2) == wantedid &&  world.getBlockId(x2, y2+1, z2) == wantedid  &&  world.getBlockId(x2, y2-1, z2) == wantedid &&  world.getBlockId(x2, y2, z2-1) == wantedid &&  world.getBlockId(x2, y2, z2+1) == wantedid )	
				{
					world.setBlock(x2, y2, z2, tocoreid);
					world.setBlock(x2+1, y2, z2, toid);
					world.setBlock(x2-1, y2, z2, toid);
					world.setBlock(x2, y2+1, z2, toid);
					world.setBlock(x2, y2-1, z2, toid);
					world.setBlock(x2, y2, z2+1, toid);
					world.setBlock(x2, y2, z2-1, toid);       		

		       		world.markBlockForUpdate(x2, y2, z2);
		       		world.markBlockForUpdate(x2+1, y2, z2);
		       		world.markBlockForUpdate(x2-1, y2, z2);
		       		world.markBlockForUpdate(x2, y2+1, z2);
		       		world.markBlockForUpdate(x2, y2-1, z2);
		       		world.markBlockForUpdate(x2, y2, z2+1);
		       		world.markBlockForUpdate(x2, y2, z2-1);
		       		if(tometa != -1){
						world.setBlockMetadataWithNotify(x2, y2, z2, tometa, 0);
						world.setBlockMetadataWithNotify(x2+1, y2, z2, tometa, 0);
						world.setBlockMetadataWithNotify(x2-1, y2, z2, tometa, 0);
						world.setBlockMetadataWithNotify(x2, y2+1, z2, tometa, 0);
						world.setBlockMetadataWithNotify(x2, y2, z2+1, tometa, 0);
						world.setBlockMetadataWithNotify(x2, y2-1, z2, tometa, 0);
			       		world.setBlockMetadataWithNotify(x2, y2, z2-1, tometa, 0);
		       		}else{
						world.setBlockMetadataWithNotify(x2, y2, z2, 0, 0);
						world.setBlockMetadataWithNotify(x2+1, y2, z2, 1, 0);
						world.setBlockMetadataWithNotify(x2-1, y2, z2, 1, 0);
						world.setBlockMetadataWithNotify(x2, y2+1, z2, 1, 0);
						world.setBlockMetadataWithNotify(x2, y2, z2+1, 1, 0);
						world.setBlockMetadataWithNotify(x2, y2-1, z2, 1, 0);
			       		world.setBlockMetadataWithNotify(x2, y2, z2-1, 1, 0);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2-1, y2, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2+1, y2, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2-1, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2+1, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2, z2+1);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2, z2-1);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       			((PustySlimeBlockTileEntityCore)world.getBlockTileEntity(x2, y2, z2)).isvalid=true;
		       		}
				}
			}else if(world.getBlockId(x, y+1, z) == wantedid && world.getBlockMetadata(x, y+1, z)==coreid)
			{
				int x2 = x;
				int y2 = y+1;
				int z2 = z;
				if(world.getBlockId(x2+1, y2, z2) == wantedid&& world.getBlockMetadata(x2, y2, z2-1) == sides&& world.getBlockMetadata(x2, y2, z2+1) == sides&& world.getBlockMetadata(x2, y2+1, z2) == sides && world.getBlockMetadata(x2, y2-1, z2) == sides && world.getBlockMetadata(x2+1, y2, z2) == sides && world.getBlockMetadata(x2-1, y2, z2) == sides &&world.getBlockId(x2-1, y2, z2) == wantedid &&  world.getBlockId(x2, y2+1, z2) == wantedid  &&  world.getBlockId(x2, y2-1, z2) == wantedid &&  world.getBlockId(x2, y2, z2-1) == wantedid &&  world.getBlockId(x2, y2, z2+1) == wantedid )	
				{
					world.setBlock(x2, y2, z2, tocoreid);
					world.setBlock(x2+1, y2, z2, toid);
					world.setBlock(x2-1, y2, z2, toid);
					world.setBlock(x2, y2+1, z2, toid);
					world.setBlock(x2, y2-1, z2, toid);
					world.setBlock(x2, y2, z2+1, toid);
					world.setBlock(x2, y2, z2-1, toid);       		
	
		       		world.markBlockForUpdate(x2, y2, z2);
		       		world.markBlockForUpdate(x2+1, y2, z2);
		       		world.markBlockForUpdate(x2-1, y2, z2);
		       		world.markBlockForUpdate(x2, y2+1, z2);
		       		world.markBlockForUpdate(x2, y2-1, z2);
		       		world.markBlockForUpdate(x2, y2, z2+1);
		       		world.markBlockForUpdate(x2, y2, z2-1);
		       		if(tometa != -1){
							world.setBlockMetadataWithNotify(x2, y2, z2, tometa, 0);
							world.setBlockMetadataWithNotify(x2+1, y2, z2, tometa, 0);
							world.setBlockMetadataWithNotify(x2-1, y2, z2, tometa, 0);
							world.setBlockMetadataWithNotify(x2, y2+1, z2, tometa, 0);
							world.setBlockMetadataWithNotify(x2, y2, z2+1, tometa, 0);
							world.setBlockMetadataWithNotify(x2, y2-1, z2, tometa, 0);
				       		world.setBlockMetadataWithNotify(x2, y2, z2-1, tometa, 0);
			       		}else{
							world.setBlockMetadataWithNotify(x2, y2, z2, 0, 0);
							world.setBlockMetadataWithNotify(x2+1, y2, z2, 1, 0);
							world.setBlockMetadataWithNotify(x2-1, y2, z2, 1, 0);
							world.setBlockMetadataWithNotify(x2, y2+1, z2, 1, 0);
							world.setBlockMetadataWithNotify(x2, y2, z2+1, 1, 0);
							world.setBlockMetadataWithNotify(x2, y2-1, z2, 1, 0);
				       		world.setBlockMetadataWithNotify(x2, y2, z2-1, 1, 0);
			       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2-1, y2, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2+1, y2, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2-1, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2+1, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2, z2+1);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2, z2-1);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       			((PustySlimeBlockTileEntityCore)world.getBlockTileEntity(x2, y2, z2)).isvalid=true;
		       		}
				}
			}else if(world.getBlockId(x, y, z-1) == wantedid && world.getBlockMetadata(x, y, z-1)==coreid)
			{
				int x2 = x;
				int y2 = y;
				int z2 = z-1;
				if(world.getBlockId(x2+1, y2, z2) == wantedid&& world.getBlockMetadata(x2, y2, z2-1) == sides&& world.getBlockMetadata(x2, y2, z2+1) == sides&& world.getBlockMetadata(x2, y2+1, z2) == sides && world.getBlockMetadata(x2, y2-1, z2) == sides && world.getBlockMetadata(x2+1, y2, z2) == sides && world.getBlockMetadata(x2-1, y2, z2) == sides &&world.getBlockId(x2-1, y2, z2) == wantedid &&  world.getBlockId(x2, y2+1, z2) == wantedid  &&  world.getBlockId(x2, y2-1, z2) == wantedid &&  world.getBlockId(x2, y2, z2-1) == wantedid &&  world.getBlockId(x2, y2, z2+1) == wantedid )	
				{
					world.setBlock(x2, y2, z2, tocoreid);
					world.setBlock(x2+1, y2, z2, toid);
					world.setBlock(x2-1, y2, z2, toid);
					world.setBlock(x2, y2+1, z2, toid);
					world.setBlock(x2, y2-1, z2, toid);
					world.setBlock(x2, y2, z2+1, toid);
					world.setBlock(x2, y2, z2-1, toid);       		
	
		       		world.markBlockForUpdate(x2, y2, z2);
		       		world.markBlockForUpdate(x2+1, y2, z2);
		       		world.markBlockForUpdate(x2-1, y2, z2);
		       		world.markBlockForUpdate(x2, y2+1, z2);
		       		world.markBlockForUpdate(x2, y2-1, z2);
		       		world.markBlockForUpdate(x2, y2, z2+1);
		       		world.markBlockForUpdate(x2, y2, z2-1);
		       		if(tometa != -1){
							world.setBlockMetadataWithNotify(x2, y2, z2, tometa, 0);
							world.setBlockMetadataWithNotify(x2+1, y2, z2, tometa, 0);
							world.setBlockMetadataWithNotify(x2-1, y2, z2, tometa, 0);
							world.setBlockMetadataWithNotify(x2, y2+1, z2, tometa, 0);
							world.setBlockMetadataWithNotify(x2, y2, z2+1, tometa, 0);
							world.setBlockMetadataWithNotify(x2, y2-1, z2, tometa, 0);
				       		world.setBlockMetadataWithNotify(x2, y2, z2-1, tometa, 0);
			       		}else{
							world.setBlockMetadataWithNotify(x2, y2, z2, 0, 0);
							world.setBlockMetadataWithNotify(x2+1, y2, z2, 1, 0);
							world.setBlockMetadataWithNotify(x2-1, y2, z2, 1, 0);
							world.setBlockMetadataWithNotify(x2, y2+1, z2, 1, 0);
							world.setBlockMetadataWithNotify(x2, y2, z2+1, 1, 0);
							world.setBlockMetadataWithNotify(x2, y2-1, z2, 1, 0);
				       		world.setBlockMetadataWithNotify(x2, y2, z2-1, 1, 0);
			       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2-1, y2, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2+1, y2, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2-1, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2+1, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2, z2+1);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2, z2-1);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       			((PustySlimeBlockTileEntityCore)world.getBlockTileEntity(x2, y2, z2)).isvalid=true;
		       		}
				}
			}else if(world.getBlockId(x, y, z+1) == wantedid && world.getBlockMetadata(x, y, z+1)==coreid)
			{
				int x2 = x;
				int y2 = y;
				int z2 = z+1;
				if(world.getBlockId(x2+1, y2, z2) == wantedid&& world.getBlockMetadata(x2, y2, z2-1) == sides&& world.getBlockMetadata(x2, y2, z2+1) == sides&& world.getBlockMetadata(x2, y2+1, z2) == sides && world.getBlockMetadata(x2, y2-1, z2) == sides && world.getBlockMetadata(x2+1, y2, z2) == sides && world.getBlockMetadata(x2-1, y2, z2) == sides &&world.getBlockId(x2-1, y2, z2) == wantedid &&  world.getBlockId(x2, y2+1, z2) == wantedid  &&  world.getBlockId(x2, y2-1, z2) == wantedid &&  world.getBlockId(x2, y2, z2-1) == wantedid &&  world.getBlockId(x2, y2, z2+1) == wantedid )	
				{
					world.setBlock(x2, y2, z2, tocoreid);
					world.setBlock(x2+1, y2, z2, toid);
					world.setBlock(x2-1, y2, z2, toid);
					world.setBlock(x2, y2+1, z2, toid);
					world.setBlock(x2, y2-1, z2, toid);
					world.setBlock(x2, y2, z2+1, toid);
					world.setBlock(x2, y2, z2-1, toid);       		

		       		
		       		
		       		
		       		world.markBlockForUpdate(x2, y2, z2);
		       		world.markBlockForUpdate(x2+1, y2, z2);
		       		world.markBlockForUpdate(x2-1, y2, z2);
		       		world.markBlockForUpdate(x2, y2+1, z2);
		       		world.markBlockForUpdate(x2, y2-1, z2);
		       		world.markBlockForUpdate(x2, y2, z2+1);
		       		world.markBlockForUpdate(x2, y2, z2-1);
		       		
		       		if(tometa != -1){
						world.setBlockMetadataWithNotify(x2, y2, z2, tometa, 0);
						world.setBlockMetadataWithNotify(x2+1, y2, z2, tometa, 0);
						world.setBlockMetadataWithNotify(x2-1, y2, z2, tometa, 0);
						world.setBlockMetadataWithNotify(x2, y2+1, z2, tometa, 0);
						world.setBlockMetadataWithNotify(x2, y2, z2+1, tometa, 0);
						world.setBlockMetadataWithNotify(x2, y2-1, z2, tometa, 0);
			       		world.setBlockMetadataWithNotify(x2, y2, z2-1, tometa, 0);
		       		}else{
						world.setBlockMetadataWithNotify(x2, y2, z2, 0, 0);
						world.setBlockMetadataWithNotify(x2+1, y2, z2, 1, 0);
						world.setBlockMetadataWithNotify(x2-1, y2, z2, 1, 0);
						world.setBlockMetadataWithNotify(x2, y2+1, z2, 1, 0);
						world.setBlockMetadataWithNotify(x2, y2, z2+1, 1, 0);
						world.setBlockMetadataWithNotify(x2, y2-1, z2, 1, 0);
			       		world.setBlockMetadataWithNotify(x2, y2, z2-1, 1, 0);
		       		}
		       		
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2-1, y2, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2+1, y2, z2);
		       		dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2-1, z2);
		       		dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2+1, z2);
		       		dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2, z2+1);
		       		dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)world.getBlockTileEntity(x2, y2, z2-1);
		       		dummy.setCore(x2,y2,z2);
		       		}
		       		{
		       			((PustySlimeBlockTileEntityCore)world.getBlockTileEntity(x2, y2, z2)).isvalid=true;
		       		}
		       		
		       		
				}
			}
	
		    
		}

		
		
	}
	
	
	
	
    /*
    public void onEntityWalking(World world, int x, int y, int z, Entity entity)
	{
	  entity.motionY += 2.0;
      if(entity instanceof EntityLiving){
        entity.fallDistance=0;
      }
	}*/
}