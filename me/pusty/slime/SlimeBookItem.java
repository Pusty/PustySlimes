package me.pusty.slime;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;

public class SlimeBookItem extends Item{


	public SlimeBookItem(int par1) {
		super(par1);
        this.setHasSubtypes(true);
		this.setMaxStackSize(1);
        setUnlocalizedName("slimebook");
		setCreativeTab(PustySlime.pustyTab);
		
		
	}
	

	
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
    	
        this.itemIcon = iconRegister.registerIcon("pustyslime:" + "slimebook");

        
    }
    
  
    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {  
    //par1ItemStack.addEnchantment(PustySlime.ench, 1);
    
    }


    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
    	if(!world.isRemote){
    		
    	}else{
    player.openGui(PustySlime.instance, 4, world, 0, 0, 0);
    	}
    	return itemstack;
    }
    

    public static int getPage(ItemStack item){

    	NBTTagCompound nbttagcompound = item.getTagCompound();

    	if (nbttagcompound == null)
    	{
    	    nbttagcompound = new NBTTagCompound();
	    	nbttagcompound.setInteger("pustyside", 0);
    	    item.setTagCompound(nbttagcompound);
    	    return 0;
    	    
    	}
    	else
    	{
    	    if(!nbttagcompound.hasKey("pustyside")){
    	    	nbttagcompound.setInteger("pustyside", 0);
        	    return 0;
    	    }
    	    return nbttagcompound.getInteger("pustyside");

    	}
    }
    
    
    
    public static ItemStack setPage(ItemStack item,int side){

    	NBTTagCompound nbttagcompound = item.getTagCompound();
    	if (nbttagcompound == null)
    	{
    	    nbttagcompound = new NBTTagCompound();
	    	nbttagcompound.setInteger("pustyside", side);
    	    item.setTagCompound(nbttagcompound);
    	    return item;
    	}
    	else
    	{
    	    	nbttagcompound.setInteger("pustyside", side);
        	    item.setTagCompound(nbttagcompound);
        	    return item;
    	}
    }
    
    
    
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
    @Override
    public String getItemDisplayName(ItemStack item)
    {
       	 return "Slime-Book";

    }
    



@Override
public void addInformation(ItemStack item, EntityPlayer player, List l, boolean B){ //Additional info (eg. the names of music discs)
l.add("Slime Infomation Book");

}

public EnumRarity getRarity(ItemStack item){ //The colour of the item name (eg. with golden apples)
return EnumRarity.common;
}


	

}
