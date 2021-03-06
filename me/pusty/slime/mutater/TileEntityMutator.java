package me.pusty.slime.mutater;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;

import me.pusty.slime.PustySlime;
import me.pusty.slime.SlimeItem;
import me.pusty.slime.SlimeTypes;
import me.pusty.slime.slimeball.SlimeMutatorItem;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
 
public class TileEntityMutator  extends TileEntity  implements IInventory{

	public TileEntityMutator(){

	}

	   public ItemStack[] inventory = new ItemStack[2];
	   public ItemStack shownItem = null;


		int at=0;
		int mt=0;
		

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
	        return this.inventory.length;
	    }

	    /**
	     * Returns the stack in slot i
	     */
	    
	    
	    public int getMaxTicks(){
	    	return mt;
	    }
	    
	    public int getCurrentTicks(){
	    	return at;
	    }
		@Override
		public void updateEntity()
		{
	         
		//	shownItem = inventory[0];
		   // getWorldObj().setBlockMetadataWithNotify(xCoord,yCoord,zCoord,shownItem==null?0:shownItem.getItem() instanceof SlimeItem?shownItem.getItemDamage():0,0);
	          

			if(inventory[1] != null && inventory[1].getItem() instanceof SlimeMutatorItem && mt != SlimeMutatorItem.getNeededTicks(inventory[1])){
				mt = SlimeMutatorItem.getNeededTicks(inventory[1]);
			}else if(inventory[1] == null || !(inventory[1].getItem() instanceof SlimeMutatorItem)){
				mt = 0;
			}
			
			if(canMutate()){
			if(at>= getMaxTicks()){
				mutateItem();
			at=0;
			
			}else
				at++;
			}else
				at = 0;
			

			if(inventory[1] != null && inventory[1].getItem() instanceof SlimeMutatorItem && mt != SlimeMutatorItem.getNeededTicks(inventory[1])){
				mt = SlimeMutatorItem.getNeededTicks(inventory[1]);
			}else if(inventory[1] == null || !(inventory[1].getItem() instanceof SlimeMutatorItem)){
				mt = 0;
			}

		}
	    
		
		private boolean canMutate()
		{
			if(inventory[0] == null || inventory[1] == null)
			{
				return false;
			}
			else if(!(inventory[0].getItem() instanceof SlimeItem) || !(inventory[1].getItem() instanceof SlimeMutatorItem)){
				return false;
			}
			else
			{
				ItemStack itemStack = mutate();
				if(itemStack == null){
					return true;
				}
				return true;
			}
		}
		
		public void mutateItem()
		{
			if(canMutate())
			{
				ItemStack itemStack = mutate();
				
					inventory[0] = itemStack==null?null:itemStack.copy();
				
				inventory[1].stackSize--;
				
				if(inventory[0] != null && inventory[0].stackSize <= 0)
					inventory[0] = null;
				
				if(inventory[1].stackSize <= 0)
					inventory[1] = null;
				
				onInventoryChanged();
			}
		}
	    
	    
	    public ItemStack getStackInSlot(int par1)
	    {
	    	return this.inventory[par1];
	    }

	    /**
	     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
	     * new stack.
	     */
	    public ItemStack decrStackSize(int par1, int par2)
	    {
	        if (this.inventory[par1] != null)
	        {
	            ItemStack itemstack;

	            if (this.inventory[par1].stackSize <= par2)
	            {
	                itemstack = this.inventory[par1];
	                this.inventory[par1] = null;
	                this.onInventoryChanged();
	                return itemstack;
	            }
	            else
	            {
	                itemstack = this.inventory[par1].splitStack(par2);

	                if (this.inventory[par1].stackSize == 0)
	                {
	                    this.inventory[par1] = null;
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
	        if (this.inventory[par1] != null)
	        {
	            ItemStack itemstack = this.inventory[par1];
	            this.inventory[par1] = null;
	            return itemstack;
	        }
	        else
	        {
	            return null;
	        }
	    }


	    
	    /**
	     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	     */
	    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	    {
	        this.inventory[par1] = par2ItemStack;

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
	        for (int i = 0; i < this.inventory.length; ++i)
	        {
	            if (this.inventory[i] == null || this.inventory[i].itemID == 0)
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
	        return this.isInvNameLocalized() ? this.customName : "SlimeMutator";
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
	        at = par1NBTTagCompound.getInteger("allreadyTicked");
	        mt = par1NBTTagCompound.getInteger("maximumTicked");
	        
           
	        
	        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
	        this.inventory = new ItemStack[this.getSizeInventory()];

	        for (int i = 0; i < nbttaglist.tagCount(); ++i)
	        {
	            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
	            int j = nbttagcompound1.getByte("Slot") & 255;

	            if (j >= 0 && j < this.inventory.length)
	            {
	                this.inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
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
	        par1NBTTagCompound.setInteger("allreadyTicked", at);
	        par1NBTTagCompound.setInteger("maximumTicked", mt);
	        NBTTagList nbttaglist = new NBTTagList();

	        for (int i = 0; i < this.inventory.length; ++i)
	        {
	            if (this.inventory[i] != null)
	            {
	                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	                nbttagcompound1.setByte("Slot", (byte)i);
	                this.inventory[i].writeToNBT(nbttagcompound1);
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


		@Override
		public boolean isItemValidForSlot(int i, ItemStack itemstack) {
			if(i==0 && itemstack != null && itemstack.getItem() instanceof SlimeItem)
				return true;
			
			if(i==1 && itemstack != null && itemstack.getItem() instanceof SlimeMutatorItem)
				return true;

			return false;
		}
		
		@Override
		public void onInventoryChanged(){
	        if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.SERVER)){
	            shownItem = inventory[0];
	            getWorldObj().setBlockMetadataWithNotify(xCoord,yCoord,zCoord,shownItem==null?0:shownItem.getItem() instanceof SlimeItem?shownItem.getItemDamage():0,0);
	            worldObj.markBlockForUpdate(xCoord,yCoord,zCoord);
	        }
		}
		
		
		public ItemStack mutate(){
			

			if(inventory[0] != null && inventory[1] != null && inventory[0].getItem() instanceof SlimeItem && inventory[1].getItem() instanceof SlimeMutatorItem){

				String s = SlimeTypes.list.get(inventory[0].getItemDamage()).typename;
				int slimeness = EnchantmentHelper.getEnchantmentLevel(52, inventory[0]);
				
				for(SlimeTypes sl:SlimeTypes.list.values()){
					if(sl.fromName.equals(s))
						if(sl.evolteName.equalsIgnoreCase(SlimeMutatorItem.getType(inventory[1]))){
							ItemStack in = inventory[0].copy();
							in.setItemDamage(sl.id);
	
							return in;}
					}
				}
				
			/*	
				if(s.equals("Main")){
				for(int i:lavaids){
					if(i==in[1].itemID){in[0].setItemDamage(1);return in[0];}//Lava
				}
				for(int i:waterids){
					if(i==in[1].itemID){in[0].setItemDamage(3);return in[0];}//Water
				}
				for(int i:plantids){
					if(i==in[1].itemID){in[0].setItemDamage(2);return in[0];}//Plant
				}
				for(int i:randomids){
					if(i==in[1].itemID){in[0].setItemDamage(5);return in[0];}//Random
				}
				}else if(s.equals("Red")){
					for(int i:plantids){
						if(i==in[1].itemID){in[0].setItemDamage(7);return in[0];}//Plant
					}
				}else if(s.equals("Blue")){
					for(int i:lavaids){
						if(i==in[1].itemID){in[0].setItemDamage(6);return in[0];}//Lava
					}	
				}else if(s.equals("Green")){
					for(int i:lavaids){
						if(i==in[1].itemID){in[0].setItemDamage(9);return in[0];}//Lava
					}	
				}else if(s.equals("White")){
					for(int i:randomids){
						if(i==in[1].itemID){in[0].setItemDamage(8);return in[0];}//Random
					}
				}*/
			
			
			return null;
		}
		
		




}