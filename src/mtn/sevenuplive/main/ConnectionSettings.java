package mtn.sevenuplive.main;

import mtn.sevenuplive.m4l.M4LMidiSystem;

public class ConnectionSettings {
	public int monomeType=0; // 64 default
	public String oscPrefix = "7up";
	public int oscHostPort = 8080;
	public int oscListenPort = 8000;
	public String oscHostAddress = "127.0.0.1";
	public String midiInputDeviceName = M4LMidiSystem.eSevenUp4InputDevices.SevenUpMidiControl.toString();
	public String stepperOutputDeviceName = M4LMidiSystem.eSevenUp4OutputDevices.Stepper.toString();
	public String looperOuputDeviceName= M4LMidiSystem.eSevenUp4OutputDevices.Looper.toString();
	public String melod1OutputDeviceName = M4LMidiSystem.eSevenUp4OutputDevices.Melodizer1.toString();
	public String melod2OutputDeviceName = M4LMidiSystem.eSevenUp4OutputDevices.Melodizer2.toString();
}
