package me.trc202.ItemDrop;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.plugin.Plugin;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemD extends JavaPlugin {
	private final IDItemListener PlayerListener = new IDItemListener(this);
	private final InvInteract InvListener = new InvInteract(this);
	private final IDPickListener PickListener = new IDPickListener(this);
	private final IDBlockListener BlockListener = new IDBlockListener(this);
	private static final Logger log = Logger.getLogger("Minecraft");
	public static PermissionHandler Permissions = null;
	List<Integer> blockdisabled = new ArrayList<Integer>(); //Contains all blocks disabled by ItemDrop
	public int IDEnable = 1; // 1 for enabled 0 for disabled
	
	public static PermissionHandler permissionHandler;
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_DROP_ITEM, PlayerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, InvListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_PICKUP_ITEM, PickListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_PLACE, BlockListener, Event.Priority.Normal, this);
		IDEnable = 1;
		setupPermissions();
		blockdisabled.add(new Integer(7));
		blockdisabled.add(new Integer(1));
		log.info("Item drop 1.0 enabled");
	}
	public void onDisable() {
		log.info("Item drop 1.0 disabled");
		IDEnable = 0;
	}

	@SuppressWarnings("static-access") //Supresses a static access warning (unsure of what the warning is) 
	private void setupPermissions() {
		Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");

		if (this.permissionHandler == null) {
			if (permissionsPlugin != null) {
				this.permissionHandler = ((Permissions) permissionsPlugin).getHandler();
			} 
			else {
				log.info("Permission system not detected. Defaulting to OP");
			}
		}
	}
	public boolean check(CommandSender sender, String permNode)
	{
		if (sender instanceof Player)
		{
			if (Permissions == null)
			{
				if (sender.isOp()) { return true; }
				else
				{
				return false;
				}
			}
			else
			{
				Player player = (Player) sender;
				return Permissions.has(player, permNode);
			}
		}
		else if (sender instanceof ConsoleCommandSender)
		{
			return false;
		}
		else
		{
			return false;
		}
	}
}