package me.pusty.slime.slimeball;

import java.util.Random;

import me.pusty.slime.PustySlime;
import me.pusty.slime.SlimeTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
 
public class PustySlimeBlockTileEntityCore  extends TileEntity  implements IInventory{

	public PustySlimeBlockTileEntityCore(){

	}

	   ItemStack[] dispenserContents = new ItemStack[2];


		boolean isvalid;
		int at=0;
		

	    /**
	     * random number generator for instance. Used in random item stack selection.
	     */
	    private Random dispenserRandom = new Random();
	    protected String customName;

	    /**
	     * Returns the number of slots in the inventory.
	     */
	    public int getSizeInventory()
	    {
	        return 2;
	    }

	    /**
	     * Returns the stack in slot i
	     */
	    
	    
		@Override
		public void updateEntity()
		{

			if(!isvalid)
			{
				int coreX = this.xCoord;
				int coreY = this.yCoord;
				int coreZ = this.zCoord;
				int x2=coreX;
				int y2=coreY;
				int z2=coreZ;
				
				int toid= PustySlime.tileentityBlock.blockID;
				int tometa = -1;
				
				if(this.worldObj.getBlockId(coreX-1, coreY, coreZ) == PustySlime.tileentityBlock.blockID && ((PustySlimeBlockTileEntity)this.worldObj.getBlockTileEntity(coreX-1, coreY, coreZ)).getCore()==this){
					if(this.worldObj.getBlockId(coreX+1, coreY, coreZ) == PustySlime.tileentityBlock.blockID && ((PustySlimeBlockTileEntity)this.worldObj.getBlockTileEntity(coreX+1, coreY, coreZ)).getCore()==this){
						if(this.worldObj.getBlockId(coreX, coreY+1, coreZ) == PustySlime.tileentityBlock.blockID && ((PustySlimeBlockTileEntity)this.worldObj.getBlockTileEntity(coreX, coreY+1, coreZ)).getCore()==this){
							if(this.worldObj.getBlockId(coreX, coreY-1, coreZ) == PustySlime.tileentityBlock.blockID && ((PustySlimeBlockTileEntity)this.worldObj.getBlockTileEntity(coreX, coreY-1, coreZ)).getCore()==this){
								if(this.worldObj.getBlockId(coreX, coreY, coreZ+1) == PustySlime.tileentityBlock.blockID && ((PustySlimeBlockTileEntity)this.worldObj.getBlockTileEntity(coreX, coreY, coreZ+1)).getCore()==this){
									if(this.worldObj.getBlockId(coreX, coreY, coreZ-1) == PustySlime.tileentityBlock.blockID && ((PustySlimeBlockTileEntity)this.worldObj.getBlockTileEntity(coreX, coreY, coreZ-1)).getCore()==this){
										isvalid = true;
										return;
									}
								}
							}
						}
					}
				}
			
				
				if(this.worldObj.getBlockId(coreX-1, coreY, coreZ) == PustySlime.tileentityBlock.blockID){
					
				}else if(this.worldObj.getBlockId(coreX-1, coreY, coreZ) == PustySlime.slimeBlock.blockID && this.worldObj.getBlockMetadata(coreX-1, coreY, coreZ) == 10){
					worldObj.setBlock(x2-1, y2, z2, toid);  		

					worldObj.markBlockForUpdate(x2, y2, z2);
					worldObj.markBlockForUpdate(x2+1, y2, z2);
					worldObj.markBlockForUpdate(x2-1, y2, z2);
					worldObj.markBlockForUpdate(x2, y2+1, z2);
					worldObj.markBlockForUpdate(x2, y2-1, z2);
					worldObj.markBlockForUpdate(x2, y2, z2+1);
					worldObj.markBlockForUpdate(x2, y2, z2-1);
		       		
		       		if(tometa != -1){
						worldObj.setBlockMetadataWithNotify(x2-1, y2, z2, tometa, 0);
		       		}else{
		       			worldObj.setBlockMetadataWithNotify(x2-1, y2, z2, 1, 0);
		       		}
		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)worldObj.getBlockTileEntity(x2-1, y2, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
				}
				
			if(this.worldObj.getBlockId(coreX+1, coreY, coreZ) == PustySlime.tileentityBlock.blockID){
					
				}else if(this.worldObj.getBlockId(coreX+1, coreY, coreZ) == PustySlime.slimeBlock.blockID && this.worldObj.getBlockMetadata(coreX+1, coreY, coreZ) == 10){
					worldObj.setBlock(x2+1, y2, z2, toid);
	

					worldObj.markBlockForUpdate(x2, y2, z2);
					worldObj.markBlockForUpdate(x2+1, y2, z2);
					worldObj.markBlockForUpdate(x2-1, y2, z2);
					worldObj.markBlockForUpdate(x2, y2+1, z2);
					worldObj.markBlockForUpdate(x2, y2-1, z2);
					worldObj.markBlockForUpdate(x2, y2, z2+1);
					worldObj.markBlockForUpdate(x2, y2, z2-1);
		       		
		       		if(tometa != -1){

		       			worldObj.setBlockMetadataWithNotify(x2+1, y2, z2, tometa, 0);

		       		}else{
		       			worldObj.setBlockMetadataWithNotify(x2+1, y2, z2, 1, 0);

		       		}

		       		{
		       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)worldObj.getBlockTileEntity(x2+1, y2, z2);
		        	dummy.setCore(x2,y2,z2);
		       		}
		       	
				}
			if(this.worldObj.getBlockId(coreX, coreY+1, coreZ) == PustySlime.tileentityBlock.blockID){
				
			}else if(this.worldObj.getBlockId(coreX, coreY+1, coreZ) == PustySlime.slimeBlock.blockID && this.worldObj.getBlockMetadata(coreX, coreY+1, coreZ) == 10){
			
				worldObj.setBlock(x2, y2+1, z2, toid);
			

				worldObj.markBlockForUpdate(x2, y2, z2);
				worldObj.markBlockForUpdate(x2+1, y2, z2);
				worldObj.markBlockForUpdate(x2-1, y2, z2);
				worldObj.markBlockForUpdate(x2, y2+1, z2);
				worldObj.markBlockForUpdate(x2, y2-1, z2);
				worldObj.markBlockForUpdate(x2, y2, z2+1);
				worldObj.markBlockForUpdate(x2, y2, z2-1);
	       		
	       		if(tometa != -1){

					worldObj.setBlockMetadataWithNotify(x2, y2+1, z2, tometa, 0);

	       		}else{

	       			worldObj.setBlockMetadataWithNotify(x2, y2+1, z2, 1, 0);

	       		}
	      
	       		{
	       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)worldObj.getBlockTileEntity(x2, y2+1, z2);
	        	dummy.setCore(x2,y2,z2);
	       		}
	       		
			}

			if(this.worldObj.getBlockId(coreX, coreY, coreZ+1) == PustySlime.tileentityBlock.blockID){
				
			}else if(this.worldObj.getBlockId(coreX, coreY, coreZ+1) == PustySlime.slimeBlock.blockID && this.worldObj.getBlockMetadata(coreX, coreY, coreZ+1) == 10){

				worldObj.setBlock(x2, y2, z2+1, toid);   		

				
				worldObj.markBlockForUpdate(x2, y2, z2+1);
	       		
	       		if(tometa != -1){
	       			
					worldObj.setBlockMetadataWithNotify(x2, y2, z2+1, tometa, 0);
					
	       		}else{
	       	
	       			worldObj.setBlockMetadataWithNotify(x2, y2, z2+1, 1, 0);
	       		}
	       	
	       		{
	       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)worldObj.getBlockTileEntity(x2, y2, z2+1);
	        	dummy.setCore(x2,y2,z2);
	       		}
	       		
			}
			
			if(this.worldObj.getBlockId(coreX, coreY-1, coreZ) == PustySlime.tileentityBlock.blockID){
				
			}else if(this.worldObj.getBlockId(coreX, coreY-1, coreZ) == PustySlime.slimeBlock.blockID && this.worldObj.getBlockMetadata(coreX, coreY-1, coreZ) == 10){

				worldObj.setBlock(x2, y2-1, z2, toid);   		

				
				worldObj.markBlockForUpdate(x2, y2-1, z2);
	       		
	       		if(tometa != -1){
	       			
					worldObj.setBlockMetadataWithNotify(x2, y2-1, z2, tometa, 0);
					
	       		}else{
	       	
	       			worldObj.setBlockMetadataWithNotify(x2, y2-1, z2, 1, 0);
	       		}
	       	
	       		{
	       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)worldObj.getBlockTileEntity(x2, y2-1, z2);
	        	dummy.setCore(x2,y2,z2);
	       		}
	       		
			}
			
			if(this.worldObj.getBlockId(coreX, coreY, coreZ-1) == PustySlime.tileentityBlock.blockID){
				
			}else if(this.worldObj.getBlockId(coreX, coreY, coreZ-1) == PustySlime.slimeBlock.blockID && this.worldObj.getBlockMetadata(coreX, coreY, coreZ-1) == 10){

				worldObj.setBlock(x2, y2, z2-1, toid);

				worldObj.markBlockForUpdate(x2, y2, z2-1);
	       		
	       		if(tometa != -1){

					worldObj.setBlockMetadataWithNotify(x2, y2, z2-1, tometa, 0);
	       		}else{
	       			worldObj.setBlockMetadataWithNotify(x2, y2, z2-1, 1, 0);
	       		}

	       		{
	       		PustySlimeBlockTileEntity dummy = (PustySlimeBlockTileEntity)worldObj.getBlockTileEntity(x2, y2, z2-1);
	        	dummy.setCore(x2,y2,z2);
	       		}
	
			}
				return;
			}
			
			if(canSmelt()){
			if(at>= 1000){
			smeltItem();
			at=0;
			
			}else
				at++;
			}else
				at = 0;
			
		}
	    
		
		private boolean canSmelt()
		{
			if(dispenserContents[0] == null)
				return false;
			else
			{
				ItemStack itemStack = FurnaceRecipes.smelting().getSmeltingResult(dispenserContents[0]);
				if(itemStack == null)
					return false;
				if(dispenserContents[1] == null)
					return true;
				if(!dispenserContents[1].isItemEqual(itemStack))
					return false;
				
				int resultingStackSize = dispenserContents[1].stackSize + itemStack.stackSize;
				return (resultingStackSize <= getInventoryStackLimit() && resultingStackSize <= itemStack.getMaxStackSize());
			}
		}
		
		public void smeltItem()
		{
			if(canSmelt())
			{
				ItemStack itemStack = FurnaceRecipes.smelting().getSmeltingResult(dispenserContents[0]);
				
				if(dispenserContents[1] == null)
					dispenserContents[1] = itemStack.copy();
				else if(dispenserContents[1].isItemEqual(itemStack))
					dispenserContents[1].stackSize += itemStack.stackSize;
				
				dispenserContents[0].stackSize--;
				if(dispenserContents[0].stackSize <= 0)
					dispenserContents[0] = null;
			}
		}
	    
	    
	    public ItemStack getStackInSlot(int par1)
	    {
	    	return this.dispenserContents[par1];
	    }

	    /**
	     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
	     * new stack.
	     */
	    public ItemStack decrStackSize(int par1, int par2)
	    {
	        if (this.dispenserContents[par1] != null)
	        {
	            ItemStack itemstack;

	            if (this.dispenserContents[par1].stackSize <= par2)
	            {
	                itemstack = this.dispenserContents[par1];
	                this.dispenserContents[par1] = null;
	                this.onInventoryChanged();
	                return itemstack;
	            }
	            else
	            {
	                itemstack = this.dispenserContents[par1].splitStack(par2);

	                if (this.dispenserContents[par1].stackSize == 0)
	                {
	                    this.dispenserContents[par1] = null;
	                }

	                this.onInventoryChanged();
	                return itemstack;
	            }
	        }
	        else
	        {
	            return null;
	        }
	    }

	   
	     
	    /**
	     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
	     * like when you close a workbench GUI.
	     */
	    public ItemStack getStackInSlotOnClosing(int par1)
	    {
	        if (this.dispenserContents[par1] != null)
	        {
	            ItemStack itemstack = this.dispenserContents[par1];
	            this.dispenserContents[par1] = null;
	            return itemstack;
	        }
	        else
	        {
	            return null;
	        }
	    }

	    public int getRandomStackFromInventory()
	    {
	        int i = -1;
	        int j = 1;

	        for (int k = 0; k < this.dispenserContents.length; ++k)
	        {
	            if (this.dispenserContents[k] != null && this.dispenserRandom.nextInt(j++) == 0)
	            {
	                i = k;
	            }
	        }

	        return i;
	    }

	    
	    /**
	     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	     */
	    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	    {
	        this.dispenserContents[par1] = par2ItemStack;

	        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
	        {
	            par2ItemStack.stackSize = this.getInventoryStackLimit();
	        }

	        this.onInventoryChanged();
	    }

	    /**
	     * Add item stack in first available inventory slot
	     */
	    public int addItem(ItemStack par1ItemStack)
	    {
	        for (int i = 0; i < this.dispenserContents.length; ++i)
	        {
	            if (this.dispenserContents[i] == null || this.dispenserContents[i].itemID == 0)
	            {
	                this.setInventorySlotContents(i, par1ItemStack);
	                return i;
	            }
	        }

	        return -1;
	    }

	    /**
	     * Returns the name of the inventory.
	     */
	    public String getInvName()
	    {
	        return this.isInvNameLocalized() ? this.customName : "SlimeFurnance";
	    }

	    public void setCustomName(String par1Str)
	    {
	        this.customName = par1Str;
	    }

	    /**
	     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
	     * language. Otherwise it will be used directly.
	     */
	    public boolean isInvNameLocalized()
	    {
	        return this.customName != null;
	    }

	    /**
	     * Reads a tile entity from NBT.
	     */
	    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	    {
	        super.readFromNBT(par1NBTTagCompound);
	        isvalid = par1NBTTagCompound.getBoolean("isValidMultiblock");
	        at = par1NBTTagCompound.getInteger("allreadyTicked");
	        
	        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
	        this.dispenserContents = new ItemStack[this.getSizeInventory()];

	        for (int i = 0; i < nbttaglist.tagCount(); ++i)
	        {
	            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
	            int j = nbttagcompound1.getByte("Slot") & 255;

	            if (j >= 0 && j < this.dispenserContents.length)
	            {
	                this.dispenserContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
	            }
	        }

	        if (par1NBTTagCompound.hasKey("CustomName"))
	        {
	            this.customName = par1NBTTagCompound.getString("CustomName");
	        }
	    }

	    /**
	     * Writes a tile entity to NBT.
	     */
	    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	    {
	        super.writeToNBT(par1NBTTagCompound);
	        par1NBTTagCompound.setBoolean("isValidMultiblock", isvalid);
	        par1NBTTagCompound.setInteger("allreadyTicked", at);
	        NBTTagList nbttaglist = new NBTTagList();

	        for (int i = 0; i < this.dispenserContents.length; ++i)
	        {
	            if (this.dispenserContents[i] != null)
	            {
	                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	                nbttagcompound1.setByte("Slot", (byte)i);
	                this.dispenserContents[i].writeToNBT(nbttagcompound1);
	                nbttaglist.appendTag(nbttagcompound1);
	            }
	        }

	        par1NBTTagCompound.setTag("Items", nbttaglist);

	        if (this.isInvNameLocalized())
	        {
	            par1NBTTagCompound.setString("CustomName", this.customName);
	        }
	    }

	    /**
	     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
	     * this more of a set than a get?*
	     */
	    public int getInventoryStackLimit()
	    {
	        return 64;
	    }

	    /**
	     * Do not make give this method the name canInteractWith because it clashes with Container
	     */
	    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	    {
	        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	    }

	    public void openChest() {}

	    public void closeChest() {}

	    /**
	     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
	     */
	    public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack)
	    {
	        return true;
	    }

		public void invalidateMultiblock() {
			this.isvalid=false;
			
		}

		@Override
		public boolean isItemValidForSlot(int i, ItemStack itemstack) {
			if(i==0)
				return true;
			return false;
		}




}