package me.pusty.slime.generator;

import java.util.Random;

import me.pusty.slime.SlimeTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
 
public class PustyGeneratorTileEntity  extends TileEntity{


	public ItemStack[] in = new ItemStack[2];
	{
		in[0]=null;
		in[1]=null;
	}

	
	public PustyGeneratorTileEntity(){
	}
	

	@Override
	public void writeToNBT(NBTTagCompound par1)
	{
	   super.writeToNBT(par1);
       NBTTagList nbttaglist = new NBTTagList();

       
       for (int i = 0; i < in.length; ++i)
       {
           if (this.in[i] != null)
           {
               NBTTagCompound nbttagcompound1 = new NBTTagCompound();
               nbttagcompound1.setByte("Slot", (byte)i);
               this.in[i].writeToNBT(nbttagcompound1);
               nbttaglist.appendTag(nbttagcompound1);
           }
       }
 par1.setTag("Items", nbttaglist);

	}

	@Override
	public void readFromNBT(NBTTagCompound par1)
	{
	   super.readFromNBT(par1);
       NBTTagList nbttaglist = par1.getTagList("Items");

       for (int i = 0; i < nbttaglist.tagCount(); ++i)
       {
           NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
           int j = nbttagcompound1.getByte("Slot") & 255;

           if (j >= 0 && j < this.in.length)
           {
               this.in[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);

               
           }
       }
       
       this.setItem(in[0]);

	}
 
public ItemStack getItem(){
	return in[0];
}
public void setItem(ItemStack item){
	in[0] = item;
}







}