package Run;
import Algorithm.AlphaBeta;
import Notation.Translator;
import Rules.*;

public class Teste {
    public static boolean character(int sq, Bits bit) {
        if (bit.chessBoard[sq/8][sq%8] == 'o') {
            return true;
        }
        return false;
    }
    public static boolean bittt(int sq, Bits bit) {
        if ((~bit.board & 1L<<sq) != 0) {
            return true;
        }
        return false;
    }
    public static String number(String n) {
        while (n.length() < 64) {
            n = "0" + n;
        }
        return n;
    }
    public static void main(String[] args) throws Exception {
        Manipulator.init();
        char[][] chess ={   {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'k',},
                            {'o', 'o', 'o', 'o', 'o', 'o', 'p', 'o',},
                            {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o',},
                            {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o',},
                            {'o', 'o', 'o', 'Q', 'o', 'o', 'o', 'o',},
                            {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o',},
                            {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o',},
                            {'o', 'o', 'o', 'K', 'o', 'o', 'o', 'o',},};
        Bits bit = new Bits(chess);
        bit.turn = true;
        Movements move = new Movements(bit);        
        long[] x = move.uncheckedMoves(bit.turn);
        AlphaBeta ab = new AlphaBeta(bit);
        bit = ab.bestPlaying(0, 4, true);
        Manipulator.printImage(bit);

        /*      
        for (int sq = 0; sq <64; sq++) {
            while (x[sq] != 0L) {
                long lsb = Manipulator.lsb(x[sq]);
                int pos = Manipulator.positionOfBit(lsb);
                Bits copy = bit.clone();
                Manipulator.changePos(sq, pos, copy);
                Manipulator.printImage(copy);
                System.out.println(" ");
                x[sq] = Manipulator.reset(x[sq]);
            }
        }
        00000000
        00000000
        00000001
        00000000
        00000000
        00000000
        00000000
        00000000

        00010000
        00010000
        00010000
        11101100
        00010000
        00010000
        00010000
        00010000
        
        */
        
    }
}
