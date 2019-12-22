package tbag.event;

import tbag.io.GameInstance;

/**
 * Event type that sends a <code>String</code> message to the given <code>Terminal</code>
 * @author Trevor Skupien
 */
public class MessageEvent extends Event{
	
	/**
	 * The Message sent to the <code>Terminal</code>
	 */
	public String message = "";
	
	/**
	 * @param message <code>String</code> used as the message.
	 * @param allowRepeatExecution Allow the <code>Event</code> to be executed multiple times
	 */
	public MessageEvent(String message, boolean allowRepeatExecution) {
		this.message = message;
		this.allowRepeatExecution = allowRepeatExecution;
	}
	
	/**
	 * Sends the message if it hasn't already been executed
	 * @param gameInstance <code>GameInstance</code> which contains the <code>Terminal</code>
	 */
	@Override
	public void Execute(GameInstance gameInstance) {
		if(!executed || allowRepeatExecution) {
			gameInstance.terminal.display(message, gameInstance);
			executed = true;
		}
	}
}
