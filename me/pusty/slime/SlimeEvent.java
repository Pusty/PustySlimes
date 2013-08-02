package me.pusty.slime;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;




public class SlimeEvent
{

	
@ForgeSubscribe
public void onJoinWorld( EntityJoinWorldEvent event)
{
if(event.entity instanceof EntityPlayer){
	EntityPlayer p = (EntityPlayer)event.entity;
	

	NBTTagCompound tag = p.getEntityData();
	NBTBase modeTag = tag.getTag("slimefirstjoin");
	if (modeTag == null || !tag.getBoolean("slimefirstjoin")) {
		p.inventory.addItemStackToInventory(new ItemStack(PustySlime.slimebook,1));
	}

	tag.setBoolean("slimefirstjoin", true);
	

}


}


}
