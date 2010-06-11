/*
	Copyright 2009 Adam Ribaldo 
	 
	Developed by Adam Ribaldo, Chris Lloyd
    
    This file is part of SevenUpLive.
    http://www.makingthenoise.com/sevenup/

    SevenUpLive is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    SevenUpLive is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with SevenUpLive.  If not, see <http://www.gnu.org/licenses/>.
*/

package mtn.sevenuplive.m4l;

public class Note {
	
	public final static float DEFAULT_VELOCITY = 100;
	public final static int DEFAULT_DURATION = 0;
	
	private int pitch;
	
	/** Velocity is between 0...1 */ 
	private float vel;
	
	private int dur;
	
	private int status;
	
	public Note(int pitch) {
		this(pitch, DEFAULT_VELOCITY, DEFAULT_DURATION);
	}
	
	/**
	 * @param pitch Midi pitch 0...127
	 * @param vel between 0...1
	 */
	public Note(int pitch, float vel) {
		this(pitch, vel, DEFAULT_DURATION);
	}
	
	/**
	 * @param pitch Midi pitch 0...127
	 * @param vel between 0...1
	 * @param dur
	 */
	public Note(int pitch, float vel, int dur) {
		this(pitch, vel, dur, 144);
	}
	
	/**
	 * @param pitch Midi pitch 0...127
	 * @param vel between 0...1
	 * @param dur
	 * @param status
	 */
	public Note(int pitch, float vel, int dur, int status) {
		this.pitch = pitch;
		this.vel = vel;
		this.dur = dur;
		this.status = status;
		
		// Trim to value between 0...1
		if (vel > 1 || vel < 0) {
			this.vel = this.vel > 1 ? 1 : this.vel;
			this.vel = this.vel < 0 ? 0 : this.vel;
		}
	}
	
	public int getPitch(){ 
		return pitch;
	}
	
	/**
	 * Range of velocity is between 0...1
	 * @return
	 */
	public float getVelocity() {
		return vel;
	}
	
	public int getLength() {
		return dur;
	}
	
	public int getStatus() {
		return status;
	}
}
