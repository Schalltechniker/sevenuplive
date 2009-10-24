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
	
	private SevenUp4LiveMelodizerClient melodizer1;
	
	private SevenUp4LiveMelodizerClient melodizer2;
	
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
		
		if (instance != null) {
			instance.shutdown();
		}
		
		instance = this;	
		
		M4LMidiOut[] m1outs = new M4LMidiOut[7];
		M4LMidiOut[] m2outs = new M4LMidiOut[7];
		
		// Wire up our ports
		for (int i = 0; i < 7; i++) {
			m1outs[i] = M4LMidiSystem.getInstance().getMidiOut(i, M4LMidiSystem.eSevenUp4OutputDevices.Melodizer1.toString());
			m2outs[i] = M4LMidiSystem.getInstance().getMidiOut(i, M4LMidiSystem.eSevenUp4OutputDevices.Melodizer2.toString());
		}
		
		melodizer1 = new SevenUp4LiveMelodizerClient(this, m1outs);
		melodizer2 = new SevenUp4LiveMelodizerClient(this, m2outs);
		
		instance = this;
	}
	
	public void bang() {
		post("Hello world!! This is SevenUp in MAX");
	}
    
	/**
	 * Initializes SevenUp with the current connection settings and starts it's heart 
	 */
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
    
	/**
	 * Shuts down SevenUps heart and releases the OSC ports 
	 */
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
		int inletNum = getInlet();; 
		//post("I got an integer in inlet "+ inletNum);
		
		switch (inletNum) {
			case 1:
				switch (i) {
				case 0:
					//post("C4");
					if (clock != null)
						clock.sendBigTick();
					break;
				case 1:
					//post("D#4");
					if (clock != null)
						clock.sendSmallTick();
					break;
				case 2:
					//post("C7");
					if (clock != null)
						clock.pumpSequencerHeart();
					break;
				case 3:
					//post("F7");
					if (clock != null)
						clock.pumpLooperHeart();
					break;
				case 4:
					//post("E7");
					if (clock != null)
						clock.pumpMelodizerHeart();
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
    
	public SevenUp4LiveMelodizerClient getMelodizer1() {
		return melodizer1;
	}

	public void setMelodizer1(SevenUp4LiveMelodizerClient melodizer1) {
		this.melodizer1 = melodizer1;
	}

	public SevenUp4LiveMelodizerClient getMelodizer2() {
		return melodizer2;
	}

	public void setMelodizer2(SevenUp4LiveMelodizerClient melodizer2) {
		this.melodizer2 = melodizer2;
	}

    
	

}
