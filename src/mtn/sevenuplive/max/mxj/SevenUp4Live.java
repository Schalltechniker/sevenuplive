package mtn.sevenuplive.max.mxj;

import mtn.sevenuplive.main.ConnectionSettings;
import mtn.sevenuplive.main.SevenUpApplet;

import com.cycling74.max.Atom;
import com.cycling74.max.DataTypes;
import com.cycling74.max.MaxObject;

public class SevenUp4Live extends MaxObject {
	
	private SevenUpApplet applet;
	
	private static final String[] INLET_ASSIST = new String[]{
		"inlet 1 help"
	};
	private static final String[] OUTLET_ASSIST = new String[]{
		"outlet 1 help"
	};
	
	public SevenUp4Live(Atom[] args)
	{
		declareInlets(new int[]{DataTypes.ALL});
		declareOutlets(new int[]{DataTypes.ALL});
		
		setInletAssist(INLET_ASSIST);
		setOutletAssist(OUTLET_ASSIST);

	}
    
	public void bang() {
		post("Hello world!! This is SevenUp in MAX");
		
	}
    
	public void initialize() {
		if (applet != null) {
			post("7up is already initialized...");
		} else {
			post("Initializing 7up heart...");
			
			ConnectionSettings settings = new ConnectionSettings();
			applet = new SevenUpApplet(settings);
			applet.setVisible(false);
			
			applet.setup();
			
			post("7up started");
		}
	}
    
	public void shutdown() {
		if (applet == null) {
			post("Cannot shutdown 7up since has not been initialized yet");
		} else {
			post("Shutting down 7up...");
			applet.stop();
			applet = null;
			post("7up is shutdown");
		}
	}
    
	public void inlet(int i)
	{
	}
    
	public void inlet(float f)
	{
	}
    
    
	public void list(Atom[] list)
	{
	}
    
	

}
