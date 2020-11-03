package Algorithm;
import java.util.LinkedList;

import Rules.Board;
import Rules.BoardOutOfBoundsException;
import Rules.Coordinate;
import Rules.Game;
import Rules.IllegalMoveException;
import Rules.UnexpectedPieceException; 

public class AlphaBeta {
    public Board board;
    public GraphBuilder gb;
    private final int inf = 1000000007;

    public AlphaBeta(Board board) {
        this.board = board;
        this.gb = new GraphBuilder();
		this.gb.createGraph(board);
    }

    public GraphBuilder getGraph() {
        return gb;
    }

    private void createSon(int h) throws BoardOutOfBoundsException, UnexpectedPieceException,
            IllegalMoveException, CloneNotSupportedException {			
        Board b = gb.getBoard(h);
        Game g = new Game(b);
        g.allLegal();
        
        LinkedList<Board> list = new LinkedList<Board>();
        for (int i = 0; i<8; i++) {
            for (int j = 0; j<8; j++) {
                for (Coordinate c : g.getStateBoard()[i][j]) {
                    if(c == null) continue;
                    Game copy = (Game)g.clone();
                    if ((b.getTurn() == b.isWhite(i,j)) && b.isAPiece(i, j)) {
                        copy.move(i, j, c.getPos_i(), c.getPos_j());
                        copy.isCheckMateBlack();
                        copy.isCheckMateWhite();
                        list.add(copy.getBoard());
                    }
                    
                }
                for (Coordinate c : g.getPassBoard()[i][j]) {
                    if(c == null) continue;
                    Game copy = (Game)g.clone();
                    if ((b.getTurn() == b.isWhite(i,j)) && b.isAPiece(i, j)) {
                        copy.move(i, j, c.getPos_i(), c.getPos_j());
                        copy.isCheckMateBlack();
                        copy.isCheckMateWhite();
                        list.add(copy.getBoard());
                    }
                }
            }
        }
        if (list.size() != 0) {
            gb.createGraph(h, list);
		}
		
    }
    
    private double algorithm(int node, int depth, double a, double b, boolean isMaximizing)
            throws Exception {
		if(depth == 0){
            if (gb.getBoard(node).isCheckmateBlack) {
                gb.setWeight(node, Integer.MAX_VALUE);
            }
            else if (gb.getBoard(node).isCheckmateWhite) {
                gb.setWeight(node, Integer.MIN_VALUE);
            }
            else {
                Evaluate e = new Evaluate(gb.getBoard(node));
                gb.setWeight(node, e.total());
            }
            return gb.getWeight(node);
        }
		else if(isMaximizing){
            double value = -inf;
            createSon(node);
			for (Integer child : gb.getSon(node)) {
                value = Math.max(value, algorithm(child, (depth-1), a, b, false));	
                a = Math.max(a, value);
                if(a >= b) break;
			}
			gb.setWeight(node, value);
			return value;
		}
		else{
            double value = inf;
            createSon(node);
			for (Integer child : gb.getSon(node)) {
                value = Math.min(value, algorithm(child, (depth-1), a, b, true));
                b = Math.min(b, value);
                if(b <= a) break;	
			}
			gb.setWeight(node, value);
			return value;
		}
		
    }
    public Board bestPlaying(int node, int depth, boolean isMaximizing) throws Exception {

		double search = algorithm(node, depth,(double)-inf, (double)inf, isMaximizing);

		//Zero-based
		LinkedList<Integer> son = gb.getSon(0);
		
		int x=0;

		for(int s : son){
			if(gb.getWeight(s)==search){
				x = s;
				break;
			}
		}
		return gb.getBoard(x);
	}

}