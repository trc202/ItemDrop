package me.trc202.ItemDrop;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;

public class IDBreakListener extends BlockListener 
{

	public static ItemD plugin;
	public IDBreakListener(ItemD instance)
	{
		plugin = instance;
	}
	public void onBlockBreak(BlockBreakEvent event) 
	{
		if(plugin.IDEnable == 1) //Check to see if the plugin is enabled
		{
			Player player = event.getPlayer();
			if(!plugin.check(player, "ItemDrop.blockbreak"))
			{
				if(plugin.blockdisabled.contains(event.getBlock().getTypeId()))
				{
				event.setCancelled(true); //Cancel Picking up item if it is bedrock
				player.sendMessage(ChatColor.RED + "You don't have permission to break this block."); //Inform the player
				}
			}
		}
		else
		{
			//do nothing, plugin is disabled
		}
	}
}
