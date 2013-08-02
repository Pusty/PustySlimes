package me.pusty.slime.potion;

import java.util.List;
import java.util.Random;

import me.pusty.slime.PustySlime;
import me.pusty.slime.SlimeBallTypes;
import me.pusty.slime.SlimeTypes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class SlimePotion extends Item{
	String type = "unknown";
    public SlimePotion(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
	    setUnlocalizedName("slimePotion");
		setCreativeTab(PustySlime.pustyTab);
	}

    
    @Override
    public String getItemDisplayName(ItemStack item)
    {
    	type = SlimeBallTypes.list.get(item.getItemDamage()).typename;
     return type+"-"+"Potion"; 
    }
    



    
@Override
public void addInformation(ItemStack item, EntityPlayer player, List l, boolean B){ //Additional info (eg. the names of music discs)


NBTTagCompound nbttagcompound = item.getTagCompound();

if (nbttagcompound == null)
{
	l.add("Nope.");
    nbttagcompound = new NBTTagCompound();
    item.setTagCompound(nbttagcompound);
}
else
{
    if(!nbttagcompound.hasKey("pusty")){
    	nbttagcompound.setString("pusty", SlimeTypes.list.get(new Random().nextInt(SlimeTypes.list.size()-1)).typename);
    }
    l.add(nbttagcompound.getString("pusty"));
}

}


	public boolean getShareTag()
    {
        return true;
    }
}
