package Algorithm;

import java.util.ArrayList;

import Rules.Coordinate;

public class RandomPlay {

	private final static char[] PIECES = {'p', 't', 'c', 'b', 'k', 'q'};
	
	//0-> preto ; 1-> branco
	public static char choosePiece(int color) throws Exception {
		char c = PIECES[(int)(Math.random()*(PIECES.length))];
		if(color > 1)
			throw new Exception("The value isn't a color.");
		else if(color == 1)
			c = (char)((int)c - ((int)'a' - (int)'A'));
		return c;	
	}
	public static Coordinate chooseMovement(ArrayList<Coordinate> movements) {	
		return movements.get((int)Math.random()*(movements.size()));	
	}

}