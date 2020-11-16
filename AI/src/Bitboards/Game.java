package Bitboards;

@SuppressWarnings("unchecked")
public class Game {
    public long[] stateBoard = new long[64];
    public Bits bit = new Bits();

    public Game(Bits bit) {
        this.bit = bit;
    }
}