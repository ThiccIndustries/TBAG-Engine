package tbag.management;

/**
 * Basic Item class
 * @author Trevor Skupien
 */
public class Item {
	public String name;
	public String desc;
	public int worth;
	public int weight;
	
	/**
	 * Primary Constructor
	 * @param name The name of the Item
	 * @param desc The description of the Item
	 */
	public Item(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}
	
	/**
	 * Full Constructor
	 * @param name The name of the Item
	 * @param desc The description of the Item
	 * @param worth The worth of the item (unimplemented)
	 * @param weight The weight of the item (unimplemented)
	 */
	public Item(String name, String desc, int worth, int weight) {
		this.name = name;
		this.desc = desc;
		this.worth = worth;
		this.weight = weight;
	}
}
