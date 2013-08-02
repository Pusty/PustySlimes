package me.pusty.slime.object;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import me.pusty.slime.PustySlime;
import me.pusty.slime.SlimeItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;

public class SlotObject extends Slot
{
    /** The player that is using the GUI where this slot resides. */
	private TileEntityObject mutator;
    private int field_75228_b;

    public SlotObject(TileEntityObject par2IInventory, int par3, int par4, int par5)
    {
        super(par2IInventory, par3, par4, par5);
        mutator = par2IInventory;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return true;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    public ItemStack decrStackSize(int par1)
    {
        if (this.getHasStack())
        {
            this.field_75228_b += Math.min(par1, this.getStack().stackSize);
        }

       
        return super.decrStackSize(par1);
    }
    
    @Override
    public void onSlotChanged(){
/*
        if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.SERVER)){
            mutator.shownItem = mutator.inventory[0];
            mutator.getWorldObj().setBlockMetadataWithNotify(mutator.xCoord,mutator.yCoord,mutator.zCoord,mutator.shownItem==null?0:mutator.shownItem.getItem() instanceof SlimeItem?mutator.shownItem.getItemDamage():0,0);
            mutator.worldObj.markBlockForUpdate(mutator.xCoord,mutator.yCoord,mutator.zCoord);
        }*/
    }

    public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
        super.onPickupFromSlot(par1EntityPlayer, par2ItemStack);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    protected void onCrafting(ItemStack par1ItemStack, int par2)
    {
        this.field_75228_b += par2;
        this.onCrafting(par1ItemStack);
    }


}
