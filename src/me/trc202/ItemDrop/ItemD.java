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
import java.io.*;

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
		//String myfilelocation = "plugins/ItemDrop/dblocks.txt";
		try
		 { //Read from a file
			    // Open the file that is the first 
			    // command line parameter
			    FileInputStream fstream = new FileInputStream("plugins/ItemDrop/dblocks.txt");
			    // Get the object of DataInputStream
			    DataInputStream in = new DataInputStream(fstream);
			        BufferedReader br = new BufferedReader(new InputStreamReader(in));
			    String strLine;
			    //Read File Line By Line
			    while ((strLine = br.readLine()) != null)   //read line until it reaches the null character
			    {
			      // Print the content on the console
			    	int blockIntTmp = Integer.parseInt(strLine);
			    	blockdisabled.add(new Integer(blockIntTmp)); //Put blocks on 
			      log.info(strLine);
			    }
			   //Close the input stream
			    in.close();
		}
		 catch (Exception e){//Catch exception if any
			 log.info("Error: " + e.getMessage()); //Print error message to console if there is one
			    }
			    String numberblocksloaded = Integer.toString(blockdisabled.size()); //Convert blockdisabled to string for use in blocks loaded
		log.info("Item drop 1.0 enabled, " + numberblocksloaded + " blocks loaded"); //Show enable message & list blocks loaded
	}
	public void onDisable() 
	{
		log.info("Item drop 1.0 disabled");
		try{ //Write to a file
		    // Create file 
		    FileWriter fstream = new FileWriter("plugins/ItemDrop/dblocks.txt");
		        BufferedWriter out = new BufferedWriter(fstream);
		        out.write("");
		        for (int counter = 0; counter < blockdisabled.size(); counter = counter+1) 
				{  
		        	String stringwriteout = Integer.toString(blockdisabled.get(counter));//convert to string block number
		        	//append it to file
		        	
		        	out.append(stringwriteout);
		        	out.append(System.getProperty("line.separator"));
				}
		    //Close the output stream
		    out.close();
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
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