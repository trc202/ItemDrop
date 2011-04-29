package me.trc202.ItemDrop;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class IDPickListener extends PlayerListener {
	public static ItemD plugin;
	public IDPickListener(ItemD instance){
		plugin = instance;
	}
	public void onPlayerPickupItem(PlayerPickupItemEvent event) //Event that happens on item pickup
	{
		if(plugin.IDEnable == 1)
		{
			Player player = event.getPlayer(); //Get player involved in the event
					if(event.getItem().getItemStack().getTypeId() == 7) //Check to see if the item is bedrock
						{
							if(!plugin.check(player, "ItemDrop.allow"))
							{
								event.setCancelled(true); //Cancel Picking up item if it is bedrock
								player.sendMessage(ChatColor.RED + "You can't have that."); //Inform the player
							}
						}
					else
						{
							//do nothing
						}
		}
	}
}