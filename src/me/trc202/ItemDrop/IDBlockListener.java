package me.trc202.ItemDrop;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockListener;;

public class IDBlockListener extends BlockListener{
	public static ItemD plugin;
	public IDBlockListener(ItemD instance){
		plugin = instance;
	}
	public void onBlockPlace(BlockPlaceEvent event)
	{
		Player player = event.getPlayer(); //Get player involved in the event (might break if entity placing block is not a player)
		if(plugin.blockdisabled.contains(event.getBlockPlaced().getTypeId()))//Checks to see if the item is blacklisted
		{
			if(!plugin.check(player, "ItemDrop.allow"))// checks to see if the user has permission
			{// user has no permission
				event.setCancelled(true); //cancel block placement
				player.sendMessage(ChatColor.RED + "You are not allowed to place this block"); // inform the user
			}
			else // user has permission
			{
				//allow event to pass
			}
		}
		else
		{
			//block placed is not nonallowed block
		}
	}
}
