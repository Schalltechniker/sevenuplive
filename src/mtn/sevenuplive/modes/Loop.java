package mtn.sevenuplive.modes;

import org.jdom.*;

public class Loop {
	private int resolution =1;
	private int step;
	private int startOffset;
	private int resCounter;
	private int pressedRow;
	private int chokeGroup;
	
	//Specifies where a loop's loop can start and end
	//private int loopStartStep;
	//private int loopEndStep;
	
	public Loop()
	{
		step = -1; //not playing currently
		chokeGroup = -1; //Default to no choke group
	}
	
	public boolean isPlaying()
	{
		return step != -1;
	}
	
	public int getStep()
	{
		return step;
	}
		
	public void stop()
	{
		step = -1;
	}

	public int getResolution()
	{
		return resolution;
	}
	
	public void setResolution(int _resolution)
	{
		resolution = _resolution;
	}
	
	public void setStep(int _step)
	{
		step = _step;

		resCounter = 0;
		if(step > 7)
			step = 0;
	}
	
	public int getStartOffset()
	{
		return startOffset;
	}
	
	public void setStartOffset(int _startOffset)
	{
		startOffset = _startOffset;
	}
	
	public void increaseResCount()
	{
		resCounter++;
		if (resCounter % (resolution +1) == 0)
		{
			resCounter = 0;
			setStep(step + 1);
		}
	}
	
	public int getResCounter()
	{
		return resCounter; 
	}
	
	public int getPressedRow()
	{
		return pressedRow;
	}
	
	public void setPressedRow(int _pressedRow)
	{
		pressedRow = _pressedRow;
	}

	
	public Element toJDOMXMLElement()
	{
		Element xmlLoop = new Element("loop");
		
		xmlLoop.setAttribute(new Attribute("resolution", ((Integer)resolution).toString()));
		xmlLoop.setAttribute(new Attribute("startOffset",((Integer)startOffset).toString()));
		xmlLoop.setAttribute(new Attribute("resCounter",((Integer)resCounter).toString()));
		xmlLoop.setAttribute(new Attribute("pressedRow",((Integer)pressedRow).toString()));
		xmlLoop.setAttribute(new Attribute("chokeGroup", ((Integer)chokeGroup).toString()));
		
		return xmlLoop;
	}
	
	public void loadJDOMXMLElement(Element xmlLoop)
	{
		step = -1; //not playing currently
		
		resolution = Integer.parseInt(xmlLoop.getAttributeValue("resolution"));
		startOffset = Integer.parseInt(xmlLoop.getAttributeValue("startOffset"));
		resCounter = Integer.parseInt(xmlLoop.getAttributeValue("resCounter"));
		pressedRow = Integer.parseInt(xmlLoop.getAttributeValue("pressedRow"));
		try
		{
			chokeGroup = Integer.parseInt(xmlLoop.getAttributeValue("chokeGroup"));
		}catch(Exception e)
		{
			System.out.println("Missing chokeGroup Param");
		}
		
	}
	
	public int getChokeGroup()
	{
		return chokeGroup;
	}
	
	public void setChokeGroup(int _chokeGroup)
	{
		chokeGroup = _chokeGroup;
	}
	
	
	
}
