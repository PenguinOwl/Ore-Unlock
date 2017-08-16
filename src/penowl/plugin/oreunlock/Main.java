package penowl.plugin.oreunlock;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;


@SuppressWarnings("unused")
public class Main extends JavaPlugin {

	private final ClickListener clickListener = new ClickListener(this);
	private final InvManagement invManagement = new InvManagement(this);
	private final ProtectListener protectListener = new ProtectListener(this);

	public static Economy economy = null;

	private Boolean setupEconomy()
	{
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}
		return (economyProvider != null);
	}

	@Override
	public void onEnable() {
		getConfig();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(clickListener, this);
		pm.registerEvents(protectListener, this);
		PluginDescriptionFile pdfFile = this.getDescription();
		getLogger().info( "A wild " + pdfFile.getName() + " version " + pdfFile.getVersion() + " appeared!" );
		setupEconomy();
		if (this.getConfig().getString("starting-rank")==null) {
			this.saveDefaultConfig();
		}
	}

	@Override
	public void onDisable() {
		saveConfig();
	}

	public boolean onCommand(CommandSender sender, Command command, String flag, String[] args){
		Boolean perm = false;
		Boolean perm1 = false;
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
			perm = player.hasPermission("oreunlock.admin");
			perm1 = player.hasPermission("oreunlock.use");
		}
		if(command.getName().equalsIgnoreCase("oreunlock")||command.getName().equalsIgnoreCase("oreu")||command.getName().equalsIgnoreCase("ou")){
			if (sender instanceof Player) {
				if (perm1 && args.length == 0) {
					player.openInventory(InvManagement.createInventory(player));
					return true;
				}
				if (perm && args.length == 2 && args[0].compareTo("get")==0) {
					player.sendMessage(this.getConfig().getString("players."+(Bukkit.getPlayer(args[1]).getUniqueId().toString())+".rank"));
					return true;
				}
				if (perm && args.length == 3 && args[0].compareTo("set")==0) {
					this.getConfig().set("players."+(Bukkit.getPlayer(args[1]).getUniqueId().toString())+".rank",Integer.valueOf(args[2]));
					player.sendMessage("Mining level set!");
					return true;
				}
			}
			return false;
		}
		return true;
	}
}

