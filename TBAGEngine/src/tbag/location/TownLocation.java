package tbag.location;

import tbag.io.GameInstance;
import tbag.management.Shop;

public class TownLocation extends Location{
	public String welcomeMessage;
	public Shop shop;
	/**
	 * Use this to manually specify a display mode on external locations
	 * @param name The display name of the location
	 * @param desc The description of the location
	 * @param displayMode the display mode used by the location
	 */
	public TownLocation(String name, String desc, int displayMode) { 
		super(name,desc,displayMode);
	}
	
	/**
	 * Use this to declare external locations
	 * @param name The display name of the location
	 * @param desc The description of the location
	 */
	public TownLocation(String name, String desc) { 
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
	 * @param displayMode the display mode used by the location
	 */
	public TownLocation(String name, String desc, Location northTravel, Location eastTravel, Location southTravel, Location westTravel, int displayMode) {
		super(name,desc,northTravel,eastTravel,southTravel,westTravel,displayMode);
	}
	
	/**
	 * Use this to declare internal locations with a manual display mode
	 * @param name The display name of the location
	 * @param desc The description of the location
	 * @param northTravel The location object to the north
	 * @param eastTravel The location object to the east
	 * @param southTravel The location object to the south
	 * @param westTravel The location object to the west
	 */
	public TownLocation(String name, String desc, Location northTravel, Location eastTravel, Location southTravel, Location westTravel) {
		super(name,desc,northTravel,eastTravel,southTravel,westTravel);
	}
	
	@Override
	public void onVisit(GameInstance gi) {
		gi.terminal.display(welcomeMessage, gi);
	}
}
