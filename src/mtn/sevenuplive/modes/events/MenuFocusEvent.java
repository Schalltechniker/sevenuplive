package mtn.sevenuplive.modes.events;

public class MenuFocusEvent implements Event {
	public enum eMenuFocusEvent {MENU_FOCUS_CHANGE_CUED, MENU_FOCUS_COMMITTED, MENU_FOCUS_CHANGE_ABORTED};
	
	public eMenuFocusEvent type;
	
	public static final String MENU_FOCUS_EVENT = "MENU_FOCUS_EVENT";
	
	public int oldIndex;
	public int newIndex;
	
	public MenuFocusEvent() {}
	
	public MenuFocusEvent(eMenuFocusEvent type, int oldIndex, int newIndex) {
		this.type = type;
		this.oldIndex = oldIndex;
		this.newIndex = newIndex;
	}
	
	public String toString() {
		return "MenuFocusEvent->" + type + " oldIndex:" + Integer.toString(oldIndex) + " newIndex:" + Integer.toString(newIndex);
	}

	public String getType() {
		return MENU_FOCUS_EVENT;
	}
}