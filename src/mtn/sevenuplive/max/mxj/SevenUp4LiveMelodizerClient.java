package mtn.sevenuplive.max.mxj;


public class SevenUp4LiveMelodizerClient extends SevenUp4LiveMidiClient {
	
	public SevenUp4LiveMelodizerClient(SevenUp4Live app, int instanceNum, int ch) {
		super(app, instanceNum, ch);
	}
	
	protected int getOutletOrdinal() {
		return SevenUp4Live.eOutlets.MelodizerMidiOutlet.ordinal();
	}
	
}
