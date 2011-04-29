package me.trc202.ItemDrop;

import org.bukkit.ChatColor;  //Allows the chat color to be red
import org.bukkit.entity.Player; 
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerListener;

public class IDItemListener extends PlayerListener {
	public static ID plugin;
	public IDItemListener(ID id){
		plugin = id;
	}
	public void onPlayerDropItem(PlayerDropItemEvent event){ //Overrides the default Item drop event
		Player player = event.getPlayer(); //Creates object player from class Player, Retrieves player who triggered event
		if(plugin.IDEnable == 1)
		{
				if(event.getItemDrop().getItemStack().getTypeId() == 7) //Checks to see if item dropped is bedrock
				{
					player.sendMessage(ChatColor.RED + "Bedrock destroyed"); //Notify's the player
					event.getItemDrop().remove(); //Removes bedrock
				}
				else
				{
					//do nothing
				}	
		}
	}
}