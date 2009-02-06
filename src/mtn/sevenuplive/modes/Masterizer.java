package mtn.sevenuplive.modes;

import java.util.ArrayList;

import mtn.sevenuplive.main.MonomeUp;
import promidi.*;

public class Masterizer extends Mode {
	//PATTERN
	int patternCol = 0;
	
	//SEQUENCER
	int sequencerCol = 1;
	int sequencerRows[];
	
	//CONROLLER
	int controllerCol = 2;
	int curControlBank = -1;
	
	//TRANSPORT LOCATOR
	private int playMode = 1;
	private int recMode = 2;
	private int locatorMode = playMode;
	private int locatorCol = 3;
	private int locatorRows[];
	
	//LOOPING
	int looperCol = 4;
	int looperRows[]; 
	
	//MELODY
	private MidiOut midiMelodyOut[];
	private int melodyCol = 5;
	private int melodyRows[];
	private int stopped = 0;
	private int playing = 1;
	private int melRecMode = MonomeUp.MEL_ON_BUTTON_PRESS;
	private boolean mel1Cue[];

	//MELODY2
	private MidiOut midiMelody2Out[];
	private int melody2Col = 6;
	private int melody2Rows[];
	private boolean mel2Cue[];
	
	//MASTER
	private MidiOut midiMasterOut;
	
	private mtn.sevenuplive.main.MonomeUp m;
	
	public Masterizer(int _navRow, MidiOut _midiMelodyOut[], MidiOut _midiMelody2Out[],MidiOut _midiMasterOut, mtn.sevenuplive.main.MonomeUp _m)
	{
		super(_navRow);
		m = _m;
		displayGrid = new int[7][8];
		
		sequencerRows = new int[8];
		
		//LOOPER
		looperRows = new int[8];
		
		//MELODY
		melodyRows = new int[8];
		mel1Cue = new boolean[8];
		midiMelodyOut = _midiMelodyOut;
		
		//MELODY2
		melody2Rows = new int[8];
		mel2Cue = new boolean[8];
		midiMelody2Out = _midiMelody2Out;
		
		//MASTER
		midiMasterOut = _midiMasterOut;
		
		//LOCATOR
		locatorRows = new int[8];
	}
	
	public void press(int x, int y)
	{
		int seqStatus;
		
		if(x == myNavRow)
		{
			//Send live MIDI notes that can be used for transport control or anything else
			//Force users to double tap the button to ensure no accidental changes of transport controls
			if(navGrid[y] == MonomeUp.OFF)
				navGrid[y] = MonomeUp.SLOWBLINK;
			else if(navGrid[y] == MonomeUp.SLOWBLINK)
			{
				navGrid[y] = MonomeUp.OFF;
				midiMasterOut.sendNoteOn(new Note(MonomeUp.C5 + y,127, 0));
			}
		}
		else if(x == patternCol)
		{
			m.patternizer.curPatternRow = y;
		}
		else if(x == sequencerCol)
		{
			//Ignore 7th row
			if(y < 7)
			{
				if(y != m.sequencer.curSequenceBank)
					m.sequencer.nextSequence = y;
				else
				{
						m.sequencer.curSeqRow = 0;
						//Play pattern from beginning
						m.patternizer.curPatternRow = 0;
				}
			}
		}
		else if(x == looperCol)
		{
			if(y < m.looper.getNumLoops())
			{
				
				if(m.looper.isLoopPlaying(y) || m.loopRecorder.isLoopSequencePlaying(y))
				{
					m.looper.stopLoop(y);
					m.loopRecorder.stopLoopSequence(y);
				}
				else
				{
					if(m.loopRecorder.loopSequenceExists(y))
						m.loopRecorder.playLoopSequence(y);
					else
						m.looper.playLoop(y, 0);
				}
			}
		}
		else if(x == melodyCol)
   	 	{
			seqStatus = m.melodizer1.getSeqStatus(y);
   		 	if(seqStatus == playing)
   		 	{
   		 		if(melRecMode == MonomeUp.MEL_ON_BUTTON_PRESS)
   		 			stopMel1Seq(y);
   		 		else
   		 			mel1Cue[y] = true;
   		 	}
   		 	else if(seqStatus == stopped)
   		 	{
   		 		if(melRecMode == MonomeUp.MEL_ON_BUTTON_PRESS)
   		 			m.melodizer1.playSeq(y);
		 		else
		 			mel1Cue[y] = true;
   		 	}
   		 		
   	 	}
		else if(x == melody2Col)
   	 	{
			seqStatus = m.melodizer2.getSeqStatus(y);
   		 	if(seqStatus == playing)
   		 	{	
   		 		if(melRecMode == MonomeUp.MEL_ON_BUTTON_PRESS)
		 			stopMel2Seq(y);
		 		else
		 			mel2Cue[y] = true;
   		 	}
   		 	else if(seqStatus == stopped)
   		 	{
   		 		if(melRecMode == MonomeUp.MEL_ON_BUTTON_PRESS)
   		 			m.melodizer2.playSeq(y);
		 		else
		 			mel2Cue[y] = true;
   		 	}
   	 	}
		else if(x == controllerCol && y < 7)
		{
			if(m.controller.isBankEnabled(y))
			{
				m.controller.sendMidiControlBank(y);
				curControlBank = y;
			}
		}
		updateDisplayGrid();
	}
	
	private void stopMel2Seq(int y) {
		m.melodizer2.stopSeq(y);
  		
		 	ArrayList<Note> noteList;
		noteList = m.melodizer2.sequences.get(y).getHeldNotes();
		//Loop through heldnotes and send note off for each
		for(int i=0;i<noteList.size();i++)
			midiMelody2Out[y].sendNoteOff(noteList.get(i));
	}

	private void stopMel1Seq(int y) {
	 		m.melodizer1.stopSeq(y);
		
		 	ArrayList<Note> noteList;
		 	noteList = m.melodizer1.sequences.get(y).getHeldNotes();
		 	//Loop through heldnotes and send note off for each
		 	for(int i=0;i<noteList.size();i++)
		 		midiMelodyOut[y].sendNoteOff(noteList.get(i));
	}

	public void updateDisplayGrid()
	{
		//CONTROLLER
		//Light up the enabled control banks
		for(int i=0;i<7;i++)
		{
			if(m.controller.isBankEnabled(i))
				displayGrid[controllerCol][i] = MonomeUp.FASTBLINK;
			else
				displayGrid[controllerCol][i] = MonomeUp.OFF;
		}
		if(curControlBank > -1)
			displayGrid[controllerCol][curControlBank] = MonomeUp.SOLID;
				
		//SEQUENCER
		//Light up the current sequence
		for(int i=0;i<8;i++)
		{
			//Slow blink the next sequence. Make the current seq solid
			if(i == m.sequencer.nextSequence || i == m.sequencer.curSequenceBank)
			{
				if(i == m.sequencer.nextSequence)
					sequencerRows[i] = MonomeUp.SLOWBLINK;
				if(i == m.sequencer.curSequenceBank)
					sequencerRows[i] = MonomeUp.SOLID;
			}
			else
				sequencerRows[i] = MonomeUp.OFF;
		}
		
		//LOOPER
		for(int i=0;i<m.looper.getNumLoops();i++)
		{
			if(m.looper.isLoopPlaying(i) || m.loopRecorder.isLoopSequencePlaying(i))
				looperRows[i] = MonomeUp.SOLID;
			else if(m.loopRecorder.loopSequenceExists(i))
				looperRows[i] = MonomeUp.FASTBLINK;
			else
				looperRows[i] = MonomeUp.OFF;
		}

		//MELODY
		int seqStatus;
		for(int i=0;i<8;i++)
		{
			seqStatus = m.melodizer1.getSeqStatus(i);
			if(seqStatus == 0)
				melodyRows[i] = MonomeUp.FASTBLINK;
			else if(seqStatus == 1)
				melodyRows[i] = MonomeUp.SOLID;
			else melodyRows[i] = MonomeUp.OFF;
			
			if(recMode == MonomeUp.MEL_QUANTIZED && mel1Cue[i])
				melodyRows[i] = MonomeUp.SLOWBLINK;
		}
		
		//MELODY2
		for(int i=0;i<8;i++)
		{
			seqStatus = m.melodizer2.getSeqStatus(i);
			if(seqStatus == 0)
				melody2Rows[i] = MonomeUp.FASTBLINK;
			else if(seqStatus == 1)
				melody2Rows[i] = MonomeUp.SOLID;
			else melody2Rows[i] = MonomeUp.OFF;
			
			if(recMode == MonomeUp.MEL_QUANTIZED && mel2Cue[i])
				melody2Rows[i] = MonomeUp.SLOWBLINK;
		}
		
		displayGrid[sequencerCol] = sequencerRows;
		displayGrid[locatorCol] = locatorRows;
		displayGrid[looperCol] = looperRows;
		displayGrid[melodyCol] = melodyRows;
		displayGrid[melody2Col] = melody2Rows;
		
	}
	
	public void updatePatternBeat(int patternRow)
	{
		displayGrid[patternCol] = new int[8];
		displayGrid[patternCol][patternRow] = MonomeUp.SOLID;
	}
	
	/***
	 * Handles the transport locator column that updates based on the location of the DAW
	 * @param noteValue
	 */
	public void locatorEvent(int noteValue)
	{
		if(noteValue == MonomeUp.C4)
		{
			//Begin play for locator mode
			locatorMode = playMode;
			locatorRows = new int[8];
			locatorRows[0] = MonomeUp.SOLID;
			
			//If cued, trigger melodizer start or stop
			for(int i=0;i<8;i++)
			{
				if(mel1Cue[i] == true)
				{
					if(m.melodizer1.getSeqStatus(i) == playing)
						stopMel1Seq(i);
					else
						m.melodizer1.playSeq(i);
					mel1Cue[i]=false;
					
				}
			
				if(mel2Cue[i] == true)
				{
					if(m.melodizer2.getSeqStatus(i) == playing)
						stopMel2Seq(i);
					else
						m.melodizer2.playSeq(i);
					
					mel2Cue[i] = false;
				}
			}
		
			
		}
		else if(noteValue == MonomeUp.CSHARP4)
		{
			//Begin a record mode (length of record shows by speed of steps)
			locatorMode = recMode;
			locatorRows = new int[8];
			locatorRows[0] = MonomeUp.FASTBLINK;
		}
		else if(noteValue == MonomeUp.DSHARP4)
		{
			int currentStep = -1;
			
			//Step in current mode
			//Find the current step
			for(int i =0; i<locatorRows.length;i++)
				if(locatorRows[i]!=MonomeUp.OFF)
					currentStep = i;
			
			//Only if the locator knows where the current step is do we step forward
			if(currentStep > -1)
			{	
				locatorRows = new int[8];
				if(locatorMode == playMode)
					locatorRows[(currentStep + 1) % 8] = MonomeUp.SOLID;
				else if(locatorMode == recMode)
					locatorRows[(currentStep + 1) % 8] = MonomeUp.FASTBLINK;
			}
		}
	}
	
	public void setMelRecMode(int _recMode)
	{
		melRecMode = _recMode;
	}

}
