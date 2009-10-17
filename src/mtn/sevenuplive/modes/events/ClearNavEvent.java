package mtn.sevenuplive.modes.events;


public class ClearNavEvent implements Event {

	public static final String CLEAR_NAV_EVENT = "CLEAR_NAV_EVENT";
		
	public ClearNavEvent() {
	}
	
	public String getType() {
		return CLEAR_NAV_EVENT;
	}
}
