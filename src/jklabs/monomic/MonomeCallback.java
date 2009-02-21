package jklabs.monomic;

public interface MonomeCallback {

	/**
	 * Implement in your listener class 
	 * @param x
	 * @param y
	 */
	public void monomePressed(int x, int y);
	
	/**
	 * Implement in your listener class
	 * @param x
	 * @param y
	 */
	public void monomeReleased(int x, int y);
	
	/**
	 * Implement in your listener class
	 * @param x
	 * @param value
	 */
	public void monomeAdc(int x, float value);

		
}
