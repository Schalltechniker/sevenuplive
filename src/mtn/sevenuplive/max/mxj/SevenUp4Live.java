package mtn.sevenuplive.max.mxj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

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
	private SevenUp4LiveStepperClient stepper;
	private SevenUp4LiveLooperClient looper;
	
	private ConnectionSettings settings = new ConnectionSettings();
	
	private boolean clockStateChange = true;
	
	public static enum eOutlets {MelodizerMidiOutlet, StepperMidiOutlet, LooperMidiOutlet, InitializationDataOutlet}; 
	
	private static final String[] INLET_ASSIST = new String[]{
		"messages (initialize, shutdown, monome (0,1,2..etc), hostaddress (127.0.0.1), listenport, hostport)",
		"clock in (0=C4,1=D#4,2=C7,3=E7,4=F7)"
	};
	private static final String[] OUTLET_ASSIST = new String[]{
		"Melodizer Midi Out",
		"Stepper Midi Out",
		"Looper Midi Out",
		"Data Initialization Out"
	};
	
	public SevenUp4Live(Atom[] args)
	{
		declareInlets(new int[]{DataTypes.ALL, DataTypes.INT});
		declareOutlets(new int[]{
				DataTypes.MESSAGE, 
				DataTypes.MESSAGE,
				DataTypes.MESSAGE,
				DataTypes.MESSAGE
				});
		
		setInletAssist(INLET_ASSIST);
		setOutletAssist(OUTLET_ASSIST);
		
		// If MAX is creating a new instance, then we need to switch out all the innards to
		// point to the new one
		if (instance != null) {
			post("7up instance already exists...transferring control to new instance");
			post("Shutting down old instance...");
			instance.shutdown();
			post("Old instance shutdown");
		} 
		init();
		instance = this;	
		post("New 7up instance created");
	}
	
	protected void bang() {
		post("Hello world!! This is SevenUp in MAX");
		
		// Send initialization data
		outlet(eOutlets.InitializationDataOutlet.ordinal(), new Atom[]{
			Atom.newAtom("monomes"),
			Atom.newAtom("Monome64 Monome128H Monome128V")
			//Atom.newAtom("Monome128H"),
			//Atom.newAtom("Monome128V"),
			//Atom.newAtom("Monome256")
		});
	}
	
	protected void loadbang() {
		if (instance.applet != null) {
			instance.applet.stop();
			instance.applet.teardown();
		}
		
		// forces data refresh
		bang();
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
		
		/** 1 Instance on Channel 8 index 7 */
		stepper = new SevenUp4LiveStepperClient(this, 1, 7);
		
		/** 1 Instance on Channel 8 index 7 */
		looper = new SevenUp4LiveLooperClient(this, 1, 7);
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
	
	public M4LMidiOut getStepperOutput(int ch, int instance) {
		if (ch == 7) // Stepper currently works on channel 7 period
			return stepper;
		else
			return null;
	}
	
	public M4LMidiOut getLooperOutput(int ch, int instance) {
		if (ch == 7) // Looper currently works on channel 7 period
			return looper;
		else
			return null;
	}
	
	/**
	 * Initializes SevenUp with the current connection settings and starts it's heart 
	 */
	public void initialize() {
		if (applet != null) {
			if (applet.isRunning()) {
				post("7up is already initialized...");
			} else {
				applet = new SevenUpApplet(settings);
				applet.setVisible(false);
				post("7up started");
			}
		} else {
			post("Initializing 7up heart...");
			applet = new SevenUpApplet(settings);
			applet.setVisible(false);
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
			instance.applet.stop();
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
				case -1:
					post("CLOCK START/STOP");
					clockStateChange = true;	
					break;
				case 0:
					post("C4");
					if (clock != null)
						clock.sendBigTick();
					break;
				case 1:
					post("D#4");
					if (clock != null)
						clock.sendSmallTick();
					break;
				case 2:
					post("C7");
					if (clock != null)
						clock.pumpSequencerHeart();
					break;
				case 3:
					post("F7");
					if (clock != null)
						clock.pumpLooperHeart();
					break;
				case 4:
					post("E7");
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
    
	public void monome(Atom[] list)
	{
		if (list.length > 0) {
			int type = list[0].getInt();
			if (type < 0)
				type = 0;
			post("Setting monome via atom to type [" + Integer.toString(type) + "]");
			settings.monomeType = type;
		}
	}
	
	public void hostaddress(Atom[] list)
	{
		if (list.length > 0) {
			String address = list[0].getString();
			if (address == null)
				return;
			
			post("Setting monome hostaddress atom to [" + address + "]");
			settings.oscHostAddress = address;
		}
	}
	
	public void listenport(Atom[] list)
	{
		if (list.length > 0) {
			int listenport = list[0].getInt();
			if (listenport < 0)
				listenport = 0;
			post("Setting monome listenport to  [" + Integer.toString(listenport) + "]");
			settings.oscListenPort = listenport;		
		}
	}
	
	public void hostport(Atom[] list)
	{
		if (list.length > 0) {
			int hostport = list[0].getInt();
			if (hostport < 0)
				hostport = 0;
			post("Setting monome hostport to  [" + Integer.toString(hostport) + "]");
			settings.oscHostPort = hostport;		
		}
	}
	
	public void writepatch(Atom[] patchparams) {
		post("writepatch() called with " + patchparams.length + " params");
		if (patchparams.length > 0) {
			String filepath = patchparams[0].toString();
			post("Writing patch [" + filepath + "]");
			savePatch(filepath);
		}
	}
	
	public void readpatch(Atom[] patchparams) {
		post("readpatch() called with " + patchparams.length + " params");
		if (patchparams.length > 0) {
			String filepath = patchparams[0].toString();
			post("Reading patch [" + filepath + "]");
			loadPatch(filepath);
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
	
	private void savePatch(String filepath) {

		if (applet == null) {
			post("Could not save patch [" + filepath + "] applet must be initialized first");
			return;
		}
		
		FileWriter fileWriter = null;
		
		try {
			if (filepath != null) {
				java.io.File file = new File(filepath);
				org.jdom.Document doc = applet.toJDOMXMLDocument(file.getName());
				org.jdom.output.XMLOutputter fmt = new XMLOutputter();

				fileWriter = new FileWriter(file);
				fmt.output(doc, fileWriter);
			}
		} catch (Exception e) {
			post("Could not save patch: " + e);
		} finally { // standard java closing resources in finally
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e1) {
					// drop it
				}
			}	
		}
	}
	
	private void loadPatch(String filepath) {
		
		if (applet == null) {
			post("Could not load patch [" + filepath + "] applet must be initialized first");
			return;
		}
		
		try {
			if (filepath != null) {
				java.io.File file = new File(filepath);
				SAXBuilder builder = new SAXBuilder();
				try
				{
					Document doc = builder.build(file);
					applet.loadJDOMXMLDocument(doc);
				}
				catch(Exception ex)
				{
					post(ex.getMessage());   
				}
			}
		} catch (Exception e) {
			post("Could not save patch: " + e);
		} 	

	}
	
}
