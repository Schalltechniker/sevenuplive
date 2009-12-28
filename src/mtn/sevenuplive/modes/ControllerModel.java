/*
	Copyright 2009 Adam Ribaldo 
	 
	Developed by Adam Ribaldo, Chris Lloyd
    
    This file is part of SevenUpLive.
    http://www.makingthenoise.com/sevenup/

    SevenUpLive is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    SevenUpLive is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with SevenUpLive.  If not, see <http://www.gnu.org/licenses/>.
*/

package mtn.sevenuplive.modes;
import mtn.sevenuplive.m4l.M4LController;
import mtn.sevenuplive.m4l.M4LMidiOut;
import mtn.sevenuplive.modes.events.Event;
import mtn.sevenuplive.modes.events.EventDispatcher;
import mtn.sevenuplive.modes.events.EventListener;

public class ControllerModel extends Mode implements EventListener, EventDispatcher {

	private M4LMidiOut midiControlOut[];
	public Integer controls[][];
	private Integer startingController;
	
	private boolean[] banksHeld = new boolean[] {false, false, false, false, false, false, false};
	
	public ControllerModel(int _navRow, M4LMidiOut[] _midiControlOut, int _startingController, int grid_width, int grid_height) {
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
		   midiControlOut[bank].sendController(new M4LController(startingController + x, controlValue));
		   midiControlOut[7].sendController(new M4LController(startingController + x + (bank * 7), controlValue));
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


	@Override
	public void onEvent(Event e) {
		// TODO Auto-generated method stub
		
	}
	
	public void monomeAdc(int x, float value) {
		// @TODO need appropriate scaled values
		int scaledValue = (int)Math.abs(value * 128);
		scaledValue = Math.min(127, scaledValue);
		
		for (int bank = 0; bank < 7; bank++) {
			// ch 9 (index 8) is the ADC channel
			if (banksHeld[bank]) // Send the controller if the bank is being held
				midiControlOut[8].sendController(new M4LController(startingController + x + (bank * 7), scaledValue));
		}
	}
	
	public void holdBank(int bank) {
		banksHeld[bank] = true;
	}
	
	public void releaseBank(int bank) {
		banksHeld[bank] = false;
	}
	
	
}
