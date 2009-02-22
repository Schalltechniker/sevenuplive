package mtn.sevenuplive.modes;

import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;

public class Sequencer extends Mode {
	
	//Array of sequences[sequenceID][sequenceRowNum] = PatternNum
	public int curSequenceBank=0;
	public int nextSequence=0;
	public int curSeqRow=0;
	
	private SequenceBank[] sequenceBanks;
	
	public Sequencer(int _navRow, int grid_width, int grid_height) {
		super(_navRow, grid_width, grid_height);
		
		sequenceBanks = new SequenceBank[7];
		for(int i=0; i<7;i++)
			sequenceBanks[i] = new SequenceBank();
		displayGrid = new int[7][8];
		updateDisplay();
	}
	
	public void step()
	{			
		//Step through patterns.  If the pattern rolls over, increment sequence row
		if(AllModes.patternizer.step(sequenceBanks[curSequenceBank].getRow(curSeqRow)))
		{
			//If a next sequence was externally set, change to that sequence rather than incrementing through sequence rows
			if(nextSequence != curSequenceBank)
			{
				curSequenceBank = nextSequence;
				curSeqRow = 0;
				//Update masterizer here
			}
			else
			{
					curSeqRow++;
					if(curSeqRow == 8)
						curSeqRow = 0;
			}
		}
	}

	public boolean[] getCurrentPatterns()
	{
		return sequenceBanks[curSequenceBank].getRow(curSeqRow);
	}
	
	public void press(int x, int y)
	{
		
		if(x == DisplayGrid.NAVCOL)
		{
			//Change sequence banks
			nextSequence = getSubMenuFromYCoord(y);
			curSequenceBank = getSubMenuFromYCoord(y);
	        updateDisplay();
		}
		else
		//Pressing display area
		{
			if(sequenceBanks[curSequenceBank].isPatternEnabledAtRow(x, y)){
				AllModes.patternizer.curPatternRow = 0;
				curSeqRow = y;
			}
			else
			{
				for(int i=0;i<7;i++)
					sequenceBanks[curSequenceBank].disablePatternAtRow(y, i);
				sequenceBanks[curSequenceBank].enablePatternAtRow(y, x);
				updateDisplay();
			}
			
		}
	}
	
	public void reset()
	{
		AllModes.patternizer.curPatternRow = 0;
	}
	
	private void updateDisplay()
	{
		//Update navcol
		super.clearNavGrid();
		navGrid[getYCoordFromSubMenu(curSequenceBank)] = DisplayGrid.FASTBLINK;
		navGrid[myNavRow] = DisplayGrid.SOLID;
		
		//Update display grid
		super.clearDisplayGrid();
		for(int i=0;i<grid_height;i++)
			for(int j=0;j<grid_width-1;j++)
				if(sequenceBanks[curSequenceBank].getRow(i)[j])
					displayGrid[j][i] = DisplayGrid.SOLID;
				else
					displayGrid[j][i] = DisplayGrid.OFF;
	}
	
	public boolean isPatternPlaying(int patNum)
	{
		return sequenceBanks[curSequenceBank].isPatternEnabledAtRow(patNum, curSeqRow);
	}
	
	
	public Element toJDOMXMLElement()
	{
		Element xmlSequencer = new Element("sequencer");
		Element xmlSequenceBank;
		 
	 	xmlSequencer.setAttribute(new Attribute("curSequence", ((Integer)curSequenceBank).toString()));
	 	xmlSequencer.setAttribute(new Attribute("nextSequence", ((Integer)nextSequence).toString()));
	 	
 		for(Integer j=0;j<sequenceBanks.length;j++)
 		{
 			xmlSequenceBank = sequenceBanks[j].toXmlElement();
 			xmlSequenceBank.setAttribute(new Attribute("sequenceBankNum", j.toString()));
 			xmlSequencer.addContent(xmlSequenceBank);
  		}

		return xmlSequencer;
	}
	
	@SuppressWarnings("unchecked")
	public void loadJDOMXMLElement(Element xmlSequencer)
	{
		List<Element> xmlSequences;
		Integer sequenceNum; 
		
		curSequenceBank = xmlSequencer.getAttributeValue("curSequence") == null ? 0 : Integer.parseInt(xmlSequencer.getAttributeValue("curSequence"));
		nextSequence = xmlSequencer.getAttributeValue("nextSequence") == null ? 0 : Integer.parseInt(xmlSequencer.getAttributeValue("nextSequence"));
	 	
		xmlSequences = xmlSequencer.getChildren();
		
		int index = 0;
		for (Element xmlSequenceBank : xmlSequences)
		{
			sequenceNum = xmlSequenceBank.getAttributeValue("sequenceBankNum") == null ? index : Integer.parseInt(xmlSequenceBank.getAttributeValue("sequenceBankNum"));
			
			sequenceBanks[sequenceNum].loadXml(xmlSequenceBank);
			index++;
		}
		
		updateDisplay();
	}
	
}