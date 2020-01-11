package tbag.io;

import tbag.location.ItemPickupLocation;
import tbag.location.Location;
import tbag.location.OptItemPickupLocation;
import tbag.location.TownLocation;
import tbag.management.Item;
import tbag.management.Trade;

public class DevCommands {
	
	public boolean allowDevCommands = false;
	
	/**
	 * Lists all available color codes
	 * @param args Not used in this command
	 * @param gameInstance The current Game Instance
	 * @return Returns a formatted <code>String</code>
	 */
	public String colors(String[] args, GameInstance gameInstance) {
			if(allowDevCommands) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < 16; i++) {
			sb.append("\n&" + Integer.toHexString(i) + "Color #" + Integer.toHexString(i));
			}
			return sb.toString();
		}
		return "&cCheats are disabled.";
	}
	
     /**
	 * Displays all variable values in <code>gameInstance</code>
	 * @param args Not used in this command
	 * @param gameInstance The current Game Instance
	 * @return returns a formatted <code>String</code>
	 */
	public String dev(String[] args, GameInstance gameInstance) {
		if(allowDevCommands) {
			StringBuilder sb = new StringBuilder();
			sb.append("\nDev Data: ");
			sb.append("\n&bPlayer:");
			sb.append("\n&e	UUID: " + gameInstance.player.toString().split("tbag.io.")[1]);
			sb.append("\n&e	Alive: " +gameInstance.player.alive);
			sb.append("\n&e	Currency: " +gameInstance.player.currency);
			sb.append("\n&e	Currency Name: " +gameInstance.player.currencyName);
			Location l = gameInstance.player.currentLocation;
			String type = l.getClass().getName().split("tbag.location.")[1];
			sb.append("\n&e	Current Location: " + gameInstance.player.currentLocation.dspName + "( " + l.toString().split("tbag.location." + type + ".")[1] + " )" );
			sb.append("\n&e	Available Trades:");
			for(Trade t : gameInstance.player.availableTrades) {
				sb.append("\n&d		" + t.itemAvailable.name + " | cost: " + t.cost);
			}
			sb.append("\n&e	Iventory Contents:");
			for(Item i : gameInstance.player.inv.inventoryItems) {
				sb.append("\n&d		" + i.name + " - " + gameInstance.player.inv.inventoryQuantities.get(gameInstance.player.inv.inventoryItems.indexOf(i)));
			}
			sb.append("\n&bLocation: ");
			sb.append("\n&e	UUID: " + l.toString().split("tbag.location.")[1]);
			sb.append("\n&e	Name: " + l.dspName);
			sb.append("\n&e	Desc: " + l.dspDesc);
			sb.append("\n&e	Type: " + type);
		
			//item pickup
			try{sb.append("\n&e	Item Pickup: " + ((ItemPickupLocation)l).itemPickup.name );}catch(Exception e) {}
			try{sb.append("\n&e	Optional Item Pickup: " + ((OptItemPickupLocation)l).itemPickup.name );}catch(Exception e) {}
		
			//shop
			try{
				TownLocation tl = (TownLocation)l;
				sb.append("\n&e 	Shop Contents:");
				for (Trade t : tl.shop.availableTrades) {
					sb.append("\n&d		" + t.itemAvailable.name + " | cost: " + t.cost);
				}
			}catch(Exception e) {}
			sb.append("\n&bTerminal:");
			sb.append("\n&e	UUID: " + gameInstance.terminal.toString().split("tbag.io.")[1]);
			sb.append("\n&e	Last Location: " + gameInstance.terminal.lastLocation.dspName + "( " + l.toString().split("tbag.location." + type + ".")[1] + " )" );
			sb.append("\n&bWindow:");
			sb.append("\n&e	UUID: " + gameInstance.window.toString().split("tbag.io.")[1]);
			sb.append("\n&e	Resolution: [ " + gameInstance.window.sizeX + " x " + gameInstance.window.sizeY + " ]");
			sb.append("\n&e	Scale: " + gameInstance.window.scale);
			sb.append("\n&e	Font Size: " + (int)(12 * gameInstance.window.scale));
			return sb.toString();
		}
		return "&cCheats are disabled.";
	}
}
