package me.pusty.slime;

import me.pusty.slime.generator.GeneratorContainer;
import me.pusty.slime.generator.GeneratorGui;
import me.pusty.slime.generator.TileEntityGenerator;
import me.pusty.slime.mutater.MutatorContainer;
import me.pusty.slime.mutater.MutatorGui;
import me.pusty.slime.mutater.TileEntityMutator;
import me.pusty.slime.object.ObjectContainer;
import me.pusty.slime.object.ObjectGui;
import me.pusty.slime.object.TileEntityObject;
import me.pusty.slime.slimeball.PustySlimeBlockTileEntityCore;
import me.pusty.slime.slimeball.PustySlimeContainer;
import me.pusty.slime.slimeball.PustySlimeFurnanceGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
        //returns an instance of the Container you made earlier
        @Override
        public Object getServerGuiElement(int id, EntityPlayer player, World world,
                        int x, int y, int z) {
             if(id == 0){
                   TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                   if(tileEntity instanceof PustySlimeBlockTileEntityCore){
                           return new PustySlimeContainer(player.inventory, (PustySlimeBlockTileEntityCore) tileEntity);
                   }  
        	   }else if(id == 1){
                   TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                   if(tileEntity instanceof TileEntityMutator){
                           return new MutatorContainer(player.inventory, (TileEntityMutator) tileEntity);
                   }
           	   }else if(id == 2){
                   TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                   if(tileEntity instanceof TileEntityObject){
                           return new ObjectContainer(player.inventory, (TileEntityObject) tileEntity);
                   }
           	   }else if(id == 3){
                   TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                   if(tileEntity instanceof TileEntityGenerator){
                           return new GeneratorContainer(player.inventory, (TileEntityGenerator) tileEntity);
                   }
           	   }
                return null;
        }

        //returns an instance of the Gui you made earlier
        @Override
        public Object getClientGuiElement(int id, EntityPlayer player, World world,
                        int x, int y, int z) {
        	if(id == 0){
                TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                if(tileEntity instanceof PustySlimeBlockTileEntityCore){
                        return new PustySlimeFurnanceGui(player.inventory, (PustySlimeBlockTileEntityCore) tileEntity);
                }
        	}else if(id == 1){
                TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                if(tileEntity instanceof TileEntityMutator){
                        return new MutatorGui(player.inventory, (TileEntityMutator) tileEntity);
                }
        	}else if(id == 2){
                TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                if(tileEntity instanceof TileEntityObject){
                        return new ObjectGui(player.inventory, (TileEntityObject) tileEntity);
                }
        	}else if(id == 3){
                TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                if(tileEntity instanceof TileEntityGenerator){
                        return new GeneratorGui(player.inventory, (TileEntityGenerator) tileEntity);
                }
        	}else if(id == 4){
                        return new SlimeBookGui(player);
                
        	}else if(id == 5){
                return new SlimeKnowledgeBookGui(player);
                
	        }else if(id == 6){
               return new SlimeBookGui2(player);
        
}
                return null;

        }
}