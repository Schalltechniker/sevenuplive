package mtn.sevenuplive.m4l;

public interface M4LMidiOut {
	
	public void sendNoteOn(Note note);
	
	public void sendNoteOff(Note note);
	
	public void sendController(M4LController controller);

}
