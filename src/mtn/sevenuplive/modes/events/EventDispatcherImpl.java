package mtn.sevenuplive.modes.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDispatcherImpl implements EventDispatcher {

	/** HashMap key is event type, value is List of Event Listener targets */
	private Map<String, List<EventListener>> subscriberMap = new HashMap<String, List<EventListener>>();
	
	public void sendEvent(Event event) {
		List<EventListener> listeners = subscriberMap.get(event.getType());
		if (listeners != null) {
			for (EventListener listener : listeners) {
				listener.onEvent(event);
			}
		}
	}

	public void subscribe(Event event, EventListener target) {
		List<EventListener> listeners = subscriberMap.get(event.getType());
		if (listeners == null) {
			listeners = new ArrayList<EventListener>();
			listeners.add(target);
		} else {
			boolean exists = false;
			for (EventListener listener : listeners) {
				if (listener == target) {
					exists = true;
					break;
				}
			}	
			if (!exists) {
				listeners.add(target);
			}
		}
		subscriberMap.put(event.getType(), listeners);
	}

	public void unsubscribe(Event event, EventListener target) {
		List<EventListener> listeners = subscriberMap.get(event.getType());
		if (listeners == null) {
			return; // nothing to do
		} else {
			boolean exists = false;
			for (EventListener listener : listeners) {
				if (listener == target) {
					exists = true;
					break;
				}
			}	
			if (exists) {
				listeners.remove(target);
			}
		}
	}

}
