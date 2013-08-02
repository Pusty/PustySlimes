package me.pusty.slime;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.StringTranslate;

public class MainItem extends Item{

	public MainItem(int par1) {
		super(par1);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
	//	this.setMaxStackSize(16);
		setCreativeTab(PustySlime.pustyTab);
	}
	

	
	

    



    
    @Override
    public String getItemDisplayName(ItemStack par1ItemStack)
    {
     return "Item"; 
    }
    


/*   
@Override
public void addInformation(ItemStack par1ItemStack, EntityPlayer player, List l, boolean B){ //Additional info (eg. the names of music discs)
l.add("");
}*/

public EnumRarity getRarity(ItemStack par1ItemStack){ //The colour of the item name (eg. with golden apples)
return EnumRarity.common;
}


	

}
