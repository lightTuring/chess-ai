package Notation;

import java.util.Arrays;

public class Translator {
	
	private static char[] letters = {'A','B','C','D','E', 'F', 'G', 'H'};
	private static int[] numbers = {8,7,6,5,4,3,2,1}; 
		
	public static int[] NotationChessToComputer(char pos_w, int pos_h) {
		
		if((int)pos_w > 90)
			pos_w = (char)((int)pos_w - ((int)'a' - (int)'A'));
		
		int[] OrderedPair = {SearchGross(numbers, pos_h), Arrays.binarySearch(letters, pos_w)};
				
		return OrderedPair;
		
	}
	public static String NotationComputerToChess(int i, int j) {
		
		String OrderedPair = letters[j] + String.valueOf(numbers[i]);
		
		return OrderedPair;
		
	}
	private static int SearchGross(int[] list, int e) {
		int element = 0;
		for (int i = 0; i < list.length; i++) {
			if(e == list[i])
				element = i;
		}
		return element;
	}
	/*
	public static void main(String[] args) {
		System.out.println(NotationComputerToChess(0, 0));
		int[] retorno = NotationChessToComputer('e', 1);
		System.out.println(retorno[0]+" "+retorno[1]);
		System.out.println(retorno[1]);
	}
	*/
}
