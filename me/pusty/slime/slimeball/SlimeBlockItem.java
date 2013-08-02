package me.pusty.slime.slimeball;
import java.util.List;

import me.pusty.slime.PustySlime;
import me.pusty.slime.SlimeBallTypes;
import me.pusty.slime.SlimeTypes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import cpw.mods.fml.relauncher.*;

public class SlimeBlockItem extends ItemBlock
{
	String type = "null";
public SlimeBlockItem(int par1)
{
         super(par1);
         this.setMaxDamage(0);
         this.setHasSubtypes(true); 
}
@Override
@SideOnly(Side.CLIENT)
public Icon getIconFromDamage(int par1) //Gets the texture
{
         return PustySlime.slimeBlock.getBlockTextureFromSide(par1);
}
public int getMetadata(int par1) //Returns the metadata value
{
         return par1;
}

@Override
public String getItemDisplayName(ItemStack item)
{
	type = SlimeBallTypes.list.get(item.getItemDamage()).typename;
 return type+"-"+"Slime"; 
}




@Override
public void addInformation(ItemStack item, EntityPlayer player, List l, boolean B){ //Additional info (eg. the names of music discs)
l.add("SlimeType: "+type);

}
}