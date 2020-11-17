package Bitboards;

public class Teste {
    public static void main(String[] args) {
        Bits bit = new Bits();
        Movements move = new Movements(bit);
        Manipulator.init();
        Manipulator.makeBoards(bit);
        Manipulator.printImage(bit);
        System.out.println(" ");


        long y = move.knightMoves(57);
        System.out.println(Long.toBinaryString(y));
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
        00000100
        00000010
        00000000
        00000000
        00000000
        00000000
        00000000

        */
        
    }
}
