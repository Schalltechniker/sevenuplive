package mtn.sevenuplive.m4l;

public interface M4LMidi {
	
	public int numberOfInputDevices();
	
	public String getInputDeviceName(int device);

	/** @TODO is this even needed? */
	public void closePorts();
	
	public int numberOfOutputDevices();
	
	public String getOutputDeviceName(int device);
	
	public M4LMidiOut getMidiOut(int ch, String deviceName);
	
	public M4LMidiIn getMidiIn(int ch, String deviceName);
	
	public void plug(processing.core.PApplet core, String event, int deviceIndex, int ch);
	
	public void closeInput(int deviceIndex);
	
	public void closeOutputs();
	
	public void printDevices(); // @TODO print to system out
}
