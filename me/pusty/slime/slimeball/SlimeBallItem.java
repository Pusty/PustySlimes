package me.pusty.slime.slimeball;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import me.pusty.slime.PustySlime;
import me.pusty.slime.SlimeBallTypes;
import me.pusty.slime.SlimeTypes;
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
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;

public class SlimeBallItem extends Item{



    
    @SideOnly(Side.CLIENT)
    private Icon[] icons;
	String type = "Normal";


	public SlimeBallItem(int par1) {
		super(par1);
        this.setHasSubtypes(true);
		this.setMaxStackSize(64);
        setUnlocalizedName("slimeBall");
		setCreativeTab(PustySlime.pustyTab);
		
		
	}
	
    @SideOnly(Side.CLIENT)

    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int par1)
    {
        int j = MathHelper.clamp_int(par1, 1, SlimeBallTypes.list.size());
        return this.icons[j];
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.icons = new Icon[SlimeBallTypes.list.size()];

        for (int i = 0; i < SlimeBallTypes.list.size(); ++i)
        {
            this.icons[i] = par1IconRegister.registerIcon("pustyslime:slimeball"+ "_" + SlimeBallTypes.list.get(i).texturename);
        }
    }

	
  /*  @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
    	
        this.itemIcon = iconRegister.registerIcon("pustyslime:" + "slimeball");
 
        icon  = iconRegister.registerIcon("pustyslime:" + "slimeball");
        
    }
    */
  
    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {  
    //par1ItemStack.addEnchantment(PustySlime.ench, 1);
    
    }



    @SideOnly(Side.CLIENT)
    public void getSubItems(int itemID, CreativeTabs tabs, List list){
            // You can also take a more direct approach and do each one individual but I prefer the lazy / right way
            for(int i = 1; i < SlimeBallTypes.list.size(); ++i){
            	  ItemStack s = new ItemStack(itemID, 1, i);
           // 	   s.addEnchantment(PustySlime.ench, 1);
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

    	type = SlimeBallTypes.list.get(item.getItemDamage()).typename;
     return type+"-"+"SlimeBall"; 
    }
    



@Override
public void addInformation(ItemStack item, EntityPlayer player, List l, boolean B){ //Additional info (eg. the names of music discs)
l.add("SlimeBallType: "+type);

}

public EnumRarity getRarity(ItemStack item){ //The colour of the item name (eg. with golden apples)

	type = SlimeBallTypes.list.get(item.getItemDamage()).typename;
return EnumRarity.common;
}


	

}
