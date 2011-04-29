package me.trc202.ItemDrop;

import java.util.logging.Logger;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ID extends JavaPlugin {
	private final IDItemListener PlayerListener = new IDItemListener(this);
	private final InvInteract InvListener = new InvInteract(this);
	private final IDPickListener PickListener = new IDPickListener(this);
	private static final Logger log = Logger.getLogger("Minecraft");
	public int IDEnable = 1;
	
	public static PermissionHandler permissionHandler;
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_DROP_ITEM, PlayerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, InvListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_PICKUP_ITEM, PickListener, Event.Priority.Normal, this);
		IDEnable = 1;
		
		setupPermissions();
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
            log.info("Permission system not detected. Item Drop is disabled");
			}
		}
	}
}