package mtn.sevenuplive.modes;


import java.util.List;

import mtn.sevenuplive.main.MonomeUp;

import org.jdom.Attribute;
import org.jdom.Element;

import promidi.MidiOut;
import promidi.Note;

public class Patternizer extends Mode {
	
	private int patternGrids[][][];
	private int basePitch = MonomeUp.C3;
	private int pressedNavButtons[];
	public int selectedPattern = 0;
	public int curPatternRow = 0;
	private MidiOut midiSampleOut;

	public Patternizer(int _navRow, promidi.MidiOut _midiSampleOut, int grid_width, int grid_height) {
		super(_navRow, grid_width, grid_height);
		
		midiSampleOut = _midiSampleOut;
		patternGrids = new int[7][7][8];
		pressedNavButtons = new int[8];
		
		updateDisplayGrid();
	}

	public void updateDisplayGrid()
	{
		//Update navcol
		super.clearNavGrid();
		navGrid[getYCoordFromSubMenu(selectedPattern)] = MonomeUp.FASTBLINK;
		navGrid[myNavRow] = MonomeUp.SOLID;
		
		//Update display grid
		displayGrid = patternGrids[selectedPattern];
	}
	
	public void press(int x, int y)
	{
		if(x == MonomeUp.NAVCOL)
			pressNavCol(y);
		else
		{
			//If pressed, change from off to fast, or fast to solid, or solid to off
			 if (patternGrids[selectedPattern][x][y] == MonomeUp.OFF)
				 patternGrids[selectedPattern][x][y] = MonomeUp.FASTBLINK;
			 else if (patternGrids[selectedPattern][x][y] == MonomeUp.FASTBLINK)
				 patternGrids[selectedPattern][x][y] = MonomeUp.SOLID;
			 else if (patternGrids[selectedPattern][x][y] == MonomeUp.SOLID)
				 patternGrids[selectedPattern][x][y] = MonomeUp.OFF;
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
			navGrid[getYCoordFromSubMenu(selectedPattern)] = MonomeUp.OFF;
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
	          if(patternGrids[i][x][curPatternRow] != MonomeUp.OFF)
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
	
	            if (patternGrids[i][x][curPatternRow] == MonomeUp.FASTBLINK)
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
		 				if(patternGrids[i][j][k] != MonomeUp.OFF)
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
	
	@SuppressWarnings("unchecked")
	public void loadJDOMXMLElement(Element xmlPatternizer)
	{
		//Clear current values
		patternGrids = new int[7][7][8];
		
		List<Element> xmlPatterns;
		List<Element> xmlKeyPresses;
		Integer col;
		Integer row;
		Integer value;
		 
		selectedPattern = Integer.parseInt(xmlPatternizer.getAttribute("selectedPattern").getValue());
		 
		xmlPatterns = xmlPatternizer.getChildren();
		for (Element xmlPattern : xmlPatterns)
		{
			Integer patternNum = Integer.parseInt(xmlPattern.getAttribute("patternNum").getValue());
			
			xmlKeyPresses = xmlPattern.getChildren();
			for(Element xmlKeyPress : xmlKeyPresses)
			{
				col = Integer.parseInt(xmlKeyPress.getAttributeValue("col"));
				row = Integer.parseInt(xmlKeyPress.getAttributeValue("row"));
				value = Integer.parseInt(xmlKeyPress.getAttributeValue("value"));
				
				patternGrids[patternNum][col][row] = value;
			}
		}
	}

}
