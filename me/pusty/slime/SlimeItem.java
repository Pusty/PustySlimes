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
import net.minecraft.util.Icon;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;

public class SlimeItem extends Item{


	String type = "Normal";
	ItemStack output = null;
	public static Icon icon = null;
	public static Icon icon2 = null;
	public static  Icon icon3 = null;
	public SlimeItem(int par1) {
		super(par1);
        this.setHasSubtypes(true);
		this.setMaxStackSize(1);
        setUnlocalizedName("slime1");
		setCreativeTab(PustySlime.pustyTab);
		
		
	}
	

	
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
    	
        this.itemIcon = iconRegister.registerIcon("pustyslime:" + "slime1");
 
        icon  = iconRegister.registerIcon("pustyslime:" + "slime1");
        icon2  = iconRegister.registerIcon("pustyslime:" + "slime2");
        icon3 = iconRegister.registerIcon("pustyslime:" + "slime3");
        
    }
    
  
    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {  
    par1ItemStack.addEnchantment(PustySlime.ench, 1);
    
    }



    @SideOnly(Side.CLIENT)
    public void getSubItems(int itemID, CreativeTabs tabs, List list){
            // You can also take a more direct approach and do each one individual but I prefer the lazy / right way
            for(int i = 1; i < SlimeTypes.list.size(); ++i){
            	if(i==16){
            		continue;
            	}
            	  ItemStack s = new ItemStack(itemID, 1, i);
            	   s.addEnchantment(PustySlime.ench, 1);
                    list.add(s);
                 

             }
     }
    

	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
    @Override
    public String getItemDisplayName(ItemStack item)
    {
        if(item.getItemDamage()==16)
       	 return "Slime";
        
    	output = SlimeTypes.list.get(item.getItemDamage()).output;
    	type = SlimeTypes.list.get(item.getItemDamage()).typename;
     return type+"-"+"Slime"; 
    }
    



@Override
public void addInformation(ItemStack item, EntityPlayer player, List l, boolean B){ //Additional info (eg. the names of music discs)
l.add("SlimeType: "+type);

}

public EnumRarity getRarity(ItemStack item){ //The colour of the item name (eg. with golden apples)
	output = SlimeTypes.list.get(item.getItemDamage()).output;
	type = SlimeTypes.list.get(item.getItemDamage()).typename;
return EnumRarity.common;
}


	

}
