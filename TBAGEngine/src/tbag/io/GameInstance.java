package tbag.io;

/**
 * Used as a unified type containing all Object instances necessary for Locations, Events, and commands
 * @author Trevor Skupien
 */
public class GameInstance {
	public Player player;
	public Terminal terminal;
	public Commands commands;
	public Window window;
	
	public GameInstance(Player player, Terminal terminal, Commands commands, Window window) {
		this.player = player;
		this.terminal = terminal;
		this.commands = commands;
		this.window = window;
	}
}
