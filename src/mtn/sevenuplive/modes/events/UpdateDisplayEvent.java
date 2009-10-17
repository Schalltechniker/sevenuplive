package mtn.sevenuplive.modes.events;

public class UpdateDisplayEvent implements Event {

	private int slot;
	
	public static final String UPDATE_DISPLAY_EVENT = "UPDATE_DISPLAY_EVENT";
		
	public UpdateDisplayEvent(){}
	
	/**
	 * Event sent to cause a view to update their display.
	 * The current slot is given as a hint to the view as to which
	 * pattern slot is active
	 * @param slot slot to update or -1 means update All
	 */
	public UpdateDisplayEvent(int slot) {
		this.slot = slot;
	}
	
	public int getSlot() {
		return this.slot;
	}

	public String getType() {
		return UPDATE_DISPLAY_EVENT;
	}
}
