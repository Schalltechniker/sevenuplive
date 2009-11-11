package mtn.sevenuplive.modes;

import java.util.ArrayList;

import mtn.sevenuplive.m4l.Note;

public interface PlayContext {
	
	/**
	 * Performs any tranposition of pitch on notes.
	 * Note that if notes are modified, a new Array with new notes should be returned
	 * @param notes
	 */
	public ArrayList<Note> transpose(ArrayList<Note> notes, int transpositionIndex);
	
	/**
	 * Are we transposing
	 * @return
	 */
	public boolean getTranspose();
	
	/**
	 * Get Current Transposition Context for a sequence
	 */
	public TranspositionContext getTranspositionContext(int sequence);
	
	/**
	 * Transpose with a specific context
	 */
	public Note transposeWithContext(Note note, TranspositionContext tc);
	
}
