package mtn.sevenuplive.modes;

public class ControllerView extends Mode {

	private int curBank = 0;
	private ControllerModel controllerModel;
	
	public ControllerView(int _navRow,int grid_width, int grid_height, ControllerModel controllerModel) {
		super(_navRow, grid_width, grid_height);
		this.controllerModel = controllerModel;
	    updateDisplayGrid();
	    updateNavGrid();
	}
	
	public void updateDisplayGrid()
	{
		super.clearDisplayGrid();
		//Loop through the controls in a control bank and set the y coordinate on the display grid
		for(int i=0;i<7;i++)
		{
			//Only set the display if the control is > 0
			if(controllerModel.controls[curBank][i] > 0)
				for(int j=7;j>=8-controllerModel.controls[curBank][i];j--)
					displayGrid[i][j] = DisplayGrid.SOLID;
		}
	}
	
	private void updateNavGrid()
	{
		clearNavGrid();
		navGrid[myNavRow] = DisplayGrid.SOLID;
		navGrid[getYCoordFromSubMenu(curBank)] = DisplayGrid.FASTBLINK;
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
		if(x == DisplayGrid.NAVCOL)
			pressNavCol(y);
		else
			controllerModel.press(x, y, curBank);
		
		updateDisplayGrid();
		updateNavGrid();
	}
	
	private void pressNavCol(int y) {
		//If changing to a different sequence
		if(curBank != getSubMenuFromYCoord(y))
			curBank = getSubMenuFromYCoord(y);

	}
	

	
	
}
