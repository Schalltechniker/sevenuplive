package mtn.sevenuplive.modes;

/**
 * Class for control values that have an ID and value assigned
 * @author Adam Ribaudo
 *
 */
public class ControlValue {
	
		private int id;
		private int value;
		
		public ControlValue(int id, int value)
		{
			this.id = id;
			this.value = value;
		}
		
		public int getId(){
			return this.id;
		}
		
		public int getValue(){
			return this.value;
		}
	
}
