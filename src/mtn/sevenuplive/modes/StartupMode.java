package mtn.sevenuplive.modes;

import java.util.Random;

/**
 * Mode that is only accessed at startup to do the startup sequence
 */
public class StartupMode extends Mode {

	int [][] template7;
	int totalframes;
	int frames;
	int rateinframes;
	int ratecounter;
	
	boolean finished = false;
	
	/**
	 * @param numscreens Need accurate count to get timing correct
	 * @param frames How many frames to apply random light calculations
	 * @param rateinframes Draw every how many frames
	 */
	public StartupMode(int numscreens, int frames, int rateinframes) {
		super(-1, 8, 8);
	
		this.rateinframes = rateinframes * numscreens;
		this.frames = frames * numscreens;
		this.ratecounter = this.rateinframes;
		this.totalframes = this.frames;
		
		template7 = new int[][] {
				new int [] {0,1,1,1,1,1,1,0},
				new int [] {0,1,1,1,1,1,1,0},
				new int [] {0,0,0,0,0,1,1,0},
				new int [] {0,0,0,0,1,1,0,0},
				new int [] {0,0,0,1,1,0,0,0},
				new int [] {0,0,1,1,0,0,0,0},
				new int [] {0,0,1,1,0,0,0,0},
				new int [] {0,0,1,1,0,0,0,0}
		};
		
		displayGrid = new int[8][8];
	}
	
	protected boolean isFinished() {
		return finished;
	}

	public void nextSequence() {
		if (finished)
			return;
		
		if (ratecounter-- < 1) {
			byte[] randomBytes = new byte[1];
			
			Random rand = new Random();
			int bvalue;
			
			for (int x = 0; x < 8; x++) {
				rand.nextBytes(randomBytes);
						
				for (int y = 0; y < 8; y++) {
					// If turning this led on, then decide how to light it
					if ((template7[y][x] & ((randomBytes[0] >> y) & 0x01)) == 1) {
						
						// Fade to Solid
						if (frames < Math.abs(totalframes / 2))
							bvalue = DisplayGrid.SOLID;
						else
							bvalue = DisplayGrid.FASTBLINK;
						
						if (x == 7) {
							this.navGrid[y] = bvalue;
						} else {
							displayGrid[x][y] = bvalue;
						}	
					}
						
				}
			}
			ratecounter = rateinframes; // reset
		}
		if (frames-- == 0)
			finished = true;
	}

	
	/* (non-Javadoc)
	 * @see mtn.sevenuplive.modes.Mode#getMyNavRow()
	 */
	public int getMyNavRow() {
		return STARTUP_MODE;
	}

}
