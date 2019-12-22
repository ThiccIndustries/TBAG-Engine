package tbag.management;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the players inventory in the form of two <code>Lists</code> one containing the <code>Items</code> and the other containing quantities.
 * @author Trevor Skupien
 */
public class Inventory {
	public List<Item> inventoryItems = new ArrayList<Item>();
	public List<Integer> inventoryQuantities = new ArrayList<Integer>();
	
	/**
	 * Attempts to remove a certain quantity of an <code>Item</code> from the player's inventory.
	 * @param itemToRemove <code>Item</code> type to remove.
	 * @param quantity How many of the <code>Item</code> to remove.
	 * @return Returns the success of the operation
	 */
	public boolean removeItem(Item itemToRemove, int quantity) {
		boolean success = false;
		for(int index = 0; index < inventoryItems.size(); index++) {
			if(inventoryItems.get(index) == itemToRemove) {
				if(inventoryQuantities.get(index) > quantity) {
					int newTotal = inventoryQuantities.get(index) - quantity;
					inventoryQuantities.set(index, newTotal);

					success = true;
				}
				if(inventoryQuantities.get(index) == quantity) {
					inventoryQuantities.remove(index);
					inventoryItems.remove(index);
					success = true;
				}
			}
		}
		return success;		
	}
	
	/**
	 * Adds a certain quantity of an <code>Item</code> to the player's inventory.
	 * @param itemToAdd <code>Item</code> type to add.
	 * @param quantity How many of the <code>Item</code> to remove.
	 */
	public void addItem(Item itemToAdd, int quantity) {
		boolean combineSuccess = false;
		for(int index = 0; index < inventoryItems.size(); index++) {
			if (inventoryItems.get(index) == itemToAdd) {
				inventoryQuantities.set(index, inventoryQuantities.get(index) + quantity);
				combineSuccess = true;
			}
		}
		if(!combineSuccess) {
			inventoryItems.add(itemToAdd);
			inventoryQuantities.add(quantity);
		}
	}
}
