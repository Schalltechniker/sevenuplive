/**
 * SevenUp for Monome's 40h
 * @author Adam Ribaudo
 * arribaud@gmail.com
 * 12/09/2007
 * Copyright 2007 - All rights reserved
 */

package mtn.sevenuplive.main;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jklabs.monomic.MonomeListener;
import jklabs.monomic.MonomeOSC;
import mtn.sevenuplive.modes.AllModes;
import mtn.sevenuplive.modes.Controller;
import mtn.sevenuplive.modes.DisplayGrid;
import mtn.sevenuplive.modes.Displays;
import mtn.sevenuplive.modes.LoopRecorder;
import mtn.sevenuplive.modes.Looper;
import mtn.sevenuplive.modes.Masterizer;
import mtn.sevenuplive.modes.Melodizer;
import mtn.sevenuplive.modes.ModeConstants;
import mtn.sevenuplive.modes.PatternizerModel;
import mtn.sevenuplive.modes.PatternizerView;
import mtn.sevenuplive.modes.Sequencer;
import mtn.sevenuplive.modes.StartupMode;
import mtn.sevenuplive.modes.Displays.GridCoordinateTarget;
import mtn.sevenuplive.scales.Scale;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;

import promidi.MidiIO;
import promidi.MidiOut;
import promidi.Note;

public final class MonomeUp extends MonomeOSC implements MonomeListener {
	
	 private SevenUpPanel parentPanel;
	 private ArrayList<Element> xmlPatches;
	 private int curPatchIndex=0;
	 private String patchTitle = "";
	 
	
	 // Monome 
	 public static final int MONOME_64 = 0;
	 public static final int MONOME_128H = 1;
	 public static final int MONOME_128V = 2;
	 public static final int MONOME_256 = 3;
	 
	 public static final int EMPTY = -1;
	 public static final int STOPPED = 0;
	 public static final int PLAYING = 1;
	 public static final int CUED = 2;
	 public static final int RECORDING = 3;
	 public static final int CUEDSTOP = 4;
	 ////////////////////////////////////////
	 
	 /////////////////////////////////////
	 //CONTROLLER
	 /////////////////////////////////////
	 private static final int STARTING_CONTROLLER = 40;
	 /////////////////////////////////////
	 
	 //Pitches
	 //Uh, these are probably wrong
	 public static final  int C7 = 108;
	 public static final  int ESHARP7 = 109;
	 public static final  int E7 = 112;
	 public static final  int F7 = 113;
	 public static final  int C4 = 72;
	 public static final  int CSHARP4 = 73;
	 public static final  int D4 = 74;
	 public static final  int DSHARP4 = 75;
	 public static final  int E4 = 76;
	 public static final  int F4 = 77;
	 public static final  int FSHARP4 = 78;
	 public static final  int G4 = 79;
	 public static final  int A4 = 58;
	 public static final int C3 = 60;
	 public static final int C1 = 36;
	 public static final int C5 = 72;
	 public static final int CSHARP5 = 73;
	 
	 /** Class that holds all our mode instances */
	 private AllModes allmodes;
	 
	 //////////////////////////////////////
	 //LOOPER
	 /////////////////////////////////////
	 private MidiOut midiLoopOut;
	 private Boolean areLoopsGated = false;
	 ////////////////////////////////////////
	 
	 ////////////////////////////////////////
	 //MELODIZER
	 ////////////////////////////////////////
	 private MidiOut midiMelodyOut[];
	 private MidiOut midiMelody2Out[];
	 /////////////////////////////////////
	 
	 /////////////////////////////////////
	 //MASTERIZER
	 /////////////////////////////////////
	 private MidiOut midiMasterOut;
	 /////////////////////////////////////
	 
	 ////////////////////////////////////////
	 //Midi members
	 ////////////////////////////////////////
	 private MidiIO midiIO;
	 private MidiOut midiSampleOut;
	 ////////////////////////////////////

	 private ConnectionSettings sevenUpConnections;
	 
	 public static final int GRID_WIDTH = 8;
	 public static final int GRID_HEIGHT = 8;
	 ////////////////////////////////////
	 
	 private DisplayGrid[] grids;
	 
	 MonomeUp (int x_grids, int y_grids, ConnectionSettings _sevenUpConnections, Scale monomeScale, promidi.MidiIO _midiIO, SevenUpPanel _parentPanel) {
	     super(x_grids, y_grids, _sevenUpConnections.oscPrefix, _sevenUpConnections.oscHostAddress, _sevenUpConnections.oscHostPort, _sevenUpConnections.oscListenPort);
	     sevenUpConnections = _sevenUpConnections;
	     
	     xmlPatches = new ArrayList<Element>();
	     parentPanel = _parentPanel;
	     
	     midiIO = _midiIO;
	     
	     // Init midi communications
	     initializeMidi();
	     
	     int totalGrids = x_grids * y_grids;
	     //Create the same number of patternizer views as there are grids
	     PatternizerModel patternizerModel = new PatternizerModel(ModeConstants.PATTERN_MODE, midiSampleOut, GRID_WIDTH, GRID_HEIGHT);
	     PatternizerView[] patternizerViews = new PatternizerView[totalGrids];
	     for(int i=0;i<patternizerViews.length;i++)
	     {
	    	 patternizerViews[i] = new PatternizerView(ModeConstants.PATTERN_MODE, GRID_WIDTH, GRID_HEIGHT, patternizerModel);
	     }
	     
	     allmodes = new AllModes(patternizerModel, patternizerViews, 
	    		 new Controller(ModeConstants.CONTROL_MODE, midiSampleOut, STARTING_CONTROLLER, GRID_WIDTH, GRID_HEIGHT),
	    		 new Sequencer(ModeConstants.SEQ_MODE, GRID_WIDTH, GRID_HEIGHT), 
	    		 new Melodizer(ModeConstants.MELODY_MODE,midiMelodyOut, GRID_WIDTH, GRID_HEIGHT), // Melodizer 1 
	    		 new Melodizer(ModeConstants.MELODY2_MODE,midiMelody2Out, GRID_WIDTH, GRID_HEIGHT), // Melodizer 2
	    		 new Looper(ModeConstants.LOOP_MODE, midiLoopOut, this, GRID_WIDTH, GRID_HEIGHT), 
	    		 new LoopRecorder(ModeConstants.LOOP_RECORD_MODE, this, GRID_WIDTH, GRID_HEIGHT), 
	    		 new Masterizer(ModeConstants.MASTER_MODE, midiMelodyOut, midiMelody2Out, midiMasterOut, this, GRID_WIDTH, GRID_HEIGHT),
	    		 new StartupMode(totalGrids, 75, 2));

	     //Set initial display grids
	     grids = new DisplayGrid[totalGrids];
	     for(int i=0;i<grids.length;i++)
	     {
	    	 int startCol = 0;
	    	 int startRow;
	    	 //Determine startCol and startRow assuming all vertical monomes
	    	 startRow = i * 8;
	    	 
	    	 //However, override for 128H or 256
	    	 if(x_grids > 1 && y_grids > 1) //256
	    	 {
	    		 if((i + 1) % 2 == 0)startRow = 8; else startRow = 0;
	    		 if(i > 1)startCol = 8; else startCol = 0;
	    	 }
	    	 else if(x_grids > 1) //128H
	    	 {
	    		 startRow = 0;
	    		 startCol = i * 8;
	    	 }
	    	 
	    	 grids[i] = new DisplayGrid(this, allmodes, startCol, startRow, 8, 8, allmodes.getPatternizerView(i), i);
	     }
	     
	    
	     // Turn on to debug monome OSC connection */
	     //this.setDebug(Monome.FINE);
	 } 
	 
	 private void initializeMidi()
	 {
		 	//Sample/Loop/Masterizer out on channel 8
		    midiSampleOut = midiIO.getMidiOut(7, sevenUpConnections.stepperOutputDeviceName);
		    midiMasterOut = midiIO.getMidiOut(7, sevenUpConnections.stepperOutputDeviceName);
		    midiLoopOut = midiIO.getMidiOut(7, sevenUpConnections.looperOuputDeviceName);

		    
		    //Create 7 channels (0-6) for melody out
		    midiMelodyOut = new MidiOut[7];
		    for(int i = 0; i<midiMelodyOut.length; i++)
		    {
		    	midiMelodyOut[i] = midiIO.getMidiOut(i, sevenUpConnections.melod1OutputDeviceName);
		    }
		    
		    //Create 7 channels (0-6) for melody2 out
		    midiMelody2Out = new MidiOut[7];
		    for(int i = 0; i<midiMelody2Out.length; i++)
		    {
		    	midiMelody2Out[i] = midiIO.getMidiOut(i, sevenUpConnections.melod2OutputDeviceName);
		    }
		    
		    panic();
	 }
	 
	 public void draw() {
		 for (DisplayGrid grid : grids) {
			  grid.draw();
		  }
	 }
	 
	 public void panic()
	 { 
		 //TODO get this to work
		 /*
		    //PANIC!!!
		     for(int j=0;j<8;j++) 
			 for(int i=0;i<128;i++)
			 {
				 midiMelodyOut[j].sendNoteOff(new Note(i, 0, 0));
			 }	   
			*/ 
	 }
	
	 public void monomePressed(int raw_x, int raw_y)
	 {
		 // Dirty flag for any action on a patch
		 if (!parentPanel.isDirty()) {
			 parentPanel.setDirty(true);
		 }
		 
		 GridCoordinateTarget targetd = Displays.translate(grids, raw_x, raw_y);
		 int x = targetd.getX_translated();
		 int y = targetd.getY_translated();
		 
		 targetd.getDisplay().monomePressed(x, y);
	 }

	 public void monomeReleased(int raw_x, int raw_y)
	 {
		 GridCoordinateTarget targetd = Displays.translate(grids, raw_x, raw_y);
		 int x = targetd.getX_translated();
		 int y = targetd.getY_translated();

		 targetd.getDisplay().monomeReleased(x, y);
	 }
	 
	 void clipLaunch(int pitch, int vel, int channel)
	 {
		 //Does pressing stop send a midi note?
		 //System.out.println("CLIP LAUNCH PITCH: " + pitch + " VEL: " + vel + " CHAN: " + channel);
		 allmodes.getMelodizer1().clipLaunch(pitch, vel, channel);
	 }
 
	 /**
	  * Receive notes from Live that tell SevenUp where the beat is
	  * @param note
	  */
	 void noteOn(int noteOnPitch)
	 {
		  if (noteOnPitch == C7)
	      {
			  for (DisplayGrid grid : grids) {
				  grid.displayCursor();
			  }
			  // Make sure we only step once
			  allmodes.getSequencer().step();
	      }
	      else if(noteOnPitch == E7)
	      {
	    	  allmodes.getMelodizer1().heartbeat();
	    	  allmodes.getMelodizer2().heartbeat();
	      }
	      else if(noteOnPitch == F7)
	      {	
	    	  allmodes.getLoopRecorder().updateDisplayGrid();
	    	  allmodes.getLooper().step();
	    	  allmodes.getLoopRecorder().step();  
	      }
	      else if(noteOnPitch == C4 || noteOnPitch == CSHARP4 || noteOnPitch == DSHARP4)
	      {
	    	  if(noteOnPitch == C4)
	    	  {
	    		  allmodes.getMelodizer1().locatorEvent();
	    		  allmodes.getMelodizer2().locatorEvent();
	    	  }
	    	  allmodes.getMasterizer().locatorEvent(noteOnPitch);
	    	  allmodes.getMasterizer().updateDisplayGrid();
	      }
	      else if(noteOnPitch == E4)
	      {
	    	  parentPanel.loadPrevPatch();
	      }
	      else if(noteOnPitch == F4)
	      {
	    	  parentPanel.loadNextPatch();
	      }
		  //Reset all modes
	      else if(noteOnPitch == FSHARP4)
	      {
	    	  System.out.println("received fsharp");
	    	  allmodes.getSequencer().reset();
	    	  allmodes.getLooper().reset();
	    	  allmodes.getMelodizer1().reset();
	    	  allmodes.getMelodizer2().reset();
	      }
	}
	 
	 /***
	  * 
	  * @param xmlDoc
	  * @return Returns whether or not the xml file loaded is a PatchPack (as opposed to a single patch)
	  */
	 
	@SuppressWarnings("unchecked")
	public boolean loadXML(Document xmlDoc)
	 {
		 if(xmlDoc.getRootElement().getName().equals("SevenUpPatch"))
		 {
			 loadXMLPatch(xmlDoc.getRootElement());
			 return false;
		 }
		 else if(xmlDoc.getRootElement().getName().equals("SevenUpPatchPack"))
		 {
			 System.out.println("Loading patch pack.");
			 xmlPatches = new ArrayList<Element>();
			 Element Patch;
			 String patchName;
			 Iterator<Element> itr = xmlDoc.getRootElement().getChildren().iterator();
			 while (itr.hasNext()) {
				 Patch = (Element)itr.next();
				 patchName = Patch.getAttributeValue("patchName");
				 System.out.println("Appending patch: " + patchName);
				 xmlPatches.add(Patch);
			 }
			 
			 //Load the first patch in the group
			 loadXMLPatch(xmlPatches.get(0));
			 return true;
		 }
		 else
		 {
			 System.out.println("**Badly formed XML");
			 return false;
		 }
	 }
	 
	 
	@SuppressWarnings("unchecked")
	public void loadXMLPatch(Element patch)
	 {
		 Element xmlState = patch;

		areLoopsGated = "true".equals((xmlState.getAttributeValue("areLoopsGated")));
		try
		{
			patchTitle = xmlState.getAttributeValue("patchName");
		}
		catch(Exception e)
		{
			System.out.println("No valid patch title in XML");
			patchTitle = "";
		}

		List<Element> xmlStateChildren = xmlState.getChildren();
		
		for  (Element xmlStateChild: xmlStateChildren) {
		
			if(xmlStateChild.getName().equals("patternizer"))
			{
				System.out.println("Loading PATTERNIZER...");
				allmodes.getPatternizerModel().loadJDOMXMLElement(xmlStateChild);
			}
			else if(xmlStateChild.getName().equals("sequencer"))
			{
				System.out.println("Loading SEQUENCER...");
				allmodes.getSequencer().loadJDOMXMLElement(xmlStateChild);
			}
			else if(xmlStateChild.getName().equals("looper"))
			{
				System.out.println("Loading LOOPER...");
				allmodes.getLooper().loadJDOMXMLElement(xmlStateChild);
			}
			else if(xmlStateChild.getName().equals("loopRecorder"))
			{
				System.out.println("Loading LOOPRECORDER...");
				allmodes.getLoopRecorder().loadJDOMXMLElement(xmlStateChild);
			}
			else if(xmlStateChild.getName().equals("melodizer"))
			{
				System.out.println("Loading MELODIZER...");
				allmodes.getMelodizer1().loadXMLElement(xmlStateChild);
			}
			else if(xmlStateChild.getName().equals("melodizer2"))
			{
				System.out.println("Loading MELODIZER2...");
				allmodes.getMelodizer2().loadXMLElement(xmlStateChild);
			}
		}
		
	 }
	 
	 public void setMelody1Scale(Scale newScale)
	 {
		 allmodes.getMelodizer1().setScale(newScale);
	 }
	 
	 public void setMelody2Scale(Scale newScale)
	 {
		 allmodes.getMelodizer2().setScale(newScale);
	 }
	 
	 public Scale getMelody1Scale()
	 {
		 return allmodes.getMelodizer1().getScale();
	 }
	 
	 public Scale getMelody2Scale()
	 {
		 return allmodes.getMelodizer2().getScale();
	 }
	 
	 public void setLoopChoke(int loopNum, int chokeGroup)
	 {
		 allmodes.getLooper().getLoop(loopNum).setChokeGroup(chokeGroup);
	 }
	 
	 public int getLoopChokeGroup(int loopNum)
	 {
		 return allmodes.getLooper().getLoop(loopNum).getChokeGroup();
	 }
	 
	 public Document toXMLDocument(String fileName)
	 {
		 //Add logic to convert all grids to XML data here
		 Element xmlState = new Element("SevenUpPatch");
		 xmlState.setAttribute(new Attribute("areLoopsGated", areLoopsGated.toString()));		
		 xmlState.setAttribute(new Attribute("patchName", fileName));	
		 
		 //Create PATTERNIZER
		 Element xmlPatternizer = allmodes.getPatternizerModel().toJDOMXMLElement();
		 
		 //Create SEQUENCER
		 Element xmlSequencer = allmodes.getSequencer().toJDOMXMLElement();
			
		 //Create LOOPER
		 Element xmlLooper = allmodes.getLooper().toJDOMXMLElement();
		
		//Create LoopRecorder
		 Element xmlLoopRecorder = allmodes.getLoopRecorder().toJDOMXMLElement();
		
		//Create CHOPPER
		//XMLElement xmlChopper = chopper.toXMLElement();
		 	
		//Create MELODIZER1
		Element xmlMelodizer = allmodes.getMelodizer1().toXMLElement("melodizer");
		
		//Create MELODIZER2
		Element xmlMelodizer2 = allmodes.getMelodizer2().toXMLElement("melodizer2");
		
		//Add modes to xmlState
	 	xmlState.addContent(xmlPatternizer);
	 	xmlState.addContent(xmlSequencer);
	 	xmlState.addContent(xmlLooper);
	 	xmlState.addContent(xmlLoopRecorder);
	 	xmlState.addContent(xmlMelodizer);
	 	xmlState.addContent(xmlMelodizer2);
	 	
		return new Document(xmlState);
	 }
	 
	   protected void finalize() {
		   
	   }

	public void setGateLoopChokes(boolean _gateLoopChokes) {
		allmodes.getLooper().setGateLoopChokes(_gateLoopChokes);
		allmodes.getLoopRecorder().setGateLoopChokes(_gateLoopChokes);
	}
	
	public boolean getGateLoopChokes()
	{
		allmodes.getLoopRecorder().setGateLoopChokes(allmodes.getLooper().getGateLoopChokes());
		return allmodes.getLooper().getGateLoopChokes();
	}

	public int loadPrevPatch() {
		if(curPatchIndex > 0)
		{
			loadXMLPatch(xmlPatches.get(curPatchIndex-1));
			curPatchIndex -=1;
		}
		return curPatchIndex;
	}
	
	public int loadNextPatch() {
		if(curPatchIndex < xmlPatches.size()-1)
		{
			loadXMLPatch(xmlPatches.get(curPatchIndex+1));
			curPatchIndex+=1;
		}
		return curPatchIndex;
	}
	
	public int getPatchPackSize()
	{
		return xmlPatches.size();
	}

	public String getPatchTitle() {
		return patchTitle;
	}

	public void setPatchTitle(String patchTitle) {
		this.patchTitle = patchTitle;
	}

	public void setLooperMute(boolean mute) {
		allmodes.getLooper().setLooperMute(mute);
	}

	public void setMelRecMode(int melRecMode) {
		allmodes.getMelodizer1().setMelRecMode(melRecMode);
		allmodes.getMelodizer2().setMelRecMode(melRecMode);
		allmodes.getMasterizer().setMelRecMode(melRecMode);
	}
	
	public void setLoopLength(int loopNum, float length) {
		allmodes.getLooper().getLoop(loopNum).setLength(length);
	}
	
	public void setLoopType(int loopNum, int type) {
		allmodes.getLooper().getLoop(loopNum).setType(type);
	}

	public void extNoteOn(Note note, int channel) {
		allmodes.getMelodizer2().extNoteOn(note, channel);
	}

	public float getLoopLength(int loopNum) {
		return allmodes.getLooper().getLoop(loopNum).getLength();
	}
	
	public int getLoopType(int loopNum) {
		return allmodes.getLooper().getLoop(loopNum).getType();
	}

	public void reset() {
		for(int i=0;i<7;i++)
		{
			//If the loop is already playing, set the step to 0
			if(allmodes.getLooper().isLoopPlaying(i))
				allmodes.getLooper().getLoop(i).setStep(0);
			
			if(allmodes.getLoopRecorder().isLoopSequencePlaying(i))
			{
				allmodes.getLoopRecorder().stopLoopSequence(i);
				allmodes.getLoopRecorder().playLoopSequence(i);
			} 
			
			if(allmodes.getMelodizer1().getSeqStatus(i) == MonomeUp.PLAYING)
			{
				allmodes.getMelodizer1().stopSeq(i);
				allmodes.getMelodizer1().playSeq(i);
			}	
			
			if(allmodes.getMelodizer2().getSeqStatus(i) == MonomeUp.PLAYING)
			{
				allmodes.getMelodizer2().stopSeq(i);
				allmodes.getMelodizer2().playSeq(i);
			}	
		}
			
		allmodes.getPatternizerModel().curPatternRow = 0;
		allmodes.getSequencer().curSeqRow = 0;
	}

	}