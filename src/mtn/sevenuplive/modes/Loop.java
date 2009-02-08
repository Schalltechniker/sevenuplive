package mtn.sevenuplive.modes;

import org.jdom.Attribute;
import org.jdom.Element;

public class Loop {
	
	/** Value indicating the loop is not playing */ 
	public static final int NOT_PLAYING = -1;
	
	/** Value indicating choke group is off */ 
	public static final int NO_CHOKE_GROUP = -1;
	
	/** Loop resolution which can be from (1 * resMultiplier) to (8 * resMultiplier) measures long */
	private int resolution = DEFAULT_RESOLUTION;
	private final static int DEFAULT_RESOLUTION = 1;
	
	/** Multiplier for loop resolution. Multiplies the full range of the loops resolution */
	private int resMultiplier = 1;
	private final static int DEFAULT_RES_MULTIPLIER = 1;
	
	/** Current step index or {@link #NOT_PLAYING} */
	private int step;
	private int startOffset;
	private int resCounter;
	
	private int pressedRow;
	
	/** Current chokeGroup or {@link #NO_CHOKE_GROUP} */
	private int chokeGroup;
	
	public Loop()
	{
		step = NOT_PLAYING; //not playing currently
		chokeGroup = NO_CHOKE_GROUP; //Default to no choke group
	}
	
	public boolean isPlaying()
	{
		return step != NOT_PLAYING;
	}
	
	public int getStep()
	{
		return step;
	}
		
	public void stop()
	{
		step = NOT_PLAYING;
	}

	public int getResolution()
	{
		return resolution;
	}
	
	public void setResolution(int resolution)
	{
		this.resolution = resolution;
	}
	
	/**
	 * Increment to the next step.
	 * This sets the current step to the new value.
	 * @param step
	 */
	public void nextStep() {
		step++;
		if(this.step > 7)
			this.step = 0;
		
	}
	
	/**
	 * Sets the current step
	 * 
	 * @param step
	 */
	public void setStep(int step)
	{
		if (step > 7)
			throw new IndexOutOfBoundsException("Step [" + Integer.toString(step) + "] is out of range: [0-" + Integer.toString(7) + "]"); 
			
		this.step = step;
	}
	
	public int getStartOffset()
	{
		return startOffset;
	}
	
	public void setStartOffset(int startOffset)
	{
		this.startOffset = startOffset;
	}
	
	public int getResCounter() {
		return resCounter;
	}
	
	public int getChokeGroup()
	{
		return chokeGroup;
	}
	
	public void setChokeGroup(int chokeGroup)
	{
		this.chokeGroup = chokeGroup;
	}
	
	/**
	 * Go to the next resCount.
	 * This will cycle back to zero when we hit the end.
	 */
	public void nextResCount()
	{
		resCounter++;
		if (resCounter % ((resolution + 1) * resMultiplier) == 0)
		{
			resCounter = 0;
			nextStep();
		}
	}

	public int getResMultiplier() {
		return resMultiplier;
	}

	public void setResMultiplier(int resMultiplier) {
		this.resMultiplier = resMultiplier;
	}

	public int getPressedRow()
	{
		return pressedRow;
	}
	
	public void setPressedRow(int pressedRow)
	{
		this.pressedRow = pressedRow;
	}

	
	public Element toJDOMXMLElement()
	{
		Element xmlLoop = new Element("loop");
		
		xmlLoop.setAttribute(new Attribute("resolution", ((Integer)resolution).toString()));
		xmlLoop.setAttribute(new Attribute("resmultiplier", ((Integer)resMultiplier).toString()));
		xmlLoop.setAttribute(new Attribute("startOffset",((Integer)startOffset).toString()));
		xmlLoop.setAttribute(new Attribute("resCounter",((Integer)resCounter).toString()));
		xmlLoop.setAttribute(new Attribute("pressedRow",((Integer)pressedRow).toString()));
		xmlLoop.setAttribute(new Attribute("chokeGroup", ((Integer)chokeGroup).toString()));
		
		return xmlLoop;
	}
	
	public void loadJDOMXMLElement(Element xmlLoop)
	{
		step = NOT_PLAYING; //not playing currently
		
		resolution = xmlLoop.getAttributeValue("resolution") == null ? DEFAULT_RESOLUTION : Integer.parseInt(xmlLoop.getAttributeValue("resolution"));
		resMultiplier = xmlLoop.getAttributeValue("resMultiplier") == null ? DEFAULT_RES_MULTIPLIER : Integer.parseInt(xmlLoop.getAttributeValue("resMultiplier"));
		startOffset = xmlLoop.getAttributeValue("startOffset") == null ? ModeConstants.NOT_SET : Integer.parseInt(xmlLoop.getAttributeValue("startOffset"));
		resCounter = xmlLoop.getAttributeValue("resCounter") == null ? ModeConstants.NOT_SET : Integer.parseInt(xmlLoop.getAttributeValue("resCounter"));
		pressedRow = xmlLoop.getAttributeValue("pressedRow") == null ? ModeConstants.NOT_SET : Integer.parseInt(xmlLoop.getAttributeValue("pressedRow"));
		chokeGroup = xmlLoop.getAttributeValue("chokeGroup") == null ? NO_CHOKE_GROUP: Integer.parseInt(xmlLoop.getAttributeValue("chokeGroup"));
		
		
	}
	
}
