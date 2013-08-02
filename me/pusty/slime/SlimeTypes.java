package me.pusty.slime;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.ItemStack;

public class SlimeTypes {
   public static HashMap<Integer,SlimeTypes> list = new HashMap<Integer,SlimeTypes>();
   public int id = 0;
   public Color bcolor;
   public Color rcolor;
   public String typename = "Normal";
   public SlimeBallTypes texturename = null;
   public ItemStack output = null;
   public int effect = 0;
   public String fromName = "Main";
   public String evolteName = "none";
   public SlimeTypes(int id,String name,ItemStack output,Color b,Color r,SlimeBallTypes t)
   {
	this.id = id;
	this.typename = name;
	this.texturename = t;
	this.output = output;
	this.bcolor = b;
	this.rcolor = r;

   }
   
   public SlimeTypes getFrom(String from,String add){
	   fromName = from;
	   evolteName = add;
	   return this;
   }
   
   public SlimeTypes setEffect(int i){
	   effect = i;
	   return this;
   }

   public void addToList(){
		list.put(id,this);
   }
   
   
}
