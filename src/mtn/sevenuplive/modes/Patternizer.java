package mtn.sevenuplive.modes;


import promidi.*;
import org.jdom.*;
import java.util.*;

public class Patternizer extends Mode {
	
	private int patternGrids[][][];
	private int basePitch = mtn.sevenuplive.main.MonomeUp.c3;
	private int pressedNavButtons[];
	public int selectedPattern = 0;
	public int curPatternRow = 0;
	private MidiOut midiSampleOut;

	public Patternizer(int _navRow, promidi.MidiOut _midiSampleOut) {
		super(_navRow);
		
		midiSampleOut = _midiSampleOut;
		patternGrids = new int[7][7][8];
		pressedNavButtons = new int[8];
		
		updateDisplayGrid();
	}

	public void updateDisplayGrid()
	{
		//Update navcol
		super.clearNavGrid();
		navGrid[getYCoordFromSubMenu(selectedPattern)] = fastBlink;
		navGrid[myNavRow] = solid;
		
		//Update display grid
		displayGrid = patternGrids[selectedPattern];
	}
	
	public void press(int x, int y)
	{
		if(x == navCol)
			pressNavCol(y);
		else
		{
			//If pressed, change from off to fast, or fast to solid, or solid to off
			 if (patternGrids[selectedPattern][x][y] == off)
				 patternGrids[selectedPattern][x][y] = fastBlink;
			 else if (patternGrids[selectedPattern][x][y] == fastBlink)
				 patternGrids[selectedPattern][x][y] = solid;
			 else if (patternGrids[selectedPattern][x][y] == solid)
				 patternGrids[selectedPattern][x][y] = off;
		}
	}
	
	public void release(int x, int y)
	{
		pressedNavButtons[y] = 0;
	}
	
	private void pressNavCol(int y)
	{
		pressedNavButtons[y] = 1;

		//Handle copying a pattern to another bank
   	 	if (getSubMenuFromYCoord(y) != selectedPattern && pressedNavButtons[getYCoordFromSubMenu(selectedPattern)] >=1 )
   	 	{
   	 		System.out.println("Copying " + selectedPattern + " to " + getSubMenuFromYCoord(y));
   			 //Copy pattern
   		 	for(int i=0;i<7;i++)
   		 		for(int j=0;j<8;j++)
   		 			patternGrids[getSubMenuFromYCoord(y)][i][j] = patternGrids[selectedPattern][i][j];
   	 	}
   	  
		//If they are changing patterns unselect current and select the new pattern
		if(selectedPattern != getSubMenuFromYCoord(y))
		{
			navGrid[getYCoordFromSubMenu(selectedPattern)] = off;
			selectedPattern = getSubMenuFromYCoord(y);
	        updateDisplayGrid();
		}
	}
	
	
	public void triggerButtonHeld(int x, int y)
	{
		patternGrids[getSubMenuFromYCoord(y)] = new int[7][8];
		selectedPattern = getSubMenuFromYCoord(y);
		updateDisplayGrid();
		System.out.println("Clearing pattern grid " + getSubMenuFromYCoord(y));
	}
	
	/***
	 * 
	 * @param curPatterns array of booleans indicating which patterns are to step
	 * @return true if the pattern starts over, false if not
	 */
	public boolean step(boolean[] curPatterns)
	{
		int sendPitch;
		int sendVel;
		Note noteSend;
		
		//For all currently playing patterns...
		for(int i=0;i<curPatterns.length;i++)
		{
			if(curPatterns[i])
			{
			//Play pattern samples
	        for(int x = 0; x < 7; x++)
	        {
	          if(patternGrids[i][x][curPatternRow] != off)
	          {
	            //set pitch
	            switch(x)
	            {
	              case 0: sendPitch = basePitch; break;
	              case 1: sendPitch = basePitch + 1; break;
	              case 2: sendPitch = basePitch + 2; break;
	              case 3: sendPitch = basePitch + 3; break;
	              case 4: sendPitch = basePitch + 4; break;
	              case 5: sendPitch = basePitch + 5; break;
	              case 6: sendPitch = basePitch + 6; break;
	              default: sendPitch = basePitch;
	            }
	
	            if (patternGrids[i][x][curPatternRow] == fastBlink)
	            	sendVel = 42;
	            else sendVel = 126;
	
	            noteSend = new Note(sendPitch,sendVel, 0);
	            
	            midiSampleOut.sendNoteOn(noteSend);
	          } 
	        }
			}
		}
        
        curPatternRow++;
        if(curPatternRow == 8)
        {
        	curPatternRow = 0;
        	return true;
        }
        else
        	return false;
	
	}

	public Element toJDOMXMLElement()
	{
		 Element xmlPatternizer = new Element("patternizer");
		 Element xmlPattern;
		 Element xmlKeyPress;
		 
		 xmlPatternizer.setAttribute(new Attribute("selectedPattern", (((Integer)selectedPattern).toString())));
	 	
	 	for(Integer i=0;i<patternGrids.length;i++)
	 	{
	 			xmlPattern = new Element("pattern");
	 			xmlPattern.setAttribute(new Attribute("patternNum", i.toString()));
	 			
		 		for(int j=0;j<patternGrids[i].length;j++)
		 		{
		 			for(int k=0;k<patternGrids[i][j].length;k++)
		 			{
		 				if(patternGrids[i][j][k] != mtn.sevenuplive.main.MonomeUp.off)
		 				{
		 					xmlKeyPress = new Element("keyPress");
		 					xmlKeyPress.setAttribute(new Attribute("col", ((Integer)j).toString()));
		 					xmlKeyPress.setAttribute(new Attribute("row", ((Integer)k).toString()));
		 					Integer value = patternGrids[i][j][k];
		 					xmlKeyPress.setAttribute(new Attribute("value", value.toString()));
		 					xmlPattern.addContent(xmlKeyPress);
		 				}
		 			}
		 		}
		 		
		 		xmlPatternizer.addContent(xmlPattern);
	 	}

		return xmlPatternizer;
	}
	
	public void loadJDOMXMLElement(Element xmlPatternizer)
	{
		//Clear current values
		patternGrids = new int[7][7][8];
		
		List xmlPatterns;
		List xmlKeyPresses;
		Iterator itrKeyPresses;
		Integer col;
		Integer row;
		Integer value;
		Element xmlPattern;
		Element xmlKeyPress;
		 
		selectedPattern = Integer.parseInt(xmlPatternizer.getAttribute("selectedPattern").getValue());
		 
		xmlPatterns = xmlPatternizer.getChildren();
		Iterator itrPatterns = xmlPatterns.iterator();
		while(itrPatterns.hasNext())
		{
			xmlPattern = (Element)itrPatterns.next();
			Integer patternNum = Integer.parseInt(xmlPattern.getAttribute("patternNum").getValue());
			
			xmlKeyPresses = xmlPattern.getChildren();
			itrKeyPresses = xmlKeyPresses.iterator();
			while(itrKeyPresses.hasNext())
			{
				xmlKeyPress = (Element)itrKeyPresses.next();
				col = Integer.parseInt(xmlKeyPress.getAttributeValue("col"));
				row = Integer.parseInt(xmlKeyPress.getAttributeValue("row"));
				value = Integer.parseInt(xmlKeyPress.getAttributeValue("value"));
				
				patternGrids[patternNum][col][row] = value;
			}
		}
	}

}
