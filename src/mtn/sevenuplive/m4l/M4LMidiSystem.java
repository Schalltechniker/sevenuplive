package mtn.sevenuplive.m4l;

import java.util.HashMap;

import processing.core.PApplet;

public class M4LMidiSystem implements M4LMidi {

	private static M4LMidiSystem instance;
	
	public static M4LMidi getInstance() {
		if (instance == null) {
			instance = new M4LMidiSystem();
		}
		
		return instance;	
	}
	
	public enum eSevenUp4OutputDevices {Melodizer1, Melodizer2, Stepper, Looper};
	public enum eSevenUp4InputDevices {SevenUpMidiControl};
	
	private HashMap<Integer, M4LMidiIn> inputDeviceMap = new HashMap<Integer, M4LMidiIn>();
	private HashMap<Integer, M4LMidiOut> outputDeviceMap = new HashMap<Integer, M4LMidiOut>();
	
	public static M4LMidi getInstance(processing.core.PApplet core) {
		
		//@TODO Not sure what we need to do with core yet
		return getInstance();	
	}

	public void closePorts() {
		// TODO Auto-generated method stub
	}

	public String getInputDeviceName(int device) {
		if (eSevenUp4InputDevices.values().length <= device)
			return null;
		
		return (eSevenUp4InputDevices.values())[device].toString();
	}

	public String getOutputDeviceName(int device) {
		if (eSevenUp4InputDevices.values().length <= device)
			return null;
		
		return (eSevenUp4InputDevices.values())[device].toString();
	}

	public int numberOfInputDevices() {
		return eSevenUp4InputDevices.values().length;
	}

	public int numberOfOutputDevices() {
		return eSevenUp4OutputDevices.values().length;
	}

	public M4LMidiIn getMidiIn(int ch, String deviceName) {
		return inputDeviceMap.get(ch);
	}

	public M4LMidiOut getMidiOut(int ch, String deviceName) {
		M4LMidiOut device = outputDeviceMap.get(ch);
		
		if (device != null)
			return device;
		
		device = new M4LForwardingMidiOutPort(ch, null);
		outputDeviceMap.put(ch, device);
		return device;
	}

	public void printDevices() {
		for (eSevenUp4OutputDevices device: eSevenUp4OutputDevices.values()) {
			System.out.println(device);
		}
		for (eSevenUp4InputDevices device: eSevenUp4InputDevices.values()) {
			System.out.println(device);
		}
	}

	public void closeInput(int deviceIndex) {
		// TODO Auto-generated method stub
	}

	public void closeOutputs() {
		// TODO Auto-generated method stub
	}

	public void plug(PApplet core, String event, int deviceIndex, int ch) {
		// @NOTE we don't use deviceIndex
		M4LMidiIn device = new M4LForwardingMidiInPort(ch, event, core);
		inputDeviceMap.put(ch, device);
	}
	
}
