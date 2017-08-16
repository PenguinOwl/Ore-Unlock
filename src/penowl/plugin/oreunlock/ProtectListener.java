package penowl.plugin.oreunlock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

@SuppressWarnings("unused")
public class ProtectListener implements Listener {

	private static Plugin plugin;

	@SuppressWarnings("static-access")
	public ProtectListener(Plugin plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (!(!(event.getPlayer().getGameMode()==GameMode.SURVIVAL) || event.getPlayer().hasPermission("oreunlock.override"))){
			Player player = event.getPlayer();
			String configloc = "players."+player.getUniqueId().toString()+".";
			int rank = plugin.getConfig().getInt(configloc+"rank");
			if (event.getBlock().getType()==Material.STONE&&rank<0) {
				player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +"Hey! " + ChatColor.RESET + "" + ChatColor.YELLOW + "You can't break this block until you unlock it with /ou");
				event.setCancelled(true);
			}
			if (event.getBlock().getType()==Material.COAL_ORE&&rank<1) {
				player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +"Hey! " + ChatColor.RESET + "" + ChatColor.YELLOW + "You can't break this block until you unlock it with /ou");
				event.setCancelled(true);
			}
			if (event.getBlock().getType()==Material.IRON_ORE&&rank<2) {
				player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +"Hey! " + ChatColor.RESET + "" + ChatColor.YELLOW + "You can't break this block until you unlock it with /ou");
				event.setCancelled(true);
			}
			if (event.getBlock().getType()==Material.GOLD_ORE&&rank<3) {
				player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +"Hey! " + ChatColor.RESET + "" + ChatColor.YELLOW + "You can't break this block until you unlock it with /ou");
				event.setCancelled(true);
			}
			if ((event.getBlock().getType()==Material.REDSTONE_ORE||event.getBlock().getType()==Material.GLOWING_REDSTONE_ORE)&&rank<4) {
				player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +"Hey! " + ChatColor.RESET + "" + ChatColor.YELLOW + "You can't break this block until you unlock it with /ou");
				event.setCancelled(true);
			}
			if (event.getBlock().getType()==Material.LAPIS_ORE&&rank<4) {
				player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +"Hey! " + ChatColor.RESET + "" + ChatColor.YELLOW + "You can't break this block until you unlock it with /ou");
				event.setCancelled(true);
			}
			if (event.getBlock().getType()==Material.DIAMOND_ORE&&rank<5) {
				player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +"Hey! " + ChatColor.RESET + "" + ChatColor.YELLOW + "You can't break this block until you unlock it with /ou");
				event.setCancelled(true);
			}
			if (event.getBlock().getType()==Material.EMERALD_ORE&&rank<6) {
				player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +"Hey! " + ChatColor.RESET + "" + ChatColor.YELLOW + "You can't break this block until you unlock it with /ou");
				event.setCancelled(true);
			}
		}
	}
}
