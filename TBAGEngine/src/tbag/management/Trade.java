package tbag.management;

/**
 * Basic class that contains a purchasable <code>Item</code>, with an associated cost.
 * @author Trevor Skupien
 *
 */
public class Trade {
	public Item itemAvailable;
	public int cost;
	
	/**
	 * Primary Constructor
	 * @param itemAvailable The <code>Item</code> type available for purchase.
	 * @param cost The cost required to purchace one of the <code>Item</code>
	 */
	public Trade(Item itemAvailable, int cost) {
		this.itemAvailable = itemAvailable;
		this.cost = cost;
	}
}
