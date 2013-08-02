package me.pusty.slime;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class SlimeEnchantment extends Enchantment {

	protected SlimeEnchantment(int par1, int par2,
			EnumEnchantmentType par3EnumEnchantmentType) {

		super(par1, par2, par3EnumEnchantmentType);
        this.setName("Slimness");
	}
	
	@Override
	public String getName(){
		return "Slimness";
	}
	@Override	
    public int getMaxLevel()
    {
        return 10;
    }
	@Override  
    public boolean canApply(ItemStack par1ItemStack)
    {
        return false;
    }

	@Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return false;
    }

}
