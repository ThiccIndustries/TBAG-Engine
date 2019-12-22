package tbag.io;

import java.lang.reflect.Method;

import tbag.management.Item;

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
		sb.append("> ");
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
		sb.append("\n" + gameInstance.player.currency + " " + gameInstance.player.currencyName);
		for(Item i : gameInstance.player.inv.inventoryItems) {
			sb.append("\n" + i.name + ": " + gameInstance.player.inv.inventoryQuantities.get(gameInstance.player.inv.inventoryItems.indexOf(i)));
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
			returnString += "\n" + command.getName();
		}
		returnString += "\n";
		return returnString;
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
}
