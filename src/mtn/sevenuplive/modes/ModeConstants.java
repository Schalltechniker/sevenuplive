package mtn.sevenuplive.modes;

/**
 * Interface for constants shared 
 * between the different modes.
 */
public interface ModeConstants {

	////////////////////////////////////
	//Interface modes
	 
	public static final int PATTERN_MODE = 0;
	public static final int SEQ_MODE = 1;
	public static final int CONTROL_MODE = 2;
	public static final int LOOP_MODE = 3;
	public static final int LOOP_RECORD_MODE = 4;
	public static final int MELODY_MODE = 5;
	public static final int MELODY2_MODE = 6;
	public static final int MASTER_MODE = 7;
	public static final int SAMPLE_MODE = 31;
	public static final int CHOPPER_MODE = 41;
	public static final int MEL_ON_BUTTON_PRESS = 1;
	public static final int MEL_QUANTIZED = 2;
	
	/** Initial default value when there is no explicit default  */ 
	public static final int NOT_SET = -1;

}
