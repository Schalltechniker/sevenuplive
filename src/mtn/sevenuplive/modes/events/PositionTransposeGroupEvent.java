package mtn.sevenuplive.modes.events;

public class PositionTransposeGroupEvent implements Event {
	
	public static final String POSITION_TRANSPOSE_GROUP_EVENT = "POSITION_TRANSPOSE_GROUP_EVENT";

	private int group;
	private int position;
	
	public int getGroup() {
		return group;
	}

	public int getPosition() {
		return position;
	}
	
	public PositionTransposeGroupEvent() {}
		
	public PositionTransposeGroupEvent(int group, int key) {
		this.group = group;
		this.position = key;
	}
	
	public String getType() {
		return POSITION_TRANSPOSE_GROUP_EVENT;
	}

}
