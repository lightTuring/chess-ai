package Run;
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
    public static void main(String[] args) {
        Manipulator.init();
        char[][] chess ={   {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'k',},
                            {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o',},
                            {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o',},
                            {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o',},
                            {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o',},
                            {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o',},
                            {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o',},
                            {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o',},};
        Bits bit = new Bits(chess);
        Movements move = new Movements(bit);        
        long x = move.kingMoves(7);
        System.out.println(number(Long.toBinaryString(x)));        

        /*
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
