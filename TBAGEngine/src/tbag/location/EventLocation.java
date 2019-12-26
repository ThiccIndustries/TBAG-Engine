package tbag.location;

import tbag.event.Event;
import tbag.io.GameInstance;

/**
 * Location that executes an <code>Event</code> when visited for the first time. <code>Event</code> must be reactivated externally to occur again.
 * @author Trevor Skupien
 */
public class EventLocation extends Location{
	/**
	 * <code>Event</code> executed by <code>onVisit()</code>
	 */
	public Event event;
	
	/**
	 * Use this to manually specify a display mode on external locations
	 * @param name The display name of the location
	 * @param desc The description of the location
	 * @param displayMode the display mode used by the location
	 * @param event Event executed by the location
	 */
	public EventLocation(String name, String desc, Event event, int displayMode) { 
		super(name,desc,displayMode);
		this.event = event;
	}
	
	/**
	 * Use this to declare external locations
	 * @param name The display name of the location
	 * @param desc The description of the location
	 * @param event Event executed by the location
	 */
	public EventLocation(String name, String desc, Event event) { 
		super(name,desc);
		this.event = event;
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
	 * @param event Event executed by the location
	 */
	public EventLocation(String name, String desc, Location northTravel, Location eastTravel, Location southTravel, Location westTravel, int displayMode, Event event) {
		super(name,desc,northTravel,eastTravel,southTravel,westTravel,displayMode);
		this.event = event;
	}
	
	/**
	 * Use this to declare internal locations with a manual display mode
	 * @param name The display name of the location
	 * @param desc The description of the location
	 * @param northTravel The location object to the north
	 * @param eastTravel The location object to the east
	 * @param southTravel The location object to the south
	 * @param westTravel The location object to the west
	 * @param event Event executed by the location
	 */
	public EventLocation(String name, String desc, Location northTravel, Location eastTravel, Location southTravel, Location westTravel, Event event) {
		super(name,desc,northTravel,eastTravel,southTravel,westTravel);
		this.event = event;
	}
	
	/**
	 * Executed when the player visits the <code>Location</code>.
	 */
	@Override
	public void onVisit(GameInstance gameInstance) {
		event.Execute(gameInstance);
	}
}
