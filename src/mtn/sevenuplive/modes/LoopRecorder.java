package mtn.sevenuplive.modes;

import java.util.List;

import mtn.sevenuplive.main.MonomeUp;

import org.jdom.Element;

public class LoopRecorder extends Mode {

	private CtrlSequence loopSequences[];
	private Boolean gateLoopChokes = true;
	private int curSequence = 0;
	
	public LoopRecorder(int _navRow, MonomeUp _m, int grid_width, int grid_height) {
		super(_navRow, grid_width, grid_height);
		loopSequences = new CtrlSequence[7];
		for(int i=0;i<7;i++)
			loopSequences[i] = new CtrlSequence(i);
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
		
		for(int i=0;i<7;i++)
		{
			//Set to fastblink if playing
			if(AllModes.getInstance().getLooper().getLoop(i).isPlaying() || loopSequences[i].getStatus() == MonomeUp.PLAYING)
			{
				navGrid[getYCoordFromSubMenu(i)] = DisplayGrid.FASTBLINK;
			}
				
			//Set to slowblink if cueing or recording
			if(loopSequences[i].getStatus() == MonomeUp.CUED || loopSequences[i].getStatus() == MonomeUp.RECORDING)
				navGrid[getYCoordFromSubMenu(i)] = DisplayGrid.SLOWBLINK;
			
			//System.out.println("LoopSeq " + i + " status is " + loopSequences[i].getStatus());
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
		
		loopSequences[x].addEvent(y);
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
				loopSequences[getSubMenuFromYCoord(y)].stop();
				loopSequences[getSubMenuFromYCoord(y)] = new CtrlSequence(getSubMenuFromYCoord(y));
				loopSequences[getSubMenuFromYCoord(y)].beginCue();
			}
			else if(loopSequences[getSubMenuFromYCoord(y)].getStatus() == MonomeUp.RECORDING)
			{
				loopSequences[getSubMenuFromYCoord(y)].endRecording();
				loopSequences[getSubMenuFromYCoord(y)].play();
				//Play the first offset
				Integer sequencedStep = loopSequences[getSubMenuFromYCoord(y)].heartbeat();
				if(sequencedStep != null && sequencedStep != -1)
				{
					AllModes.getInstance().getLooper().playLoop(getSubMenuFromYCoord(y), sequencedStep);
				}
			}
		}
	}
	
	public void step()
	{
		Integer sequencedStep;
		int curChokeGroup;
		
		//loop through sequences and perform heartbeat on each.  sending each event that is returned
		for(int i=0;i<7;i++)
		{
			sequencedStep = loopSequences[i].heartbeat();
			if(sequencedStep != null && sequencedStep != -1)
			{
				//If using gating, only 1 loop can play per step.  Determine that loop.  Play it and stop the others.
				curChokeGroup = AllModes.getInstance().getLooper().getLoop(i).getChokeGroup();
				if(curChokeGroup > -1)
				{
					AllModes.getInstance().getLooper().playLoop(i, sequencedStep);
					for(int k=0;k<7;k++)
					{
						if(k!=i && AllModes.getInstance().getLooper().getLoop(k).getChokeGroup() == curChokeGroup)
							AllModes.getInstance().getLooper().setLoopStopOnNextStep(k);
					}
					
				}
				else
					AllModes.getInstance().getLooper().playLoop(i, sequencedStep);
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
		Integer sequencedStep = loopSequences[loopIndex].heartbeat();
		if(sequencedStep != null)
		{
			AllModes.getInstance().getLooper().playLoop(loopIndex, sequencedStep);
		}
	}
	
	public void stopLoopSequence(int loopIndex)
	{
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
