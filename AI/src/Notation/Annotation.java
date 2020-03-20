package Notation;

import java.util.ArrayList;

import Rules.Coordinate;

public class Annotation {

	private static ArrayList<String> movementsChess = new ArrayList<>();
	private static ArrayList<Coordinate> movementsComputer = new ArrayList<>();
	
	public static void putMovement(String movement) {
		movementsChess.add(movement);
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
