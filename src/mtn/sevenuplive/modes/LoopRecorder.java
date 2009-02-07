package mtn.sevenuplive.modes;

import java.util.List;

import mtn.sevenuplive.main.MonomeUp;

import org.jdom.Element;

public class LoopRecorder extends Mode {

	private CtrlSequence loopSequences[];
	private MonomeUp m;
	private Boolean gateLoopChokes = true;
	
	public LoopRecorder(int _navRow, MonomeUp _m, int grid_width, int grid_height) {
		super(_navRow, grid_width, grid_height);
		m = _m;
		loopSequences = new CtrlSequence[7];
		for(int i=0;i<7;i++)
			loopSequences[i] = new CtrlSequence(i);
	}
	
	public void updateDisplayGrid()
	{
		clearDisplayGrid();
		
		for(int i=0;i<7;i++)
		{
			if(m.looper.getLoop(i).isPlaying())
				displayGrid[i][m.looper.getLoop(i).getStep()] = MonomeUp.SOLID;
		}
	}
	
	public void updateNavGrid()
	{
		clearNavGrid();
		navGrid[myNavRow] = MonomeUp.SOLID;
		
		for(int i=0;i<7;i++)
		{
			//Set to fastblink if playing
			if(m.looper.getLoop(i).isPlaying() || loopSequences[i].getStatus() == MonomeUp.PLAYING)
			{
				navGrid[getYCoordFromSubMenu(i)] = MonomeUp.FASTBLINK;
			}
				
			//Set to slowblink if cueing or recording
			if(loopSequences[i].getStatus() == MonomeUp.CUED || loopSequences[i].getStatus() == MonomeUp.RECORDING)
				navGrid[getYCoordFromSubMenu(i)] = MonomeUp.SLOWBLINK;
			
			//System.out.println("LoopSeq " + i + " status is " + loopSequences[i].getStatus());
		}
	}
	
	public void press(int x, int y)
	{
		if(x == MonomeUp.NAVCOL)
			pressNavCol(y);
		else
			pressDisplay(x,y);
		
		updateDisplayGrid();
		updateNavGrid();
	}
	
	private void pressDisplay(int x,int y)
	{
		int curChokeGroup = m.looper.getLoop(x).getChokeGroup();
		
		if(curChokeGroup > -1)
		{
			for(int k=0;k<7;k++)
			{
				if(k!=x && m.looper.getLoop(k).getChokeGroup() == curChokeGroup)
				{
					if(gateLoopChokes)
						m.looper.stopLoop(k);
					else
						m.looper.stopLoopsOnNextStep[k] = true;
				
					//All of the loops in the same choke group will start recording at the same time
					if(loopSequences[x].getStatus() == MonomeUp.CUED && loopSequences[k].getStatus() == MonomeUp.CUED)
					loopSequences[k].startRecording();
				}
			}
		}
		
		loopSequences[x].addEvent(y);
		m.looper.stopLoopsOnNextStep[x] = false;
		m.looper.playLoop(x, y);
		//System.out.println("Playing loop " + x);
	}
	
	private void pressNavCol(int y)
	{
		if(loopSequences[getSubMenuFromYCoord(y)].getStatus() == MonomeUp.STOPPED)
		{
			loopSequences[getSubMenuFromYCoord(y)].beginCue();
		}
		else if(loopSequences[getSubMenuFromYCoord(y)].getStatus() == MonomeUp.CUED)
		{
			loopSequences[getSubMenuFromYCoord(y)].stop();
		}
		else if(loopSequences[getSubMenuFromYCoord(y)].getStatus() == MonomeUp.PLAYING)
		{
			loopSequences[getSubMenuFromYCoord(y)].stop();
			m.looper.stopLoop(getSubMenuFromYCoord(y));
		}
		else if(loopSequences[getSubMenuFromYCoord(y)].getStatus() == MonomeUp.RECORDING)
		{
			loopSequences[getSubMenuFromYCoord(y)].endRecording();
			m.looper.stopLoop(getSubMenuFromYCoord(y));
			loopSequences[getSubMenuFromYCoord(y)].play();
			//Play the first offset
			Integer sequencedStep = loopSequences[getSubMenuFromYCoord(y)].heartbeat();
			if(sequencedStep != null && sequencedStep != -1)
			{
				m.looper.playLoop(getSubMenuFromYCoord(y), sequencedStep);
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
				curChokeGroup = m.looper.getLoop(i).getChokeGroup();
				if(curChokeGroup > -1)
				{
					m.looper.playLoop(i, sequencedStep);
					for(int k=0;k<7;k++)
					{
						if(k!=i && m.looper.getLoop(k).getChokeGroup() == curChokeGroup)
							m.looper.setLoopStopOnNextStep(k);
					}
					
				}
				else
					m.looper.playLoop(i, sequencedStep);
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
			m.looper.playLoop(loopIndex, sequencedStep);
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
