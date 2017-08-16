package penowl.plugin.oreunlock;
import org.bukkit.event.Listener;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

@SuppressWarnings("unused")
public final class ClickListener implements Listener {

	private static Plugin plugin;

	@SuppressWarnings("static-access")
	public ClickListener(Plugin plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler
	public void someoneKnocked(InventoryClickEvent event) {
		if (event.getClickedInventory().getHolder() != null) {
			if (event.getClickedInventory().getHolder() instanceof FakeHolderM) {
				ItemStack clicked = event.getCurrentItem();
				Inventory inventory = event.getClickedInventory();
				Player player = (Player) event.getWhoClicked();
				int slot = event.getSlot();
				String configloc = "players."+player.getUniqueId().toString()+".";
				event.setCancelled(true);
				int rank = plugin.getConfig().getInt(configloc+"rank");
				if (slot==1) {
					if (rank==-1&&Main.economy.getBalance((OfflinePlayer) player)>=plugin.getConfig().getInt("prices.stone")) {
						plugin.getConfig().set(configloc+"rank",0);
						plugin.saveConfig();
						player.closeInventory();
						player.sendMessage(ChatColor.YELLOW+"Unlocked "+ChatColor.BOLD+"Stone");
					}
				}
				if (slot==2) {
					if (rank==0&&Main.economy.getBalance((OfflinePlayer) player)>=plugin.getConfig().getInt("prices.coal")) {
						plugin.getConfig().set(configloc+"rank",1);
						plugin.saveConfig();
						player.closeInventory();
						player.sendMessage(ChatColor.YELLOW+"Unlocked "+ChatColor.BOLD+"Coal");
					}
				}
				if (slot==3) {
					if (rank==1&&Main.economy.getBalance((OfflinePlayer) player)>=plugin.getConfig().getInt("prices.iron")) {
						plugin.getConfig().set(configloc+"rank",2);
						plugin.saveConfig();
						player.closeInventory();
						player.sendMessage(ChatColor.YELLOW+"Unlocked "+ChatColor.BOLD+"Iron");
					}
				}
				if (slot==4) {
					if (rank==2&&Main.economy.getBalance((OfflinePlayer) player)>=plugin.getConfig().getInt("prices.gold")) {
						plugin.getConfig().set(configloc+"rank",3);
						plugin.saveConfig();
						player.closeInventory();
						player.sendMessage(ChatColor.YELLOW+"Unlocked "+ChatColor.BOLD+"Gold");
					}
				}
				if (slot==5) {
					if (rank==3&&Main.economy.getBalance((OfflinePlayer) player)>=plugin.getConfig().getInt("prices.redstone")) {
						plugin.getConfig().set(configloc+"rank",4);
						plugin.saveConfig();
						player.closeInventory();
						player.sendMessage(ChatColor.YELLOW+"Unlocked "+ChatColor.BOLD+"Redstone and Lapis");
					}
				}
				if (slot==6) {
					if (rank==4&&Main.economy.getBalance((OfflinePlayer) player)>=plugin.getConfig().getInt("prices.diamond")) {
						plugin.getConfig().set(configloc+"rank",5);
						plugin.saveConfig();
						player.closeInventory();
						player.sendMessage(ChatColor.YELLOW+"Unlocked "+ChatColor.BOLD+"Diamond");
					}
				}
				if (slot==7) {
					if (rank==5&&Main.economy.getBalance((OfflinePlayer) player)>=plugin.getConfig().getInt("prices.emerald")) {
						plugin.getConfig().set(configloc+"rank",6);
						plugin.saveConfig();
						player.closeInventory();
						player.sendMessage(ChatColor.YELLOW+"Unlocked "+ChatColor.BOLD+"Emerald");
					}
				}
			}
		}
	}
}
