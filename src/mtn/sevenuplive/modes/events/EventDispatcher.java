package mtn.sevenuplive.modes.events;

public interface EventDispatcher {

	/** 
	 * Subscribe to events 
	 * @param event Prototype of the event we are subscribing to
	 * @param target that is listening for the event 
	 */
	public void subscribe(Event event, EventListener target);
	
	/** 
	 * Unsubscribe to events 
	 * @param event Prototype of the event we are unsubscribing from
	 * @param target that is listening for the event 
	 */
	public void unsubscribe(Event event, EventListener target);

	/**
	 * Send the event to the all listeners
	 * @param event
	 */
	public void sendEvent(Event event);
}
