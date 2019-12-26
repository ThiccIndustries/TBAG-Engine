package tbag.io;

import java.lang.reflect.Method;

import java.util.Arrays;
import java.util.List;

import tbag.location.Location;
import tbag.management.Inventory;
import tbag.management.Trade;

/**
 * Contains methods and information relevant to the player and the player's input.
 * Contains the player's current position as a <code>Location</code> object.
 * @author Trevor Skupien
 */


public class Player {

	public Location currentLocation;
	public boolean alive = true;
	public Inventory inv = new Inventory();
	public List<Trade> availableTrades;
	
	public int currency = 0;
	public String currencyName = "Coins";
	/**
	 * Declares player instance
	 * @param startingLocation The Location object to start the player at
	 */
	public Player(Location startingLocation) {
		currentLocation = startingLocation;
	}
	
	/**
	 * Reads a command given by the player in the console
	 * @param input The string of the command, unparsed
	 * @param gameInstance The current <code>GameInstance</code>
	 * @return Returns the output of the command, if there is anything
	 */
	public Object readCommand(String input, GameInstance gameInstance) {
		Object returnObj = null;
		if(alive) {
		String[] splitInput = input.trim().split("\\s+"); //split the input into separate strings
		
		//protect against array out of bounds exception on one word commands
		if(splitInput.length == 1) {
			String temp = splitInput[0];
			splitInput = new String[2];
			splitInput[0] = temp;
			splitInput[1] = "";
		}
		//attempt to execute the command
		Method command;
		
		//splits splitInput into separate, more usable objects
		String com = splitInput[0].toLowerCase();
		String[] args = Arrays.copyOfRange(splitInput, 1, splitInput.length);
		
		//debug
		//System.out.println("Command: " + com + ", args: " + Arrays.toString(args));
		
		//find the command
		try {
			command = gameInstance.commands.getClass().getMethod(com, args.getClass(), gameInstance.getClass());
			returnObj = command.invoke(gameInstance.commands, args, gameInstance);
		}	catch (NoSuchMethodException e) {returnObj = "Sorry, I dont know what you mean by that.";}
			catch (Exception e) {gameInstance.terminal.displayError(e.toString(), gameInstance);}
		return returnObj;
		}
		return null;
	}
}
