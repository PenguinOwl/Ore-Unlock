package penowl.plugin.oreunlock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.SkullType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

@SuppressWarnings("unused")
public class InvManagement {

	private static Plugin plugin;

	@SuppressWarnings("static-access")
	public InvManagement(Plugin plugin)
	{
		this.plugin = plugin;
	}

	public static ItemStack mmai(Material type, int amount, short dmg, String name) {
		ItemStack item = new ItemStack(type, amount, dmg);
		setName(item,name);
		return item;
	}

	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public static ItemStack setName(ItemStack items, String name){
		ItemMeta meta = items.getItemMeta();
		meta.setDisplayName(name);
		items.setItemMeta(meta);
		return items;
	}

	public static ItemStack setOwner(String name) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(name);
		meta.setDisplayName(ChatColor.GREEN + "Owner: " + ChatColor.RESET + "" + name);
		skull.setItemMeta(meta);
		return skull;
	}

	public static ItemStack taa(ItemStack is, int amount) {
		ItemStack kg = is.clone();
		kg.setAmount(amount);
		return kg;
	}

	public static ItemStack tfwool(boolean bln) {
		if (bln) {
			return mmai(Material.WOOL, 1, (short) 5, ChatColor.RESET + "" + ChatColor.GREEN + "TRUE");
		} else {
			return mmai(Material.WOOL, 1, (short) 14, ChatColor.RESET + "" + ChatColor.RED + "FALSE");
		}
	}

	public static int getSpace(Inventory inventory, ItemStack cur) {
		int count = 0;
		for(int x = 0; x < inventory.getSize(); x++) {
			if (inventory.getItem(x)==null) {
				count = count + cur.getMaxStackSize();
			} else if (inventory.getItem(x).isSimilar(cur)) {
				count = count + cur.getMaxStackSize() - inventory.getItem(x).getAmount();
			}
		}
		return count;
	}

	public static ItemStack sbwool(boolean bln) {
		if (bln) {
			return mmai(Material.WOOL, 1, (short) 4, ChatColor.RESET + "" + ChatColor.YELLOW + "BUY");
		} else {
			return mmai(Material.WOOL, 1, (short) 10, ChatColor.RESET + "" + ChatColor.DARK_PURPLE + "SELL");
		}
	}

	public static ItemStack conLE(ItemStack item, String name, String s1, Boolean ench) {
		String dname = ChatColor.RESET+""+ChatColor.AQUA+name;
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RESET+""+ChatColor.GRAY+s1);
		meta.setLore(lore);
		meta.setDisplayName(dname);
		if (ench) {
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			meta.addEnchant(Enchantment.FROST_WALKER, 1, true);
		}
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack ifbook(String name, String s1, String s2, String s3, String s4) {
		ItemStack item = mmai(Material.BOOK, 1, (short) 0, ChatColor.RESET+""+ChatColor.AQUA+name);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RESET+""+ChatColor.GRAY+s1);
		lore.add(ChatColor.RESET+""+ChatColor.GRAY+s2);
		lore.add(ChatColor.RESET+""+ChatColor.GRAY+s3);
		lore.add(ChatColor.RESET+""+ChatColor.GRAY+s4);
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addEnchant(Enchantment.FROST_WALKER, 1, true);
		item.setItemMeta(meta);
		return item;
	}

	public static void inverror(String error, Player player) {
		Inventory temp = Bukkit.createInventory(new FakeHolderM(new Location(null,0,0,0)), 18, "ERROR"); 
		for(int x = 0; x < 18; x = x + 1) {
			ItemStack blank = setName(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14),error);
			temp.setItem(x, blank);
		}
		player.closeInventory();
		player.openInventory(temp);
	}

	public static Inventory createInventory(Player player) {
		String configloc = "players."+player.getUniqueId().toString()+".";
		String ranka = plugin.getConfig().getString(configloc+"rank");
		plugin.getConfig().set(configloc+"name",player.getName());
		if (ranka == "" || ranka == null) {
			plugin.getConfig().set(configloc+"rank",plugin.getConfig().getInt("starting-rank"));
		}
		int rank = plugin.getConfig().getInt(configloc+"rank");
		Inventory inventory = Bukkit.createInventory(new FakeHolderM(null), 9, "Ore Unlock");
		inventory.setItem(1,conLE(mmai(Material.STONE, 1, (short) 0, ""),ChatColor.RESET+""+ChatColor.AQUA+"Stone",ChatColor.RESET+""+ChatColor.GREEN+"$"+plugin.getConfig().getString("prices.stone"),rank>-1));
		inventory.setItem(2,conLE(mmai(Material.COAL_ORE, 1, (short) 0, ""),ChatColor.RESET+""+ChatColor.AQUA+"Coal",ChatColor.RESET+""+ChatColor.GREEN+"$"+plugin.getConfig().getString("prices.coal"),rank>0));
		inventory.setItem(3,conLE(mmai(Material.IRON_ORE, 1, (short) 0, ""),ChatColor.RESET+""+ChatColor.AQUA+"Iron",ChatColor.RESET+""+ChatColor.GREEN+"$"+plugin.getConfig().getString("prices.iron"),rank>1));
		inventory.setItem(4,conLE(mmai(Material.GOLD_ORE, 1, (short) 0, ""),ChatColor.RESET+""+ChatColor.AQUA+"Gold",ChatColor.RESET+""+ChatColor.GREEN+"$"+plugin.getConfig().getString("prices.gold"),rank>2));
		inventory.setItem(5,conLE(mmai(Material.REDSTONE_ORE, 1, (short) 0, ""),ChatColor.RESET+""+ChatColor.AQUA+"Redstone and Lapis",ChatColor.RESET+""+ChatColor.GREEN+"$"+plugin.getConfig().getString("prices.redstone"),rank>3));
		inventory.setItem(6,conLE(mmai(Material.DIAMOND_ORE, 1, (short) 0, ""),ChatColor.RESET+""+ChatColor.AQUA+"Diamond",ChatColor.RESET+""+ChatColor.GREEN+"$"+plugin.getConfig().getString("prices.diamond"),rank>4));
		inventory.setItem(7,conLE(mmai(Material.EMERALD_ORE, 1, (short) 0, ""),ChatColor.RESET+""+ChatColor.AQUA+"Emerald",ChatColor.RESET+""+ChatColor.GREEN+"$"+plugin.getConfig().getString("prices.emerald"),rank>5));
		return inventory;
	}

}
