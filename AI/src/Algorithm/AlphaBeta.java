package Algorithm;

import java.util.LinkedList;

import Rules.Bits;
import Rules.Manipulator;
import Rules.Movements;

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
    /*
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

		
    }*/
    
    private double algorithm(int node, int depth, double a, double b, boolean isMaximizing)
            throws Exception {
		if(depth == 0){
            Evaluate e = new Evaluate(gb.getBits(node));
            gb.setWeight(node, e.total());
            gb.removeBit(node);
            return gb.getWeight(node);
        }
        else if (isMaximizing) {
            double value = -inf;
            Bits p = gb.getBits(node);
            Movements move = new Movements(p);
            long[] x = move.uncheckedMoves(p.turn);
			for (int sq = 0; sq<64; sq++) {
                while (x[sq] != 0L) {
                    long lsb = Manipulator.lsb(x[sq]);
                    int pos = Manipulator.positionOfBit(lsb);
                    Bits copy = p.clone();
                    Manipulator.changePos(sq, pos, copy);
                    copy.turn = !copy.turn;
                    if (!(Manipulator.isCheckWhite(copy))) {
                        gb.createGraph(node, copy);
                        value = Math.max(value, algorithm((gb.getCountNodes() - 1), (depth-1), a, b, false));
                        a = Math.max(a, value);
                    }                 
                    x[sq] = Manipulator.reset(x[sq]);
                    if(a >= b) break;
                }
                if(a >= b) break;               
            }
            gb.removeBitIf(node);
            gb.setWeight(node, value);
			return value;
        }
        /*
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
		}*/
		else{
            double value = inf;
            Bits p = gb.getBits(node);
            Movements move = new Movements(p);
            long[] x = move.uncheckedMoves(p.turn);
			for (int sq = 0; sq<64; sq++) {
                while (x[sq] != 0L) {
                    long lsb = Manipulator.lsb(x[sq]);
                    int pos = Manipulator.positionOfBit(lsb);
                    Bits copy = p.clone();
                    Manipulator.changePos(sq, pos, copy);
                    copy.turn = !copy.turn;
                    if (!(Manipulator.isCheckBlack(copy))) {
                        gb.createGraph(node, copy);
                        value = Math.min(value, algorithm((gb.getCountNodes() - 1), (depth-1), a, b, true));
                        b = Math.min(b, value);
                    }
                    x[sq] = Manipulator.reset(x[sq]);
                    if(b <= a) break;	
                }
                if(b <= a) break;                
            }
            gb.removeBitIf(node);
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