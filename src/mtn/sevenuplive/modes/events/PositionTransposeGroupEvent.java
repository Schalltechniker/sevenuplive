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

package mtn.sevenuplive.modes.events;

public class PositionTransposeGroupEvent implements Event {
	
	public static final String POSITION_TRANSPOSE_GROUP_EVENT = "POSITION_TRANSPOSE_GROUP_EVENT";

	private int group;
	private int position;
	
	public int getGroup() {
		return group;
	}

	public int getPosition() {
		return position;
	}
	
	public PositionTransposeGroupEvent() {}
		
	public PositionTransposeGroupEvent(int group, int key) {
		this.group = group;
		this.position = key;
	}
	
	public String getType() {
		return POSITION_TRANSPOSE_GROUP_EVENT;
	}

}
