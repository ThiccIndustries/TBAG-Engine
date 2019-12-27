package tbag.io;

import java.lang.reflect.Method;

import tbag.location.ItemPickupLocation;
import tbag.location.Location;
import tbag.location.OptItemPickupLocation;
import tbag.location.TownLocation;
import tbag.management.Item;
import tbag.management.Trade;

/**
 * Contains standard TBAG Commands
 * 
 * <p>
 * 
 * All commands will receive <code>String[]</code> and <code>Player</code>.
 * All commands have the ability to return a <code>String</code>.
 * 
 * <p>
 * 
 * To supplement with additional commands, create your own subclass using extends Commands. 
 * Be sure to pass an instance of your subclass into <code>readCommand()</code>
 * @author Trevor Skupien
 */
public class Commands {

	
	/**
	 * Repeats the given message
	 * @param message Message to be repeated
	 * @param gameInstance Current Game Instance
	 * @return returns the Args as a formatted <code>String</code>
	 */
	public String echo(String[] message, GameInstance gameInstance) {
		StringBuilder sb = new StringBuilder();
		sb.append("&2> ");
		for(String s : message){
			sb.append(s + " ");
		}
		return sb.toString();
	}
	
	/**
	 * Travels given Adjacent location of player's current position
	 * @param direction The direction of travel
	 * @param gameInstance The current Game Instance
	 * @return May return error statement
	 */
	public String go(String[] direction, GameInstance gameInstance) { 
		String dir = direction[0].toLowerCase();
		switch (dir) {
			case "north":
				gameInstance.player.currentLocation = gameInstance.player.currentLocation.northLocation;
				break;
			case "east":
				gameInstance.player.currentLocation = gameInstance.player.currentLocation.eastLocation;
				break;
			case "south":
				gameInstance.player.currentLocation = gameInstance.player.currentLocation.southLocation;
				break;
			case "west":
				gameInstance.player.currentLocation = gameInstance.player.currentLocation.westLocation;
				break;	
			default:
				return ("I don't know which way that is.");
		}
		return null;
	}
	
	/**
	 * Displays the amount of currency held by the <code>Player</code> as well as the <code>Item</code> contents of their <code>Inventory</code>
	 * @param args Not used in this command 
	 * @param gameInstance The current Game Instance
	 * @return returns contents in a formatted <code>String</code>
	 */
	public String bag(String[] args, GameInstance gameInstance) {
		StringBuilder sb = new StringBuilder();
		sb.append("You Have:");
		sb.append("\n&6 " + gameInstance.player.currency + " " + gameInstance.player.currencyName);
		for(Item i : gameInstance.player.inv.inventoryItems) {
			sb.append("\n&6 " + i.name + ": " + gameInstance.player.inv.inventoryQuantities.get(gameInstance.player.inv.inventoryItems.indexOf(i)));
		}
		return sb.toString();
	}
	
	/**
	 * Displays all commands found in the class
	 * @param args Not used in this command
	 * @param gameInstance Not used in this command
	 * @return returns list of all commands, formatted with /n
	 */
	public String help(String[] args, GameInstance gameInstance) {

		Method[] commands = this.getClass().getDeclaredMethods();
		String returnString = "Available commands: ";
		for(Method command : commands) {
			returnString += "\n &6" + command.getName();
		}
		returnString += "\n";
		return returnString;
	}
	
	/**
	 * Displays all the available trades in the Shop of a Town Location.
	 * <p>
	 * Sets the player's availableTrades to the shop's trades
	 * @param args Not used in this command
	 * @param gameInstance Current game instance.
	 * @return Returns the available Trades in a formatted String.
	 */
	public String shop(String[] args, GameInstance gameInstance) {
	String shopContents = null;
	try {
	if(((TownLocation)gameInstance.player.currentLocation).shop != null) {
		gameInstance.player.availableTrades = ((TownLocation)gameInstance.player.currentLocation).shop.availableTrades;
		StringBuilder sb = new StringBuilder();
		sb.append("Available trades:");
		
		for(int i = 0; i < gameInstance.player.availableTrades.size(); i++) {
			sb.append("\n[ " + (i+1) + " ] " + gameInstance.player.availableTrades.get(i).itemAvailable.name + ": " + gameInstance.player.availableTrades.get(i).cost + " each");
		}
		
		shopContents = sb.toString();
	}
	}catch(Exception e) { return("There isn't a shop here."); }
	return shopContents;
	}
	
	/**
	 * Buys one of the trades in the player's available trades
	 * @param args The index of the trade (displayed index, starting at 1)
	 * @param gameInstance The current game instance
	 * @return Returns the success or reason of failure.
	 */
	public String buy(String[] args, GameInstance gameInstance) {
		String output = null;
		try {
			Trade temp = gameInstance.player.availableTrades.get(Integer.parseInt(args[0]) - 1);
			if(temp.cost <= gameInstance.player.currency) {
				gameInstance.player.currency -= temp.cost;
				gameInstance.player.inv.addItem(temp.itemAvailable, 1);
				output = "You've bought one " + temp.itemAvailable.name + " for " + temp.cost + " " + gameInstance.player.currencyName;
			}else {
				output = "You cant afford to buy that.";
			}
		}catch(Exception e) {
			output = "That trade isn't available.";
		}
		return output;
	}
	
	/**
	 * Pickups up the <code>Item</code> from an <code>Optional Item Pickup Location</code>
	 * @param item The name of the item the player is trying to pick up
	 * @param gameInstance The current Game Instance
	 * @return Returns a formatted <code>String</code>
	 */
	public String pickup(String[] item, GameInstance gameInstance) {
		try {
			OptItemPickupLocation temp = ((OptItemPickupLocation)gameInstance.player.currentLocation);
			return(temp.pickupItem(item[0], gameInstance));
		}catch(Exception e) {return("Theres nothing to pickup here.");}
	}
	
	/**
	 * Quits the game
	 * @param args Not used in this command
	 * @param gameInstance Not used in this command
	 * @return does not return any value
	 */
	public String quit(String[] args, GameInstance gameInstance) {

		System.exit(0);
		return null;
	}
	
	/**
	 * Displays all variable values in <code>gameInstance</code>
	 * @param args Not used in this command
	 * @param gameInstance The current Game Instance
	 * @return returns a formatted <code>String<code>
	 */
	public String dev(String[] args, GameInstance gameInstance) {
		StringBuilder sb = new StringBuilder();
		sb.append("\nDev Data: ");
		sb.append("\n&3Player:");
		sb.append("\n&6	Alive: " +gameInstance.player.alive);
		sb.append("\n&6	Currency: " +gameInstance.player.currency);
		sb.append("\n&6	Currency Name: " +gameInstance.player.currencyName);
		Location l = gameInstance.player.currentLocation;
		String type = l.getClass().getName().split("tbag.location.")[1];
		sb.append("\n&6	Current Location: " + gameInstance.player.currentLocation.dspName + "( " + l.toString().split("tbag.location." + type + ".")[1] + " )" );
		sb.append("\n&6	Available Trades:");
		for(Trade t : gameInstance.player.availableTrades) {
			sb.append("\n&5		" + t.itemAvailable.name + " | cost: " + t.cost);
		}
		sb.append("\n&6	Iventory Contents:");
		for(Item i : gameInstance.player.inv.inventoryItems) {
			sb.append("\n&5		" + i.name + " - " + gameInstance.player.inv.inventoryQuantities.get(gameInstance.player.inv.inventoryItems.indexOf(i)));
		}
		sb.append("\n&3Location: ");
		sb.append("\n&6	UUID: " + l.toString().split("tbag.location." + type + ".")[1]);
		sb.append("\n&6	Name: " + l.dspName);
		sb.append("\n&6	Desc: " + l.dspDesc);
		sb.append("\n&6	Type: " + type);
		
		//item pickup
		try{sb.append("\n&6	Item Pickup: " + ((ItemPickupLocation)l).itemPickup.name );}catch(Exception e) {}
		try{sb.append("\n&6	Optional Item Pickup: " + ((OptItemPickupLocation)l).itemPickup.name );}catch(Exception e) {}
		
		//shop
		try{
			TownLocation tl = (TownLocation)l;
			sb.append("\n&6 	Shop Contents:");
			for (Trade t : tl.shop.availableTrades) {
				sb.append("\n&5		" + t.itemAvailable.name + " | cost: " + t.cost);
			}
		}catch(Exception e) {}
		sb.append("\n&3Terminal:");
		sb.append("\n&6	Last Location: " + gameInstance.terminal.lastLocation.dspName + "( " + l.toString().split("tbag.location." + type + ".")[1] + " )" );
		sb.append("\n&3Window:");
		sb.append("\n&6	Resolution: [ " + gameInstance.window.sizeX + " x " + gameInstance.window.sizeY + " ]");
		sb.append("\n&6	Scale: " + gameInstance.window.scale);
		sb.append("\n&6	Font Size: " + (int)(12 * gameInstance.window.scale));
		return sb.toString();
	}
}
