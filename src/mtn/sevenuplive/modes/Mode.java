package mtn.sevenuplive.modes;


public abstract class Mode implements ModeConstants{
	
	protected int myNavRow;
	protected int displayGrid[][];
	protected int navGrid[];
	
	protected int grid_width;
	protected int grid_height;
	
	/**
	 * Abstract class for each mode
	 * @param navRow The row to use for navigation
	 * @param the mode  
	 * @param grid_width total width of the monome grid
	 * @param grid_height total height of the monome grid
	 */
	public Mode(int navRow, int grid_width, int grid_height)
	{
		this.grid_width = grid_width;
		this.grid_height = grid_height;
		
		this.myNavRow = navRow;
		displayGrid = new int[grid_width - 1][grid_height];
		navGrid = new int[grid_height];
		
		// startup uses -1 as nav row
		if (navRow > 0 && navRow < grid_height)
			navGrid[myNavRow] = DisplayGrid.SOLID;
	}
	
	public int getMyNavRow() {
		return myNavRow;
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
				displayGrid[i][j] = DisplayGrid.OFF;
	}
	
	protected void clearNavGrid()
	{
		for(int i=0;i<navGrid.length;i++)
			navGrid[i] = DisplayGrid.OFF;
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
