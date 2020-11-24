package Notation;

import java.util.ArrayList;

import Rules.Coordinate;

public class Annotation {

	private static ArrayList<String> movementsChess = new ArrayList<>();
	private static ArrayList<Coordinate> movementsComputer = new ArrayList<>();
	
	public static void putMovement(int pos_i, int pos_j) {
		Coordinate c = new Coordinate(pos_i, pos_j);
		movementsComputer.add(c);
		movementsChess.add(c.getPos_i() + "" + c.getPos_j());
	}
	public static ArrayList<String> getMovements(){
		return movementsChess;
	}
	public static ArrayList<Coordinate> getMovementsMatrixCoordinate(){
		return movementsComputer;	
	}
	public static String getLastMovement(){
		return movementsChess.get(movementsChess.size()-1);
	}
	public static Coordinate getLastMovementMatrixCoordinate(){
		return movementsComputer.get(movementsComputer.size()-1);
	}
	
}
