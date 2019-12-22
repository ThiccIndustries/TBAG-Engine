package tbag.io;

import tbag.location.Location;

/**
 * Contains methods for outputting to the console
 * @author Trevor Skupien
 */
public class Terminal {
	
	private Location lastLocation = null;

	/**
	 * Displays the player's current location's name and description.
	 * Depending on the locations display mode, will display the name of adjacent locations.
	 * @param gi.player The player instance used
	 */
	public void displayLocation(GameInstance gi) {
		if(lastLocation != gi.player.currentLocation) {
			StringBuilder sb = new StringBuilder();
			sb.append("\n> " + gi.player.currentLocation.dspName + " <");
			sb.append("\n" + gi.player.currentLocation.dspDesc);
			sb.append("\n");
			for(int i = 0; i < gi.player.currentLocation.dspDesc.length(); i++)
				sb.append("-");
			//display tiles according to displayAdj mode
			int tempDisp = gi.player.currentLocation.displayAdj;
			if(tempDisp >= 16) {
				sb.append("\nTo the north lies: " + gi.player.currentLocation.northLocation.dspName);
				tempDisp -= 16;
			}
			if(tempDisp >= 8) {
				sb.append("\nTo the east lies: " + gi.player.currentLocation.eastLocation.dspName);
				tempDisp -= 8;
			}
			if(tempDisp >= 4) {
				sb.append("\nTo the south lies: " + gi.player.currentLocation.southLocation.dspName);
				tempDisp -= 4;
			}
			if(tempDisp >= 2) {
				sb.append("\nTo the west lies: " + gi.player.currentLocation.westLocation.dspName);
				tempDisp -= 2;
			}
			gi.terminal.display(sb.toString(), gi);
			lastLocation = gi.player.currentLocation;
		}
	}
	
	/**
	 * Basic output command
	 * @param o Object displayed
	 * @param gi Current GameInstance
	 */
	public void display(Object o, GameInstance gi) {
		if(o != null) {
			gi.window.append("\n");
			gi.window.append(o);
		}
	}
	
	/**
	 * Outputs <code>o</code> as a red, bold error.
	 * @param o Object displayed
	 * @param gi Current GameInstance
	 */
	public void displayError(Object o, GameInstance gi) {
		if(o != null) {
			gi.window.appendError("\n");
			gi.window.appendError(o);
		}
	}
}
