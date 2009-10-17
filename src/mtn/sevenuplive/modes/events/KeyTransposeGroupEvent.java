package mtn.sevenuplive.modes.events;

public class KeyTransposeGroupEvent implements Event {
	
	public static final String KEY_TRANSPOSE_GROUP_EVENT = "KEY_TRANSPOSE_GROUP_EVENT";

	private int group;
	private int key_x;
	private int key_y;
	
	public int getGroup() {
		return group;
	}

	public int getKeyX() {
		return key_x;
	}
	
	public int getKeyY() {
		return key_y;
	}
	
	public KeyTransposeGroupEvent() {}
		
	public KeyTransposeGroupEvent(int group, int key_x, int key_y) {
		this.group = group;
		this.key_x = key_x;
		this.key_y = key_y;
	}
	
	public String getType() {
		return KEY_TRANSPOSE_GROUP_EVENT;
	}

}
