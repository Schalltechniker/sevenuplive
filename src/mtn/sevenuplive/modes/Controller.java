package mtn.sevenuplive.modes;
import promidi.*;
import org.jdom.*;
import java.util.*;

public class Controller extends Mode {

	private MidiOut midiControlOut;
	public Integer curControlBank  = 0;
	private Integer controls[][];
	private Integer startingController;
	
	public Controller(int _navRow, MidiOut _midiControlOut, int _startingController) {
		super(_navRow);
		midiControlOut = _midiControlOut;
		controls = new Integer[7][7];
		startingController = _startingController;
		
		//Initialize controls to -1 for "not set"
	     for(int i=0; i<7; i++)
	       for(int j=0;j<7;j++)
	           controls[i][j] = -1;
	     
	     navGrid[curControlBank] = fastBlink;
	     updateDisplay();
	}
	
	private void updateDisplay()
	{
		super.clearDisplayGrid();
		//Loop through the controls in a control bank and set the y coordinate on the display grid
		for(int i=0;i<7;i++)
		{
			//Only set the display if the control is > 0
			if(controls[curControlBank][i] > 0)
				for(int j=7;j>=8-controls[curControlBank][i];j--)
					displayGrid[i][j] = solid;
		}
	}
	
	private void updateNavGrid()
	{
		super.clearNavGrid();
		navGrid[getYCoordFromSubMenu(curControlBank)] = fastBlink;
		navGrid[myNavRow] = solid;
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
		if (x == navCol)
		{
			curControlBank = getSubMenuFromYCoord(y);
			updateNavGrid();
			updateDisplay();
		}
		else
		{
			 //If they hit the bottom row twice, clear the column
		       if(y==7 && controls[curControlBank][x] == 1)
		       {
		    	   controls[curControlBank][x] = 0;
		       }
		       //Otherwise set the value to 8-y
		       else
		       { 
		    	   controls[curControlBank][x] = 8-y;
		       }
		       
		       updateDisplay();
		       
		       //Send the control value for the previous selected column
		       sendMidiControls(curControlBank, x);
		}
	}
	
	public void sendMidiControlBank(int controlBank)
	{
		for(int i =0;i<7;i++)
		{
			if(controls[controlBank][i] > -1)
				sendMidiControls(controlBank, i);
		}
	}
	
	private void sendMidiControls(int controlBank, int x)
	 {
		   int controlValue = controls[controlBank][x] * 16;
		   //Can not send 128 as a value
		   if(controlValue == 128)
			   controlValue = 127;
		   
		   //send controlvalue to control corresponding to the current control grid and the column
		   midiControlOut.sendController(new promidi.Controller(startingController + x, controlValue));
	 }
	
	//X is the Y button being held down by a user
	public void clearControlBank(int x)
	{
		for(int i=0;i<7;i++)
			controls[getSubMenuFromYCoord(x)][i] = -1;
		updateDisplay();
	}
	
	//Returns true if the bank has any control values > -1
	public boolean isBankEnabled(int x)
	{
		for(int i=0;i<7;i++)
			if(controls[x][i] > -1)
				return true;
		
		return false;	
	}
	
	public Element toJDOMXMLElement()
	{
		Element xmlController;
		//TODO No longer necessary.  Only 1 controller
		if(myNavRow == 2)
			xmlController = new Element("controller");
		else
			xmlController = new Element("controller2");
		
		Element xmlControlBank;
		Element xmlControlValue;
		
		xmlController.setAttribute(new Attribute("startingController", startingController.toString()));
		
		for(Integer i=0;i<7;i++)
		{
			xmlControlBank = new Element("controlBank");
			xmlControlBank.setAttribute(new Attribute("position", i.toString()));
			
			for(Integer j=0;j<7;j++)
			{
				xmlControlValue = new Element("controlValue");
				xmlControlValue.setAttribute(new Attribute("position", j.toString()));
				xmlControlValue.setAttribute(new Attribute("value", controls[i][j].toString()));
				
				xmlControlBank.addContent(xmlControlValue);
			}
			
			xmlController.addContent(xmlControlBank);
		}
		
		return xmlController;
	}
	
	@SuppressWarnings("unchecked")
	public void loadJDOMXMLElement(Element xmlController)
	{
		List<Element> xmlControlBanks;
		List<Element> xmlControlValues;
		Integer controlBankPosition;
		Integer controlValuePosition;
		Integer controlValueValue;
		
		startingController = Integer.parseInt(xmlController.getAttributeValue("startingController"));
		
		xmlControlBanks = xmlController.getChildren();
		
		for (Element xmlControlBank : xmlControlBanks)
		{
			controlBankPosition = Integer.parseInt(xmlControlBank.getAttributeValue("position"));
			
			xmlControlValues = xmlControlBank.getChildren();
			
			for (Element xmlControlValue: xmlControlValues)
			{
				controlValuePosition = Integer.parseInt(xmlControlValue.getAttributeValue("position"));
				controlValueValue = Integer.parseInt(xmlControlValue.getAttributeValue("value"));
				
				controls[controlBankPosition][controlValuePosition] = controlValueValue;
			}
		}
		
		updateDisplay();
	}
}
