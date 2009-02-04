package mtn.sevenuplive.modes;

import org.jdom.*;
import java.util.*;

public class Sequencer extends Mode {
	
	//Array of sequences[sequenceID][sequenceRowNum] = PatternNum
	public int curSequenceBank=0;
	public int nextSequence=0;
	public int curSeqRow=0;
	
	private SequenceBank[] sequenceBanks;
	
	mtn.sevenuplive.main.MonomeUp m;
	Patternizer patternizer;
	
	public Sequencer(int _navRow, mtn.sevenuplive.main.MonomeUp _m) {
		super(_navRow);
		
		sequenceBanks = new SequenceBank[7];
		for(int i=0; i<7;i++)
			sequenceBanks[i] = new SequenceBank();
		displayGrid = new int[7][8];
		patternizer = _m.patternizer;
		m = _m;
		updateDisplay();
	}
	
	public void step(int pitch)
	{			
		//Step through patterns.  If the pattern rolls over, increment sequence row
		if(patternizer.step(sequenceBanks[curSequenceBank].getRow(curSeqRow)))
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
		
		if(x == navCol)
		{
			//Change sequence banks
			nextSequence = getSubMenuFromYCoord(y);
			curSequenceBank = getSubMenuFromYCoord(y);
	        updateDisplay();
		}
		else
		//Pressing display area
		{
			sequenceBanks[curSequenceBank].switchPatternAtRow(y, x);
			updateDisplay();
		}
	}
	
	public void reset()
	{
		patternizer.curPatternRow = 0;
	}
	
	private void updateDisplay()
	{
		//Update navcol
		super.clearNavGrid();
		navGrid[getYCoordFromSubMenu(curSequenceBank)] = fastBlink;
		navGrid[myNavRow] = solid;
		
		//Update display grid
		super.clearDisplayGrid();
		for(int i=0;i<8;i++)
			for(int j=0;j<7;j++)
				if(sequenceBanks[curSequenceBank].getRow(i)[j])
					displayGrid[j][i] = solid;
				else
					displayGrid[j][i] = off;
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
	
	public void loadJDOMXMLElement(Element xmlSequencer)
	{
		List xmlSequences;
		Iterator itrSequences;
		Element xmlSequenceBank;
		Integer sequenceNum; 
		
		curSequenceBank = Integer.parseInt(xmlSequencer.getAttributeValue("curSequence"));
		nextSequence = Integer.parseInt(xmlSequencer.getAttributeValue("nextSequence"));
	 	
		xmlSequences = xmlSequencer.getChildren();
		itrSequences = xmlSequences.iterator();
		
		while(itrSequences.hasNext())
		{
			xmlSequenceBank = (Element)itrSequences.next();
			sequenceNum = Integer.parseInt(xmlSequenceBank.getAttributeValue("sequenceBankNum"));
			
			sequenceBanks[sequenceNum].loadXml(xmlSequenceBank);
		}
		
		updateDisplay();
	}
	
}
