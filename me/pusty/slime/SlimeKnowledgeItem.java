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
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;

public class SlimeKnowledgeItem extends Item{


	public SlimeKnowledgeItem(int par1) {
		super(par1);
        this.setHasSubtypes(true);
		this.setMaxStackSize(1);
        setUnlocalizedName("slimeknowledgebook");
		setCreativeTab(PustySlime.pustyTab);
		
		
	}
	

	
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
    	
        this.itemIcon = iconRegister.registerIcon("pustyslime:" + "knowledgebook");

        
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
    player.openGui(PustySlime.instance, 5, world, 0, 0, 0);
    	}
    	return itemstack;
    }
    

    public static boolean getPage(ItemStack item,int i){

    	NBTTagCompound nbttagcompound = item.getTagCompound();

    	if (nbttagcompound == null)
    	{
    	    nbttagcompound = new NBTTagCompound();
	    	nbttagcompound.setBoolean("pustyside"+i, false);
    	    item.setTagCompound(nbttagcompound);
    	    return false;
    	    
    	}
    	else
    	{
    	    if(!nbttagcompound.hasKey("pustyside"+i)){
    	    	nbttagcompound.setBoolean("pustyside"+i, false);
        	    return false;
    	    }

    	    return nbttagcompound.getBoolean("pustyside"+i);

    	}
    }
    
    
    public static String getShownTag(String type){
    	if(type.equalsIgnoreCase("null"))
    	return EnumChatFormatting.WHITE+"";
    	else if(type.equalsIgnoreCase("water"))
        return EnumChatFormatting.BLUE+"Water";
    	else if(type.equalsIgnoreCase("air"))
        return EnumChatFormatting.YELLOW+"Air";
    	else if(type.equalsIgnoreCase("bedrock"))
        return EnumChatFormatting.GRAY+"Bedrock";
    	else if(type.equalsIgnoreCase("fire"))
        return EnumChatFormatting.RED+"Fire";
    	else if(type.equalsIgnoreCase("Rare"))
        return EnumChatFormatting.DARK_PURPLE+"Mysterious";
    	else if(type.equalsIgnoreCase("Plant"))
        return EnumChatFormatting.GREEN+"Plant";
    	else if(type.equalsIgnoreCase("Normal"))
            return EnumChatFormatting.DARK_GRAY+"Normal";
    	return EnumChatFormatting.WHITE+"";  	
    }
    
    public static String getPageString(ItemStack item,int i){

    	NBTTagCompound nbttagcompound = item.getTagCompound();

    	if (nbttagcompound == null)
    	{
    	    nbttagcompound = new NBTTagCompound();
	    	nbttagcompound.setString("pustystring"+i, "null");
    	    item.setTagCompound(nbttagcompound);
    	    return "null";
    	    
    	}
    	else
    	{
    	    if(!nbttagcompound.hasKey("pustystring"+i)){
    	    	nbttagcompound.setString("pustystring"+i, "null");
        	    return "null";
    	    }

    	    return nbttagcompound.getString("pustystring"+i);

    	}
    }
    
    
    
    public static ItemStack setPage(ItemStack item,int side,boolean the){

    	NBTTagCompound nbttagcompound = item.getTagCompound();
    	if (nbttagcompound == null)
    	{
    	    nbttagcompound = new NBTTagCompound();
	    	nbttagcompound.setBoolean("pustyside"+side, the);
    	    item.setTagCompound(nbttagcompound);
    	    return item;
    	}
    	else
    	{
    	    	nbttagcompound.setBoolean("pustyside"+side, the);
        	    item.setTagCompound(nbttagcompound);
        	    return item;
    	}
    }
    
    public static ItemStack setPageString(ItemStack item,int side,String the){

    	NBTTagCompound nbttagcompound = item.getTagCompound();
    	if (nbttagcompound == null)
    	{
    	    nbttagcompound = new NBTTagCompound();
	    	nbttagcompound.setString("pustystring"+side, the);
    	    item.setTagCompound(nbttagcompound);
    	    return item;
    	}
    	else
    	{
    	    	nbttagcompound.setString("pustystring"+side, the);
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
       	 return "Knowledge-Book";

    }
    



@Override
public void addInformation(ItemStack item, EntityPlayer player, List l, boolean B){ //Additional info (eg. the names of music discs)
l.add("Used to make rare Slime Items");
l.add("Use it at different places!");

}

public EnumRarity getRarity(ItemStack item){ //The colour of the item name (eg. with golden apples)
return EnumRarity.rare;
}



public static KnowledgeSet getSet(String[] st) {

	for(KnowledgeSet set:KnowledgeSet.list) {
	   if(set.set1.equalsIgnoreCase(st[0]))
		   if(set.set2.equalsIgnoreCase(st[1]))
			   if(set.set3.equalsIgnoreCase(st[2]))
				   if(set.set4.equalsIgnoreCase(st[3]))
					   if(set.set5.equalsIgnoreCase(st[4]))
						   if(set.set6.equalsIgnoreCase(st[5]))
							   return set;
		   
	}
	return null;
}


	

}
