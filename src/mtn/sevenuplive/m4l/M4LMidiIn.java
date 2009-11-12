package mtn.sevenuplive.m4l;

public interface M4LMidiIn {
	
	public final static String NOTE = "NOTE";
	public final static String CC = "CC";
	
	public void sendNoteOn(Note note);
	
	public void sendNoteOff(Note note);
	
	public void sendController(M4LController controller);

}
