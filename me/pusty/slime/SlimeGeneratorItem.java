package me.pusty.slime;
import java.util.List;

import me.pusty.slime.PustySlime;
import me.pusty.slime.SlimeBallTypes;
import me.pusty.slime.SlimeTypes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;

import cpw.mods.fml.relauncher.*;

public class SlimeGeneratorItem extends ItemBlock
{
public SlimeGeneratorItem(int par1)
{
         super(par1);
         this.setMaxDamage(0);
         this.setHasSubtypes(true); 
}
@Override
@SideOnly(Side.CLIENT)
public Icon getIconFromDamage(int par1) //Gets the texture
{
         return PustySlime.generatorBlock.getBlockTextureFromSide(par1);
         
}
public int getMetadata(int par1) //Returns the metadata value
{
         return par1;
}

@Override
public String getItemDisplayName(ItemStack item)
{
	 return EnumChatFormatting.BLUE+"SlimeGenerator"; 
}




@Override
public void addInformation(ItemStack item, EntityPlayer player, List l, boolean B){
l.add(EnumChatFormatting.GRAY+"This Block is used");
l.add(EnumChatFormatting.GRAY+"for generating SlimeBalls");
l.add(EnumChatFormatting.GRAY+"using Slimes");
}

public EnumRarity getRarity(ItemStack item){
return EnumRarity.uncommon;
}
	


}