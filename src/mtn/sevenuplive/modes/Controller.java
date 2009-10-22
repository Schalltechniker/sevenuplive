package mtn.sevenuplive.modes;
import mtn.sevenuplive.m4l.M4LController;
import mtn.sevenuplive.m4l.M4LMidiOut;

public class Controller extends Mode {
	private int curBank = 0;
	private M4LMidiOut midiControlOut;
	private Integer controls[][];
	private Integer startingController;
	
	public Controller(int _navRow, M4LMidiOut _midiControlOut, int _startingController, int grid_width, int grid_height) {
		super(_navRow, grid_width, grid_height);
		midiControlOut = _midiControlOut;
		controls = new Integer[7][7];
		startingController = _startingController;
		
		//Initialize controls to -1 for "not set"
		for(int j=0;j<7;j++)
			for(int i=0;i<7;i++)
				controls[j][i] = -1;
	     
	    updateDisplayGrid();
	    updateNavGrid();
	}
	
	private void updateDisplayGrid()
	{
		super.clearDisplayGrid();
		//Loop through the controls in a control bank and set the y coordinate on the display grid
		for(int i=0;i<7;i++)
		{
			//Only set the display if the control is > 0
			if(controls[curBank][i] > 0)
				for(int j=7;j>=8-controls[curBank][i];j--)
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
			pressDisplay(x,y);
		
		updateDisplayGrid();
		updateNavGrid();
	}
	
	private void pressDisplay(int x, int y) {
		   //If they hit the bottom row twice, clear the column
	       if(y==7 && controls[curBank][x] == 1)
	       {
	    	   controls[curBank][x] = 0;
	       }
	       //Otherwise set the value to 8-y
	       else
	       { 
	    	   controls[curBank][x] = 8-y;
	       }
	       
	       updateDisplayGrid();
	       
	       //Send the control value for the previous selected column
	       sendMidiControls(curBank, x);
	}

	private void pressNavCol(int y) {
		//If changing to a different sequence
		if(curBank != getSubMenuFromYCoord(y))
			curBank = getSubMenuFromYCoord(y);

	}
	
	private void sendMidiControls(int bank, int x)
	 {
		   int controlValue = controls[bank][x] * 16;
		   //Can not send 128 as a value
		   if(controlValue == 128)
			   controlValue = 127;
		   
		   //send controlvalue to control corresponding to the current control grid and the column
		   midiControlOut.sendController(new M4LController(startingController + x + (bank * 7), controlValue));
	 }

	public boolean bankHasValues(int bank) {
		if(bank < 7) //Only check 7 banks (0-6)
		for(int j =0; j<7;j++)
		{
			if(controls[bank][j] > -1)return true;
		}
		
		return false;
	}

	public void sendAllBankValues(int bank) {
		for(int i =0; i<7;i++)
		{
			if(controls[bank][i] > -1)
				sendMidiControls(bank, i);
		}		
	}
	
	
}
