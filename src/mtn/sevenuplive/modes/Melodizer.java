package mtn.sevenuplive.modes;
import java.util.*;

import mtn.sevenuplive.main.*;
import mtn.sevenuplive.scales.*;
import proxml.*;
import promidi.*;

import org.jdom.*;

/**
 * @author Adam Ribaudo
 * Melodizer Class.
 * 
 * Contains the logic involved in recording and playing back sequences of melodies for a MonomeUp.
 */
public class Melodizer extends Mode {
	
	public int key[];
	public Hashtable <Integer, NoteSequence> sequences;
	private int cuedIndex;
	private mtn.sevenuplive.main.MonomeUp m;
	private boolean isRecording;
	private int curSeqBank;
	private int displayNote[]; //Array of ints holding [pitch] of notes being played back in a sequence
	private int clipNotes[][]; //When clipMode=true.  Array of ints holding [channel][pitch] of clips being launched/stopped
	private boolean heldNote[];
	private boolean newHeldNote[];
	private MidiOut midiMelodyOut[];
	private Scale melodyScale;
	private int recMode = MonomeUp.melOnButtonPress;
	public Boolean clipMode = false;

	public Melodizer(int _navRow, MidiOut _midiMelodyOut[], mtn.sevenuplive.main.MonomeUp _m){
		super(_navRow);
		midiMelodyOut = _midiMelodyOut;
		m = _m;
		key = new int[7];
		cuedIndex = -1;
		sequences = new Hashtable<Integer, NoteSequence>();
		 
		curSeqBank = 0;
	    heldNote = new boolean[128];
	    displayNote = new int[128];
	    clipNotes = new int[6][128];
	    newHeldNote = new boolean[128];
	    melodyScale = new Scale(ScaleName.Major);
	    
	    updateNavGrid();
	    updateDisplayGrid();
	}
	
	public void updateDisplayGrid()
	{
		//System.out.println("Update melodizer display grid");
		clearDisplayGrid();
		
		if(!clipMode)
			{
			//Show keys
			displayGrid[0][7] = fastBlink; // C
			displayGrid[1][6] = fastBlink; // C#
			displayGrid[1][7] = fastBlink; // D
			displayGrid[2][6] = fastBlink; // D#
			displayGrid[2][7] = fastBlink; // E
			displayGrid[3][7] = fastBlink; // F
			displayGrid[4][6] = fastBlink; // F#
			displayGrid[4][7] = fastBlink; // G
			displayGrid[5][6] = fastBlink; // G#
			displayGrid[5][7] = fastBlink; // A
			displayGrid[6][6] = fastBlink; // A#
			displayGrid[6][7] = fastBlink; // B	
			
			//Set current key
			switch(key[curSeqBank])
			{
				case 0: displayGrid[0][7] = solid; // C
				break;
				case 1: displayGrid[1][6] = solid; // C#
				break;
				case 2: displayGrid[1][7] = solid; // D
				break;
				case 3: displayGrid[2][6] = solid; // D#
				break;
				case 4: displayGrid[2][7] = solid; // E
				break;
				case 5: displayGrid[3][7] = solid; // F
				break;
				case 6: displayGrid[4][6] = solid; // F#
				break;
				case 7: displayGrid[4][7] = solid; // G
				break;
				case 8: displayGrid[5][6] = solid; // G#
				break;
				case 9: displayGrid[5][7] = solid; // A
				break;
				case 10: displayGrid[6][6] = solid; // A#
				break;
				case 11: displayGrid[6][7] = solid; // B	
				break;
			}
		}
			if(!clipMode)
			{
				//Light this note if not in clipMode
				//Loop through entire grid.  Find each note value. Light it if it's held
				int noteStatus;
				for(int j=0;j<7;j++)
				{
					for(int k=0;k<6;k++)
					{
						noteStatus = displayNote[(((8-k) * 12 - 12) + melodyScale.Degrees[j] + key[curSeqBank])];
						if(noteStatus != MonomeUp.off)
							displayGrid[j][k] = noteStatus;
					}
				}	
			}
			else
			{
				//Display clip status based on incoming MIDI notes from Live
				int noteStatus;
				for(int j=0;j<7;j++)
				{
					for(int k=0;k<8;k++)
					{
						noteStatus = clipNotes[curSeqBank][(((8-k) * 12 - 12) + melodyScale.Degrees[j] + key[curSeqBank])];
						if(noteStatus != MonomeUp.off)
							displayGrid[j][k] = noteStatus;
					}
				}	
			}
	}
	
	public void updateNavGrid()
	{
		clearNavGrid();
		
		navGrid[myNavRow] = solid;

		if(isRecording)
			navGrid[getYCoordFromSubMenu(curSeqBank)] = slowBlink;
		else
			navGrid[getYCoordFromSubMenu(curSeqBank)] = fastBlink;
	}
	
	public void press(int x, int y)
	{
	  
		if(x == navCol)
		{
			pressNavCol(y);
			updateNavGrid();
		}
		else
			pressDisplay(x,y);

		updateDisplayGrid();
	}
	
	public void release(int x, int y)
	{
	   int melodyPitch;
	   melodyPitch = ((8-y) * 12 - 12) + melodyScale.Degrees[x] + key[curSeqBank];
	   heldNote[melodyPitch] = false;
	   if(!clipMode)
		   displayNote[melodyPitch] = MonomeUp.off;
	   Note melodyRelease;
	   melodyRelease = new Note(melodyPitch,0, 0);
       midiMelodyOut[curSeqBank].sendNoteOff(melodyRelease);
       addEvent(melodyRelease);
       updateDisplayGrid();
	}
	
	private void pressNavCol(int y)
	{
	  int pressedSeq = getSubMenuFromYCoord(y);

	  //If they press the current bank and it is not already recording, begin cuing in that bank
	  if (pressedSeq == curSeqBank && isRecording == false)
  	  {
		//TODO send note offs only to the notes that are currently playing in this sequence
		if(getSeqStatus(pressedSeq) == NoteSequence.playing)
		{
			ArrayList<Note> noteList;
			Note note;
			noteList = sequences.get(pressedSeq).getHeldNotes();
			//Loop through heldnotes and send note off for each
			for(int i=0;i<noteList.size();i++)
			{
				note = noteList.get(i);
				midiMelodyOut[pressedSeq].sendNoteOff(note);
				if(pressedSeq == curSeqBank)
					displayNote[note.getPitch()] = MonomeUp.off;
			}
		}
	 	//Reset displayNote container
	 	displayNote = new int[128];
  		isRecording = true;
  		
		beginCue(pressedSeq);
  	  }
  	  //Stop recording
  	  else if(pressedSeq == curSeqBank && isRecording == true)
  	  {
  		  isRecording = false;
  		  endRecording();
  		 //immediately begin playing the recorded sequence
  		 playSeq(pressedSeq);
  	  }
  	  else if(pressedSeq != curSeqBank)
  	  {
  		  curSeqBank = pressedSeq;
  		  //Reset displayNote container
  		  displayNote = new int[128];
  	  }
	  
	  updateNavGrid();
	}

	private void pressDisplay(int x, int y)
	{

   	 //User is pressing a note button (as opposed to changing key)
   	 if(y < 6 || clipMode)
   	 {
	    //Calculate the pitch by the octave (y) and the transposed scale
    	 int melodyPitch;
    	 melodyPitch = ((8-y) * 12 - 12) + melodyScale.Degrees[x] + key[curSeqBank];
    	 
    	 Note melodySend;
    	 melodySend = new Note(melodyPitch,100, 0);
    	 heldNote[melodyPitch] = true;
    	 if(!clipMode)
    		 displayNote[melodyPitch] = MonomeUp.solid;
         midiMelodyOut[curSeqBank].sendNoteOn(melodySend);
         addEvent(melodySend);
   	 }
   	 //User is pressing a button in the key area
   	 else
   	 {
   		//User is changing keys
   		if(key[curSeqBank] != getKeyFromCoords(x, y))
   	    {
   	    	int curKeyValue = key[curSeqBank];
   	    	
    		//Set new key
    		key[curSeqBank] = getKeyFromCoords( x, y);
    		int keyDif = curKeyValue - key[curSeqBank];
	    		
   	    	//Release notes from old key
   	    	for(int i=0; i<128;i++)
   	    	{ 
   	    		if(heldNote[i])
   	    		{
   	    			Note melodyRelease;
   	    			melodyRelease = new Note(i,0, 0);
   	    			midiMelodyOut[curSeqBank].sendNoteOff(melodyRelease);
   	    			addEvent(melodyRelease);
   	    			heldNote[i] = false;
   	    			displayNote[i] = MonomeUp.off;
   	    			newHeldNote[i-keyDif]=true;
   	    			
   	    			//System.out.println("  Killing " + i);
   	    		}
   	    	}
   	    	
   	    	//TODO why do I need this?  Try again now with OSC
   			try
   			{
   				Thread.sleep(100);
   			}catch(Exception e)
   			{
   				
   			
   			}
   	    	
	    	//Send notes for new key
   	    	for(int i=0; i<128;i++)
   	    	{ 
   	    		if(newHeldNote[i])
   	    		{		
   	    			Note melodySend;
   	    			melodySend = new Note(i,100, 0);
   	    			midiMelodyOut[curSeqBank].sendNoteOn(melodySend);
   	    			addEvent(melodySend);
   	    			heldNote[i]=true;
   	    			displayNote[i] = MonomeUp.solid;
   	    			newHeldNote[i]=false;
   	    			//System.out.println("Sending " + i);
   	    		}
   	    	}
   	    }
   	 }
		
	}
	
	private int getKeyFromCoords(int x, int y)
	{
		//Set new key
		if(x == 0 && y == 7)return 0;
		else if(x == 1 && y == 6)return 1;
		else if(x == 1 && y == 7)return 2;
		else if(x == 2 && y == 6)return 3;
		else if(x == 2 && y == 7)return 4;
		else if(x == 3 && y == 7)return 5;
		else if(x == 4 && y == 6)return 6;
		else if(x == 4 && y == 7)return 7;
		else if(x == 5 && y == 6)return 8;
		else if(x == 5 && y == 7)return 9;
		else if(x == 6 && y == 6)return 10;
		else if(x == 6 && y == 7)return 11;
		else return 0;
	}
	
	public void heartbeat()
	{
		ArrayList<Note> noteList;
		NoteSequence s;
		Hashtable <Integer, ArrayList<Note>> notePackage = new Hashtable<Integer, ArrayList<Note>>();
		Integer index;
		
		//loop through sequences and perform heartbeat on each.  packaging up each note that is returned
		for(Enumeration els = sequences.keys();els.hasMoreElements();)
		{
			index = Integer.class.cast(els.nextElement());
			s = sequences.get(index);
			noteList = s.heartbeat();
			if(noteList != null)
			{
				//package the note
				notePackage.put(index, noteList);
			}
		}
		
		//Old monomeup heartbeat code
		 Note note;

		 //Loop through and send all notes in the notePackage
		 for(Enumeration els = notePackage.keys();els.hasMoreElements();)
			{
				index = Integer.class.cast(els.nextElement());
				noteList = notePackage.get(index);
				
				for(int i=0;i<noteList.size();i++)
				{
					note = noteList.get(i);
					if(note.getVelocity() > 0)
					{
						//System.out.println("Playing note " + note.getPitch());
						midiMelodyOut[index].sendNoteOn(note);
						if(index == curSeqBank)
							displayNote[note.getPitch()] = MonomeUp.solid;
					}
					else
					{
						//System.out.println("Releasing note " + note.getPitch());
						midiMelodyOut[index].sendNoteOff(note);
						if(index == curSeqBank)
							displayNote[note.getPitch()] = MonomeUp.off;
					}
					if(index == curSeqBank)
						updateDisplayGrid();
				}
			}
	}
	
	/**
	 * Beginning a cue creates a new sequence if one does not exist at the specified index.  Otherwise, it begins cueing on the existing sequence.
	 * 
	 * @param seqIndex
	 */
	public void beginCue(int seqIndex)
	{
		if(!sequences.containsKey(seqIndex))
		{
			sequences.put(seqIndex, new NoteSequence(seqIndex));
			setMelRecMode(recMode);
		}
		
		sequences.get(seqIndex).beginCue();
		
		cuedIndex = seqIndex;
	}
	
	public void endRecording()
	{
		sequences.get(cuedIndex).endRecording();
		cuedIndex = -1;
	}
	
	/**
	 * Sends a note to the currently cued sequence.  If no sequence is cued, the note will be ignored.
	 * 
	 * @param note
	 */
	public void addEvent(Note note)
	{
		if(cuedIndex > -1)
		{
			sequences.get(cuedIndex).addEvent(note);
		}
	}
	
	/**
	 * 
	 * @param seqIndex
	 * @return returns true if the sequence exists and has started playing and false otherwise
	 */
	public boolean playSeq(int seqIndex)
	{
		if(sequences.containsKey(seqIndex))
		{
			sequences.get(seqIndex).play();
			return true;
		}
		else return false;
	}
	
	public void stopSeq(int seqIndex)
	{
		if(sequences.containsKey(seqIndex))
		{
			sequences.get(seqIndex).stop();
		}
	}
	
	//Returns -1 if no such sequence exists.  0 if stopped. 1 if playing.
	public int getSeqStatus(int index)
	{
		if(sequences.containsKey(index))
			return sequences.get(index).getStatus();
		else return -1;
	}
	
	public Element toXMLElement(String ElementName)
	{
		
		
		
		Element xmlMelodizer = new Element(ElementName);
		
		xmlMelodizer.setAttribute(new Attribute("scale", melodyScale.Name.toString()));
		xmlMelodizer.setAttribute(new Attribute("clipMode", clipMode.toString()));
		String keyString = "";
		for(int i=0;i<key.length;i++)
		{
			keyString += key[i];
			if(i!=key.length-1)
				 keyString+= ",";
		}
			
		xmlMelodizer.setAttribute(new Attribute("key", keyString));
		
		Element xmlSequence;
		
		int seqIndex;
		NoteSequence sequence;
		
		for(Enumeration els = sequences.keys();els.hasMoreElements();)
		{
			seqIndex = Integer.class.cast(els.nextElement());
			sequence = sequences.get(seqIndex);
			xmlSequence = sequence.toJDOMXMLElement();
			xmlSequence.setAttribute(new Attribute("key", ((Integer)key[seqIndex]).toString()));
			xmlMelodizer.addContent(xmlSequence);
		}
		
		return xmlMelodizer;
	}
	
	public void loadXMLElement(Element xmlMelodizer)
	{
		//Clear current info
		cuedIndex = -1;
		sequences = new Hashtable<Integer, NoteSequence>();
		
		//Load XML	
		melodyScale = new Scale(ScaleName.valueOf(xmlMelodizer.getAttribute("scale").getValue()));
		try {
			clipMode = xmlMelodizer.getAttribute("clipMode").getBooleanValue();
		} catch (DataConversionException e) {
			clipMode = false;
			System.out.println("Error: Invalid value for clipMode attribute in XML file");
		} catch (NullPointerException e) {
			clipMode = false;
			System.out.println("Error: No clipMode attribute found in XML file");
		}finally 
		{
			try {
				String keyString = xmlMelodizer.getAttribute("key").getValue();
				int i=0;
				for(String strKey : keyString.split(","))
				{
					key[i] = Integer.parseInt(strKey);
					i++;
				}
			} catch (Exception e) {
				System.out.println("Error: No key attribute found in XML file");
			} finally
			{
				List xmlSequences;
				Iterator itrSequences;
				Element xmlSequence;
				NoteSequence sequence;
				Integer index;
				
				xmlSequences = xmlMelodizer.getChildren();
				itrSequences = xmlSequences.iterator();
				
				while(itrSequences.hasNext())
				{
					xmlSequence = (Element)itrSequences.next();
					index = Integer.parseInt(xmlSequence.getAttributeValue("index"));	
					key[index] = Integer.parseInt(xmlSequence.getAttributeValue("key"));
					sequence = new NoteSequence(index);
					setMelRecMode(recMode);
					sequence.loadJDOMXMLElement(xmlSequence);
					sequences.put(index, sequence);
				}
				
				updateDisplayGrid();
			}
		}
	}

	public void reset() {
		for(int i=0;i<7;i++)
			stopSeq(i);
	}

	public void clipLaunch(int pitch, int vel, int channel) {
			if(vel == 0)
			{
				clipNotes[channel][pitch] = MonomeUp.off;
				System.out.println("Setting " + pitch + " OFF for channel " + channel);
			}
			else if(vel == 126)
			{
				clipNotes[channel][pitch] = MonomeUp.slowBlink;
				//System.out.println("Setting " + pitch + " SLOWBLINK");
			}	
			else if(vel == 127)
			{
				//Set all other notes in this note's melodizer column to off (only 1 note in one column can be playing at a time)
				int lowestPitch = 0;
				for(int i = pitch;i>=0;i-=12)
					lowestPitch = i;
				for(int i = lowestPitch;i<=127;i+=12)
					clipNotes[channel][i] = MonomeUp.off;
				
				//Turn on the note that actually plays
				clipNotes[channel][pitch] = MonomeUp.solid;
			}
			//TODO add something for vel 1 (continue)
			if(channel == curSeqBank)
				updateDisplayGrid();
	}
	
	public Scale getScale()
	{
		return melodyScale;
	}
	
	public void setScale(Scale newScale)
	{
		melodyScale = newScale;
	}

	public void locatorEvent() {
		if(sequences.containsKey(curSeqBank))
		{
			sequences.get(curSeqBank).locatorEvent();
		}
	}

	public void setMelRecMode(int _recMode) {
		//Set all sequences to the new rec Mode
		recMode = _recMode;
		Integer sequenceIndex;
		for(Enumeration els = sequences.keys();els.hasMoreElements();)
		{
			sequenceIndex = Integer.class.cast(els.nextElement());
			sequences.get(sequenceIndex).setMelRecMode(recMode);
		}
		
		
	}

	public void extNoteOn(Note note, int channel) {
		
		//System.out.println("Ext note received channel " + channel);
		//Convert the external instrument's channel to the melodizer's channel
		int melodizerChannel = channel - 8;
		//144 = noteOn
		if(note.getVelocity() > 0 && note.getStatus() == 144)
		{
			heldNote[note.getPitch()] = true;
			midiMelodyOut[melodizerChannel].sendNoteOn(note);
			if(melodizerChannel == curSeqBank)
				displayNote[note.getPitch()] = MonomeUp.solid;
			addEvent(note);
		}
		else
		{
			Note releaseNote = new Note(note.getPitch(),0, 0);
			midiMelodyOut[melodizerChannel].sendNoteOff(releaseNote);
			heldNote[note.getPitch()] = false;
			if(melodizerChannel == curSeqBank)
				displayNote[note.getPitch()] = MonomeUp.off;
			addEvent(releaseNote);
		}
   	 	
        updateDisplayGrid();
	}

}
