package tbag.event;

import tbag.io.GameInstance;
import tbag.management.Item;

public class ItemPickupEvent extends Event{
	public Item itemToRecieve;
	public String itemLocation = "of the ground.";

	public ItemPickupEvent(Item itemToRecieve, String itemLocation) {
		this.itemLocation = itemLocation;
		this.itemToRecieve = itemToRecieve;
	}
	
	@Override
	public void Execute(GameInstance gameInstance) {
		if(!executed || allowRepeatExecution) {
			gameInstance.player.inv.addItem(itemToRecieve, 1);
			gameInstance.terminal.display("You've picked up a(n) " + itemToRecieve.name + " " + itemLocation, gameInstance);
			executed = true;
		}
	}
}
