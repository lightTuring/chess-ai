package Rules;

import java.util.LinkedList;
@SuppressWarnings("unchecked")
public class Controller {

	public static boolean isSquareAttacked(Coordinate c, Board b) throws BoardOutOfBoundsException,
			UnexpectedPieceException, IllegalMoveException {
		LinkedList<Coordinate>[][] list = uncheckedMoves(b);
		for (int i = 0; i<8; i++) {
			for (int j = 0; j<8; j++) {
				for (Coordinate a : list[i][j]) {
					if (a.equals(c) && !b.hasSameColor(i, j, a.getPos_i(), a.getPos_j())) {
						return true;	
					}
				}
			}
		}
            
		return false;
	}
	
	public static boolean isSquareDefended(Coordinate c, Board b) throws BoardOutOfBoundsException,
			UnexpectedPieceException, IllegalMoveException {
		LinkedList<Coordinate>[][] list = uncheckedMoves(b);
		
		for (int i = 0; i<8; i++) {
			for (int j = 0; j<8; j++) {
				for (Coordinate a : list[i][j]) {
					if (a.equals(c) && b.hasSameColor(i, j, a.getPos_i(), a.getPos_j())) {
						return true;	
					}
				}
			}
		
		}
		return false;
	}
	
	public static boolean isSquareAttacked(Coordinate[] c, Board b, boolean turn) throws BoardOutOfBoundsException,UnexpectedPieceException, IllegalMoveException {
		LinkedList<Coordinate>[][] list = uncheckedMoves(b);
		for (int i = 0; i<8; i++) {
			for (int j = 0; j<8; j++) {
				if (b.isWhite(i,j) == turn) {
					for (Coordinate a : list[i][j]) {
						if (a.equals(c[0]) || a.equals(c[1])) {
							return true;	
						}
					}
				}
			}
		}
            
		return false;
	}

  	public static LinkedList<Coordinate> getPawnMoves(Board b, int i, int j) throws UnexpectedPieceException, BoardOutOfBoundsException {
		LinkedList<Coordinate> movesP = new LinkedList<Coordinate>();
		Coordinate x;
		// Se o peão for Branco:
		if (b.getPiece(i, j) == 'P') {
			// Se a casa na diagonal esquerda tiver alguma peça:
			if (!(j-1 < 0 || j-1>=8 || i-1 < 0 || i-1>=8) && b.getPiece(i-1, j-1) != 'o') {
				x = new Coordinate(i-1, j-1);
				movesP.add(x);
			}
				// Se a casa na diagonal direita tiver alguma peça:
			if (!(j+1 < 0 || j+1>=8 || i-1 < 0 || i-1>=8) && b.getPiece(i-1, j+1) != 'o') {
				x = new Coordinate((i-1), (j+1));
				movesP.add(x);
			}
			// Se a casa a frente estiver vazia:
			if (!(i-1 < 0 || i-1>=8) && b.getPiece(i-1, j) == 'o') {
				x = new Coordinate((i-1), (j));
				movesP.add(x);
			}
			// Se no primeiro movimento do peão, a casa na frente da frente estiver vazia:
			if (!(i-2 < 0 || i-2>=8) && b.hasPawnMoved(i, j) == false && b.getPiece(i-2, j) == 'o' && b.getPiece(i-1, j) == 'o') {
				x = new Coordinate((i-2), (j));
				movesP.add(x);
			}
		}
		if (b.getPiece(i, j) == 'p') {
			if (!(j+1 >= 8  || i+1>=8) && b.getPiece(i+1, j+1) != 'o') {
				x = new Coordinate((i+1), (j+1));
				movesP.add(x);
			}
	
			if (!(j-1 < 0 || j-1>=8 || i+1 < 0 || i+1>=8) && b.getPiece(i+1, j-1) != 'o' ) {
				x = new Coordinate((i+1), (j-1));
				movesP.add(x);
			}
	
			if (!(i+1 < 0 || i+1>=8) && b.getPiece(i+1, j) == 'o') {
				x = new Coordinate((i+1), (j));
				movesP.add(x);
			}
			if (!(i+2 < 0 || i+2>=8) && b.hasPawnMoved(i, j) == false && b.getPiece(i+2, j) == 'o' && b.getPiece(i+1, j) == 'o') {
				x = new Coordinate((i+2), (j));
				movesP.add(x);
			}
		}
		
		return movesP;
    }

    public static LinkedList<Coordinate> getRookMoves(Board b, int i, int j) throws BoardOutOfBoundsException {
	    LinkedList<Coordinate> movesP = rookGen(b, i, j);
	    return movesP;
    }

  	public static LinkedList<Coordinate> getKnightMoves(Board b, int pos_i, int pos_j)
			throws BoardOutOfBoundsException {
		LinkedList<Coordinate> movesK = new LinkedList<Coordinate>();
		Coordinate x;
		byte[] addingGeneralCoordinate = {-2, -1, -2, 1, 2, -1, 2, 1, -1, -2, -1, 2, 1, -2, 1, 2};
	    
	    	for(byte i = 0; i < addingGeneralCoordinate.length; i+=2) {
    			if(((pos_i + addingGeneralCoordinate[i]) >= 0 && (pos_i + addingGeneralCoordinate[i]) <= 7) && ((pos_j + addingGeneralCoordinate[i+1]) >= 0 && (pos_j + addingGeneralCoordinate[i+1] <= 7))) {
    				x = new Coordinate((pos_i + addingGeneralCoordinate[i]), (pos_j + addingGeneralCoordinate[i+1]));
        			movesK.add(x);
    			}
    		}
		
		
		return movesK;
	}

    public static LinkedList<Coordinate> getBishopMoves(Board b, int pos_i, int pos_j) throws BoardOutOfBoundsException {
    	return bishopGen(b, pos_i, pos_j);
    }

    public static LinkedList<Coordinate> getKingMoves(Board b, int i, int j) throws BoardOutOfBoundsException {
		/*
		LinkedList<Coordinate> moves = new LinkedList<Coordinate>();
    	Coordinate c;
		for (int x = -1; x<=1 && x>=-1; x++) {
			for (int y = -1; y<=1 && y>=-1; y++) {
				if ((y != 0 || x!=0) && (i + x)<8 && (j+y)<8 && (i + x)>=0 && (j+y)>=0 && (b.getPiece(i+x,j+y) == 'o'||!b.hasSameColor(i, j, i+x, j+y))) {
					c = new Coordinate(i + x, j+y);
					moves.add(c);
				}
			}
		}
		*/
		LinkedList<Coordinate> moves = new LinkedList<Coordinate>();
		int[] l = {1, -1, 0, 0, -1, 1, 1, -1};
		int[] c = {0, 0, 1, -1, -1, 1, -1, 1};

		for(int k = 0; k<l.length; k++){
			int x = i+l[k];
			int y = j+c[k];

			if(x<0||x>=8||x<0||x>=8||y<0||y>=8||b.hasSameColor(i, j, x, y)) continue;

			moves.add(new Coordinate(x, y));

		}

		return moves;
    }

	public static LinkedList<Coordinate> getQueenMoves(Board b, int pos_i, int pos_j) throws BoardOutOfBoundsException {
		LinkedList<Coordinate> moves = new LinkedList<>();
		moves.add(new Coordinate(-1,-1));
		moves = bishopGen(b, pos_i, pos_j);
		LinkedList<Coordinate> list = rookGen(b, pos_i, pos_j);

		for (Coordinate c : list) {
			if(c.getPos_i() != -1) {
				moves.add(c);
			}
		}
		return moves;
	}
		
	private static LinkedList<Coordinate> bishopGen (Board b, int pos_i, int pos_j) throws BoardOutOfBoundsException{
		Coordinate x;
		LinkedList<Coordinate> list = new LinkedList<Coordinate>();
		for (int i = 1; (pos_i + i) < 8; i++) {
			if (pos_j + i < 8 && b.getPiece(pos_i + i, pos_j + i) == 'o') {
				x = new Coordinate(pos_i + i, pos_j + i);
				list.add(x);
			}
			else {
				if (pos_j + i < 8) {
					x = new Coordinate(pos_i + i, pos_j + i);
					list.add(x);
				}
				break;
			}
		}
		for (int i = 1; (pos_i + i) < 8; i++) {
			if (pos_j - i >= 0 && b.getPiece(pos_i + i, pos_j - i) == 'o') {
				x = new Coordinate(pos_i + i, pos_j - i);
				list.add(x);
			}
			else {
				if (pos_j - i >= 0) {
					x = new Coordinate(pos_i + i, pos_j - i);
					list.add(x);
				}
				break;
			}
		}
		for (int i = 1; (pos_i - i) >= 0; i++) {
			if (pos_j + i < 8 && b.getPiece(pos_i - i, pos_j + i) == 'o') {
				x = new Coordinate(pos_i - i, pos_j + i);
				list.add(x);
			}
			else {
				if (pos_j + i < 8) {
					x = new Coordinate(pos_i - i, pos_j + i);
					list.add(x);
				}
				break;
			}
		}
		for (int i = 1; (pos_i - i) >= 0; i++) {
			if ((pos_j - i) >= 0 && b.getPiece(pos_i - i, pos_j - i) == 'o') {
				x = new Coordinate(pos_i - i, pos_j - i);
				list.add(x);
			}
			else {
				if ((pos_j - i) >= 0) {
					x = new Coordinate(pos_i - i, pos_j - i);
					list.add(x);
				}
				break;
			}
		}
		return list;
	}

	

	private static LinkedList<Coordinate> rookGen (Board b, int pos_i, int pos_j) throws BoardOutOfBoundsException{
		LinkedList<Coordinate> moves = new LinkedList<Coordinate>();
		Coordinate x; 
		boolean pathBlocked = false;
		boolean pathBlocked2 = false;
		int t = 0;
		//Para uma fileira
		for (int j = 0; j < pos_j; j++) {
			if(b.getPiece(pos_i, j) != 'o') {
				pathBlocked = true;
				t = j;
			}
		}
		if (pathBlocked == true) {
			x = new Coordinate(pos_i, t);
			moves.add(x);
			
			for (int j = (t+1); j < pos_j; j++) {
				x = new Coordinate(pos_i, j);
				moves.add(x);
			}
		}
		else {
			for (int j = 0; j < pos_j; j++) {
				x = new Coordinate(pos_i, j);
				moves.add(x);
			}
		}
		
		for (int j = (pos_j + 1); j < 8; j++) {
			if (b.getPiece(pos_i, j) == 'o') {
				x = new Coordinate(pos_i, j);
				moves.add(x);
			}
			else {
				x = new Coordinate(pos_i, j);
				moves.add(x);
				break;
			}
		}
		//Para uma coluna 
		for (int i = 0; i<pos_i; i++) {
			if(b.getPiece(i, pos_j) != 'o') {
				pathBlocked2 = true;
				t = i;
			}
		}
		if(pathBlocked2 == true) {
			x = new Coordinate(t, pos_j);
			moves.add(x);
			
			for (int i = (t+1); i < pos_i; i++) {
				x = new Coordinate(i, pos_j);
				moves.add(x);
			}
		}
		else {
			for (int i = 0; i < pos_i; i++) {
				x = new Coordinate(i, pos_j);
				moves.add(x);
			}
		}
		for(int i = (pos_i + 1); i<8; i++) {
			if (b.getPiece(i, pos_j) == 'o') {
				x = new Coordinate(i, pos_j);
				moves.add(x);
			}
			else {
				x = new Coordinate(i, pos_j);			
				moves.add(x);
				break;
			}
		}
		return moves;
	}  

	public static LinkedList<Coordinate>[][] uncheckedMoves(Board b) throws BoardOutOfBoundsException, UnexpectedPieceException,
			IllegalMoveException {
		LinkedList<Coordinate>[][] list = new LinkedList[8][8];
        for (int i = 0; i<8; i++) {
			for (int j = 0; j<8; j++) {
				if (b.getPiece(i, j) != 'o') {
					if (b.getPiece(i, j) == 'P') {
						list[i][j] = Controller.getPawnMoves(b, i, j);
					}

					else if (b.getPiece(i, j) == 'C') {
						list[i][j] = Controller.getKnightMoves(b, i, j);
					}

					else if (b.getPiece(i, j) == 'B') {
						list[i][j] = Controller.getBishopMoves(b, i, j);
					}

					else if (b.getPiece(i, j) == 'T') {
						list[i][j] = Controller.getRookMoves(b, i, j);
					}

					else if (b.getPiece(i, j) == 'Q') {
						list[i][j] = Controller.getQueenMoves(b, i, j);
					}

					else if (b.getPiece(i, j) == 'K') {
						list[i][j] = Controller.getKingMoves(b, i, j);
						
					}
					else if (b.getPiece(i, j) == 'p') {
						list[i][j] = Controller.getPawnMoves(b, i, j);
					}

					else if (b.getPiece(i, j) == 'c') {
						list[i][j] = Controller.getKnightMoves(b, i, j);
					}

					else if (b.getPiece(i, j) == 'b') {
						list[i][j] = Controller.getBishopMoves(b, i, j);
					}

					else if (b.getPiece(i, j) == 't') {
						list[i][j] = Controller.getRookMoves(b, i, j);
					}

					else if (b.getPiece(i, j) == 'q') {
						list[i][j] = Controller.getQueenMoves(b, i, j);
					}

					else if (b.getPiece(i, j) == 'k') {
						list[i][j] = Controller.getKingMoves(b, i, j);
					}
				
				}
				else {
					list[i][j] = new LinkedList<>();
				}
			}
		}
		return list;
		
	}

	public static LinkedList<Coordinate>[] pass (Board b, int i, int j) throws BoardOutOfBoundsException {
		LinkedList<Coordinate> list[] = new LinkedList[2];
		list[0] = new LinkedList<Coordinate>();
		list[1] = new LinkedList<Coordinate>();
		if (b.getLastMove()[0] != null) {
			if (b.getPiece(i, j) == 'P' && i == 3) {
					Coordinate a1 = new Coordinate(1, j+1);
					Coordinate b1 = new Coordinate(1,j-1);
					Coordinate a2 = new Coordinate(3, j+1);
					Coordinate b2 = new Coordinate(3, j-1);
					if ((j+1<8) && b.getLastMove()[0].equals(a1) && b.getLastMove()[1].equals(a2) && b.getPiece(2, j+1) == 'o' && b.getPiece(3, j+1) == 'p') {
						list[0].add(new Coordinate(2, j+1));
						list[1].add(new Coordinate(3, j+1));
					}
					if ((j-1)>=0 && b.getLastMove()[0].equals(b1) && b.getLastMove()[1].equals(b2) && b.getPiece(2, j-1) == 'o' && b.getPiece(3, j-1) == 'p') {
						list[0].add(new Coordinate(2, j-1));
						list[1].add(new Coordinate(3, j-1));
					}
				
			}
			else if (b.getPiece(i, j) == 'p' && i==4) {
					Coordinate a1 = new Coordinate(6, j+1);
					Coordinate b1 = new Coordinate(6,j-1);
					Coordinate a2 = new Coordinate(4, j+1);
					Coordinate b2 = new Coordinate(4, j-1);
					if ((j+1<8) && b.getLastMove()[0].equals(a1) && b.getLastMove()[1].equals(a2) && b.getPiece(5, j+1) == 'o' && b.getPiece(4, j+1) == 'P') {
						list[0].add(new Coordinate(5, j+1));
						list[1].add(new Coordinate(4, j+1));
					}
					if ((j-1)>=0 && b.getLastMove()[0].equals(b1) && b.getLastMove()[1].equals(b2) && b.getPiece(5, j-1) == 'o' && b.getPiece(4, j-1) == 'P') {
						list[0].add(new Coordinate(5, j-1));
						list[1].add(new Coordinate(4, j-1));
					}
			}
		}
		return list;
	}
		
}

