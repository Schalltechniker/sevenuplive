package mtn.sevenuplive.modes;

import mtn.sevenuplive.main.MonomeUp;

public abstract class Mode {
	
	protected int myNavRow;
	protected int displayGrid[][];
	protected int navGrid[];
	
	public Mode(int _navRow)
	{
		myNavRow = _navRow;
		displayGrid = new int[7][8];
		navGrid = new int[8];
		navGrid[myNavRow] = MonomeUp.SOLID;
	}
	
	public int[][] getDisplayGrid()
	{
		return displayGrid;
	}
	
	public int[] getNavGrid()
	{
		return navGrid;
	}
	
	protected void clearDisplayGrid()
	{
		for(int i=0;i<displayGrid.length;i++)
			for(int j=0;j<displayGrid[0].length;j++)
				displayGrid[i][j] = MonomeUp.OFF;
	}
	
	protected void clearNavGrid()
	{
		for(int i=0;i<navGrid.length;i++)
			navGrid[i] = MonomeUp.OFF;
	}
	
	protected int getYCoordFromSubMenu(int subMenuNum)
	{
		if(subMenuNum >= myNavRow)
			return subMenuNum + 1;
		else return subMenuNum;
	}
	
	protected int getSubMenuFromYCoord(int ycoord)
	{
		if(ycoord >= myNavRow)
			return ycoord - 1;
		else return ycoord;
	}
	



}
