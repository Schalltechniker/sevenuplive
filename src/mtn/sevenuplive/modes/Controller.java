package mtn.sevenuplive.modes;
import java.util.ArrayList;
import java.util.List;

import mtn.sevenuplive.main.MonomeUp;

import org.jdom.Attribute;
import org.jdom.Element;

import promidi.MidiOut;

public class Controller extends Mode {

	private CtrlSequence ctrlSequences[];
	private int curSequence = 0;
	private MidiOut midiControlOut;
	private Integer controls[];
	private Integer startingController;
	
	public Controller(int _navRow, MidiOut _midiControlOut, int _startingController, int grid_width, int grid_height) {
		super(_navRow, grid_width, grid_height);
		midiControlOut = _midiControlOut;
		//Initialize ctrl sequences
		ctrlSequences = new CtrlSequence[7];
		for(int i=0;i<7;i++)
			ctrlSequences[i] = new CtrlSequence(i);
		
		controls = new Integer[7];
		
		startingController = _startingController;
		
		//Initialize controls to -1 for "not set"
		for(int j=0;j<7;j++)
           controls[j] = -1;
	     
	    updateDisplayGrid();
	}
	
	private void updateDisplayGrid()
	{
		super.clearDisplayGrid();
		//Loop through the controls in a control bank and set the y coordinate on the display grid
		for(int i=0;i<7;i++)
		{
			//Only set the display if the control is > 0
			if(controls[i] > 0)
				for(int j=7;j>=8-controls[i];j--)
					displayGrid[i][j] = DisplayGrid.SOLID;
		}
	}
	
	private void updateNavGrid()
	{
		clearNavGrid();
		navGrid[myNavRow] = DisplayGrid.SOLID;

		switch(ctrlSequences[curSequence].getStatus()){
		case MonomeUp.STOPPED: navGrid[getYCoordFromSubMenu(curSequence)] = DisplayGrid.FASTBLINK;
			break;
		case MonomeUp.CUED: navGrid[getYCoordFromSubMenu(curSequence)] = DisplayGrid.SLOWBLINK;
			break;
		case MonomeUp.RECORDING: navGrid[getYCoordFromSubMenu(curSequence)] = DisplayGrid.SLOWBLINK;
			break;
		case MonomeUp.PLAYING: navGrid[getYCoordFromSubMenu(curSequence)] = DisplayGrid.FASTBLINK;
			break;
		}
	}
	
	/*
	 * [8] //y=0
	 * [7] //y=1
	 * [6] //y=2
	 * [5] //y=3
	 * [4] //y=4
	 * [3] //y=5
	 * [2] //y=6
	 * [1] //y=7
	 * [0] // Send control value = 0
	 * [-1] // Disabled, do not send a control value
	 */
	public void press(int x, int y)
	{
		if(x == DisplayGrid.NAVCOL)
			pressNavCol(y);
		else
			pressDisplay(x,y);
		
		updateDisplayGrid();
		updateNavGrid();
	}
	
	private void pressDisplay(int x, int y) {
		   //If they hit the bottom row twice, clear the column
	       if(y==7 && controls[x] == 1)
	       {
	    	   controls[x] = 0;
	    	   if(ctrlSequences[curSequence].getStatus() == MonomeUp.CUED || ctrlSequences[curSequence].getStatus() == MonomeUp.RECORDING)
	    		   ctrlSequences[curSequence].addEvent(x, 0);
	    	   
	       }
	       //Otherwise set the value to 8-y
	       else
	       { 
	    	   controls[x] = 8-y;
	    	   if(ctrlSequences[curSequence].getStatus() == MonomeUp.CUED || ctrlSequences[curSequence].getStatus() == MonomeUp.RECORDING) 
	    		   ctrlSequences[curSequence].addEvent(x, 8-y);
	       }
	       
	       updateDisplayGrid();
	       
	       //Send the control value for the previous selected column
	       sendMidiControls(x);
	}

	private void pressNavCol(int y) {
		//If changing to a different sequence
		if(curSequence != getSubMenuFromYCoord(y))
		{
			curSequence = getSubMenuFromYCoord(y);
		}
		else
		{
			if(ctrlSequences[getSubMenuFromYCoord(y)].getStatus() == MonomeUp.STOPPED)
			{
				ctrlSequences[getSubMenuFromYCoord(y)].beginCue();
			}
			else if(ctrlSequences[getSubMenuFromYCoord(y)].getStatus() == MonomeUp.CUED)
			{
				//If cued and then stopped, clear the sequence
				ctrlSequences[getSubMenuFromYCoord(y)] = new CtrlSequence(getSubMenuFromYCoord(y));
			}
			else if(ctrlSequences[getSubMenuFromYCoord(y)].getStatus() == MonomeUp.PLAYING)
			{
				//If playing, stop, clear, and cue the sequence
				stopCtrlSequence(getSubMenuFromYCoord(y));
				ctrlSequences[getSubMenuFromYCoord(y)] = new CtrlSequence(getSubMenuFromYCoord(y));
				ctrlSequences[getSubMenuFromYCoord(y)].beginCue();
			}
			else if(ctrlSequences[getSubMenuFromYCoord(y)].getStatus() == MonomeUp.RECORDING)
			{
				ctrlSequences[getSubMenuFromYCoord(y)].endRecording();
				ctrlSequences[getSubMenuFromYCoord(y)].play();
				//Play the first offset
				ArrayList<ControlValue> sequencedControlValues = ctrlSequences[getSubMenuFromYCoord(y)].heartbeat();
				
				for(ControlValue cv : sequencedControlValues)
					if(cv != null && cv.getValue() > -1)
					{
						controls[cv.getId()] = cv.getValue();
						sendMidiControls(cv.getId());
					}
			}
		}
		
	}
	
	public void stopCtrlSequence(int ctrlIndex)
	{
		for(Integer loopId : ctrlSequences[ctrlIndex].getUniqueCtrlIds())
			AllModes.getInstance().getLooper().stopLoop(loopId);
		ctrlSequences[ctrlIndex].stop();
	}
	
	private void sendMidiControls(int x)
	 {
		   int controlValue = controls[x] * 16;
		   //Can not send 128 as a value
		   if(controlValue == 128)
			   controlValue = 127;
		   
		   //send controlvalue to control corresponding to the current control grid and the column
		   midiControlOut.sendController(new promidi.Controller(startingController + x, controlValue));
	}
	
	public void step()
	{
		//If a ctrl sequence is playing, call heartbeat
		for(int i=0;i<7;i++)
		{
			ArrayList<ControlValue> sequencedControlValues = ctrlSequences[i].heartbeat();
			
			if(sequencedControlValues != null)
			{
				for(ControlValue cv : sequencedControlValues)
					if(cv != null && cv.getValue() > -1)
					{
						controls[cv.getId()] = cv.getValue();
						sendMidiControls(cv.getId());
					}
			}
		}
		
		updateDisplayGrid();
	}
	
	public int getSeqStatus(int seqNum)
	{
		return ctrlSequences[seqNum].getStatus();
	}
	
	public void playSequence(int index)
	{
		ctrlSequences[index].play();
		step();
	}
	
	public void stopSequence(int index)
	{
		ctrlSequences[index].stop();
	}
	
	public boolean sequenceExists(int index)
	{
		return ctrlSequences[index].hasEvents();
	}
	
	
	public Element toJDOMXMLElement()
	{
		Element xmlController = new Element("controller");
		xmlController.setAttribute(new Attribute("startingController", startingController.toString()));
		Element xmlControllerSequence;
		
		for(int i=0;i<ctrlSequences.length;i++)	
		{
			xmlControllerSequence = ctrlSequences[i].toJDOMXMLElement();
			xmlController.addContent(xmlControllerSequence);
		}
		
		return xmlController;
	}
	
	@SuppressWarnings("unchecked")
	public void loadJDOMXMLElement(Element xmlController)
	{	
		startingController = xmlController.getAttributeValue("startingController") == null ? startingController : Integer.parseInt(xmlController.getAttributeValue("startingController"));
		
		//Clear current info
		ctrlSequences = new CtrlSequence[7];
		for(int i=0;i<7;i++)
			ctrlSequences[i] = new CtrlSequence(i);
		
		List<Element> xmlSequences;
		Integer index;
		CtrlSequence sequence;
		
		xmlSequences = xmlController.getChildren();
		
		int defaultindex = 0;
		for (Element xmlSequence: xmlSequences)
		{
			index = xmlSequence.getAttributeValue("index") == null ? defaultindex : Integer.parseInt(xmlSequence.getAttributeValue("index"));
			sequence = new CtrlSequence(index);
			sequence.loadJDOMXMLElement(xmlSequence);
			ctrlSequences[index] = sequence;
			defaultindex++;
		}
		
		updateDisplayGrid();
	}
}
