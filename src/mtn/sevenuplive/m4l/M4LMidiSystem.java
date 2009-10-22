package mtn.sevenuplive.m4l;

import processing.core.PApplet;

public class M4LMidiSystem implements M4LMidi {

	private static M4LMidiSystem instance;
	
	public static M4LMidi getInstance() {
		if (instance == null) {
			instance = new M4LMidiSystem();
		}
		
		return instance;	
	}
	
	public static M4LMidi getInstance(processing.core.PApplet core) {
		
		//@TODO Not sure what we need to do with core yet
		return instance;	
	}

	public void closePorts() {
		// TODO Auto-generated method stub
	}

	public String getInputDeviceName(int device) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getOutputDeviceName(int device) {
		// TODO Auto-generated method stub
		return null;
	}

	public int numberOfInputDevices() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int numberOfOutputDevices() {
		// TODO Auto-generated method stub
		return 0;
	}

	public M4LMidiIn getMidiIn(int ch, String deviceName) {
		// TODO Auto-generated method stub
		return null;
	}

	public M4LMidiOut getMidiOut(int ch, String deviceName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void printDevices() {
		// TODO Auto-generated method stub
		
	}

	public void closeInput(int deviceIndex) {
		// TODO Auto-generated method stub
		
	}

	public void closeOutputs() {
		// TODO Auto-generated method stub
		
	}

	public void plug(PApplet core, String event, int deviceIndex, int ch) {
		// TODO Auto-generated method stub
		
	}
	
}
