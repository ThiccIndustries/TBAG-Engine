package tbag.location;

import tbag.io.GameInstance;

/**
 * Most basic location type.
 * Contains a name, description, and references to adjacent locations
 * @author Trevor Skupien
 */
public class Location {
	public String dspName = "location";
	public String dspDesc = "a location";
	public Location northLocation = null; //location to the north
	public Location eastLocation = null; //east
	public Location southLocation = null; //south
	public Location westLocation = null; //west
	
	/**
	 * Used to determine what adjacent locations to display.
	 * 
	 * <p>
	 * 
	 * Valve is determined by adding: <br>
	 * 16 to display north location <br>
	 * 8 to display east location <br>
	 * 4 to display south location <br>
	 * 2 to display west location <br>
	 */
	public int displayAdj = 0; 
	
	/**
	 * Use this to manually specify a display mode on external locations
	 * @param name The display name of the location
	 * @param desc The description of the location
	 * @param displayMode the display mode used by the location
	 */
	public Location(String name, String desc, int displayMode) { 
		dspName = name;
		dspDesc = desc;
		//at least one of these should be overridden
		northLocation = this;
		eastLocation = this;
		southLocation = this;
		westLocation = this;
		
		displayAdj = displayMode;
	}
	/**
	 * Use this to declare external locations
	 * @param name The display name of the location
	 * @param desc The description of the location
	 */
	public Location(String name, String desc) { 
		dspName = name;
		dspDesc = desc;
		//at least one of these should be overridden
		northLocation = this;
		eastLocation = this;
		southLocation = this;
		westLocation = this;
	}
	
	/**
	 * Use this to declare internal locations with a manual display mode
	 * @param name The display name of the location
	 * @param desc The description of the location
	 * @param northTravel The location object to the north
	 * @param eastTravel The location object to the east
	 * @param southTravel The location object to the south
	 * @param westTravel The location object to the west
	 * @param displayMode the display mode used by the location
	 */
	public Location(String name, String desc, Location northTravel, Location eastTravel, Location southTravel, Location westTravel, int displayMode) {
		dspName = name;
		dspDesc = desc;
		northLocation = northTravel;
		eastLocation = eastTravel;
		southLocation = southTravel;
		westLocation = westTravel;
		
		displayAdj = displayMode;
	}
	
	/**
	 * Use this to declare internal locations
	 * @param name The display name of the location
	 * @param desc The description of the location
	 * @param northTravel The location object to the north
	 * @param eastTravel The location object to the east
	 * @param southTravel The location object to the south
	 * @param westTravel The location object to the west
	 */
	public Location(String name, String desc, Location northTravel, Location eastTravel, Location southTravel, Location westTravel) {
		dspName = name;
		dspDesc = desc;
		northLocation = northTravel;
		eastLocation = eastTravel;
		southLocation = southTravel;
		westLocation = westTravel;
	}
	
	
	/**
	 * Sets all four Adjacent locations at once
	 * @param northTravel The location object to the north
	 * @param eastTravel The location object to the east
	 * @param southTravel The location object to the south
	 * @param westTravel The location object to the west
	 */
	public void SetLocations(Location northTravel, Location eastTravel, Location southTravel, Location westTravel) {
		northLocation = northTravel;
		eastLocation = eastTravel;
		southLocation = southTravel;
		westLocation = westTravel;
	}
	
	/**
	 * Get all four adjacent locations
	 * @return Returns all four adjacent locations in the form of a Location[]
	 */
	public Location[] GetLocations() {
		Location[] locations = new Location[4];
		locations[0] = northLocation;
		locations[1] = eastLocation;
		locations[2] = southLocation;
		locations[3] = westLocation;
		return locations;
	}
	
	/**
	 * Automatically calculates ideal display mode for location
	 */
	public void calculateDisplayMode() {
		displayAdj = 0;
		if(northLocation != this)
			displayAdj += 16;
		if(eastLocation != this)
			displayAdj += 8;
		if(southLocation != this)
			displayAdj += 4;
		if(westLocation != this)
			displayAdj += 2;
	}
	
	/**
	 * Executed when the player visits the <code>Location</code>.
	 * @param gameInstance The current <code>GameInstance</code>
	 */
	public void onVisit(GameInstance gameInstance) { }
	
	public String travel(String dir, GameInstance gameInstance) { 
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
}
