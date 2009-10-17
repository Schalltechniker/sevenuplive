package mtn.sevenuplive.modes.events;

public class UpdateNavEvent implements Event {

	private int slot;
	
	public static final String UPDATE_NAV_EVENT = "UPDATE_NAV_EVENT";
	
	public UpdateNavEvent() {}
	
	/**
	 * Event sent to cause a view to update their nav display.
	 * The current slot is given as a hint to the view as to which
	 * pattern slot is active
	 * @param slot slot to update or -1 means update All
	 */
	public UpdateNavEvent(int slot) {
		this.slot = slot;
	}
	
	public int getSlot() {
		return this.slot;
	}
	
	public String getType() {
		return UPDATE_NAV_EVENT;
	}
}
