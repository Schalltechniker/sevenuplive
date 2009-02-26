package mtn.sevenuplive.modes;

import jklabs.monomic.Monome;

public class DisplayGrid {

	private int start_column;
	private int start_row;
	private int grid_width;
	private int grid_height;
	private int grid_index;

	private int curDisplayGrid[][];
	private int navGrid[];
	private int pressedButtonsLength[][];

	public static final int NAVCOL = 7;

	///////////////////////////////////////// 
	//Blink-speed members
	/////////////////////////////////////////
	private static final int FRAMES = 10;
	public static final int FASTBLINK = 2;
	public static final int SLOWBLINK = 1;
	public static final int SOLID = 3;
	public static final int OFF = 0;
	private static final  int SLOWBLINKFRAME = 10;
	private static final int FASTBLINKFRAME = 1;

	////////////////////////////////////
	//Interface modes
	////////////////////////////////////
	private int menuLevel;
	private final static int MAINMENU = 0;
	private final static int SUBMENU = 1;

	private int curMode;
	private int frmCount;

	private Monome monome;
	private AllModes allmodes;
	
	private Mode defaultMode;

	public DisplayGrid(Monome monome, AllModes allmodes, int start_column, int start_row, int grid_width, int grid_height, Mode defaultMode, int grid_index) {
		this.start_row = start_row;
		this.start_column = start_column;
		this.grid_width = grid_width;
		this.grid_height = grid_height;
		this.grid_index = grid_index;
		
		this.monome = monome;
		this.allmodes = allmodes;
		menuLevel = SUBMENU;

		//Pressed buttons
		pressedButtonsLength = new int[grid_width][grid_height];

		this.defaultMode = defaultMode;
		
		curDisplayGrid = AllModes.startup.getDisplayGrid();
		navGrid = AllModes.startup.getNavGrid();
		curMode = AllModes.startup.getMyNavRow();
	}

	public Mode getDefaultMode() {
		return defaultMode;
	}

	public int translateX(int x) {
		return x-start_column;
	}

	public int translateY(int y) {
		return y-start_row;
	}

	public boolean hitTest(int x, int y) {
		if (x >= start_column && x < start_column + grid_width) {
			if (y >= start_row && y < start_row + grid_height)
				return true;
		}
		return false;
	}

	public void draw()
	{
		//Very fast int compare here to not slow us down
		if (curMode == StartupMode.STARTUP_MODE) {
			
			if (AllModes.startup.isFinished()) {
				// If we press a key in startup mode, then change to default mode
				curDisplayGrid = defaultMode.getDisplayGrid();
				navGrid = defaultMode.getNavGrid();
				curMode = defaultMode.getMyNavRow();
				return; // don't do anything else
			}
			
			AllModes.startup.nextSequence();
		}
			
		
		//if a button is being held, count to the holdtime and fire a heldevent
		//TODO: need to be able to handle multiple press and release

		//draw the display grid
		for (int x = 0; x<grid_width-1;x++)
		{
			for (int y = 0; y<grid_height;y++)
			{
				switch(curDisplayGrid[x][y])
				{
				case 0:
					if(monome.isLit(x+start_column, y+start_row))
						monome.setValue(x+start_column, y+start_row, 0);
					break;
				case 3:  
					if(!monome.isLit(x+start_column, y+start_row))
						monome.setValue(x+start_column, y+start_row, 1);

					break;

				case 2:   
					if(frmCount % FASTBLINKFRAME == 0) 
					{
						monome.setValue(x+start_column, y+start_row, !monome.isLit(x+start_column, y+start_row));
					} 
					break;
				case 1:
					if(frmCount % SLOWBLINKFRAME == 0) 
					{
						monome.setValue(x+start_column, y+start_row, !monome.isLit(x+start_column, y+start_row));
					} 
					break;

				}      
			}
		}

		//Draw navbar
		for (int y = 0; y<grid_height;y++)
		{
			switch(navGrid[y])
			{
			case 0:
				if(monome.isLit(NAVCOL+start_column, y+start_row))
					monome.setValue(NAVCOL+start_column, y+start_row, 0);
				break;
			case 3:  
				if(!monome.isLit(NAVCOL+start_column, y+start_row))
					monome.setValue(NAVCOL+start_column, y+start_row, 1);

				break;

			case 2:   
				if(frmCount % FASTBLINKFRAME == 0) 
				{
					monome.setValue(NAVCOL+start_column, y+start_row, !monome.isLit(NAVCOL+start_column, y+start_row));
				} 
				break;
			case 1:
				if(frmCount % SLOWBLINKFRAME == 0) 
				{
					monome.setValue(NAVCOL+start_column, y+start_row, !monome.isLit(NAVCOL+start_column, y+start_row));
				} 
				break;
			}
		}

		//Loop through pressedNotesLength and increment the number of frames each pressed note has been held
		for(int i=0;i<grid_width;i++)
			for(int j=0;j<grid_height;j++)
			{
				if(pressedButtonsLength[i][j] >= 1)
				{
					pressedButtonsLength[i][j]++;
					//if the button has been held longer than 4 seconds then trigger a held event
					if(pressedButtonsLength[i][j] >= 4 * FRAMES)
					{	
						triggerButtonHeld(i, j);
						//TODO may need to make this equal 1 if i want to hold longer than 4 seconds
						pressedButtonsLength[i][j] = 0;

					}
				}
			}

		frmCount++;
		if (frmCount == FRAMES) frmCount = 0;
	}

	public void monomePressed(int x, int y) {
		
		// Very fast int compare here to not slow us down
		if (curMode == StartupMode.STARTUP_MODE) {
			// If we press a key in startup mode, then change to default mode
			curDisplayGrid = defaultMode.getDisplayGrid();
			navGrid = defaultMode.getNavGrid();
			curMode = defaultMode.getMyNavRow();
			return; // don't do anything else
		}
		
		//Flag current button as pressed
	    pressedButtonsLength[x][y] = 1;
	   
		//Handle pressing navbar button
		if (x == NAVCOL)
		{
			if(menuLevel == MAINMENU)
			{
				//If they hit the same menu mode then move back to submenu
				if (y == curMode)
				{
					navGrid = new int[grid_height];
					navGrid[curMode] = DisplayGrid.SOLID;
					menuLevel = SUBMENU;

					if (curMode == ModeConstants.PATTERN_MODE)
						navGrid = allmodes.getPatternizerView(grid_index).getNavGrid();
					else if (curMode == ModeConstants.CONTROL_MODE)
						navGrid = allmodes.getController().getNavGrid();
					else if (curMode == ModeConstants.SEQ_MODE)
						navGrid = allmodes.getSequencer().getNavGrid();
					else if (curMode == ModeConstants.LOOP_MODE)
						navGrid = allmodes.getLooper().getNavGrid();
					else if (curMode == ModeConstants.LOOP_RECORD_MODE)
						navGrid = allmodes.getLoopRecorder().getNavGrid();
					else if (curMode == ModeConstants.MELODY_MODE)
						navGrid = allmodes.getMelodizer1().getNavGrid();
					else if (curMode == ModeConstants.MELODY2_MODE)
						navGrid = allmodes.getMelodizer2().getNavGrid();
				}
				//Else they are changing modes
				else
				{
					navGrid = new int[8];
					curMode = y;
					menuLevel = SUBMENU;
					if(y == ModeConstants.PATTERN_MODE)
					{
						curDisplayGrid = allmodes.getPatternizerView(grid_index).getDisplayGrid();
						navGrid = allmodes.getPatternizerView(grid_index).getNavGrid();
					}
					else if(y == ModeConstants.CONTROL_MODE)
					{
						curDisplayGrid = allmodes.getController().getDisplayGrid();
						navGrid = allmodes.getController().getNavGrid();
					}
					else if(y == ModeConstants.SEQ_MODE)
					{
						curDisplayGrid = allmodes.getSequencer().getDisplayGrid();
						navGrid = allmodes.getSequencer().getNavGrid();
					}
					else if(y == ModeConstants.LOOP_MODE)
					{
						allmodes.getLooper().updateDisplayGrid();
						curDisplayGrid = allmodes.getLooper().getDisplayGrid();
						navGrid = allmodes.getLooper().getNavGrid();
					}
					else if(y == ModeConstants.LOOP_RECORD_MODE)
					{
						allmodes.getLoopRecorder().updateDisplayGrid();
						allmodes.getLoopRecorder().updateNavGrid();
						curDisplayGrid = allmodes.getLoopRecorder().getDisplayGrid();
						navGrid = allmodes.getLoopRecorder().getNavGrid();
					}
					else if(y == ModeConstants.MELODY_MODE)
					{
						allmodes.getMelodizer1().updateDisplayGrid();
						curDisplayGrid = allmodes.getMelodizer1().getDisplayGrid();
						navGrid = allmodes.getMelodizer1().getNavGrid();
					}
					else if(y == ModeConstants.MELODY2_MODE)
					{
						allmodes.getMelodizer2().updateDisplayGrid();
						curDisplayGrid = allmodes.getMelodizer2().getDisplayGrid();
						navGrid = allmodes.getMelodizer2().getNavGrid();
					}
					else if(y == ModeConstants.MASTER_MODE)
					{
						allmodes.getMasterizer().updateDisplayGrid();
						curDisplayGrid = allmodes.getMasterizer().getDisplayGrid();
						navGrid = allmodes.getMasterizer().getNavGrid();
					}
				}
			}
			else if(menuLevel == SUBMENU)
			{
				//if they hit the curMenuPoint button again, change back main menu
				if (y == curMode)
				{
					navGrid = new int[grid_height];
					navGrid[curMode] = DisplayGrid.SOLID;
					menuLevel = MAINMENU;
				}
				//Else they are moving between sub-menu items
				else
				{
					if(curMode == ModeConstants.PATTERN_MODE)
					{
						allmodes.getPatternizerView(grid_index).press(x, y);
						curDisplayGrid = allmodes.getPatternizerView(grid_index).getDisplayGrid();
					}
					else if(curMode == ModeConstants.CONTROL_MODE)
					{
						allmodes.getController().press(x, y);
					}
					else if(curMode == ModeConstants.SEQ_MODE)
					{
						allmodes.getSequencer().press(x, y);
						curDisplayGrid = allmodes.getSequencer().getDisplayGrid();
					}
					else if(curMode == ModeConstants.LOOP_MODE)
					{
						allmodes.getLooper().press(x, y);
					}
					else if(curMode == ModeConstants.LOOP_RECORD_MODE)
					{
						allmodes.getLoopRecorder().press(x, y);
					}
					else if(curMode == ModeConstants.MELODY_MODE)
					{
						allmodes.getMelodizer1().press(x, y);
					}
					else if(curMode == ModeConstants.MELODY2_MODE)
					{
						allmodes.getMelodizer2().press(x, y);
					}
					else if(curMode == ModeConstants.MASTER_MODE)
						allmodes.getMasterizer().press(x, y);
				}
			}
		}

		////////////////////////////////////
		//Handle pressing the displayed grid
		////////////////////////////////////
		else
		{
			if (curMode == ModeConstants.PATTERN_MODE)
			{
				allmodes.getPatternizerView(grid_index).press(x, y);
			}
			else if(curMode == ModeConstants.CONTROL_MODE)
			{
				allmodes.getController().press(x, y);
			}
			// seq mode
			else if(curMode == ModeConstants.SEQ_MODE)
			{
				allmodes.getSequencer().press(x, y);
			}
			else if(curMode == ModeConstants.LOOP_MODE)
				allmodes.getLooper().press(x, y);
			else if(curMode == ModeConstants.LOOP_RECORD_MODE)
				allmodes.getLoopRecorder().press(x, y);
			else if (curMode == ModeConstants.MELODY_MODE)
				allmodes.getMelodizer1().press(x, y);
			else if (curMode == ModeConstants.MELODY2_MODE)
				allmodes.getMelodizer2().press(x, y);
			else if(curMode == ModeConstants.MASTER_MODE)
				allmodes.getMasterizer().press(x, y);
		}
	}

	public void monomeReleased(int x, int y) {

		if(curMode == ModeConstants.PATTERN_MODE)
		{
			allmodes.getPatternizerView(grid_index).release(x, y);
		}
		//If user releases within the melodizer play area
		else if(curMode == ModeConstants.MELODY_MODE && x != NAVCOL)
		{
			if(y<6 || allmodes.getMelodizer1().clipMode)
				allmodes.getMelodizer1().release(x, y);
		}
		else if(curMode == ModeConstants.MELODY2_MODE && x != NAVCOL)
		{
			if(y<6 || allmodes.getMelodizer2().clipMode)
				allmodes.getMelodizer2().release(x, y);
		}

		pressedButtonsLength[x][y] = 0;

	}

	public void triggerButtonHeld(int x, int y)
	{
		if(curMode == ModeConstants.PATTERN_MODE && x == NAVCOL && y>0)
		{
			allmodes.getPatternizerView(grid_index).triggerButtonHeld(x, y);
			curDisplayGrid = allmodes.getPatternizerView(grid_index).getDisplayGrid();
		}
		else if(curMode == ModeConstants.CONTROL_MODE && x == NAVCOL && navGrid[y] == DisplayGrid.FASTBLINK)
			allmodes.getController().clearControlBank(y);
	}
	
	
	public void displayCursor() {
		
		//Only show the beat blips in current pattern mode
        if ((curMode == ModeConstants.PATTERN_MODE && allmodes.getSequencer().isPatternPlaying(allmodes.getPatternizerView(grid_index).selectedPattern)) )
        {
        	for (int x = start_column; x < start_column + grid_width; x++) {
        		if (monome.isLit(x, AllModes.patternizerModel.curPatternRow + start_row)) { 
        			monome.setValue(x, AllModes.patternizerModel.curPatternRow + start_row, 0);  
        		} else {
        			monome.setValue(x, AllModes.patternizerModel.curPatternRow + start_row, 1);
        		}
        	}
        	// @TODO clloyd fix this 
        	//monome.invertRowByte(allmodes.getPatternizer().curPatternRow + start_row, Math.abs(start_column / 8)); 
        }
        else if(curMode == ModeConstants.MASTER_MODE)
        {
	        //blip the masterizer
        	allmodes.getMasterizer().updatePatternBeat(allmodes.getPatternizerModel().curPatternRow);
        }
        else if(curMode == ModeConstants.SEQ_MODE && allmodes.getPatternizerModel().curPatternRow % 4 == 0)
        {
        	for (int x = start_column; x < start_column + grid_width; x++) {
        		if (monome.isLit(x, AllModes.sequencer.curSeqRow + start_row)) { 
        			monome.setValue(x, AllModes.sequencer.curSeqRow + start_row, 0);  
        		} else {
        			monome.setValue(x, AllModes.sequencer.curSeqRow + start_row, 1);
        		}
        	}
        	//@TODO clloyd fix this
        	//monome.invertRowByte(allmodes.getSequencer().curSeqRow + start_row, Math.abs(start_column / 8)); 	
        }
        
        if(curMode == ModeConstants.MASTER_MODE)
        	allmodes.getMasterizer().updateDisplayGrid();
	}
	
	public int getGridIndex()
	{
		return grid_index;
	}
	

}
