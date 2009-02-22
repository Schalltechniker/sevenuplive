package mtn.sevenuplive.modes;

import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;

public class SequenceBank {
	
	private boolean rowPatterns[][];

	public SequenceBank()
	{
		rowPatterns = new boolean[8][7];
		//Set default to first pattern for each row
		for(int i=0;i<8;i++)
			enablePatternAtRow(i, 0);
	}
	
	public void enablePatternAtRow(int rowNum, int patNum)
	{
		rowPatterns[rowNum][patNum] = true;
	}
	
	public void disablePatternAtRow(int rowNum, int patNum)
	{
		rowPatterns[rowNum][patNum] = false;
	}
	
	public void switchPatternAtRow(int rowNum, int patNum)
	{
		rowPatterns[rowNum][patNum] = !rowPatterns[rowNum][patNum];
	}
	
	public int getEnabledPatternInRow(int rowNum)
	{
		for(int i=0;i<7;i++)
		{
			if(rowPatterns[rowNum][i])
				return i;
		}
		
		return 0;
	}
	
	public boolean isPatternEnabledAtRow(int patNum, int rowNum)
	{
		return rowPatterns[rowNum][patNum];
	}

	@SuppressWarnings("unchecked")
	public void loadXml(Element xmlSequenceBank) {
		
		//Clear current patterns
		rowPatterns = new boolean[8][7];

		List<Element> xmlRows;
		List<Element> xmlPatterns;
		Integer rowNum;
		Integer patternNum;
		
		xmlRows = xmlSequenceBank.getChildren();
		
		int outerindex = 0;
		for (Element xmlRow : xmlRows)
		{
			rowNum = xmlRow.getAttributeValue("row") == null ? outerindex : Integer.parseInt(xmlRow.getAttributeValue("row"));
			xmlPatterns = xmlRow.getChildren();
			
			int innerindex = 0;
			for (Element xmlPattern : xmlPatterns)
			{
				patternNum = xmlPattern.getAttributeValue("patternNum") == null ? innerindex : Integer.parseInt(xmlPattern.getAttributeValue("patternNum"));
				rowPatterns[rowNum][patternNum] = true;
				innerindex++;
			}
			outerindex++;
		}
	}
	
	public Element toXmlElement()
	{
		Element xmlSequenceBank;
		Element xmlSequenceRow;
		Element xmlPattern;
		
		xmlSequenceBank = new Element("sequenceBank");
		
		for(Integer i=0;i<rowPatterns.length;i++)
		{
			xmlSequenceRow = new Element("sequenceRow");
			xmlSequenceRow.setAttribute(new Attribute("row", i.toString()));
			for(Integer j=0;j<rowPatterns[i].length;j++)
			{
				if(rowPatterns[i][j])
				{
					xmlPattern = new Element("pattern");
					xmlPattern.setAttribute(new Attribute("patternNum", j.toString()));
					xmlSequenceRow.addContent(xmlPattern);
				}
			}
			xmlSequenceBank.addContent(xmlSequenceRow);
		}
		
		return xmlSequenceBank;
	}
}
