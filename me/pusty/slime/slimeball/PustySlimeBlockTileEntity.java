package me.pusty.slime.slimeball;

import java.util.Random;

import me.pusty.slime.PustySlime;
import me.pusty.slime.SlimeTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
 
public class PustySlimeBlockTileEntity  extends TileEntity  implements IInventory{

	public PustySlimeBlockTileEntity(){
	}

	   private ItemStack[] dispenserContents = new ItemStack[9];
	   public PustySlimeBlockTileEntityCore core = null;
		int coreX;
		int coreY;
		int coreZ;

		public void setCore(PustySlimeBlockTileEntityCore core)
		{
			coreX = core.xCoord;
			coreY = core.yCoord;
			coreZ = core.zCoord;
			this.core = core;
		}
		
		public void setCore(int x,int y,int z)
		{
			coreX = x;
			coreY = y;
			coreZ = z;
		}
		
		public PustySlimeBlockTileEntityCore getCore()
		{
			if(this.core == null)
				this.core = (PustySlimeBlockTileEntityCore)worldObj.getBlockTileEntity(coreX, coreY, coreZ);
			
			return this.core;
		}
		

	    public int idDropped(int par1, Random par2Random, int par3)
	    {
	        return PustySlime.slimeBlock.blockID;
	    }

	    public int getSizeInventory()
	    {
	    	if(getCore() != null)
	        return getCore().getSizeInventory();
	    	else
	    	return 0;
	    }

	    /**
	     * Returns the stack in slot i
	     */
	    public ItemStack getStackInSlot(int par1)
	    {
	        return getCore().getStackInSlot(par1);
	    }

	    /**
	     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
	     * new stack.
	     */
	    public ItemStack decrStackSize(int par1, int par2)
	    {
	      return getCore().decrStackSize(par1, par2);
	    }

	    /**
	     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
	     * like when you close a workbench GUI.
	     */
	    public ItemStack getStackInSlotOnClosing(int par1)
	    {
	     return getCore().getStackInSlotOnClosing(par1);
	    }

	    public int getRandomStackFromInventory()
	    {
	      return getCore().getRandomStackFromInventory();
	    }

	    /**
	     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	     */
	    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	    {
	        getCore().setInventorySlotContents(par1, par2ItemStack);
	        this.onInventoryChanged();
	    }

	    /**
	     * Add item stack in first available inventory slot
	     */
	    public int addItem(ItemStack par1ItemStack)
	    {	
	    	return getCore().addItem(par1ItemStack);

	    }

	    /**
	     * Returns the name of the inventory.
	     */
	    public String getInvName()
	    {
	        return getCore().getInvName();
	    }

	    public void setCustomName(String par1Str)
	    {
	        getCore().setCustomName(par1Str);
	    }

	    /**
	     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
	     * language. Otherwise it will be used directly.
	     */
	    public boolean isInvNameLocalized()
	    {
	        return getCore().customName != null;
	    }

	    /**
	     * Reads a tile entity from NBT.
	     */
	    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	    {
	        super.readFromNBT(par1NBTTagCompound);
			
			coreX = par1NBTTagCompound.getInteger("CoreX");
			coreY = par1NBTTagCompound.getInteger("CoreY");
			coreZ = par1NBTTagCompound.getInteger("CoreZ");
	    }

	    /**
	     * Writes a tile entity to NBT.
	     */
	    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	    {
	        super.writeToNBT(par1NBTTagCompound);
			
	        par1NBTTagCompound.setInteger("CoreX", coreX);
	        par1NBTTagCompound.setInteger("CoreY", coreY);
	        par1NBTTagCompound.setInteger("CoreZ", coreZ);
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
	        return this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64.0D;
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

		@Override
		public boolean isItemValidForSlot(int i, ItemStack itemstack) {
			if(i==0)
				return true;
			return false;
		}




}