package me.pusty.slime.slimeball;

import java.util.List;
import java.util.Random;

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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;

public class SlimeMutatorItem extends Item{



    
    @SideOnly(Side.CLIENT)
    private Icon[] icons;


	public SlimeMutatorItem(int par1) {
		super(par1);
        this.setHasSubtypes(true);
		this.setMaxStackSize(1);
        setUnlocalizedName("slimeMutator");
		setCreativeTab(PustySlime.pustyTab);
		
		
	}
	
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1)
    {
        int j = MathHelper.clamp_int(par1, 0, 6);
        return this.icons[j];
    }
    
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {

        this.icons = new Icon[7];

        this.icons[0] = par1IconRegister.registerIcon("pustyslime:slimemutator_empty");
        this.icons[1] = par1IconRegister.registerIcon("pustyslime:slimemutator_white");
        this.icons[2] = par1IconRegister.registerIcon("pustyslime:slimemutator_red");
        this.icons[3] = par1IconRegister.registerIcon("pustyslime:slimemutator_blue");
        this.icons[4] = par1IconRegister.registerIcon("pustyslime:slimemutator_green");
        this.icons[5] = par1IconRegister.registerIcon("pustyslime:slimemutator_stone");
        this.icons[6] = par1IconRegister.registerIcon("pustyslime:slimemutator_main");
        
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
          list.add(setType(new ItemStack(itemID, 1, 0),"Empty"));
          list.add(setTicks(setType(new ItemStack(itemID, 1, 1),"White"),1250));
          list.add(setTicks(setType(new ItemStack(itemID, 1, 2),"Red"),1500));
          list.add(setTicks(setType(new ItemStack(itemID, 1, 3),"Blue"),1500));
          list.add(setTicks(setType(new ItemStack(itemID, 1, 4),"Green"),1500));
          list.add(setTicks(setType(new ItemStack(itemID, 1, 5),"Stone"),5000));
          list.add(setTicks(setType(new ItemStack(itemID, 1, 6),"Main"),50000));
     }
    
	
    @Override
    public String getItemDisplayName(ItemStack item)
    {
     return getType(item)+"-"+"Mutator"; 
    }
    
    
    public static String getType(ItemStack item){
    	NBTTagCompound nbttagcompound = item.getTagCompound();

    	if (nbttagcompound == null)
    	{
    	    nbttagcompound = new NBTTagCompound();
	    	nbttagcompound.setString("pustymutatortype", "Empty");
    	    item.setTagCompound(nbttagcompound);
    	    return "Empty";
    	    
    	}
    	else
    	{
    	    if(!nbttagcompound.hasKey("pustymutatortype")){
    	    	nbttagcompound.setString("pustymutatortype", "Empty");
        	    return "Empty";
    	    }
    	    return nbttagcompound.getString("pustymutatortype");

    	}
    }
    
    
    public static int getNeededTicks(ItemStack item){
    	NBTTagCompound nbttagcompound = item.getTagCompound();

    	if (nbttagcompound == null)
    	{
    	    nbttagcompound = new NBTTagCompound();
	    	nbttagcompound.setInteger("pustymutatorticks", 100);
    	    item.setTagCompound(nbttagcompound);
    	    return 100;
    	    
    	}
    	else
    	{
    	    if(!nbttagcompound.hasKey("pustymutatorticks")){
    	    	nbttagcompound.setInteger("pustymutatorticks", 100);
        	    return 100;
    	    }
    	    return nbttagcompound.getInteger("pustymutatorticks");

    	}
    }
    
    public static ItemStack setType(ItemStack item,String string){
    	NBTTagCompound nbttagcompound = item.getTagCompound();

    	if (nbttagcompound == null)
    	{
    	    nbttagcompound = new NBTTagCompound();
	    	nbttagcompound.setString("pustymutatortype", string);
    	    item.setTagCompound(nbttagcompound);
    	    return item;
    	}
    	else
    	{
    	    	nbttagcompound.setString("pustymutatortype", string);
        	    item.setTagCompound(nbttagcompound);
        	    return item;
    	}
    }
    
    public static ItemStack setTicks(ItemStack item,int ticks){
    	NBTTagCompound nbttagcompound = item.getTagCompound();

    	if (nbttagcompound == null)
    	{
    	    nbttagcompound = new NBTTagCompound();
	    	nbttagcompound.setInteger("pustymutatorticks", ticks);
    	    item.setTagCompound(nbttagcompound);
    	    return item;
    	}
    	else
    	{
    	    	nbttagcompound.setInteger("pustymutatorticks", ticks);
        	    item.setTagCompound(nbttagcompound);
        	    return item;
    	}
    }
    



@Override
public void addInformation(ItemStack item, EntityPlayer player, List l, boolean B){ //Additional info (eg. the names of music discs)
l.add("MutationType: "+getType(item));
l.add("Needed Ticks: "+getNeededTicks(item));
}

public EnumRarity getRarity(ItemStack item){
if(getType(item).equalsIgnoreCase("stone")){
return EnumRarity.uncommon;
}else
return EnumRarity.common;
}

public boolean hasEffect(ItemStack item){
if(getType(item).equalsIgnoreCase("stone")){
return true;
}else
return false;
}


	

}
