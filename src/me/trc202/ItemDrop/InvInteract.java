package me.trc202.ItemDrop;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;


public class InvInteract extends PlayerListener {
	public static ID plugin;
	public InvInteract(ID instance){
		plugin = instance;
	}
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(plugin.IDEnable == 1)
		{
			if(event.getAction() == Action.RIGHT_CLICK_BLOCK)  //Checks to see if player right clicked a block, otherwise does nothing
			{
				Block myblock = event.getClickedBlock();
				if(myblock.getTypeId() == 54 || myblock.getTypeId() == 61 || myblock.getTypeId() == 62 || myblock.getTypeId() == 23)
				{
					player.sendMessage(ChatColor.RED + "You clicked a chest, furnace, or despencer!");
					if(player.getInventory().contains(7))
					{
						player.getInventory().remove(7);
						player.sendMessage(ChatColor.RED + "Bedrock Removed");
					}
				}
			}
		}
	}
}