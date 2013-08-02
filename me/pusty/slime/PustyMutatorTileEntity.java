package me.pusty.slime;

import java.util.Random;

import me.pusty.slime.slimeball.SlimeMutatorItem;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
 
public class PustyMutatorTileEntity  extends TileEntity{


	public ItemStack[] in = new ItemStack[2];
	{
		in[0]=null;
		in[1]=null;
	}
	Random r = new Random();
	
	public static int[] lavaids = {378,377,87,49,327};
	public static int[] waterids = {12,19,78,79,80,82,326,337,349,350,346,373};
	public static int[] plantids = {2,6,17,18,31,32,81,106,295,296,338,86,360};
	public static int[] randomids = {25,47,48,84,264,345,347};
	public PustyMutatorTileEntity(){
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

public ItemStack getMItem(){
	return in[1];
}
public void setMItem(ItemStack item){
	in[1] = item;

}

public ItemStack mutate(){
	
	if(in[0] != null && in[1] != null){
		String s = SlimeTypes.list.get(in[0].getItemDamage()).typename;
		int slimeness = EnchantmentHelper.getEnchantmentLevel(52, in[0]);
		
		
		for(SlimeTypes sl:SlimeTypes.list.values()){
			if(sl.fromName.equals(s)){
				
				if(sl.evolteName.equalsIgnoreCase(SlimeMutatorItem.getType(in[1]))){
					in[0].setItemDamage(sl.id);return in[0];}
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
	}else if(in[0] != null)
		return in[0];
	
	return null;
}



}