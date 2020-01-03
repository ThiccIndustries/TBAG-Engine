package tbag.location;

import tbag.io.GameInstance;
import tbag.management.Item;

public class ItemRestrictedLocation extends Location{
	public Item itemRequired = null;
	public String reason = "to open the door.";
	/**
	 * Same format as <code>Display Mode</code>
	 */
	public int restrictedTravelDir = 0;
	
	/**
	 * Use this to manually specify a display mode on external locations
	 * @param name The display name of the location
	 * @param desc The description of the location
	 * @param restrictedTravelDir The directions that require the <code>Item</code> to continue. Uses same format as <code>DisplayMode</code>
	 * @param itemRequired The Item required to travel in the restricted directions.
	 * @param displayMode the display mode used by the location
	 */
	public ItemRestrictedLocation(String name, String desc, int restrictedTravelDir, Item itemRequired, int displayMode) { 
		super(name,desc,displayMode);
		this.restrictedTravelDir = restrictedTravelDir;
		this.itemRequired = itemRequired;
	}
	
	/*
	 * Use this to declare external locations
	 * @param name The display name of the location
	 * @param desc The description of the location
	 */
	public ItemRestrictedLocation(String name, String desc) { 
		super(name,desc);
	}
	
	/**
	 * Use this to declare internal locations with a manual display mode
	 * @param name The display name of the location
	 * @param desc The description of the location
	 * @param northTravel The location object to the north
	 * @param eastTravel The location object to the east
	 * @param southTravel The location object to the south
	 * @param westTravel The location object to the west
	 * @param restrictedTravelDir The directions that require the <code>Item</code> to continue. Uses same format as <code>DisplayMode</code>
	 * @param itemRequired The Item required to travel in the restricted directions.
	 * @param displayMode the display mode used by the location
	 */
	public ItemRestrictedLocation(String name, String desc, Location northTravel, Location eastTravel, Location southTravel, Location westTravel, int restrictedTravelDir, Item itemRequired, int displayMode) {
		super(name,desc,northTravel,eastTravel,southTravel,westTravel,displayMode);
		this.restrictedTravelDir = restrictedTravelDir;
		this.itemRequired = itemRequired;
	}
	
	/**
	 * Use this to declare internal locations with a manual display mode
	 * @param name The display name of the location
	 * @param desc The description of the location
	 * @param northTravel The location object to the north
	 * @param eastTravel The location object to the east
	 * @param southTravel The location object to the south
	 * @param westTravel The location object to the west
	 * @param restrictedTravelDir The directions that require the <code>Item</code> to continue. Uses same format as <code>DisplayMode</code>
	 * @param itemRequired The Item required to travel in the restricted directions.
	 */
	public ItemRestrictedLocation(String name, String desc, Location northTravel, Location eastTravel, Location southTravel, Location westTravel, int restrictedTravelDir, Item itemRequired) {
		super(name,desc,northTravel,eastTravel,southTravel,westTravel);
		this.restrictedTravelDir = restrictedTravelDir;
		this.itemRequired = itemRequired;
	}

	@Override
	public String travel(String dir, GameInstance gameInstance) {
		int restrictedTravelDirTemp = restrictedTravelDir;
		boolean restrictNorth = false;
		boolean restrictEast = false;
		boolean restrictSouth = false;
		boolean restrictWest = false;
		
		if(restrictedTravelDirTemp >= 16) {
			restrictNorth = true;
			restrictedTravelDirTemp -= 16;
		}
		
		if(restrictedTravelDirTemp >= 8) {
			restrictEast = true;
			restrictedTravelDirTemp -= 8;
		}
		
		if(restrictedTravelDirTemp >= 4) {
			restrictSouth = true;
			restrictedTravelDirTemp -= 4;
		}
		
		if(restrictedTravelDirTemp >= 2) {
			restrictWest = true;
			restrictedTravelDirTemp -= 2;
		}
		
		if(dir.equals("north") && (!restrictNorth || gameInstance.player.inv.inventoryItems.contains(itemRequired))) {
			gameInstance.player.currentLocation = this.northLocation;
		}else if(dir.equals("east") && (!restrictEast || gameInstance.player.inv.inventoryItems.contains(itemRequired))) {
			gameInstance.player.currentLocation = this.eastLocation;
		}else if(dir.equals("south") && (!restrictSouth || gameInstance.player.inv.inventoryItems.contains(itemRequired))) {
			gameInstance.player.currentLocation = this.southLocation;
		}else if(dir.equals("west") && (!restrictWest || gameInstance.player.inv.inventoryItems.contains(itemRequired))) {
			gameInstance.player.currentLocation = this.westLocation;
		}else{ return("You need a(n) " + itemRequired.name + " " + reason); }
		return null;
	}
}
