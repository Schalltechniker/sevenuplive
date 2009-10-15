package mtn.sevenuplive.modes;

public class TranspositionContext {
	public int transpositionIndex;
	public int localOffset;
	public int localKeyOffset;
	public int key;
	
	public TranspositionContext(int transpositionIndex, int localOffset, int localKeyOffset, int key) {
		this.transpositionIndex = transpositionIndex;
		this.localOffset = localOffset;
		this.localKeyOffset = localKeyOffset;
		this.key = key;
	}
}
