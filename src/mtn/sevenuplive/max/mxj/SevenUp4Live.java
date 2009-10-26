package mtn.sevenuplive.max.mxj;

import mtn.sevenuplive.m4l.M4LMidiOut;
import mtn.sevenuplive.m4l.M4LMidiSystem;
import mtn.sevenuplive.main.ConnectionSettings;
import mtn.sevenuplive.main.SevenUpApplet;

import com.cycling74.max.Atom;
import com.cycling74.max.DataTypes;
import com.cycling74.max.MaxObject;

public class SevenUp4Live extends MaxObject {
	
	private SevenUpApplet applet;
	private static SevenUpClock clock;
	
	// There can be only one of these
	private static SevenUp4Live instance;
	
	private SevenUp4LiveMelodizerClient[] melodizer1;
	private SevenUp4LiveMelodizerClient[] melodizer2;
	
	private static final String[] INLET_ASSIST = new String[]{
		"messages",
		"clock in (0=C4,1=D#4,2=C7,3=E7,4=F7)"
	};
	private static final String[] OUTLET_ASSIST = new String[]{
		"outlet 1 help"
	};
	
	public SevenUp4Live(Atom[] args)
	{
		declareInlets(new int[]{DataTypes.ALL, DataTypes.INT});
		declareOutlets(new int[]{DataTypes.ALL});
		
		setInletAssist(INLET_ASSIST);
		setOutletAssist(OUTLET_ASSIST);
		
		// If MAX is creating a new instance, then we need to switch out all the innards to
		// point to the new one
		if (instance != null) {
			post("7up instance already exists...transferring control to new instance");
			post("Shutting down old instance...");
			instance.shutdown();
			post("Old instance shutdown");
			this.clock = instance.clock;
			this.applet = instance.applet;
			this.melodizer1 = instance.melodizer1;
			this.melodizer2 = instance.melodizer2;
			
			// Disconnect old one
			instance.clock = null;
			instance.applet = null;
			instance.melodizer1 = null;
			instance.melodizer2 = null;
			
			for (int i = 0; i < 7; i++) {
				this.melodizer1[i].setApp(this);
				this.melodizer2[i].setApp(this);
			}
			post("Transfer complete");
		} else {
			init();
		}
		
		instance = this;	
	}
	
	protected void loadbang() {
		if (applet != null) {
			instance.applet.stop();
			instance.applet.teardown();
		}
	}
	
	private void init() {
		M4LMidiSystem.init(this);
		melodizer1 = new SevenUp4LiveMelodizerClient[7];
		melodizer2 = new SevenUp4LiveMelodizerClient[7];
		
		// Wire up our ports
		for (int i = 0; i < 7; i++) {
			melodizer1[i] = new SevenUp4LiveMelodizerClient(this, 1, i);
			melodizer2[i] = new SevenUp4LiveMelodizerClient(this, 2, i);
		}
	}
	
	public M4LMidiOut getMelodizerOutput(int ch, int instance) {
		switch (instance) {
		case 1:
			if (ch < 8)
				return melodizer1[ch];
		break;
		case 2:
			if (ch < 8)
				return melodizer2[ch];
		break;
		}
		return null;
	}
	
	public void bang() {
		post("Hello world!! This is SevenUp in MAX");
	}
    
	/**
	 * Initializes SevenUp with the current connection settings and starts it's heart 
	 */
	public void initialize() {
		if (instance.applet != null) {
			if (instance.applet.isRunning()) {
				post("7up is already initialized...");
			} else {
				ConnectionSettings settings = new ConnectionSettings();
				instance.applet = new SevenUpApplet(settings);
				instance.applet.setVisible(false);
				post("7up started");
			}
		} else {
			post("Initializing 7up heart...");
			
			ConnectionSettings settings = new ConnectionSettings();
			instance.applet = new SevenUpApplet(settings);
			instance.applet.setVisible(false);
			post("7up started");
		}
	}
    
	/**
	 * Shuts down SevenUps heart and releases the OSC ports 
	 */
	public void shutdown() {
		if (instance.applet == null) {
			post("Cannot shutdown 7up since has not been initialized yet");
		} else {
			post("Shutting down 7up...");
			instance.applet.teardown();
			post("7up is shutdown");
		}
	}
    
	public void inlet(int i)
	{
		int inletNum = getInlet();; 
		//post("I got an integer in inlet "+ inletNum);
		
		switch (inletNum) {
			case 1:
				switch (i) {
				case 0:
					//post("C4");
					if (instance.clock != null)
						instance.clock.sendBigTick();
					break;
				case 1:
					//post("D#4");
					if (instance.clock != null)
						instance.clock.sendSmallTick();
					break;
				case 2:
					//post("C7");
					if (instance.clock != null)
						instance.clock.pumpSequencerHeart();
					break;
				case 3:
					//post("F7");
					if (instance.clock != null)
						instance.clock.pumpLooperHeart();
					break;
				case 4:
					//post("E7");
					if (instance.clock != null)
						instance.clock.pumpMelodizerHeart();
					break;
				default:
					post("Clock does not understand " + i);
				}
				break;
			default:	
		}
	}
    
	public void inlet(float f)
	{
	}
    
    
	public void list(Atom[] list)
	{
	}

	/////////////////////////////////////////////
	// Non MXJ Operations
	
	public static SevenUp4Live getInstance() {
		return instance;
	}
	
	public static void setClock(SevenUpClock clock) {
		SevenUp4Live.clock = clock;
	}
    

}
