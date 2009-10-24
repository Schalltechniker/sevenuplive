package mtn.sevenuplive.max.mxj;

import com.cycling74.max.Atom;
import com.cycling74.max.DataTypes;
import com.cycling74.max.MaxObject;

public class SevenUp4LiveMelodizer extends MaxObject {
	
	private static final String[] INLET_ASSIST = new String[]{
		"control messages"
	};
	private static final String[] OUTLET_ASSIST = new String[]{
		"outlet 1 help"
	};
	
	public SevenUp4LiveMelodizer(Atom[] args)
	{
		declareInlets(new int[]{DataTypes.ALL});
		declareOutlets(new int[]{DataTypes.ALL});
		
		setInletAssist(INLET_ASSIST);
		setOutletAssist(OUTLET_ASSIST);
		
	}
	
	public void bang() {
		post("Hello world!! This is SevenUp Melodizer component in MAX");
	}
    
	public void inlet(int i)
	{
		int inletNum = getInlet();; 
		//post("I got an integer in inlet "+ inletNum);
	}
		
	public void inlet(float f)
	{
	}
	
	public void list(Atom[] list)
	{
	}

	
}
