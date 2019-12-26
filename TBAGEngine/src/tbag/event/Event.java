package tbag.event;

import tbag.io.GameInstance;

/**
 * Basic Event class, executed once upon arrival at an EventLocation
 * @author Trevor Skupien
 */
public class Event {
	public boolean executed = false;
	public boolean allowRepeatExecution = false;
	
	/**
	 * Executed by <code>EventLocation</code> upon visit
	 * @param gameInstance The current <code>GameInstance</code>
	 */
	public void Execute(GameInstance gameInstance) {}
	
}
