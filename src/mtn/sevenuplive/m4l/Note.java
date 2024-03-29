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
	
	private int pitch;
	private int vel;
	private int dur;
	private int status;
	
	public Note(int pitch, int vel, int dur) {
		this(pitch, vel, dur, 144);
	}
	
	public Note(int pitch, int vel, int dur, int status) {
		this.pitch = pitch;
		this.vel = vel;
		this.dur = dur;
		this.status = status;
	}
	
	public int getPitch(){ 
		return pitch;
	}
	
	public int getVelocity() {
		return vel;
	}
	
	public int getLength() {
		return dur;
	}
	
	public int getStatus() {
		return status;
	}
}
