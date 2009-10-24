package mtn.sevenuplive.m4l;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import mtn.sevenuplive.modes.Controller;

import processing.core.PApplet;

public class M4LForwardingMidiInPort implements M4LMidiIn, M4LMidiOut {

	private PApplet forwardingPort;
	private Method callbackOnNoteMethod;
	private Method callbackOnControllerMethod;
	
	public M4LForwardingMidiInPort(int ch, String callbackFunction, PApplet forwardingPort) {
		this.forwardingPort = forwardingPort;
		
		try {
			if (forwardingPort != null) {
				callbackOnControllerMethod = forwardingPort.getClass().getMethod(callbackFunction, Controller.class);
			}	
		} catch (SecurityException e) {
			// Do nothing
		} catch (NoSuchMethodException e) {
			// Do nothing
		}
		
		try {
			if (forwardingPort != null) {
				callbackOnNoteMethod = forwardingPort.getClass().getMethod(callbackFunction, Note.class);
			}	
		} catch (SecurityException e) {
			// Do nothing
		} catch (NoSuchMethodException e) {
			// Do nothing
		}
	}
	
	public PApplet getForwardingPort() {
		return forwardingPort;
	}

	public void setForwardingPort(PApplet forwardingPort) {
		this.forwardingPort = forwardingPort;
	}

	public void sendController(M4LController controller) {
		if (forwardingPort == null || callbackOnControllerMethod == null)
			return;
		
		try {
			callbackOnControllerMethod.invoke(callbackOnNoteMethod, controller);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void sendNoteOff(Note note) {
		if (forwardingPort == null || callbackOnNoteMethod == null)
			return;
		
		try {
			callbackOnNoteMethod.invoke(callbackOnNoteMethod, note);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void sendNoteOn(Note note) {
		if (forwardingPort == null || callbackOnNoteMethod == null)
			return;
		
		try {
			callbackOnNoteMethod.invoke(callbackOnNoteMethod, note);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
}
