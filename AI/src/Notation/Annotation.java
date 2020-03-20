package Notation;

import java.util.ArrayList;

import Rules.Coordinate;

public class Annotation {

	private static ArrayList<String> movementsChess = new ArrayList<>();
	private static ArrayList<Coordinate> movementsComputer = new ArrayList<>();
	
	public static void putMovement(String movement) {
		movementsChess.add(movement);
		Coordinate c = new Coordinate(Translator.NotationChessToComputer(movement.charAt(0),Character.getNumericValue(movement.charAt(1)))[0], Translator.NotationChessToComputer(movement.charAt(0),Character.getNumericValue(movement.charAt(1)))[1]);
		movementsComputer.add(c);
	
	}
	public static void putMovement(int pos_i, int pos_j) {
		
	}
	public static ArrayList<String> getMovements(){
		return movementsChess;
		
	}
	public static ArrayList<Coordinate> getMovementsMatrixCoordinate(){
		return movementsComputer;
		
	}
	
}
