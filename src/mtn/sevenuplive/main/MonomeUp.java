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

import jklabs.monomic.MonomeOSC;
import mtn.sevenuplive.modes.LoopRecorder;
import mtn.sevenuplive.modes.Looper;
import mtn.sevenuplive.modes.Masterizer;
import mtn.sevenuplive.modes.Melodizer;
import mtn.sevenuplive.modes.ModeConstants;
import mtn.sevenuplive.modes.Patternizer;
import mtn.sevenuplive.scales.Scale;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;

import promidi.MidiIO;
import promidi.MidiOut;
import promidi.Note;

public final class MonomeUp extends MonomeOSC {
	
	 private SevenUpPanel parentPanel;
	 private ArrayList<Element> xmlPatches;
	 private int curPatchIndex=0;
	 private String patchTitle = "";
	 
	 ///////////////////////////////////////// 
	 //Blink-speed members
	 /////////////////////////////////////////
	 private static final int FRAMES = 10;
	 private static final int FASTBLINKFRAME = 1;
	 private static final  int SLOWBLINKFRAME = 10;
	 public static final int OFF = 0;
	 public static final int SOLID = 3;
	 public static final int SLOWBLINK = 1;
	 public static final int FASTBLINK = 2;
	 private int frmCount;
	 ////////////////////////////////////////
	 
	
	 ////////////////////////////////////////
	 // MonomeUp Modes
	 ////////////////////////////////////////
	 private int curDisplayGrid[][];
	 private int navGrid[];
	 private int pressedButtonsLength[][];
	 
	 //Int specifying which column the navbar will be located
	 public static final int NAVCOL = 7;
	 public static final int STOPPED = 0;
	 public static final int PLAYING = 1;
	 public static final int CUED = 2;
	 public static final int RECORDING = 3;
	 public static final int CUEDSTOP = 4;
	 ////////////////////////////////////////
	 
	 //////////////////////////////////////
	 //PATTERNIZER
	 //////////////////////////////////////
	 public Patternizer patternizer;
	 //////////////////////////////////////
	 
	 /////////////////////////////////////
	 //CONTROLLER
	 /////////////////////////////////////
	 public mtn.sevenuplive.modes.Controller controller;
	 private static final int STARTING_CONTROLLER = 40;
	 /////////////////////////////////////
	 
	 
	 /////////////////////////////////////
	 //SEQUENCER
	 /////////////////////////////////////
	 public mtn.sevenuplive.modes.Sequencer sequencer;
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
	 
	 
	 //////////////////////////////////////
	 //LOOPER
	 /////////////////////////////////////
	 public Looper looper;
	 public LoopRecorder loopRecorder;
	 private MidiOut midiLoopOut;
	 private Boolean areLoopsGated = false;
	 ////////////////////////////////////////
	 
	 ////////////////////////////////////////
	 //MELODIZER
	 ////////////////////////////////////////
	 public Melodizer melodizer1;
	 public Melodizer melodizer2;
	 private MidiOut midiMelodyOut[];
	 private MidiOut midiMelody2Out[];
	 /////////////////////////////////////
	 
	 /////////////////////////////////////
	 //MASTERIZER
	 /////////////////////////////////////
	 private Masterizer masterizer;
	 private MidiOut midiMasterOut;
	 /////////////////////////////////////
	 
	 ////////////////////////////////////////
	 //Midi members
	 ////////////////////////////////////////
	 private MidiIO midiIO;
	 private MidiOut midiSampleOut;
	 ////////////////////////////////////

	 private ConnectionSettings sevenUpConnections;
	 
	 ////////////////////////////////////
	 //Interface modes
	 ////////////////////////////////////
	 private int menuLevel;
	 private final static int MAINMENU = 0;
	 private final static int SUBMENU = 1;
	 private int curMode;
	 
	 public static final int GRID_WIDTH = 8;
	 public static final int GRID_HEIGHT = 8;
	 ////////////////////////////////////
	 
	 SevenUpApplet SevenUpApplet;
	 
	 MonomeUp (processing.core.PApplet listener, ConnectionSettings _sevenUpConnections, Scale monomeScale, promidi.MidiIO _midiIO, SevenUpPanel _parentPanel) {
	     super(listener, _sevenUpConnections.oscPrefix, _sevenUpConnections.oscHostAddress, _sevenUpConnections.oscHostPort, _sevenUpConnections.oscListenPort);
	     SevenUpApplet = (SevenUpApplet)listener;
	     sevenUpConnections = _sevenUpConnections;
	     
	     xmlPatches = new ArrayList<Element>();
	     parentPanel = _parentPanel;
	     
	     midiIO = _midiIO;

	     //Pressed buttons
	     pressedButtonsLength = new int[8][8];
	     menuLevel = SUBMENU;
	     
	     initializeMidi(listener);
	     
	     patternizer = new Patternizer(ModeConstants.PATTERN_MODE, midiSampleOut, GRID_WIDTH, GRID_HEIGHT);
	     controller = new mtn.sevenuplive.modes.Controller(ModeConstants.CONTROL_MODE, midiSampleOut, STARTING_CONTROLLER, GRID_WIDTH, GRID_HEIGHT);
	     sequencer = new mtn.sevenuplive.modes.Sequencer(ModeConstants.SEQ_MODE, this, GRID_WIDTH, GRID_HEIGHT);
	     melodizer1 = new mtn.sevenuplive.modes.Melodizer(ModeConstants.MELODY_MODE,midiMelodyOut, GRID_WIDTH, GRID_HEIGHT);
	     melodizer2 = new mtn.sevenuplive.modes.Melodizer(ModeConstants.MELODY2_MODE,midiMelodyOut, GRID_WIDTH, GRID_HEIGHT);
	     melodizer2 = new mtn.sevenuplive.modes.Melodizer(ModeConstants.MELODY2_MODE,midiMelody2Out, GRID_WIDTH, GRID_HEIGHT);
	     looper = new mtn.sevenuplive.modes.Looper(ModeConstants.LOOP_MODE, midiLoopOut, this, GRID_WIDTH, GRID_HEIGHT);
	     loopRecorder = new mtn.sevenuplive.modes.LoopRecorder(ModeConstants.LOOP_RECORD_MODE, this, GRID_WIDTH, GRID_HEIGHT);
	     masterizer = new mtn.sevenuplive.modes.Masterizer(ModeConstants.MASTER_MODE, midiMelodyOut, midiMelody2Out, midiMasterOut, this, GRID_WIDTH, GRID_HEIGHT);
	     
	     //Set initial display grid
	     curDisplayGrid = patternizer.getDisplayGrid();
	     navGrid = patternizer.getNavGrid();
	 } 
	 
	 private void initializeMidi(processing.core.PApplet listener)
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
	
	 public void draw()
	 {
	   //if a button is being held, count to the holdtime and fire a heldevent
	   //TODO: need to be able to handle multiple press and release

	  //draw the display grid
	  for (int x = 0; x<7;x++)
	  {
	    for (int y = 0; y<8;y++)
	    {
	       switch(curDisplayGrid[x][y])
	       {
	          case 0:
	            if(super.isLit(x, y))
	              super.setValue(x, y, 0);
	            break;
	          case 3:  
	            if(!super.isLit(x, y))
	            super.setValue(x, y, 1);
	         
	          break;

	          case 2:   
	            if(frmCount % FASTBLINKFRAME == 0) 
	            {
	                super.setValue(x, y, !this.isLit(x, y));
	            } 
	            break;
	          case 1:
	            if(frmCount % SLOWBLINKFRAME == 0) 
	            {
	                super.setValue(x, y, !this.isLit(x, y));
	            } 
	            break;
	      
	       }      
	    }
	  }
	  
	  //Draw navbar
	    for (int y = 0; y<8;y++)
	    {
	       switch(navGrid[y])
	       {
	          case 0:
	            if(super.isLit(NAVCOL, y))
	              super.setValue(NAVCOL, y, 0);
	            break;
	          case 3:  
	            if(!super.isLit(NAVCOL, y))
	            super.setValue(NAVCOL, y, 1);
	         
	          break;
	         
	          case 2:   
	            if(frmCount % FASTBLINKFRAME == 0) 
	            {
	                super.setValue(NAVCOL, y, !this.isLit(NAVCOL, y));
	            } 
	            break;
	          case 1:
	            if(frmCount % SLOWBLINKFRAME == 0) 
	            {
	                super.setValue(NAVCOL, y, !this.isLit(NAVCOL, y));
	            } 
	            break;
	       }
	    }
	  
	    //If in sampleMode, clear the grid as quick as possible
	    /*
		if (curMode == sampleMode) {
			for(int i=0; i<7;i++)
	         for(int j=0;j<8;j++)
	           curDisplayGrid[i][j] = off;               
	   	}
	   	*/
		
		//Loop through pressedNotesLength and increment the number of frames each pressed note has been held
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
			{
				if(pressedButtonsLength[i][j] >= 1)
					{
						pressedButtonsLength[i][j]++;
						//if the button has been held longer than 4 seconds then trigger a held event
						if(pressedButtonsLength[i][j] >= 4 * FRAMES)
						{	
							triggerButtonHeld(i, j);
							//TODO may need to make this equal 1 if i want to hold longer than 4 seconds
							pressedButtonsLength[i][j] = 0;
							
						}
					}
			}
		
	  frmCount++;
	  if (frmCount == FRAMES) frmCount = 0;
	 }
	 
	 void triggerButtonHeld(int x, int y)
	 {
		 if(curMode == ModeConstants.PATTERN_MODE && x == NAVCOL && y>0)
		 {
			patternizer.triggerButtonHeld(x, y);
			curDisplayGrid = patternizer.getDisplayGrid();
		 }
		 else if(curMode == ModeConstants.CONTROL_MODE && x == NAVCOL && navGrid[y] == FASTBLINK)
			 controller.clearControlBank(y);
	 }
	 
	 void monomePressed(int x, int y)
	 {
	   //Flag current button as pressed
	   pressedButtonsLength[x][y] = 1;
	   
	   //Handle pressing navbar button
	   if (x == NAVCOL)
	   {
	     if(menuLevel == MAINMENU)
	     {
	       //If they hit the same menu mode then move back to submenu
	       if (y == curMode)
	       {
	           navGrid = new int[8];
	           navGrid[curMode] = SOLID;
	           menuLevel = SUBMENU;
	           
	           if (curMode == ModeConstants.PATTERN_MODE)
	               navGrid = patternizer.getNavGrid();
	           else if (curMode == ModeConstants.CONTROL_MODE)
	        	   navGrid = controller.getNavGrid();
	           else if (curMode == ModeConstants.SEQ_MODE)
	               navGrid = sequencer.getNavGrid();
	           else if (curMode == ModeConstants.LOOP_MODE)
	        	   navGrid = looper.getNavGrid();
	           else if (curMode == ModeConstants.LOOP_RECORD_MODE)
	        	   navGrid = loopRecorder.getNavGrid();
	           else if (curMode == ModeConstants.MELODY_MODE)
	        	   navGrid = melodizer1.getNavGrid();
	           else if (curMode == ModeConstants.MELODY2_MODE)
	        	   navGrid = melodizer2.getNavGrid();
	       }
	       //Else they are changing modes
	       else
	       {
	            navGrid = new int[8];
	            curMode = y;
	            menuLevel = SUBMENU;
	            if(y == ModeConstants.PATTERN_MODE)
	            {
	              curDisplayGrid = patternizer.getDisplayGrid();
	              navGrid = patternizer.getNavGrid();
	            }
	            else if(y == ModeConstants.CONTROL_MODE)
	            {
	              curDisplayGrid = controller.getDisplayGrid();
	              navGrid = controller.getNavGrid();
	            }
	            else if(y == ModeConstants.SEQ_MODE)
	            {
		          curDisplayGrid = sequencer.getDisplayGrid();
		          navGrid = sequencer.getNavGrid();
	            }
	            else if(y == ModeConstants.LOOP_MODE)
	            {
	              looper.updateDisplayGrid();
	              curDisplayGrid = looper.getDisplayGrid();
	              navGrid = looper.getNavGrid();
	            }
	            else if(y == ModeConstants.LOOP_RECORD_MODE)
	            {
	              loopRecorder.updateDisplayGrid();
	              loopRecorder.updateNavGrid();
	              curDisplayGrid = loopRecorder.getDisplayGrid();
	              navGrid = loopRecorder.getNavGrid();
	            }
	            else if(y == ModeConstants.MELODY_MODE)
	            {
	            	melodizer1.updateDisplayGrid();
	            	curDisplayGrid = melodizer1.getDisplayGrid();
	            	navGrid = melodizer1.getNavGrid();
	            }
	            else if(y == ModeConstants.MELODY2_MODE)
	            {
	            	melodizer2.updateDisplayGrid();
	            	curDisplayGrid = melodizer2.getDisplayGrid();
	            	navGrid = melodizer2.getNavGrid();
	            }
	            else if(y == ModeConstants.MASTER_MODE)
	            {
	            	masterizer.updateDisplayGrid();
	            	curDisplayGrid = masterizer.getDisplayGrid();
	            	navGrid = masterizer.getNavGrid();
	            }
	       }
	     }
	     else if(menuLevel == SUBMENU)
	     {
	       //if they hit the curMenuPoint button again, change back main menu
	       if (y == curMode)
	       {
	          navGrid = new int[8];
	          navGrid[curMode] = SOLID;
	          menuLevel = MAINMENU;
	       }
	       //Else they are moving between sub-menu items
	       else
	       {
	          if(curMode == ModeConstants.PATTERN_MODE)
	          {
	        	 patternizer.press(x, y);
	        	 //TODO how do i get it to keep the display grid as a reference?
	        	 curDisplayGrid = patternizer.getDisplayGrid();
	          }
	          else if(curMode == ModeConstants.CONTROL_MODE)
	          {
	        	 controller.press(x, y);
	          }
	          else if(curMode == ModeConstants.SEQ_MODE)
	          {
	             sequencer.press(x, y);
	             //TODO how do i get it to keep the display grid as a reference?
	             curDisplayGrid = sequencer.getDisplayGrid();
	          }
	          else if(curMode == ModeConstants.LOOP_MODE)
	          {
	        	  looper.press(x, y);
	          }
	          else if(curMode == ModeConstants.LOOP_RECORD_MODE)
	          {
	        	  loopRecorder.press(x, y);
	          }
	          else if(curMode == ModeConstants.MELODY_MODE)
	          {
	        	 melodizer1.press(x, y);
	          }
	          else if(curMode == ModeConstants.MELODY2_MODE)
	          {
	        	 melodizer2.press(x, y);
	          }
	          else if(curMode == ModeConstants.MASTER_MODE)
	        	  masterizer.press(x, y);
	        }
	     }
	     }

	   ////////////////////////////////////
	   //Handle pressing the displayed grid
	   ////////////////////////////////////
	   else
	   {
	     if (curMode == ModeConstants.PATTERN_MODE)
	     {
	    	 patternizer.press(x, y);
	     }
	     else if(curMode == ModeConstants.CONTROL_MODE)
	     {
	         controller.press(x, y);
	     }
	     // seq mode
	     else if(curMode == ModeConstants.SEQ_MODE)
	     {
	       sequencer.press(x, y);
	     }
	     else if(curMode == ModeConstants.LOOP_MODE)
	    	looper.press(x, y);
         else if(curMode == ModeConstants.LOOP_RECORD_MODE)
       	  loopRecorder.press(x, y);
	     else if (curMode == ModeConstants.MELODY_MODE)
	       melodizer1.press(x, y);
	     else if (curMode == ModeConstants.MELODY2_MODE)
		       melodizer2.press(x, y);
	     else if(curMode == ModeConstants.MASTER_MODE)
	    	masterizer.press(x, y);
	   }
	 }

	 void monomeReleased(int x, int y)
	 {
		 if(curMode == ModeConstants.PATTERN_MODE)
		 {
			 patternizer.release(x, y);
		 }
	   //If user releases within the melodizer play area
	   else if(curMode == ModeConstants.MELODY_MODE && x != NAVCOL)
	   {
		   if(y<6 || melodizer1.clipMode)
			   melodizer1.release(x, y);
	   }
	   else if(curMode == ModeConstants.MELODY2_MODE && x != NAVCOL)
	   {
		   if(y<6 || melodizer2.clipMode)
			   melodizer2.release(x, y);
	   }
		 
	   pressedButtonsLength[x][y] = 0;
	 }
	 
	 void clipLaunch(int pitch, int vel, int channel)
	 {
		 //Does pressing stop send a midi note?
		 //System.out.println("CLIP LAUNCH PITCH: " + pitch + " VEL: " + vel + " CHAN: " + channel);
		 melodizer1.clipLaunch(pitch, vel, channel);
	 }
 
	 /**
	  * Receive notes from Live that tell SevenUp where the beat is
	  * @param note
	  */
	 void noteOn(int noteOnPitch)
	 {
		  if (noteOnPitch == C7)
	      {
	    	 //Only show the beat blips in current pattern mode
	        if ((curMode == ModeConstants.PATTERN_MODE && sequencer.isPatternPlaying(patternizer.selectedPattern)) )
	        {
	        	super.invertRow(patternizer.curPatternRow);
	        }
	        else if(curMode == ModeConstants.MASTER_MODE)
	        {
		      //blip the masterizer
	        	masterizer.updatePatternBeat(patternizer.curPatternRow);
	        }
	        else if(curMode == ModeConstants.SEQ_MODE && patternizer.curPatternRow % 4 == 0)
	        {
	        	// 	show bar blips in sequence mode
	        	super.invertRow(sequencer.curSeqRow);
	        }
	        
	        sequencer.step(noteOnPitch);
	        
	        if(curMode == ModeConstants.MASTER_MODE)
	        	masterizer.updateDisplayGrid();
	      }
	      else if(noteOnPitch == E7)
	      {
	    	  melodizer1.heartbeat();
	    	  melodizer2.heartbeat();
	      }
	      else if(noteOnPitch == F7)
	      {	
	    	  loopRecorder.updateDisplayGrid();
	    	  looper.step();
	    	  loopRecorder.step();  
	      }
	      else if(noteOnPitch == C4 || noteOnPitch == CSHARP4 || noteOnPitch == DSHARP4)
	      {
	    	  if(noteOnPitch == C4)
	    	  {
	    		  melodizer1.locatorEvent();
	    		  melodizer2.locatorEvent();
	    	  }
	    	  masterizer.locatorEvent(noteOnPitch);
	    	  masterizer.updateDisplayGrid();
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
	    	  sequencer.reset();
	    	  looper.reset();
	    	  melodizer1.reset();
	    	  melodizer2.reset();
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
				patternizer.loadJDOMXMLElement(xmlStateChild);
			}
			else if(xmlStateChild.getName().equals("controller"))
			{
				System.out.println("Loading CONTROLLER...");
				controller.loadJDOMXMLElement(xmlStateChild);
			}
			else if(xmlStateChild.getName().equals("sequencer"))
			{
				System.out.println("Loading SEQUENCER...");
				sequencer.loadJDOMXMLElement(xmlStateChild);
			}
			else if(xmlStateChild.getName().equals("looper"))
			{
				System.out.println("Loading LOOPER...");
				looper.loadJDOMXMLElement(xmlStateChild);
			}
			else if(xmlStateChild.getName().equals("loopRecorder"))
			{
				System.out.println("Loading LOOPRECORDER...");
				loopRecorder.loadJDOMXMLElement(xmlStateChild);
			}
			else if(xmlStateChild.getName().equals("melodizer"))
			{
				System.out.println("Loading MELODIZER...");
				melodizer1.loadXMLElement(xmlStateChild);
			}
			else if(xmlStateChild.getName().equals("melodizer2"))
			{
				System.out.println("Loading MELODIZER2...");
				melodizer2.loadXMLElement(xmlStateChild);
			}
		}
		
	 }
	 
	 public void setMelody1Scale(Scale newScale)
	 {
		 melodizer1.setScale(newScale);
	 }
	 public void setMelody2Scale(Scale newScale)
	 {
		 melodizer2.setScale(newScale);
	 }
	 
	 public Scale getMelody1Scale()
	 {
		 return melodizer1.getScale();
	 }
	 
	 public Scale getMelody2Scale()
	 {
		 return melodizer2.getScale();
	 }
	 
	 public void setLoopChoke(int loopNum, int chokeGroup)
	 {
		 looper.getLoop(loopNum).setChokeGroup(chokeGroup);
	 }
	 
	 public int getLoopChokeGroup(int loopNum)
	 {
		 return looper.getLoop(loopNum).getChokeGroup();
	 }
	 
	 public Document toXMLDocument(String fileName)
	 {
		 //Add logic to convert all grids to XML data here
		 Element xmlState = new Element("SevenUpPatch");
		 xmlState.setAttribute(new Attribute("areLoopsGated", areLoopsGated.toString()));		
		 xmlState.setAttribute(new Attribute("patchName", fileName));	
		 
		 //Create PATTERNIZER
		 Element xmlPatternizer = patternizer.toJDOMXMLElement();
		 
	 	 //Create CONTROLLER
		 Element xmlController = controller.toJDOMXMLElement();
		 
		 //Create SEQUENCER
		 Element xmlSequencer = sequencer.toJDOMXMLElement();
			
		 //Create LOOPER
		 Element xmlLooper = looper.toJDOMXMLElement();
		
		//Create LoopRecorder
		 Element xmlLoopRecorder = loopRecorder.toJDOMXMLElement();
		
		//Create CHOPPER
		//XMLElement xmlChopper = chopper.toXMLElement();
		 	
		//Create MELODIZER1
		Element xmlMelodizer = melodizer1.toXMLElement("melodizer");
		
		//Create MELODIZER2
		Element xmlMelodizer2 = melodizer2.toXMLElement("melodizer2");
		
		//Add modes to xmlState
	 	xmlState.addContent(xmlPatternizer);
	 	xmlState.addContent(xmlController);
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
		looper.setGateLoopChokes(_gateLoopChokes);
		loopRecorder.setGateLoopChokes(_gateLoopChokes);
	}
	
	public boolean getGateLoopChokes()
	{
		loopRecorder.setGateLoopChokes(looper.getGateLoopChokes());
		return looper.getGateLoopChokes();
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

	public void setLooperMute(boolean mute) {
		looper.setLooperMute(mute);
	}

	public void setMelRecMode(int melRecMode) {
		melodizer1.setMelRecMode(melRecMode);
		melodizer2.setMelRecMode(melRecMode);
		masterizer.setMelRecMode(melRecMode);
	}
	
	public void setLoopMultiplier(int loopNum, int multiplier) {
		looper.getLoop(loopNum).setResMultiplier(multiplier);
	}

	public void extNoteOn(Note note, int channel) {
		melodizer2.extNoteOn(note, channel);
	}

	}