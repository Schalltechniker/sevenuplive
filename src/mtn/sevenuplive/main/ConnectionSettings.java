package mtn.sevenuplive.main;

public class ConnectionSettings {
	public int monomeType=0; // 64 default
	public String oscPrefix = "7up";
	public int oscHostPort = 8080;
	public int oscListenPort = 8000;
	public String oscHostAddress = "127.0.0.1";
	public String midiInputDeviceName;
	public int midiInputDeviceIndex;
	public String stepperOutputDeviceName;
	public String looperOuputDeviceName;
	public String melod1OutputDeviceName;
	public String melod2OutputDeviceName;
}
