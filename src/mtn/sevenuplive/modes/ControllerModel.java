package mtn.sevenuplive.modes;
import promidi.MidiOut;

public class ControllerModel extends Mode {

	private MidiOut midiControlOut;
	public Integer controls[][];
	private Integer startingController;
	
	
	public ControllerModel(int _navRow, MidiOut _midiControlOut, int _startingController, int grid_width, int grid_height) {
		super(_navRow, grid_width, grid_height);
		midiControlOut = _midiControlOut;
		controls = new Integer[7][7];
		startingController = _startingController;
		
		//Initialize controls to -1 for "not set"
		for(int j=0;j<7;j++)
			for(int i=0;i<7;i++)
				controls[j][i] = -1;
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
	public void press(int x, int y, int curBank)
	{
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
	       
	       //Send the control value for the previous selected column
	       sendMidiControls(curBank, x);
	}

	private void sendMidiControls(int bank, int x)
	 {
		   int controlValue = controls[bank][x] * 16;
		   //Can not send 128 as a value
		   if(controlValue == 128)
			   controlValue = 127;
		   
		   //send controlvalue to control corresponding to the current control grid and the column
		   midiControlOut.sendController(new promidi.Controller(startingController + x + (bank * 7), controlValue));
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
