package mtn.sevenuplive.max.mxj;

import mtn.sevenuplive.m4l.M4LController;
import mtn.sevenuplive.m4l.M4LForwardingMidiOutPort;
import mtn.sevenuplive.m4l.M4LMidiOut;
import mtn.sevenuplive.m4l.Note;

import com.cycling74.max.Atom;

public class SevenUp4LiveMelodizerClient implements M4LMidiOut {
	
	private SevenUp4Live app;
	
	public SevenUp4LiveMelodizerClient(SevenUp4Live app, M4LMidiOut[] outs) {
	
		this.app = app;
		
		M4LForwardingMidiOutPort port = null;
		
		for (int i = 0; i < 7; i++) {
			// Wire together
			if (outs[i] instanceof M4LForwardingMidiOutPort) {
				port = (M4LForwardingMidiOutPort)outs[i];
				port.setForwardingPort(this);
			}
		}
		
	}
	///////////////////////////////////////////////////////
	// Implementation of M4LMidiOut  
	
	public void sendController(M4LController controller) {
		SevenUp4Live.post("Got Controller: " + controller);
	}

	public void sendNoteOff(Note note) {
		SevenUp4Live.post("Got note OFF: " + note);
		
		app.outlet(0, new Atom[]{Atom.newAtom(144),
				Atom.newAtom(note.getPitch()), 
				Atom.newAtom(note.getVelocity())});
	}

	public void sendNoteOn(Note note) {
		SevenUp4Live.post("Got note ON: " + note);
		
		app.outlet(0, new Atom[]{Atom.newAtom(144),
				Atom.newAtom(note.getPitch()), 
				Atom.newAtom(note.getVelocity())});
	}


}
