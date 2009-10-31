/**
 * SevenUp for Monome's 40h
 * @author Adam Ribaudo
 * arribaud@gmail.com
 * 12/09/2007
 * Copyright 2007 - All rights reserved
 * 
 * SevenUpApplet Class - initializes the SevenUpApplet that extends processing's core PApplet class.  Sends COM port to MonomeUp and opens the Midi input device.
 */

package mtn.sevenuplive.main;
import mtn.sevenuplive.m4l.M4LMidi;
import mtn.sevenuplive.m4l.M4LMidiSystem;
import mtn.sevenuplive.m4l.Note;
import mtn.sevenuplive.max.mxj.SevenUp4Live;
import mtn.sevenuplive.max.mxj.SevenUpClock;
import mtn.sevenuplive.modes.AllModes;
import mtn.sevenuplive.modes.MelodizerModel;
import mtn.sevenuplive.scales.Scale;
import mtn.sevenuplive.scales.ScaleName;

import org.jdom.Document;

import proxml.XMLInOut;

public class SevenUpApplet extends processing.core.PApplet implements SevenUpClock
{
	private static final long serialVersionUID = 1L;
	private MonomeUp m;
	private XMLInOut xmlIO;
	private Scale monomeScale;
	private M4LMidi midiIO;
	private ConnectionSettings sevenUpConnections;
	
	private int monomeType = MonomeUp.MONOME_64;
	
	/** Dirty flag for changes to the patch */
	private boolean dirty; 
	private boolean isRunning = false;
	
	public boolean isRunning() {
		return isRunning;
	}

	public SevenUpApplet(ConnectionSettings _sevenUpConnections)
	{
		sevenUpConnections = _sevenUpConnections;
		init();
	}
	
	public void setup()
	{
	   frameRate(35);
	   
	   xmlIO = new XMLInOut(this);
	   midiIO = M4LMidiSystem.getInstance();
	   midiIO.printDevices();
	   monomeScale = new Scale(ScaleName.Major);
	   
	   // Get the type of monome selected
	   monomeType = sevenUpConnections.monomeType;
		   
	   // Figure out dimensions of monome grid
	   int x_grids = 1;
	   int y_grids = 1;
	   switch (monomeType) {
	   case 0: //1 x 64
		   x_grids=1; y_grids=1;
		   break;
	   case 1: //128H
		   x_grids=2; y_grids=1;
		   break;
	   case 2: //128V
		   x_grids=1; y_grids=2;
		   break;
	   case 3: //3 x 64's
		   x_grids=1; y_grids=3;
		   break;
	   case 4: //256
		   x_grids=2; y_grids=2;
		   break;
	   case 5: //2x256
		   x_grids=4; y_grids=2;
		   break;
	   case 6: //3x256
		   x_grids=6; y_grids=2;
		   break;	   
	   case 7:
		   x_grids = 1; y_grids = 5;
		   break;
	   case 8:
		   x_grids = 1; y_grids = 6;
		   break;
	   case 9:
		   x_grids = 1; y_grids = 7;
		   break;
	   case 10:
		   x_grids = 1; y_grids = 8;
		   break;
	   case 11:
		   x_grids = 1; y_grids = 9;
		   break;
	   case 12: //10 x 64's
		   x_grids = 1; y_grids = 10;
		   break;
	   };
	   
	   m = new MonomeUp(x_grids, y_grids, sevenUpConnections, monomeScale, midiIO, this);
	   m.lightsOff();
	   this.online = false;
	   
	   //This sets us up to be called with clock events
	   SevenUp4Live.setClock(this);
	   
	   // Ch8 is the channel we listen for midi to control certain actions in SevenUp
	   midiIO.plug(this, "noteOn", sevenUpConnections.midiInputDeviceName, 7);
	   
	   //Channels 1-7 used to listen to Clip Launch events.
	   midiIO.plug(this, "noteOnCh1", sevenUpConnections.midiInputDeviceName, 0);
	   midiIO.plug(this, "noteOnCh2", sevenUpConnections.midiInputDeviceName, 1);
	   midiIO.plug(this, "noteOnCh3", sevenUpConnections.midiInputDeviceName, 2);
	   midiIO.plug(this, "noteOnCh4", sevenUpConnections.midiInputDeviceName, 3);
	   midiIO.plug(this, "noteOnCh5", sevenUpConnections.midiInputDeviceName, 4);
	   midiIO.plug(this, "noteOnCh6", sevenUpConnections.midiInputDeviceName, 5);
	   midiIO.plug(this, "noteOnCh7", sevenUpConnections.midiInputDeviceName, 6);
	   
	   //Channels 9-15 used to listen to external instruments for melodizer
	   midiIO.plug(this, "noteOnCh9", sevenUpConnections.midiInputDeviceName, 8);
	   midiIO.plug(this, "noteOnCh10", sevenUpConnections.midiInputDeviceName, 9);
	   midiIO.plug(this, "noteOnCh11", sevenUpConnections.midiInputDeviceName, 10);
	   midiIO.plug(this, "noteOnCh12", sevenUpConnections.midiInputDeviceName, 11);
	   midiIO.plug(this, "noteOnCh13", sevenUpConnections.midiInputDeviceName, 12);
	   midiIO.plug(this, "noteOnCh14", sevenUpConnections.midiInputDeviceName, 13);
	   midiIO.plug(this, "noteOnCh15", sevenUpConnections.midiInputDeviceName, 14);
	   
	   isRunning = true;
	}
	
	public ConnectionSettings getSevenUpConnections() {
		return sevenUpConnections;
	}

	public void setSevenUpConnections(ConnectionSettings sevenUpConnections) {
		this.sevenUpConnections = sevenUpConnections;
	}
	
	private Scale getScaleFromString(String scaleName)
	{
		ScaleName sn = ScaleName.valueOf(scaleName);
		if (sn == null)
			return new Scale(ScaleName.Major);
		
		return new Scale(sn);
	}
	
	public void setMelody1Scale(String scaleName)
	{
		m.setMelody1Scale(getScaleFromString(scaleName));
	}
	
	public void setMelody2Scale(String scaleName)
	{
		m.setMelody2Scale(getScaleFromString(scaleName));
	}
	
	public void setLoopChokeGroup(int loopNum, int chokeGroup)
	{
		m.setLoopChoke(loopNum, chokeGroup);
	}
	
	public void setMel1TransposeGroup(int slotNum, int chokeGroup)
	{
		m.setMel1TransposeGroup(slotNum, chokeGroup);
	}
	
	public void setMel2TransposeGroup(int slotNum, int chokeGroup)
	{
		m.setMel2TransposeGroup(slotNum, chokeGroup);
	}
	
	public boolean getMel1TransposeSustain(int slotNum)
	{
		return m.getMel1TransposeSustain(slotNum);
	}
	
	public boolean getMel2TransposeSustain(int slotNum)
	{
		return m.getMel2TransposeSustain(slotNum);
	}
	
	public void setMel1TransposeSustain(int slotNum, boolean value)
	{
		m.setMel1TransposeSustain(slotNum, value);
	}
	
	public void setMel2TransposeSustain(int slotNum, boolean value)
	{
		m.setMel2TransposeSustain(slotNum, value);
	}
	
	public int getMel1TransposeGroup(int slotNum)
	{
		return m.getMel1TransposeGroup(slotNum);
	}
	
	public int getMel2TransposeGroup(int slotNum)
	{
		return m.getMel2TransposeGroup(slotNum);
	}
	
	
	public void setGateLoopChokes(boolean gateLoops)
	{
		m.setGateLoopChokes(gateLoops);
	}
	
	public boolean getGateLoopChokes()
	{
		return m.getGateLoopChokes();
	}
	
	public boolean openSevenUpPatch(String patchPath)
	{
		try
		{
			xmlIO.loadElement(patchPath);
			dirty = false;
			return true;
		}catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public void draw()
	{
		m.draw();
	}
	
	public void teardown() {
		
		try {
			if (m != null) {
				m.forceShutdown();
			}
			this.stop();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		isRunning = false;
	}
	
	public void finalize()
	{
		if (m != null) {
			m.lightsOff();
		}
		m = null;
	}
	
	public void noteOn(Note note){
		//status 144 = noteOn
		//status 128 = noteOff? note 123 = stop?
		if(note.getPitch() == 123 && note.getStatus() == 128)
			m.reset();
		else if (note.getStatus() == 144)
			m.noteOn(note.getPitch());
	}
	
	public void noteOnCh1(Note note){
		m.clipLaunch(note.getPitch(), note.getVelocity(), 0);
	}
	public void noteOnCh2(Note note){
		m.clipLaunch(note.getPitch(), note.getVelocity(), 1);
	}
	public void noteOnCh3(Note note){
		m.clipLaunch(note.getPitch(), note.getVelocity(), 2);
	}
	public void noteOnCh4(Note note){
		m.clipLaunch(note.getPitch(), note.getVelocity(), 3);
	}
	public void noteOnCh5(Note note){
		m.clipLaunch(note.getPitch(), note.getVelocity(), 4);
	}
	public void noteOnCh6(Note note){
		m.clipLaunch(note.getPitch(), note.getVelocity(), 5);
	}
	public void noteOnCh7(Note note){
		m.clipLaunch(note.getPitch(), note.getVelocity(), 6);
	}
	
	public void noteOnCh9(Note note){
		if(note.getPitch() != 0)
			m.extNoteOn(note, 8);
	}
	
	public void noteOnCh10(Note note){
		if(note.getPitch() != 0)
			m.extNoteOn(note, 9);
	}

	public void noteOnCh11(Note note){
		if(note.getPitch() != 0)
			m.extNoteOn(note, 10);
	}

	public void noteOnCh12(Note note){
		if(note.getPitch() != 0)
			m.extNoteOn(note, 11);
	}
	
	public void noteOnCh13(Note note){
		if(note.getPitch() != 0)
			m.extNoteOn(note, 12);
	}

	public void noteOnCh14(Note note){
		if(note.getPitch() != 0)
			m.extNoteOn(note, 13);
	}
	
	public void noteOnCh15(Note note){
		if(note.getPitch() != 0)
			m.extNoteOn(note, 14);
	}
	
	public Scale getMelody1Scale()
	{
		return m.getMelody1Scale();
	}
	
	public Scale getMelody2Scale()
	{
		return m.getMelody2Scale();
	}
	
	public Document toJDOMXMLDocument(String fileName)
	{
		m.setPatchTitle(fileName);
		return m.toXMLDocument(fileName);
	}
	
	public boolean loadJDOMXMLDocument(Document XMLDoc)
	{
		return m.loadXML(XMLDoc);
	}
	
	public int getLoopChokeGroup(int loopNum)
	{
		return m.getLoopChokeGroup(loopNum);
	}
	
	public void quit()
	{
		//Redundant
		midiIO.closeInput(sevenUpConnections.midiInputDeviceName);
		midiIO.closeOutputs();
	}

	public void close() {
		midiIO.closeInput(sevenUpConnections.midiInputDeviceName);
		midiIO.closeOutputs();
	}

	public int loadPrevPatch() {
		return m.loadPrevPatch();
	}

	public int loadNextPatch() {
		return m.loadNextPatch();
	}
	
	public int getPatchPackSize()
	{
		return m.getPatchPackSize();
	}

	public String getPatchTitle() {
		return m.getPatchTitle();
	}
	
	public void setMonomeType(int monomeType) {
		this.monomeType = monomeType;
	}
	
	public int getMonomeType() {
		return monomeType;
	}
	
	public int getLoopType(int loopNum) {
		return m.getLoopType(loopNum);
	}

	public void setMelody1ClipMode(boolean b) {
		if (b) {
			AllModes.getInstance().getMelodizer1Model().setCurrentMode(MelodizerModel.eMelodizerMode.CLIP);
		} else {
			AllModes.getInstance().getMelodizer1Model().setCurrentMode(MelodizerModel.eMelodizerMode.KEYBOARD);
		}
		
	}
	
	public void setMelody1Mode(MelodizerModel.eMelodizerMode mode) {
		AllModes.getInstance().getMelodizer1Model().setCurrentMode(mode);
	}
	
	public MelodizerModel.eMelodizerMode getMelody1Mode()
	{
		return AllModes.getInstance().getMelodizer1Model().getCurrentMode();
	}
	
	public void setMelody2Mode(MelodizerModel.eMelodizerMode mode) {
		AllModes.getInstance().getMelodizer2Model().setCurrentMode(mode);
	}
	
	public MelodizerModel.eMelodizerMode getMelody2Mode()
	{
		return AllModes.getInstance().getMelodizer2Model().getCurrentMode();
	}
	
	public void setMelody1Transpose(boolean transpose) {
		AllModes.getInstance().getMelodizer1Model().setTranspose(transpose);
	}

	public boolean getMelody1Transpose() {
		return AllModes.getInstance().getMelodizer1Model().getTranspose();
	}
	
	public void setMelody1AltMode(MelodizerModel.eMelodizerMode mode) {
		AllModes.getInstance().getMelodizer1Model().setAltMode(mode);
	}
	
	public MelodizerModel.eMelodizerMode getMelody1AltMode()
	{
		return AllModes.getInstance().getMelodizer1Model().getAltMode();
	}
	
	public void setMelody2Transpose(boolean transpose) {
		AllModes.getInstance().getMelodizer2Model().setTranspose(transpose);
	}

	public boolean getMelody2Transpose() {
		return AllModes.getInstance().getMelodizer2Model().getTranspose();
	}

	public void setMelody2AltMode(MelodizerModel.eMelodizerMode mode) {
		AllModes.getInstance().getMelodizer2Model().setAltMode(mode);
	}
	
	public MelodizerModel.eMelodizerMode getMelody2AltMode()
	{
		return AllModes.getInstance().getMelodizer2Model().getAltMode();
	}
	
	public void setLooperMute(boolean mute) {
		m.setLooperMute(mute);
	}
	
	public void setMel1RecMode(int melRecMode) {
		m.setMel1RecMode(melRecMode);
	}
	
	public void setMel2RecMode(int melRecMode) {
		m.setMel2RecMode(melRecMode);
	}
	
	public int getMel1RecMode() {
		return m.getMel1RecMode();
	}
	
	public int getMel2RecMode() {
		return m.getMel1RecMode();
	}
	
	
	public void setLoopLength(int loopNum, float length) {
		m.setLoopLength(loopNum, length);
	}
	
	public void setLoopType(int loopNum, int type) {
		m.setLoopType(loopNum, type);
	}
	
	public float getLoopLength(int loopNum )
	{
		return m.getLoopLength(loopNum);
	}
	
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public boolean isDirty() {
		return dirty;
	}

	public void sendBigTick() {
		m.sendBigTick();
	}

	public void pumpSequencerHeart() {
		m.pumpSequencerHeart();
	}

	public void sendSmallTick() {
		m.sendSmallTick();
	}
	
	public void cSharp4() {
		m.cSharp4();
	}

	public void pumpMelodizerHeart() {
		m.pumpMelodizerHeart();	
	}

	public void pumpLooperHeart() {
		m.pumpLooperHeart();
	}

}
		