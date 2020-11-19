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
    public static void main(String[] args) {
        Bits bit = new Bits();
        Movements move = new Movements(bit);
        Manipulator.init();
        Manipulator.makeBoards(bit);
        Manipulator.printImage(bit);
        System.out.println(" ");
        int i = Translator.NotationChessToComputer('e', 4);
        System.out.println(Manipulator.positionOfBit(Long.parseLong("100000",2)));

        

        /*
        00000000
        00000000
        00000001
        00000000
        00000000
        00000000
        00000000
        00000000
        
        00000000
        00000000
        00000000
        00000001
        00000000
        00000000
        00000000
        00000000

        00000000
        00000000
        00000000
        00000000
        00000000
        00000000
        00000000
        11111111

        00000000
        00000000
        00000010
        00000010
        00000000
        00000000
        00000000
        00000000

        00000000
        00000000
        00000000
        00000000
        00000000
        10100101
        11111111
        01010110
        */
        
    }
}
