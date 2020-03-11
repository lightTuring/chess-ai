package Notation;

import java.util.Arrays;

public class Translator {
	
	private static char[] letters = {'A','B','C','D','E', 'F', 'G', 'H'};
	private static int[] numbers = {8,7,6,5,4,3,2,1}; 
	
	public static int[] NotationChessToComputer(char pos_w, int pos_h) {
		
		int[] OrderedPair = {Arrays.binarySearch(letters, pos_w), numbers.length + 1 + Arrays.binarySearch(numbers, pos_h)};
				
		return OrderedPair;
		
	}
	public static String NotationComputerToChess(int i, int j) {
		
		String OrderedPair = letters[i] + String.valueOf(numbers[j]);
		
		return OrderedPair;
		
	}
	/*
	public static void main(String[] args) {
		System.out.println(NotationComputerToChess(0, 0));
		int[] retorno = NotationChessToComputer('A', 8);
		System.out.println(retorno[0]+" "+retorno[1]);
		System.out.println(retorno[1]);
	}*/
}
