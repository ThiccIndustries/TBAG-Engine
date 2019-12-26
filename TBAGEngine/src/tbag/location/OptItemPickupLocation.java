package tbag.location;

import tbag.io.GameInstance;
import tbag.management.Item;

public class OptItemPickupLocation extends Location{
	
	public Item itemPickup;
	public String itemLocation = "on the ground.";
	private boolean recieved = false;
	
	/**
	 * Use this to manually specify a display mode on external locations
	 * @param name The display name of the location
	 * @param desc The description of the location
	 * @param itemPickup The <code>Item</code> that the player picks up
	 * @param itemLocation String that describes the item's location.
	 * @param displayMode the display mode used by the location
	 */
	public OptItemPickupLocation(String name, String desc, Item itemPickup, String itemLocation,int displayMode) { 
		super(name,desc,displayMode);
		this.itemPickup = itemPickup;
		this.itemLocation = itemLocation;
	}
	
	/**
	 * Use this to declare external locations
	 * @param name The display name of the location
	 * @param desc The description of the location
	 */
	public OptItemPickupLocation(String name, String desc) { 
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
	 * @param itemPickup The <code>Item</code> that the player picks up
	 * @param itemLocation String that describes the item's location.
	 * @param displayMode the display mode used by the location
	 */
	public OptItemPickupLocation(String name, String desc, Location northTravel, Location eastTravel, Location southTravel, Location westTravel, Item itemPickup, String itemLocation, int displayMode) {
		super(name,desc,northTravel,eastTravel,southTravel,westTravel,displayMode);
		this.itemPickup = itemPickup;
		this.itemLocation = itemLocation;
	}
	
	/**
	 * Use this to declare internal locations with a manual display mode
	 * @param name The display name of the location
	 * @param desc The description of the location
	 * @param northTravel The location object to the north
	 * @param eastTravel The location object to the east
	 * @param southTravel The location object to the south
	 * @param westTravel The location object to the west
	 * @param itemPickup The <code>Item</code> that the player picks up
	 * @param itemLocation String that describes the item's location.
	 */
	public OptItemPickupLocation(String name, String desc, Location northTravel, Location eastTravel, Location southTravel, Location westTravel, Item itemPickup, String itemLocation) {
		super(name,desc,northTravel,eastTravel,southTravel,westTravel);
		this.itemPickup = itemPickup;
		this.itemLocation = itemLocation;
	}
	
	@Override
	/**
	 * Executed when the player visits the <code>Location</code>.
	 * @param gameInstance The current <code>GameInstance</code>
	 */
	public void onVisit(GameInstance gi) {
		if(!recieved)
			gi.terminal.display("There is a(n) " + itemPickup.name + " " + itemLocation, gi);
	}
	
	/**
	 * executed by the 'pickup' <code>Command</code>
	 * @param name Name of the Item that the player is attempting to pick up
	 * @param gameInstance The current Game Instance
	 * @return returns a formatted <code>String</code>
	 */
	public String pickupItem(String name, GameInstance gameInstance) {
		if(!recieved) {
			if(name.toLowerCase().equals(itemPickup.name.toLowerCase())) {
				gameInstance.player.inv.addItem(itemPickup, 1);
				recieved = true;
				return("You've picked up a(n) " + itemPickup.name + " " + itemLocation);
			}else {
				return("I dont see a(n) " + name.toLowerCase() + " here.");
			}
		}
		return("There isn't anything to pickup.");
	}
}
