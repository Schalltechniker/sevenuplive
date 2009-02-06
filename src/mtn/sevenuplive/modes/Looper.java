package mtn.sevenuplive.modes;

import promidi.*;
import mtn.sevenuplive.main.*;
import org.jdom.*;

import java.util.*;

public class Looper extends Mode {
	
	private Loop[] loops;
	private int[] looperViewModes;
	public boolean[] stopLoopsOnNextStep;
	private int playViewMode = 0;
	private int resViewMode = 1;
	private int navCol = 7;
	private MidiOut midiOut;
	private int offsetStartCtrl = 96;
	private MonomeUp m;
	private Boolean gateLoopChokes = true;
	private boolean muteNotes = false;

	public Looper(int _navRow, MidiOut _midiOut, mtn.sevenuplive.main.MonomeUp _m) {
		super(_navRow);
		
		looperViewModes = new int[7];
		stopLoopsOnNextStep = new boolean[7];
		
		loops = new Loop[7];
		for(int i=0;i<loops.length;i++)
			loops[i] = new Loop();
		
		midiOut = _midiOut;
	
		m = _m;
		
		//Set default res for a couple loops for the default SevenUp Live Template pack
		loops[6].setResolution(7);
		loops[5].setResolution(3);
		loops[4].setResolution(7);
	}
	
	public int getNumLoops()
	{
		return loops.length;
	}
	
	public Loop getLoop(int index)
	{
		return loops[index];
	}
	
	private void updateNavGrid()
	{
		clearNavGrid();
		navGrid[myNavRow] = solid;
	   //Iterate through the loops and set them all to their coordesponding values
 	   for(int i=0;i<loops.length;i++)
       {
     			 if(looperViewModes[i] == playViewMode)
     				  navGrid[getYCoordFromSubMenu(i)] = fastBlink;
     			 else if(looperViewModes[i] == resViewMode)
     				  navGrid[getYCoordFromSubMenu(i)] = off;
       }
	}
	
	public void updateDisplayGrid()
	{
		clearDisplayGrid();
		
		for(int i=0;i<loops.length;i++)
	    {
		   //Display play mode (current step)
		   if(looperViewModes[i] == playViewMode)
		   {
			   if(loops[i].isPlaying())
				   displayGrid[i][loops[i].getStep()] = solid;
		   }
		   else if(looperViewModes[i] == resViewMode)
		   {
			   //Display resolution
			   for(int k=7;k>=0;k--)
	  				if(7 - k <=  loops[i].getResolution())
	  					displayGrid[i][k]=solid;	 
		   }
	    }
	}
	
	public int[] getNavGrid()
	{
		updateNavGrid();
		return navGrid;
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
	
	private void pressNavCol(int y)
	{
		int loopIndex = getSubMenuFromYCoord(y);
		//Inverse the mode of the corresponding loop
		if(looperViewModes[loopIndex] == playViewMode)
		{
			if(!m.loopRecorder.isLoopSequencePlaying(loopIndex))
				loops[loopIndex].stop();
			looperViewModes[loopIndex] = resViewMode;
		}
		else
			looperViewModes[loopIndex] = playViewMode;

		midiOut.sendNoteOff(new Note(mtn.sevenuplive.main.MonomeUp.c3+loopIndex,127, 0));
	}
	
	public boolean isLoopPlaying(int loopNum)
	{
		return loops[loopNum].isPlaying();
	}
	
	public void stopLoop(int loopNum)
	{
		loops[loopNum].stop();
		midiOut.sendNoteOff(new Note(mtn.sevenuplive.main.MonomeUp.c3+loopNum,127, 0));
	}
	
	public void setLoopStopOnNextStep(int loopNum)
	{
		stopLoopsOnNextStep[loopNum] = true;
	}
	
	public void playLoop(int loopNum, int step)
	{
		loops[loopNum].setStep(step);
		loops[loopNum].setPressedRow(step);
		
		//In buzz you have to send the controller as soon as it's pressed
		//midiOut.sendController(new promidi.Controller(offsetStartCtrl+loopNum, loops[loopNum].getStep() * 16));
		//System.out.println("Loop " + loopNum + " sending ctrl " + (loops[loopNum].getStep() * 16));
	}
	
	private void pressDisplay(int x, int y)
	{
		if ( looperViewModes[x] == playViewMode)
    	{
			//Choke loops in the same choke group
			int curChokeGroup = loops[x].getChokeGroup();
			if(curChokeGroup > -1)
			{
				for(int i=0; i<7;i++)
					if(loops[i].getChokeGroup() == curChokeGroup && i != x)
					{
						if(gateLoopChokes)
							stopLoop(i);
						else
							stopLoopsOnNextStep[i] = true;
					}
			}
			
			stopLoopsOnNextStep[x] = false;
			playLoop(x, y);
			
			System.out.println("Gate loops is " + gateLoopChokes);
		}
    	else
    	{
    		//Set loop res to the inverse of y
    		loops[x].setResolution(7 - y);
    		
    		//Send midi controls for x
    		int loopFitCtrlVal = (7 - y+1) * 8 ;
    		
   			loopFitCtrlVal = (int)(loopFitCtrlVal);
    		
   			//No longer send res controller values (ableton live doesnt have a knob for it)
    		//midiOut.sendController(new promidi.Controller(resStartCtrl+x, loopFitCtrlVal ));
    	}
	}
	
	// NOTE: Not used
	/*private int getSizeOfStoppedLoopsInChokeGroup(int chokeGroup)
	{
		int size = 0;
		for(int i=0;i<7;i++)
		{
			if(stopLoopsOnNextStep[i])
				if(loops[i].getChokeGroup() == chokeGroup)
					size++;
		}
		return size;
	}
	
	private int getChokeGroupSize(int chokeGroup)
	{
		int size = 0;
		for(int i=0; i<7;i++)
		{
			if(loops[i].getChokeGroup() == chokeGroup)
				size++;
		}
		return size;
	}*/

	public void step()
	{
		int pressedRow;
		int resCounter;
		int step;
		
		updateDisplayGrid();
		
		for(int i=0;i<7;i++)
		{
			if(stopLoopsOnNextStep[i])
			{
				stopLoop(i);
				stopLoopsOnNextStep[i] = false;
			}
		}
		
		for(int i = 0;i<loops.length;i++)
        {
        	if(loops[i].isPlaying())
        	{
        		pressedRow = loops[i].getPressedRow();
        		resCounter = loops[i].getResCounter();
        		step = loops[i].getStep();
        		
        		//In buzz you have to send the controller AFTER the note is played
        		int loopCtrlValue = (loops[i].getStep() * 16);
        		midiOut.sendController(new promidi.Controller(offsetStartCtrl+i, loopCtrlValue));

        		//Send note every time looprow is 0 or at it's offset
        		if((resCounter == 0) && (step == 0 || pressedRow > -1))
        		{
        			if(!muteNotes)
        				midiOut.sendNoteOn(new Note(mtn.sevenuplive.main.MonomeUp.c3+i,127, 0));	
        			pressedRow = -1;
        		}
        		
        		loops[i].increaseResCount();
        	}
        }
	}
	
	public Element toJDOMXMLElement()
	{
		Element xmlLooper = new Element("looper");
		
		xmlLooper.setAttribute(new Attribute("gateLoopChokes", gateLoopChokes.toString()));
		
		Element xmlLoop;

		for(Integer i=0;i<loops.length;i++)
		{
			xmlLoop = loops[i].toJDOMXMLElement();
			xmlLoop.setAttribute(new Attribute("index", i.toString()));
			xmlLooper.addContent(xmlLoop);
		}
		
		return xmlLooper;
	}
	
	@SuppressWarnings("unchecked")
	public void loadJDOMXMLElement(Element xmlLooper)
	{	
		List<Element> xmlLoops;
		Integer loopIndex;
		
		gateLoopChokes = Boolean.parseBoolean(xmlLooper.getAttributeValue("gateLoopChokes"));
		
		xmlLoops = xmlLooper.getChildren();
		
		for (Element xmlLoop : xmlLoops)
		{
			loopIndex = Integer.parseInt(xmlLoop.getAttributeValue("index"));
			loops[loopIndex].loadJDOMXMLElement(xmlLoop);		
		}
	}
	
	public void setGateLoopChokes(boolean _gateLoopChokes)
	{
		gateLoopChokes = _gateLoopChokes;
	}
	
	public boolean getGateLoopChokes()
	{
		return gateLoopChokes;
	}

	public void reset() {
		for(int i=0;i<7;i++)
			stopLoop(i);
	}

	public void setLooperMute(boolean mute) {
		muteNotes = mute;
		
	}
}


