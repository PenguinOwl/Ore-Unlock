package penowl.plugin.oreunlock;

import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class FakeHolderM implements InventoryHolder{
	
	public Location grl = null;
	public String dis = "Sd";
	
	@Override
    public Inventory getInventory() {
        return null;
    }
	
	public void setLoc(Location loc) {
		grl = loc;
	}
	
	public Location getLoc() {
		return grl;
	}
	
	public FakeHolderM(Location loc) {
		grl = loc;
	}
	
}
