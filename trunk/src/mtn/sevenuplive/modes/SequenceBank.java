package mtn.sevenuplive.modes;

import java.util.Iterator;
import java.util.List;

import org.jdom.*;

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
	
	public boolean[] getRow(int rowNum)
	{
		return rowPatterns[rowNum];
	}
	
	public boolean isPatternEnabledAtRow(int patNum, int rowNum)
	{
		return rowPatterns[rowNum][patNum];
	}

	public void loadXml(Element xmlSequenceBank) {
		
		//Clear current patterns
		rowPatterns = new boolean[8][7];

		List xmlRows;
		List xmlPatterns;
		Iterator itrRows;
		Iterator itrPatterns;
		Element xmlRow;
		Element xmlPattern;
		Integer rowNum;
		Integer patternNum;
		
		xmlRows = xmlSequenceBank.getChildren();
		itrRows = xmlRows.iterator();
		while(itrRows.hasNext())
		{
			xmlRow = (Element)itrRows.next();
			rowNum = Integer.parseInt(xmlRow.getAttributeValue("row"));
			xmlPatterns = xmlRow.getChildren();
			itrPatterns = xmlPatterns.iterator();
			while(itrPatterns.hasNext())
			{
				xmlPattern = (Element)itrPatterns.next();
				patternNum = Integer.parseInt(xmlPattern.getAttributeValue("patternNum"));
				rowPatterns[rowNum][patternNum] = true;
			}
			
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
