/*
	Copyright (c) 2009 Jesse Kriss
	
	This file and all files in the jklabs java package have been modified for 
	SevenUpLive based on the Monomic Library written originally by Jesse Kriss 
	under the MIT license shown below.
	
	SevenUpLive as a whole is licensed under the GNU Lesser General Public License
    provided here <http://www.gnu.org/licenses/>.
    
    Original MIT License Notice for this file follows:  
	
	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:
	
	The above copyright notice and this permission notice shall be included in
	all copies or substantial portions of the Software.
	
	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
	THE SOFTWARE.

*/

package jklabs.monomic;

import netP5.NetAddress;
import oscP5.OscEventListener;
import oscP5.OscMessage;
import oscP5.OscP5;
import oscP5.OscStatus;

public class MonomeOSC extends Monome implements MonomeListener {

	// oscP5 instance for the osc communication
	private OscP5 oscP5;
	private String boxName = "box";

	// osc constants
	private static final String LED = "led";
	private static final String ROW = "led_row";
	private static final String COL = "led_col";
	private static final String SHUTDOWN = "shutdown";
	private static final String BUTTON = "press";
	private static final String TEST = "test";
	private static final String ADC = "adc";
	private static final String TILT = "tilt";
	private static final String REFRESH = "refresh";
	private static final String ADC_ENABLE = "adc_enable";
	private static final String INTENSITY = "intensity";
	
	/** 
	 * This is extended monome protocol for press with velocity 
	 * xpress [x] [y] [velocity] 
	 * velocity is integer between 0...127 
	 */
	private static final String XBUTTON = "xpress";
	
	/** 
	 * This is extended monome protocol for aftertouch 
	 * xafter [x] [y] [value] 
	 * value is float between 0...1 
	 */
	private static final String XAFTER = "xafter";
	
	// Address for P5Osc
	private NetAddress myRemoteLocation;
	private NetAddress myLocalLocation;
	
	private MonomeListener listener;
	
	// osc addresses for this instance
	protected String led, row, col, shutdown, button, test, adc, tilt, refresh, adc_enable, intensity, xbutton, xafter;
	
	/**
	 * Construct any monome grid in byte(8) button increments
	 * @param x_bytes size of monome width in bytes, 1 byte = 8 buttons  
	 * @param y_bytes size of monome height in bytes, 1 byte = 8 buttons
	 * @param boxName prefix to use "box" or "mlr" etc
	 * @param host IP address of the host
	 * @param sendPort port to send on
	 * @param receivePort port to receive on
	 */
	public MonomeOSC(int x_bytes, int y_bytes) {
		super(x_bytes, y_bytes);
	}

	/**
	 * Construct any monome grid in byte(8) button increments
	 * @param listener listener class for events
	 * @param x_bytes size of monome width in bytes, 1 byte = 8 buttons  
	 * @param y_bytes size of monome height in bytes, 1 byte = 8 buttons
	 * @param boxName prefix to use "box" or "mlr" etc
	 * @param host IP address of the host
	 * @param sendPort port to send on
	 * @param receivePort port to receive on
	 */
	public MonomeOSC(MonomeListener listener, int x_bytes, int y_bytes) {
		super(x_bytes, y_bytes);
		this.listener = listener;
	}

	/**
	 * You have to call this to start the OSC communications
	 * @param boxName
	 * @param host
	 * @param sendPort
	 * @param receivePort
	 */
	public void startup(String boxName, String host, int sendPort, int receivePort) {
		this.boxName = boxName;
		
		initOsc(host, sendPort, receivePort);
		setBoxName(boxName);
		
		super.init();
	}
	
	public String getBoxName() {
		return boxName;
	}
	
	public void setBoxName(String boxName) {
		this.boxName = boxName;

		// set osc addresses
		led = prependName(LED);
		row = prependName(ROW);
		col = prependName(COL);
		shutdown = prependName(SHUTDOWN);
		button = prependName(BUTTON);
		test = prependName(TEST);
		tilt = prependName(TILT);
		refresh = prependName(REFRESH);
		adc = prependName(ADC);
		adc_enable = prependName(ADC_ENABLE);
		intensity = prependName(INTENSITY);
		
		// Extended monome proto messages
		xbutton = prependName(XBUTTON);
		xafter = prependName(XAFTER);
	}

	private String prependName(String command) {
		return "/" + boxName + "/" + command;
	}

	////////////////////////////////////////////////// monome functions

	public void testPattern(boolean b) {
		super.testPattern(b);
		OscMessage oscMsg = makeMessage(test);
		oscMsg.add(b ? 1 : 0);
		send(oscMsg);
	}

	public void setValue(int x, int y, int value) {
		super.setValue(x, y, value);
		OscMessage oscMsg = makeMessage(led);
		oscMsg.add(x);
		oscMsg.add(y);
		oscMsg.add(value);
		send(oscMsg);
	}

	public void setRow(int i, byte[] bitVals) {
		super.setRow(i, bitVals);
		setRowOrColumn(row, i, bitVals);
	}

	public void setCol(int i, byte[] bitVals) {
		super.setCol(i, bitVals);
		setRowOrColumn(col, i, bitVals);
	}

	public void setLowPower(boolean b) {
		super.setLowPower(b);
		OscMessage oscMsg = makeMessage(shutdown);
		oscMsg.add(b ? 1 : 0);
		send(oscMsg);
	}

	public void setLedIntensity(float f) {
		OscMessage oscMsg = makeMessage(intensity);
		oscMsg.add(f);
		send(oscMsg);
	}
	
	protected void setADC(int i, boolean b) {
		OscMessage oscMsg = makeMessage(adc_enable);
		oscMsg.add(i);
		oscMsg.add(b?1:0);
		send(oscMsg);
	}

	////////////////////////////////////////////////// helper methods

	private OscMessage makeMessage(String command) {
		return new OscMessage(command);
	}

	private void setRowOrColumn(String command, int i, byte[] bitVals) {
		OscMessage oscMsg = makeMessage(command);
		oscMsg.add(i);
		for (int b : bitVals)
			oscMsg.add((int)b);
		
		send(oscMsg);
	}

	////////////////////////////////////////////////// osc communication

	private void send(OscMessage m) {
		if (debug == FINE) System.out.println("$$ sending " + m.addrPattern() + " " + m.arguments());
		oscP5.send(m, myRemoteLocation);
	}

	public void initOsc(String host, int sendPort, int receivePort) {
		myRemoteLocation = new NetAddress(host, sendPort);
		myLocalLocation = new NetAddress(host, receivePort);
		oscP5 = new OscP5(new OSCReceiver(this), receivePort);
	}
	
	public void oscEvent(OscMessage oscIn) {
		if (debug == FINE) 
			System.out.println("received a message ... forwarding to unpackMessage(OscIn)");
		unpackMessage(oscIn);
	}

	public void unpackMessage(OscMessage oscIn) {
		if (boxName == null) {
			String a = oscIn.getAddrPattern();
			if (a.indexOf("m40h") != -1) {
				String newBox = a.substring(1, a.indexOf('/', 1));
				System.out.println("discovered new monome 40h: " + newBox);
				setBoxName(newBox);
			}
		}
		if (oscIn.checkAddrPattern(button)) {
			if (oscIn.checkTypetag("iii")) {
				int x = oscIn.get(0).intValue();
				int y = oscIn.get(1).intValue();
				int value = oscIn.get(2).intValue();
				handleInputEvent(x, y, value);
			}
		} else if (oscIn.checkAddrPattern(adc)) {
			if (oscIn.checkTypetag("if")) {
				int port = oscIn.get(0).intValue();
				float value = oscIn.get(1).floatValue();
				handleAdcInput(port, value);
			} 
		} else if (oscIn.checkAddrPattern(tilt)) {
			if (oscIn.checkTypetag("ff")) {
				float xval = oscIn.get(0).floatValue();
				float yval = oscIn.get(1).floatValue();
				handleAdcInput(0, xval/255.0f);
				handleAdcInput(1, yval/255.0f);
			}
		} if (oscIn.checkAddrPattern(xbutton)) {
			if (oscIn.checkTypetag("iii")) {
				int x = oscIn.get(0).intValue();
				int y = oscIn.get(1).intValue();
				int value = oscIn.get(2).intValue();
				handleExtendedInputEvent(x, y, value);
			}
		} if (oscIn.checkAddrPattern(xafter)) {
			if (oscIn.checkTypetag("iif")) {
				int x = oscIn.get(0).intValue();
				int y = oscIn.get(1).intValue();
				float value = oscIn.get(2).floatValue();
				handleAfterTouchEvent(x, y, value);
			}
		} else if (oscIn.checkAddrPattern(refresh)) {
			handleRefresh();
		} else {
			if (debug == FINE) {
				System.out.println("you have received an osc message "
						+ oscIn.getAddrPattern() + "   " + oscIn.getTypetag());
				Object[] o = oscIn.getData();
				for (int i = 0; i < o.length; i++) {
					System.out.println(i + "  " + o[i]);
				}
			}
		}
	}
	
	/**
	 * Override or implement in your listener class 
	 * @param x
	 * @param y
	 */
	public void monomePressed(int x, int y) {
		if (listener != null)
			listener.monomePressed(x, y);
	}
	
	/**
	 * Override or implement in your listener class
	 * @param x
	 * @param y
	 */
	public void monomeReleased(int x, int y) {
		if (listener != null)
			listener.monomeReleased(x, y)
;	}
	
	/**
	 * Override or implement in your listener class
	 * @param x
	 * @param value
	 */
	public void monomeAdc(int x, float value) {
		if (listener != null)
			listener.monomeAdc(x, value);
	}

	/**
	 * Override or implement in your listener class 
	 * @param x
	 * @param y
	 * @param velocity Velocity value is 0....127
	 */
	public void monomeXPressed(int x, int y, int velocity) {
		if (listener != null)
			listener.monomeXPressed(x, y, velocity);
	}
	
	/**
	 * Override or implement in your listener class 
	 * @param x
	 * @param y
	 * @param velocity Velocity value is 0....1 
	 */
	public void monomeAfterTouch(int x, int y, float value) {
		if (listener != null)
			listener.monomeAfterTouch(x, y, value);
	}

	/* (non-Javadoc)
	 * @see jklabs.monomic.MonomeListener#monomeRefresh()
	 */
	public void monomeRefresh() {
		if (listener != null)
			listener.monomeRefresh();
	}
	////////////////////////////////////////////////// cleanup

	protected void finalize() throws Throwable {
		forceShutdown();
		super.finalize();
	}
	
	public void forceShutdown() throws Throwable {
		lightsOff();
		oscP5.stop();
		oscP5.disconnect(myLocalLocation);
		oscP5.disconnect(myRemoteLocation);
	}
	
	/**
	 * The only reason this class is here is to wrap "this" MonomeOSC instance
	 * as the OSC event receiver. For some unknown reason OscP5 refuses to 
	 * notify us directly, even if MonomeOSC implements OscEventListener
	 */
	public class OSCReceiver implements OscEventListener {
		MonomeOSC parent;
		
		public OSCReceiver(MonomeOSC parent) {
			this.parent = parent;
		}
		
		public void oscEvent(OscMessage oscIn) {
			parent.oscEvent(oscIn);
		}

		public void oscStatus(OscStatus arg0) {}
	}
	

}