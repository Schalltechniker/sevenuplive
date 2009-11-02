package mtn.sevenuplive.main;

import org.jdom.Document;

import proxml.XMLInOut;
import mtn.sevenuplive.m4l.M4LMidiSystem;
import mtn.sevenuplive.max.mxj.SevenUpClock;
import mtn.sevenuplive.scales.Scale;
import mtn.sevenuplive.scales.ScaleName;

public class SevenUpEnvironment {

	private MonomeUp m;

	private SevenUpApplet applet;

	private Scale monomeScale;

	private ConnectionSettings sevenUpConnections;

	private int monomeType = MonomeUp.MONOME_64;

	private XMLInOut xmlIO;
	
	/** Dirty flag for changes to the patch */
	private boolean dirty; 

	public SevenUpEnvironment(ConnectionSettings sevenUpConnections) {
		this.sevenUpConnections = sevenUpConnections;
		newMonome();
	}
	
	public SevenUpClock getClock() {
		if (applet != null) {
			return applet;
		}
		return null;
	}
	
	public boolean startSevenUp() {
		if (applet != null) {
			if (applet.isRunning()) {
				return false;
			}
		} else {
			newMonome(); // new Monome because size may have changed
			applet = new SevenUpApplet(m, sevenUpConnections);
			applet.setVisible(false);
			xmlIO = new XMLInOut(applet);
		}
		return true;
	}

	public boolean stopSevenUp() {
		if (applet == null) {
			return false;
		} else {
			try {
				if (m != null) {
					m.forceShutdown();
				}
				applet.stop();
			} catch (Throwable e) {
				applet = null;
				e.printStackTrace();
			}
			applet = null;
		}
		return true;
	}
	
	private void newMonome() {
		monomeScale = new Scale(ScaleName.Major);

		// Get the type of monome selected
		monomeType = sevenUpConnections.monomeType;

		// Figure out dimensions of monome grid
		int x_grids = 1;
		int y_grids = 1;
		switch (monomeType) {
		case 0: //1 x 64
			x_grids=1; y_grids=1;
			break;
		case 1: //128H
			x_grids=2; y_grids=1;
			break;
		case 2: //128V
			x_grids=1; y_grids=2;
			break;
		case 3: //3 x 64's
			x_grids=1; y_grids=3;
			break;
		case 4: //256
			x_grids=2; y_grids=2;
			break;
		case 5: //2x256
			x_grids=4; y_grids=2;
			break;
		case 6: //3x256
			x_grids=6; y_grids=2;
			break;	   
		case 7:
			x_grids = 1; y_grids = 5;
			break;
		case 8:
			x_grids = 1; y_grids = 6;
			break;
		case 9:
			x_grids = 1; y_grids = 7;
			break;
		case 10:
			x_grids = 1; y_grids = 8;
			break;
		case 11:
			x_grids = 1; y_grids = 9;
			break;
		case 12: //10 x 64's
		x_grids = 1; y_grids = 10;
		break;
		};

		m = new MonomeUp(x_grids, y_grids, sevenUpConnections, monomeScale, this);
	}

	public ConnectionSettings getSevenUpConnections() {
		return sevenUpConnections;
	}

	public void setSevenUpConnections(ConnectionSettings sevenUpConnections) {
		this.sevenUpConnections = sevenUpConnections;
	}

	private Scale getScaleFromString(String scaleName)
	{
		ScaleName sn = ScaleName.valueOf(scaleName);
		if (sn == null)
			return new Scale(ScaleName.Major);

		return new Scale(sn);
	}

	public void setMonomeType(int monomeType) {
		this.monomeType = monomeType;
	}

	public int getMonomeType() {
		return monomeType;
	}

	public int getLoopType(int loopNum) {
		return m.getLoopType(loopNum);
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public boolean isDirty() {
		return dirty;
	}

	public void finalize()
	{
		if (m != null) {
			m.lightsOff();
		}
		m = null;
	}

	public boolean openSevenUpPatch(String patchPath)
	{
		if (xmlIO == null)
			return false;

		try
		{
			xmlIO.loadElement(patchPath);
			dirty = false;
			return true;
		}catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public Document toJDOMXMLDocument(String fileName)
	{
		return m.toJDOMXMLDocument(fileName);
	}

	public boolean loadJDOMXMLDocument(Document XMLDoc)
	{
		return m.loadJDOMXMLDocument(XMLDoc);
	}

}
