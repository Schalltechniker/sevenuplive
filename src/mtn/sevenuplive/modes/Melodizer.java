package mtn.sevenuplive.modes;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import mtn.sevenuplive.main.MonomeUp;
import mtn.sevenuplive.scales.Scale;
import mtn.sevenuplive.scales.ScaleName;

import org.jdom.Attribute;
import org.jdom.Element;

import promidi.MidiOut;
import promidi.Note;

/**
 * @author Adam Ribaudo
 * Melodizer Class.
 * 
 * Contains the logic involved in recording and playing back sequences of melodies for a MonomeUp.
 */
public class Melodizer extends Mode implements PlayContext {

	/** 
	 * This is used to record the key state when a recorded pattern
	 * is triggered. And use it in transpose mode to compute the key offset  
	 */
	public int startingKey[];
	
	// Last key before transpose
	public int lastKey[];

	/** This is currently active key for each slot */
	public int key[];

	/** This is the scale offset/position in degrees for each slot */
	public int offset[];

	/** 
	 * This is used to record the offset state when a recorded pattern
	 * is triggered. And use it in transpose mode to compute the offset  
	 */
	public int startingOffset[];

	// Last offset before transpose
	public int lastOffset[];

	public Hashtable <Integer, NoteSequence> sequences;
	private int cuedIndex;

	private boolean isRecording;

	// Should changing the key or offset transpose the sequence that is playing?
	private boolean transpose = false;
	// Tells us that a transposition index has changed
	private boolean transposeDirty = false;

	// Different display modes for Melodizer
	public enum eMelodizerMode {KEYBOARD, CLIP, NONE, POSITION};
	private eMelodizerMode currentMode = eMelodizerMode.KEYBOARD;
	private eMelodizerMode altMode = eMelodizerMode.KEYBOARD;

	private int curSeqBank;
	private int displayNote[]; //Array of ints holding [pitch] of notes being played back in a sequence
	private int clipNotes[][]; //When clipMode=true.  Array of ints holding [channel][pitch] of clips being launched/stopped
	private boolean heldNote[];
	private boolean newHeldNote[];
	private MidiOut midiMelodyOut[];
	private Scale melodyScale;
	private Scale clipScale = new Scale(ScaleName.Major);

	private int recMode = ModeConstants.MEL_ON_BUTTON_PRESS;

	public Melodizer(int _navRow, MidiOut _midiMelodyOut[], int grid_width, int grid_height) {
		super(_navRow, grid_width, grid_height);
		midiMelodyOut = _midiMelodyOut;
		key = new int[7];
		offset = new int[7];
		startingKey = new int[7];
		startingOffset = new int[7];
		lastKey = new int[7];
		lastOffset = new int[7];
		cuedIndex = -1;
		sequences = new Hashtable<Integer, NoteSequence>();
		curSeqBank = 0;
		heldNote = new boolean[128];
		displayNote = new int[128];
		clipNotes = new int[7][128];
		newHeldNote = new boolean[128];
		melodyScale = new Scale(ScaleName.Major);

		updateNavGrid();
		updateDisplayGrid();
	}

	public void updateDisplayGrid()
	{
		//System.out.println("Update melodizer display grid");
		clearDisplayGrid();

		if(currentMode == eMelodizerMode.KEYBOARD)
		{
			//Show keys
			displayGrid[0][7] = DisplayGrid.FASTBLINK; // C
			displayGrid[1][6] = DisplayGrid.FASTBLINK; // C#
			displayGrid[1][7] = DisplayGrid.FASTBLINK; // D
			displayGrid[2][6] = DisplayGrid.FASTBLINK; // D#
			displayGrid[2][7] = DisplayGrid.FASTBLINK; // E
			displayGrid[3][7] = DisplayGrid.FASTBLINK; // F
			displayGrid[4][6] = DisplayGrid.FASTBLINK; // F#
			displayGrid[4][7] = DisplayGrid.FASTBLINK; // G
			displayGrid[5][6] = DisplayGrid.FASTBLINK; // G#
			displayGrid[5][7] = DisplayGrid.FASTBLINK; // A
			displayGrid[6][6] = DisplayGrid.FASTBLINK; // A#
			displayGrid[6][7] = DisplayGrid.FASTBLINK; // B	

			//Set current key
			switch(key[curSeqBank])
			{
			case 0: displayGrid[0][7] = DisplayGrid.SOLID; // C
			break;
			case 1: displayGrid[1][6] = DisplayGrid.SOLID; // C#
			break;
			case 2: displayGrid[1][7] = DisplayGrid.SOLID; // D
			break;
			case 3: displayGrid[2][6] = DisplayGrid.SOLID; // D#
			break;
			case 4: displayGrid[2][7] = DisplayGrid.SOLID; // E
			break;
			case 5: displayGrid[3][7] = DisplayGrid.SOLID; // F
			break;
			case 6: displayGrid[4][6] = DisplayGrid.SOLID; // F#
			break;
			case 7: displayGrid[4][7] = DisplayGrid.SOLID; // G
			break;
			case 8: displayGrid[5][6] = DisplayGrid.SOLID; // G#
			break;
			case 9: displayGrid[5][7] = DisplayGrid.SOLID; // A
			break;
			case 10: displayGrid[6][6] = DisplayGrid.SOLID; // A#
			break;
			case 11: displayGrid[6][7] = DisplayGrid.SOLID; // B	
			break;
			}

		} else if (currentMode == eMelodizerMode.POSITION) {

			//Show keys
			displayGrid[0][7] = DisplayGrid.FASTBLINK; 
			displayGrid[1][7] = DisplayGrid.FASTBLINK; 
			displayGrid[2][7] = DisplayGrid.FASTBLINK; 
			displayGrid[3][7] = DisplayGrid.FASTBLINK; 
			displayGrid[4][7] = DisplayGrid.FASTBLINK; 
			displayGrid[5][7] = DisplayGrid.FASTBLINK; 
			displayGrid[6][7] = DisplayGrid.FASTBLINK; 	

			// Set current position
			switch(offset[curSeqBank])
			{
			case 0: displayGrid[0][7] = DisplayGrid.SOLID; 
			break;
			case 1: displayGrid[1][7] = DisplayGrid.SOLID; 
			break;
			case 2: displayGrid[2][7] = DisplayGrid.SOLID; 
			break;
			case 3: displayGrid[3][7] = DisplayGrid.SOLID; 
			break;
			case 4: displayGrid[4][7] = DisplayGrid.SOLID; 
			break;
			case 5: displayGrid[5][7] = DisplayGrid.SOLID;
			break;
			case 6: displayGrid[6][7] = DisplayGrid.SOLID; 
			break;
			case 7: displayGrid[7][7] = DisplayGrid.SOLID; 
			break;
			}
		}

		if(currentMode != eMelodizerMode.CLIP)
		{
			//Light this note if not in clipMode
			//Loop through entire grid.  Find each note value. Light it if it's held
			int noteStatus;
			for(int j=0;j<7;j++)
			{
				for(int k=0;k<8;k++)
				{
					noteStatus = displayNote[convertGridPositionToNote(j, k, curSeqBank)];

					if (currentMode == eMelodizerMode.KEYBOARD && k < 7) {
						if(noteStatus != DisplayGrid.OFF)
							displayGrid[j][k] = noteStatus;
					} else if (currentMode == eMelodizerMode.POSITION && k < 8) {
						if(noteStatus != DisplayGrid.OFF)
							displayGrid[j][k] = noteStatus;
					} else if (currentMode == eMelodizerMode.NONE) {
						if(noteStatus != DisplayGrid.OFF)
							displayGrid[j][k] = noteStatus;
					}
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
					noteStatus = clipNotes[curSeqBank][convertGridPositionToClipNote(j, k)];
					if(noteStatus != DisplayGrid.OFF)
						displayGrid[j][k] = noteStatus;
				}
			}	
		}
	}

	/**
	 * If note is out of midi range 1-128
	 * return 0 
	 * @param note
	 * @return
	 */
	private int clipRange(int note) {
		if (note < 0 || note > 128)
			return 0;
		else
			return note;
	}

	/**
	 * Calculate the note under a pad in the grid
	 * Taking into account the scale and the degree 
	 * offset within the scale.
	 * 
	 * @param x
	 * @param y
	 * @param sequence which sequence are we operating on
	 * @return
	 */
	private int convertGridPositionToNote(int x, int y, int sequence) {
		int note = (((8-y) * 12 - 12) + (Math.abs((x + offset[sequence]) / melodyScale.Degrees.length) * 12) + melodyScale.Degrees[((x + offset[sequence]) % melodyScale.Degrees.length)] + key[sequence]);
		
		//System.out.println("Position to Note->Grid x:" + Integer.toString(x) + " y:" + Integer.toString(y) + " note:" + Integer.toString(note));
		return clipRange(note);
	}
	
	/**
	 * Calculate the note under a pad in the grid
	 * Taking into account the scale only 
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private int convertGridPositionToNoteNoOffset(int x, int y, int key) {
		int note = (((8-y) * 12 - 12) + melodyScale.Degrees[x % melodyScale.Degrees.length] + key);
		return clipRange(note);
	}
	
	/**
	 * Calculate the note under a pad in the grid
	 * Taking into account the Context 
	 * 
	 * @param x
	 * @param y
	 * @param tc
	 * @return
	 */
	private int convertGridPositionToNoteWithContext(int x, int y, TranspositionContext tc) {
		int note = (((8-y) * 12 - 12) + (Math.abs((x + tc.localOffset) / melodyScale.Degrees.length) * 12) + melodyScale.Degrees[((x + tc.localOffset) % melodyScale.Degrees.length)] + tc.key + tc.localKeyOffset);
		return clipRange(note);
	}
	
	/**
	 * Calculate the note under a pad in the grid
	 * Taking into account it's clip launch mode 
	 * This should be C Major Scale
	 * @param x
	 * @param y
	 * @return
	 */
	private int convertGridPositionToClipNote(int x, int y) {
		int note = (((8-y) * 12 - 12) + clipScale.Degrees[x % clipScale.Degrees.length]);
		return clipRange(note);
	}
	
	/**
	 * Calculate the note under a pad in the grid
	 * Taking into account the scale and the degree 
	 * offset within the scale.
	 * 
	 * @param note 0-127
	 * @param sequence which sequence are we operating on
	 * @return First grid position, higher coordinate top/left if duplicates, null if not found
	 */
	private GridPosition convertNoteToGridPosition(int note, int sequence) {
		int gridNote;

		for(int j=0;j<7;j++)
		{
			// Range of y goes above and beyond grid so we can hit test notes that fall off the physical grid
			for(int k=-2;k<10;k++)
			{
				gridNote = convertGridPositionToNote(j, k, sequence);
				if (gridNote == note) {
					//System.out.println("Note to position-> Note:" + Integer.toString(note) + "Grid x:" + Integer.toString(j) + " y:" + Integer.toString(k));
					return new GridPosition(this.melodyScale, j, k);
				}
			}
		}	
		return null;
	}

	/**
	 * Calculate the note under a pad in the grid
	 * Taking into account the scale only 
	 * 
	 * @param note 0-127
	 * @param key
	 * @return First grid position, higher coordinate top/left if duplicates, null if not found
	 */
	private GridPosition convertNoteToGridPositionNoOffset(int note, int key) {
		int gridNote;

		for(int j=0;j<7;j++)
		{
			// Range of y goes above and beyond grid so we can hit test notes that fall off the physical grid
			for(int k=-2;k<10;k++)
			{
				gridNote = convertGridPositionToNoteNoOffset(j, k, key);
				if (gridNote == note) {
					//System.out.println("Note to position-> Note:" + Integer.toString(note) + "Grid x:" + Integer.toString(j) + " y:" + Integer.toString(k));
					return new GridPosition(this.melodyScale, j, k);
				}
			}
		}	
		return null;
	}

	/**
	 * Calculate the note under a pad in the grid
	 * Taking into account the Context 
	 * 
	 * @param note 0-127
	 * @param tc
	 * @return First grid position, higher coordinate top/left if duplicates, null if not found
	 */
	private GridPosition convertNoteToGridPositionWithContext(int note, TranspositionContext tc) {
		int gridNote;

		for(int j=0;j<7;j++)
		{
			// Range of y goes above and beyond grid so we can hit test notes that fall off the physical grid
			for(int k=-2;k<10;k++)
			{
				gridNote = convertGridPositionToNoteWithContext(j, k, tc);
				if (gridNote == note) {
					//System.out.println("Note to position-> Note:" + Integer.toString(note) + "Grid x:" + Integer.toString(j) + " y:" + Integer.toString(k));
					return new GridPosition(this.melodyScale, j, k);
				}
			}
		}	
		return null;
	}

	public void updateNavGrid()
	{
		clearNavGrid();

		navGrid[myNavRow] = DisplayGrid.SOLID;

		if(isRecording)
			navGrid[getYCoordFromSubMenu(curSeqBank)] = DisplayGrid.SLOWBLINK;
		else
			navGrid[getYCoordFromSubMenu(curSeqBank)] = DisplayGrid.FASTBLINK;
	}

	public void press(int x, int y)
	{

		if(x == DisplayGrid.NAVCOL)
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
		melodyPitch = convertGridPositionToNote(x, y, curSeqBank);
		heldNote[melodyPitch] = false;
		if(currentMode != eMelodizerMode.CLIP)
			displayNote[melodyPitch] = DisplayGrid.OFF;
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
			if(getSeqStatus(pressedSeq) == MonomeUp.PLAYING)
			{
				ArrayList<Note> noteList;
				Note note;
				noteList = sequences.get(pressedSeq).getHeldNotesTransposedPitch();
				//Loop through heldnotes and send note off for each
				for(int i=0;i<noteList.size();i++)
				{
					note = noteList.get(i);
					midiMelodyOut[pressedSeq].sendNoteOff(note);
					if(pressedSeq == curSeqBank)
						displayNote[note.getPitch()] = DisplayGrid.OFF;
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

	/**
	 * Determines if the row represents a note row or not
	 * @param y
	 * @return 
	 */
	public boolean isNote(int y) {
		return (y < 6 && currentMode == eMelodizerMode.KEYBOARD) || 
		currentMode == eMelodizerMode.NONE || 
		currentMode == eMelodizerMode.CLIP || 
		(y < 7 && currentMode == eMelodizerMode.POSITION);
	}

	private void pressDisplay(int x, int y)
	{
		//User is pressing a note button (as opposed to changing key)
		if (isNote(y))
		{
			//Calculate the pitch by the octave (y) and the transposed scale
			int melodyPitch;
			if (currentMode == eMelodizerMode.CLIP)
				melodyPitch = convertGridPositionToClipNote(x, y);
			else	
				melodyPitch = convertGridPositionToNote(x, y, curSeqBank);
			
			//System.out.println("Press note at " +  Integer.toString((x + offset[curSeqBank]) % melodyScale.Degrees.length) +  " degrees");
			
			Note melodySend;
			melodySend = new Note(melodyPitch,100, 0);
			heldNote[melodyPitch] = true;
			if(currentMode != eMelodizerMode.CLIP)
				displayNote[melodyPitch] = DisplayGrid.SOLID;
			midiMelodyOut[curSeqBank].sendNoteOn(melodySend);
			addEvent(melodySend);
		}
		//User is pressing a button in the key area
		else if (currentMode == eMelodizerMode.KEYBOARD)
		{
			//User is changing keys
			for(int i=0; i<128;i++)
			{
				// If transposing we want the old pitch here
				if (transpose) 
					displayNote[i] = DisplayGrid.OFF;
				
			}

			int curKeyValue = key[curSeqBank];

			//Set new key
			key[curSeqBank] = getKeyFromCoords( x, y);
			int keyDif = curKeyValue - key[curSeqBank];
			if (transpose) {
				transposeDirty = true;
				markStartTransposeOffsets(curSeqBank);
			}
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
					
					// If !transposing we want the new pitch here
					if (!transpose) 
						displayNote[i] = DisplayGrid.OFF;
					
					if ((i-keyDif) >= 0)
						newHeldNote[i-keyDif] = true;

					//System.out.println("  Killing " + i);
				}
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
					displayNote[i] = DisplayGrid.SOLID;
					newHeldNote[i] = false;
					//System.out.println("Sending " + i);
				}
			}

		} else if (currentMode == eMelodizerMode.POSITION) {

			GridPosition[] oldPositions = new GridPosition[128]; 

			// Mark all old positions
			for(int i=0; i<128;i++)
			{
				oldPositions[i] = convertNoteToGridPosition(i, curSeqBank);
				
				// If transposing we want the old pitch here
				if (transpose) 
					displayNote[i] = DisplayGrid.OFF;
				
			}

			//Set new offset
			offset[curSeqBank] = x;
			if (transpose) {
				transposeDirty = true;
				markStartTransposeOffsets(curSeqBank);
			}
			//Release notes from old offset
			for(int i=0; i<128;i++)
			{ 
				if(heldNote[i])
				{
					Note melodyRelease;
					melodyRelease = new Note(i,0, 0);
					midiMelodyOut[curSeqBank].sendNoteOff(melodyRelease);
					addEvent(melodyRelease);
					heldNote[i] = false;
					
					// If !transposing we want the new pitch here
					if (!transpose)
						displayNote[i] = DisplayGrid.OFF;
					
					// Can't compute if we don't know the original position
					if (oldPositions[i] != null) { 
						// Because offset has changed, new note will be a different note from same grid position
						int newNote = convertGridPositionToNote(oldPositions[i].x, oldPositions[i].y, curSeqBank);
						newHeldNote[newNote] = true;
					}
				}
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
					displayNote[i] = DisplayGrid.SOLID;
					newHeldNote[i]=false;
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
		for(Enumeration<Integer> els = sequences.keys();els.hasMoreElements();)
		{
			index = Integer.class.cast(els.nextElement());
			s = sequences.get(index);

			// Check if notes are being transposed and the transposition has changed recently
			if (transposeDirty && transpose) {
				
				// Collect held notes before the heartbeat
				ArrayList<Note> notesHeld = s.getHeldNotesTransposedPitch();
				
				//Loop through old heldnotes 
				for(int i=0;i< notesHeld.size();i++) {
					midiMelodyOut[index].sendNoteOff(notesHeld.get(i));
					displayNote[notesHeld.get(i).getPitch()] = DisplayGrid.OFF;
				}	
				 
			}
			
			// If there are transpositions then the notelist returned will be a clone with the transpositions
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
		for(Enumeration<Integer> els = notePackage.keys();els.hasMoreElements();)
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
						displayNote[note.getPitch()] = DisplayGrid.SOLID;
				}
				else
				{
					//System.out.println("Releasing note " + note.getPitch());
					midiMelodyOut[index].sendNoteOff(note);
					if(index == curSeqBank)
						displayNote[note.getPitch()] = DisplayGrid.OFF;
				}
				if(index == curSeqBank)
					updateDisplayGrid();
			}
		}
		if (transposeDirty && transpose) {
			transposeDirty = false; // reset flag
			markStartTransposeOffsets(curSeqBank);
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
			sequences.put(seqIndex, new NoteSequence(seqIndex, this));
			setMelRecMode(recMode);
		}
		sequences.get(seqIndex).beginCue();

		cuedIndex = seqIndex;
	}

	public void endRecording()
	{
		// Transposition offsets will be marked from here
		markSequenceOffsets(cuedIndex);
		
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
	 * Play to particular sequence
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
			NoteSequence sequence = sequences.get(seqIndex); 
			sequence.stop();
		 	ArrayList<Note> noteList;
			noteList = sequence.getHeldNotesTransposedPitch();
			
			//Loop through heldnotes and send note off for each
			for(int i=0;i<noteList.size();i++) {
				midiMelodyOut[seqIndex].sendNoteOff(noteList.get(i));
				if (seqIndex == curSeqBank) {
					displayNote[noteList.get(i).getPitch()] = DisplayGrid.OFF;
				}
			}
			if (seqIndex == curSeqBank) {
				updateDisplayGrid();
			}
		}
	}

	//Returns -1 if no such sequence exists or empty.  0 if stopped. 1 if playing.
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
		// For backwards compatibility only
		xmlMelodizer.setAttribute(new Attribute("clipMode", currentMode == eMelodizerMode.CLIP ? "true" : "false"));
		xmlMelodizer.setAttribute(new Attribute("melodizerMode", currentMode.toString()));
		xmlMelodizer.setAttribute(new Attribute("altMode", altMode.toString()));
		xmlMelodizer.setAttribute(new Attribute("transpose", Boolean.toString(transpose)));
		
		// Serialize the Key in each pattern slot
		String keyString = "";
		for(int i=0;i<key.length;i++)
		{
			keyString += key[i];
			if(i!=key.length-1)
				keyString+= ",";
		}

		xmlMelodizer.setAttribute(new Attribute("key", keyString));
		
		// Serialize the existing offset in each pattern slot
		String offsetString = "";
		for(int i=0;i<offset.length;i++)
		{
			offsetString += offset[i];
			if(i!=offset.length-1)
				offsetString+= ",";
		}

		xmlMelodizer.setAttribute(new Attribute("offset", offsetString));

		// The starting offset and key is necessary when transpose is on, so we know how much transpose
		// Offset there is from the patterns starting position
		
		// Serialize the starting offset in each pattern slot
		String startingOffsetString = "";
		for(int i=0;i<startingOffset.length;i++)
		{
			startingOffsetString += startingOffset[i];
			if(i!=startingOffset.length-1)
				startingOffsetString+= ",";
		}

		xmlMelodizer.setAttribute(new Attribute("startingOffset", startingOffsetString));

		// Serialize the starting key in each pattern slot
		String startingKeyString = "";
		for(int i=0;i<startingKey.length;i++)
		{
			startingKeyString += startingKey[i];
			if(i!=startingKey.length-1)
				startingKeyString+= ",";
		}

		xmlMelodizer.setAttribute(new Attribute("startingKey", startingKeyString));

		Element xmlSequence;

		int seqIndex;
		NoteSequence sequence;

		for(Enumeration<Integer> els = sequences.keys();els.hasMoreElements();)
		{
			seqIndex = Integer.class.cast(els.nextElement());
			sequence = sequences.get(seqIndex);
			xmlSequence = sequence.toJDOMXMLElement();
			xmlSequence.setAttribute(new Attribute("key", ((Integer)key[seqIndex]).toString()));
			xmlMelodizer.addContent(xmlSequence);
		}

		return xmlMelodizer;
	}

	@SuppressWarnings("unchecked")
	public void loadXMLElement(Element xmlMelodizer)
	{
		//Clear current info
		cuedIndex = -1;
		sequences = new Hashtable<Integer, NoteSequence>();

		//Load XML	
		melodyScale = new Scale(ScaleName.valueOf(xmlMelodizer.getAttribute("scale").getValue()));
	
		try {
			// This is optional and for backwards compatibility only
			if (xmlMelodizer.getAttribute("clipMode").getBooleanValue() == true) {
				currentMode = eMelodizerMode.CLIP;
			}
			if (xmlMelodizer.getAttribute("melodizerMode") != null) {
				currentMode = eMelodizerMode.valueOf(xmlMelodizer.getAttribute("melodizerMode").getValue());
			}
			if (xmlMelodizer.getAttribute("altMode") != null) {
				altMode = eMelodizerMode.valueOf(xmlMelodizer.getAttribute("altMode").getValue());
			}
			if (xmlMelodizer.getAttribute("transpose") != null) {
				transpose = Boolean.parseBoolean(xmlMelodizer.getAttribute("transpose").getValue());
			}
		} catch (Throwable t) {
			// Do nothing
		} 
		try {
			String keyString = xmlMelodizer.getAttribute("key").getValue();
			int i=0;
			for(String strKey : keyString.split(","))
			{
				key[i] = Integer.parseInt(strKey);
				i++;
			}
		} catch (Throwable t) {}
		
		try {
			String offsetString = xmlMelodizer.getAttribute("offset").getValue();
			int j=0;
			for(String strOffset : offsetString.split(","))
			{
				offset[j] = Integer.parseInt(strOffset);
				j++;
			}
		} catch (Throwable t) {}
		
		try {	
			String startingOffsetString = xmlMelodizer.getAttribute("startingOffset").getValue();
			int k=0;
			for(String strStartingOffset : startingOffsetString.split(","))
			{
				startingOffset[k] = Integer.parseInt(strStartingOffset);
				k++;
			}
		} catch (Throwable t) {}
		
		try {
			String startingKeyString = xmlMelodizer.getAttribute("startingKey").getValue();
			int l=0;
			for(String strStartingKey : startingKeyString.split(","))
			{
				startingKey[l] = Integer.parseInt(strStartingKey);
				l++;
			}
		} catch (Throwable t) {}	
	
		List<Element> xmlSequences;
		NoteSequence sequence;
		Integer index;

		xmlSequences = xmlMelodizer.getChildren();

		for (Element xmlSequence : xmlSequences)
		{
			index = Integer.parseInt(xmlSequence.getAttributeValue("index"));	
			key[index] = Integer.parseInt(xmlSequence.getAttributeValue("key"));
			sequence = new NoteSequence(index, this);
			setMelRecMode(recMode);
			sequence.loadJDOMXMLElement(xmlSequence);
			//Set status to stopped if there is a sequence
			if(!sequence.isEmpty())sequence.setStatus(MonomeUp.STOPPED);
			sequences.put(index, sequence);
		}

		updateDisplayGrid();
	
	}

	public void reset() {
		for(int i=0;i<7;i++)
			stopSeq(i);
	}

	public void clipLaunch(int pitch, int vel, int channel) {
		if(vel == 0 ||vel == 64) //STOP or OFF
		{
			clipNotes[channel][pitch] = DisplayGrid.OFF;
			//System.out.println("Setting " + pitch + " OFF for channel " + channel);
		}
		else if(vel == 126)//CUE
		{
			clipNotes[channel][pitch] = DisplayGrid.SLOWBLINK;
			//System.out.println("Setting " + pitch + " SLOWBLINK");
		}	
		else if(vel == 127 || vel == 1)//PLAY or continue
		{
			//Turn on the note that actually plays
			clipNotes[channel][pitch] = DisplayGrid.SOLID;
		}
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
		for(Enumeration<Integer> els = sequences.keys();els.hasMoreElements();)
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
				displayNote[note.getPitch()] = DisplayGrid.SOLID;
			addEvent(note);
		}
		else
		{
			Note releaseNote = new Note(note.getPitch(),0, 0);
			midiMelodyOut[melodizerChannel].sendNoteOff(releaseNote);
			heldNote[note.getPitch()] = false;
			if(melodizerChannel == curSeqBank)
				displayNote[note.getPitch()] = DisplayGrid.OFF;
			addEvent(releaseNote);
		}

		updateDisplayGrid();
	}

	public eMelodizerMode getCurrentMode() {
		return currentMode;
	}

	public void setCurrentMode(eMelodizerMode currentMode) {
		this.currentMode = currentMode;
		updateNavGrid();
		updateDisplayGrid();
	}

	public void setAltMode(eMelodizerMode altCurrentMode) {
		this.altMode = altCurrentMode;
	}

	public eMelodizerMode getAltMode() {
		return altMode;
	}

	public void swapModes() {
		eMelodizerMode oldCurrentMode = currentMode;
		setCurrentMode(this.altMode);
		setAltMode(oldCurrentMode);
	}

	public boolean getTranspose() {
		return transpose;
	}

	public void setTranspose(boolean transpose) {
		clearDisplayGrid();
		if (transpose)
			this.transposeDirty = true;
		this.transpose = transpose;
	}

	/**
	 * Mark the state of key and offsets on a sequence
	 * @param seqIndex
	 */
	private void markSequenceOffsets(int seqIndex) {
		/** 
		 * Record this as the starting offset 
		 * and key 
		 */
		startingOffset[seqIndex] = offset[seqIndex];
		startingKey[seqIndex] = key[seqIndex];
		markStartTransposeOffsets(seqIndex);
	}
	
	private void markStartTransposeOffsets(int seqIndex) {
		lastKey[seqIndex] = key[seqIndex];
		lastOffset[seqIndex] = offset[seqIndex];
	}	

	/**
	 * Called when NavMenu change is being cued, aborted or committed
	 */
	@Override
	public void onMenuFocusChange(MenuFocusEvent event) {
		super.onMenuFocusChange(event);

		//System.out.println(event);

		// We are interested in this case
		if (event.oldIndex == myNavRow) {
			// When we toggle current mode button we switch between default modes
			if (event.type == Mode.MenuFocusEvent.eMenuFocusEvent.MENU_FOCUS_CHANGE_ABORTED) {
				swapModes();
			}
		}
	}

	/**
	 * Perform pitch transformation if
	 * requested on running note sequences
	 * 
	 */
	public ArrayList<Note> transpose(ArrayList<Note> notes, int transpositionIndex) {
		if (!transpose || notes == null)
			return notes;
		
		ArrayList<Note> newNotes = new ArrayList<Note>();
		
		Note newNote;
		for (Note note : notes) {
			newNote = transposeWithContext(note, getTranspositionContext(transpositionIndex));
			if (newNote != null)
				newNotes.add(newNote);
		}
		return newNotes;
	}
	
	public Note transposeWithContext(Note note, TranspositionContext tc) {
			int pitch = note.getPitch();
			pitch = pitch + tc.localKeyOffset;
			GridPosition pos = convertNoteToGridPositionNoOffset(pitch, tc.key);
			//System.out.println("old pitch:" + Integer.toString(pitch) + " Position:" + pos);
			
			// Drop notes that fall off the grid
			if (pos != null) {
				GridPosition newpos = pos.offsetX(tc.localOffset);
				pitch = convertGridPositionToNoteNoOffset(newpos.x, newpos.y, tc.key);
				
				//System.out.println("new pitch:" + Integer.toString(pitch) + " Position:" + newpos + " offset:" + localOffset + " keyoffset:" + localKeyOffset);
				return new Note(pitch, note.getVelocity(), note.getLength());
			}	
		System.out.println("Fell off the grid");
		return null;	
	}
	
	public TranspositionContext getTranspositionContext(int sequence) {
		return new TranspositionContext(sequence, offset[sequence] - startingOffset[sequence], key[sequence] - startingKey[sequence], key[sequence]);
	}

	// Grid Test
	public static void main(String args[]) {
		
		
		try {
			// Chromatic
			Melodizer.GridPosition grid = new Melodizer.GridPosition(new Scale(ScaleName.Chromatic), 0,0);
			if (!testScaleArithmetic(grid))
				throw new Exception("Chromatic 0,0 test failed");
			
			grid = new Melodizer.GridPosition(new Scale(ScaleName.Chromatic), 1,-1);
			if (!testScaleArithmetic(grid))
				throw new Exception("Chromatic -1,1 test failed");
			
			grid = new Melodizer.GridPosition(new Scale(ScaleName.Chromatic), 7,-10);
			if (!testScaleArithmetic(grid))
				throw new Exception("Chromatic -10,10 test failed");
		
			// Pentatonic
			grid = new Melodizer.GridPosition(new Scale(ScaleName.Pentatonic), 0,0);
			if (!testScaleArithmetic(grid))
				throw new Exception("Pentatonic 0,0 test failed");
			
			grid = new Melodizer.GridPosition(new Scale(ScaleName.Pentatonic), 1,-1);
			if (!testScaleArithmetic(grid))
				throw new Exception("Pentatonic -1,1 test failed");
			
			grid = new Melodizer.GridPosition(new Scale(ScaleName.Pentatonic), 7,-10);
			if (!testScaleArithmetic(grid))
				throw new Exception("Pentatonic -10,10 test failed");
		
			System.out.println("Tests succeeded!!");
		} catch (Exception e) {
			System.err.println("Test failed!! " + e);
		}
	}
	
	private static boolean testScaleArithmetic(Melodizer.GridPosition grid) {
		Melodizer.GridPosition gridStart = grid;
		
		for (int i = 0; i < 64; i++) {
			grid = grid.offsetX(1);
			System.out.println(grid);
		}
		for (int i = 0; i < 64; i++) {
			grid = grid.offsetX(-1);
			System.out.println(grid);
		}
		
		for (int i = 0; i < 64; i++) {
			grid = grid.offsetX(-3);
			System.out.println(grid);
		}
		for (int i = 0; i < 64; i++) {
			grid = grid.offsetX(3);
			System.out.println(grid);
		}
		
		for (int i = 0; i < 64; i++) {
			grid = grid.offsetX(6);
			System.out.println(grid);
		}
		for (int i = 0; i < 64; i++) {
			grid = grid.offsetX(-6);
			System.out.println(grid);
		}
		
		for (int i = 0; i < 64; i++) {
			grid = grid.offsetX(7);
			System.out.println(grid);
		}
		for (int i = 0; i < 64; i++) {
			grid = grid.offsetX(-7);
			System.out.println(grid);
		}
		
		return gridStart.equals(grid);
	}

	public static class GridPosition {
		private static final int DIM_X = 7;
		private int degrees = 7;
		private Scale scale;
		
		public int x;
		public int y;

		public GridPosition(Scale scale, int x, int y) {
			this.scale = scale;
			if (scale != null)
				this.degrees = scale.Degrees.length;
			
			int width = Math.min(degrees, DIM_X);
			
			this.x = (x % width);
			this.y = y  - Math.abs(x / width);	
				
		}

		/** 
		 * Add x to the grid position
		 * Return a new grid representing the new position.
		 * Existing grid is untouched
		 */
		public GridPosition offsetX(int offset_x) {
			int new_x = 0;
			int new_y = 0;
			
			// We wrap the scale grid on the degrees of the scale or width of our canvas, 
			// whichever is less
			int width = Math.min(degrees, DIM_X);
			
			int multiplier = Math.round(Math.signum(offset_x + x));
			if (multiplier < 0) {
				new_x = width + ((offset_x + x) % width);
				if (Math.abs((offset_x + x) / width) == 0) 
					new_y = y + 1 + (Math.abs((offset_x + x) / width) * multiplier);
				else
					new_y = y + 1 - (Math.abs((offset_x + x) / width) * multiplier);
			} else if (multiplier == 0) {
				new_x = 0;
				new_y = y;
			} else {	
				new_x = (offset_x + x) % width;
				new_y = y - (Math.abs((offset_x + x) / width) * multiplier);
			}
			
			return new GridPosition(this.scale, new_x, new_y);
		}
		
		public boolean equals(GridPosition pos) {
			if (pos.x == this.x && pos.y == this.y)
				return true;
			else
				return false;
		}

		public String toString() {
			return Integer.toString(x) + "," + Integer.toString(y);
		}


	}

}
