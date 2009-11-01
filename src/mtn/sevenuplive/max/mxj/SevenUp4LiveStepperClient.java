package mtn.sevenuplive.max.mxj;

import mtn.sevenuplive.m4l.M4LController;
import mtn.sevenuplive.m4l.M4LMidiOut;
import mtn.sevenuplive.m4l.Note;

import com.cycling74.max.Atom;

public class SevenUp4LiveStepperClient implements M4LMidiOut {
	
	private int instanceNum;
	private int channel;
	private SevenUp4Live app;
	private static final int stepperCh = 7; // channel 8 
	private static final int instNum = 1; // There is only ever 1 
	
	public SevenUp4LiveStepperClient(SevenUp4Live app, int instanceNum, int ch) {
		
		this.instanceNum = instanceNum;
		this.app = app;
		this.channel = ch;
	}
	
	public void sendController(M4LController controller) {
		//SevenUp4Live.post("Got Controller: " + controller);
		
		app.outlet(SevenUp4Live.eOutlets.StepperMidiOutlet.ordinal(), new Atom[]{
			Atom.newAtom(instNum),
			Atom.newAtom(channel + 1),
			Atom.newAtom(M4LMidiOut.CC),
			Atom.newAtom(controller.getCC()), 
			Atom.newAtom(controller.getValue())});
	}

	public void sendNoteOff(Note note) {
		SevenUp4Live.post("Got note OFF: " + note);
		
		app.outlet(SevenUp4Live.eOutlets.StepperMidiOutlet.ordinal(), new Atom[]{
				Atom.newAtom(instNum),
				Atom.newAtom(stepperCh + 1),
				Atom.newAtom(M4LMidiOut.NOTE),
				Atom.newAtom(144 + stepperCh),
				Atom.newAtom(note.getPitch()), 
				Atom.newAtom(0)});
	}

	public void sendNoteOn(Note note) {
		SevenUp4Live.post("Got note ON: " + note);
		
		app.outlet(SevenUp4Live.eOutlets.StepperMidiOutlet.ordinal(), new Atom[]{
				Atom.newAtom(instNum),
				Atom.newAtom(stepperCh + 1),
				Atom.newAtom(M4LMidiOut.NOTE),
				Atom.newAtom(144 + stepperCh),
				Atom.newAtom(note.getPitch()), 
				Atom.newAtom(note.getVelocity())});
	}

	public int getInstanceNum() {
		return instanceNum;
	}

	public void setInstanceNum(int instanceNum) {
		this.instanceNum = instanceNum;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}
	
	public SevenUp4Live getApp() {
		return app;
	}

	public void setApp(SevenUp4Live app) {
		this.app = app;
	}


}
