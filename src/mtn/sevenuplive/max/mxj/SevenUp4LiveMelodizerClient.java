package mtn.sevenuplive.max.mxj;

import mtn.sevenuplive.m4l.M4LController;
import mtn.sevenuplive.m4l.M4LMidiOut;
import mtn.sevenuplive.m4l.Note;

import com.cycling74.max.Atom;

public class SevenUp4LiveMelodizerClient implements M4LMidiOut {
	
	private int instanceNum;
	private int channel;
	private SevenUp4Live app;
	
	public SevenUp4LiveMelodizerClient(SevenUp4Live app, int instanceNum, int ch) {
		
		this.instanceNum = instanceNum;
		this.app = app;
		this.instanceNum = instanceNum;
		this.channel = ch;
	}
	
	public void sendController(M4LController controller) {
		SevenUp4Live.post("Got Controller: " + controller);
	}

	public void sendNoteOff(Note note) {
		SevenUp4Live.post("Got note OFF: " + note);
		
		app.outlet(0, new Atom[]{
				Atom.newAtom(instanceNum),
				Atom.newAtom(channel + 1),
				Atom.newAtom(144),
				Atom.newAtom(note.getPitch()), 
				Atom.newAtom(note.getVelocity())});
	}

	public void sendNoteOn(Note note) {
		SevenUp4Live.post("Got note ON: " + note);
		
		app.outlet(0, new Atom[]{
				Atom.newAtom(instanceNum),
				Atom.newAtom(channel + 1),
				Atom.newAtom(144),
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
