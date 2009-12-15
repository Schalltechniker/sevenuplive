/*
	Copyright 2009 Adam Ribaldo 
	 
	Developed by Adam Ribaldo, Chris Lloyd
    
    This file is part of SevenUpLive.
    http://www.makingthenoise.com/sevenup/

    SevenUpLive is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    SevenUpLive is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with SevenUpLive.  If not, see <http://www.gnu.org/licenses/>.
*/

package mtn.sevenuplive.modes;

import java.util.ArrayList;
import java.util.List;

import mtn.sevenuplive.main.MonomeUp;
import mtn.sevenuplive.modes.events.Event;
import mtn.sevenuplive.modes.events.UpdateDisplayEvent;
import mtn.sevenuplive.modes.events.UpdateNavEvent;

import org.jdom.Element;

public class LoopRecorder extends Mode {

	private CtrlSequence loopSequences[];
	private Boolean gateLoopChokes = true;
	private int curSequence = 0;
	private int tick = 0;
	
	public LoopRecorder(int _navRow, MonomeUp _m, int grid_width, int grid_height) {
		super(_navRow, grid_width, grid_height);
		loopSequences = new CtrlSequence[7];
		for(int i=0;i<7;i++)
			loopSequences[i] = new CtrlSequence(i);
		
		// Subscribe to the events we want to receive
		subscribe(new UpdateDisplayEvent(), this);
		subscribe(new UpdateNavEvent(), this);
	}
	
	public void onEvent(Event e) {
		if (e.getType().equals(UpdateDisplayEvent.UPDATE_DISPLAY_EVENT)) {
			updateDisplayGrid(); 
		} else if (e.getType().equals(UpdateNavEvent.UPDATE_NAV_EVENT)) {
			updateNavGrid(); 
		}
	}
	
	public void updateDisplayGrid()
	{
		clearDisplayGrid();
		
		for(int i=0;i<7;i++)
		{
			if(AllModes.getInstance().getLooper().getLoop(i).isPlaying())
				displayGrid[i][AllModes.getInstance().getLooper().getLoop(i).getStep()] = DisplayGrid.SOLID;
		}
	}
	
	public void updateNavGrid()
	{
		clearNavGrid();
		navGrid[myNavRow] = DisplayGrid.SOLID;

		switch(loopSequences[curSequence].getStatus()){
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
	
	public void press(int x, int y)
	{
		if(x == DisplayGrid.NAVCOL)
			pressNavCol(y);
		else
			pressDisplay(x,y);
		
		updateDisplayGrid();
		updateNavGrid();
	}
	
	private void pressDisplay(int x,int y)
	{
		if(loopSequences[curSequence].getStatus() == MonomeUp.CUED || loopSequences[curSequence].getStatus() == MonomeUp.RECORDING)
		{
			loopSequences[curSequence].addEvent(x, y);
		}
		
		int curChokeGroup = AllModes.getInstance().getLooper().getLoop(x).getChokeGroup();
		
		if(curChokeGroup > -1)
		{
			for(int k=0;k<7;k++)
			{
				if(k!=x && AllModes.getInstance().getLooper().getLoop(k).getChokeGroup() == curChokeGroup)
				{
					if(gateLoopChokes)
						AllModes.getInstance().getLooper().stopLoop(k);
					else
						AllModes.getInstance().getLooper().stopLoopsOnNextStep[k] = true;
				}
			}
		}
		
		AllModes.getInstance().getLooper().stopLoopsOnNextStep[x] = false;
		AllModes.getInstance().getLooper().playLoop(x, y);
	}
	
	private void pressNavCol(int y)
	{
		//If changing to a different sequence
		if(curSequence != getSubMenuFromYCoord(y))
		{
			curSequence = getSubMenuFromYCoord(y);
		}
		else
		{
			if(loopSequences[getSubMenuFromYCoord(y)].getStatus() == MonomeUp.STOPPED)
			{
				loopSequences[getSubMenuFromYCoord(y)].beginCue();
			}
			else if(loopSequences[getSubMenuFromYCoord(y)].getStatus() == MonomeUp.CUED)
			{
				//If cued and then stopped, clear the sequence
				loopSequences[getSubMenuFromYCoord(y)] = new CtrlSequence(getSubMenuFromYCoord(y));
			}
			else if(loopSequences[getSubMenuFromYCoord(y)].getStatus() == MonomeUp.PLAYING)
			{
				//If playing, stop, clear, and cue the sequence
				stopLoopSequence(getSubMenuFromYCoord(y));
				loopSequences[getSubMenuFromYCoord(y)] = new CtrlSequence(getSubMenuFromYCoord(y));
				loopSequences[getSubMenuFromYCoord(y)].beginCue();
			}
			else if(loopSequences[getSubMenuFromYCoord(y)].getStatus() == MonomeUp.RECORDING)
			{
				loopSequences[getSubMenuFromYCoord(y)].endRecording();
				loopSequences[getSubMenuFromYCoord(y)].play();
				//Play the first offset
				ArrayList<ControlValue> sequencedControlValues = loopSequences[getSubMenuFromYCoord(y)].heartbeat();
				
				for(ControlValue cv : sequencedControlValues)
				{
					if(cv != null && cv.getValue() > -1)
					{
						AllModes.getInstance().getLooper().playLoop(cv.getId(), cv.getValue());
					}
				}
			}
		}
	}
	
	/**
	 * Called for subticks of a step using 64th clock.
	 * On tick 3, this sets up next loop step before we 
	 * actually take the real step.
	 */
	public void tick() {
		tick++;
		
		if (tick == 3) {
			//If a loop sequence is playing, tick it over 
			for(int i=0;i<7;i++)
			{
				prestep(i);
			}
		}
	}
	
	
	
	/**
	 * Take the loop step.
	 */
	public void step()
	{
		tick = 0;
		
		//If a loop sequence is playing, call heartbeat
		for(int i=0;i<7;i++)
		{
			step(i);
		}
	}
	
	/**
	 * Take the loop step
	 * @param loopIndex
	 */
	public void step(int loopIndex)
	{
		int curChokeGroup;
		int i = loopIndex;
		ArrayList<ControlValue> sequencedControlValues = loopSequences[i].heartbeat();
		
		if(sequencedControlValues != null)
		{
			for(ControlValue cv : sequencedControlValues)
			{
				if(cv != null && cv.getValue() > -1)
				{
					curChokeGroup = AllModes.getInstance().getLooper().getLoop(i).getChokeGroup();
					if(curChokeGroup > -1)
					{
						for(int k=0;k<7;k++)
						{
							if(k!=i && AllModes.getInstance().getLooper().getLoop(k).getChokeGroup() == curChokeGroup)
								AllModes.getInstance().getLooper().setLoopStopOnNextStep(k);
						}
						
					}
				}
			}
		}
		

	}
	
	/**
	 * Setup the next step before taking it.
	 * @param loopIndex
	 */
	public void prestep(int loopIndex)
	{
		int i = loopIndex;
		ArrayList<ControlValue> sequencedControlValues = loopSequences[i].peekstep();
		
		if(sequencedControlValues != null)
		{
			for(ControlValue cv : sequencedControlValues)
			{
				if(cv != null && cv.getValue() > -1)
				{
					AllModes.getInstance().getLooper().playLoop(cv.getId(), cv.getValue());
				}
			}
		}
		
	}
	
	public boolean isLoopSequencePlaying(int loopIndex)
	{
		return loopSequences[loopIndex].getStatus() == 1;
	}
	
	public boolean loopSequenceExists(int loopIndex)
	{
		return loopSequences[loopIndex].hasEvents();
	}
	
	public void playLoopSequence(int loopIndex)
	{
		loopSequences[loopIndex].play();
		
		// If we are passed the trigger point then fire anyway. 
		// We are still before the step.
		if (tick > 3)
			prestep(loopIndex);
		
	}
	
	public int getSeqStatus(int seqNum)
	{
		return loopSequences[seqNum].getStatus();
	}
	
	public void stopLoopSequence(int loopIndex)
	{
		for(Integer loopId : loopSequences[loopIndex].getUniqueCtrlIds())
			AllModes.getInstance().getLooper().stopLoop(loopId);
		loopSequences[loopIndex].stop();
	}
	
	
	public Element toJDOMXMLElement()
	{
		Element xmlLoopRecorder = new Element("loopRecorder");
		Element xmlLoopSequence;
		
		for(int i=0;i<loopSequences.length;i++)	
		{
			xmlLoopSequence = loopSequences[i].toJDOMXMLElement();
			xmlLoopRecorder.addContent(xmlLoopSequence);
		}
		
		return xmlLoopRecorder;
	}
	
	@SuppressWarnings("unchecked")
	public void loadJDOMXMLElement(Element xmlLoopRecorder)
	{
		//Clear current info
		loopSequences = new CtrlSequence[7];
		for(int i=0;i<7;i++)
			loopSequences[i] = new CtrlSequence(i);
		
		List<Element> xmlSequences;
		Integer index;
		CtrlSequence sequence;
		
		xmlSequences = xmlLoopRecorder.getChildren();
		
		int defaultindex = 0;
		for (Element xmlSequence: xmlSequences)
		{
			index = xmlSequence.getAttributeValue("index") == null ? defaultindex : Integer.parseInt(xmlSequence.getAttributeValue("index"));
			sequence = new CtrlSequence(index);
			sequence.loadJDOMXMLElement(xmlSequence);
			loopSequences[index] = sequence;
			defaultindex++;
		}
	}
	
	public void setGateLoopChokes(boolean _gateLoopChokes)
	{
		gateLoopChokes = _gateLoopChokes;
	}
	
	
}
