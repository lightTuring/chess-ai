package Notation;

import java.util.Arrays;

public class Translator {
	
	private static char[] letters = {'A','B','C','D','E', 'F', 'G', 'H'};
	private static byte[] numbers = {1,2,3,4,5,6,7,8}; 
	
	public static int[] NotationChessToComputer(char pos_w, byte pos_h) {
		
		int[] OrderedPair = {Arrays.binarySearch(letters, pos_w), Arrays.binarySearch(numbers, pos_h)};
				
		return OrderedPair;
		
	}
	public static String NotationComputerToChess(byte pos_i, byte pos_j) {
		return null;
		
	}
}
