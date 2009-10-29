package mtn.sevenuplive.m4l;

public class M4LController {

	private int cc;
	
	private int value;

	public M4LController(int cc, int value) {
		this.cc = cc;
		this.value = value;
	}
	
	public int getCC() {
		return cc;
	}

	public void setCC(int cc) {
		this.cc = cc;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
