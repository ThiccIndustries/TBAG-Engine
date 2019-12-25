package tbag.location;

import tbag.io.GameInstance;
import tbag.management.Item;

/**
 * Location which gives the player an item when they visit the location for the first time.
 * @author Trevor Skupien
 */
public class ItemPickupLocation extends Location{
	
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
	public ItemPickupLocation(String name, String desc, Item itemPickup, String itemLocation,int displayMode) { 
		super(name,desc,displayMode);
		this.itemPickup = itemPickup;
		this.itemLocation = itemLocation;
	}
	
	/**
	 * Use this to declare external locations
	 * @param name The display name of the location
	 * @param desc The description of the location
	 */
	public ItemPickupLocation(String name, String desc) { 
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
	public ItemPickupLocation(String name, String desc, Location northTravel, Location eastTravel, Location southTravel, Location westTravel, Item itemPickup, String itemLocation, int displayMode) {
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
	public ItemPickupLocation(String name, String desc, Location northTravel, Location eastTravel, Location southTravel, Location westTravel, Item itemPickup, String itemLocation) {
		super(name,desc,northTravel,eastTravel,southTravel,westTravel);
		this.itemPickup = itemPickup;
		this.itemLocation = itemLocation;
	}

	/**
	 * Executed when the player visits the <code>Location</code>.
	 */
	@Override
	public void onVisit(GameInstance gameInstance) {
		if(!recieved) {
			gameInstance.player.inv.addItem(itemPickup, 1);
			gameInstance.terminal.display("You've picked up a(n) " + itemPickup.name + " " + itemLocation, gameInstance);
			recieved = true;
		}
	}
}
