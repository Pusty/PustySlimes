package me.pusty.slime;


import java.awt.Color;

import me.pusty.slime.generator.PustyGeneratorBlock;
import me.pusty.slime.generator.PustyGeneratorTileEntity;
import me.pusty.slime.generator.TileEntityGenerator;
import me.pusty.slime.mutater.PustyMutatorBlock;
import me.pusty.slime.mutater.TileEntityMutator;
import me.pusty.slime.object.TileEntityObject;
import me.pusty.slime.packet.SlimePacketHandler;
import me.pusty.slime.potion.SlimePotion;
import me.pusty.slime.slimeball.PustySlimeBlockTileEntity;
import me.pusty.slime.slimeball.PustySlimeBlockTileEntityCore;
import me.pusty.slime.slimeball.PustySlimeFurnanceGui;
import me.pusty.slime.slimeball.SlimeBallItem;
import me.pusty.slime.slimeball.SlimeBlock;
import me.pusty.slime.slimeball.SlimeBlockItem;
import me.pusty.slime.slimeball.SlimeBlockTileEntity;
import me.pusty.slime.slimeball.SlimeBlockTileEntityCore;
import me.pusty.slime.slimeball.SlimeMutatorItem;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
			



/**
 * NOTES:
 * 
 * -Make a GUI for the new Mutator.
 * -Fix the bug which need a client update to show the slime in the Mutator.
 * -After I fix that bug i'll make a Interface to the SlimeBox and the SlimeGenerator too.
 * 
 */


		@Mod(modid="PustySlime", name="PustySlime", version="0.4")
		@NetworkMod(clientSideRequired=true, serverSideRequired=false, 
				channels={"PustySlime"}, packetHandler = SlimePacketHandler.class)
		public class PustySlime
                      {
			@Instance("PustySlime")
			public static PustySlime instance;
			
			@SidedProxy(clientSide="me.pusty.slime.ClientProxy", serverSide="me.pusty.slime.CommonProxy")
			public static CommonProxy proxy;

			public static CreativeTabs pustyTab = new PustyTab("PustyTab");
			public static Enchantment ench = new SlimeEnchantment(52,2,null);
			

  
	

			public  static final Item slimeItem = new SlimeItem(2292).setFull3D();
			
			 public  static final Block objectBlock = new me.pusty.slime.object.PustyObjectBlock(2293,TileEntityObject.class);
			 public  static final Block mutatorBlock = new PustyMutatorBlock(2294,TileEntityMutator.class);
			 public  static final Item slimeBallItem = new SlimeBallItem(2295);
			public static final Block slimeBlock = new SlimeBlock(2296);
			 public  static final Block generatorBlock = new PustyGeneratorBlock(2297,TileEntityGenerator.class);
			// public  static final Item slimePotion = new SlimePotion(2298);
			 public  static final Block tileentityBlock = new SlimeBlockTileEntity(2299,PustySlimeBlockTileEntity.class);
			 public  static final Block tileentityCoreBlock = new SlimeBlockTileEntityCore(2300,PustySlimeBlockTileEntityCore.class);
			 public  static final Item slimeMutationItem = new SlimeMutatorItem(2301);
			 public  static final Item slimebook = new SlimeBookItem(2302);
			 public  static final Item slimeknowledgebook = new SlimeKnowledgeItem(2303);
			 public  static final Item slimebook2 = new SlimeBookItem2(2304);
		
			 {
				 new KnowledgeSet("Normal","Rare","Bedrock","Bedrock","Rare","Normal",new ItemStack(slimebook,1));
				 new KnowledgeSet("Bedrock","Air","Normal","Normal","Air","Bedrock",new ItemStack(slimeItem,1,18));
				 
					ItemStack e;
				    e = SlimeMutatorItem.setTicks(SlimeMutatorItem.setType(new ItemStack(this.slimeMutationItem, 1, 1),"White"),150000);
				    new KnowledgeSet("Bedrock","Rare","Air","Air","Rare","Bedrock",e);
				    e = SlimeMutatorItem.setTicks(SlimeMutatorItem.setType(new ItemStack(this.slimeMutationItem, 1, 2),"Red"),150000);
				    new KnowledgeSet("Bedrock","Rare","Fire","Fire","Rare","Bedrock",e);
				    e = SlimeMutatorItem.setTicks(SlimeMutatorItem.setType(new ItemStack(this.slimeMutationItem, 1, 3),"Blue"),150000);
				    new KnowledgeSet("Bedrock","Rare","Water","Water","Rare","Bedrock",e);
				    e = SlimeMutatorItem.setTicks(SlimeMutatorItem.setType(new ItemStack(this.slimeMutationItem, 1, 4),"Green"),150000);
				    new KnowledgeSet("Bedrock","Rare","Plant","Plant","Rare","Bedrock",e);
				    e = SlimeMutatorItem.setTicks(SlimeMutatorItem.setType(new ItemStack(this.slimeMutationItem, 1, 6),"Main"),500000);
				    new KnowledgeSet("Bedrock","Normal","Normal","Normal","Normal","Bedrock",e);
				    
			 }

			 {
				 new SlimeBallTypes(0, "Normal", "normal").setParticel("slime").addToList();
				 new SlimeBallTypes(1, "Lava", "red").setParticel("flame").addToList();
				 new SlimeBallTypes(2, "Plant", "green").setParticel("slime").addToList();
				 new SlimeBallTypes(3, "Water", "blue").setParticel("splash").addToList();
				 new SlimeBallTypes(4, "Main", "main").setParticel("explode").addToList();
				 new SlimeBallTypes(5, "Fruty", "fruty").setParticel("crit").addToList();
				 new SlimeBallTypes(6, "Magical", "magic").setParticel("enchantmenttable").addToList();
				 
				 new SlimeBallTypes(7, "End", "end").setParticel("portal").addToList();
				 new SlimeBallTypes(8, "Nether", "nether").setParticel("dripLava").addToList();
				 
				 new SlimeBallTypes(9, "Stone", "stone").setParticel("smoke").addToList();
				 new SlimeBallTypes(10, "Iron", "iron").setParticel("smoke").addToList();
			 }
			 {
				 new SlimeTypes(0, "null", new ItemStack(slimeBallItem,1,0), new Color(0,0,0), new Color(0,0,0),SlimeBallTypes.list.get(0)).getFrom("null", "none").addToList(); // Not available
				 new SlimeTypes(1, "Red", new ItemStack(Item.magmaCream,1), new Color(60,0,0), new Color(70,0,0),SlimeBallTypes.list.get(1)).getFrom("Main", "red").addToList(); //From Main  //Add Lava
				 new SlimeTypes(2, "Green", new ItemStack(slimeBallItem,1,2), new Color(0,60,0), new Color(0,70,0),SlimeBallTypes.list.get(2)).getFrom("Main", "green").addToList(); // From Main  //Add Plant
				 new SlimeTypes(3, "Blue", new ItemStack(slimeBallItem,1,3), new Color(0,0,60), new Color(0,0,70),SlimeBallTypes.list.get(3)).getFrom("Main", "blue").addToList();  // From Main   //Add Water
				 new SlimeTypes(4, "Main", new ItemStack(slimeBallItem,1,4), new Color(0,0,0), new Color(10,10,10),SlimeBallTypes.list.get(4)).getFrom("Basic", "main").addToList(); //Found in Dungeons
				 new SlimeTypes(5, "White", new ItemStack(slimeBallItem,1,4), new Color(5,5,5), new Color(5,5,5),SlimeBallTypes.list.get(4)).getFrom("Main", "white").addToList();  //From Main  //Add Random
				 new SlimeTypes(6, "Obsidian", new ItemStack(slimeBallItem,1,3), new Color(60,0,60), new Color(70,0,70),SlimeBallTypes.list.get(3)).getFrom("Blue", "red").addToList(); //From Blue  // Add Lava
				 new SlimeTypes(7, "Yellow", new ItemStack(Item.magmaCream,1), new Color(60,60,0), new Color(70,70,0),SlimeBallTypes.list.get(1)).getFrom("Red", "green").addToList();  //From Red // Add Plant
				 new SlimeTypes(8, "Boxy", new ItemStack(slimeBallItem,1,4), new Color(60,0,60), new Color(70,10,70),SlimeBallTypes.list.get(4)).getFrom("White", "white").addToList(); // From White //Add Random
				 new SlimeTypes(9, "Grapefruit", new ItemStack(slimeBallItem,1,5), Color.RED, Color.YELLOW,SlimeBallTypes.list.get(5)).getFrom("Green", "red").addToList(); // From Green //Add Lava
				 new SlimeTypes(10, "Lemon", new ItemStack(slimeBallItem,1,5), Color.WHITE, Color.YELLOW,SlimeBallTypes.list.get(5)).getFrom("Grapefruit", "white").addToList(); // From Grapefruit //Add White
				 new SlimeTypes(11, "Magma", new ItemStack(slimeBallItem,1,1), Color.YELLOW, Color.RED,SlimeBallTypes.list.get(1)).getFrom("Yellow", "green").addToList(); // From Yellow //Add Plant
				 new SlimeTypes(12, "Ghosty", new ItemStack(slimeBallItem,1,6), Color.WHITE, Color.WHITE,SlimeBallTypes.list.get(6)).getFrom("Boxy", "white").addToList(); // From Boxy //Add White
	             new SlimeTypes(13,"Stone",new ItemStack(slimeBallItem,1,9),Color.GRAY, Color.GRAY,SlimeBallTypes.list.get(9)).getFrom("Boxy","stone").addToList();
	             new SlimeTypes(14,"Iron",new ItemStack(slimeBallItem,1,10),Color.GRAY,  Color.YELLOW,SlimeBallTypes.list.get(10)).getFrom("Stone","stone").addToList();
                 new SlimeTypes(15,"Ender",new ItemStack(slimeBallItem,1,7),new Color(0,0,0),  new Color(70,0,70),SlimeBallTypes.list.get(7)).getFrom("Nether","stone").addToList();
				 new SlimeTypes(16, "null", new ItemStack(slimeBallItem,1,0), new Color(0,0,0), new Color(0,0,0),SlimeBallTypes.list.get(0)).getFrom("null", "none").addToList(); // Not available
				 new SlimeTypes(17, "Nether", new ItemStack(slimeBallItem,1,8), Color.red, Color.red,SlimeBallTypes.list.get(8)).getFrom("Magma", "stone").addToList();
				 new SlimeTypes(18, "Basic", new ItemStack(Item.slimeBall), new Color(0,60,0), new Color(0,70,0),SlimeBallTypes.list.get(0)).getFrom("null", "none").addToList(); // From Main  //Add Plant
				 
			 }
			
		     @EventHandler
		        public void preInit(FMLPreInitializationEvent event) {
		                // Stub Method
		    	 MinecraftForge.EVENT_BUS.register(new SlimeEvent());
		        }
		        
			@Init
			public void load(FMLInitializationEvent event) {
				LanguageRegistry.instance().addStringLocalization("itemGroup.PustySlimes", "PustySlimes");
				
				GameRegistry.registerBlock(objectBlock,SlimeObjectItem.class,"pustyObject");
				GameRegistry.registerBlock(slimeBlock,SlimeBlockItem.class,"slimeBlock");
			    
				GameRegistry.registerBlock(mutatorBlock,me.pusty.slime.SlimeMutatorItem.class,"pustyMutator");
				GameRegistry.registerBlock(generatorBlock,SlimeGeneratorItem.class,"pustyGenerator");
				GameRegistry.registerBlock(tileentityBlock,SlimeBlockItem.class,"pustyMultiBlock");
				GameRegistry.registerBlock(tileentityCoreBlock,SlimeBlockItem.class,"pustyMultiBlockCore");

			    GameRegistry.registerTileEntity(TileEntityObject.class, "pustyObject");
			    GameRegistry.registerTileEntity(TileEntityMutator.class, "pustyMutator");
			    GameRegistry.registerTileEntity(TileEntityGenerator.class, "pustyGenerator");
			    GameRegistry.registerTileEntity(PustySlimeBlockTileEntity.class, "pustyMultiBlock");
			    GameRegistry.registerTileEntity(PustySlimeBlockTileEntityCore.class, "pustyMultiBlockCore");
			    
		        NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
			    
			   OreDictionary.registerOre("slimeBall", Item.slimeBall);
			   for(int i=1;i<SlimeBallTypes.list.size();i++)
			    OreDictionary.registerOre("slimeBall", new ItemStack(this.slimeBallItem,1,i));
			   
				proxy.registerRenderers();
				
	
				LanguageRegistry.addName(mutatorBlock, "SlimeMutator");
				LanguageRegistry.addName(objectBlock, "SlimeBox");
				LanguageRegistry.addName(generatorBlock, "SlimeGenerator");
				LanguageRegistry.addName(new ItemStack(this.slimeBlock,1,0), "NormalSlimeBlock");

				GameRegistry.addShapedRecipe(new ItemStack(this.objectBlock), "XXX", "IOI","XXX",'X', Block.cloth, 'I', Block.glass,'O',Block.obsidian);
				GameRegistry.addShapedRecipe(new ItemStack(this.mutatorBlock), "MMM", "IOI","XXX",'X', Block.cloth, 'I', Block.glass,'O',Block.obsidian,'M',Item.slimeBall);
				GameRegistry.addShapedRecipe(new ItemStack(this.generatorBlock), "XXX", "IOI","XXX",'X', Block.cloth, 'I', Block.glass,'O',Item.diamond);
				GameRegistry.addShapedRecipe(new ItemStack(this.slimeknowledgebook), "OXO", "XKX","OXO",'X', Item.paper, 'K', Item.diamond,'O',Block.blockRedstone);
				ItemStack d = new ItemStack(this.slimeItem,1,4);
				ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(d,  1, 1,10));
				d  = new ItemStack(this.slimeItem,1,4);
				d.addEnchantment(this.ench, 1);
				ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(d,  1, 2,5));
				d  = new ItemStack(this.slimeItem,1,4);
				d.addEnchantment(this.ench, 2);
				ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(d,  1, 1,3));
				d  = new ItemStack(this.slimeItem,1,4);
				d.addEnchantment(this.ench, 3);
				ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(d,  1, 1,3));
				
				d  = new ItemStack(this.slimeItem,1,4);
				d.addEnchantment(this.ench, 4);
				ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(d,  1, 1,3));
				
				d  = new ItemStack(this.slimeItem,1,1);
				d.addEnchantment(this.ench, 2);
				ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(d, 1, 1,1));
				
				d  = new ItemStack(this.slimeItem,1,7);
				d.addEnchantment(this.ench, 5);
				ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(d,  1, 1,1));
				
				
				d  = new ItemStack(this.slimeItem,1,9);
				d.addEnchantment(this.ench, 5);
				ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(d, 1, 1,1));
				
				
				d  = new ItemStack(this.slimeItem,1,4);
				d.addEnchantment(this.ench, 6);
				ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(d,1, 1,1));
				

				 
				
				ItemStack e = new ItemStack(this.slimeMutationItem,1,1);
			    e = SlimeMutatorItem.setTicks(SlimeMutatorItem.setType(new ItemStack(this.slimeMutationItem, 1, 1),"White"),1250);
				ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(d,  1, 1,20));
				
				 e = new ItemStack(this.slimeMutationItem,1,2);
				 e = SlimeMutatorItem.setTicks(SlimeMutatorItem.setType(new ItemStack(this.slimeMutationItem, 1, 2),"Red"),1500);
				ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(d,  1, 1,20));
				 e = new ItemStack(this.slimeMutationItem,1,3);
				e =  SlimeMutatorItem.setTicks(SlimeMutatorItem.setType(new ItemStack(this.slimeMutationItem, 1, 3),"Blue"),1500);
				ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(d,  1, 1,20));
				e = new ItemStack(this.slimeMutationItem,1,4);
				e =  SlimeMutatorItem.setTicks(SlimeMutatorItem.setType(new ItemStack(this.slimeMutationItem, 1, 4),"Green"),1500);
				ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(d,  1, 1,20));
			    e = new ItemStack(this.slimeMutationItem,1,5);
				e =  SlimeMutatorItem.setTicks(SlimeMutatorItem.setType(new ItemStack(this.slimeMutationItem, 1, 5),"Stone"),5000);
				ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(d,  1, 1,5));
				
				
				
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.magmaCream),"slimeBall",Item.blazePowder));
                GameRegistry.addShapedRecipe(new ItemStack(this.slimeBlock,1,0),"XXX","XXX","XXX",'X',new ItemStack(Item.slimeBall,1));
                GameRegistry.addShapedRecipe(new ItemStack(this.slimeBlock,1,1),"XXX","XXX","XXX",'X',new ItemStack(this.slimeBallItem,1,1));
                GameRegistry.addShapedRecipe(new ItemStack(this.slimeBlock,1,2),"XXX","XXX","XXX",'X',new ItemStack(this.slimeBallItem,1,2));
                GameRegistry.addShapedRecipe(new ItemStack(this.slimeBlock,1,3),"XXX","XXX","XXX",'X',new ItemStack(this.slimeBallItem,1,3));
                GameRegistry.addShapedRecipe(new ItemStack(this.slimeBlock,1,4),"XXX","XXX","XXX",'X',new ItemStack(this.slimeBallItem,1,4));
                GameRegistry.addShapedRecipe(new ItemStack(this.slimeBlock,1,10),"XXX","XXX","XXX",'X',new ItemStack(this.slimeBallItem,1,10));
                
                GameRegistry.addShapedRecipe(new ItemStack(Block.oreIron,1),"OXO","XOX","OXO",'X',new ItemStack(this.slimeBallItem,1,9),'O',new ItemStack(this.slimeBallItem,1,10));
                
                GameRegistry.addShapelessRecipe(new ItemStack(Block.cobblestone,1),new ItemStack(this.slimeBallItem,1,9),new ItemStack(this.slimeBallItem,1,9),new ItemStack(this.slimeBallItem,1,9),new ItemStack(this.slimeBallItem,1,9));
                GameRegistry.addShapelessRecipe(new ItemStack(Block.whiteStone,1),new ItemStack(this.slimeBallItem,1,7),new ItemStack(this.slimeBallItem,1,7),new ItemStack(this.slimeBallItem,1,7),new ItemStack(this.slimeBallItem,1,7));
                GameRegistry.addShapelessRecipe(new ItemStack(Block.netherBrick,1),new ItemStack(this.slimeBallItem,1,8),new ItemStack(this.slimeBallItem,1,8),new ItemStack(this.slimeBallItem,1,8),new ItemStack(this.slimeBallItem,1,8));
                GameRegistry.addShapelessRecipe(new ItemStack(Item.expBottle,1),new ItemStack(this.slimeBallItem,1,6),new ItemStack(Item.glassBottle));
                GameRegistry.addShapelessRecipe(new ItemStack(Item.appleRed,1),new ItemStack(this.slimeBallItem,1,5),new ItemStack(this.slimeBallItem,1,5),new ItemStack(this.slimeBallItem,1,5),new ItemStack(this.slimeBallItem,1,5));
                GameRegistry.addShapelessRecipe(new ItemStack(Block.leaves,1),new ItemStack(this.slimeBallItem,1,2));
                GameRegistry.addShapelessRecipe(new ItemStack(Block.wood,1),new ItemStack(this.slimeBallItem,1,2),new ItemStack(this.slimeBallItem,1,2),new ItemStack(this.slimeBallItem,1,2),new ItemStack(this.slimeBallItem,1,2));
                GameRegistry.addShapelessRecipe(new ItemStack(Item.potion,1,0),new ItemStack(this.slimeBallItem,1,3),new ItemStack(Item.glassBottle));
                GameRegistry.addShapelessRecipe(new ItemStack(Item.bucketLava,1),new ItemStack(this.slimeBallItem,1,1),new ItemStack(this.slimeBallItem,1,1),new ItemStack(this.slimeBallItem,1,1),new ItemStack(this.slimeBallItem,1,1),new ItemStack(this.slimeBallItem,1,1),new ItemStack(this.slimeBallItem,1,1),new ItemStack(this.slimeBallItem,1,1),new ItemStack(this.slimeBallItem,1,1),new ItemStack(Item.bucketEmpty));
                GameRegistry.addShapelessRecipe(new ItemStack(Item.bucketWater,1),new ItemStack(this.slimeBallItem,1,3),new ItemStack(this.slimeBallItem,1,3),new ItemStack(this.slimeBallItem,1,3),new ItemStack(this.slimeBallItem,1,3),new ItemStack(this.slimeBallItem,1,3),new ItemStack(this.slimeBallItem,1,3),new ItemStack(this.slimeBallItem,1,3),new ItemStack(this.slimeBallItem,1,3),new ItemStack(Item.bucketEmpty));
                
                GameRegistry.addShapelessRecipe(new ItemStack(Item.slimeBall,9),new ItemStack(this.slimeBlock,1,0));
                GameRegistry.addShapelessRecipe(new ItemStack(this.slimeBallItem,9,1),new ItemStack(this.slimeBlock,1,1));
                GameRegistry.addShapelessRecipe(new ItemStack(this.slimeBallItem,9,2),new ItemStack(this.slimeBlock,1,2));
                GameRegistry.addShapelessRecipe(new ItemStack(this.slimeBallItem,9,3),new ItemStack(this.slimeBlock,1,3));
                GameRegistry.addShapelessRecipe(new ItemStack(this.slimeBallItem,9,4),new ItemStack(this.slimeBlock,1,4));
                GameRegistry.addShapelessRecipe(new ItemStack(this.slimeBallItem,9,10),new ItemStack(this.slimeBlock,1,10));
            



			}
			
			@PostInit
			public void postInit(FMLPostInitializationEvent event) {
                proxy.registerRenderInformation();
			}
			







	



}