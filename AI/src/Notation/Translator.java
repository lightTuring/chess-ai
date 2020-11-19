package Notation;


public class Translator {
	
	private static char[] letters = {'A','B','C','D','E', 'F', 'G', 'H'};
	private static int[] numbers = {8,7,6,5,4,3,2,1}; 
		
	public static int NotationChessToComputer(char pos_w, int pos_h) {
			
		int i = 7 - (pos_h - 1);
		int j = 0;
		if (Character.isUpperCase(pos_w)) {
			j = pos_w - 'A';
		}
		else {
			j = pos_w - 'a';
		}
		return (i*8 + j);
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
