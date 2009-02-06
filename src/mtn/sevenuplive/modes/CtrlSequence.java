package mtn.sevenuplive.modes;

import java.util.*;

import mtn.sevenuplive.main.MonomeUp;

import org.jdom.*;

public class CtrlSequence {
	
	private int counter;
	private int length;
	private int index;
	private int status;
	private boolean doQuantize = false;
	
	//Hashtable of keys (metronome count) and Integer loop positions
	private Hashtable<Integer, Integer> events;

	CtrlSequence(int _index){
		initialize();
		index = _index;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public boolean hasEvents()
	{
		return !events.isEmpty();
	}
	
	private void initialize()
	{
		status = MonomeUp.STOPPED;
		events = new Hashtable<Integer, Integer>();	
	}
	
	public void beginCue()
	{
		initialize();
		status = MonomeUp.CUED;
	}
	
	public void endRecording()
	{
		if(doQuantize)
		{
			//Quantizing! To 1/8th note
			int modCount = (counter - 1) % 4;
			int quantizedCount;
			
			//If the modCount is 0, 1, or 2 then the event was late and should be pushed back to the previous count % 4
			if (modCount <= 2)
			{
				quantizedCount = counter - modCount;
			}
			//Else if the modCount is 3 then the hit was early and should be positioned to the next counter
			else
			{
				quantizedCount = counter + 1;
			}
			
			//Remove one because it ends at the end of the last bar, not the beginning of the next
			quantizedCount--;
			length = quantizedCount;
		}
		else
			length = counter - 1;
		
		status = MonomeUp.STOPPED;
		//System.out.println("Sequence - " + index + " length = " + length);
	}
	
	public Integer heartbeat()
	{
		//account for recording never stopping
		if(counter == Integer.MAX_VALUE - 1)
		{
			endRecording();
			return null;
		}
		
		//Advance counter if sequence is playing or recording
		if(status == MonomeUp.PLAYING || status == MonomeUp.RECORDING)
		{
			counter ++;
			//System.out.println("Sequence - " + _index + ": count = " + counter);
		}
		
		//Send Integer if isPlaying and there is an event at the current count
		if(status == MonomeUp.PLAYING)
		{
			//Reset counter to beginning if it reaches the length
			if(counter > length)
			{
				counter = 1;
			}

			if(events.containsKey(counter))
			{
				//System.out.println("Sequence - " + index + " sending ctrl " + events.get(counter) + " at " + counter);
				return events.get(counter);
			}
			else return null;
		}
		
		return null;
	}
	
	public void startRecording()
	{
		//Start recording even if no ctrlValue was sent. Use -1
		status = MonomeUp.RECORDING;
		counter = 1;
		events.put(counter, -1);
	}
	
	public void addEvent(Integer ctrlValue)
	{
		if(status == MonomeUp.CUED)
		{
			status = MonomeUp.RECORDING;
			counter = 1;
			events.put(counter, ctrlValue);
			//System.out.println("Sequencer " + index + " - Adding ctrlValue " + ctrlValue + " to " + counter);
		}
		else if(status == MonomeUp.RECORDING)
		{
			if(doQuantize)
			{
				//Quantizing!
				int modCount = (counter - 1) % 4;
				int quantizedCount;
				
				//If the modCount is 0, 1, or 2 then the event was late and should be pushed back to the previous count % 4
				if (modCount <= 2)
				{
					quantizedCount = counter - modCount;
				}
				//Else if the modCount is 3 then the hit was early and should be positioned to the next counter
				else
				{
					quantizedCount = counter + 1;
				}
				
				events.put(quantizedCount, ctrlValue);
			}
			else
				events.put(counter, ctrlValue);
		}
	}
	
	public void play()
	{
		status = MonomeUp.PLAYING;
		counter = 0;
	}
	
	public void stop()
	{
		status = MonomeUp.STOPPED;
	}
	
	public int getStatus()
	{
		return status;
	}
	

	public Element toJDOMXMLElement()
	{
		Element xmlSequence = new Element("ctrlSequence");
		
		Element xmlEvent;
		
		Integer eventIndex;
		Integer ctrlValue;

		xmlSequence.setAttribute(new Attribute("length", ((Integer)length).toString()));
		xmlSequence.setAttribute(new Attribute("index", ((Integer)index).toString()));

		for(Enumeration<Integer> els = events.keys();els.hasMoreElements();)
		{
			xmlEvent = new Element("event");
			eventIndex = Integer.class.cast(els.nextElement());
			xmlEvent.setAttribute(new Attribute("index", eventIndex.toString()));
			
			ctrlValue = events.get(eventIndex);
			xmlEvent.setAttribute(new Attribute("ctrlValue", ctrlValue.toString()));
			
			xmlSequence.addContent(xmlEvent);
		}
		
		return xmlSequence;
	}
	
	@SuppressWarnings("unchecked")
	public void loadJDOMXMLElement(Element xmlSequence)
	{
		initialize();
		
		length = Integer.parseInt(xmlSequence.getAttributeValue("length"));
		
		List<Element> xmlEvents;
		Integer ctrlValue;
		Integer index;
		
		xmlEvents = xmlSequence.getChildren();
		
		for (Element xmlEvent :  xmlEvents)
		{
			ctrlValue = Integer.parseInt(xmlEvent.getAttributeValue("ctrlValue"));
			index = Integer.parseInt(xmlEvent.getAttributeValue("index"));
			events.put(index, ctrlValue);
		}
	}
	
	
}
