package me.pusty.slime;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class KnowledgeSet {
	public static List<KnowledgeSet> 	  list = new ArrayList<KnowledgeSet>();
	public String set1 = null;
	public String set2 = null;
	public String set3 = null;
	public String set4 = null;
	public String set5 = null;
	public String set6 = null;
	public ItemStack item=null;
   public KnowledgeSet(String set1,String set2,String set3,String set4,String set5,String set6,ItemStack item){

	   this.set1 = set1;
	   this.set2 = set2;
	   this.set3 = set3;
	   this.set4 = set4;
	   this.set5 = set5;
	   this.set6 = set6;
	   this.item = item;
	   
	   addToList();
   }
   
   public void addToList(){
	   list.add(this);
   }
}
