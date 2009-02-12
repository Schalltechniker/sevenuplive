package mtn.sevenuplive.modes;

import java.util.ArrayList;

import mtn.sevenuplive.main.MonomeUp;
import promidi.MidiOut;
import promidi.Note;

public class Masterizer extends Mode {
	//PATTERN
	private final static int PATTERN_COL = 0;
	
	//SEQUENCER
	private final static int SEQUENCER_COL = 1;
	int sequencerRows[];
	
	//CONROLLER
	private final static int CONTROLER_COL = 2;
	int curControlBank = -1;
	
	//TRANSPORT LOCATOR
	private final static int PLAYMODE = 1;
	private final static int RECMODE = 2;
	private final static int LOCATOR_COL = 3;
	private int locatorRows[];
	private int locatorMode = PLAYMODE;
	
	//LOOPING
	private final static int LOOPER_COL = 4;
	int looperRows[]; 
	
	//MELODY
	private MidiOut midiMelodyOut[];
	private final static int MELODY_COL = 5;
	private int melodyRows[];
	private int melRecMode = ModeConstants.MEL_ON_BUTTON_PRESS;
	private boolean mel1Cue[];

	//MELODY2
	private MidiOut midiMelody2Out[];
	private int melody2Col = 6;
	private int melody2Rows[];
	private boolean mel2Cue[];
	
	//MASTER
	private MidiOut midiMasterOut;
	
	private mtn.sevenuplive.main.MonomeUp m;
	
	public Masterizer(int _navRow, MidiOut _midiMelodyOut[], MidiOut _midiMelody2Out[],MidiOut _midiMasterOut, mtn.sevenuplive.main.MonomeUp _m,  int grid_width, int grid_height)
	{
		super(_navRow, grid_width, grid_height);
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
			if(navGrid[y] == DisplayGrid.OFF)
				navGrid[y] = DisplayGrid.SLOWBLINK;
			else if(navGrid[y] == DisplayGrid.SLOWBLINK)
			{
				navGrid[y] = DisplayGrid.OFF;
				midiMasterOut.sendNoteOn(new Note(MonomeUp.C5 + y,127, 0));
			}
		}
		else if(x == PATTERN_COL)
		{
			AllModes.patternizer.curPatternRow = y;
		}
		else if(x == SEQUENCER_COL)
		{
			//Ignore 7th row
			if(y < 7)
			{
				if(y != AllModes.sequencer.curSequenceBank)
					AllModes.sequencer.nextSequence = y;
				else
				{
						AllModes.sequencer.curSeqRow = 0;
						//Play pattern from beginning
						AllModes.patternizer.curPatternRow = 0;
				}
			}
		}
		else if(x == LOOPER_COL)
		{
			if(y < AllModes.looper.getNumLoops())
			{
				
				if(AllModes.looper.isLoopPlaying(y) || AllModes.loopRecorder.isLoopSequencePlaying(y))
				{
					AllModes.looper.stopLoop(y);
					AllModes.loopRecorder.stopLoopSequence(y);
				}
				else
				{
					if(AllModes.loopRecorder.loopSequenceExists(y))
						AllModes.loopRecorder.playLoopSequence(y);
					else
						AllModes.looper.playLoop(y, 0);
				}
			}
		}
		else if(x == MELODY_COL)
   	 	{
			seqStatus = AllModes.melodizer1.getSeqStatus(y);
   		 	if(seqStatus == MonomeUp.PLAYING)
   		 	{
   		 		if(melRecMode == ModeConstants.MEL_ON_BUTTON_PRESS)
   		 			stopMel1Seq(y);
   		 		else
   		 			mel1Cue[y] = true;
   		 	}
   		 	else if(seqStatus == MonomeUp.STOPPED)
   		 	{
   		 		if(melRecMode == ModeConstants.MEL_ON_BUTTON_PRESS)
   		 			AllModes.melodizer1.playSeq(y);
		 		else
		 			mel1Cue[y] = true;
   		 	}
   		 		
   	 	}
		else if(x == melody2Col)
   	 	{
			seqStatus = AllModes.melodizer2.getSeqStatus(y);
   		 	if(seqStatus == MonomeUp.PLAYING)
   		 	{	
   		 		if(melRecMode == ModeConstants.MEL_ON_BUTTON_PRESS)
		 			stopMel2Seq(y);
		 		else
		 			mel2Cue[y] = true;
   		 	}
   		 	else if(seqStatus == MonomeUp.STOPPED)
   		 	{
   		 		if(melRecMode == ModeConstants.MEL_ON_BUTTON_PRESS)
   		 			AllModes.melodizer2.playSeq(y);
		 		else
		 			mel2Cue[y] = true;
   		 	}
   	 	}
		else if(x == CONTROLER_COL && y < 7)
		{
			if(AllModes.controller.isBankEnabled(y))
			{
				AllModes.controller.sendMidiControlBank(y);
				curControlBank = y;
			}
		}
		updateDisplayGrid();
	}
	
	private void stopMel2Seq(int y) {
		AllModes.melodizer2.stopSeq(y);
  		
		 	ArrayList<Note> noteList;
		noteList = AllModes.melodizer2.sequences.get(y).getHeldNotes();
		//Loop through heldnotes and send note off for each
		for(int i=0;i<noteList.size();i++)
			midiMelody2Out[y].sendNoteOff(noteList.get(i));
	}

	private void stopMel1Seq(int y) {
	 		AllModes.melodizer1.stopSeq(y);
		
		 	ArrayList<Note> noteList;
		 	noteList = AllModes.melodizer1.sequences.get(y).getHeldNotes();
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
			if(AllModes.controller.isBankEnabled(i))
				displayGrid[CONTROLER_COL][i] = DisplayGrid.FASTBLINK;
			else
				displayGrid[CONTROLER_COL][i] = DisplayGrid.OFF;
		}
		if(curControlBank > -1)
			displayGrid[CONTROLER_COL][curControlBank] = DisplayGrid.SOLID;
				
		//SEQUENCER
		//Light up the current sequence
		for(int i=0;i<8;i++)
		{
			//Slow blink the next sequence. Make the current seq solid
			if(i == AllModes.sequencer.nextSequence || i == AllModes.sequencer.curSequenceBank)
			{
				if(i == AllModes.sequencer.nextSequence)
					sequencerRows[i] = DisplayGrid.SLOWBLINK;
				if(i == AllModes.sequencer.curSequenceBank)
					sequencerRows[i] = DisplayGrid.SOLID;
			}
			else
				sequencerRows[i] = DisplayGrid.OFF;
		}
		
		//LOOPER
		for(int i=0;i<AllModes.looper.getNumLoops();i++)
		{
			if(AllModes.looper.isLoopPlaying(i) || AllModes.loopRecorder.isLoopSequencePlaying(i))
				looperRows[i] = DisplayGrid.SOLID;
			else if(AllModes.loopRecorder.loopSequenceExists(i))
				looperRows[i] = DisplayGrid.FASTBLINK;
			else
				looperRows[i] = DisplayGrid.OFF;
		}

		//MELODY
		int seqStatus;
		for(int i=0;i<8;i++)
		{
			seqStatus = AllModes.melodizer1.getSeqStatus(i);
			if(seqStatus == 0)
				melodyRows[i] = DisplayGrid.FASTBLINK;
			else if(seqStatus == 1)
				melodyRows[i] = DisplayGrid.SOLID;
			else melodyRows[i] = DisplayGrid.OFF;
			
			if(RECMODE == ModeConstants.MEL_QUANTIZED && mel1Cue[i])
				melodyRows[i] = DisplayGrid.SLOWBLINK;
		}
		
		//MELODY2
		for(int i=0;i<8;i++)
		{
			seqStatus = AllModes.melodizer2.getSeqStatus(i);
			if(seqStatus == 0)
				melody2Rows[i] = DisplayGrid.FASTBLINK;
			else if(seqStatus == 1)
				melody2Rows[i] = DisplayGrid.SOLID;
			else melody2Rows[i] = DisplayGrid.OFF;
			
			if(RECMODE == ModeConstants.MEL_QUANTIZED && mel2Cue[i])
				melody2Rows[i] = DisplayGrid.SLOWBLINK;
		}
		
		displayGrid[SEQUENCER_COL] = sequencerRows;
		displayGrid[LOCATOR_COL] = locatorRows;
		displayGrid[LOOPER_COL] = looperRows;
		displayGrid[MELODY_COL] = melodyRows;
		displayGrid[melody2Col] = melody2Rows;
		
	}
	
	public void updatePatternBeat(int patternRow)
	{
		displayGrid[PATTERN_COL] = new int[8];
		displayGrid[PATTERN_COL][patternRow] = DisplayGrid.SOLID;
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
			locatorMode = PLAYMODE;
			locatorRows = new int[8];
			locatorRows[0] = DisplayGrid.SOLID;
			
			//If cued, trigger melodizer start or stop
			for(int i=0;i<8;i++)
			{
				if(mel1Cue[i] == true)
				{
					if(AllModes.melodizer1.getSeqStatus(i) == MonomeUp.PLAYING)
						stopMel1Seq(i);
					else
						AllModes.melodizer1.playSeq(i);
					mel1Cue[i]=false;
					
				}
			
				if(mel2Cue[i] == true)
				{
					if(AllModes.melodizer2.getSeqStatus(i) == MonomeUp.PLAYING)
						stopMel2Seq(i);
					else
						AllModes.melodizer2.playSeq(i);
					
					mel2Cue[i] = false;
				}
			}
		
			
		}
		else if(noteValue == MonomeUp.CSHARP4)
		{
			//Begin a record mode (length of record shows by speed of steps)
			locatorMode = RECMODE;
			locatorRows = new int[8];
			locatorRows[0] = DisplayGrid.FASTBLINK;
		}
		else if(noteValue == MonomeUp.DSHARP4)
		{
			int currentStep = -1;
			
			//Step in current mode
			//Find the current step
			for(int i =0; i<locatorRows.length;i++)
				if(locatorRows[i]!=DisplayGrid.OFF)
					currentStep = i;
			
			//Only if the locator knows where the current step is do we step forward
			if(currentStep > -1)
			{	
				locatorRows = new int[8];
				if(locatorMode == PLAYMODE)
					locatorRows[(currentStep + 1) % 8] = DisplayGrid.SOLID;
				else if(locatorMode == RECMODE)
					locatorRows[(currentStep + 1) % 8] = DisplayGrid.FASTBLINK;
			}
		}
	}
	
	public void setMelRecMode(int _recMode)
	{
		melRecMode = _recMode;
	}

}