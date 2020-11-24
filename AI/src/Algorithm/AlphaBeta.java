package Algorithm;
import java.util.LinkedList;

import Rules.Bits;
import Rules.Manipulator;
import Rules.Game;
import Rules.IllegalMoveException;

public class AlphaBeta {
    public Bits bit;
    public GraphBuilder gb;
    private final int inf = 1000000007;

    public AlphaBeta(Bits bit) {
        this.bit = bit;
        this.gb = new GraphBuilder();
		this.gb.createGraph(bit);
    }

    public GraphBuilder getGraph() {
        return gb;
    }
    //otimizar criacao
    private void createSon(int h) throws IllegalMoveException {
        Bits b = gb.getBits(h);
        Game g = new Game(b);
        
        LinkedList<Bits> list = new LinkedList<Bits>();
        if (!(g.isCheckMateBlack() || g.isCheckMateBlack())) {        
            for (int sq = 0; sq<64; sq++) {
                long x = g.stateBoard[sq];  
                while (x != 0L) {
                    long lsb = Manipulator.lsb(x);
                    int pos = Manipulator.positionOfBit(lsb);
                    Bits copy = b.clone();
                    g.move(sq, pos, copy);
                    list.add(copy);
                    x = Manipulator.reset(x);
                }
            }
            if (list.size() != 0) {
                gb.createGraph(h, list);
            }
        }

		
    }
    
    private double algorithm(int node, int depth, double a, double b, boolean isMaximizing)
            throws Exception {
		if(depth == 0 || gb.getBits(node).endOfGame){
            if (gb.getBits(node).checkmateBlack) {
                gb.setWeight(node, Integer.MAX_VALUE);
            }
            else if (gb.getBits(node).checkmateWhite) {
                gb.setWeight(node, Integer.MIN_VALUE);
            }
            else {
                Evaluate e = new Evaluate(gb.getBits(node));
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
    public Bits bestPlaying(int node, int depth, boolean isMaximizing) throws Exception {

		double search = algorithm(node, depth,(double)(-inf), (double)inf, isMaximizing);

		//Zero-based
		LinkedList<Integer> son = gb.getSon(0);
		
		int x=0;

		for(int s : son){
			if(gb.getWeight(s)==search){
				x = s;
				break;
			}
		}
		return gb.getBits(x);
	}

}