package me.pusty.slime;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PustyTab extends CreativeTabs {
	public PustyTab(String label) {
	    super(label);

	}
	
    @SideOnly(Side.CLIENT)

    /**
     * the itemID for the item to be displayed on the tab
     */
    public ItemStack getIconItemStack()
    {
    	ItemStack s = new ItemStack(PustySlime.slimeItem,1,4);
    	s.addEnchantment(PustySlime.ench, 1);
        return s;
    }
    
    @Override
    public String getTabLabel(){
    	return "PustySlimes";
    }
    
}
