package mtn.sevenuplive.modes;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import mtn.sevenuplive.main.MonomeUp;

import org.jdom.Attribute;
import org.jdom.Element;

import promidi.Note;

/***
 * A sequence of notes that may be recorded live and played back.  
 * This object keeps track of the current record/play position and which notes are attached to each position.  
 * Note information is stored in a hashtable which maintains the event position and an array of notes that were recorded.
 * @author Adam Ribaudo
 *
 */
public class NoteSequence {
	
	private Integer counter;  //Event position
	private Integer length;  //Length of the entire sequence
	private Integer index;	//Sequence index out of all possible NoteSequences for printout in XML
	private int status;  //Current status of the sequence
	private Hashtable <Integer, Note> heldNotesPlaying;  //Hashtable keeping track of currently held notes
	
	//Create hashtable of keys (metronome count) and ArrayList<Note>(notes played at that event position)
	private Hashtable<Integer, ArrayList<Note>> events;
	private ArrayList<Integer> notesOn;
	private int recMode = ModeConstants.MEL_ON_BUTTON_PRESS;
	
	NoteSequence(int _index){
		initialize();
		index = _index;
		counter = 0;
		heldNotesPlaying = new Hashtable<Integer, Note>();
	}
	
	private void initialize()
	{
		status = MonomeUp.EMPTY;
		counter = 0;
		events = new Hashtable<Integer, ArrayList<Note>>();	
		notesOn = new ArrayList<Integer>();
	}
	
	public void beginCue()
	{
		initialize();
		status = MonomeUp.CUED;
	}
	
	/***
	 * Called when the noteSequence is told to stop recording.  Depending on the recMode, recording may not actually stop until the next quantize point
	 */
	public void endRecording()
	{
		if(recMode == ModeConstants.MEL_QUANTIZED)
			//Wait for a locatorEvent before actually ending the recording
			status = MonomeUp.CUEDSTOP;
		else
			endAllRecording();
	}
	
	/***
	 * Called when recording is to stop after the quantize point as been reached.
	 */
	private void endAllRecording() {
		//Quantizing to 1/8th note
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

		//Turn held notes off at the end of the recording
		ArrayList<Note> notesOnSend = new ArrayList<Note>();
		
		for(int i=0;i<notesOn.size();i++)
		{
			notesOnSend.add(new Note(notesOn.get(i), 0, 0));
		}
		
		//Check to see if there are any events AFTER the length and add them to the final event
		int eventIndex;
		for(Enumeration<Integer> els = events.keys();els.hasMoreElements();)
		{
			eventIndex = Integer.class.cast(els.nextElement());
			if(eventIndex > length)
				for(int i=0;i<events.get(eventIndex).size();i++)
					notesOnSend.add(events.get(eventIndex).get(i));
		}
		
		if(!isEmpty())events.put(length, notesOnSend);
		status = MonomeUp.STOPPED;
	}

	/**
	 * Called by the intiating class in order to cycle through the sequence events and return any notes to be played
	 * @return An ArrayList to be played at the current count.  If no events, returns null.
	 */
	public ArrayList<Note> heartbeat()
	{
		//account for recording never stopping
		if(counter == Integer.MAX_VALUE - 1)
		{
			endRecording();
			return null;
		}
		
		//Advance counter if sequence is playing or recording
		if(status == MonomeUp.PLAYING || status == MonomeUp.RECORDING || status == MonomeUp.CUEDSTOP)
			counter ++;
		
		//Send note if isPlaying and there is an event at the current count
		if(status == MonomeUp.PLAYING)
		{
			//Reset counter to beginning if it reaches the length
			if(counter > length)
			{
				counter = 1;
			}

			if(events.containsKey(counter))
			{
				//Keep track of which notes are playing
				ArrayList<Note> noteList;
				noteList = events.get(counter);
				for(int i=0;i<noteList.size();i++)
				{
					if(noteList.get(i).getVelocity() > 0)
					{
						//Add a noteOn event to heldNotes
						heldNotesPlaying.put(noteList.get(i).getPitch(), noteList.get(i));
					}
					else
					{
						//Remove a note from heldNotes
						if(heldNotesPlaying.containsKey(noteList.get(i).getPitch()))
						{
							heldNotesPlaying.remove(noteList.get(i).getPitch());
						}
					}
				}
				
				return noteList;
			}
				
			else return null;
		}
		
		return null;
	}
	
	/***
	 * Called by the initiating class to add a note event to the sequence
	 * @param note The note to be added at the current event position
	 */
	public void addEvent(Note note)
	{
		ArrayList<Note> noteList;
		
		//Ensures that the user can add events to the first beat of a quantized recording
		if(status == MonomeUp.CUED && recMode == ModeConstants.MEL_QUANTIZED)
		{
			
			noteList = new ArrayList<Note>();
			noteList.add(note);
			counter = 1;
			events.put(counter, noteList);
			notesOn.add(note.getPitch());
		}
		//Else if the recMode is on button press, begin recording immediately
		else if(status == MonomeUp.CUED && recMode == ModeConstants.MEL_ON_BUTTON_PRESS)
		{
			
			noteList = new ArrayList<Note>();
			noteList.add(note);
			
			status = MonomeUp.RECORDING;
			counter = 1;
			events.put(counter, noteList);
			notesOn.add(note.getPitch());
			//System.out.println("Sequencer " + _index + " - Adding note " + note.getPitch() + " to " + counter);
		}
		//If currently recording, just add a note to the sequence
		else if(status == MonomeUp.RECORDING)
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

			boolean found = false;
			int removeIndex = 0;
			
			if (note.getVelocity() > 0)
			{
				//Add note to list of notes that are on if it is not already in the list
				for(int i=0;i<notesOn.size();i++)
				{
					if(notesOn.get(i) == note.getPitch())
					{
						found = true;
					}
				}
				if(!found)
				{
					notesOn.add(note.getPitch());
				}
				
				//System.out.println("Quantized count is: " + quantizedCount);
				//If the event list already contains an event for this count, add the note event to the existing arraylist
				if(events.containsKey(quantizedCount))
				{
					events.get(quantizedCount).add(note);
				}
				//Otherwise add a new event and arraylist
				else
				{
					noteList = new ArrayList<Note>();
					noteList.add(note);
					events.put(quantizedCount, noteList);
				}
				//System.out.println("Sequencer " + index + " - Adding note " + note.getPitch() + " to " + quantizedCount);
			}
			else
			{
				//remove note from list of notes that are on if it is in the list
				for(int i=0;i<notesOn.size();i++)
				{
					if(notesOn.get(i) == note.getPitch())
					{
						found = true;
						removeIndex = i;
					}
				}
				
				if(found)
				{
					notesOn.remove(removeIndex);
				}
				
				
				if(notesOn.contains(note.getPitch()))
				{
					notesOn.remove(new Integer(note.getPitch()));
				}
				
				//If the event list already contains an event for this count, add the note event to the existing arraylist
				if(events.containsKey(counter))
				{
					events.get(counter).add(note);
				}
				//Otherwise add a new event and arraylist
				else
				{
					noteList = new ArrayList<Note>();
					noteList.add(note);
					events.put(counter, noteList);
				}
				//System.out.println("Sequencer " + index + " - Adding release note " + note.getPitch() + " to " + counter);
			}
		}
	}
	
	public void play()
	{
		if(!(status == MonomeUp.CUEDSTOP))
		{
			status = MonomeUp.PLAYING;
			counter = 0;
		}
	}
	
	public void stop()
	{
		status = MonomeUp.STOPPED;
	}
	
	public int getStatus()
	{
		if(isEmpty()) return MonomeUp.EMPTY;
		
		return status;
	}
	
	
	public Element toJDOMXMLElement()
	{
		Element xmlSequence = new Element("sequence");
		Element xmlEvent;
		Element xmlNote;
		
		Integer eventIndex;
		ArrayList<Note> noteList;

		xmlSequence.setAttribute(new Attribute("length", length.toString()));
		xmlSequence.setAttribute(new Attribute("index", index.toString()));

		for(Enumeration<Integer> els = events.keys();els.hasMoreElements();)
		{
			xmlEvent = new Element("event");
			eventIndex = Integer.class.cast(els.nextElement());
			xmlEvent.setAttribute(new Attribute("index", eventIndex.toString()));
			
			noteList = events.get(eventIndex);
			
			for(int i=0;i<noteList.size();i++)
			{
				xmlNote = new Element("note");
				xmlNote.setAttribute(new Attribute("pitch", ((Integer)noteList.get(i).getPitch()).toString()));
				xmlNote.setAttribute(new Attribute("velocity", ((Integer)noteList.get(i).getVelocity()).toString()));
				xmlEvent.addContent(xmlNote);
			}
			xmlSequence.addContent(xmlEvent);
		}
		
		return xmlSequence;
	}
	
	@SuppressWarnings("unchecked")
	public void loadJDOMXMLElement(Element xmlSequence)
	{
		initialize();
		
		//Load XML
		length = xmlSequence.getAttributeValue("length") == null ? length : Integer.parseInt(xmlSequence.getAttributeValue("length"));
		
		Integer eventIndex;
		List<Element> xmlEvents;
		Integer velocity;
		Integer pitch;
		List<Element> xmlNotes;
		ArrayList<Note> notes;
		Note note;
		
		xmlEvents = xmlSequence.getChildren();
		
		int outerindex = 0;
		for (Element xmlEvent: xmlEvents)
		{
			eventIndex = xmlEvent.getAttributeValue("index") == null ? outerindex : Integer.parseInt(xmlEvent.getAttributeValue("index"));
			notes = new ArrayList<Note>();
			
			xmlNotes = xmlEvent.getChildren();
			
			for(Element xmlNote : xmlNotes)
			{
				// @TODO Give a default velocity and pitch if not set
				velocity = xmlNote.getAttributeValue("velocity") == null ? new Integer(ModeConstants.NOT_SET) : Integer.parseInt(xmlNote.getAttributeValue("velocity"));
				pitch = xmlNote.getAttributeValue("pitch") == null ? new Integer(ModeConstants.NOT_SET) : Integer.parseInt(xmlNote.getAttributeValue("pitch"));
				note = new Note(pitch,velocity, 0);
				notes.add(note);
			}
			
			events.put(eventIndex, notes);
			outerindex++;
		}	
	}

	public ArrayList<Note> getHeldNotes() {
		ArrayList<Note> heldNotesArray = new ArrayList<Note>();
		Integer index;
		for(Enumeration<Integer> els = heldNotesPlaying.keys();els.hasMoreElements();)
		{
			index = Integer.class.cast(els.nextElement());
			heldNotesArray.add(heldNotesPlaying.get(index));
		}
		
		return heldNotesArray;
	}

	/***
	 * When the melodizer is in quantized recording mode, locator events tell it 
	 * when to actually start or stop recording after being cued to do so
	 */
	public void locatorEvent() {
		if(recMode == ModeConstants.MEL_QUANTIZED)
		{
			if(status == MonomeUp.CUEDSTOP)
			{
				//System.out.println("Quantized Sequence " + index + " is STOPPED");
				endAllRecording();
				status = MonomeUp.STOPPED;
				//Immediately begin playback
				play();
			}
			if(status == MonomeUp.CUED)
			{	
				status = MonomeUp.RECORDING;
				counter = 1;
				if(!events.containsKey(counter))
					events.put(counter, new ArrayList<Note>());
				//System.out.println("Quantized Sequence " + index + "is STARTED");
			}
		}
	}

	public void setMelRecMode(int _recMode) {
		recMode = _recMode;
	}
	
	public boolean isEmpty(){
		return events.size() == 0;
	}
}
