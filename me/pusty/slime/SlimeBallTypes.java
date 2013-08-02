package me.pusty.slime;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.ItemStack;

public class SlimeBallTypes {
   public static HashMap<Integer,SlimeBallTypes> list = new HashMap<Integer,SlimeBallTypes>();
   public int id = 0;

   public String typename = "Normal";
   public String texturename = "normal";
   public String particelname = "slime";

   public SlimeBallTypes(int id,String name,String texture)
   {
	this.id = id;
	this.typename = name;
	this.texturename = texture.toLowerCase();


   }
   
   public SlimeBallTypes setParticel(String n){
	   particelname = n;
	   return this;
   }
   
   public SlimeBallTypes setTextureName(String s){
	this.texturename = s.toLowerCase();
	return this;
   }
   
   public void addToList(){
		list.put(id,this);
   }
   
   
}