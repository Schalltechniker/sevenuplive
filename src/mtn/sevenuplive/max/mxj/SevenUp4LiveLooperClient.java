package mtn.sevenuplive.max.mxj;


public class SevenUp4LiveLooperClient extends SevenUp4LiveMidiClient {
	
	public SevenUp4LiveLooperClient(SevenUp4Live app, int instanceNum, int ch) {
		super(app, instanceNum, ch);
	}
	
	protected int getOutletOrdinal() {
		return SevenUp4Live.eOutlets.LooperMidiOutlet.ordinal();
	}
	
}
