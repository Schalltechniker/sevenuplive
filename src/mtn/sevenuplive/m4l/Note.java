package mtn.sevenuplive.m4l;

public class Note {
	
	private int pitch;
	private int vel;
	private int dur;
	private int status;

	public Note(int pitch, int vel, int dur) {
		this.pitch = pitch;
		this.vel = vel;
		this.dur = dur;
	}
	
	public int getPitch(){ 
		return pitch;
	}
	
	public int getVelocity() {
		return vel;
	}
	
	public int getStatus() {
		return status;
	}
	
	public int getLength() {
		return dur;
	}
}
