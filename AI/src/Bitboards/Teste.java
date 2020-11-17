package Bitboards;

public class Teste {
    public static void main(String[] args) {
        Bits bit = new Bits();
        Movements move = new Movements(bit);
        Manipulator.init();
        Manipulator.makeBoards(bit);
        Manipulator.printImage(bit);
    }
}
