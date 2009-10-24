package mtn.sevenuplive.m4l;

public class M4LForwardingMidiOutPort implements M4LMidiOut {

	private M4LMidiOut forwardingPort;
	
	public M4LMidiOut getForwardingPort() {
		return forwardingPort;
	}

	public void setForwardingPort(M4LMidiOut forwardingPort) {
		this.forwardingPort = forwardingPort;
	}

	public M4LForwardingMidiOutPort(int ch, M4LMidiOut forwardingPort) {
		this.forwardingPort = forwardingPort;
	}
	
	public void sendController(M4LController controller) {
		if (forwardingPort != null)
			forwardingPort.sendController(controller);
	}

	public void sendNoteOff(Note note) {
		if (forwardingPort != null)
			forwardingPort.sendNoteOff(note);
	}

	public void sendNoteOn(Note note) {
		if (forwardingPort != null)
			forwardingPort.sendNoteOn(note);
	}

}
