package mtn.sevenuplive.max.mxj;


public class SevenUp4LiveStepperClient extends SevenUp4LiveMidiClient {
	
	public SevenUp4LiveStepperClient(SevenUp4Live app, int instanceNum, int ch) {
		super(app, instanceNum, ch);
	}
	
	protected int getOutletOrdinal() {
		return SevenUp4Live.eOutlets.StepperMidiOutlet.ordinal();
	}
	
}
